package io.ktor.client.plugins.logging;

import io.ktor.client.statement.HttpResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: Logging.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/client/statement/HttpResponse;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.logging.Logging$setupResponseLogging$observer$1", f = "Logging.kt", i = {0, 0, 1, 3, 5}, l = {222, 225, 226, 225, 226, 225, 226}, m = "invokeSuspend", n = {"logger", "log", "logger", "logger", "logger"}, s = {"L$0", "L$1", "L$0", "L$0", "L$0"})
/* loaded from: classes9.dex */
final class Logging$setupResponseLogging$observer$1 extends SuspendLambda implements Function2<HttpResponse, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ Logging this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Logging$setupResponseLogging$observer$1(Logging logging, Continuation<? super Logging$setupResponseLogging$observer$1> continuation) {
        super(2, continuation);
        this.this$0 = logging;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Logging$setupResponseLogging$observer$1 logging$setupResponseLogging$observer$1 = new Logging$setupResponseLogging$observer$1(this.this$0, continuation);
        logging$setupResponseLogging$observer$1.L$0 = obj;
        return logging$setupResponseLogging$observer$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(HttpResponse httpResponse, Continuation<? super Unit> continuation) {
        return ((Logging$setupResponseLogging$observer$1) create(httpResponse, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0009. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0129 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00f3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00e3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0119 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x011a  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            Method dump skipped, instructions count: 324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.logging.Logging$setupResponseLogging$observer$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
