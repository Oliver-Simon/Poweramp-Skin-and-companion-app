package com.google.ai.client.generativeai.type;

import com.google.ai.client.generativeai.type.FunctionCallingConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ToolConfig.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/google/ai/client/generativeai/type/ToolConfig;", "", "functionCallingConfig", "Lcom/google/ai/client/generativeai/type/FunctionCallingConfig;", "(Lcom/google/ai/client/generativeai/type/FunctionCallingConfig;)V", "getFunctionCallingConfig", "()Lcom/google/ai/client/generativeai/type/FunctionCallingConfig;", "Companion", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ToolConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FunctionCallingConfig functionCallingConfig;

    public ToolConfig(FunctionCallingConfig functionCallingConfig) {
        Intrinsics.checkNotNullParameter(functionCallingConfig, "functionCallingConfig");
        this.functionCallingConfig = functionCallingConfig;
    }

    public final FunctionCallingConfig getFunctionCallingConfig() {
        return this.functionCallingConfig;
    }

    /* compiled from: ToolConfig.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/type/ToolConfig$Companion;", "", "()V", "always", "Lcom/google/ai/client/generativeai/type/ToolConfig;", "never", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ToolConfig never() {
            return new ToolConfig(new FunctionCallingConfig(FunctionCallingConfig.Mode.NONE));
        }

        public final ToolConfig always() {
            return new ToolConfig(new FunctionCallingConfig(FunctionCallingConfig.Mode.ANY));
        }
    }
}
