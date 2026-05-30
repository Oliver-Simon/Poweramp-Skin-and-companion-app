package io.ktor.http;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: HeaderValueWithParameters.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u0003J\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lio/ktor/http/HeaderValueWithParameters;", "", "content", "", "parameters", "", "Lio/ktor/http/HeaderValueParam;", "(Ljava/lang/String;Ljava/util/List;)V", "getContent", "()Ljava/lang/String;", "getParameters", "()Ljava/util/List;", "parameter", "name", "toString", "Companion", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public abstract class HeaderValueWithParameters {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String content;
    private final List<HeaderValueParam> parameters;

    public HeaderValueWithParameters(String content, List<HeaderValueParam> parameters) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        this.content = content;
        this.parameters = parameters;
    }

    public /* synthetic */ HeaderValueWithParameters(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? CollectionsKt.emptyList() : list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String getContent() {
        return this.content;
    }

    public final List<HeaderValueParam> getParameters() {
        return this.parameters;
    }

    public final String parameter(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        int index = 0;
        int lastIndex = CollectionsKt.getLastIndex(this.parameters);
        if (0 > lastIndex) {
            return null;
        }
        while (true) {
            HeaderValueParam parameter = this.parameters.get(index);
            if (!StringsKt.equals(parameter.getName(), name, true)) {
                if (index == lastIndex) {
                    return null;
                }
                index++;
            } else {
                return parameter.getValue();
            }
        }
    }

    public String toString() {
        if (this.parameters.isEmpty()) {
            return this.content;
        }
        int length = this.content.length();
        int i = 0;
        for (HeaderValueParam it : this.parameters) {
            i += it.getName().length() + it.getValue().length() + 3;
        }
        int size = length + i;
        StringBuilder $this$toString_u24lambda_u241 = new StringBuilder(size);
        $this$toString_u24lambda_u241.append(this.content);
        int index = 0;
        int lastIndex = CollectionsKt.getLastIndex(this.parameters);
        if (0 <= lastIndex) {
            while (true) {
                HeaderValueParam element = this.parameters.get(index);
                $this$toString_u24lambda_u241.append("; ");
                $this$toString_u24lambda_u241.append(element.getName());
                $this$toString_u24lambda_u241.append("=");
                String $this$escapeIfNeededTo$iv = element.getValue();
                if (HeaderValueWithParametersKt.access$needQuotes($this$escapeIfNeededTo$iv)) {
                    $this$toString_u24lambda_u241.append(HeaderValueWithParametersKt.quote($this$escapeIfNeededTo$iv));
                } else {
                    $this$toString_u24lambda_u241.append($this$escapeIfNeededTo$iv);
                }
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        String sb = $this$toString_u24lambda_u241.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "{\n            val size =…   }.toString()\n        }");
        return sb;
    }

    /* compiled from: HeaderValueWithParameters.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J?\u0010\u0003\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u001e\u0010\u0007\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0004\u0012\u0002H\u00040\bH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\f"}, d2 = {"Lio/ktor/http/HeaderValueWithParameters$Companion;", "", "()V", "parse", "R", "value", "", "init", "Lkotlin/Function2;", "", "Lio/ktor/http/HeaderValueParam;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <R> R parse(String value, Function2<? super String, ? super List<HeaderValueParam>, ? extends R> init) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(init, "init");
            HeaderValue headerValue = (HeaderValue) CollectionsKt.last((List) HttpHeaderValueParserKt.parseHeaderValue(value));
            return init.invoke(headerValue.getValue(), headerValue.getParams());
        }
    }
}
