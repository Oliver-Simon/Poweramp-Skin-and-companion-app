package io.ktor.http;

import io.ktor.util.date.GMTDate;
import io.ktor.util.date.GMTDateParser;
import io.ktor.util.date.InvalidDateStringException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: DateUtils.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0004*\u00020\u0002\u001a\u0014\u0010\u0006\u001a\u00020\u0002*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002\u001a\n\u0010\t\u001a\u00020\u0002*\u00020\u0004\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"HTTP_DATE_FORMATS", "", "", "fromCookieToGmtDate", "Lio/ktor/util/date/GMTDate;", "fromHttpToGmtDate", "padZero", "", "length", "toHttpDate", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class DateUtilsKt {
    private static final List<String> HTTP_DATE_FORMATS = CollectionsKt.listOf((Object[]) new String[]{"***, dd MMM YYYY hh:mm:ss zzz", "****, dd-MMM-YYYY hh:mm:ss zzz", "*** MMM d hh:mm:ss YYYY", "***, dd-MMM-YYYY hh:mm:ss zzz", "***, dd-MMM-YYYY hh-mm-ss zzz", "***, dd MMM YYYY hh:mm:ss zzz", "*** dd-MMM-YYYY hh:mm:ss zzz", "*** dd MMM YYYY hh:mm:ss zzz", "*** dd-MMM-YYYY hh-mm-ss zzz", "***,dd-MMM-YYYY hh:mm:ss zzz", "*** MMM d YYYY hh:mm:ss zzz"});

    public static final GMTDate fromHttpToGmtDate(String $this$fromHttpToGmtDate) {
        Intrinsics.checkNotNullParameter($this$fromHttpToGmtDate, "<this>");
        String $this$fromHttpToGmtDate_u24lambda_u240 = StringsKt.trim((CharSequence) $this$fromHttpToGmtDate).toString();
        Iterator<String> it = HTTP_DATE_FORMATS.iterator();
        while (it.hasNext()) {
            String format = it.next();
            try {
                GMTDateParser parser = new GMTDateParser(format);
                return parser.parse($this$fromHttpToGmtDate);
            } catch (InvalidDateStringException e) {
            }
        }
        throw new IllegalStateException(("Failed to parse date: " + $this$fromHttpToGmtDate_u24lambda_u240).toString());
    }

    public static final GMTDate fromCookieToGmtDate(String $this$fromCookieToGmtDate) {
        Intrinsics.checkNotNullParameter($this$fromCookieToGmtDate, "<this>");
        String $this$fromCookieToGmtDate_u24lambda_u241 = StringsKt.trim((CharSequence) $this$fromCookieToGmtDate).toString();
        try {
            CookieDateParser parser = new CookieDateParser();
            return parser.parse($this$fromCookieToGmtDate_u24lambda_u241);
        } catch (InvalidCookieDateException e) {
            return fromHttpToGmtDate($this$fromCookieToGmtDate_u24lambda_u241);
        }
    }

    public static final String toHttpDate(GMTDate $this$toHttpDate) {
        Intrinsics.checkNotNullParameter($this$toHttpDate, "<this>");
        StringBuilder $this$toHttpDate_u24lambda_u242 = new StringBuilder();
        $this$toHttpDate_u24lambda_u242.append($this$toHttpDate.getDayOfWeek().getValue() + ", ");
        $this$toHttpDate_u24lambda_u242.append(padZero($this$toHttpDate.getDayOfMonth(), 2) + ' ');
        $this$toHttpDate_u24lambda_u242.append($this$toHttpDate.getMonth().getValue() + ' ');
        $this$toHttpDate_u24lambda_u242.append(padZero($this$toHttpDate.getYear(), 4));
        $this$toHttpDate_u24lambda_u242.append(' ' + padZero($this$toHttpDate.getHours(), 2) + AbstractJsonLexerKt.COLON + padZero($this$toHttpDate.getMinutes(), 2) + AbstractJsonLexerKt.COLON + padZero($this$toHttpDate.getSeconds(), 2) + ' ');
        $this$toHttpDate_u24lambda_u242.append("GMT");
        String sb = $this$toHttpDate_u24lambda_u242.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    private static final String padZero(int $this$padZero, int length) {
        return StringsKt.padStart(String.valueOf($this$padZero), length, '0');
    }
}
