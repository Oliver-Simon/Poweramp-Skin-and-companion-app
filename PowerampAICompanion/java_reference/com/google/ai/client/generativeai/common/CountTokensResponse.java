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
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* compiled from: Response.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B+\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\u0019\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ$\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J!\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 HÇ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006#"}, d2 = {"Lcom/google/ai/client/generativeai/common/CountTokensResponse;", "Lcom/google/ai/client/generativeai/common/Response;", "seen1", "", "totalTokens", "totalBillableCharacters", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ILjava/lang/Integer;)V", "getTotalBillableCharacters", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTotalTokens", "()I", "component1", "component2", "copy", "(ILjava/lang/Integer;)Lcom/google/ai/client/generativeai/common/CountTokensResponse;", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class CountTokensResponse implements Response {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Integer totalBillableCharacters;
    private final int totalTokens;

    public static /* synthetic */ CountTokensResponse copy$default(CountTokensResponse countTokensResponse, int i, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = countTokensResponse.totalTokens;
        }
        if ((i2 & 2) != 0) {
            num = countTokensResponse.totalBillableCharacters;
        }
        return countTokensResponse.copy(i, num);
    }

    /* renamed from: component1, reason: from getter */
    public final int getTotalTokens() {
        return this.totalTokens;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getTotalBillableCharacters() {
        return this.totalBillableCharacters;
    }

    public final CountTokensResponse copy(int totalTokens, Integer totalBillableCharacters) {
        return new CountTokensResponse(totalTokens, totalBillableCharacters);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CountTokensResponse)) {
            return false;
        }
        CountTokensResponse countTokensResponse = (CountTokensResponse) other;
        return this.totalTokens == countTokensResponse.totalTokens && Intrinsics.areEqual(this.totalBillableCharacters, countTokensResponse.totalBillableCharacters);
    }

    public int hashCode() {
        return (Integer.hashCode(this.totalTokens) * 31) + (this.totalBillableCharacters == null ? 0 : this.totalBillableCharacters.hashCode());
    }

    public String toString() {
        return "CountTokensResponse(totalTokens=" + this.totalTokens + ", totalBillableCharacters=" + this.totalBillableCharacters + ")";
    }

    /* compiled from: Response.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/CountTokensResponse$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/CountTokensResponse;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<CountTokensResponse> serializer() {
            return CountTokensResponse$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CountTokensResponse(int seen1, int totalTokens, Integer totalBillableCharacters, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (seen1 & 1)) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 1, CountTokensResponse$$serializer.INSTANCE.getDescriptor());
        }
        this.totalTokens = totalTokens;
        if ((seen1 & 2) == 0) {
            this.totalBillableCharacters = null;
        } else {
            this.totalBillableCharacters = totalBillableCharacters;
        }
    }

    public CountTokensResponse(int totalTokens, Integer totalBillableCharacters) {
        this.totalTokens = totalTokens;
        this.totalBillableCharacters = totalBillableCharacters;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(CountTokensResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeIntElement(serialDesc, 0, self.totalTokens);
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.totalBillableCharacters != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.totalBillableCharacters);
        }
    }

    public /* synthetic */ CountTokensResponse(int i, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : num);
    }

    public final Integer getTotalBillableCharacters() {
        return this.totalBillableCharacters;
    }

    public final int getTotalTokens() {
        return this.totalTokens;
    }
}
