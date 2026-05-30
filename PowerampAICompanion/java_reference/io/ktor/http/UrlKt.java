package io.ktor.http;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Url.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u001aj\u0010\t\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006\u0017"}, d2 = {"authority", "", "Lio/ktor/http/Url;", "getAuthority", "(Lio/ktor/http/Url;)Ljava/lang/String;", "encodedUserAndPassword", "getEncodedUserAndPassword", "protocolWithAuthority", "getProtocolWithAuthority", "copy", "protocol", "Lio/ktor/http/URLProtocol;", "host", "specifiedPort", "", "encodedPath", "parameters", "Lio/ktor/http/Parameters;", "fragment", "user", "password", "trailingQuery", "", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class UrlKt {
    public static /* synthetic */ Url copy$default(Url url, URLProtocol uRLProtocol, String str, int i, String str2, Parameters parameters, String str3, String str4, String str5, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            uRLProtocol = url.getProtocol();
        }
        if ((i2 & 2) != 0) {
            str = url.getHost();
        }
        if ((i2 & 4) != 0) {
            i = url.getSpecifiedPort();
        }
        if ((i2 & 8) != 0) {
            str2 = url.getEncodedPath();
        }
        if ((i2 & 16) != 0) {
            parameters = url.getParameters();
        }
        if ((i2 & 32) != 0) {
            str3 = url.getFragment();
        }
        if ((i2 & 64) != 0) {
            str4 = url.getUser();
        }
        if ((i2 & 128) != 0) {
            str5 = url.getPassword();
        }
        boolean trailingQuery = (i2 & 256) != 0 ? url.getTrailingQuery() : z;
        String str6 = str4;
        String str7 = str5;
        return copy(url, uRLProtocol, str, i, str2, parameters, str3, str6, str7, trailingQuery);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Url is not a data class anymore. Please use URLBuilder(url)", replaceWith = @ReplaceWith(expression = "URLBuilder(this)", imports = {}))
    public static final Url copy(Url $this$copy, URLProtocol protocol, String host, int specifiedPort, String encodedPath, Parameters parameters, String fragment, String user, String password, boolean trailingQuery) {
        Intrinsics.checkNotNullParameter($this$copy, "<this>");
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(encodedPath, "encodedPath");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        throw new IllegalStateException("Please use URLBuilder(url)".toString());
    }

    public static final String getAuthority(Url $this$authority) {
        Intrinsics.checkNotNullParameter($this$authority, "<this>");
        StringBuilder $this$_get_authority__u24lambda_u240 = new StringBuilder();
        $this$_get_authority__u24lambda_u240.append(getEncodedUserAndPassword($this$authority));
        if ($this$authority.getSpecifiedPort() == 0 || $this$authority.getSpecifiedPort() == $this$authority.getProtocol().getDefaultPort()) {
            $this$_get_authority__u24lambda_u240.append($this$authority.getHost());
        } else {
            $this$_get_authority__u24lambda_u240.append(URLUtilsKt.getHostWithPort($this$authority));
        }
        String sb = $this$_get_authority__u24lambda_u240.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    public static final String getProtocolWithAuthority(Url $this$protocolWithAuthority) {
        Intrinsics.checkNotNullParameter($this$protocolWithAuthority, "<this>");
        StringBuilder $this$_get_protocolWithAuthority__u24lambda_u241 = new StringBuilder();
        $this$_get_protocolWithAuthority__u24lambda_u241.append($this$protocolWithAuthority.getProtocol().getName());
        $this$_get_protocolWithAuthority__u24lambda_u241.append("://");
        $this$_get_protocolWithAuthority__u24lambda_u241.append(getEncodedUserAndPassword($this$protocolWithAuthority));
        if ($this$protocolWithAuthority.getSpecifiedPort() == 0 || $this$protocolWithAuthority.getSpecifiedPort() == $this$protocolWithAuthority.getProtocol().getDefaultPort()) {
            $this$_get_protocolWithAuthority__u24lambda_u241.append($this$protocolWithAuthority.getHost());
        } else {
            $this$_get_protocolWithAuthority__u24lambda_u241.append(URLUtilsKt.getHostWithPort($this$protocolWithAuthority));
        }
        String sb = $this$_get_protocolWithAuthority__u24lambda_u241.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    public static final String getEncodedUserAndPassword(Url $this$encodedUserAndPassword) {
        Intrinsics.checkNotNullParameter($this$encodedUserAndPassword, "<this>");
        StringBuilder $this$_get_encodedUserAndPassword__u24lambda_u242 = new StringBuilder();
        URLUtilsKt.appendUserAndPassword($this$_get_encodedUserAndPassword__u24lambda_u242, $this$encodedUserAndPassword.getEncodedUser(), $this$encodedUserAndPassword.getEncodedPassword());
        String sb = $this$_get_encodedUserAndPassword__u24lambda_u242.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }
}
