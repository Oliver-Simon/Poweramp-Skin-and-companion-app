package io.ktor.http;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: URLParser.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u001a(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a \u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002\u001a$\u0010\r\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002\u001a\u001c\u0010\u0010\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u000bH\u0002\u001a,\u0010\u0013\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0002\u001a$\u0010\u0015\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002\u001a$\u0010\u0016\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002\u001a$\u0010\u0017\u001a\u00020\u0006*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002\u001a\u0012\u0010\u0018\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u0002\u001a\u0014\u0010\u0019\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u0002H\u0000\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u001a"}, d2 = {"ROOT_PATH", "", "", "getROOT_PATH", "()Ljava/util/List;", "count", "", "urlString", "startIndex", "endIndex", "char", "", "findScheme", "fillHost", "", "Lio/ktor/http/URLBuilder;", "indexOfColonInHostPort", "isLetter", "", "parseFile", "slashCount", "parseFragment", "parseMailto", "parseQuery", "takeFrom", "takeFromUnsafe", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class URLParserKt {
    private static final List<String> ROOT_PATH = CollectionsKt.listOf("");

    public static final List<String> getROOT_PATH() {
        return ROOT_PATH;
    }

    public static final URLBuilder takeFrom(URLBuilder $this$takeFrom, String urlString) {
        Intrinsics.checkNotNullParameter($this$takeFrom, "<this>");
        Intrinsics.checkNotNullParameter(urlString, "urlString");
        if (StringsKt.isBlank(urlString)) {
            return $this$takeFrom;
        }
        try {
            return takeFromUnsafe($this$takeFrom, urlString);
        } catch (Throwable cause) {
            throw new URLParserException(urlString, cause);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0046, code lost:
    
        r7 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0036, code lost:
    
        r7 = r5;
        r5 = r5 - 1;
        r8 = r2.charAt(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0041, code lost:
    
        if (kotlin.text.CharsKt.isWhitespace(r8) != false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0044, code lost:
    
        if (r5 >= 0) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0047, code lost:
    
        r2 = true;
        r7 = r7 + 1;
        r3 = findScheme(r23, r4, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004f, code lost:
    
        if (r3 <= 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0051, code lost:
    
        r8 = r23.substring(r4, r4 + r3);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, "this as java.lang.String…ing(startIndex, endIndex)");
        r22.setProtocol(io.ktor.http.URLProtocol.INSTANCE.createOrDefault(r8));
        r4 = r4 + (r3 + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0066, code lost:
    
        r9 = count(r23, r4, r7, '/');
        r4 = r4 + r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x007b, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r22.getProtocol().getName(), "file") == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x007d, code lost:
    
        parseFile(r22, r23, r4, r7, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0080, code lost:
    
        return r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0090, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r22.getProtocol().getName(), "mailto") == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0092, code lost:
    
        if (r9 != 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0095, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0096, code lost:
    
        if (r2 == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0098, code lost:
    
        parseMailto(r22, r23, r4, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x009b, code lost:
    
        return r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a7, code lost:
    
        throw new java.lang.IllegalArgumentException("Failed requirement.".toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a8, code lost:
    
        r12 = null;
        r15 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ab, code lost:
    
        if (r9 < 2) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ae, code lost:
    
        r4 = java.lang.Integer.valueOf(kotlin.text.StringsKt.indexOfAny$default((java.lang.CharSequence) r23, io.ktor.util.CharsetKt.toCharArray("@/\\?#"), r15, false, 4, (java.lang.Object) null));
        r10 = r4.intValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00cd, code lost:
    
        if (r10 <= 0) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00cf, code lost:
    
        r10 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00d2, code lost:
    
        if (r10 == 0) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00d5, code lost:
    
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00d6, code lost:
    
        if (r4 == null) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00d8, code lost:
    
        r4 = r4.intValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00de, code lost:
    
        if (r4 >= r7) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00e6, code lost:
    
        if (r23.charAt(r4) != '@') goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00e8, code lost:
    
        r10 = indexOfColonInHostPort(r23, r15, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ec, code lost:
    
        if (r10 == (-1)) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ee, code lost:
    
        r13 = r23.substring(r15, r10);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, "this as java.lang.String…ing(startIndex, endIndex)");
        r22.setEncodedUser(r13);
        r13 = r23.substring(r10 + 1, r4);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, "this as java.lang.String…ing(startIndex, endIndex)");
        r22.setEncodedPassword(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x010f, code lost:
    
        r15 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0105, code lost:
    
        r13 = r23.substring(r15, r4);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, "this as java.lang.String…ing(startIndex, endIndex)");
        r22.setEncodedUser(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0112, code lost:
    
        fillHost(r22, r23, r15, r4);
        r15 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00dd, code lost:
    
        r4 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d1, code lost:
    
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0117, code lost:
    
        if (r15 < r7) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x011f, code lost:
    
        if (r23.charAt(r7 - 1) != '/') goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0121, code lost:
    
        r2 = io.ktor.http.URLParserKt.ROOT_PATH;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0128, code lost:
    
        r22.setEncodedPathSegments(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x012b, code lost:
    
        return r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0124, code lost:
    
        r2 = kotlin.collections.CollectionsKt.emptyList();
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x012c, code lost:
    
        if (r9 != 0) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x012e, code lost:
    
        r4 = kotlin.collections.CollectionsKt.dropLast(r22.getEncodedPathSegments(), 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x013b, code lost:
    
        r22.setEncodedPathSegments(r4);
        r4 = java.lang.Integer.valueOf(kotlin.text.StringsKt.indexOfAny$default((java.lang.CharSequence) r23, io.ktor.util.CharsetKt.toCharArray("?#"), r15, false, 4, (java.lang.Object) null));
        r6 = r4.intValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x015d, code lost:
    
        if (r6 <= 0) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x015f, code lost:
    
        r6 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0162, code lost:
    
        if (r6 == 0) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0164, code lost:
    
        r12 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0165, code lost:
    
        if (r12 == null) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0167, code lost:
    
        r4 = r12.intValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x016d, code lost:
    
        if (r4 <= r15) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x016f, code lost:
    
        r6 = r23.substring(r15, r4);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, "this as java.lang.String…ing(startIndex, endIndex)");
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x017f, code lost:
    
        if (r22.getEncodedPathSegments().size() != 1) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x018f, code lost:
    
        if (((java.lang.CharSequence) kotlin.collections.CollectionsKt.first((java.util.List) r22.getEncodedPathSegments())).length() != 0) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0191, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0194, code lost:
    
        if (r5 == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0196, code lost:
    
        r5 = kotlin.collections.CollectionsKt.emptyList();
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01a6, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r6, "/") == false) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01a8, code lost:
    
        r8 = io.ktor.http.URLParserKt.ROOT_PATH;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01c2, code lost:
    
        if (r9 != 1) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01c4, code lost:
    
        r2 = io.ktor.http.URLParserKt.ROOT_PATH;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01cb, code lost:
    
        r2 = kotlin.collections.CollectionsKt.plus((java.util.Collection) r2, (java.lang.Iterable) r8);
        r22.setEncodedPathSegments(kotlin.collections.CollectionsKt.plus((java.util.Collection) r5, (java.lang.Iterable) r2));
        r15 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01c7, code lost:
    
        r2 = kotlin.collections.CollectionsKt.emptyList();
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01ab, code lost:
    
        r8 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r6, new char[]{'/'}, false, 0, 6, (java.lang.Object) null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0193, code lost:
    
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x019b, code lost:
    
        r5 = r22.getEncodedPathSegments();
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01e2, code lost:
    
        if (r15 >= r7) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01ea, code lost:
    
        if (r23.charAt(r15) != '?') goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01ec, code lost:
    
        r15 = parseQuery(r22, r23, r15, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01f0, code lost:
    
        parseFragment(r22, r23, r15, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01f3, code lost:
    
        return r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x016c, code lost:
    
        r4 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0161, code lost:
    
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0137, code lost:
    
        r4 = kotlin.collections.CollectionsKt.emptyList();
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0034, code lost:
    
        if (r5 >= 0) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final io.ktor.http.URLBuilder takeFromUnsafe(io.ktor.http.URLBuilder r22, java.lang.String r23) {
        /*
            Method dump skipped, instructions count: 500
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.URLParserKt.takeFromUnsafe(io.ktor.http.URLBuilder, java.lang.String):io.ktor.http.URLBuilder");
    }

    private static final void parseFile(URLBuilder $this$parseFile, String urlString, int startIndex, int endIndex, int slashCount) {
        switch (slashCount) {
            case 2:
                int nextSlash = StringsKt.indexOf$default((CharSequence) urlString, '/', startIndex, false, 4, (Object) null);
                if (nextSlash == -1 || nextSlash == endIndex) {
                    String substring = urlString.substring(startIndex, endIndex);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    $this$parseFile.setHost(substring);
                    return;
                } else {
                    String substring2 = urlString.substring(startIndex, nextSlash);
                    Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                    $this$parseFile.setHost(substring2);
                    String substring3 = urlString.substring(nextSlash, endIndex);
                    Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
                    URLBuilderKt.setEncodedPath($this$parseFile, substring3);
                    return;
                }
            case 3:
                $this$parseFile.setHost("");
                StringBuilder append = new StringBuilder().append('/');
                String substring4 = urlString.substring(startIndex, endIndex);
                Intrinsics.checkNotNullExpressionValue(substring4, "this as java.lang.String…ing(startIndex, endIndex)");
                URLBuilderKt.setEncodedPath($this$parseFile, append.append(substring4).toString());
                return;
            default:
                throw new IllegalArgumentException("Invalid file url: " + urlString);
        }
    }

    private static final void parseMailto(URLBuilder $this$parseMailto, String urlString, int startIndex, int endIndex) {
        int delimiter = StringsKt.indexOf$default((CharSequence) urlString, "@", startIndex, false, 4, (Object) null);
        if (delimiter == -1) {
            throw new IllegalArgumentException("Invalid mailto url: " + urlString + ", it should contain '@'.");
        }
        String substring = urlString.substring(startIndex, delimiter);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        $this$parseMailto.setUser(CodecsKt.decodeURLPart$default(substring, 0, 0, null, 7, null));
        String substring2 = urlString.substring(delimiter + 1, endIndex);
        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
        $this$parseMailto.setHost(substring2);
    }

    private static final int parseQuery(final URLBuilder $this$parseQuery, String urlString, int startIndex, int endIndex) {
        if (startIndex + 1 == endIndex) {
            $this$parseQuery.setTrailingQuery(true);
            return endIndex;
        }
        Integer valueOf = Integer.valueOf(StringsKt.indexOf$default((CharSequence) urlString, '#', startIndex + 1, false, 4, (Object) null));
        int it = valueOf.intValue();
        if (!(it > 0)) {
            valueOf = null;
        }
        int fragmentStart = valueOf != null ? valueOf.intValue() : endIndex;
        String substring = urlString.substring(startIndex + 1, fragmentStart);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        Parameters rawParameters = QueryKt.parseQueryString$default(substring, 0, 0, false, 6, null);
        rawParameters.forEach(new Function2<String, List<? extends String>, Unit>() { // from class: io.ktor.http.URLParserKt$parseQuery$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, List<? extends String> list) {
                invoke2(str, (List<String>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String key, List<String> values) {
                Intrinsics.checkNotNullParameter(key, "key");
                Intrinsics.checkNotNullParameter(values, "values");
                URLBuilder.this.getEncodedParameters().appendAll(key, values);
            }
        });
        return fragmentStart;
    }

    private static final void parseFragment(URLBuilder $this$parseFragment, String urlString, int startIndex, int endIndex) {
        if (startIndex < endIndex && urlString.charAt(startIndex) == '#') {
            String substring = urlString.substring(startIndex + 1, endIndex);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            $this$parseFragment.setEncodedFragment(substring);
        }
    }

    private static final void fillHost(URLBuilder $this$fillHost, String urlString, int startIndex, int endIndex) {
        Integer valueOf = Integer.valueOf(indexOfColonInHostPort(urlString, startIndex, endIndex));
        int it = valueOf.intValue();
        if (!(it > 0)) {
            valueOf = null;
        }
        int colonIndex = valueOf != null ? valueOf.intValue() : endIndex;
        String substring = urlString.substring(startIndex, colonIndex);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        $this$fillHost.setHost(substring);
        if (colonIndex + 1 < endIndex) {
            String substring2 = urlString.substring(colonIndex + 1, endIndex);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
            $this$fillHost.setPort(Integer.parseInt(substring2));
            return;
        }
        $this$fillHost.setPort(0);
    }

    private static final int findScheme(String urlString, int startIndex, int endIndex) {
        int current = startIndex;
        int incorrectSchemePosition = -1;
        char firstChar = urlString.charAt(current);
        if (!('a' <= firstChar && firstChar < '{')) {
            if (!('A' <= firstChar && firstChar < '[')) {
                incorrectSchemePosition = current;
            }
        }
        while (current < endIndex) {
            char charAt = urlString.charAt(current);
            if (charAt == ':') {
                if (incorrectSchemePosition != -1) {
                    throw new IllegalArgumentException("Illegal character in scheme at position " + incorrectSchemePosition);
                }
                return current - startIndex;
            }
            if (charAt == '/' || charAt == '?' || charAt == '#') {
                return -1;
            }
            if (incorrectSchemePosition == -1) {
                if (!('a' <= charAt && charAt < '{')) {
                    if (!('A' <= charAt && charAt < '[')) {
                        if (!('0' <= charAt && charAt < ':') && charAt != '.' && charAt != '+' && charAt != '-') {
                            incorrectSchemePosition = current;
                        }
                    }
                }
            }
            current++;
        }
        return -1;
    }

    private static final int count(String urlString, int startIndex, int endIndex, char c) {
        int result = 0;
        while (startIndex + result < endIndex && urlString.charAt(startIndex + result) == c) {
            result++;
        }
        return result;
    }

    private static final int indexOfColonInHostPort(String $this$indexOfColonInHostPort, int startIndex, int endIndex) {
        boolean skip = false;
        for (int index = startIndex; index < endIndex; index++) {
            char charAt = $this$indexOfColonInHostPort.charAt(index);
            if (charAt == '[') {
                skip = true;
            } else if (charAt == ']') {
                skip = false;
            } else if (charAt == ':' && !skip) {
                return index;
            }
        }
        return -1;
    }

    private static final boolean isLetter(char $this$isLetter) {
        char lowerCase = Character.toLowerCase($this$isLetter);
        return 'a' <= lowerCase && lowerCase < '{';
    }
}
