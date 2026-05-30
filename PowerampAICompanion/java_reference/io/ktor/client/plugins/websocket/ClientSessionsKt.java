package io.ktor.client.plugins.websocket;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.client.plugins.HttpClientPluginKt;
import io.ktor.serialization.ContentConverterKt;
import io.ktor.serialization.WebsocketContentConverter;
import io.ktor.serialization.WebsocketConverterNotFoundException;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.util.reflect.TypeInfoJvmKt;
import io.ktor.websocket.serialization.WebsocketChannelSerializationKt;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

/* compiled from: ClientSessions.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u001d\u0010\u0005\u001a\u0002H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u0002H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a#\u0010\u0005\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006*\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a%\u0010\u000b\u001a\u00020\f\"\u0006\b\u0000\u0010\u0006\u0018\u0001*\u00020\u00022\u0006\u0010\r\u001a\u0002H\u0006H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a'\u0010\u000b\u001a\u00020\f*\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\b\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"converter", "Lio/ktor/serialization/WebsocketContentConverter;", "Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;", "getConverter", "(Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;)Lio/ktor/serialization/WebsocketContentConverter;", "receiveDeserialized", ExifInterface.GPS_DIRECTION_TRUE, "(Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "typeInfo", "Lio/ktor/util/reflect/TypeInfo;", "(Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;Lio/ktor/util/reflect/TypeInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendSerialized", "", "data", "(Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;Ljava/lang/Object;Lio/ktor/util/reflect/TypeInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ClientSessionsKt {
    public static final WebsocketContentConverter getConverter(DefaultClientWebSocketSession $this$converter) {
        Intrinsics.checkNotNullParameter($this$converter, "<this>");
        WebSockets webSockets = (WebSockets) HttpClientPluginKt.pluginOrNull($this$converter.getCall().getClient(), WebSockets.INSTANCE);
        if (webSockets != null) {
            return webSockets.getContentConverter();
        }
        return null;
    }

    public static final Object sendSerialized(DefaultClientWebSocketSession $this$sendSerialized, Object data, TypeInfo typeInfo, Continuation<? super Unit> continuation) {
        WebsocketContentConverter converter = getConverter($this$sendSerialized);
        if (converter == null) {
            throw new WebsocketConverterNotFoundException("No converter was found for websocket", null, 2, null);
        }
        Object data2 = WebsocketChannelSerializationKt.sendSerializedBase($this$sendSerialized, data, typeInfo, converter, ContentConverterKt.suitableCharset$default($this$sendSerialized.getCall().getRequest().getHeaders(), null, 1, null), continuation);
        return data2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? data2 : Unit.INSTANCE;
    }

    public static final /* synthetic */ <T> Object sendSerialized(DefaultClientWebSocketSession $this$sendSerialized, T t, Continuation<? super Unit> continuation) {
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        Type reifiedType$iv = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        sendSerialized($this$sendSerialized, t, TypeInfoJvmKt.typeInfoImpl(reifiedType$iv, Reflection.getOrCreateKotlinClass(Object.class), null), continuation);
        return Unit.INSTANCE;
    }

    public static final <T> Object receiveDeserialized(DefaultClientWebSocketSession $this$receiveDeserialized, TypeInfo typeInfo, Continuation<? super T> continuation) {
        WebsocketContentConverter converter = getConverter($this$receiveDeserialized);
        if (converter == null) {
            throw new WebsocketConverterNotFoundException("No converter was found for websocket", null, 2, null);
        }
        Object receiveDeserializedBase = WebsocketChannelSerializationKt.receiveDeserializedBase($this$receiveDeserialized, typeInfo, converter, ContentConverterKt.suitableCharset$default($this$receiveDeserialized.getCall().getRequest().getHeaders(), null, 1, null), continuation);
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return receiveDeserializedBase;
    }

    public static final /* synthetic */ <T> Object receiveDeserialized(DefaultClientWebSocketSession $this$receiveDeserialized, Continuation<? super T> continuation) {
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        Type reifiedType$iv = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return receiveDeserialized($this$receiveDeserialized, TypeInfoJvmKt.typeInfoImpl(reifiedType$iv, Reflection.getOrCreateKotlinClass(Object.class), null), continuation);
    }
}
