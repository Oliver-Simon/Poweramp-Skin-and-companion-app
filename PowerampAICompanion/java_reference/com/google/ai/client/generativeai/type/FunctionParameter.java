package com.google.ai.client.generativeai.type;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FunctionParameter.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/google/ai/client/generativeai/type/FunctionParameter;", ExifInterface.GPS_DIRECTION_TRUE, "", "name", "", "description", "type", "Lcom/google/ai/client/generativeai/type/FunctionType;", "(Ljava/lang/String;Ljava/lang/String;Lcom/google/ai/client/generativeai/type/FunctionType;)V", "getDescription", "()Ljava/lang/String;", "getName", "getType", "()Lcom/google/ai/client/generativeai/type/FunctionType;", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FunctionParameter<T> {
    private final String description;
    private final String name;
    private final FunctionType<T> type;

    public FunctionParameter(String name, String description, FunctionType<T> type) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(type, "type");
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getName() {
        return this.name;
    }

    public final FunctionType<T> getType() {
        return this.type;
    }
}
