package io.ktor.util.cio;

import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.InputStream;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: InputStreamAdapters.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.util.cio.InputStreamAdaptersKt$toByteReadChannel$1", f = "InputStreamAdapters.kt", i = {0, 0}, l = {34}, m = "invokeSuspend", n = {"$this$writer", "buffer"}, s = {"L$0", "L$1"})
/* loaded from: classes9.dex */
final class InputStreamAdaptersKt$toByteReadChannel$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ObjectPool<ByteBuffer> $pool;
    final /* synthetic */ InputStream $this_toByteReadChannel;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InputStreamAdaptersKt$toByteReadChannel$1(ObjectPool<ByteBuffer> objectPool, InputStream inputStream, Continuation<? super InputStreamAdaptersKt$toByteReadChannel$1> continuation) {
        super(2, continuation);
        this.$pool = objectPool;
        this.$this_toByteReadChannel = inputStream;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        InputStreamAdaptersKt$toByteReadChannel$1 inputStreamAdaptersKt$toByteReadChannel$1 = new InputStreamAdaptersKt$toByteReadChannel$1(this.$pool, this.$this_toByteReadChannel, continuation);
        inputStreamAdaptersKt$toByteReadChannel$1.L$0 = obj;
        return inputStreamAdaptersKt$toByteReadChannel$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((InputStreamAdaptersKt$toByteReadChannel$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007d A[SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            switch(r1) {
                case 0: goto L20;
                case 1: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L11:
            r1 = r9
            java.lang.Object r2 = r1.L$1
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            java.lang.Object r3 = r1.L$0
            io.ktor.utils.io.WriterScope r3 = (io.ktor.utils.io.WriterScope) r3
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L1e
            goto L73
        L1e:
            r0 = move-exception
            goto L75
        L20:
            kotlin.ResultKt.throwOnFailure(r10)
            r1 = r9
            java.lang.Object r2 = r1.L$0
            io.ktor.utils.io.WriterScope r2 = (io.ktor.utils.io.WriterScope) r2
            io.ktor.utils.io.pool.ObjectPool<java.nio.ByteBuffer> r3 = r1.$pool
            java.lang.Object r3 = r3.borrow()
            java.nio.ByteBuffer r3 = (java.nio.ByteBuffer) r3
            r8 = r3
            r3 = r2
            r2 = r8
        L33:
            r2.clear()     // Catch: java.lang.Throwable -> L1e
            java.io.InputStream r4 = r1.$this_toByteReadChannel     // Catch: java.lang.Throwable -> L1e
            byte[] r5 = r2.array()     // Catch: java.lang.Throwable -> L1e
            int r6 = r2.arrayOffset()     // Catch: java.lang.Throwable -> L1e
            int r7 = r2.position()     // Catch: java.lang.Throwable -> L1e
            int r6 = r6 + r7
            int r7 = r2.remaining()     // Catch: java.lang.Throwable -> L1e
            int r4 = r4.read(r5, r6, r7)     // Catch: java.lang.Throwable -> L1e
            if (r4 < 0) goto L74
            if (r4 == 0) goto L33
            int r5 = r2.position()     // Catch: java.lang.Throwable -> L1e
            int r5 = r5 + r4
            r2.position(r5)     // Catch: java.lang.Throwable -> L1e
            r2.flip()     // Catch: java.lang.Throwable -> L1e
            io.ktor.utils.io.ByteWriteChannel r4 = r3.getChannel()     // Catch: java.lang.Throwable -> L1e
            r5 = r1
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch: java.lang.Throwable -> L1e
            r1.L$0 = r3     // Catch: java.lang.Throwable -> L1e
            r1.L$1 = r2     // Catch: java.lang.Throwable -> L1e
            r6 = 1
            r1.label = r6     // Catch: java.lang.Throwable -> L1e
            java.lang.Object r4 = r4.writeFully(r2, r5)     // Catch: java.lang.Throwable -> L1e
            if (r4 != r0) goto L73
            return r0
        L73:
            goto L33
        L74:
            goto L7d
        L75:
            io.ktor.utils.io.ByteWriteChannel r4 = r3.getChannel()     // Catch: java.lang.Throwable -> L8b
            r4.close(r0)     // Catch: java.lang.Throwable -> L8b
        L7d:
            io.ktor.utils.io.pool.ObjectPool<java.nio.ByteBuffer> r0 = r1.$pool
            r0.recycle(r2)
            java.io.InputStream r0 = r1.$this_toByteReadChannel
            r0.close()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L8b:
            r0 = move-exception
            io.ktor.utils.io.pool.ObjectPool<java.nio.ByteBuffer> r3 = r1.$pool
            r3.recycle(r2)
            java.io.InputStream r2 = r1.$this_toByteReadChannel
            r2.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.cio.InputStreamAdaptersKt$toByteReadChannel$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
