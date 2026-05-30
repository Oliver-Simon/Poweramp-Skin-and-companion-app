package io.ktor.util.pipeline;

import io.ktor.utils.io.ExceptionUtilsJvmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StackTraceRecoverJvm.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0000¨\u0006\u0003"}, d2 = {"withCause", "", "cause", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class StackTraceRecoverJvmKt {
    public static final Throwable withCause(Throwable $this$withCause, Throwable cause) {
        Intrinsics.checkNotNullParameter($this$withCause, "<this>");
        if (cause == null || Intrinsics.areEqual($this$withCause.getCause(), cause)) {
            return $this$withCause;
        }
        Throwable result = ExceptionUtilsJvmKt.tryCopyException($this$withCause, cause);
        if (result == null) {
            return $this$withCause;
        }
        result.setStackTrace($this$withCause.getStackTrace());
        return result;
    }
}
