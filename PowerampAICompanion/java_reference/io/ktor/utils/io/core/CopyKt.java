package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Copy.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"copyTo", "", "Lio/ktor/utils/io/core/Input;", "output", "Lio/ktor/utils/io/core/Output;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class CopyKt {
    public static final long copyTo(Input $this$copyTo, Output output) {
        Intrinsics.checkNotNullParameter($this$copyTo, "<this>");
        Intrinsics.checkNotNullParameter(output, "output");
        long copied = 0;
        while (true) {
            ChunkBuffer head = $this$copyTo.stealAll$ktor_io();
            if (head == null) {
                if ($this$copyTo.prepareRead(1) == null) {
                    return copied;
                }
            } else {
                copied += BuffersKt.remainingAll(head);
                output.appendChain$ktor_io(head);
            }
        }
    }
}
