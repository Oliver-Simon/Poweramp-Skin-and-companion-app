package io.ktor.utils.io.core;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Builder.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a2\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a\n\u0010\u0007\u001a\u00020\u0005*\u00020\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\b"}, d2 = {"buildPacket", "Lio/ktor/utils/io/core/ByteReadPacket;", "block", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/BytePacketBuilder;", "", "Lkotlin/ExtensionFunctionType;", "reset", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class BuilderKt {
    public static final ByteReadPacket buildPacket(Function1<? super BytePacketBuilder, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        BytePacketBuilder builder = new BytePacketBuilder(null, 1, null);
        try {
            block.invoke(builder);
            return builder.build();
        } catch (Throwable t) {
            builder.release();
            throw t;
        }
    }

    public static final void reset(BytePacketBuilder $this$reset) {
        Intrinsics.checkNotNullParameter($this$reset, "<this>");
        $this$reset.release();
    }
}
