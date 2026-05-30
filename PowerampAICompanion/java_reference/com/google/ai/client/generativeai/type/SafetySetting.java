package com.google.ai.client.generativeai.type;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SafetySetting.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/google/ai/client/generativeai/type/SafetySetting;", "", "harmCategory", "Lcom/google/ai/client/generativeai/type/HarmCategory;", "threshold", "Lcom/google/ai/client/generativeai/type/BlockThreshold;", "(Lcom/google/ai/client/generativeai/type/HarmCategory;Lcom/google/ai/client/generativeai/type/BlockThreshold;)V", "getHarmCategory", "()Lcom/google/ai/client/generativeai/type/HarmCategory;", "getThreshold", "()Lcom/google/ai/client/generativeai/type/BlockThreshold;", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SafetySetting {
    private final HarmCategory harmCategory;
    private final BlockThreshold threshold;

    public SafetySetting(HarmCategory harmCategory, BlockThreshold threshold) {
        Intrinsics.checkNotNullParameter(harmCategory, "harmCategory");
        Intrinsics.checkNotNullParameter(threshold, "threshold");
        this.harmCategory = harmCategory;
        this.threshold = threshold;
    }

    public final HarmCategory getHarmCategory() {
        return this.harmCategory;
    }

    public final BlockThreshold getThreshold() {
        return this.threshold;
    }
}
