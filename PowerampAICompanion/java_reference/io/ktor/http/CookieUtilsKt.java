package io.ktor.http;

import io.ktor.util.date.Month;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CookieUtils.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0007H\u0000\u001a\f\u0010\b\u001a\u00020\u0006*\u00020\u0007H\u0000\u001a\f\u0010\t\u001a\u00020\u0006*\u00020\u0007H\u0000\u001a\f\u0010\n\u001a\u00020\u0006*\u00020\u0007H\u0000\u001a\f\u0010\u000b\u001a\u00020\u0006*\u00020\u0007H\u0000\u001a\u001e\u0010\f\u001a\u00020\u0001*\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0080\bø\u0001\u0000\u001a$\u0010\u000f\u001a\u00020\u0001*\u00020\u00042\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0011H\u0080\bø\u0001\u0000\u001a$\u0010\u0013\u001a\u00020\u0001*\u00020\u00042\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0011H\u0080\bø\u0001\u0000\u001a0\u0010\u0015\u001a\u00020\u0001*\u00020\u00042\u001e\u0010\u0010\u001a\u001a\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0016H\u0080\bø\u0001\u0000\u001a$\u0010\u0017\u001a\u00020\u0001*\u00020\u00042\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0011H\u0080\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0018"}, d2 = {"handleToken", "", "Lio/ktor/http/CookieDateBuilder;", "token", "", "isDelimiter", "", "", "isDigit", "isNonDelimiter", "isNonDigit", "isOctet", "otherwise", "block", "Lkotlin/Function0;", "tryParseDayOfMonth", "success", "Lkotlin/Function1;", "", "tryParseMonth", "Lio/ktor/util/date/Month;", "tryParseTime", "Lkotlin/Function3;", "tryParseYear", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class CookieUtilsKt {
    public static final boolean isDelimiter(char $this$isDelimiter) {
        if ($this$isDelimiter == '\t') {
            return true;
        }
        if (' ' <= $this$isDelimiter && $this$isDelimiter < '0') {
            return true;
        }
        if (';' <= $this$isDelimiter && $this$isDelimiter < 'A') {
            return true;
        }
        if ('[' <= $this$isDelimiter && $this$isDelimiter < 'a') {
            return true;
        }
        return '{' <= $this$isDelimiter && $this$isDelimiter < 127;
    }

    public static final boolean isNonDelimiter(char $this$isNonDelimiter) {
        if ($this$isNonDelimiter >= 0 && $this$isNonDelimiter < '\t') {
            return true;
        }
        if ('\n' <= $this$isNonDelimiter && $this$isNonDelimiter < ' ') {
            return true;
        }
        if (('0' <= $this$isNonDelimiter && $this$isNonDelimiter < ':') || $this$isNonDelimiter == ':') {
            return true;
        }
        if ('a' <= $this$isNonDelimiter && $this$isNonDelimiter < '{') {
            return true;
        }
        if ('A' <= $this$isNonDelimiter && $this$isNonDelimiter < '[') {
            return true;
        }
        return 127 <= $this$isNonDelimiter && $this$isNonDelimiter < 256;
    }

    public static final boolean isOctet(char $this$isOctet) {
        return $this$isOctet >= 0 && $this$isOctet < 256;
    }

    public static final boolean isNonDigit(char $this$isNonDigit) {
        if ($this$isNonDigit >= 0 && $this$isNonDigit < '0') {
            return true;
        }
        return 'J' <= $this$isNonDigit && $this$isNonDigit < 256;
    }

    public static final boolean isDigit(char $this$isDigit) {
        return '0' <= $this$isDigit && $this$isDigit < ':';
    }

    public static final void otherwise(boolean $this$otherwise, Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (!$this$otherwise) {
            block.invoke();
        }
    }

    public static final void tryParseTime(String $this$tryParseTime, Function3<? super Integer, ? super Integer, ? super Integer, Unit> success) {
        Intrinsics.checkNotNullParameter($this$tryParseTime, "<this>");
        Intrinsics.checkNotNullParameter(success, "success");
        StringLexer lexer = new StringLexer($this$tryParseTime);
        int start$iv = lexer.getIndex();
        boolean $this$otherwise$iv = lexer.accept(CookieUtilsKt$tryParseTime$hour$1$1.INSTANCE);
        if (!$this$otherwise$iv) {
            return;
        }
        lexer.accept(CookieUtilsKt$tryParseTime$hour$1$3.INSTANCE);
        String substring = lexer.getSource().substring(start$iv, lexer.getIndex());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        int hour = Integer.parseInt(substring);
        boolean $this$otherwise$iv2 = lexer.accept(CookieUtilsKt$tryParseTime$1.INSTANCE);
        if (!$this$otherwise$iv2) {
            return;
        }
        int start$iv2 = lexer.getIndex();
        boolean $this$otherwise$iv3 = lexer.accept(CookieUtilsKt$tryParseTime$minute$1$1.INSTANCE);
        if (!$this$otherwise$iv3) {
            return;
        }
        lexer.accept(CookieUtilsKt$tryParseTime$minute$1$3.INSTANCE);
        String substring2 = lexer.getSource().substring(start$iv2, lexer.getIndex());
        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
        int minute = Integer.parseInt(substring2);
        boolean $this$otherwise$iv4 = lexer.accept(CookieUtilsKt$tryParseTime$3.INSTANCE);
        if (!$this$otherwise$iv4) {
            return;
        }
        int start$iv3 = lexer.getIndex();
        boolean $this$otherwise$iv5 = lexer.accept(CookieUtilsKt$tryParseTime$second$1$1.INSTANCE);
        if (!$this$otherwise$iv5) {
            return;
        }
        lexer.accept(CookieUtilsKt$tryParseTime$second$1$3.INSTANCE);
        String substring3 = lexer.getSource().substring(start$iv3, lexer.getIndex());
        Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
        int second = Integer.parseInt(substring3);
        if (lexer.accept(CookieUtilsKt$tryParseTime$5.INSTANCE)) {
            lexer.acceptWhile(CookieUtilsKt$tryParseTime$6.INSTANCE);
        }
        success.invoke(Integer.valueOf(hour), Integer.valueOf(minute), Integer.valueOf(second));
    }

    public static final void tryParseMonth(String $this$tryParseMonth, Function1<? super Month, Unit> success) {
        Intrinsics.checkNotNullParameter($this$tryParseMonth, "<this>");
        Intrinsics.checkNotNullParameter(success, "success");
        if ($this$tryParseMonth.length() < 3) {
            return;
        }
        for (Month month : Month.values()) {
            if (StringsKt.startsWith($this$tryParseMonth, month.getValue(), true)) {
                success.invoke(month);
                return;
            }
        }
    }

    public static final void tryParseDayOfMonth(String $this$tryParseDayOfMonth, Function1<? super Integer, Unit> success) {
        Intrinsics.checkNotNullParameter($this$tryParseDayOfMonth, "<this>");
        Intrinsics.checkNotNullParameter(success, "success");
        StringLexer lexer = new StringLexer($this$tryParseDayOfMonth);
        int start$iv = lexer.getIndex();
        boolean $this$otherwise$iv = lexer.accept(CookieUtilsKt$tryParseDayOfMonth$day$1$1.INSTANCE);
        if (!$this$otherwise$iv) {
            return;
        }
        lexer.accept(CookieUtilsKt$tryParseDayOfMonth$day$1$3.INSTANCE);
        String substring = lexer.getSource().substring(start$iv, lexer.getIndex());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        int day = Integer.parseInt(substring);
        if (lexer.accept(CookieUtilsKt$tryParseDayOfMonth$1.INSTANCE)) {
            lexer.acceptWhile(CookieUtilsKt$tryParseDayOfMonth$2.INSTANCE);
        }
        success.invoke(Integer.valueOf(day));
    }

    public static final void tryParseYear(String $this$tryParseYear, Function1<? super Integer, Unit> success) {
        Intrinsics.checkNotNullParameter($this$tryParseYear, "<this>");
        Intrinsics.checkNotNullParameter(success, "success");
        StringLexer lexer = new StringLexer($this$tryParseYear);
        int start$iv = lexer.getIndex();
        for (int i = 0; i < 2; i++) {
            boolean $this$otherwise$iv = lexer.accept(CookieUtilsKt$tryParseYear$year$1$1$1.INSTANCE);
            if (!$this$otherwise$iv) {
                return;
            }
        }
        for (int i2 = 0; i2 < 2; i2++) {
            lexer.accept(CookieUtilsKt$tryParseYear$year$1$2$1.INSTANCE);
        }
        String substring = lexer.getSource().substring(start$iv, lexer.getIndex());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        int year = Integer.parseInt(substring);
        if (lexer.accept(CookieUtilsKt$tryParseYear$1.INSTANCE)) {
            lexer.acceptWhile(CookieUtilsKt$tryParseYear$2.INSTANCE);
        }
        success.invoke(Integer.valueOf(year));
    }

    public static final void handleToken(CookieDateBuilder $this$handleToken, String token) {
        Intrinsics.checkNotNullParameter($this$handleToken, "<this>");
        Intrinsics.checkNotNullParameter(token, "token");
        if ($this$handleToken.getHours() == null || $this$handleToken.getMinutes() == null || $this$handleToken.getSeconds() == null) {
            StringLexer lexer$iv = new StringLexer(token);
            int start$iv$iv = lexer$iv.getIndex();
            boolean $this$otherwise$iv$iv = lexer$iv.accept(CookieUtilsKt$tryParseTime$hour$1$1.INSTANCE);
            if ($this$otherwise$iv$iv) {
                lexer$iv.accept(CookieUtilsKt$tryParseTime$hour$1$3.INSTANCE);
                String substring = lexer$iv.getSource().substring(start$iv$iv, lexer$iv.getIndex());
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                int hour$iv = Integer.parseInt(substring);
                boolean $this$otherwise$iv$iv2 = lexer$iv.accept(CookieUtilsKt$tryParseTime$1.INSTANCE);
                if ($this$otherwise$iv$iv2) {
                    int start$iv$iv2 = lexer$iv.getIndex();
                    boolean $this$otherwise$iv$iv3 = lexer$iv.accept(CookieUtilsKt$tryParseTime$minute$1$1.INSTANCE);
                    if ($this$otherwise$iv$iv3) {
                        lexer$iv.accept(CookieUtilsKt$tryParseTime$minute$1$3.INSTANCE);
                        String substring2 = lexer$iv.getSource().substring(start$iv$iv2, lexer$iv.getIndex());
                        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                        int minute$iv = Integer.parseInt(substring2);
                        boolean $this$otherwise$iv$iv4 = lexer$iv.accept(CookieUtilsKt$tryParseTime$3.INSTANCE);
                        if ($this$otherwise$iv$iv4) {
                            int start$iv$iv3 = lexer$iv.getIndex();
                            boolean $this$otherwise$iv$iv5 = lexer$iv.accept(CookieUtilsKt$tryParseTime$second$1$1.INSTANCE);
                            if ($this$otherwise$iv$iv5) {
                                lexer$iv.accept(CookieUtilsKt$tryParseTime$second$1$3.INSTANCE);
                                String substring3 = lexer$iv.getSource().substring(start$iv$iv3, lexer$iv.getIndex());
                                Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
                                int second$iv = Integer.parseInt(substring3);
                                if (lexer$iv.accept(CookieUtilsKt$tryParseTime$5.INSTANCE)) {
                                    lexer$iv.acceptWhile(CookieUtilsKt$tryParseTime$6.INSTANCE);
                                }
                                $this$handleToken.setHours(Integer.valueOf(hour$iv));
                                $this$handleToken.setMinutes(Integer.valueOf(minute$iv));
                                $this$handleToken.setSeconds(Integer.valueOf(second$iv));
                                return;
                            }
                        }
                    }
                }
            }
        }
        if ($this$handleToken.getDayOfMonth() == null) {
            StringLexer lexer$iv2 = new StringLexer(token);
            int start$iv$iv4 = lexer$iv2.getIndex();
            boolean $this$otherwise$iv$iv6 = lexer$iv2.accept(CookieUtilsKt$tryParseDayOfMonth$day$1$1.INSTANCE);
            if ($this$otherwise$iv$iv6) {
                lexer$iv2.accept(CookieUtilsKt$tryParseDayOfMonth$day$1$3.INSTANCE);
                String substring4 = lexer$iv2.getSource().substring(start$iv$iv4, lexer$iv2.getIndex());
                Intrinsics.checkNotNullExpressionValue(substring4, "this as java.lang.String…ing(startIndex, endIndex)");
                int day$iv = Integer.parseInt(substring4);
                if (lexer$iv2.accept(CookieUtilsKt$tryParseDayOfMonth$1.INSTANCE)) {
                    lexer$iv2.acceptWhile(CookieUtilsKt$tryParseDayOfMonth$2.INSTANCE);
                }
                $this$handleToken.setDayOfMonth(Integer.valueOf(day$iv));
                return;
            }
        }
        if ($this$handleToken.getMonth() == null && token.length() >= 3) {
            for (Month month$iv : Month.values()) {
                if (StringsKt.startsWith(token, month$iv.getValue(), true)) {
                    $this$handleToken.setMonth(month$iv);
                    return;
                }
            }
        }
        if ($this$handleToken.getYear() == null) {
            StringLexer lexer$iv3 = new StringLexer(token);
            int start$iv$iv5 = lexer$iv3.getIndex();
            for (int i = 0; i < 2; i++) {
                boolean $this$otherwise$iv$iv7 = lexer$iv3.accept(CookieUtilsKt$tryParseYear$year$1$1$1.INSTANCE);
                if (!$this$otherwise$iv$iv7) {
                    return;
                }
            }
            for (int i2 = 0; i2 < 2; i2++) {
                lexer$iv3.accept(CookieUtilsKt$tryParseYear$year$1$2$1.INSTANCE);
            }
            String substring5 = lexer$iv3.getSource().substring(start$iv$iv5, lexer$iv3.getIndex());
            Intrinsics.checkNotNullExpressionValue(substring5, "this as java.lang.String…ing(startIndex, endIndex)");
            int year$iv = Integer.parseInt(substring5);
            if (lexer$iv3.accept(CookieUtilsKt$tryParseYear$1.INSTANCE)) {
                lexer$iv3.acceptWhile(CookieUtilsKt$tryParseYear$2.INSTANCE);
            }
            $this$handleToken.setYear(Integer.valueOf(year$iv));
        }
    }
}
