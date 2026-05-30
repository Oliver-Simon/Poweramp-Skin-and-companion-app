package io.ktor.http;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.slf4j.Marker;

/* compiled from: HttpHeaderValueParser.kt */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001c\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001a\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001a\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u001a\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\t\u001a>\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u001c\u0010\r\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00020\u000fj\b\u0012\u0004\u0012\u00020\u0002`\u00100\u000e2\u0006\u0010\b\u001a\u00020\tH\u0002\u001a6\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u001c\u0010\u0012\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00130\u000fj\b\u0012\u0004\u0012\u00020\u0013`\u00100\u000eH\u0002\u001a$\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\u00152\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0002\u001a$\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\u00152\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0002\u001a\u0014\u0010\u0018\u001a\u00020\t*\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0002\u001a\u001c\u0010\u0019\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u000bH\u0002\u001a\"\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00130\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00150\u001c\u001a$\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001e0\u0001\"\u0004\b\u0000\u0010\u001e*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001e0\u00010\u000eH\u0002¨\u0006\u001f"}, d2 = {"parseAndSortContentTypeHeader", "", "Lio/ktor/http/HeaderValue;", "header", "", "parseAndSortHeader", "parseHeaderValue", "text", "parametersOnly", "", "parseHeaderValueItem", "", "start", "items", "Lkotlin/Lazy;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "parseHeaderValueParameter", "parameters", "Lio/ktor/http/HeaderValueParam;", "parseHeaderValueParameterValue", "Lkotlin/Pair;", "value", "parseHeaderValueParameterValueQuoted", "nextIsSemicolonOrEnd", "subtrim", "end", "toHeaderParamsList", "", "valueOrEmpty", ExifInterface.GPS_DIRECTION_TRUE, "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpHeaderValueParserKt {
    public static final List<HeaderValue> parseAndSortHeader(String header) {
        Iterable $this$sortedByDescending$iv = parseHeaderValue(header);
        return CollectionsKt.sortedWith($this$sortedByDescending$iv, new Comparator() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseAndSortHeader$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                HeaderValue it = (HeaderValue) t2;
                HeaderValue it2 = (HeaderValue) t;
                return ComparisonsKt.compareValues(Double.valueOf(it.getQuality()), Double.valueOf(it2.getQuality()));
            }
        });
    }

    public static final List<HeaderValue> parseAndSortContentTypeHeader(String header) {
        List<HeaderValue> parseHeaderValue = parseHeaderValue(header);
        final Comparator comparator = new Comparator() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseAndSortContentTypeHeader$$inlined$compareByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                HeaderValue it = (HeaderValue) t2;
                Double valueOf = Double.valueOf(it.getQuality());
                HeaderValue it2 = (HeaderValue) t;
                return ComparisonsKt.compareValues(valueOf, Double.valueOf(it2.getQuality()));
            }
        };
        final Comparator comparator2 = new Comparator() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseAndSortContentTypeHeader$$inlined$thenBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int previousCompare = comparator.compare(t, t2);
                if (previousCompare != 0) {
                    return previousCompare;
                }
                HeaderValue it = (HeaderValue) t;
                ContentType contentType = ContentType.INSTANCE.parse(it.getValue());
                int asterisks = Intrinsics.areEqual(contentType.getContentType(), Marker.ANY_MARKER) ? 0 + 2 : 0;
                if (Intrinsics.areEqual(contentType.getContentSubtype(), Marker.ANY_MARKER)) {
                    asterisks++;
                }
                Integer valueOf = Integer.valueOf(asterisks);
                HeaderValue it2 = (HeaderValue) t2;
                ContentType contentType2 = ContentType.INSTANCE.parse(it2.getValue());
                int asterisks2 = Intrinsics.areEqual(contentType2.getContentType(), Marker.ANY_MARKER) ? 0 + 2 : 0;
                if (Intrinsics.areEqual(contentType2.getContentSubtype(), Marker.ANY_MARKER)) {
                    asterisks2++;
                }
                return ComparisonsKt.compareValues(valueOf, Integer.valueOf(asterisks2));
            }
        };
        return CollectionsKt.sortedWith(parseHeaderValue, new Comparator() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseAndSortContentTypeHeader$$inlined$thenByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int previousCompare = comparator2.compare(t, t2);
                if (previousCompare != 0) {
                    return previousCompare;
                }
                HeaderValue it = (HeaderValue) t2;
                Integer valueOf = Integer.valueOf(it.getParams().size());
                HeaderValue it2 = (HeaderValue) t;
                return ComparisonsKt.compareValues(valueOf, Integer.valueOf(it2.getParams().size()));
            }
        });
    }

    public static final List<HeaderValue> parseHeaderValue(String text) {
        return parseHeaderValue(text, false);
    }

    public static final List<HeaderValue> parseHeaderValue(String text, boolean parametersOnly) {
        if (text == null) {
            return CollectionsKt.emptyList();
        }
        int position = 0;
        Lazy items = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ArrayList<HeaderValue>>() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseHeaderValue$items$1
            @Override // kotlin.jvm.functions.Function0
            public final ArrayList<HeaderValue> invoke() {
                return new ArrayList<>();
            }
        });
        while (position <= StringsKt.getLastIndex(text)) {
            position = parseHeaderValueItem(text, position, items, parametersOnly);
        }
        return valueOrEmpty(items);
    }

    public static final List<HeaderValueParam> toHeaderParamsList(Iterable<Pair<String, String>> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Pair item$iv$iv : iterable) {
            Pair it = item$iv$iv;
            destination$iv$iv.add(new HeaderValueParam(it.getFirst(), it.getSecond()));
        }
        return (List) destination$iv$iv;
    }

    private static final <T> List<T> valueOrEmpty(Lazy<? extends List<? extends T>> lazy) {
        return lazy.isInitialized() ? lazy.getValue() : CollectionsKt.emptyList();
    }

    private static final String subtrim(String $this$subtrim, int start, int end) {
        String substring = $this$subtrim.substring(start, end);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return StringsKt.trim((CharSequence) substring).toString();
    }

    private static final int parseHeaderValueItem(String text, int start, Lazy<? extends ArrayList<HeaderValue>> lazy, boolean parametersOnly) {
        int i;
        int position = start;
        Lazy parameters = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ArrayList<HeaderValueParam>>() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseHeaderValueItem$parameters$1
            @Override // kotlin.jvm.functions.Function0
            public final ArrayList<HeaderValueParam> invoke() {
                return new ArrayList<>();
            }
        });
        Integer valueEnd = parametersOnly ? Integer.valueOf(position) : null;
        while (position <= StringsKt.getLastIndex(text)) {
            char charAt = text.charAt(position);
            if (charAt == ',') {
                lazy.getValue().add(new HeaderValue(subtrim(text, start, valueEnd != null ? valueEnd.intValue() : position), valueOrEmpty(parameters)));
                return position + 1;
            }
            if (charAt == ';') {
                if (valueEnd == null) {
                    valueEnd = Integer.valueOf(position);
                }
                position = parseHeaderValueParameter(text, position + 1, parameters);
            } else {
                if (parametersOnly) {
                    i = parseHeaderValueParameter(text, position, parameters);
                } else {
                    i = position + 1;
                }
                position = i;
            }
        }
        lazy.getValue().add(new HeaderValue(subtrim(text, start, valueEnd != null ? valueEnd.intValue() : position), valueOrEmpty(parameters)));
        return position;
    }

    private static final void parseHeaderValueParameter$addParam(Lazy<? extends ArrayList<HeaderValueParam>> lazy, String text, int start, int end, String value) {
        String name = subtrim(text, start, end);
        if (name.length() == 0) {
            return;
        }
        lazy.getValue().add(new HeaderValueParam(name, value));
    }

    private static final int parseHeaderValueParameter(String text, int start, Lazy<? extends ArrayList<HeaderValueParam>> lazy) {
        int position = start;
        while (position <= StringsKt.getLastIndex(text)) {
            char charAt = text.charAt(position);
            if (charAt == '=') {
                Pair<Integer, String> parseHeaderValueParameterValue = parseHeaderValueParameterValue(text, position + 1);
                int paramEnd = parseHeaderValueParameterValue.component1().intValue();
                String paramValue = parseHeaderValueParameterValue.component2();
                parseHeaderValueParameter$addParam(lazy, text, start, position, paramValue);
                return paramEnd;
            }
            boolean z = true;
            if (charAt != ';' && charAt != ',') {
                z = false;
            }
            if (z) {
                parseHeaderValueParameter$addParam(lazy, text, start, position, "");
                return position;
            }
            position++;
        }
        parseHeaderValueParameter$addParam(lazy, text, start, position, "");
        return position;
    }

    private static final Pair<Integer, String> parseHeaderValueParameterValue(String value, int start) {
        if (value.length() == start) {
            return TuplesKt.to(Integer.valueOf(start), "");
        }
        int position = start;
        if (value.charAt(start) == '\"') {
            return parseHeaderValueParameterValueQuoted(value, position + 1);
        }
        while (position <= StringsKt.getLastIndex(value)) {
            char charAt = value.charAt(position);
            boolean z = true;
            if (charAt != ';' && charAt != ',') {
                z = false;
            }
            if (z) {
                return TuplesKt.to(Integer.valueOf(position), subtrim(value, start, position));
            }
            position++;
        }
        return TuplesKt.to(Integer.valueOf(position), subtrim(value, start, position));
    }

    private static final Pair<Integer, String> parseHeaderValueParameterValueQuoted(String value, int start) {
        int position = start;
        StringBuilder builder = new StringBuilder();
        while (position <= StringsKt.getLastIndex(value)) {
            char currentChar = value.charAt(position);
            if (currentChar == '\"' && nextIsSemicolonOrEnd(value, position)) {
                Integer valueOf = Integer.valueOf(position + 1);
                String sb = builder.toString();
                Intrinsics.checkNotNullExpressionValue(sb, "builder.toString()");
                return TuplesKt.to(valueOf, sb);
            }
            if (currentChar == '\\' && position < StringsKt.getLastIndex(value) - 2) {
                builder.append(value.charAt(position + 1));
                position += 2;
            } else {
                builder.append(currentChar);
                position++;
            }
        }
        Integer valueOf2 = Integer.valueOf(position);
        String sb2 = builder.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "builder.toString()");
        return TuplesKt.to(valueOf2, '\"' + sb2);
    }

    private static final boolean nextIsSemicolonOrEnd(String $this$nextIsSemicolonOrEnd, int start) {
        int position = start + 1;
        while (position < $this$nextIsSemicolonOrEnd.length() && $this$nextIsSemicolonOrEnd.charAt(position) == ' ') {
            position++;
        }
        return position == $this$nextIsSemicolonOrEnd.length() || $this$nextIsSemicolonOrEnd.charAt(position) == ';';
    }
}
