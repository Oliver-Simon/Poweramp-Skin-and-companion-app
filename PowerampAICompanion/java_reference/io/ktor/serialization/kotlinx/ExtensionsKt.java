package io.ktor.serialization.kotlinx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerialFormat;

/* compiled from: Extensions.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"extensions", "", "Lio/ktor/serialization/kotlinx/KotlinxSerializationExtension;", "format", "Lkotlinx/serialization/SerialFormat;", "ktor-serialization-kotlinx"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ExtensionsKt {
    public static final List<KotlinxSerializationExtension> extensions(SerialFormat format) {
        Intrinsics.checkNotNullParameter(format, "format");
        Iterable $this$mapNotNull$iv = ExtensionsJvmKt.getProviders();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            KotlinxSerializationExtensionProvider it = (KotlinxSerializationExtensionProvider) element$iv$iv$iv;
            KotlinxSerializationExtension extension = it.extension(format);
            if (extension != null) {
                destination$iv$iv.add(extension);
            }
        }
        return (List) destination$iv$iv;
    }
}
