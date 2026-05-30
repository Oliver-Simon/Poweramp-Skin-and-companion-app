package io.ktor.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Charset.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0019\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0005¨\u0006\u0006"}, d2 = {"isLowerCase", "", "", "toCharArray", "", "", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class CharsetKt {
    public static final boolean isLowerCase(char $this$isLowerCase) {
        return Character.toLowerCase($this$isLowerCase) == $this$isLowerCase;
    }

    public static final char[] toCharArray(String $this$toCharArray) {
        Intrinsics.checkNotNullParameter($this$toCharArray, "<this>");
        int length = $this$toCharArray.length();
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = $this$toCharArray.charAt(i);
        }
        return cArr;
    }
}
