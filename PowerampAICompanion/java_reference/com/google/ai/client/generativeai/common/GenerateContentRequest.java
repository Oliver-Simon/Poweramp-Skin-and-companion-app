package com.google.ai.client.generativeai.common;

import com.google.ai.client.generativeai.common.client.GenerationConfig;
import com.google.ai.client.generativeai.common.client.GenerationConfig$$serializer;
import com.google.ai.client.generativeai.common.client.Tool;
import com.google.ai.client.generativeai.common.client.Tool$$serializer;
import com.google.ai.client.generativeai.common.client.ToolConfig;
import com.google.ai.client.generativeai.common.client.ToolConfig$$serializer;
import com.google.ai.client.generativeai.common.shared.Content;
import com.google.ai.client.generativeai.common.shared.Content$$serializer;
import com.google.ai.client.generativeai.common.shared.SafetySetting;
import com.google.ai.client.generativeai.common.shared.SafetySetting$$serializer;
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
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: Request.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 ?2\u00020\u0001:\u0002>?By\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0010\b\u0001\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014Bg\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0015J\u000b\u0010)\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u0011\u0010+\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\fHÆ\u0003J\u0011\u0010-\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0007HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\bHÆ\u0003Jm\u00100\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u000104HÖ\u0003J\t\u00105\u001a\u00020\u0003HÖ\u0001J\t\u00106\u001a\u00020\u0005HÖ\u0001J!\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00002\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=HÇ\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR$\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0019\u001a\u0004\b\u001f\u0010\u0017R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0019\u001a\u0004\b!\u0010\"R&\u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b#\u0010\u0019\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0017¨\u0006@"}, d2 = {"Lcom/google/ai/client/generativeai/common/GenerateContentRequest;", "Lcom/google/ai/client/generativeai/common/Request;", "seen1", "", "model", "", "contents", "", "Lcom/google/ai/client/generativeai/common/shared/Content;", "safetySettings", "Lcom/google/ai/client/generativeai/common/shared/SafetySetting;", "generationConfig", "Lcom/google/ai/client/generativeai/common/client/GenerationConfig;", "tools", "Lcom/google/ai/client/generativeai/common/client/Tool;", "toolConfig", "Lcom/google/ai/client/generativeai/common/client/ToolConfig;", "systemInstruction", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/google/ai/client/generativeai/common/client/GenerationConfig;Ljava/util/List;Lcom/google/ai/client/generativeai/common/client/ToolConfig;Lcom/google/ai/client/generativeai/common/shared/Content;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/google/ai/client/generativeai/common/client/GenerationConfig;Ljava/util/List;Lcom/google/ai/client/generativeai/common/client/ToolConfig;Lcom/google/ai/client/generativeai/common/shared/Content;)V", "getContents", "()Ljava/util/List;", "getGenerationConfig$annotations", "()V", "getGenerationConfig", "()Lcom/google/ai/client/generativeai/common/client/GenerationConfig;", "getModel", "()Ljava/lang/String;", "getSafetySettings$annotations", "getSafetySettings", "getSystemInstruction$annotations", "getSystemInstruction", "()Lcom/google/ai/client/generativeai/common/shared/Content;", "getToolConfig$annotations", "getToolConfig", "()Lcom/google/ai/client/generativeai/common/client/ToolConfig;", "setToolConfig", "(Lcom/google/ai/client/generativeai/common/client/ToolConfig;)V", "getTools", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class GenerateContentRequest implements Request {
    private final List<Content> contents;
    private final GenerationConfig generationConfig;
    private final String model;
    private final List<SafetySetting> safetySettings;
    private final Content systemInstruction;
    private ToolConfig toolConfig;
    private final List<Tool> tools;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {null, new ArrayListSerializer(Content$$serializer.INSTANCE), new ArrayListSerializer(SafetySetting$$serializer.INSTANCE), null, new ArrayListSerializer(Tool$$serializer.INSTANCE), null, null};

    public static /* synthetic */ GenerateContentRequest copy$default(GenerateContentRequest generateContentRequest, String str, List list, List list2, GenerationConfig generationConfig, List list3, ToolConfig toolConfig, Content content, int i, Object obj) {
        if ((i & 1) != 0) {
            str = generateContentRequest.model;
        }
        if ((i & 2) != 0) {
            list = generateContentRequest.contents;
        }
        if ((i & 4) != 0) {
            list2 = generateContentRequest.safetySettings;
        }
        if ((i & 8) != 0) {
            generationConfig = generateContentRequest.generationConfig;
        }
        if ((i & 16) != 0) {
            list3 = generateContentRequest.tools;
        }
        if ((i & 32) != 0) {
            toolConfig = generateContentRequest.toolConfig;
        }
        if ((i & 64) != 0) {
            content = generateContentRequest.systemInstruction;
        }
        ToolConfig toolConfig2 = toolConfig;
        Content content2 = content;
        List list4 = list3;
        List list5 = list2;
        return generateContentRequest.copy(str, list, list5, generationConfig, list4, toolConfig2, content2);
    }

    @SerialName("generation_config")
    public static /* synthetic */ void getGenerationConfig$annotations() {
    }

    @SerialName("safety_settings")
    public static /* synthetic */ void getSafetySettings$annotations() {
    }

    @SerialName("system_instruction")
    public static /* synthetic */ void getSystemInstruction$annotations() {
    }

    @SerialName("tool_config")
    public static /* synthetic */ void getToolConfig$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getModel() {
        return this.model;
    }

    public final List<Content> component2() {
        return this.contents;
    }

    public final List<SafetySetting> component3() {
        return this.safetySettings;
    }

    /* renamed from: component4, reason: from getter */
    public final GenerationConfig getGenerationConfig() {
        return this.generationConfig;
    }

    public final List<Tool> component5() {
        return this.tools;
    }

    /* renamed from: component6, reason: from getter */
    public final ToolConfig getToolConfig() {
        return this.toolConfig;
    }

    /* renamed from: component7, reason: from getter */
    public final Content getSystemInstruction() {
        return this.systemInstruction;
    }

    public final GenerateContentRequest copy(String model, List<Content> contents, List<SafetySetting> safetySettings, GenerationConfig generationConfig, List<Tool> tools, ToolConfig toolConfig, Content systemInstruction) {
        Intrinsics.checkNotNullParameter(contents, "contents");
        return new GenerateContentRequest(model, contents, safetySettings, generationConfig, tools, toolConfig, systemInstruction);
    }

    public boolean equals(Object r6) {
        if (this == r6) {
            return true;
        }
        if (!(r6 instanceof GenerateContentRequest)) {
            return false;
        }
        GenerateContentRequest generateContentRequest = (GenerateContentRequest) r6;
        return Intrinsics.areEqual(this.model, generateContentRequest.model) && Intrinsics.areEqual(this.contents, generateContentRequest.contents) && Intrinsics.areEqual(this.safetySettings, generateContentRequest.safetySettings) && Intrinsics.areEqual(this.generationConfig, generateContentRequest.generationConfig) && Intrinsics.areEqual(this.tools, generateContentRequest.tools) && Intrinsics.areEqual(this.toolConfig, generateContentRequest.toolConfig) && Intrinsics.areEqual(this.systemInstruction, generateContentRequest.systemInstruction);
    }

    public int hashCode() {
        return ((((((((((((this.model == null ? 0 : this.model.hashCode()) * 31) + this.contents.hashCode()) * 31) + (this.safetySettings == null ? 0 : this.safetySettings.hashCode())) * 31) + (this.generationConfig == null ? 0 : this.generationConfig.hashCode())) * 31) + (this.tools == null ? 0 : this.tools.hashCode())) * 31) + (this.toolConfig == null ? 0 : this.toolConfig.hashCode())) * 31) + (this.systemInstruction != null ? this.systemInstruction.hashCode() : 0);
    }

    public String toString() {
        return "GenerateContentRequest(model=" + this.model + ", contents=" + this.contents + ", safetySettings=" + this.safetySettings + ", generationConfig=" + this.generationConfig + ", tools=" + this.tools + ", toolConfig=" + this.toolConfig + ", systemInstruction=" + this.systemInstruction + ")";
    }

    /* compiled from: Request.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/GenerateContentRequest$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/GenerateContentRequest;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<GenerateContentRequest> serializer() {
            return GenerateContentRequest$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ GenerateContentRequest(int seen1, String model, List contents, @SerialName("safety_settings") List safetySettings, @SerialName("generation_config") GenerationConfig generationConfig, List tools, @SerialName("tool_config") ToolConfig toolConfig, @SerialName("system_instruction") Content systemInstruction, SerializationConstructorMarker serializationConstructorMarker) {
        if (2 != (seen1 & 2)) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 2, GenerateContentRequest$$serializer.INSTANCE.getDescriptor());
        }
        if ((seen1 & 1) == 0) {
            this.model = null;
        } else {
            this.model = model;
        }
        this.contents = contents;
        if ((seen1 & 4) == 0) {
            this.safetySettings = null;
        } else {
            this.safetySettings = safetySettings;
        }
        if ((seen1 & 8) == 0) {
            this.generationConfig = null;
        } else {
            this.generationConfig = generationConfig;
        }
        if ((seen1 & 16) == 0) {
            this.tools = null;
        } else {
            this.tools = tools;
        }
        if ((seen1 & 32) == 0) {
            this.toolConfig = null;
        } else {
            this.toolConfig = toolConfig;
        }
        if ((seen1 & 64) == 0) {
            this.systemInstruction = null;
        } else {
            this.systemInstruction = systemInstruction;
        }
    }

    public GenerateContentRequest(String model, List<Content> contents, List<SafetySetting> list, GenerationConfig generationConfig, List<Tool> list2, ToolConfig toolConfig, Content systemInstruction) {
        Intrinsics.checkNotNullParameter(contents, "contents");
        this.model = model;
        this.contents = contents;
        this.safetySettings = list;
        this.generationConfig = generationConfig;
        this.tools = list2;
        this.toolConfig = toolConfig;
        this.systemInstruction = systemInstruction;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(GenerateContentRequest self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.model != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.model);
        }
        output.encodeSerializableElement(serialDesc, 1, kSerializerArr[1], self.contents);
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.safetySettings != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, kSerializerArr[2], self.safetySettings);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.generationConfig != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, GenerationConfig$$serializer.INSTANCE, self.generationConfig);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.tools != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, kSerializerArr[4], self.tools);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.toolConfig != null) {
            output.encodeNullableSerializableElement(serialDesc, 5, ToolConfig$$serializer.INSTANCE, self.toolConfig);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.systemInstruction != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, Content$$serializer.INSTANCE, self.systemInstruction);
        }
    }

    public /* synthetic */ GenerateContentRequest(String str, List list, List list2, GenerationConfig generationConfig, List list3, ToolConfig toolConfig, Content content, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, list, (i & 4) != 0 ? null : list2, (i & 8) != 0 ? null : generationConfig, (i & 16) != 0 ? null : list3, (i & 32) != 0 ? null : toolConfig, (i & 64) != 0 ? null : content);
    }

    public final String getModel() {
        return this.model;
    }

    public final List<Content> getContents() {
        return this.contents;
    }

    public final List<SafetySetting> getSafetySettings() {
        return this.safetySettings;
    }

    public final GenerationConfig getGenerationConfig() {
        return this.generationConfig;
    }

    public final List<Tool> getTools() {
        return this.tools;
    }

    public final ToolConfig getToolConfig() {
        return this.toolConfig;
    }

    public final void setToolConfig(ToolConfig toolConfig) {
        this.toolConfig = toolConfig;
    }

    public final Content getSystemInstruction() {
        return this.systemInstruction;
    }
}
