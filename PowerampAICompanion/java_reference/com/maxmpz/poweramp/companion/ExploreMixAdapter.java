package com.maxmpz.poweramp.companion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: ExploreMixAdapter.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0014B)\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/maxmpz/poweramp/companion/ExploreMixAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/maxmpz/poweramp/companion/ExploreMixAdapter$MixViewHolder;", "mixes", "", "Lcom/maxmpz/poweramp/companion/ExploreMix;", "onMixClick", "Lkotlin/Function1;", "", "<init>", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "holder", "position", "getItemCount", "MixViewHolder", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class ExploreMixAdapter extends RecyclerView.Adapter<MixViewHolder> {
    private final List<ExploreMix> mixes;
    private final Function1<ExploreMix, Unit> onMixClick;

    /* JADX WARN: Multi-variable type inference failed */
    public ExploreMixAdapter(List<ExploreMix> list, Function1<? super ExploreMix, Unit> function1) {
        this.mixes = list;
        this.onMixClick = function1;
    }

    /* compiled from: ExploreMixAdapter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/maxmpz/poweramp/companion/ExploreMixAdapter$MixViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "<init>", "(Landroid/view/View;)V", "ivIcon", "Landroid/widget/ImageView;", "getIvIcon", "()Landroid/widget/ImageView;", "tvTitle", "Landroid/widget/TextView;", "getTvTitle", "()Landroid/widget/TextView;", "tvSubtitle", "getTvSubtitle", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public static final class MixViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivIcon;
        private final TextView tvSubtitle;
        private final TextView tvTitle;

        public MixViewHolder(View view) {
            super(view);
            this.ivIcon = (ImageView) view.findViewById(R.id.ivMixIcon);
            this.tvTitle = (TextView) view.findViewById(R.id.tvMixTitle);
            this.tvSubtitle = (TextView) view.findViewById(R.id.tvMixSubtitle);
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
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MixViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_explore_mix, parent, false);
        return new MixViewHolder(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MixViewHolder holder, int position) {
        final ExploreMix mix = this.mixes.get(position);
        holder.getTvTitle().setText(mix.getTitle());
        holder.getTvSubtitle().setText(mix.getSubtitle());
        holder.getIvIcon().setImageResource(mix.getIconRes());
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.ExploreMixAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExploreMixAdapter.onBindViewHolder$lambda$0(ExploreMixAdapter.this, mix, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(ExploreMixAdapter this$0, ExploreMix $mix, View it) {
        this$0.onMixClick.invoke($mix);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mixes.size();
    }
}
