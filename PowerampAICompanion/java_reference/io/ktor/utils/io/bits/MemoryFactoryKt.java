package io.ktor.utils.io.bits;

import io.ktor.http.ContentDisposition;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MemoryFactory.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001aC\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00010\u0005H\u0086\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\u0007\u001aC\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00010\u0005H\u0086\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\t\u0082\u0002\u000b\n\u0005\b\u009920\u0001\n\u0002\b\u0019¨\u0006\n"}, d2 = {"withMemory", "R", ContentDisposition.Parameters.Size, "", "block", "Lkotlin/Function1;", "Lio/ktor/utils/io/bits/Memory;", "(ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "", "(JLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class MemoryFactoryKt {
    public static final <R> R withMemory(int size, Function1<? super Memory, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        long size$iv = size;
        DefaultAllocator allocator$iv = DefaultAllocator.INSTANCE;
        ByteBuffer memory$iv = allocator$iv.mo229allocgFvZug(size$iv);
        try {
            return block.invoke(Memory.m234boximpl(memory$iv));
        } finally {
            allocator$iv.mo230free3GNKZMM(memory$iv);
        }
    }

    public static final <R> R withMemory(long size, Function1<? super Memory, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        DefaultAllocator allocator = DefaultAllocator.INSTANCE;
        ByteBuffer memory = allocator.mo229allocgFvZug(size);
        try {
            return block.invoke(Memory.m234boximpl(memory));
        } finally {
            allocator.mo230free3GNKZMM(memory);
        }
    }
}
