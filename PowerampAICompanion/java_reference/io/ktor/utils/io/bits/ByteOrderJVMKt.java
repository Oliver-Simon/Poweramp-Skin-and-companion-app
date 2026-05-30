package io.ktor.utils.io.bits;

import kotlin.Metadata;

/* compiled from: ByteOrderJvm.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0086\b\u001a\r\u0010\u0000\u001a\u00020\u0002*\u00020\u0002H\u0086\b\u001a\r\u0010\u0000\u001a\u00020\u0003*\u00020\u0003H\u0086\b\u001a\r\u0010\u0000\u001a\u00020\u0004*\u00020\u0004H\u0086\b\u001a\r\u0010\u0000\u001a\u00020\u0005*\u00020\u0005H\u0086\b¨\u0006\u0006"}, d2 = {"reverseByteOrder", "", "", "", "", "", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ByteOrderJVMKt {
    public static final short reverseByteOrder(short $this$reverseByteOrder) {
        return Short.reverseBytes($this$reverseByteOrder);
    }

    public static final int reverseByteOrder(int $this$reverseByteOrder) {
        return Integer.reverseBytes($this$reverseByteOrder);
    }

    public static final long reverseByteOrder(long $this$reverseByteOrder) {
        return Long.reverseBytes($this$reverseByteOrder);
    }

    public static final float reverseByteOrder(float $this$reverseByteOrder) {
        return Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits($this$reverseByteOrder)));
    }

    public static final double reverseByteOrder(double $this$reverseByteOrder) {
        return Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits($this$reverseByteOrder)));
    }
}
