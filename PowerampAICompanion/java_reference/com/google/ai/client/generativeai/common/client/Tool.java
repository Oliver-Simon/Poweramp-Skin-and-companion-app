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
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectSerializer;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B3\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB#\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\fJ\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\bHÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J!\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 HÇ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006#"}, d2 = {"Lcom/google/ai/client/generativeai/common/client/Tool;", "", "seen1", "", "functionDeclarations", "", "Lcom/google/ai/client/generativeai/common/client/FunctionDeclaration;", "codeExecution", "Lkotlinx/serialization/json/JsonObject;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/json/JsonObject;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/util/List;Lkotlinx/serialization/json/JsonObject;)V", "getCodeExecution", "()Lkotlinx/serialization/json/JsonObject;", "getFunctionDeclarations", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class Tool {
    private final JsonObject codeExecution;
    private final List<FunctionDeclaration> functionDeclarations;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {new ArrayListSerializer(FunctionDeclaration$$serializer.INSTANCE), null};

    /* JADX WARN: Multi-variable type inference failed */
    public Tool() {
        this((List) null, (JsonObject) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Tool copy$default(Tool tool, List list, JsonObject jsonObject, int i, Object obj) {
        if ((i & 1) != 0) {
            list = tool.functionDeclarations;
        }
        if ((i & 2) != 0) {
            jsonObject = tool.codeExecution;
        }
        return tool.copy(list, jsonObject);
    }

    public final List<FunctionDeclaration> component1() {
        return this.functionDeclarations;
    }

    /* renamed from: component2, reason: from getter */
    public final JsonObject getCodeExecution() {
        return this.codeExecution;
    }

    public final Tool copy(List<FunctionDeclaration> functionDeclarations, JsonObject codeExecution) {
        return new Tool(functionDeclarations, codeExecution);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Tool)) {
            return false;
        }
        Tool tool = (Tool) other;
        return Intrinsics.areEqual(this.functionDeclarations, tool.functionDeclarations) && Intrinsics.areEqual(this.codeExecution, tool.codeExecution);
    }

    public int hashCode() {
        return ((this.functionDeclarations == null ? 0 : this.functionDeclarations.hashCode()) * 31) + (this.codeExecution != null ? this.codeExecution.hashCode() : 0);
    }

    public String toString() {
        return "Tool(functionDeclarations=" + this.functionDeclarations + ", codeExecution=" + this.codeExecution + ")";
    }

    /* compiled from: Types.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/client/Tool$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/client/Tool;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<Tool> serializer() {
            return Tool$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ Tool(int seen1, List functionDeclarations, JsonObject codeExecution, SerializationConstructorMarker serializationConstructorMarker) {
        if ((seen1 & 1) == 0) {
            this.functionDeclarations = null;
        } else {
            this.functionDeclarations = functionDeclarations;
        }
        if ((seen1 & 2) == 0) {
            this.codeExecution = null;
        } else {
            this.codeExecution = codeExecution;
        }
    }

    public Tool(List<FunctionDeclaration> list, JsonObject codeExecution) {
        this.functionDeclarations = list;
        this.codeExecution = codeExecution;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(Tool self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.functionDeclarations != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, kSerializerArr[0], self.functionDeclarations);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.codeExecution != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, JsonObjectSerializer.INSTANCE, self.codeExecution);
        }
    }

    public /* synthetic */ Tool(List list, JsonObject jsonObject, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : jsonObject);
    }

    public final List<FunctionDeclaration> getFunctionDeclarations() {
        return this.functionDeclarations;
    }

    public final JsonObject getCodeExecution() {
        return this.codeExecution;
    }
}
