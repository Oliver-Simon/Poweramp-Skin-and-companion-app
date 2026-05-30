package io.ktor.utils.io.streams;

import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Output.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"asOutput", "Lio/ktor/utils/io/core/Output;", "Ljava/io/OutputStream;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class OutputKt {
    public static final Output asOutput(OutputStream $this$asOutput) {
        Intrinsics.checkNotNullParameter($this$asOutput, "<this>");
        return new OutputStreamAdapter(ChunkBuffer.INSTANCE.getPool(), $this$asOutput);
    }
}
