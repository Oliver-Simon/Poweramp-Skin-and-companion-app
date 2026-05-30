package io.ktor.http.content;

import com.maxmpz.poweramp.player.TableDefs;
import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.http.ContentType;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.cio.ByteBufferPoolKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.jvm.javaio.ReadingKt;
import java.io.InputStream;
import java.net.URI;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: URIFileContent.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B#\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0018\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lio/ktor/http/content/URIFileContent;", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", TrackProviderConsts.COLUMN_URL, "Ljava/net/URL;", "contentType", "Lio/ktor/http/ContentType;", "(Ljava/net/URL;Lio/ktor/http/ContentType;)V", TableDefs.SettingsSearchHistory.URI, "Ljava/net/URI;", "contentLength", "", "(Ljava/net/URI;Lio/ktor/http/ContentType;Ljava/lang/Long;)V", "getContentLength", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getContentType", "()Lio/ktor/http/ContentType;", "getUri", "()Ljava/net/URI;", "readFrom", "Lio/ktor/utils/io/ByteReadChannel;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class URIFileContent extends OutgoingContent.ReadChannelContent {
    private final Long contentLength;
    private final ContentType contentType;
    private final URI uri;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ URIFileContent(java.net.URI r2, io.ktor.http.ContentType r3, java.lang.Long r4, int r5, kotlin.jvm.internal.DefaultConstructorMarker r6) {
        /*
            r1 = this;
            r6 = r5 & 2
            if (r6 == 0) goto L13
            io.ktor.http.ContentType$Companion r3 = io.ktor.http.ContentType.INSTANCE
            java.lang.String r6 = r2.getPath()
            java.lang.String r0 = "uri.path"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
            io.ktor.http.ContentType r3 = io.ktor.http.FileContentTypeKt.defaultForFilePath(r3, r6)
        L13:
            r5 = r5 & 4
            if (r5 == 0) goto L18
            r4 = 0
        L18:
            r1.<init>(r2, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.content.URIFileContent.<init>(java.net.URI, io.ktor.http.ContentType, java.lang.Long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final URI getUri() {
        return this.uri;
    }

    @Override // io.ktor.http.content.OutgoingContent
    public ContentType getContentType() {
        return this.contentType;
    }

    @Override // io.ktor.http.content.OutgoingContent
    public Long getContentLength() {
        return this.contentLength;
    }

    public URIFileContent(URI uri, ContentType contentType, Long contentLength) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        this.uri = uri;
        this.contentType = contentType;
        this.contentLength = contentLength;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public URIFileContent(java.net.URL r8, io.ktor.http.ContentType r9) {
        /*
            r7 = this;
            java.lang.String r0 = "url"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "contentType"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.net.URI r2 = r8.toURI()
            java.lang.String r0 = "url.toURI()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
            r5 = 4
            r6 = 0
            r4 = 0
            r1 = r7
            r3 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.content.URIFileContent.<init>(java.net.URL, io.ktor.http.ContentType):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ URIFileContent(java.net.URL r1, io.ktor.http.ContentType r2, int r3, kotlin.jvm.internal.DefaultConstructorMarker r4) {
        /*
            r0 = this;
            r3 = r3 & 2
            if (r3 == 0) goto L13
            io.ktor.http.ContentType$Companion r2 = io.ktor.http.ContentType.INSTANCE
            java.lang.String r3 = r1.getPath()
            java.lang.String r4 = "url.path"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            io.ktor.http.ContentType r2 = io.ktor.http.FileContentTypeKt.defaultForFilePath(r2, r3)
        L13:
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.content.URIFileContent.<init>(java.net.URL, io.ktor.http.ContentType, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    @Override // io.ktor.http.content.OutgoingContent.ReadChannelContent
    /* renamed from: readFrom */
    public ByteReadChannel getChannel() {
        InputStream openStream = this.uri.toURL().openStream();
        Intrinsics.checkNotNullExpressionValue(openStream, "uri.toURL().openStream()");
        return ReadingKt.toByteReadChannel$default(openStream, null, ByteBufferPoolKt.getKtorDefaultPool(), 1, null);
    }
}
