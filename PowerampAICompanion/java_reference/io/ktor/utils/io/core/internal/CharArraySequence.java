package io.ktor.utils.io.core.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CharArraySequence.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0086\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u0005H\u0002J\u0016\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lio/ktor/utils/io/core/internal/CharArraySequence;", "", "array", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "length", "([CII)V", "getLength", "()I", "get", "", "index", "indexOutOfBounds", "", "subSequence", "startIndex", "endIndex", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class CharArraySequence implements CharSequence {
    private final char[] array;
    private final int length;
    private final int offset;

    public CharArraySequence(char[] array, int offset, int length) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.array = array;
        this.offset = offset;
        this.length = length;
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ char charAt(int index) {
        return get(index);
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ int length() {
        return this.length;
    }

    public final int getLength() {
        return this.length;
    }

    public final char get(int index) {
        if (index >= this.length) {
            indexOutOfBounds(index);
            throw new KotlinNothingValueException();
        }
        return this.array[this.offset + index];
    }

    @Override // java.lang.CharSequence
    public final CharSequence subSequence(int startIndex, int endIndex) {
        if (!(startIndex >= 0)) {
            throw new IllegalArgumentException(("startIndex shouldn't be negative: " + startIndex).toString());
        }
        if (!(startIndex <= this.length)) {
            throw new IllegalArgumentException(("startIndex is too large: " + startIndex + " > " + this.length).toString());
        }
        if (!(startIndex + endIndex <= this.length)) {
            throw new IllegalArgumentException(("endIndex is too large: " + endIndex + " > " + this.length).toString());
        }
        if (endIndex >= startIndex) {
            return new CharArraySequence(this.array, this.offset + startIndex, endIndex - startIndex);
        }
        throw new IllegalArgumentException(("endIndex should be greater or equal to startIndex: " + startIndex + " > " + endIndex).toString());
    }

    private final Void indexOutOfBounds(int index) {
        throw new IndexOutOfBoundsException("String index out of bounds: " + index + " > " + this.length);
    }
}
