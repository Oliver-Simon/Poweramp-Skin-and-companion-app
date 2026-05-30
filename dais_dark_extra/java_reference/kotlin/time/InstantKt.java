package kotlin.time;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;

/* compiled from: Instant.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\f\n\u0002\u0010\u0015\n\u0002\b\u0006\u001a\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0003\u001a\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0002H\u0003\u001a'\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0082\b\u001a'\u0010\u001c\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0082\b\u001a\u0010\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u0014H\u0000\u001a\u0014\u0010&\u001a\u00020\u0014*\u00020\u00142\u0006\u0010$\u001a\u00020\u0001H\u0002\u001a\u0014\u0010,\u001a\u00020\u0011*\u00020\u000f2\u0006\u0010-\u001a\u00020\u0014H\u0002\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0000\u0010\u0005\"\u001f\u0010\u0006\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\u0004\u001a\u0004\b\u0006\u0010\u0005\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010 \u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010!\u001a\u00020\u0014X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\"\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010#\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010)\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010*\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010+\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"isDistantPast", "", "Lkotlin/time/Instant;", "isDistantPast$annotations", "(Lkotlin/time/Instant;)V", "(Lkotlin/time/Instant;)Z", "isDistantFuture", "isDistantFuture$annotations", "DISTANT_PAST_SECONDS", "", "DISTANT_FUTURE_SECONDS", "MIN_SECOND", "MAX_SECOND", "parseIso", "isoString", "", "formatIso", "", "instant", "DAYS_PER_CYCLE", "", "DAYS_0000_TO_1970", "safeAddOrElse", "a", "b", "action", "Lkotlin/Function0;", "", "safeMultiplyOrElse", "SECONDS_PER_HOUR", "SECONDS_PER_MINUTE", "HOURS_PER_DAY", "SECONDS_PER_DAY", "NANOS_PER_SECOND", "NANOS_PER_MILLI", "MILLIS_PER_SECOND", "isLeapYear", "year", "monthLength", "POWERS_OF_TEN", "", "asciiDigitPositionsInIsoStringAfterYear", "colonsInIsoOffsetString", "asciiDigitsInIsoOffsetString", "truncateForErrorMessage", "maxLength", "kotlin-stdlib"}, k = 2, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class InstantKt {
    private static final int DAYS_0000_TO_1970 = 719528;
    private static final int DAYS_PER_CYCLE = 146097;
    private static final long DISTANT_FUTURE_SECONDS = 3093527980800L;
    private static final long DISTANT_PAST_SECONDS = -3217862419201L;
    private static final int HOURS_PER_DAY = 24;
    private static final long MAX_SECOND = 31556889864403199L;
    private static final int MILLIS_PER_SECOND = 1000;
    private static final long MIN_SECOND = -31557014167219200L;
    private static final int NANOS_PER_MILLI = 1000000;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    public static final int NANOS_PER_SECOND = 1000000000;
    private static final int[] POWERS_OF_TEN = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, NANOS_PER_SECOND};
    private static final int[] asciiDigitPositionsInIsoStringAfterYear = {1, 2, 4, 5, 7, 8, 10, 11, 13, 14};
    private static final int[] colonsInIsoOffsetString = {3, 6};
    private static final int[] asciiDigitsInIsoOffsetString = {1, 2, 4, 5, 7, 8};

    public static final /* synthetic */ String access$formatIso(Instant instant) {
        return formatIso(instant);
    }

    public static final /* synthetic */ Instant access$parseIso(CharSequence isoString) {
        return parseIso(isoString);
    }

    public static /* synthetic */ void isDistantFuture$annotations(Instant instant) {
    }

    public static /* synthetic */ void isDistantPast$annotations(Instant instant) {
    }

    private static final boolean isDistantPast(Instant $this$isDistantPast) {
        Intrinsics.checkNotNullParameter($this$isDistantPast, "<this>");
        return $this$isDistantPast.compareTo(Instant.INSTANCE.getDISTANT_PAST()) <= 0;
    }

    private static final boolean isDistantFuture(Instant $this$isDistantFuture) {
        Intrinsics.checkNotNullParameter($this$isDistantFuture, "<this>");
        return $this$isDistantFuture.compareTo(Instant.INSTANCE.getDISTANT_FUTURE()) >= 0;
    }

    private static final Void parseIso$parseFailure(CharSequence $isoString, String error) {
        throw new InstantFormatException(error + " when parsing an Instant from \"" + truncateForErrorMessage($isoString, 64) + Typography.quote);
    }

    private static final void parseIso$expect(CharSequence $isoString, String what, int where, Function1<? super Character, Boolean> function1) {
        char c = $isoString.charAt(where);
        if (!function1.invoke(Character.valueOf(c)).booleanValue()) {
            parseIso$parseFailure($isoString, "Expected " + what + ", but got '" + c + "' at position " + where);
            throw new KotlinNothingValueException();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x029c A[LOOP:4: B:124:0x0276->B:133:0x029c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02a3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0448  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0510  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x016b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.time.Instant parseIso(java.lang.CharSequence r29) {
        /*
            Method dump skipped, instructions count: 1372
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.time.InstantKt.parseIso(java.lang.CharSequence):kotlin.time.Instant");
    }

    public static final boolean parseIso$lambda$1(char it) {
        return it == '-';
    }

    public static final boolean parseIso$lambda$2(char it) {
        return it == '-';
    }

    public static final boolean parseIso$lambda$3(char it) {
        return it == 'T' || it == 't';
    }

    public static final boolean parseIso$lambda$4(char it) {
        return it == ':';
    }

    public static final boolean parseIso$lambda$5(char it) {
        return it == ':';
    }

    public static final boolean parseIso$lambda$6(char it) {
        return '0' <= it && it < ':';
    }

    private static final int parseIso$twoDigitNumber(CharSequence s, int index) {
        return ((s.charAt(index) - '0') * 10) + (s.charAt(index + 1) - '0');
    }

    public static final String formatIso(Instant instant) {
        StringBuilder $this$formatIso_u24lambda_u248 = new StringBuilder();
        UnboundLocalDateTime ldt = UnboundLocalDateTime.INSTANCE.fromInstant(instant);
        int number = ldt.getYear();
        if (Math.abs(number) < 1000) {
            StringBuilder innerBuilder = new StringBuilder();
            if (number >= 0) {
                Intrinsics.checkNotNullExpressionValue(innerBuilder.append(number + 10000).deleteCharAt(0), "deleteCharAt(...)");
            } else {
                Intrinsics.checkNotNullExpressionValue(innerBuilder.append(number - 10000).deleteCharAt(1), "deleteCharAt(...)");
            }
            $this$formatIso_u24lambda_u248.append((CharSequence) innerBuilder);
        } else {
            if (number >= 10000) {
                $this$formatIso_u24lambda_u248.append('+');
            }
            $this$formatIso_u24lambda_u248.append(number);
        }
        $this$formatIso_u24lambda_u248.append('-');
        formatIso$lambda$8$appendTwoDigits($this$formatIso_u24lambda_u248, $this$formatIso_u24lambda_u248, ldt.getMonth());
        $this$formatIso_u24lambda_u248.append('-');
        formatIso$lambda$8$appendTwoDigits($this$formatIso_u24lambda_u248, $this$formatIso_u24lambda_u248, ldt.getDay());
        $this$formatIso_u24lambda_u248.append('T');
        formatIso$lambda$8$appendTwoDigits($this$formatIso_u24lambda_u248, $this$formatIso_u24lambda_u248, ldt.getHour());
        $this$formatIso_u24lambda_u248.append(':');
        formatIso$lambda$8$appendTwoDigits($this$formatIso_u24lambda_u248, $this$formatIso_u24lambda_u248, ldt.getMinute());
        $this$formatIso_u24lambda_u248.append(':');
        formatIso$lambda$8$appendTwoDigits($this$formatIso_u24lambda_u248, $this$formatIso_u24lambda_u248, ldt.getSecond());
        if (ldt.getNanosecond() != 0) {
            $this$formatIso_u24lambda_u248.append('.');
            int zerosToStrip = 0;
            while (ldt.getNanosecond() % POWERS_OF_TEN[zerosToStrip + 1] == 0) {
                zerosToStrip++;
            }
            int zerosToStrip2 = zerosToStrip - (zerosToStrip % 3);
            int numberToOutput = ldt.getNanosecond() / POWERS_OF_TEN[zerosToStrip2];
            String valueOf = String.valueOf(POWERS_OF_TEN[9 - zerosToStrip2] + numberToOutput);
            Intrinsics.checkNotNull(valueOf, "null cannot be cast to non-null type java.lang.String");
            String substring = valueOf.substring(1);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            $this$formatIso_u24lambda_u248.append(substring);
        }
        $this$formatIso_u24lambda_u248.append('Z');
        return $this$formatIso_u24lambda_u248.toString();
    }

    private static final void formatIso$lambda$8$appendTwoDigits(Appendable $this$formatIso_u24lambda_u248_u24appendTwoDigits, StringBuilder $this_buildString, int number) {
        if (number < 10) {
            $this$formatIso_u24lambda_u248_u24appendTwoDigits.append('0');
        }
        $this_buildString.append(number);
    }

    private static final long safeAddOrElse(long a, long b, Function0 action) {
        long sum = a + b;
        if ((a ^ sum) < 0 && (a ^ b) >= 0) {
            action.invoke();
            throw new KotlinNothingValueException();
        }
        return sum;
    }

    private static final long safeMultiplyOrElse(long a, long b, Function0 action) {
        if (b == 1) {
            return a;
        }
        if (a == 1) {
            return b;
        }
        if (a == 0 || b == 0) {
            return 0L;
        }
        long total = a * b;
        if (total / b != a || ((a == Long.MIN_VALUE && b == -1) || (b == Long.MIN_VALUE && a == -1))) {
            action.invoke();
            throw new KotlinNothingValueException();
        }
        return total;
    }

    public static final boolean isLeapYear(int year) {
        return (year & 3) == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    private static final int monthLength(int $this$monthLength, boolean isLeapYear) {
        switch ($this$monthLength) {
            case 2:
                return isLeapYear ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }

    private static final String truncateForErrorMessage(CharSequence $this$truncateForErrorMessage, int maxLength) {
        return $this$truncateForErrorMessage.length() <= maxLength ? $this$truncateForErrorMessage.toString() : $this$truncateForErrorMessage.subSequence(0, maxLength).toString() + "...";
    }
}
