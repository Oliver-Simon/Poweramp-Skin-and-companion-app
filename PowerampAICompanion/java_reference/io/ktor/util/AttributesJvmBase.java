package io.ktor.util;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.util.Attributes;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AttributesJvm.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\"\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0086\u0002J%\u0010\u0010\u001a\u0004\u0018\u0001H\u0011\"\b\b\u0000\u0010\u0011*\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0005¢\u0006\u0002\u0010\u0012J+\u0010\u0013\u001a\u00020\u0014\"\b\b\u0000\u0010\u0011*\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u00052\u0006\u0010\u0015\u001a\u0002H\u0011¢\u0006\u0002\u0010\u0016J\u001e\u0010\u0017\u001a\u00020\u0014\"\b\b\u0000\u0010\u0011*\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0005R\u001b\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R$\u0010\b\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lio/ktor/util/AttributesJvmBase;", "Lio/ktor/util/Attributes;", "()V", "allKeys", "", "Lio/ktor/util/AttributeKey;", "getAllKeys", "()Ljava/util/List;", "map", "", "", "getMap", "()Ljava/util/Map;", "contains", "", "key", "getOrNull", ExifInterface.GPS_DIRECTION_TRUE, "(Lio/ktor/util/AttributeKey;)Ljava/lang/Object;", "put", "", "value", "(Lio/ktor/util/AttributeKey;Ljava/lang/Object;)V", "remove", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
abstract class AttributesJvmBase implements Attributes {
    protected abstract Map<AttributeKey<?>, Object> getMap();

    @Override // io.ktor.util.Attributes
    public <T> T get(AttributeKey<T> attributeKey) {
        return (T) Attributes.DefaultImpls.get(this, attributeKey);
    }

    @Override // io.ktor.util.Attributes
    public <T> T take(AttributeKey<T> attributeKey) {
        return (T) Attributes.DefaultImpls.take(this, attributeKey);
    }

    @Override // io.ktor.util.Attributes
    public <T> T takeOrNull(AttributeKey<T> attributeKey) {
        return (T) Attributes.DefaultImpls.takeOrNull(this, attributeKey);
    }

    @Override // io.ktor.util.Attributes
    public final <T> T getOrNull(AttributeKey<T> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return (T) getMap().get(key);
    }

    @Override // io.ktor.util.Attributes
    public final boolean contains(AttributeKey<?> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return getMap().containsKey(key);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.ktor.util.Attributes
    public final <T> void put(AttributeKey<T> key, T value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        getMap().put(key, value);
    }

    @Override // io.ktor.util.Attributes
    public final <T> void remove(AttributeKey<T> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        getMap().remove(key);
    }

    @Override // io.ktor.util.Attributes
    public final List<AttributeKey<?>> getAllKeys() {
        return kotlin.collections.CollectionsKt.toList(getMap().keySet());
    }
}
