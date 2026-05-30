package com.google.ai.client.generativeai.common.shared;

import kotlin.Metadata;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bw\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002\u0082\u0001\u0007\u0003\u0004\u0005\u0006\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/google/ai/client/generativeai/common/shared/Part;", "", "Companion", "Lcom/google/ai/client/generativeai/common/shared/BlobPart;", "Lcom/google/ai/client/generativeai/common/shared/CodeExecutionResultPart;", "Lcom/google/ai/client/generativeai/common/shared/ExecutableCodePart;", "Lcom/google/ai/client/generativeai/common/shared/FileDataPart;", "Lcom/google/ai/client/generativeai/common/shared/FunctionCallPart;", "Lcom/google/ai/client/generativeai/common/shared/FunctionResponsePart;", "Lcom/google/ai/client/generativeai/common/shared/TextPart;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable(with = PartSerializer.class)
/* loaded from: classes.dex */
public interface Part {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* compiled from: Types.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/shared/Part$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/shared/Part;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final KSerializer<Part> serializer() {
            return PartSerializer.INSTANCE;
        }
    }
}
