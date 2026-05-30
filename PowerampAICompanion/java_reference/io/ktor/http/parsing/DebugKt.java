package io.ktor.http.parsing;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: Debug.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u0016\u0010\u0006\u001a\u00020\u0001*\u00020\u00072\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\b"}, d2 = {"printlnWithOffset", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "node", "", "printDebug", "Lio/ktor/http/parsing/Grammar;", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class DebugKt {
    public static final void printDebug(Grammar $this$printDebug, int offset) {
        Intrinsics.checkNotNullParameter($this$printDebug, "<this>");
        if (!($this$printDebug instanceof StringGrammar)) {
            if (!($this$printDebug instanceof RawGrammar)) {
                if ($this$printDebug instanceof NamedGrammar) {
                    printlnWithOffset(offset, "NAMED[" + ((NamedGrammar) $this$printDebug).getName() + AbstractJsonLexerKt.END_LIST);
                    printDebug(((NamedGrammar) $this$printDebug).getGrammar(), offset + 2);
                    return;
                }
                if ($this$printDebug instanceof SequenceGrammar) {
                    printlnWithOffset(offset, "SEQUENCE");
                    Iterable $this$forEach$iv = ((SequenceGrammar) $this$printDebug).getGrammars();
                    for (Object element$iv : $this$forEach$iv) {
                        Grammar it = (Grammar) element$iv;
                        printDebug(it, offset + 2);
                    }
                    return;
                }
                if ($this$printDebug instanceof OrGrammar) {
                    printlnWithOffset(offset, "OR");
                    Iterable $this$forEach$iv2 = ((OrGrammar) $this$printDebug).getGrammars();
                    for (Object element$iv2 : $this$forEach$iv2) {
                        Grammar it2 = (Grammar) element$iv2;
                        printDebug(it2, offset + 2);
                    }
                    return;
                }
                if ($this$printDebug instanceof MaybeGrammar) {
                    printlnWithOffset(offset, "MAYBE");
                    printDebug(((MaybeGrammar) $this$printDebug).getGrammar(), offset + 2);
                    return;
                }
                if ($this$printDebug instanceof ManyGrammar) {
                    printlnWithOffset(offset, "MANY");
                    printDebug(((ManyGrammar) $this$printDebug).getGrammar(), offset + 2);
                    return;
                } else if ($this$printDebug instanceof AtLeastOne) {
                    printlnWithOffset(offset, "MANY_NOT_EMPTY");
                    printDebug(((AtLeastOne) $this$printDebug).getGrammar(), offset + 2);
                    return;
                } else {
                    if (!($this$printDebug instanceof AnyOfGrammar)) {
                        if (!($this$printDebug instanceof RangeGrammar)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        printlnWithOffset(offset, "RANGE[" + ((RangeGrammar) $this$printDebug).getFrom() + '-' + ((RangeGrammar) $this$printDebug).getTo() + AbstractJsonLexerKt.END_LIST);
                        return;
                    }
                    printlnWithOffset(offset, "ANY_OF[" + Regex.INSTANCE.escape(((AnyOfGrammar) $this$printDebug).getValue()) + AbstractJsonLexerKt.END_LIST);
                    return;
                }
            }
            printlnWithOffset(offset, "STRING[" + ((RawGrammar) $this$printDebug).getValue() + AbstractJsonLexerKt.END_LIST);
            return;
        }
        printlnWithOffset(offset, "STRING[" + Regex.INSTANCE.escape(((StringGrammar) $this$printDebug).getValue()) + AbstractJsonLexerKt.END_LIST);
    }

    public static /* synthetic */ void printDebug$default(Grammar grammar, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        printDebug(grammar, i);
    }

    private static final void printlnWithOffset(int offset, Object node) {
        System.out.println((Object) (StringsKt.repeat(" ", offset) + (offset / 2) + ": " + node));
    }
}
