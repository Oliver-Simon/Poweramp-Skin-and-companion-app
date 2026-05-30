package io.ktor.utils.io.core;

import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InputArraysJvm.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001\u001a\u001c\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001¨\u0006\b"}, d2 = {"readAvailable", "", "Lio/ktor/utils/io/core/Input;", "dst", "Ljava/nio/ByteBuffer;", "length", "readFully", "", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class InputArraysJvmKt {
    public static /* synthetic */ void readFully$default(Input input, ByteBuffer byteBuffer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = byteBuffer.remaining();
        }
        readFully(input, byteBuffer, i);
    }

    public static final void readFully(Input $this$readFully, ByteBuffer dst, int length) {
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        if (readAvailable($this$readFully, dst, length) < length) {
            StringsKt.prematureEndOfStream(length);
            throw new KotlinNothingValueException();
        }
    }

    public static /* synthetic */ int readAvailable$default(Input input, ByteBuffer byteBuffer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = byteBuffer.remaining();
        }
        return readAvailable(input, byteBuffer, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006b A[EDGE_INSN: B:29:0x006b->B:20:0x006b BREAK  A[LOOP:0: B:7:0x0020->B:18:0x0073], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int readAvailable(io.ktor.utils.io.core.Input r16, java.nio.ByteBuffer r17, int r18) {
        /*
            r1 = r17
            java.lang.String r0 = "<this>"
            r2 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            r0 = 0
            r3 = r16
            r4 = 0
            r5 = 1
            r6 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r7 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r3, r6)
            if (r7 != 0) goto L1d
            r12 = r18
            goto L72
        L1d:
            r8 = r7
            r7 = r5
            r5 = r0
        L20:
            r0 = r8
            io.ktor.utils.io.core.Buffer r0 = (io.ktor.utils.io.core.Buffer) r0     // Catch: java.lang.Throwable -> L78
            r9 = 0
            int r10 = r1.limit()     // Catch: java.lang.Throwable -> L78
            int r11 = r1.position()     // Catch: java.lang.Throwable -> L78
            r12 = r0
            r13 = 0
            int r14 = r12.getWritePosition()     // Catch: java.lang.Throwable -> L78
            int r15 = r12.getReadPosition()     // Catch: java.lang.Throwable -> L78
            int r14 = r14 - r15
            int r11 = r11 + r14
            int r11 = java.lang.Math.min(r10, r11)     // Catch: java.lang.Throwable -> L78
            r1.limit(r11)     // Catch: java.lang.Throwable -> L78
            int r11 = r1.remaining()     // Catch: java.lang.Throwable -> L78
            java.nio.ByteBuffer r12 = r0.getMemory()     // Catch: java.lang.Throwable -> L78
            int r13 = r0.getReadPosition()     // Catch: java.lang.Throwable -> L78
            io.ktor.utils.io.bits.MemoryJvmKt.m252copyTo62zg_DM(r12, r1, r13)     // Catch: java.lang.Throwable -> L78
            r1.limit(r10)     // Catch: java.lang.Throwable -> L78
            int r5 = r5 + r11
            boolean r12 = r1.hasRemaining()     // Catch: java.lang.Throwable -> L78
            if (r12 == 0) goto L5e
            r12 = r18
            if (r5 >= r12) goto L60
            r13 = r6
            goto L61
        L5e:
            r12 = r18
        L60:
            r13 = 0
        L61:
            if (r13 != 0) goto L64
            goto L6b
        L64:
            r7 = 0
            io.ktor.utils.io.core.internal.ChunkBuffer r0 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r3, r8)     // Catch: java.lang.Throwable -> L76
            if (r0 != 0) goto L73
        L6b:
            if (r7 == 0) goto L70
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r3, r8)
        L70:
            r0 = r5
        L72:
            return r0
        L73:
            r8 = r0
            r7 = 1
            goto L20
        L76:
            r0 = move-exception
            goto L7b
        L78:
            r0 = move-exception
            r12 = r18
        L7b:
            if (r7 == 0) goto L80
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r3, r8)
        L80:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysJvmKt.readAvailable(io.ktor.utils.io.core.Input, java.nio.ByteBuffer, int):int");
    }
}
