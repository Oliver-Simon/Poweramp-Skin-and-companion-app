package io.ktor.client.plugins.api;

import io.ktor.client.HttpClient;
import io.ktor.util.AttributeKey;
import io.ktor.util.InternalAPI;
import java.io.Closeable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ClientPluginInstance.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00060\u0003j\u0002`\u0004B6\b\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f¢\u0006\u0002\u0010\rJ\b\u0010\u0017\u001a\u00020\u000bH\u0016J\u0010\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0007R+\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0005\u001a\u00028\u0000X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lio/ktor/client/plugins/api/ClientPluginInstance;", "PluginConfig", "", "Ljava/io/Closeable;", "Lio/ktor/utils/io/core/Closeable;", "config", "name", "", "body", "Lkotlin/Function1;", "Lio/ktor/client/plugins/api/ClientPluginBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getBody$ktor_client_core", "()Lkotlin/jvm/functions/Function1;", "getConfig$ktor_client_core", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getName$ktor_client_core", "()Ljava/lang/String;", "onClose", "Lkotlin/Function0;", "close", "install", "scope", "Lio/ktor/client/HttpClient;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ClientPluginInstance<PluginConfig> implements Closeable {
    private final Function1<ClientPluginBuilder<PluginConfig>, Unit> body;
    private final PluginConfig config;
    private final String name;
    private Function0<Unit> onClose;

    /* JADX WARN: Multi-variable type inference failed */
    public ClientPluginInstance(PluginConfig config, String name, Function1<? super ClientPluginBuilder<PluginConfig>, Unit> body) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        this.config = config;
        this.name = name;
        this.body = body;
        this.onClose = new Function0<Unit>() { // from class: io.ktor.client.plugins.api.ClientPluginInstance$onClose$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }
        };
    }

    public final PluginConfig getConfig$ktor_client_core() {
        return this.config;
    }

    /* renamed from: getName$ktor_client_core, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final Function1<ClientPluginBuilder<PluginConfig>, Unit> getBody$ktor_client_core() {
        return this.body;
    }

    @InternalAPI
    public final void install(HttpClient scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        ClientPluginBuilder pluginBuilder = new ClientPluginBuilder(new AttributeKey(this.name), scope, this.config);
        this.body.invoke(pluginBuilder);
        this.onClose = pluginBuilder.getOnClose$ktor_client_core();
        Iterable $this$forEach$iv = pluginBuilder.getHooks$ktor_client_core();
        for (Object element$iv : $this$forEach$iv) {
            HookHandler it = (HookHandler) element$iv;
            it.install(scope);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.onClose.invoke();
    }
}
