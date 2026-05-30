package io.ktor.http;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CookieUtils.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0011\u001a\u00020\u00062\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00060\u0013J\u001a\u0010\u0015\u001a\u00020\u00062\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00060\u0013J%\u0010\u0016\u001a\u00020\u00032\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00180\u0013¢\u0006\u0002\b\u0019H\u0086\bø\u0001\u0000J\u001a\u0010\u001a\u001a\u00020\u00062\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00060\u0013R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001b"}, d2 = {"Lio/ktor/http/StringLexer;", "", "source", "", "(Ljava/lang/String;)V", "hasRemaining", "", "getHasRemaining", "()Z", "index", "", "getIndex", "()I", "setIndex", "(I)V", "getSource", "()Ljava/lang/String;", "accept", "predicate", "Lkotlin/Function1;", "", "acceptWhile", "capture", "block", "", "Lkotlin/ExtensionFunctionType;", "test", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class StringLexer {
    private int index;
    private final String source;

    public StringLexer(String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
    }

    public final String getSource() {
        return this.source;
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    public final boolean getHasRemaining() {
        return this.index < this.source.length();
    }

    public final boolean test(Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return this.index < this.source.length() && predicate.invoke(Character.valueOf(this.source.charAt(this.index))).booleanValue();
    }

    public final boolean accept(Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        boolean it = test(predicate);
        if (it) {
            this.index++;
        }
        return it;
    }

    public final boolean acceptWhile(Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        if (!test(predicate)) {
            return false;
        }
        while (test(predicate)) {
            this.index++;
        }
        return true;
    }

    public final String capture(Function1<? super StringLexer, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        int start = getIndex();
        block.invoke(this);
        String substring = getSource().substring(start, getIndex());
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }
}
