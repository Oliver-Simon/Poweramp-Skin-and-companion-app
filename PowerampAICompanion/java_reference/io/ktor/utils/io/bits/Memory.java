package io.ktor.utils.io.bits;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.maxmpz.poweramp.player.RouterConsts;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.core.internal.NumbersKt;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MemoryJvm.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0005\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 32\u00020\u0001:\u00013B\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J3\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\rø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J3\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\tø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0018J\u001a\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u001f\u0010\u000fJ\u0018\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\rH\u0086\b¢\u0006\u0004\b#\u0010$J\u0018\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\tH\u0086\b¢\u0006\u0004\b#\u0010%J&\u0010&\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\rø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b'\u0010(J&\u0010&\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\tø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b'\u0010)J \u0010*\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\r2\u0006\u0010+\u001a\u00020!H\u0086\b¢\u0006\u0004\b,\u0010-J \u0010*\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\t2\u0006\u0010+\u001a\u00020!H\u0086\b¢\u0006\u0004\b,\u0010.J\u0010\u0010/\u001a\u000200HÖ\u0001¢\u0006\u0004\b1\u00102R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0088\u0001\u0002ø\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00064"}, d2 = {"Lio/ktor/utils/io/bits/Memory;", "", "buffer", "Ljava/nio/ByteBuffer;", "constructor-impl", "(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;", "getBuffer", "()Ljava/nio/ByteBuffer;", ContentDisposition.Parameters.Size, "", "getSize-impl", "(Ljava/nio/ByteBuffer;)J", "size32", "", "getSize32-impl", "(Ljava/nio/ByteBuffer;)I", "copyTo", "", "destination", TypedValues.CycleType.S_WAVE_OFFSET, "length", "destinationOffset", "copyTo-JT6ljtQ", "(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;III)V", "(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;JJJ)V", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "equals-impl", "(Ljava/nio/ByteBuffer;Ljava/lang/Object;)Z", "hashCode", "hashCode-impl", "loadAt", "", "index", "loadAt-impl", "(Ljava/nio/ByteBuffer;I)B", "(Ljava/nio/ByteBuffer;J)B", "slice", "slice-87lwejk", "(Ljava/nio/ByteBuffer;II)Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;JJ)Ljava/nio/ByteBuffer;", "storeAt", "value", "storeAt-impl", "(Ljava/nio/ByteBuffer;IB)V", "(Ljava/nio/ByteBuffer;JB)V", "toString", "", "toString-impl", "(Ljava/nio/ByteBuffer;)Ljava/lang/String;", "Companion", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
/* loaded from: classes9.dex */
public final class Memory {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ByteBuffer Empty;
    private final ByteBuffer buffer;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Memory m234boximpl(ByteBuffer byteBuffer) {
        return new Memory(byteBuffer);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static ByteBuffer m235constructorimpl(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        return buffer;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m238equalsimpl(ByteBuffer byteBuffer, Object obj) {
        return (obj instanceof Memory) && Intrinsics.areEqual(byteBuffer, ((Memory) obj).m250unboximpl());
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m239equalsimpl0(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        return Intrinsics.areEqual(byteBuffer, byteBuffer2);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m242hashCodeimpl(ByteBuffer byteBuffer) {
        return byteBuffer.hashCode();
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m249toStringimpl(ByteBuffer byteBuffer) {
        return "Memory(buffer=" + byteBuffer + ')';
    }

    public boolean equals(Object obj) {
        return m238equalsimpl(this.buffer, obj);
    }

    public int hashCode() {
        return m242hashCodeimpl(this.buffer);
    }

    public String toString() {
        return m249toStringimpl(this.buffer);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ ByteBuffer m250unboximpl() {
        return this.buffer;
    }

    private /* synthetic */ Memory(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public final ByteBuffer getBuffer() {
        return this.buffer;
    }

    /* renamed from: getSize-impl, reason: not valid java name */
    public static final long m240getSizeimpl(ByteBuffer arg0) {
        return arg0.limit();
    }

    /* renamed from: getSize32-impl, reason: not valid java name */
    public static final int m241getSize32impl(ByteBuffer arg0) {
        return arg0.limit();
    }

    /* renamed from: loadAt-impl, reason: not valid java name */
    public static final byte m243loadAtimpl(ByteBuffer arg0, int index) {
        return arg0.get(index);
    }

    /* renamed from: loadAt-impl, reason: not valid java name */
    public static final byte m244loadAtimpl(ByteBuffer arg0, long index) {
        if (index < 2147483647L) {
            return arg0.get((int) index);
        }
        NumbersKt.failLongToIntConversion(index, "index");
        throw new KotlinNothingValueException();
    }

    /* renamed from: storeAt-impl, reason: not valid java name */
    public static final void m247storeAtimpl(ByteBuffer arg0, int index, byte value) {
        arg0.put(index, value);
    }

    /* renamed from: storeAt-impl, reason: not valid java name */
    public static final void m248storeAtimpl(ByteBuffer arg0, long index, byte value) {
        if (index < 2147483647L) {
            arg0.put((int) index, value);
        } else {
            NumbersKt.failLongToIntConversion(index, "index");
            throw new KotlinNothingValueException();
        }
    }

    /* renamed from: slice-87lwejk, reason: not valid java name */
    public static final ByteBuffer m245slice87lwejk(ByteBuffer arg0, int offset, int length) {
        return m235constructorimpl(MemoryJvmKt.sliceSafe(arg0, offset, length));
    }

    /* renamed from: slice-87lwejk, reason: not valid java name */
    public static final ByteBuffer m246slice87lwejk(ByteBuffer arg0, long offset, long length) {
        if (offset >= 2147483647L) {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
        int i = (int) offset;
        if (length < 2147483647L) {
            return m245slice87lwejk(arg0, i, (int) length);
        }
        NumbersKt.failLongToIntConversion(length, "length");
        throw new KotlinNothingValueException();
    }

    /* renamed from: copyTo-JT6ljtQ, reason: not valid java name */
    public static final void m236copyToJT6ljtQ(ByteBuffer arg0, ByteBuffer destination, int offset, int length, int destinationOffset) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (arg0.hasArray() && destination.hasArray() && !arg0.isReadOnly() && !destination.isReadOnly()) {
            System.arraycopy(arg0.array(), arg0.arrayOffset() + offset, destination.array(), destination.arrayOffset() + destinationOffset, length);
            return;
        }
        ByteBuffer srcCopy = arg0.duplicate();
        srcCopy.position(offset);
        srcCopy.limit(offset + length);
        ByteBuffer dstCopy = destination.duplicate();
        dstCopy.position(destinationOffset);
        dstCopy.put(srcCopy);
    }

    /* renamed from: copyTo-JT6ljtQ, reason: not valid java name */
    public static final void m237copyToJT6ljtQ(ByteBuffer arg0, ByteBuffer destination, long offset, long length, long destinationOffset) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (offset >= 2147483647L) {
            NumbersKt.failLongToIntConversion(offset, TypedValues.CycleType.S_WAVE_OFFSET);
            throw new KotlinNothingValueException();
        }
        int i = (int) offset;
        if (length >= 2147483647L) {
            NumbersKt.failLongToIntConversion(length, "length");
            throw new KotlinNothingValueException();
        }
        int i2 = (int) length;
        if (destinationOffset < 2147483647L) {
            m236copyToJT6ljtQ(arg0, destination, i, i2, (int) destinationOffset);
        } else {
            NumbersKt.failLongToIntConversion(destinationOffset, "destinationOffset");
            throw new KotlinNothingValueException();
        }
    }

    /* compiled from: MemoryJvm.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\b"}, d2 = {"Lio/ktor/utils/io/bits/Memory$Companion;", "", "()V", "Empty", "Lio/ktor/utils/io/bits/Memory;", "getEmpty-SK3TCg8", "()Ljava/nio/ByteBuffer;", "Ljava/nio/ByteBuffer;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* renamed from: getEmpty-SK3TCg8, reason: not valid java name */
        public final ByteBuffer m251getEmptySK3TCg8() {
            return Memory.Empty;
        }
    }

    static {
        ByteBuffer order = ByteBuffer.allocate(0).order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "allocate(0).order(ByteOrder.BIG_ENDIAN)");
        Empty = m235constructorimpl(order);
    }
}
