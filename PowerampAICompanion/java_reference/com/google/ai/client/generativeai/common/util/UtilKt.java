package com.google.ai.client.generativeai.common.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: util.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001¨\u0006\u0003"}, d2 = {"fullModelName", "", "name", "common_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class UtilKt {
    public static final String fullModelName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        String str = StringsKt.contains$default((CharSequence) name, (CharSequence) "/", false, 2, (Object) null) ? name : null;
        return str == null ? "models/" + name : str;
    }
}
