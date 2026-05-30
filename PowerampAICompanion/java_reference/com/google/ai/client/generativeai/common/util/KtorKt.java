package com.google.ai.client.generativeai.common.util;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.serialization.json.Json;

/* compiled from: ktor.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\u001a#\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0006\b\u0000\u0010\u0004\u0018\u0001*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0080\b\u001a9\u0010\b\u001a\u00020\t*\u00020\u00072\"\u0010\n\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u000bH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a\u001d\u0010\u000f\u001a\u00020\t*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u0013\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"SSE_SEPARATOR", "", "decodeToFlow", "Lkotlinx/coroutines/flow/Flow;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/serialization/json/Json;", "channel", "Lio/ktor/utils/io/ByteReadChannel;", "onEachLine", "", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "send", "Lio/ktor/utils/io/ByteChannel;", "bytes", "", "(Lio/ktor/utils/io/ByteChannel;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "common_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class KtorKt {
    public static final String SSE_SEPARATOR = "\r\n\r\n";

    /* JADX WARN: Code restructure failed: missing block: B:35:0x005b, code lost:
    
        r10 = r0;
        r0 = r1;
        r1 = r2;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x00a8 -> B:12:0x005b). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object onEachLine(io.ktor.utils.io.ByteReadChannel r8, kotlin.jvm.functions.Function2<? super java.lang.String, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instructions count: 192
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.common.util.KtorKt.onEachLine(io.ktor.utils.io.ByteReadChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final /* synthetic */ <T> Flow<T> decodeToFlow(Json $this$decodeToFlow, ByteReadChannel channel) {
        Intrinsics.checkNotNullParameter($this$decodeToFlow, "<this>");
        Intrinsics.checkNotNullParameter(channel, "channel");
        Intrinsics.needClassReification();
        return FlowKt.channelFlow(new KtorKt$decodeToFlow$1(channel, $this$decodeToFlow, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object send(io.ktor.utils.io.ByteChannel r4, byte[] r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            boolean r0 = r6 instanceof com.google.ai.client.generativeai.common.util.KtorKt$send$1
            if (r0 == 0) goto L14
            r0 = r6
            com.google.ai.client.generativeai.common.util.KtorKt$send$1 r0 = (com.google.ai.client.generativeai.common.util.KtorKt$send$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            com.google.ai.client.generativeai.common.util.KtorKt$send$1 r0 = new com.google.ai.client.generativeai.common.util.KtorKt$send$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L34;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2c:
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteChannel r4 = (io.ktor.utils.io.ByteChannel) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L46
        L34:
            kotlin.ResultKt.throwOnFailure(r6)
            r2 = r4
            io.ktor.utils.io.ByteWriteChannel r2 = (io.ktor.utils.io.ByteWriteChannel) r2
            r0.L$0 = r4
            r3 = 1
            r0.label = r3
            java.lang.Object r5 = io.ktor.utils.io.ByteWriteChannelKt.writeFully(r2, r5, r0)
            if (r5 != r1) goto L46
            return r1
        L46:
            r5 = r4
            io.ktor.utils.io.ByteWriteChannel r5 = (io.ktor.utils.io.ByteWriteChannel) r5
            io.ktor.utils.io.ByteWriteChannelKt.close(r5)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.common.util.KtorKt.send(io.ktor.utils.io.ByteChannel, byte[], kotlin.coroutines.Continuation):java.lang.Object");
    }
}
