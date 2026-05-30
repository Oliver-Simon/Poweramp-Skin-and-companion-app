package kotlin.internal;

import kotlin.Metadata;
import kotlin.UInt;
import kotlin.ULong;

/* compiled from: UProgressionUtil.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a'\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u000f\u0010\u0006\u001a'\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0011\u0010\t¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class UProgressionUtilKt {
    /* renamed from: differenceModulo-WZ9TVnA, reason: not valid java name */
    private static final int m1722differenceModuloWZ9TVnA(int a, int b, int c) {
        int ac = Integer.remainderUnsigned(a, c);
        int bc = Integer.remainderUnsigned(b, c);
        return UInt.m605constructorimpl(Integer.compareUnsigned(ac, bc) >= 0 ? ac - bc : UInt.m605constructorimpl(ac - bc) + c);
    }

    /* renamed from: differenceModulo-sambcqE, reason: not valid java name */
    private static final long m1723differenceModulosambcqE(long a, long b, long c) {
        long ac = Long.remainderUnsigned(a, c);
        long bc = Long.remainderUnsigned(b, c);
        return ULong.m684constructorimpl(Long.compareUnsigned(ac, bc) >= 0 ? ac - bc : ULong.m684constructorimpl(ac - bc) + c);
    }

    /* renamed from: getProgressionLastElement-Nkh28Cs, reason: not valid java name */
    public static final int m1725getProgressionLastElementNkh28Cs(int start, int end, int step) {
        if (step > 0) {
            if (Integer.compareUnsigned(start, end) < 0) {
                return UInt.m605constructorimpl(end - m1722differenceModuloWZ9TVnA(end, start, UInt.m605constructorimpl(step)));
            }
        } else {
            if (step >= 0) {
                throw new IllegalArgumentException("Step is zero.");
            }
            if (Integer.compareUnsigned(start, end) > 0) {
                return UInt.m605constructorimpl(m1722differenceModuloWZ9TVnA(start, end, UInt.m605constructorimpl(-step)) + end);
            }
        }
        return end;
    }

    /* renamed from: getProgressionLastElement-7ftBX0g, reason: not valid java name */
    public static final long m1724getProgressionLastElement7ftBX0g(long start, long end, long step) {
        if (step > 0) {
            if (Long.compareUnsigned(start, end) < 0) {
                return ULong.m684constructorimpl(end - m1723differenceModulosambcqE(end, start, ULong.m684constructorimpl(step)));
            }
        } else if (step < 0) {
            if (Long.compareUnsigned(start, end) > 0) {
                return ULong.m684constructorimpl(m1723differenceModulosambcqE(start, end, ULong.m684constructorimpl(-step)) + end);
            }
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
        return end;
    }
}
