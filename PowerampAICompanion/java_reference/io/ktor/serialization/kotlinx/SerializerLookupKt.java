package io.ktor.serialization.kotlinx;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.util.reflect.TypeInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KType;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.modules.SerializersModule;

/* compiled from: SerializerLookup.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u001c\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a(\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0001\"\b\b\u0000\u0010\t*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\t0\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a\u0018\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0001*\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0007¨\u0006\r"}, d2 = {"guessSerializer", "Lkotlinx/serialization/KSerializer;", "", "value", "module", "Lkotlinx/serialization/modules/SerializersModule;", "elementSerializer", "", "maybeNullable", ExifInterface.GPS_DIRECTION_TRUE, "typeInfo", "Lio/ktor/util/reflect/TypeInfo;", "serializerForTypeInfo", "ktor-serialization-kotlinx"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class SerializerLookupKt {
    @ExperimentalSerializationApi
    @InternalSerializationApi
    public static final KSerializer<?> serializerForTypeInfo(SerializersModule $this$serializerForTypeInfo, TypeInfo typeInfo) {
        KSerializer<?> serializerOrNull;
        Intrinsics.checkNotNullParameter($this$serializerForTypeInfo, "<this>");
        Intrinsics.checkNotNullParameter(typeInfo, "typeInfo");
        KType type = typeInfo.getKotlinType();
        if (type != null) {
            if (type.getArguments().isEmpty()) {
                serializerOrNull = null;
            } else {
                serializerOrNull = SerializersKt.serializerOrNull($this$serializerForTypeInfo, type);
            }
            if (serializerOrNull != null) {
                return serializerOrNull;
            }
        }
        KSerializer contextual$default = SerializersModule.getContextual$default($this$serializerForTypeInfo, typeInfo.getType(), null, 2, null);
        return contextual$default != null ? maybeNullable(contextual$default, typeInfo) : maybeNullable(SerializersKt.serializer(typeInfo.getType()), typeInfo);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> KSerializer<?> maybeNullable(KSerializer<T> kSerializer, TypeInfo typeInfo) {
        KType kotlinType = typeInfo.getKotlinType();
        boolean z = false;
        if (kotlinType != null && kotlinType.isMarkedNullable()) {
            z = true;
        }
        return z ? BuiltinSerializersKt.getNullable(kSerializer) : kSerializer;
    }

    public static final KSerializer<Object> guessSerializer(Object value, SerializersModule module) {
        KSerializer keySerializer;
        Intrinsics.checkNotNullParameter(module, "module");
        if (value == null) {
            keySerializer = BuiltinSerializersKt.getNullable(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE));
        } else if (value instanceof List) {
            keySerializer = BuiltinSerializersKt.ListSerializer(elementSerializer((Collection) value, module));
        } else if (value instanceof Object[]) {
            Object it = ArraysKt.firstOrNull((Object[]) value);
            if (it == null || (keySerializer = guessSerializer(it, module)) == null) {
                keySerializer = BuiltinSerializersKt.ListSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE));
            }
        } else if (value instanceof Set) {
            keySerializer = BuiltinSerializersKt.SetSerializer(elementSerializer((Collection) value, module));
        } else if (value instanceof Map) {
            KSerializer keySerializer2 = elementSerializer(((Map) value).keySet(), module);
            KSerializer valueSerializer = elementSerializer(((Map) value).values(), module);
            keySerializer = BuiltinSerializersKt.MapSerializer(keySerializer2, valueSerializer);
        } else {
            keySerializer = SerializersModule.getContextual$default(module, Reflection.getOrCreateKotlinClass(value.getClass()), null, 2, null);
            if (keySerializer == null) {
                keySerializer = SerializersKt.serializer(Reflection.getOrCreateKotlinClass(value.getClass()));
            }
        }
        Intrinsics.checkNotNull(keySerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any>");
        return keySerializer;
    }

    private static final KSerializer<?> elementSerializer(Collection<?> collection, SerializersModule module) {
        Iterable $this$map$iv = CollectionsKt.filterNotNull(collection);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(guessSerializer(item$iv$iv, module));
        }
        Iterable $this$distinctBy$iv = (List) destination$iv$iv;
        HashSet set$iv = new HashSet();
        ArrayList list$iv = new ArrayList();
        for (Object e$iv : $this$distinctBy$iv) {
            KSerializer it = (KSerializer) e$iv;
            if (set$iv.add(it.getDescriptor().getSerialName())) {
                list$iv.add(e$iv);
            }
        }
        ArrayList serializers = list$iv;
        boolean z = true;
        if (serializers.size() > 1) {
            StringBuilder append = new StringBuilder().append("Serializing collections of different element types is not yet supported. Selected serializers: ");
            ArrayList $this$map$iv2 = serializers;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
            for (Object item$iv$iv2 : $this$map$iv2) {
                KSerializer it2 = (KSerializer) item$iv$iv2;
                destination$iv$iv2.add(it2.getDescriptor().getSerialName());
            }
            throw new IllegalStateException(append.append((List) destination$iv$iv2).toString().toString());
        }
        KSerializer selected = (KSerializer) CollectionsKt.singleOrNull((List) serializers);
        if (selected == null) {
            selected = BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE);
        }
        if (selected.getDescriptor().isNullable()) {
            return selected;
        }
        Intrinsics.checkNotNull(selected, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any>");
        Collection<?> $this$any$iv = collection;
        if (!($this$any$iv instanceof Collection) || !$this$any$iv.isEmpty()) {
            Iterator it3 = $this$any$iv.iterator();
            while (true) {
                if (it3.hasNext()) {
                    Object element$iv = it3.next();
                    Object it4 = element$iv == null ? 1 : null;
                    if (it4 != null) {
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (z) {
            return BuiltinSerializersKt.getNullable(selected);
        }
        return selected;
    }
}
