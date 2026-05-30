package io.ktor.websocket;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.util.AttributeKey;
import io.ktor.websocket.Frame;
import io.ktor.websocket.internals.DeflaterUtilsKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: WebSocketDeflateExtension.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 #2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002#$B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0016\u0010\u001b\u001a\u00020\f2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016J\u0010\u0010 \u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016J\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016R\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\u00020\u00010\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000e\"\u0004\b\u0015\u0010\u0010R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006%"}, d2 = {"Lio/ktor/websocket/WebSocketDeflateExtension;", "Lio/ktor/websocket/WebSocketExtension;", "Lio/ktor/websocket/WebSocketDeflateExtension$Config;", "config", "(Lio/ktor/websocket/WebSocketDeflateExtension$Config;)V", "deflater", "Ljava/util/zip/Deflater;", "factory", "Lio/ktor/websocket/WebSocketExtensionFactory;", "getFactory", "()Lio/ktor/websocket/WebSocketExtensionFactory;", "incomingNoContextTakeover", "", "getIncomingNoContextTakeover$ktor_websockets", "()Z", "setIncomingNoContextTakeover$ktor_websockets", "(Z)V", "inflater", "Ljava/util/zip/Inflater;", "outgoingNoContextTakeover", "getOutgoingNoContextTakeover$ktor_websockets", "setOutgoingNoContextTakeover$ktor_websockets", "protocols", "", "Lio/ktor/websocket/WebSocketExtensionHeader;", "getProtocols", "()Ljava/util/List;", "clientNegotiation", "negotiatedProtocols", "processIncomingFrame", "Lio/ktor/websocket/Frame;", TypedValues.AttributesType.S_FRAME, "processOutgoingFrame", "serverNegotiation", "requestedProtocols", "Companion", "Config", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class WebSocketDeflateExtension implements WebSocketExtension<Config> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<WebSocketDeflateExtension> key = new AttributeKey<>("WebsocketDeflateExtension");
    private static final boolean rsv1 = true;
    private static final boolean rsv2 = false;
    private static final boolean rsv3 = false;
    private final Config config;
    private final Deflater deflater;
    private final WebSocketExtensionFactory<Config, ? extends WebSocketExtension<Config>> factory;
    private boolean incomingNoContextTakeover;
    private final Inflater inflater;
    private boolean outgoingNoContextTakeover;
    private final List<WebSocketExtensionHeader> protocols;

    public WebSocketDeflateExtension(Config config) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
        this.factory = INSTANCE;
        this.protocols = this.config.build$ktor_websockets();
        this.inflater = new Inflater(true);
        this.deflater = new Deflater(this.config.getCompressionLevel(), true);
    }

    @Override // io.ktor.websocket.WebSocketExtension
    public WebSocketExtensionFactory<Config, ? extends WebSocketExtension<Config>> getFactory() {
        return this.factory;
    }

    @Override // io.ktor.websocket.WebSocketExtension
    public List<WebSocketExtensionHeader> getProtocols() {
        return this.protocols;
    }

    /* renamed from: getOutgoingNoContextTakeover$ktor_websockets, reason: from getter */
    public final boolean getOutgoingNoContextTakeover() {
        return this.outgoingNoContextTakeover;
    }

    public final void setOutgoingNoContextTakeover$ktor_websockets(boolean z) {
        this.outgoingNoContextTakeover = z;
    }

    /* renamed from: getIncomingNoContextTakeover$ktor_websockets, reason: from getter */
    public final boolean getIncomingNoContextTakeover() {
        return this.incomingNoContextTakeover;
    }

    public final void setIncomingNoContextTakeover$ktor_websockets(boolean z) {
        this.incomingNoContextTakeover = z;
    }

    @Override // io.ktor.websocket.WebSocketExtension
    public boolean clientNegotiation(List<WebSocketExtensionHeader> negotiatedProtocols) {
        Object obj;
        Intrinsics.checkNotNullParameter(negotiatedProtocols, "negotiatedProtocols");
        Iterator<T> it = negotiatedProtocols.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            WebSocketExtensionHeader it2 = (WebSocketExtensionHeader) obj;
            if (Intrinsics.areEqual(it2.getName(), "permessage-deflate")) {
                break;
            }
        }
        WebSocketExtensionHeader protocol = (WebSocketExtensionHeader) obj;
        if (protocol == null) {
            return false;
        }
        this.incomingNoContextTakeover = this.config.getServerNoContextTakeOver();
        this.outgoingNoContextTakeover = this.config.getClientNoContextTakeOver();
        Iterator<Pair<String, String>> it3 = protocol.parseParameters().iterator();
        while (true) {
            if (!it3.hasNext()) {
                return true;
            }
            Pair<String, String> next = it3.next();
            String key2 = next.component1();
            String value = next.component2();
            switch (key2.hashCode()) {
                case -708713803:
                    if (!key2.equals("client_no_context_takeover")) {
                        continue;
                    } else {
                        if (!StringsKt.isBlank(value)) {
                            throw new IllegalStateException(("WebSocket permessage-deflate extension parameter client_no_context_takeover shouldn't have a value. Current: " + value).toString());
                        }
                        this.outgoingNoContextTakeover = true;
                        break;
                    }
                case 646404390:
                    if (key2.equals("client_max_window_bits") && !StringsKt.isBlank(value)) {
                        if (!(Integer.parseInt(value) == 15)) {
                            throw new IllegalStateException("Only 15 window size is supported.".toString());
                        }
                        break;
                    }
                    break;
                case 1266201133:
                    if (!key2.equals("server_no_context_takeover")) {
                        continue;
                    } else {
                        if (!StringsKt.isBlank(value)) {
                            throw new IllegalStateException(("WebSocket permessage-deflate extension parameter server_no_context_takeover shouldn't have a value. Current: " + value).toString());
                        }
                        this.incomingNoContextTakeover = true;
                        break;
                    }
                case 2034279582:
                    key2.equals("server_max_window_bits");
                    break;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:16:0x0071. Please report as an issue. */
    @Override // io.ktor.websocket.WebSocketExtension
    public List<WebSocketExtensionHeader> serverNegotiation(List<WebSocketExtensionHeader> requestedProtocols) {
        Object obj;
        Intrinsics.checkNotNullParameter(requestedProtocols, "requestedProtocols");
        Iterator<T> it = requestedProtocols.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            WebSocketExtensionHeader it2 = (WebSocketExtensionHeader) obj;
            if (Intrinsics.areEqual(it2.getName(), "permessage-deflate")) {
                break;
            }
        }
        WebSocketExtensionHeader protocol = (WebSocketExtensionHeader) obj;
        if (protocol == null) {
            return CollectionsKt.emptyList();
        }
        List parameters = new ArrayList();
        for (Pair<String, String> pair : protocol.parseParameters()) {
            String key2 = pair.component1();
            String value = pair.component2();
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
            String lowerCase = key2.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            switch (lowerCase.hashCode()) {
                case -708713803:
                    if (lowerCase.equals("client_no_context_takeover")) {
                        if (!StringsKt.isBlank(value)) {
                            throw new IllegalStateException("Check failed.".toString());
                        }
                        this.incomingNoContextTakeover = true;
                        parameters.add("client_no_context_takeover");
                    } else {
                        throw new IllegalStateException(("Unsupported extension parameter: (" + key2 + ", " + value + ')').toString());
                    }
                case 646404390:
                    if (!lowerCase.equals("client_max_window_bits")) {
                        throw new IllegalStateException(("Unsupported extension parameter: (" + key2 + ", " + value + ')').toString());
                    }
                case 1266201133:
                    if (lowerCase.equals("server_no_context_takeover")) {
                        if (!StringsKt.isBlank(value)) {
                            throw new IllegalStateException("Check failed.".toString());
                        }
                        this.outgoingNoContextTakeover = true;
                        parameters.add("server_no_context_takeover");
                    } else {
                        throw new IllegalStateException(("Unsupported extension parameter: (" + key2 + ", " + value + ')').toString());
                    }
                case 2034279582:
                    if (lowerCase.equals("server_max_window_bits")) {
                        if (!(Integer.parseInt(value) == 15)) {
                            throw new IllegalStateException("Only 15 window size is supported".toString());
                        }
                    } else {
                        throw new IllegalStateException(("Unsupported extension parameter: (" + key2 + ", " + value + ')').toString());
                    }
                default:
                    throw new IllegalStateException(("Unsupported extension parameter: (" + key2 + ", " + value + ')').toString());
            }
        }
        return CollectionsKt.listOf(new WebSocketExtensionHeader("permessage-deflate", parameters));
    }

    @Override // io.ktor.websocket.WebSocketExtension
    public Frame processOutgoingFrame(Frame frame) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        if ((!(frame instanceof Frame.Text) && !(frame instanceof Frame.Binary)) || !this.config.getCompressCondition$ktor_websockets().invoke(frame).booleanValue()) {
            return frame;
        }
        byte[] deflated = DeflaterUtilsKt.deflateFully(this.deflater, frame.getData());
        if (this.outgoingNoContextTakeover) {
            this.deflater.reset();
        }
        return Frame.INSTANCE.byType(frame.getFin(), frame.getFrameType(), deflated, rsv1, frame.getRsv2(), frame.getRsv3());
    }

    @Override // io.ktor.websocket.WebSocketExtension
    public Frame processIncomingFrame(Frame frame) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        if (!frame.getRsv1() || (!(frame instanceof Frame.Text) && !(frame instanceof Frame.Binary))) {
            return frame;
        }
        byte[] inflated = DeflaterUtilsKt.inflateFully(this.inflater, frame.getData());
        if (this.incomingNoContextTakeover) {
            this.inflater.reset();
        }
        return Frame.INSTANCE.byType(frame.getFin(), frame.getFrameType(), inflated, !rsv1, frame.getRsv2(), frame.getRsv3());
    }

    /* compiled from: WebSocketDeflateExtension.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00180 H\u0000¢\u0006\u0002\b!J)\u0010\"\u001a\u00020\u00192!\u0010#\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u00040\nJ\u000e\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u0011J/\u0010)\u001a\u00020\u00192'\u0010#\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u00190\nR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR&\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R,\u0010\u0016\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u0017\u0012\u0004\u0012\u00020\u00190\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000fR\u001a\u0010\u001c\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0006\"\u0004\b\u001e\u0010\b¨\u0006+"}, d2 = {"Lio/ktor/websocket/WebSocketDeflateExtension$Config;", "", "()V", "clientNoContextTakeOver", "", "getClientNoContextTakeOver", "()Z", "setClientNoContextTakeOver", "(Z)V", "compressCondition", "Lkotlin/Function1;", "Lio/ktor/websocket/Frame;", "getCompressCondition$ktor_websockets", "()Lkotlin/jvm/functions/Function1;", "setCompressCondition$ktor_websockets", "(Lkotlin/jvm/functions/Function1;)V", "compressionLevel", "", "getCompressionLevel", "()I", "setCompressionLevel", "(I)V", "manualConfig", "", "Lio/ktor/websocket/WebSocketExtensionHeader;", "", "getManualConfig$ktor_websockets", "setManualConfig$ktor_websockets", "serverNoContextTakeOver", "getServerNoContextTakeOver", "setServerNoContextTakeOver", "build", "", "build$ktor_websockets", "compressIf", "block", "Lkotlin/ParameterName;", "name", TypedValues.AttributesType.S_FRAME, "compressIfBiggerThan", "bytes", "configureProtocols", "protocols", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Config {
        private boolean clientNoContextTakeOver;
        private boolean serverNoContextTakeOver;
        private int compressionLevel = -1;
        private Function1<? super List<WebSocketExtensionHeader>, Unit> manualConfig = new Function1<List<WebSocketExtensionHeader>, Unit>() { // from class: io.ktor.websocket.WebSocketDeflateExtension$Config$manualConfig$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<WebSocketExtensionHeader> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<WebSocketExtensionHeader> it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }
        };
        private Function1<? super Frame, Boolean> compressCondition = new Function1<Frame, Boolean>() { // from class: io.ktor.websocket.WebSocketDeflateExtension$Config$compressCondition$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Frame it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return true;
            }
        };

        public final boolean getClientNoContextTakeOver() {
            return this.clientNoContextTakeOver;
        }

        public final void setClientNoContextTakeOver(boolean z) {
            this.clientNoContextTakeOver = z;
        }

        public final boolean getServerNoContextTakeOver() {
            return this.serverNoContextTakeOver;
        }

        public final void setServerNoContextTakeOver(boolean z) {
            this.serverNoContextTakeOver = z;
        }

        public final int getCompressionLevel() {
            return this.compressionLevel;
        }

        public final void setCompressionLevel(int i) {
            this.compressionLevel = i;
        }

        public final Function1<List<WebSocketExtensionHeader>, Unit> getManualConfig$ktor_websockets() {
            return this.manualConfig;
        }

        public final void setManualConfig$ktor_websockets(Function1<? super List<WebSocketExtensionHeader>, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "<set-?>");
            this.manualConfig = function1;
        }

        public final Function1<Frame, Boolean> getCompressCondition$ktor_websockets() {
            return this.compressCondition;
        }

        public final void setCompressCondition$ktor_websockets(Function1<? super Frame, Boolean> function1) {
            Intrinsics.checkNotNullParameter(function1, "<set-?>");
            this.compressCondition = function1;
        }

        public final void configureProtocols(final Function1<? super List<WebSocketExtensionHeader>, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            final Function1 old = this.manualConfig;
            this.manualConfig = new Function1<List<WebSocketExtensionHeader>, Unit>() { // from class: io.ktor.websocket.WebSocketDeflateExtension$Config$configureProtocols$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<WebSocketExtensionHeader> list) {
                    invoke2(list);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(List<WebSocketExtensionHeader> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    old.invoke(it);
                    block.invoke(it);
                }
            };
        }

        public final void compressIf(final Function1<? super Frame, Boolean> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            final Function1 old = this.compressCondition;
            this.compressCondition = new Function1<Frame, Boolean>() { // from class: io.ktor.websocket.WebSocketDeflateExtension$Config$compressIf$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Frame it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Boolean.valueOf(block.invoke(it).booleanValue() && old.invoke(it).booleanValue());
                }
            };
        }

        public final void compressIfBiggerThan(final int bytes) {
            compressIf(new Function1<Frame, Boolean>() { // from class: io.ktor.websocket.WebSocketDeflateExtension$Config$compressIfBiggerThan$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Frame frame) {
                    Intrinsics.checkNotNullParameter(frame, "frame");
                    return Boolean.valueOf(frame.getData().length > bytes);
                }
            });
        }

        public final List<WebSocketExtensionHeader> build$ktor_websockets() {
            List result = new ArrayList();
            List parameters = new ArrayList();
            if (this.clientNoContextTakeOver) {
                parameters.add("client_no_context_takeover");
            }
            if (this.serverNoContextTakeOver) {
                parameters.add("server_no_context_takeover");
            }
            result.add(new WebSocketExtensionHeader("permessage-deflate", parameters));
            this.manualConfig.invoke(result);
            return result;
        }
    }

    /* compiled from: WebSocketDeflateExtension.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J!\u0010\u0011\u001a\u00020\u00032\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\u0002\b\u0015H\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u0016"}, d2 = {"Lio/ktor/websocket/WebSocketDeflateExtension$Companion;", "Lio/ktor/websocket/WebSocketExtensionFactory;", "Lio/ktor/websocket/WebSocketDeflateExtension$Config;", "Lio/ktor/websocket/WebSocketDeflateExtension;", "()V", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "rsv1", "", "getRsv1", "()Z", "rsv2", "getRsv2", "rsv3", "getRsv3", "install", "config", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Companion implements WebSocketExtensionFactory<Config, WebSocketDeflateExtension> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // io.ktor.websocket.WebSocketExtensionFactory
        public AttributeKey<WebSocketDeflateExtension> getKey() {
            return WebSocketDeflateExtension.key;
        }

        @Override // io.ktor.websocket.WebSocketExtensionFactory
        public boolean getRsv1() {
            return WebSocketDeflateExtension.rsv1;
        }

        @Override // io.ktor.websocket.WebSocketExtensionFactory
        public boolean getRsv2() {
            return WebSocketDeflateExtension.rsv2;
        }

        @Override // io.ktor.websocket.WebSocketExtensionFactory
        public boolean getRsv3() {
            return WebSocketDeflateExtension.rsv3;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.websocket.WebSocketExtensionFactory
        public WebSocketDeflateExtension install(Function1<? super Config, Unit> config) {
            Intrinsics.checkNotNullParameter(config, "config");
            Config config2 = new Config();
            config.invoke(config2);
            return new WebSocketDeflateExtension(config2);
        }
    }
}
