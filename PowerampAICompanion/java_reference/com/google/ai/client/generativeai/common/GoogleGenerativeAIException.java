package com.google.ai.client.generativeai.common;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.serialization.JsonConvertException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.TimeoutCancellationException;

/* compiled from: Exceptions.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \b2\u00060\u0001j\u0002`\u0002:\u0001\bB\u001b\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007\u0082\u0001\u000b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013¨\u0006\u0014"}, d2 = {"Lcom/google/ai/client/generativeai/common/GoogleGenerativeAIException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "Companion", "Lcom/google/ai/client/generativeai/common/InvalidAPIKeyException;", "Lcom/google/ai/client/generativeai/common/InvalidStateException;", "Lcom/google/ai/client/generativeai/common/PromptBlockedException;", "Lcom/google/ai/client/generativeai/common/QuotaExceededException;", "Lcom/google/ai/client/generativeai/common/RequestTimeoutException;", "Lcom/google/ai/client/generativeai/common/ResponseStoppedException;", "Lcom/google/ai/client/generativeai/common/SerializationException;", "Lcom/google/ai/client/generativeai/common/ServerException;", "Lcom/google/ai/client/generativeai/common/ServiceDisabledException;", "Lcom/google/ai/client/generativeai/common/UnknownException;", "Lcom/google/ai/client/generativeai/common/UnsupportedUserLocationException;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class GoogleGenerativeAIException extends RuntimeException {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public /* synthetic */ GoogleGenerativeAIException(String str, Throwable th, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, th);
    }

    public /* synthetic */ GoogleGenerativeAIException(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : th, null);
    }

    /* compiled from: Exceptions.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/google/ai/client/generativeai/common/GoogleGenerativeAIException$Companion;", "", "()V", TypedValues.TransitionType.S_FROM, "Lcom/google/ai/client/generativeai/common/GoogleGenerativeAIException;", "cause", "", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final GoogleGenerativeAIException from(Throwable cause) {
            Intrinsics.checkNotNullParameter(cause, "cause");
            if (cause instanceof GoogleGenerativeAIException) {
                return (GoogleGenerativeAIException) cause;
            }
            if (cause instanceof JsonConvertException ? true : cause instanceof kotlinx.serialization.SerializationException) {
                return new SerializationException("Something went wrong while trying to deserialize a response from the server.", cause);
            }
            if (cause instanceof TimeoutCancellationException) {
                return new RequestTimeoutException("The request failed to complete in the allotted time.", null, 2, 0 == true ? 1 : 0);
            }
            return new UnknownException("Something unexpected happened.", cause);
        }
    }

    private GoogleGenerativeAIException(String message, Throwable cause) {
        super(message, cause);
    }
}
