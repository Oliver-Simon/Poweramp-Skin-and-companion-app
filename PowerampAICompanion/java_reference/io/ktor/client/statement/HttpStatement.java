package io.ktor.client.statement;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.engine.HttpClientEngineCapabilityKt;
import io.ktor.client.plugins.HttpClientPlugin;
import io.ktor.client.plugins.HttpClientPluginKt;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.utils.ExceptionUtilsJvmKt;
import io.ktor.util.reflect.TypeInfoJvmKt;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: HttpStatement.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\u000b\u001a\u0002H\f\"\u0006\b\u0000\u0010\f\u0018\u0001H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\rJT\u0010\u000b\u001a\u0002H\u000e\"\u0006\b\u0000\u0010\f\u0018\u0001\"\u0004\b\u0001\u0010\u000e23\b\u0004\u0010\u000f\u001a-\b\u0001\u0012\u0013\u0012\u0011H\f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0010H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0011\u0010\u0018\u001a\u00020\u0019H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rJJ\u0010\u0018\u001a\u0002H\f\"\u0004\b\u0000\u0010\f21\u0010\u000f\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\f0\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u0011\u0010\u001a\u001a\u00020\u0019H\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0015\u0010\u001d\u001a\u00020\u0017*\u00020\u0019H\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u00020\u00058\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"Lio/ktor/client/statement/HttpStatement;", "", "builder", "Lio/ktor/client/request/HttpRequestBuilder;", "client", "Lio/ktor/client/HttpClient;", "(Lio/ktor/client/request/HttpRequestBuilder;Lio/ktor/client/HttpClient;)V", "getClient$annotations", "()V", "getClient", "()Lio/ktor/client/HttpClient;", "body", ExifInterface.GPS_DIRECTION_TRUE, "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "R", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "response", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkCapabilities", "", "execute", "Lio/ktor/client/statement/HttpResponse;", "executeUnsafe", "toString", "", "cleanup", "(Lio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpStatement {
    private final HttpRequestBuilder builder;
    private final HttpClient client;

    public static /* synthetic */ void getClient$annotations() {
    }

    public HttpStatement(HttpRequestBuilder builder, HttpClient client) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(client, "client");
        this.builder = builder;
        this.client = client;
        checkCapabilities();
    }

    public final HttpClient getClient() {
        return this.client;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x009d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00af A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x008d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final <T> java.lang.Object execute(kotlin.jvm.functions.Function2<? super io.ktor.client.statement.HttpResponse, ? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r10, kotlin.coroutines.Continuation<? super T> r11) {
        /*
            Method dump skipped, instructions count: 204
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.HttpStatement.execute(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object execute(Continuation<? super HttpResponse> continuation) {
        return execute(new HttpStatement$execute$4(null), continuation);
    }

    public final /* synthetic */ <T> Object body(Continuation<? super T> continuation) {
        try {
            HttpResponse response = (HttpResponse) executeUnsafe(null);
            try {
                HttpClientCall call = response.getCall();
                Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
                Type reifiedType$iv$iv = TypesJVMKt.getJavaType((KType) null);
                Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                Object bodyNullable = call.bodyNullable(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv, Reflection.getOrCreateKotlinClass(Object.class), null), null);
                Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
                Object obj = bodyNullable;
                return bodyNullable;
            } finally {
                HttpResponseKt.complete(response);
            }
        } catch (CancellationException cause$iv) {
            throw ExceptionUtilsJvmKt.unwrapCancellationException(cause$iv);
        }
    }

    public final /* synthetic */ <T, R> Object body(Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
        try {
            HttpResponse response = (HttpResponse) executeUnsafe(null);
            try {
                HttpClientCall call = response.getCall();
                Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
                Type reifiedType$iv$iv = TypesJVMKt.getJavaType((KType) null);
                Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                Object result = call.bodyNullable(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv, Reflection.getOrCreateKotlinClass(Object.class), null), null);
                Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
                Object obj = result;
                return function2.invoke(result, null);
            } finally {
                cleanup(response, null);
            }
        } catch (CancellationException cause$iv) {
            throw ExceptionUtilsJvmKt.unwrapCancellationException(cause$iv);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object executeUnsafe(kotlin.coroutines.Continuation<? super io.ktor.client.statement.HttpResponse> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof io.ktor.client.statement.HttpStatement$executeUnsafe$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.client.statement.HttpStatement$executeUnsafe$1 r0 = (io.ktor.client.statement.HttpStatement$executeUnsafe$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.client.statement.HttpStatement$executeUnsafe$1 r0 = new io.ktor.client.statement.HttpStatement$executeUnsafe$1
            r0.<init>(r8, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L35;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L2c:
            r1 = 0
            r2 = 0
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.util.concurrent.CancellationException -> L33
            r6 = r9
            goto L55
        L33:
            r2 = move-exception
            goto L5e
        L35:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r8
            r3 = 0
            r4 = 0
            io.ktor.client.request.HttpRequestBuilder r5 = new io.ktor.client.request.HttpRequestBuilder     // Catch: java.util.concurrent.CancellationException -> L5c
            r5.<init>()     // Catch: java.util.concurrent.CancellationException -> L5c
            io.ktor.client.request.HttpRequestBuilder r6 = r2.builder     // Catch: java.util.concurrent.CancellationException -> L5c
            io.ktor.client.request.HttpRequestBuilder r5 = r5.takeFromWithExecutionContext(r6)     // Catch: java.util.concurrent.CancellationException -> L5c
            io.ktor.client.HttpClient r6 = r2.client     // Catch: java.util.concurrent.CancellationException -> L5c
            r7 = 1
            r0.label = r7     // Catch: java.util.concurrent.CancellationException -> L5c
            java.lang.Object r6 = r6.execute$ktor_client_core(r5, r0)     // Catch: java.util.concurrent.CancellationException -> L5c
            if (r6 != r1) goto L53
            return r1
        L53:
            r1 = r3
            r2 = r4
        L55:
            io.ktor.client.call.HttpClientCall r6 = (io.ktor.client.call.HttpClientCall) r6     // Catch: java.util.concurrent.CancellationException -> L33
            io.ktor.client.statement.HttpResponse r3 = r6.getResponse()     // Catch: java.util.concurrent.CancellationException -> L33
            return r3
        L5c:
            r2 = move-exception
            r1 = r3
        L5e:
            r3 = r2
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            java.lang.Throwable r3 = io.ktor.client.utils.ExceptionUtilsJvmKt.unwrapCancellationException(r3)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.HttpStatement.executeUnsafe(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object cleanup(io.ktor.client.statement.HttpResponse r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.client.statement.HttpStatement$cleanup$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.client.statement.HttpStatement$cleanup$1 r0 = (io.ktor.client.statement.HttpStatement$cleanup$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.client.statement.HttpStatement$cleanup$1 r0 = new io.ktor.client.statement.HttpStatement$cleanup$1
            r0.<init>(r6, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L35;
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
            java.lang.Object r1 = r0.L$0
            kotlinx.coroutines.CompletableJob r1 = (kotlinx.coroutines.CompletableJob) r1
            kotlin.ResultKt.throwOnFailure(r8)
            goto L66
        L35:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlin.coroutines.CoroutineContext r2 = r7.getCoroutineContext()
            kotlinx.coroutines.Job$Key r3 = kotlinx.coroutines.Job.INSTANCE
            kotlin.coroutines.CoroutineContext$Key r3 = (kotlin.coroutines.CoroutineContext.Key) r3
            kotlin.coroutines.CoroutineContext$Element r2 = r2.get(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            kotlinx.coroutines.CompletableJob r2 = (kotlinx.coroutines.CompletableJob) r2
            r3 = r2
            r4 = 0
            r3.complete()
            io.ktor.utils.io.ByteReadChannel r5 = r7.getContent()     // Catch: java.lang.Throwable -> L58
            io.ktor.utils.io.ByteReadChannelKt.cancel(r5)     // Catch: java.lang.Throwable -> L58
            goto L59
        L58:
            r7 = move-exception
        L59:
            r0.L$0 = r2
            r7 = 1
            r0.label = r7
            java.lang.Object r7 = r3.join(r0)
            if (r7 != r1) goto L65
            return r1
        L65:
            r7 = r4
        L66:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.HttpStatement.cleanup(io.ktor.client.statement.HttpResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void checkCapabilities() {
        Iterable keySet;
        Map map = (Map) this.builder.getAttributes().getOrNull(HttpClientEngineCapabilityKt.getENGINE_CAPABILITIES_KEY());
        if (map != null && (keySet = map.keySet()) != null) {
            Iterable $this$filterIsInstance$iv = keySet;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filterIsInstance$iv) {
                if (element$iv$iv instanceof HttpClientPlugin) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            Iterable $this$forEach$iv = (List) destination$iv$iv;
            for (Object element$iv : $this$forEach$iv) {
                HttpClientPlugin it = (HttpClientPlugin) element$iv;
                if (HttpClientPluginKt.pluginOrNull(this.client, it) == null) {
                    throw new IllegalArgumentException(("Consider installing " + it + " plugin because the request requires it to be installed").toString());
                }
            }
        }
    }

    public String toString() {
        return "HttpStatement[" + this.builder.getUrl() + AbstractJsonLexerKt.END_LIST;
    }
}
