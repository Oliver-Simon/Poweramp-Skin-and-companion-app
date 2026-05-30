package io.ktor.client.engine.okhttp;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.jvm.javaio.BlockingKt;
import java.io.Closeable;
import java.io.IOException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/* compiled from: StreamRequestBody.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u0011"}, d2 = {"Lio/ktor/client/engine/okhttp/StreamRequestBody;", "Lokhttp3/RequestBody;", "contentLength", "", "block", "Lkotlin/Function0;", "Lio/ktor/utils/io/ByteReadChannel;", "(Ljava/lang/Long;Lkotlin/jvm/functions/Function0;)V", "Ljava/lang/Long;", "contentType", "Lokhttp3/MediaType;", "isOneShot", "", "writeTo", "", "sink", "Lokio/BufferedSink;", "ktor-client-okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class StreamRequestBody extends RequestBody {
    private final Function0<ByteReadChannel> block;
    private final Long contentLength;

    /* JADX WARN: Multi-variable type inference failed */
    public StreamRequestBody(Long contentLength, Function0<? extends ByteReadChannel> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.contentLength = contentLength;
        this.block = block;
    }

    @Override // okhttp3.RequestBody
    /* renamed from: contentType */
    public MediaType get$contentType() {
        return null;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        try {
            Closeable $this$use$iv = Okio.source(BlockingKt.toInputStream$default(this.block.invoke(), null, 1, null));
            Object result$iv = null;
            Throwable thrown$iv = null;
            try {
                Source it = (Source) $this$use$iv;
                result$iv = Long.valueOf(sink.writeAll(it));
            } catch (Throwable t$iv) {
                thrown$iv = t$iv;
            }
            if ($this$use$iv != null) {
                try {
                    $this$use$iv.close();
                } catch (Throwable t$iv2) {
                    if (thrown$iv == null) {
                        thrown$iv = t$iv2;
                    } else {
                        ExceptionsKt.addSuppressed(thrown$iv, t$iv2);
                    }
                }
            }
            if (thrown$iv != null) {
                throw thrown$iv;
            }
            Intrinsics.checkNotNull(result$iv);
        } catch (IOException cause) {
            throw cause;
        } catch (Throwable cause2) {
            throw new StreamAdapterIOException(cause2);
        }
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        Long l = this.contentLength;
        if (l != null) {
            return l.longValue();
        }
        return -1L;
    }

    @Override // okhttp3.RequestBody
    public boolean isOneShot() {
        return true;
    }
}
