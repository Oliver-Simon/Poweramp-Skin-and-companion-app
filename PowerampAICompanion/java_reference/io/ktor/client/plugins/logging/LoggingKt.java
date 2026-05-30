package io.ktor.client.plugins.logging;

import io.ktor.client.HttpClientConfig;
import io.ktor.client.plugins.logging.Logging;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Logging.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a)\u0010\u0005\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u00062\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0002\b\n\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"ClientCallLogger", "Lio/ktor/util/AttributeKey;", "Lio/ktor/client/plugins/logging/HttpClientCallLogger;", "DisableLogging", "", "Logging", "Lio/ktor/client/HttpClientConfig;", "block", "Lkotlin/Function1;", "Lio/ktor/client/plugins/logging/Logging$Config;", "Lkotlin/ExtensionFunctionType;", "ktor-client-logging"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class LoggingKt {
    private static final AttributeKey<HttpClientCallLogger> ClientCallLogger = new AttributeKey<>("CallLogger");
    private static final AttributeKey<Unit> DisableLogging = new AttributeKey<>("DisableLogging");

    public static /* synthetic */ void Logging$default(HttpClientConfig httpClientConfig, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Logging.Config, Unit>() { // from class: io.ktor.client.plugins.logging.LoggingKt$Logging$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Logging.Config config) {
                    invoke2(config);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Logging.Config $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        Logging(httpClientConfig, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void Logging(HttpClientConfig<?> httpClientConfig, Function1<? super Logging.Config, Unit> block) {
        Intrinsics.checkNotNullParameter(httpClientConfig, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        httpClientConfig.install(Logging.Companion, block);
    }
}
