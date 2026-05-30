package io.ktor.utils.io;

import io.ktor.utils.io.core.ByteOrder;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.StringsKt;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ByteWriteChannel.kt */
@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001d\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\b\u001a\u00020\t*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\u001d\u0010\f\u001a\u00020\t*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u001d\u0010\u000e\u001a\u00020\t*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u001d\u0010\u0012\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\u0013\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a%\u0010\u0013\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a.\u0010\u001a\u001a\u00020\t*\u00020\u00022\u0017\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\t0\u001c¢\u0006\u0002\b\u001eH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u001f\u001a>\u0010 \u001a\u00020\t*\u00020\u00022'\u0010\u001b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\"\u0012\u0006\u0012\u0004\u0018\u00010#0!¢\u0006\u0002\b\u001eH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010$\u001a\u001d\u0010%\u001a\u00020\t*\u00020\u00022\u0006\u0010&\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a%\u0010%\u001a\u00020\t*\u00020\u00022\u0006\u0010&\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010'\u001a\u001d\u0010(\u001a\u00020\t*\u00020\u00022\u0006\u0010&\u001a\u00020)H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010*\u001a\u001d\u0010(\u001a\u00020\t*\u00020\u00022\u0006\u0010&\u001a\u00020+H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010,\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, d2 = {"close", "", "Lio/ktor/utils/io/ByteWriteChannel;", "writeAvailable", "", "src", "", "(Lio/ktor/utils/io/ByteWriteChannel;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeBoolean", "", "b", "(Lio/ktor/utils/io/ByteWriteChannel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeByte", "(Lio/ktor/utils/io/ByteWriteChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeChar", "ch", "", "(Lio/ktor/utils/io/ByteWriteChannel;CLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFully", "writeInt", "i", "", "(Lio/ktor/utils/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "byteOrder", "Lio/ktor/utils/io/core/ByteOrder;", "(Lio/ktor/utils/io/ByteWriteChannel;JLio/ktor/utils/io/core/ByteOrder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writePacket", "builder", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/BytePacketBuilder;", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writePacketSuspend", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeShort", "s", "(Lio/ktor/utils/io/ByteWriteChannel;ILio/ktor/utils/io/core/ByteOrder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeStringUtf8", "", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/lang/CharSequence;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ByteWriteChannelKt {
    public static final Object writeAvailable(ByteWriteChannel $this$writeAvailable, byte[] src, Continuation<? super Integer> continuation) {
        return $this$writeAvailable.writeAvailable(src, 0, src.length, continuation);
    }

    public static final Object writeFully(ByteWriteChannel $this$writeFully, byte[] src, Continuation<? super Unit> continuation) {
        Object writeFully = $this$writeFully.writeFully(src, 0, src.length, continuation);
        return writeFully == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeFully : Unit.INSTANCE;
    }

    public static final Object writeShort(ByteWriteChannel $this$writeShort, int s, Continuation<? super Unit> continuation) {
        Object writeShort = $this$writeShort.writeShort((short) (65535 & s), continuation);
        return writeShort == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeShort : Unit.INSTANCE;
    }

    public static final Object writeShort(ByteWriteChannel $this$writeShort, int s, ByteOrder byteOrder, Continuation<? super Unit> continuation) {
        Object writeShort = ChannelLittleEndianKt.writeShort($this$writeShort, (short) (65535 & s), byteOrder, continuation);
        return writeShort == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeShort : Unit.INSTANCE;
    }

    public static final Object writeByte(ByteWriteChannel $this$writeByte, int b, Continuation<? super Unit> continuation) {
        Object writeByte = $this$writeByte.writeByte((byte) (b & 255), continuation);
        return writeByte == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeByte : Unit.INSTANCE;
    }

    public static final Object writeInt(ByteWriteChannel $this$writeInt, long i, Continuation<? super Unit> continuation) {
        Object writeInt = $this$writeInt.writeInt((int) i, continuation);
        return writeInt == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeInt : Unit.INSTANCE;
    }

    public static final Object writeInt(ByteWriteChannel $this$writeInt, long i, ByteOrder byteOrder, Continuation<? super Unit> continuation) {
        Object writeInt = ChannelLittleEndianKt.writeInt($this$writeInt, (int) i, byteOrder, continuation);
        return writeInt == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeInt : Unit.INSTANCE;
    }

    public static final boolean close(ByteWriteChannel $this$close) {
        Intrinsics.checkNotNullParameter($this$close, "<this>");
        return $this$close.close(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Object writeStringUtf8(ByteWriteChannel byteWriteChannel, CharSequence charSequence, Continuation<? super Unit> continuation) {
        Throwable th;
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        try {
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            StringsKt.writeText$default(bytePacketBuilder, charSequence, 0, 0, (Charset) null, 14, (Object) null);
            Object writePacket = byteWriteChannel.writePacket(bytePacketBuilder.build(), continuation);
            return writePacket == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writePacket : Unit.INSTANCE;
        } catch (Throwable th3) {
            th = th3;
            bytePacketBuilder.release();
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Object writeStringUtf8(ByteWriteChannel byteWriteChannel, String str, Continuation<? super Unit> continuation) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        try {
            StringsKt.writeText$default(bytePacketBuilder, str, 0, 0, (Charset) null, 14, (Object) null);
            Object writePacket = byteWriteChannel.writePacket(bytePacketBuilder.build(), continuation);
            return writePacket == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writePacket : Unit.INSTANCE;
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    public static final Object writeBoolean(ByteWriteChannel byteWriteChannel, boolean z, Continuation<? super Unit> continuation) {
        Object writeByte = byteWriteChannel.writeByte(z ? (byte) 1 : (byte) 0, continuation);
        return writeByte == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeByte : Unit.INSTANCE;
    }

    public static final Object writeChar(ByteWriteChannel $this$writeChar, char ch, Continuation<? super Unit> continuation) {
        Object writeShort = writeShort($this$writeChar, ch, continuation);
        return writeShort == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeShort : Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Object writePacket(ByteWriteChannel byteWriteChannel, Function1<? super BytePacketBuilder, Unit> function1, Continuation<? super Unit> continuation) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        try {
            function1.invoke(bytePacketBuilder);
            Object writePacket = byteWriteChannel.writePacket(bytePacketBuilder.build(), continuation);
            return writePacket == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writePacket : Unit.INSTANCE;
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final Object writePacket$$forInline(ByteWriteChannel byteWriteChannel, Function1<? super BytePacketBuilder, Unit> function1, Continuation<? super Unit> continuation) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        try {
            function1.invoke(bytePacketBuilder);
            byteWriteChannel.writePacket(bytePacketBuilder.build(), continuation);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0022. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0070 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object writePacketSuspend(io.ktor.utils.io.ByteWriteChannel r8, kotlin.jvm.functions.Function2<? super io.ktor.utils.io.core.BytePacketBuilder, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteWriteChannelKt$writePacketSuspend$1
            if (r0 == 0) goto L14
            r0 = r10
            io.ktor.utils.io.ByteWriteChannelKt$writePacketSuspend$1 r0 = (io.ktor.utils.io.ByteWriteChannelKt$writePacketSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            io.ktor.utils.io.ByteWriteChannelKt$writePacketSuspend$1 r0 = new io.ktor.utils.io.ByteWriteChannelKt$writePacketSuspend$1
            r0.<init>(r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            switch(r2) {
                case 0: goto L3f;
                case 1: goto L31;
                case 2: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2d:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L71
        L31:
            r8 = 0
            r9 = 0
            java.lang.Object r2 = r0.L$1
            io.ktor.utils.io.ByteWriteChannel r2 = (io.ktor.utils.io.ByteWriteChannel) r2
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.core.BytePacketBuilder r4 = (io.ktor.utils.io.core.BytePacketBuilder) r4
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L74
            goto L5d
        L3f:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r8
            r8 = 0
            io.ktor.utils.io.core.BytePacketBuilder r4 = new io.ktor.utils.io.core.BytePacketBuilder
            r5 = 1
            r4.<init>(r3, r5, r3)
            r6 = r4
            r7 = 0
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L74
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L74
            r0.label = r5     // Catch: java.lang.Throwable -> L74
            java.lang.Object r5 = r9.invoke(r6, r0)     // Catch: java.lang.Throwable -> L74
            if (r5 != r1) goto L5c
            return r1
        L5c:
            r9 = r7
        L5d:
            io.ktor.utils.io.core.ByteReadPacket r9 = r4.build()     // Catch: java.lang.Throwable -> L74
            r0.L$0 = r3
            r0.L$1 = r3
            r8 = 2
            r0.label = r8
            java.lang.Object r8 = r2.writePacket(r9, r0)
            if (r8 != r1) goto L71
            return r1
        L71:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L74:
            r9 = move-exception
            r4.release()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteWriteChannelKt.writePacketSuspend(io.ktor.utils.io.ByteWriteChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
