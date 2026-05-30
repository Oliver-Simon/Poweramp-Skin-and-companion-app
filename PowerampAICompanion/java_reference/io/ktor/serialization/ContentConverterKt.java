package io.ktor.serialization;

import io.ktor.http.HeaderValue;
import io.ktor.http.Headers;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.slf4j.Marker;

/* compiled from: ContentConverter.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a7\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\u001c\u0010\f\u001a\u00060\tj\u0002`\n*\u00020\r2\f\b\u0002\u0010\u000e\u001a\u00060\tj\u0002`\n\u001a \u0010\u000f\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\n*\u00020\r2\f\b\u0002\u0010\u000e\u001a\u00060\tj\u0002`\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"deserialize", "", "", "Lio/ktor/serialization/ContentConverter;", "body", "Lio/ktor/utils/io/ByteReadChannel;", "typeInfo", "Lio/ktor/util/reflect/TypeInfo;", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "(Ljava/util/List;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/util/reflect/TypeInfo;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "suitableCharset", "Lio/ktor/http/Headers;", "defaultCharset", "suitableCharsetOrNull", "ktor-serialization"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ContentConverterKt {
    public static /* synthetic */ Charset suitableCharset$default(Headers headers, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return suitableCharset(headers, charset);
    }

    public static final Charset suitableCharset(Headers $this$suitableCharset, Charset defaultCharset) {
        Intrinsics.checkNotNullParameter($this$suitableCharset, "<this>");
        Intrinsics.checkNotNullParameter(defaultCharset, "defaultCharset");
        Charset suitableCharsetOrNull = suitableCharsetOrNull($this$suitableCharset, defaultCharset);
        return suitableCharsetOrNull == null ? defaultCharset : suitableCharsetOrNull;
    }

    public static /* synthetic */ Charset suitableCharsetOrNull$default(Headers headers, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return suitableCharsetOrNull(headers, charset);
    }

    public static final Charset suitableCharsetOrNull(Headers $this$suitableCharsetOrNull, Charset defaultCharset) {
        Intrinsics.checkNotNullParameter($this$suitableCharsetOrNull, "<this>");
        Intrinsics.checkNotNullParameter(defaultCharset, "defaultCharset");
        Iterator<HeaderValue> it = HttpHeaderValueParserKt.parseAndSortHeader($this$suitableCharsetOrNull.get(HttpHeaders.INSTANCE.getAcceptCharset())).iterator();
        while (it.hasNext()) {
            String charset = it.next().getValue();
            if (Intrinsics.areEqual(charset, Marker.ANY_MARKER)) {
                return defaultCharset;
            }
            if (Charset.isSupported(charset)) {
                return Charset.forName(charset);
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    @io.ktor.util.InternalAPI
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object deserialize(java.util.List<? extends io.ktor.serialization.ContentConverter> r9, final io.ktor.utils.io.ByteReadChannel r10, final io.ktor.util.reflect.TypeInfo r11, final java.nio.charset.Charset r12, kotlin.coroutines.Continuation<java.lang.Object> r13) {
        /*
            boolean r0 = r13 instanceof io.ktor.serialization.ContentConverterKt$deserialize$1
            if (r0 == 0) goto L14
            r0 = r13
            io.ktor.serialization.ContentConverterKt$deserialize$1 r0 = (io.ktor.serialization.ContentConverterKt$deserialize$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L19
        L14:
            io.ktor.serialization.ContentConverterKt$deserialize$1 r0 = new io.ktor.serialization.ContentConverterKt$deserialize$1
            r0.<init>(r13)
        L19:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            switch(r2) {
                case 0: goto L3c;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2e:
            java.lang.Object r9 = r0.L$1
            io.ktor.util.reflect.TypeInfo r9 = (io.ktor.util.reflect.TypeInfo) r9
            java.lang.Object r10 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r10 = (io.ktor.utils.io.ByteReadChannel) r10
            kotlin.ResultKt.throwOnFailure(r13)
            r11 = r9
            r9 = r13
            goto L67
        L3c:
            kotlin.ResultKt.throwOnFailure(r13)
            r2 = r9
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            kotlinx.coroutines.flow.Flow r9 = kotlinx.coroutines.flow.FlowKt.asFlow(r2)
            r2 = 0
            r5 = r9
            r6 = 0
            r7 = 0
            io.ktor.serialization.ContentConverterKt$deserialize$$inlined$map$1 r8 = new io.ktor.serialization.ContentConverterKt$deserialize$$inlined$map$1
            r8.<init>()
            kotlinx.coroutines.flow.Flow r8 = (kotlinx.coroutines.flow.Flow) r8
            io.ktor.serialization.ContentConverterKt$deserialize$result$2 r9 = new io.ktor.serialization.ContentConverterKt$deserialize$result$2
            r9.<init>(r10, r4)
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            r0.L$0 = r10
            r0.L$1 = r11
            r0.label = r3
            java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.firstOrNull(r8, r9, r0)
            if (r9 != r1) goto L67
            return r1
        L67:
            if (r9 != 0) goto La0
            boolean r9 = r10.isClosedForRead()
            if (r9 != 0) goto L72
            goto La1
        L72:
            kotlin.reflect.KType r9 = r11.getKotlinType()
            r10 = 0
            if (r9 == 0) goto L80
            boolean r9 = r9.isMarkedNullable()
            if (r9 != r3) goto L80
            goto L81
        L80:
            r3 = r10
        L81:
            if (r3 == 0) goto L86
            io.ktor.http.content.NullBody r10 = io.ktor.http.content.NullBody.INSTANCE
            goto La1
        L86:
            io.ktor.serialization.ContentConvertException r9 = new io.ktor.serialization.ContentConvertException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r12 = "No suitable converter found for "
            java.lang.StringBuilder r10 = r10.append(r12)
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            r12 = 2
            r9.<init>(r10, r4, r12, r4)
            throw r9
        La0:
            r10 = r9
        La1:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.serialization.ContentConverterKt.deserialize(java.util.List, io.ktor.utils.io.ByteReadChannel, io.ktor.util.reflect.TypeInfo, java.nio.charset.Charset, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
