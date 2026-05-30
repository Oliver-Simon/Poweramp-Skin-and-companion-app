package io.ktor.client.statement;

import androidx.core.text.HtmlCompat;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.call.SavedCallKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: HttpStatement.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "Lio/ktor/client/statement/HttpResponse;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.statement.HttpStatement$execute$4", f = "HttpStatement.kt", i = {}, l = {HtmlCompat.FROM_HTML_MODE_COMPACT}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class HttpStatement$execute$4 extends SuspendLambda implements Function2<HttpResponse, Continuation<? super HttpResponse>, Object> {
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpStatement$execute$4(Continuation<? super HttpStatement$execute$4> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        HttpStatement$execute$4 httpStatement$execute$4 = new HttpStatement$execute$4(continuation);
        httpStatement$execute$4.L$0 = obj;
        return httpStatement$execute$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(HttpResponse httpResponse, Continuation<? super HttpResponse> continuation) {
        return ((HttpStatement$execute$4) create(httpResponse, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                HttpResponse it = (HttpResponse) this.L$0;
                this.label = 1;
                Object save = SavedCallKt.save(it.getCall(), this);
                if (save != coroutine_suspended) {
                    $result = save;
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
        HttpClientCall savedCall = (HttpClientCall) $result;
        return savedCall.getResponse();
    }
}
