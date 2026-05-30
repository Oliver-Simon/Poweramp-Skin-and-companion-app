package com.google.ai.client.generativeai.common.shared;

import com.maxmpz.poweramp.player.RouterConsts;
import com.maxmpz.poweramp.player.TrackProviderConsts;
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
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B7\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\rJ\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\tHÆ\u0003J)\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J!\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$HÇ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006'"}, d2 = {"Lcom/google/ai/client/generativeai/common/shared/SafetySetting;", "", "seen1", "", "category", "Lcom/google/ai/client/generativeai/common/shared/HarmCategory;", "threshold", "Lcom/google/ai/client/generativeai/common/shared/HarmBlockThreshold;", TrackProviderConsts.COLUMN_HTTP_METHOD, "Lcom/google/ai/client/generativeai/common/shared/HarmBlockMethod;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/google/ai/client/generativeai/common/shared/HarmCategory;Lcom/google/ai/client/generativeai/common/shared/HarmBlockThreshold;Lcom/google/ai/client/generativeai/common/shared/HarmBlockMethod;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/google/ai/client/generativeai/common/shared/HarmCategory;Lcom/google/ai/client/generativeai/common/shared/HarmBlockThreshold;Lcom/google/ai/client/generativeai/common/shared/HarmBlockMethod;)V", "getCategory", "()Lcom/google/ai/client/generativeai/common/shared/HarmCategory;", "getMethod", "()Lcom/google/ai/client/generativeai/common/shared/HarmBlockMethod;", "getThreshold", "()Lcom/google/ai/client/generativeai/common/shared/HarmBlockThreshold;", "component1", "component2", "component3", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class SafetySetting {
    private final HarmCategory category;
    private final HarmBlockMethod method;
    private final HarmBlockThreshold threshold;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {null, HarmBlockThreshold.INSTANCE.serializer(), HarmBlockMethod.INSTANCE.serializer()};

    public static /* synthetic */ SafetySetting copy$default(SafetySetting safetySetting, HarmCategory harmCategory, HarmBlockThreshold harmBlockThreshold, HarmBlockMethod harmBlockMethod, int i, Object obj) {
        if ((i & 1) != 0) {
            harmCategory = safetySetting.category;
        }
        if ((i & 2) != 0) {
            harmBlockThreshold = safetySetting.threshold;
        }
        if ((i & 4) != 0) {
            harmBlockMethod = safetySetting.method;
        }
        return safetySetting.copy(harmCategory, harmBlockThreshold, harmBlockMethod);
    }

    /* renamed from: component1, reason: from getter */
    public final HarmCategory getCategory() {
        return this.category;
    }

    /* renamed from: component2, reason: from getter */
    public final HarmBlockThreshold getThreshold() {
        return this.threshold;
    }

    /* renamed from: component3, reason: from getter */
    public final HarmBlockMethod getMethod() {
        return this.method;
    }

    public final SafetySetting copy(HarmCategory category, HarmBlockThreshold threshold, HarmBlockMethod method) {
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(threshold, "threshold");
        return new SafetySetting(category, threshold, method);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SafetySetting)) {
            return false;
        }
        SafetySetting safetySetting = (SafetySetting) other;
        return this.category == safetySetting.category && this.threshold == safetySetting.threshold && this.method == safetySetting.method;
    }

    public int hashCode() {
        return (((this.category.hashCode() * 31) + this.threshold.hashCode()) * 31) + (this.method == null ? 0 : this.method.hashCode());
    }

    public String toString() {
        return "SafetySetting(category=" + this.category + ", threshold=" + this.threshold + ", method=" + this.method + ")";
    }

    /* compiled from: Types.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/shared/SafetySetting$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/shared/SafetySetting;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SafetySetting> serializer() {
            return SafetySetting$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ SafetySetting(int seen1, HarmCategory category, HarmBlockThreshold threshold, HarmBlockMethod method, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (seen1 & 3)) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 3, SafetySetting$$serializer.INSTANCE.getDescriptor());
        }
        this.category = category;
        this.threshold = threshold;
        if ((seen1 & 4) == 0) {
            this.method = null;
        } else {
            this.method = method;
        }
    }

    public SafetySetting(HarmCategory category, HarmBlockThreshold threshold, HarmBlockMethod method) {
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(threshold, "threshold");
        this.category = category;
        this.threshold = threshold;
        this.method = method;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(SafetySetting self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        output.encodeSerializableElement(serialDesc, 0, HarmCategorySerializer.INSTANCE, self.category);
        output.encodeSerializableElement(serialDesc, 1, kSerializerArr[1], self.threshold);
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.method != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, kSerializerArr[2], self.method);
        }
    }

    public /* synthetic */ SafetySetting(HarmCategory harmCategory, HarmBlockThreshold harmBlockThreshold, HarmBlockMethod harmBlockMethod, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(harmCategory, harmBlockThreshold, (i & 4) != 0 ? null : harmBlockMethod);
    }

    public final HarmCategory getCategory() {
        return this.category;
    }

    public final HarmBlockThreshold getThreshold() {
        return this.threshold;
    }

    public final HarmBlockMethod getMethod() {
        return this.method;
    }
}
