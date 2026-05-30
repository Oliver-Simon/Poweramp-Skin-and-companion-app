package io.ktor.util.reflect;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

/* compiled from: TypeInfoJvm.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a\u0011\u0010\b\u001a\u00020\t\"\u0006\b\u0000\u0010\n\u0018\u0001H\u0086\b\u001a(\u0010\u000b\u001a\u00020\t2\n\u0010\f\u001a\u00060\u0001j\u0002`\u00022\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u001a\u0016\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u000e\"\u001f\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00020\u00038F¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007*\n\u0010\u0014\"\u00020\u00012\u00020\u0001¨\u0006\u0015"}, d2 = {"platformType", "Ljava/lang/reflect/Type;", "Lio/ktor/util/reflect/Type;", "Lkotlin/reflect/KType;", "getPlatformType$annotations", "(Lkotlin/reflect/KType;)V", "getPlatformType", "(Lkotlin/reflect/KType;)Ljava/lang/reflect/Type;", "typeInfo", "Lio/ktor/util/reflect/TypeInfo;", ExifInterface.GPS_DIRECTION_TRUE, "typeInfoImpl", "reifiedType", "kClass", "Lkotlin/reflect/KClass;", "kType", "instanceOf", "", "", "type", "Type", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class TypeInfoJvmKt {
    public static /* synthetic */ void getPlatformType$annotations(KType kType) {
    }

    public static final /* synthetic */ <T> TypeInfo typeInfo() {
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        Type reifiedType = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return typeInfoImpl(reifiedType, Reflection.getOrCreateKotlinClass(Object.class), null);
    }

    public static final TypeInfo typeInfoImpl(Type reifiedType, KClass<?> kClass, KType kType) {
        Intrinsics.checkNotNullParameter(reifiedType, "reifiedType");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        return new TypeInfo(kClass, reifiedType, kType);
    }

    public static final boolean instanceOf(Object $this$instanceOf, KClass<?> type) {
        Intrinsics.checkNotNullParameter($this$instanceOf, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        return JvmClassMappingKt.getJavaClass((KClass) type).isInstance($this$instanceOf);
    }

    public static final Type getPlatformType(KType $this$platformType) {
        Intrinsics.checkNotNullParameter($this$platformType, "<this>");
        return TypesJVMKt.getJavaType($this$platformType);
    }
}
