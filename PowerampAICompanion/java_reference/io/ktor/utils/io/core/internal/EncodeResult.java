package io.ktor.utils.io.core.internal;

import com.maxmpz.poweramp.player.RouterConsts;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.JvmInline;

/* compiled from: EncodeResult.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0081@\u0018\u00002\u00020\u0001B\u001c\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006B\u0012\u0012\u0006\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\tJ\u0019\u0010\u000f\u001a\u00020\u0003H\u0086\u0002ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000bJ\u0019\u0010\u0011\u001a\u00020\u0003H\u0086\u0002ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u000bJ\u001a\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u0019\u0010\tJ\u0010\u0010\u001a\u001a\u00020\u001bHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0004\u001a\u00020\u00038Fø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u00038Fø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u0088\u0001\u0007ø\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001e"}, d2 = {"Lio/ktor/utils/io/core/internal/EncodeResult;", "", "characters", "Lkotlin/UShort;", "bytes", "constructor-impl", "(SS)I", "value", "", "(I)I", "getBytes-Mh2AYeg", "(I)S", "getCharacters-Mh2AYeg", "getValue", "()I", "component1", "component1-Mh2AYeg", "component2", "component2-Mh2AYeg", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
/* loaded from: classes9.dex */
public final class EncodeResult {
    private final int value;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ EncodeResult m485boximpl(int i) {
        return new EncodeResult(i);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m488constructorimpl(int i) {
        return i;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m490equalsimpl(int i, Object obj) {
        return (obj instanceof EncodeResult) && i == ((EncodeResult) obj).m496unboximpl();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m491equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m494hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m495toStringimpl(int i) {
        return "EncodeResult(value=" + i + ')';
    }

    public boolean equals(Object obj) {
        return m490equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m494hashCodeimpl(this.value);
    }

    public String toString() {
        return m495toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m496unboximpl() {
        return this.value;
    }

    private /* synthetic */ EncodeResult(int value) {
        this.value = value;
    }

    public final int getValue() {
        return this.value;
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m489constructorimpl(short characters, short bytes) {
        return m488constructorimpl((65535 & bytes) | ((characters & UShort.MAX_VALUE) << 16));
    }

    /* renamed from: getCharacters-Mh2AYeg, reason: not valid java name */
    public static final short m493getCharactersMh2AYeg(int arg0) {
        return UShort.m791constructorimpl((short) (arg0 >>> 16));
    }

    /* renamed from: getBytes-Mh2AYeg, reason: not valid java name */
    public static final short m492getBytesMh2AYeg(int arg0) {
        return UShort.m791constructorimpl((short) (65535 & arg0));
    }

    /* renamed from: component1-Mh2AYeg, reason: not valid java name */
    public static final short m486component1Mh2AYeg(int arg0) {
        return m493getCharactersMh2AYeg(arg0);
    }

    /* renamed from: component2-Mh2AYeg, reason: not valid java name */
    public static final short m487component2Mh2AYeg(int arg0) {
        return m492getBytesMh2AYeg(arg0);
    }
}
