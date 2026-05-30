package com.google.ai.client.generativeai.common.shared;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.json.JsonContentPolymorphicSerializer;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0014¨\u0006\b"}, d2 = {"Lcom/google/ai/client/generativeai/common/shared/PartSerializer;", "Lkotlinx/serialization/json/JsonContentPolymorphicSerializer;", "Lcom/google/ai/client/generativeai/common/shared/Part;", "()V", "selectDeserializer", "Lkotlinx/serialization/DeserializationStrategy;", "element", "Lkotlinx/serialization/json/JsonElement;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PartSerializer extends JsonContentPolymorphicSerializer<Part> {
    public static final PartSerializer INSTANCE = new PartSerializer();

    private PartSerializer() {
        super(Reflection.getOrCreateKotlinClass(Part.class));
    }

    @Override // kotlinx.serialization.json.JsonContentPolymorphicSerializer
    protected DeserializationStrategy<Part> selectDeserializer(JsonElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        JsonObject jsonObject = JsonElementKt.getJsonObject(element);
        if (jsonObject.containsKey((Object) "text")) {
            return TextPart.INSTANCE.serializer();
        }
        if (jsonObject.containsKey((Object) "functionCall")) {
            return FunctionCallPart.INSTANCE.serializer();
        }
        if (jsonObject.containsKey((Object) "functionResponse")) {
            return FunctionResponsePart.INSTANCE.serializer();
        }
        if (jsonObject.containsKey((Object) "inlineData")) {
            return BlobPart.INSTANCE.serializer();
        }
        if (jsonObject.containsKey((Object) "fileData")) {
            return FileDataPart.INSTANCE.serializer();
        }
        if (jsonObject.containsKey((Object) "executableCode")) {
            return ExecutableCodePart.INSTANCE.serializer();
        }
        if (jsonObject.containsKey((Object) "codeExecutionResult")) {
            return CodeExecutionResultPart.INSTANCE.serializer();
        }
        throw new SerializationException("Unknown Part type");
    }
}
