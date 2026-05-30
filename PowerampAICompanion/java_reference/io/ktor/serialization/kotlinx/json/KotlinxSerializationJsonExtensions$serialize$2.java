package io.ktor.serialization.kotlinx.json;

import io.ktor.utils.io.ByteWriteChannel;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.serialization.KSerializer;

/* compiled from: KotlinxSerializationJsonExtensions.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/ByteWriteChannel;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$2", f = "KotlinxSerializationJsonExtensions.kt", i = {}, l = {51}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class KotlinxSerializationJsonExtensions$serialize$2 extends SuspendLambda implements Function2<ByteWriteChannel, Continuation<? super Unit>, Object> {
    final /* synthetic */ Charset $charset;
    final /* synthetic */ KSerializer<?> $serializer;
    final /* synthetic */ Object $value;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ KotlinxSerializationJsonExtensions this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinxSerializationJsonExtensions$serialize$2(KotlinxSerializationJsonExtensions kotlinxSerializationJsonExtensions, Object obj, KSerializer<?> kSerializer, Charset charset, Continuation<? super KotlinxSerializationJsonExtensions$serialize$2> continuation) {
        super(2, continuation);
        this.this$0 = kotlinxSerializationJsonExtensions;
        this.$value = obj;
        this.$serializer = kSerializer;
        this.$charset = charset;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        KotlinxSerializationJsonExtensions$serialize$2 kotlinxSerializationJsonExtensions$serialize$2 = new KotlinxSerializationJsonExtensions$serialize$2(this.this$0, this.$value, this.$serializer, this.$charset, continuation);
        kotlinxSerializationJsonExtensions$serialize$2.L$0 = obj;
        return kotlinxSerializationJsonExtensions$serialize$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ByteWriteChannel byteWriteChannel, Continuation<? super Unit> continuation) {
        return ((KotlinxSerializationJsonExtensions$serialize$2) create(byteWriteChannel, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object serialize;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ByteWriteChannel $this$$receiver = (ByteWriteChannel) this.L$0;
                KotlinxSerializationJsonExtensions kotlinxSerializationJsonExtensions = this.this$0;
                Object obj = this.$value;
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.flow.Flow<*>");
                KSerializer<?> kSerializer = this.$serializer;
                Intrinsics.checkNotNull(kSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any?>");
                this.label = 1;
                serialize = kotlinxSerializationJsonExtensions.serialize((Flow) obj, kSerializer, this.$charset, $this$$receiver, (Continuation<? super Unit>) this);
                if (serialize != coroutine_suspended) {
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
