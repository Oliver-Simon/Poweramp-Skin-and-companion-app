package io.ktor.utils.io.core.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChunkBuffer.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"isExclusivelyOwned", "", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ChunkBufferKt {
    public static final boolean isExclusivelyOwned(ChunkBuffer $this$isExclusivelyOwned) {
        Intrinsics.checkNotNullParameter($this$isExclusivelyOwned, "<this>");
        return $this$isExclusivelyOwned.getRefCount() == 1;
    }
}
