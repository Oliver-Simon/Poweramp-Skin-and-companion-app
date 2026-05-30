package io.ktor.utils.io;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.utils.io.core.ByteOrder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChannelLittleEndian.kt */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0014\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u0002H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\b\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a\u0015\u0010\n\u001a\u00020\t*\u00020\u0002H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\u000b\u001a\u00020\f*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a\u0015\u0010\r\u001a\u00020\f*\u00020\u0002H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a\u0015\u0010\u0010\u001a\u00020\u000f*\u00020\u0002H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\u0011\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a\u0015\u0010\u0013\u001a\u00020\u0012*\u00020\u0002H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a<\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u0015*\u0002H\u00152\u0006\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u00150\u0017¢\u0006\u0002\b\u0018H\u0081\bø\u0001\u0001¢\u0006\u0002\u0010\u0019\u001a<\u0010\u001a\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u0015*\u00020\u00022\u0006\u0010\u001b\u001a\u0002H\u00152\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u00150\u0017¢\u0006\u0002\b\u0018H\u0081\bø\u0001\u0001¢\u0006\u0002\u0010\u001c\u001a9\u0010\u001a\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u0015*\u00020\u001d2\u0006\u0010\u001b\u001a\u0002H\u00152\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u00150\u0017¢\u0006\u0002\b\u0018H\u0082\b¢\u0006\u0002\u0010\u001e\u001a%\u0010\u001f\u001a\u00020 *\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!\u001a\u001d\u0010\"\u001a\u00020 *\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010#\u001a%\u0010$\u001a\u00020 *\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010%\u001a\u001d\u0010&\u001a\u00020 *\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010'\u001a%\u0010(\u001a\u00020 *\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010)\u001a\u001d\u0010*\u001a\u00020 *\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010+\u001a%\u0010,\u001a\u00020 *\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010-\u001a\u001d\u0010.\u001a\u00020 *\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u000fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010/\u001a%\u00100\u001a\u00020 *\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u00101\u001a\u001d\u00102\u001a\u00020 *\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0002\u00103\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u00064"}, d2 = {"readDouble", "", "Lio/ktor/utils/io/ByteReadChannel;", "byteOrder", "Lio/ktor/utils/io/core/ByteOrder;", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/core/ByteOrder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readDoubleLittleEndian", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readFloat", "", "readFloatLittleEndian", "readInt", "", "readIntLittleEndian", "readLong", "", "readLongLittleEndian", "readShort", "", "readShortLittleEndian", "reverseIfNeeded", ExifInterface.GPS_DIRECTION_TRUE, "reverseBlock", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lio/ktor/utils/io/core/ByteOrder;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "toLittleEndian", "value", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteWriteChannel;", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeDouble", "", "(Lio/ktor/utils/io/ByteWriteChannel;DLio/ktor/utils/io/core/ByteOrder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeDoubleLittleEndian", "(Lio/ktor/utils/io/ByteWriteChannel;DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFloat", "(Lio/ktor/utils/io/ByteWriteChannel;FLio/ktor/utils/io/core/ByteOrder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFloatLittleEndian", "(Lio/ktor/utils/io/ByteWriteChannel;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeInt", "(Lio/ktor/utils/io/ByteWriteChannel;ILio/ktor/utils/io/core/ByteOrder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeIntLittleEndian", "(Lio/ktor/utils/io/ByteWriteChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeLong", "(Lio/ktor/utils/io/ByteWriteChannel;JLio/ktor/utils/io/core/ByteOrder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeLongLittleEndian", "(Lio/ktor/utils/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeShort", "(Lio/ktor/utils/io/ByteWriteChannel;SLio/ktor/utils/io/core/ByteOrder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeShortLittleEndian", "(Lio/ktor/utils/io/ByteWriteChannel;SLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ChannelLittleEndianKt {

    /* compiled from: ChannelLittleEndian.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    /* loaded from: classes9.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ByteOrder.values().length];
            try {
                iArr[ByteOrder.BIG_ENDIAN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readShort(io.ktor.utils.io.ByteReadChannel r6, io.ktor.utils.io.core.ByteOrder r7, kotlin.coroutines.Continuation<? super java.lang.Short> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.utils.io.ChannelLittleEndianKt$readShort$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ChannelLittleEndianKt$readShort$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt$readShort$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ChannelLittleEndianKt$readShort$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readShort$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2d:
            r6 = 0
            java.lang.Object r7 = r0.L$0
            io.ktor.utils.io.core.ByteOrder r7 = (io.ktor.utils.io.core.ByteOrder) r7
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
            r6 = r8
            goto L47
        L38:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = 0
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r6 = r6.readShort(r0)
            if (r6 != r1) goto L47
            return r1
        L47:
            r1 = 0
            int[] r4 = io.ktor.utils.io.ChannelLittleEndianKt.WhenMappings.$EnumSwitchMapping$0
            int r5 = r7.ordinal()
            r7 = r4[r5]
            if (r7 != r3) goto L54
            goto L68
        L54:
            r7 = r6
            java.lang.Number r7 = (java.lang.Number) r7
            short r6 = r7.shortValue()
            r7 = 0
            r3 = r6
            r4 = 0
            short r5 = (short) r3
            short r3 = java.lang.Short.reverseBytes(r5)
            java.lang.Short r6 = kotlin.coroutines.jvm.internal.Boxing.boxShort(r3)
        L68:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readShort(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.core.ByteOrder, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object readShort$$forInline(ByteReadChannel $this$readShort, ByteOrder byteOrder, Continuation<? super Short> continuation) {
        Object $this$reverseIfNeeded$iv = $this$readShort.readShort(continuation);
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            return $this$reverseIfNeeded$iv;
        }
        short $this$readShort_u24lambda_u240 = ((Number) $this$reverseIfNeeded$iv).shortValue();
        return Short.valueOf(Short.reverseBytes($this$readShort_u24lambda_u240));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readInt(io.ktor.utils.io.ByteReadChannel r6, io.ktor.utils.io.core.ByteOrder r7, kotlin.coroutines.Continuation<? super java.lang.Integer> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.utils.io.ChannelLittleEndianKt$readInt$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ChannelLittleEndianKt$readInt$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt$readInt$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ChannelLittleEndianKt$readInt$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readInt$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2d:
            r6 = 0
            java.lang.Object r7 = r0.L$0
            io.ktor.utils.io.core.ByteOrder r7 = (io.ktor.utils.io.core.ByteOrder) r7
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
            r6 = r8
            goto L47
        L38:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = 0
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r6 = r6.readInt(r0)
            if (r6 != r1) goto L47
            return r1
        L47:
            r1 = 0
            int[] r4 = io.ktor.utils.io.ChannelLittleEndianKt.WhenMappings.$EnumSwitchMapping$0
            int r5 = r7.ordinal()
            r7 = r4[r5]
            if (r7 != r3) goto L54
            goto L67
        L54:
            r7 = r6
            java.lang.Number r7 = (java.lang.Number) r7
            int r6 = r7.intValue()
            r7 = 0
            r3 = r6
            r4 = 0
            int r3 = java.lang.Integer.reverseBytes(r3)
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
        L67:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readInt(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.core.ByteOrder, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object readInt$$forInline(ByteReadChannel $this$readInt, ByteOrder byteOrder, Continuation<? super Integer> continuation) {
        Object $this$reverseIfNeeded$iv = $this$readInt.readInt(continuation);
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            return $this$reverseIfNeeded$iv;
        }
        int $this$readInt_u24lambda_u241 = ((Number) $this$reverseIfNeeded$iv).intValue();
        return Integer.valueOf(Integer.reverseBytes($this$readInt_u24lambda_u241));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readLong(io.ktor.utils.io.ByteReadChannel r7, io.ktor.utils.io.core.ByteOrder r8, kotlin.coroutines.Continuation<? super java.lang.Long> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.utils.io.ChannelLittleEndianKt$readLong$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.ChannelLittleEndianKt$readLong$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt$readLong$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.ChannelLittleEndianKt$readLong$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readLong$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2d:
            r7 = 0
            java.lang.Object r8 = r0.L$0
            io.ktor.utils.io.core.ByteOrder r8 = (io.ktor.utils.io.core.ByteOrder) r8
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r7
            r7 = r9
            goto L47
        L38:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = 0
            r0.L$0 = r8
            r0.label = r3
            java.lang.Object r7 = r7.readLong(r0)
            if (r7 != r1) goto L47
            return r1
        L47:
            r1 = 0
            int[] r4 = io.ktor.utils.io.ChannelLittleEndianKt.WhenMappings.$EnumSwitchMapping$0
            int r5 = r8.ordinal()
            r8 = r4[r5]
            if (r8 != r3) goto L54
            goto L67
        L54:
            r8 = r7
            java.lang.Number r8 = (java.lang.Number) r8
            long r7 = r8.longValue()
            r3 = 0
            r4 = r7
            r6 = 0
            long r4 = java.lang.Long.reverseBytes(r4)
            java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r4)
        L67:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readLong(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.core.ByteOrder, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object readLong$$forInline(ByteReadChannel $this$readLong, ByteOrder byteOrder, Continuation<? super Long> continuation) {
        Object $this$reverseIfNeeded$iv = $this$readLong.readLong(continuation);
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            return $this$reverseIfNeeded$iv;
        }
        long $this$readLong_u24lambda_u242 = ((Number) $this$reverseIfNeeded$iv).longValue();
        return Long.valueOf(Long.reverseBytes($this$readLong_u24lambda_u242));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readFloat(io.ktor.utils.io.ByteReadChannel r6, io.ktor.utils.io.core.ByteOrder r7, kotlin.coroutines.Continuation<? super java.lang.Float> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.utils.io.ChannelLittleEndianKt$readFloat$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ChannelLittleEndianKt$readFloat$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt$readFloat$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ChannelLittleEndianKt$readFloat$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readFloat$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2d:
            r6 = 0
            java.lang.Object r7 = r0.L$0
            io.ktor.utils.io.core.ByteOrder r7 = (io.ktor.utils.io.core.ByteOrder) r7
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
            r6 = r8
            goto L47
        L38:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = 0
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r6 = r6.readFloat(r0)
            if (r6 != r1) goto L47
            return r1
        L47:
            r1 = 0
            int[] r4 = io.ktor.utils.io.ChannelLittleEndianKt.WhenMappings.$EnumSwitchMapping$0
            int r5 = r7.ordinal()
            r7 = r4[r5]
            if (r7 != r3) goto L54
            goto L70
        L54:
            r7 = r6
            java.lang.Number r7 = (java.lang.Number) r7
            float r6 = r7.floatValue()
            r7 = 0
            r3 = r6
            r4 = 0
            int r5 = java.lang.Float.floatToRawIntBits(r3)
            int r5 = java.lang.Integer.reverseBytes(r5)
            float r5 = java.lang.Float.intBitsToFloat(r5)
            java.lang.Float r6 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r5)
        L70:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readFloat(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.core.ByteOrder, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object readFloat$$forInline(ByteReadChannel $this$readFloat, ByteOrder byteOrder, Continuation<? super Float> continuation) {
        Object $this$reverseIfNeeded$iv = $this$readFloat.readFloat(continuation);
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            return $this$reverseIfNeeded$iv;
        }
        float $this$readFloat_u24lambda_u243 = ((Number) $this$reverseIfNeeded$iv).floatValue();
        return Float.valueOf(Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits($this$readFloat_u24lambda_u243))));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readDouble(io.ktor.utils.io.ByteReadChannel r9, io.ktor.utils.io.core.ByteOrder r10, kotlin.coroutines.Continuation<? super java.lang.Double> r11) {
        /*
            boolean r0 = r11 instanceof io.ktor.utils.io.ChannelLittleEndianKt$readDouble$1
            if (r0 == 0) goto L14
            r0 = r11
            io.ktor.utils.io.ChannelLittleEndianKt$readDouble$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt$readDouble$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            io.ktor.utils.io.ChannelLittleEndianKt$readDouble$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readDouble$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2d:
            r9 = 0
            java.lang.Object r10 = r0.L$0
            io.ktor.utils.io.core.ByteOrder r10 = (io.ktor.utils.io.core.ByteOrder) r10
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = r9
            r9 = r11
            goto L47
        L38:
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = 0
            r0.L$0 = r10
            r0.label = r3
            java.lang.Object r9 = r9.readDouble(r0)
            if (r9 != r1) goto L47
            return r1
        L47:
            r1 = 0
            int[] r4 = io.ktor.utils.io.ChannelLittleEndianKt.WhenMappings.$EnumSwitchMapping$0
            int r5 = r10.ordinal()
            r10 = r4[r5]
            if (r10 != r3) goto L54
            goto L70
        L54:
            r10 = r9
            java.lang.Number r10 = (java.lang.Number) r10
            double r9 = r10.doubleValue()
            r3 = 0
            r4 = r9
            r6 = 0
            long r7 = java.lang.Double.doubleToRawLongBits(r4)
            long r7 = java.lang.Long.reverseBytes(r7)
            double r7 = java.lang.Double.longBitsToDouble(r7)
            java.lang.Double r9 = kotlin.coroutines.jvm.internal.Boxing.boxDouble(r7)
        L70:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readDouble(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.core.ByteOrder, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object readDouble$$forInline(ByteReadChannel $this$readDouble, ByteOrder byteOrder, Continuation<? super Double> continuation) {
        Object $this$reverseIfNeeded$iv = $this$readDouble.readDouble(continuation);
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            return $this$reverseIfNeeded$iv;
        }
        double $this$readDouble_u24lambda_u244 = ((Number) $this$reverseIfNeeded$iv).doubleValue();
        return Double.valueOf(Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits($this$readDouble_u24lambda_u244))));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readShortLittleEndian(io.ktor.utils.io.ByteReadChannel r8, kotlin.coroutines.Continuation<? super java.lang.Short> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.utils.io.ChannelLittleEndianKt$readShortLittleEndian$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.ChannelLittleEndianKt$readShortLittleEndian$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt$readShortLittleEndian$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.ChannelLittleEndianKt$readShortLittleEndian$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readShortLittleEndian$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L33;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2c:
            r8 = 0
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r8
            r8 = r9
            goto L41
        L33:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = 0
            r3 = 1
            r0.label = r3
            java.lang.Object r8 = r8.readShort(r0)
            if (r8 != r1) goto L41
            return r1
        L41:
            r1 = 0
            r3 = r8
            java.lang.Number r3 = (java.lang.Number) r3
            short r3 = r3.shortValue()
            r4 = 0
            r5 = r3
            r6 = 0
            short r7 = (short) r5
            short r5 = java.lang.Short.reverseBytes(r7)
            java.lang.Short r3 = kotlin.coroutines.jvm.internal.Boxing.boxShort(r5)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readShortLittleEndian(io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object readShortLittleEndian$$forInline(ByteReadChannel $this$readShortLittleEndian, Continuation<? super Short> continuation) {
        Object value$iv = $this$readShortLittleEndian.readShort(continuation);
        short $this$readShortLittleEndian_u24lambda_u245 = ((Number) value$iv).shortValue();
        return Short.valueOf(Short.reverseBytes($this$readShortLittleEndian_u24lambda_u245));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readIntLittleEndian(io.ktor.utils.io.ByteReadChannel r7, kotlin.coroutines.Continuation<? super java.lang.Integer> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.utils.io.ChannelLittleEndianKt$readIntLittleEndian$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ChannelLittleEndianKt$readIntLittleEndian$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt$readIntLittleEndian$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ChannelLittleEndianKt$readIntLittleEndian$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readIntLittleEndian$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L33;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            r7 = 0
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r7
            r7 = r8
            goto L41
        L33:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = 0
            r3 = 1
            r0.label = r3
            java.lang.Object r7 = r7.readInt(r0)
            if (r7 != r1) goto L41
            return r1
        L41:
            r1 = 0
            r3 = r7
            java.lang.Number r3 = (java.lang.Number) r3
            int r3 = r3.intValue()
            r4 = 0
            r5 = r3
            r6 = 0
            int r5 = java.lang.Integer.reverseBytes(r5)
            java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readIntLittleEndian(io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object readIntLittleEndian$$forInline(ByteReadChannel $this$readIntLittleEndian, Continuation<? super Integer> continuation) {
        Object value$iv = $this$readIntLittleEndian.readInt(continuation);
        int $this$readIntLittleEndian_u24lambda_u246 = ((Number) value$iv).intValue();
        return Integer.valueOf(Integer.reverseBytes($this$readIntLittleEndian_u24lambda_u246));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readLongLittleEndian(io.ktor.utils.io.ByteReadChannel r9, kotlin.coroutines.Continuation<? super java.lang.Long> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.utils.io.ChannelLittleEndianKt$readLongLittleEndian$1
            if (r0 == 0) goto L14
            r0 = r10
            io.ktor.utils.io.ChannelLittleEndianKt$readLongLittleEndian$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt$readLongLittleEndian$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            io.ktor.utils.io.ChannelLittleEndianKt$readLongLittleEndian$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readLongLittleEndian$1
            r0.<init>(r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L33;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2c:
            r9 = 0
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r9
            r9 = r10
            goto L41
        L33:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = 0
            r3 = 1
            r0.label = r3
            java.lang.Object r9 = r9.readLong(r0)
            if (r9 != r1) goto L41
            return r1
        L41:
            r1 = 0
            r3 = r9
            java.lang.Number r3 = (java.lang.Number) r3
            long r3 = r3.longValue()
            r5 = 0
            r6 = r3
            r8 = 0
            long r6 = java.lang.Long.reverseBytes(r6)
            java.lang.Long r3 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r6)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readLongLittleEndian(io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object readLongLittleEndian$$forInline(ByteReadChannel $this$readLongLittleEndian, Continuation<? super Long> continuation) {
        Object value$iv = $this$readLongLittleEndian.readLong(continuation);
        long $this$readLongLittleEndian_u24lambda_u247 = ((Number) value$iv).longValue();
        return Long.valueOf(Long.reverseBytes($this$readLongLittleEndian_u24lambda_u247));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readFloatLittleEndian(io.ktor.utils.io.ByteReadChannel r8, kotlin.coroutines.Continuation<? super java.lang.Float> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.utils.io.ChannelLittleEndianKt$readFloatLittleEndian$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.ChannelLittleEndianKt$readFloatLittleEndian$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt$readFloatLittleEndian$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.ChannelLittleEndianKt$readFloatLittleEndian$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readFloatLittleEndian$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L33;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2c:
            r8 = 0
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r8
            r8 = r9
            goto L41
        L33:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = 0
            r3 = 1
            r0.label = r3
            java.lang.Object r8 = r8.readFloat(r0)
            if (r8 != r1) goto L41
            return r1
        L41:
            r1 = 0
            r3 = r8
            java.lang.Number r3 = (java.lang.Number) r3
            float r3 = r3.floatValue()
            r4 = 0
            r5 = r3
            r6 = 0
            int r7 = java.lang.Float.floatToRawIntBits(r5)
            int r7 = java.lang.Integer.reverseBytes(r7)
            float r7 = java.lang.Float.intBitsToFloat(r7)
            java.lang.Float r3 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r7)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readFloatLittleEndian(io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object readFloatLittleEndian$$forInline(ByteReadChannel $this$readFloatLittleEndian, Continuation<? super Float> continuation) {
        Object value$iv = $this$readFloatLittleEndian.readFloat(continuation);
        float $this$readFloatLittleEndian_u24lambda_u248 = ((Number) value$iv).floatValue();
        return Float.valueOf(Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits($this$readFloatLittleEndian_u24lambda_u248))));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readDoubleLittleEndian(io.ktor.utils.io.ByteReadChannel r11, kotlin.coroutines.Continuation<? super java.lang.Double> r12) {
        /*
            boolean r0 = r12 instanceof io.ktor.utils.io.ChannelLittleEndianKt$readDoubleLittleEndian$1
            if (r0 == 0) goto L14
            r0 = r12
            io.ktor.utils.io.ChannelLittleEndianKt$readDoubleLittleEndian$1 r0 = (io.ktor.utils.io.ChannelLittleEndianKt$readDoubleLittleEndian$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            io.ktor.utils.io.ChannelLittleEndianKt$readDoubleLittleEndian$1 r0 = new io.ktor.utils.io.ChannelLittleEndianKt$readDoubleLittleEndian$1
            r0.<init>(r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L33;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L2c:
            r11 = 0
            kotlin.ResultKt.throwOnFailure(r12)
            r2 = r11
            r11 = r12
            goto L41
        L33:
            kotlin.ResultKt.throwOnFailure(r12)
            r2 = 0
            r3 = 1
            r0.label = r3
            java.lang.Object r11 = r11.readDouble(r0)
            if (r11 != r1) goto L41
            return r1
        L41:
            r1 = 0
            r3 = r11
            java.lang.Number r3 = (java.lang.Number) r3
            double r3 = r3.doubleValue()
            r5 = 0
            r6 = r3
            r8 = 0
            long r9 = java.lang.Double.doubleToRawLongBits(r6)
            long r9 = java.lang.Long.reverseBytes(r9)
            double r9 = java.lang.Double.longBitsToDouble(r9)
            java.lang.Double r3 = kotlin.coroutines.jvm.internal.Boxing.boxDouble(r9)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ChannelLittleEndianKt.readDoubleLittleEndian(io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object readDoubleLittleEndian$$forInline(ByteReadChannel $this$readDoubleLittleEndian, Continuation<? super Double> continuation) {
        Object value$iv = $this$readDoubleLittleEndian.readDouble(continuation);
        double $this$readDoubleLittleEndian_u24lambda_u249 = ((Number) value$iv).doubleValue();
        return Double.valueOf(Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits($this$readDoubleLittleEndian_u24lambda_u249))));
    }

    public static final Object writeShort(ByteWriteChannel $this$writeShort, short value, ByteOrder byteOrder, Continuation<? super Unit> continuation) {
        short $this$reverseByteOrder$iv;
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            $this$reverseByteOrder$iv = value;
        } else {
            $this$reverseByteOrder$iv = Short.reverseBytes(value);
        }
        Object writeShort = $this$writeShort.writeShort($this$reverseByteOrder$iv, continuation);
        return writeShort == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeShort : Unit.INSTANCE;
    }

    public static final Object writeInt(ByteWriteChannel $this$writeInt, int value, ByteOrder byteOrder, Continuation<? super Unit> continuation) {
        int $this$reverseByteOrder$iv;
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            $this$reverseByteOrder$iv = value;
        } else {
            $this$reverseByteOrder$iv = Integer.reverseBytes(value);
        }
        Object writeInt = $this$writeInt.writeInt($this$reverseByteOrder$iv, continuation);
        return writeInt == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeInt : Unit.INSTANCE;
    }

    public static final Object writeLong(ByteWriteChannel $this$writeLong, long value, ByteOrder byteOrder, Continuation<? super Unit> continuation) {
        long $this$reverseByteOrder$iv;
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            $this$reverseByteOrder$iv = value;
        } else {
            $this$reverseByteOrder$iv = Long.reverseBytes(value);
        }
        Object writeLong = $this$writeLong.writeLong($this$reverseByteOrder$iv, continuation);
        return writeLong == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeLong : Unit.INSTANCE;
    }

    public static final Object writeFloat(ByteWriteChannel $this$writeFloat, float value, ByteOrder byteOrder, Continuation<? super Unit> continuation) {
        float intBitsToFloat;
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            intBitsToFloat = value;
        } else {
            intBitsToFloat = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(value)));
        }
        Object writeFloat = $this$writeFloat.writeFloat(intBitsToFloat, continuation);
        return writeFloat == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeFloat : Unit.INSTANCE;
    }

    public static final Object writeDouble(ByteWriteChannel $this$writeDouble, double value, ByteOrder byteOrder, Continuation<? super Unit> continuation) {
        double longBitsToDouble;
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1) {
            longBitsToDouble = value;
        } else {
            longBitsToDouble = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(value)));
        }
        Object writeDouble = $this$writeDouble.writeDouble(longBitsToDouble, continuation);
        return writeDouble == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeDouble : Unit.INSTANCE;
    }

    public static final Object writeShortLittleEndian(ByteWriteChannel $this$writeShortLittleEndian, short value, Continuation<? super Unit> continuation) {
        short $this$reverseByteOrder$iv = Short.reverseBytes(value);
        Object writeShort = $this$writeShortLittleEndian.writeShort($this$reverseByteOrder$iv, continuation);
        return writeShort == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeShort : Unit.INSTANCE;
    }

    public static final Object writeIntLittleEndian(ByteWriteChannel $this$writeIntLittleEndian, int value, Continuation<? super Unit> continuation) {
        int $this$reverseByteOrder$iv = Integer.reverseBytes(value);
        Object writeInt = $this$writeIntLittleEndian.writeInt($this$reverseByteOrder$iv, continuation);
        return writeInt == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeInt : Unit.INSTANCE;
    }

    public static final Object writeLongLittleEndian(ByteWriteChannel $this$writeLongLittleEndian, long value, Continuation<? super Unit> continuation) {
        long $this$reverseByteOrder$iv = Long.reverseBytes(value);
        Object writeLong = $this$writeLongLittleEndian.writeLong($this$reverseByteOrder$iv, continuation);
        return writeLong == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeLong : Unit.INSTANCE;
    }

    public static final Object writeFloatLittleEndian(ByteWriteChannel $this$writeFloatLittleEndian, float value, Continuation<? super Unit> continuation) {
        Object writeFloat = $this$writeFloatLittleEndian.writeFloat(Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(value))), continuation);
        return writeFloat == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeFloat : Unit.INSTANCE;
    }

    public static final Object writeDoubleLittleEndian(ByteWriteChannel $this$writeDoubleLittleEndian, double value, Continuation<? super Unit> continuation) {
        Object writeDouble = $this$writeDoubleLittleEndian.writeDouble(Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(value))), continuation);
        return writeDouble == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeDouble : Unit.INSTANCE;
    }

    public static final <T> T toLittleEndian(ByteReadChannel $this$toLittleEndian, T t, Function1<? super T, ? extends T> reverseBlock) {
        Intrinsics.checkNotNullParameter($this$toLittleEndian, "<this>");
        Intrinsics.checkNotNullParameter(reverseBlock, "reverseBlock");
        return reverseBlock.invoke(t);
    }

    private static final <T> T toLittleEndian(ByteWriteChannel $this$toLittleEndian, T t, Function1<? super T, ? extends T> function1) {
        return function1.invoke(t);
    }

    public static final <T> T reverseIfNeeded(T t, ByteOrder byteOrder, Function1<? super T, ? extends T> reverseBlock) {
        Intrinsics.checkNotNullParameter(byteOrder, "byteOrder");
        Intrinsics.checkNotNullParameter(reverseBlock, "reverseBlock");
        return WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1 ? t : reverseBlock.invoke(t);
    }
}
