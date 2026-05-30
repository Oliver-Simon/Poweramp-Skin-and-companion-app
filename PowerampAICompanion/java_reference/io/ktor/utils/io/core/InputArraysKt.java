package io.ktor.utils.io.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InputArrays.kt */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0013\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0007\u0010\b\u001a/\u0010\u0000\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0007\u0010\n\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u000f2\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00102\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00112\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00122\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00132\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a/\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u001a/\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0018\u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u000f2\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00102\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00112\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00122\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001a&\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00132\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001\u001aj\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012K\u0010\u001b\u001aG\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00150\u001cH\u0082\b\u001a\u0082\u0001\u0010\u0019\u001a\u00020\t*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\t2`\u0010\u001b\u001a\\\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00150\"H\u0082\bø\u0001\u0001\u001ar\u0010$\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u00012K\u0010\u001b\u001aG\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00150\u001cH\u0082\b\u001a\r\u0010&\u001a\u00020\u0015*\u00020\u0001H\u0082\b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006'"}, d2 = {"readAvailable", "", "Lio/ktor/utils/io/core/Input;", "destination", "Lio/ktor/utils/io/bits/Memory;", "destinationOffset", "length", "readAvailable-UAd2zVI", "(Lio/ktor/utils/io/core/Input;Ljava/nio/ByteBuffer;II)I", "", "(Lio/ktor/utils/io/core/Input;Ljava/nio/ByteBuffer;JJ)J", "dst", "Lio/ktor/utils/io/core/Buffer;", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "", "", "", "", "readFully", "", "readFully-UAd2zVI", "(Lio/ktor/utils/io/core/Input;Ljava/nio/ByteBuffer;II)V", "(Lio/ktor/utils/io/core/Input;Ljava/nio/ByteBuffer;JJ)V", "readFullyBytesTemplate", "initialDstOffset", "readBlock", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "src", "dstOffset", "count", "Lkotlin/Function4;", "srcOffset", "readFullyTemplate", "componentSize", "requireNoRemaining", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class InputArraysKt {
    public static /* synthetic */ void readFully$default(Input input, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        readFully(input, bArr, i, i2);
    }

    public static final void readFully(Input $this$readFully, byte[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int remaining$iv = length;
        ChunkBuffer current$iv$iv = UnsafeKt.prepareReadFirstHead($this$readFully, 1);
        if (current$iv$iv != null) {
            ChunkBuffer current$iv$iv2 = current$iv$iv;
            boolean release$iv$iv = true;
            int dstOffset$iv = offset;
            int remaining$iv2 = remaining$iv;
            while (true) {
                try {
                    Buffer buffer$iv = current$iv$iv2;
                    int count$iv = Math.min(remaining$iv2, buffer$iv.getWritePosition() - buffer$iv.getReadPosition());
                    int dstOffset = dstOffset$iv;
                    BufferPrimitivesKt.readFully(buffer$iv, dst, dstOffset, count$iv);
                    remaining$iv2 -= count$iv;
                    dstOffset$iv += count$iv;
                    if (!(remaining$iv2 > 0)) {
                        break;
                    }
                    release$iv$iv = false;
                    ChunkBuffer next$iv$iv = UnsafeKt.prepareReadNextHead($this$readFully, current$iv$iv2);
                    if (next$iv$iv == null) {
                        break;
                    }
                    current$iv$iv2 = next$iv$iv;
                    release$iv$iv = true;
                } finally {
                    if (release$iv$iv) {
                        UnsafeKt.completeReadHead($this$readFully, current$iv$iv2);
                    }
                }
            }
            remaining$iv = remaining$iv2;
        }
        if (remaining$iv <= 0) {
            return;
        }
        StringsKt.prematureEndOfStream(remaining$iv);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ void readFully$default(Input input, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        readFully(input, sArr, i, i2);
    }

    public static final void readFully(Input $this$readFully, short[] dst, int offset, int length) {
        Input $this$readFullyTemplate$iv;
        int after$iv$iv;
        ChunkBuffer next$iv$iv;
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        Input $this$readFullyTemplate$iv2 = $this$readFully;
        int $this$requireNoRemaining$iv = length;
        ChunkBuffer current$iv$iv = UnsafeKt.prepareReadFirstHead($this$readFullyTemplate$iv2, 1);
        if (current$iv$iv != null) {
            int size$iv$iv = 1;
            ChunkBuffer current$iv$iv2 = current$iv$iv;
            boolean release$iv$iv = true;
            int dstOffset$iv = offset;
            int remaining$iv = $this$requireNoRemaining$iv;
            while (true) {
                try {
                    Buffer this_$iv$iv$iv = current$iv$iv2;
                    int before$iv$iv = this_$iv$iv$iv.getWritePosition() - this_$iv$iv$iv.getReadPosition();
                    if (before$iv$iv >= size$iv$iv) {
                        try {
                            Buffer buffer$iv = current$iv$iv2;
                            int count$iv = Math.min(remaining$iv, (buffer$iv.getWritePosition() - buffer$iv.getReadPosition()) / 2);
                            int dstOffset = dstOffset$iv;
                            $this$readFullyTemplate$iv = $this$readFullyTemplate$iv2;
                            try {
                                BufferPrimitivesKt.readFully(buffer$iv, dst, dstOffset, count$iv);
                                remaining$iv -= count$iv;
                                dstOffset$iv += count$iv;
                                size$iv$iv = remaining$iv > 0 ? 2 : 0;
                                try {
                                    Buffer this_$iv$iv$iv2 = current$iv$iv2;
                                    after$iv$iv = this_$iv$iv$iv2.getWritePosition() - this_$iv$iv$iv2.getReadPosition();
                                } catch (Throwable th) {
                                    th = th;
                                    if (release$iv$iv) {
                                        UnsafeKt.completeReadHead($this$readFullyTemplate$iv2, current$iv$iv2);
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                Buffer this_$iv$iv$iv3 = current$iv$iv2;
                                int writePosition = this_$iv$iv$iv3.getWritePosition() - this_$iv$iv$iv3.getReadPosition();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } else {
                        $this$readFullyTemplate$iv = $this$readFullyTemplate$iv2;
                        after$iv$iv = before$iv$iv;
                    }
                    release$iv$iv = false;
                    if (after$iv$iv == 0) {
                        next$iv$iv = UnsafeKt.prepareReadNextHead($this$readFullyTemplate$iv2, current$iv$iv2);
                    } else {
                        if (after$iv$iv >= size$iv$iv) {
                            Buffer this_$iv$iv$iv4 = current$iv$iv2;
                            if (this_$iv$iv$iv4.getCapacity() - this_$iv$iv$iv4.getLimit() >= 8) {
                                next$iv$iv = current$iv$iv2;
                            }
                        }
                        UnsafeKt.completeReadHead($this$readFullyTemplate$iv2, current$iv$iv2);
                        next$iv$iv = UnsafeKt.prepareReadFirstHead($this$readFullyTemplate$iv2, size$iv$iv);
                    }
                    if (next$iv$iv == null) {
                        break;
                    }
                    current$iv$iv2 = next$iv$iv;
                    release$iv$iv = true;
                    if (size$iv$iv <= 0) {
                        break;
                    } else {
                        $this$readFullyTemplate$iv2 = $this$readFullyTemplate$iv;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }
            if (release$iv$iv) {
                UnsafeKt.completeReadHead($this$readFullyTemplate$iv2, current$iv$iv2);
            }
            $this$requireNoRemaining$iv = remaining$iv;
        }
        if ($this$requireNoRemaining$iv <= 0) {
            return;
        }
        StringsKt.prematureEndOfStream($this$requireNoRemaining$iv);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ void readFully$default(Input input, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        readFully(input, iArr, i, i2);
    }

    public static final void readFully(Input $this$readFully, int[] dst, int offset, int length) {
        Input $this$readFullyTemplate$iv;
        int after$iv$iv;
        ChunkBuffer next$iv$iv;
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        Input $this$readFullyTemplate$iv2 = $this$readFully;
        int $this$requireNoRemaining$iv = length;
        ChunkBuffer current$iv$iv = UnsafeKt.prepareReadFirstHead($this$readFullyTemplate$iv2, 1);
        if (current$iv$iv != null) {
            int size$iv$iv = 1;
            ChunkBuffer current$iv$iv2 = current$iv$iv;
            boolean release$iv$iv = true;
            int dstOffset$iv = offset;
            int remaining$iv = $this$requireNoRemaining$iv;
            while (true) {
                try {
                    Buffer this_$iv$iv$iv = current$iv$iv2;
                    int before$iv$iv = this_$iv$iv$iv.getWritePosition() - this_$iv$iv$iv.getReadPosition();
                    if (before$iv$iv >= size$iv$iv) {
                        try {
                            Buffer buffer$iv = current$iv$iv2;
                            int count$iv = Math.min(remaining$iv, (buffer$iv.getWritePosition() - buffer$iv.getReadPosition()) / 4);
                            int dstOffset = dstOffset$iv;
                            $this$readFullyTemplate$iv = $this$readFullyTemplate$iv2;
                            try {
                                BufferPrimitivesKt.readFully(buffer$iv, dst, dstOffset, count$iv);
                                remaining$iv -= count$iv;
                                dstOffset$iv += count$iv;
                                size$iv$iv = remaining$iv > 0 ? 4 : 0;
                                try {
                                    Buffer this_$iv$iv$iv2 = current$iv$iv2;
                                    after$iv$iv = this_$iv$iv$iv2.getWritePosition() - this_$iv$iv$iv2.getReadPosition();
                                } catch (Throwable th) {
                                    th = th;
                                    if (release$iv$iv) {
                                        UnsafeKt.completeReadHead($this$readFullyTemplate$iv2, current$iv$iv2);
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                Buffer this_$iv$iv$iv3 = current$iv$iv2;
                                int writePosition = this_$iv$iv$iv3.getWritePosition() - this_$iv$iv$iv3.getReadPosition();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } else {
                        $this$readFullyTemplate$iv = $this$readFullyTemplate$iv2;
                        after$iv$iv = before$iv$iv;
                    }
                    release$iv$iv = false;
                    if (after$iv$iv == 0) {
                        next$iv$iv = UnsafeKt.prepareReadNextHead($this$readFullyTemplate$iv2, current$iv$iv2);
                    } else {
                        if (after$iv$iv >= size$iv$iv) {
                            Buffer this_$iv$iv$iv4 = current$iv$iv2;
                            if (this_$iv$iv$iv4.getCapacity() - this_$iv$iv$iv4.getLimit() >= 8) {
                                next$iv$iv = current$iv$iv2;
                            }
                        }
                        UnsafeKt.completeReadHead($this$readFullyTemplate$iv2, current$iv$iv2);
                        next$iv$iv = UnsafeKt.prepareReadFirstHead($this$readFullyTemplate$iv2, size$iv$iv);
                    }
                    if (next$iv$iv == null) {
                        break;
                    }
                    current$iv$iv2 = next$iv$iv;
                    release$iv$iv = true;
                    if (size$iv$iv <= 0) {
                        break;
                    } else {
                        $this$readFullyTemplate$iv2 = $this$readFullyTemplate$iv;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }
            if (release$iv$iv) {
                UnsafeKt.completeReadHead($this$readFullyTemplate$iv2, current$iv$iv2);
            }
            $this$requireNoRemaining$iv = remaining$iv;
        }
        if ($this$requireNoRemaining$iv <= 0) {
            return;
        }
        StringsKt.prematureEndOfStream($this$requireNoRemaining$iv);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ void readFully$default(Input input, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        readFully(input, jArr, i, i2);
    }

    public static final void readFully(Input $this$readFully, long[] dst, int offset, int length) {
        Input $this$readFullyTemplate$iv;
        int after$iv$iv;
        ChunkBuffer next$iv$iv;
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        Input $this$readFullyTemplate$iv2 = $this$readFully;
        int $this$requireNoRemaining$iv = length;
        ChunkBuffer current$iv$iv = UnsafeKt.prepareReadFirstHead($this$readFullyTemplate$iv2, 1);
        if (current$iv$iv != null) {
            int size$iv$iv = 1;
            ChunkBuffer current$iv$iv2 = current$iv$iv;
            boolean release$iv$iv = true;
            int dstOffset$iv = offset;
            int remaining$iv = $this$requireNoRemaining$iv;
            while (true) {
                try {
                    Buffer this_$iv$iv$iv = current$iv$iv2;
                    int before$iv$iv = this_$iv$iv$iv.getWritePosition() - this_$iv$iv$iv.getReadPosition();
                    if (before$iv$iv >= size$iv$iv) {
                        try {
                            Buffer buffer$iv = current$iv$iv2;
                            int count$iv = Math.min(remaining$iv, (buffer$iv.getWritePosition() - buffer$iv.getReadPosition()) / 8);
                            int dstOffset = dstOffset$iv;
                            $this$readFullyTemplate$iv = $this$readFullyTemplate$iv2;
                            try {
                                BufferPrimitivesKt.readFully(buffer$iv, dst, dstOffset, count$iv);
                                remaining$iv -= count$iv;
                                dstOffset$iv += count$iv;
                                size$iv$iv = remaining$iv > 0 ? 8 : 0;
                                try {
                                    Buffer this_$iv$iv$iv2 = current$iv$iv2;
                                    after$iv$iv = this_$iv$iv$iv2.getWritePosition() - this_$iv$iv$iv2.getReadPosition();
                                } catch (Throwable th) {
                                    th = th;
                                    if (release$iv$iv) {
                                        UnsafeKt.completeReadHead($this$readFullyTemplate$iv2, current$iv$iv2);
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                Buffer this_$iv$iv$iv3 = current$iv$iv2;
                                int writePosition = this_$iv$iv$iv3.getWritePosition() - this_$iv$iv$iv3.getReadPosition();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } else {
                        $this$readFullyTemplate$iv = $this$readFullyTemplate$iv2;
                        after$iv$iv = before$iv$iv;
                    }
                    release$iv$iv = false;
                    if (after$iv$iv == 0) {
                        next$iv$iv = UnsafeKt.prepareReadNextHead($this$readFullyTemplate$iv2, current$iv$iv2);
                    } else {
                        if (after$iv$iv >= size$iv$iv) {
                            Buffer this_$iv$iv$iv4 = current$iv$iv2;
                            if (this_$iv$iv$iv4.getCapacity() - this_$iv$iv$iv4.getLimit() >= 8) {
                                next$iv$iv = current$iv$iv2;
                            }
                        }
                        UnsafeKt.completeReadHead($this$readFullyTemplate$iv2, current$iv$iv2);
                        next$iv$iv = UnsafeKt.prepareReadFirstHead($this$readFullyTemplate$iv2, size$iv$iv);
                    }
                    if (next$iv$iv == null) {
                        break;
                    }
                    current$iv$iv2 = next$iv$iv;
                    release$iv$iv = true;
                    if (size$iv$iv <= 0) {
                        break;
                    } else {
                        $this$readFullyTemplate$iv2 = $this$readFullyTemplate$iv;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }
            if (release$iv$iv) {
                UnsafeKt.completeReadHead($this$readFullyTemplate$iv2, current$iv$iv2);
            }
            $this$requireNoRemaining$iv = remaining$iv;
        }
        if ($this$requireNoRemaining$iv <= 0) {
            return;
        }
        StringsKt.prematureEndOfStream($this$requireNoRemaining$iv);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ void readFully$default(Input input, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        readFully(input, fArr, i, i2);
    }

    public static final void readFully(Input $this$readFully, float[] dst, int offset, int length) {
        Input $this$readFullyTemplate$iv;
        int after$iv$iv;
        ChunkBuffer next$iv$iv;
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        Input $this$readFullyTemplate$iv2 = $this$readFully;
        int $this$requireNoRemaining$iv = length;
        ChunkBuffer current$iv$iv = UnsafeKt.prepareReadFirstHead($this$readFullyTemplate$iv2, 1);
        if (current$iv$iv != null) {
            int size$iv$iv = 1;
            ChunkBuffer current$iv$iv2 = current$iv$iv;
            boolean release$iv$iv = true;
            int dstOffset$iv = offset;
            int remaining$iv = $this$requireNoRemaining$iv;
            while (true) {
                try {
                    Buffer this_$iv$iv$iv = current$iv$iv2;
                    int before$iv$iv = this_$iv$iv$iv.getWritePosition() - this_$iv$iv$iv.getReadPosition();
                    if (before$iv$iv >= size$iv$iv) {
                        try {
                            Buffer buffer$iv = current$iv$iv2;
                            int count$iv = Math.min(remaining$iv, (buffer$iv.getWritePosition() - buffer$iv.getReadPosition()) / 4);
                            int dstOffset = dstOffset$iv;
                            $this$readFullyTemplate$iv = $this$readFullyTemplate$iv2;
                            try {
                                BufferPrimitivesKt.readFully(buffer$iv, dst, dstOffset, count$iv);
                                remaining$iv -= count$iv;
                                dstOffset$iv += count$iv;
                                size$iv$iv = remaining$iv > 0 ? 4 : 0;
                                try {
                                    Buffer this_$iv$iv$iv2 = current$iv$iv2;
                                    after$iv$iv = this_$iv$iv$iv2.getWritePosition() - this_$iv$iv$iv2.getReadPosition();
                                } catch (Throwable th) {
                                    th = th;
                                    if (release$iv$iv) {
                                        UnsafeKt.completeReadHead($this$readFullyTemplate$iv2, current$iv$iv2);
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                Buffer this_$iv$iv$iv3 = current$iv$iv2;
                                int writePosition = this_$iv$iv$iv3.getWritePosition() - this_$iv$iv$iv3.getReadPosition();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } else {
                        $this$readFullyTemplate$iv = $this$readFullyTemplate$iv2;
                        after$iv$iv = before$iv$iv;
                    }
                    release$iv$iv = false;
                    if (after$iv$iv == 0) {
                        next$iv$iv = UnsafeKt.prepareReadNextHead($this$readFullyTemplate$iv2, current$iv$iv2);
                    } else {
                        if (after$iv$iv >= size$iv$iv) {
                            Buffer this_$iv$iv$iv4 = current$iv$iv2;
                            if (this_$iv$iv$iv4.getCapacity() - this_$iv$iv$iv4.getLimit() >= 8) {
                                next$iv$iv = current$iv$iv2;
                            }
                        }
                        UnsafeKt.completeReadHead($this$readFullyTemplate$iv2, current$iv$iv2);
                        next$iv$iv = UnsafeKt.prepareReadFirstHead($this$readFullyTemplate$iv2, size$iv$iv);
                    }
                    if (next$iv$iv == null) {
                        break;
                    }
                    current$iv$iv2 = next$iv$iv;
                    release$iv$iv = true;
                    if (size$iv$iv <= 0) {
                        break;
                    } else {
                        $this$readFullyTemplate$iv2 = $this$readFullyTemplate$iv;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }
            if (release$iv$iv) {
                UnsafeKt.completeReadHead($this$readFullyTemplate$iv2, current$iv$iv2);
            }
            $this$requireNoRemaining$iv = remaining$iv;
        }
        if ($this$requireNoRemaining$iv <= 0) {
            return;
        }
        StringsKt.prematureEndOfStream($this$requireNoRemaining$iv);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ void readFully$default(Input input, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        readFully(input, dArr, i, i2);
    }

    public static final void readFully(Input $this$readFully, double[] dst, int offset, int length) {
        Input $this$readFullyTemplate$iv;
        int after$iv$iv;
        ChunkBuffer next$iv$iv;
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        Input $this$readFullyTemplate$iv2 = $this$readFully;
        int $this$requireNoRemaining$iv = length;
        ChunkBuffer current$iv$iv = UnsafeKt.prepareReadFirstHead($this$readFullyTemplate$iv2, 1);
        if (current$iv$iv != null) {
            int size$iv$iv = 1;
            ChunkBuffer current$iv$iv2 = current$iv$iv;
            boolean release$iv$iv = true;
            int dstOffset$iv = offset;
            int remaining$iv = $this$requireNoRemaining$iv;
            while (true) {
                try {
                    Buffer this_$iv$iv$iv = current$iv$iv2;
                    int before$iv$iv = this_$iv$iv$iv.getWritePosition() - this_$iv$iv$iv.getReadPosition();
                    if (before$iv$iv >= size$iv$iv) {
                        try {
                            Buffer buffer$iv = current$iv$iv2;
                            int count$iv = Math.min(remaining$iv, (buffer$iv.getWritePosition() - buffer$iv.getReadPosition()) / 8);
                            int dstOffset = dstOffset$iv;
                            $this$readFullyTemplate$iv = $this$readFullyTemplate$iv2;
                            try {
                                BufferPrimitivesKt.readFully(buffer$iv, dst, dstOffset, count$iv);
                                remaining$iv -= count$iv;
                                dstOffset$iv += count$iv;
                                size$iv$iv = remaining$iv > 0 ? 8 : 0;
                                try {
                                    Buffer this_$iv$iv$iv2 = current$iv$iv2;
                                    after$iv$iv = this_$iv$iv$iv2.getWritePosition() - this_$iv$iv$iv2.getReadPosition();
                                } catch (Throwable th) {
                                    th = th;
                                    if (release$iv$iv) {
                                        UnsafeKt.completeReadHead($this$readFullyTemplate$iv2, current$iv$iv2);
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                Buffer this_$iv$iv$iv3 = current$iv$iv2;
                                int writePosition = this_$iv$iv$iv3.getWritePosition() - this_$iv$iv$iv3.getReadPosition();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } else {
                        $this$readFullyTemplate$iv = $this$readFullyTemplate$iv2;
                        after$iv$iv = before$iv$iv;
                    }
                    release$iv$iv = false;
                    if (after$iv$iv == 0) {
                        next$iv$iv = UnsafeKt.prepareReadNextHead($this$readFullyTemplate$iv2, current$iv$iv2);
                    } else {
                        if (after$iv$iv >= size$iv$iv) {
                            Buffer this_$iv$iv$iv4 = current$iv$iv2;
                            if (this_$iv$iv$iv4.getCapacity() - this_$iv$iv$iv4.getLimit() >= 8) {
                                next$iv$iv = current$iv$iv2;
                            }
                        }
                        UnsafeKt.completeReadHead($this$readFullyTemplate$iv2, current$iv$iv2);
                        next$iv$iv = UnsafeKt.prepareReadFirstHead($this$readFullyTemplate$iv2, size$iv$iv);
                    }
                    if (next$iv$iv == null) {
                        break;
                    }
                    current$iv$iv2 = next$iv$iv;
                    release$iv$iv = true;
                    if (size$iv$iv <= 0) {
                        break;
                    } else {
                        $this$readFullyTemplate$iv2 = $this$readFullyTemplate$iv;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }
            if (release$iv$iv) {
                UnsafeKt.completeReadHead($this$readFullyTemplate$iv2, current$iv$iv2);
            }
            $this$requireNoRemaining$iv = remaining$iv;
        }
        if ($this$requireNoRemaining$iv <= 0) {
            return;
        }
        StringsKt.prematureEndOfStream($this$requireNoRemaining$iv);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ void readFully$default(Input input, Buffer this_$iv, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = this_$iv.getLimit() - this_$iv.getWritePosition();
        }
        readFully(input, this_$iv, i);
    }

    public static final void readFully(Input $this$readFully, Buffer dst, int length) {
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int remaining$iv = length;
        ChunkBuffer current$iv$iv = UnsafeKt.prepareReadFirstHead($this$readFully, 1);
        if (current$iv$iv != null) {
            ChunkBuffer current$iv$iv2 = current$iv$iv;
            boolean release$iv$iv = true;
            int dstOffset$iv = 0;
            int remaining$iv2 = remaining$iv;
            while (true) {
                try {
                    Buffer buffer$iv = current$iv$iv2;
                    int count$iv = Math.min(remaining$iv2, buffer$iv.getWritePosition() - buffer$iv.getReadPosition());
                    BufferPrimitivesKt.readFully(buffer$iv, dst, count$iv);
                    remaining$iv2 -= count$iv;
                    dstOffset$iv += count$iv;
                    if (!(remaining$iv2 > 0)) {
                        break;
                    }
                    release$iv$iv = false;
                    ChunkBuffer next$iv$iv = UnsafeKt.prepareReadNextHead($this$readFully, current$iv$iv2);
                    if (next$iv$iv == null) {
                        break;
                    }
                    current$iv$iv2 = next$iv$iv;
                    release$iv$iv = true;
                } finally {
                    if (release$iv$iv) {
                        UnsafeKt.completeReadHead($this$readFully, current$iv$iv2);
                    }
                }
            }
            remaining$iv = remaining$iv2;
        }
        if (remaining$iv <= 0) {
            return;
        }
        StringsKt.prematureEndOfStream(remaining$iv);
        throw new KotlinNothingValueException();
    }

    /* renamed from: readFully-UAd2zVI */
    public static final void m423readFullyUAd2zVI(Input readFully, ByteBuffer destination, int destinationOffset, int length) {
        Intrinsics.checkNotNullParameter(readFully, "$this$readFully");
        Intrinsics.checkNotNullParameter(destination, "destination");
        m424readFullyUAd2zVI(readFully, destination, destinationOffset, length);
    }

    /* renamed from: readFully-UAd2zVI */
    public static final void m424readFullyUAd2zVI(Input readFully, ByteBuffer destination, long destinationOffset, long length) {
        Intrinsics.checkNotNullParameter(readFully, "$this$readFully");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (m422readAvailableUAd2zVI(readFully, destination, destinationOffset, length) != length) {
            StringsKt.prematureEndOfStream(length);
            throw new KotlinNothingValueException();
        }
    }

    public static /* synthetic */ int readAvailable$default(Input input, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        return readAvailable(input, bArr, i, i2);
    }

    public static final int readAvailable(Input $this$readAvailable, byte[] dst, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int remaining$iv = length;
        ChunkBuffer current$iv$iv = UnsafeKt.prepareReadFirstHead($this$readAvailable, 1);
        if (current$iv$iv != null) {
            ChunkBuffer current$iv$iv2 = current$iv$iv;
            boolean release$iv$iv = true;
            int dstOffset$iv = offset;
            int remaining$iv2 = remaining$iv;
            while (true) {
                try {
                    Buffer buffer$iv = current$iv$iv2;
                    int count$iv = Math.min(remaining$iv2, buffer$iv.getWritePosition() - buffer$iv.getReadPosition());
                    int dstOffset = dstOffset$iv;
                    BufferPrimitivesKt.readFully(buffer$iv, dst, dstOffset, count$iv);
                    remaining$iv2 -= count$iv;
                    dstOffset$iv += count$iv;
                    if (!(remaining$iv2 > 0)) {
                        break;
                    }
                    release$iv$iv = false;
                    ChunkBuffer next$iv$iv = UnsafeKt.prepareReadNextHead($this$readAvailable, current$iv$iv2);
                    if (next$iv$iv == null) {
                        break;
                    }
                    current$iv$iv2 = next$iv$iv;
                    release$iv$iv = true;
                } finally {
                    if (release$iv$iv) {
                        UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
                    }
                }
            }
            remaining$iv = remaining$iv2;
        }
        return length - remaining$iv;
    }

    public static /* synthetic */ int readAvailable$default(Input input, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        return readAvailable(input, sArr, i, i2);
    }

    public static final int readAvailable(Input $this$readAvailable, short[] dst, int offset, int length) {
        int componentSize$iv;
        int componentSize$iv2;
        ChunkBuffer next$iv$iv;
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int componentSize$iv3 = 2;
        int remaining$iv = length;
        ChunkBuffer current$iv$iv = UnsafeKt.prepareReadFirstHead($this$readAvailable, 1);
        if (current$iv$iv != null) {
            int size$iv$iv = 1;
            ChunkBuffer current$iv$iv2 = current$iv$iv;
            boolean release$iv$iv = true;
            int dstOffset$iv = offset;
            int remaining$iv2 = remaining$iv;
            while (true) {
                try {
                    Buffer this_$iv$iv$iv = current$iv$iv2;
                    int before$iv$iv = this_$iv$iv$iv.getWritePosition() - this_$iv$iv$iv.getReadPosition();
                    if (before$iv$iv >= size$iv$iv) {
                        try {
                            Buffer buffer$iv = current$iv$iv2;
                            int count$iv = Math.min(remaining$iv2, (buffer$iv.getWritePosition() - buffer$iv.getReadPosition()) / componentSize$iv3);
                            int dstOffset = dstOffset$iv;
                            componentSize$iv = componentSize$iv3;
                            try {
                                BufferPrimitivesKt.readFully(buffer$iv, dst, dstOffset, count$iv);
                                remaining$iv2 -= count$iv;
                                dstOffset$iv += count$iv;
                                size$iv$iv = remaining$iv2 > 0 ? componentSize$iv : 0;
                                try {
                                    Buffer this_$iv$iv$iv2 = current$iv$iv2;
                                    componentSize$iv2 = this_$iv$iv$iv2.getWritePosition() - this_$iv$iv$iv2.getReadPosition();
                                } catch (Throwable th) {
                                    th = th;
                                    if (release$iv$iv) {
                                        UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                Buffer this_$iv$iv$iv3 = current$iv$iv2;
                                int writePosition = this_$iv$iv$iv3.getWritePosition() - this_$iv$iv$iv3.getReadPosition();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } else {
                        componentSize$iv = componentSize$iv3;
                        componentSize$iv2 = before$iv$iv;
                    }
                    release$iv$iv = false;
                    if (componentSize$iv2 == 0) {
                        next$iv$iv = UnsafeKt.prepareReadNextHead($this$readAvailable, current$iv$iv2);
                    } else {
                        if (componentSize$iv2 >= size$iv$iv) {
                            Buffer this_$iv$iv$iv4 = current$iv$iv2;
                            if (this_$iv$iv$iv4.getCapacity() - this_$iv$iv$iv4.getLimit() >= 8) {
                                next$iv$iv = current$iv$iv2;
                            }
                        }
                        UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
                        next$iv$iv = UnsafeKt.prepareReadFirstHead($this$readAvailable, size$iv$iv);
                    }
                    if (next$iv$iv == null) {
                        break;
                    }
                    current$iv$iv2 = next$iv$iv;
                    release$iv$iv = true;
                    if (size$iv$iv <= 0) {
                        break;
                    }
                    componentSize$iv3 = componentSize$iv;
                } catch (Throwable th4) {
                    th = th4;
                }
            }
            if (release$iv$iv) {
                UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
            }
            remaining$iv = remaining$iv2;
        }
        return length - remaining$iv;
    }

    public static /* synthetic */ int readAvailable$default(Input input, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        return readAvailable(input, iArr, i, i2);
    }

    public static final int readAvailable(Input $this$readAvailable, int[] dst, int offset, int length) {
        int componentSize$iv;
        int componentSize$iv2;
        ChunkBuffer next$iv$iv;
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int componentSize$iv3 = 4;
        int remaining$iv = length;
        ChunkBuffer current$iv$iv = UnsafeKt.prepareReadFirstHead($this$readAvailable, 1);
        if (current$iv$iv != null) {
            int size$iv$iv = 1;
            ChunkBuffer current$iv$iv2 = current$iv$iv;
            boolean release$iv$iv = true;
            int dstOffset$iv = offset;
            int remaining$iv2 = remaining$iv;
            while (true) {
                try {
                    Buffer this_$iv$iv$iv = current$iv$iv2;
                    int before$iv$iv = this_$iv$iv$iv.getWritePosition() - this_$iv$iv$iv.getReadPosition();
                    if (before$iv$iv >= size$iv$iv) {
                        try {
                            Buffer buffer$iv = current$iv$iv2;
                            int count$iv = Math.min(remaining$iv2, (buffer$iv.getWritePosition() - buffer$iv.getReadPosition()) / componentSize$iv3);
                            int dstOffset = dstOffset$iv;
                            componentSize$iv = componentSize$iv3;
                            try {
                                BufferPrimitivesKt.readFully(buffer$iv, dst, dstOffset, count$iv);
                                remaining$iv2 -= count$iv;
                                dstOffset$iv += count$iv;
                                size$iv$iv = remaining$iv2 > 0 ? componentSize$iv : 0;
                                try {
                                    Buffer this_$iv$iv$iv2 = current$iv$iv2;
                                    componentSize$iv2 = this_$iv$iv$iv2.getWritePosition() - this_$iv$iv$iv2.getReadPosition();
                                } catch (Throwable th) {
                                    th = th;
                                    if (release$iv$iv) {
                                        UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                Buffer this_$iv$iv$iv3 = current$iv$iv2;
                                int writePosition = this_$iv$iv$iv3.getWritePosition() - this_$iv$iv$iv3.getReadPosition();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } else {
                        componentSize$iv = componentSize$iv3;
                        componentSize$iv2 = before$iv$iv;
                    }
                    release$iv$iv = false;
                    if (componentSize$iv2 == 0) {
                        next$iv$iv = UnsafeKt.prepareReadNextHead($this$readAvailable, current$iv$iv2);
                    } else {
                        if (componentSize$iv2 >= size$iv$iv) {
                            Buffer this_$iv$iv$iv4 = current$iv$iv2;
                            if (this_$iv$iv$iv4.getCapacity() - this_$iv$iv$iv4.getLimit() >= 8) {
                                next$iv$iv = current$iv$iv2;
                            }
                        }
                        UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
                        next$iv$iv = UnsafeKt.prepareReadFirstHead($this$readAvailable, size$iv$iv);
                    }
                    if (next$iv$iv == null) {
                        break;
                    }
                    current$iv$iv2 = next$iv$iv;
                    release$iv$iv = true;
                    if (size$iv$iv <= 0) {
                        break;
                    }
                    componentSize$iv3 = componentSize$iv;
                } catch (Throwable th4) {
                    th = th4;
                }
            }
            if (release$iv$iv) {
                UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
            }
            remaining$iv = remaining$iv2;
        }
        return length - remaining$iv;
    }

    public static /* synthetic */ int readAvailable$default(Input input, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        return readAvailable(input, jArr, i, i2);
    }

    public static final int readAvailable(Input $this$readAvailable, long[] dst, int offset, int length) {
        int componentSize$iv;
        int componentSize$iv2;
        ChunkBuffer next$iv$iv;
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int componentSize$iv3 = 8;
        int remaining$iv = length;
        ChunkBuffer current$iv$iv = UnsafeKt.prepareReadFirstHead($this$readAvailable, 1);
        if (current$iv$iv != null) {
            int size$iv$iv = 1;
            ChunkBuffer current$iv$iv2 = current$iv$iv;
            boolean release$iv$iv = true;
            int dstOffset$iv = offset;
            int remaining$iv2 = remaining$iv;
            while (true) {
                try {
                    Buffer this_$iv$iv$iv = current$iv$iv2;
                    int before$iv$iv = this_$iv$iv$iv.getWritePosition() - this_$iv$iv$iv.getReadPosition();
                    if (before$iv$iv >= size$iv$iv) {
                        try {
                            Buffer buffer$iv = current$iv$iv2;
                            int count$iv = Math.min(remaining$iv2, (buffer$iv.getWritePosition() - buffer$iv.getReadPosition()) / componentSize$iv3);
                            int dstOffset = dstOffset$iv;
                            componentSize$iv = componentSize$iv3;
                            try {
                                BufferPrimitivesKt.readFully(buffer$iv, dst, dstOffset, count$iv);
                                remaining$iv2 -= count$iv;
                                dstOffset$iv += count$iv;
                                size$iv$iv = remaining$iv2 > 0 ? componentSize$iv : 0;
                                try {
                                    Buffer this_$iv$iv$iv2 = current$iv$iv2;
                                    componentSize$iv2 = this_$iv$iv$iv2.getWritePosition() - this_$iv$iv$iv2.getReadPosition();
                                } catch (Throwable th) {
                                    th = th;
                                    if (release$iv$iv) {
                                        UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                Buffer this_$iv$iv$iv3 = current$iv$iv2;
                                int writePosition = this_$iv$iv$iv3.getWritePosition() - this_$iv$iv$iv3.getReadPosition();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } else {
                        componentSize$iv = componentSize$iv3;
                        componentSize$iv2 = before$iv$iv;
                    }
                    release$iv$iv = false;
                    if (componentSize$iv2 == 0) {
                        next$iv$iv = UnsafeKt.prepareReadNextHead($this$readAvailable, current$iv$iv2);
                    } else {
                        if (componentSize$iv2 >= size$iv$iv) {
                            Buffer this_$iv$iv$iv4 = current$iv$iv2;
                            if (this_$iv$iv$iv4.getCapacity() - this_$iv$iv$iv4.getLimit() >= 8) {
                                next$iv$iv = current$iv$iv2;
                            }
                        }
                        UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
                        next$iv$iv = UnsafeKt.prepareReadFirstHead($this$readAvailable, size$iv$iv);
                    }
                    if (next$iv$iv == null) {
                        break;
                    }
                    current$iv$iv2 = next$iv$iv;
                    release$iv$iv = true;
                    if (size$iv$iv <= 0) {
                        break;
                    }
                    componentSize$iv3 = componentSize$iv;
                } catch (Throwable th4) {
                    th = th4;
                }
            }
            if (release$iv$iv) {
                UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
            }
            remaining$iv = remaining$iv2;
        }
        return length - remaining$iv;
    }

    public static /* synthetic */ int readAvailable$default(Input input, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        return readAvailable(input, fArr, i, i2);
    }

    public static final int readAvailable(Input $this$readAvailable, float[] dst, int offset, int length) {
        int componentSize$iv;
        int componentSize$iv2;
        ChunkBuffer next$iv$iv;
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int componentSize$iv3 = 4;
        int remaining$iv = length;
        ChunkBuffer current$iv$iv = UnsafeKt.prepareReadFirstHead($this$readAvailable, 1);
        if (current$iv$iv != null) {
            int size$iv$iv = 1;
            ChunkBuffer current$iv$iv2 = current$iv$iv;
            boolean release$iv$iv = true;
            int dstOffset$iv = offset;
            int remaining$iv2 = remaining$iv;
            while (true) {
                try {
                    Buffer this_$iv$iv$iv = current$iv$iv2;
                    int before$iv$iv = this_$iv$iv$iv.getWritePosition() - this_$iv$iv$iv.getReadPosition();
                    if (before$iv$iv >= size$iv$iv) {
                        try {
                            Buffer buffer$iv = current$iv$iv2;
                            int count$iv = Math.min(remaining$iv2, (buffer$iv.getWritePosition() - buffer$iv.getReadPosition()) / componentSize$iv3);
                            int dstOffset = dstOffset$iv;
                            componentSize$iv = componentSize$iv3;
                            try {
                                BufferPrimitivesKt.readFully(buffer$iv, dst, dstOffset, count$iv);
                                remaining$iv2 -= count$iv;
                                dstOffset$iv += count$iv;
                                size$iv$iv = remaining$iv2 > 0 ? componentSize$iv : 0;
                                try {
                                    Buffer this_$iv$iv$iv2 = current$iv$iv2;
                                    componentSize$iv2 = this_$iv$iv$iv2.getWritePosition() - this_$iv$iv$iv2.getReadPosition();
                                } catch (Throwable th) {
                                    th = th;
                                    if (release$iv$iv) {
                                        UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                Buffer this_$iv$iv$iv3 = current$iv$iv2;
                                int writePosition = this_$iv$iv$iv3.getWritePosition() - this_$iv$iv$iv3.getReadPosition();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } else {
                        componentSize$iv = componentSize$iv3;
                        componentSize$iv2 = before$iv$iv;
                    }
                    release$iv$iv = false;
                    if (componentSize$iv2 == 0) {
                        next$iv$iv = UnsafeKt.prepareReadNextHead($this$readAvailable, current$iv$iv2);
                    } else {
                        if (componentSize$iv2 >= size$iv$iv) {
                            Buffer this_$iv$iv$iv4 = current$iv$iv2;
                            if (this_$iv$iv$iv4.getCapacity() - this_$iv$iv$iv4.getLimit() >= 8) {
                                next$iv$iv = current$iv$iv2;
                            }
                        }
                        UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
                        next$iv$iv = UnsafeKt.prepareReadFirstHead($this$readAvailable, size$iv$iv);
                    }
                    if (next$iv$iv == null) {
                        break;
                    }
                    current$iv$iv2 = next$iv$iv;
                    release$iv$iv = true;
                    if (size$iv$iv <= 0) {
                        break;
                    }
                    componentSize$iv3 = componentSize$iv;
                } catch (Throwable th4) {
                    th = th4;
                }
            }
            if (release$iv$iv) {
                UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
            }
            remaining$iv = remaining$iv2;
        }
        return length - remaining$iv;
    }

    public static /* synthetic */ int readAvailable$default(Input input, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        return readAvailable(input, dArr, i, i2);
    }

    public static final int readAvailable(Input $this$readAvailable, double[] dst, int offset, int length) {
        int componentSize$iv;
        int componentSize$iv2;
        ChunkBuffer next$iv$iv;
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int componentSize$iv3 = 8;
        int remaining$iv = length;
        ChunkBuffer current$iv$iv = UnsafeKt.prepareReadFirstHead($this$readAvailable, 1);
        if (current$iv$iv != null) {
            int size$iv$iv = 1;
            ChunkBuffer current$iv$iv2 = current$iv$iv;
            boolean release$iv$iv = true;
            int dstOffset$iv = offset;
            int remaining$iv2 = remaining$iv;
            while (true) {
                try {
                    Buffer this_$iv$iv$iv = current$iv$iv2;
                    int before$iv$iv = this_$iv$iv$iv.getWritePosition() - this_$iv$iv$iv.getReadPosition();
                    if (before$iv$iv >= size$iv$iv) {
                        try {
                            Buffer buffer$iv = current$iv$iv2;
                            int count$iv = Math.min(remaining$iv2, (buffer$iv.getWritePosition() - buffer$iv.getReadPosition()) / componentSize$iv3);
                            int dstOffset = dstOffset$iv;
                            componentSize$iv = componentSize$iv3;
                            try {
                                BufferPrimitivesKt.readFully(buffer$iv, dst, dstOffset, count$iv);
                                remaining$iv2 -= count$iv;
                                dstOffset$iv += count$iv;
                                size$iv$iv = remaining$iv2 > 0 ? componentSize$iv : 0;
                                try {
                                    Buffer this_$iv$iv$iv2 = current$iv$iv2;
                                    componentSize$iv2 = this_$iv$iv$iv2.getWritePosition() - this_$iv$iv$iv2.getReadPosition();
                                } catch (Throwable th) {
                                    th = th;
                                    if (release$iv$iv) {
                                        UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                Buffer this_$iv$iv$iv3 = current$iv$iv2;
                                int writePosition = this_$iv$iv$iv3.getWritePosition() - this_$iv$iv$iv3.getReadPosition();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } else {
                        componentSize$iv = componentSize$iv3;
                        componentSize$iv2 = before$iv$iv;
                    }
                    release$iv$iv = false;
                    if (componentSize$iv2 == 0) {
                        next$iv$iv = UnsafeKt.prepareReadNextHead($this$readAvailable, current$iv$iv2);
                    } else {
                        if (componentSize$iv2 >= size$iv$iv) {
                            Buffer this_$iv$iv$iv4 = current$iv$iv2;
                            if (this_$iv$iv$iv4.getCapacity() - this_$iv$iv$iv4.getLimit() >= 8) {
                                next$iv$iv = current$iv$iv2;
                            }
                        }
                        UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
                        next$iv$iv = UnsafeKt.prepareReadFirstHead($this$readAvailable, size$iv$iv);
                    }
                    if (next$iv$iv == null) {
                        break;
                    }
                    current$iv$iv2 = next$iv$iv;
                    release$iv$iv = true;
                    if (size$iv$iv <= 0) {
                        break;
                    }
                    componentSize$iv3 = componentSize$iv;
                } catch (Throwable th4) {
                    th = th4;
                }
            }
            if (release$iv$iv) {
                UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
            }
            remaining$iv = remaining$iv2;
        }
        return length - remaining$iv;
    }

    public static /* synthetic */ int readAvailable$default(Input input, Buffer this_$iv, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = this_$iv.getLimit() - this_$iv.getWritePosition();
        }
        return readAvailable(input, this_$iv, i);
    }

    public static final int readAvailable(Input $this$readAvailable, Buffer dst, int length) {
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        int remaining$iv = length;
        ChunkBuffer current$iv$iv = UnsafeKt.prepareReadFirstHead($this$readAvailable, 1);
        if (current$iv$iv != null) {
            ChunkBuffer current$iv$iv2 = current$iv$iv;
            boolean release$iv$iv = true;
            int dstOffset$iv = 0;
            int remaining$iv2 = remaining$iv;
            while (true) {
                try {
                    Buffer buffer$iv = current$iv$iv2;
                    int count$iv = Math.min(remaining$iv2, buffer$iv.getWritePosition() - buffer$iv.getReadPosition());
                    BufferPrimitivesKt.readFully(buffer$iv, dst, count$iv);
                    remaining$iv2 -= count$iv;
                    dstOffset$iv += count$iv;
                    if (!(remaining$iv2 > 0)) {
                        break;
                    }
                    release$iv$iv = false;
                    ChunkBuffer next$iv$iv = UnsafeKt.prepareReadNextHead($this$readAvailable, current$iv$iv2);
                    if (next$iv$iv == null) {
                        break;
                    }
                    current$iv$iv2 = next$iv$iv;
                    release$iv$iv = true;
                } finally {
                    if (release$iv$iv) {
                        UnsafeKt.completeReadHead($this$readAvailable, current$iv$iv2);
                    }
                }
            }
            remaining$iv = remaining$iv2;
        }
        return length - remaining$iv;
    }

    /* renamed from: readAvailable-UAd2zVI */
    public static final int m421readAvailableUAd2zVI(Input readAvailable, ByteBuffer destination, int destinationOffset, int length) {
        Intrinsics.checkNotNullParameter(readAvailable, "$this$readAvailable");
        Intrinsics.checkNotNullParameter(destination, "destination");
        return (int) m422readAvailableUAd2zVI(readAvailable, destination, destinationOffset, length);
    }

    /* renamed from: readAvailable-UAd2zVI */
    public static final long m422readAvailableUAd2zVI(Input readAvailable, ByteBuffer destination, long destinationOffset, long length) {
        ChunkBuffer current$iv$iv;
        long j;
        Intrinsics.checkNotNullParameter(readAvailable, "$this$readAvailable");
        ByteBuffer destination2 = destination;
        Intrinsics.checkNotNullParameter(destination2, "destination");
        long remaining = length;
        ChunkBuffer current$iv$iv2 = UnsafeKt.prepareReadFirstHead(readAvailable, 1);
        if (current$iv$iv2 == null) {
            j = 0;
        } else {
            boolean release$iv$iv = true;
            long dstOffset$iv = destinationOffset;
            while (true) {
                try {
                    Buffer buffer$iv = current$iv$iv2;
                    int count$iv = (int) Math.min(remaining, buffer$iv.getWritePosition() - buffer$iv.getReadPosition());
                    ByteBuffer src = buffer$iv.getMemory();
                    long srcOffset = buffer$iv.getReadPosition();
                    long remaining$iv = remaining;
                    long srcOffset2 = dstOffset$iv;
                    current$iv$iv = current$iv$iv2;
                    j = 0;
                    try {
                        Memory.m237copyToJT6ljtQ(src, destination2, srcOffset, count$iv, srcOffset2);
                        buffer$iv.discardExact(count$iv);
                        remaining = remaining$iv - count$iv;
                        dstOffset$iv += count$iv;
                        if (!(remaining > 0)) {
                            break;
                        }
                        release$iv$iv = false;
                        try {
                            ChunkBuffer next$iv$iv = UnsafeKt.prepareReadNextHead(readAvailable, current$iv$iv);
                            if (next$iv$iv == null) {
                                break;
                            }
                            current$iv$iv2 = next$iv$iv;
                            release$iv$iv = true;
                            destination2 = destination;
                        } catch (Throwable th) {
                            th = th;
                            if (release$iv$iv) {
                                UnsafeKt.completeReadHead(readAvailable, current$iv$iv);
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    current$iv$iv = current$iv$iv2;
                }
            }
            if (release$iv$iv) {
                UnsafeKt.completeReadHead(readAvailable, current$iv$iv);
            }
        }
        long result = length - remaining;
        if (result == j && readAvailable.getEndOfInput()) {
            return -1L;
        }
        return result;
    }

    private static final int readFullyBytesTemplate(Input $this$readFullyBytesTemplate, int initialDstOffset, int length, Function3<? super Buffer, ? super Integer, ? super Integer, Unit> function3) {
        ChunkBuffer current$iv = UnsafeKt.prepareReadFirstHead($this$readFullyBytesTemplate, 1);
        if (current$iv == null) {
            return length;
        }
        ChunkBuffer current$iv2 = current$iv;
        boolean release$iv = true;
        int dstOffset = initialDstOffset;
        int remaining = length;
        while (true) {
            try {
                Buffer buffer = current$iv2;
                int count = Math.min(remaining, buffer.getWritePosition() - buffer.getReadPosition());
                try {
                    function3.invoke(buffer, Integer.valueOf(dstOffset), Integer.valueOf(count));
                    remaining -= count;
                    dstOffset += count;
                    if (!(remaining > 0)) {
                        break;
                    }
                    release$iv = false;
                    ChunkBuffer next$iv = UnsafeKt.prepareReadNextHead($this$readFullyBytesTemplate, current$iv2);
                    if (next$iv == null) {
                        break;
                    }
                    current$iv2 = next$iv;
                    release$iv = true;
                } catch (Throwable th) {
                    th = th;
                    if (release$iv) {
                        UnsafeKt.completeReadHead($this$readFullyBytesTemplate, current$iv2);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        if (release$iv) {
            UnsafeKt.completeReadHead($this$readFullyBytesTemplate, current$iv2);
        }
        return remaining;
    }

    private static final long readFullyBytesTemplate(Input $this$readFullyBytesTemplate, long initialDstOffset, long length, Function4<? super Memory, ? super Long, ? super Long, ? super Integer, Unit> function4) {
        int $i$f$readFullyBytesTemplate = 0;
        ChunkBuffer current$iv = UnsafeKt.prepareReadFirstHead($this$readFullyBytesTemplate, 1);
        if (current$iv == null) {
            return length;
        }
        ChunkBuffer current$iv2 = current$iv;
        long dstOffset = initialDstOffset;
        long remaining = length;
        boolean release$iv = true;
        while (true) {
            try {
                Buffer buffer = current$iv2;
                int count = (int) Math.min(remaining, buffer.getWritePosition() - buffer.getReadPosition());
                int $i$f$readFullyBytesTemplate2 = $i$f$readFullyBytesTemplate;
                try {
                    function4.invoke(Memory.m234boximpl(buffer.getMemory()), Long.valueOf(buffer.getReadPosition()), Long.valueOf(dstOffset), Integer.valueOf(count));
                    buffer.discardExact(count);
                    remaining -= count;
                    dstOffset += count;
                    if (!(remaining > 0)) {
                        break;
                    }
                    release$iv = false;
                    ChunkBuffer next$iv = UnsafeKt.prepareReadNextHead($this$readFullyBytesTemplate, current$iv2);
                    if (next$iv == null) {
                        break;
                    }
                    current$iv2 = next$iv;
                    release$iv = true;
                    $i$f$readFullyBytesTemplate = $i$f$readFullyBytesTemplate2;
                } catch (Throwable th) {
                    th = th;
                    if (release$iv) {
                        UnsafeKt.completeReadHead($this$readFullyBytesTemplate, current$iv2);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        if (release$iv) {
            UnsafeKt.completeReadHead($this$readFullyBytesTemplate, current$iv2);
        }
        return remaining;
    }

    private static final int readFullyTemplate(Input $this$readFullyTemplate, int offset, int length, int componentSize, Function3<? super Buffer, ? super Integer, ? super Integer, Unit> function3) {
        Buffer buffer;
        int count;
        int $i$f$readFullyTemplate;
        int after$iv;
        ChunkBuffer next$iv;
        int $i$f$readFullyTemplate2 = 0;
        ChunkBuffer current$iv = UnsafeKt.prepareReadFirstHead($this$readFullyTemplate, 1);
        if (current$iv == null) {
            return length;
        }
        int size$iv = 1;
        ChunkBuffer current$iv2 = current$iv;
        boolean release$iv = true;
        int dstOffset = offset;
        int remaining = length;
        while (true) {
            try {
                Buffer this_$iv$iv = current$iv2;
                int before$iv = this_$iv$iv.getWritePosition() - this_$iv$iv.getReadPosition();
                if (before$iv >= size$iv) {
                    try {
                        buffer = current$iv2;
                        count = Math.min(remaining, (buffer.getWritePosition() - buffer.getReadPosition()) / componentSize);
                        $i$f$readFullyTemplate = $i$f$readFullyTemplate2;
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        function3.invoke(buffer, Integer.valueOf(dstOffset), Integer.valueOf(count));
                        remaining -= count;
                        dstOffset += count;
                        size$iv = remaining > 0 ? componentSize : 0;
                        try {
                            Buffer this_$iv$iv2 = current$iv2;
                            after$iv = this_$iv$iv2.getWritePosition() - this_$iv$iv2.getReadPosition();
                        } catch (Throwable th2) {
                            th = th2;
                            if (release$iv) {
                                UnsafeKt.completeReadHead($this$readFullyTemplate, current$iv2);
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        Buffer this_$iv$iv3 = current$iv2;
                        int writePosition = this_$iv$iv3.getWritePosition() - this_$iv$iv3.getReadPosition();
                        throw th;
                    }
                } else {
                    $i$f$readFullyTemplate = $i$f$readFullyTemplate2;
                    after$iv = before$iv;
                }
                release$iv = false;
                if (after$iv == 0) {
                    next$iv = UnsafeKt.prepareReadNextHead($this$readFullyTemplate, current$iv2);
                } else {
                    if (after$iv >= size$iv) {
                        Buffer this_$iv$iv4 = current$iv2;
                        if (this_$iv$iv4.getCapacity() - this_$iv$iv4.getLimit() >= 8) {
                            next$iv = current$iv2;
                        }
                    }
                    UnsafeKt.completeReadHead($this$readFullyTemplate, current$iv2);
                    next$iv = UnsafeKt.prepareReadFirstHead($this$readFullyTemplate, size$iv);
                }
                if (next$iv == null) {
                    break;
                }
                current$iv2 = next$iv;
                release$iv = true;
                if (size$iv <= 0) {
                    break;
                }
                $i$f$readFullyTemplate2 = $i$f$readFullyTemplate;
            } catch (Throwable th4) {
                th = th4;
            }
        }
        if (release$iv) {
            UnsafeKt.completeReadHead($this$readFullyTemplate, current$iv2);
        }
        return remaining;
    }

    private static final void requireNoRemaining(int $this$requireNoRemaining) {
        if ($this$requireNoRemaining > 0) {
            StringsKt.prematureEndOfStream($this$requireNoRemaining);
            throw new KotlinNothingValueException();
        }
    }
}
