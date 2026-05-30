package com.google.ai.client.generativeai.common.client;

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
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/google/ai/client/generativeai/common/client/GenerationConfig.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/google/ai/client/generativeai/common/client/GenerationConfig;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* loaded from: classes.dex */
public final class GenerationConfig$$serializer implements GeneratedSerializer<GenerationConfig> {
    public static final GenerationConfig$$serializer INSTANCE = new GenerationConfig$$serializer();
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.google.ai.client.generativeai.common.client.GenerationConfig", INSTANCE, 10);
        pluginGeneratedSerialDescriptor.addElement("temperature", false);
        pluginGeneratedSerialDescriptor.addElement("top_p", false);
        pluginGeneratedSerialDescriptor.addElement("top_k", false);
        pluginGeneratedSerialDescriptor.addElement("candidate_count", false);
        pluginGeneratedSerialDescriptor.addElement("max_output_tokens", false);
        pluginGeneratedSerialDescriptor.addElement("stop_sequences", false);
        pluginGeneratedSerialDescriptor.addElement("response_mime_type", true);
        pluginGeneratedSerialDescriptor.addElement("presence_penalty", true);
        pluginGeneratedSerialDescriptor.addElement("frequency_penalty", true);
        pluginGeneratedSerialDescriptor.addElement("response_schema", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private GenerationConfig$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        KSerializer[] kSerializerArr;
        kSerializerArr = GenerationConfig.$childSerializers;
        return new KSerializer[]{BuiltinSerializersKt.getNullable(FloatSerializer.INSTANCE), BuiltinSerializersKt.getNullable(FloatSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(kSerializerArr[5]), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(FloatSerializer.INSTANCE), BuiltinSerializersKt.getNullable(FloatSerializer.INSTANCE), BuiltinSerializersKt.getNullable(Schema$$serializer.INSTANCE)};
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0090. Please report as an issue. */
    @Override // kotlinx.serialization.DeserializationStrategy
    public GenerationConfig deserialize(Decoder decoder) {
        KSerializer[] kSerializerArr;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        int i;
        Object obj10;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder beginStructure = decoder.beginStructure(descriptor2);
        kSerializerArr = GenerationConfig.$childSerializers;
        Object obj11 = null;
        if (beginStructure.decodeSequentially()) {
            obj10 = beginStructure.decodeNullableSerializableElement(descriptor2, 0, FloatSerializer.INSTANCE, null);
            Object decodeNullableSerializableElement = beginStructure.decodeNullableSerializableElement(descriptor2, 1, FloatSerializer.INSTANCE, null);
            obj9 = beginStructure.decodeNullableSerializableElement(descriptor2, 2, IntSerializer.INSTANCE, null);
            obj7 = beginStructure.decodeNullableSerializableElement(descriptor2, 3, IntSerializer.INSTANCE, null);
            obj6 = beginStructure.decodeNullableSerializableElement(descriptor2, 4, IntSerializer.INSTANCE, null);
            obj3 = beginStructure.decodeNullableSerializableElement(descriptor2, 5, kSerializerArr[5], null);
            obj8 = beginStructure.decodeNullableSerializableElement(descriptor2, 6, StringSerializer.INSTANCE, null);
            obj5 = beginStructure.decodeNullableSerializableElement(descriptor2, 7, FloatSerializer.INSTANCE, null);
            obj4 = beginStructure.decodeNullableSerializableElement(descriptor2, 8, FloatSerializer.INSTANCE, null);
            obj2 = beginStructure.decodeNullableSerializableElement(descriptor2, 9, Schema$$serializer.INSTANCE, null);
            obj = decodeNullableSerializableElement;
            i = 1023;
        } else {
            int i2 = 5;
            boolean z = true;
            int i3 = 0;
            Object obj12 = null;
            Object obj13 = null;
            Object obj14 = null;
            Object obj15 = null;
            Object obj16 = null;
            Object obj17 = null;
            Object obj18 = null;
            Object obj19 = null;
            obj = null;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(descriptor2);
                switch (decodeElementIndex) {
                    case -1:
                        z = false;
                        i2 = 5;
                    case 0:
                        obj11 = beginStructure.decodeNullableSerializableElement(descriptor2, 0, FloatSerializer.INSTANCE, obj11);
                        i3 |= 1;
                        i2 = 5;
                    case 1:
                        obj = beginStructure.decodeNullableSerializableElement(descriptor2, 1, FloatSerializer.INSTANCE, obj);
                        i3 |= 2;
                        i2 = 5;
                    case 2:
                        obj19 = beginStructure.decodeNullableSerializableElement(descriptor2, 2, IntSerializer.INSTANCE, obj19);
                        i3 |= 4;
                        i2 = 5;
                    case 3:
                        obj17 = beginStructure.decodeNullableSerializableElement(descriptor2, 3, IntSerializer.INSTANCE, obj17);
                        i3 |= 8;
                        i2 = 5;
                    case 4:
                        obj14 = beginStructure.decodeNullableSerializableElement(descriptor2, 4, IntSerializer.INSTANCE, obj14);
                        i3 |= 16;
                        i2 = 5;
                    case 5:
                        obj16 = beginStructure.decodeNullableSerializableElement(descriptor2, i2, kSerializerArr[i2], obj16);
                        i3 |= 32;
                    case 6:
                        obj18 = beginStructure.decodeNullableSerializableElement(descriptor2, 6, StringSerializer.INSTANCE, obj18);
                        i3 |= 64;
                    case 7:
                        obj13 = beginStructure.decodeNullableSerializableElement(descriptor2, 7, FloatSerializer.INSTANCE, obj13);
                        i3 |= 128;
                    case 8:
                        obj12 = beginStructure.decodeNullableSerializableElement(descriptor2, 8, FloatSerializer.INSTANCE, obj12);
                        i3 |= 256;
                    case 9:
                        obj15 = beginStructure.decodeNullableSerializableElement(descriptor2, 9, Schema$$serializer.INSTANCE, obj15);
                        i3 |= 512;
                    default:
                        throw new UnknownFieldException(decodeElementIndex);
                }
            }
            obj2 = obj15;
            obj3 = obj16;
            obj4 = obj12;
            obj5 = obj13;
            obj6 = obj14;
            obj7 = obj17;
            obj8 = obj18;
            obj9 = obj19;
            i = i3;
            obj10 = obj11;
        }
        beginStructure.endStructure(descriptor2);
        return new GenerationConfig(i, (Float) obj10, (Float) obj, (Integer) obj9, (Integer) obj7, (Integer) obj6, (List) obj3, (String) obj8, (Float) obj5, (Float) obj4, (Schema) obj2, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, GenerationConfig value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        GenerationConfig.write$Self(value, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
