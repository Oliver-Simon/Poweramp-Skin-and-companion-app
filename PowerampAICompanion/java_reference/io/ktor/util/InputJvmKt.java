package io.ktor.util;

import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.InputArraysKt;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InputJvm.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"asStream", "Ljava/io/InputStream;", "Lio/ktor/utils/io/core/Input;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class InputJvmKt {
    public static final InputStream asStream(final Input $this$asStream) {
        Intrinsics.checkNotNullParameter($this$asStream, "<this>");
        return new InputStream() { // from class: io.ktor.util.InputJvmKt$asStream$1
            @Override // java.io.InputStream
            public int read() {
                if (Input.this.getEndOfInput()) {
                    return -1;
                }
                return Input.this.readByte();
            }

            @Override // java.io.InputStream
            public int read(byte[] buffer, int offset, int length) {
                Intrinsics.checkNotNullParameter(buffer, "buffer");
                if (Input.this.getEndOfInput()) {
                    return -1;
                }
                return InputArraysKt.readAvailable(Input.this, buffer, offset, length);
            }

            @Override // java.io.InputStream
            public long skip(long count) {
                return Input.this.discard(count);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                Input.this.close();
            }
        };
    }
}
