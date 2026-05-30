package io.ktor.serialization.kotlinx.json;

import io.ktor.util.reflect.TypeInfo;
import io.ktor.util.reflect.TypeInfoJvmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;

/* compiled from: KotlinxSerializationJsonExtensions.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"argumentTypeInfo", "Lio/ktor/util/reflect/TypeInfo;", "ktor-serialization-kotlinx-json"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class KotlinxSerializationJsonExtensionsKt {
    public static final TypeInfo argumentTypeInfo(TypeInfo $this$argumentTypeInfo) {
        Intrinsics.checkNotNullParameter($this$argumentTypeInfo, "<this>");
        KType kotlinType = $this$argumentTypeInfo.getKotlinType();
        Intrinsics.checkNotNull(kotlinType);
        KType elementType = kotlinType.getArguments().get(0).getType();
        Intrinsics.checkNotNull(elementType);
        KClassifier classifier = elementType.getClassifier();
        Intrinsics.checkNotNull(classifier, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
        return new TypeInfo((KClass) classifier, TypeInfoJvmKt.getPlatformType(elementType), elementType);
    }
}
