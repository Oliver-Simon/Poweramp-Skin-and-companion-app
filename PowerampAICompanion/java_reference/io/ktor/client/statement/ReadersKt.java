package io.ktor.client.statement;

import io.ktor.utils.io.ByteReadChannelKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

/* compiled from: Readers.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a\u0015\u0010\u0004\u001a\u00020\u0005*\u00020\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"discardRemaining", "", "Lio/ktor/client/statement/HttpResponse;", "(Lio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readBytes", "", "count", "", "(Lio/ktor/client/statement/HttpResponse;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ReadersKt {
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readBytes(io.ktor.client.statement.HttpResponse r6, int r7, kotlin.coroutines.Continuation<? super byte[]> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.client.statement.ReadersKt$readBytes$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.client.statement.ReadersKt$readBytes$1 r0 = (io.ktor.client.statement.ReadersKt$readBytes$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.client.statement.ReadersKt$readBytes$1 r0 = new io.ktor.client.statement.ReadersKt$readBytes$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L35;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2c:
            r6 = 0
            java.lang.Object r7 = r0.L$0
            byte[] r7 = (byte[]) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L4e
        L35:
            kotlin.ResultKt.throwOnFailure(r8)
            byte[] r2 = new byte[r7]
            r7 = r2
            r3 = 0
            io.ktor.utils.io.ByteReadChannel r4 = r6.getContent()
            r0.L$0 = r2
            r5 = 1
            r0.label = r5
            java.lang.Object r6 = io.ktor.utils.io.ByteReadChannelKt.readFully(r4, r7, r0)
            if (r6 != r1) goto L4c
            return r1
        L4c:
            r7 = r2
            r6 = r3
        L4e:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.ReadersKt.readBytes(io.ktor.client.statement.HttpResponse, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readBytes(io.ktor.client.statement.HttpResponse r8, kotlin.coroutines.Continuation<? super byte[]> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.client.statement.ReadersKt$readBytes$3
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.client.statement.ReadersKt$readBytes$3 r0 = (io.ktor.client.statement.ReadersKt$readBytes$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.client.statement.ReadersKt$readBytes$3 r0 = new io.ktor.client.statement.ReadersKt$readBytes$3
            r0.<init>(r9)
        L19:
            r4 = r0
            java.lang.Object r9 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r7 = 1
            switch(r1) {
                case 0: goto L33;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2e:
            kotlin.ResultKt.throwOnFailure(r9)
            r8 = r9
            goto L47
        L33:
            kotlin.ResultKt.throwOnFailure(r9)
            io.ktor.utils.io.ByteReadChannel r1 = r8.getContent()
            r4.label = r7
            r2 = 0
            r5 = 1
            r6 = 0
            java.lang.Object r8 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r1, r2, r4, r5, r6)
            if (r8 != r0) goto L47
            return r0
        L47:
            io.ktor.utils.io.core.ByteReadPacket r8 = (io.ktor.utils.io.core.ByteReadPacket) r8
            r0 = 0
            r1 = 0
            byte[] r8 = io.ktor.utils.io.core.StringsKt.readBytes$default(r8, r0, r7, r1)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.ReadersKt.readBytes(io.ktor.client.statement.HttpResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object discardRemaining(HttpResponse $this$discardRemaining, Continuation<? super Unit> continuation) {
        Object discard = ByteReadChannelKt.discard($this$discardRemaining.getContent(), continuation);
        return discard == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? discard : Unit.INSTANCE;
    }
}
