package com.google.ai.client.generativeai.common.client;

import com.maxmpz.poweramp.player.RouterConsts;
import java.util.List;
import java.util.Map;
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
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 82\u00020\u0001:\u000278B\u0081\u0001\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b\u0012\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0000\u0018\u00010\r\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0000\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012By\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b\u0012\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0000\u0018\u00010\r\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0000¢\u0006\u0002\u0010\u0013J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0011\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000bHÆ\u0003J\u0017\u0010'\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0000\u0018\u00010\rHÆ\u0003J\u0011\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000bHÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0000HÆ\u0003J\u0084\u0001\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b2\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0000\u0018\u00010\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0000HÆ\u0001¢\u0006\u0002\u0010+J\u0013\u0010,\u001a\u00020\t2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020\u0003HÖ\u0001J\t\u0010/\u001a\u00020\u0005HÖ\u0001J!\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00002\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206HÇ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u001f\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0000\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0019\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015¨\u00069"}, d2 = {"Lcom/google/ai/client/generativeai/common/client/Schema;", "", "seen1", "", "type", "", "description", "format", "nullable", "", "enum", "", "properties", "", "required", "items", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;Ljava/util/Map;Ljava/util/List;Lcom/google/ai/client/generativeai/common/client/Schema;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;Ljava/util/Map;Ljava/util/List;Lcom/google/ai/client/generativeai/common/client/Schema;)V", "getDescription", "()Ljava/lang/String;", "getEnum", "()Ljava/util/List;", "getFormat", "getItems", "()Lcom/google/ai/client/generativeai/common/client/Schema;", "getNullable", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getProperties", "()Ljava/util/Map;", "getRequired", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;Ljava/util/Map;Ljava/util/List;Lcom/google/ai/client/generativeai/common/client/Schema;)Lcom/google/ai/client/generativeai/common/client/Schema;", "equals", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class Schema {
    private final String description;
    private final List<String> enum;
    private final String format;
    private final Schema items;
    private final Boolean nullable;
    private final Map<String, Schema> properties;
    private final List<String> required;
    private final String type;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, null, new ArrayListSerializer(StringSerializer.INSTANCE), new LinkedHashMapSerializer(StringSerializer.INSTANCE, Schema$$serializer.INSTANCE), new ArrayListSerializer(StringSerializer.INSTANCE), null};

    public static /* synthetic */ Schema copy$default(Schema schema, String str, String str2, String str3, Boolean bool, List list, Map map, List list2, Schema schema2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = schema.type;
        }
        if ((i & 2) != 0) {
            str2 = schema.description;
        }
        if ((i & 4) != 0) {
            str3 = schema.format;
        }
        if ((i & 8) != 0) {
            bool = schema.nullable;
        }
        if ((i & 16) != 0) {
            list = schema.enum;
        }
        if ((i & 32) != 0) {
            map = schema.properties;
        }
        if ((i & 64) != 0) {
            list2 = schema.required;
        }
        if ((i & 128) != 0) {
            schema2 = schema.items;
        }
        List list3 = list2;
        Schema schema3 = schema2;
        List list4 = list;
        Map map2 = map;
        return schema.copy(str, str2, str3, bool, list4, map2, list3, schema3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFormat() {
        return this.format;
    }

    /* renamed from: component4, reason: from getter */
    public final Boolean getNullable() {
        return this.nullable;
    }

    public final List<String> component5() {
        return this.enum;
    }

    public final Map<String, Schema> component6() {
        return this.properties;
    }

    public final List<String> component7() {
        return this.required;
    }

    /* renamed from: component8, reason: from getter */
    public final Schema getItems() {
        return this.items;
    }

    public final Schema copy(String type, String description, String format, Boolean nullable, List<String> r15, Map<String, Schema> properties, List<String> required, Schema items) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new Schema(type, description, format, nullable, r15, properties, required, items);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Schema)) {
            return false;
        }
        Schema schema = (Schema) other;
        return Intrinsics.areEqual(this.type, schema.type) && Intrinsics.areEqual(this.description, schema.description) && Intrinsics.areEqual(this.format, schema.format) && Intrinsics.areEqual(this.nullable, schema.nullable) && Intrinsics.areEqual(this.enum, schema.enum) && Intrinsics.areEqual(this.properties, schema.properties) && Intrinsics.areEqual(this.required, schema.required) && Intrinsics.areEqual(this.items, schema.items);
    }

    public int hashCode() {
        return (((((((((((((this.type.hashCode() * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.format == null ? 0 : this.format.hashCode())) * 31) + (this.nullable == null ? 0 : this.nullable.hashCode())) * 31) + (this.enum == null ? 0 : this.enum.hashCode())) * 31) + (this.properties == null ? 0 : this.properties.hashCode())) * 31) + (this.required == null ? 0 : this.required.hashCode())) * 31) + (this.items != null ? this.items.hashCode() : 0);
    }

    public String toString() {
        return "Schema(type=" + this.type + ", description=" + this.description + ", format=" + this.format + ", nullable=" + this.nullable + ", enum=" + this.enum + ", properties=" + this.properties + ", required=" + this.required + ", items=" + this.items + ")";
    }

    /* compiled from: Types.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/client/Schema$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/client/Schema;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<Schema> serializer() {
            return Schema$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ Schema(int seen1, String type, String description, String format, Boolean nullable, List list, Map properties, List required, Schema items, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (seen1 & 1)) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 1, Schema$$serializer.INSTANCE.getDescriptor());
        }
        this.type = type;
        if ((seen1 & 2) == 0) {
            this.description = null;
        } else {
            this.description = description;
        }
        if ((seen1 & 4) == 0) {
            this.format = null;
        } else {
            this.format = format;
        }
        if ((seen1 & 8) == 0) {
            this.nullable = false;
        } else {
            this.nullable = nullable;
        }
        if ((seen1 & 16) == 0) {
            this.enum = null;
        } else {
            this.enum = list;
        }
        if ((seen1 & 32) == 0) {
            this.properties = null;
        } else {
            this.properties = properties;
        }
        if ((seen1 & 64) == 0) {
            this.required = null;
        } else {
            this.required = required;
        }
        if ((seen1 & 128) == 0) {
            this.items = null;
        } else {
            this.items = items;
        }
    }

    public Schema(String type, String description, String format, Boolean nullable, List<String> list, Map<String, Schema> map, List<String> list2, Schema items) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
        this.description = description;
        this.format = format;
        this.nullable = nullable;
        this.enum = list;
        this.properties = map;
        this.required = list2;
        this.items = items;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(Schema self, CompositeEncoder output, SerialDescriptor serialDesc) {
        boolean z;
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.type);
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.description != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.description);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.format != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.format);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3)) {
            z = true;
        } else {
            z = !Intrinsics.areEqual((Object) self.nullable, (Object) false);
        }
        if (z) {
            output.encodeNullableSerializableElement(serialDesc, 3, BooleanSerializer.INSTANCE, self.nullable);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.enum != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, kSerializerArr[4], self.enum);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.properties != null) {
            output.encodeNullableSerializableElement(serialDesc, 5, kSerializerArr[5], self.properties);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.required != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, kSerializerArr[6], self.required);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.items != null) {
            output.encodeNullableSerializableElement(serialDesc, 7, Schema$$serializer.INSTANCE, self.items);
        }
    }

    public /* synthetic */ Schema(String str, String str2, String str3, Boolean bool, List list, Map map, List list2, Schema schema, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? false : bool, (i & 16) != 0 ? null : list, (i & 32) != 0 ? null : map, (i & 64) != 0 ? null : list2, (i & 128) != 0 ? null : schema);
    }

    public final String getType() {
        return this.type;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getFormat() {
        return this.format;
    }

    public final Boolean getNullable() {
        return this.nullable;
    }

    public final List<String> getEnum() {
        return this.enum;
    }

    public final Map<String, Schema> getProperties() {
        return this.properties;
    }

    public final List<String> getRequired() {
        return this.required;
    }

    public final Schema getItems() {
        return this.items;
    }
}
