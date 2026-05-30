package com.google.ai.client.generativeai.type;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.ai.client.generativeai.internal.util.ConversionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.TimeoutCancellationException;

/* compiled from: Exceptions.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \b2\u00060\u0001j\u0002`\u0002:\u0001\bB\u001b\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007\u0082\u0001\n\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012¨\u0006\u0013"}, d2 = {"Lcom/google/ai/client/generativeai/type/GoogleGenerativeAIException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "Companion", "Lcom/google/ai/client/generativeai/type/InvalidAPIKeyException;", "Lcom/google/ai/client/generativeai/type/InvalidStateException;", "Lcom/google/ai/client/generativeai/type/PromptBlockedException;", "Lcom/google/ai/client/generativeai/type/QuotaExceededException;", "Lcom/google/ai/client/generativeai/type/RequestTimeoutException;", "Lcom/google/ai/client/generativeai/type/ResponseStoppedException;", "Lcom/google/ai/client/generativeai/type/SerializationException;", "Lcom/google/ai/client/generativeai/type/ServerException;", "Lcom/google/ai/client/generativeai/type/UnknownException;", "Lcom/google/ai/client/generativeai/type/UnsupportedUserLocationException;", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
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

    private GoogleGenerativeAIException(String message, Throwable cause) {
        super(message, cause);
    }

    /* compiled from: Exceptions.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/google/ai/client/generativeai/type/GoogleGenerativeAIException$Companion;", "", "()V", TypedValues.TransitionType.S_FROM, "Lcom/google/ai/client/generativeai/type/GoogleGenerativeAIException;", "cause", "", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
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
            int i = 2;
            Throwable th = null;
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            Object[] objArr3 = 0;
            if (cause instanceof com.google.ai.client.generativeai.common.GoogleGenerativeAIException) {
                com.google.ai.client.generativeai.common.GoogleGenerativeAIException googleGenerativeAIException = (com.google.ai.client.generativeai.common.GoogleGenerativeAIException) cause;
                if (googleGenerativeAIException instanceof com.google.ai.client.generativeai.common.SerializationException) {
                    String message = cause.getMessage();
                    return new SerializationException(message != null ? message : "", cause.getCause());
                }
                if (googleGenerativeAIException instanceof com.google.ai.client.generativeai.common.ServerException) {
                    String message2 = cause.getMessage();
                    return new ServerException(message2 != null ? message2 : "", cause.getCause());
                }
                if (googleGenerativeAIException instanceof com.google.ai.client.generativeai.common.InvalidAPIKeyException) {
                    String message3 = cause.getMessage();
                    return new InvalidAPIKeyException(message3 != null ? message3 : "", th, i, objArr3 == true ? 1 : 0);
                }
                if (googleGenerativeAIException instanceof com.google.ai.client.generativeai.common.PromptBlockedException) {
                    return new PromptBlockedException(ConversionsKt.toPublic(((com.google.ai.client.generativeai.common.PromptBlockedException) cause).getResponse()), cause.getCause());
                }
                if (googleGenerativeAIException instanceof com.google.ai.client.generativeai.common.UnsupportedUserLocationException) {
                    return new UnsupportedUserLocationException(cause.getCause());
                }
                if (googleGenerativeAIException instanceof com.google.ai.client.generativeai.common.InvalidStateException) {
                    String message4 = cause.getMessage();
                    return new InvalidStateException(message4 != null ? message4 : "", cause);
                }
                if (googleGenerativeAIException instanceof com.google.ai.client.generativeai.common.ResponseStoppedException) {
                    return new ResponseStoppedException(ConversionsKt.toPublic(((com.google.ai.client.generativeai.common.ResponseStoppedException) cause).getResponse()), cause.getCause());
                }
                if (googleGenerativeAIException instanceof com.google.ai.client.generativeai.common.RequestTimeoutException) {
                    String message5 = cause.getMessage();
                    return new RequestTimeoutException(message5 != null ? message5 : "", cause.getCause());
                }
                if (googleGenerativeAIException instanceof com.google.ai.client.generativeai.common.UnknownException) {
                    String message6 = cause.getMessage();
                    return new UnknownException(message6 != null ? message6 : "", cause.getCause());
                }
                if (googleGenerativeAIException instanceof com.google.ai.client.generativeai.common.QuotaExceededException) {
                    String message7 = cause.getMessage();
                    return new QuotaExceededException(message7 != null ? message7 : "", cause.getCause());
                }
                String message8 = cause.getMessage();
                return new UnknownException(message8 != null ? message8 : "", cause);
            }
            if (cause instanceof TimeoutCancellationException) {
                return new RequestTimeoutException("The request failed to complete in the allotted time.", objArr2 == true ? 1 : 0, i, objArr == true ? 1 : 0);
            }
            return new UnknownException("Something unexpected happened.", cause);
        }
    }
}
