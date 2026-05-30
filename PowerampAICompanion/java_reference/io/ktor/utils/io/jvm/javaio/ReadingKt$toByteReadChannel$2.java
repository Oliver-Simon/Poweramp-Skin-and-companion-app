package io.ktor.utils.io.jvm.javaio;

import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: Reading.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.jvm.javaio.ReadingKt$toByteReadChannel$2", f = "Reading.kt", i = {0, 0}, l = {90}, m = "invokeSuspend", n = {"$this$writer", "buffer"}, s = {"L$0", "L$1"})
/* loaded from: classes9.dex */
final class ReadingKt$toByteReadChannel$2 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ObjectPool<byte[]> $pool;
    final /* synthetic */ InputStream $this_toByteReadChannel;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReadingKt$toByteReadChannel$2(ObjectPool<byte[]> objectPool, InputStream inputStream, Continuation<? super ReadingKt$toByteReadChannel$2> continuation) {
        super(2, continuation);
        this.$pool = objectPool;
        this.$this_toByteReadChannel = inputStream;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ReadingKt$toByteReadChannel$2 readingKt$toByteReadChannel$2 = new ReadingKt$toByteReadChannel$2(this.$pool, this.$this_toByteReadChannel, continuation);
        readingKt$toByteReadChannel$2.L$0 = obj;
        return readingKt$toByteReadChannel$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((ReadingKt$toByteReadChannel$2) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0056 A[SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            switch(r1) {
                case 0: goto L20;
                case 1: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L11:
            r1 = r10
            java.lang.Object r2 = r1.L$1
            byte[] r2 = (byte[]) r2
            java.lang.Object r3 = r1.L$0
            io.ktor.utils.io.WriterScope r3 = (io.ktor.utils.io.WriterScope) r3
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L1e
            goto L55
        L1e:
            r0 = move-exception
            goto L5c
        L20:
            kotlin.ResultKt.throwOnFailure(r11)
            r1 = r10
            java.lang.Object r2 = r1.L$0
            io.ktor.utils.io.WriterScope r2 = (io.ktor.utils.io.WriterScope) r2
            io.ktor.utils.io.pool.ObjectPool<byte[]> r3 = r1.$pool
            java.lang.Object r3 = r3.borrow()
            byte[] r3 = (byte[]) r3
            r9 = r3
            r3 = r2
            r2 = r9
        L33:
            java.io.InputStream r4 = r1.$this_toByteReadChannel     // Catch: java.lang.Throwable -> L1e
            int r5 = r2.length     // Catch: java.lang.Throwable -> L1e
            r6 = 0
            int r4 = r4.read(r2, r6, r5)     // Catch: java.lang.Throwable -> L1e
            if (r4 < 0) goto L56
            if (r4 == 0) goto L33
            io.ktor.utils.io.ByteWriteChannel r5 = r3.getChannel()     // Catch: java.lang.Throwable -> L1e
            r7 = r1
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch: java.lang.Throwable -> L1e
            r1.L$0 = r3     // Catch: java.lang.Throwable -> L1e
            r1.L$1 = r2     // Catch: java.lang.Throwable -> L1e
            r8 = 1
            r1.label = r8     // Catch: java.lang.Throwable -> L1e
            java.lang.Object r5 = r5.writeFully(r2, r6, r4, r7)     // Catch: java.lang.Throwable -> L1e
            if (r5 != r0) goto L55
            return r0
        L55:
            goto L33
        L56:
            io.ktor.utils.io.pool.ObjectPool<byte[]> r0 = r1.$pool
            r0.recycle(r2)
            goto L69
        L5c:
            io.ktor.utils.io.ByteWriteChannel r4 = r3.getChannel()     // Catch: java.lang.Throwable -> L72
            r4.close(r0)     // Catch: java.lang.Throwable -> L72
            io.ktor.utils.io.pool.ObjectPool<byte[]> r0 = r1.$pool
            r0.recycle(r2)
        L69:
            java.io.InputStream r0 = r1.$this_toByteReadChannel
            r0.close()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L72:
            r0 = move-exception
            io.ktor.utils.io.pool.ObjectPool<byte[]> r3 = r1.$pool
            r3.recycle(r2)
            java.io.InputStream r2 = r1.$this_toByteReadChannel
            r2.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.jvm.javaio.ReadingKt$toByteReadChannel$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
