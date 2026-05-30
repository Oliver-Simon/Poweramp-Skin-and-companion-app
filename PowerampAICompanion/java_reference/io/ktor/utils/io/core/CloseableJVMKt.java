package io.ktor.utils.io.core;

import com.maxmpz.poweramp.player.RouterConsts;
import java.lang.reflect.Method;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CloseableJVM.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0001\"\u001d\u0010\u0000\u001a\u0004\u0018\u00010\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003*\n\u0010\n\"\u00020\u000b2\u00020\u000b¨\u0006\f"}, d2 = {"AddSuppressedMethod", "Ljava/lang/reflect/Method;", "getAddSuppressedMethod", "()Ljava/lang/reflect/Method;", "AddSuppressedMethod$delegate", "Lkotlin/Lazy;", "addSuppressedInternal", "", "", RouterConsts.DEVICE_NAME_OTHER, "Closeable", "Ljava/io/Closeable;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class CloseableJVMKt {
    private static final Lazy AddSuppressedMethod$delegate = LazyKt.lazy(new Function0<Method>() { // from class: io.ktor.utils.io.core.CloseableJVMKt$AddSuppressedMethod$2
        @Override // kotlin.jvm.functions.Function0
        public final Method invoke() {
            try {
                return Throwable.class.getMethod("addSuppressed", Throwable.class);
            } catch (Throwable th) {
                return null;
            }
        }
    });

    public static final void addSuppressedInternal(Throwable $this$addSuppressedInternal, Throwable other) {
        Intrinsics.checkNotNullParameter($this$addSuppressedInternal, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Method addSuppressedMethod = getAddSuppressedMethod();
        if (addSuppressedMethod != null) {
            addSuppressedMethod.invoke($this$addSuppressedInternal, other);
        }
    }

    private static final Method getAddSuppressedMethod() {
        return (Method) AddSuppressedMethod$delegate.getValue();
    }
}
