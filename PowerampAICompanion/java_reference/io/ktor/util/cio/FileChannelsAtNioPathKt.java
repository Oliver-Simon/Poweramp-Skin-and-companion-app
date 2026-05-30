package io.ktor.util.cio;

import io.ktor.utils.io.ByteReadChannel;
import java.io.File;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;

/* compiled from: FileChannelsAtNioPath.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"readChannel", "Lio/ktor/utils/io/ByteReadChannel;", "Ljava/nio/file/Path;", "start", "", "endInclusive", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class FileChannelsAtNioPathKt {
    public static final ByteReadChannel readChannel(Path $this$readChannel, long start, long endInclusive) {
        ByteReadChannel readChannel;
        Intrinsics.checkNotNullParameter($this$readChannel, "<this>");
        File file = $this$readChannel.toFile();
        Intrinsics.checkNotNullExpressionValue(file, "toFile()");
        readChannel = FileChannelsKt.readChannel(file, (r12 & 1) != 0 ? 0L : start, (r12 & 2) != 0 ? -1L : endInclusive, (r12 & 4) != 0 ? Dispatchers.getIO() : null);
        return readChannel;
    }

    public static final ByteReadChannel readChannel(Path $this$readChannel) {
        ByteReadChannel readChannel;
        Intrinsics.checkNotNullParameter($this$readChannel, "<this>");
        File file = $this$readChannel.toFile();
        Intrinsics.checkNotNullExpressionValue(file, "toFile()");
        readChannel = FileChannelsKt.readChannel(file, (r12 & 1) != 0 ? 0L : 0L, (r12 & 2) != 0 ? -1L : 0L, (r12 & 4) != 0 ? Dispatchers.getIO() : null);
        return readChannel;
    }
}
