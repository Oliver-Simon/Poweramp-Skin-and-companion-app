package com.google.ai.client.generativeai.common.shared;

import com.maxmpz.poweramp.player.RouterConsts;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001c\u001dB#\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J!\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bHÇ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/google/ai/client/generativeai/common/shared/CodeExecutionResultPart;", "Lcom/google/ai/client/generativeai/common/shared/Part;", "seen1", "", "codeExecutionResult", "Lcom/google/ai/client/generativeai/common/shared/CodeExecutionResult;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/google/ai/client/generativeai/common/shared/CodeExecutionResult;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/google/ai/client/generativeai/common/shared/CodeExecutionResult;)V", "getCodeExecutionResult", "()Lcom/google/ai/client/generativeai/common/shared/CodeExecutionResult;", "component1", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class CodeExecutionResultPart implements Part {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CodeExecutionResult codeExecutionResult;

    public static /* synthetic */ CodeExecutionResultPart copy$default(CodeExecutionResultPart codeExecutionResultPart, CodeExecutionResult codeExecutionResult, int i, Object obj) {
        if ((i & 1) != 0) {
            codeExecutionResult = codeExecutionResultPart.codeExecutionResult;
        }
        return codeExecutionResultPart.copy(codeExecutionResult);
    }

    /* renamed from: component1, reason: from getter */
    public final CodeExecutionResult getCodeExecutionResult() {
        return this.codeExecutionResult;
    }

    public final CodeExecutionResultPart copy(CodeExecutionResult codeExecutionResult) {
        Intrinsics.checkNotNullParameter(codeExecutionResult, "codeExecutionResult");
        return new CodeExecutionResultPart(codeExecutionResult);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof CodeExecutionResultPart) && Intrinsics.areEqual(this.codeExecutionResult, ((CodeExecutionResultPart) other).codeExecutionResult);
    }

    public int hashCode() {
        return this.codeExecutionResult.hashCode();
    }

    public String toString() {
        return "CodeExecutionResultPart(codeExecutionResult=" + this.codeExecutionResult + ")";
    }

    /* compiled from: Types.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/shared/CodeExecutionResultPart$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/shared/CodeExecutionResultPart;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<CodeExecutionResultPart> serializer() {
            return CodeExecutionResultPart$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CodeExecutionResultPart(int seen1, CodeExecutionResult codeExecutionResult, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (seen1 & 1)) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 1, CodeExecutionResultPart$$serializer.INSTANCE.getDescriptor());
        }
        this.codeExecutionResult = codeExecutionResult;
    }

    public CodeExecutionResultPart(CodeExecutionResult codeExecutionResult) {
        Intrinsics.checkNotNullParameter(codeExecutionResult, "codeExecutionResult");
        this.codeExecutionResult = codeExecutionResult;
    }

    public final CodeExecutionResult getCodeExecutionResult() {
        return this.codeExecutionResult;
    }
}
