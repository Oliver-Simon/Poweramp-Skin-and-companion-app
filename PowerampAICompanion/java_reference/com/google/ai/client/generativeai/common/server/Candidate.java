package com.google.ai.client.generativeai.common.server;

import com.google.ai.client.generativeai.common.shared.Content;
import com.google.ai.client.generativeai.common.shared.Content$$serializer;
import com.maxmpz.poweramp.player.RouterConsts;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 12\u00020\u0001:\u000201BQ\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011BG\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u0012J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0011\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u000eHÆ\u0003JK\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\t\u0010'\u001a\u00020(HÖ\u0001J!\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/HÇ\u0001R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u00062"}, d2 = {"Lcom/google/ai/client/generativeai/common/server/Candidate;", "", "seen1", "", "content", "Lcom/google/ai/client/generativeai/common/shared/Content;", "finishReason", "Lcom/google/ai/client/generativeai/common/server/FinishReason;", "safetyRatings", "", "Lcom/google/ai/client/generativeai/common/server/SafetyRating;", "citationMetadata", "Lcom/google/ai/client/generativeai/common/server/CitationMetadata;", "groundingMetadata", "Lcom/google/ai/client/generativeai/common/server/GroundingMetadata;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/google/ai/client/generativeai/common/shared/Content;Lcom/google/ai/client/generativeai/common/server/FinishReason;Ljava/util/List;Lcom/google/ai/client/generativeai/common/server/CitationMetadata;Lcom/google/ai/client/generativeai/common/server/GroundingMetadata;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/google/ai/client/generativeai/common/shared/Content;Lcom/google/ai/client/generativeai/common/server/FinishReason;Ljava/util/List;Lcom/google/ai/client/generativeai/common/server/CitationMetadata;Lcom/google/ai/client/generativeai/common/server/GroundingMetadata;)V", "getCitationMetadata", "()Lcom/google/ai/client/generativeai/common/server/CitationMetadata;", "getContent", "()Lcom/google/ai/client/generativeai/common/shared/Content;", "getFinishReason", "()Lcom/google/ai/client/generativeai/common/server/FinishReason;", "getGroundingMetadata", "()Lcom/google/ai/client/generativeai/common/server/GroundingMetadata;", "getSafetyRatings", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class Candidate {
    private final CitationMetadata citationMetadata;
    private final Content content;
    private final FinishReason finishReason;
    private final GroundingMetadata groundingMetadata;
    private final List<SafetyRating> safetyRatings;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {null, null, new ArrayListSerializer(SafetyRating$$serializer.INSTANCE), null, null};

    public Candidate() {
        this((Content) null, (FinishReason) null, (List) null, (CitationMetadata) null, (GroundingMetadata) null, 31, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Candidate copy$default(Candidate candidate, Content content, FinishReason finishReason, List list, CitationMetadata citationMetadata, GroundingMetadata groundingMetadata, int i, Object obj) {
        if ((i & 1) != 0) {
            content = candidate.content;
        }
        if ((i & 2) != 0) {
            finishReason = candidate.finishReason;
        }
        if ((i & 4) != 0) {
            list = candidate.safetyRatings;
        }
        if ((i & 8) != 0) {
            citationMetadata = candidate.citationMetadata;
        }
        if ((i & 16) != 0) {
            groundingMetadata = candidate.groundingMetadata;
        }
        GroundingMetadata groundingMetadata2 = groundingMetadata;
        List list2 = list;
        return candidate.copy(content, finishReason, list2, citationMetadata, groundingMetadata2);
    }

    /* renamed from: component1, reason: from getter */
    public final Content getContent() {
        return this.content;
    }

    /* renamed from: component2, reason: from getter */
    public final FinishReason getFinishReason() {
        return this.finishReason;
    }

    public final List<SafetyRating> component3() {
        return this.safetyRatings;
    }

    /* renamed from: component4, reason: from getter */
    public final CitationMetadata getCitationMetadata() {
        return this.citationMetadata;
    }

    /* renamed from: component5, reason: from getter */
    public final GroundingMetadata getGroundingMetadata() {
        return this.groundingMetadata;
    }

    public final Candidate copy(Content content, FinishReason finishReason, List<SafetyRating> safetyRatings, CitationMetadata citationMetadata, GroundingMetadata groundingMetadata) {
        return new Candidate(content, finishReason, safetyRatings, citationMetadata, groundingMetadata);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Candidate)) {
            return false;
        }
        Candidate candidate = (Candidate) other;
        return Intrinsics.areEqual(this.content, candidate.content) && this.finishReason == candidate.finishReason && Intrinsics.areEqual(this.safetyRatings, candidate.safetyRatings) && Intrinsics.areEqual(this.citationMetadata, candidate.citationMetadata) && Intrinsics.areEqual(this.groundingMetadata, candidate.groundingMetadata);
    }

    public int hashCode() {
        return ((((((((this.content == null ? 0 : this.content.hashCode()) * 31) + (this.finishReason == null ? 0 : this.finishReason.hashCode())) * 31) + (this.safetyRatings == null ? 0 : this.safetyRatings.hashCode())) * 31) + (this.citationMetadata == null ? 0 : this.citationMetadata.hashCode())) * 31) + (this.groundingMetadata != null ? this.groundingMetadata.hashCode() : 0);
    }

    public String toString() {
        return "Candidate(content=" + this.content + ", finishReason=" + this.finishReason + ", safetyRatings=" + this.safetyRatings + ", citationMetadata=" + this.citationMetadata + ", groundingMetadata=" + this.groundingMetadata + ")";
    }

    /* compiled from: Types.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/server/Candidate$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/server/Candidate;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<Candidate> serializer() {
            return Candidate$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ Candidate(int seen1, Content content, FinishReason finishReason, List safetyRatings, CitationMetadata citationMetadata, GroundingMetadata groundingMetadata, SerializationConstructorMarker serializationConstructorMarker) {
        if ((seen1 & 1) == 0) {
            this.content = null;
        } else {
            this.content = content;
        }
        if ((seen1 & 2) == 0) {
            this.finishReason = null;
        } else {
            this.finishReason = finishReason;
        }
        if ((seen1 & 4) == 0) {
            this.safetyRatings = null;
        } else {
            this.safetyRatings = safetyRatings;
        }
        if ((seen1 & 8) == 0) {
            this.citationMetadata = null;
        } else {
            this.citationMetadata = citationMetadata;
        }
        if ((seen1 & 16) == 0) {
            this.groundingMetadata = null;
        } else {
            this.groundingMetadata = groundingMetadata;
        }
    }

    public Candidate(Content content, FinishReason finishReason, List<SafetyRating> list, CitationMetadata citationMetadata, GroundingMetadata groundingMetadata) {
        this.content = content;
        this.finishReason = finishReason;
        this.safetyRatings = list;
        this.citationMetadata = citationMetadata;
        this.groundingMetadata = groundingMetadata;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(Candidate self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.content != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, Content$$serializer.INSTANCE, self.content);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.finishReason != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, FinishReasonSerializer.INSTANCE, self.finishReason);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.safetyRatings != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, kSerializerArr[2], self.safetyRatings);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.citationMetadata != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, CitationMetadata$$serializer.INSTANCE, self.citationMetadata);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.groundingMetadata != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, GroundingMetadata$$serializer.INSTANCE, self.groundingMetadata);
        }
    }

    public /* synthetic */ Candidate(Content content, FinishReason finishReason, List list, CitationMetadata citationMetadata, GroundingMetadata groundingMetadata, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : content, (i & 2) != 0 ? null : finishReason, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : citationMetadata, (i & 16) != 0 ? null : groundingMetadata);
    }

    public final Content getContent() {
        return this.content;
    }

    public final FinishReason getFinishReason() {
        return this.finishReason;
    }

    public final List<SafetyRating> getSafetyRatings() {
        return this.safetyRatings;
    }

    public final CitationMetadata getCitationMetadata() {
        return this.citationMetadata;
    }

    public final GroundingMetadata getGroundingMetadata() {
        return this.groundingMetadata;
    }
}
