package com.google.ai.client.generativeai.type;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: GenerationConfig.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u0000  2\u00020\u0001:\u0002\u001f Ba\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\f\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011R\u0013\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001d\u0010\u0011R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001e\u0010\u001b¨\u0006!"}, d2 = {"Lcom/google/ai/client/generativeai/type/GenerationConfig;", "", "temperature", "", "topK", "", "topP", "candidateCount", "maxOutputTokens", "stopSequences", "", "", "responseMimeType", "responseSchema", "Lcom/google/ai/client/generativeai/type/Schema;", "(Ljava/lang/Float;Ljava/lang/Integer;Ljava/lang/Float;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Lcom/google/ai/client/generativeai/type/Schema;)V", "getCandidateCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMaxOutputTokens", "getResponseMimeType", "()Ljava/lang/String;", "getResponseSchema", "()Lcom/google/ai/client/generativeai/type/Schema;", "getStopSequences", "()Ljava/util/List;", "getTemperature", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getTopK", "getTopP", "Builder", "Companion", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class GenerationConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Integer candidateCount;
    private final Integer maxOutputTokens;
    private final String responseMimeType;
    private final Schema<?> responseSchema;
    private final List<String> stopSequences;
    private final Float temperature;
    private final Integer topK;
    private final Float topP;

    public /* synthetic */ GenerationConfig(Float f, Integer num, Float f2, Integer num2, Integer num3, List list, String str, Schema schema, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, num, f2, num2, num3, list, str, schema);
    }

    private GenerationConfig(Float temperature, Integer topK, Float topP, Integer candidateCount, Integer maxOutputTokens, List<String> list, String responseMimeType, Schema<?> schema) {
        this.temperature = temperature;
        this.topK = topK;
        this.topP = topP;
        this.candidateCount = candidateCount;
        this.maxOutputTokens = maxOutputTokens;
        this.stopSequences = list;
        this.responseMimeType = responseMimeType;
        this.responseSchema = schema;
    }

    public final Float getTemperature() {
        return this.temperature;
    }

    public final Integer getTopK() {
        return this.topK;
    }

    public final Float getTopP() {
        return this.topP;
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

    public final Schema<?> getResponseSchema() {
        return this.responseSchema;
    }

    /* compiled from: GenerationConfig.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0012\u001a\u00020\u0013R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\f8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0004\n\u0002\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0004\n\u0002\u0010\u000f¨\u0006\u0014"}, d2 = {"Lcom/google/ai/client/generativeai/type/GenerationConfig$Builder;", "", "()V", "candidateCount", "", "Ljava/lang/Integer;", "maxOutputTokens", "responseMimeType", "", "responseSchema", "Lcom/google/ai/client/generativeai/type/Schema;", "stopSequences", "", "temperature", "", "Ljava/lang/Float;", "topK", "topP", "build", "Lcom/google/ai/client/generativeai/type/GenerationConfig;", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Builder {
        public Integer candidateCount;
        public Integer maxOutputTokens;
        public String responseMimeType;
        public Schema<?> responseSchema;
        public List<String> stopSequences;
        public Float temperature;
        public Integer topK;
        public Float topP;

        public final GenerationConfig build() {
            return new GenerationConfig(this.temperature, this.topK, this.topP, this.candidateCount, this.maxOutputTokens, this.stopSequences, this.responseMimeType, this.responseSchema, null);
        }
    }

    /* compiled from: GenerationConfig.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/google/ai/client/generativeai/type/GenerationConfig$Companion;", "", "()V", "builder", "Lcom/google/ai/client/generativeai/type/GenerationConfig$Builder;", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Builder builder() {
            return new Builder();
        }
    }
}
