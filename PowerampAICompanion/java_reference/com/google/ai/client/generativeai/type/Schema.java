package com.google.ai.client.generativeai.type;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.json.JSONObject;

/* compiled from: FunctionDeclarations.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\u0018\u0000 %*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001%B\u0093\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n\u0012\u001e\b\u0002\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0000\u0018\u00010\f\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n\u0012\u0012\b\u0002\u0010\u000e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\u0000\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010¢\u0006\u0002\u0010\u0011J\u0017\u0010\"\u001a\u0004\u0018\u00018\u00002\b\u0010#\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010$R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u001b\u0010\u000e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR'\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0000\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u0006&"}, d2 = {"Lcom/google/ai/client/generativeai/type/Schema;", ExifInterface.GPS_DIRECTION_TRUE, "", "name", "", "description", "format", "nullable", "", "enum", "", "properties", "", "required", "items", "type", "Lcom/google/ai/client/generativeai/type/FunctionType;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;Ljava/util/Map;Ljava/util/List;Lcom/google/ai/client/generativeai/type/Schema;Lcom/google/ai/client/generativeai/type/FunctionType;)V", "getDescription", "()Ljava/lang/String;", "getEnum", "()Ljava/util/List;", "getFormat", "getItems", "()Lcom/google/ai/client/generativeai/type/Schema;", "getName", "getNullable", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getProperties", "()Ljava/util/Map;", "getRequired", "getType", "()Lcom/google/ai/client/generativeai/type/FunctionType;", "fromString", "value", "(Ljava/lang/String;)Ljava/lang/Object;", "Companion", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Schema<T> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String description;
    private final List<String> enum;
    private final String format;
    private final Schema<? extends Object> items;
    private final String name;
    private final Boolean nullable;
    private final Map<String, Schema<? extends Object>> properties;
    private final List<String> required;
    private final FunctionType<T> type;

    /* JADX WARN: Multi-variable type inference failed */
    public Schema(String name, String description, String format, Boolean nullable, List<String> list, Map<String, ? extends Schema<? extends Object>> map, List<String> list2, Schema<? extends Object> schema, FunctionType<T> type) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(type, "type");
        this.name = name;
        this.description = description;
        this.format = format;
        this.nullable = nullable;
        this.enum = list;
        this.properties = map;
        this.required = list2;
        this.items = schema;
        this.type = type;
    }

    public /* synthetic */ Schema(String str, String str2, String str3, Boolean bool, List list, Map map, List list2, Schema schema, FunctionType functionType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : bool, (i & 16) != 0 ? null : list, (i & 32) != 0 ? null : map, (i & 64) != 0 ? null : list2, (i & 128) != 0 ? null : schema, functionType);
    }

    public final String getName() {
        return this.name;
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

    public final Map<String, Schema<? extends Object>> getProperties() {
        return this.properties;
    }

    public final List<String> getRequired() {
        return this.required;
    }

    public final Schema<? extends Object> getItems() {
        return this.items;
    }

    public final FunctionType<T> getType() {
        return this.type;
    }

    public final T fromString(String value) {
        return this.type.getParse().invoke(value);
    }

    /* compiled from: FunctionDeclarations.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0012\b\u0002\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0001\u0018\u00010\u0004J\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J*\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006JE\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\"\u0010\u0016\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00040\u0017\"\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0004¢\u0006\u0002\u0010\u0018J\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006¨\u0006\u001a"}, d2 = {"Lcom/google/ai/client/generativeai/type/Schema$Companion;", "", "()V", "arr", "Lcom/google/ai/client/generativeai/type/Schema;", "", "", "name", "description", "items", "bool", "", "double", "", "enum", "values", "int", "", "long", "", "obj", "Lorg/json/JSONObject;", "contents", "", "(Ljava/lang/String;Ljava/lang/String;[Lcom/google/ai/client/generativeai/type/Schema;)Lcom/google/ai/client/generativeai/type/Schema;", "str", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* renamed from: int, reason: not valid java name */
        public final Schema<Integer> m87int(String name, String description) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(description, "description");
            return new Schema<>(name, description, "int32", false, null, null, null, null, FunctionType.INSTANCE.getINTEGER(), 240, null);
        }

        /* renamed from: long, reason: not valid java name */
        public final Schema<Long> m88long(String name, String description) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(description, "description");
            return new Schema<>(name, description, null, false, null, null, null, null, FunctionType.INSTANCE.getLONG(), 244, null);
        }

        public final Schema<String> str(String name, String description) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(description, "description");
            return new Schema<>(name, description, null, false, null, null, null, null, FunctionType.INSTANCE.getSTRING(), 244, null);
        }

        public final Schema<Boolean> bool(String name, String description) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(description, "description");
            return new Schema<>(name, description, null, false, null, null, null, null, FunctionType.INSTANCE.getBOOLEAN(), 244, null);
        }

        /* renamed from: double, reason: not valid java name */
        public final Schema<Double> m85double(String name, String description) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(description, "description");
            return new Schema<>(name, description, null, false, null, null, null, null, FunctionType.INSTANCE.getNUMBER(), 244, null);
        }

        public final Schema<JSONObject> obj(String name, String description, Schema<? extends Object>... contents) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(contents, "contents");
            FunctionType<JSONObject> object = FunctionType.INSTANCE.getOBJECT();
            Collection destination$iv$iv = new ArrayList(contents.length);
            for (Schema<? extends Object> schema : contents) {
                destination$iv$iv.add(schema.getName());
            }
            ArrayList arrayList = (List) destination$iv$iv;
            int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(contents.length), 16);
            Map destination$iv$iv2 = new LinkedHashMap(capacity$iv);
            for (Schema<? extends Object> schema2 : contents) {
                destination$iv$iv2.put(schema2.getName(), schema2);
            }
            return new Schema<>(name, description, null, false, null, MapsKt.toMap(destination$iv$iv2), arrayList, null, object, 148, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Schema arr$default(Companion companion, String str, String str2, Schema schema, int i, Object obj) {
            if ((i & 4) != 0) {
                schema = null;
            }
            return companion.arr(str, str2, schema);
        }

        public final Schema<List<String>> arr(String name, String description, Schema<? extends Object> items) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(description, "description");
            return new Schema<>(name, description, null, false, null, null, null, items, FunctionType.INSTANCE.getARRAY(), 116, null);
        }

        /* renamed from: enum, reason: not valid java name */
        public final Schema<String> m86enum(String name, String description, List<String> values) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(values, "values");
            return new Schema<>(name, description, "enum", false, values, null, null, null, FunctionType.INSTANCE.getSTRING(), 224, null);
        }
    }
}
