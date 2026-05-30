package io.ktor.http;

import io.ktor.util.date.GMTDateParser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import org.slf4j.Marker;

/* compiled from: ContentRange.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a+\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\b\u001a+\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"contentRangeHeaderValue", "", "range", "Lkotlin/ranges/LongRange;", "fullLength", "", "unit", "Lio/ktor/http/RangeUnits;", "(Lkotlin/ranges/LongRange;Ljava/lang/Long;Lio/ktor/http/RangeUnits;)Ljava/lang/String;", "(Lkotlin/ranges/LongRange;Ljava/lang/Long;Ljava/lang/String;)Ljava/lang/String;", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ContentRangeKt {
    public static /* synthetic */ String contentRangeHeaderValue$default(LongRange longRange, Long l, RangeUnits rangeUnits, int i, Object obj) {
        if ((i & 2) != 0) {
            l = null;
        }
        if ((i & 4) != 0) {
            rangeUnits = RangeUnits.Bytes;
        }
        return contentRangeHeaderValue(longRange, l, rangeUnits);
    }

    public static final String contentRangeHeaderValue(LongRange range, Long fullLength, RangeUnits unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return contentRangeHeaderValue(range, fullLength, unit.getUnitToken());
    }

    public static /* synthetic */ String contentRangeHeaderValue$default(LongRange longRange, Long l, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            l = null;
        }
        if ((i & 4) != 0) {
            str = RangeUnits.Bytes.getUnitToken();
        }
        return contentRangeHeaderValue(longRange, l, str);
    }

    public static final String contentRangeHeaderValue(LongRange range, Long fullLength, String unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        StringBuilder $this$contentRangeHeaderValue_u24lambda_u240 = new StringBuilder();
        $this$contentRangeHeaderValue_u24lambda_u240.append(unit);
        $this$contentRangeHeaderValue_u24lambda_u240.append(" ");
        if (range != null) {
            $this$contentRangeHeaderValue_u24lambda_u240.append(range.getStart().longValue());
            $this$contentRangeHeaderValue_u24lambda_u240.append('-');
            $this$contentRangeHeaderValue_u24lambda_u240.append(range.getEndInclusive().longValue());
        } else {
            $this$contentRangeHeaderValue_u24lambda_u240.append(GMTDateParser.ANY);
        }
        $this$contentRangeHeaderValue_u24lambda_u240.append('/');
        $this$contentRangeHeaderValue_u24lambda_u240.append(fullLength == null ? Marker.ANY_MARKER : fullLength);
        String sb = $this$contentRangeHeaderValue_u24lambda_u240.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }
}
