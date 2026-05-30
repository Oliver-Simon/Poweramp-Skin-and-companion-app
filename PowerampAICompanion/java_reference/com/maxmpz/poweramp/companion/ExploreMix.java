package com.maxmpz.poweramp.companion;

import com.maxmpz.poweramp.player.RouterConsts;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExploreMixAdapter.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/maxmpz/poweramp/companion/ExploreMix;", "", "title", "", "subtitle", "prompt", "iconRes", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getTitle", "()Ljava/lang/String;", "getSubtitle", "getPrompt", "getIconRes", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "toString", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class ExploreMix {
    private final int iconRes;
    private final String prompt;
    private final String subtitle;
    private final String title;

    public static /* synthetic */ ExploreMix copy$default(ExploreMix exploreMix, String str, String str2, String str3, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = exploreMix.title;
        }
        if ((i2 & 2) != 0) {
            str2 = exploreMix.subtitle;
        }
        if ((i2 & 4) != 0) {
            str3 = exploreMix.prompt;
        }
        if ((i2 & 8) != 0) {
            i = exploreMix.iconRes;
        }
        return exploreMix.copy(str, str2, str3, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSubtitle() {
        return this.subtitle;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPrompt() {
        return this.prompt;
    }

    /* renamed from: component4, reason: from getter */
    public final int getIconRes() {
        return this.iconRes;
    }

    public final ExploreMix copy(String title, String subtitle, String prompt, int iconRes) {
        return new ExploreMix(title, subtitle, prompt, iconRes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExploreMix)) {
            return false;
        }
        ExploreMix exploreMix = (ExploreMix) other;
        return Intrinsics.areEqual(this.title, exploreMix.title) && Intrinsics.areEqual(this.subtitle, exploreMix.subtitle) && Intrinsics.areEqual(this.prompt, exploreMix.prompt) && this.iconRes == exploreMix.iconRes;
    }

    public int hashCode() {
        return (((((this.title.hashCode() * 31) + this.subtitle.hashCode()) * 31) + this.prompt.hashCode()) * 31) + Integer.hashCode(this.iconRes);
    }

    public String toString() {
        return "ExploreMix(title=" + this.title + ", subtitle=" + this.subtitle + ", prompt=" + this.prompt + ", iconRes=" + this.iconRes + ")";
    }

    public ExploreMix(String title, String subtitle, String prompt, int iconRes) {
        this.title = title;
        this.subtitle = subtitle;
        this.prompt = prompt;
        this.iconRes = iconRes;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final String getPrompt() {
        return this.prompt;
    }

    public final int getIconRes() {
        return this.iconRes;
    }
}
