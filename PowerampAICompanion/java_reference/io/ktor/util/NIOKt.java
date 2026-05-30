package io.ktor.util;

import io.ktor.http.ContentDisposition;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: NIO.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u001a\u0014\u0010\u0006\u001a\u00020\u0007*\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u001c\u0010\n\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\u0005\u001a\n\u0010\r\u001a\u00020\u000e*\u00020\u0001¨\u0006\u000f"}, d2 = {"copy", "Ljava/nio/ByteBuffer;", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", ContentDisposition.Parameters.Size, "", "decodeString", "", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "moveTo", "destination", "limit", "moveToByteArray", "", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class NIOKt {
    public static /* synthetic */ int moveTo$default(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        return moveTo(byteBuffer, byteBuffer2, i);
    }

    public static final int moveTo(ByteBuffer $this$moveTo, ByteBuffer destination, int limit) {
        Intrinsics.checkNotNullParameter($this$moveTo, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        int size = Math.min(limit, Math.min($this$moveTo.remaining(), destination.remaining()));
        if (size == $this$moveTo.remaining()) {
            destination.put($this$moveTo);
        } else {
            int l = $this$moveTo.limit();
            $this$moveTo.limit($this$moveTo.position() + size);
            destination.put($this$moveTo);
            $this$moveTo.limit(l);
        }
        return size;
    }

    public static final byte[] moveToByteArray(ByteBuffer $this$moveToByteArray) {
        Intrinsics.checkNotNullParameter($this$moveToByteArray, "<this>");
        byte[] array = new byte[$this$moveToByteArray.remaining()];
        $this$moveToByteArray.get(array);
        return array;
    }

    public static /* synthetic */ String decodeString$default(ByteBuffer byteBuffer, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return decodeString(byteBuffer, charset);
    }

    public static final String decodeString(ByteBuffer $this$decodeString, Charset charset) {
        Intrinsics.checkNotNullParameter($this$decodeString, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        String charBuffer = charset.decode($this$decodeString).toString();
        Intrinsics.checkNotNullExpressionValue(charBuffer, "charset.decode(this).toString()");
        return charBuffer;
    }

    public static /* synthetic */ ByteBuffer copy$default(ByteBuffer byteBuffer, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = byteBuffer.remaining();
        }
        return copy(byteBuffer, i);
    }

    public static final ByteBuffer copy(ByteBuffer $this$copy, int size) {
        Intrinsics.checkNotNullParameter($this$copy, "<this>");
        ByteBuffer $this$copy_u24lambda_u240 = ByteBuffer.allocate(size);
        ByteBuffer slice = $this$copy.slice();
        Intrinsics.checkNotNullExpressionValue(slice, "this@copy.slice()");
        Intrinsics.checkNotNullExpressionValue($this$copy_u24lambda_u240, "this@apply");
        moveTo$default(slice, $this$copy_u24lambda_u240, 0, 2, null);
        $this$copy_u24lambda_u240.clear();
        Intrinsics.checkNotNullExpressionValue($this$copy_u24lambda_u240, "allocate(size).apply {\n …ly)\n        clear()\n    }");
        return $this$copy_u24lambda_u240;
    }

    public static final ByteBuffer copy(ByteBuffer $this$copy, ObjectPool<ByteBuffer> pool, int size) {
        Intrinsics.checkNotNullParameter($this$copy, "<this>");
        Intrinsics.checkNotNullParameter(pool, "pool");
        ByteBuffer borrow = pool.borrow();
        ByteBuffer $this$copy_u24lambda_u241 = borrow;
        $this$copy_u24lambda_u241.limit(size);
        ByteBuffer slice = $this$copy.slice();
        Intrinsics.checkNotNullExpressionValue(slice, "this@copy.slice()");
        moveTo$default(slice, $this$copy_u24lambda_u241, 0, 2, null);
        $this$copy_u24lambda_u241.flip();
        return borrow;
    }

    public static /* synthetic */ ByteBuffer copy$default(ByteBuffer byteBuffer, ObjectPool objectPool, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = byteBuffer.remaining();
        }
        return copy(byteBuffer, objectPool, i);
    }
}
