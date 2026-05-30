package com.maxmpz.poweramp.companion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.checkbox.MaterialCheckBox

class StatsAdapter(
    private var items: List<StatsEngine.StatItem>,
    private val onTrackClick: (StatsEngine.StatItem) -> Unit,
    private val onQueueClick: (StatsEngine.StatItem) -> Unit,
    private val onPlayNextClick: (StatsEngine.StatItem) -> Unit = {},
    private val onLongClick: () -> Unit = {},
    private val onSelectionChangedBatch: (Int) -> Unit = {}
) : RecyclerView.Adapter<StatsAdapter.ViewHolder>() {

    var isGridView = false
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    var isSelectionMode = false
        set(value) {
            field = value
            if (!value) selectedItems.clear()
            notifyDataSetChanged()
        }
    val selectedItems = mutableSetOf<StatsEngine.StatItem>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRank: TextView = view.findViewById(R.id.tvRank)
        val ivIcon: ImageView = view.findViewById(R.id.ivStatIcon)
        val tvTitle: TextView = view.findViewById(R.id.tvStatTitle)
        val tvSubtitle: TextView? = view.findViewById(R.id.tvStatSubtitle)
        val tvCount: TextView? = view.findViewById(R.id.tvPlayCount)
        val btnQueue: android.widget.ImageButton? = view.findViewById(R.id.btnQueueStat)
        val btnPlayNext: android.widget.ImageButton? = view.findViewById(R.id.btnPlayNextStat)
        val cbSelect: MaterialCheckBox = view.findViewById(R.id.cbSelect)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isGridView) VIEW_TYPE_GRID else VIEW_TYPE_LIST
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = if (viewType == VIEW_TYPE_GRID) R.layout.item_stat_grid else R.layout.item_stat
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvRank.text = "${position + 1}"
        holder.tvTitle.text = item.title
        holder.tvSubtitle?.text = item.subtitle
        holder.tvCount?.text = "${item.playCount}"
        
        holder.ivIcon.clearColorFilter()
        holder.ivIcon.scaleType = ImageView.ScaleType.CENTER_CROP
        
        if (item.albumArtUri != null) {
            Glide.with(holder.itemView.context)
                .load(item.albumArtUri)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .into(holder.ivIcon)
        } else {
            Glide.with(holder.itemView.context).clear(holder.ivIcon)
            if (isGridView) {
                holder.ivIcon.scaleType = ImageView.ScaleType.CENTER_CROP 
            } else {
                holder.ivIcon.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
            holder.ivIcon.setImageResource(R.drawable.ic_launcher)
        }

        // Selection Logic
        holder.cbSelect.visibility = if (isSelectionMode) View.VISIBLE else View.GONE
        holder.cbSelect.isChecked = selectedItems.contains(item)

        holder.itemView.setOnClickListener {
            if (isSelectionMode) {
                toggleSelection(item, position)
            } else {
                onTrackClick(item)
            }
        }

        holder.itemView.setOnLongClickListener {
            if (!isSelectionMode) {
                isSelectionMode = true
                onLongClick()
                toggleSelection(item, position)
                true
            } else false
        }
        
        holder.cbSelect.setOnClickListener {
            toggleSelection(item, position)
        }

        holder.btnQueue?.setOnClickListener {
            if (!isSelectionMode) onQueueClick(item)
        }

        holder.btnPlayNext?.setOnClickListener {
            if (!isSelectionMode) onPlayNextClick(item)
        }

        holder.btnQueue?.visibility = if (isSelectionMode) View.GONE else View.VISIBLE
        holder.btnPlayNext?.visibility = if (isSelectionMode) View.GONE else View.VISIBLE
    }

    fun toggleSelection(item: StatsEngine.StatItem) {
        val position = items.indexOf(item)
        if (position != -1) {
            toggleSelection(item, position)
        }
    }

    private fun toggleSelection(item: StatsEngine.StatItem, position: Int) {
        if (selectedItems.contains(item)) {
            selectedItems.remove(item)
        } else {
            selectedItems.add(item)
        }
        notifyItemChanged(position)
        onSelectionChangedBatch(selectedItems.size)
    }

    override fun getItemCount() = items.size

    fun getItems(): List<StatsEngine.StatItem> = items

    fun updateData(newItems: List<StatsEngine.StatItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    companion object {
        private const val VIEW_TYPE_LIST = 0
        private const val VIEW_TYPE_GRID = 1
    }
}
