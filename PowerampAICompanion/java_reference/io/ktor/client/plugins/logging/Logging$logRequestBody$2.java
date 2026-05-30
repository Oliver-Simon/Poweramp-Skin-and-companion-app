package io.ktor.client.plugins.logging;

import io.ktor.utils.io.ByteChannel;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Logging.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.logging.Logging$logRequestBody$2", f = "Logging.kt", i = {0}, l = {268}, m = "invokeSuspend", n = {"charset$iv"}, s = {"L$0"})
/* loaded from: classes9.dex */
final class Logging$logRequestBody$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteChannel $channel;
    final /* synthetic */ Charset $charset;
    final /* synthetic */ StringBuilder $requestLog;
    Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Logging$logRequestBody$2(ByteChannel byteChannel, Charset charset, StringBuilder sb, Continuation<? super Logging$logRequestBody$2> continuation) {
        super(2, continuation);
        this.$channel = byteChannel;
        this.$charset = charset;
        this.$requestLog = sb;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Logging$logRequestBody$2(this.$channel, this.$charset, this.$requestLog, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((Logging$logRequestBody$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:13:0x005b  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 0
            switch(r1) {
                case 0: goto L21;
                case 1: goto L12;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L12:
            r1 = r11
            r3 = 0
            java.lang.Object r0 = r1.L$0
            java.nio.charset.Charset r0 = (java.nio.charset.Charset) r0
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L1f
            r10 = r3
            r3 = r1
            r1 = r12
            goto L45
        L1f:
            r0 = move-exception
            goto L55
        L21:
            kotlin.ResultKt.throwOnFailure(r12)
            r1 = r11
            io.ktor.utils.io.ByteChannel r3 = r1.$channel
            r4 = r3
            io.ktor.utils.io.ByteReadChannel r4 = (io.ktor.utils.io.ByteReadChannel) r4
            java.nio.charset.Charset r3 = r1.$charset
            r10 = 0
            r7 = r1
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch: java.lang.Throwable -> L53
            r1.L$0 = r3     // Catch: java.lang.Throwable -> L53
            r5 = 1
            r1.label = r5     // Catch: java.lang.Throwable -> L53
            r5 = 0
            r8 = 1
            r9 = 0
            java.lang.Object r5 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r4, r5, r7, r8, r9)     // Catch: java.lang.Throwable -> L53
            if (r5 != r0) goto L41
            return r0
        L41:
            r0 = r3
            r3 = r1
            r1 = r12
            r12 = r5
        L45:
            io.ktor.utils.io.core.Input r12 = (io.ktor.utils.io.core.Input) r12     // Catch: java.lang.Throwable -> L4e
            r4 = 0
            r5 = 2
            java.lang.String r2 = io.ktor.utils.io.core.StringsKt.readText$default(r12, r0, r4, r5, r2)     // Catch: java.lang.Throwable -> L4e
            goto L58
        L4e:
            r0 = move-exception
            r12 = r1
            r1 = r3
            r3 = r10
            goto L55
        L53:
            r0 = move-exception
            r3 = r10
        L55:
            r10 = r3
            r3 = r1
            r1 = r12
        L58:
            if (r2 != 0) goto L5d
            java.lang.String r2 = "[request body omitted]"
        L5d:
            java.lang.StringBuilder r12 = r3.$requestLog
            java.lang.String r0 = "BODY START"
            java.lang.StringBuilder r12 = r12.append(r0)
            java.lang.String r0 = "append(value)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r0)
            r4 = 10
            java.lang.StringBuilder r12 = r12.append(r4)
            java.lang.String r5 = "append('\\n')"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r5)
            java.lang.StringBuilder r12 = r3.$requestLog
            java.lang.StringBuilder r12 = r12.append(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r0)
            java.lang.StringBuilder r12 = r12.append(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r5)
            java.lang.StringBuilder r12 = r3.$requestLog
            java.lang.String r0 = "BODY END"
            r12.append(r0)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.logging.Logging$logRequestBody$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
