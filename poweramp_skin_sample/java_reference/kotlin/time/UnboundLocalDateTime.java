package kotlin.time;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Instant.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0003J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\u001a"}, d2 = {"Lkotlin/time/UnboundLocalDateTime;", "", "year", "", "month", "day", "hour", "minute", "second", "nanosecond", "<init>", "(IIIIIII)V", "getYear", "()I", "getMonth", "getDay", "getHour", "getMinute", "getSecond", "getNanosecond", "toInstant", "Lkotlin/time/Instant;", "offsetSeconds", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class UnboundLocalDateTime {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int day;
    private final int hour;
    private final int minute;
    private final int month;
    private final int nanosecond;
    private final int second;
    private final int year;

    public UnboundLocalDateTime(int year, int month, int day, int hour, int minute, int second, int nanosecond) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.nanosecond = nanosecond;
    }

    public final int getYear() {
        return this.year;
    }

    public final int getMonth() {
        return this.month;
    }

    public final int getDay() {
        return this.day;
    }

    public final int getHour() {
        return this.hour;
    }

    public final int getMinute() {
        return this.minute;
    }

    public final int getSecond() {
        return this.second;
    }

    public final int getNanosecond() {
        return this.nanosecond;
    }

    public final Instant toInstant(int offsetSeconds) {
        long total;
        UnboundLocalDateTime $this$toInstant_u24lambda_u241 = this;
        long y = $this$toInstant_u24lambda_u241.year;
        long total2 = 365 * y;
        if (y >= 0) {
            total = total2 + (((3 + y) / 4) - ((99 + y) / 100)) + ((399 + y) / 400);
        } else {
            total = total2 - (((y / (-4)) - (y / (-100))) + (y / (-400)));
        }
        long total3 = total + ((($this$toInstant_u24lambda_u241.month * 367) - 362) / 12) + ($this$toInstant_u24lambda_u241.day - 1);
        if ($this$toInstant_u24lambda_u241.month > 2) {
            total3--;
            if (!InstantKt.isLeapYear($this$toInstant_u24lambda_u241.year)) {
                total3--;
            }
        }
        long epochDays = total3 - 719528;
        int daySeconds = ($this$toInstant_u24lambda_u241.hour * 3600) + ($this$toInstant_u24lambda_u241.minute * 60) + $this$toInstant_u24lambda_u241.second;
        long epochSeconds = ((86400 * epochDays) + daySeconds) - offsetSeconds;
        if (epochSeconds < Instant.INSTANCE.getMIN$kotlin_stdlib().getEpochSeconds() || epochSeconds > Instant.INSTANCE.getMAX$kotlin_stdlib().getEpochSeconds()) {
            throw new InstantFormatException("The parsed date is outside the range representable by Instant (Unix epoch second " + epochSeconds + ')');
        }
        return Instant.INSTANCE.fromEpochSeconds(epochSeconds, this.nanosecond);
    }

    public String toString() {
        return "UnboundLocalDateTime(" + this.year + '-' + this.month + '-' + this.day + ' ' + this.hour + ':' + this.minute + ':' + this.second + '.' + this.nanosecond + ')';
    }

    /* compiled from: Instant.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lkotlin/time/UnboundLocalDateTime$Companion;", "", "<init>", "()V", "fromInstant", "Lkotlin/time/UnboundLocalDateTime;", "instant", "Lkotlin/time/Instant;", "kotlin-stdlib"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UnboundLocalDateTime fromInstant(Instant instant) {
            int i;
            long doyEst;
            Intrinsics.checkNotNullParameter(instant, "instant");
            long localSecond = instant.getEpochSeconds();
            long epochDays = localSecond / 86400;
            if ((localSecond ^ 86400) < 0 && epochDays * 86400 != localSecond) {
                epochDays--;
            }
            long j = localSecond % 86400;
            int secsOfDay = (int) (j + (((((-j) | j) & (j ^ 86400)) >> 63) & 86400));
            long zeroDay = (719528 + epochDays) - 60;
            long adjust = 0;
            if (zeroDay < 0) {
                long adjustCycles = 146097;
                long adjustCycles2 = ((zeroDay + 1) / adjustCycles) - 1;
                i = 400;
                adjust = adjustCycles2 * 400;
                zeroDay += (-adjustCycles2) * adjustCycles;
            } else {
                i = 400;
            }
            long j2 = i;
            long yearEst = ((j2 * zeroDay) + 591) / 146097;
            long j3 = 365;
            long j4 = j3 * yearEst;
            long j5 = 4;
            long j6 = j4 + (yearEst / j5);
            long j7 = 100;
            long doyEst2 = zeroDay - ((j6 - (yearEst / j7)) + (yearEst / j2));
            if (doyEst2 >= 0) {
                doyEst = doyEst2;
            } else {
                yearEst--;
                doyEst = zeroDay - ((((j3 * yearEst) + (yearEst / j5)) - (yearEst / j7)) + (yearEst / j2));
            }
            int marchDoy0 = (int) doyEst;
            int marchMonth0 = ((marchDoy0 * 5) + 2) / 153;
            int month = ((marchMonth0 + 2) % 12) + 1;
            int day = (marchDoy0 - (((marchMonth0 * 306) + 5) / 10)) + 1;
            int year = (int) ((marchMonth0 / 10) + yearEst + adjust);
            int hours = secsOfDay / 3600;
            int secondWithoutHours = secsOfDay - (hours * 3600);
            int minutes = secondWithoutHours / 60;
            int second = secondWithoutHours - (minutes * 60);
            return new UnboundLocalDateTime(year, month, day, hours, minutes, second, instant.getNanosecondsOfSecond());
        }
    }
}
