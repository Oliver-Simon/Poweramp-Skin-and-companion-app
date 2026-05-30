package org.slf4j;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;
import org.slf4j.event.SubstituteLoggingEvent;
import org.slf4j.helpers.NOP_FallbackServiceProvider;
import org.slf4j.helpers.SubstituteLogger;
import org.slf4j.helpers.SubstituteServiceProvider;
import org.slf4j.helpers.Util;
import org.slf4j.spi.SLF4JServiceProvider;

/* loaded from: classes9.dex */
public final class LoggerFactory {
    static final String CODES_PREFIX = "https://www.slf4j.org/codes.html";
    static final int FAILED_INITIALIZATION = 2;
    static final String IGNORED_BINDINGS_URL = "https://www.slf4j.org/codes.html#ignoredBindings";
    static final String JAVA_VENDOR_PROPERTY = "java.vendor.url";
    static final String LOGGER_NAME_MISMATCH_URL = "https://www.slf4j.org/codes.html#loggerNameMismatch";
    static final String MULTIPLE_BINDINGS_URL = "https://www.slf4j.org/codes.html#multiple_bindings";
    static final int NOP_FALLBACK_INITIALIZATION = 4;
    static final String NO_PROVIDERS_URL = "https://www.slf4j.org/codes.html#noProviders";
    static final int ONGOING_INITIALIZATION = 1;
    static volatile SLF4JServiceProvider PROVIDER = null;
    public static final String PROVIDER_PROPERTY_KEY = "slf4j.provider";
    static final String REPLAY_URL = "https://www.slf4j.org/codes.html#replay";
    private static final String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";
    static final String SUBSTITUTE_LOGGER_URL = "https://www.slf4j.org/codes.html#substituteLogger";
    static final int SUCCESSFUL_INITIALIZATION = 3;
    static final int UNINITIALIZED = 0;
    static final String UNSUCCESSFUL_INIT_MSG = "org.slf4j.LoggerFactory in failed state. Original exception was thrown EARLIER. See also https://www.slf4j.org/codes.html#unsuccessfulInit";
    static final String UNSUCCESSFUL_INIT_URL = "https://www.slf4j.org/codes.html#unsuccessfulInit";
    static final String VERSION_MISMATCH = "https://www.slf4j.org/codes.html#version_mismatch";
    static volatile int INITIALIZATION_STATE = 0;
    static final SubstituteServiceProvider SUBST_PROVIDER = new SubstituteServiceProvider();
    static final NOP_FallbackServiceProvider NOP_FALLBACK_SERVICE_PROVIDER = new NOP_FallbackServiceProvider();
    static final String DETECT_LOGGER_NAME_MISMATCH_PROPERTY = "slf4j.detectLoggerNameMismatch";
    static boolean DETECT_LOGGER_NAME_MISMATCH = Util.safeGetBooleanSystemProperty(DETECT_LOGGER_NAME_MISMATCH_PROPERTY);
    private static final String[] API_COMPATIBILITY_LIST = {"2.0"};

