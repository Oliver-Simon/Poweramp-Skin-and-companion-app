package io.ktor.client.plugins;

import com.maxmpz.poweramp.player.PowerampAPI;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpResponseKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelJVMKt;
import io.ktor.utils.io.WriterScope;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: DefaultTransform.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.DefaultTransformKt$defaultTransformers$2$result$channel$1", f = "DefaultTransform.kt", i = {}, l = {PowerampAPI.Track.MAX_FILE_NUMBER}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class DefaultTransformKt$defaultTransformers$2$result$channel$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $body;
    final /* synthetic */ HttpResponse $response;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultTransformKt$defaultTransformers$2$result$channel$1(Object obj, HttpResponse httpResponse, Continuation<? super DefaultTransformKt$defaultTransformers$2$result$channel$1> continuation) {
        super(2, continuation);
        this.$body = obj;
        this.$response = httpResponse;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DefaultTransformKt$defaultTransformers$2$result$channel$1 defaultTransformKt$defaultTransformers$2$result$channel$1 = new DefaultTransformKt$defaultTransformers$2$result$channel$1(this.$body, this.$response, continuation);
        defaultTransformKt$defaultTransformers$2$result$channel$1.L$0 = obj;
        return defaultTransformKt$defaultTransformers$2$result$channel$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((DefaultTransformKt$defaultTransformers$2$result$channel$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0006. Please report as an issue. */
    /* JADX WARN: Type inference failed for: r0v0, types: [io.ktor.client.plugins.DefaultTransformKt$defaultTransformers$2$result$channel$1, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Throwable cause;
        DefaultTransformKt$defaultTransformers$2$result$channel$1 defaultTransformKt$defaultTransformers$2$result$channel$1;
        CancellationException cause2;
        ?? coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    WriterScope $this$writer = (WriterScope) this.L$0;
                    try {
                        this.label = 1;
                        if (ByteReadChannelJVMKt.copyTo((ByteReadChannel) this.$body, $this$writer.getChannel(), Long.MAX_VALUE, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        defaultTransformKt$defaultTransformers$2$result$channel$1 = this;
                        HttpResponseKt.complete(defaultTransformKt$defaultTransformers$2$result$channel$1.$response);
                        return Unit.INSTANCE;
                    } catch (CancellationException e) {
                        cause2 = e;
                        defaultTransformKt$defaultTransformers$2$result$channel$1 = this;
                        CoroutineScopeKt.cancel(defaultTransformKt$defaultTransformers$2$result$channel$1.$response, cause2);
                        throw cause2;
                    } catch (Throwable th) {
                        cause = th;
                        defaultTransformKt$defaultTransformers$2$result$channel$1 = this;
                        CoroutineScopeKt.cancel(defaultTransformKt$defaultTransformers$2$result$channel$1.$response, "Receive failed", cause);
                        throw cause;
                    }
                case 1:
                    defaultTransformKt$defaultTransformers$2$result$channel$1 = this;
                    try {
                        ResultKt.throwOnFailure($result);
                        HttpResponseKt.complete(defaultTransformKt$defaultTransformers$2$result$channel$1.$response);
                        return Unit.INSTANCE;
                    } catch (CancellationException e2) {
                        cause2 = e2;
                        CoroutineScopeKt.cancel(defaultTransformKt$defaultTransformers$2$result$channel$1.$response, cause2);
                        throw cause2;
                    } catch (Throwable th2) {
                        cause = th2;
                        CoroutineScopeKt.cancel(defaultTransformKt$defaultTransformers$2$result$channel$1.$response, "Receive failed", cause);
                        throw cause;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable cause3) {
            HttpResponseKt.complete(coroutine_suspended.$response);
            throw cause3;
        }
    }
}
