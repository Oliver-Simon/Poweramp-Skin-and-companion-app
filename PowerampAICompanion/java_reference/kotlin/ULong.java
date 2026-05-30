package kotlin;

import com.maxmpz.poweramp.player.RouterConsts;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ULongRange;
import kotlin.ranges.URangesKt;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: ULong.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b2\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0010\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087@\u0018\u0000 {2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001{B\u0011\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b\f\u0010\rJ\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0000H\u0097\n¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b\u0017\u0010\u0018J\u0018\u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b\u0019\u0010\u001aJ\u0018\u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b\u001b\u0010\u001cJ\u0018\u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\u001d\u0010\u001eJ\u0018\u0010\u001f\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b \u0010\u0018J\u0018\u0010\u001f\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b!\u0010\u001aJ\u0018\u0010\u001f\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b\"\u0010\u001cJ\u0018\u0010\u001f\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b#\u0010\u001eJ\u0018\u0010$\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b%\u0010\u0018J\u0018\u0010$\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b&\u0010\u001aJ\u0018\u0010$\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b'\u0010\u001cJ\u0018\u0010$\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b(\u0010\u001eJ\u0018\u0010)\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b*\u0010\u0018J\u0018\u0010)\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b+\u0010\u001aJ\u0018\u0010)\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b,\u0010\u001cJ\u0018\u0010)\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b-\u0010\u001eJ\u0018\u0010.\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b/\u0010\u0018J\u0018\u0010.\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000eH\u0087\n¢\u0006\u0004\b0\u0010\u001aJ\u0018\u0010.\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0011H\u0087\n¢\u0006\u0004\b1\u0010\u001cJ\u0018\u0010.\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b2\u0010\u001eJ\u0018\u00103\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b4\u0010\u0018J\u0018\u00103\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000eH\u0087\b¢\u0006\u0004\b5\u0010\u001aJ\u0018\u00103\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0011H\u0087\b¢\u0006\u0004\b6\u0010\u001cJ\u0018\u00103\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b7\u0010\u001eJ\u0018\u00108\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b9\u0010:J\u0018\u00108\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000eH\u0087\b¢\u0006\u0004\b;\u0010<J\u0018\u00108\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u0011H\u0087\b¢\u0006\u0004\b=\u0010\u0013J\u0018\u00108\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b>\u0010\u001eJ\u0010\u0010?\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b@\u0010\u0005J\u0010\u0010A\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bB\u0010\u0005J\u0018\u0010C\u001a\u00020D2\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bE\u0010FJ\u0018\u0010G\u001a\u00020D2\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bH\u0010FJ\u0018\u0010I\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\tH\u0087\f¢\u0006\u0004\bK\u0010\u001cJ\u0018\u0010L\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\tH\u0087\f¢\u0006\u0004\bM\u0010\u001cJ\u0018\u0010N\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bO\u0010\u001eJ\u0018\u0010P\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bQ\u0010\u001eJ\u0018\u0010R\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bS\u0010\u001eJ\u0010\u0010T\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\bU\u0010\u0005J\u0010\u0010V\u001a\u00020WH\u0087\b¢\u0006\u0004\bX\u0010YJ\u0010\u0010Z\u001a\u00020[H\u0087\b¢\u0006\u0004\b\\\u0010]J\u0010\u0010^\u001a\u00020\tH\u0087\b¢\u0006\u0004\b_\u0010`J\u0010\u0010a\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bb\u0010\u0005J\u0010\u0010c\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\bd\u0010YJ\u0010\u0010e\u001a\u00020\u000eH\u0087\b¢\u0006\u0004\bf\u0010]J\u0010\u0010g\u001a\u00020\u0011H\u0087\b¢\u0006\u0004\bh\u0010`J\u0010\u0010i\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\bj\u0010\u0005J\u0010\u0010k\u001a\u00020lH\u0087\b¢\u0006\u0004\bm\u0010nJ\u0010\u0010o\u001a\u00020pH\u0087\b¢\u0006\u0004\bq\u0010rJ\u000f\u0010s\u001a\u00020tH\u0016¢\u0006\u0004\bu\u0010vJ\u0013\u0010w\u001a\u00020x2\b\u0010\n\u001a\u0004\u0018\u00010yHÖ\u0003J\t\u0010z\u001a\u00020\tHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006|"}, d2 = {"Lkotlin/ULong;", "", "data", "", "constructor-impl", "(J)J", "getData$annotations", "()V", "compareTo", "", RouterConsts.DEVICE_NAME_OTHER, "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "plus", "plus-7apg3OU", "(JB)J", "plus-xj2QHRw", "(JS)J", "plus-WZ4Q5Ns", "(JI)J", "plus-VKZWuLQ", "(JJ)J", "minus", "minus-7apg3OU", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times", "times-7apg3OU", "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div", "div-7apg3OU", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem", "rem-7apg3OU", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "floorDiv", "floorDiv-7apg3OU", "floorDiv-xj2QHRw", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "mod", "mod-7apg3OU", "(JB)B", "mod-xj2QHRw", "(JS)S", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "inc", "inc-s-VKNKU", "dec", "dec-s-VKNKU", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rangeUntil", "rangeUntil-VKZWuLQ", "shl", "bitCount", "shl-s-VKNKU", "shr", "shr-s-VKNKU", "and", "and-VKZWuLQ", "or", "or-VKZWuLQ", "xor", "xor-VKZWuLQ", "inv", "inv-s-VKNKU", "toByte", "", "toByte-impl", "(J)B", "toShort", "", "toShort-impl", "(J)S", "toInt", "toInt-impl", "(J)I", "toLong", "toLong-impl", "toUByte", "toUByte-w2LRezQ", "toUShort", "toUShort-Mh2AYeg", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toFloat", "", "toFloat-impl", "(J)F", "toDouble", "", "toDouble-impl", "(J)D", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "equals", "", "", "hashCode", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes9.dex */
public final class ULong implements Comparable<ULong> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final long MAX_VALUE = -1;
    public static final long MIN_VALUE = 0;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;
    private final long data;

    /* renamed from: box-impl */
    public static final /* synthetic */ ULong m678boximpl(long j) {
        return new ULong(j);
    }

    /* renamed from: constructor-impl */
    public static long m684constructorimpl(long j) {
        return j;
    }

    /* renamed from: equals-impl */
    public static boolean m690equalsimpl(long j, Object obj) {
        return (obj instanceof ULong) && j == ((ULong) obj).getData();
    }

    /* renamed from: equals-impl0 */
    public static final boolean m691equalsimpl0(long j, long j2) {
        return j == j2;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl */
    public static int m696hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    public boolean equals(Object r3) {
        return m690equalsimpl(this.data, r3);
    }

    public int hashCode() {
        return m696hashCodeimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: from getter */
    public final /* synthetic */ long getData() {
        return this.data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(ULong uLong) {
        return UnsignedKt.ulongCompare(getData(), uLong.getData());
    }

    private /* synthetic */ ULong(long data) {
        this.data = data;
    }

    /* compiled from: ULong.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/ULong$Companion;", "", "<init>", "()V", "MIN_VALUE", "Lkotlin/ULong;", "J", "MAX_VALUE", "SIZE_BYTES", "", "SIZE_BITS", "kotlin-stdlib"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* renamed from: compareTo-7apg3OU */
    private static final int m679compareTo7apg3OU(long arg0, byte other) {
        return Long.compareUnsigned(arg0, m684constructorimpl(other & 255));
    }

    /* renamed from: compareTo-xj2QHRw */
    private static final int m683compareToxj2QHRw(long arg0, short other) {
        return Long.compareUnsigned(arg0, m684constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    /* renamed from: compareTo-WZ4Q5Ns */
    private static final int m682compareToWZ4Q5Ns(long arg0, int other) {
        return Long.compareUnsigned(arg0, m684constructorimpl(other & 4294967295L));
    }

    /* renamed from: compareTo-VKZWuLQ */
    private int m680compareToVKZWuLQ(long other) {
        return UnsignedKt.ulongCompare(getData(), other);
    }

    /* renamed from: compareTo-VKZWuLQ */
    private static int m681compareToVKZWuLQ(long arg0, long other) {
        return UnsignedKt.ulongCompare(arg0, other);
    }

    /* renamed from: plus-7apg3OU */
    private static final long m708plus7apg3OU(long arg0, byte other) {
        return m684constructorimpl(m684constructorimpl(other & 255) + arg0);
    }

    /* renamed from: plus-xj2QHRw */
    private static final long m711plusxj2QHRw(long arg0, short other) {
        return m684constructorimpl(m684constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX) + arg0);
    }

    /* renamed from: plus-WZ4Q5Ns */
    private static final long m710plusWZ4Q5Ns(long arg0, int other) {
        return m684constructorimpl(m684constructorimpl(other & 4294967295L) + arg0);
    }

    /* renamed from: plus-VKZWuLQ */
    private static final long m709plusVKZWuLQ(long arg0, long other) {
        return m684constructorimpl(arg0 + other);
    }

    /* renamed from: minus-7apg3OU */
    private static final long m699minus7apg3OU(long arg0, byte other) {
        return m684constructorimpl(arg0 - m684constructorimpl(other & 255));
    }

    /* renamed from: minus-xj2QHRw */
    private static final long m702minusxj2QHRw(long arg0, short other) {
        return m684constructorimpl(arg0 - m684constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    /* renamed from: minus-WZ4Q5Ns */
    private static final long m701minusWZ4Q5Ns(long arg0, int other) {
        return m684constructorimpl(arg0 - m684constructorimpl(other & 4294967295L));
    }

    /* renamed from: minus-VKZWuLQ */
    private static final long m700minusVKZWuLQ(long arg0, long other) {
        return m684constructorimpl(arg0 - other);
    }

    /* renamed from: times-7apg3OU */
    private static final long m720times7apg3OU(long arg0, byte other) {
        return m684constructorimpl(m684constructorimpl(other & 255) * arg0);
    }

    /* renamed from: times-xj2QHRw */
    private static final long m723timesxj2QHRw(long arg0, short other) {
        return m684constructorimpl(m684constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX) * arg0);
    }

    /* renamed from: times-WZ4Q5Ns */
    private static final long m722timesWZ4Q5Ns(long arg0, int other) {
        return m684constructorimpl(m684constructorimpl(other & 4294967295L) * arg0);
    }

    /* renamed from: times-VKZWuLQ */
    private static final long m721timesVKZWuLQ(long arg0, long other) {
        return m684constructorimpl(arg0 * other);
    }

    /* renamed from: div-7apg3OU */
    private static final long m686div7apg3OU(long arg0, byte other) {
        return Long.divideUnsigned(arg0, m684constructorimpl(other & 255));
    }

    /* renamed from: div-xj2QHRw */
    private static final long m689divxj2QHRw(long arg0, short other) {
        return Long.divideUnsigned(arg0, m684constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    /* renamed from: div-WZ4Q5Ns */
    private static final long m688divWZ4Q5Ns(long arg0, int other) {
        return Long.divideUnsigned(arg0, m684constructorimpl(other & 4294967295L));
    }

    /* renamed from: div-VKZWuLQ */
    private static final long m687divVKZWuLQ(long arg0, long other) {
        return UnsignedKt.m863ulongDivideeb3DHEI(arg0, other);
    }

    /* renamed from: rem-7apg3OU */
    private static final long m714rem7apg3OU(long arg0, byte other) {
        return Long.remainderUnsigned(arg0, m684constructorimpl(other & 255));
    }

    /* renamed from: rem-xj2QHRw */
    private static final long m717remxj2QHRw(long arg0, short other) {
        return Long.remainderUnsigned(arg0, m684constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    /* renamed from: rem-WZ4Q5Ns */
    private static final long m716remWZ4Q5Ns(long arg0, int other) {
        return Long.remainderUnsigned(arg0, m684constructorimpl(other & 4294967295L));
    }

    /* renamed from: rem-VKZWuLQ */
    private static final long m715remVKZWuLQ(long arg0, long other) {
        return UnsignedKt.m864ulongRemaindereb3DHEI(arg0, other);
    }

    /* renamed from: floorDiv-7apg3OU */
    private static final long m692floorDiv7apg3OU(long arg0, byte other) {
        return Long.divideUnsigned(arg0, m684constructorimpl(other & 255));
    }

    /* renamed from: floorDiv-xj2QHRw */
    private static final long m695floorDivxj2QHRw(long arg0, short other) {
        return Long.divideUnsigned(arg0, m684constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    /* renamed from: floorDiv-WZ4Q5Ns */
    private static final long m694floorDivWZ4Q5Ns(long arg0, int other) {
        return Long.divideUnsigned(arg0, m684constructorimpl(other & 4294967295L));
    }

    /* renamed from: floorDiv-VKZWuLQ */
    private static final long m693floorDivVKZWuLQ(long arg0, long other) {
        return Long.divideUnsigned(arg0, other);
    }

    /* renamed from: mod-7apg3OU */
    private static final byte m703mod7apg3OU(long arg0, byte other) {
        return UByte.m528constructorimpl((byte) Long.remainderUnsigned(arg0, m684constructorimpl(other & 255)));
    }

    /* renamed from: mod-xj2QHRw */
    private static final short m706modxj2QHRw(long arg0, short other) {
        return UShort.m791constructorimpl((short) Long.remainderUnsigned(arg0, m684constructorimpl(other & WebSocketProtocol.PAYLOAD_SHORT_MAX)));
    }

    /* renamed from: mod-WZ4Q5Ns */
    private static final int m705modWZ4Q5Ns(long arg0, int other) {
        return UInt.m605constructorimpl((int) Long.remainderUnsigned(arg0, m684constructorimpl(other & 4294967295L)));
    }

    /* renamed from: mod-VKZWuLQ */
    private static final long m704modVKZWuLQ(long arg0, long other) {
        return Long.remainderUnsigned(arg0, other);
    }

    /* renamed from: inc-s-VKNKU */
    private static final long m697incsVKNKU(long arg0) {
        return m684constructorimpl(1 + arg0);
    }

    /* renamed from: dec-s-VKNKU */
    private static final long m685decsVKNKU(long arg0) {
        return m684constructorimpl((-1) + arg0);
    }

    /* renamed from: rangeTo-VKZWuLQ */
    private static final ULongRange m712rangeToVKZWuLQ(long arg0, long other) {
        return new ULongRange(arg0, other, null);
    }

    /* renamed from: rangeUntil-VKZWuLQ */
    private static final ULongRange m713rangeUntilVKZWuLQ(long arg0, long other) {
        return URangesKt.m1788untileb3DHEI(arg0, other);
    }

    /* renamed from: shl-s-VKNKU */
    private static final long m718shlsVKNKU(long arg0, int bitCount) {
        return m684constructorimpl(arg0 << bitCount);
    }

    /* renamed from: shr-s-VKNKU */
    private static final long m719shrsVKNKU(long arg0, int bitCount) {
        return m684constructorimpl(arg0 >>> bitCount);
    }

    /* renamed from: and-VKZWuLQ */
    private static final long m677andVKZWuLQ(long arg0, long other) {
        return m684constructorimpl(arg0 & other);
    }

    /* renamed from: or-VKZWuLQ */
    private static final long m707orVKZWuLQ(long arg0, long other) {
        return m684constructorimpl(arg0 | other);
    }

    /* renamed from: xor-VKZWuLQ */
    private static final long m735xorVKZWuLQ(long arg0, long other) {
        return m684constructorimpl(arg0 ^ other);
    }

    /* renamed from: inv-s-VKNKU */
    private static final long m698invsVKNKU(long arg0) {
        return m684constructorimpl(~arg0);
    }

    /* renamed from: toByte-impl */
    private static final byte m724toByteimpl(long arg0) {
        return (byte) arg0;
    }

    /* renamed from: toShort-impl */
    private static final short m729toShortimpl(long arg0) {
        return (short) arg0;
    }

    /* renamed from: toInt-impl */
    private static final int m727toIntimpl(long arg0) {
        return (int) arg0;
    }

    /* renamed from: toLong-impl */
    private static final long m728toLongimpl(long arg0) {
        return arg0;
    }

    /* renamed from: toUByte-w2LRezQ */
    private static final byte m731toUBytew2LRezQ(long arg0) {
        return UByte.m528constructorimpl((byte) arg0);
    }

    /* renamed from: toUShort-Mh2AYeg */
    private static final short m734toUShortMh2AYeg(long arg0) {
        return UShort.m791constructorimpl((short) arg0);
    }

    /* renamed from: toUInt-pVg5ArA */
    private static final int m732toUIntpVg5ArA(long arg0) {
        return UInt.m605constructorimpl((int) arg0);
    }

    /* renamed from: toULong-s-VKNKU */
    private static final long m733toULongsVKNKU(long arg0) {
        return arg0;
    }

    /* renamed from: toFloat-impl */
    private static final float m726toFloatimpl(long arg0) {
        return (float) UnsignedKt.ulongToDouble(arg0);
    }

    /* renamed from: toDouble-impl */
    private static final double m725toDoubleimpl(long arg0) {
        return UnsignedKt.ulongToDouble(arg0);
    }

    /* renamed from: toString-impl */
    public static String m730toStringimpl(long arg0) {
        return UnsignedKt.ulongToString(arg0, 10);
    }

    public String toString() {
        return m730toStringimpl(this.data);
    }
}
