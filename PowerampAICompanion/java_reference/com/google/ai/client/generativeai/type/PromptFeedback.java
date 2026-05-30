package com.google.ai.client.generativeai.type;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PromptFeedback.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001d\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/google/ai/client/generativeai/type/PromptFeedback;", "", "blockReason", "Lcom/google/ai/client/generativeai/type/BlockReason;", "safetyRatings", "", "Lcom/google/ai/client/generativeai/type/SafetyRating;", "(Lcom/google/ai/client/generativeai/type/BlockReason;Ljava/util/List;)V", "getBlockReason", "()Lcom/google/ai/client/generativeai/type/BlockReason;", "getSafetyRatings", "()Ljava/util/List;", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PromptFeedback {
    private final BlockReason blockReason;
    private final List<SafetyRating> safetyRatings;

    public PromptFeedback(BlockReason blockReason, List<SafetyRating> safetyRatings) {
        Intrinsics.checkNotNullParameter(safetyRatings, "safetyRatings");
        this.blockReason = blockReason;
        this.safetyRatings = safetyRatings;
    }

    public final BlockReason getBlockReason() {
        return this.blockReason;
    }

    public final List<SafetyRating> getSafetyRatings() {
        return this.safetyRatings;
    }
}
