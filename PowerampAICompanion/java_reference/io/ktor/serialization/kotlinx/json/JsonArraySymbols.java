package io.ktor.serialization.kotlinx.json;

import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: KotlinxSerializationJsonExtensions.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\t¨\u0006\u000e"}, d2 = {"Lio/ktor/serialization/kotlinx/json/JsonArraySymbols;", "", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "(Ljava/nio/charset/Charset;)V", "beginArray", "", "getBeginArray", "()[B", "endArray", "getEndArray", "objectSeparator", "getObjectSeparator", "ktor-serialization-kotlinx-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
final class JsonArraySymbols {
    private final byte[] beginArray;
    private final byte[] endArray;
    private final byte[] objectSeparator;

    public JsonArraySymbols(Charset charset) {
        byte[] encodeToByteArray;
        byte[] encodeToByteArray2;
        byte[] encodeToByteArray3;
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (Intrinsics.areEqual(charset, Charsets.UTF_8)) {
            encodeToByteArray = StringsKt.encodeToByteArray("[");
        } else {
            CharsetEncoder newEncoder = charset.newEncoder();
            Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
            encodeToByteArray = CharsetJVMKt.encodeToByteArray(newEncoder, "[", 0, "[".length());
        }
        this.beginArray = encodeToByteArray;
        if (Intrinsics.areEqual(charset, Charsets.UTF_8)) {
            encodeToByteArray2 = StringsKt.encodeToByteArray("]");
        } else {
            CharsetEncoder newEncoder2 = charset.newEncoder();
            Intrinsics.checkNotNullExpressionValue(newEncoder2, "charset.newEncoder()");
            encodeToByteArray2 = CharsetJVMKt.encodeToByteArray(newEncoder2, "]", 0, "]".length());
        }
        this.endArray = encodeToByteArray2;
        if (Intrinsics.areEqual(charset, Charsets.UTF_8)) {
            encodeToByteArray3 = StringsKt.encodeToByteArray(",");
        } else {
            CharsetEncoder newEncoder3 = charset.newEncoder();
            Intrinsics.checkNotNullExpressionValue(newEncoder3, "charset.newEncoder()");
            encodeToByteArray3 = CharsetJVMKt.encodeToByteArray(newEncoder3, ",", 0, ",".length());
        }
        this.objectSeparator = encodeToByteArray3;
    }

    public final byte[] getBeginArray() {
        return this.beginArray;
    }

    public final byte[] getEndArray() {
        return this.endArray;
    }

    public final byte[] getObjectSeparator() {
        return this.objectSeparator;
    }
}
