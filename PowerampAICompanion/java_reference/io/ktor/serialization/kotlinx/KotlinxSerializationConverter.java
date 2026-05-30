package io.ktor.serialization.kotlinx;

import io.ktor.http.ContentType;
import io.ktor.http.ContentTypesKt;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.http.content.ByteArrayContent;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.TextContent;
import io.ktor.serialization.ContentConverter;
import io.ktor.util.reflect.TypeInfo;
import java.nio.charset.Charset;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.BinaryFormat;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialFormat;
import kotlinx.serialization.StringFormat;

/* compiled from: KotlinxSerializationConverter.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J/\u0010\b\u001a\u0004\u0018\u00010\t2\n\u0010\n\u001a\u00060\u000bj\u0002`\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J5\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\n\u0010\n\u001a\u00060\u000bj\u0002`\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\tH\u0097@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J:\u0010\u0018\u001a\u00020\u00192\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001b2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0014\u001a\u00020\u00152\n\u0010\n\u001a\u00060\u000bj\u0002`\fH\u0002J7\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\n\u0010\n\u001a\u00060\u000bj\u0002`\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\tH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lio/ktor/serialization/kotlinx/KotlinxSerializationConverter;", "Lio/ktor/serialization/ContentConverter;", "format", "Lkotlinx/serialization/SerialFormat;", "(Lkotlinx/serialization/SerialFormat;)V", "extensions", "", "Lio/ktor/serialization/kotlinx/KotlinxSerializationExtension;", "deserialize", "", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "typeInfo", "Lio/ktor/util/reflect/TypeInfo;", "content", "Lio/ktor/utils/io/ByteReadChannel;", "(Ljava/nio/charset/Charset;Lio/ktor/util/reflect/TypeInfo;Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "serialize", "Lio/ktor/http/content/OutgoingContent;", "contentType", "Lio/ktor/http/ContentType;", "value", "(Lio/ktor/http/ContentType;Ljava/nio/charset/Charset;Lio/ktor/util/reflect/TypeInfo;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "serializeContent", "Lio/ktor/http/content/OutgoingContent$ByteArrayContent;", "serializer", "Lkotlinx/serialization/KSerializer;", "serializeNullable", "ktor-serialization-kotlinx"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class KotlinxSerializationConverter implements ContentConverter {
    private final List<KotlinxSerializationExtension> extensions;
    private final SerialFormat format;

    public KotlinxSerializationConverter(SerialFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        this.format = format;
        this.extensions = ExtensionsKt.extensions(this.format);
        if ((this.format instanceof BinaryFormat) || (this.format instanceof StringFormat)) {
        } else {
            throw new IllegalArgumentException(("Only binary and string formats are supported, " + this.format + " is not supported.").toString());
        }
    }

    @Override // io.ktor.serialization.ContentConverter
    @Deprecated(level = DeprecationLevel.WARNING, message = "Please override and use serializeNullable instead", replaceWith = @ReplaceWith(expression = "serializeNullable(charset, typeInfo, contentType, value)", imports = {}))
    public Object serialize(ContentType contentType, Charset charset, TypeInfo typeInfo, Object value, Continuation<? super OutgoingContent> continuation) {
        return serializeNullable(contentType, charset, typeInfo, value, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x008a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x008c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // io.ktor.serialization.ContentConverter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object serializeNullable(final io.ktor.http.ContentType r10, final java.nio.charset.Charset r11, final io.ktor.util.reflect.TypeInfo r12, final java.lang.Object r13, kotlin.coroutines.Continuation<? super io.ktor.http.content.OutgoingContent> r14) {
        /*
            r9 = this;
            boolean r0 = r14 instanceof io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$1
            if (r0 == 0) goto L14
            r0 = r14
            io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$1 r0 = (io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$1 r0 = new io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$1
            r0.<init>(r9, r14)
        L19:
            r14 = r0
            java.lang.Object r1 = r14.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r14.label
            switch(r2) {
                case 0: goto L48;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2d:
            java.lang.Object r10 = r14.L$4
            java.lang.Object r11 = r14.L$3
            io.ktor.util.reflect.TypeInfo r11 = (io.ktor.util.reflect.TypeInfo) r11
            java.lang.Object r12 = r14.L$2
            java.nio.charset.Charset r12 = (java.nio.charset.Charset) r12
            java.lang.Object r13 = r14.L$1
            io.ktor.http.ContentType r13 = (io.ktor.http.ContentType) r13
            java.lang.Object r0 = r14.L$0
            io.ktor.serialization.kotlinx.KotlinxSerializationConverter r0 = (io.ktor.serialization.kotlinx.KotlinxSerializationConverter) r0
            kotlin.ResultKt.throwOnFailure(r1)
            r5 = r10
            r7 = r12
            r6 = r13
            r2 = r0
            r10 = r1
            goto L85
        L48:
            kotlin.ResultKt.throwOnFailure(r1)
            r2 = r9
            r6 = r11
            r8 = r13
            r5 = r10
            r7 = r12
            java.util.List<io.ktor.serialization.kotlinx.KotlinxSerializationExtension> r10 = r2.extensions
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            kotlinx.coroutines.flow.Flow r10 = kotlinx.coroutines.flow.FlowKt.asFlow(r10)
            r11 = 0
            r4 = r10
            r12 = 0
            r13 = 0
            io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$$inlined$map$1 r3 = new io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$$inlined$map$1
            r3.<init>()
            kotlinx.coroutines.flow.Flow r3 = (kotlinx.coroutines.flow.Flow) r3
            io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$fromExtension$2 r10 = new io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$fromExtension$2
            r11 = 0
            r10.<init>(r11)
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            r14.L$0 = r2
            r14.L$1 = r5
            r14.L$2 = r6
            r14.L$3 = r7
            r14.L$4 = r8
            r11 = 1
            r14.label = r11
            java.lang.Object r10 = kotlinx.coroutines.flow.FlowKt.firstOrNull(r3, r10, r14)
            if (r10 != r0) goto L81
            return r0
        L81:
            r11 = r7
            r7 = r6
            r6 = r5
            r5 = r8
        L85:
            io.ktor.http.content.OutgoingContent r10 = (io.ktor.http.content.OutgoingContent) r10
            if (r10 == 0) goto L8b
            return r10
        L8b:
            kotlinx.serialization.SerialFormat r10 = r2.format     // Catch: kotlinx.serialization.SerializationException -> L98
            kotlinx.serialization.modules.SerializersModule r10 = r10.getSerializersModule()     // Catch: kotlinx.serialization.SerializationException -> L98
            kotlinx.serialization.KSerializer r10 = io.ktor.serialization.kotlinx.SerializerLookupKt.serializerForTypeInfo(r10, r11)     // Catch: kotlinx.serialization.SerializationException -> L98
            r3 = r10
            goto La4
        L98:
            r0 = move-exception
            kotlinx.serialization.SerialFormat r10 = r2.format
            kotlinx.serialization.modules.SerializersModule r10 = r10.getSerializersModule()
            kotlinx.serialization.KSerializer r10 = io.ktor.serialization.kotlinx.SerializerLookupKt.guessSerializer(r5, r10)
            r3 = r10
        La4:
            kotlinx.serialization.SerialFormat r4 = r2.format
            io.ktor.http.content.OutgoingContent$ByteArrayContent r10 = r2.serializeContent(r3, r4, r5, r6, r7)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.serialization.kotlinx.KotlinxSerializationConverter.serializeNullable(io.ktor.http.ContentType, java.nio.charset.Charset, io.ktor.util.reflect.TypeInfo, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x002a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00d1 A[Catch: all -> 0x011e, TryCatch #0 {all -> 0x011e, blocks: (B:14:0x00ca, B:16:0x00d1, B:18:0x00e5, B:20:0x00e9, B:22:0x00f9, B:23:0x011d), top: B:13:0x00ca }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00e5 A[Catch: all -> 0x011e, TryCatch #0 {all -> 0x011e, blocks: (B:14:0x00ca, B:16:0x00d1, B:18:0x00e5, B:20:0x00e9, B:22:0x00f9, B:23:0x011d), top: B:13:0x00ca }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    @Override // io.ktor.serialization.ContentConverter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object deserialize(final java.nio.charset.Charset r17, io.ktor.util.reflect.TypeInfo r18, final io.ktor.utils.io.ByteReadChannel r19, kotlin.coroutines.Continuation<java.lang.Object> r20) {
        /*
            Method dump skipped, instructions count: 328
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.serialization.kotlinx.KotlinxSerializationConverter.deserialize(java.nio.charset.Charset, io.ktor.util.reflect.TypeInfo, io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final OutgoingContent.ByteArrayContent serializeContent(KSerializer<?> serializer, SerialFormat format, Object value, ContentType contentType, Charset charset) {
        if (format instanceof StringFormat) {
            Intrinsics.checkNotNull(serializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any?>");
            String content = ((StringFormat) format).encodeToString(serializer, value);
            return new TextContent(content, ContentTypesKt.withCharsetIfNeeded(contentType, charset), null, 4, null);
        }
        if (format instanceof BinaryFormat) {
            Intrinsics.checkNotNull(serializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<kotlin.Any?>");
            byte[] content2 = ((BinaryFormat) format).encodeToByteArray(serializer, value);
            return new ByteArrayContent(content2, contentType, null, 4, null);
        }
        throw new IllegalStateException(("Unsupported format " + format).toString());
    }
}
