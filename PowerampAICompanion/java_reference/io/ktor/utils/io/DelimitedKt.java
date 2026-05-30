package io.ktor.utils.io;

import io.ktor.utils.io.internal.UtilsKt;
import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: Delimited.kt */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a-\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a\u001d\u0010\n\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\u001d\u0010\r\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\u0014\u0010\u000e\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u001c\u0010\u0010\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0011\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"readUntilDelimiter", "", "Lio/ktor/utils/io/ByteReadChannel;", "delimiter", "Ljava/nio/ByteBuffer;", "dst", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readUntilDelimiterSuspend", "copied0", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "skipDelimiter", "", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "skipDelimiterSuspend", "startsWithDelimiter", "Lio/ktor/utils/io/LookAheadSession;", "tryCopyUntilDelimiter", "tryEnsureDelimiter", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class DelimitedKt {
    public static final Object readUntilDelimiter(ByteReadChannel $this$readUntilDelimiter, final ByteBuffer delimiter, final ByteBuffer dst, Continuation<? super Integer> continuation) {
        int i;
        if (!delimiter.hasRemaining()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(delimiter != dst)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        final Ref.IntRef copied = new Ref.IntRef();
        final Ref.BooleanRef endFound = new Ref.BooleanRef();
        $this$readUntilDelimiter.lookAhead(new Function1<LookAheadSession, Unit>() { // from class: io.ktor.utils.io.DelimitedKt$readUntilDelimiter$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LookAheadSession lookAheadSession) {
                invoke2(lookAheadSession);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LookAheadSession lookAhead) {
                int rc;
                int size;
                Intrinsics.checkNotNullParameter(lookAhead, "$this$lookAhead");
                do {
                    rc = DelimitedKt.tryCopyUntilDelimiter(lookAhead, delimiter, dst);
                    if (rc != 0) {
                        if (rc < 0) {
                            endFound.element = true;
                            size = -rc;
                        } else {
                            size = rc;
                        }
                        copied.element += size;
                        if (!dst.hasRemaining()) {
                            return;
                        }
                    } else {
                        return;
                    }
                } while (!endFound.element);
            }
        });
        if (copied.element == 0 && $this$readUntilDelimiter.isClosedForRead()) {
            i = -1;
        } else if (!dst.hasRemaining() || endFound.element) {
            i = copied.element;
        } else {
            return readUntilDelimiterSuspend($this$readUntilDelimiter, delimiter, dst, copied.element, continuation);
        }
        return Boxing.boxInt(i);
    }

    public static final Object skipDelimiter(ByteReadChannel $this$skipDelimiter, final ByteBuffer delimiter, Continuation<? super Unit> continuation) {
        Object skipDelimiterSuspend;
        if (!delimiter.hasRemaining()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        final Ref.BooleanRef found = new Ref.BooleanRef();
        $this$skipDelimiter.lookAhead(new Function1<LookAheadSession, Unit>() { // from class: io.ktor.utils.io.DelimitedKt$skipDelimiter$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LookAheadSession lookAheadSession) {
                invoke2(lookAheadSession);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LookAheadSession lookAhead) {
                int tryEnsureDelimiter;
                Intrinsics.checkNotNullParameter(lookAhead, "$this$lookAhead");
                Ref.BooleanRef booleanRef = Ref.BooleanRef.this;
                tryEnsureDelimiter = DelimitedKt.tryEnsureDelimiter(lookAhead, delimiter);
                booleanRef.element = tryEnsureDelimiter == delimiter.remaining();
            }
        });
        return (found.element || (skipDelimiterSuspend = skipDelimiterSuspend($this$skipDelimiter, delimiter, continuation)) != IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : skipDelimiterSuspend;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object skipDelimiterSuspend(ByteReadChannel $this$skipDelimiterSuspend, ByteBuffer delimiter, Continuation<? super Unit> continuation) {
        Object lookAheadSuspend = $this$skipDelimiterSuspend.lookAheadSuspend(new DelimitedKt$skipDelimiterSuspend$2(delimiter, null), continuation);
        return lookAheadSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? lookAheadSuspend : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readUntilDelimiterSuspend(io.ktor.utils.io.ByteReadChannel r11, java.nio.ByteBuffer r12, java.nio.ByteBuffer r13, int r14, kotlin.coroutines.Continuation<? super java.lang.Integer> r15) {
        /*
            Method dump skipped, instructions count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.DelimitedKt.readUntilDelimiterSuspend(io.ktor.utils.io.ByteReadChannel, java.nio.ByteBuffer, java.nio.ByteBuffer, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int tryCopyUntilDelimiter(LookAheadSession $this$tryCopyUntilDelimiter, ByteBuffer delimiter, ByteBuffer dst) {
        int size;
        boolean endFound = false;
        ByteBuffer buffer = $this$tryCopyUntilDelimiter.request(0, 1);
        if (buffer == null) {
            return 0;
        }
        int index = UtilsKt.indexOfPartial(buffer, delimiter);
        if (index == -1) {
            size = UtilsKt.putAtMost$default(dst, buffer, 0, 2, null);
        } else {
            int found = Math.min(buffer.remaining() - index, delimiter.remaining());
            int notKnown = delimiter.remaining() - found;
            if (notKnown == 0) {
                endFound = true;
                size = UtilsKt.putLimited(dst, buffer, buffer.position() + index);
            } else {
                ByteBuffer remembered = buffer.duplicate();
                ByteBuffer next = $this$tryCopyUntilDelimiter.request(index + found, 1);
                if (next == null) {
                    Intrinsics.checkNotNullExpressionValue(remembered, "remembered");
                    size = UtilsKt.putLimited(dst, remembered, remembered.position() + index);
                } else if (UtilsKt.startsWith(next, delimiter, found)) {
                    if (next.remaining() >= notKnown) {
                        endFound = true;
                        Intrinsics.checkNotNullExpressionValue(remembered, "remembered");
                        size = UtilsKt.putLimited(dst, remembered, remembered.position() + index);
                    } else {
                        Intrinsics.checkNotNullExpressionValue(remembered, "remembered");
                        size = UtilsKt.putLimited(dst, remembered, remembered.position() + index);
                    }
                } else {
                    Intrinsics.checkNotNullExpressionValue(remembered, "remembered");
                    size = UtilsKt.putLimited(dst, remembered, remembered.position() + index + 1);
                }
            }
        }
        $this$tryCopyUntilDelimiter.mo501consumed(size);
        return endFound ? -size : size;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int tryEnsureDelimiter(LookAheadSession $this$tryEnsureDelimiter, ByteBuffer delimiter) {
        int found = startsWithDelimiter($this$tryEnsureDelimiter, delimiter);
        if (found == -1) {
            throw new IOException("Failed to skip delimiter: actual bytes differ from delimiter bytes");
        }
        if (found < delimiter.remaining()) {
            return found;
        }
        $this$tryEnsureDelimiter.mo501consumed(delimiter.remaining());
        return delimiter.remaining();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int startsWithDelimiter(LookAheadSession $this$startsWithDelimiter, ByteBuffer delimiter) {
        ByteBuffer buffer = $this$startsWithDelimiter.request(0, 1);
        if (buffer == null) {
            return 0;
        }
        int index = UtilsKt.indexOfPartial(buffer, delimiter);
        if (index != 0) {
            return -1;
        }
        int found = Math.min(buffer.remaining() - index, delimiter.remaining());
        int notKnown = delimiter.remaining() - found;
        if (notKnown > 0) {
            ByteBuffer next = $this$startsWithDelimiter.request(index + found, notKnown);
            if (next == null) {
                return found;
            }
            if (!UtilsKt.startsWith(next, delimiter, found)) {
                return -1;
            }
        }
        return delimiter.remaining();
    }
}
