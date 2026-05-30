package io.ktor.util;

import androidx.exifinterface.media.ExifInterface;
import com.maxmpz.poweramp.player.RouterConsts;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Attributes.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002*J\b\u0007\u0010\u0004\u001a\u0004\b\u0000\u0010\u0005\"\b\u0012\u0004\u0012\u0002H\u00050\u00062\b\u0012\u0004\u0012\u0002H\u00050\u0006B*\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u001c\b\n\u0012\u0018\b\u000bB\u0014\b\u000b\u0012\u0006\b\f\u0012\u0002\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e¨\u0006\u000f"}, d2 = {"putAll", "", "Lio/ktor/util/Attributes;", RouterConsts.DEVICE_NAME_OTHER, "EquatableAttributeKey", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/util/AttributeKey;", "Lkotlin/Deprecated;", "message", "Please use `AttributeKey` class instead", "replaceWith", "Lkotlin/ReplaceWith;", "imports", "expression", "AttributeKey", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class AttributesKt {
    @Deprecated(message = "Please use `AttributeKey` class instead", replaceWith = @ReplaceWith(expression = "AttributeKey", imports = {}))
    public static /* synthetic */ void EquatableAttributeKey$annotations() {
    }

    public static final void putAll(Attributes $this$putAll, Attributes other) {
        Intrinsics.checkNotNullParameter($this$putAll, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Iterable $this$forEach$iv = other.getAllKeys();
        for (Object element$iv : $this$forEach$iv) {
            AttributeKey it = (AttributeKey) element$iv;
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type io.ktor.util.AttributeKey<kotlin.Any>");
            $this$putAll.put(it, other.get(it));
        }
    }
}
