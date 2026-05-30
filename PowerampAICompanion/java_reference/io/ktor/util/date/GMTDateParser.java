package io.ktor.util.date;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GMTDateParser.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003J\u001c\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lio/ktor/util/date/GMTDateParser;", "", "pattern", "", "(Ljava/lang/String;)V", "parse", "Lio/ktor/util/date/GMTDate;", "dateString", "handleToken", "", "Lio/ktor/util/date/GMTDateBuilder;", "type", "", "chunk", "Companion", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class GMTDateParser {
    public static final char ANY = '*';
    public static final char DAY_OF_MONTH = 'd';
    public static final char HOURS = 'h';
    public static final char MINUTES = 'm';
    public static final char MONTH = 'M';
    public static final char SECONDS = 's';
    public static final char YEAR = 'Y';
    public static final char ZONE = 'z';
    private final String pattern;

    public GMTDateParser(String pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        this.pattern = pattern;
        if (!(this.pattern.length() > 0)) {
            throw new IllegalStateException("Date parser pattern shouldn't be empty.".toString());
        }
    }

    public final GMTDate parse(String dateString) {
        Intrinsics.checkNotNullParameter(dateString, "dateString");
        GMTDateBuilder builder = new GMTDateBuilder();
        int start = 0;
        char current = this.pattern.charAt(0);
        int chunkStart = 0;
        int index = 1;
        while (index < this.pattern.length()) {
            try {
                if (this.pattern.charAt(index) == current) {
                    index++;
                } else {
                    int chunkEnd = (chunkStart + index) - start;
                    String substring = dateString.substring(chunkStart, chunkEnd);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    handleToken(builder, current, substring);
                    chunkStart = chunkEnd;
                    start = index;
                    current = this.pattern.charAt(index);
                    index++;
                }
            } catch (Throwable th) {
                throw new InvalidDateStringException(dateString, chunkStart, this.pattern);
            }
        }
        if (chunkStart < dateString.length()) {
            String substring2 = dateString.substring(chunkStart);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
            handleToken(builder, current, substring2);
        }
        return builder.build();
    }

    private final void handleToken(GMTDateBuilder $this$handleToken, char type, String chunk) {
        if (type == 's') {
            $this$handleToken.setSeconds(Integer.valueOf(Integer.parseInt(chunk)));
            return;
        }
        if (type == 'm') {
            $this$handleToken.setMinutes(Integer.valueOf(Integer.parseInt(chunk)));
            return;
        }
        if (type == 'h') {
            $this$handleToken.setHours(Integer.valueOf(Integer.parseInt(chunk)));
            return;
        }
        if (type == 'd') {
            $this$handleToken.setDayOfMonth(Integer.valueOf(Integer.parseInt(chunk)));
            return;
        }
        if (type == 'M') {
            $this$handleToken.setMonth(Month.INSTANCE.from(chunk));
            return;
        }
        if (type == 'Y') {
            $this$handleToken.setYear(Integer.valueOf(Integer.parseInt(chunk)));
            return;
        }
        if (type == 'z') {
            if (!Intrinsics.areEqual(chunk, "GMT")) {
                throw new IllegalStateException("Check failed.".toString());
            }
            return;
        }
        if (type != '*') {
            String $this$all$iv = chunk;
            boolean z = false;
            int i = 0;
            while (true) {
                if (i < $this$all$iv.length()) {
                    char element$iv = $this$all$iv.charAt(i);
                    if (!(element$iv == type)) {
                        break;
                    } else {
                        i++;
                    }
                } else {
                    z = true;
                    break;
                }
            }
            if (!z) {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
    }
}
