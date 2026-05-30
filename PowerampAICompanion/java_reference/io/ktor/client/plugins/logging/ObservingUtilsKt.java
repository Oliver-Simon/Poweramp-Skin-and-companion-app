package io.ktor.client.plugins.logging;

import io.ktor.http.content.OutgoingContent;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.CoroutinesKt;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: ObservingUtils.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0007H\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, d2 = {"observe", "Lio/ktor/http/content/OutgoingContent;", "log", "Lio/ktor/utils/io/ByteWriteChannel;", "(Lio/ktor/http/content/OutgoingContent;Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toReadChannel", "Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/http/content/OutgoingContent$WriteChannelContent;", "ktor-client-logging"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ObservingUtilsKt {
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object observe(io.ktor.http.content.OutgoingContent r6, io.ktor.utils.io.ByteWriteChannel r7, kotlin.coroutines.Continuation<? super io.ktor.http.content.OutgoingContent> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.client.plugins.logging.ObservingUtilsKt$observe$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.client.plugins.logging.ObservingUtilsKt$observe$1 r0 = (io.ktor.client.plugins.logging.ObservingUtilsKt$observe$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.client.plugins.logging.ObservingUtilsKt$observe$1 r0 = new io.ktor.client.plugins.logging.ObservingUtilsKt$observe$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2c:
            java.lang.Object r6 = r0.L$1
            io.ktor.utils.io.ByteWriteChannel r6 = (io.ktor.utils.io.ByteWriteChannel) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.http.content.OutgoingContent r7 = (io.ktor.http.content.OutgoingContent) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L57
        L38:
            kotlin.ResultKt.throwOnFailure(r8)
            boolean r2 = r6 instanceof io.ktor.http.content.OutgoingContent.ByteArrayContent
            r3 = 1
            if (r2 == 0) goto L5c
            r2 = r6
            io.ktor.http.content.OutgoingContent$ByteArrayContent r2 = (io.ktor.http.content.OutgoingContent.ByteArrayContent) r2
            byte[] r2 = r2.getBytes()
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r2 = io.ktor.utils.io.ByteWriteChannelKt.writeFully(r7, r2, r0)
            if (r2 != r1) goto L54
            return r1
        L54:
            r5 = r7
            r7 = r6
            r6 = r5
        L57:
            io.ktor.utils.io.ByteWriteChannelKt.close(r6)
            r6 = r7
            goto La4
        L5c:
            boolean r1 = r6 instanceof io.ktor.http.content.OutgoingContent.ReadChannelContent
            r2 = 0
            r4 = 0
            if (r1 == 0) goto L7f
            io.ktor.utils.io.ByteChannel r1 = io.ktor.utils.io.ByteChannelKt.ByteChannel$default(r4, r3, r2)
            r2 = r6
            io.ktor.http.content.OutgoingContent$ReadChannelContent r2 = (io.ktor.http.content.OutgoingContent.ReadChannelContent) r2
            io.ktor.utils.io.ByteReadChannel r2 = r2.getChannel()
            r3 = r1
            io.ktor.utils.io.ByteWriteChannel r3 = (io.ktor.utils.io.ByteWriteChannel) r3
            io.ktor.util.ByteChannelsKt.copyToBoth(r2, r7, r3)
            io.ktor.client.plugins.logging.LoggedContent r7 = new io.ktor.client.plugins.logging.LoggedContent
            r3 = r1
            io.ktor.utils.io.ByteReadChannel r3 = (io.ktor.utils.io.ByteReadChannel) r3
            r7.<init>(r6, r3)
            r6 = r7
            io.ktor.http.content.OutgoingContent r6 = (io.ktor.http.content.OutgoingContent) r6
            goto La4
        L7f:
            boolean r1 = r6 instanceof io.ktor.http.content.OutgoingContent.WriteChannelContent
            if (r1 == 0) goto La0
            io.ktor.utils.io.ByteChannel r1 = io.ktor.utils.io.ByteChannelKt.ByteChannel$default(r4, r3, r2)
            r2 = r6
            io.ktor.http.content.OutgoingContent$WriteChannelContent r2 = (io.ktor.http.content.OutgoingContent.WriteChannelContent) r2
            io.ktor.utils.io.ByteReadChannel r2 = toReadChannel(r2)
            r3 = r1
            io.ktor.utils.io.ByteWriteChannel r3 = (io.ktor.utils.io.ByteWriteChannel) r3
            io.ktor.util.ByteChannelsKt.copyToBoth(r2, r7, r3)
            io.ktor.client.plugins.logging.LoggedContent r7 = new io.ktor.client.plugins.logging.LoggedContent
            r3 = r1
            io.ktor.utils.io.ByteReadChannel r3 = (io.ktor.utils.io.ByteReadChannel) r3
            r7.<init>(r6, r3)
            r6 = r7
            io.ktor.http.content.OutgoingContent r6 = (io.ktor.http.content.OutgoingContent) r6
            goto La4
        La0:
            io.ktor.utils.io.ByteWriteChannelKt.close(r7)
        La4:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.logging.ObservingUtilsKt.observe(io.ktor.http.content.OutgoingContent, io.ktor.utils.io.ByteWriteChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final ByteReadChannel toReadChannel(OutgoingContent.WriteChannelContent $this$toReadChannel) {
        return CoroutinesKt.writer$default((CoroutineScope) GlobalScope.INSTANCE, (CoroutineContext) Dispatchers.getDefault(), false, (Function2) new ObservingUtilsKt$toReadChannel$1($this$toReadChannel, null), 2, (Object) null).getChannel();
    }
}
