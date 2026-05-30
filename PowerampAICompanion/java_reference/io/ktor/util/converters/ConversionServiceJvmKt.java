package io.ktor.util.converters;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: ConversionServiceJvm.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u001a\u001e\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002\u001a\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0000\u001a\u0018\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b2\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¨\u0006\t"}, d2 = {"convertSimpleTypes", "", "value", "", "klass", "Lkotlin/reflect/KClass;", "platformDefaultFromValues", "platformDefaultToValues", "", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ConversionServiceJvmKt {
    public static final Object platformDefaultFromValues(String value, KClass<?> klass) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(klass, "klass");
        Object converted = convertSimpleTypes(value, klass);
        if (converted != null) {
            return converted;
        }
        Object obj = null;
        if (!JvmClassMappingKt.getJavaClass((KClass) klass).isEnum()) {
            return null;
        }
        Object[] $this$firstOrNull$iv = JvmClassMappingKt.getJavaClass((KClass) klass).getEnumConstants();
        if ($this$firstOrNull$iv != null) {
            int length = $this$firstOrNull$iv.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Object element$iv = $this$firstOrNull$iv[i];
                Intrinsics.checkNotNull(element$iv, "null cannot be cast to non-null type kotlin.Enum<*>");
                if (Intrinsics.areEqual(((Enum) element$iv).name(), value)) {
                    obj = element$iv;
                    break;
                }
                i++;
            }
            if (obj != null) {
                return obj;
            }
        }
        throw new DataConversionException("Value " + value + " is not a enum member name of " + klass);
    }

    private static final Object convertSimpleTypes(String value, KClass<?> kClass) {
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Integer.class))) {
            return Integer.valueOf(Integer.parseInt(value));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Float.class))) {
            return Float.valueOf(Float.parseFloat(value));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Double.class))) {
            return Double.valueOf(Double.parseDouble(value));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Long.class))) {
            return Long.valueOf(Long.parseLong(value));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Short.class))) {
            return Short.valueOf(Short.parseShort(value));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Boolean.class))) {
            return Boolean.valueOf(Boolean.parseBoolean(value));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(String.class))) {
            return value;
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Character.class))) {
            return Character.valueOf(value.charAt(0));
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(BigDecimal.class))) {
            return new BigDecimal(value);
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(BigInteger.class))) {
            return new BigInteger(value);
        }
        if (Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(UUID.class))) {
            return UUID.fromString(value);
        }
        return null;
    }

    public static final List<String> platformDefaultToValues(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof Enum) {
            return CollectionsKt.listOf(((Enum) value).name());
        }
        if ((value instanceof Integer) || (value instanceof Float) || (value instanceof Double) || (value instanceof Long) || (value instanceof Boolean) || (value instanceof Short) || (value instanceof String) || (value instanceof Character) || (value instanceof BigDecimal) || (value instanceof BigInteger) || (value instanceof UUID)) {
            return CollectionsKt.listOf(value.toString());
        }
        return null;
    }
}
