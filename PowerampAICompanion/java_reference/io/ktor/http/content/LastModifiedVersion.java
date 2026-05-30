package io.ktor.http.content;

import com.maxmpz.poweramp.player.RouterConsts;
import io.ktor.http.DateUtilsKt;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.util.date.DateKt;
import io.ktor.util.date.GMTDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: Versions.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\u0014\u0010\u0018\u001a\u00020\u00132\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u001aJ\u0014\u0010\u001b\u001a\u00020\u00132\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u001aJ\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\u001a\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001a*\b\u0012\u0004\u0012\u00020\u001d0\u001aH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lio/ktor/http/content/LastModifiedVersion;", "Lio/ktor/http/content/Version;", "lastModified", "Lio/ktor/util/date/GMTDate;", "(Lio/ktor/util/date/GMTDate;)V", "getLastModified", "()Lio/ktor/util/date/GMTDate;", "truncatedModificationDate", "appendHeadersTo", "", "builder", "Lio/ktor/http/HeadersBuilder;", "check", "Lio/ktor/http/content/VersionCheckResult;", "requestHeaders", "Lio/ktor/http/Headers;", "component1", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "", "hashCode", "", "ifModifiedSince", "dates", "", "ifUnmodifiedSince", "toString", "", "parseDates", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final /* data */ class LastModifiedVersion implements Version {
    private final GMTDate lastModified;
    private final GMTDate truncatedModificationDate;

    public static /* synthetic */ LastModifiedVersion copy$default(LastModifiedVersion lastModifiedVersion, GMTDate gMTDate, int i, Object obj) {
        if ((i & 1) != 0) {
            gMTDate = lastModifiedVersion.lastModified;
        }
        return lastModifiedVersion.copy(gMTDate);
    }

    /* renamed from: component1, reason: from getter */
    public final GMTDate getLastModified() {
        return this.lastModified;
    }

    public final LastModifiedVersion copy(GMTDate lastModified) {
        Intrinsics.checkNotNullParameter(lastModified, "lastModified");
        return new LastModifiedVersion(lastModified);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LastModifiedVersion) && Intrinsics.areEqual(this.lastModified, ((LastModifiedVersion) other).lastModified);
    }

    public int hashCode() {
        return this.lastModified.hashCode();
    }

    public String toString() {
        return "LastModifiedVersion(lastModified=" + this.lastModified + ')';
    }

    public LastModifiedVersion(GMTDate lastModified) {
        Intrinsics.checkNotNullParameter(lastModified, "lastModified");
        this.lastModified = lastModified;
        this.truncatedModificationDate = DateKt.truncateToSeconds(this.lastModified);
    }

    public final GMTDate getLastModified() {
        return this.lastModified;
    }

    @Override // io.ktor.http.content.Version
    public VersionCheckResult check(Headers requestHeaders) {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        List<String> all = requestHeaders.getAll(HttpHeaders.INSTANCE.getIfModifiedSince());
        List modifiedSince = all != null ? parseDates(all) : null;
        if (modifiedSince != null && !ifModifiedSince(modifiedSince)) {
            return VersionCheckResult.NOT_MODIFIED;
        }
        List<String> all2 = requestHeaders.getAll(HttpHeaders.INSTANCE.getIfUnmodifiedSince());
        List unmodifiedSince = all2 != null ? parseDates(all2) : null;
        if (unmodifiedSince != null && !ifUnmodifiedSince(unmodifiedSince)) {
            return VersionCheckResult.PRECONDITION_FAILED;
        }
        return VersionCheckResult.OK;
    }

    public final boolean ifModifiedSince(List<GMTDate> dates) {
        Intrinsics.checkNotNullParameter(dates, "dates");
        List<GMTDate> $this$any$iv = dates;
        if (($this$any$iv instanceof Collection) && $this$any$iv.isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            GMTDate it = (GMTDate) element$iv;
            GMTDate it2 = this.truncatedModificationDate.compareTo(it) > 0 ? 1 : null;
            if (it2 != null) {
                return true;
            }
        }
        return false;
    }

    public final boolean ifUnmodifiedSince(List<GMTDate> dates) {
        Intrinsics.checkNotNullParameter(dates, "dates");
        List<GMTDate> $this$all$iv = dates;
        if (($this$all$iv instanceof Collection) && $this$all$iv.isEmpty()) {
            return true;
        }
        for (Object element$iv : $this$all$iv) {
            GMTDate it = (GMTDate) element$iv;
            GMTDate it2 = this.truncatedModificationDate.compareTo(it) <= 0 ? 1 : null;
            if (it2 == null) {
                return false;
            }
        }
        return true;
    }

    @Override // io.ktor.http.content.Version
    public void appendHeadersTo(HeadersBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.set(HttpHeaders.INSTANCE.getLastModified(), DateUtilsKt.toHttpDate(this.lastModified));
    }

    private final List<GMTDate> parseDates(List<String> list) {
        List<String> $this$filter$iv = list;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            if (!StringsKt.isBlank((String) element$iv$iv)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$mapNotNull$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        Iterator it = $this$mapNotNull$iv.iterator();
        while (true) {
            GMTDate gMTDate = null;
            if (!it.hasNext()) {
                break;
            }
            Object element$iv$iv$iv = it.next();
            try {
                gMTDate = DateUtilsKt.fromHttpToGmtDate((String) element$iv$iv$iv);
            } catch (Throwable th) {
            }
            if (gMTDate != null) {
                destination$iv$iv2.add(gMTDate);
            }
        }
        List it2 = (List) destination$iv$iv2;
        if (it2.isEmpty()) {
            return null;
        }
        return it2;
    }
}
