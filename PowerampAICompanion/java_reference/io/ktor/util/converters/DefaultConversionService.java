package io.ktor.util.converters;

import io.ktor.util.reflect.TypeInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.text.StringsKt;

/* compiled from: ConversionService.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006J \u0010\n\u001a\u0004\u0018\u00010\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\bH\u0002J\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\u0013"}, d2 = {"Lio/ktor/util/converters/DefaultConversionService;", "Lio/ktor/util/converters/ConversionService;", "()V", "convertPrimitives", "", "klass", "Lkotlin/reflect/KClass;", "value", "", "fromValue", "fromValues", "values", "", "type", "Lio/ktor/util/reflect/TypeInfo;", "throwConversionException", "", "typeName", "toValues", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class DefaultConversionService implements ConversionService {
    public static final DefaultConversionService INSTANCE = new DefaultConversionService();

    private DefaultConversionService() {
    }

    @Override // io.ktor.util.converters.ConversionService
    public List<String> toValues(Object value) {
        if (value == null) {
            return CollectionsKt.emptyList();
        }
        List converted = ConversionServiceJvmKt.platformDefaultToValues(value);
        if (converted != null) {
            return converted;
        }
        if (!(value instanceof Iterable)) {
            KClass klass = Reflection.getOrCreateKotlinClass(value.getClass());
            if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Integer.TYPE)) ? true : Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Float.TYPE)) ? true : Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Double.TYPE)) ? true : Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Long.TYPE)) ? true : Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Short.TYPE)) ? true : Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Character.TYPE)) ? true : Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Boolean.TYPE)) ? true : Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(String.class))) {
                return CollectionsKt.listOf(value.toString());
            }
            throw new DataConversionException("Class " + klass + " is not supported in default data conversion service");
        }
        Iterable $this$flatMap$iv = (Iterable) value;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            Iterable it = INSTANCE.toValues(element$iv$iv);
            Iterable list$iv$iv = it;
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        return (List) destination$iv$iv;
    }

    @Override // io.ktor.util.converters.ConversionService
    public Object fromValues(List<String> values, TypeInfo type) {
        List<KTypeProjection> arguments;
        KTypeProjection kTypeProjection;
        KType type2;
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(type, "type");
        if (values.isEmpty()) {
            return null;
        }
        if (Intrinsics.areEqual(type.getType(), Reflection.getOrCreateKotlinClass(List.class)) || Intrinsics.areEqual(type.getType(), Reflection.getOrCreateKotlinClass(List.class))) {
            KType kotlinType = type.getKotlinType();
            Object classifier = (kotlinType == null || (arguments = kotlinType.getArguments()) == null || (kTypeProjection = (KTypeProjection) CollectionsKt.single((List) arguments)) == null || (type2 = kTypeProjection.getType()) == null) ? null : type2.getClassifier();
            KClass argumentType = classifier instanceof KClass ? (KClass) classifier : null;
            if (argumentType != null) {
                List<String> $this$map$iv = values;
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    String it = (String) item$iv$iv;
                    destination$iv$iv.add(INSTANCE.fromValue(it, argumentType));
                }
                return (List) destination$iv$iv;
            }
        }
        if (values.isEmpty()) {
            throw new DataConversionException("There are no values when trying to construct single value " + type);
        }
        if (values.size() > 1) {
            throw new DataConversionException("There are multiple values when trying to construct single value " + type);
        }
        return fromValue((String) CollectionsKt.single((List) values), type.getType());
    }

    public final Object fromValue(String value, KClass<?> klass) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(klass, "klass");
        Object converted = convertPrimitives(klass, value);
        if (converted != null) {
            return converted;
        }
        Object platformConverted = ConversionServiceJvmKt.platformDefaultFromValues(value, klass);
        if (platformConverted != null) {
            return platformConverted;
        }
        throwConversionException(klass.toString());
        throw new KotlinNothingValueException();
    }

    private final Object convertPrimitives(KClass<?> klass, String value) {
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            return Integer.valueOf(Integer.parseInt(value));
        }
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Float.TYPE))) {
            return Float.valueOf(Float.parseFloat(value));
        }
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return Double.valueOf(Double.parseDouble(value));
        }
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            return Long.valueOf(Long.parseLong(value));
        }
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Short.TYPE))) {
            return Short.valueOf(Short.parseShort(value));
        }
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Character.TYPE))) {
            return Character.valueOf(StringsKt.single(value));
        }
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            return Boolean.valueOf(Boolean.parseBoolean(value));
        }
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(String.class))) {
            return value;
        }
        return null;
    }

    private final Void throwConversionException(String typeName) {
        throw new DataConversionException("Type " + typeName + " is not supported in default data conversion service");
    }
}
