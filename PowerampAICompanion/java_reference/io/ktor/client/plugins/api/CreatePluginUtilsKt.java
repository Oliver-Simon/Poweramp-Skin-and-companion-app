package io.ktor.client.plugins.api;

import io.ktor.client.HttpClient;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CreatePluginUtils.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001aK\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00072\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f\u001a3\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f¨\u0006\r"}, d2 = {"createClientPlugin", "Lio/ktor/client/plugins/api/ClientPlugin;", "PluginConfigT", "", "name", "", "createConfiguration", "Lkotlin/Function0;", "body", "Lkotlin/Function1;", "Lio/ktor/client/plugins/api/ClientPluginBuilder;", "", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class CreatePluginUtilsKt {
    public static final <PluginConfigT> ClientPlugin<PluginConfigT> createClientPlugin(final String name, final Function0<? extends PluginConfigT> createConfiguration, final Function1<? super ClientPluginBuilder<PluginConfigT>, Unit> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(createConfiguration, "createConfiguration");
        Intrinsics.checkNotNullParameter(body, "body");
        return new ClientPlugin<PluginConfigT>(name, createConfiguration, body) { // from class: io.ktor.client.plugins.api.CreatePluginUtilsKt$createClientPlugin$1
            final /* synthetic */ Function1<ClientPluginBuilder<PluginConfigT>, Unit> $body;
            final /* synthetic */ Function0<PluginConfigT> $createConfiguration;
            final /* synthetic */ String $name;
            private final AttributeKey<ClientPluginInstance<PluginConfigT>> key;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$name = name;
                this.$createConfiguration = createConfiguration;
                this.$body = body;
                this.key = new AttributeKey<>(name);
            }

            @Override // io.ktor.client.plugins.HttpClientPlugin
            public AttributeKey<ClientPluginInstance<PluginConfigT>> getKey() {
                return this.key;
            }

            @Override // io.ktor.client.plugins.HttpClientPlugin
            public ClientPluginInstance<PluginConfigT> prepare(Function1<? super PluginConfigT, Unit> block) {
                Intrinsics.checkNotNullParameter(block, "block");
                Object config = this.$createConfiguration.invoke();
                block.invoke(config);
                return new ClientPluginInstance<>(config, this.$name, this.$body);
            }

            @Override // io.ktor.client.plugins.HttpClientPlugin
            public void install(ClientPluginInstance<PluginConfigT> plugin, HttpClient scope) {
                Intrinsics.checkNotNullParameter(plugin, "plugin");
                Intrinsics.checkNotNullParameter(scope, "scope");
                plugin.install(scope);
            }
        };
    }

    public static final ClientPlugin<Unit> createClientPlugin(String name, Function1<? super ClientPluginBuilder<Unit>, Unit> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        return createClientPlugin(name, new Function0<Unit>() { // from class: io.ktor.client.plugins.api.CreatePluginUtilsKt$createClientPlugin$2
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }
        }, body);
    }
}
