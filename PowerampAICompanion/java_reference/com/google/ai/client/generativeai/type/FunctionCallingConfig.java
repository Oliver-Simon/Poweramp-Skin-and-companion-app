package com.google.ai.client.generativeai.type;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FunctionCallingConfig.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/google/ai/client/generativeai/type/FunctionCallingConfig;", "", "mode", "Lcom/google/ai/client/generativeai/type/FunctionCallingConfig$Mode;", "(Lcom/google/ai/client/generativeai/type/FunctionCallingConfig$Mode;)V", "getMode", "()Lcom/google/ai/client/generativeai/type/FunctionCallingConfig$Mode;", "Mode", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FunctionCallingConfig {
    private final Mode mode;

    /* compiled from: FunctionCallingConfig.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/type/FunctionCallingConfig$Mode;", "", "(Ljava/lang/String;I)V", "AUTO", "ANY", "NONE", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public enum Mode {
        AUTO,
        ANY,
        NONE
    }

    public FunctionCallingConfig(Mode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.mode = mode;
    }

    public final Mode getMode() {
        return this.mode;
    }
}
