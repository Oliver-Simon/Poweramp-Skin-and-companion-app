package com.google.ai.client.generativeai.common.shared;

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

/* compiled from: Types.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B1\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J!\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fHÇ\u0001R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006\""}, d2 = {"Lcom/google/ai/client/generativeai/common/shared/FileData;", "", "seen1", "", "mimeType", "", "fileUri", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "getFileUri$annotations", "()V", "getFileUri", "()Ljava/lang/String;", "getMimeType$annotations", "getMimeType", "component1", "component2", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* loaded from: classes.dex */
public final /* data */ class FileData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String fileUri;
    private final String mimeType;

    public static /* synthetic */ FileData copy$default(FileData fileData, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = fileData.mimeType;
        }
        if ((i & 2) != 0) {
            str2 = fileData.fileUri;
        }
        return fileData.copy(str, str2);
    }

    @SerialName("file_uri")
    public static /* synthetic */ void getFileUri$annotations() {
    }

    @SerialName("mime_type")
    public static /* synthetic */ void getMimeType$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getMimeType() {
        return this.mimeType;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFileUri() {
        return this.fileUri;
    }

    public final FileData copy(String mimeType, String fileUri) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(fileUri, "fileUri");
        return new FileData(mimeType, fileUri);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FileData)) {
            return false;
        }
        FileData fileData = (FileData) other;
        return Intrinsics.areEqual(this.mimeType, fileData.mimeType) && Intrinsics.areEqual(this.fileUri, fileData.fileUri);
    }

    public int hashCode() {
        return (this.mimeType.hashCode() * 31) + this.fileUri.hashCode();
    }

    public String toString() {
        return "FileData(mimeType=" + this.mimeType + ", fileUri=" + this.fileUri + ")";
    }

    /* compiled from: Types.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/google/ai/client/generativeai/common/shared/FileData$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/ai/client/generativeai/common/shared/FileData;", "common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<FileData> serializer() {
            return FileData$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ FileData(int seen1, @SerialName("mime_type") String mimeType, @SerialName("file_uri") String fileUri, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (seen1 & 3)) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 3, FileData$$serializer.INSTANCE.getDescriptor());
        }
        this.mimeType = mimeType;
        this.fileUri = fileUri;
    }

    public FileData(String mimeType, String fileUri) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(fileUri, "fileUri");
        this.mimeType = mimeType;
        this.fileUri = fileUri;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(FileData self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.mimeType);
        output.encodeStringElement(serialDesc, 1, self.fileUri);
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final String getFileUri() {
        return this.fileUri;
    }
}
