package io.ktor.http.parsing.regex;

import io.ktor.http.parsing.ParseResult;
import io.ktor.http.parsing.Parser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;

/* compiled from: RegexParser.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\f\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lio/ktor/http/parsing/regex/RegexParser;", "Lio/ktor/http/parsing/Parser;", "expression", "Lkotlin/text/Regex;", "indexes", "", "", "", "", "(Lkotlin/text/Regex;Ljava/util/Map;)V", "match", "", "input", "parse", "Lio/ktor/http/parsing/ParseResult;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class RegexParser implements Parser {
    private final Regex expression;
    private final Map<String, List<Integer>> indexes;

    /* JADX WARN: Multi-variable type inference failed */
    public RegexParser(Regex expression, Map<String, ? extends List<Integer>> indexes) {
        Intrinsics.checkNotNullParameter(expression, "expression");
        Intrinsics.checkNotNullParameter(indexes, "indexes");
        this.expression = expression;
        this.indexes = indexes;
    }

    @Override // io.ktor.http.parsing.Parser
    public ParseResult parse(String input) {
        MatchResult match;
        Intrinsics.checkNotNullParameter(input, "input");
        MatchResult match2 = this.expression.matchEntire(input);
        if (match2 != null && match2.getValue().length() == input.length()) {
            Map mapping = new LinkedHashMap();
            Map $this$forEach$iv = this.indexes;
            for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
                String key = element$iv.getKey();
                List<Integer> locations = element$iv.getValue();
                List<Integer> $this$forEach$iv2 = locations;
                Iterator it = $this$forEach$iv2.iterator();
                while (it.hasNext()) {
                    int index = ((Number) it.next()).intValue();
                    ArrayList arrayList = new ArrayList();
                    MatchGroup it2 = match2.getGroups().get(index);
                    if (it2 != null) {
                        match = match2;
                        arrayList.add(it2.getValue());
                    } else {
                        match = match2;
                    }
                    if (!arrayList.isEmpty()) {
                        mapping.put(key, arrayList);
                    }
                    match2 = match;
                }
            }
            return new ParseResult(mapping);
        }
        return null;
    }

    @Override // io.ktor.http.parsing.Parser
    public boolean match(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return this.expression.matches(input);
    }
}
