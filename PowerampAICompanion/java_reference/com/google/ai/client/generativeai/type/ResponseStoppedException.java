package com.google.ai.client.generativeai.type;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Exceptions.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/google/ai/client/generativeai/type/ResponseStoppedException;", "Lcom/google/ai/client/generativeai/type/GoogleGenerativeAIException;", "response", "Lcom/google/ai/client/generativeai/type/GenerateContentResponse;", "cause", "", "(Lcom/google/ai/client/generativeai/type/GenerateContentResponse;Ljava/lang/Throwable;)V", "getResponse", "()Lcom/google/ai/client/generativeai/type/GenerateContentResponse;", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ResponseStoppedException extends GoogleGenerativeAIException {
    private final GenerateContentResponse response;

    public /* synthetic */ ResponseStoppedException(GenerateContentResponse generateContentResponse, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(generateContentResponse, (i & 2) != 0 ? null : th);
    }

    public final GenerateContentResponse getResponse() {
        return this.response;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ResponseStoppedException(com.google.ai.client.generativeai.type.GenerateContentResponse r5, java.lang.Throwable r6) {
        /*
            r4 = this;
            java.lang.String r0 = "response"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.util.List r0 = r5.getCandidates()
            java.lang.Object r0 = kotlin.collections.CollectionsKt.first(r0)
            com.google.ai.client.generativeai.type.Candidate r0 = (com.google.ai.client.generativeai.type.Candidate) r0
            com.google.ai.client.generativeai.type.FinishReason r0 = r0.getFinishReason()
            r1 = 0
            if (r0 == 0) goto L1c
            java.lang.String r0 = r0.name()
            goto L1d
        L1c:
            r0 = r1
        L1d:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Content generation stopped. Reason: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r4.<init>(r0, r6, r1)
            r4.response = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.type.ResponseStoppedException.<init>(com.google.ai.client.generativeai.type.GenerateContentResponse, java.lang.Throwable):void");
    }
}
