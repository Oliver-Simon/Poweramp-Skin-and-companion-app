package io.ktor.client.plugins.logging;

import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: LoggerJvm.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0001¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0011\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0082\u0010R\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lio/ktor/client/plugins/logging/MessageLengthLimitingLogger;", "Lio/ktor/client/plugins/logging/Logger;", "maxLength", "", "minLength", "delegate", "(IILio/ktor/client/plugins/logging/Logger;)V", "log", "", "message", "", "logLong", "ktor-client-logging"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class MessageLengthLimitingLogger implements Logger {
    private final Logger delegate;
    private final int maxLength;
    private final int minLength;

    public MessageLengthLimitingLogger() {
        this(0, 0, null, 7, null);
    }

    public MessageLengthLimitingLogger(int maxLength, int minLength, Logger delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.maxLength = maxLength;
        this.minLength = minLength;
        this.delegate = delegate;
    }

    public /* synthetic */ MessageLengthLimitingLogger(int i, int i2, Logger logger, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 4000 : i, (i3 & 2) != 0 ? PathInterpolatorCompat.MAX_NUM_POINTS : i2, (i3 & 4) != 0 ? LoggerJvmKt.getDEFAULT(Logger.INSTANCE) : logger);
    }

    @Override // io.ktor.client.plugins.logging.Logger
    public void log(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        logLong(message);
    }

    private final void logLong(String message) {
        while (message.length() > this.maxLength) {
            String substring = message.substring(0, this.maxLength);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            int msgSubstringEndIndex = this.maxLength;
            int lastIndex = StringsKt.lastIndexOf$default((CharSequence) substring, '\n', 0, false, 6, (Object) null);
            if (lastIndex >= this.minLength) {
                String substring2 = substring.substring(0, lastIndex);
                Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                substring = substring2;
                msgSubstringEndIndex = lastIndex + 1;
            }
            this.delegate.log(substring);
            String substring3 = message.substring(msgSubstringEndIndex);
            Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String).substring(startIndex)");
            message = substring3;
        }
        this.delegate.log(message);
    }
}
