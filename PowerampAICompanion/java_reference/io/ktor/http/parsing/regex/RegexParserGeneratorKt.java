package io.ktor.http.parsing.regex;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.parsing.AnyOfGrammar;
import io.ktor.http.parsing.AtLeastOne;
import io.ktor.http.parsing.ComplexGrammar;
import io.ktor.http.parsing.Grammar;
import io.ktor.http.parsing.ManyGrammar;
import io.ktor.http.parsing.MaybeGrammar;
import io.ktor.http.parsing.NamedGrammar;
import io.ktor.http.parsing.OrGrammar;
import io.ktor.http.parsing.Parser;
import io.ktor.http.parsing.RangeGrammar;
import io.ktor.http.parsing.RawGrammar;
import io.ktor.http.parsing.SimpleGrammar;
import io.ktor.http.parsing.StringGrammar;
import io.ktor.util.date.GMTDateParser;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: RegexParserGenerator.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a.\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00022\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0002\u001a\f\u0010\b\u001a\u00020\t*\u00020\nH\u0000\u001a:\u0010\u000b\u001a\u00020\f*\u00020\n2\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0002¨\u0006\u0011"}, d2 = {"add", "", "", "", "", "", "key", "value", "buildRegexParser", "Lio/ktor/http/parsing/Parser;", "Lio/ktor/http/parsing/Grammar;", "toRegex", "Lio/ktor/http/parsing/regex/GrammarRegex;", "groups", TypedValues.CycleType.S_WAVE_OFFSET, "shouldGroup", "", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class RegexParserGeneratorKt {
    public static final Parser buildRegexParser(Grammar $this$buildRegexParser) {
        Intrinsics.checkNotNullParameter($this$buildRegexParser, "<this>");
        Map groups = new LinkedHashMap();
        String expression = toRegex$default($this$buildRegexParser, groups, 0, false, 6, null).getRegex();
        return new RegexParser(new Regex(expression), groups);
    }

    static /* synthetic */ GrammarRegex toRegex$default(Grammar grammar, Map map, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return toRegex(grammar, map, i, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final GrammarRegex toRegex(Grammar grammar, Map<String, List<Integer>> map, int offset, boolean shouldGroup) {
        char operator;
        boolean z;
        if (grammar instanceof StringGrammar) {
            return new GrammarRegex(Regex.INSTANCE.escape(((StringGrammar) grammar).getValue()), 0, false, 6, null);
        }
        if (grammar instanceof RawGrammar) {
            return new GrammarRegex(((RawGrammar) grammar).getValue(), 0, false, 6, null);
        }
        boolean z2 = true;
        if (!(grammar instanceof NamedGrammar)) {
            if (!(grammar instanceof ComplexGrammar)) {
                if (grammar instanceof SimpleGrammar) {
                    if (grammar instanceof MaybeGrammar) {
                        operator = '?';
                    } else if (grammar instanceof ManyGrammar) {
                        operator = GMTDateParser.ANY;
                    } else {
                        if (!(grammar instanceof AtLeastOne)) {
                            throw new IllegalStateException(("Unsupported simple grammar element: " + grammar).toString());
                        }
                        operator = '+';
                    }
                    GrammarRegex nested = toRegex(((SimpleGrammar) grammar).getGrammar(), map, offset, true);
                    return new GrammarRegex(nested.getRegex() + operator, nested.getGroupsCount(), false, 4, null);
                }
                if (grammar instanceof AnyOfGrammar) {
                    return new GrammarRegex(AbstractJsonLexerKt.BEGIN_LIST + Regex.INSTANCE.escape(((AnyOfGrammar) grammar).getValue()) + AbstractJsonLexerKt.END_LIST, 0, false, 6, null);
                }
                if (grammar instanceof RangeGrammar) {
                    return new GrammarRegex(new StringBuilder().append(AbstractJsonLexerKt.BEGIN_LIST).append(((RangeGrammar) grammar).getFrom()).append('-').append(((RangeGrammar) grammar).getTo()).append(AbstractJsonLexerKt.END_LIST).toString(), 0, false, 6, null);
                }
                throw new IllegalStateException(("Unsupported grammar element: " + grammar).toString());
            }
            StringBuilder expression = new StringBuilder();
            int currentOffset = shouldGroup ? offset + 1 : offset;
            Iterable $this$forEachIndexed$iv = ((ComplexGrammar) grammar).getGrammars();
            int index = 0;
            for (Object item$iv : $this$forEachIndexed$iv) {
                int index$iv = index + 1;
                if (index < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Grammar grammar2 = (Grammar) item$iv;
                GrammarRegex current = toRegex(grammar2, map, currentOffset, z2);
                if (index != 0) {
                    z = z2;
                    if (grammar instanceof OrGrammar) {
                        expression.append("|");
                    }
                } else {
                    z = z2;
                }
                expression.append(current.getRegex());
                currentOffset += current.getGroupsCount();
                index = index$iv;
                z2 = z;
            }
            int groupsCount = currentOffset - offset;
            if (shouldGroup) {
                groupsCount--;
            }
            String sb = expression.toString();
            Intrinsics.checkNotNullExpressionValue(sb, "expression.toString()");
            return new GrammarRegex(sb, groupsCount, shouldGroup);
        }
        GrammarRegex nested2 = toRegex$default(((NamedGrammar) grammar).getGrammar(), map, offset + 1, false, 4, null);
        add(map, ((NamedGrammar) grammar).getName(), offset);
        return new GrammarRegex(nested2.getRegex(), nested2.getGroupsCount(), true);
    }

    private static final void add(Map<String, List<Integer>> map, String key, int value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList());
        }
        Integer valueOf = Integer.valueOf(value);
        List<Integer> list = map.get(key);
        Intrinsics.checkNotNull(list);
        list.add(valueOf);
    }
}