    static List<SLF4JServiceProvider> findServiceProviders() {
        List<SLF4JServiceProvider> providerList = new ArrayList<>();
        ClassLoader classLoaderOfLoggerFactory = LoggerFactory.class.getClassLoader();
        SLF4JServiceProvider explicitProvider = loadExplicitlySpecified(classLoaderOfLoggerFactory);
        if (explicitProvider != null) {
            providerList.add(explicitProvider);
            return providerList;
        }
        ServiceLoader<SLF4JServiceProvider> serviceLoader = getServiceLoader(classLoaderOfLoggerFactory);
        Iterator<SLF4JServiceProvider> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            safelyInstantiate(providerList, iterator);
        }
        return providerList;
    }

    private static ServiceLoader<SLF4JServiceProvider> getServiceLoader(final ClassLoader classLoaderOfLoggerFactory) {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager == null) {
            ServiceLoader<SLF4JServiceProvider> serviceLoader = ServiceLoader.load(SLF4JServiceProvider.class, classLoaderOfLoggerFactory);
            return serviceLoader;
        }
        PrivilegedAction<ServiceLoader<SLF4JServiceProvider>> action = new PrivilegedAction() { // from class: org.slf4j.LoggerFactory$$ExternalSyntheticLambda0
            @Override // java.security.PrivilegedAction
            public final Object run() {
                ServiceLoader load;
                load = ServiceLoader.load(SLF4JServiceProvider.class, classLoaderOfLoggerFactory);
                return load;
            }
        };
        ServiceLoader<SLF4JServiceProvider> serviceLoader2 = (ServiceLoader) AccessController.doPrivileged(action);
        return serviceLoader2;
    }

    private static void safelyInstantiate(List<SLF4JServiceProvider> providerList, Iterator<SLF4JServiceProvider> iterator) {
        try {
            SLF4JServiceProvider provider = iterator.next();
            providerList.add(provider);
        } catch (ServiceConfigurationError e) {
            Util.report("A SLF4J service provider failed to instantiate:\n" + e.getMessage());
        }
    }

    private LoggerFactory() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void reset() {
        INITIALIZATION_STATE = 0;
    }

    private static final void performInitialization() {
        bind();
        if (INITIALIZATION_STATE == 3) {
            versionSanityCheck();
        }
    }

    private static final void bind() {
        try {
            List<SLF4JServiceProvider> providersList = findServiceProviders();
            reportMultipleBindingAmbiguity(providersList);
            if (providersList != null && !providersList.isEmpty()) {
                PROVIDER = providersList.get(0);
                PROVIDER.initialize();
                INITIALIZATION_STATE = 3;
                reportActualBinding(providersList);
            } else {
                INITIALIZATION_STATE = 4;
                Util.report("No SLF4J providers were found.");
                Util.report("Defaulting to no-operation (NOP) logger implementation");
                Util.report("See https://www.slf4j.org/codes.html#noProviders for further details.");
                Set<URL> staticLoggerBinderPathSet = findPossibleStaticLoggerBinderPathSet();
                reportIgnoredStaticLoggerBinders(staticLoggerBinderPathSet);
            }
            postBindCleanUp();
        } catch (Exception e) {
            failedBinding(e);
            throw new IllegalStateException("Unexpected initialization failure", e);
        }
    }

    static SLF4JServiceProvider loadExplicitlySpecified(ClassLoader classLoader) {
        String explicitlySpecified = System.getProperty(PROVIDER_PROPERTY_KEY);
        if (explicitlySpecified == null || explicitlySpecified.isEmpty()) {
            return null;
        }
        try {
            String message = String.format("Attempting to load provider \"%s\" specified via \"%s\" system property", explicitlySpecified, PROVIDER_PROPERTY_KEY);
            Util.report(message);
            Class<?> clazz = classLoader.loadClass(explicitlySpecified);
            Constructor<?> constructor = clazz.getConstructor(new Class[0]);
            Object provider = constructor.newInstance(new Object[0]);
            return (SLF4JServiceProvider) provider;
        } catch (ClassCastException e) {
            String message2 = String.format("Specified SLF4JServiceProvider (%s) does not implement SLF4JServiceProvider interface", explicitlySpecified);
            Util.report(message2, e);
            return null;
        } catch (ClassNotFoundException e2) {
            e = e2;
            String message3 = String.format("Failed to instantiate the specified SLF4JServiceProvider (%s)", explicitlySpecified);
            Util.report(message3, e);
            return null;
        } catch (IllegalAccessException e3) {
            e = e3;
            String message32 = String.format("Failed to instantiate the specified SLF4JServiceProvider (%s)", explicitlySpecified);
            Util.report(message32, e);
            return null;
        } catch (InstantiationException e4) {
            e = e4;
            String message322 = String.format("Failed to instantiate the specified SLF4JServiceProvider (%s)", explicitlySpecified);
            Util.report(message322, e);
            return null;
        } catch (NoSuchMethodException e5) {
            e = e5;
            String message3222 = String.format("Failed to instantiate the specified SLF4JServiceProvider (%s)", explicitlySpecified);
            Util.report(message3222, e);
            return null;
        } catch (InvocationTargetException e6) {
            e = e6;
            String message32222 = String.format("Failed to instantiate the specified SLF4JServiceProvider (%s)", explicitlySpecified);
            Util.report(message32222, e);
            return null;
        }
    }

    private static void reportIgnoredStaticLoggerBinders(Set<URL> staticLoggerBinderPathSet) {
        if (staticLoggerBinderPathSet.isEmpty()) {
            return;
        }
        Util.report("Class path contains SLF4J bindings targeting slf4j-api versions 1.7.x or earlier.");
        for (URL path : staticLoggerBinderPathSet) {
            Util.report("Ignoring binding found at [" + path + "]");
        }
        Util.report("See https://www.slf4j.org/codes.html#ignoredBindings for an explanation.");
    }

    static Set<URL> findPossibleStaticLoggerBinderPathSet() {
        Enumeration<URL> paths;
        Set<URL> staticLoggerBinderPathSet = new LinkedHashSet<>();
        try {
            ClassLoader loggerFactoryClassLoader = LoggerFactory.class.getClassLoader();
            if (loggerFactoryClassLoader == null) {
                paths = ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH);
            } else {
                paths = loggerFactoryClassLoader.getResources(STATIC_LOGGER_BINDER_PATH);
            }
            while (paths.hasMoreElements()) {
                URL path = paths.nextElement();
                staticLoggerBinderPathSet.add(path);
            }
        } catch (IOException ioe) {
            Util.report("Error getting resources from path", ioe);
        }
        return staticLoggerBinderPathSet;
    }

    private static void postBindCleanUp() {
        fixSubstituteLoggers();
        replayEvents();
        SUBST_PROVIDER.getSubstituteLoggerFactory().clear();
    }

    private static void fixSubstituteLoggers() {
        synchronized (SUBST_PROVIDER) {
            SUBST_PROVIDER.getSubstituteLoggerFactory().postInitialization();
            for (SubstituteLogger substLogger : SUBST_PROVIDER.getSubstituteLoggerFactory().getLoggers()) {
                Logger logger = getLogger(substLogger.getName());
                substLogger.setDelegate(logger);
            }
        }
    }

    static void failedBinding(Throwable t) {
        INITIALIZATION_STATE = 2;
        Util.report("Failed to instantiate SLF4J LoggerFactory", t);
    }

    /* JADX WARN: Incorrect condition in loop: B:3:0x001c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void replayEvents() {
        /*
            org.slf4j.helpers.SubstituteServiceProvider r0 = org.slf4j.LoggerFactory.SUBST_PROVIDER
            org.slf4j.helpers.SubstituteLoggerFactory r0 = r0.getSubstituteLoggerFactory()
            java.util.concurrent.LinkedBlockingQueue r0 = r0.getEventQueue()
            int r1 = r0.size()
            r2 = 0
            r3 = 128(0x80, float:1.8E-43)
            java.util.ArrayList r4 = new java.util.ArrayList
            r5 = 128(0x80, float:1.8E-43)
            r4.<init>(r5)
        L18:
            int r6 = r0.drainTo(r4, r5)
            if (r6 != 0) goto L20
        L1f:
            return
        L20:
            java.util.Iterator r7 = r4.iterator()
        L24:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L3c
            java.lang.Object r8 = r7.next()
            org.slf4j.event.SubstituteLoggingEvent r8 = (org.slf4j.event.SubstituteLoggingEvent) r8
            replaySingleEvent(r8)
            int r9 = r2 + 1
            if (r2 != 0) goto L3a
            emitReplayOrSubstituionWarning(r8, r1)
        L3a:
            r2 = r9
            goto L24
        L3c:
            r4.clear()
            goto L18
        */
        throw new UnsupportedOperationException("Method not decompiled: org.slf4j.LoggerFactory.replayEvents():void");
    }

    private static void emitReplayOrSubstituionWarning(SubstituteLoggingEvent event, int queueSize) {
        if (event.getLogger().isDelegateEventAware()) {
            emitReplayWarning(queueSize);
        } else if (!event.getLogger().isDelegateNOP()) {
            emitSubstitutionWarning();
        }
    }

    private static void replaySingleEvent(SubstituteLoggingEvent event) {
        if (event == null) {
            return;
        }
        SubstituteLogger substLogger = event.getLogger();
        String loggerName = substLogger.getName();
        if (substLogger.isDelegateNull()) {
            throw new IllegalStateException("Delegate logger cannot be null at this state.");
        }
        if (!substLogger.isDelegateNOP()) {
            if (substLogger.isDelegateEventAware()) {
                if (substLogger.isEnabledForLevel(event.getLevel())) {
                    substLogger.log(event);
                    return;
                }
                return;
            }
            Util.report(loggerName);
        }
    }

    private static void emitSubstitutionWarning() {
        Util.report("The following set of substitute loggers may have been accessed");
        Util.report("during the initialization phase. Logging calls during this");
        Util.report("phase were not honored. However, subsequent logging calls to these");
        Util.report("loggers will work as normally expected.");
        Util.report("See also https://www.slf4j.org/codes.html#substituteLogger");
    }

    private static void emitReplayWarning(int eventCount) {
        Util.report("A number (" + eventCount + ") of logging calls during the initialization phase have been intercepted and are");
        Util.report("now being replayed. These are subject to the filtering rules of the underlying logging system.");
        Util.report("See also https://www.slf4j.org/codes.html#replay");
    }

    private static final void versionSanityCheck() {
        try {
            String requested = PROVIDER.getRequestedApiVersion();
            boolean match = false;
            for (String aAPI_COMPATIBILITY_LIST : API_COMPATIBILITY_LIST) {
                if (requested.startsWith(aAPI_COMPATIBILITY_LIST)) {
                    match = true;
                }
            }
            if (!match) {
                Util.report("The requested version " + requested + " by your slf4j provider is not compatible with " + Arrays.asList(API_COMPATIBILITY_LIST).toString());
                Util.report("See https://www.slf4j.org/codes.html#version_mismatch for further details.");
            }
        } catch (NoSuchFieldError e) {
        } catch (Throwable e2) {
            Util.report("Unexpected problem occurred during version sanity check", e2);
        }
    }

    private static boolean isAmbiguousProviderList(List<SLF4JServiceProvider> providerList) {
        return providerList.size() > 1;
    }

    private static void reportMultipleBindingAmbiguity(List<SLF4JServiceProvider> providerList) {
        if (isAmbiguousProviderList(providerList)) {
            Util.report("Class path contains multiple SLF4J providers.");
            for (SLF4JServiceProvider provider : providerList) {
                Util.report("Found provider [" + provider + "]");
            }
            Util.report("See https://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
        }
    }

    private static void reportActualBinding(List<SLF4JServiceProvider> providerList) {
        if (!providerList.isEmpty() && isAmbiguousProviderList(providerList)) {
            Util.report("Actual provider is of type [" + providerList.get(0) + "]");
        }
    }

    public static Logger getLogger(String name) {
        ILoggerFactory iLoggerFactory = getILoggerFactory();
        return iLoggerFactory.getLogger(name);
    }

    public static Logger getLogger(Class<?> clazz) {
        Class<?> autoComputedCallingClass;
        Logger logger = getLogger(clazz.getName());
        if (DETECT_LOGGER_NAME_MISMATCH && (autoComputedCallingClass = Util.getCallingClass()) != null && nonMatchingClasses(clazz, autoComputedCallingClass)) {
            Util.report(String.format("Detected logger name mismatch. Given name: \"%s\"; computed name: \"%s\".", logger.getName(), autoComputedCallingClass.getName()));
            Util.report("See https://www.slf4j.org/codes.html#loggerNameMismatch for an explanation");
        }
        return logger;
    }

    private static boolean nonMatchingClasses(Class<?> clazz, Class<?> autoComputedCallingClass) {
        return !autoComputedCallingClass.isAssignableFrom(clazz);
    }

    public static ILoggerFactory getILoggerFactory() {
        return getProvider().getLoggerFactory();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SLF4JServiceProvider getProvider() {
        if (INITIALIZATION_STATE == 0) {
            synchronized (LoggerFactory.class) {
                if (INITIALIZATION_STATE == 0) {
                    INITIALIZATION_STATE = 1;
                    performInitialization();
                }
            }
        }
        switch (INITIALIZATION_STATE) {
            case 1:
                return SUBST_PROVIDER;
            case 2:
                throw new IllegalStateException(UNSUCCESSFUL_INIT_MSG);
            case 3:
                return PROVIDER;
            case 4:
                return NOP_FALLBACK_SERVICE_PROVIDER;
            default:
                throw new IllegalStateException("Unreachable code");
        }
    }
}
