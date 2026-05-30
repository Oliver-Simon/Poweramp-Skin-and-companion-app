package com.google.ai.client.generativeai.common.server;

import com.google.ai.client.generativeai.common.shared.HarmCategory;
import com.google.ai.client.generativeai.common.shared.HarmCategorySerializer;
import com.maxmpz.poweramp.player.RouterConsts;
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
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 52\u00020\u0001:\u000245BU\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011BE\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\u0012J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u0010#\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u001bJ\u000b\u0010$\u001a\u0004\u0018\u00010\rHÆ\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u001bJR\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u0010'J\u0013\u0010(\u001a\u00020\t2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020\u0003HÖ\u0001J\t\u0010+\u001a\u00020,HÖ\u0001J!\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000203HÇ\u0001R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001f\u0010\u001b¨\u00066"}, d2 = {"Lcom/google/ai/client/generativeai/common/server/SafetyRating;", "", "seen1", "", "category", "Lcom/google/ai/client/generativeai/common/shared/HarmCategory;", "probability", "Lcom/google/ai/client/generativeai/common/server/HarmProbability;", "blocked", "", "probabilityScore", "", "severity", "Lcom/google/ai/client/generativeai/common/server/HarmSeverity;", "severityScore", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/google/ai/client/generativeai/common/shared/HarmCategory;Lcom/google/ai/client/generativeai/common/server/HarmProbability;Ljava/lang/Boolean;Ljava/lang/Float;Lcom/google/ai/client/generativeai/common/server/HarmSeverity;Ljava/lang/Float;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/google/ai/client/generativeai/common/shared/HarmCategory;Lcom/google/ai/client/generativeai/common/server/HarmProbability;Ljava/lang/Boolean;Ljava/lang/Float;Lcom/google/ai/client/generativeai/common/server/HarmSeverity;Ljava/lang/Float;)V", "getBlocked", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getCategory", "()Lcom/google/ai/client/generativeai/common/shared/HarmCategory;", "getProbability", "()Lcom/google/ai/client/generativeai/common/server/HarmProbability;", "getProbabilityScore", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getSeverity", "()Lcom/google/ai/client/generativeai/common/server/HarmSeverity;", "getSeverityScore", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Lcom/google/ai/client/generativeai/common/shared/HarmCategory;Lcom/google/ai/client/generativeai/common/server/HarmProbability;Ljava/lang/Boolean;Ljava/lang/Float;Lcom/google/ai/client/generativeai/common/server/HarmSeverity;Ljava/lang/Float;)Lcom/google/ai/client/generativeai/common/server/SafetyRating;", "equals", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class SafetyRating {
    private final Boolean blocked;
    private final HarmCategory category;
    private final HarmProbability probability;
    private final Float probabilityScore;
    private final HarmSeverity severity;
    private final Float severityScore;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, null, HarmSeverity.INSTANCE.serializer(), null};

    public static /* synthetic */ SafetyRating copy$default(SafetyRating safetyRating, HarmCategory harmCategory, HarmProbability harmProbability, Boolean bool, Float f, HarmSeverity harmSeverity, Float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            harmCategory = safetyRating.category;
        }
        if ((i & 2) != 0) {
            harmProbability = safetyRating.probability;
        }
        if ((i & 4) != 0) {
            bool = safetyRating.blocked;
        }
        if ((i & 8) != 0) {
            f = safetyRating.probabilityScore;
        }
        if ((i & 16) != 0) {
            harmSeverity = safetyRating.severity;
        }
        if ((i & 32) != 0) {
            f2 = safetyRating.severityScore;
        }
        HarmSeverity harmSeverity2 = harmSeverity;
        Float f3 = f2;
        return safetyRating.copy(harmCategory, harmProbability, bool, f, harmSeverity2, f3);
    }

    /* renamed from: component1, reason: from getter */
    public final HarmCategory getCategory() {
        return this.category;
    }

    /* renamed from: component2, reason: from getter */
    public final HarmProbability getProbability() {
        return this.probability;
    }

    /* renamed from: component3, reason: from getter */
    public final Boolean getBlocked() {
        return this.blocked;
    }

    /* renamed from: component4, reason: from getter */
    public final Float getProbabilityScore() {
        return this.probabilityScore;
    }

    /* renamed from: component5, reason: from getter */
    public final HarmSeverity getSeverity() {
        return this.severity;
    }

    /* renamed from: component6, reason: from getter */
    public final Float getSeverityScore() {
        return this.severityScore;
    }

    public final SafetyRating copy(HarmCategory category, HarmProbability probability, Boolean blocked, Float probabilityScore, HarmSeverity severity, Float severityScore) {
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(probability, "probability");
        return new SafetyRating(category, probability, blocked, probabilityScore, severity, severityScore);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SafetyRating)) {
            return false;
        }
        SafetyRating safetyRating = (SafetyRating) other;
        return this.category == safetyRating.category && this.probability == safetyRating.probability && Intrinsics.areEqual(this.blocked, safetyRating.blocked) && Intrinsics.areEqual((Object) this.probabilityScore, (Object) safetyRating.probabilityScore) && this.severity == safetyRating.severity && Intrinsics.areEqual((Object) this.severityScore, (Object) safetyRating.severityScore);
    }

    public int hashCode() {
        return (((((((((this.category.hashCode() * 31) + this.probability.hashCode()) * 31) + (this.blocked == null ? 0 : this.blocked.hashCode())) * 31) + (this.probabilityScore == null ? 0 : this.probabilityScore.hashCode())) * 31) + (this.severity == null ? 0 : this.severity.hashCode())) * 31) + (this.severityScore != null ? this.severityScore.hashCode() : 0);
    }

    public String toString() {
        return "SafetyRating(category=" + this.category + ", probability=" + this.probability + ", blocked=" + this.blocked + ", probabilityScore=" + this.probabilityScore + ", severity=" + this.severity + ", severityScore=" + this.severityScore + ")";
    }

    /* compiled from: Types.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/server/SafetyRating$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/server/SafetyRating;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SafetyRating> serializer() {
            return SafetyRating$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ SafetyRating(int seen1, HarmCategory category, HarmProbability probability, Boolean blocked, Float probabilityScore, HarmSeverity severity, Float severityScore, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (seen1 & 3)) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 3, SafetyRating$$serializer.INSTANCE.getDescriptor());
        }
        this.category = category;
        this.probability = probability;
        if ((seen1 & 4) == 0) {
            this.blocked = null;
        } else {
            this.blocked = blocked;
        }
        if ((seen1 & 8) == 0) {
            this.probabilityScore = null;
        } else {
            this.probabilityScore = probabilityScore;
        }
        if ((seen1 & 16) == 0) {
            this.severity = null;
        } else {
            this.severity = severity;
        }
        if ((seen1 & 32) == 0) {
            this.severityScore = null;
        } else {
            this.severityScore = severityScore;
        }
    }

    public SafetyRating(HarmCategory category, HarmProbability probability, Boolean blocked, Float probabilityScore, HarmSeverity severity, Float severityScore) {
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(probability, "probability");
        this.category = category;
        this.probability = probability;
        this.blocked = blocked;
        this.probabilityScore = probabilityScore;
        this.severity = severity;
        this.severityScore = severityScore;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(SafetyRating self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        output.encodeSerializableElement(serialDesc, 0, HarmCategorySerializer.INSTANCE, self.category);
        output.encodeSerializableElement(serialDesc, 1, HarmProbabilitySerializer.INSTANCE, self.probability);
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.blocked != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, BooleanSerializer.INSTANCE, self.blocked);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.probabilityScore != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, FloatSerializer.INSTANCE, self.probabilityScore);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.severity != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, kSerializerArr[4], self.severity);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.severityScore != null) {
            output.encodeNullableSerializableElement(serialDesc, 5, FloatSerializer.INSTANCE, self.severityScore);
        }
    }

    public /* synthetic */ SafetyRating(HarmCategory harmCategory, HarmProbability harmProbability, Boolean bool, Float f, HarmSeverity harmSeverity, Float f2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(harmCategory, harmProbability, (i & 4) != 0 ? null : bool, (i & 8) != 0 ? null : f, (i & 16) != 0 ? null : harmSeverity, (i & 32) != 0 ? null : f2);
    }

    public final HarmCategory getCategory() {
        return this.category;
    }

    public final HarmProbability getProbability() {
        return this.probability;
    }

    public final Boolean getBlocked() {
        return this.blocked;
    }

    public final Float getProbabilityScore() {
        return this.probabilityScore;
    }

    public final HarmSeverity getSeverity() {
        return this.severity;
    }

    public final Float getSeverityScore() {
        return this.severityScore;
    }
}
