package com.maxmpz.poweramp.companion;

import androidx.lifecycle.ViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: GenerateViewModel.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/maxmpz/poweramp/companion/GenerateViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "generatedTracks", "", "Lcom/maxmpz/poweramp/companion/PowerampTrack;", "getGeneratedTracks", "()Ljava/util/List;", "setGeneratedTracks", "(Ljava/util/List;)V", "lastPrompt", "", "getLastPrompt", "()Ljava/lang/String;", "setLastPrompt", "(Ljava/lang/String;)V", "isGenerating", "", "()Z", "setGenerating", "(Z)V", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class GenerateViewModel extends ViewModel {
    private boolean isGenerating;
    private List<PowerampTrack> generatedTracks = CollectionsKt.emptyList();
    private String lastPrompt = "";

    public final List<PowerampTrack> getGeneratedTracks() {
        return this.generatedTracks;
    }

    public final void setGeneratedTracks(List<PowerampTrack> list) {
        this.generatedTracks = list;
    }

    public final String getLastPrompt() {
        return this.lastPrompt;
    }

    public final void setLastPrompt(String str) {
        this.lastPrompt = str;
    }

    /* renamed from: isGenerating, reason: from getter */
    public final boolean getIsGenerating() {
        return this.isGenerating;
    }

    public final void setGenerating(boolean z) {
        this.isGenerating = z;
    }
}
