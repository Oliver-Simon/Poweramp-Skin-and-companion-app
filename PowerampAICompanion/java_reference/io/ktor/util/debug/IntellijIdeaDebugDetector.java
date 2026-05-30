package io.ktor.util.debug;

import java.lang.management.ManagementFactory;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt;

/* compiled from: IntellijIdeaDebugDetectorJvm.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0003\u0010\u0005¨\u0006\b"}, d2 = {"Lio/ktor/util/debug/IntellijIdeaDebugDetector;", "", "()V", "isDebuggerConnected", "", "()Z", "isDebuggerConnected$delegate", "Lkotlin/Lazy;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class IntellijIdeaDebugDetector {
    public static final IntellijIdeaDebugDetector INSTANCE = new IntellijIdeaDebugDetector();

    /* renamed from: isDebuggerConnected$delegate, reason: from kotlin metadata */
    private static final Lazy isDebuggerConnected = LazyKt.lazy(new Function0<Boolean>() { // from class: io.ktor.util.debug.IntellijIdeaDebugDetector$isDebuggerConnected$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            boolean z = false;
            try {
                z = StringsKt.contains$default((CharSequence) ManagementFactory.getRuntimeMXBean().getInputArguments().toString(), (CharSequence) "jdwp", false, 2, (Object) null);
            } catch (Throwable th) {
            }
            return Boolean.valueOf(z);
        }
    });

    private IntellijIdeaDebugDetector() {
    }

    public final boolean isDebuggerConnected() {
        return ((Boolean) isDebuggerConnected.getValue()).booleanValue();
    }
}
