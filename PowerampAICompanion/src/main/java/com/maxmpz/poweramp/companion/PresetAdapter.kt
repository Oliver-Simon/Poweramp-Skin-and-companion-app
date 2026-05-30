package com.maxmpz.poweramp.companion

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections

class PresetAdapter(
    private var presets: MutableList<String>,
    private val onPresetClick: (String) -> Unit,
    private val onOrderChanged: (List<String>) -> Unit
) : RecyclerView.Adapter<PresetAdapter.PresetViewHolder>() {

    var isExpanded: Boolean = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setPresets(newPresets: List<String>) {
        this.presets = newPresets.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_preset_chip, parent, false)
        return PresetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PresetViewHolder, position: Int) {
        val preset = presets[position]
        holder.tvPreset.text = preset
        holder.itemView.setOnClickListener {
            onPresetClick(preset)
        }
    }

    override fun getItemCount(): Int {
        return if (isExpanded) presets.size else minOf(presets.size, 6)
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        if (fromPosition < presets.size && toPosition < presets.size) {
            Collections.swap(presets, fromPosition, toPosition)
            notifyItemMoved(fromPosition, toPosition)
        }
    }

    fun commitOrder() {
        onOrderChanged(presets)
    }

    class PresetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPreset: TextView = itemView.findViewById(R.id.tvPreset)
    }
}
