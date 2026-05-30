package io.ktor.http;

import java.util.Arrays;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: HttpHeaders.kt */
@Metadata(d1 = {"\u00005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0003\b§\u0001\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b!\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010Ô\u0001\u001a\u00030Õ\u00012\u0007\u0010Ö\u0001\u001a\u00020\u0004J\u0011\u0010×\u0001\u001a\u00030Õ\u00012\u0007\u0010Ø\u0001\u001a\u00020\u0004J\u0011\u0010Ù\u0001\u001a\u00030Ú\u00012\u0007\u0010Û\u0001\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0014\u0010\u001d\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u0014\u0010\u001f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0014\u0010!\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006R\u0014\u0010#\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0006R\u0014\u0010%\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006R\u0014\u0010'\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0006R\u0014\u0010)\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0006R\u0014\u0010+\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0006R\u0014\u0010-\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0006R\u0014\u0010/\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0006R\u0014\u00101\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0006R\u0014\u00103\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0006R\u0014\u00105\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0006R\u0014\u00107\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0006R\u0014\u00109\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0006R\u0014\u0010;\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0006R\u0014\u0010=\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0006R\u0014\u0010?\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0006R\u0014\u0010A\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0006R\u0014\u0010C\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\u0006R\u0014\u0010E\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0006R\u0014\u0010G\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\u0006R\u0014\u0010I\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\u0006R\u0014\u0010K\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bL\u0010\u0006R\u0014\u0010M\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bN\u0010\u0006R\u0014\u0010O\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bP\u0010\u0006R\u0014\u0010Q\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\u0006R\u0014\u0010S\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bT\u0010\u0006R\u0014\u0010U\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bV\u0010\u0006R\u0014\u0010W\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bX\u0010\u0006R\u0014\u0010Y\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\u0006R\u0014\u0010[\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010\u0006R\u0014\u0010]\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\u0006R\u0014\u0010_\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b`\u0010\u0006R\u0014\u0010a\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\u0006R\u0014\u0010c\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\u0006R\u0014\u0010e\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bf\u0010\u0006R\u0014\u0010g\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bh\u0010\u0006R\u0014\u0010i\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bj\u0010\u0006R\u0014\u0010k\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bl\u0010\u0006R\u0014\u0010m\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bn\u0010\u0006R\u0014\u0010o\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bp\u0010\u0006R\u0014\u0010q\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\br\u0010\u0006R\u0014\u0010s\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bt\u0010\u0006R\u0014\u0010u\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bv\u0010\u0006R\u0014\u0010w\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bx\u0010\u0006R\u0014\u0010y\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bz\u0010\u0006R\u0014\u0010{\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b|\u0010\u0006R\u0014\u0010}\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b~\u0010\u0006R\u0015\u0010\u007f\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0080\u0001\u0010\u0006R\u0016\u0010\u0081\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0082\u0001\u0010\u0006R\u0016\u0010\u0083\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0084\u0001\u0010\u0006R\u0016\u0010\u0085\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0086\u0001\u0010\u0006R\u0016\u0010\u0087\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0088\u0001\u0010\u0006R\u0016\u0010\u0089\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u008a\u0001\u0010\u0006R\u0016\u0010\u008b\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u008c\u0001\u0010\u0006R\u0016\u0010\u008d\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u008e\u0001\u0010\u0006R\u0016\u0010\u008f\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0090\u0001\u0010\u0006R\u0016\u0010\u0091\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0092\u0001\u0010\u0006R\u0016\u0010\u0093\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0094\u0001\u0010\u0006R\u0016\u0010\u0095\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0096\u0001\u0010\u0006R\u0016\u0010\u0097\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0098\u0001\u0010\u0006R\u0016\u0010\u0099\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u009a\u0001\u0010\u0006R\u0016\u0010\u009b\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u009c\u0001\u0010\u0006R\u0016\u0010\u009d\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u009e\u0001\u0010\u0006R\u0016\u0010\u009f\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b \u0001\u0010\u0006R\u0016\u0010¡\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¢\u0001\u0010\u0006R\u0016\u0010£\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¤\u0001\u0010\u0006R\u0016\u0010¥\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¦\u0001\u0010\u0006R\u0016\u0010§\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¨\u0001\u0010\u0006R\u0016\u0010©\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bª\u0001\u0010\u0006R%\u0010«\u0001\u001a\t\u0012\u0004\u0012\u00020\u00040¬\u00018FX\u0087\u0004¢\u0006\u000f\u0012\u0005\b\u00ad\u0001\u0010\u0002\u001a\u0006\b®\u0001\u0010¯\u0001R\u0019\u0010°\u0001\u001a\t\u0012\u0004\u0012\u00020\u00040¬\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010±\u0001R\u001b\u0010²\u0001\u001a\t\u0012\u0004\u0012\u00020\u00040³\u0001¢\u0006\n\n\u0000\u001a\u0006\b´\u0001\u0010µ\u0001R\u0016\u0010¶\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b·\u0001\u0010\u0006R\u0016\u0010¸\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¹\u0001\u0010\u0006R\u0016\u0010º\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b»\u0001\u0010\u0006R\u0016\u0010¼\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b½\u0001\u0010\u0006R\u0016\u0010¾\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¿\u0001\u0010\u0006R\u0016\u0010À\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÁ\u0001\u0010\u0006R\u0016\u0010Â\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÃ\u0001\u0010\u0006R\u0016\u0010Ä\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÅ\u0001\u0010\u0006R\u0016\u0010Æ\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÇ\u0001\u0010\u0006R\u0016\u0010È\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÉ\u0001\u0010\u0006R\u0016\u0010Ê\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bË\u0001\u0010\u0006R\u0016\u0010Ì\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÍ\u0001\u0010\u0006R\u0016\u0010Î\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÏ\u0001\u0010\u0006R\u0016\u0010Ð\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÑ\u0001\u0010\u0006R\u0016\u0010Ò\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÓ\u0001\u0010\u0006¨\u0006Ü\u0001"}, d2 = {"Lio/ktor/http/HttpHeaders;", "", "()V", "ALPN", "", "getALPN", "()Ljava/lang/String;", com.google.common.net.HttpHeaders.ACCEPT, "getAccept", "AcceptCharset", "getAcceptCharset", "AcceptEncoding", "getAcceptEncoding", "AcceptLanguage", "getAcceptLanguage", "AcceptRanges", "getAcceptRanges", "AccessControlAllowCredentials", "getAccessControlAllowCredentials", "AccessControlAllowHeaders", "getAccessControlAllowHeaders", "AccessControlAllowMethods", "getAccessControlAllowMethods", "AccessControlAllowOrigin", "getAccessControlAllowOrigin", "AccessControlExposeHeaders", "getAccessControlExposeHeaders", "AccessControlMaxAge", "getAccessControlMaxAge", "AccessControlRequestHeaders", "getAccessControlRequestHeaders", "AccessControlRequestMethod", "getAccessControlRequestMethod", com.google.common.net.HttpHeaders.AGE, "getAge", com.google.common.net.HttpHeaders.ALLOW, "getAllow", "AuthenticationInfo", "getAuthenticationInfo", com.google.common.net.HttpHeaders.AUTHORIZATION, "getAuthorization", "CacheControl", "getCacheControl", com.google.common.net.HttpHeaders.CONNECTION, "getConnection", "ContentDisposition", "getContentDisposition", "ContentEncoding", "getContentEncoding", "ContentLanguage", "getContentLanguage", "ContentLength", "getContentLength", "ContentLocation", "getContentLocation", "ContentRange", "getContentRange", "ContentType", "getContentType", com.google.common.net.HttpHeaders.COOKIE, "getCookie", "DASL", "getDASL", "DAV", "getDAV", com.google.common.net.HttpHeaders.DATE, "getDate", "Depth", "getDepth", "Destination", "getDestination", com.google.common.net.HttpHeaders.ETAG, "getETag", com.google.common.net.HttpHeaders.EXPECT, "getExpect", com.google.common.net.HttpHeaders.EXPIRES, "getExpires", com.google.common.net.HttpHeaders.FORWARDED, "getForwarded", com.google.common.net.HttpHeaders.FROM, "getFrom", "HTTP2Settings", "getHTTP2Settings", com.google.common.net.HttpHeaders.HOST, "getHost", "If", "getIf", "IfMatch", "getIfMatch", "IfModifiedSince", "getIfModifiedSince", "IfNoneMatch", "getIfNoneMatch", "IfRange", "getIfRange", "IfScheduleTagMatch", "getIfScheduleTagMatch", "IfUnmodifiedSince", "getIfUnmodifiedSince", "LastModified", "getLastModified", com.google.common.net.HttpHeaders.LINK, "getLink", com.google.common.net.HttpHeaders.LOCATION, "getLocation", "LockToken", "getLockToken", "MIMEVersion", "getMIMEVersion", "MaxForwards", "getMaxForwards", "OrderingType", "getOrderingType", com.google.common.net.HttpHeaders.ORIGIN, "getOrigin", "Overwrite", "getOverwrite", "Position", "getPosition", com.google.common.net.HttpHeaders.PRAGMA, "getPragma", "Prefer", "getPrefer", "PreferenceApplied", "getPreferenceApplied", "ProxyAuthenticate", "getProxyAuthenticate", "ProxyAuthenticationInfo", "getProxyAuthenticationInfo", "ProxyAuthorization", "getProxyAuthorization", "PublicKeyPins", "getPublicKeyPins", "PublicKeyPinsReportOnly", "getPublicKeyPinsReportOnly", com.google.common.net.HttpHeaders.RANGE, "getRange", "Referrer", "getReferrer", "RetryAfter", "getRetryAfter", "SLUG", "getSLUG", "ScheduleReply", "getScheduleReply", "ScheduleTag", "getScheduleTag", "SecWebSocketAccept", "getSecWebSocketAccept", "SecWebSocketExtensions", "getSecWebSocketExtensions", "SecWebSocketKey", "getSecWebSocketKey", "SecWebSocketProtocol", "getSecWebSocketProtocol", "SecWebSocketVersion", "getSecWebSocketVersion", com.google.common.net.HttpHeaders.SERVER, "getServer", "SetCookie", "getSetCookie", "StrictTransportSecurity", "getStrictTransportSecurity", com.google.common.net.HttpHeaders.TE, "getTE", "Timeout", "getTimeout", com.google.common.net.HttpHeaders.TRAILER, "getTrailer", "TransferEncoding", "getTransferEncoding", "UnsafeHeaders", "", "getUnsafeHeaders$annotations", "getUnsafeHeaders", "()[Ljava/lang/String;", "UnsafeHeadersArray", "[Ljava/lang/String;", "UnsafeHeadersList", "", "getUnsafeHeadersList", "()Ljava/util/List;", com.google.common.net.HttpHeaders.UPGRADE, "getUpgrade", "UserAgent", "getUserAgent", com.google.common.net.HttpHeaders.VARY, "getVary", com.google.common.net.HttpHeaders.VIA, "getVia", "WWWAuthenticate", "getWWWAuthenticate", com.google.common.net.HttpHeaders.WARNING, "getWarning", "XCorrelationId", "getXCorrelationId", "XForwardedFor", "getXForwardedFor", "XForwardedHost", "getXForwardedHost", "XForwardedPort", "getXForwardedPort", "XForwardedProto", "getXForwardedProto", "XForwardedServer", "getXForwardedServer", "XHttpMethodOverride", "getXHttpMethodOverride", "XRequestId", "getXRequestId", "XTotalCount", "getXTotalCount", "checkHeaderName", "", "name", "checkHeaderValue", "value", "isUnsafe", "", "header", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpHeaders {
    public static final HttpHeaders INSTANCE = new HttpHeaders();
    private static final String Accept = com.google.common.net.HttpHeaders.ACCEPT;
    private static final String AcceptCharset = com.google.common.net.HttpHeaders.ACCEPT_CHARSET;
    private static final String AcceptEncoding = com.google.common.net.HttpHeaders.ACCEPT_ENCODING;
    private static final String AcceptLanguage = com.google.common.net.HttpHeaders.ACCEPT_LANGUAGE;
    private static final String AcceptRanges = com.google.common.net.HttpHeaders.ACCEPT_RANGES;
    private static final String Age = com.google.common.net.HttpHeaders.AGE;
    private static final String Allow = com.google.common.net.HttpHeaders.ALLOW;
    private static final String ALPN = "ALPN";
    private static final String AuthenticationInfo = "Authentication-Info";
    private static final String Authorization = com.google.common.net.HttpHeaders.AUTHORIZATION;
    private static final String CacheControl = com.google.common.net.HttpHeaders.CACHE_CONTROL;
    private static final String Connection = com.google.common.net.HttpHeaders.CONNECTION;
    private static final String ContentDisposition = com.google.common.net.HttpHeaders.CONTENT_DISPOSITION;
    private static final String ContentEncoding = com.google.common.net.HttpHeaders.CONTENT_ENCODING;
    private static final String ContentLanguage = com.google.common.net.HttpHeaders.CONTENT_LANGUAGE;
    private static final String ContentLength = com.google.common.net.HttpHeaders.CONTENT_LENGTH;
    private static final String ContentLocation = com.google.common.net.HttpHeaders.CONTENT_LOCATION;
    private static final String ContentRange = com.google.common.net.HttpHeaders.CONTENT_RANGE;
    private static final String ContentType = com.google.common.net.HttpHeaders.CONTENT_TYPE;
    private static final String Cookie = com.google.common.net.HttpHeaders.COOKIE;
    private static final String DASL = "DASL";
    private static final String Date = com.google.common.net.HttpHeaders.DATE;
    private static final String DAV = "DAV";
    private static final String Depth = "Depth";
    private static final String Destination = "Destination";
    private static final String ETag = com.google.common.net.HttpHeaders.ETAG;
    private static final String Expect = com.google.common.net.HttpHeaders.EXPECT;
    private static final String Expires = com.google.common.net.HttpHeaders.EXPIRES;
    private static final String From = com.google.common.net.HttpHeaders.FROM;
    private static final String Forwarded = com.google.common.net.HttpHeaders.FORWARDED;
    private static final String Host = com.google.common.net.HttpHeaders.HOST;
    private static final String HTTP2Settings = com.google.common.net.HttpHeaders.HTTP2_SETTINGS;
    private static final String If = "If";
    private static final String IfMatch = com.google.common.net.HttpHeaders.IF_MATCH;
    private static final String IfModifiedSince = com.google.common.net.HttpHeaders.IF_MODIFIED_SINCE;
    private static final String IfNoneMatch = com.google.common.net.HttpHeaders.IF_NONE_MATCH;
    private static final String IfRange = com.google.common.net.HttpHeaders.IF_RANGE;
    private static final String IfScheduleTagMatch = "If-Schedule-Tag-Match";
    private static final String IfUnmodifiedSince = com.google.common.net.HttpHeaders.IF_UNMODIFIED_SINCE;
    private static final String LastModified = com.google.common.net.HttpHeaders.LAST_MODIFIED;
    private static final String Location = com.google.common.net.HttpHeaders.LOCATION;
    private static final String LockToken = "Lock-Token";
    private static final String Link = com.google.common.net.HttpHeaders.LINK;
    private static final String MaxForwards = com.google.common.net.HttpHeaders.MAX_FORWARDS;
    private static final String MIMEVersion = "MIME-Version";
    private static final String OrderingType = "Ordering-Type";
    private static final String Origin = com.google.common.net.HttpHeaders.ORIGIN;
    private static final String Overwrite = "Overwrite";
    private static final String Position = "Position";
    private static final String Pragma = com.google.common.net.HttpHeaders.PRAGMA;
    private static final String Prefer = "Prefer";
    private static final String PreferenceApplied = "Preference-Applied";
    private static final String ProxyAuthenticate = com.google.common.net.HttpHeaders.PROXY_AUTHENTICATE;
    private static final String ProxyAuthenticationInfo = "Proxy-Authentication-Info";
    private static final String ProxyAuthorization = com.google.common.net.HttpHeaders.PROXY_AUTHORIZATION;
    private static final String PublicKeyPins = com.google.common.net.HttpHeaders.PUBLIC_KEY_PINS;
    private static final String PublicKeyPinsReportOnly = com.google.common.net.HttpHeaders.PUBLIC_KEY_PINS_REPORT_ONLY;
    private static final String Range = com.google.common.net.HttpHeaders.RANGE;
    private static final String Referrer = com.google.common.net.HttpHeaders.REFERER;
    private static final String RetryAfter = com.google.common.net.HttpHeaders.RETRY_AFTER;
    private static final String ScheduleReply = "Schedule-Reply";
    private static final String ScheduleTag = "Schedule-Tag";
    private static final String SecWebSocketAccept = com.google.common.net.HttpHeaders.SEC_WEBSOCKET_ACCEPT;
    private static final String SecWebSocketExtensions = com.google.common.net.HttpHeaders.SEC_WEBSOCKET_EXTENSIONS;
    private static final String SecWebSocketKey = com.google.common.net.HttpHeaders.SEC_WEBSOCKET_KEY;
    private static final String SecWebSocketProtocol = com.google.common.net.HttpHeaders.SEC_WEBSOCKET_PROTOCOL;
    private static final String SecWebSocketVersion = com.google.common.net.HttpHeaders.SEC_WEBSOCKET_VERSION;
    private static final String Server = com.google.common.net.HttpHeaders.SERVER;
    private static final String SetCookie = com.google.common.net.HttpHeaders.SET_COOKIE;
    private static final String SLUG = "SLUG";
    private static final String StrictTransportSecurity = com.google.common.net.HttpHeaders.STRICT_TRANSPORT_SECURITY;
    private static final String TE = com.google.common.net.HttpHeaders.TE;
    private static final String Timeout = "Timeout";
    private static final String Trailer = com.google.common.net.HttpHeaders.TRAILER;
    private static final String TransferEncoding = com.google.common.net.HttpHeaders.TRANSFER_ENCODING;
    private static final String Upgrade = com.google.common.net.HttpHeaders.UPGRADE;
    private static final String UserAgent = com.google.common.net.HttpHeaders.USER_AGENT;
    private static final String Vary = com.google.common.net.HttpHeaders.VARY;
    private static final String Via = com.google.common.net.HttpHeaders.VIA;
    private static final String Warning = com.google.common.net.HttpHeaders.WARNING;
    private static final String WWWAuthenticate = com.google.common.net.HttpHeaders.WWW_AUTHENTICATE;
    private static final String AccessControlAllowOrigin = com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
    private static final String AccessControlAllowMethods = com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS;
    private static final String AccessControlAllowCredentials = com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS;
    private static final String AccessControlAllowHeaders = com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS;
    private static final String AccessControlRequestMethod = com.google.common.net.HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD;
    private static final String AccessControlRequestHeaders = com.google.common.net.HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS;
    private static final String AccessControlExposeHeaders = com.google.common.net.HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS;
    private static final String AccessControlMaxAge = com.google.common.net.HttpHeaders.ACCESS_CONTROL_MAX_AGE;
    private static final String XHttpMethodOverride = "X-Http-Method-Override";
    private static final String XForwardedHost = com.google.common.net.HttpHeaders.X_FORWARDED_HOST;
    private static final String XForwardedServer = "X-Forwarded-Server";
    private static final String XForwardedProto = com.google.common.net.HttpHeaders.X_FORWARDED_PROTO;
    private static final String XForwardedFor = com.google.common.net.HttpHeaders.X_FORWARDED_FOR;
    private static final String XForwardedPort = com.google.common.net.HttpHeaders.X_FORWARDED_PORT;
    private static final String XRequestId = com.google.common.net.HttpHeaders.X_REQUEST_ID;
    private static final String XCorrelationId = "X-Correlation-ID";
    private static final String XTotalCount = "X-Total-Count";
    private static final String[] UnsafeHeadersArray = {TransferEncoding, Upgrade};
    private static final List<String> UnsafeHeadersList = ArraysKt.asList(UnsafeHeadersArray);

    @Deprecated(message = "Use UnsafeHeadersList instead.", replaceWith = @ReplaceWith(expression = "HttpHeaders.UnsafeHeadersList", imports = {}))
    public static /* synthetic */ void getUnsafeHeaders$annotations() {
    }

    private HttpHeaders() {
    }

    public final String getAccept() {
        return Accept;
    }

    public final String getAcceptCharset() {
        return AcceptCharset;
    }

    public final String getAcceptEncoding() {
        return AcceptEncoding;
    }

    public final String getAcceptLanguage() {
        return AcceptLanguage;
    }

    public final String getAcceptRanges() {
        return AcceptRanges;
    }

    public final String getAge() {
        return Age;
    }

    public final String getAllow() {
        return Allow;
    }

    public final String getALPN() {
        return ALPN;
    }

    public final String getAuthenticationInfo() {
        return AuthenticationInfo;
    }

    public final String getAuthorization() {
        return Authorization;
    }

    public final String getCacheControl() {
        return CacheControl;
    }

    public final String getConnection() {
        return Connection;
    }

    public final String getContentDisposition() {
        return ContentDisposition;
    }

    public final String getContentEncoding() {
        return ContentEncoding;
    }

    public final String getContentLanguage() {
        return ContentLanguage;
    }

    public final String getContentLength() {
        return ContentLength;
    }

    public final String getContentLocation() {
        return ContentLocation;
    }

    public final String getContentRange() {
        return ContentRange;
    }

    public final String getContentType() {
        return ContentType;
    }

    public final String getCookie() {
        return Cookie;
    }

    public final String getDASL() {
        return DASL;
    }

    public final String getDate() {
        return Date;
    }

    public final String getDAV() {
        return DAV;
    }

    public final String getDepth() {
        return Depth;
    }

    public final String getDestination() {
        return Destination;
    }

    public final String getETag() {
        return ETag;
    }

    public final String getExpect() {
        return Expect;
    }

    public final String getExpires() {
        return Expires;
    }

    public final String getFrom() {
        return From;
    }

    public final String getForwarded() {
        return Forwarded;
    }

    public final String getHost() {
        return Host;
    }

    public final String getHTTP2Settings() {
        return HTTP2Settings;
    }

    public final String getIf() {
        return If;
    }

    public final String getIfMatch() {
        return IfMatch;
    }

    public final String getIfModifiedSince() {
        return IfModifiedSince;
    }

    public final String getIfNoneMatch() {
        return IfNoneMatch;
    }

    public final String getIfRange() {
        return IfRange;
    }

    public final String getIfScheduleTagMatch() {
        return IfScheduleTagMatch;
    }

    public final String getIfUnmodifiedSince() {
        return IfUnmodifiedSince;
    }

    public final String getLastModified() {
        return LastModified;
    }

    public final String getLocation() {
        return Location;
    }

    public final String getLockToken() {
        return LockToken;
    }

    public final String getLink() {
        return Link;
    }

    public final String getMaxForwards() {
        return MaxForwards;
    }

    public final String getMIMEVersion() {
        return MIMEVersion;
    }

    public final String getOrderingType() {
        return OrderingType;
    }

    public final String getOrigin() {
        return Origin;
    }

    public final String getOverwrite() {
        return Overwrite;
    }

    public final String getPosition() {
        return Position;
    }

    public final String getPragma() {
        return Pragma;
    }

    public final String getPrefer() {
        return Prefer;
    }

    public final String getPreferenceApplied() {
        return PreferenceApplied;
    }

    public final String getProxyAuthenticate() {
        return ProxyAuthenticate;
    }

    public final String getProxyAuthenticationInfo() {
        return ProxyAuthenticationInfo;
    }

    public final String getProxyAuthorization() {
        return ProxyAuthorization;
    }

    public final String getPublicKeyPins() {
        return PublicKeyPins;
    }

    public final String getPublicKeyPinsReportOnly() {
        return PublicKeyPinsReportOnly;
    }

    public final String getRange() {
        return Range;
    }

    public final String getReferrer() {
        return Referrer;
    }

    public final String getRetryAfter() {
        return RetryAfter;
    }

    public final String getScheduleReply() {
        return ScheduleReply;
    }

    public final String getScheduleTag() {
        return ScheduleTag;
    }

    public final String getSecWebSocketAccept() {
        return SecWebSocketAccept;
    }

    public final String getSecWebSocketExtensions() {
        return SecWebSocketExtensions;
    }

    public final String getSecWebSocketKey() {
        return SecWebSocketKey;
    }

    public final String getSecWebSocketProtocol() {
        return SecWebSocketProtocol;
    }

    public final String getSecWebSocketVersion() {
        return SecWebSocketVersion;
    }

    public final String getServer() {
        return Server;
    }

    public final String getSetCookie() {
        return SetCookie;
    }

    public final String getSLUG() {
        return SLUG;
    }

    public final String getStrictTransportSecurity() {
        return StrictTransportSecurity;
    }

    public final String getTE() {
        return TE;
    }

    public final String getTimeout() {
        return Timeout;
    }

    public final String getTrailer() {
        return Trailer;
    }

    public final String getTransferEncoding() {
        return TransferEncoding;
    }

    public final String getUpgrade() {
        return Upgrade;
    }

    public final String getUserAgent() {
        return UserAgent;
    }

    public final String getVary() {
        return Vary;
    }

    public final String getVia() {
        return Via;
    }

    public final String getWarning() {
        return Warning;
    }

    public final String getWWWAuthenticate() {
        return WWWAuthenticate;
    }

    public final String getAccessControlAllowOrigin() {
        return AccessControlAllowOrigin;
    }

    public final String getAccessControlAllowMethods() {
        return AccessControlAllowMethods;
    }

    public final String getAccessControlAllowCredentials() {
        return AccessControlAllowCredentials;
    }

    public final String getAccessControlAllowHeaders() {
        return AccessControlAllowHeaders;
    }

    public final String getAccessControlRequestMethod() {
        return AccessControlRequestMethod;
    }

    public final String getAccessControlRequestHeaders() {
        return AccessControlRequestHeaders;
    }

    public final String getAccessControlExposeHeaders() {
        return AccessControlExposeHeaders;
    }

    public final String getAccessControlMaxAge() {
        return AccessControlMaxAge;
    }

    public final String getXHttpMethodOverride() {
        return XHttpMethodOverride;
    }

    public final String getXForwardedHost() {
        return XForwardedHost;
    }

    public final String getXForwardedServer() {
        return XForwardedServer;
    }

    public final String getXForwardedProto() {
        return XForwardedProto;
    }

    public final String getXForwardedFor() {
        return XForwardedFor;
    }

    public final String getXForwardedPort() {
        return XForwardedPort;
    }

    public final String getXRequestId() {
        return XRequestId;
    }

    public final String getXCorrelationId() {
        return XCorrelationId;
    }

    public final String getXTotalCount() {
        return XTotalCount;
    }

    public final boolean isUnsafe(String header) {
        Intrinsics.checkNotNullParameter(header, "header");
        for (String str : UnsafeHeadersArray) {
            if (StringsKt.equals(str, header, true)) {
                return true;
            }
        }
        return false;
    }

    public final String[] getUnsafeHeaders() {
        String[] strArr = UnsafeHeadersArray;
        Object[] copyOf = Arrays.copyOf(strArr, strArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        return (String[]) copyOf;
    }

    public final List<String> getUnsafeHeadersList() {
        return UnsafeHeadersList;
    }

    public final void checkHeaderName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        String $this$forEachIndexed$iv = name;
        int index$iv = 0;
        int i = 0;
        while (i < $this$forEachIndexed$iv.length()) {
            char item$iv = $this$forEachIndexed$iv.charAt(i);
            int index$iv2 = index$iv + 1;
            if (Intrinsics.compare((int) item$iv, 32) <= 0 || HttpHeadersKt.access$isDelimiter(item$iv)) {
                throw new IllegalHeaderNameException(name, index$iv);
            }
            i++;
            index$iv = index$iv2;
        }
    }

    public final void checkHeaderValue(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        String $this$forEachIndexed$iv = value;
        int index$iv = 0;
        int i = 0;
        while (i < $this$forEachIndexed$iv.length()) {
            char item$iv = $this$forEachIndexed$iv.charAt(i);
            int index$iv2 = index$iv + 1;
            if (Intrinsics.compare((int) item$iv, 32) < 0 && item$iv != '\t') {
                throw new IllegalHeaderValueException(value, index$iv);
            }
            i++;
            index$iv = index$iv2;
        }
    }
}
