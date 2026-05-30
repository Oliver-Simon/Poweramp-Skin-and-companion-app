package io.ktor.http;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: CookieUtils.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(C)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 176)
/* loaded from: classes9.dex */
public final class CookieUtilsKt$tryParseDayOfMonth$2 extends Lambda implements Function1<Character, Boolean> {
    public static final CookieUtilsKt$tryParseDayOfMonth$2 INSTANCE = new CookieUtilsKt$tryParseDayOfMonth$2();

    public CookieUtilsKt$tryParseDayOfMonth$2() {
        super(1);
    }

    public final Boolean invoke(char it) {
        return Boolean.valueOf(CookieUtilsKt.isOctet(it));
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Boolean invoke(Character ch) {
        return invoke(ch.charValue());
    }
}
