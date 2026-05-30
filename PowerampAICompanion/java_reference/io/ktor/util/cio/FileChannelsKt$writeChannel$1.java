package io.ktor.util.cio;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ReaderScope;
import io.ktor.utils.io.core.CloseableJVMKt;
import io.ktor.utils.io.jvm.nio.WritingKt;
import java.io.Closeable;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileChannels.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/ReaderScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.util.cio.FileChannelsKt$writeChannel$1", f = "FileChannels.kt", i = {0, 0, 0}, l = {98}, m = "invokeSuspend", n = {"$this$use$iv", "file", "closed$iv"}, s = {"L$0", "L$1", "I$0"})
/* loaded from: classes9.dex */
final class FileChannelsKt$writeChannel$1 extends SuspendLambda implements Function2<ReaderScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ File $this_writeChannel;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileChannelsKt$writeChannel$1(File file, Continuation<? super FileChannelsKt$writeChannel$1> continuation) {
        super(2, continuation);
        this.$this_writeChannel = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FileChannelsKt$writeChannel$1 fileChannelsKt$writeChannel$1 = new FileChannelsKt$writeChannel$1(this.$this_writeChannel, continuation);
        fileChannelsKt$writeChannel$1.L$0 = obj;
        return fileChannelsKt$writeChannel$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ReaderScope readerScope, Continuation<? super Unit> continuation) {
        return ((FileChannelsKt$writeChannel$1) create(readerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        FileChannelsKt$writeChannel$1 fileChannelsKt$writeChannel$1;
        RandomAccessFile $this$use$iv;
        RandomAccessFile file;
        Object $result2;
        Throwable first$iv;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                fileChannelsKt$writeChannel$1 = this;
                ReaderScope $this$reader = (ReaderScope) fileChannelsKt$writeChannel$1.L$0;
                $this$use$iv = new RandomAccessFile(fileChannelsKt$writeChannel$1.$this_writeChannel, "rw");
                try {
                    file = $this$use$iv;
                    ByteReadChannel channel = $this$reader.getChannel();
                    FileChannel channel2 = file.getChannel();
                    Intrinsics.checkNotNullExpressionValue(channel2, "file.channel");
                    fileChannelsKt$writeChannel$1.L$0 = $this$use$iv;
                    fileChannelsKt$writeChannel$1.L$1 = file;
                    fileChannelsKt$writeChannel$1.I$0 = 0;
                    fileChannelsKt$writeChannel$1.label = 1;
                    Object copyTo$default = WritingKt.copyTo$default(channel, channel2, 0L, fileChannelsKt$writeChannel$1, 2, (Object) null);
                    if (copyTo$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result2 = $result;
                    $result = copyTo$default;
                    try {
                        long copied = ((Number) $result).longValue();
                        file.setLength(copied);
                        Unit unit = Unit.INSTANCE;
                        $this$use$iv.close();
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        th = th;
                        first$iv = th;
                        try {
                            try {
                                $this$use$iv.close();
                                throw first$iv;
                            } catch (Throwable th2) {
                                if (1 == 0) {
                                    $this$use$iv.close();
                                }
                                throw th2;
                            }
                        } catch (Throwable second$iv) {
                            CloseableJVMKt.addSuppressedInternal(first$iv, second$iv);
                            throw first$iv;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    first$iv = th;
                    $this$use$iv.close();
                    throw first$iv;
                }
            case 1:
                int i = this.I$0;
                file = (RandomAccessFile) this.L$1;
                $this$use$iv = (Closeable) this.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    fileChannelsKt$writeChannel$1 = this;
                    $result2 = $result;
                    long copied2 = ((Number) $result).longValue();
                    file.setLength(copied2);
                    Unit unit2 = Unit.INSTANCE;
                    $this$use$iv.close();
                    return Unit.INSTANCE;
                } catch (Throwable th4) {
                    th = th4;
                    first$iv = th;
                    $this$use$iv.close();
                    throw first$iv;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
