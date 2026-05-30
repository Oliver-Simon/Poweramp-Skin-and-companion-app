package io.ktor.serialization.kotlinx;

import io.ktor.http.ContentType;
import io.ktor.serialization.Configuration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.BinaryFormat;
import kotlinx.serialization.StringFormat;

/* compiled from: KotlinxSerializationConverter.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0007¨\u0006\b"}, d2 = {"serialization", "", "Lio/ktor/serialization/Configuration;", "contentType", "Lio/ktor/http/ContentType;", "format", "Lkotlinx/serialization/BinaryFormat;", "Lkotlinx/serialization/StringFormat;", "ktor-serialization-kotlinx"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class KotlinxSerializationConverterKt {
    public static final void serialization(Configuration $this$serialization, ContentType contentType, BinaryFormat format) {
        Intrinsics.checkNotNullParameter($this$serialization, "<this>");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(format, "format");
        Configuration.DefaultImpls.register$default($this$serialization, contentType, new KotlinxSerializationConverter(format), null, 4, null);
    }

    public static final void serialization(Configuration $this$serialization, ContentType contentType, StringFormat format) {
        Intrinsics.checkNotNullParameter($this$serialization, "<this>");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(format, "format");
        Configuration.DefaultImpls.register$default($this$serialization, contentType, new KotlinxSerializationConverter(format), null, 4, null);
    }
}
