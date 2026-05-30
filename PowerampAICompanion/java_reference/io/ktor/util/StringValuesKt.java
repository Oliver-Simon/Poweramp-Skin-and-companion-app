package io.ktor.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StringValues.kt */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u001aH\u0010\u0000\u001a\u00020\u00012\u001e\u0010\u0002\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00040\u00032\u001e\u0010\u0007\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00040\u0003H\u0002\u001a0\u0010\b\u001a\u00020\t2\u001e\u0010\n\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00040\u00032\u0006\u0010\u000b\u001a\u00020\tH\u0002\u001a\u0006\u0010\f\u001a\u00020\r\u001aM\u0010\f\u001a\u00020\r26\u0010\u000e\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00100\u000f\"\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0001¢\u0006\u0002\u0010\u0012\u001a \u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0001\u001a&\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00052\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u0001\u001a*\u0010\f\u001a\u00020\r2\u0018\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00180\u00172\b\b\u0002\u0010\u0011\u001a\u00020\u0001\u001a\u0012\u0010\u0019\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a\u001a6\u0010\u001c\u001a\u00020\u001d*\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\u00012\u0018\u0010 \u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010!\u001a\u001a\u0010\"\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005\u001a\u001a\u0010#\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005\u001a.\u0010$\u001a\u00020\r*\u00020\r2\b\b\u0002\u0010\u001f\u001a\u00020\u00012\u0018\u0010 \u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010!\u001a\u001c\u0010%\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00100\u0006*\u00020\r\u001a$\u0010&\u001a\u00020\u001d*\u00020\r2\u0018\u0010'\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001d0!\u001a\u001c\u0010(\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u0017*\u00020\r¨\u0006)"}, d2 = {"entriesEquals", "", "a", "", "", "", "", "b", "entriesHashCode", "", "entries", "seed", "valuesOf", "Lio/ktor/util/StringValues;", "pairs", "", "Lkotlin/Pair;", "caseInsensitiveKey", "([Lkotlin/Pair;Z)Lio/ktor/util/StringValues;", "name", "value", "values", "map", "", "", "appendAll", "Lio/ktor/util/StringValuesBuilder;", "builder", "appendFiltered", "", "source", "keepEmpty", "predicate", "Lkotlin/Function2;", "appendIfNameAbsent", "appendIfNameAndValueAbsent", "filter", "flattenEntries", "flattenForEach", "block", "toMap", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class StringValuesKt {
    public static /* synthetic */ StringValues valuesOf$default(Pair[] pairArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return valuesOf((Pair<String, ? extends List<String>>[]) pairArr, z);
    }

    public static final StringValues valuesOf(Pair<String, ? extends List<String>>[] pairs, boolean caseInsensitiveKey) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        return new StringValues(caseInsensitiveKey, MapsKt.toMap(ArraysKt.asList(pairs)));
    }

    public static /* synthetic */ StringValues valuesOf$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return valuesOf(str, str2, z);
    }

    public static final StringValues valuesOf(String name, String value, boolean caseInsensitiveKey) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        return new StringValues(caseInsensitiveKey, name, kotlin.collections.CollectionsKt.listOf(value));
    }

    public static /* synthetic */ StringValues valuesOf$default(String str, List list, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return valuesOf(str, (List<String>) list, z);
    }

    public static final StringValues valuesOf(String name, List<String> values, boolean caseInsensitiveKey) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(values, "values");
        return new StringValues(caseInsensitiveKey, name, values);
    }

    public static final StringValues valuesOf() {
        return StringValues.INSTANCE.getEmpty();
    }

    public static /* synthetic */ StringValues valuesOf$default(Map map, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return valuesOf((Map<String, ? extends Iterable<String>>) map, z);
    }

    public static final StringValues valuesOf(Map<String, ? extends Iterable<String>> map, boolean caseInsensitiveKey) {
        Intrinsics.checkNotNullParameter(map, "map");
        int size = map.size();
        if (size == 1) {
            Map.Entry entry = (Map.Entry) kotlin.collections.CollectionsKt.single(map.entrySet());
            return new StringValues(caseInsensitiveKey, (String) entry.getKey(), kotlin.collections.CollectionsKt.toList((Iterable) entry.getValue()));
        }
        LinkedHashMap values = caseInsensitiveKey ? CollectionsKt.caseInsensitiveMap() : new LinkedHashMap(size);
        Iterable $this$forEach$iv = map.entrySet();
        for (Object element$iv : $this$forEach$iv) {
            Map.Entry it = (Map.Entry) element$iv;
            values.put(it.getKey(), kotlin.collections.CollectionsKt.toList((Iterable) it.getValue()));
        }
        return new StringValues(caseInsensitiveKey, values);
    }

    public static final Map<String, List<String>> toMap(StringValues $this$toMap) {
        Intrinsics.checkNotNullParameter($this$toMap, "<this>");
        Iterable $this$associateByTo$iv = $this$toMap.entries();
        Map destination$iv = new LinkedHashMap();
        for (Object element$iv : $this$associateByTo$iv) {
            Map.Entry it = (Map.Entry) element$iv;
            String str = (String) it.getKey();
            Map.Entry it2 = (Map.Entry) element$iv;
            destination$iv.put(str, kotlin.collections.CollectionsKt.toList((Iterable) it2.getValue()));
        }
        return destination$iv;
    }

    public static final List<Pair<String, String>> flattenEntries(StringValues $this$flattenEntries) {
        Intrinsics.checkNotNullParameter($this$flattenEntries, "<this>");
        Iterable $this$flatMap$iv = $this$flattenEntries.entries();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            Map.Entry e = (Map.Entry) element$iv$iv;
            Iterable $this$map$iv = (Iterable) e.getValue();
            Collection destination$iv$iv2 = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Iterable $this$flatMap$iv2 = $this$flatMap$iv;
                String it = (String) item$iv$iv;
                destination$iv$iv2.add(TuplesKt.to(e.getKey(), it));
                $this$flatMap$iv = $this$flatMap$iv2;
            }
            Iterable $this$flatMap$iv3 = $this$flatMap$iv;
            Iterable list$iv$iv = (List) destination$iv$iv2;
            kotlin.collections.CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
            $this$flatMap$iv = $this$flatMap$iv3;
        }
        return (List) destination$iv$iv;
    }

    public static final void flattenForEach(StringValues $this$flattenForEach, final Function2<? super String, ? super String, Unit> block) {
        Intrinsics.checkNotNullParameter($this$flattenForEach, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        $this$flattenForEach.forEach(new Function2<String, List<? extends String>, Unit>() { // from class: io.ktor.util.StringValuesKt$flattenForEach$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, List<? extends String> list) {
                invoke2(str, (List<String>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name, List<String> items) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(items, "items");
                List<String> $this$forEach$iv = items;
                Function2<String, String, Unit> function2 = block;
                for (Object element$iv : $this$forEach$iv) {
                    String it = (String) element$iv;
                    function2.invoke(name, it);
                }
            }
        });
    }

    public static /* synthetic */ StringValues filter$default(StringValues stringValues, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return filter(stringValues, z, function2);
    }

    public static final StringValues filter(StringValues $this$filter, boolean keepEmpty, Function2<? super String, ? super String, Boolean> predicate) {
        Intrinsics.checkNotNullParameter($this$filter, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        Set entries = $this$filter.entries();
        LinkedHashMap values = $this$filter.getCaseInsensitiveName() ? CollectionsKt.caseInsensitiveMap() : new LinkedHashMap(entries.size());
        Set<Map.Entry> $this$forEach$iv = entries;
        for (Map.Entry entry : $this$forEach$iv) {
            Iterable $this$filterTo$iv = (Iterable) entry.getValue();
            Collection destination$iv = new ArrayList(((List) entry.getValue()).size());
            for (Object element$iv : $this$filterTo$iv) {
                String it = (String) element$iv;
                Set entries2 = entries;
                if (predicate.invoke(entry.getKey(), it).booleanValue()) {
                    destination$iv.add(element$iv);
                }
                entries = entries2;
            }
            Set entries3 = entries;
            ArrayList list = (ArrayList) destination$iv;
            if (keepEmpty || !list.isEmpty()) {
                values.put(entry.getKey(), list);
            }
            entries = entries3;
        }
        return new StringValues($this$filter.getCaseInsensitiveName(), values);
    }

    public static /* synthetic */ void appendFiltered$default(StringValuesBuilder stringValuesBuilder, StringValues stringValues, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        appendFiltered(stringValuesBuilder, stringValues, z, function2);
    }

    public static final void appendFiltered(final StringValuesBuilder $this$appendFiltered, StringValues source, final boolean keepEmpty, final Function2<? super String, ? super String, Boolean> predicate) {
        Intrinsics.checkNotNullParameter($this$appendFiltered, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        source.forEach(new Function2<String, List<? extends String>, Unit>() { // from class: io.ktor.util.StringValuesKt$appendFiltered$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, List<? extends String> list) {
                invoke2(str, (List<String>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name, List<String> value) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(value, "value");
                List<String> $this$filterTo$iv = value;
                Collection destination$iv = new ArrayList(value.size());
                Function2<String, String, Boolean> function2 = predicate;
                for (Object element$iv : $this$filterTo$iv) {
                    String it = (String) element$iv;
                    if (function2.invoke(name, it).booleanValue()) {
                        destination$iv.add(element$iv);
                    }
                }
                ArrayList list = (ArrayList) destination$iv;
                if (keepEmpty || !list.isEmpty()) {
                    $this$appendFiltered.appendAll(name, list);
                }
            }
        });
    }

    public static final StringValuesBuilder appendAll(StringValuesBuilder $this$appendAll, StringValuesBuilder builder) {
        Intrinsics.checkNotNullParameter($this$appendAll, "<this>");
        Intrinsics.checkNotNullParameter(builder, "builder");
        Iterable $this$forEach$iv = builder.entries();
        for (Object element$iv : $this$forEach$iv) {
            Map.Entry entry = (Map.Entry) element$iv;
            String name = (String) entry.getKey();
            List values = (List) entry.getValue();
            $this$appendAll.appendAll(name, values);
        }
        return $this$appendAll;
    }

    public static final StringValuesBuilder appendIfNameAbsent(StringValuesBuilder $this$appendIfNameAbsent, String name, String value) {
        Intrinsics.checkNotNullParameter($this$appendIfNameAbsent, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        if (!$this$appendIfNameAbsent.contains(name)) {
            $this$appendIfNameAbsent.append(name, value);
        }
        return $this$appendIfNameAbsent;
    }

    public static final StringValuesBuilder appendIfNameAndValueAbsent(StringValuesBuilder $this$appendIfNameAndValueAbsent, String name, String value) {
        Intrinsics.checkNotNullParameter($this$appendIfNameAndValueAbsent, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        if (!$this$appendIfNameAndValueAbsent.contains(name, value)) {
            $this$appendIfNameAndValueAbsent.append(name, value);
        }
        return $this$appendIfNameAndValueAbsent;
    }

    public static final boolean entriesEquals(Set<? extends Map.Entry<String, ? extends List<String>>> set, Set<? extends Map.Entry<String, ? extends List<String>>> set2) {
        return Intrinsics.areEqual(set, set2);
    }

    public static final int entriesHashCode(Set<? extends Map.Entry<String, ? extends List<String>>> set, int seed) {
        return (seed * 31) + set.hashCode();
    }
}
