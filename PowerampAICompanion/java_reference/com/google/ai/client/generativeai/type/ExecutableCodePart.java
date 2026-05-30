package com.google.ai.client.generativeai.type;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Part.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/google/ai/client/generativeai/type/ExecutableCodePart;", "Lcom/google/ai/client/generativeai/type/Part;", "language", "", "code", "(Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getLanguage", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ExecutableCodePart implements Part {
    private final String code;
    private final String language;

    public ExecutableCodePart(String language, String code) {
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(code, "code");
        this.language = language;
        this.code = code;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getLanguage() {
        return this.language;
    }
}
