package io.ktor.utils.io;

import kotlin.Metadata;

/* compiled from: ReadSession.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lio/ktor/utils/io/HasReadSession;", "", "endReadSession", "", "startReadSession", "Lio/ktor/utils/io/SuspendableReadSession;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public interface HasReadSession {
    void endReadSession();

    SuspendableReadSession startReadSession();
}
