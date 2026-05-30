package io.ktor.client.plugins.cookies;

import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.http.Cookie;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;

/* compiled from: HttpCookies.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002\u001a#\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a#\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006*\u00020\b2\u0006\u0010\f\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u001d\u0010\u000e\u001a\u0004\u0018\u00010\u0007*\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000f\u001a\u00020\u0004H\u0086\u0002\"\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"LOGGER", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "renderClientCookies", "", TrackProviderConsts.COLUMN_COOKIES, "", "Lio/ktor/http/Cookie;", "Lio/ktor/client/HttpClient;", TrackProviderConsts.COLUMN_URL, "Lio/ktor/http/Url;", "(Lio/ktor/client/HttpClient;Lio/ktor/http/Url;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "urlString", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "get", "name", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpCookiesKt {
    private static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpCookies");

    /* JADX INFO: Access modifiers changed from: private */
    public static final String renderClientCookies(List<Cookie> list) {
        return CollectionsKt.joinToString$default(list, "; ", null, null, 0, null, HttpCookiesKt$renderClientCookies$1.INSTANCE, 30, null);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object cookies(io.ktor.client.HttpClient r3, io.ktor.http.Url r4, kotlin.coroutines.Continuation<? super java.util.List<io.ktor.http.Cookie>> r5) {
        /*
            boolean r0 = r5 instanceof io.ktor.client.plugins.cookies.HttpCookiesKt$cookies$1
            if (r0 == 0) goto L14
            r0 = r5
            io.ktor.client.plugins.cookies.HttpCookiesKt$cookies$1 r0 = (io.ktor.client.plugins.cookies.HttpCookiesKt$cookies$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            io.ktor.client.plugins.cookies.HttpCookiesKt$cookies$1 r0 = new io.ktor.client.plugins.cookies.HttpCookiesKt$cookies$1
            r0.<init>(r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L31;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
            r3.<init>(r4)
            throw r3
        L2c:
            kotlin.ResultKt.throwOnFailure(r5)
            r3 = r5
            goto L4a
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            io.ktor.client.plugins.cookies.HttpCookies$Companion r2 = io.ktor.client.plugins.cookies.HttpCookies.INSTANCE
            io.ktor.client.plugins.HttpClientPlugin r2 = (io.ktor.client.plugins.HttpClientPlugin) r2
            java.lang.Object r2 = io.ktor.client.plugins.HttpClientPluginKt.pluginOrNull(r3, r2)
            io.ktor.client.plugins.cookies.HttpCookies r2 = (io.ktor.client.plugins.cookies.HttpCookies) r2
            if (r2 == 0) goto L4e
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.get(r4, r0)
            if (r3 != r1) goto L4a
            return r1
        L4a:
            java.util.List r3 = (java.util.List) r3
            if (r3 != 0) goto L52
        L4e:
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
        L52:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cookies.HttpCookiesKt.cookies(io.ktor.client.HttpClient, io.ktor.http.Url, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object cookies(io.ktor.client.HttpClient r4, java.lang.String r5, kotlin.coroutines.Continuation<? super java.util.List<io.ktor.http.Cookie>> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.client.plugins.cookies.HttpCookiesKt$cookies$2
            if (r0 == 0) goto L14
            r0 = r6
            io.ktor.client.plugins.cookies.HttpCookiesKt$cookies$2 r0 = (io.ktor.client.plugins.cookies.HttpCookiesKt$cookies$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            io.ktor.client.plugins.cookies.HttpCookiesKt$cookies$2 r0 = new io.ktor.client.plugins.cookies.HttpCookiesKt$cookies$2
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L31;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2c:
            kotlin.ResultKt.throwOnFailure(r6)
            r4 = r6
            goto L4e
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            io.ktor.client.plugins.cookies.HttpCookies$Companion r2 = io.ktor.client.plugins.cookies.HttpCookies.INSTANCE
            io.ktor.client.plugins.HttpClientPlugin r2 = (io.ktor.client.plugins.HttpClientPlugin) r2
            java.lang.Object r2 = io.ktor.client.plugins.HttpClientPluginKt.pluginOrNull(r4, r2)
            io.ktor.client.plugins.cookies.HttpCookies r2 = (io.ktor.client.plugins.cookies.HttpCookies) r2
            if (r2 == 0) goto L52
            io.ktor.http.Url r4 = io.ktor.http.URLUtilsKt.Url(r5)
            r3 = 1
            r0.label = r3
            java.lang.Object r4 = r2.get(r4, r0)
            if (r4 != r1) goto L4e
            return r1
        L4e:
            java.util.List r4 = (java.util.List) r4
            if (r4 != 0) goto L56
        L52:
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()
        L56:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cookies.HttpCookiesKt.cookies(io.ktor.client.HttpClient, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Cookie get(List<Cookie> list, String name) {
        Object obj;
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Cookie it2 = (Cookie) obj;
            if (Intrinsics.areEqual(it2.getName(), name)) {
                break;
            }
        }
        return (Cookie) obj;
    }
}
