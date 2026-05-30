package io.ktor.http;

import kotlin.Metadata;
import kotlin.text.StringsKt;

/* compiled from: HttpHeaders.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006\u0004"}, d2 = {"isDelimiter", "", "ch", "", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpHeadersKt {
    public static final /* synthetic */ boolean access$isDelimiter(char ch) {
        return isDelimiter(ch);
    }

    public static final boolean isDelimiter(char ch) {
        return StringsKt.contains$default((CharSequence) "\"(),/:;<=>?@[\\]{}", ch, false, 2, (Object) null);
    }
}
