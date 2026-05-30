package com.google.ai.client.generativeai.type;

import kotlin.Metadata;

/* compiled from: UsageMetadata.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/google/ai/client/generativeai/type/UsageMetadata;", "", "promptTokenCount", "", "candidatesTokenCount", "totalTokenCount", "(III)V", "getCandidatesTokenCount", "()I", "getPromptTokenCount", "getTotalTokenCount", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class UsageMetadata {
    private final int candidatesTokenCount;
    private final int promptTokenCount;
    private final int totalTokenCount;

    public UsageMetadata(int promptTokenCount, int candidatesTokenCount, int totalTokenCount) {
        this.promptTokenCount = promptTokenCount;
        this.candidatesTokenCount = candidatesTokenCount;
        this.totalTokenCount = totalTokenCount;
    }

    public final int getPromptTokenCount() {
        return this.promptTokenCount;
    }

    public final int getCandidatesTokenCount() {
        return this.candidatesTokenCount;
    }

    public final int getTotalTokenCount() {
        return this.totalTokenCount;
    }
}
