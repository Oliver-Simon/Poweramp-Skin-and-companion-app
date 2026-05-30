package io.ktor.client.plugins.contentnegotiation;

import java.io.InputStream;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: DefaultIgnoredTypesJvm.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001e\u0010\u0000\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"DefaultIgnoredTypes", "", "Lkotlin/reflect/KClass;", "getDefaultIgnoredTypes", "()Ljava/util/Set;", "ktor-client-content-negotiation"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class DefaultIgnoredTypesJvmKt {
    private static final Set<KClass<?>> DefaultIgnoredTypes = SetsKt.mutableSetOf(Reflection.getOrCreateKotlinClass(InputStream.class));

    public static final Set<KClass<?>> getDefaultIgnoredTypes() {
        return DefaultIgnoredTypes;
    }
}
