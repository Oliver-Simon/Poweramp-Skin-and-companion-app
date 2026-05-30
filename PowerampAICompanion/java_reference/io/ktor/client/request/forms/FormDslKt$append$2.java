package io.ktor.client.request.forms;

import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.Input;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: formDsl.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/core/Input;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
/* loaded from: classes9.dex */
public final class FormDslKt$append$2 extends Lambda implements Function0<Input> {
    final /* synthetic */ Function1<BytePacketBuilder, Unit> $bodyBuilder;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FormDslKt$append$2(Function1<? super BytePacketBuilder, Unit> function1) {
        super(0);
        this.$bodyBuilder = function1;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Input invoke() {
        Function1<BytePacketBuilder, Unit> function1 = this.$bodyBuilder;
        BytePacketBuilder builder$iv = new BytePacketBuilder(null, 1, null);
        try {
            function1.invoke(builder$iv);
            return builder$iv.build();
        } catch (Throwable t$iv) {
            builder$iv.release();
            throw t$iv;
        }
    }
}
