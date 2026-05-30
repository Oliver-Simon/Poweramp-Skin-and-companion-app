package com.maxmpz.poweramp.companion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class ExploreMix(
    val title: String,
    val subtitle: String,
    val prompt: String,
    val iconRes: Int
)

class ExploreMixAdapter(
    private val mixes: List<ExploreMix>,
    private val onMixClick: (ExploreMix) -> Unit
) : RecyclerView.Adapter<ExploreMixAdapter.MixViewHolder>() {

    class MixViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivIcon: ImageView = view.findViewById(R.id.ivMixIcon)
        val tvTitle: TextView = view.findViewById(R.id.tvMixTitle)
        val tvSubtitle: TextView = view.findViewById(R.id.tvMixSubtitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MixViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_explore_mix, parent, false)
        return MixViewHolder(view)
    }

    override fun onBindViewHolder(holder: MixViewHolder, position: Int) {
        val mix = mixes[position]
        holder.tvTitle.text = mix.title
        holder.tvSubtitle.text = mix.subtitle
        holder.ivIcon.setImageResource(mix.iconRes)
        holder.itemView.setOnClickListener { onMixClick(mix) }
    }

    override fun getItemCount() = mixes.size
}
