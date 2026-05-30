package io.ktor.utils.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Delimited.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/LookAheadSuspendSession;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$copied$1", f = "Delimited.kt", i = {0, 0, 1, 1}, l = {85, 95}, m = "invokeSuspend", n = {"$this$lookAheadSuspend", "copied", "$this$lookAheadSuspend", "copied"}, s = {"L$0", "I$0", "L$0", "I$0"})
/* loaded from: classes9.dex */
public final class DelimitedKt$readUntilDelimiterSuspend$copied$1 extends SuspendLambda implements Function2<LookAheadSuspendSession, Continuation<? super Integer>, Object> {
    final /* synthetic */ int $copied0;
    final /* synthetic */ ByteBuffer $delimiter;
    final /* synthetic */ ByteBuffer $dst;
    final /* synthetic */ Ref.BooleanRef $endFound;
    final /* synthetic */ ByteReadChannel $this_readUntilDelimiterSuspend;
    int I$0;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DelimitedKt$readUntilDelimiterSuspend$copied$1(int i, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, Ref.BooleanRef booleanRef, ByteReadChannel byteReadChannel, Continuation<? super DelimitedKt$readUntilDelimiterSuspend$copied$1> continuation) {
        super(2, continuation);
        this.$copied0 = i;
        this.$delimiter = byteBuffer;
        this.$dst = byteBuffer2;
        this.$endFound = booleanRef;
        this.$this_readUntilDelimiterSuspend = byteReadChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DelimitedKt$readUntilDelimiterSuspend$copied$1 delimitedKt$readUntilDelimiterSuspend$copied$1 = new DelimitedKt$readUntilDelimiterSuspend$copied$1(this.$copied0, this.$delimiter, this.$dst, this.$endFound, this.$this_readUntilDelimiterSuspend, continuation);
        delimitedKt$readUntilDelimiterSuspend$copied$1.L$0 = obj;
        return delimitedKt$readUntilDelimiterSuspend$copied$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(LookAheadSuspendSession lookAheadSuspendSession, Continuation<? super Integer> continuation) {
        return ((DelimitedKt$readUntilDelimiterSuspend$copied$1) create(lookAheadSuspendSession, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x009c, code lost:
    
        r8 = r4;
        r4 = r3 + r5;
        r3 = r8;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:10:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0042 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0086 -> B:7:0x0089). Please report as a decompilation issue!!! */
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
            r2 = 1
            switch(r1) {
                case 0: goto L29;
                case 1: goto L1e;
                case 2: goto L12;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L12:
            r1 = r9
            int r3 = r1.I$0
            java.lang.Object r4 = r1.L$0
            io.ktor.utils.io.LookAheadSuspendSession r4 = (io.ktor.utils.io.LookAheadSuspendSession) r4
            kotlin.ResultKt.throwOnFailure(r10)
            goto L89
        L1e:
            r1 = r9
            int r3 = r1.I$0
            java.lang.Object r4 = r1.L$0
            io.ktor.utils.io.LookAheadSuspendSession r4 = (io.ktor.utils.io.LookAheadSuspendSession) r4
            kotlin.ResultKt.throwOnFailure(r10)
            goto L46
        L29:
            kotlin.ResultKt.throwOnFailure(r10)
            r1 = r9
            java.lang.Object r3 = r1.L$0
            io.ktor.utils.io.LookAheadSuspendSession r3 = (io.ktor.utils.io.LookAheadSuspendSession) r3
            int r4 = r1.$copied0
        L33:
            r5 = r1
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r1.L$0 = r3
            r1.I$0 = r4
            r1.label = r2
            java.lang.Object r5 = r3.awaitAtLeast(r2, r5)
            if (r5 != r0) goto L43
            return r0
        L43:
            r8 = r4
            r4 = r3
            r3 = r8
        L46:
            r5 = r4
            io.ktor.utils.io.LookAheadSession r5 = (io.ktor.utils.io.LookAheadSession) r5
            java.nio.ByteBuffer r6 = r1.$delimiter
            java.nio.ByteBuffer r7 = r1.$dst
            int r5 = io.ktor.utils.io.DelimitedKt.access$tryCopyUntilDelimiter(r5, r6, r7)
            if (r5 != 0) goto L8d
            r5 = r4
            io.ktor.utils.io.LookAheadSession r5 = (io.ktor.utils.io.LookAheadSession) r5
            java.nio.ByteBuffer r6 = r1.$delimiter
            int r5 = io.ktor.utils.io.DelimitedKt.access$startsWithDelimiter(r5, r6)
            java.nio.ByteBuffer r6 = r1.$delimiter
            int r6 = r6.remaining()
            if (r5 != r6) goto L69
            kotlin.jvm.internal.Ref$BooleanRef r0 = r1.$endFound
            r0.element = r2
            goto Lab
        L69:
            io.ktor.utils.io.ByteReadChannel r5 = r1.$this_readUntilDelimiterSuspend
            boolean r5 = r5.isClosedForWrite()
            if (r5 == 0) goto L72
            goto Lab
        L72:
            java.nio.ByteBuffer r5 = r1.$delimiter
            int r5 = r5.remaining()
            r6 = r1
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r1.L$0 = r4
            r1.I$0 = r3
            r7 = 2
            r1.label = r7
            java.lang.Object r5 = r4.awaitAtLeast(r5, r6)
            if (r5 != r0) goto L89
            return r0
        L89:
            r8 = r4
            r4 = r3
            r3 = r8
            goto L9c
        L8d:
            if (r5 > 0) goto L96
            kotlin.jvm.internal.Ref$BooleanRef r6 = r1.$endFound
            r6.element = r2
            int r6 = -r5
            r5 = r6
            goto L97
        L96:
        L97:
            int r3 = r3 + r5
            r8 = r4
            r4 = r3
            r3 = r8
        L9c:
            java.nio.ByteBuffer r5 = r1.$dst
            boolean r5 = r5.hasRemaining()
            if (r5 == 0) goto Laa
            kotlin.jvm.internal.Ref$BooleanRef r5 = r1.$endFound
            boolean r5 = r5.element
            if (r5 == 0) goto L33
        Laa:
            r3 = r4
        Lab:
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.DelimitedKt$readUntilDelimiterSuspend$copied$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
