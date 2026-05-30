package io.ktor.http;

import io.ktor.http.Parameters;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* compiled from: Query.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a,\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b\u001a \u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0002\u001a \u0010\u000e\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\rH\u0002\u001a4\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a,\u0010\u0015\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\u0016"}, d2 = {"parseQueryString", "Lio/ktor/http/Parameters;", "query", "", "startIndex", "", "limit", "decode", "", "trimEnd", "start", "end", "text", "", "trimStart", "appendParam", "", "Lio/ktor/http/ParametersBuilder;", "nameIndex", "equalIndex", "endIndex", "parse", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class QueryKt {
    public static /* synthetic */ Parameters parseQueryString$default(String str, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = 1000;
        }
        if ((i3 & 8) != 0) {
            z = true;
        }
        return parseQueryString(str, i, i2, z);
    }

    public static final Parameters parseQueryString(String query, int startIndex, int limit, boolean decode) {
        Intrinsics.checkNotNullParameter(query, "query");
        if (startIndex > StringsKt.getLastIndex(query)) {
            return Parameters.INSTANCE.getEmpty();
        }
        Parameters.Companion companion = Parameters.INSTANCE;
        ParametersBuilder $this$parseQueryString_u24lambda_u240 = ParametersKt.ParametersBuilder$default(0, 1, null);
        parse($this$parseQueryString_u24lambda_u240, query, startIndex, limit, decode);
        return $this$parseQueryString_u24lambda_u240.build();
    }

    private static final void parse(ParametersBuilder $this$parse, String query, int startIndex, int limit, boolean decode) {
        int nameIndex = startIndex;
        int equalIndex = -1;
        int index = startIndex;
        int lastIndex = StringsKt.getLastIndex(query);
        int count = 0;
        if (index <= lastIndex) {
            while (count != limit) {
                char charAt = query.charAt(index);
                if (charAt == '&') {
                    appendParam($this$parse, query, nameIndex, equalIndex, index, decode);
                    count++;
                    nameIndex = index + 1;
                    equalIndex = -1;
                } else if (charAt == '=' && equalIndex == -1) {
                    equalIndex = index;
                }
                if (index != lastIndex) {
                    index++;
                }
            }
            return;
        }
        if (count == limit) {
            return;
        }
        appendParam($this$parse, query, nameIndex, equalIndex, query.length(), decode);
    }

    private static final void appendParam(ParametersBuilder $this$appendParam, String query, int nameIndex, int equalIndex, int endIndex, boolean decode) {
        String substring;
        String value;
        String name;
        if (equalIndex == -1) {
            int spaceNameIndex = trimStart(nameIndex, endIndex, query);
            int spaceEndIndex = trimEnd(spaceNameIndex, endIndex, query);
            if (spaceEndIndex > spaceNameIndex) {
                if (decode) {
                    name = CodecsKt.decodeURLQueryComponent$default(query, spaceNameIndex, spaceEndIndex, false, null, 12, null);
                } else {
                    name = query.substring(spaceNameIndex, spaceEndIndex);
                    Intrinsics.checkNotNullExpressionValue(name, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                $this$appendParam.appendAll(name, CollectionsKt.emptyList());
                return;
            }
            return;
        }
        int spaceNameIndex2 = trimStart(nameIndex, equalIndex, query);
        int spaceEqualIndex = trimEnd(spaceNameIndex2, equalIndex, query);
        if (spaceEqualIndex > spaceNameIndex2) {
            if (decode) {
                substring = CodecsKt.decodeURLQueryComponent$default(query, spaceNameIndex2, spaceEqualIndex, false, null, 12, null);
            } else {
                substring = query.substring(spaceNameIndex2, spaceEqualIndex);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            String name2 = substring;
            int spaceValueIndex = trimStart(equalIndex + 1, endIndex, query);
            int spaceEndIndex2 = trimEnd(spaceValueIndex, endIndex, query);
            if (decode) {
                value = CodecsKt.decodeURLQueryComponent$default(query, spaceValueIndex, spaceEndIndex2, true, null, 8, null);
            } else {
                value = query.substring(spaceValueIndex, spaceEndIndex2);
                Intrinsics.checkNotNullExpressionValue(value, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            $this$appendParam.append(name2, value);
        }
    }

    private static final int trimEnd(int start, int end, CharSequence text) {
        int spaceIndex = end;
        while (spaceIndex > start && CharsKt.isWhitespace(text.charAt(spaceIndex - 1))) {
            spaceIndex--;
        }
        return spaceIndex;
    }

    private static final int trimStart(int start, int end, CharSequence query) {
        int spaceIndex = start;
        while (spaceIndex < end && CharsKt.isWhitespace(query.charAt(spaceIndex))) {
            spaceIndex++;
        }
        return spaceIndex;
    }
}
