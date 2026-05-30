package com.google.ai.client.generativeai.common;

import com.maxmpz.poweramp.player.RouterConsts;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: Response.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B7\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB)\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ2\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J!\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!HÇ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000f\u0010\f¨\u0006$"}, d2 = {"Lcom/google/ai/client/generativeai/common/UsageMetadata;", "", "seen1", "", "promptTokenCount", "candidatesTokenCount", "totalTokenCount", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getCandidatesTokenCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPromptTokenCount", "getTotalTokenCount", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/google/ai/client/generativeai/common/UsageMetadata;", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class UsageMetadata {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Integer candidatesTokenCount;
    private final Integer promptTokenCount;
    private final Integer totalTokenCount;

    public UsageMetadata() {
        this((Integer) null, (Integer) null, (Integer) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ UsageMetadata copy$default(UsageMetadata usageMetadata, Integer num, Integer num2, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            num = usageMetadata.promptTokenCount;
        }
        if ((i & 2) != 0) {
            num2 = usageMetadata.candidatesTokenCount;
        }
        if ((i & 4) != 0) {
            num3 = usageMetadata.totalTokenCount;
        }
        return usageMetadata.copy(num, num2, num3);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getPromptTokenCount() {
        return this.promptTokenCount;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getCandidatesTokenCount() {
        return this.candidatesTokenCount;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getTotalTokenCount() {
        return this.totalTokenCount;
    }

    public final UsageMetadata copy(Integer promptTokenCount, Integer candidatesTokenCount, Integer totalTokenCount) {
        return new UsageMetadata(promptTokenCount, candidatesTokenCount, totalTokenCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UsageMetadata)) {
            return false;
        }
        UsageMetadata usageMetadata = (UsageMetadata) other;
        return Intrinsics.areEqual(this.promptTokenCount, usageMetadata.promptTokenCount) && Intrinsics.areEqual(this.candidatesTokenCount, usageMetadata.candidatesTokenCount) && Intrinsics.areEqual(this.totalTokenCount, usageMetadata.totalTokenCount);
    }

    public int hashCode() {
        return ((((this.promptTokenCount == null ? 0 : this.promptTokenCount.hashCode()) * 31) + (this.candidatesTokenCount == null ? 0 : this.candidatesTokenCount.hashCode())) * 31) + (this.totalTokenCount != null ? this.totalTokenCount.hashCode() : 0);
    }

    public String toString() {
        return "UsageMetadata(promptTokenCount=" + this.promptTokenCount + ", candidatesTokenCount=" + this.candidatesTokenCount + ", totalTokenCount=" + this.totalTokenCount + ")";
    }

    /* compiled from: Response.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/UsageMetadata$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/UsageMetadata;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<UsageMetadata> serializer() {
            return UsageMetadata$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ UsageMetadata(int seen1, Integer promptTokenCount, Integer candidatesTokenCount, Integer totalTokenCount, SerializationConstructorMarker serializationConstructorMarker) {
        if ((seen1 & 1) == 0) {
            this.promptTokenCount = null;
        } else {
            this.promptTokenCount = promptTokenCount;
        }
        if ((seen1 & 2) == 0) {
            this.candidatesTokenCount = null;
        } else {
            this.candidatesTokenCount = candidatesTokenCount;
        }
        if ((seen1 & 4) == 0) {
            this.totalTokenCount = null;
        } else {
            this.totalTokenCount = totalTokenCount;
        }
    }

    public UsageMetadata(Integer promptTokenCount, Integer candidatesTokenCount, Integer totalTokenCount) {
        this.promptTokenCount = promptTokenCount;
        this.candidatesTokenCount = candidatesTokenCount;
        this.totalTokenCount = totalTokenCount;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(UsageMetadata self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.promptTokenCount != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.promptTokenCount);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.candidatesTokenCount != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.candidatesTokenCount);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.totalTokenCount != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.totalTokenCount);
        }
    }

    public /* synthetic */ UsageMetadata(Integer num, Integer num2, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : num3);
    }

    public final Integer getPromptTokenCount() {
        return this.promptTokenCount;
    }

    public final Integer getCandidatesTokenCount() {
        return this.candidatesTokenCount;
    }

    public final Integer getTotalTokenCount() {
        return this.totalTokenCount;
    }
}
