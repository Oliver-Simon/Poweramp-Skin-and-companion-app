package io.ktor.http;

import io.ktor.http.Parameters;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: HttpUrlEncoded.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00040\u0003\u001a\u0016\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\n\u0010\u0007\u001a\u00060\bj\u0002`\t\u001a\u0018\u0010\u0005\u001a\u00020\u0006*\u00020\n2\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0000\u001a*\u0010\u0005\u001a\u00020\u0006*\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00040\u00032\n\u0010\u0007\u001a\u00060\bj\u0002`\t\u001a0\u0010\u0005\u001a\u00020\u0006*\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00030\f0\u000b2\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0000\u001a\"\u0010\r\u001a\u00020\u0002*\u00020\u00012\f\b\u0002\u0010\u000e\u001a\u00060\u000fj\u0002`\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012¨\u0006\u0013"}, d2 = {"formUrlEncode", "", "Lio/ktor/http/Parameters;", "", "Lkotlin/Pair;", "formUrlEncodeTo", "", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "Lio/ktor/http/ParametersBuilder;", "", "", "parseUrlEncodedParameters", "defaultEncoding", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "limit", "", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpUrlEncodedKt {
    public static /* synthetic */ Parameters parseUrlEncodedParameters$default(String str, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = 1000;
        }
        return parseUrlEncodedParameters(str, charset, i);
    }

    public static final Parameters parseUrlEncodedParameters(String $this$parseUrlEncodedParameters, Charset defaultEncoding, int limit) {
        Object element$iv;
        String encoding;
        Intrinsics.checkNotNullParameter($this$parseUrlEncodedParameters, "<this>");
        Intrinsics.checkNotNullParameter(defaultEncoding, "defaultEncoding");
        Iterable $this$map$iv = StringsKt.split$default((CharSequence) $this$parseUrlEncodedParameters, new String[]{"&"}, false, limit, 2, (Object) null);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(TuplesKt.to(StringsKt.substringBefore$default(it, "=", (String) null, 2, (Object) null), StringsKt.substringAfter(it, "=", "")));
        }
        Iterable parameters = (List) destination$iv$iv;
        Iterable $this$firstOrNull$iv = parameters;
        Iterator it2 = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it2.hasNext()) {
                element$iv = it2.next();
                if (Intrinsics.areEqual(((Pair) element$iv).getFirst(), "_charset_")) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        Pair pair = (Pair) element$iv;
        if (pair == null || (encoding = (String) pair.getSecond()) == null) {
            encoding = CharsetJVMKt.getName(defaultEncoding);
        }
        Charset charset = Charset.forName(encoding);
        Parameters.Companion companion = Parameters.INSTANCE;
        ParametersBuilder $this$parseUrlEncodedParameters_u24lambda_u243 = ParametersKt.ParametersBuilder$default(0, 1, null);
        Iterable $this$forEach$iv = parameters;
        for (Object element$iv2 : $this$forEach$iv) {
            Pair pair2 = (Pair) element$iv2;
            String key = (String) pair2.component1();
            String value = (String) pair2.component2();
            Intrinsics.checkNotNullExpressionValue(charset, "charset");
            Charset charset2 = charset;
            $this$parseUrlEncodedParameters_u24lambda_u243.append(CodecsKt.decodeURLQueryComponent$default(key, 0, 0, false, charset2, 7, null), CodecsKt.decodeURLQueryComponent$default(value, 0, 0, false, charset2, 7, null));
            charset = charset2;
        }
        return $this$parseUrlEncodedParameters_u24lambda_u243.build();
    }

    public static final String formUrlEncode(List<Pair<String, String>> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        StringBuilder $this$formUrlEncode_u24lambda_u244 = new StringBuilder();
        formUrlEncodeTo(list, $this$formUrlEncode_u24lambda_u244);
        String sb = $this$formUrlEncode_u24lambda_u244.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    public static final void formUrlEncodeTo(List<Pair<String, String>> list, Appendable out) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        CollectionsKt.joinTo(list, out, (r14 & 2) != 0 ? ", " : "&", (r14 & 4) != 0 ? "" : null, (r14 & 8) != 0 ? "" : null, (r14 & 16) != 0 ? -1 : 0, (r14 & 32) != 0 ? "..." : null, (r14 & 64) != 0 ? null : new Function1<Pair<? extends String, ? extends String>, CharSequence>() { // from class: io.ktor.http.HttpUrlEncodedKt$formUrlEncodeTo$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Pair<? extends String, ? extends String> pair) {
                return invoke2((Pair<String, String>) pair);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final CharSequence invoke2(Pair<String, String> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                String key = CodecsKt.encodeURLParameter(it.getFirst(), true);
                if (it.getSecond() == null) {
                    return key;
                }
                String value = CodecsKt.encodeURLParameterValue(String.valueOf(it.getSecond()));
                return key + '=' + value;
            }
        });
    }

    public static final String formUrlEncode(Parameters $this$formUrlEncode) {
        Intrinsics.checkNotNullParameter($this$formUrlEncode, "<this>");
        Iterable $this$flatMap$iv = $this$formUrlEncode.entries();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            Map.Entry e = (Map.Entry) element$iv$iv;
            Iterable $this$map$iv = (Iterable) e.getValue();
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Iterable $this$flatMap$iv2 = $this$flatMap$iv;
                String it = (String) item$iv$iv;
                destination$iv$iv2.add(TuplesKt.to(e.getKey(), it));
                $this$flatMap$iv = $this$flatMap$iv2;
            }
            Iterable $this$flatMap$iv3 = $this$flatMap$iv;
            Iterable list$iv$iv = (List) destination$iv$iv2;
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
            $this$flatMap$iv = $this$flatMap$iv3;
        }
        return formUrlEncode((List<Pair<String, String>>) destination$iv$iv);
    }

    public static final void formUrlEncodeTo(Parameters $this$formUrlEncodeTo, Appendable out) {
        Intrinsics.checkNotNullParameter($this$formUrlEncodeTo, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        formUrlEncodeTo($this$formUrlEncodeTo.entries(), out);
    }

    public static final void formUrlEncodeTo(ParametersBuilder $this$formUrlEncodeTo, Appendable out) {
        Intrinsics.checkNotNullParameter($this$formUrlEncodeTo, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        formUrlEncodeTo($this$formUrlEncodeTo.entries(), out);
    }

    public static final void formUrlEncodeTo(Set<? extends Map.Entry<String, ? extends List<String>>> set, Appendable out) {
        Iterable $this$map$iv;
        Intrinsics.checkNotNullParameter(set, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        Set<? extends Map.Entry<String, ? extends List<String>>> $this$flatMap$iv = set;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            Map.Entry entry = (Map.Entry) element$iv$iv;
            String key = (String) entry.getKey();
            List value = (List) entry.getValue();
            if (value.isEmpty()) {
                $this$map$iv = CollectionsKt.listOf(TuplesKt.to(key, null));
            } else {
                List $this$map$iv2 = value;
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                for (Object item$iv$iv : $this$map$iv2) {
                    String it = (String) item$iv$iv;
                    destination$iv$iv2.add(TuplesKt.to(key, it));
                }
                $this$map$iv = (List) destination$iv$iv2;
            }
            Iterable list$iv$iv = $this$map$iv;
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        formUrlEncodeTo((List<Pair<String, String>>) destination$iv$iv, out);
    }
}
