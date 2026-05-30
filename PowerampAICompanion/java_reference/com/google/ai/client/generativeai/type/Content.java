package com.google.ai.client.generativeai.type;

import android.graphics.Bitmap;
import androidx.exifinterface.media.ExifInterface;
import com.maxmpz.poweramp.player.TableDefs;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Content.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\fB!\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/google/ai/client/generativeai/type/Content;", "", "role", "", "parts", "", "Lcom/google/ai/client/generativeai/type/Part;", "(Ljava/lang/String;Ljava/util/List;)V", "getParts", "()Ljava/util/List;", "getRole", "()Ljava/lang/String;", "Builder", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Content {
    private final List<Part> parts;
    private final String role;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public Content(List<? extends Part> parts) {
        this(null, parts, 1, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(parts, "parts");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Content(String role, List<? extends Part> parts) {
        Intrinsics.checkNotNullParameter(parts, "parts");
        this.role = role;
        this.parts = parts;
    }

    public /* synthetic */ Content(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "user" : str, list);
    }

    public final List<Part> getParts() {
        return this.parts;
    }

    public final String getRole() {
        return this.role;
    }

    /* compiled from: Content.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0012H\u0007¢\u0006\u0002\b\u0013J\u0006\u0010\u0014\u001a\u00020\u0015J\u001d\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000bH\u0007¢\u0006\u0002\b\u0018J\u0015\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001aH\u0007¢\u0006\u0002\b\u001bJ!\u0010\u001c\u001a\u00020\u0000\"\b\b\u0000\u0010\u001d*\u00020\u00052\u0006\u0010\u001e\u001a\u0002H\u001dH\u0007¢\u0006\u0004\b\u001f\u0010 J\u0015\u0010!\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u000bH\u0007¢\u0006\u0002\b\"R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006#"}, d2 = {"Lcom/google/ai/client/generativeai/type/Content$Builder;", "", "()V", "parts", "", "Lcom/google/ai/client/generativeai/type/Part;", "getParts", "()Ljava/util/List;", "setParts", "(Ljava/util/List;)V", "role", "", "getRole", "()Ljava/lang/String;", "setRole", "(Ljava/lang/String;)V", "blob", "mimeType", "", "addBlob", "build", "Lcom/google/ai/client/generativeai/type/Content;", "fileData", TableDefs.SettingsSearchHistory.URI, "addFileData", "image", "Landroid/graphics/Bitmap;", "addImage", "part", ExifInterface.GPS_DIRECTION_TRUE, "data", "addPart", "(Lcom/google/ai/client/generativeai/type/Part;)Lcom/google/ai/client/generativeai/type/Content$Builder;", "text", "addText", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Builder {
        private String role = "user";
        private List<Part> parts = new ArrayList();

        public final String getRole() {
            return this.role;
        }

        public final void setRole(String str) {
            this.role = str;
        }

        public final List<Part> getParts() {
            return this.parts;
        }

        public final void setParts(List<Part> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.parts = list;
        }

        public final <T extends Part> Builder addPart(T data) {
            Intrinsics.checkNotNullParameter(data, "data");
            Builder $this$part_u24lambda_u240 = this;
            $this$part_u24lambda_u240.parts.add(data);
            return this;
        }

        public final Builder addText(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            return addPart(new TextPart(text));
        }

        public final Builder addBlob(String mimeType, byte[] blob) {
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            Intrinsics.checkNotNullParameter(blob, "blob");
            return addPart(new BlobPart(mimeType, blob));
        }

        public final Builder addImage(Bitmap image) {
            Intrinsics.checkNotNullParameter(image, "image");
            return addPart(new ImagePart(image));
        }

        public final Builder addFileData(String uri, String mimeType) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            return addPart(new FileDataPart(uri, mimeType));
        }

        public final Content build() {
            return new Content(this.role, this.parts);
        }
    }
}
