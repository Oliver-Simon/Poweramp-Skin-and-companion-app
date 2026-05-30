package io.ktor.util;

import com.maxmpz.poweramp.player.RouterConsts;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: Text.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\f\u001a\u00020\bH\u0016J\b\u0010\r\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lio/ktor/util/CaseInsensitiveString;", "", "content", "", "(Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "hash", "", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class CaseInsensitiveString {
    private final String content;
    private final int hash;

    public CaseInsensitiveString(String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        this.content = content;
        String lowerCase = this.content.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        this.hash = lowerCase.hashCode();
    }

    public final String getContent() {
        return this.content;
    }

    public boolean equals(Object other) {
        String str;
        CaseInsensitiveString caseInsensitiveString = other instanceof CaseInsensitiveString ? (CaseInsensitiveString) other : null;
        return (caseInsensitiveString == null || (str = caseInsensitiveString.content) == null || !StringsKt.equals(str, this.content, true)) ? false : true;
    }

    /* renamed from: hashCode, reason: from getter */
    public int getHash() {
        return this.hash;
    }

    public String toString() {
        return this.content;
    }
}
