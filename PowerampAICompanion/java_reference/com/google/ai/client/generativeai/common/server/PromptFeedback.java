package com.google.ai.client.generativeai.common.server;

import com.maxmpz.poweramp.player.RouterConsts;
import java.util.List;
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
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B3\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB#\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\u0010\fJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J!\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 HÇ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006#"}, d2 = {"Lcom/google/ai/client/generativeai/common/server/PromptFeedback;", "", "seen1", "", "blockReason", "Lcom/google/ai/client/generativeai/common/server/BlockReason;", "safetyRatings", "", "Lcom/google/ai/client/generativeai/common/server/SafetyRating;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/google/ai/client/generativeai/common/server/BlockReason;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/google/ai/client/generativeai/common/server/BlockReason;Ljava/util/List;)V", "getBlockReason", "()Lcom/google/ai/client/generativeai/common/server/BlockReason;", "getSafetyRatings", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class PromptFeedback {
    private final BlockReason blockReason;
    private final List<SafetyRating> safetyRatings;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {null, new ArrayListSerializer(SafetyRating$$serializer.INSTANCE)};

    /* JADX WARN: Multi-variable type inference failed */
    public PromptFeedback() {
        this((BlockReason) null, (List) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PromptFeedback copy$default(PromptFeedback promptFeedback, BlockReason blockReason, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            blockReason = promptFeedback.blockReason;
        }
        if ((i & 2) != 0) {
            list = promptFeedback.safetyRatings;
        }
        return promptFeedback.copy(blockReason, list);
    }

    /* renamed from: component1, reason: from getter */
    public final BlockReason getBlockReason() {
        return this.blockReason;
    }

    public final List<SafetyRating> component2() {
        return this.safetyRatings;
    }

    public final PromptFeedback copy(BlockReason blockReason, List<SafetyRating> safetyRatings) {
        return new PromptFeedback(blockReason, safetyRatings);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PromptFeedback)) {
            return false;
        }
        PromptFeedback promptFeedback = (PromptFeedback) other;
        return this.blockReason == promptFeedback.blockReason && Intrinsics.areEqual(this.safetyRatings, promptFeedback.safetyRatings);
    }

    public int hashCode() {
        return ((this.blockReason == null ? 0 : this.blockReason.hashCode()) * 31) + (this.safetyRatings != null ? this.safetyRatings.hashCode() : 0);
    }

    public String toString() {
        return "PromptFeedback(blockReason=" + this.blockReason + ", safetyRatings=" + this.safetyRatings + ")";
    }

    /* compiled from: Types.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/server/PromptFeedback$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/server/PromptFeedback;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<PromptFeedback> serializer() {
            return PromptFeedback$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ PromptFeedback(int seen1, BlockReason blockReason, List safetyRatings, SerializationConstructorMarker serializationConstructorMarker) {
        if ((seen1 & 1) == 0) {
            this.blockReason = null;
        } else {
            this.blockReason = blockReason;
        }
        if ((seen1 & 2) == 0) {
            this.safetyRatings = null;
        } else {
            this.safetyRatings = safetyRatings;
        }
    }

    public PromptFeedback(BlockReason blockReason, List<SafetyRating> list) {
        this.blockReason = blockReason;
        this.safetyRatings = list;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(PromptFeedback self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.blockReason != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, BlockReasonSerializer.INSTANCE, self.blockReason);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.safetyRatings != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, kSerializerArr[1], self.safetyRatings);
        }
    }

    public /* synthetic */ PromptFeedback(BlockReason blockReason, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : blockReason, (i & 2) != 0 ? null : list);
    }

    public final BlockReason getBlockReason() {
        return this.blockReason;
    }

    public final List<SafetyRating> getSafetyRatings() {
        return this.safetyRatings;
    }
}
