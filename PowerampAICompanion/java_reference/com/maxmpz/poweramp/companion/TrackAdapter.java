package com.maxmpz.poweramp.companion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.imageview.ShapeableImageView;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

/* compiled from: TrackAdapter.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001#B}\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\f\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u000eH\u0016J\u0018\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u000eH\u0016J\b\u0010\"\u001a\u00020\u000eH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0016@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006$"}, d2 = {"Lcom/maxmpz/poweramp/companion/TrackAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/maxmpz/poweramp/companion/TrackAdapter$TrackViewHolder;", "tracks", "", "Lcom/maxmpz/poweramp/companion/PowerampTrack;", "onTrackClick", "Lkotlin/Function1;", "", "onQueueClick", "onPlayNextClick", "onLongClick", "Lkotlin/Function0;", "onSelectionChangedBatch", "", "<init>", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "selectedTracks", "", "getSelectedTracks", "()Ljava/util/Set;", "value", "", "isSelectionModeEnabled", "()Z", "setSelectionModeEnabled", "(Z)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onBindViewHolder", "holder", "position", "getItemCount", "TrackViewHolder", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class TrackAdapter extends RecyclerView.Adapter<TrackViewHolder> {
    private boolean isSelectionModeEnabled;
    private final Function0<Unit> onLongClick;
    private final Function1<PowerampTrack, Unit> onPlayNextClick;
    private final Function1<PowerampTrack, Unit> onQueueClick;
    private final Function1<Integer, Unit> onSelectionChangedBatch;
    private final Function1<PowerampTrack, Unit> onTrackClick;
    private final Set<PowerampTrack> selectedTracks;
    private final List<PowerampTrack> tracks;

    public /* synthetic */ TrackAdapter(List list, Function1 function1, Function1 function12, Function1 function13, Function0 function0, Function1 function14, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, function1, function12, (i & 8) != 0 ? new Function1() { // from class: com.maxmpz.poweramp.companion.TrackAdapter$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit _init_$lambda$0;
                _init_$lambda$0 = TrackAdapter._init_$lambda$0((PowerampTrack) obj);
                return _init_$lambda$0;
            }
        } : function13, (i & 16) != 0 ? null : function0, (i & 32) != 0 ? null : function14);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$0(PowerampTrack it) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TrackAdapter(List<PowerampTrack> list, Function1<? super PowerampTrack, Unit> function1, Function1<? super PowerampTrack, Unit> function12, Function1<? super PowerampTrack, Unit> function13, Function0<Unit> function0, Function1<? super Integer, Unit> function14) {
        this.tracks = list;
        this.onTrackClick = function1;
        this.onQueueClick = function12;
        this.onPlayNextClick = function13;
        this.onLongClick = function0;
        this.onSelectionChangedBatch = function14;
        this.selectedTracks = new LinkedHashSet();
    }

    public final Set<PowerampTrack> getSelectedTracks() {
        return this.selectedTracks;
    }

    /* renamed from: isSelectionModeEnabled, reason: from getter */
    public final boolean getIsSelectionModeEnabled() {
        return this.isSelectionModeEnabled;
    }

    public final void setSelectionModeEnabled(boolean value) {
        this.isSelectionModeEnabled = value;
        if (!value) {
            this.selectedTracks.clear();
        }
        notifyDataSetChanged();
        Function1<Integer, Unit> function1 = this.onSelectionChangedBatch;
        if (function1 != null) {
            function1.invoke(Integer.valueOf(this.selectedTracks.size()));
        }
    }

    /* compiled from: TrackAdapter.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/maxmpz/poweramp/companion/TrackAdapter$TrackViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "tvTitle", "Landroid/widget/TextView;", "getTvTitle", "()Landroid/widget/TextView;", "tvArtist", "getTvArtist", "ivAlbumArt", "Lcom/google/android/material/imageview/ShapeableImageView;", "getIvAlbumArt", "()Lcom/google/android/material/imageview/ShapeableImageView;", "btnQueue", "Landroid/widget/ImageButton;", "getBtnQueue", "()Landroid/widget/ImageButton;", "btnPlayNext", "getBtnPlayNext", "cbSelect", "Lcom/google/android/material/checkbox/MaterialCheckBox;", "getCbSelect", "()Lcom/google/android/material/checkbox/MaterialCheckBox;", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public static final class TrackViewHolder extends RecyclerView.ViewHolder {
        private final ImageButton btnPlayNext;
        private final ImageButton btnQueue;
        private final MaterialCheckBox cbSelect;
        private final ShapeableImageView ivAlbumArt;
        private final TextView tvArtist;
        private final TextView tvTitle;

        public TrackViewHolder(View view) {
            super(view);
            this.tvTitle = (TextView) view.findViewById(R.id.tvTrackTitle);
            this.tvArtist = (TextView) view.findViewById(R.id.tvTrackArtist);
            this.ivAlbumArt = (ShapeableImageView) view.findViewById(R.id.ivAlbumArt);
            this.btnQueue = (ImageButton) view.findViewById(R.id.btnQueueTrack);
            this.btnPlayNext = (ImageButton) view.findViewById(R.id.btnPlayNextTrack);
            this.cbSelect = (MaterialCheckBox) view.findViewById(R.id.cbSelectTrack);
        }

        public final TextView getTvTitle() {
            return this.tvTitle;
        }

        public final TextView getTvArtist() {
            return this.tvArtist;
        }

        public final ShapeableImageView getIvAlbumArt() {
            return this.ivAlbumArt;
        }

        public final ImageButton getBtnQueue() {
            return this.btnQueue;
        }

        public final ImageButton getBtnPlayNext() {
            return this.btnPlayNext;
        }

        public final MaterialCheckBox getCbSelect() {
            return this.cbSelect;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_track, parent, false);
        return new TrackViewHolder(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final TrackViewHolder holder, int position) {
        final PowerampTrack track = this.tracks.get(position);
        holder.getTvTitle().setText(track.getTitle());
        String artist = track.getArtist();
        if (artist == null || StringsKt.isBlank(artist)) {
            holder.getTvArtist().setVisibility(8);
        } else {
            holder.getTvArtist().setText(track.getArtist());
            holder.getTvArtist().setVisibility(0);
        }
        Glide.with(holder.itemView.getContext()).load(track.getAlbumArtUri()).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(holder.getIvAlbumArt());
        holder.getBtnQueue().setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.TrackAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrackAdapter.onBindViewHolder$lambda$1(TrackAdapter.this, track, view);
            }
        });
        holder.getBtnPlayNext().setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.TrackAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrackAdapter.onBindViewHolder$lambda$2(TrackAdapter.this, track, view);
            }
        });
        holder.getBtnQueue().setVisibility(this.isSelectionModeEnabled ? 8 : 0);
        holder.getBtnPlayNext().setVisibility(this.isSelectionModeEnabled ? 8 : 0);
        holder.getCbSelect().setOnCheckedChangeListener(null);
        holder.getCbSelect().setVisibility(this.isSelectionModeEnabled ? 0 : 8);
        holder.getCbSelect().setChecked(this.selectedTracks.contains(track));
        holder.getCbSelect().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.maxmpz.poweramp.companion.TrackAdapter$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                TrackAdapter.onBindViewHolder$lambda$3(TrackAdapter.this, track, compoundButton, z);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.TrackAdapter$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrackAdapter.onBindViewHolder$lambda$4(TrackAdapter.this, track, holder, view);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.maxmpz.poweramp.companion.TrackAdapter$$ExternalSyntheticLambda4
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean onBindViewHolder$lambda$5;
                onBindViewHolder$lambda$5 = TrackAdapter.onBindViewHolder$lambda$5(TrackAdapter.this, track, holder, view);
                return onBindViewHolder$lambda$5;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$1(TrackAdapter this$0, PowerampTrack $track, View it) {
        if (!this$0.isSelectionModeEnabled) {
            this$0.onQueueClick.invoke($track);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$2(TrackAdapter this$0, PowerampTrack $track, View it) {
        if (!this$0.isSelectionModeEnabled) {
            this$0.onPlayNextClick.invoke($track);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$3(TrackAdapter this$0, PowerampTrack $track, CompoundButton compoundButton, boolean isChecked) {
        Set<PowerampTrack> set = this$0.selectedTracks;
        if (isChecked) {
            set.add($track);
        } else {
            set.remove($track);
        }
        Function1<Integer, Unit> function1 = this$0.onSelectionChangedBatch;
        if (function1 != null) {
            function1.invoke(Integer.valueOf(this$0.selectedTracks.size()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$4(TrackAdapter this$0, PowerampTrack $track, TrackViewHolder $holder, View it) {
        if (this$0.isSelectionModeEnabled) {
            boolean isNowSelected = !this$0.selectedTracks.contains($track);
            Set<PowerampTrack> set = this$0.selectedTracks;
            if (isNowSelected) {
                set.add($track);
            } else {
                set.remove($track);
            }
            $holder.getCbSelect().setChecked(isNowSelected);
            Function1<Integer, Unit> function1 = this$0.onSelectionChangedBatch;
            if (function1 != null) {
                function1.invoke(Integer.valueOf(this$0.selectedTracks.size()));
                return;
            }
            return;
        }
        this$0.onTrackClick.invoke($track);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$5(TrackAdapter this$0, PowerampTrack $track, TrackViewHolder $holder, View it) {
        if (!this$0.isSelectionModeEnabled) {
            this$0.setSelectionModeEnabled(true);
            this$0.selectedTracks.add($track);
            $holder.getCbSelect().setChecked(true);
            Function0<Unit> function0 = this$0.onLongClick;
            if (function0 != null) {
                function0.invoke();
            }
            Function1<Integer, Unit> function1 = this$0.onSelectionChangedBatch;
            if (function1 != null) {
                function1.invoke(Integer.valueOf(this$0.selectedTracks.size()));
                return true;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.tracks.size();
    }
}
