package io.ktor.utils.io.core.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Numbers.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0081\b¨\u0006\b"}, d2 = {"failLongToIntConversion", "", "value", "", "name", "", "toIntOrFail", "", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class NumbersKt {
    public static final int toIntOrFail(long $this$toIntOrFail, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if ($this$toIntOrFail >= 2147483647L) {
            failLongToIntConversion($this$toIntOrFail, name);
            throw new KotlinNothingValueException();
        }
        return (int) $this$toIntOrFail;
    }

    public static final Void failLongToIntConversion(long value, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        throw new IllegalArgumentException("Long value " + value + " of " + name + " doesn't fit into 32-bit integer");
    }
}
