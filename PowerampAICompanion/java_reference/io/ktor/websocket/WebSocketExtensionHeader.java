package io.ktor.websocket;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* compiled from: WebSocketExtensionHeader.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\u0003H\u0002J\u0018\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e0\rJ\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lio/ktor/websocket/WebSocketExtensionHeader;", "", "name", "", "parameters", "", "(Ljava/lang/String;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getParameters", "()Ljava/util/List;", "parametersToString", "parseParameters", "Lkotlin/sequences/Sequence;", "Lkotlin/Pair;", "toString", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class WebSocketExtensionHeader {
    private final String name;
    private final List<String> parameters;

    public WebSocketExtensionHeader(String name, List<String> parameters) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        this.name = name;
        this.parameters = parameters;
    }

    public final String getName() {
        return this.name;
    }

    public final List<String> getParameters() {
        return this.parameters;
    }

    public final Sequence<Pair<String, String>> parseParameters() {
        return SequencesKt.map(CollectionsKt.asSequence(this.parameters), new Function1<String, Pair<? extends String, ? extends String>>() { // from class: io.ktor.websocket.WebSocketExtensionHeader$parseParameters$1
            @Override // kotlin.jvm.functions.Function1
            public final Pair<String, String> invoke(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                int equalsIndex = StringsKt.indexOf$default((CharSequence) it, '=', 0, false, 6, (Object) null);
                String value = "";
                if (equalsIndex < 0) {
                    return TuplesKt.to(it, "");
                }
                String key = StringsKt.substring(it, RangesKt.until(0, equalsIndex));
                if (equalsIndex + 1 < it.length()) {
                    value = it.substring(equalsIndex + 1);
                    Intrinsics.checkNotNullExpressionValue(value, "this as java.lang.String).substring(startIndex)");
                }
                return TuplesKt.to(key, value);
            }
        });
    }

    public String toString() {
        return this.name + ' ' + parametersToString();
    }

    private final String parametersToString() {
        return this.parameters.isEmpty() ? "" : ", " + CollectionsKt.joinToString$default(this.parameters, ",", null, null, 0, null, null, 62, null);
    }
}
