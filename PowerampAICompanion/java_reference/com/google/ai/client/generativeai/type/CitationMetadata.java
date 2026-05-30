package com.google.ai.client.generativeai.type;

import com.maxmpz.poweramp.player.TableDefs;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Candidate.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B+\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/google/ai/client/generativeai/type/CitationMetadata;", "", "startIndex", "", "endIndex", TableDefs.SettingsSearchHistory.URI, "", "license", "(IILjava/lang/String;Ljava/lang/String;)V", "getEndIndex", "()I", "getLicense", "()Ljava/lang/String;", "getStartIndex", "getUri", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CitationMetadata {
    private final int endIndex;
    private final String license;
    private final int startIndex;
    private final String uri;

    public CitationMetadata(int startIndex, int endIndex, String uri, String license) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.uri = uri;
        this.license = license;
    }

    public /* synthetic */ CitationMetadata(int i, int i2, String str, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
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
