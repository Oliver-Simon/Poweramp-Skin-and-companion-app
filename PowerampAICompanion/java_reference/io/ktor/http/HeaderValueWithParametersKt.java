package io.ktor.http;

import io.ktor.util.StringValuesBuilder;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: HeaderValueWithParameters.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\"\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u001a\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u001a\n\u0010\n\u001a\u00020\u0007*\u00020\u0007\u001a\u0019\u0010\u000b\u001a\u00020\u0004*\u00020\u00072\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0082\b\u001a\f\u0010\u000f\u001a\u00020\u0010*\u00020\u0007H\u0002\u001a\f\u0010\u0011\u001a\u00020\u0010*\u00020\u0007H\u0002\u001a\n\u0010\u0012\u001a\u00020\u0007*\u00020\u0007\u001a\u0018\u0010\u0013\u001a\u00020\u0004*\u00020\u00072\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"HeaderFieldValueSeparators", "", "", "append", "", "Lio/ktor/util/StringValuesBuilder;", "name", "", "value", "Lio/ktor/http/HeaderValueWithParameters;", "escapeIfNeeded", "escapeIfNeededTo", "out", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "isQuoted", "", "needQuotes", "quote", "quoteTo", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HeaderValueWithParametersKt {
    private static final Set<Character> HeaderFieldValueSeparators = SetsKt.setOf((Object[]) new Character[]{'(', ')', Character.valueOf(Typography.less), Character.valueOf(Typography.greater), '@', Character.valueOf(AbstractJsonLexerKt.COMMA), ';', Character.valueOf(AbstractJsonLexerKt.COLON), Character.valueOf(AbstractJsonLexerKt.STRING_ESC), '\"', '/', Character.valueOf(AbstractJsonLexerKt.BEGIN_LIST), Character.valueOf(AbstractJsonLexerKt.END_LIST), '?', '=', Character.valueOf(AbstractJsonLexerKt.BEGIN_OBJ), Character.valueOf(AbstractJsonLexerKt.END_OBJ), ' ', '\t', '\n', '\r'});

    public static final void append(StringValuesBuilder $this$append, String name, HeaderValueWithParameters value) {
        Intrinsics.checkNotNullParameter($this$append, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        $this$append.append(name, value.toString());
    }

    public static final String escapeIfNeeded(String $this$escapeIfNeeded) {
        Intrinsics.checkNotNullParameter($this$escapeIfNeeded, "<this>");
        return needQuotes($this$escapeIfNeeded) ? quote($this$escapeIfNeeded) : $this$escapeIfNeeded;
    }

    private static final void escapeIfNeededTo(String $this$escapeIfNeededTo, StringBuilder out) {
        if (!needQuotes($this$escapeIfNeededTo)) {
            out.append($this$escapeIfNeededTo);
        } else {
            out.append(quote($this$escapeIfNeededTo));
        }
    }

    public static final boolean needQuotes(String $this$needQuotes) {
        if ($this$needQuotes.length() == 0) {
            return true;
        }
        if (isQuoted($this$needQuotes)) {
            return false;
        }
        int length = $this$needQuotes.length();
        for (int index = 0; index < length; index++) {
            if (HeaderFieldValueSeparators.contains(Character.valueOf($this$needQuotes.charAt(index)))) {
                return true;
            }
        }
        return false;
    }

    private static final boolean isQuoted(String $this$isQuoted) {
        if ($this$isQuoted.length() < 2 || StringsKt.first($this$isQuoted) != '\"' || StringsKt.last($this$isQuoted) != '\"') {
            return false;
        }
        int startIndex = 1;
        do {
            int index = StringsKt.indexOf$default((CharSequence) $this$isQuoted, '\"', startIndex, false, 4, (Object) null);
            if (index != StringsKt.getLastIndex($this$isQuoted)) {
                int slashesCount = 0;
                for (int slashIndex = index - 1; $this$isQuoted.charAt(slashIndex) == '\\'; slashIndex--) {
                    slashesCount++;
                }
                if (slashesCount % 2 == 0) {
                    return false;
                }
                startIndex = index + 1;
            } else {
                return true;
            }
        } while (startIndex < $this$isQuoted.length());
        return true;
    }

    public static final String quote(String $this$quote) {
        Intrinsics.checkNotNullParameter($this$quote, "<this>");
        StringBuilder $this$quote_u24lambda_u240 = new StringBuilder();
        quoteTo($this$quote, $this$quote_u24lambda_u240);
        String sb = $this$quote_u24lambda_u240.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    private static final void quoteTo(String $this$quoteTo, StringBuilder out) {
        out.append("\"");
        int length = $this$quoteTo.length();
        for (int i = 0; i < length; i++) {
            char ch = $this$quoteTo.charAt(i);
            if (ch == '\\') {
                out.append("\\\\");
            } else if (ch == '\n') {
                out.append("\\n");
            } else if (ch == '\r') {
                out.append("\\r");
            } else if (ch == '\t') {
                out.append("\\t");
            } else if (ch == '\"') {
                out.append("\\\"");
            } else {
                out.append(ch);
            }
        }
        out.append("\"");
    }
}
