package io.ktor.util.date;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;

/* compiled from: Date.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\u0002\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0086\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\u0002\u001a\"\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0086\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\u0007\u001a\n\u0010\n\u001a\u00020\u0001*\u00020\u0001\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"minus", "Lio/ktor/util/date/GMTDate;", "milliseconds", "", TypedValues.TransitionType.S_DURATION, "Lkotlin/time/Duration;", "minus-HG0u8IE", "(Lio/ktor/util/date/GMTDate;J)Lio/ktor/util/date/GMTDate;", "plus", "plus-HG0u8IE", "truncateToSeconds", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class DateKt {
    public static final GMTDate plus(GMTDate $this$plus, long milliseconds) {
        Intrinsics.checkNotNullParameter($this$plus, "<this>");
        return DateJvmKt.GMTDate(Long.valueOf($this$plus.getTimestamp() + milliseconds));
    }

    public static final GMTDate minus(GMTDate $this$minus, long milliseconds) {
        Intrinsics.checkNotNullParameter($this$minus, "<this>");
        return DateJvmKt.GMTDate(Long.valueOf($this$minus.getTimestamp() - milliseconds));
    }

    /* renamed from: plus-HG0u8IE, reason: not valid java name */
    public static final GMTDate m220plusHG0u8IE(GMTDate plus, long duration) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        return DateJvmKt.GMTDate(Long.valueOf(plus.getTimestamp() + Duration.m1866getInWholeMillisecondsimpl(duration)));
    }

    /* renamed from: minus-HG0u8IE, reason: not valid java name */
    public static final GMTDate m219minusHG0u8IE(GMTDate minus, long duration) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        return DateJvmKt.GMTDate(Long.valueOf(minus.getTimestamp() - Duration.m1866getInWholeMillisecondsimpl(duration)));
    }

    public static final GMTDate truncateToSeconds(GMTDate $this$truncateToSeconds) {
        Intrinsics.checkNotNullParameter($this$truncateToSeconds, "<this>");
        return DateJvmKt.GMTDate($this$truncateToSeconds.getSeconds(), $this$truncateToSeconds.getMinutes(), $this$truncateToSeconds.getHours(), $this$truncateToSeconds.getDayOfMonth(), $this$truncateToSeconds.getMonth(), $this$truncateToSeconds.getYear());
    }
}
