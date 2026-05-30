package io.ktor.util;

import kotlin.Metadata;

/* compiled from: PlatformUtils.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006¨\u0006\u0011"}, d2 = {"Lio/ktor/util/PlatformUtils;", "", "()V", "IS_BROWSER", "", "getIS_BROWSER", "()Z", "IS_DEVELOPMENT_MODE", "getIS_DEVELOPMENT_MODE", "IS_JVM", "getIS_JVM", "IS_NATIVE", "getIS_NATIVE", "IS_NEW_MM_ENABLED", "getIS_NEW_MM_ENABLED", "IS_NODE", "getIS_NODE", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class PlatformUtils {
    public static final PlatformUtils INSTANCE = new PlatformUtils();
    private static final boolean IS_BROWSER;
    private static final boolean IS_DEVELOPMENT_MODE;
    private static final boolean IS_JVM;
    private static final boolean IS_NATIVE;
    private static final boolean IS_NEW_MM_ENABLED;
    private static final boolean IS_NODE;

    private PlatformUtils() {
    }

    static {
        IS_BROWSER = PlatformUtilsJvmKt.getPlatform(INSTANCE) == Platform.Browser;
        IS_NODE = PlatformUtilsJvmKt.getPlatform(INSTANCE) == Platform.Node;
        IS_JVM = PlatformUtilsJvmKt.getPlatform(INSTANCE) == Platform.Jvm;
        IS_NATIVE = PlatformUtilsJvmKt.getPlatform(INSTANCE) == Platform.Native;
        IS_DEVELOPMENT_MODE = PlatformUtilsJvmKt.isDevelopmentMode(INSTANCE);
        IS_NEW_MM_ENABLED = PlatformUtilsJvmKt.isNewMemoryModel(INSTANCE);
    }

    public final boolean getIS_BROWSER() {
        return IS_BROWSER;
    }

    public final boolean getIS_NODE() {
        return IS_NODE;
    }

    public final boolean getIS_JVM() {
        return IS_JVM;
    }

    public final boolean getIS_NATIVE() {
        return IS_NATIVE;
    }

    public final boolean getIS_DEVELOPMENT_MODE() {
        return IS_DEVELOPMENT_MODE;
    }

    public final boolean getIS_NEW_MM_ENABLED() {
        return IS_NEW_MM_ENABLED;
    }
}
