package io.ktor.client.request.forms;

import io.ktor.client.request.forms.PreparedPart;
import io.ktor.http.ContentType;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.PartData;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputKt;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: FormDataContent.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001fR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"Lio/ktor/client/request/forms/MultiPartFormDataContent;", "Lio/ktor/http/content/OutgoingContent$WriteChannelContent;", "parts", "", "Lio/ktor/http/content/PartData;", "boundary", "", "contentType", "Lio/ktor/http/ContentType;", "(Ljava/util/List;Ljava/lang/String;Lio/ktor/http/ContentType;)V", "BODY_OVERHEAD_SIZE", "", "BOUNDARY_BYTES", "", "LAST_BOUNDARY_BYTES", "PART_OVERHEAD_SIZE", "getBoundary", "()Ljava/lang/String;", "contentLength", "", "getContentLength", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getContentType", "()Lio/ktor/http/ContentType;", "rawParts", "Lio/ktor/client/request/forms/PreparedPart;", "writeTo", "", "channel", "Lio/ktor/utils/io/ByteWriteChannel;", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class MultiPartFormDataContent extends OutgoingContent.WriteChannelContent {
    private final int BODY_OVERHEAD_SIZE;
    private final byte[] BOUNDARY_BYTES;
    private final byte[] LAST_BOUNDARY_BYTES;
    private final int PART_OVERHEAD_SIZE;
    private final String boundary;
    private final Long contentLength;
    private final ContentType contentType;
    private final List<PreparedPart> rawParts;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ MultiPartFormDataContent(java.util.List r1, java.lang.String r2, io.ktor.http.ContentType r3, int r4, kotlin.jvm.internal.DefaultConstructorMarker r5) {
        /*
            r0 = this;
            r5 = r4 & 2
            if (r5 == 0) goto L8
            java.lang.String r2 = io.ktor.client.request.forms.FormDataContentKt.access$generateBoundary()
        L8:
            r4 = r4 & 4
            if (r4 == 0) goto L18
            io.ktor.http.ContentType$MultiPart r3 = io.ktor.http.ContentType.MultiPart.INSTANCE
            io.ktor.http.ContentType r3 = r3.getFormData()
            java.lang.String r4 = "boundary"
            io.ktor.http.ContentType r3 = r3.withParameter(r4, r2)
        L18:
            r0.<init>(r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.request.forms.MultiPartFormDataContent.<init>(java.util.List, java.lang.String, io.ktor.http.ContentType, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getBoundary() {
        return this.boundary;
    }

    @Override // io.ktor.http.content.OutgoingContent
    public ContentType getContentType() {
        return this.contentType;
    }

    public MultiPartFormDataContent(List<? extends PartData> parts, String boundary, ContentType contentType) {
        byte[] encodeToByteArray;
        byte[] encodeToByteArray2;
        byte[] bArr;
        Iterable $this$map$iv;
        int $i$f$map;
        Iterable $this$mapTo$iv$iv;
        int i;
        PreparedPart channelPart;
        byte[] bArr2;
        byte[] bArr3;
        Intrinsics.checkNotNullParameter(parts, "parts");
        Intrinsics.checkNotNullParameter(boundary, "boundary");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        this.boundary = boundary;
        this.contentType = contentType;
        String $this$toByteArray_u24default$iv = "--" + this.boundary + "\r\n";
        Charset charset$iv = Charsets.UTF_8;
        if (Intrinsics.areEqual(charset$iv, Charsets.UTF_8)) {
            encodeToByteArray = StringsKt.encodeToByteArray($this$toByteArray_u24default$iv);
        } else {
            CharsetEncoder newEncoder = charset$iv.newEncoder();
            Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
            encodeToByteArray = CharsetJVMKt.encodeToByteArray(newEncoder, $this$toByteArray_u24default$iv, 0, $this$toByteArray_u24default$iv.length());
        }
        this.BOUNDARY_BYTES = encodeToByteArray;
        String $this$toByteArray_u24default$iv2 = "--" + this.boundary + "--\r\n";
        Charset charset$iv2 = Charsets.UTF_8;
        if (Intrinsics.areEqual(charset$iv2, Charsets.UTF_8)) {
            encodeToByteArray2 = StringsKt.encodeToByteArray($this$toByteArray_u24default$iv2);
        } else {
            CharsetEncoder newEncoder2 = charset$iv2.newEncoder();
            Intrinsics.checkNotNullExpressionValue(newEncoder2, "charset.newEncoder()");
            encodeToByteArray2 = CharsetJVMKt.encodeToByteArray(newEncoder2, $this$toByteArray_u24default$iv2, 0, $this$toByteArray_u24default$iv2.length());
        }
        this.LAST_BOUNDARY_BYTES = encodeToByteArray2;
        this.BODY_OVERHEAD_SIZE = this.LAST_BOUNDARY_BYTES.length;
        bArr = FormDataContentKt.RN_BYTES;
        this.PART_OVERHEAD_SIZE = (bArr.length * 2) + this.BOUNDARY_BYTES.length;
        List<? extends PartData> $this$map$iv2 = parts;
        int $i$f$map2 = 0;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        Iterable $this$mapTo$iv$iv2 = $this$map$iv2;
        Iterator it = $this$mapTo$iv$iv2.iterator();
        while (it.hasNext()) {
            Object item$iv$iv = it.next();
            PartData part = (PartData) item$iv$iv;
            BytePacketBuilder headersBuilder = new BytePacketBuilder(null, 1, null);
            for (Map.Entry<String, List<String>> entry : part.getHeaders().entries()) {
                String key = entry.getKey();
                List values = entry.getValue();
                io.ktor.utils.io.core.StringsKt.writeText$default(headersBuilder, key + ": " + CollectionsKt.joinToString$default(values, "; ", null, null, 0, null, null, 62, null), 0, 0, (Charset) null, 14, (Object) null);
                bArr3 = FormDataContentKt.RN_BYTES;
                OutputKt.writeFully$default((Output) headersBuilder, bArr3, 0, 0, 6, (Object) null);
                it = it;
            }
            Iterator it2 = it;
            String str = part.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
            Long bodySize = str != null ? Long.valueOf(Long.parseLong(str)) : null;
            if (part instanceof PartData.FileItem) {
                byte[] headers = io.ktor.utils.io.core.StringsKt.readBytes$default(headersBuilder.build(), 0, 1, null);
                channelPart = new PreparedPart.InputPart(headers, ((PartData.FileItem) part).getProvider(), bodySize != null ? Long.valueOf(bodySize.longValue() + this.PART_OVERHEAD_SIZE + headers.length) : null);
                $this$map$iv = $this$map$iv2;
                $i$f$map = $i$f$map2;
                $this$mapTo$iv$iv = $this$mapTo$iv$iv2;
                i = 0;
            } else if (part instanceof PartData.BinaryItem) {
                byte[] headers2 = io.ktor.utils.io.core.StringsKt.readBytes$default(headersBuilder.build(), 0, 1, null);
                channelPart = new PreparedPart.InputPart(headers2, ((PartData.BinaryItem) part).getProvider(), bodySize != null ? Long.valueOf(bodySize.longValue() + this.PART_OVERHEAD_SIZE + headers2.length) : null);
                $this$map$iv = $this$map$iv2;
                $i$f$map = $i$f$map2;
                $this$mapTo$iv$iv = $this$mapTo$iv$iv2;
                i = 0;
            } else if (!(part instanceof PartData.FormItem)) {
                $this$map$iv = $this$map$iv2;
                $i$f$map = $i$f$map2;
                $this$mapTo$iv$iv = $this$mapTo$iv$iv2;
                if (part instanceof PartData.BinaryChannelItem) {
                    i = 0;
                    byte[] headers3 = io.ktor.utils.io.core.StringsKt.readBytes$default(headersBuilder.build(), 0, 1, null);
                    channelPart = new PreparedPart.ChannelPart(headers3, ((PartData.BinaryChannelItem) part).getProvider(), bodySize != null ? Long.valueOf(bodySize.longValue() + this.PART_OVERHEAD_SIZE + headers3.length) : null);
                } else {
                    throw new NoWhenBranchMatchedException();
                }
            } else {
                BytePacketBuilder builder$iv = new BytePacketBuilder(null, 1, null);
                try {
                    io.ktor.utils.io.core.StringsKt.writeText$default(builder$iv, ((PartData.FormItem) part).getValue(), 0, 0, (Charset) null, 14, (Object) null);
                    final byte[] bytes = io.ktor.utils.io.core.StringsKt.readBytes$default(builder$iv.build(), 0, 1, null);
                    Function0 provider = new Function0<ByteReadPacket>() { // from class: io.ktor.client.request.forms.MultiPartFormDataContent$rawParts$1$provider$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final ByteReadPacket invoke() {
                            byte[] bArr4 = bytes;
                            BytePacketBuilder builder$iv2 = new BytePacketBuilder(null, 1, null);
                            try {
                                OutputKt.writeFully$default((Output) builder$iv2, bArr4, 0, 0, 6, (Object) null);
                                return builder$iv2.build();
                            } catch (Throwable t$iv) {
                                builder$iv2.release();
                                throw t$iv;
                            }
                        }
                    };
                    if (bodySize == null) {
                        io.ktor.utils.io.core.StringsKt.writeText$default(headersBuilder, HttpHeaders.INSTANCE.getContentLength() + ": " + bytes.length, 0, 0, (Charset) null, 14, (Object) null);
                        bArr2 = FormDataContentKt.RN_BYTES;
                        OutputKt.writeFully$default((Output) headersBuilder, bArr2, 0, 0, 6, (Object) null);
                    }
                    $this$map$iv = $this$map$iv2;
                    byte[] headers4 = io.ktor.utils.io.core.StringsKt.readBytes$default(headersBuilder.build(), 0, 1, null);
                    $i$f$map = $i$f$map2;
                    $this$mapTo$iv$iv = $this$mapTo$iv$iv2;
                    channelPart = new PreparedPart.InputPart(headers4, provider, Long.valueOf(bytes.length + this.PART_OVERHEAD_SIZE + headers4.length));
                    i = 0;
                } catch (Throwable t$iv) {
                    builder$iv.release();
                    throw t$iv;
                }
            }
            destination$iv$iv.add(channelPart);
            $i$f$map2 = $i$f$map;
            $this$map$iv2 = $this$map$iv;
            it = it2;
            $this$mapTo$iv$iv2 = $this$mapTo$iv$iv;
        }
        this.rawParts = (List) destination$iv$iv;
        Long rawLength = 0L;
        Iterator<PreparedPart> it3 = this.rawParts.iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            Long size = it3.next().getSize();
            if (size == null) {
                rawLength = null;
                break;
            }
            rawLength = rawLength != null ? Long.valueOf(rawLength.longValue() + size.longValue()) : null;
        }
        this.contentLength = rawLength != null ? Long.valueOf(rawLength.longValue() + this.BODY_OVERHEAD_SIZE) : rawLength;
    }

    @Override // io.ktor.http.content.OutgoingContent
    public Long getContentLength() {
        return this.contentLength;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:1|(2:3|(4:5|6|7|8))|110|6|7|8|(3:(0)|(1:57)|(1:65))) */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x00c8, code lost:
    
        r13 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01dd, code lost:
    
        r13.close(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01e5, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01e6, code lost:
    
        io.ktor.utils.io.ByteWriteChannelKt.close(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x01e9, code lost:
    
        throw r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x005c, code lost:
    
        r1 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x005d, code lost:
    
        r13 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x01dc, code lost:
    
        r1 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x00c7, code lost:
    
        r1 = th;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00dc A[Catch: all -> 0x01dc, TryCatch #8 {all -> 0x01dc, blocks: (B:13:0x0031, B:21:0x00d6, B:23:0x00dc, B:27:0x00f9, B:30:0x010f, B:47:0x01aa, B:77:0x01c5, B:98:0x00d0), top: B:7:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x010e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0124 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x012e A[Catch: all -> 0x00c7, TRY_LEAVE, TryCatch #4 {all -> 0x00c7, blocks: (B:34:0x012a, B:36:0x012e, B:45:0x015e, B:63:0x0179, B:64:0x017d, B:69:0x017e, B:71:0x0182, B:90:0x0092, B:92:0x00a7, B:95:0x00bf, B:56:0x0175, B:60:0x0171, B:55:0x016c), top: B:7:0x0022, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01bf A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x017e A[Catch: all -> 0x00c7, TryCatch #4 {all -> 0x00c7, blocks: (B:34:0x012a, B:36:0x012e, B:45:0x015e, B:63:0x0179, B:64:0x017d, B:69:0x017e, B:71:0x0182, B:90:0x0092, B:92:0x00a7, B:95:0x00bf, B:56:0x0175, B:60:0x0171, B:55:0x016c), top: B:7:0x0022, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01c5 A[Catch: all -> 0x01dc, TRY_LEAVE, TryCatch #8 {all -> 0x01dc, blocks: (B:13:0x0031, B:21:0x00d6, B:23:0x00dc, B:27:0x00f9, B:30:0x010f, B:47:0x01aa, B:77:0x01c5, B:98:0x00d0), top: B:7:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x01c0 -> B:20:0x01c3). Please report as a decompilation issue!!! */
    @Override // io.ktor.http.content.OutgoingContent.WriteChannelContent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object writeTo(io.ktor.utils.io.ByteWriteChannel r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            Method dump skipped, instructions count: 510
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.request.forms.MultiPartFormDataContent.writeTo(io.ktor.utils.io.ByteWriteChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
