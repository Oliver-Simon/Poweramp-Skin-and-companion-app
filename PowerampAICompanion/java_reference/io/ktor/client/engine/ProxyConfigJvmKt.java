package io.ktor.client.engine;

import java.net.Proxy;
import java.net.SocketAddress;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProxyConfigJvm.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0006\u001a\u00060\u0007j\u0002`\b*\u00060\u0002j\u0002`\u0003\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005*\n\u0010\t\"\u00020\u00022\u00020\u0002¨\u0006\n"}, d2 = {"type", "Lio/ktor/client/engine/ProxyType;", "Ljava/net/Proxy;", "Lio/ktor/client/engine/ProxyConfig;", "getType", "(Ljava/net/Proxy;)Lio/ktor/client/engine/ProxyType;", "resolveAddress", "Ljava/net/SocketAddress;", "Lio/ktor/util/network/NetworkAddress;", "ProxyConfig", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ProxyConfigJvmKt {

    /* compiled from: ProxyConfigJvm.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Proxy.Type.values().length];
            try {
                iArr[Proxy.Type.SOCKS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Proxy.Type.HTTP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final SocketAddress resolveAddress(Proxy $this$resolveAddress) {
        Intrinsics.checkNotNullParameter($this$resolveAddress, "<this>");
        SocketAddress address = $this$resolveAddress.address();
        Intrinsics.checkNotNullExpressionValue(address, "address()");
        return address;
    }

    public static final ProxyType getType(Proxy $this$type) {
        Intrinsics.checkNotNullParameter($this$type, "<this>");
        Proxy.Type type = $this$type.type();
        switch (type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                return ProxyType.SOCKS;
            case 2:
                return ProxyType.HTTP;
            default:
                return ProxyType.UNKNOWN;
        }
    }
}
