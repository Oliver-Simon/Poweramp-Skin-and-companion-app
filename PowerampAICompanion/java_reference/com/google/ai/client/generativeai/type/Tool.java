package com.google.ai.client.generativeai.type;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.json.JSONObject;

/* compiled from: Tool.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB#\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/google/ai/client/generativeai/type/Tool;", "", "functionDeclarations", "", "Lcom/google/ai/client/generativeai/type/FunctionDeclaration;", "codeExecution", "Lorg/json/JSONObject;", "(Ljava/util/List;Lorg/json/JSONObject;)V", "getCodeExecution", "()Lorg/json/JSONObject;", "getFunctionDeclarations", "()Ljava/util/List;", "Companion", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Tool {
    private final JSONObject codeExecution;
    private final List<FunctionDeclaration> functionDeclarations;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Tool CODE_EXECUTION = new Tool(0 == true ? 1 : 0, new JSONObject(), 1, 0 == true ? 1 : 0);

    /* JADX WARN: Multi-variable type inference failed */
    public Tool() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public Tool(List<FunctionDeclaration> list, JSONObject codeExecution) {
        this.functionDeclarations = list;
        this.codeExecution = codeExecution;
    }

    public /* synthetic */ Tool(List list, JSONObject jSONObject, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : jSONObject);
    }

    public final List<FunctionDeclaration> getFunctionDeclarations() {
        return this.functionDeclarations;
    }

    public final JSONObject getCodeExecution() {
        return this.codeExecution;
    }

    /* compiled from: Tool.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/google/ai/client/generativeai/type/Tool$Companion;", "", "()V", "CODE_EXECUTION", "Lcom/google/ai/client/generativeai/type/Tool;", "getCODE_EXECUTION", "()Lcom/google/ai/client/generativeai/type/Tool;", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Tool getCODE_EXECUTION() {
            return Tool.CODE_EXECUTION;
        }
    }
}
