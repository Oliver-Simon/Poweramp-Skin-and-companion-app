package io.ktor.util;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: Text.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0002\u001a\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0000\u001a>\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\b*\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0018\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\b0\u000bH\u0086\bø\u0001\u0000\u001a\n\u0010\f\u001a\u00020\u0006*\u00020\u0006\u001a\n\u0010\r\u001a\u00020\u0006*\u00020\u0006\u001a\n\u0010\u000e\u001a\u00020\u0006*\u00020\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000f"}, d2 = {"toLowerCasePreservingASCII", "", "ch", "toUpperCasePreservingASCII", "caseInsensitive", "Lio/ktor/util/CaseInsensitiveString;", "", "chomp", "Lkotlin/Pair;", "separator", "onMissingDelimiter", "Lkotlin/Function0;", "escapeHTML", "toLowerCasePreservingASCIIRules", "toUpperCasePreservingASCIIRules", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class TextKt {
    public static final String escapeHTML(String $this$escapeHTML) {
        Intrinsics.checkNotNullParameter($this$escapeHTML, "<this>");
        if ($this$escapeHTML.length() == 0) {
            return $this$escapeHTML;
        }
        StringBuilder $this$escapeHTML_u24lambda_u240 = new StringBuilder($this$escapeHTML.length());
        int length = $this$escapeHTML.length();
        for (int idx = 0; idx < length; idx++) {
            char ch = $this$escapeHTML.charAt(idx);
            if (ch == '\'') {
                $this$escapeHTML_u24lambda_u240.append("&#x27;");
            } else if (ch == '\"') {
                $this$escapeHTML_u24lambda_u240.append("&quot;");
            } else if (ch == '&') {
                $this$escapeHTML_u24lambda_u240.append("&amp;");
            } else if (ch == '<') {
                $this$escapeHTML_u24lambda_u240.append("&lt;");
            } else if (ch == '>') {
                $this$escapeHTML_u24lambda_u240.append("&gt;");
            } else {
                $this$escapeHTML_u24lambda_u240.append(ch);
            }
        }
        String sb = $this$escapeHTML_u24lambda_u240.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder(capacity).…builderAction).toString()");
        return sb;
    }

    public static final Pair<String, String> chomp(String $this$chomp, String separator, Function0<Pair<String, String>> onMissingDelimiter) {
        Intrinsics.checkNotNullParameter($this$chomp, "<this>");
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(onMissingDelimiter, "onMissingDelimiter");
        int idx = StringsKt.indexOf$default((CharSequence) $this$chomp, separator, 0, false, 6, (Object) null);
        if (idx == -1) {
            return onMissingDelimiter.invoke();
        }
        String substring = $this$chomp.substring(0, idx);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        String substring2 = $this$chomp.substring(idx + 1);
        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
        return TuplesKt.to(substring, substring2);
    }

    public static final String toLowerCasePreservingASCIIRules(String $this$toLowerCasePreservingASCIIRules) {
        Intrinsics.checkNotNullParameter($this$toLowerCasePreservingASCIIRules, "<this>");
        String $this$indexOfFirst$iv = $this$toLowerCasePreservingASCIIRules;
        int index$iv = 0;
        int length = $this$indexOfFirst$iv.length();
        while (true) {
            if (index$iv < length) {
                char it = $this$indexOfFirst$iv.charAt(index$iv);
                if (toLowerCasePreservingASCII(it) != it) {
                    break;
                }
                index$iv++;
            } else {
                index$iv = -1;
                break;
            }
        }
        if (index$iv == -1) {
            return $this$toLowerCasePreservingASCIIRules;
        }
        StringBuilder $this$toLowerCasePreservingASCIIRules_u24lambda_u242 = new StringBuilder($this$toLowerCasePreservingASCIIRules.length());
        $this$toLowerCasePreservingASCIIRules_u24lambda_u242.append((CharSequence) $this$toLowerCasePreservingASCIIRules, 0, index$iv);
        int index = index$iv;
        int lastIndex = StringsKt.getLastIndex($this$toLowerCasePreservingASCIIRules);
        if (index <= lastIndex) {
            while (true) {
                $this$toLowerCasePreservingASCIIRules_u24lambda_u242.append(toLowerCasePreservingASCII($this$toLowerCasePreservingASCIIRules.charAt(index)));
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        String sb = $this$toLowerCasePreservingASCIIRules_u24lambda_u242.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder(capacity).…builderAction).toString()");
        return sb;
    }

    public static final String toUpperCasePreservingASCIIRules(String $this$toUpperCasePreservingASCIIRules) {
        Intrinsics.checkNotNullParameter($this$toUpperCasePreservingASCIIRules, "<this>");
        String $this$indexOfFirst$iv = $this$toUpperCasePreservingASCIIRules;
        int index$iv = 0;
        int length = $this$indexOfFirst$iv.length();
        while (true) {
            if (index$iv < length) {
                char it = $this$indexOfFirst$iv.charAt(index$iv);
                if (toUpperCasePreservingASCII(it) != it) {
                    break;
                }
                index$iv++;
            } else {
                index$iv = -1;
                break;
            }
        }
        if (index$iv == -1) {
            return $this$toUpperCasePreservingASCIIRules;
        }
        StringBuilder $this$toUpperCasePreservingASCIIRules_u24lambda_u244 = new StringBuilder($this$toUpperCasePreservingASCIIRules.length());
        $this$toUpperCasePreservingASCIIRules_u24lambda_u244.append((CharSequence) $this$toUpperCasePreservingASCIIRules, 0, index$iv);
        int index = index$iv;
        int lastIndex = StringsKt.getLastIndex($this$toUpperCasePreservingASCIIRules);
        if (index <= lastIndex) {
            while (true) {
                $this$toUpperCasePreservingASCIIRules_u24lambda_u244.append(toUpperCasePreservingASCII($this$toUpperCasePreservingASCIIRules.charAt(index)));
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        String sb = $this$toUpperCasePreservingASCIIRules_u24lambda_u244.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder(capacity).…builderAction).toString()");
        return sb;
    }

    private static final char toLowerCasePreservingASCII(char ch) {
        if ('A' <= ch && ch < '[') {
            return (char) (ch + ' ');
        }
        return ch >= 0 && ch < 128 ? ch : Character.toLowerCase(ch);
    }

    private static final char toUpperCasePreservingASCII(char ch) {
        if ('a' <= ch && ch < '{') {
            return (char) (ch - ' ');
        }
        return ch >= 0 && ch < 128 ? ch : Character.toLowerCase(ch);
    }

    public static final CaseInsensitiveString caseInsensitive(String $this$caseInsensitive) {
        Intrinsics.checkNotNullParameter($this$caseInsensitive, "<this>");
        return new CaseInsensitiveString($this$caseInsensitive);
    }
}
