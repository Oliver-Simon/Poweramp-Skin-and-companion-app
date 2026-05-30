package com.google.ai.client.generativeai.type;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Part.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/google/ai/client/generativeai/type/BlobPart;", "Lcom/google/ai/client/generativeai/type/Part;", "mimeType", "", "blob", "", "(Ljava/lang/String;[B)V", "getBlob", "()[B", "getMimeType", "()Ljava/lang/String;", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BlobPart implements Part {
    private final byte[] blob;
    private final String mimeType;

    public BlobPart(String mimeType, byte[] blob) {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(blob, "blob");
        this.mimeType = mimeType;
        this.blob = blob;
    }

    public final byte[] getBlob() {
        return this.blob;
    }

    public final String getMimeType() {
        return this.mimeType;
    }
}
