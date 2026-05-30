package kotlin;

import com.maxmpz.poweramp.player.RouterConsts;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;

/* compiled from: UByte.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087@\u0018\u0000 s2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001sB\u0011\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0000H\u0097\n¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\u0017\u0010\fJ\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\u0018\u0010\u000fJ\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u0019\u0010\u0012J\u0018\u0010\u0016\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\u001d\u0010\fJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\u001e\u0010\u000fJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u001f\u0010\u0012J\u0018\u0010\u001c\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b \u0010\u001bJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\"\u0010\fJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b#\u0010\u000fJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b$\u0010\u0012J\u0018\u0010!\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b%\u0010\u001bJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b'\u0010\fJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b(\u0010\u000fJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b)\u0010\u0012J\u0018\u0010&\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b*\u0010\u001bJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b,\u0010\fJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b-\u0010\u000fJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b.\u0010\u0012J\u0018\u0010+\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b/\u0010\u001bJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b1\u0010\fJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\b¢\u0006\u0004\b2\u0010\u000fJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b3\u0010\u0012J\u0018\u00100\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b4\u0010\u001bJ\u0018\u00105\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b6\u00107J\u0018\u00105\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\rH\u0087\b¢\u0006\u0004\b8\u00109J\u0018\u00105\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b:\u0010\u0012J\u0018\u00105\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b;\u0010\u001bJ\u0010\u0010<\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b=\u0010\u0005J\u0010\u0010>\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b?\u0010\u0005J\u0018\u0010@\u001a\u00020A2\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bB\u0010CJ\u0018\u0010D\u001a\u00020A2\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bE\u0010CJ\u0018\u0010F\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bG\u00107J\u0018\u0010H\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bI\u00107J\u0018\u0010J\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bK\u00107J\u0010\u0010L\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\bM\u0010\u0005J\u0010\u0010N\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bO\u0010\u0005J\u0010\u0010P\u001a\u00020QH\u0087\b¢\u0006\u0004\bR\u0010SJ\u0010\u0010T\u001a\u00020\tH\u0087\b¢\u0006\u0004\bU\u0010VJ\u0010\u0010W\u001a\u00020XH\u0087\b¢\u0006\u0004\bY\u0010ZJ\u0010\u0010[\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b\\\u0010\u0005J\u0010\u0010]\u001a\u00020\rH\u0087\b¢\u0006\u0004\b^\u0010SJ\u0010\u0010_\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b`\u0010VJ\u0010\u0010a\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\bb\u0010ZJ\u0010\u0010c\u001a\u00020dH\u0087\b¢\u0006\u0004\be\u0010fJ\u0010\u0010g\u001a\u00020hH\u0087\b¢\u0006\u0004\bi\u0010jJ\u000f\u0010k\u001a\u00020lH\u0016¢\u0006\u0004\bm\u0010nJ\u0013\u0010o\u001a\u00020p2\b\u0010\n\u001a\u0004\u0018\u00010qHÖ\u0003J\t\u0010r\u001a\u00020\tHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006t"}, d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "getData$annotations", "()V", "compareTo", "", RouterConsts.DEVICE_NAME_OTHER, "compareTo-7apg3OU", "(BB)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "plus", "plus-7apg3OU", "plus-xj2QHRw", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "(BJ)J", "minus", "minus-7apg3OU", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times", "times-7apg3OU", "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div", "div-7apg3OU", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem", "rem-7apg3OU", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "floorDiv", "floorDiv-7apg3OU", "floorDiv-xj2QHRw", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "mod", "mod-7apg3OU", "(BB)B", "mod-xj2QHRw", "(BS)S", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "inc", "inc-w2LRezQ", "dec", "dec-w2LRezQ", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-7apg3OU", "and", "and-7apg3OU", "or", "or-7apg3OU", "xor", "xor-7apg3OU", "inv", "inv-w2LRezQ", "toByte", "toByte-impl", "toShort", "", "toShort-impl", "(B)S", "toInt", "toInt-impl", "(B)I", "toLong", "", "toLong-impl", "(B)J", "toUByte", "toUByte-w2LRezQ", "toUShort", "toUShort-Mh2AYeg", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toFloat", "", "toFloat-impl", "(B)F", "toDouble", "", "toDouble-impl", "(B)D", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "equals", "", "", "hashCode", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes9.dex */
public final class UByte implements Comparable<UByte> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    /* renamed from: box-impl */
    public static final /* synthetic */ UByte m522boximpl(byte b) {
        return new UByte(b);
    }

    /* renamed from: constructor-impl */
    public static byte m528constructorimpl(byte b) {
        return b;
    }

    /* renamed from: equals-impl */
    public static boolean m534equalsimpl(byte b, Object obj) {
        return (obj instanceof UByte) && b == ((UByte) obj).getData();
    }

    /* renamed from: equals-impl0 */
    public static final boolean m535equalsimpl0(byte b, byte b2) {
        return b == b2;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl */
    public static int m540hashCodeimpl(byte b) {
        return Byte.hashCode(b);
    }

    public boolean equals(Object r2) {
        return m534equalsimpl(this.data, r2);
    }

    public int hashCode() {
        return m540hashCodeimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: from getter */
    public final /* synthetic */ byte getData() {
        return this.data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UByte uByte) {
        return Intrinsics.compare(getData() & 255, uByte.getData() & 255);
    }

    private /* synthetic */ UByte(byte data) {
        this.data = data;
    }

    /* compiled from: UByte.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/UByte$Companion;", "", "<init>", "()V", "MIN_VALUE", "Lkotlin/UByte;", "B", "MAX_VALUE", "SIZE_BYTES", "", "SIZE_BITS", "kotlin-stdlib"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* renamed from: compareTo-7apg3OU */
    private int m523compareTo7apg3OU(byte other) {
        return Intrinsics.compare(getData() & 255, other & 255);
    }

    /* renamed from: compareTo-7apg3OU */
    private static int m524compareTo7apg3OU(byte arg0, byte other) {
        return Intrinsics.compare(arg0 & 255, other & 255);
    }

    /* renamed from: compareTo-xj2QHRw */
    private static final int m527compareToxj2QHRw(byte arg0, short other) {
        return Intrinsics.compare(arg0 & 255, 65535 & other);
    }

    /* renamed from: compareTo-WZ4Q5Ns */
    private static final int m526compareToWZ4Q5Ns(byte arg0, int other) {
        return Integer.compareUnsigned(UInt.m605constructorimpl(arg0 & 255), other);
    }

    /* renamed from: compareTo-VKZWuLQ */
    private static final int m525compareToVKZWuLQ(byte arg0, long other) {
        return Long.compareUnsigned(ULong.m684constructorimpl(arg0 & 255), other);
    }

    /* renamed from: plus-7apg3OU */
    private static final int m552plus7apg3OU(byte arg0, byte other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(arg0 & 255) + UInt.m605constructorimpl(other & 255));
    }

    /* renamed from: plus-xj2QHRw */
    private static final int m555plusxj2QHRw(byte arg0, short other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(arg0 & 255) + UInt.m605constructorimpl(65535 & other));
    }

    /* renamed from: plus-WZ4Q5Ns */
    private static final int m554plusWZ4Q5Ns(byte arg0, int other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(arg0 & 255) + other);
    }

    /* renamed from: plus-VKZWuLQ */
    private static final long m553plusVKZWuLQ(byte arg0, long other) {
        return ULong.m684constructorimpl(ULong.m684constructorimpl(arg0 & 255) + other);
    }

    /* renamed from: minus-7apg3OU */
    private static final int m543minus7apg3OU(byte arg0, byte other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(arg0 & 255) - UInt.m605constructorimpl(other & 255));
    }

    /* renamed from: minus-xj2QHRw */
    private static final int m546minusxj2QHRw(byte arg0, short other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(arg0 & 255) - UInt.m605constructorimpl(65535 & other));
    }

    /* renamed from: minus-WZ4Q5Ns */
    private static final int m545minusWZ4Q5Ns(byte arg0, int other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(arg0 & 255) - other);
    }

    /* renamed from: minus-VKZWuLQ */
    private static final long m544minusVKZWuLQ(byte arg0, long other) {
        return ULong.m684constructorimpl(ULong.m684constructorimpl(arg0 & 255) - other);
    }

    /* renamed from: times-7apg3OU */
    private static final int m562times7apg3OU(byte arg0, byte other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(arg0 & 255) * UInt.m605constructorimpl(other & 255));
    }

    /* renamed from: times-xj2QHRw */
    private static final int m565timesxj2QHRw(byte arg0, short other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(arg0 & 255) * UInt.m605constructorimpl(65535 & other));
    }

    /* renamed from: times-WZ4Q5Ns */
    private static final int m564timesWZ4Q5Ns(byte arg0, int other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(arg0 & 255) * other);
    }

    /* renamed from: times-VKZWuLQ */
    private static final long m563timesVKZWuLQ(byte arg0, long other) {
        return ULong.m684constructorimpl(ULong.m684constructorimpl(arg0 & 255) * other);
    }

    /* renamed from: div-7apg3OU */
    private static final int m530div7apg3OU(byte arg0, byte other) {
        return Integer.divideUnsigned(UInt.m605constructorimpl(arg0 & 255), UInt.m605constructorimpl(other & 255));
    }

    /* renamed from: div-xj2QHRw */
    private static final int m533divxj2QHRw(byte arg0, short other) {
        return Integer.divideUnsigned(UInt.m605constructorimpl(arg0 & 255), UInt.m605constructorimpl(65535 & other));
    }

    /* renamed from: div-WZ4Q5Ns */
    private static final int m532divWZ4Q5Ns(byte arg0, int other) {
        return Integer.divideUnsigned(UInt.m605constructorimpl(arg0 & 255), other);
    }

    /* renamed from: div-VKZWuLQ */
    private static final long m531divVKZWuLQ(byte arg0, long other) {
        return Long.divideUnsigned(ULong.m684constructorimpl(arg0 & 255), other);
    }

    /* renamed from: rem-7apg3OU */
    private static final int m558rem7apg3OU(byte arg0, byte other) {
        return Integer.remainderUnsigned(UInt.m605constructorimpl(arg0 & 255), UInt.m605constructorimpl(other & 255));
    }

    /* renamed from: rem-xj2QHRw */
    private static final int m561remxj2QHRw(byte arg0, short other) {
        return Integer.remainderUnsigned(UInt.m605constructorimpl(arg0 & 255), UInt.m605constructorimpl(65535 & other));
    }

    /* renamed from: rem-WZ4Q5Ns */
    private static final int m560remWZ4Q5Ns(byte arg0, int other) {
        return Integer.remainderUnsigned(UInt.m605constructorimpl(arg0 & 255), other);
    }

    /* renamed from: rem-VKZWuLQ */
    private static final long m559remVKZWuLQ(byte arg0, long other) {
        return Long.remainderUnsigned(ULong.m684constructorimpl(arg0 & 255), other);
    }

    /* renamed from: floorDiv-7apg3OU */
    private static final int m536floorDiv7apg3OU(byte arg0, byte other) {
        return Integer.divideUnsigned(UInt.m605constructorimpl(arg0 & 255), UInt.m605constructorimpl(other & 255));
    }

    /* renamed from: floorDiv-xj2QHRw */
    private static final int m539floorDivxj2QHRw(byte arg0, short other) {
        return Integer.divideUnsigned(UInt.m605constructorimpl(arg0 & 255), UInt.m605constructorimpl(65535 & other));
    }

    /* renamed from: floorDiv-WZ4Q5Ns */
    private static final int m538floorDivWZ4Q5Ns(byte arg0, int other) {
        return Integer.divideUnsigned(UInt.m605constructorimpl(arg0 & 255), other);
    }

    /* renamed from: floorDiv-VKZWuLQ */
    private static final long m537floorDivVKZWuLQ(byte arg0, long other) {
        return Long.divideUnsigned(ULong.m684constructorimpl(arg0 & 255), other);
    }

    /* renamed from: mod-7apg3OU */
    private static final byte m547mod7apg3OU(byte arg0, byte other) {
        return m528constructorimpl((byte) Integer.remainderUnsigned(UInt.m605constructorimpl(arg0 & 255), UInt.m605constructorimpl(other & 255)));
    }

    /* renamed from: mod-xj2QHRw */
    private static final short m550modxj2QHRw(byte arg0, short other) {
        return UShort.m791constructorimpl((short) Integer.remainderUnsigned(UInt.m605constructorimpl(arg0 & 255), UInt.m605constructorimpl(65535 & other)));
    }

    /* renamed from: mod-WZ4Q5Ns */
    private static final int m549modWZ4Q5Ns(byte arg0, int other) {
        return Integer.remainderUnsigned(UInt.m605constructorimpl(arg0 & 255), other);
    }

    /* renamed from: mod-VKZWuLQ */
    private static final long m548modVKZWuLQ(byte arg0, long other) {
        return Long.remainderUnsigned(ULong.m684constructorimpl(arg0 & 255), other);
    }

    /* renamed from: inc-w2LRezQ */
    private static final byte m541incw2LRezQ(byte arg0) {
        return m528constructorimpl((byte) (arg0 + 1));
    }

    /* renamed from: dec-w2LRezQ */
    private static final byte m529decw2LRezQ(byte arg0) {
        return m528constructorimpl((byte) (arg0 - 1));
    }

    /* renamed from: rangeTo-7apg3OU */
    private static final UIntRange m556rangeTo7apg3OU(byte arg0, byte other) {
        return new UIntRange(UInt.m605constructorimpl(arg0 & 255), UInt.m605constructorimpl(other & 255), null);
    }

    /* renamed from: rangeUntil-7apg3OU */
    private static final UIntRange m557rangeUntil7apg3OU(byte arg0, byte other) {
        return URangesKt.m1786untilJ1ME1BU(UInt.m605constructorimpl(arg0 & 255), UInt.m605constructorimpl(other & 255));
    }

    /* renamed from: and-7apg3OU */
    private static final byte m521and7apg3OU(byte arg0, byte other) {
        return m528constructorimpl((byte) (arg0 & other));
    }

    /* renamed from: or-7apg3OU */
    private static final byte m551or7apg3OU(byte arg0, byte other) {
        return m528constructorimpl((byte) (arg0 | other));
    }

    /* renamed from: xor-7apg3OU */
    private static final byte m577xor7apg3OU(byte arg0, byte other) {
        return m528constructorimpl((byte) (arg0 ^ other));
    }

    /* renamed from: inv-w2LRezQ */
    private static final byte m542invw2LRezQ(byte arg0) {
        return m528constructorimpl((byte) (~arg0));
    }

    /* renamed from: toByte-impl */
    private static final byte m566toByteimpl(byte arg0) {
        return arg0;
    }

    /* renamed from: toShort-impl */
    private static final short m571toShortimpl(byte arg0) {
        return (short) (arg0 & 255);
    }

    /* renamed from: toInt-impl */
    private static final int m569toIntimpl(byte arg0) {
        return arg0 & 255;
    }

    /* renamed from: toLong-impl */
    private static final long m570toLongimpl(byte arg0) {
        return arg0 & 255;
    }

    /* renamed from: toUByte-w2LRezQ */
    private static final byte m573toUBytew2LRezQ(byte arg0) {
        return arg0;
    }

    /* renamed from: toUShort-Mh2AYeg */
    private static final short m576toUShortMh2AYeg(byte arg0) {
        return UShort.m791constructorimpl((short) (arg0 & 255));
    }

    /* renamed from: toUInt-pVg5ArA */
    private static final int m574toUIntpVg5ArA(byte arg0) {
        return UInt.m605constructorimpl(arg0 & 255);
    }

    /* renamed from: toULong-s-VKNKU */
    private static final long m575toULongsVKNKU(byte arg0) {
        return ULong.m684constructorimpl(arg0 & 255);
    }

    /* renamed from: toFloat-impl */
    private static final float m568toFloatimpl(byte arg0) {
        return (float) UnsignedKt.uintToDouble(arg0 & 255);
    }

    /* renamed from: toDouble-impl */
    private static final double m567toDoubleimpl(byte arg0) {
        return UnsignedKt.uintToDouble(arg0 & 255);
    }

    /* renamed from: toString-impl */
    public static String m572toStringimpl(byte arg0) {
        return String.valueOf(arg0 & 255);
    }

    public String toString() {
        return m572toStringimpl(this.data);
    }
}
