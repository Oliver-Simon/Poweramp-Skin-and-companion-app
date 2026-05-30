package io.ktor.http;

import androidx.core.app.FrameMetricsAggregator;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: URLBuilder.kt */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u000e\u001a\u00020\u0004*\u00020\u00042\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0010\"\u00020\u0003¢\u0006\u0002\u0010\u0011\u001a\u0018\u0010\u000e\u001a\u00020\u0004*\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013\u001a \u0010\u0014\u001a\u00020\u0015*\u00060\u0016j\u0002`\u00172\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H\u0002\u001a \u0010\u0019\u001a\u00020\u0015*\u00060\u0016j\u0002`\u00172\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0003H\u0002\u001a%\u0010\u001b\u001a\u00020\u0004*\u00020\u00042\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0010\"\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0011\u001a-\u0010\u001b\u001a\u00020\u0004*\u00020\u00042\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0010\"\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u001d¢\u0006\u0002\u0010\u001e\u001a\u001a\u0010\u001b\u001a\u00020\u0004*\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013H\u0007\u001a\"\u0010\u001b\u001a\u00020\u0004*\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00132\b\b\u0002\u0010\u001c\u001a\u00020\u001d\u001a'\u0010\u001f\u001a\u0002H \"\f\b\u0000\u0010 *\u00060\u0016j\u0002`\u0017*\u00020\u00042\u0006\u0010!\u001a\u0002H H\u0002¢\u0006\u0002\u0010\"\u001a\n\u0010#\u001a\u00020\u0004*\u00020\u0004\u001a\u0012\u0010$\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\u00030\u0013H\u0002\u001a#\u0010%\u001a\u00020\u0015*\u00020\u00042\u0012\u0010%\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0010\"\u00020\u0003¢\u0006\u0002\u0010&\u001a%\u0010'\u001a\u00020\u0004*\u00020\u00042\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0010\"\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0011\u001a\u001a\u0010'\u001a\u00020\u0004*\u00020\u00042\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013H\u0007\u001aZ\u0010(\u001a\u00020\u0015*\u00020\u00042\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00032\u0019\b\u0002\u0010+\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00150,¢\u0006\u0002\b-¢\u0006\u0002\u0010.\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"(\u0010\b\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0006\"\u0004\b\n\u0010\u000b\"\u0018\u0010\f\u001a\u00020\u0003*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0006¨\u0006/"}, d2 = {"DEFAULT_PORT", "", "authority", "", "Lio/ktor/http/URLBuilder;", "getAuthority", "(Lio/ktor/http/URLBuilder;)Ljava/lang/String;", "value", "encodedPath", "getEncodedPath", "setEncodedPath", "(Lio/ktor/http/URLBuilder;Ljava/lang/String;)V", "encodedUserAndPassword", "getEncodedUserAndPassword", "appendEncodedPathSegments", "components", "", "(Lio/ktor/http/URLBuilder;[Ljava/lang/String;)Lio/ktor/http/URLBuilder;", "segments", "", "appendFile", "", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "host", "appendMailto", "encodedUser", "appendPathSegments", "encodeSlash", "", "(Lio/ktor/http/URLBuilder;[Ljava/lang/String;Z)Lio/ktor/http/URLBuilder;", "appendTo", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "out", "(Lio/ktor/http/URLBuilder;Ljava/lang/Appendable;)Ljava/lang/Appendable;", "clone", "joinPath", "path", "(Lio/ktor/http/URLBuilder;[Ljava/lang/String;)V", "pathComponents", "set", "scheme", "port", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/http/URLBuilder;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class URLBuilderKt {
    public static final int DEFAULT_PORT = 0;

    public static final <A extends Appendable> A appendTo(URLBuilder $this$appendTo, A a) {
        a.append($this$appendTo.getProtocol().getName());
        String name = $this$appendTo.getProtocol().getName();
        if (Intrinsics.areEqual(name, "file")) {
            appendFile(a, $this$appendTo.getHost(), getEncodedPath($this$appendTo));
            return a;
        }
        if (Intrinsics.areEqual(name, "mailto")) {
            appendMailto(a, getEncodedUserAndPassword($this$appendTo), $this$appendTo.getHost());
            return a;
        }
        a.append("://");
        a.append(getAuthority($this$appendTo));
        URLUtilsKt.appendUrlFullPath(a, getEncodedPath($this$appendTo), $this$appendTo.getEncodedParameters(), $this$appendTo.getTrailingQuery());
        if ($this$appendTo.getEncodedFragment().length() > 0) {
            a.append('#');
            a.append($this$appendTo.getEncodedFragment());
        }
        return a;
    }

    private static final void appendMailto(Appendable $this$appendMailto, String encodedUser, String host) {
        $this$appendMailto.append(":");
        $this$appendMailto.append(encodedUser);
        $this$appendMailto.append(host);
    }

    private static final void appendFile(Appendable $this$appendFile, String host, String encodedPath) {
        $this$appendFile.append("://");
        $this$appendFile.append(host);
        if (!StringsKt.startsWith$default((CharSequence) encodedPath, '/', false, 2, (Object) null)) {
            $this$appendFile.append('/');
        }
        $this$appendFile.append(encodedPath);
    }

    public static final URLBuilder clone(URLBuilder $this$clone) {
        Intrinsics.checkNotNullParameter($this$clone, "<this>");
        return URLUtilsKt.takeFrom(new URLBuilder(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null), $this$clone);
    }

    public static final String getEncodedUserAndPassword(URLBuilder $this$encodedUserAndPassword) {
        Intrinsics.checkNotNullParameter($this$encodedUserAndPassword, "<this>");
        StringBuilder $this$_get_encodedUserAndPassword__u24lambda_u240 = new StringBuilder();
        URLUtilsKt.appendUserAndPassword($this$_get_encodedUserAndPassword__u24lambda_u240, $this$encodedUserAndPassword.getEncodedUser(), $this$encodedUserAndPassword.getEncodedPassword());
        String sb = $this$_get_encodedUserAndPassword__u24lambda_u240.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    public static /* synthetic */ URLBuilder appendPathSegments$default(URLBuilder uRLBuilder, List list, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return appendPathSegments(uRLBuilder, (List<String>) list, z);
    }

    public static final URLBuilder appendPathSegments(URLBuilder $this$appendPathSegments, List<String> segments, boolean encodeSlash) {
        Collection destination$iv$iv;
        Intrinsics.checkNotNullParameter($this$appendPathSegments, "<this>");
        Intrinsics.checkNotNullParameter(segments, "segments");
        if (encodeSlash) {
            destination$iv$iv = segments;
        } else {
            List<String> $this$flatMap$iv = segments;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv : $this$flatMap$iv) {
                String it = (String) element$iv$iv;
                Iterable list$iv$iv = StringsKt.split$default((CharSequence) it, new char[]{'/'}, false, 0, 6, (Object) null);
                CollectionsKt.addAll(destination$iv$iv2, list$iv$iv);
            }
            destination$iv$iv = (List) destination$iv$iv2;
        }
        Collection $this$map$iv = destination$iv$iv;
        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it2 = (String) item$iv$iv;
            destination$iv$iv3.add(CodecsKt.encodeURLPathPart(it2));
        }
        List encodedSegments = (List) destination$iv$iv3;
        appendEncodedPathSegments($this$appendPathSegments, (List<String>) encodedSegments);
        return $this$appendPathSegments;
    }

    public static /* synthetic */ URLBuilder appendPathSegments$default(URLBuilder uRLBuilder, String[] strArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return appendPathSegments(uRLBuilder, strArr, z);
    }

    public static final URLBuilder appendPathSegments(URLBuilder $this$appendPathSegments, String[] components, boolean encodeSlash) {
        Intrinsics.checkNotNullParameter($this$appendPathSegments, "<this>");
        Intrinsics.checkNotNullParameter(components, "components");
        return appendPathSegments($this$appendPathSegments, (List<String>) ArraysKt.toList(components), encodeSlash);
    }

    public static final void path(URLBuilder $this$path, String... path) {
        Intrinsics.checkNotNullParameter($this$path, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        Collection destination$iv$iv = new ArrayList(path.length);
        for (String str : path) {
            String it = CodecsKt.encodeURLPath(str);
            destination$iv$iv.add(it);
        }
        $this$path.setEncodedPathSegments((List) destination$iv$iv);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final io.ktor.http.URLBuilder appendEncodedPathSegments(io.ktor.http.URLBuilder r5, java.util.List<java.lang.String> r6) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "segments"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.util.List r0 = r5.getEncodedPathSegments()
            int r0 = r0.size()
            r1 = 0
            r2 = 1
            if (r0 <= r2) goto L36
            java.util.List r0 = r5.getEncodedPathSegments()
            java.lang.Object r0 = kotlin.collections.CollectionsKt.last(r0)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L28
            r0 = r2
            goto L29
        L28:
            r0 = r1
        L29:
            if (r0 == 0) goto L36
            r0 = r6
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L36
            r0 = r2
            goto L37
        L36:
            r0 = r1
        L37:
            int r3 = r6.size()
            if (r3 <= r2) goto L5c
            java.lang.Object r3 = kotlin.collections.CollectionsKt.first(r6)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            int r3 = r3.length()
            if (r3 != 0) goto L4c
            r3 = r2
            goto L4d
        L4c:
            r3 = r1
        L4d:
            if (r3 == 0) goto L5c
            java.util.List r3 = r5.getEncodedPathSegments()
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L5c
            r1 = r2
        L5c:
            if (r0 == 0) goto L7a
            if (r1 == 0) goto L7a
            java.util.List r3 = r5.getEncodedPathSegments()
            java.util.List r3 = kotlin.collections.CollectionsKt.dropLast(r3, r2)
            java.util.Collection r3 = (java.util.Collection) r3
            r4 = r6
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.List r2 = kotlin.collections.CollectionsKt.drop(r4, r2)
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.List r2 = kotlin.collections.CollectionsKt.plus(r3, r2)
            goto Lb1
        L7a:
            if (r0 == 0) goto L8e
            java.util.List r3 = r5.getEncodedPathSegments()
            java.util.List r2 = kotlin.collections.CollectionsKt.dropLast(r3, r2)
            java.util.Collection r2 = (java.util.Collection) r2
            r3 = r6
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.List r2 = kotlin.collections.CollectionsKt.plus(r2, r3)
            goto Lb1
        L8e:
            if (r1 == 0) goto La4
            java.util.List r3 = r5.getEncodedPathSegments()
            java.util.Collection r3 = (java.util.Collection) r3
            r4 = r6
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.List r2 = kotlin.collections.CollectionsKt.drop(r4, r2)
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.List r2 = kotlin.collections.CollectionsKt.plus(r3, r2)
            goto Lb1
        La4:
            java.util.List r2 = r5.getEncodedPathSegments()
            java.util.Collection r2 = (java.util.Collection) r2
            r3 = r6
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.List r2 = kotlin.collections.CollectionsKt.plus(r2, r3)
        Lb1:
            r5.setEncodedPathSegments(r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.URLBuilderKt.appendEncodedPathSegments(io.ktor.http.URLBuilder, java.util.List):io.ktor.http.URLBuilder");
    }

    public static final URLBuilder appendEncodedPathSegments(URLBuilder $this$appendEncodedPathSegments, String... components) {
        Intrinsics.checkNotNullParameter($this$appendEncodedPathSegments, "<this>");
        Intrinsics.checkNotNullParameter(components, "components");
        return appendEncodedPathSegments($this$appendEncodedPathSegments, (List<String>) ArraysKt.toList(components));
    }

    public static final String getAuthority(URLBuilder $this$authority) {
        Intrinsics.checkNotNullParameter($this$authority, "<this>");
        StringBuilder $this$_get_authority__u24lambda_u244 = new StringBuilder();
        $this$_get_authority__u24lambda_u244.append(getEncodedUserAndPassword($this$authority));
        $this$_get_authority__u24lambda_u244.append($this$authority.getHost());
        if ($this$authority.getPort() != 0 && $this$authority.getPort() != $this$authority.getProtocol().getDefaultPort()) {
            $this$_get_authority__u24lambda_u244.append(":");
            $this$_get_authority__u24lambda_u244.append(String.valueOf($this$authority.getPort()));
        }
        String sb = $this$_get_authority__u24lambda_u244.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    public static final String getEncodedPath(URLBuilder $this$encodedPath) {
        Intrinsics.checkNotNullParameter($this$encodedPath, "<this>");
        return joinPath($this$encodedPath.getEncodedPathSegments());
    }

    public static final void setEncodedPath(URLBuilder $this$encodedPath, String value) {
        List<String> root_path;
        Intrinsics.checkNotNullParameter($this$encodedPath, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        if (StringsKt.isBlank(value)) {
            root_path = CollectionsKt.emptyList();
        } else {
            root_path = Intrinsics.areEqual(value, "/") ? URLParserKt.getROOT_PATH() : CollectionsKt.toMutableList((Collection) StringsKt.split$default((CharSequence) value, new char[]{'/'}, false, 0, 6, (Object) null));
        }
        $this$encodedPath.setEncodedPathSegments(root_path);
    }

    private static final String joinPath(List<String> list) {
        if (list.isEmpty()) {
            return "";
        }
        if (list.size() == 1) {
            return ((CharSequence) CollectionsKt.first((List) list)).length() == 0 ? "/" : (String) CollectionsKt.first((List) list);
        }
        return CollectionsKt.joinToString$default(list, "/", null, null, 0, null, null, 62, null);
    }

    public static /* synthetic */ void set$default(URLBuilder uRLBuilder, String str, String str2, Integer num, String str3, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            num = null;
        }
        if ((i & 8) != 0) {
            str3 = null;
        }
        if ((i & 16) != 0) {
            function1 = new Function1<URLBuilder, Unit>() { // from class: io.ktor.http.URLBuilderKt$set$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(URLBuilder uRLBuilder2) {
                    invoke2(uRLBuilder2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(URLBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        set(uRLBuilder, str, str2, num, str3, function1);
    }

    public static final void set(URLBuilder $this$set, String scheme, String host, Integer port, String path, Function1<? super URLBuilder, Unit> block) {
        Intrinsics.checkNotNullParameter($this$set, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        if (scheme != null) {
            $this$set.setProtocol(URLProtocol.INSTANCE.createOrDefault(scheme));
        }
        if (host != null) {
            $this$set.setHost(host);
        }
        if (port != null) {
            $this$set.setPort(port.intValue());
        }
        if (path != null) {
            setEncodedPath($this$set, path);
        }
        block.invoke($this$set);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Plesae use method with boolean parameter")
    public static final /* synthetic */ URLBuilder appendPathSegments(URLBuilder $this$appendPathSegments, List segments) {
        Intrinsics.checkNotNullParameter($this$appendPathSegments, "<this>");
        Intrinsics.checkNotNullParameter(segments, "segments");
        return appendPathSegments($this$appendPathSegments, (List<String>) segments, false);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Plesae use method with boolean parameter")
    public static final /* synthetic */ URLBuilder appendPathSegments(URLBuilder $this$appendPathSegments, String... components) {
        Intrinsics.checkNotNullParameter($this$appendPathSegments, "<this>");
        Intrinsics.checkNotNullParameter(components, "components");
        return appendPathSegments($this$appendPathSegments, (List<String>) ArraysKt.toList(components), false);
    }

    @Deprecated(message = "Please use appendPathSegments method", replaceWith = @ReplaceWith(expression = "this.appendPathSegments(components", imports = {}))
    public static final URLBuilder pathComponents(URLBuilder $this$pathComponents, String... components) {
        Intrinsics.checkNotNullParameter($this$pathComponents, "<this>");
        Intrinsics.checkNotNullParameter(components, "components");
        return appendPathSegments$default($this$pathComponents, ArraysKt.toList(components), false, 2, (Object) null);
    }

    @Deprecated(message = "Please use appendPathSegments method", replaceWith = @ReplaceWith(expression = "this.appendPathSegments(components", imports = {}))
    public static final URLBuilder pathComponents(URLBuilder $this$pathComponents, List<String> components) {
        Intrinsics.checkNotNullParameter($this$pathComponents, "<this>");
        Intrinsics.checkNotNullParameter(components, "components");
        return appendPathSegments$default($this$pathComponents, (List) components, false, 2, (Object) null);
    }
}
