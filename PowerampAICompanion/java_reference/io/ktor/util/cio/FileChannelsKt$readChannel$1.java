package io.ktor.util.cio;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.core.CloseableJVMKt;
import java.io.Closeable;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: FileChannels.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.util.cio.FileChannelsKt$readChannel$1", f = "FileChannels.kt", i = {0, 0, 1, 1}, l = {46, ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT}, m = "invokeSuspend", n = {"$this$use$iv", "closed$iv", "$this$use$iv", "closed$iv"}, s = {"L$0", "I$0", "L$0", "I$0"})
/* loaded from: classes9.dex */
final class FileChannelsKt$readChannel$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $endInclusive;
    final /* synthetic */ long $fileLength;
    final /* synthetic */ long $start;
    final /* synthetic */ File $this_readChannel;
    int I$0;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileChannelsKt$readChannel$1(long j, long j2, long j3, File file, Continuation<? super FileChannelsKt$readChannel$1> continuation) {
        super(2, continuation);
        this.$start = j;
        this.$endInclusive = j2;
        this.$fileLength = j3;
        this.$this_readChannel = file;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FileChannelsKt$readChannel$1 fileChannelsKt$readChannel$1 = new FileChannelsKt$readChannel$1(this.$start, this.$endInclusive, this.$fileLength, this.$this_readChannel, continuation);
        fileChannelsKt$readChannel$1.L$0 = obj;
        return fileChannelsKt$readChannel$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((FileChannelsKt$readChannel$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0008. Please report as an issue. */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        RandomAccessFile $this$use$iv;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    WriterScope $this$writer = (WriterScope) this.L$0;
                    boolean z = this.$start >= 0;
                    long j = this.$start;
                    if (!z) {
                        throw new IllegalArgumentException(("start position shouldn't be negative but it is " + j).toString());
                    }
                    boolean z2 = this.$endInclusive <= this.$fileLength - 1;
                    long j2 = this.$fileLength;
                    long j3 = this.$endInclusive;
                    if (!z2) {
                        throw new IllegalArgumentException(("endInclusive points to the position out of the file: file size = " + j2 + ", endInclusive = " + j3).toString());
                    }
                    $this$use$iv = new RandomAccessFile(this.$this_readChannel, "r");
                    long j4 = this.$start;
                    final long j5 = this.$endInclusive;
                    try {
                        RandomAccessFile file = $this$use$iv;
                        final FileChannel fileChannel = file.getChannel();
                        Intrinsics.checkNotNullExpressionValue(fileChannel, "file.channel");
                        if (j4 > 0) {
                            fileChannel.position(j4);
                        }
                        if (j5 == -1) {
                            ByteWriteChannel channel = $this$writer.getChannel();
                            FileChannelsKt$readChannel$1$3$1 fileChannelsKt$readChannel$1$3$1 = new FileChannelsKt$readChannel$1$3$1($this$writer, fileChannel, null);
                            this.L$0 = $this$use$iv;
                            this.I$0 = 0;
                            this.label = 1;
                            if (channel.writeSuspendSession(fileChannelsKt$readChannel$1$3$1, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            Unit unit = Unit.INSTANCE;
                            $this$use$iv.close();
                            return Unit.INSTANCE;
                        }
                        final Ref.LongRef position = new Ref.LongRef();
                        position.element = j4;
                        ByteWriteChannel channel2 = $this$writer.getChannel();
                        Function1<ByteBuffer, Boolean> function1 = new Function1<ByteBuffer, Boolean>() { // from class: io.ktor.util.cio.FileChannelsKt$readChannel$1$3$2
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(ByteBuffer buffer) {
                                int rc;
                                Intrinsics.checkNotNullParameter(buffer, "buffer");
                                long fileRemaining = (j5 - position.element) + 1;
                                if (fileRemaining < buffer.remaining()) {
                                    int l = buffer.limit();
                                    buffer.limit(buffer.position() + ((int) fileRemaining));
                                    rc = fileChannel.read(buffer);
                                    buffer.limit(l);
                                } else {
                                    rc = fileChannel.read(buffer);
                                }
                                if (rc > 0) {
                                    position.element += rc;
                                }
                                return Boolean.valueOf(rc != -1 && position.element <= j5);
                            }
                        };
                        this.L$0 = $this$use$iv;
                        this.I$0 = 0;
                        this.label = 2;
                        if (channel2.writeWhile(function1, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Unit unit2 = Unit.INSTANCE;
                        $this$use$iv.close();
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        th = th;
                        Throwable first$iv = th;
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
                case 1:
                    int i = this.I$0;
                    $this$use$iv = (Closeable) this.L$0;
                    ResultKt.throwOnFailure($result);
                    Unit unit22 = Unit.INSTANCE;
                    $this$use$iv.close();
                    return Unit.INSTANCE;
                case 2:
                    int i2 = this.I$0;
                    $this$use$iv = (Closeable) this.L$0;
                    ResultKt.throwOnFailure($result);
                    Unit unit222 = Unit.INSTANCE;
                    $this$use$iv.close();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
