package io.ktor.websocket;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.bits.DefaultAllocator;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* compiled from: RawWebSocketCommon.kt */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a%\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a%\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"mask", "Lio/ktor/utils/io/core/ByteReadPacket;", "maskKey", "", "readFrame", "Lio/ktor/websocket/Frame;", "Lio/ktor/utils/io/ByteReadChannel;", "maxFrameSize", "", "lastOpcode", "(Lio/ktor/utils/io/ByteReadChannel;JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFrame", "", "Lio/ktor/utils/io/ByteWriteChannel;", TypedValues.AttributesType.S_FRAME, "masking", "", "(Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/websocket/Frame;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class RawWebSocketCommonKt {
    private static final ByteReadPacket mask(ByteReadPacket $this$mask, int maskKey) {
        int size$iv = 4;
        int $i$f$withMemory = 0;
        long size$iv$iv = 4;
        DefaultAllocator allocator$iv$iv = DefaultAllocator.INSTANCE;
        ByteBuffer memory$iv$iv = allocator$iv$iv.mo229allocgFvZug(size$iv$iv);
        try {
            memory$iv$iv.putInt(0, maskKey);
            BytePacketBuilder builder$iv = new BytePacketBuilder(null, 1, null);
            try {
                int remaining = (int) $this$mask.getRemaining();
                int i = 0;
                while (i < remaining) {
                    int i2 = i;
                    int index$iv = i2 % 4;
                    int size$iv2 = size$iv;
                    int $i$f$withMemory2 = $i$f$withMemory;
                    try {
                        builder$iv.writeByte((byte) ($this$mask.readByte() ^ memory$iv$iv.get(index$iv)));
                        i++;
                        $i$f$withMemory = $i$f$withMemory2;
                        size$iv = size$iv2;
                    } catch (Throwable th) {
                        t$iv = th;
                        try {
                            builder$iv.release();
                            throw t$iv;
                        } catch (Throwable th2) {
                            t$iv = th2;
                            allocator$iv$iv.mo230free3GNKZMM(memory$iv$iv);
                            throw t$iv;
                        }
                    }
                }
                ByteReadPacket build = builder$iv.build();
                allocator$iv$iv.mo230free3GNKZMM(memory$iv$iv);
                return build;
            } catch (Throwable th3) {
                t$iv = th3;
            }
        } catch (Throwable th4) {
            t$iv = th4;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:33:0x0114. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0023. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0196 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0147 A[PHI: r2 r3 r13
      0x0147: PHI (r2v10 'frame' io.ktor.websocket.Frame) = (r2v6 'frame' io.ktor.websocket.Frame), (r2v9 'frame' io.ktor.websocket.Frame), (r2v12 'frame' io.ktor.websocket.Frame) binds: [B:33:0x0114, B:31:0x0144, B:20:0x012c] A[DONT_GENERATE, DONT_INLINE]
      0x0147: PHI (r3v10 '$this$writeFrame' io.ktor.utils.io.ByteWriteChannel) = 
      (r3v6 '$this$writeFrame' io.ktor.utils.io.ByteWriteChannel)
      (r3v9 '$this$writeFrame' io.ktor.utils.io.ByteWriteChannel)
      (r3v11 '$this$writeFrame' io.ktor.utils.io.ByteWriteChannel)
     binds: [B:33:0x0114, B:31:0x0144, B:20:0x012c] A[DONT_GENERATE, DONT_INLINE]
      0x0147: PHI (r13v13 'masking' boolean) = (r13v5 'masking' boolean), (r13v10 'masking' boolean), (r13v18 'masking' boolean) binds: [B:33:0x0114, B:31:0x0144, B:20:0x012c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x010f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    @io.ktor.util.InternalAPI
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object writeFrame(io.ktor.utils.io.ByteWriteChannel r11, io.ktor.websocket.Frame r12, boolean r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            Method dump skipped, instructions count: 442
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.RawWebSocketCommonKt.writeFrame(io.ktor.utils.io.ByteWriteChannel, io.ktor.websocket.Frame, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0025. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:92:0x012c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x00d5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    @io.ktor.util.InternalAPI
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readFrame(io.ktor.utils.io.ByteReadChannel r21, long r22, int r24, kotlin.coroutines.Continuation<? super io.ktor.websocket.Frame> r25) {
        /*
            Method dump skipped, instructions count: 618
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.RawWebSocketCommonKt.readFrame(io.ktor.utils.io.ByteReadChannel, long, int, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
