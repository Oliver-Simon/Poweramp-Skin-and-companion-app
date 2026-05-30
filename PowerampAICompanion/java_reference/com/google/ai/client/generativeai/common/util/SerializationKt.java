package com.google.ai.client.generativeai.common.util;

import androidx.exifinterface.media.ExifInterface;
import com.google.ai.client.generativeai.common.SerializationException;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.SerialName;

/* compiled from: serialization.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0010\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a+\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\b¢\u0006\u0002\u0010\t\"%\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\u0002H\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\n"}, d2 = {"serialName", "", ExifInterface.GPS_DIRECTION_TRUE, "", "getSerialName", "(Ljava/lang/Enum;)Ljava/lang/String;", "enumValues", "", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)[Ljava/lang/Enum;", "common_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SerializationKt {
    public static final <T extends Enum<T>> String getSerialName(T t) {
        String value;
        Intrinsics.checkNotNullParameter(t, "<this>");
        Class declaringClass = t.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "this as java.lang.Enum<E>).declaringClass");
        Field $this$getAnnotation$iv = declaringClass.getField(t.name());
        Intrinsics.checkNotNullExpressionValue($this$getAnnotation$iv, "declaringJavaClass.getField(name)");
        SerialName serialName = (SerialName) $this$getAnnotation$iv.getAnnotation(SerialName.class);
        return (serialName == null || (value = serialName.value()) == null) ? t.name() : value;
    }

    public static final <T extends Enum<T>> T[] enumValues(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        T[] tArr = (T[]) ((Enum[]) JvmClassMappingKt.getJavaClass((KClass) kClass).getEnumConstants());
        if (tArr != null) {
            return tArr;
        }
        throw new SerializationException(kClass.getSimpleName() + " is not a valid enum type.", null, 2, null);
    }
}
