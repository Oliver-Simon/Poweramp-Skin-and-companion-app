package androidx.core.os;

import java.lang.Throwable;

/* loaded from: classes.dex */
public interface OutcomeReceiverCompat<R, E extends Throwable> {
    void onError(E e);

    void onResult(R r);

    /* renamed from: androidx.core.os.OutcomeReceiverCompat$-CC, reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        public static void $default$onError(OutcomeReceiverCompat outcomeReceiverCompat, Throwable th) {
        }
    }
}
