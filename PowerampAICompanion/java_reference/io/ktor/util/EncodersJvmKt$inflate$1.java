package io.ktor.util;

import androidx.appcompat.app.AppCompatDelegate;
import com.maxmpz.poweramp.player.PowerampAPI;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.WriterScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EncodersJvm.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.util.EncodersJvmKt$inflate$1", f = "EncodersJvm.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6}, l = {68, 85, 161, 164, 103, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, 121}, m = "invokeSuspend", n = {"$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "magic", "format", PowerampAPI.Track.FLAGS, "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "magic", "format", PowerampAPI.Track.FLAGS, "extraLen", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "n$iv", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "totalSize", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "totalSize", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "totalSize"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "S$0", "B$0", "B$1", "L$0", "L$1", "L$2", "L$3", "L$4", "S$0", "B$0", "B$1", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* loaded from: classes9.dex */
public final class EncodersJvmKt$inflate$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $gzip;
    final /* synthetic */ ByteReadChannel $source;
    byte B$0;
    byte B$1;
    int I$0;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    short S$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EncodersJvmKt$inflate$1(boolean z, ByteReadChannel byteReadChannel, Continuation<? super EncodersJvmKt$inflate$1> continuation) {
        super(2, continuation);
        this.$gzip = z;
        this.$source = byteReadChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        EncodersJvmKt$inflate$1 encodersJvmKt$inflate$1 = new EncodersJvmKt$inflate$1(this.$gzip, this.$source, continuation);
        encodersJvmKt$inflate$1.L$0 = obj;
        return encodersJvmKt$inflate$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((EncodersJvmKt$inflate$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01ec A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x03c7 A[Catch: all -> 0x04d2, TRY_LEAVE, TryCatch #0 {all -> 0x04d2, blocks: (B:8:0x0039, B:12:0x03c1, B:14:0x03c7, B:18:0x0409, B:20:0x040d, B:24:0x041a, B:28:0x043f, B:35:0x0449, B:36:0x0472, B:38:0x0473, B:39:0x047f, B:41:0x0480, B:42:0x04a7, B:44:0x04a8, B:46:0x04c3, B:47:0x04ce, B:58:0x0061, B:63:0x0354, B:65:0x035a, B:67:0x0360, B:71:0x03a7, B:72:0x030e, B:74:0x0316, B:83:0x03b3, B:85:0x03bb, B:87:0x04d1, B:89:0x0086, B:95:0x0307), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0409 A[Catch: all -> 0x04d2, TRY_ENTER, TryCatch #0 {all -> 0x04d2, blocks: (B:8:0x0039, B:12:0x03c1, B:14:0x03c7, B:18:0x0409, B:20:0x040d, B:24:0x041a, B:28:0x043f, B:35:0x0449, B:36:0x0472, B:38:0x0473, B:39:0x047f, B:41:0x0480, B:42:0x04a7, B:44:0x04a8, B:46:0x04c3, B:47:0x04ce, B:58:0x0061, B:63:0x0354, B:65:0x035a, B:67:0x0360, B:71:0x03a7, B:72:0x030e, B:74:0x0316, B:83:0x03b3, B:85:0x03bb, B:87:0x04d1, B:89:0x0086, B:95:0x0307), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x035a A[Catch: all -> 0x04d2, TryCatch #0 {all -> 0x04d2, blocks: (B:8:0x0039, B:12:0x03c1, B:14:0x03c7, B:18:0x0409, B:20:0x040d, B:24:0x041a, B:28:0x043f, B:35:0x0449, B:36:0x0472, B:38:0x0473, B:39:0x047f, B:41:0x0480, B:42:0x04a7, B:44:0x04a8, B:46:0x04c3, B:47:0x04ce, B:58:0x0061, B:63:0x0354, B:65:0x035a, B:67:0x0360, B:71:0x03a7, B:72:0x030e, B:74:0x0316, B:83:0x03b3, B:85:0x03bb, B:87:0x04d1, B:89:0x0086, B:95:0x0307), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0316 A[Catch: all -> 0x04d2, TRY_LEAVE, TryCatch #0 {all -> 0x04d2, blocks: (B:8:0x0039, B:12:0x03c1, B:14:0x03c7, B:18:0x0409, B:20:0x040d, B:24:0x041a, B:28:0x043f, B:35:0x0449, B:36:0x0472, B:38:0x0473, B:39:0x047f, B:41:0x0480, B:42:0x04a7, B:44:0x04a8, B:46:0x04c3, B:47:0x04ce, B:58:0x0061, B:63:0x0354, B:65:0x035a, B:67:0x0360, B:71:0x03a7, B:72:0x030e, B:74:0x0316, B:83:0x03b3, B:85:0x03bb, B:87:0x04d1, B:89:0x0086, B:95:0x0307), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0341 A[Catch: all -> 0x03af, TRY_LEAVE, TryCatch #2 {all -> 0x03af, blocks: (B:10:0x03f1, B:61:0x038d, B:78:0x0339, B:80:0x0341), top: B:60:0x038d }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x03b3 A[Catch: all -> 0x04d2, TryCatch #0 {all -> 0x04d2, blocks: (B:8:0x0039, B:12:0x03c1, B:14:0x03c7, B:18:0x0409, B:20:0x040d, B:24:0x041a, B:28:0x043f, B:35:0x0449, B:36:0x0472, B:38:0x0473, B:39:0x047f, B:41:0x0480, B:42:0x04a7, B:44:0x04a8, B:46:0x04c3, B:47:0x04ce, B:58:0x0061, B:63:0x0354, B:65:0x035a, B:67:0x0360, B:71:0x03a7, B:72:0x030e, B:74:0x0316, B:83:0x03b3, B:85:0x03bb, B:87:0x04d1, B:89:0x0086, B:95:0x0307), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0281  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x03ed -> B:10:0x03f1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:70:0x0386 -> B:60:0x038d). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:81:0x0353 -> B:63:0x0354). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:82:0x03ac -> B:72:0x030e). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r22) {
        /*
            Method dump skipped, instructions count: 1276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.EncodersJvmKt$inflate$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
