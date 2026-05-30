package io.ktor.http;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ContentDisposition.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002¨\u0006\u0004"}, d2 = {"encodeContentDispositionAttribute", "", "key", "value", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ContentDispositionKt {
    public static final /* synthetic */ String access$encodeContentDispositionAttribute(String key, String value) {
        return encodeContentDispositionAttribute(key, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String encodeContentDispositionAttribute(String key, String value) {
        if (!Intrinsics.areEqual(key, ContentDisposition.Parameters.FileNameAsterisk)) {
            return value;
        }
        boolean z = true;
        if (StringsKt.startsWith(value, "utf-8''", true)) {
            return value;
        }
        String $this$all$iv = value;
        int i = 0;
        while (true) {
            if (i >= $this$all$iv.length()) {
                break;
            }
            char element$iv = $this$all$iv.charAt(i);
            if (!CodecsKt.getATTRIBUTE_CHARACTERS().contains(Character.valueOf(element$iv))) {
                z = false;
                break;
            }
            i++;
        }
        if (z) {
            return value;
        }
        String encodedValue = CodecsKt.percentEncode(value, CodecsKt.getATTRIBUTE_CHARACTERS());
        return "utf-8''" + encodedValue;
    }
}
