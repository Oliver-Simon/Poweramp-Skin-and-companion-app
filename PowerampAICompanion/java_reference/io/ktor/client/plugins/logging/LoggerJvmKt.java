package io.ktor.client.plugins.logging;

import io.ktor.client.HttpClient;
import io.ktor.client.plugins.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.LoggerFactory;

/* compiled from: LoggerJvm.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\u0007"}, d2 = {"ANDROID", "Lio/ktor/client/plugins/logging/Logger;", "Lio/ktor/client/plugins/logging/Logger$Companion;", "getANDROID", "(Lio/ktor/client/plugins/logging/Logger$Companion;)Lio/ktor/client/plugins/logging/Logger;", "DEFAULT", "getDEFAULT", "ktor-client-logging"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class LoggerJvmKt {
    public static final Logger getDEFAULT(Logger.Companion $this$DEFAULT) {
        Intrinsics.checkNotNullParameter($this$DEFAULT, "<this>");
        return new Logger() { // from class: io.ktor.client.plugins.logging.LoggerJvmKt$DEFAULT$1
            private final org.slf4j.Logger delegate;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                org.slf4j.Logger logger = LoggerFactory.getLogger((Class<?>) HttpClient.class);
                Intrinsics.checkNotNull(logger);
                this.delegate = logger;
            }

            @Override // io.ktor.client.plugins.logging.Logger
            public void log(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                this.delegate.info(message);
            }
        };
    }

    public static final Logger getANDROID(Logger.Companion $this$ANDROID) {
        Intrinsics.checkNotNullParameter($this$ANDROID, "<this>");
        return new MessageLengthLimitingLogger(0, 0, null, 7, null);
    }
}
