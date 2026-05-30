package com.google.ai.client.generativeai.type;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FunctionDeclarations.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001a:\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0012\b\u0002\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¨\u0006\t"}, d2 = {"defineFunction", "Lcom/google/ai/client/generativeai/type/FunctionDeclaration;", "name", "", "description", "parameters", "", "Lcom/google/ai/client/generativeai/type/Schema;", "requiredParameters", "generativeai_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FunctionDeclarationsKt {
    public static /* synthetic */ FunctionDeclaration defineFunction$default(String str, String str2, List list, List list2, int i, Object obj) {
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((i & 8) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        return defineFunction(str, str2, list, list2);
    }

    public static final FunctionDeclaration defineFunction(String name, String description, List<? extends Schema<?>> parameters, List<String> requiredParameters) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(requiredParameters, "requiredParameters");
        return new FunctionDeclaration(name, description, parameters, requiredParameters);
    }
}
