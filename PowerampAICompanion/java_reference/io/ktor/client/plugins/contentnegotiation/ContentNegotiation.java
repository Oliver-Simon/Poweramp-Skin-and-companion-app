package io.ktor.client.plugins.contentnegotiation;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.client.HttpClient;
import io.ktor.client.plugins.HttpClientPlugin;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.statement.HttpResponsePipeline;
import io.ktor.http.ContentType;
import io.ktor.http.ContentTypeMatcher;
import io.ktor.http.Url;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.serialization.Configuration;
import io.ktor.serialization.ContentConverter;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.ktor.util.reflect.TypeInfo;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.Charsets;

/* compiled from: ContentNegotiation.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0002\u001f B'\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0010\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ%\u0010\r\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0001H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012JC\u0010\u0013\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\f\b\u0002\u0010\u001a\u001a\u00060\u001bj\u0002`\u001cH\u0080@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eR\u001e\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation;", "", "registrations", "", "Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation$Config$ConverterRegistration;", "ignoredTypes", "", "Lkotlin/reflect/KClass;", "(Ljava/util/List;Ljava/util/Set;)V", "getIgnoredTypes$ktor_client_content_negotiation", "()Ljava/util/Set;", "getRegistrations$ktor_client_content_negotiation", "()Ljava/util/List;", "convertRequest", "request", "Lio/ktor/client/request/HttpRequestBuilder;", "body", "convertRequest$ktor_client_content_negotiation", "(Lio/ktor/client/request/HttpRequestBuilder;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "convertResponse", "requestUrl", "Lio/ktor/http/Url;", "info", "Lio/ktor/util/reflect/TypeInfo;", "responseContentType", "Lio/ktor/http/ContentType;", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "convertResponse$ktor_client_content_negotiation", "(Lio/ktor/http/Url;Lio/ktor/util/reflect/TypeInfo;Ljava/lang/Object;Lio/ktor/http/ContentType;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Config", "Plugin", "ktor-client-content-negotiation"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ContentNegotiation {

    /* renamed from: Plugin, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<ContentNegotiation> key = new AttributeKey<>("ContentNegotiation");
    private final Set<KClass<?>> ignoredTypes;
    private final List<Config.ConverterRegistration> registrations;

    /* JADX WARN: Multi-variable type inference failed */
    public ContentNegotiation(List<Config.ConverterRegistration> registrations, Set<? extends KClass<?>> ignoredTypes) {
        Intrinsics.checkNotNullParameter(registrations, "registrations");
        Intrinsics.checkNotNullParameter(ignoredTypes, "ignoredTypes");
        this.registrations = registrations;
        this.ignoredTypes = ignoredTypes;
    }

    public final List<Config.ConverterRegistration> getRegistrations$ktor_client_content_negotiation() {
        return this.registrations;
    }

    public final Set<KClass<?>> getIgnoredTypes$ktor_client_content_negotiation() {
        return this.ignoredTypes;
    }

    /* compiled from: ContentNegotiation.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0011\u0010\u0013\u001a\u00020\u000e\"\u0006\b\u0000\u0010\u0014\u0018\u0001H\u0086\bJ\u0012\u0010\u0013\u001a\u00020\u000e2\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0005J@\u0010\u0016\u001a\u00020\u000e\"\b\b\u0000\u0010\u0014*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u0002H\u00142\u0017\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u000e0\u001b¢\u0006\u0002\b\u001cH\u0016¢\u0006\u0002\u0010\u001dJF\u0010\u0016\u001a\u00020\u000e\"\b\b\u0000\u0010\u0014*\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u0002H\u00142\u0006\u0010\u001f\u001a\u00020\u00102\u0017\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u000e0\u001b¢\u0006\u0002\b\u001c¢\u0006\u0002\u0010 J\u0011\u0010!\u001a\u00020\u000e\"\u0006\b\u0000\u0010\u0014\u0018\u0001H\u0086\bJ\u0012\u0010!\u001a\u00020\u000e2\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0005R\u001e\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006#"}, d2 = {"Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation$Config;", "Lio/ktor/serialization/Configuration;", "()V", "ignoredTypes", "", "Lkotlin/reflect/KClass;", "getIgnoredTypes$ktor_client_content_negotiation", "()Ljava/util/Set;", "registrations", "", "Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation$Config$ConverterRegistration;", "getRegistrations$ktor_client_content_negotiation", "()Ljava/util/List;", "clearIgnoredTypes", "", "defaultMatcher", "Lio/ktor/http/ContentTypeMatcher;", "pattern", "Lio/ktor/http/ContentType;", "ignoreType", ExifInterface.GPS_DIRECTION_TRUE, "type", "register", "Lio/ktor/serialization/ContentConverter;", "contentType", "converter", "configuration", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/http/ContentType;Lio/ktor/serialization/ContentConverter;Lkotlin/jvm/functions/Function1;)V", "contentTypeToSend", "contentTypeMatcher", "(Lio/ktor/http/ContentType;Lio/ktor/serialization/ContentConverter;Lio/ktor/http/ContentTypeMatcher;Lkotlin/jvm/functions/Function1;)V", "removeIgnoredType", "ConverterRegistration", "ktor-client-content-negotiation"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Config implements Configuration {
        private final Set<KClass<?>> ignoredTypes = CollectionsKt.toMutableSet(SetsKt.plus((Set) DefaultIgnoredTypesJvmKt.getDefaultIgnoredTypes(), (Iterable) ContentNegotiationKt.getDefaultCommonIgnoredTypes()));
        private final List<ConverterRegistration> registrations = new ArrayList();

        /* compiled from: ContentNegotiation.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation$Config$ConverterRegistration;", "", "converter", "Lio/ktor/serialization/ContentConverter;", "contentTypeToSend", "Lio/ktor/http/ContentType;", "contentTypeMatcher", "Lio/ktor/http/ContentTypeMatcher;", "(Lio/ktor/serialization/ContentConverter;Lio/ktor/http/ContentType;Lio/ktor/http/ContentTypeMatcher;)V", "getContentTypeMatcher", "()Lio/ktor/http/ContentTypeMatcher;", "getContentTypeToSend", "()Lio/ktor/http/ContentType;", "getConverter", "()Lio/ktor/serialization/ContentConverter;", "ktor-client-content-negotiation"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes9.dex */
        public static final class ConverterRegistration {
            private final ContentTypeMatcher contentTypeMatcher;
            private final ContentType contentTypeToSend;
            private final ContentConverter converter;

            public ConverterRegistration(ContentConverter converter, ContentType contentTypeToSend, ContentTypeMatcher contentTypeMatcher) {
                Intrinsics.checkNotNullParameter(converter, "converter");
                Intrinsics.checkNotNullParameter(contentTypeToSend, "contentTypeToSend");
                Intrinsics.checkNotNullParameter(contentTypeMatcher, "contentTypeMatcher");
                this.converter = converter;
                this.contentTypeToSend = contentTypeToSend;
                this.contentTypeMatcher = contentTypeMatcher;
            }

            public final ContentConverter getConverter() {
                return this.converter;
            }

            public final ContentType getContentTypeToSend() {
                return this.contentTypeToSend;
            }

            public final ContentTypeMatcher getContentTypeMatcher() {
                return this.contentTypeMatcher;
            }
        }

        public final Set<KClass<?>> getIgnoredTypes$ktor_client_content_negotiation() {
            return this.ignoredTypes;
        }

        public final List<ConverterRegistration> getRegistrations$ktor_client_content_negotiation() {
            return this.registrations;
        }

        @Override // io.ktor.serialization.Configuration
        public <T extends ContentConverter> void register(ContentType contentType, T converter, Function1<? super T, Unit> configuration) {
            Intrinsics.checkNotNullParameter(contentType, "contentType");
            Intrinsics.checkNotNullParameter(converter, "converter");
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            JsonContentTypeMatcher matcher = Intrinsics.areEqual(contentType, ContentType.Application.INSTANCE.getJson()) ? JsonContentTypeMatcher.INSTANCE : defaultMatcher(contentType);
            register(contentType, converter, matcher, configuration);
        }

        public final <T extends ContentConverter> void register(ContentType contentTypeToSend, T converter, ContentTypeMatcher contentTypeMatcher, Function1<? super T, Unit> configuration) {
            Intrinsics.checkNotNullParameter(contentTypeToSend, "contentTypeToSend");
            Intrinsics.checkNotNullParameter(converter, "converter");
            Intrinsics.checkNotNullParameter(contentTypeMatcher, "contentTypeMatcher");
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            configuration.invoke(converter);
            ConverterRegistration registration = new ConverterRegistration(converter, contentTypeToSend, contentTypeMatcher);
            this.registrations.add(registration);
        }

        public final /* synthetic */ <T> void ignoreType() {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            ignoreType(Reflection.getOrCreateKotlinClass(Object.class));
        }

        public final /* synthetic */ <T> void removeIgnoredType() {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            removeIgnoredType(Reflection.getOrCreateKotlinClass(Object.class));
        }

        public final void removeIgnoredType(KClass<?> type) {
            Intrinsics.checkNotNullParameter(type, "type");
            this.ignoredTypes.remove(type);
        }

        public final void ignoreType(KClass<?> type) {
            Intrinsics.checkNotNullParameter(type, "type");
            this.ignoredTypes.add(type);
        }

        public final void clearIgnoredTypes() {
            this.ignoredTypes.clear();
        }

        private final ContentTypeMatcher defaultMatcher(final ContentType pattern) {
            return new ContentTypeMatcher() { // from class: io.ktor.client.plugins.contentnegotiation.ContentNegotiation$Config$defaultMatcher$1
                @Override // io.ktor.http.ContentTypeMatcher
                public boolean contains(ContentType contentType) {
                    Intrinsics.checkNotNullParameter(contentType, "contentType");
                    return contentType.match(ContentType.this);
                }
            };
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0027. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x02aa A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x0261 -> B:12:0x0267). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object convertRequest$ktor_client_content_negotiation(io.ktor.client.request.HttpRequestBuilder r23, java.lang.Object r24, kotlin.coroutines.Continuation<java.lang.Object> r25) {
        /*
            Method dump skipped, instructions count: 814
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.contentnegotiation.ContentNegotiation.convertRequest$ktor_client_content_negotiation(io.ktor.client.request.HttpRequestBuilder, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object convertResponse$ktor_client_content_negotiation$default(ContentNegotiation contentNegotiation, Url url, TypeInfo typeInfo, Object obj, ContentType contentType, Charset charset, Continuation continuation, int i, Object obj2) {
        Charset charset2;
        if ((i & 16) == 0) {
            charset2 = charset;
        } else {
            charset2 = Charsets.UTF_8;
        }
        return contentNegotiation.convertResponse$ktor_client_content_negotiation(url, typeInfo, obj, contentType, charset2, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object convertResponse$ktor_client_content_negotiation(io.ktor.http.Url r20, io.ktor.util.reflect.TypeInfo r21, java.lang.Object r22, io.ktor.http.ContentType r23, java.nio.charset.Charset r24, kotlin.coroutines.Continuation<java.lang.Object> r25) {
        /*
            Method dump skipped, instructions count: 432
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.contentnegotiation.ContentNegotiation.convertResponse$ktor_client_content_negotiation(io.ktor.http.Url, io.ktor.util.reflect.TypeInfo, java.lang.Object, io.ktor.http.ContentType, java.nio.charset.Charset, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: ContentNegotiation.kt */
    @KtorDsl
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0016J!\u0010\u000e\u001a\u00020\u00032\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n0\u0010¢\u0006\u0002\b\u0011H\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation$Config;", "Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation;", "()V", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "", "plugin", "scope", "Lio/ktor/client/HttpClient;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "ktor-client-content-negotiation"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: io.ktor.client.plugins.contentnegotiation.ContentNegotiation$Plugin, reason: from kotlin metadata */
    /* loaded from: classes9.dex */
    public static final class Companion implements HttpClientPlugin<Config, ContentNegotiation> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<ContentNegotiation> getKey() {
            return ContentNegotiation.key;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public ContentNegotiation prepare(Function1<? super Config, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            Config config = new Config();
            block.invoke(config);
            return new ContentNegotiation(config.getRegistrations$ktor_client_content_negotiation(), config.getIgnoredTypes$ktor_client_content_negotiation());
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(ContentNegotiation plugin, HttpClient scope) {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            Intrinsics.checkNotNullParameter(scope, "scope");
            scope.getRequestPipeline().intercept(HttpRequestPipeline.INSTANCE.getTransform(), new ContentNegotiation$Plugin$install$1(plugin, null));
            scope.getResponsePipeline().intercept(HttpResponsePipeline.INSTANCE.getTransform(), new ContentNegotiation$Plugin$install$2(plugin, null));
        }
    }
}
