package io.ktor.utils.io.internal;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Utils.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0000\u001a\f\u0010\b\u001a\u00020\t*\u00020\u0006H\u0000\u001a\u001e\u0010\n\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u0001H\u0000\u001a\u001e\u0010\r\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u0001H\u0000\u001a\u001e\u0010\u000f\u001a\u00020\t*\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u0001H\u0000¨\u0006\u0012"}, d2 = {"getIOIntProperty", "", "name", "", "default", "indexOfPartial", "Ljava/nio/ByteBuffer;", "sub", "isEmpty", "", "putAtMost", "src", "n", "putLimited", "limit", "startsWith", "prefix", "prefixSkip", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class UtilsKt {
    public static final boolean isEmpty(ByteBuffer $this$isEmpty) {
        Intrinsics.checkNotNullParameter($this$isEmpty, "<this>");
        return !$this$isEmpty.hasRemaining();
    }

    public static final int getIOIntProperty(String name, int i) {
        String str;
        Integer intOrNull;
        Intrinsics.checkNotNullParameter(name, "name");
        try {
            str = System.getProperty("io.ktor.utils.io." + name);
        } catch (SecurityException e) {
            str = null;
        }
        return (str == null || (intOrNull = kotlin.text.StringsKt.toIntOrNull(str)) == null) ? i : intOrNull.intValue();
    }

    public static final int indexOfPartial(ByteBuffer $this$indexOfPartial, ByteBuffer sub) {
        int j;
        Intrinsics.checkNotNullParameter($this$indexOfPartial, "<this>");
        Intrinsics.checkNotNullParameter(sub, "sub");
        int subPosition = sub.position();
        int subSize = sub.remaining();
        byte first = sub.get(subPosition);
        int limit = $this$indexOfPartial.limit();
        for (int idx = $this$indexOfPartial.position(); idx < limit; idx++) {
            if ($this$indexOfPartial.get(idx) == first) {
                while (j < subSize && idx + j != limit) {
                    j = $this$indexOfPartial.get(idx + j) == sub.get(subPosition + j) ? j + 1 : 1;
                }
                return idx - $this$indexOfPartial.position();
            }
        }
        return -1;
    }

    public static /* synthetic */ boolean startsWith$default(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return startsWith(byteBuffer, byteBuffer2, i);
    }

    public static final boolean startsWith(ByteBuffer $this$startsWith, ByteBuffer prefix, int prefixSkip) {
        Intrinsics.checkNotNullParameter($this$startsWith, "<this>");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        int size = Math.min($this$startsWith.remaining(), prefix.remaining() - prefixSkip);
        if (size <= 0) {
            return false;
        }
        int position = $this$startsWith.position();
        int prefixPosition = prefix.position() + prefixSkip;
        for (int i = 0; i < size; i++) {
            if ($this$startsWith.get(position + i) != prefix.get(prefixPosition + i)) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ int putAtMost$default(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = byteBuffer2.remaining();
        }
        return putAtMost(byteBuffer, byteBuffer2, i);
    }

    public static final int putAtMost(ByteBuffer $this$putAtMost, ByteBuffer src, int n) {
        Intrinsics.checkNotNullParameter($this$putAtMost, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        int rem = $this$putAtMost.remaining();
        int srcRem = src.remaining();
        if (srcRem <= rem && srcRem <= n) {
            $this$putAtMost.put(src);
            return srcRem;
        }
        int size = Math.min(rem, Math.min(srcRem, n));
        int idx = 1;
        if (1 > size) {
            return size;
        }
        while (true) {
            $this$putAtMost.put(src.get());
            if (idx == size) {
                return size;
            }
            idx++;
        }
    }

    public static /* synthetic */ int putLimited$default(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = byteBuffer.limit();
        }
        return putLimited(byteBuffer, byteBuffer2, i);
    }

    public static final int putLimited(ByteBuffer $this$putLimited, ByteBuffer src, int limit) {
        Intrinsics.checkNotNullParameter($this$putLimited, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        return putAtMost($this$putLimited, src, limit - src.position());
    }
}
