package com.google.ai.client.generativeai.type;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Part.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/google/ai/client/generativeai/type/CodeExecutionResultPart;", "Lcom/google/ai/client/generativeai/type/Part;", "outcome", "Lcom/google/ai/client/generativeai/type/ExecutionOutcome;", "output", "", "(Lcom/google/ai/client/generativeai/type/ExecutionOutcome;Ljava/lang/String;)V", "getOutcome", "()Lcom/google/ai/client/generativeai/type/ExecutionOutcome;", "getOutput", "()Ljava/lang/String;", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CodeExecutionResultPart implements Part {
    private final ExecutionOutcome outcome;
    private final String output;

    public CodeExecutionResultPart(ExecutionOutcome outcome, String output) {
        Intrinsics.checkNotNullParameter(outcome, "outcome");
        Intrinsics.checkNotNullParameter(output, "output");
        this.outcome = outcome;
        this.output = output;
    }

    public final ExecutionOutcome getOutcome() {
        return this.outcome;
    }

    public final String getOutput() {
        return this.output;
    }
}
