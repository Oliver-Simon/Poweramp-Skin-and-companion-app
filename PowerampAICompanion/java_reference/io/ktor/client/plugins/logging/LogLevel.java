package io.ktor.client.plugins.logging;

import com.maxmpz.poweramp.player.TrackProviderConsts;
import kotlin.Metadata;

/* compiled from: LogLevel.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lio/ktor/client/plugins/logging/LogLevel;", "", "info", "", TrackProviderConsts.COLUMN_HEADERS, "body", "(Ljava/lang/String;IZZZ)V", "getBody", "()Z", "getHeaders", "getInfo", "ALL", "HEADERS", "BODY", "INFO", "NONE", "ktor-client-logging"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public enum LogLevel {
    ALL(true, true, true),
    HEADERS(true, true, false),
    BODY(true, false, true),
    INFO(true, false, false),
    NONE(false, false, false);

    private final boolean body;
    private final boolean headers;
    private final boolean info;

    LogLevel(boolean info, boolean headers, boolean body) {
        this.info = info;
        this.headers = headers;
        this.body = body;
    }

    public final boolean getInfo() {
        return this.info;
    }

    public final boolean getHeaders() {
        return this.headers;
    }

    public final boolean getBody() {
        return this.body;
    }
}
