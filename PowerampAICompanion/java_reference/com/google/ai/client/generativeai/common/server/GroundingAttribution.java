package com.google.ai.client.generativeai.common.server;

import com.maxmpz.poweramp.player.RouterConsts;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0002$%B/\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ$\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J!\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#HÇ\u0001R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0010\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006&"}, d2 = {"Lcom/google/ai/client/generativeai/common/server/GroundingAttribution;", "", "seen1", "", "segment", "Lcom/google/ai/client/generativeai/common/server/Segment;", "confidenceScore", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/google/ai/client/generativeai/common/server/Segment;Ljava/lang/Float;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/google/ai/client/generativeai/common/server/Segment;Ljava/lang/Float;)V", "getConfidenceScore$annotations", "()V", "getConfidenceScore", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getSegment", "()Lcom/google/ai/client/generativeai/common/server/Segment;", "component1", "component2", "copy", "(Lcom/google/ai/client/generativeai/common/server/Segment;Ljava/lang/Float;)Lcom/google/ai/client/generativeai/common/server/GroundingAttribution;", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class GroundingAttribution {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Float confidenceScore;
    private final Segment segment;

    public static /* synthetic */ GroundingAttribution copy$default(GroundingAttribution groundingAttribution, Segment segment, Float f, int i, Object obj) {
        if ((i & 1) != 0) {
            segment = groundingAttribution.segment;
        }
        if ((i & 2) != 0) {
            f = groundingAttribution.confidenceScore;
        }
        return groundingAttribution.copy(segment, f);
    }

    @SerialName("confidence_score")
    public static /* synthetic */ void getConfidenceScore$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final Segment getSegment() {
        return this.segment;
    }

    /* renamed from: component2, reason: from getter */
    public final Float getConfidenceScore() {
        return this.confidenceScore;
    }

    public final GroundingAttribution copy(Segment segment, Float confidenceScore) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        return new GroundingAttribution(segment, confidenceScore);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GroundingAttribution)) {
            return false;
        }
        GroundingAttribution groundingAttribution = (GroundingAttribution) other;
        return Intrinsics.areEqual(this.segment, groundingAttribution.segment) && Intrinsics.areEqual((Object) this.confidenceScore, (Object) groundingAttribution.confidenceScore);
    }

    public int hashCode() {
        return (this.segment.hashCode() * 31) + (this.confidenceScore == null ? 0 : this.confidenceScore.hashCode());
    }

    public String toString() {
        return "GroundingAttribution(segment=" + this.segment + ", confidenceScore=" + this.confidenceScore + ")";
    }

    /* compiled from: Types.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/server/GroundingAttribution$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/server/GroundingAttribution;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<GroundingAttribution> serializer() {
            return GroundingAttribution$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ GroundingAttribution(int seen1, Segment segment, @SerialName("confidence_score") Float confidenceScore, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (seen1 & 3)) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 3, GroundingAttribution$$serializer.INSTANCE.getDescriptor());
        }
        this.segment = segment;
        this.confidenceScore = confidenceScore;
    }

    public GroundingAttribution(Segment segment, Float confidenceScore) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        this.segment = segment;
        this.confidenceScore = confidenceScore;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(GroundingAttribution self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeSerializableElement(serialDesc, 0, Segment$$serializer.INSTANCE, self.segment);
        output.encodeNullableSerializableElement(serialDesc, 1, FloatSerializer.INSTANCE, self.confidenceScore);
    }

    public final Segment getSegment() {
        return this.segment;
    }

    public final Float getConfidenceScore() {
        return this.confidenceScore;
    }
}
