package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PacketDirect.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a;\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\u0081\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\b"}, d2 = {"read", "", "Lio/ktor/utils/io/core/Input;", "n", "", "block", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/Buffer;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class PacketDirectKt {
    public static /* synthetic */ void read$default(Input $this$read_u24default, int n, Function1 block, int i, Object obj) {
        if ((i & 1) != 0) {
            n = 1;
        }
        Intrinsics.checkNotNullParameter($this$read_u24default, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ChunkBuffer buffer = $this$read_u24default.prepareRead(n);
        if (buffer == null) {
            StringsKt.prematureEndOfStream(n);
            throw new KotlinNothingValueException();
        }
        int positionBefore = buffer.getReadPosition();
        try {
            block.invoke(buffer);
            int positionAfter = buffer.getReadPosition();
            if (positionAfter < positionBefore) {
                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
            }
            if (positionAfter == buffer.getWritePosition()) {
                $this$read_u24default.ensureNext(buffer);
            } else {
                $this$read_u24default.setHeadPosition(positionAfter);
            }
        } catch (Throwable th) {
            int positionAfter2 = buffer.getReadPosition();
            if (positionAfter2 < positionBefore) {
                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
            }
            if (positionAfter2 == buffer.getWritePosition()) {
                $this$read_u24default.ensureNext(buffer);
            } else {
                $this$read_u24default.setHeadPosition(positionAfter2);
            }
            throw th;
        }
    }

    public static final void read(Input $this$read, int n, Function1<? super Buffer, Unit> block) {
        Intrinsics.checkNotNullParameter($this$read, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ChunkBuffer buffer = $this$read.prepareRead(n);
        if (buffer == null) {
            StringsKt.prematureEndOfStream(n);
            throw new KotlinNothingValueException();
        }
        int positionBefore = buffer.getReadPosition();
        try {
            block.invoke(buffer);
            int positionAfter = buffer.getReadPosition();
            if (positionAfter < positionBefore) {
                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
            }
            if (positionAfter == buffer.getWritePosition()) {
                $this$read.ensureNext(buffer);
            } else {
                $this$read.setHeadPosition(positionAfter);
            }
        } catch (Throwable th) {
            int positionAfter2 = buffer.getReadPosition();
            if (positionAfter2 < positionBefore) {
                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
            }
            if (positionAfter2 == buffer.getWritePosition()) {
                $this$read.ensureNext(buffer);
            } else {
                $this$read.setHeadPosition(positionAfter2);
            }
            throw th;
        }
    }
}
