package io.ktor.websocket;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RawWebSocketCommon.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.websocket.RawWebSocketCommonKt", f = "RawWebSocketCommon.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4}, l = {173, 183, 186, 187, 195, 200}, m = "writeFrame", n = {"$this$writeFrame", TypedValues.AttributesType.S_FRAME, "masking", "length", "$this$writeFrame", TypedValues.AttributesType.S_FRAME, "masking", "length", "formattedLength", "$this$writeFrame", TypedValues.AttributesType.S_FRAME, "masking", "$this$writeFrame", TypedValues.AttributesType.S_FRAME, "masking", "$this$writeFrame", "data", "maskKey"}, s = {"L$0", "L$1", "Z$0", "I$0", "L$0", "L$1", "Z$0", "I$0", "I$1", "L$0", "L$1", "Z$0", "L$0", "L$1", "Z$0", "L$0", "L$1", "I$0"})
/* loaded from: classes9.dex */
public final class RawWebSocketCommonKt$writeFrame$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RawWebSocketCommonKt$writeFrame$1(Continuation<? super RawWebSocketCommonKt$writeFrame$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return RawWebSocketCommonKt.writeFrame(null, null, false, this);
    }
}
