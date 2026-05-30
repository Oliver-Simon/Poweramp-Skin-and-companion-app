package com.google.ai.client.generativeai.type;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

/* compiled from: RequestOptions.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\b\u0017\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001a\u0012\u0006\u0010\u0002\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u000e"}, d2 = {"Lcom/google/ai/client/generativeai/type/RequestOptions;", "", "timeout", "", "apiVersion", "", "(Ljava/lang/Long;Ljava/lang/String;)V", "Lkotlin/time/Duration;", "(JLjava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getApiVersion", "()Ljava/lang/String;", "getTimeout-UwyO8pc", "()J", "J", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RequestOptions {
    private final String apiVersion;
    private final long timeout;

    /* JADX WARN: Multi-variable type inference failed */
    public RequestOptions() {
        this((Long) null, (String) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
    }

    public /* synthetic */ RequestOptions(long j, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RequestOptions(Long l) {
        this(l, (String) null, 2, (DefaultConstructorMarker) (0 == true ? 1 : 0));
    }

    private RequestOptions(long timeout, String apiVersion) {
        Intrinsics.checkNotNullParameter(apiVersion, "apiVersion");
        this.timeout = timeout;
        this.apiVersion = apiVersion;
    }

    public /* synthetic */ RequestOptions(long j, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (i & 2) != 0 ? "v1beta" : str, null);
    }

    public final String getApiVersion() {
        return this.apiVersion;
    }

    /* renamed from: getTimeout-UwyO8pc, reason: not valid java name and from getter */
    public final long getTimeout() {
        return this.timeout;
    }

    public /* synthetic */ RequestOptions(Long l, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Long.MAX_VALUE : l, (i & 2) != 0 ? "v1beta" : str);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RequestOptions(Long timeout, String apiVersion) {
        this(DurationKt.toDuration(timeout != null ? timeout.longValue() : Long.MAX_VALUE, DurationUnit.MILLISECONDS), apiVersion, null);
        Intrinsics.checkNotNullParameter(apiVersion, "apiVersion");
    }
}
