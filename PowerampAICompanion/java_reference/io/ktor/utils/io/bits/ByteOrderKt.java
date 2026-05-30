package io.ktor.utils.io.bits;

import kotlin.Metadata;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;

/* compiled from: ByteOrder.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0013\u001a\u00020\u0014*\u00020\u0014ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0017\u0010\u0013\u001a\u00020\u0017*\u00020\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0017\u0010\u0013\u001a\u00020\u001a*\u00020\u001aø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001c\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0016\u0010\u0005\u001a\u00020\u0006*\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\t\"\u0016\u0010\n\u001a\u00020\u0002*\u00020\u00068Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0016\u0010\r\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0004\"\u0016\u0010\u000f\u001a\u00020\u0006*\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\t\"\u0016\u0010\u0011\u001a\u00020\u0002*\u00020\u00068Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"highByte", "", "", "getHighByte", "(S)B", "highInt", "", "", "getHighInt", "(J)I", "highShort", "getHighShort", "(I)S", "lowByte", "getLowByte", "lowInt", "getLowInt", "lowShort", "getLowShort", "reverseByteOrder", "Lkotlin/UInt;", "reverseByteOrder-WZ4Q5Ns", "(I)I", "Lkotlin/ULong;", "reverseByteOrder-VKZWuLQ", "(J)J", "Lkotlin/UShort;", "reverseByteOrder-xj2QHRw", "(S)S", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ByteOrderKt {
    /* renamed from: reverseByteOrder-xj2QHRw, reason: not valid java name */
    public static final short m233reverseByteOrderxj2QHRw(short $this$reverseByteOrder_u2dxj2QHRw) {
        short $this$reverseByteOrder$iv = Short.reverseBytes($this$reverseByteOrder_u2dxj2QHRw);
        return UShort.m791constructorimpl($this$reverseByteOrder$iv);
    }

    /* renamed from: reverseByteOrder-WZ4Q5Ns, reason: not valid java name */
    public static final int m232reverseByteOrderWZ4Q5Ns(int $this$reverseByteOrder_u2dWZ4Q5Ns) {
        int $this$reverseByteOrder$iv = Integer.reverseBytes($this$reverseByteOrder_u2dWZ4Q5Ns);
        return UInt.m605constructorimpl($this$reverseByteOrder$iv);
    }

    /* renamed from: reverseByteOrder-VKZWuLQ, reason: not valid java name */
    public static final long m231reverseByteOrderVKZWuLQ(long $this$reverseByteOrder_u2dVKZWuLQ) {
        long $this$reverseByteOrder$iv = Long.reverseBytes($this$reverseByteOrder_u2dVKZWuLQ);
        return ULong.m684constructorimpl($this$reverseByteOrder$iv);
    }

    public static final byte getHighByte(short $this$highByte) {
        return (byte) ($this$highByte >>> 8);
    }

    public static final byte getLowByte(short $this$lowByte) {
        return (byte) ($this$lowByte & 255);
    }

    public static final short getHighShort(int $this$highShort) {
        return (short) ($this$highShort >>> 16);
    }

    public static final short getLowShort(int $this$lowShort) {
        return (short) (65535 & $this$lowShort);
    }

    public static final int getHighInt(long $this$highInt) {
        return (int) ($this$highInt >>> 32);
    }

    public static final int getLowInt(long $this$lowInt) {
        return (int) (4294967295L & $this$lowInt);
    }
}
