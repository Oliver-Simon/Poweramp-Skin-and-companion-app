package io.ktor.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Throwable.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\" \u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"rootCause", "", "getRootCause$annotations", "(Ljava/lang/Throwable;)V", "getRootCause", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ThrowableKt {
    @InternalAPI
    public static /* synthetic */ void getRootCause$annotations(Throwable th) {
    }

    public static final Throwable getRootCause(Throwable $this$rootCause) {
        Intrinsics.checkNotNullParameter($this$rootCause, "<this>");
        Throwable rootCause = $this$rootCause;
        while (true) {
            if ((rootCause != null ? rootCause.getCause() : null) != null) {
                rootCause = rootCause.getCause();
            } else {
                return rootCause;
            }
        }
    }
}
