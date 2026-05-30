package com.maxmpz.poweramp.companion;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.button.MaterialButton;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DiscoveryAdapter.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0014B\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000eH\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/maxmpz/poweramp/companion/DiscoveryAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/maxmpz/poweramp/companion/DiscoveryAdapter$DiscoveryViewHolder;", "items", "", "Lcom/maxmpz/poweramp/companion/DiscoveryItem;", "context", "Landroid/content/Context;", "<init>", "(Ljava/util/List;Landroid/content/Context;)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "", "holder", "position", "getItemCount", "DiscoveryViewHolder", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class DiscoveryAdapter extends RecyclerView.Adapter<DiscoveryViewHolder> {
    private final Context context;
    private final List<DiscoveryItem> items;

    public DiscoveryAdapter(List<DiscoveryItem> list, Context context) {
        this.items = list;
        this.context = context;
    }

    /* compiled from: DiscoveryAdapter.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0010\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\u0012\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/maxmpz/poweramp/companion/DiscoveryAdapter$DiscoveryViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "ivArt", "Landroid/widget/ImageView;", "getIvArt", "()Landroid/widget/ImageView;", "tvTitle", "Landroid/widget/TextView;", "getTvTitle", "()Landroid/widget/TextView;", "tvArtist", "getTvArtist", "tvReason", "getTvReason", "tvBadge", "getTvBadge", "btnYtMusic", "Lcom/google/android/material/button/MaterialButton;", "getBtnYtMusic", "()Lcom/google/android/material/button/MaterialButton;", "btnSpotify", "getBtnSpotify", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public static final class DiscoveryViewHolder extends RecyclerView.ViewHolder {
        private final MaterialButton btnSpotify;
        private final MaterialButton btnYtMusic;
        private final ImageView ivArt;
        private final TextView tvArtist;
        private final TextView tvBadge;
        private final TextView tvReason;
        private final TextView tvTitle;

        public DiscoveryViewHolder(View view) {
            super(view);
            this.ivArt = (ImageView) view.findViewById(R.id.ivDiscoveryArt);
            this.tvTitle = (TextView) view.findViewById(R.id.tvDiscoveryTitle);
            this.tvArtist = (TextView) view.findViewById(R.id.tvDiscoveryArtist);
            this.tvReason = (TextView) view.findViewById(R.id.tvDiscoveryReason);
            this.tvBadge = (TextView) view.findViewById(R.id.tvDiscoveryBadge);
            this.btnYtMusic = (MaterialButton) view.findViewById(R.id.btnYtMusic);
            this.btnSpotify = (MaterialButton) view.findViewById(R.id.btnSpotify);
        }

        public final ImageView getIvArt() {
            return this.ivArt;
        }

        public final TextView getTvTitle() {
            return this.tvTitle;
        }

        public final TextView getTvArtist() {
            return this.tvArtist;
        }

        public final TextView getTvReason() {
            return this.tvReason;
        }

        public final TextView getTvBadge() {
            return this.tvBadge;
        }

        public final MaterialButton getBtnYtMusic() {
            return this.btnYtMusic;
        }

        public final MaterialButton getBtnSpotify() {
            return this.btnSpotify;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public DiscoveryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discovery_card, parent, false);
        return new DiscoveryViewHolder(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(DiscoveryViewHolder holder, int position) {
        final DiscoveryItem item = this.items.get(position);
        holder.getTvTitle().setText(item.getTitle());
        holder.getTvArtist().setText(item.getArtist());
        holder.getTvReason().setText(item.getReason());
        holder.getTvBadge().setText(Intrinsics.areEqual(item.getType(), "ALBUM") ? "ALBUM-RADAR" : "SONG-RADAR");
        Glide.with(this.context).load(item.getImageUrl()).placeholder(android.R.drawable.ic_menu_report_image).error(android.R.drawable.ic_menu_report_image).transition(DrawableTransitionOptions.withCrossFade()).into(holder.getIvArt());
        holder.getBtnYtMusic().setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.DiscoveryAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiscoveryAdapter.onBindViewHolder$lambda$1(DiscoveryItem.this, this, view);
            }
        });
        holder.getBtnSpotify().setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.DiscoveryAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiscoveryAdapter.onBindViewHolder$lambda$3(DiscoveryItem.this, this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$1(DiscoveryItem $item, DiscoveryAdapter this$0, View it) {
        String query = $item.getArtist() + " " + $item.getTitle();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://music.youtube.com/search?q=" + Uri.encode(query)));
        intent.setPackage("com.google.android.apps.youtube.music");
        try {
            this$0.context.startActivity(intent);
        } catch (Exception e) {
            Intent webIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://music.youtube.com/search?q=" + Uri.encode(query)));
            this$0.context.startActivity(webIntent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$3(DiscoveryItem $item, DiscoveryAdapter this$0, View it) {
        String query = "track:" + $item.getTitle() + " artist:" + $item.getArtist();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("spotify:search:" + Uri.encode(query)));
        try {
            this$0.context.startActivity(intent);
        } catch (Exception e) {
            Intent webIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://open.spotify.com/search/" + Uri.encode(query)));
            this$0.context.startActivity(webIntent);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }
}
