package com.google.ai.client.generativeai.type;

import com.google.ai.client.generativeai.type.Content;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Content.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a+\u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b¨\u0006\t"}, d2 = {"content", "Lcom/google/ai/client/generativeai/type/Content;", "role", "", "init", "Lkotlin/Function1;", "Lcom/google/ai/client/generativeai/type/Content$Builder;", "", "Lkotlin/ExtensionFunctionType;", "generativeai_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ContentKt {
    public static /* synthetic */ Content content$default(String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "user";
        }
        return content(str, function1);
    }

    public static final Content content(String role, Function1<? super Content.Builder, Unit> init) {
        Intrinsics.checkNotNullParameter(init, "init");
        Content.Builder builder = new Content.Builder();
        builder.setRole(role);
        init.invoke(builder);
        return builder.build();
    }
}
