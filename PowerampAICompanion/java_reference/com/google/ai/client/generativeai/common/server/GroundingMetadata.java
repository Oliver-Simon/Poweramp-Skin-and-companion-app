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
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 -2\u00020\u0001:\u0002,-B[\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0001\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0010\b\u0001\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0010\b\u0001\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eB?\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005¢\u0006\u0002\u0010\u000fJ\u0011\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0011\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005HÆ\u0003JK\u0010\u001f\u001a\u00020\u00002\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0003HÖ\u0001J\t\u0010$\u001a\u00020\u0006HÖ\u0001J!\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00002\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+HÇ\u0001R$\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R$\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0018R$\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u001a\u0010\u0013¨\u0006."}, d2 = {"Lcom/google/ai/client/generativeai/common/server/GroundingMetadata;", "", "seen1", "", "webSearchQueries", "", "", "searchEntryPoint", "Lcom/google/ai/client/generativeai/common/server/SearchEntryPoint;", "retrievalQueries", "groundingAttribution", "Lcom/google/ai/client/generativeai/common/server/GroundingAttribution;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lcom/google/ai/client/generativeai/common/server/SearchEntryPoint;Ljava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/util/List;Lcom/google/ai/client/generativeai/common/server/SearchEntryPoint;Ljava/util/List;Ljava/util/List;)V", "getGroundingAttribution$annotations", "()V", "getGroundingAttribution", "()Ljava/util/List;", "getRetrievalQueries$annotations", "getRetrievalQueries", "getSearchEntryPoint$annotations", "getSearchEntryPoint", "()Lcom/google/ai/client/generativeai/common/server/SearchEntryPoint;", "getWebSearchQueries$annotations", "getWebSearchQueries", "component1", "component2", "component3", "component4", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class GroundingMetadata {
    private final List<GroundingAttribution> groundingAttribution;
    private final List<String> retrievalQueries;
    private final SearchEntryPoint searchEntryPoint;
    private final List<String> webSearchQueries;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer<Object>[] $childSerializers = {new ArrayListSerializer(StringSerializer.INSTANCE), null, new ArrayListSerializer(StringSerializer.INSTANCE), new ArrayListSerializer(GroundingAttribution$$serializer.INSTANCE)};

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GroundingMetadata copy$default(GroundingMetadata groundingMetadata, List list, SearchEntryPoint searchEntryPoint, List list2, List list3, int i, Object obj) {
        if ((i & 1) != 0) {
            list = groundingMetadata.webSearchQueries;
        }
        if ((i & 2) != 0) {
            searchEntryPoint = groundingMetadata.searchEntryPoint;
        }
        if ((i & 4) != 0) {
            list2 = groundingMetadata.retrievalQueries;
        }
        if ((i & 8) != 0) {
            list3 = groundingMetadata.groundingAttribution;
        }
        return groundingMetadata.copy(list, searchEntryPoint, list2, list3);
    }

    @SerialName("grounding_attribution")
    public static /* synthetic */ void getGroundingAttribution$annotations() {
    }

    @SerialName("retrieval_queries")
    public static /* synthetic */ void getRetrievalQueries$annotations() {
    }

    @SerialName("search_entry_point")
    public static /* synthetic */ void getSearchEntryPoint$annotations() {
    }

    @SerialName("web_search_queries")
    public static /* synthetic */ void getWebSearchQueries$annotations() {
    }

    public final List<String> component1() {
        return this.webSearchQueries;
    }

    /* renamed from: component2, reason: from getter */
    public final SearchEntryPoint getSearchEntryPoint() {
        return this.searchEntryPoint;
    }

    public final List<String> component3() {
        return this.retrievalQueries;
    }

    public final List<GroundingAttribution> component4() {
        return this.groundingAttribution;
    }

    public final GroundingMetadata copy(List<String> webSearchQueries, SearchEntryPoint searchEntryPoint, List<String> retrievalQueries, List<GroundingAttribution> groundingAttribution) {
        return new GroundingMetadata(webSearchQueries, searchEntryPoint, retrievalQueries, groundingAttribution);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GroundingMetadata)) {
            return false;
        }
        GroundingMetadata groundingMetadata = (GroundingMetadata) other;
        return Intrinsics.areEqual(this.webSearchQueries, groundingMetadata.webSearchQueries) && Intrinsics.areEqual(this.searchEntryPoint, groundingMetadata.searchEntryPoint) && Intrinsics.areEqual(this.retrievalQueries, groundingMetadata.retrievalQueries) && Intrinsics.areEqual(this.groundingAttribution, groundingMetadata.groundingAttribution);
    }

    public int hashCode() {
        return ((((((this.webSearchQueries == null ? 0 : this.webSearchQueries.hashCode()) * 31) + (this.searchEntryPoint == null ? 0 : this.searchEntryPoint.hashCode())) * 31) + (this.retrievalQueries == null ? 0 : this.retrievalQueries.hashCode())) * 31) + (this.groundingAttribution != null ? this.groundingAttribution.hashCode() : 0);
    }

    public String toString() {
        return "GroundingMetadata(webSearchQueries=" + this.webSearchQueries + ", searchEntryPoint=" + this.searchEntryPoint + ", retrievalQueries=" + this.retrievalQueries + ", groundingAttribution=" + this.groundingAttribution + ")";
    }

    /* compiled from: Types.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/server/GroundingMetadata$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/server/GroundingMetadata;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<GroundingMetadata> serializer() {
            return GroundingMetadata$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ GroundingMetadata(int seen1, @SerialName("web_search_queries") List webSearchQueries, @SerialName("search_entry_point") SearchEntryPoint searchEntryPoint, @SerialName("retrieval_queries") List retrievalQueries, @SerialName("grounding_attribution") List groundingAttribution, SerializationConstructorMarker serializationConstructorMarker) {
        if (15 != (seen1 & 15)) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 15, GroundingMetadata$$serializer.INSTANCE.getDescriptor());
        }
        this.webSearchQueries = webSearchQueries;
        this.searchEntryPoint = searchEntryPoint;
        this.retrievalQueries = retrievalQueries;
        this.groundingAttribution = groundingAttribution;
    }

    public GroundingMetadata(List<String> list, SearchEntryPoint searchEntryPoint, List<String> list2, List<GroundingAttribution> list3) {
        this.webSearchQueries = list;
        this.searchEntryPoint = searchEntryPoint;
        this.retrievalQueries = list2;
        this.groundingAttribution = list3;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(GroundingMetadata self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        output.encodeNullableSerializableElement(serialDesc, 0, kSerializerArr[0], self.webSearchQueries);
        output.encodeNullableSerializableElement(serialDesc, 1, SearchEntryPoint$$serializer.INSTANCE, self.searchEntryPoint);
        output.encodeNullableSerializableElement(serialDesc, 2, kSerializerArr[2], self.retrievalQueries);
        output.encodeNullableSerializableElement(serialDesc, 3, kSerializerArr[3], self.groundingAttribution);
    }

    public final List<String> getWebSearchQueries() {
        return this.webSearchQueries;
    }

    public final SearchEntryPoint getSearchEntryPoint() {
        return this.searchEntryPoint;
    }

    public final List<String> getRetrievalQueries() {
        return this.retrievalQueries;
    }

    public final List<GroundingAttribution> getGroundingAttribution() {
        return this.groundingAttribution;
    }
}
