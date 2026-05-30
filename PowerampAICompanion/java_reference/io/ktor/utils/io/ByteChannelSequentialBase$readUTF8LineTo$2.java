package io.ktor.utils.io;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.core.Input;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ByteChannelSequential.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\f\b\u0000\u0010\u0002*\u00060\u0003j\u0002`\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/core/Input;", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", ContentDisposition.Parameters.Size, ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteChannelSequentialBase$readUTF8LineTo$2", f = "ByteChannelSequential.kt", i = {}, l = {721}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
public final class ByteChannelSequentialBase$readUTF8LineTo$2 extends SuspendLambda implements Function2<Integer, Continuation<? super Input>, Object> {
    /* synthetic */ int I$0;
    int label;
    final /* synthetic */ ByteChannelSequentialBase this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteChannelSequentialBase$readUTF8LineTo$2(ByteChannelSequentialBase byteChannelSequentialBase, Continuation<? super ByteChannelSequentialBase$readUTF8LineTo$2> continuation) {
        super(2, continuation);
        this.this$0 = byteChannelSequentialBase;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ByteChannelSequentialBase$readUTF8LineTo$2 byteChannelSequentialBase$readUTF8LineTo$2 = new ByteChannelSequentialBase$readUTF8LineTo$2(this.this$0, continuation);
        byteChannelSequentialBase$readUTF8LineTo$2.I$0 = ((Number) obj).intValue();
        return byteChannelSequentialBase$readUTF8LineTo$2;
    }

    public final Object invoke(int i, Continuation<? super Input> continuation) {
        return ((ByteChannelSequentialBase$readUTF8LineTo$2) create(Integer.valueOf(i), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Integer num, Continuation<? super Input> continuation) {
        return invoke(num.intValue(), continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        ByteChannelSequentialBase$readUTF8LineTo$2 byteChannelSequentialBase$readUTF8LineTo$2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                byteChannelSequentialBase$readUTF8LineTo$2 = this;
                int size = byteChannelSequentialBase$readUTF8LineTo$2.I$0;
                byteChannelSequentialBase$readUTF8LineTo$2.label = 1;
                Object await = byteChannelSequentialBase$readUTF8LineTo$2.this$0.await(size, byteChannelSequentialBase$readUTF8LineTo$2);
                if (await != coroutine_suspended) {
                    $result = await;
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                byteChannelSequentialBase$readUTF8LineTo$2 = this;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (((Boolean) $result).booleanValue()) {
            return byteChannelSequentialBase$readUTF8LineTo$2.this$0.getReadable();
        }
        return null;
    }
}
