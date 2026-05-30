package com.google.ai.client.generativeai.type;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Candidate.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B5\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/google/ai/client/generativeai/type/Candidate;", "", "content", "Lcom/google/ai/client/generativeai/type/Content;", "safetyRatings", "", "Lcom/google/ai/client/generativeai/type/SafetyRating;", "citationMetadata", "Lcom/google/ai/client/generativeai/type/CitationMetadata;", "finishReason", "Lcom/google/ai/client/generativeai/type/FinishReason;", "(Lcom/google/ai/client/generativeai/type/Content;Ljava/util/List;Ljava/util/List;Lcom/google/ai/client/generativeai/type/FinishReason;)V", "getCitationMetadata", "()Ljava/util/List;", "getContent", "()Lcom/google/ai/client/generativeai/type/Content;", "getFinishReason", "()Lcom/google/ai/client/generativeai/type/FinishReason;", "getSafetyRatings", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Candidate {
    private final List<CitationMetadata> citationMetadata;
    private final Content content;
    private final FinishReason finishReason;
    private final List<SafetyRating> safetyRatings;

    public Candidate(Content content, List<SafetyRating> safetyRatings, List<CitationMetadata> citationMetadata, FinishReason finishReason) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(safetyRatings, "safetyRatings");
        Intrinsics.checkNotNullParameter(citationMetadata, "citationMetadata");
        this.content = content;
        this.safetyRatings = safetyRatings;
        this.citationMetadata = citationMetadata;
        this.finishReason = finishReason;
    }

    public final Content getContent() {
        return this.content;
    }

    public final List<SafetyRating> getSafetyRatings() {
        return this.safetyRatings;
    }

    public final List<CitationMetadata> getCitationMetadata() {
        return this.citationMetadata;
    }

    public final FinishReason getFinishReason() {
        return this.finishReason;
    }
}
