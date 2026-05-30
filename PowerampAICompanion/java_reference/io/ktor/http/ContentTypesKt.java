package io.ktor.http;

import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import java.nio.charset.Charset;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ContentTypes.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002*\u00020\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\n\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002\u001a\u0016\u0010\u0006\u001a\u00020\u0005*\u00020\u00052\n\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002¨\u0006\u0007"}, d2 = {HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "Lio/ktor/http/HeaderValueWithParameters;", "withCharset", "Lio/ktor/http/ContentType;", "withCharsetIfNeeded", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ContentTypesKt {
    public static final ContentType withCharset(ContentType $this$withCharset, Charset charset) {
        Intrinsics.checkNotNullParameter($this$withCharset, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return $this$withCharset.withParameter(HttpAuthHeader.Parameters.Charset, CharsetJVMKt.getName(charset));
    }

    public static final ContentType withCharsetIfNeeded(ContentType $this$withCharsetIfNeeded, Charset charset) {
        Intrinsics.checkNotNullParameter($this$withCharsetIfNeeded, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        String lowerCase = $this$withCharsetIfNeeded.getContentType().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        if (!Intrinsics.areEqual(lowerCase, "text")) {
            return $this$withCharsetIfNeeded;
        }
        return $this$withCharsetIfNeeded.withParameter(HttpAuthHeader.Parameters.Charset, CharsetJVMKt.getName(charset));
    }

    public static final Charset charset(HeaderValueWithParameters $this$charset) {
        Intrinsics.checkNotNullParameter($this$charset, "<this>");
        String it = $this$charset.parameter(HttpAuthHeader.Parameters.Charset);
        if (it == null) {
            return null;
        }
        try {
            return Charset.forName(it);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
