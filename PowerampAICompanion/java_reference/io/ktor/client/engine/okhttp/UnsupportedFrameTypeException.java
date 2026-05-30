package io.ktor.client.engine.okhttp;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.websocket.Frame;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;

/* compiled from: OkHttpWebsocketSession.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0000H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lio/ktor/client/engine/okhttp/UnsupportedFrameTypeException;", "Ljava/lang/IllegalArgumentException;", "Lkotlin/IllegalArgumentException;", "Lkotlinx/coroutines/CopyableThrowable;", TypedValues.AttributesType.S_FRAME, "Lio/ktor/websocket/Frame;", "(Lio/ktor/websocket/Frame;)V", "createCopy", "ktor-client-okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class UnsupportedFrameTypeException extends IllegalArgumentException implements CopyableThrowable<UnsupportedFrameTypeException> {
    private final Frame frame;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnsupportedFrameTypeException(Frame frame) {
        super("Unsupported frame type: " + frame);
        Intrinsics.checkNotNullParameter(frame, "frame");
        this.frame = frame;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlinx.coroutines.CopyableThrowable
    public UnsupportedFrameTypeException createCopy() {
        UnsupportedFrameTypeException it = new UnsupportedFrameTypeException(this.frame);
        it.initCause(this);
        return it;
    }
}
