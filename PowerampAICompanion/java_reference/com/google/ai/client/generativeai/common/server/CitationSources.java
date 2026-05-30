package com.google.ai.client.generativeai.common.server;

import com.maxmpz.poweramp.player.RouterConsts;
import com.maxmpz.poweramp.player.TableDefs;
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
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0002$%B=\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB+\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\fJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003J3\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001J!\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#HÇ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006&"}, d2 = {"Lcom/google/ai/client/generativeai/common/server/CitationSources;", "", "seen1", "", "startIndex", "endIndex", TableDefs.SettingsSearchHistory.URI, "", "license", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(IILjava/lang/String;Ljava/lang/String;)V", "getEndIndex", "()I", "getLicense", "()Ljava/lang/String;", "getStartIndex", "getUri", "component1", "component2", "component3", "component4", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class CitationSources {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int endIndex;
    private final String license;
    private final int startIndex;
    private final String uri;

    public static /* synthetic */ CitationSources copy$default(CitationSources citationSources, int i, int i2, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = citationSources.startIndex;
        }
        if ((i3 & 2) != 0) {
            i2 = citationSources.endIndex;
        }
        if ((i3 & 4) != 0) {
            str = citationSources.uri;
        }
        if ((i3 & 8) != 0) {
            str2 = citationSources.license;
        }
        return citationSources.copy(i, i2, str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getStartIndex() {
        return this.startIndex;
    }

    /* renamed from: component2, reason: from getter */
    public final int getEndIndex() {
        return this.endIndex;
    }

    /* renamed from: component3, reason: from getter */
    public final String getUri() {
        return this.uri;
    }

    /* renamed from: component4, reason: from getter */
    public final String getLicense() {
        return this.license;
    }

    public final CitationSources copy(int startIndex, int endIndex, String uri, String license) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return new CitationSources(startIndex, endIndex, uri, license);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CitationSources)) {
            return false;
        }
        CitationSources citationSources = (CitationSources) other;
        return this.startIndex == citationSources.startIndex && this.endIndex == citationSources.endIndex && Intrinsics.areEqual(this.uri, citationSources.uri) && Intrinsics.areEqual(this.license, citationSources.license);
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.startIndex) * 31) + Integer.hashCode(this.endIndex)) * 31) + this.uri.hashCode()) * 31) + (this.license == null ? 0 : this.license.hashCode());
    }

    public String toString() {
        return "CitationSources(startIndex=" + this.startIndex + ", endIndex=" + this.endIndex + ", uri=" + this.uri + ", license=" + this.license + ")";
    }

    /* compiled from: Types.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/server/CitationSources$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/server/CitationSources;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<CitationSources> serializer() {
            return CitationSources$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CitationSources(int seen1, int startIndex, int endIndex, String uri, String license, SerializationConstructorMarker serializationConstructorMarker) {
        if (6 != (seen1 & 6)) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 6, CitationSources$$serializer.INSTANCE.getDescriptor());
        }
        if ((seen1 & 1) == 0) {
            this.startIndex = 0;
        } else {
            this.startIndex = startIndex;
        }
        this.endIndex = endIndex;
        this.uri = uri;
        if ((seen1 & 8) == 0) {
            this.license = null;
        } else {
            this.license = license;
        }
    }

    public CitationSources(int startIndex, int endIndex, String uri, String license) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.uri = uri;
        this.license = license;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(CitationSources self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.startIndex != 0) {
            output.encodeIntElement(serialDesc, 0, self.startIndex);
        }
        output.encodeIntElement(serialDesc, 1, self.endIndex);
        output.encodeStringElement(serialDesc, 2, self.uri);
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.license != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.license);
        }
    }

    public /* synthetic */ CitationSources(int i, int i2, String str, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, i2, str, (i3 & 8) != 0 ? null : str2);
    }

    public final int getStartIndex() {
        return this.startIndex;
    }

    public final int getEndIndex() {
        return this.endIndex;
    }

    public final String getUri() {
        return this.uri;
    }

    public final String getLicense() {
        return this.license;
    }
}
