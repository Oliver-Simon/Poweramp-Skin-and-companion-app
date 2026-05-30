package com.maxmpz.poweramp.companion

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.material.imageview.ShapeableImageView

class TrackAdapter(
    private val tracks: List<PowerampTrack>,
    private val onTrackClick: (PowerampTrack) -> Unit,
    private val onQueueClick: (PowerampTrack) -> Unit,
    private val onPlayNextClick: (PowerampTrack) -> Unit = {},
    /** Called when long-press initiates selection mode. */
    private val onLongClick: (() -> Unit)? = null,
    /** Called after each selection change with the new count. */
    private val onSelectionChangedBatch: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    val selectedTracks = mutableSetOf<PowerampTrack>()
    var isSelectionModeEnabled = false
        set(value) {
            field = value
            if (!value) selectedTracks.clear()
            notifyDataSetChanged()
            onSelectionChangedBatch?.invoke(selectedTracks.size)
        }

    class TrackViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTrackTitle)
        val tvArtist: TextView = view.findViewById(R.id.tvTrackArtist)
        val ivAlbumArt: ShapeableImageView = view.findViewById(R.id.ivAlbumArt)
        val btnQueue: ImageButton = view.findViewById(R.id.btnQueueTrack)
        val btnPlayNext: ImageButton = view.findViewById(R.id.btnPlayNextTrack)
        val cbSelect: com.google.android.material.checkbox.MaterialCheckBox = view.findViewById(R.id.cbSelectTrack)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track = tracks[position]
        holder.tvTitle.text = track.title
        if (track.artist.isNullOrBlank()) {
            holder.tvArtist.visibility = View.GONE
        } else {
            holder.tvArtist.text = track.artist
            holder.tvArtist.visibility = View.VISIBLE
        }

        // Album art: use URI (Glide) with aggressive disk caching strategy for smoother scrolling
        if (track.albumArtUri != null) {
            Glide.with(holder.itemView.context)
                .load(track.albumArtUri)
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .into(holder.ivAlbumArt)
        } else {
            holder.ivAlbumArt.setImageResource(R.drawable.ic_launcher)
        }

        holder.btnQueue.setOnClickListener {
            if (!isSelectionModeEnabled) onQueueClick(track)
        }

        holder.btnPlayNext.setOnClickListener {
            if (!isSelectionModeEnabled) onPlayNextClick(track)
        }

        holder.btnQueue.visibility = if (isSelectionModeEnabled) View.GONE else View.VISIBLE
        holder.btnPlayNext.visibility = if (isSelectionModeEnabled) View.GONE else View.VISIBLE

        // Checkbox visiblity
        holder.cbSelect.setOnCheckedChangeListener(null)
        holder.cbSelect.visibility = if (isSelectionModeEnabled) View.VISIBLE else View.GONE
        holder.cbSelect.isChecked = selectedTracks.contains(track)
        holder.cbSelect.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedTracks.add(track) else selectedTracks.remove(track)
            onSelectionChangedBatch?.invoke(selectedTracks.size)
        }

        holder.itemView.setOnClickListener {
            if (isSelectionModeEnabled) {
                // In selection mode, a tap toggles checkbox
                val isNowSelected = !selectedTracks.contains(track)
                if (isNowSelected) selectedTracks.add(track) else selectedTracks.remove(track)
                holder.cbSelect.isChecked = isNowSelected
                onSelectionChangedBatch?.invoke(selectedTracks.size)
            } else {
                onTrackClick(track)
            }
        }

        // Long-press enters selection mode and selects this item
        holder.itemView.setOnLongClickListener {
            if (!isSelectionModeEnabled) {
                isSelectionModeEnabled = true
                selectedTracks.add(track)
                holder.cbSelect.isChecked = true
                onLongClick?.invoke()
                onSelectionChangedBatch?.invoke(selectedTracks.size)
                true
            } else false
        }

    }

    override fun getItemCount() = tracks.size
}
