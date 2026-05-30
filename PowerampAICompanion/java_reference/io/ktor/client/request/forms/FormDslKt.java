package io.ktor.client.request.forms;

import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import io.ktor.http.HeaderValueWithParametersKt;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.PartData;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.ByteReadPacketExtensionsKt;
import io.ktor.utils.io.core.Input;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: formDsl.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u001a\u0010\b\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\n0\t\"\u0006\u0012\u0002\b\u00030\n¢\u0006\u0002\u0010\u000b\u001a[\u0010\f\u001a\u00020\u0006*\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0019\b\u0004\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0004 \u0001¢\u0006\u0002\u0010\u0015\u001a]\u0010\f\u001a\u00020\u0006*\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0005 \u0001¢\u0006\u0002\u0010\u0019\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001a"}, d2 = {"formData", "", "Lio/ktor/http/content/PartData;", "block", "Lkotlin/Function1;", "Lio/ktor/client/request/forms/FormBuilder;", "", "Lkotlin/ExtensionFunctionType;", "values", "", "Lio/ktor/client/request/forms/FormPart;", "([Lio/ktor/client/request/forms/FormPart;)Ljava/util/List;", "append", "key", "", TrackProviderConsts.COLUMN_HEADERS, "Lio/ktor/http/Headers;", ContentDisposition.Parameters.Size, "", "bodyBuilder", "Lio/ktor/utils/io/core/BytePacketBuilder;", "(Lio/ktor/client/request/forms/FormBuilder;Ljava/lang/String;Lio/ktor/http/Headers;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", ContentDisposition.Parameters.FileName, "contentType", "Lio/ktor/http/ContentType;", "(Lio/ktor/client/request/forms/FormBuilder;Ljava/lang/String;Ljava/lang/String;Lio/ktor/http/ContentType;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class FormDslKt {
    public static final List<PartData> formData(FormPart<?>... values) {
        PartData binaryChannelItem;
        Intrinsics.checkNotNullParameter(values, "values");
        List result = new ArrayList();
        int length = values.length;
        int i = 0;
        int i2 = 0;
        while (i2 < length) {
            FormPart<?> formPart = values[i2];
            String key = formPart.getKey();
            final Object value = formPart.component2();
            Headers headers = formPart.getHeaders();
            HeadersBuilder partHeaders = new HeadersBuilder(i, 1, null);
            List result2 = result;
            partHeaders.append(HttpHeaders.INSTANCE.getContentDisposition(), "form-data; name=" + HeaderValueWithParametersKt.escapeIfNeeded(key));
            partHeaders.appendAll(headers);
            if (value instanceof String) {
                binaryChannelItem = new PartData.FormItem((String) value, new Function0<Unit>() { // from class: io.ktor.client.request.forms.FormDslKt$formData$1$part$1
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }
                }, partHeaders.build());
            } else if (value instanceof Number) {
                binaryChannelItem = new PartData.FormItem(value.toString(), new Function0<Unit>() { // from class: io.ktor.client.request.forms.FormDslKt$formData$1$part$2
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }
                }, partHeaders.build());
            } else if (value instanceof Boolean) {
                binaryChannelItem = new PartData.FormItem(value.toString(), new Function0<Unit>() { // from class: io.ktor.client.request.forms.FormDslKt$formData$1$part$3
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }
                }, partHeaders.build());
            } else if (value instanceof byte[]) {
                partHeaders.append(HttpHeaders.INSTANCE.getContentLength(), String.valueOf(((byte[]) value).length));
                binaryChannelItem = new PartData.BinaryItem(new Function0<Input>() { // from class: io.ktor.client.request.forms.FormDslKt$formData$1$part$4
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Input invoke() {
                        final byte[] array$iv = (byte[]) value;
                        int length$iv = array$iv.length;
                        ByteBuffer wrap = ByteBuffer.wrap(array$iv, 0, length$iv);
                        Intrinsics.checkNotNullExpressionValue(wrap, "wrap(array, offset, length)");
                        return ByteReadPacketExtensionsKt.ByteReadPacket(wrap, new Function1<ByteBuffer, Unit>() { // from class: io.ktor.client.request.forms.FormDslKt$formData$1$part$4$invoke$$inlined$ByteReadPacket$default$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(ByteBuffer byteBuffer) {
                                invoke2(byteBuffer);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(ByteBuffer it) {
                                Intrinsics.checkNotNullParameter(it, "it");
                                byte[] bArr = array$iv;
                            }
                        });
                    }
                }, new Function0<Unit>() { // from class: io.ktor.client.request.forms.FormDslKt$formData$1$part$5
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }
                }, partHeaders.build());
            } else if (value instanceof ByteReadPacket) {
                partHeaders.append(HttpHeaders.INSTANCE.getContentLength(), String.valueOf(((ByteReadPacket) value).getRemaining()));
                binaryChannelItem = new PartData.BinaryItem(new Function0<Input>() { // from class: io.ktor.client.request.forms.FormDslKt$formData$1$part$6
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Input invoke() {
                        return ((ByteReadPacket) value).copy();
                    }
                }, new Function0<Unit>() { // from class: io.ktor.client.request.forms.FormDslKt$formData$1$part$7
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        ((ByteReadPacket) value).close();
                    }
                }, partHeaders.build());
            } else if (value instanceof InputProvider) {
                Long size = ((InputProvider) value).getSize();
                if (size != null) {
                    partHeaders.append(HttpHeaders.INSTANCE.getContentLength(), size.toString());
                }
                binaryChannelItem = new PartData.BinaryItem(((InputProvider) value).getBlock(), new Function0<Unit>() { // from class: io.ktor.client.request.forms.FormDslKt$formData$1$part$8
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }
                }, partHeaders.build());
            } else {
                if (!(value instanceof ChannelProvider)) {
                    if (value instanceof Input) {
                        throw new IllegalStateException(("Can't use [Input] as part of form: " + value + ". Consider using [InputProvider] instead.").toString());
                    }
                    throw new IllegalStateException(("Unknown form content type: " + value).toString());
                }
                Long size2 = ((ChannelProvider) value).getSize();
                if (size2 != null) {
                    partHeaders.append(HttpHeaders.INSTANCE.getContentLength(), size2.toString());
                }
                binaryChannelItem = new PartData.BinaryChannelItem(((ChannelProvider) value).getBlock(), partHeaders.build());
            }
            PartData part = binaryChannelItem;
            result2.add(part);
            i2++;
            result = result2;
            i = 0;
        }
        return result;
    }

    public static final List<PartData> formData(Function1<? super FormBuilder, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        FormBuilder formBuilder = new FormBuilder();
        block.invoke(formBuilder);
        Collection $this$toTypedArray$iv = formBuilder.build$ktor_client_core();
        FormPart[] formPartArr = (FormPart[]) $this$toTypedArray$iv.toArray(new FormPart[0]);
        return formData((FormPart<?>[]) Arrays.copyOf(formPartArr, formPartArr.length));
    }

    public static /* synthetic */ void append$default(FormBuilder $this$append_u24default, String key, Headers headers, Long size, Function1 bodyBuilder, int i, Object obj) {
        if ((i & 2) != 0) {
            headers = Headers.INSTANCE.getEmpty();
        }
        if ((i & 4) != 0) {
            size = null;
        }
        Intrinsics.checkNotNullParameter($this$append_u24default, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(bodyBuilder, "bodyBuilder");
        $this$append_u24default.append(new FormPart(key, new InputProvider(size, new FormDslKt$append$2(bodyBuilder)), headers));
    }

    public static final void append(FormBuilder $this$append, String key, Headers headers, Long size, Function1<? super BytePacketBuilder, Unit> bodyBuilder) {
        Intrinsics.checkNotNullParameter($this$append, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(bodyBuilder, "bodyBuilder");
        $this$append.append(new FormPart(key, new InputProvider(size, new FormDslKt$append$2(bodyBuilder)), headers));
    }

    public static /* synthetic */ void append$default(FormBuilder formBuilder, String str, String str2, ContentType contentType, Long l, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            contentType = null;
        }
        if ((i & 8) != 0) {
            l = null;
        }
        append(formBuilder, str, str2, contentType, l, function1);
    }

    public static final void append(FormBuilder $this$append, String key, String filename, ContentType contentType, Long size, Function1<? super BytePacketBuilder, Unit> bodyBuilder) {
        Intrinsics.checkNotNullParameter($this$append, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(filename, "filename");
        Intrinsics.checkNotNullParameter(bodyBuilder, "bodyBuilder");
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, null);
        headersBuilder.set(HttpHeaders.INSTANCE.getContentDisposition(), "filename=" + HeaderValueWithParametersKt.escapeIfNeeded(filename));
        if (contentType != null) {
            headersBuilder.set(HttpHeaders.INSTANCE.getContentType(), contentType.toString());
        }
        Headers headers = headersBuilder.build();
        $this$append.append(new FormPart(key, new InputProvider(size, new FormDslKt$append$2(bodyBuilder)), headers));
    }
}
