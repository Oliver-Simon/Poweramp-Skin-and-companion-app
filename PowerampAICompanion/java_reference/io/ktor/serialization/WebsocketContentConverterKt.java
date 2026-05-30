package io.ktor.serialization;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.util.reflect.TypeInfoJvmKt;
import io.ktor.websocket.Frame;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;
import kotlin.text.Charsets;

/* compiled from: WebsocketContentConverter.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a3\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\b\u0002\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\b\u001a3\u0010\t\u001a\u00020\u0004\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\n\u001a\u0002H\u00012\f\b\u0002\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"deserialize", ExifInterface.GPS_DIRECTION_TRUE, "Lio/ktor/serialization/WebsocketContentConverter;", "content", "Lio/ktor/websocket/Frame;", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "(Lio/ktor/serialization/WebsocketContentConverter;Lio/ktor/websocket/Frame;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "serialize", "value", "(Lio/ktor/serialization/WebsocketContentConverter;Ljava/lang/Object;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-serialization"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class WebsocketContentConverterKt {
    public static /* synthetic */ Object serialize$default(WebsocketContentConverter $this$serialize_u24default, Object value, Charset charset, Continuation $completion, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        Type reifiedType$iv = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return $this$serialize_u24default.serializeNullable(charset, TypeInfoJvmKt.typeInfoImpl(reifiedType$iv, Reflection.getOrCreateKotlinClass(Object.class), null), value, $completion);
    }

    public static final /* synthetic */ <T> Object serialize(WebsocketContentConverter $this$serialize, T t, Charset charset, Continuation<? super Frame> continuation) {
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        Type reifiedType$iv = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return $this$serialize.serializeNullable(charset, TypeInfoJvmKt.typeInfoImpl(reifiedType$iv, Reflection.getOrCreateKotlinClass(Object.class), null), t, continuation);
    }

    public static /* synthetic */ Object deserialize$default(WebsocketContentConverter $this$deserialize_u24default, Frame content, Charset charset, Continuation $completion, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        Type reifiedType$iv = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Object deserialize = $this$deserialize_u24default.deserialize(charset, TypeInfoJvmKt.typeInfoImpl(reifiedType$iv, Reflection.getOrCreateKotlinClass(Object.class), null), content, $completion);
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return deserialize;
    }

    public static final /* synthetic */ <T> Object deserialize(WebsocketContentConverter $this$deserialize, Frame content, Charset charset, Continuation<? super T> continuation) {
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        Type reifiedType$iv = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Object deserialize = $this$deserialize.deserialize(charset, TypeInfoJvmKt.typeInfoImpl(reifiedType$iv, Reflection.getOrCreateKotlinClass(Object.class), null), content, continuation);
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return deserialize;
    }
}
