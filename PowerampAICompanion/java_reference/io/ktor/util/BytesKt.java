package io.ktor.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Bytes.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\n\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"readShort", "", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class BytesKt {
    @InternalAPI
    public static final short readShort(byte[] $this$readShort, int offset) {
        Intrinsics.checkNotNullParameter($this$readShort, "<this>");
        int result = (($this$readShort[offset] & 255) << 8) | ($this$readShort[offset + 1] & 255);
        return (short) result;
    }
}
