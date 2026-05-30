package io.ktor.http;

import io.ktor.http.ContentRange;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: Ranges.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000\u001a \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\t\u001a\u00020\nH\u0000¨\u0006\u000b"}, d2 = {"parseRangesSpecifier", "Lio/ktor/http/RangesSpecifier;", "rangeSpec", "", "mergeRangesKeepOrder", "", "Lkotlin/ranges/LongRange;", "toLongRanges", "Lio/ktor/http/ContentRange;", "contentLength", "", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class RangesKt {
    public static final RangesSpecifier parseRangesSpecifier(String rangeSpec) {
        int $i$f$map;
        String str;
        Pair pair;
        String str2;
        ContentRange tailFrom;
        String str3 = "";
        String str4 = "this as java.lang.String).substring(startIndex)";
        Intrinsics.checkNotNullParameter(rangeSpec, "rangeSpec");
        Object obj = null;
        try {
            int idx$iv = StringsKt.indexOf$default((CharSequence) rangeSpec, "=", 0, false, 6, (Object) null);
            if (idx$iv == -1) {
                return null;
            }
            boolean z = false;
            String substring = rangeSpec.substring(0, idx$iv);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            String substring2 = rangeSpec.substring(idx$iv + 1);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
            Pair pair2 = TuplesKt.to(substring, substring2);
            String unit = (String) pair2.component1();
            String allRangesString = (String) pair2.component2();
            Iterable $this$map$iv = StringsKt.split$default((CharSequence) allRangesString, new char[]{AbstractJsonLexerKt.COMMA}, false, 0, 6, (Object) null);
            int $i$f$map2 = 0;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                String it = (String) item$iv$iv;
                if (StringsKt.startsWith$default(it, "-", z, 2, obj)) {
                    $i$f$map = $i$f$map2;
                    tailFrom = new ContentRange.Suffix(Long.parseLong(StringsKt.removePrefix(it, (CharSequence) "-")));
                    str = str3;
                    str2 = str4;
                } else {
                    $i$f$map = $i$f$map2;
                    int idx$iv2 = StringsKt.indexOf$default((CharSequence) it, "-", 0, false, 6, (Object) null);
                    if (idx$iv2 == -1) {
                        str = str3;
                        pair = TuplesKt.to(str3, str3);
                    } else {
                        str = str3;
                        String substring3 = it.substring(0, idx$iv2);
                        Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
                        String substring4 = it.substring(idx$iv2 + 1);
                        Intrinsics.checkNotNullExpressionValue(substring4, str4);
                        pair = TuplesKt.to(substring3, substring4);
                    }
                    String from = (String) pair.component1();
                    String to = (String) pair.component2();
                    if (to.length() > 0) {
                        str2 = str4;
                        tailFrom = new ContentRange.Bounded(Long.parseLong(from), Long.parseLong(to));
                    } else {
                        str2 = str4;
                        tailFrom = new ContentRange.TailFrom(Long.parseLong(from));
                    }
                }
                destination$iv$iv.add(tailFrom);
                str3 = str;
                $i$f$map2 = $i$f$map;
                str4 = str2;
                obj = null;
                z = false;
            }
            List allRanges = (List) destination$iv$iv;
            if (allRanges.isEmpty()) {
                return null;
            }
            if (unit.length() == 0) {
                return null;
            }
            RangesSpecifier spec = new RangesSpecifier(unit, (List<? extends ContentRange>) allRanges);
            if (RangesSpecifier.isValid$default(spec, null, 1, null)) {
                return spec;
            }
            return null;
        } catch (Throwable th) {
            return null;
        }
    }

    public static final List<LongRange> toLongRanges(List<? extends ContentRange> list, long contentLength) {
        Iterable $this$map$iv;
        int $i$f$map;
        Iterable $this$mapTo$iv$iv;
        int $i$f$mapTo;
        LongRange until;
        Intrinsics.checkNotNullParameter(list, "<this>");
        List<? extends ContentRange> $this$map$iv2 = list;
        int $i$f$map2 = 0;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        Iterable $this$mapTo$iv$iv2 = $this$map$iv2;
        int $i$f$mapTo2 = 0;
        for (Object item$iv$iv : $this$mapTo$iv$iv2) {
            ContentRange it = (ContentRange) item$iv$iv;
            if (it instanceof ContentRange.Bounded) {
                $this$map$iv = $this$map$iv2;
                $i$f$map = $i$f$map2;
                $this$mapTo$iv$iv = $this$mapTo$iv$iv2;
                $i$f$mapTo = $i$f$mapTo2;
                until = new LongRange(((ContentRange.Bounded) it).getFrom(), kotlin.ranges.RangesKt.coerceAtMost(((ContentRange.Bounded) it).getTo(), contentLength - 1));
            } else {
                $this$map$iv = $this$map$iv2;
                $i$f$map = $i$f$map2;
                $this$mapTo$iv$iv = $this$mapTo$iv$iv2;
                $i$f$mapTo = $i$f$mapTo2;
                if (it instanceof ContentRange.TailFrom) {
                    until = kotlin.ranges.RangesKt.until(((ContentRange.TailFrom) it).getFrom(), contentLength);
                } else {
                    if (!(it instanceof ContentRange.Suffix)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    until = kotlin.ranges.RangesKt.until(kotlin.ranges.RangesKt.coerceAtLeast(contentLength - ((ContentRange.Suffix) it).getLastCount(), 0L), contentLength);
                }
            }
            destination$iv$iv.add(until);
            $this$mapTo$iv$iv2 = $this$mapTo$iv$iv;
            $this$map$iv2 = $this$map$iv;
            $i$f$map2 = $i$f$map;
            $i$f$mapTo2 = $i$f$mapTo;
        }
        Iterable $this$filterNot$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv : $this$filterNot$iv) {
            if (!((LongRange) element$iv$iv).isEmpty()) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv2;
    }

    public static final List<LongRange> mergeRangesKeepOrder(List<LongRange> list) {
        Iterable $this$fold$iv;
        ArrayList arrayList;
        int $i$f$fold;
        Intrinsics.checkNotNullParameter(list, "<this>");
        List<LongRange> $this$sortedBy$iv = list;
        Iterable $this$fold$iv2 = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: io.ktor.http.RangesKt$mergeRangesKeepOrder$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                LongRange it = (LongRange) t;
                LongRange it2 = (LongRange) t2;
                return ComparisonsKt.compareValues(it.getStart(), it2.getStart());
            }
        });
        ArrayList arrayList2 = new ArrayList(list.size());
        int $i$f$fold2 = 0;
        ArrayList arrayList3 = arrayList2;
        for (Object element$iv : $this$fold$iv2) {
            LongRange range = (LongRange) element$iv;
            ArrayList acc = arrayList3;
            if (acc.isEmpty()) {
                acc.add(range);
                $this$fold$iv = $this$fold$iv2;
                arrayList = arrayList2;
                $i$f$fold = $i$f$fold2;
            } else if (((LongRange) CollectionsKt.last((List) acc)).getEndInclusive().longValue() < range.getStart().longValue() - 1) {
                acc.add(range);
                $this$fold$iv = $this$fold$iv2;
                arrayList = arrayList2;
                $i$f$fold = $i$f$fold2;
            } else {
                LongRange last = (LongRange) CollectionsKt.last((List) acc);
                $this$fold$iv = $this$fold$iv2;
                arrayList = arrayList2;
                $i$f$fold = $i$f$fold2;
                acc.set(CollectionsKt.getLastIndex(acc), new LongRange(last.getStart().longValue(), Math.max(last.getEndInclusive().longValue(), range.getEndInclusive().longValue())));
            }
            arrayList3 = acc;
            $this$fold$iv2 = $this$fold$iv;
            arrayList2 = arrayList;
            $i$f$fold2 = $i$f$fold;
        }
        ArrayList sortedMerged = arrayList3;
        LongRange[] result = new LongRange[list.size()];
        Iterator it = sortedMerged.iterator();
        while (it.hasNext()) {
            LongRange range2 = (LongRange) it.next();
            int i = 0;
            int size = list.size();
            while (true) {
                if (i < size) {
                    Intrinsics.checkNotNullExpressionValue(range2, "range");
                    if (!io.ktor.util.RangesKt.contains(range2, list.get(i))) {
                        i++;
                    } else {
                        result[i] = range2;
                        break;
                    }
                }
            }
        }
        return ArraysKt.filterNotNull(result);
    }
}
