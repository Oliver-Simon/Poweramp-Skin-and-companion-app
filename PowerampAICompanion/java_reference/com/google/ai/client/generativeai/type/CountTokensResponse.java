package com.google.ai.client.generativeai.type;

import kotlin.Metadata;

/* compiled from: CountTokensResponse.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u0086\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/google/ai/client/generativeai/type/CountTokensResponse;", "", "totalTokens", "", "(I)V", "getTotalTokens", "()I", "component1", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CountTokensResponse {
    private final int totalTokens;

    public CountTokensResponse(int totalTokens) {
        this.totalTokens = totalTokens;
    }

    public final int getTotalTokens() {
        return this.totalTokens;
    }

    /* renamed from: component1, reason: from getter */
    public final int getTotalTokens() {
        return this.totalTokens;
    }
}
