package com.google.ai.client.generativeai.common.server;

import com.maxmpz.poweramp.player.RouterConsts;
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
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B1\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0019\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\nJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J!\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fHÇ\u0001R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006\""}, d2 = {"Lcom/google/ai/client/generativeai/common/server/SearchEntryPoint;", "", "seen1", "", "renderedContent", "", "sdkBlob", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "getRenderedContent$annotations", "()V", "getRenderedContent", "()Ljava/lang/String;", "getSdkBlob$annotations", "getSdkBlob", "component1", "component2", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class SearchEntryPoint {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String renderedContent;
    private final String sdkBlob;

    public static /* synthetic */ SearchEntryPoint copy$default(SearchEntryPoint searchEntryPoint, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = searchEntryPoint.renderedContent;
        }
        if ((i & 2) != 0) {
            str2 = searchEntryPoint.sdkBlob;
        }
        return searchEntryPoint.copy(str, str2);
    }

    @SerialName("rendered_content")
    public static /* synthetic */ void getRenderedContent$annotations() {
    }

    @SerialName("sdk_blob")
    public static /* synthetic */ void getSdkBlob$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getRenderedContent() {
        return this.renderedContent;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSdkBlob() {
        return this.sdkBlob;
    }

    public final SearchEntryPoint copy(String renderedContent, String sdkBlob) {
        return new SearchEntryPoint(renderedContent, sdkBlob);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchEntryPoint)) {
            return false;
        }
        SearchEntryPoint searchEntryPoint = (SearchEntryPoint) other;
        return Intrinsics.areEqual(this.renderedContent, searchEntryPoint.renderedContent) && Intrinsics.areEqual(this.sdkBlob, searchEntryPoint.sdkBlob);
    }

    public int hashCode() {
        return ((this.renderedContent == null ? 0 : this.renderedContent.hashCode()) * 31) + (this.sdkBlob != null ? this.sdkBlob.hashCode() : 0);
    }

    public String toString() {
        return "SearchEntryPoint(renderedContent=" + this.renderedContent + ", sdkBlob=" + this.sdkBlob + ")";
    }

    /* compiled from: Types.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/server/SearchEntryPoint$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/server/SearchEntryPoint;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SearchEntryPoint> serializer() {
            return SearchEntryPoint$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ SearchEntryPoint(int seen1, @SerialName("rendered_content") String renderedContent, @SerialName("sdk_blob") String sdkBlob, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (seen1 & 3)) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 3, SearchEntryPoint$$serializer.INSTANCE.getDescriptor());
        }
        this.renderedContent = renderedContent;
        this.sdkBlob = sdkBlob;
    }

    public SearchEntryPoint(String renderedContent, String sdkBlob) {
        this.renderedContent = renderedContent;
        this.sdkBlob = sdkBlob;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(SearchEntryPoint self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.renderedContent);
        output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.sdkBlob);
    }

    public final String getRenderedContent() {
        return this.renderedContent;
    }

    public final String getSdkBlob() {
        return this.sdkBlob;
    }
}
