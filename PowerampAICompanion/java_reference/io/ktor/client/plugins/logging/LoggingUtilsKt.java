package io.ktor.client.plugins.logging;

import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.StringsKt;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoggingUtils.kt */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a/\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a2\u0010\n\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0000\u001a \u0010\u0012\u001a\u00020\u0001*\u00060\u0013j\u0002`\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0000\u001a>\u0010\u0018\u001a\u00020\u0001*\u00060\u0013j\u0002`\u00142\u001e\u0010\u0019\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00100\u001b0\u001a2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0000\u001a#\u0010\u001c\u001a\u0004\u0018\u00010\u0016*\u00020\b2\n\u0010\u001d\u001a\u00060\u001ej\u0002`\u001fH\u0080Hø\u0001\u0000¢\u0006\u0002\u0010 \u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"logResponseBody", "", "log", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "contentType", "Lio/ktor/http/ContentType;", "content", "Lio/ktor/utils/io/ByteReadChannel;", "(Ljava/lang/StringBuilder;Lio/ktor/http/ContentType;Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logResponseHeader", "response", "Lio/ktor/client/statement/HttpResponse;", "level", "Lio/ktor/client/plugins/logging/LogLevel;", "sanitizedHeaders", "", "Lio/ktor/client/plugins/logging/SanitizedHeader;", "logHeader", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "key", "", "value", "logHeaders", TrackProviderConsts.COLUMN_HEADERS, "", "", "tryReadText", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-logging"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class LoggingUtilsKt {
    public static final void logHeaders(Appendable $this$logHeaders, Set<? extends Map.Entry<String, ? extends List<String>>> headers, List<SanitizedHeader> sanitizedHeaders) {
        Object element$iv;
        Intrinsics.checkNotNullParameter($this$logHeaders, "<this>");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(sanitizedHeaders, "sanitizedHeaders");
        Iterable $this$sortedBy$iv = CollectionsKt.toList(headers);
        Iterable $this$forEach$iv = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: io.ktor.client.plugins.logging.LoggingUtilsKt$logHeaders$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Map.Entry it = (Map.Entry) t;
                String str = (String) it.getKey();
                Map.Entry it2 = (Map.Entry) t2;
                return ComparisonsKt.compareValues(str, (String) it2.getKey());
            }
        });
        for (Object element$iv2 : $this$forEach$iv) {
            Map.Entry entry = (Map.Entry) element$iv2;
            String key = (String) entry.getKey();
            List values = (List) entry.getValue();
            List<SanitizedHeader> $this$firstOrNull$iv = sanitizedHeaders;
            Iterator it = $this$firstOrNull$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    element$iv = it.next();
                    SanitizedHeader it2 = (SanitizedHeader) element$iv;
                    if (it2.getPredicate().invoke(key).booleanValue()) {
                        break;
                    }
                } else {
                    element$iv = null;
                    break;
                }
            }
            SanitizedHeader sanitizedHeader = (SanitizedHeader) element$iv;
            String placeholder = sanitizedHeader != null ? sanitizedHeader.getPlaceholder() : null;
            logHeader($this$logHeaders, key, placeholder == null ? CollectionsKt.joinToString$default(values, "; ", null, null, 0, null, null, 62, null) : placeholder);
        }
    }

    public static final void logHeader(Appendable $this$logHeader, String key, String value) {
        Intrinsics.checkNotNullParameter($this$logHeader, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        Appendable append = $this$logHeader.append("-> " + key + ": " + value);
        Intrinsics.checkNotNullExpressionValue(append, "append(value)");
        Intrinsics.checkNotNullExpressionValue(append.append('\n'), "append('\\n')");
    }

    public static final void logResponseHeader(StringBuilder log, HttpResponse response, LogLevel level, List<SanitizedHeader> sanitizedHeaders) {
        Intrinsics.checkNotNullParameter(log, "log");
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(sanitizedHeaders, "sanitizedHeaders");
        if (level.getInfo()) {
            StringBuilder append = log.append("RESPONSE: " + response.getStatus());
            Intrinsics.checkNotNullExpressionValue(append, "append(value)");
            Intrinsics.checkNotNullExpressionValue(append.append('\n'), "append('\\n')");
            StringBuilder append2 = log.append("METHOD: " + response.getCall().getRequest().getMethod());
            Intrinsics.checkNotNullExpressionValue(append2, "append(value)");
            Intrinsics.checkNotNullExpressionValue(append2.append('\n'), "append('\\n')");
            StringBuilder append3 = log.append("FROM: " + response.getCall().getRequest().getUrl());
            Intrinsics.checkNotNullExpressionValue(append3, "append(value)");
            Intrinsics.checkNotNullExpressionValue(append3.append('\n'), "append('\\n')");
        }
        if (level.getHeaders()) {
            StringBuilder append4 = log.append("COMMON HEADERS");
            Intrinsics.checkNotNullExpressionValue(append4, "append(value)");
            Intrinsics.checkNotNullExpressionValue(append4.append('\n'), "append('\\n')");
            logHeaders(log, response.getHeaders().entries(), sanitizedHeaders);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object tryReadText(io.ktor.utils.io.ByteReadChannel r8, java.nio.charset.Charset r9, kotlin.coroutines.Continuation<? super java.lang.String> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.client.plugins.logging.LoggingUtilsKt$tryReadText$1
            if (r0 == 0) goto L14
            r0 = r10
            io.ktor.client.plugins.logging.LoggingUtilsKt$tryReadText$1 r0 = (io.ktor.client.plugins.logging.LoggingUtilsKt$tryReadText$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            io.ktor.client.plugins.logging.LoggingUtilsKt$tryReadText$1 r0 = new io.ktor.client.plugins.logging.LoggingUtilsKt$tryReadText$1
            r0.<init>(r10)
        L19:
            r4 = r0
            java.lang.Object r10 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r7 = 0
            switch(r1) {
                case 0: goto L38;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2e:
            r8 = 0
            java.lang.Object r9 = r4.L$0
            java.nio.charset.Charset r9 = (java.nio.charset.Charset) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L56
            r2 = r10
            goto L4d
        L38:
            kotlin.ResultKt.throwOnFailure(r10)
            r1 = r8
            r8 = 0
            r4.L$0 = r9     // Catch: java.lang.Throwable -> L56
            r2 = 1
            r4.label = r2     // Catch: java.lang.Throwable -> L56
            r2 = 0
            r5 = 1
            r6 = 0
            java.lang.Object r2 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r1, r2, r4, r5, r6)     // Catch: java.lang.Throwable -> L56
            if (r2 != r0) goto L4d
            return r0
        L4d:
            io.ktor.utils.io.core.Input r2 = (io.ktor.utils.io.core.Input) r2     // Catch: java.lang.Throwable -> L56
            r0 = 0
            r1 = 2
            java.lang.String r7 = io.ktor.utils.io.core.StringsKt.readText$default(r2, r9, r0, r1, r7)     // Catch: java.lang.Throwable -> L56
            goto L58
        L56:
            r0 = move-exception
        L58:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.logging.LoggingUtilsKt.tryReadText(io.ktor.utils.io.ByteReadChannel, java.nio.charset.Charset, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object tryReadText$$forInline(ByteReadChannel $this$tryReadText, Charset charset, Continuation<? super String> continuation) {
        try {
            return StringsKt.readText$default((Input) ByteReadChannel.DefaultImpls.readRemaining$default($this$tryReadText, 0L, continuation, 1, null), charset, 0, 2, (Object) null);
        } catch (Throwable th) {
            return null;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object logResponseBody(java.lang.StringBuilder r17, io.ktor.http.ContentType r18, io.ktor.utils.io.ByteReadChannel r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof io.ktor.client.plugins.logging.LoggingUtilsKt$logResponseBody$1
            if (r1 == 0) goto L16
            r1 = r0
            io.ktor.client.plugins.logging.LoggingUtilsKt$logResponseBody$1 r1 = (io.ktor.client.plugins.logging.LoggingUtilsKt$logResponseBody$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L16
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L1b
        L16:
            io.ktor.client.plugins.logging.LoggingUtilsKt$logResponseBody$1 r1 = new io.ktor.client.plugins.logging.LoggingUtilsKt$logResponseBody$1
            r1.<init>(r0)
        L1b:
            java.lang.Object r2 = r1.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 0
            java.lang.String r5 = "append('\\n')"
            r6 = 10
            java.lang.String r7 = "append(value)"
            switch(r3) {
                case 0: goto L47;
                case 1: goto L35;
                default: goto L2d;
            }
        L2d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L35:
            r3 = 0
            r8 = 0
            java.lang.Object r0 = r1.L$1
            java.nio.charset.Charset r0 = (java.nio.charset.Charset) r0
            java.lang.Object r9 = r1.L$0
            java.lang.StringBuilder r9 = (java.lang.StringBuilder) r9
            kotlin.ResultKt.throwOnFailure(r2)     // Catch: java.lang.Throwable -> L44
            r12 = r2
            goto Lab
        L44:
            r0 = move-exception
            goto Lb7
        L47:
            kotlin.ResultKt.throwOnFailure(r2)
            r3 = r17
            r8 = r19
            r9 = r18
            r10 = 0
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "BODY Content-Type: "
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.StringBuilder r11 = r11.append(r9)
            java.lang.String r11 = r11.toString()
            java.lang.StringBuilder r11 = r3.append(r11)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r7)
            java.lang.StringBuilder r11 = r11.append(r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r5)
            java.lang.String r11 = "BODY START"
            java.lang.StringBuilder r11 = r3.append(r11)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r7)
            java.lang.StringBuilder r11 = r11.append(r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r5)
            if (r9 == 0) goto L8f
            r11 = r9
            io.ktor.http.HeaderValueWithParameters r11 = (io.ktor.http.HeaderValueWithParameters) r11
            java.nio.charset.Charset r9 = io.ktor.http.ContentTypesKt.charset(r11)
            if (r9 != 0) goto L91
        L8f:
            java.nio.charset.Charset r9 = kotlin.text.Charsets.UTF_8
        L91:
            r11 = r8
            r14 = r1
            r8 = 0
            r1.L$0 = r3     // Catch: java.lang.Throwable -> Lb4
            r1.L$1 = r9     // Catch: java.lang.Throwable -> Lb4
            r12 = 1
            r1.label = r12     // Catch: java.lang.Throwable -> Lb4
            r12 = 0
            r15 = 1
            r16 = 0
            java.lang.Object r12 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r11, r12, r14, r15, r16)     // Catch: java.lang.Throwable -> Lb4
            if (r12 != r0) goto La8
            return r0
        La8:
            r0 = r9
            r9 = r3
            r3 = r10
        Lab:
            io.ktor.utils.io.core.Input r12 = (io.ktor.utils.io.core.Input) r12     // Catch: java.lang.Throwable -> L44
            r10 = 0
            r11 = 2
            java.lang.String r4 = io.ktor.utils.io.core.StringsKt.readText$default(r12, r0, r10, r11, r4)     // Catch: java.lang.Throwable -> L44
            goto Lb8
        Lb4:
            r0 = move-exception
            r9 = r3
            r3 = r10
        Lb7:
        Lb8:
            if (r4 != 0) goto Lbd
            java.lang.String r4 = "[response body omitted]"
        Lbd:
            java.lang.StringBuilder r0 = r9.append(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r7)
            java.lang.StringBuilder r0 = r0.append(r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r5)
            java.lang.String r0 = "BODY END"
            r9.append(r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.logging.LoggingUtilsKt.logResponseBody(java.lang.StringBuilder, io.ktor.http.ContentType, io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
