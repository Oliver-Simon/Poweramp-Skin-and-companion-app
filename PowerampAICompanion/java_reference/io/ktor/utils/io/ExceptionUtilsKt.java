package io.ktor.utils.io;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExceptionUtils.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0003\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"unwrapCancellationException", "", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ExceptionUtilsKt {
    public static final Throwable unwrapCancellationException(Throwable $this$unwrapCancellationException) {
        Intrinsics.checkNotNullParameter($this$unwrapCancellationException, "<this>");
        Throwable exception = $this$unwrapCancellationException;
        while (exception instanceof CancellationException) {
            if (Intrinsics.areEqual(exception, exception.getCause())) {
                return $this$unwrapCancellationException;
            }
            Throwable cause = exception.getCause();
            if (cause == null) {
                return exception;
            }
            exception = cause;
        }
        return exception;
    }
}
