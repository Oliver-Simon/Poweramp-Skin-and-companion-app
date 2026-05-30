package io.ktor.serialization.kotlinx.json;

import io.ktor.http.ContentType;
import io.ktor.serialization.Configuration;
import io.ktor.serialization.kotlinx.KotlinxSerializationConverterKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;

/* compiled from: JsonSupport.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0004\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\t"}, d2 = {"DefaultJson", "Lkotlinx/serialization/json/Json;", "getDefaultJson", "()Lkotlinx/serialization/json/Json;", "json", "", "Lio/ktor/serialization/Configuration;", "contentType", "Lio/ktor/http/ContentType;", "ktor-serialization-kotlinx-json"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class JsonSupportKt {
    private static final Json DefaultJson = JsonKt.Json$default(null, new Function1<JsonBuilder, Unit>() { // from class: io.ktor.serialization.kotlinx.json.JsonSupportKt$DefaultJson$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(JsonBuilder jsonBuilder) {
            invoke2(jsonBuilder);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(JsonBuilder Json) {
            Intrinsics.checkNotNullParameter(Json, "$this$Json");
            Json.setEncodeDefaults(true);
            Json.setLenient(true);
            Json.setAllowSpecialFloatingPointValues(true);
            Json.setAllowStructuredMapKeys(true);
            Json.setPrettyPrint(false);
            Json.setUseArrayPolymorphism(false);
        }
    }, 1, null);

    public static final Json getDefaultJson() {
        return DefaultJson;
    }

    public static /* synthetic */ void json$default(Configuration configuration, Json json, ContentType contentType, int i, Object obj) {
        if ((i & 1) != 0) {
            json = DefaultJson;
        }
        if ((i & 2) != 0) {
            contentType = ContentType.Application.INSTANCE.getJson();
        }
        json(configuration, json, contentType);
    }

    public static final void json(Configuration $this$json, Json json, ContentType contentType) {
        Intrinsics.checkNotNullParameter($this$json, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        KotlinxSerializationConverterKt.serialization($this$json, contentType, json);
    }
}
