package io.ktor.http.auth;

import com.maxmpz.poweramp.player.RouterConsts;
import io.ktor.http.CodecsKt;
import io.ktor.http.HeaderValueParam;
import io.ktor.http.HeaderValueWithParametersKt;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.http.parsing.ParseException;
import io.ktor.util.Hash;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: HttpAuthHeader.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u000b2\u00020\u0001:\u0004\u000b\f\r\u000eB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\u000f\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/http/auth/HttpAuthHeader;", "", "authScheme", "", "(Ljava/lang/String;)V", "getAuthScheme", "()Ljava/lang/String;", "render", "encoding", "Lio/ktor/http/auth/HeaderValueEncoding;", "toString", "Companion", "Parameterized", "Parameters", "Single", "Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "Lio/ktor/http/auth/HttpAuthHeader$Single;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public abstract class HttpAuthHeader {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String authScheme;

    public /* synthetic */ HttpAuthHeader(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    public abstract String render();

    public abstract String render(HeaderValueEncoding encoding);

    private HttpAuthHeader(String authScheme) {
        Regex regex;
        this.authScheme = authScheme;
        String str = this.authScheme;
        regex = HttpAuthHeaderKt.token68Pattern;
        if (regex.matches(str)) {
        } else {
            throw new ParseException("Invalid authScheme value: it should be token, but instead it is " + this.authScheme, null, 2, null);
        }
    }

    public final String getAuthScheme() {
        return this.authScheme;
    }

    /* compiled from: HttpAuthHeader.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lio/ktor/http/auth/HttpAuthHeader$Single;", "Lio/ktor/http/auth/HttpAuthHeader;", "authScheme", "", "blob", "(Ljava/lang/String;Ljava/lang/String;)V", "getBlob", "()Ljava/lang/String;", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "", "hashCode", "", "render", "encoding", "Lio/ktor/http/auth/HeaderValueEncoding;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Single extends HttpAuthHeader {
        private final String blob;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Single(String authScheme, String blob) {
            super(authScheme, null);
            Regex regex;
            Intrinsics.checkNotNullParameter(authScheme, "authScheme");
            Intrinsics.checkNotNullParameter(blob, "blob");
            this.blob = blob;
            String str = this.blob;
            regex = HttpAuthHeaderKt.token68Pattern;
            if (regex.matches(str)) {
            } else {
                throw new ParseException("Invalid blob value: it should be token68", null, 2, null);
            }
        }

        public final String getBlob() {
            return this.blob;
        }

        @Override // io.ktor.http.auth.HttpAuthHeader
        public String render() {
            return getAuthScheme() + ' ' + this.blob;
        }

        @Override // io.ktor.http.auth.HttpAuthHeader
        public String render(HeaderValueEncoding encoding) {
            Intrinsics.checkNotNullParameter(encoding, "encoding");
            return render();
        }

        public boolean equals(Object other) {
            return (other instanceof Single) && StringsKt.equals(((Single) other).getAuthScheme(), getAuthScheme(), true) && StringsKt.equals(((Single) other).blob, this.blob, true);
        }

        public int hashCode() {
            Hash hash = Hash.INSTANCE;
            String lowerCase = getAuthScheme().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            String lowerCase2 = this.blob.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            return hash.combine(lowerCase, lowerCase2);
        }
    }

    /* compiled from: HttpAuthHeader.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B-\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0017\u001a\u00020\u0003J\b\u0010\u0018\u001a\u00020\u0003H\u0016J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0016\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003J\u0016\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003J\u0014\u0010\u001c\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "Lio/ktor/http/auth/HttpAuthHeader;", "authScheme", "", "parameters", "", "encoding", "Lio/ktor/http/auth/HeaderValueEncoding;", "(Ljava/lang/String;Ljava/util/Map;Lio/ktor/http/auth/HeaderValueEncoding;)V", "", "Lio/ktor/http/HeaderValueParam;", "(Ljava/lang/String;Ljava/util/List;Lio/ktor/http/auth/HeaderValueEncoding;)V", "getEncoding", "()Lio/ktor/http/auth/HeaderValueEncoding;", "getParameters", "()Ljava/util/List;", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "", "hashCode", "", "parameter", "name", "render", "withParameter", "value", "withReplacedParameter", "encode", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Parameterized extends HttpAuthHeader {
        private final HeaderValueEncoding encoding;
        private final List<HeaderValueParam> parameters;

        /* compiled from: HttpAuthHeader.kt */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* loaded from: classes9.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[HeaderValueEncoding.values().length];
                try {
                    iArr[HeaderValueEncoding.QUOTED_WHEN_REQUIRED.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[HeaderValueEncoding.QUOTED_ALWAYS.ordinal()] = 2;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[HeaderValueEncoding.URI_ENCODE.ordinal()] = 3;
                } catch (NoSuchFieldError e3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Parameterized(String str, List list, HeaderValueEncoding headerValueEncoding, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (List<HeaderValueParam>) list, (i & 4) != 0 ? HeaderValueEncoding.QUOTED_WHEN_REQUIRED : headerValueEncoding);
        }

        public final List<HeaderValueParam> getParameters() {
            return this.parameters;
        }

        public final HeaderValueEncoding getEncoding() {
            return this.encoding;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Parameterized(String authScheme, List<HeaderValueParam> parameters, HeaderValueEncoding encoding) {
            super(authScheme, null);
            Regex regex;
            Intrinsics.checkNotNullParameter(authScheme, "authScheme");
            Intrinsics.checkNotNullParameter(parameters, "parameters");
            Intrinsics.checkNotNullParameter(encoding, "encoding");
            this.parameters = parameters;
            this.encoding = encoding;
            Iterable $this$forEach$iv = this.parameters;
            for (Object element$iv : $this$forEach$iv) {
                HeaderValueParam it = (HeaderValueParam) element$iv;
                String name = it.getName();
                regex = HttpAuthHeaderKt.token68Pattern;
                if (!regex.matches(name)) {
                    throw new ParseException("Parameter name should be a token", null, 2, null);
                }
            }
        }

        public /* synthetic */ Parameterized(String str, Map map, HeaderValueEncoding headerValueEncoding, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (Map<String, String>) map, (i & 4) != 0 ? HeaderValueEncoding.QUOTED_WHEN_REQUIRED : headerValueEncoding);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public Parameterized(java.lang.String r13, java.util.Map<java.lang.String, java.lang.String> r14, io.ktor.http.auth.HeaderValueEncoding r15) {
            /*
                r12 = this;
                java.lang.String r0 = "authScheme"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
                java.lang.String r0 = "parameters"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
                java.lang.String r0 = "encoding"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
                java.util.Set r0 = r14.entrySet()
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                r1 = 0
                java.util.ArrayList r2 = new java.util.ArrayList
                r3 = 10
                int r3 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r3)
                r2.<init>(r3)
                java.util.Collection r2 = (java.util.Collection) r2
                r3 = r0
                r4 = 0
                java.util.Iterator r5 = r3.iterator()
            L29:
                boolean r6 = r5.hasNext()
                if (r6 == 0) goto L4c
                java.lang.Object r6 = r5.next()
                r7 = r6
                java.util.Map$Entry r7 = (java.util.Map.Entry) r7
                r8 = 0
                io.ktor.http.HeaderValueParam r9 = new io.ktor.http.HeaderValueParam
                java.lang.Object r10 = r7.getKey()
                java.lang.String r10 = (java.lang.String) r10
                java.lang.Object r11 = r7.getValue()
                java.lang.String r11 = (java.lang.String) r11
                r9.<init>(r10, r11)
                r2.add(r9)
                goto L29
            L4c:
                java.util.List r2 = (java.util.List) r2
                r12.<init>(r13, r2, r15)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.auth.HttpAuthHeader.Parameterized.<init>(java.lang.String, java.util.Map, io.ktor.http.auth.HeaderValueEncoding):void");
        }

        public final Parameterized withParameter(String name, String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            return new Parameterized(getAuthScheme(), (List<HeaderValueParam>) CollectionsKt.plus((Collection<? extends HeaderValueParam>) this.parameters, new HeaderValueParam(name, value)), this.encoding);
        }

        public final Parameterized withReplacedParameter(String name, String value) {
            HeaderValueParam headerValueParam;
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            List $this$indexOfFirst$iv = this.parameters;
            int index$iv = 0;
            Iterator<HeaderValueParam> it = $this$indexOfFirst$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    index$iv = -1;
                    break;
                }
                Object item$iv = it.next();
                if (Intrinsics.areEqual(((HeaderValueParam) item$iv).getName(), name)) {
                    break;
                }
                index$iv++;
            }
            if (index$iv == -1) {
                return withParameter(name, value);
            }
            boolean replaced = false;
            Iterable $this$mapNotNull$iv = this.parameters;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                HeaderValueParam it2 = (HeaderValueParam) element$iv$iv$iv;
                boolean replaced2 = replaced;
                if (!Intrinsics.areEqual(it2.getName(), name)) {
                    headerValueParam = it2;
                } else if (replaced2) {
                    headerValueParam = null;
                } else {
                    replaced2 = true;
                    headerValueParam = new HeaderValueParam(name, value);
                }
                if (headerValueParam != null) {
                    destination$iv$iv.add(headerValueParam);
                }
                replaced = replaced2;
            }
            List newParameters = (List) destination$iv$iv;
            return new Parameterized(getAuthScheme(), (List<HeaderValueParam>) newParameters, this.encoding);
        }

        @Override // io.ktor.http.auth.HttpAuthHeader
        public String render(final HeaderValueEncoding encoding) {
            Intrinsics.checkNotNullParameter(encoding, "encoding");
            if (this.parameters.isEmpty()) {
                return getAuthScheme();
            }
            return CollectionsKt.joinToString$default(this.parameters, ", ", getAuthScheme() + ' ', null, 0, null, new Function1<HeaderValueParam, CharSequence>() { // from class: io.ktor.http.auth.HttpAuthHeader$Parameterized$render$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final CharSequence invoke(HeaderValueParam it) {
                    String encode;
                    Intrinsics.checkNotNullParameter(it, "it");
                    StringBuilder append = new StringBuilder().append(it.getName()).append('=');
                    encode = HttpAuthHeader.Parameterized.this.encode(it.getValue(), encoding);
                    return append.append(encode).toString();
                }
            }, 28, null);
        }

        public final String parameter(String name) {
            Object element$iv;
            Intrinsics.checkNotNullParameter(name, "name");
            Iterable $this$firstOrNull$iv = this.parameters;
            Iterator it = $this$firstOrNull$iv.iterator();
            while (true) {
                if (it.hasNext()) {
                    element$iv = it.next();
                    HeaderValueParam it2 = (HeaderValueParam) element$iv;
                    if (Intrinsics.areEqual(it2.getName(), name)) {
                        break;
                    }
                } else {
                    element$iv = null;
                    break;
                }
            }
            HeaderValueParam headerValueParam = (HeaderValueParam) element$iv;
            if (headerValueParam != null) {
                return headerValueParam.getValue();
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String encode(String $this$encode, HeaderValueEncoding encoding) {
            switch (WhenMappings.$EnumSwitchMapping$0[encoding.ordinal()]) {
                case 1:
                    return HeaderValueWithParametersKt.escapeIfNeeded($this$encode);
                case 2:
                    return HeaderValueWithParametersKt.quote($this$encode);
                case 3:
                    return CodecsKt.encodeURLParameter$default($this$encode, false, 1, null);
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }

        @Override // io.ktor.http.auth.HttpAuthHeader
        public String render() {
            return render(this.encoding);
        }

        public boolean equals(Object other) {
            return (other instanceof Parameterized) && StringsKt.equals(((Parameterized) other).getAuthScheme(), getAuthScheme(), true) && Intrinsics.areEqual(((Parameterized) other).parameters, this.parameters);
        }

        public int hashCode() {
            Hash hash = Hash.INSTANCE;
            String lowerCase = getAuthScheme().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            return hash.combine(lowerCase, this.parameters);
        }
    }

    public String toString() {
        return render();
    }

    /* compiled from: HttpAuthHeader.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\tJ\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006JO\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u00062\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0006¢\u0006\u0002\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/ktor/http/auth/HttpAuthHeader$Companion;", "", "()V", "basicAuthChallenge", "Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", Parameters.Realm, "", Parameters.Charset, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "bearerAuthChallenge", "Lio/ktor/http/auth/HttpAuthHeader;", "scheme", "digestAuthChallenge", "nonce", "domain", "", "opaque", "stale", "", "algorithm", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Parameterized basicAuthChallenge(String realm, Charset charset) {
            Intrinsics.checkNotNullParameter(realm, "realm");
            LinkedHashMap $this$basicAuthChallenge_u24lambda_u240 = new LinkedHashMap();
            $this$basicAuthChallenge_u24lambda_u240.put(Parameters.Realm, realm);
            if (charset != null) {
                $this$basicAuthChallenge_u24lambda_u240.put(Parameters.Charset, CharsetJVMKt.getName(charset));
            }
            return new Parameterized(AuthScheme.Basic, $this$basicAuthChallenge_u24lambda_u240, (HeaderValueEncoding) null, 4, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ HttpAuthHeader bearerAuthChallenge$default(Companion companion, String str, String str2, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = null;
            }
            return companion.bearerAuthChallenge(str, str2);
        }

        public final HttpAuthHeader bearerAuthChallenge(String scheme, String realm) {
            Intrinsics.checkNotNullParameter(scheme, "scheme");
            return new Parameterized(scheme, realm == null ? MapsKt.emptyMap() : MapsKt.mapOf(TuplesKt.to(Parameters.Realm, realm)), (HeaderValueEncoding) null, 4, (DefaultConstructorMarker) null);
        }

        public final Parameterized digestAuthChallenge(String realm, String nonce, List<String> domain, String opaque, Boolean stale, String algorithm) {
            Intrinsics.checkNotNullParameter(realm, "realm");
            Intrinsics.checkNotNullParameter(nonce, "nonce");
            Intrinsics.checkNotNullParameter(domain, "domain");
            Intrinsics.checkNotNullParameter(algorithm, "algorithm");
            LinkedHashMap $this$digestAuthChallenge_u24lambda_u241 = new LinkedHashMap();
            $this$digestAuthChallenge_u24lambda_u241.put(Parameters.Realm, realm);
            $this$digestAuthChallenge_u24lambda_u241.put("nonce", nonce);
            if (!domain.isEmpty()) {
                $this$digestAuthChallenge_u24lambda_u241.put("domain", CollectionsKt.joinToString$default(domain, " ", null, null, 0, null, null, 62, null));
            }
            if (opaque != null) {
                $this$digestAuthChallenge_u24lambda_u241.put("opaque", opaque);
            }
            if (stale != null) {
                $this$digestAuthChallenge_u24lambda_u241.put("stale", stale.toString());
            }
            $this$digestAuthChallenge_u24lambda_u241.put("algorithm", algorithm);
            return new Parameterized(AuthScheme.Digest, $this$digestAuthChallenge_u24lambda_u241, HeaderValueEncoding.QUOTED_ALWAYS);
        }
    }

    /* compiled from: HttpAuthHeader.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lio/ktor/http/auth/HttpAuthHeader$Parameters;", "", "()V", "Charset", "", "OAuthCallback", "OAuthCallbackConfirmed", "OAuthConsumerKey", "OAuthNonce", "OAuthSignature", "OAuthSignatureMethod", "OAuthTimestamp", "OAuthToken", "OAuthTokenSecret", "OAuthVerifier", "OAuthVersion", "Realm", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Parameters {
        public static final String Charset = "charset";
        public static final Parameters INSTANCE = new Parameters();
        public static final String OAuthCallback = "oauth_callback";
        public static final String OAuthCallbackConfirmed = "oauth_callback_confirmed";
        public static final String OAuthConsumerKey = "oauth_consumer_key";
        public static final String OAuthNonce = "oauth_nonce";
        public static final String OAuthSignature = "oauth_signature";
        public static final String OAuthSignatureMethod = "oauth_signature_method";
        public static final String OAuthTimestamp = "oauth_timestamp";
        public static final String OAuthToken = "oauth_token";
        public static final String OAuthTokenSecret = "oauth_token_secret";
        public static final String OAuthVerifier = "oauth_verifier";
        public static final String OAuthVersion = "oauth_version";
        public static final String Realm = "realm";

        private Parameters() {
        }
    }
}
