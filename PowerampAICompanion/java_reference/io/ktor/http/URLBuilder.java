package io.ktor.http;

import androidx.core.app.FrameMetricsAggregator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: URLBuilder.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 G2\u00020\u0001:\u0001GBi\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\b\u0010A\u001a\u00020BH\u0002J\u0006\u0010C\u001a\u00020DJ\u0006\u0010E\u001a\u00020\u0005J\b\u0010F\u001a\u00020\u0005H\u0016R\u001a\u0010\u0012\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0018@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010\u0016R \u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010&\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0014\"\u0004\b(\u0010\u0016R$\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b)\u0010\u0014\"\u0004\b*\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0014\"\u0004\b,\u0010\u0016R\u001e\u0010\f\u001a\u00020\u00182\u0006\u0010-\u001a\u00020\u0018@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001bR(\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b/\u0010\u0014\"\u0004\b0\u0010\u0016R0\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b1\u0010#\"\u0004\b2\u0010%R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R(\u0010\b\u001a\u0004\u0018\u00010\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b?\u0010\u0014\"\u0004\b@\u0010\u0016¨\u0006H"}, d2 = {"Lio/ktor/http/URLBuilder;", "", "protocol", "Lio/ktor/http/URLProtocol;", "host", "", "port", "", "user", "password", "pathSegments", "", "parameters", "Lio/ktor/http/Parameters;", "fragment", "trailingQuery", "", "(Lio/ktor/http/URLProtocol;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/util/List;Lio/ktor/http/Parameters;Ljava/lang/String;Z)V", "encodedFragment", "getEncodedFragment", "()Ljava/lang/String;", "setEncodedFragment", "(Ljava/lang/String;)V", "value", "Lio/ktor/http/ParametersBuilder;", "encodedParameters", "getEncodedParameters", "()Lio/ktor/http/ParametersBuilder;", "setEncodedParameters", "(Lio/ktor/http/ParametersBuilder;)V", "encodedPassword", "getEncodedPassword", "setEncodedPassword", "encodedPathSegments", "getEncodedPathSegments", "()Ljava/util/List;", "setEncodedPathSegments", "(Ljava/util/List;)V", "encodedUser", "getEncodedUser", "setEncodedUser", "getFragment", "setFragment", "getHost", "setHost", "<set-?>", "getParameters", "getPassword", "setPassword", "getPathSegments", "setPathSegments", "getPort", "()I", "setPort", "(I)V", "getProtocol", "()Lio/ktor/http/URLProtocol;", "setProtocol", "(Lio/ktor/http/URLProtocol;)V", "getTrailingQuery", "()Z", "setTrailingQuery", "(Z)V", "getUser", "setUser", "applyOrigin", "", "build", "Lio/ktor/http/Url;", "buildString", "toString", "Companion", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class URLBuilder {
    private static final Url originUrl = URLUtilsKt.Url(URLBuilderJvmKt.getOrigin(INSTANCE));
    private String encodedFragment;
    private ParametersBuilder encodedParameters;
    private String encodedPassword;
    private List<String> encodedPathSegments;
    private String encodedUser;
    private String host;
    private ParametersBuilder parameters;
    private int port;
    private URLProtocol protocol;
    private boolean trailingQuery;

    public URLBuilder() {
        this(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    public URLBuilder(URLProtocol protocol, String host, int port, String user, String password, List<String> pathSegments, Parameters parameters, String fragment, boolean trailingQuery) {
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.trailingQuery = trailingQuery;
        this.encodedUser = user != null ? CodecsKt.encodeURLParameter$default(user, false, 1, null) : null;
        this.encodedPassword = password != null ? CodecsKt.encodeURLParameter$default(password, false, 1, null) : null;
        this.encodedFragment = CodecsKt.encodeURLQueryComponent$default(fragment, false, false, null, 7, null);
        List<String> $this$map$iv = pathSegments;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(CodecsKt.encodeURLPathPart(it));
        }
        this.encodedPathSegments = (List) destination$iv$iv;
        this.encodedParameters = UrlDecodedParametersBuilderKt.encodeParameters(parameters);
        this.parameters = new UrlDecodedParametersBuilder(this.encodedParameters);
    }

    public /* synthetic */ URLBuilder(URLProtocol uRLProtocol, String str, int i, String str2, String str3, List list, Parameters parameters, String str4, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? URLProtocol.INSTANCE.getHTTP() : uRLProtocol, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? null : str2, (i2 & 16) != 0 ? null : str3, (i2 & 32) != 0 ? CollectionsKt.emptyList() : list, (i2 & 64) != 0 ? Parameters.INSTANCE.getEmpty() : parameters, (i2 & 128) != 0 ? "" : str4, (i2 & 256) != 0 ? false : z);
    }

    public final URLProtocol getProtocol() {
        return this.protocol;
    }

    public final void setProtocol(URLProtocol uRLProtocol) {
        Intrinsics.checkNotNullParameter(uRLProtocol, "<set-?>");
        this.protocol = uRLProtocol;
    }

    public final String getHost() {
        return this.host;
    }

    public final void setHost(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.host = str;
    }

    public final int getPort() {
        return this.port;
    }

    public final void setPort(int i) {
        this.port = i;
    }

    public final boolean getTrailingQuery() {
        return this.trailingQuery;
    }

    public final void setTrailingQuery(boolean z) {
        this.trailingQuery = z;
    }

    public final String getEncodedUser() {
        return this.encodedUser;
    }

    public final void setEncodedUser(String str) {
        this.encodedUser = str;
    }

    public final String getUser() {
        String str = this.encodedUser;
        if (str != null) {
            return CodecsKt.decodeURLPart$default(str, 0, 0, null, 7, null);
        }
        return null;
    }

    public final void setUser(String value) {
        this.encodedUser = value != null ? CodecsKt.encodeURLParameter$default(value, false, 1, null) : null;
    }

    public final String getEncodedPassword() {
        return this.encodedPassword;
    }

    public final void setEncodedPassword(String str) {
        this.encodedPassword = str;
    }

    public final String getPassword() {
        String str = this.encodedPassword;
        if (str != null) {
            return CodecsKt.decodeURLPart$default(str, 0, 0, null, 7, null);
        }
        return null;
    }

    public final void setPassword(String value) {
        this.encodedPassword = value != null ? CodecsKt.encodeURLParameter$default(value, false, 1, null) : null;
    }

    public final String getEncodedFragment() {
        return this.encodedFragment;
    }

    public final void setEncodedFragment(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.encodedFragment = str;
    }

    public final String getFragment() {
        return CodecsKt.decodeURLQueryComponent$default(this.encodedFragment, 0, 0, false, null, 15, null);
    }

    public final void setFragment(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.encodedFragment = CodecsKt.encodeURLQueryComponent$default(value, false, false, null, 7, null);
    }

    public final List<String> getEncodedPathSegments() {
        return this.encodedPathSegments;
    }

    public final void setEncodedPathSegments(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.encodedPathSegments = list;
    }

    public final List<String> getPathSegments() {
        Iterable $this$map$iv = this.encodedPathSegments;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(CodecsKt.decodeURLPart$default(it, 0, 0, null, 7, null));
        }
        return (List) destination$iv$iv;
    }

    public final void setPathSegments(List<String> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        List<String> $this$map$iv = value;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(CodecsKt.encodeURLPathPart(it));
        }
        this.encodedPathSegments = (List) destination$iv$iv;
    }

    public final ParametersBuilder getEncodedParameters() {
        return this.encodedParameters;
    }

    public final void setEncodedParameters(ParametersBuilder value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.encodedParameters = value;
        this.parameters = new UrlDecodedParametersBuilder(value);
    }

    public final ParametersBuilder getParameters() {
        return this.parameters;
    }

    public final String buildString() {
        applyOrigin();
        String sb = ((StringBuilder) URLBuilderKt.access$appendTo(this, new StringBuilder(256))).toString();
        Intrinsics.checkNotNullExpressionValue(sb, "appendTo(StringBuilder(256)).toString()");
        return sb;
    }

    public String toString() {
        String sb = ((StringBuilder) URLBuilderKt.access$appendTo(this, new StringBuilder(256))).toString();
        Intrinsics.checkNotNullExpressionValue(sb, "appendTo(StringBuilder(256)).toString()");
        return sb;
    }

    public final Url build() {
        applyOrigin();
        return new Url(this.protocol, this.host, this.port, getPathSegments(), this.parameters.build(), getFragment(), getUser(), getPassword(), this.trailingQuery, buildString());
    }

    private final void applyOrigin() {
        if ((this.host.length() > 0) || Intrinsics.areEqual(this.protocol.getName(), "file")) {
            return;
        }
        this.host = originUrl.getHost();
        if (Intrinsics.areEqual(this.protocol, URLProtocol.INSTANCE.getHTTP())) {
            this.protocol = originUrl.getProtocol();
        }
        if (this.port == 0) {
            this.port = originUrl.getSpecifiedPort();
        }
    }
}
