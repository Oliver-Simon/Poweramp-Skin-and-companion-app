package com.google.ai.client.generativeai.type;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import org.json.JSONObject;

/* compiled from: Type.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \f*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\fB%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR!\u0010\u0005\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/google/ai/client/generativeai/type/FunctionType;", ExifInterface.GPS_DIRECTION_TRUE, "", "name", "", "parse", "Lkotlin/Function1;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getName", "()Ljava/lang/String;", "getParse", "()Lkotlin/jvm/functions/Function1;", "Companion", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FunctionType<T> {
    private final String name;
    private final Function1<String, T> parse;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final FunctionType<String> STRING = new FunctionType<>("STRING", new Function1<String, String>() { // from class: com.google.ai.client.generativeai.type.FunctionType$Companion$STRING$1
        @Override // kotlin.jvm.functions.Function1
        public final String invoke(String it) {
            return it;
        }
    });
    private static final FunctionType<Integer> INTEGER = new FunctionType<>("INTEGER", new Function1<String, Integer>() { // from class: com.google.ai.client.generativeai.type.FunctionType$Companion$INTEGER$1
        @Override // kotlin.jvm.functions.Function1
        public final Integer invoke(String it) {
            if (it != null) {
                return StringsKt.toIntOrNull(it);
            }
            return null;
        }
    });
    private static final FunctionType<Long> LONG = new FunctionType<>("INTEGER", new Function1<String, Long>() { // from class: com.google.ai.client.generativeai.type.FunctionType$Companion$LONG$1
        @Override // kotlin.jvm.functions.Function1
        public final Long invoke(String it) {
            if (it != null) {
                return StringsKt.toLongOrNull(it);
            }
            return null;
        }
    });
    private static final FunctionType<Double> NUMBER = new FunctionType<>("NUMBER", new Function1<String, Double>() { // from class: com.google.ai.client.generativeai.type.FunctionType$Companion$NUMBER$1
        @Override // kotlin.jvm.functions.Function1
        public final Double invoke(String it) {
            if (it != null) {
                return StringsKt.toDoubleOrNull(it);
            }
            return null;
        }
    });
    private static final FunctionType<Boolean> BOOLEAN = new FunctionType<>("BOOLEAN", new Function1<String, Boolean>() { // from class: com.google.ai.client.generativeai.type.FunctionType$Companion$BOOLEAN$1
        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(String it) {
            if (it != null) {
                return Boolean.valueOf(Boolean.parseBoolean(it));
            }
            return null;
        }
    });
    private static final FunctionType<List<String>> ARRAY = new FunctionType<>("ARRAY", new Function1<String, List<? extends String>>() { // from class: com.google.ai.client.generativeai.type.FunctionType$Companion$ARRAY$1
        @Override // kotlin.jvm.functions.Function1
        public final List<String> invoke(String it) {
            if (it == null) {
                return null;
            }
            Iterable $this$map$iv = JsonElementKt.getJsonArray(Json.Default.parseToJsonElement(it));
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                JsonElement element = (JsonElement) item$iv$iv;
                destination$iv$iv.add(element.toString());
            }
            return (List) destination$iv$iv;
        }
    });
    private static final FunctionType<JSONObject> OBJECT = new FunctionType<>("OBJECT", new Function1<String, JSONObject>() { // from class: com.google.ai.client.generativeai.type.FunctionType$Companion$OBJECT$1
        @Override // kotlin.jvm.functions.Function1
        public final JSONObject invoke(String it) {
            if (it != null) {
                return new JSONObject(it);
            }
            return null;
        }
    });

    /* compiled from: Type.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\bR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\b¨\u0006\u001a"}, d2 = {"Lcom/google/ai/client/generativeai/type/FunctionType$Companion;", "", "()V", "ARRAY", "Lcom/google/ai/client/generativeai/type/FunctionType;", "", "", "getARRAY", "()Lcom/google/ai/client/generativeai/type/FunctionType;", "BOOLEAN", "", "getBOOLEAN", "INTEGER", "", "getINTEGER", "LONG", "", "getLONG", "NUMBER", "", "getNUMBER", "OBJECT", "Lorg/json/JSONObject;", "getOBJECT", "STRING", "getSTRING", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FunctionType<String> getSTRING() {
            return FunctionType.STRING;
        }

        public final FunctionType<Integer> getINTEGER() {
            return FunctionType.INTEGER;
        }

        public final FunctionType<Long> getLONG() {
            return FunctionType.LONG;
        }

        public final FunctionType<Double> getNUMBER() {
            return FunctionType.NUMBER;
        }

        public final FunctionType<Boolean> getBOOLEAN() {
            return FunctionType.BOOLEAN;
        }

        public final FunctionType<List<String>> getARRAY() {
            return FunctionType.ARRAY;
        }

        public final FunctionType<JSONObject> getOBJECT() {
            return FunctionType.OBJECT;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FunctionType(String name, Function1<? super String, ? extends T> parse) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(parse, "parse");
        this.name = name;
        this.parse = parse;
    }

    public final String getName() {
        return this.name;
    }

    public final Function1<String, T> getParse() {
        return this.parse;
    }
}
