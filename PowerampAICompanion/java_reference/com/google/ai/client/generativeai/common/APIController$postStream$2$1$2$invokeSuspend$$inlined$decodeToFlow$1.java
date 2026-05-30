package com.google.ai.client.generativeai.common;

import androidx.exifinterface.media.ExifInterface;
import com.google.ai.client.generativeai.common.util.KtorKt;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.reflect.KType;
import kotlin.text.StringsKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: ktor.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@¨\u0006\u0004"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/channels/ProducerScope;", "com/google/ai/client/generativeai/common/util/KtorKt$decodeToFlow$1"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.ai.client.generativeai.common.util.KtorKt$decodeToFlow$1", f = "ktor.kt", i = {}, l = {82}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class APIController$postStream$2$1$2$invokeSuspend$$inlined$decodeToFlow$1<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteReadChannel $channel;
    final /* synthetic */ Json $this_decodeToFlow;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public APIController$postStream$2$1$2$invokeSuspend$$inlined$decodeToFlow$1(ByteReadChannel byteReadChannel, Json json, Continuation continuation) {
        super(2, continuation);
        this.$channel = byteReadChannel;
        this.$this_decodeToFlow = json;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        APIController$postStream$2$1$2$invokeSuspend$$inlined$decodeToFlow$1 aPIController$postStream$2$1$2$invokeSuspend$$inlined$decodeToFlow$1 = new APIController$postStream$2$1$2$invokeSuspend$$inlined$decodeToFlow$1(this.$channel, this.$this_decodeToFlow, continuation);
        aPIController$postStream$2$1$2$invokeSuspend$$inlined$decodeToFlow$1.L$0 = obj;
        return aPIController$postStream$2$1$2$invokeSuspend$$inlined$decodeToFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super R> producerScope, Continuation<? super Unit> continuation) {
        return ((APIController$postStream$2$1$2$invokeSuspend$$inlined$decodeToFlow$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: ktor.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@¨\u0006\u0005"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "it", "", "com/google/ai/client/generativeai/common/util/KtorKt$decodeToFlow$1$1"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "com.google.ai.client.generativeai.common.util.KtorKt$decodeToFlow$1$1", f = "ktor.kt", i = {}, l = {84}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.google.ai.client.generativeai.common.APIController$postStream$2$1$2$invokeSuspend$$inlined$decodeToFlow$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<String, Continuation<? super Unit>, Object> {
        final /* synthetic */ ProducerScope<R> $$this$channelFlow;
        final /* synthetic */ Json $this_decodeToFlow;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ProducerScope producerScope, Json json, Continuation continuation) {
            super(2, continuation);
            this.$this_decodeToFlow = json;
            this.$$this$channelFlow = producerScope;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$channelFlow, this.$this_decodeToFlow, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(String str, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(str, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    String removePrefix = StringsKt.removePrefix((String) this.L$0, (CharSequence) "data:");
                    ProducerScope<R> producerScope = this.$$this$channelFlow;
                    Json json = this.$this_decodeToFlow;
                    SerializersModule serializersModule = json.getSerializersModule();
                    Intrinsics.reifiedOperationMarker(6, "R");
                    MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
                    this.label = 1;
                    if (producerScope.send(json.decodeFromString(SerializersKt.serializer(serializersModule, (KType) null), removePrefix), this) != coroutine_suspended) {
                        break;
                    } else {
                        return coroutine_suspended;
                    }
                case 1:
                    ResultKt.throwOnFailure(obj);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ProducerScope $this$channelFlow = (ProducerScope) this.L$0;
                ByteReadChannel byteReadChannel = this.$channel;
                Intrinsics.needClassReification();
                this.label = 1;
                if (KtorKt.onEachLine(byteReadChannel, new AnonymousClass1($this$channelFlow, this.$this_decodeToFlow, null), this) != coroutine_suspended) {
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
