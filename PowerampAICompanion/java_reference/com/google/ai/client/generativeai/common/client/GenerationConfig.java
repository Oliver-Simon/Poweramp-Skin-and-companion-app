package com.google.ai.client.generativeai.common.client;

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
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 J2\u00020\u0001:\u0002IJB\u0095\u0001\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0001\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014Bw\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0015J\u0010\u00101\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u000b\u00102\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0019J\u0011\u00107\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\fHÆ\u0003J\u0010\u00109\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u0010:\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u008c\u0001\u0010;\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001¢\u0006\u0002\u0010<J\u0013\u0010=\u001a\u00020>2\b\u0010?\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010@\u001a\u00020\u0003HÖ\u0001J\t\u0010A\u001a\u00020\fHÖ\u0001J!\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u00002\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020HHÇ\u0001R \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001a\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R \u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001e\u0012\u0004\b\u001b\u0010\u0017\u001a\u0004\b\u001c\u0010\u001dR \u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001a\u0012\u0004\b\u001f\u0010\u0017\u001a\u0004\b \u0010\u0019R \u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001e\u0012\u0004\b!\u0010\u0017\u001a\u0004\b\"\u0010\u001dR\u001e\u0010\r\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u0017\u001a\u0004\b$\u0010%R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\u0017\u001a\u0004\b'\u0010(R$\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u0017\u001a\u0004\b*\u0010+R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b,\u0010\u001dR \u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001a\u0012\u0004\b-\u0010\u0017\u001a\u0004\b.\u0010\u0019R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001e\u0012\u0004\b/\u0010\u0017\u001a\u0004\b0\u0010\u001d¨\u0006K"}, d2 = {"Lcom/google/ai/client/generativeai/common/client/GenerationConfig;", "", "seen1", "", "temperature", "", "topP", "topK", "candidateCount", "maxOutputTokens", "stopSequences", "", "", "responseMimeType", "presencePenalty", "frequencyPenalty", "responseSchema", "Lcom/google/ai/client/generativeai/common/client/Schema;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Float;Ljava/lang/Float;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Float;Lcom/google/ai/client/generativeai/common/client/Schema;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Float;Lcom/google/ai/client/generativeai/common/client/Schema;)V", "getCandidateCount$annotations", "()V", "getCandidateCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getFrequencyPenalty$annotations", "getFrequencyPenalty", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getMaxOutputTokens$annotations", "getMaxOutputTokens", "getPresencePenalty$annotations", "getPresencePenalty", "getResponseMimeType$annotations", "getResponseMimeType", "()Ljava/lang/String;", "getResponseSchema$annotations", "getResponseSchema", "()Lcom/google/ai/client/generativeai/common/client/Schema;", "getStopSequences$annotations", "getStopSequences", "()Ljava/util/List;", "getTemperature", "getTopK$annotations", "getTopK", "getTopP$annotations", "getTopP", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Float;Lcom/google/ai/client/generativeai/common/client/Schema;)Lcom/google/ai/client/generativeai/common/client/GenerationConfig;", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class GenerationConfig {
    private final Integer candidateCount;
    private final Float frequencyPenalty;
    private final Integer maxOutputTokens;
    private final Float presencePenalty;
    private final String responseMimeType;
    private final Schema responseSchema;
    private final List<String> stopSequences;
    private final Float temperature;
    private final Integer topK;
    private final Float topP;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, null, null, new ArrayListSerializer(StringSerializer.INSTANCE), null, null, null, null};

    public static /* synthetic */ GenerationConfig copy$default(GenerationConfig generationConfig, Float f, Float f2, Integer num, Integer num2, Integer num3, List list, String str, Float f3, Float f4, Schema schema, int i, Object obj) {
        if ((i & 1) != 0) {
            f = generationConfig.temperature;
        }
        if ((i & 2) != 0) {
            f2 = generationConfig.topP;
        }
        if ((i & 4) != 0) {
            num = generationConfig.topK;
        }
        if ((i & 8) != 0) {
            num2 = generationConfig.candidateCount;
        }
        if ((i & 16) != 0) {
            num3 = generationConfig.maxOutputTokens;
        }
        if ((i & 32) != 0) {
            list = generationConfig.stopSequences;
        }
        if ((i & 64) != 0) {
            str = generationConfig.responseMimeType;
        }
        if ((i & 128) != 0) {
            f3 = generationConfig.presencePenalty;
        }
        if ((i & 256) != 0) {
            f4 = generationConfig.frequencyPenalty;
        }
        if ((i & 512) != 0) {
            schema = generationConfig.responseSchema;
        }
        Float f5 = f4;
        Schema schema2 = schema;
        String str2 = str;
        Float f6 = f3;
        Integer num4 = num3;
        List list2 = list;
        return generationConfig.copy(f, f2, num, num2, num4, list2, str2, f6, f5, schema2);
    }

    @SerialName("candidate_count")
    public static /* synthetic */ void getCandidateCount$annotations() {
    }

    @SerialName("frequency_penalty")
    public static /* synthetic */ void getFrequencyPenalty$annotations() {
    }

    @SerialName("max_output_tokens")
    public static /* synthetic */ void getMaxOutputTokens$annotations() {
    }

    @SerialName("presence_penalty")
    public static /* synthetic */ void getPresencePenalty$annotations() {
    }

    @SerialName("response_mime_type")
    public static /* synthetic */ void getResponseMimeType$annotations() {
    }

    @SerialName("response_schema")
    public static /* synthetic */ void getResponseSchema$annotations() {
    }

    @SerialName("stop_sequences")
    public static /* synthetic */ void getStopSequences$annotations() {
    }

    @SerialName("top_k")
    public static /* synthetic */ void getTopK$annotations() {
    }

    @SerialName("top_p")
    public static /* synthetic */ void getTopP$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final Float getTemperature() {
        return this.temperature;
    }

    /* renamed from: component10, reason: from getter */
    public final Schema getResponseSchema() {
        return this.responseSchema;
    }

    /* renamed from: component2, reason: from getter */
    public final Float getTopP() {
        return this.topP;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getTopK() {
        return this.topK;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getCandidateCount() {
        return this.candidateCount;
    }

    /* renamed from: component5, reason: from getter */
    public final Integer getMaxOutputTokens() {
        return this.maxOutputTokens;
    }

    public final List<String> component6() {
        return this.stopSequences;
    }

    /* renamed from: component7, reason: from getter */
    public final String getResponseMimeType() {
        return this.responseMimeType;
    }

    /* renamed from: component8, reason: from getter */
    public final Float getPresencePenalty() {
        return this.presencePenalty;
    }

    /* renamed from: component9, reason: from getter */
    public final Float getFrequencyPenalty() {
        return this.frequencyPenalty;
    }

    public final GenerationConfig copy(Float temperature, Float topP, Integer topK, Integer candidateCount, Integer maxOutputTokens, List<String> stopSequences, String responseMimeType, Float presencePenalty, Float frequencyPenalty, Schema responseSchema) {
        return new GenerationConfig(temperature, topP, topK, candidateCount, maxOutputTokens, stopSequences, responseMimeType, presencePenalty, frequencyPenalty, responseSchema);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GenerationConfig)) {
            return false;
        }
        GenerationConfig generationConfig = (GenerationConfig) other;
        return Intrinsics.areEqual((Object) this.temperature, (Object) generationConfig.temperature) && Intrinsics.areEqual((Object) this.topP, (Object) generationConfig.topP) && Intrinsics.areEqual(this.topK, generationConfig.topK) && Intrinsics.areEqual(this.candidateCount, generationConfig.candidateCount) && Intrinsics.areEqual(this.maxOutputTokens, generationConfig.maxOutputTokens) && Intrinsics.areEqual(this.stopSequences, generationConfig.stopSequences) && Intrinsics.areEqual(this.responseMimeType, generationConfig.responseMimeType) && Intrinsics.areEqual((Object) this.presencePenalty, (Object) generationConfig.presencePenalty) && Intrinsics.areEqual((Object) this.frequencyPenalty, (Object) generationConfig.frequencyPenalty) && Intrinsics.areEqual(this.responseSchema, generationConfig.responseSchema);
    }

    public int hashCode() {
        return ((((((((((((((((((this.temperature == null ? 0 : this.temperature.hashCode()) * 31) + (this.topP == null ? 0 : this.topP.hashCode())) * 31) + (this.topK == null ? 0 : this.topK.hashCode())) * 31) + (this.candidateCount == null ? 0 : this.candidateCount.hashCode())) * 31) + (this.maxOutputTokens == null ? 0 : this.maxOutputTokens.hashCode())) * 31) + (this.stopSequences == null ? 0 : this.stopSequences.hashCode())) * 31) + (this.responseMimeType == null ? 0 : this.responseMimeType.hashCode())) * 31) + (this.presencePenalty == null ? 0 : this.presencePenalty.hashCode())) * 31) + (this.frequencyPenalty == null ? 0 : this.frequencyPenalty.hashCode())) * 31) + (this.responseSchema != null ? this.responseSchema.hashCode() : 0);
    }

    public String toString() {
        return "GenerationConfig(temperature=" + this.temperature + ", topP=" + this.topP + ", topK=" + this.topK + ", candidateCount=" + this.candidateCount + ", maxOutputTokens=" + this.maxOutputTokens + ", stopSequences=" + this.stopSequences + ", responseMimeType=" + this.responseMimeType + ", presencePenalty=" + this.presencePenalty + ", frequencyPenalty=" + this.frequencyPenalty + ", responseSchema=" + this.responseSchema + ")";
    }

    /* compiled from: Types.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/client/GenerationConfig$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/client/GenerationConfig;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<GenerationConfig> serializer() {
            return GenerationConfig$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ GenerationConfig(int seen1, Float temperature, @SerialName("top_p") Float topP, @SerialName("top_k") Integer topK, @SerialName("candidate_count") Integer candidateCount, @SerialName("max_output_tokens") Integer maxOutputTokens, @SerialName("stop_sequences") List stopSequences, @SerialName("response_mime_type") String responseMimeType, @SerialName("presence_penalty") Float presencePenalty, @SerialName("frequency_penalty") Float frequencyPenalty, @SerialName("response_schema") Schema responseSchema, SerializationConstructorMarker serializationConstructorMarker) {
        if (63 != (seen1 & 63)) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 63, GenerationConfig$$serializer.INSTANCE.getDescriptor());
        }
        this.temperature = temperature;
        this.topP = topP;
        this.topK = topK;
        this.candidateCount = candidateCount;
        this.maxOutputTokens = maxOutputTokens;
        this.stopSequences = stopSequences;
        if ((seen1 & 64) == 0) {
            this.responseMimeType = null;
        } else {
            this.responseMimeType = responseMimeType;
        }
        if ((seen1 & 128) == 0) {
            this.presencePenalty = null;
        } else {
            this.presencePenalty = presencePenalty;
        }
        if ((seen1 & 256) == 0) {
            this.frequencyPenalty = null;
        } else {
            this.frequencyPenalty = frequencyPenalty;
        }
        if ((seen1 & 512) == 0) {
            this.responseSchema = null;
        } else {
            this.responseSchema = responseSchema;
        }
    }

    public GenerationConfig(Float temperature, Float topP, Integer topK, Integer candidateCount, Integer maxOutputTokens, List<String> list, String responseMimeType, Float presencePenalty, Float frequencyPenalty, Schema responseSchema) {
        this.temperature = temperature;
        this.topP = topP;
        this.topK = topK;
        this.candidateCount = candidateCount;
        this.maxOutputTokens = maxOutputTokens;
        this.stopSequences = list;
        this.responseMimeType = responseMimeType;
        this.presencePenalty = presencePenalty;
        this.frequencyPenalty = frequencyPenalty;
        this.responseSchema = responseSchema;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(GenerationConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        output.encodeNullableSerializableElement(serialDesc, 0, FloatSerializer.INSTANCE, self.temperature);
        output.encodeNullableSerializableElement(serialDesc, 1, FloatSerializer.INSTANCE, self.topP);
        output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.topK);
        output.encodeNullableSerializableElement(serialDesc, 3, IntSerializer.INSTANCE, self.candidateCount);
        output.encodeNullableSerializableElement(serialDesc, 4, IntSerializer.INSTANCE, self.maxOutputTokens);
        output.encodeNullableSerializableElement(serialDesc, 5, kSerializerArr[5], self.stopSequences);
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.responseMimeType != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.responseMimeType);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.presencePenalty != null) {
            output.encodeNullableSerializableElement(serialDesc, 7, FloatSerializer.INSTANCE, self.presencePenalty);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.frequencyPenalty != null) {
            output.encodeNullableSerializableElement(serialDesc, 8, FloatSerializer.INSTANCE, self.frequencyPenalty);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 9) || self.responseSchema != null) {
            output.encodeNullableSerializableElement(serialDesc, 9, Schema$$serializer.INSTANCE, self.responseSchema);
        }
    }

    public /* synthetic */ GenerationConfig(Float f, Float f2, Integer num, Integer num2, Integer num3, List list, String str, Float f3, Float f4, Schema schema, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, num, num2, num3, list, (i & 64) != 0 ? null : str, (i & 128) != 0 ? null : f3, (i & 256) != 0 ? null : f4, (i & 512) != 0 ? null : schema);
    }

    public final Float getTemperature() {
        return this.temperature;
    }

    public final Float getTopP() {
        return this.topP;
    }

    public final Integer getTopK() {
        return this.topK;
    }

    public final Integer getCandidateCount() {
        return this.candidateCount;
    }

    public final Integer getMaxOutputTokens() {
        return this.maxOutputTokens;
    }

    public final List<String> getStopSequences() {
        return this.stopSequences;
    }

    public final String getResponseMimeType() {
        return this.responseMimeType;
    }

    public final Float getPresencePenalty() {
        return this.presencePenalty;
    }

    public final Float getFrequencyPenalty() {
        return this.frequencyPenalty;
    }

    public final Schema getResponseSchema() {
        return this.responseSchema;
    }
}
