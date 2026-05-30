package io.ktor.utils.io.bits;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PrimiteArrays.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a>\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\n\u001a>\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\f\u001a>\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000e2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\n\u001a>\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000e2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\f\u001a>\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00112\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013\u001a>\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00112\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0014\u001a>\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00162\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0018\u001a>\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00162\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0019\u001a>\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u001b2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001d\u001a>\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u001b2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001e\u001a>\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00062\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010\n\u001a>\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u00062\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010\f\u001a>\u0010#\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u000e2\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010\n\u001a>\u0010#\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000e2\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010\f\u001a>\u0010%\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00112\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010\u0013\u001a>\u0010%\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u00112\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010\u0014\u001a>\u0010'\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00162\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010\u0018\u001a>\u0010'\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u00162\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010\u0019\u001a>\u0010)\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u001b2\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010\u001d\u001a>\u0010)\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u001b2\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010\u001e\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006+"}, d2 = {"loadByteArray", "", "Lio/ktor/utils/io/bits/Memory;", TypedValues.CycleType.S_WAVE_OFFSET, "", "destination", "", "destinationOffset", "count", "loadByteArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[BII)V", "", "(Ljava/nio/ByteBuffer;J[BII)V", "loadUByteArray", "Lkotlin/UByteArray;", "loadUByteArray-KqtU1YU", "loadUIntArray", "Lkotlin/UIntArray;", "loadUIntArray-EM3dPTA", "(Ljava/nio/ByteBuffer;I[III)V", "(Ljava/nio/ByteBuffer;J[III)V", "loadULongArray", "Lkotlin/ULongArray;", "loadULongArray-bNlDJKc", "(Ljava/nio/ByteBuffer;I[JII)V", "(Ljava/nio/ByteBuffer;J[JII)V", "loadUShortArray", "Lkotlin/UShortArray;", "loadUShortArray-m8CCUi4", "(Ljava/nio/ByteBuffer;I[SII)V", "(Ljava/nio/ByteBuffer;J[SII)V", "storeByteArray", "source", "sourceOffset", "storeByteArray-9zorpBc", "storeUByteArray", "storeUByteArray-KqtU1YU", "storeUIntArray", "storeUIntArray-EM3dPTA", "storeULongArray", "storeULongArray-bNlDJKc", "storeUShortArray", "storeUShortArray-m8CCUi4", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class PrimiteArraysKt {
    /* renamed from: loadByteArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m301loadByteArray9zorpBc$default(ByteBuffer loadByteArray, int offset, byte[] destination, int destinationOffset, int count, int i, Object obj) {
        if ((i & 4) != 0) {
            destinationOffset = 0;
        }
        if ((i & 8) != 0) {
            count = destination.length - destinationOffset;
        }
        Intrinsics.checkNotNullParameter(loadByteArray, "$this$loadByteArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        MemoryJvmKt.m254copyTo9zorpBc(loadByteArray, destination, offset, count, destinationOffset);
    }

    /* renamed from: loadByteArray-9zorpBc, reason: not valid java name */
    public static final void m299loadByteArray9zorpBc(ByteBuffer loadByteArray, int offset, byte[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadByteArray, "$this$loadByteArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        MemoryJvmKt.m254copyTo9zorpBc(loadByteArray, destination, offset, count, destinationOffset);
    }

    /* renamed from: loadByteArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m302loadByteArray9zorpBc$default(ByteBuffer loadByteArray, long offset, byte[] destination, int destinationOffset, int count, int i, Object obj) {
        int destinationOffset2;
        int count2;
        if ((i & 4) == 0) {
            destinationOffset2 = destinationOffset;
        } else {
            destinationOffset2 = 0;
        }
        int destinationOffset3 = i & 8;
        if (destinationOffset3 == 0) {
            count2 = count;
        } else {
            int count3 = destination.length - destinationOffset2;
            count2 = count3;
        }
        Intrinsics.checkNotNullParameter(loadByteArray, "$this$loadByteArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        MemoryJvmKt.m255copyTo9zorpBc(loadByteArray, destination, offset, count2, destinationOffset2);
    }

    /* renamed from: loadByteArray-9zorpBc, reason: not valid java name */
    public static final void m300loadByteArray9zorpBc(ByteBuffer loadByteArray, long offset, byte[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadByteArray, "$this$loadByteArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        MemoryJvmKt.m255copyTo9zorpBc(loadByteArray, destination, offset, count, destinationOffset);
    }

    /* renamed from: loadUByteArray-KqtU1YU$default, reason: not valid java name */
    public static /* synthetic */ void m305loadUByteArrayKqtU1YU$default(ByteBuffer loadUByteArray, int offset, byte[] destination, int destinationOffset, int count, int i, Object obj) {
        if ((i & 4) != 0) {
            destinationOffset = 0;
        }
        if ((i & 8) != 0) {
            count = UByteArray.m587getSizeimpl(destination) - destinationOffset;
        }
        Intrinsics.checkNotNullParameter(loadUByteArray, "$this$loadUByteArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        MemoryJvmKt.m254copyTo9zorpBc(loadUByteArray, destination, offset, count, destinationOffset);
    }

    /* renamed from: loadUByteArray-KqtU1YU, reason: not valid java name */
    public static final void m303loadUByteArrayKqtU1YU(ByteBuffer loadUByteArray, int offset, byte[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadUByteArray, "$this$loadUByteArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        MemoryJvmKt.m254copyTo9zorpBc(loadUByteArray, destination, offset, count, destinationOffset);
    }

    /* renamed from: loadUByteArray-KqtU1YU$default, reason: not valid java name */
    public static /* synthetic */ void m306loadUByteArrayKqtU1YU$default(ByteBuffer loadUByteArray, long offset, byte[] destination, int destinationOffset, int count, int i, Object obj) {
        int destinationOffset2;
        int count2;
        if ((i & 4) == 0) {
            destinationOffset2 = destinationOffset;
        } else {
            destinationOffset2 = 0;
        }
        int destinationOffset3 = i & 8;
        if (destinationOffset3 == 0) {
            count2 = count;
        } else {
            int count3 = UByteArray.m587getSizeimpl(destination) - destinationOffset2;
            count2 = count3;
        }
        Intrinsics.checkNotNullParameter(loadUByteArray, "$this$loadUByteArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        MemoryJvmKt.m255copyTo9zorpBc(loadUByteArray, destination, offset, count2, destinationOffset2);
    }

    /* renamed from: loadUByteArray-KqtU1YU, reason: not valid java name */
    public static final void m304loadUByteArrayKqtU1YU(ByteBuffer loadUByteArray, long offset, byte[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadUByteArray, "$this$loadUByteArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        MemoryJvmKt.m255copyTo9zorpBc(loadUByteArray, destination, offset, count, destinationOffset);
    }

    /* renamed from: loadUShortArray-m8CCUi4$default, reason: not valid java name */
    public static /* synthetic */ void m317loadUShortArraym8CCUi4$default(ByteBuffer loadUShortArray, int offset, short[] destination, int destinationOffset, int count, int i, Object obj) {
        if ((i & 4) != 0) {
            destinationOffset = 0;
        }
        if ((i & 8) != 0) {
            count = UShortArray.m850getSizeimpl(destination) - destinationOffset;
        }
        Intrinsics.checkNotNullParameter(loadUShortArray, "$this$loadUShortArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        PrimitiveArraysJvmKt.m355loadShortArray9zorpBc(loadUShortArray, offset, destination, destinationOffset, count);
    }

    /* renamed from: loadUShortArray-m8CCUi4, reason: not valid java name */
    public static final void m315loadUShortArraym8CCUi4(ByteBuffer loadUShortArray, int offset, short[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadUShortArray, "$this$loadUShortArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        PrimitiveArraysJvmKt.m355loadShortArray9zorpBc(loadUShortArray, offset, destination, destinationOffset, count);
    }

    /* renamed from: loadUShortArray-m8CCUi4$default, reason: not valid java name */
    public static /* synthetic */ void m318loadUShortArraym8CCUi4$default(ByteBuffer loadUShortArray, long offset, short[] destination, int destinationOffset, int count, int i, Object obj) {
        int destinationOffset2;
        int count2;
        if ((i & 4) == 0) {
            destinationOffset2 = destinationOffset;
        } else {
            destinationOffset2 = 0;
        }
        int destinationOffset3 = i & 8;
        if (destinationOffset3 == 0) {
            count2 = count;
        } else {
            int count3 = UShortArray.m850getSizeimpl(destination) - destinationOffset2;
            count2 = count3;
        }
        Intrinsics.checkNotNullParameter(loadUShortArray, "$this$loadUShortArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        PrimitiveArraysJvmKt.m356loadShortArray9zorpBc(loadUShortArray, offset, destination, destinationOffset2, count2);
    }

    /* renamed from: loadUShortArray-m8CCUi4, reason: not valid java name */
    public static final void m316loadUShortArraym8CCUi4(ByteBuffer loadUShortArray, long offset, short[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadUShortArray, "$this$loadUShortArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        PrimitiveArraysJvmKt.m356loadShortArray9zorpBc(loadUShortArray, offset, destination, destinationOffset, count);
    }

    /* renamed from: loadUIntArray-EM3dPTA$default, reason: not valid java name */
    public static /* synthetic */ void m309loadUIntArrayEM3dPTA$default(ByteBuffer loadUIntArray, int offset, int[] destination, int destinationOffset, int count, int i, Object obj) {
        if ((i & 4) != 0) {
            destinationOffset = 0;
        }
        if ((i & 8) != 0) {
            count = UIntArray.m666getSizeimpl(destination) - destinationOffset;
        }
        Intrinsics.checkNotNullParameter(loadUIntArray, "$this$loadUIntArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        PrimitiveArraysJvmKt.m347loadIntArray9zorpBc(loadUIntArray, offset, destination, destinationOffset, count);
    }

    /* renamed from: loadUIntArray-EM3dPTA, reason: not valid java name */
    public static final void m307loadUIntArrayEM3dPTA(ByteBuffer loadUIntArray, int offset, int[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadUIntArray, "$this$loadUIntArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        PrimitiveArraysJvmKt.m347loadIntArray9zorpBc(loadUIntArray, offset, destination, destinationOffset, count);
    }

    /* renamed from: loadUIntArray-EM3dPTA$default, reason: not valid java name */
    public static /* synthetic */ void m310loadUIntArrayEM3dPTA$default(ByteBuffer loadUIntArray, long offset, int[] destination, int destinationOffset, int count, int i, Object obj) {
        int destinationOffset2;
        int count2;
        if ((i & 4) == 0) {
            destinationOffset2 = destinationOffset;
        } else {
            destinationOffset2 = 0;
        }
        int destinationOffset3 = i & 8;
        if (destinationOffset3 == 0) {
            count2 = count;
        } else {
            int count3 = UIntArray.m666getSizeimpl(destination) - destinationOffset2;
            count2 = count3;
        }
        Intrinsics.checkNotNullParameter(loadUIntArray, "$this$loadUIntArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        PrimitiveArraysJvmKt.m348loadIntArray9zorpBc(loadUIntArray, offset, destination, destinationOffset2, count2);
    }

    /* renamed from: loadUIntArray-EM3dPTA, reason: not valid java name */
    public static final void m308loadUIntArrayEM3dPTA(ByteBuffer loadUIntArray, long offset, int[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadUIntArray, "$this$loadUIntArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        PrimitiveArraysJvmKt.m348loadIntArray9zorpBc(loadUIntArray, offset, destination, destinationOffset, count);
    }

    /* renamed from: loadULongArray-bNlDJKc$default, reason: not valid java name */
    public static /* synthetic */ void m313loadULongArraybNlDJKc$default(ByteBuffer loadULongArray, int offset, long[] destination, int destinationOffset, int count, int i, Object obj) {
        if ((i & 4) != 0) {
            destinationOffset = 0;
        }
        if ((i & 8) != 0) {
            count = ULongArray.m745getSizeimpl(destination) - destinationOffset;
        }
        Intrinsics.checkNotNullParameter(loadULongArray, "$this$loadULongArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        PrimitiveArraysJvmKt.m351loadLongArray9zorpBc(loadULongArray, offset, destination, destinationOffset, count);
    }

    /* renamed from: loadULongArray-bNlDJKc, reason: not valid java name */
    public static final void m311loadULongArraybNlDJKc(ByteBuffer loadULongArray, int offset, long[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadULongArray, "$this$loadULongArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        PrimitiveArraysJvmKt.m351loadLongArray9zorpBc(loadULongArray, offset, destination, destinationOffset, count);
    }

    /* renamed from: loadULongArray-bNlDJKc$default, reason: not valid java name */
    public static /* synthetic */ void m314loadULongArraybNlDJKc$default(ByteBuffer loadULongArray, long offset, long[] destination, int destinationOffset, int count, int i, Object obj) {
        int destinationOffset2;
        int count2;
        if ((i & 4) == 0) {
            destinationOffset2 = destinationOffset;
        } else {
            destinationOffset2 = 0;
        }
        int destinationOffset3 = i & 8;
        if (destinationOffset3 == 0) {
            count2 = count;
        } else {
            int count3 = ULongArray.m745getSizeimpl(destination) - destinationOffset2;
            count2 = count3;
        }
        Intrinsics.checkNotNullParameter(loadULongArray, "$this$loadULongArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        PrimitiveArraysJvmKt.m352loadLongArray9zorpBc(loadULongArray, offset, destination, destinationOffset2, count2);
    }

    /* renamed from: loadULongArray-bNlDJKc, reason: not valid java name */
    public static final void m312loadULongArraybNlDJKc(ByteBuffer loadULongArray, long offset, long[] destination, int destinationOffset, int count) {
        Intrinsics.checkNotNullParameter(loadULongArray, "$this$loadULongArray");
        Intrinsics.checkNotNullParameter(destination, "destination");
        PrimitiveArraysJvmKt.m352loadLongArray9zorpBc(loadULongArray, offset, destination, destinationOffset, count);
    }

    /* renamed from: storeByteArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m321storeByteArray9zorpBc$default(ByteBuffer storeByteArray, int offset, byte[] source, int sourceOffset, int count, int i, Object obj) {
        if ((i & 4) != 0) {
            sourceOffset = 0;
        }
        if ((i & 8) != 0) {
            count = source.length - sourceOffset;
        }
        Intrinsics.checkNotNullParameter(storeByteArray, "$this$storeByteArray");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer order = ByteBuffer.wrap(source, sourceOffset, count).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        ByteBuffer sourceMemory = Memory.m235constructorimpl(order);
        Memory.m236copyToJT6ljtQ(sourceMemory, storeByteArray, 0, count, offset);
    }

    /* renamed from: storeByteArray-9zorpBc, reason: not valid java name */
    public static final void m319storeByteArray9zorpBc(ByteBuffer storeByteArray, int offset, byte[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeByteArray, "$this$storeByteArray");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer order = ByteBuffer.wrap(source, sourceOffset, count).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        ByteBuffer sourceMemory = Memory.m235constructorimpl(order);
        Memory.m236copyToJT6ljtQ(sourceMemory, storeByteArray, 0, count, offset);
    }

    /* renamed from: storeByteArray-9zorpBc$default, reason: not valid java name */
    public static /* synthetic */ void m322storeByteArray9zorpBc$default(ByteBuffer storeByteArray, long offset, byte[] source, int sourceOffset, int count, int i, Object obj) {
        int sourceOffset2;
        int count2;
        if ((i & 4) == 0) {
            sourceOffset2 = sourceOffset;
        } else {
            sourceOffset2 = 0;
        }
        if ((i & 8) == 0) {
            count2 = count;
        } else {
            count2 = source.length - sourceOffset2;
        }
        Intrinsics.checkNotNullParameter(storeByteArray, "$this$storeByteArray");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer order = ByteBuffer.wrap(source, sourceOffset2, count2).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        ByteBuffer sourceMemory = Memory.m235constructorimpl(order);
        Memory.m237copyToJT6ljtQ(sourceMemory, storeByteArray, 0L, count2, offset);
    }

    /* renamed from: storeByteArray-9zorpBc, reason: not valid java name */
    public static final void m320storeByteArray9zorpBc(ByteBuffer storeByteArray, long offset, byte[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeByteArray, "$this$storeByteArray");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer order = ByteBuffer.wrap(source, sourceOffset, count).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        ByteBuffer sourceMemory = Memory.m235constructorimpl(order);
        Memory.m237copyToJT6ljtQ(sourceMemory, storeByteArray, 0L, count, offset);
    }

    /* renamed from: storeUByteArray-KqtU1YU$default, reason: not valid java name */
    public static /* synthetic */ void m325storeUByteArrayKqtU1YU$default(ByteBuffer storeUByteArray, int offset, byte[] source, int sourceOffset, int count, int i, Object obj) {
        if ((i & 4) != 0) {
            sourceOffset = 0;
        }
        if ((i & 8) != 0) {
            count = UByteArray.m587getSizeimpl(source) - sourceOffset;
        }
        Intrinsics.checkNotNullParameter(storeUByteArray, "$this$storeUByteArray");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer order = ByteBuffer.wrap(source, sourceOffset, count).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        ByteBuffer sourceMemory$iv = Memory.m235constructorimpl(order);
        Memory.m236copyToJT6ljtQ(sourceMemory$iv, storeUByteArray, 0, count, offset);
    }

    /* renamed from: storeUByteArray-KqtU1YU, reason: not valid java name */
    public static final void m323storeUByteArrayKqtU1YU(ByteBuffer storeUByteArray, int offset, byte[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeUByteArray, "$this$storeUByteArray");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer order = ByteBuffer.wrap(source, sourceOffset, count).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        ByteBuffer sourceMemory$iv = Memory.m235constructorimpl(order);
        Memory.m236copyToJT6ljtQ(sourceMemory$iv, storeUByteArray, 0, count, offset);
    }

    /* renamed from: storeUByteArray-KqtU1YU$default, reason: not valid java name */
    public static /* synthetic */ void m326storeUByteArrayKqtU1YU$default(ByteBuffer storeUByteArray, long offset, byte[] source, int sourceOffset, int count, int i, Object obj) {
        int sourceOffset2;
        int count2;
        if ((i & 4) == 0) {
            sourceOffset2 = sourceOffset;
        } else {
            sourceOffset2 = 0;
        }
        if ((i & 8) == 0) {
            count2 = count;
        } else {
            count2 = UByteArray.m587getSizeimpl(source) - sourceOffset2;
        }
        Intrinsics.checkNotNullParameter(storeUByteArray, "$this$storeUByteArray");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer order = ByteBuffer.wrap(source, sourceOffset2, count2).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        ByteBuffer sourceMemory$iv = Memory.m235constructorimpl(order);
        Memory.m237copyToJT6ljtQ(sourceMemory$iv, storeUByteArray, 0L, count2, offset);
    }

    /* renamed from: storeUByteArray-KqtU1YU, reason: not valid java name */
    public static final void m324storeUByteArrayKqtU1YU(ByteBuffer storeUByteArray, long offset, byte[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeUByteArray, "$this$storeUByteArray");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer order = ByteBuffer.wrap(source, sourceOffset, count).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        ByteBuffer sourceMemory$iv = Memory.m235constructorimpl(order);
        Memory.m237copyToJT6ljtQ(sourceMemory$iv, storeUByteArray, 0L, count, offset);
    }

    /* renamed from: storeUShortArray-m8CCUi4$default, reason: not valid java name */
    public static /* synthetic */ void m337storeUShortArraym8CCUi4$default(ByteBuffer storeUShortArray, int offset, short[] source, int sourceOffset, int count, int i, Object obj) {
        if ((i & 4) != 0) {
            sourceOffset = 0;
        }
        if ((i & 8) != 0) {
            count = UShortArray.m850getSizeimpl(source) - sourceOffset;
        }
        Intrinsics.checkNotNullParameter(storeUShortArray, "$this$storeUShortArray");
        Intrinsics.checkNotNullParameter(source, "source");
        PrimitiveArraysJvmKt.m375storeShortArray9zorpBc(storeUShortArray, offset, source, sourceOffset, count);
    }

    /* renamed from: storeUShortArray-m8CCUi4, reason: not valid java name */
    public static final void m335storeUShortArraym8CCUi4(ByteBuffer storeUShortArray, int offset, short[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeUShortArray, "$this$storeUShortArray");
        Intrinsics.checkNotNullParameter(source, "source");
        PrimitiveArraysJvmKt.m375storeShortArray9zorpBc(storeUShortArray, offset, source, sourceOffset, count);
    }

    /* renamed from: storeUShortArray-m8CCUi4$default, reason: not valid java name */
    public static /* synthetic */ void m338storeUShortArraym8CCUi4$default(ByteBuffer storeUShortArray, long offset, short[] source, int sourceOffset, int count, int i, Object obj) {
        int sourceOffset2;
        int count2;
        if ((i & 4) == 0) {
            sourceOffset2 = sourceOffset;
        } else {
            sourceOffset2 = 0;
        }
        int sourceOffset3 = i & 8;
        if (sourceOffset3 == 0) {
            count2 = count;
        } else {
            int count3 = UShortArray.m850getSizeimpl(source) - sourceOffset2;
            count2 = count3;
        }
        Intrinsics.checkNotNullParameter(storeUShortArray, "$this$storeUShortArray");
        Intrinsics.checkNotNullParameter(source, "source");
        PrimitiveArraysJvmKt.m376storeShortArray9zorpBc(storeUShortArray, offset, source, sourceOffset2, count2);
    }

    /* renamed from: storeUShortArray-m8CCUi4, reason: not valid java name */
    public static final void m336storeUShortArraym8CCUi4(ByteBuffer storeUShortArray, long offset, short[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeUShortArray, "$this$storeUShortArray");
        Intrinsics.checkNotNullParameter(source, "source");
        PrimitiveArraysJvmKt.m376storeShortArray9zorpBc(storeUShortArray, offset, source, sourceOffset, count);
    }

    /* renamed from: storeUIntArray-EM3dPTA$default, reason: not valid java name */
    public static /* synthetic */ void m329storeUIntArrayEM3dPTA$default(ByteBuffer storeUIntArray, int offset, int[] source, int sourceOffset, int count, int i, Object obj) {
        if ((i & 4) != 0) {
            sourceOffset = 0;
        }
        if ((i & 8) != 0) {
            count = UIntArray.m666getSizeimpl(source) - sourceOffset;
        }
        Intrinsics.checkNotNullParameter(storeUIntArray, "$this$storeUIntArray");
        Intrinsics.checkNotNullParameter(source, "source");
        PrimitiveArraysJvmKt.m367storeIntArray9zorpBc(storeUIntArray, offset, source, sourceOffset, count);
    }

    /* renamed from: storeUIntArray-EM3dPTA, reason: not valid java name */
    public static final void m327storeUIntArrayEM3dPTA(ByteBuffer storeUIntArray, int offset, int[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeUIntArray, "$this$storeUIntArray");
        Intrinsics.checkNotNullParameter(source, "source");
        PrimitiveArraysJvmKt.m367storeIntArray9zorpBc(storeUIntArray, offset, source, sourceOffset, count);
    }

    /* renamed from: storeUIntArray-EM3dPTA$default, reason: not valid java name */
    public static /* synthetic */ void m330storeUIntArrayEM3dPTA$default(ByteBuffer storeUIntArray, long offset, int[] source, int sourceOffset, int count, int i, Object obj) {
        int sourceOffset2;
        int count2;
        if ((i & 4) == 0) {
            sourceOffset2 = sourceOffset;
        } else {
            sourceOffset2 = 0;
        }
        int sourceOffset3 = i & 8;
        if (sourceOffset3 == 0) {
            count2 = count;
        } else {
            int count3 = UIntArray.m666getSizeimpl(source) - sourceOffset2;
            count2 = count3;
        }
        Intrinsics.checkNotNullParameter(storeUIntArray, "$this$storeUIntArray");
        Intrinsics.checkNotNullParameter(source, "source");
        PrimitiveArraysJvmKt.m368storeIntArray9zorpBc(storeUIntArray, offset, source, sourceOffset2, count2);
    }

    /* renamed from: storeUIntArray-EM3dPTA, reason: not valid java name */
    public static final void m328storeUIntArrayEM3dPTA(ByteBuffer storeUIntArray, long offset, int[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeUIntArray, "$this$storeUIntArray");
        Intrinsics.checkNotNullParameter(source, "source");
        PrimitiveArraysJvmKt.m368storeIntArray9zorpBc(storeUIntArray, offset, source, sourceOffset, count);
    }

    /* renamed from: storeULongArray-bNlDJKc$default, reason: not valid java name */
    public static /* synthetic */ void m333storeULongArraybNlDJKc$default(ByteBuffer storeULongArray, int offset, long[] source, int sourceOffset, int count, int i, Object obj) {
        if ((i & 4) != 0) {
            sourceOffset = 0;
        }
        if ((i & 8) != 0) {
            count = ULongArray.m745getSizeimpl(source) - sourceOffset;
        }
        Intrinsics.checkNotNullParameter(storeULongArray, "$this$storeULongArray");
        Intrinsics.checkNotNullParameter(source, "source");
        PrimitiveArraysJvmKt.m371storeLongArray9zorpBc(storeULongArray, offset, source, sourceOffset, count);
    }

    /* renamed from: storeULongArray-bNlDJKc, reason: not valid java name */
    public static final void m331storeULongArraybNlDJKc(ByteBuffer storeULongArray, int offset, long[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeULongArray, "$this$storeULongArray");
        Intrinsics.checkNotNullParameter(source, "source");
        PrimitiveArraysJvmKt.m371storeLongArray9zorpBc(storeULongArray, offset, source, sourceOffset, count);
    }

    /* renamed from: storeULongArray-bNlDJKc$default, reason: not valid java name */
    public static /* synthetic */ void m334storeULongArraybNlDJKc$default(ByteBuffer storeULongArray, long offset, long[] source, int sourceOffset, int count, int i, Object obj) {
        int sourceOffset2;
        int count2;
        if ((i & 4) == 0) {
            sourceOffset2 = sourceOffset;
        } else {
            sourceOffset2 = 0;
        }
        int sourceOffset3 = i & 8;
        if (sourceOffset3 == 0) {
            count2 = count;
        } else {
            int count3 = ULongArray.m745getSizeimpl(source) - sourceOffset2;
            count2 = count3;
        }
        Intrinsics.checkNotNullParameter(storeULongArray, "$this$storeULongArray");
        Intrinsics.checkNotNullParameter(source, "source");
        PrimitiveArraysJvmKt.m372storeLongArray9zorpBc(storeULongArray, offset, source, sourceOffset2, count2);
    }

    /* renamed from: storeULongArray-bNlDJKc, reason: not valid java name */
    public static final void m332storeULongArraybNlDJKc(ByteBuffer storeULongArray, long offset, long[] source, int sourceOffset, int count) {
        Intrinsics.checkNotNullParameter(storeULongArray, "$this$storeULongArray");
        Intrinsics.checkNotNullParameter(source, "source");
        PrimitiveArraysJvmKt.m372storeLongArray9zorpBc(storeULongArray, offset, source, sourceOffset, count);
    }
}
