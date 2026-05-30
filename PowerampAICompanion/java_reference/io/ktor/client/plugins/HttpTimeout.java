package io.ktor.client.plugins;

import com.maxmpz.poweramp.player.RouterConsts;
import io.ktor.client.HttpClient;
import io.ktor.client.engine.HttpClientEngineCapability;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HttpTimeout.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0002\n\u000bB%\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\b\u0010\b\u001a\u00020\tH\u0002R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\f"}, d2 = {"Lio/ktor/client/plugins/HttpTimeout;", "", "requestTimeoutMillis", "", "connectTimeoutMillis", "socketTimeoutMillis", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)V", "Ljava/lang/Long;", "hasNotNullTimeouts", "", "HttpTimeoutCapabilityConfiguration", "Plugin", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpTimeout {
    public static final long INFINITE_TIMEOUT_MS = Long.MAX_VALUE;

    /* renamed from: Plugin, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<HttpTimeout> key = new AttributeKey<>("TimeoutPlugin");
    private final Long connectTimeoutMillis;
    private final Long requestTimeoutMillis;
    private final Long socketTimeoutMillis;

    public /* synthetic */ HttpTimeout(Long l, Long l2, Long l3, DefaultConstructorMarker defaultConstructorMarker) {
        this(l, l2, l3);
    }

    private HttpTimeout(Long requestTimeoutMillis, Long connectTimeoutMillis, Long socketTimeoutMillis) {
        this.requestTimeoutMillis = requestTimeoutMillis;
        this.connectTimeoutMillis = connectTimeoutMillis;
        this.socketTimeoutMillis = socketTimeoutMillis;
    }

    /* compiled from: HttpTimeout.kt */
    @KtorDsl
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB+\b\u0016\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\r\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0016J\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0002¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR(\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR(\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR(\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000f¨\u0006\u001f"}, d2 = {"Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;", "", "requestTimeoutMillis", "", "connectTimeoutMillis", "socketTimeoutMillis", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)V", "_connectTimeoutMillis", "Ljava/lang/Long;", "_requestTimeoutMillis", "_socketTimeoutMillis", "value", "getConnectTimeoutMillis", "()Ljava/lang/Long;", "setConnectTimeoutMillis", "(Ljava/lang/Long;)V", "getRequestTimeoutMillis", "setRequestTimeoutMillis", "getSocketTimeoutMillis", "setSocketTimeoutMillis", "build", "Lio/ktor/client/plugins/HttpTimeout;", "build$ktor_client_core", "checkTimeoutValue", "(Ljava/lang/Long;)Ljava/lang/Long;", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "", "Companion", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class HttpTimeoutCapabilityConfiguration {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final AttributeKey<HttpTimeoutCapabilityConfiguration> key = new AttributeKey<>("TimeoutConfiguration");
        private Long _connectTimeoutMillis;
        private Long _requestTimeoutMillis;
        private Long _socketTimeoutMillis;

        public /* synthetic */ HttpTimeoutCapabilityConfiguration(Long l, Long l2, Long l3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : l, (i & 2) != 0 ? null : l2, (i & 4) != 0 ? null : l3);
        }

        public HttpTimeoutCapabilityConfiguration(Long requestTimeoutMillis, Long connectTimeoutMillis, Long socketTimeoutMillis) {
            this._requestTimeoutMillis = 0L;
            this._connectTimeoutMillis = 0L;
            this._socketTimeoutMillis = 0L;
            setRequestTimeoutMillis(requestTimeoutMillis);
            setConnectTimeoutMillis(connectTimeoutMillis);
            setSocketTimeoutMillis(socketTimeoutMillis);
        }

        /* renamed from: getRequestTimeoutMillis, reason: from getter */
        public final Long get_requestTimeoutMillis() {
            return this._requestTimeoutMillis;
        }

        public final void setRequestTimeoutMillis(Long value) {
            this._requestTimeoutMillis = checkTimeoutValue(value);
        }

        /* renamed from: getConnectTimeoutMillis, reason: from getter */
        public final Long get_connectTimeoutMillis() {
            return this._connectTimeoutMillis;
        }

        public final void setConnectTimeoutMillis(Long value) {
            this._connectTimeoutMillis = checkTimeoutValue(value);
        }

        /* renamed from: getSocketTimeoutMillis, reason: from getter */
        public final Long get_socketTimeoutMillis() {
            return this._socketTimeoutMillis;
        }

        public final void setSocketTimeoutMillis(Long value) {
            this._socketTimeoutMillis = checkTimeoutValue(value);
        }

        public final HttpTimeout build$ktor_client_core() {
            return new HttpTimeout(get_requestTimeoutMillis(), get_connectTimeoutMillis(), get_socketTimeoutMillis(), null);
        }

        private final Long checkTimeoutValue(Long value) {
            if (!(value == null || value.longValue() > 0)) {
                throw new IllegalArgumentException("Only positive timeout values are allowed, for infinite timeout use HttpTimeout.INFINITE_TIMEOUT_MS".toString());
            }
            return value;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || getClass() != other.getClass()) {
                return false;
            }
            if (Intrinsics.areEqual(this._requestTimeoutMillis, ((HttpTimeoutCapabilityConfiguration) other)._requestTimeoutMillis) && Intrinsics.areEqual(this._connectTimeoutMillis, ((HttpTimeoutCapabilityConfiguration) other)._connectTimeoutMillis) && Intrinsics.areEqual(this._socketTimeoutMillis, ((HttpTimeoutCapabilityConfiguration) other)._socketTimeoutMillis)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            Long l = this._requestTimeoutMillis;
            int result = l != null ? l.hashCode() : 0;
            int i = result * 31;
            Long l2 = this._connectTimeoutMillis;
            int result2 = i + (l2 != null ? l2.hashCode() : 0);
            int result3 = result2 * 31;
            Long l3 = this._socketTimeoutMillis;
            return result3 + (l3 != null ? l3.hashCode() : 0);
        }

        /* compiled from: HttpTimeout.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration$Companion;", "", "()V", "key", "Lio/ktor/util/AttributeKey;", "Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes9.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final AttributeKey<HttpTimeoutCapabilityConfiguration> getKey() {
                return HttpTimeoutCapabilityConfiguration.key;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasNotNullTimeouts() {
        return (this.requestTimeoutMillis == null && this.connectTimeoutMillis == null && this.socketTimeoutMillis == null) ? false : true;
    }

    /* compiled from: HttpTimeout.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\b\u0012\u0004\u0012\u00020\u00020\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0005J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J!\u0010\u0011\u001a\u00020\u00032\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\r0\u0013¢\u0006\u0002\b\u0014H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lio/ktor/client/plugins/HttpTimeout$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;", "Lio/ktor/client/plugins/HttpTimeout;", "Lio/ktor/client/engine/HttpClientEngineCapability;", "()V", "INFINITE_TIMEOUT_MS", "", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "", "plugin", "scope", "Lio/ktor/client/HttpClient;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: io.ktor.client.plugins.HttpTimeout$Plugin, reason: from kotlin metadata */
    /* loaded from: classes9.dex */
    public static final class Companion implements HttpClientPlugin<HttpTimeoutCapabilityConfiguration, HttpTimeout>, HttpClientEngineCapability<HttpTimeoutCapabilityConfiguration> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<HttpTimeout> getKey() {
            return HttpTimeout.key;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public HttpTimeout prepare(Function1<? super HttpTimeoutCapabilityConfiguration, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = new HttpTimeoutCapabilityConfiguration(null, null, null, 7, null);
            block.invoke(httpTimeoutCapabilityConfiguration);
            return httpTimeoutCapabilityConfiguration.build$ktor_client_core();
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(HttpTimeout plugin, HttpClient scope) {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            Intrinsics.checkNotNullParameter(scope, "scope");
            ((HttpSend) HttpClientPluginKt.plugin(scope, HttpSend.INSTANCE)).intercept(new HttpTimeout$Plugin$install$1(plugin, scope, null));
        }
    }
}
