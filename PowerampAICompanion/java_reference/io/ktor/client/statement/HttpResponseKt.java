package io.ktor.client.statement;

import io.ktor.client.request.HttpRequest;
import io.ktor.util.InternalAPI;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.Job;

/* compiled from: HttpResponse.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u001a\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a#\u0010\b\u001a\u00020\t*\u00020\u00022\f\b\u0002\u0010\n\u001a\u00060\u000bj\u0002`\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0002H\u0001\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"request", "Lio/ktor/client/request/HttpRequest;", "Lio/ktor/client/statement/HttpResponse;", "getRequest", "(Lio/ktor/client/statement/HttpResponse;)Lio/ktor/client/request/HttpRequest;", "bodyAsChannel", "Lio/ktor/utils/io/ByteReadChannel;", "(Lio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bodyAsText", "", "fallbackCharset", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "(Lio/ktor/client/statement/HttpResponse;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "complete", "", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpResponseKt {
    public static final HttpRequest getRequest(HttpResponse $this$request) {
        Intrinsics.checkNotNullParameter($this$request, "<this>");
        return $this$request.getCall().getRequest();
    }

    @InternalAPI
    public static final void complete(HttpResponse $this$complete) {
        Intrinsics.checkNotNullParameter($this$complete, "<this>");
        CoroutineContext.Element element = $this$complete.getCoroutineContext().get(Job.INSTANCE);
        Intrinsics.checkNotNull(element);
        CompletableJob job = (CompletableJob) element;
        job.complete();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object bodyAsText(io.ktor.client.statement.HttpResponse r7, java.nio.charset.Charset r8, kotlin.coroutines.Continuation<? super java.lang.String> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.client.statement.HttpResponseKt$bodyAsText$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.client.statement.HttpResponseKt$bodyAsText$1 r0 = (io.ktor.client.statement.HttpResponseKt$bodyAsText$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.client.statement.HttpResponseKt$bodyAsText$1 r0 = new io.ktor.client.statement.HttpResponseKt$bodyAsText$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L37;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            r7 = 0
            java.lang.Object r8 = r0.L$0
            java.nio.charset.CharsetDecoder r8 = (java.nio.charset.CharsetDecoder) r8
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r7
            r7 = r9
            goto L71
        L37:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r7
            io.ktor.http.HttpMessage r2 = (io.ktor.http.HttpMessage) r2
            java.nio.charset.Charset r2 = io.ktor.http.HttpMessagePropertiesKt.charset(r2)
            if (r2 != 0) goto L44
            goto L45
        L44:
            r8 = r2
        L45:
            java.nio.charset.CharsetDecoder r8 = r8.newDecoder()
            r2 = 0
            io.ktor.client.call.HttpClientCall r3 = r7.getCall()
            r7 = 0
            java.lang.Class<io.ktor.utils.io.core.Input> r4 = io.ktor.utils.io.core.Input.class
            kotlin.reflect.KType r4 = kotlin.jvm.internal.Reflection.typeOf(r4)
            java.lang.reflect.Type r5 = kotlin.reflect.TypesJVMKt.getJavaType(r4)
            java.lang.Class<io.ktor.utils.io.core.Input> r6 = io.ktor.utils.io.core.Input.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            io.ktor.util.reflect.TypeInfo r7 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r5, r6, r4)
            r0.L$0 = r8
            r4 = 1
            r0.label = r4
            java.lang.Object r7 = r3.bodyNullable(r7, r0)
            if (r7 != r1) goto L71
            return r1
        L71:
            if (r7 == 0) goto L83
            io.ktor.utils.io.core.Input r7 = (io.ktor.utils.io.core.Input) r7
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r1)
            r1 = 2
            r2 = 0
            r3 = 0
            java.lang.String r1 = io.ktor.utils.io.charsets.EncodingKt.decode$default(r8, r7, r3, r1, r2)
            return r1
        L83:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.lang.String r1 = "null cannot be cast to non-null type io.ktor.utils.io.core.Input"
            r7.<init>(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.HttpResponseKt.bodyAsText(io.ktor.client.statement.HttpResponse, java.nio.charset.Charset, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object bodyAsText$default(HttpResponse httpResponse, Charset charset, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return bodyAsText(httpResponse, charset, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object bodyAsChannel(io.ktor.client.statement.HttpResponse r7, kotlin.coroutines.Continuation<? super io.ktor.utils.io.ByteReadChannel> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.client.statement.HttpResponseKt$bodyAsChannel$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.client.statement.HttpResponseKt$bodyAsChannel$1 r0 = (io.ktor.client.statement.HttpResponseKt$bodyAsChannel$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.client.statement.HttpResponseKt$bodyAsChannel$1 r0 = new io.ktor.client.statement.HttpResponseKt$bodyAsChannel$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L33;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            r7 = 0
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r7
            r7 = r8
            goto L5b
        L33:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = 0
            io.ktor.client.call.HttpClientCall r3 = r7.getCall()
            r7 = 0
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r4 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.KType r4 = kotlin.jvm.internal.Reflection.typeOf(r4)
            java.lang.reflect.Type r5 = kotlin.reflect.TypesJVMKt.getJavaType(r4)
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r6 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            io.ktor.util.reflect.TypeInfo r7 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r5, r6, r4)
            r4 = 1
            r0.label = r4
            java.lang.Object r7 = r3.bodyNullable(r7, r0)
            if (r7 != r1) goto L5b
            return r1
        L5b:
            if (r7 == 0) goto L60
            io.ktor.utils.io.ByteReadChannel r7 = (io.ktor.utils.io.ByteReadChannel) r7
            return r7
        L60:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.lang.String r1 = "null cannot be cast to non-null type io.ktor.utils.io.ByteReadChannel"
            r7.<init>(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.HttpResponseKt.bodyAsChannel(io.ktor.client.statement.HttpResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
