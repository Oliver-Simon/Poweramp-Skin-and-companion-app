package com.google.ai.client.generativeai.type;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Candidate.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/google/ai/client/generativeai/type/SafetyRating;", "", "category", "Lcom/google/ai/client/generativeai/type/HarmCategory;", "probability", "Lcom/google/ai/client/generativeai/type/HarmProbability;", "(Lcom/google/ai/client/generativeai/type/HarmCategory;Lcom/google/ai/client/generativeai/type/HarmProbability;)V", "getCategory", "()Lcom/google/ai/client/generativeai/type/HarmCategory;", "getProbability", "()Lcom/google/ai/client/generativeai/type/HarmProbability;", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SafetyRating {
    private final HarmCategory category;
    private final HarmProbability probability;

    public SafetyRating(HarmCategory category, HarmProbability probability) {
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(probability, "probability");
        this.category = category;
        this.probability = probability;
    }

    public final HarmCategory getCategory() {
        return this.category;
    }

    public final HarmProbability getProbability() {
        return this.probability;
    }
}
