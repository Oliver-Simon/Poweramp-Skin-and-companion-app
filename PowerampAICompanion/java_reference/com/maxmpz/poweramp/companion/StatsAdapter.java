package com.maxmpz.poweramp.companion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.maxmpz.poweramp.companion.StatsEngine;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: StatsAdapter.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010#\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 +2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002*+By\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f\u0012\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000eH\u0016J\u0018\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u000eH\u0016J\u0018\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u000eH\u0016J\u000e\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\u0005J\u0018\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u000eH\u0002J\b\u0010'\u001a\u00020\u000eH\u0016J\u0014\u0010(\u001a\u00020\b2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006,"}, d2 = {"Lcom/maxmpz/poweramp/companion/StatsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/maxmpz/poweramp/companion/StatsAdapter$ViewHolder;", "items", "", "Lcom/maxmpz/poweramp/companion/StatsEngine$StatItem;", "onTrackClick", "Lkotlin/Function1;", "", "onQueueClick", "onPlayNextClick", "onLongClick", "Lkotlin/Function0;", "onSelectionChangedBatch", "", "<init>", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "value", "", "isGridView", "()Z", "setGridView", "(Z)V", "isSelectionMode", "setSelectionMode", "selectedItems", "", "getSelectedItems", "()Ljava/util/Set;", "getItemViewType", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onBindViewHolder", "holder", "toggleSelection", "item", "getItemCount", "updateData", "newItems", "ViewHolder", "Companion", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class StatsAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int VIEW_TYPE_GRID = 1;
    private static final int VIEW_TYPE_LIST = 0;
    private boolean isGridView;
    private boolean isSelectionMode;
    private List<StatsEngine.StatItem> items;
    private final Function0<Unit> onLongClick;
    private final Function1<StatsEngine.StatItem, Unit> onPlayNextClick;
    private final Function1<StatsEngine.StatItem, Unit> onQueueClick;
    private final Function1<Integer, Unit> onSelectionChangedBatch;
    private final Function1<StatsEngine.StatItem, Unit> onTrackClick;
    private final Set<StatsEngine.StatItem> selectedItems;

    public /* synthetic */ StatsAdapter(List list, Function1 function1, Function1 function12, Function1 function13, Function0 function0, Function1 function14, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, function1, function12, (i & 8) != 0 ? new Function1() { // from class: com.maxmpz.poweramp.companion.StatsAdapter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit _init_$lambda$0;
                _init_$lambda$0 = StatsAdapter._init_$lambda$0((StatsEngine.StatItem) obj);
                return _init_$lambda$0;
            }
        } : function13, (i & 16) != 0 ? new Function0() { // from class: com.maxmpz.poweramp.companion.StatsAdapter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit unit;
                unit = Unit.INSTANCE;
                return unit;
            }
        } : function0, (i & 32) != 0 ? new Function1() { // from class: com.maxmpz.poweramp.companion.StatsAdapter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit _init_$lambda$2;
                _init_$lambda$2 = StatsAdapter._init_$lambda$2(((Integer) obj).intValue());
                return _init_$lambda$2;
            }
        } : function14);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$0(StatsEngine.StatItem it) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$2(int it) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public StatsAdapter(List<StatsEngine.StatItem> list, Function1<? super StatsEngine.StatItem, Unit> function1, Function1<? super StatsEngine.StatItem, Unit> function12, Function1<? super StatsEngine.StatItem, Unit> function13, Function0<Unit> function0, Function1<? super Integer, Unit> function14) {
        this.items = list;
        this.onTrackClick = function1;
        this.onQueueClick = function12;
        this.onPlayNextClick = function13;
        this.onLongClick = function0;
        this.onSelectionChangedBatch = function14;
        this.selectedItems = new LinkedHashSet();
    }

    /* renamed from: isGridView, reason: from getter */
    public final boolean getIsGridView() {
        return this.isGridView;
    }

    public final void setGridView(boolean value) {
        if (this.isGridView != value) {
            this.isGridView = value;
            notifyDataSetChanged();
        }
    }

    /* renamed from: isSelectionMode, reason: from getter */
    public final boolean getIsSelectionMode() {
        return this.isSelectionMode;
    }

    public final void setSelectionMode(boolean value) {
        this.isSelectionMode = value;
        if (!value) {
            this.selectedItems.clear();
        }
        notifyDataSetChanged();
    }

    public final Set<StatsEngine.StatItem> getSelectedItems() {
        return this.selectedItems;
    }

    /* compiled from: StatsAdapter.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\tR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\tR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lcom/maxmpz/poweramp/companion/StatsAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "tvRank", "Landroid/widget/TextView;", "getTvRank", "()Landroid/widget/TextView;", "ivIcon", "Landroid/widget/ImageView;", "getIvIcon", "()Landroid/widget/ImageView;", "tvTitle", "getTvTitle", "tvSubtitle", "getTvSubtitle", "tvCount", "getTvCount", "btnQueue", "Landroid/widget/ImageButton;", "getBtnQueue", "()Landroid/widget/ImageButton;", "btnPlayNext", "getBtnPlayNext", "cbSelect", "Lcom/google/android/material/checkbox/MaterialCheckBox;", "getCbSelect", "()Lcom/google/android/material/checkbox/MaterialCheckBox;", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageButton btnPlayNext;
        private final ImageButton btnQueue;
        private final MaterialCheckBox cbSelect;
        private final ImageView ivIcon;
        private final TextView tvCount;
        private final TextView tvRank;
        private final TextView tvSubtitle;
        private final TextView tvTitle;

        public ViewHolder(View view) {
            super(view);
            this.tvRank = (TextView) view.findViewById(R.id.tvRank);
            this.ivIcon = (ImageView) view.findViewById(R.id.ivStatIcon);
            this.tvTitle = (TextView) view.findViewById(R.id.tvStatTitle);
            this.tvSubtitle = (TextView) view.findViewById(R.id.tvStatSubtitle);
            this.tvCount = (TextView) view.findViewById(R.id.tvPlayCount);
            this.btnQueue = (ImageButton) view.findViewById(R.id.btnQueueStat);
            this.btnPlayNext = (ImageButton) view.findViewById(R.id.btnPlayNextStat);
            this.cbSelect = (MaterialCheckBox) view.findViewById(R.id.cbSelect);
        }

        public final TextView getTvRank() {
            return this.tvRank;
        }

        public final ImageView getIvIcon() {
            return this.ivIcon;
        }

        public final TextView getTvTitle() {
            return this.tvTitle;
        }

        public final TextView getTvSubtitle() {
            return this.tvSubtitle;
        }

        public final TextView getTvCount() {
            return this.tvCount;
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
    public int getItemViewType(int position) {
        return this.isGridView ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = viewType == 1 ? R.layout.item_stat_grid : R.layout.item_stat;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final StatsEngine.StatItem item = this.items.get(position);
        holder.getTvRank().setText(String.valueOf(position + 1));
        holder.getTvTitle().setText(item.getTitle());
        TextView tvSubtitle = holder.getTvSubtitle();
        if (tvSubtitle != null) {
            tvSubtitle.setText(item.getSubtitle());
        }
        TextView tvCount = holder.getTvCount();
        if (tvCount != null) {
            tvCount.setText(String.valueOf(item.getPlayCount()));
        }
        holder.getIvIcon().clearColorFilter();
        Glide.with(holder.itemView.getContext()).load(item.getAlbumArtUri()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).centerCrop().into(holder.getIvIcon());
        holder.getCbSelect().setVisibility(this.isSelectionMode ? 0 : 8);
        holder.getCbSelect().setChecked(this.selectedItems.contains(item));
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.StatsAdapter$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StatsAdapter.onBindViewHolder$lambda$3(StatsAdapter.this, item, position, view);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.maxmpz.poweramp.companion.StatsAdapter$$ExternalSyntheticLambda4
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean onBindViewHolder$lambda$4;
                onBindViewHolder$lambda$4 = StatsAdapter.onBindViewHolder$lambda$4(StatsAdapter.this, item, position, view);
                return onBindViewHolder$lambda$4;
            }
        });
        holder.getCbSelect().setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.StatsAdapter$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StatsAdapter.this.toggleSelection(item, position);
            }
        });
        ImageButton btnQueue = holder.getBtnQueue();
        if (btnQueue != null) {
            btnQueue.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.StatsAdapter$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StatsAdapter.onBindViewHolder$lambda$6(StatsAdapter.this, item, view);
                }
            });
        }
        ImageButton btnPlayNext = holder.getBtnPlayNext();
        if (btnPlayNext != null) {
            btnPlayNext.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.StatsAdapter$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StatsAdapter.onBindViewHolder$lambda$7(StatsAdapter.this, item, view);
                }
            });
        }
        ImageButton btnQueue2 = holder.getBtnQueue();
        if (btnQueue2 != null) {
            btnQueue2.setVisibility(this.isSelectionMode ? 8 : 0);
        }
        ImageButton btnPlayNext2 = holder.getBtnPlayNext();
        if (btnPlayNext2 != null) {
            btnPlayNext2.setVisibility(this.isSelectionMode ? 8 : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$3(StatsAdapter this$0, StatsEngine.StatItem $item, int $position, View it) {
        if (this$0.isSelectionMode) {
            this$0.toggleSelection($item, $position);
        } else {
            this$0.onTrackClick.invoke($item);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$4(StatsAdapter this$0, StatsEngine.StatItem $item, int $position, View it) {
        if (!this$0.isSelectionMode) {
            this$0.setSelectionMode(true);
            this$0.onLongClick.invoke();
            this$0.toggleSelection($item, $position);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$6(StatsAdapter this$0, StatsEngine.StatItem $item, View it) {
        if (!this$0.isSelectionMode) {
            this$0.onQueueClick.invoke($item);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$7(StatsAdapter this$0, StatsEngine.StatItem $item, View it) {
        if (!this$0.isSelectionMode) {
            this$0.onPlayNextClick.invoke($item);
        }
    }

    public final void toggleSelection(StatsEngine.StatItem item) {
        int position = this.items.indexOf(item);
        if (position != -1) {
            toggleSelection(item, position);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toggleSelection(StatsEngine.StatItem item, int position) {
        if (this.selectedItems.contains(item)) {
            this.selectedItems.remove(item);
        } else {
            this.selectedItems.add(item);
        }
        notifyItemChanged(position);
        this.onSelectionChangedBatch.invoke(Integer.valueOf(this.selectedItems.size()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public final void updateData(List<StatsEngine.StatItem> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }
}
