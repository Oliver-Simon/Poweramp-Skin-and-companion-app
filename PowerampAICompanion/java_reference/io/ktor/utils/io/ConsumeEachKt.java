package io.ktor.utils.io;

import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Buffer;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* compiled from: ConsumeEach.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aQ\u0010\u0000\u001a\u00020\u0001*\u00020\u00022:\u0010\u0003\u001a6\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\t0\u0004j\u0002`\u000bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\f*j\u0010\r\"2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\t0\u000422\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\t0\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"consumeEachBufferRange", "", "Lio/ktor/utils/io/ByteReadChannel;", "visitor", "Lkotlin/Function2;", "Ljava/nio/ByteBuffer;", "Lkotlin/ParameterName;", "name", "buffer", "", "last", "Lio/ktor/utils/io/ConsumeEachBufferVisitor;", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ConsumeEachBufferVisitor", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ConsumeEachKt {
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0026. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x013c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x017f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x013d -> B:17:0x0143). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object consumeEachBufferRange(io.ktor.utils.io.ByteReadChannel r19, kotlin.jvm.functions.Function2<? super java.nio.ByteBuffer, ? super java.lang.Boolean, java.lang.Boolean> r20, kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            Method dump skipped, instructions count: 400
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ConsumeEachKt.consumeEachBufferRange(io.ktor.utils.io.ByteReadChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object consumeEachBufferRange$$forInline(ByteReadChannel $this$consumeEachBufferRange, Function2<? super ByteBuffer, ? super Boolean, Boolean> function2, Continuation<? super Unit> continuation) {
        ByteReadChannel $this$read_u24default$iv;
        ByteReadChannel $this$read_u24default$iv2;
        ByteBuffer nioBuffer;
        Ref.BooleanRef continueFlag = new Ref.BooleanRef();
        Ref.BooleanRef lastChunkReported = new Ref.BooleanRef();
        do {
            continueFlag.element = false;
            Buffer buffer = (Buffer) ReadSessionKt.requestBuffer($this$consumeEachBufferRange, 1, continuation);
            if (buffer == null) {
                buffer = Buffer.INSTANCE.getEmpty();
            }
            Buffer buffer$iv = buffer;
            try {
                Memory m234boximpl = Memory.m234boximpl(buffer$iv.getMemory());
                Long valueOf = Long.valueOf(buffer$iv.getReadPosition());
                long endExclusive = Long.valueOf(buffer$iv.getWritePosition()).longValue();
                long start = valueOf.longValue();
                Memory memory = m234boximpl;
                ByteBuffer source = m234boximpl.m250unboximpl();
                if (endExclusive > start) {
                    $this$read_u24default$iv2 = $this$consumeEachBufferRange;
                    try {
                        nioBuffer = Memory.m246slice87lwejk(source, start, endExclusive - start);
                    } catch (Throwable th) {
                        cause$iv = th;
                        $this$read_u24default$iv = $this$read_u24default$iv2;
                        ReadSessionKt.completeReadingFromBuffer($this$read_u24default$iv, buffer$iv, 0, continuation);
                        throw cause$iv;
                    }
                } else {
                    $this$read_u24default$iv2 = $this$consumeEachBufferRange;
                    nioBuffer = Memory.INSTANCE.m251getEmptySK3TCg8();
                }
                try {
                    lastChunkReported.element = nioBuffer.remaining() == $this$consumeEachBufferRange.getAvailableForRead() && $this$consumeEachBufferRange.isClosedForWrite();
                    try {
                        continueFlag.element = function2.invoke(nioBuffer, Boolean.valueOf(lastChunkReported.element)).booleanValue();
                        int bytesRead$iv = Integer.valueOf(nioBuffer.position()).intValue();
                        $this$read_u24default$iv = $this$read_u24default$iv2;
                        try {
                            ReadSessionKt.completeReadingFromBuffer($this$read_u24default$iv, buffer$iv, bytesRead$iv, continuation);
                            Integer.valueOf(bytesRead$iv);
                            if (lastChunkReported.element && $this$consumeEachBufferRange.isClosedForRead()) {
                                break;
                            }
                        } catch (Throwable th2) {
                            cause$iv = th2;
                            ReadSessionKt.completeReadingFromBuffer($this$read_u24default$iv, buffer$iv, 0, continuation);
                            throw cause$iv;
                        }
                    } catch (Throwable th3) {
                        cause$iv = th3;
                        $this$read_u24default$iv = $this$read_u24default$iv2;
                        ReadSessionKt.completeReadingFromBuffer($this$read_u24default$iv, buffer$iv, 0, continuation);
                        throw cause$iv;
                    }
                } catch (Throwable th4) {
                    cause$iv = th4;
                }
            } catch (Throwable th5) {
                cause$iv = th5;
                $this$read_u24default$iv = $this$consumeEachBufferRange;
            }
        } while (continueFlag.element);
        return Unit.INSTANCE;
    }
}
