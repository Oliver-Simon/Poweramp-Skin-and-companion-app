package com.google.ai.client.generativeai.common;

import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: APIController.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007H¦@ø\u0001\u0000¢\u0006\u0002\u0010\tR\u001b\u0010\u0002\u001a\u00020\u0003X¦\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\n"}, d2 = {"Lcom/google/ai/client/generativeai/common/HeaderProvider;", "", "timeout", "Lkotlin/time/Duration;", "getTimeout-UwyO8pc", "()J", "generateHeaders", "", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface HeaderProvider {
    Object generateHeaders(Continuation<? super Map<String, String>> continuation);

    /* renamed from: getTimeout-UwyO8pc, reason: not valid java name */
    long m82getTimeoutUwyO8pc();
}
