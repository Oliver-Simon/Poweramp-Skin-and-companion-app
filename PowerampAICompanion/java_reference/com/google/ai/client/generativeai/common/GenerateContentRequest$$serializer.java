package com.google.ai.client.generativeai.common;

import com.google.ai.client.generativeai.common.client.GenerationConfig;
import com.google.ai.client.generativeai.common.client.GenerationConfig$$serializer;
import com.google.ai.client.generativeai.common.client.ToolConfig;
import com.google.ai.client.generativeai.common.client.ToolConfig$$serializer;
import com.google.ai.client.generativeai.common.shared.Content;
import com.google.ai.client.generativeai.common.shared.Content$$serializer;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: Request.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/google/ai/client/generativeai/common/GenerateContentRequest.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/google/ai/client/generativeai/common/GenerateContentRequest;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes.dex */
public final class GenerateContentRequest$$serializer implements GeneratedSerializer<GenerateContentRequest> {
    public static final GenerateContentRequest$$serializer INSTANCE = new GenerateContentRequest$$serializer();
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.google.ai.client.generativeai.common.GenerateContentRequest", INSTANCE, 7);
        pluginGeneratedSerialDescriptor.addElement("model", true);
        pluginGeneratedSerialDescriptor.addElement("contents", false);
        pluginGeneratedSerialDescriptor.addElement("safety_settings", true);
        pluginGeneratedSerialDescriptor.addElement("generation_config", true);
        pluginGeneratedSerialDescriptor.addElement("tools", true);
        pluginGeneratedSerialDescriptor.addElement("tool_config", true);
        pluginGeneratedSerialDescriptor.addElement("system_instruction", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private GenerateContentRequest$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        KSerializer<?>[] kSerializerArr;
        kSerializerArr = GenerateContentRequest.$childSerializers;
        return new KSerializer[]{BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), kSerializerArr[1], BuiltinSerializersKt.getNullable(kSerializerArr[2]), BuiltinSerializersKt.getNullable(GenerationConfig$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(kSerializerArr[4]), BuiltinSerializersKt.getNullable(ToolConfig$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(Content$$serializer.INSTANCE)};
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0072. Please report as an issue. */
    @Override // kotlinx.serialization.DeserializationStrategy
    public GenerateContentRequest deserialize(Decoder decoder) {
        KSerializer[] kSerializerArr;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        int i;
        Object obj7;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder beginStructure = decoder.beginStructure(descriptor2);
        kSerializerArr = GenerateContentRequest.$childSerializers;
        int i2 = 6;
        Object obj8 = null;
        if (beginStructure.decodeSequentially()) {
            obj7 = beginStructure.decodeNullableSerializableElement(descriptor2, 0, StringSerializer.INSTANCE, null);
            obj5 = beginStructure.decodeSerializableElement(descriptor2, 1, kSerializerArr[1], null);
            Object decodeNullableSerializableElement = beginStructure.decodeNullableSerializableElement(descriptor2, 2, kSerializerArr[2], null);
            obj6 = beginStructure.decodeNullableSerializableElement(descriptor2, 3, GenerationConfig$$serializer.INSTANCE, null);
            obj4 = beginStructure.decodeNullableSerializableElement(descriptor2, 4, kSerializerArr[4], null);
            obj3 = beginStructure.decodeNullableSerializableElement(descriptor2, 5, ToolConfig$$serializer.INSTANCE, null);
            obj2 = beginStructure.decodeNullableSerializableElement(descriptor2, 6, Content$$serializer.INSTANCE, null);
            obj = decodeNullableSerializableElement;
            i = 127;
        } else {
            int i3 = 2;
            int i4 = 1;
            boolean z = true;
            int i5 = 0;
            Object obj9 = null;
            Object obj10 = null;
            Object obj11 = null;
            Object obj12 = null;
            obj = null;
            Object obj13 = null;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(descriptor2);
                switch (decodeElementIndex) {
                    case -1:
                        z = false;
                        i2 = 6;
                        i4 = 1;
                        i3 = 2;
                    case 0:
                        obj8 = beginStructure.decodeNullableSerializableElement(descriptor2, 0, StringSerializer.INSTANCE, obj8);
                        i5 |= 1;
                        i2 = 6;
                        i4 = 1;
                        i3 = 2;
                    case 1:
                        obj12 = beginStructure.decodeSerializableElement(descriptor2, i4, kSerializerArr[i4], obj12);
                        i5 |= 2;
                        i2 = 6;
                        i3 = 2;
                    case 2:
                        obj = beginStructure.decodeNullableSerializableElement(descriptor2, i3, kSerializerArr[i3], obj);
                        i5 |= 4;
                        i2 = 6;
                    case 3:
                        obj13 = beginStructure.decodeNullableSerializableElement(descriptor2, 3, GenerationConfig$$serializer.INSTANCE, obj13);
                        i5 |= 8;
                        i2 = 6;
                    case 4:
                        obj11 = beginStructure.decodeNullableSerializableElement(descriptor2, 4, kSerializerArr[4], obj11);
                        i5 |= 16;
                    case 5:
                        obj10 = beginStructure.decodeNullableSerializableElement(descriptor2, 5, ToolConfig$$serializer.INSTANCE, obj10);
                        i5 |= 32;
                    case 6:
                        obj9 = beginStructure.decodeNullableSerializableElement(descriptor2, i2, Content$$serializer.INSTANCE, obj9);
                        i5 |= 64;
                    default:
                        throw new UnknownFieldException(decodeElementIndex);
                }
            }
            obj2 = obj9;
            obj3 = obj10;
            obj4 = obj11;
            obj5 = obj12;
            obj6 = obj13;
            i = i5;
            obj7 = obj8;
        }
        beginStructure.endStructure(descriptor2);
        return new GenerateContentRequest(i, (String) obj7, (List) obj5, (List) obj, (GenerationConfig) obj6, (List) obj4, (ToolConfig) obj3, (Content) obj2, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, GenerateContentRequest value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        GenerateContentRequest.write$Self(value, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
