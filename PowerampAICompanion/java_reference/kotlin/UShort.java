package kotlin;

import androidx.exifinterface.media.ExifInterface;
import com.maxmpz.poweramp.player.RouterConsts;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: UShort.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0005\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087@\u0018\u0000 s2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001sB\u0011\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b\f\u0010\rJ\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0000H\u0097\n¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b\u0017\u0010\rJ\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\u0018\u0010\u000fJ\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u0019\u0010\u0012J\u0018\u0010\u0016\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b\u001d\u0010\rJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\u001e\u0010\u000fJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u001f\u0010\u0012J\u0018\u0010\u001c\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b \u0010\u001bJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b\"\u0010\rJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b#\u0010\u000fJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b$\u0010\u0012J\u0018\u0010!\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b%\u0010\u001bJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b'\u0010\rJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b(\u0010\u000fJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b)\u0010\u0012J\u0018\u0010&\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b*\u0010\u001bJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b,\u0010\rJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b-\u0010\u000fJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b.\u0010\u0012J\u0018\u0010+\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b/\u0010\u001bJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b1\u0010\rJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b2\u0010\u000fJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b3\u0010\u0012J\u0018\u00100\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b4\u0010\u001bJ\u0018\u00105\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b6\u00107J\u0018\u00105\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b8\u00109J\u0018\u00105\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b:\u0010\u0012J\u0018\u00105\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b;\u0010\u001bJ\u0010\u0010<\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b=\u0010\u0005J\u0010\u0010>\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b?\u0010\u0005J\u0018\u0010@\u001a\u00020A2\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bB\u0010CJ\u0018\u0010D\u001a\u00020A2\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bE\u0010CJ\u0018\u0010F\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bG\u00109J\u0018\u0010H\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bI\u00109J\u0018\u0010J\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bK\u00109J\u0010\u0010L\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\bM\u0010\u0005J\u0010\u0010N\u001a\u00020OH\u0087\b¢\u0006\u0004\bP\u0010QJ\u0010\u0010R\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bS\u0010\u0005J\u0010\u0010T\u001a\u00020\tH\u0087\b¢\u0006\u0004\bU\u0010VJ\u0010\u0010W\u001a\u00020XH\u0087\b¢\u0006\u0004\bY\u0010ZJ\u0010\u0010[\u001a\u00020\u000bH\u0087\b¢\u0006\u0004\b\\\u0010QJ\u0010\u0010]\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b^\u0010\u0005J\u0010\u0010_\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b`\u0010VJ\u0010\u0010a\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\bb\u0010ZJ\u0010\u0010c\u001a\u00020dH\u0087\b¢\u0006\u0004\be\u0010fJ\u0010\u0010g\u001a\u00020hH\u0087\b¢\u0006\u0004\bi\u0010jJ\u000f\u0010k\u001a\u00020lH\u0016¢\u0006\u0004\bm\u0010nJ\u0013\u0010o\u001a\u00020p2\b\u0010\n\u001a\u0004\u0018\u00010qHÖ\u0003J\t\u0010r\u001a\u00020\tHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006t"}, d2 = {"Lkotlin/UShort;", "", "data", "", "constructor-impl", "(S)S", "getData$annotations", "()V", "compareTo", "", RouterConsts.DEVICE_NAME_OTHER, "Lkotlin/UByte;", "compareTo-7apg3OU", "(SB)I", "compareTo-xj2QHRw", "(SS)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(SI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(SJ)I", "plus", "plus-7apg3OU", "plus-xj2QHRw", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "(SJ)J", "minus", "minus-7apg3OU", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times", "times-7apg3OU", "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div", "div-7apg3OU", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem", "rem-7apg3OU", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "floorDiv", "floorDiv-7apg3OU", "floorDiv-xj2QHRw", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "mod", "mod-7apg3OU", "(SB)B", "mod-xj2QHRw", "(SS)S", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "inc", "inc-Mh2AYeg", "dec", "dec-Mh2AYeg", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-xj2QHRw", "(SS)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-xj2QHRw", "and", "and-xj2QHRw", "or", "or-xj2QHRw", "xor", "xor-xj2QHRw", "inv", "inv-Mh2AYeg", "toByte", "", "toByte-impl", "(S)B", "toShort", "toShort-impl", "toInt", "toInt-impl", "(S)I", "toLong", "", "toLong-impl", "(S)J", "toUByte", "toUByte-w2LRezQ", "toUShort", "toUShort-Mh2AYeg", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toFloat", "", "toFloat-impl", "(S)F", "toDouble", "", "toDouble-impl", "(S)D", "toString", "", "toString-impl", "(S)Ljava/lang/String;", "equals", "", "", "hashCode", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes9.dex */
public final class UShort implements Comparable<UShort> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final short MAX_VALUE = -1;
    public static final short MIN_VALUE = 0;
    public static final int SIZE_BITS = 16;
    public static final int SIZE_BYTES = 2;
    private final short data;

    /* renamed from: box-impl */
    public static final /* synthetic */ UShort m785boximpl(short s) {
        return new UShort(s);
    }

    /* renamed from: constructor-impl */
    public static short m791constructorimpl(short s) {
        return s;
    }

    /* renamed from: equals-impl */
    public static boolean m797equalsimpl(short s, Object obj) {
        return (obj instanceof UShort) && s == ((UShort) obj).getData();
    }

    /* renamed from: equals-impl0 */
    public static final boolean m798equalsimpl0(short s, short s2) {
        return s == s2;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl */
    public static int m803hashCodeimpl(short s) {
        return Short.hashCode(s);
    }

    public boolean equals(Object r2) {
        return m797equalsimpl(this.data, r2);
    }

    public int hashCode() {
        return m803hashCodeimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: from getter */
    public final /* synthetic */ short getData() {
        return this.data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UShort uShort) {
        return Intrinsics.compare(getData() & MAX_VALUE, uShort.getData() & MAX_VALUE);
    }

    private /* synthetic */ UShort(short data) {
        this.data = data;
    }

    /* compiled from: UShort.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/UShort$Companion;", "", "<init>", "()V", "MIN_VALUE", "Lkotlin/UShort;", ExifInterface.LATITUDE_SOUTH, "MAX_VALUE", "SIZE_BYTES", "", "SIZE_BITS", "kotlin-stdlib"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* renamed from: compareTo-7apg3OU */
    private static final int m786compareTo7apg3OU(short arg0, byte other) {
        return Intrinsics.compare(65535 & arg0, other & 255);
    }

    /* renamed from: compareTo-xj2QHRw */
    private int m789compareToxj2QHRw(short other) {
        return Intrinsics.compare(getData() & MAX_VALUE, 65535 & other);
    }

    /* renamed from: compareTo-xj2QHRw */
    private static int m790compareToxj2QHRw(short arg0, short other) {
        return Intrinsics.compare(arg0 & MAX_VALUE, 65535 & other);
    }

    /* renamed from: compareTo-WZ4Q5Ns */
    private static final int m788compareToWZ4Q5Ns(short arg0, int other) {
        return Integer.compareUnsigned(UInt.m605constructorimpl(65535 & arg0), other);
    }

    /* renamed from: compareTo-VKZWuLQ */
    private static final int m787compareToVKZWuLQ(short arg0, long other) {
        return Long.compareUnsigned(ULong.m684constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    /* renamed from: plus-7apg3OU */
    private static final int m815plus7apg3OU(short arg0, byte other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(65535 & arg0) + UInt.m605constructorimpl(other & 255));
    }

    /* renamed from: plus-xj2QHRw */
    private static final int m818plusxj2QHRw(short arg0, short other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(arg0 & MAX_VALUE) + UInt.m605constructorimpl(65535 & other));
    }

    /* renamed from: plus-WZ4Q5Ns */
    private static final int m817plusWZ4Q5Ns(short arg0, int other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(65535 & arg0) + other);
    }

    /* renamed from: plus-VKZWuLQ */
    private static final long m816plusVKZWuLQ(short arg0, long other) {
        return ULong.m684constructorimpl(ULong.m684constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX) + other);
    }

    /* renamed from: minus-7apg3OU */
    private static final int m806minus7apg3OU(short arg0, byte other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(65535 & arg0) - UInt.m605constructorimpl(other & 255));
    }

    /* renamed from: minus-xj2QHRw */
    private static final int m809minusxj2QHRw(short arg0, short other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(arg0 & MAX_VALUE) - UInt.m605constructorimpl(65535 & other));
    }

    /* renamed from: minus-WZ4Q5Ns */
    private static final int m808minusWZ4Q5Ns(short arg0, int other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(65535 & arg0) - other);
    }

    /* renamed from: minus-VKZWuLQ */
    private static final long m807minusVKZWuLQ(short arg0, long other) {
        return ULong.m684constructorimpl(ULong.m684constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX) - other);
    }

    /* renamed from: times-7apg3OU */
    private static final int m825times7apg3OU(short arg0, byte other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(65535 & arg0) * UInt.m605constructorimpl(other & 255));
    }

    /* renamed from: times-xj2QHRw */
    private static final int m828timesxj2QHRw(short arg0, short other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(arg0 & MAX_VALUE) * UInt.m605constructorimpl(65535 & other));
    }

    /* renamed from: times-WZ4Q5Ns */
    private static final int m827timesWZ4Q5Ns(short arg0, int other) {
        return UInt.m605constructorimpl(UInt.m605constructorimpl(65535 & arg0) * other);
    }

    /* renamed from: times-VKZWuLQ */
    private static final long m826timesVKZWuLQ(short arg0, long other) {
        return ULong.m684constructorimpl(ULong.m684constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX) * other);
    }

    /* renamed from: div-7apg3OU */
    private static final int m793div7apg3OU(short arg0, byte other) {
        return Integer.divideUnsigned(UInt.m605constructorimpl(65535 & arg0), UInt.m605constructorimpl(other & 255));
    }

    /* renamed from: div-xj2QHRw */
    private static final int m796divxj2QHRw(short arg0, short other) {
        return Integer.divideUnsigned(UInt.m605constructorimpl(arg0 & MAX_VALUE), UInt.m605constructorimpl(65535 & other));
    }

    /* renamed from: div-WZ4Q5Ns */
    private static final int m795divWZ4Q5Ns(short arg0, int other) {
        return Integer.divideUnsigned(UInt.m605constructorimpl(65535 & arg0), other);
    }

    /* renamed from: div-VKZWuLQ */
    private static final long m794divVKZWuLQ(short arg0, long other) {
        return Long.divideUnsigned(ULong.m684constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    /* renamed from: rem-7apg3OU */
    private static final int m821rem7apg3OU(short arg0, byte other) {
        return Integer.remainderUnsigned(UInt.m605constructorimpl(65535 & arg0), UInt.m605constructorimpl(other & 255));
    }

    /* renamed from: rem-xj2QHRw */
    private static final int m824remxj2QHRw(short arg0, short other) {
        return Integer.remainderUnsigned(UInt.m605constructorimpl(arg0 & MAX_VALUE), UInt.m605constructorimpl(65535 & other));
    }

    /* renamed from: rem-WZ4Q5Ns */
    private static final int m823remWZ4Q5Ns(short arg0, int other) {
        return Integer.remainderUnsigned(UInt.m605constructorimpl(65535 & arg0), other);
    }

    /* renamed from: rem-VKZWuLQ */
    private static final long m822remVKZWuLQ(short arg0, long other) {
        return Long.remainderUnsigned(ULong.m684constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    /* renamed from: floorDiv-7apg3OU */
    private static final int m799floorDiv7apg3OU(short arg0, byte other) {
        return Integer.divideUnsigned(UInt.m605constructorimpl(65535 & arg0), UInt.m605constructorimpl(other & 255));
    }

    /* renamed from: floorDiv-xj2QHRw */
    private static final int m802floorDivxj2QHRw(short arg0, short other) {
        return Integer.divideUnsigned(UInt.m605constructorimpl(arg0 & MAX_VALUE), UInt.m605constructorimpl(65535 & other));
    }

    /* renamed from: floorDiv-WZ4Q5Ns */
    private static final int m801floorDivWZ4Q5Ns(short arg0, int other) {
        return Integer.divideUnsigned(UInt.m605constructorimpl(65535 & arg0), other);
    }

    /* renamed from: floorDiv-VKZWuLQ */
    private static final long m800floorDivVKZWuLQ(short arg0, long other) {
        return Long.divideUnsigned(ULong.m684constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    /* renamed from: mod-7apg3OU */
    private static final byte m810mod7apg3OU(short arg0, byte other) {
        return UByte.m528constructorimpl((byte) Integer.remainderUnsigned(UInt.m605constructorimpl(65535 & arg0), UInt.m605constructorimpl(other & 255)));
    }

    /* renamed from: mod-xj2QHRw */
    private static final short m813modxj2QHRw(short arg0, short other) {
        return m791constructorimpl((short) Integer.remainderUnsigned(UInt.m605constructorimpl(arg0 & MAX_VALUE), UInt.m605constructorimpl(65535 & other)));
    }

    /* renamed from: mod-WZ4Q5Ns */
    private static final int m812modWZ4Q5Ns(short arg0, int other) {
        return Integer.remainderUnsigned(UInt.m605constructorimpl(65535 & arg0), other);
    }

    /* renamed from: mod-VKZWuLQ */
    private static final long m811modVKZWuLQ(short arg0, long other) {
        return Long.remainderUnsigned(ULong.m684constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    /* renamed from: inc-Mh2AYeg */
    private static final short m804incMh2AYeg(short arg0) {
        return m791constructorimpl((short) (arg0 + 1));
    }

    /* renamed from: dec-Mh2AYeg */
    private static final short m792decMh2AYeg(short arg0) {
        return m791constructorimpl((short) (arg0 - 1));
    }

    /* renamed from: rangeTo-xj2QHRw */
    private static final UIntRange m819rangeToxj2QHRw(short arg0, short other) {
        return new UIntRange(UInt.m605constructorimpl(arg0 & MAX_VALUE), UInt.m605constructorimpl(65535 & other), null);
    }

    /* renamed from: rangeUntil-xj2QHRw */
    private static final UIntRange m820rangeUntilxj2QHRw(short arg0, short other) {
        return URangesKt.m1786untilJ1ME1BU(UInt.m605constructorimpl(arg0 & MAX_VALUE), UInt.m605constructorimpl(65535 & other));
    }

    /* renamed from: and-xj2QHRw */
    private static final short m784andxj2QHRw(short arg0, short other) {
        return m791constructorimpl((short) (arg0 & other));
    }

    /* renamed from: or-xj2QHRw */
    private static final short m814orxj2QHRw(short arg0, short other) {
        return m791constructorimpl((short) (arg0 | other));
    }

    /* renamed from: xor-xj2QHRw */
    private static final short m840xorxj2QHRw(short arg0, short other) {
        return m791constructorimpl((short) (arg0 ^ other));
    }

    /* renamed from: inv-Mh2AYeg */
    private static final short m805invMh2AYeg(short arg0) {
        return m791constructorimpl((short) (~arg0));
    }

    /* renamed from: toByte-impl */
    private static final byte m829toByteimpl(short arg0) {
        return (byte) arg0;
    }

    /* renamed from: toShort-impl */
    private static final short m834toShortimpl(short arg0) {
        return arg0;
    }

    /* renamed from: toInt-impl */
    private static final int m832toIntimpl(short arg0) {
        return 65535 & arg0;
    }

    /* renamed from: toLong-impl */
    private static final long m833toLongimpl(short arg0) {
        return arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX;
    }

    /* renamed from: toUByte-w2LRezQ */
    private static final byte m836toUBytew2LRezQ(short arg0) {
        return UByte.m528constructorimpl((byte) arg0);
    }

    /* renamed from: toUShort-Mh2AYeg */
    private static final short m839toUShortMh2AYeg(short arg0) {
        return arg0;
    }

    /* renamed from: toUInt-pVg5ArA */
    private static final int m837toUIntpVg5ArA(short arg0) {
        return UInt.m605constructorimpl(65535 & arg0);
    }

    /* renamed from: toULong-s-VKNKU */
    private static final long m838toULongsVKNKU(short arg0) {
        return ULong.m684constructorimpl(arg0 & WebSocketProtocol.PAYLOAD_SHORT_MAX);
    }

    /* renamed from: toFloat-impl */
    private static final float m831toFloatimpl(short arg0) {
        return (float) UnsignedKt.uintToDouble(65535 & arg0);
    }

    /* renamed from: toDouble-impl */
    private static final double m830toDoubleimpl(short arg0) {
        return UnsignedKt.uintToDouble(65535 & arg0);
    }

    /* renamed from: toString-impl */
    public static String m835toStringimpl(short arg0) {
        return String.valueOf(65535 & arg0);
    }

    public String toString() {
        return m835toStringimpl(this.data);
    }
}
