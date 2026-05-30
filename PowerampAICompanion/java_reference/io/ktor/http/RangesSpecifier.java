package io.ktor.http;

import androidx.exifinterface.media.ExifInterface;
import com.maxmpz.poweramp.player.RouterConsts;
import io.ktor.http.ContentRange;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;

/* compiled from: RangesSpecifier.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007B\u001d\u0012\b\b\u0002\u0010\u0002\u001a\u00020\b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\bHÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\b2\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\u001c\u0010\u0016\u001a\u00020\u00122\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u0018J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00052\u0006\u0010\u001b\u001a\u00020\u001cJ\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00052\u0006\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u0015J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\b\u0010\u001f\u001a\u00020\bH\u0016J\u001f\u0010 \u001a\b\u0012\u0004\u0012\u0002H!0\u0005\"\u0004\b\u0000\u0010!*\u0004\u0018\u0001H!H\u0002¢\u0006\u0002\u0010\"R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006#"}, d2 = {"Lio/ktor/http/RangesSpecifier;", "", "unit", "Lio/ktor/http/RangeUnits;", "ranges", "", "Lio/ktor/http/ContentRange;", "(Lio/ktor/http/RangeUnits;Ljava/util/List;)V", "", "(Ljava/lang/String;Ljava/util/List;)V", "getRanges", "()Ljava/util/List;", "getUnit", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "", "isValid", "rangeUnitPredicate", "Lkotlin/Function1;", "merge", "Lkotlin/ranges/LongRange;", "length", "", "maxRangeCount", "mergeToSingle", "toString", "toList", ExifInterface.GPS_DIRECTION_TRUE, "(Ljava/lang/Object;)Ljava/util/List;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final /* data */ class RangesSpecifier {
    private final List<ContentRange> ranges;
    private final String unit;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RangesSpecifier copy$default(RangesSpecifier rangesSpecifier, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rangesSpecifier.unit;
        }
        if ((i & 2) != 0) {
            list = rangesSpecifier.ranges;
        }
        return rangesSpecifier.copy(str, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getUnit() {
        return this.unit;
    }

    public final List<ContentRange> component2() {
        return this.ranges;
    }

    public final RangesSpecifier copy(String unit, List<? extends ContentRange> ranges) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(ranges, "ranges");
        return new RangesSpecifier(unit, ranges);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RangesSpecifier)) {
            return false;
        }
        RangesSpecifier rangesSpecifier = (RangesSpecifier) other;
        return Intrinsics.areEqual(this.unit, rangesSpecifier.unit) && Intrinsics.areEqual(this.ranges, rangesSpecifier.ranges);
    }

    public int hashCode() {
        return (this.unit.hashCode() * 31) + this.ranges.hashCode();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RangesSpecifier(String unit, List<? extends ContentRange> ranges) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(ranges, "ranges");
        this.unit = unit;
        this.ranges = ranges;
        if (this.ranges.isEmpty()) {
            throw new IllegalArgumentException("It should be at least one range".toString());
        }
    }

    public /* synthetic */ RangesSpecifier(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? RangeUnits.Bytes.getUnitToken() : str, (List<? extends ContentRange>) list);
    }

    public final List<ContentRange> getRanges() {
        return this.ranges;
    }

    public final String getUnit() {
        return this.unit;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RangesSpecifier(RangeUnits unit, List<? extends ContentRange> ranges) {
        this(unit.getUnitToken(), ranges);
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(ranges, "ranges");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean isValid$default(RangesSpecifier rangesSpecifier, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<String, Boolean>() { // from class: io.ktor.http.RangesSpecifier$isValid$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Boolean.valueOf(Intrinsics.areEqual(it, RangeUnits.Bytes.getUnitToken()));
                }
            };
        }
        return rangesSpecifier.isValid(function1);
    }

    public final boolean isValid(Function1<? super String, Boolean> rangeUnitPredicate) {
        Iterable $this$none$iv;
        boolean z;
        Intrinsics.checkNotNullParameter(rangeUnitPredicate, "rangeUnitPredicate");
        if (!rangeUnitPredicate.invoke(this.unit).booleanValue()) {
            return false;
        }
        Iterable $this$none$iv2 = this.ranges;
        if (!($this$none$iv2 instanceof Collection) || !((Collection) $this$none$iv2).isEmpty()) {
            Iterator it = $this$none$iv2.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    ContentRange it2 = (ContentRange) element$iv;
                    if (it2 instanceof ContentRange.Bounded) {
                        z = ((ContentRange.Bounded) it2).getFrom() < 0 || ((ContentRange.Bounded) it2).getTo() < ((ContentRange.Bounded) it2).getFrom();
                    } else if (it2 instanceof ContentRange.TailFrom) {
                        z = ((ContentRange.TailFrom) it2).getFrom() < 0;
                    } else {
                        if (!(it2 instanceof ContentRange.Suffix)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        z = ((ContentRange.Suffix) it2).getLastCount() < 0;
                    }
                    if (z) {
                        $this$none$iv = null;
                        break;
                    }
                } else {
                    $this$none$iv = 1;
                    break;
                }
            }
        } else {
            $this$none$iv = 1;
        }
        return $this$none$iv != null;
    }

    public static /* synthetic */ List merge$default(RangesSpecifier rangesSpecifier, long j, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 50;
        }
        return rangesSpecifier.merge(j, i);
    }

    public final List<LongRange> merge(long length, int maxRangeCount) {
        if (this.ranges.size() > maxRangeCount) {
            return toList(mergeToSingle(length));
        }
        return merge(length);
    }

    public final List<LongRange> merge(long length) {
        return RangesKt.mergeRangesKeepOrder(RangesKt.toLongRanges(this.ranges, length));
    }

    public final LongRange mergeToSingle(long length) {
        Object minElem$iv;
        List mapped = RangesKt.toLongRanges(this.ranges, length);
        Object maxElem$iv = null;
        if (mapped.isEmpty()) {
            return null;
        }
        List $this$minByOrNull$iv = mapped;
        Iterator iterator$iv = $this$minByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            minElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                LongRange it = (LongRange) minElem$iv;
                long minValue$iv = it.getStart().longValue();
                do {
                    Object e$iv = iterator$iv.next();
                    LongRange it2 = (LongRange) e$iv;
                    long v$iv = it2.getStart().longValue();
                    if (minValue$iv > v$iv) {
                        minElem$iv = e$iv;
                        minValue$iv = v$iv;
                    }
                } while (iterator$iv.hasNext());
            }
        } else {
            minElem$iv = null;
        }
        Intrinsics.checkNotNull(minElem$iv);
        long start = ((LongRange) minElem$iv).getStart().longValue();
        List $this$maxByOrNull$iv = mapped;
        Iterator iterator$iv2 = $this$maxByOrNull$iv.iterator();
        if (iterator$iv2.hasNext()) {
            maxElem$iv = iterator$iv2.next();
            if (iterator$iv2.hasNext()) {
                LongRange it3 = (LongRange) maxElem$iv;
                long maxValue$iv = it3.getEndInclusive().longValue();
                do {
                    Object e$iv2 = iterator$iv2.next();
                    LongRange it4 = (LongRange) e$iv2;
                    long v$iv2 = it4.getEndInclusive().longValue();
                    if (maxValue$iv < v$iv2) {
                        maxElem$iv = e$iv2;
                        maxValue$iv = v$iv2;
                    }
                } while (iterator$iv2.hasNext());
            }
        }
        Intrinsics.checkNotNull(maxElem$iv);
        long endInclusive = kotlin.ranges.RangesKt.coerceAtMost(((LongRange) maxElem$iv).getEndInclusive().longValue(), length - 1);
        return new LongRange(start, endInclusive);
    }

    public String toString() {
        return CollectionsKt.joinToString$default(this.ranges, ",", this.unit + '=', null, 0, null, null, 60, null);
    }

    private final <T> List<T> toList(T t) {
        return t == null ? CollectionsKt.emptyList() : CollectionsKt.listOf(t);
    }
}
