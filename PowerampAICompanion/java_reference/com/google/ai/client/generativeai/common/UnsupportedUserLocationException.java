package com.google.ai.client.generativeai.common;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Exceptions.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/google/ai/client/generativeai/common/UnsupportedUserLocationException;", "Lcom/google/ai/client/generativeai/common/GoogleGenerativeAIException;", "cause", "", "(Ljava/lang/Throwable;)V", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class UnsupportedUserLocationException extends GoogleGenerativeAIException {
    /* JADX WARN: Multi-variable type inference failed */
    public UnsupportedUserLocationException() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ UnsupportedUserLocationException(Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : th);
    }

    public UnsupportedUserLocationException(Throwable cause) {
        super("User location is not supported for the API use.", cause, null);
    }
}
