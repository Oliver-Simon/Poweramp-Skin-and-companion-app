package com.maxmpz.poweramp.companion

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.button.MaterialButton

class DiscoveryAdapter(
    private val items: List<DiscoveryItem>,
    private val context: Context
) : RecyclerView.Adapter<DiscoveryAdapter.DiscoveryViewHolder>() {

    class DiscoveryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivArt: ImageView = view.findViewById(R.id.ivDiscoveryArt)
        val tvTitle: TextView = view.findViewById(R.id.tvDiscoveryTitle)
        val tvArtist: TextView = view.findViewById(R.id.tvDiscoveryArtist)
        val tvReason: TextView = view.findViewById(R.id.tvDiscoveryReason)
        val tvBadge: TextView = view.findViewById(R.id.tvDiscoveryBadge)
        val btnAddPlaylist: MaterialButton = view.findViewById(R.id.btnAddPlaylist)
        val btnYtMusic: MaterialButton = view.findViewById(R.id.btnYtMusic)
        val btnSpotify: MaterialButton = view.findViewById(R.id.btnSpotify)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoveryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_discovery_card, parent, false)
        return DiscoveryViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiscoveryViewHolder, position: Int) {
        val item = items[position]
        holder.tvTitle.text = item.title
        holder.tvArtist.text = item.artist
        holder.tvReason.text = item.reason
        holder.tvBadge.text = if (item.type == "ALBUM") "ALBUM-RADAR" else "SONG-RADAR"

        if (!item.imageUrl.isNullOrBlank()) {
            Glide.with(context)
                .load(item.imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_gallery)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.ivArt)
        } else if (item.localTrackId != null) {
            val aaUri = com.maxmpz.poweramp.player.PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(item.localTrackId.toString()).build()
            Glide.with(context)
                .load(aaUri)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_gallery)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.ivArt)
        } else {
            Glide.with(context).clear(holder.ivArt)
            holder.ivArt.setImageResource(android.R.drawable.ic_menu_gallery)
        }

        if (item.localTrackId != null && item.targetPlaylistId != null) {
            holder.btnAddPlaylist.visibility = View.VISIBLE
            holder.btnAddPlaylist.setOnClickListener {
                val mainActivity = context as? MainActivity
                val success = mainActivity?.getPowerampController()?.addToPlaylist(item.localTrackId, item.targetPlaylistId)
                if (success == true) {
                    android.widget.Toast.makeText(context, "Erfolgreich hinzugefügt!", android.widget.Toast.LENGTH_SHORT).show()
                    holder.btnAddPlaylist.isEnabled = false
                    holder.btnAddPlaylist.text = "Hinzugefügt"
                    holder.btnAddPlaylist.setIconResource(android.R.drawable.checkbox_on_background)
                } else {
                    android.widget.Toast.makeText(context, "Fehler beim Hinzufügen.", android.widget.Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            holder.btnAddPlaylist.visibility = View.GONE
        }

        holder.btnYtMusic.setOnClickListener {
            val query = "${item.artist} ${item.title}"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://music.youtube.com/search?q=${Uri.encode(query)}")
                setPackage("com.google.android.apps.youtube.music")
            }
            try {
                context.startActivity(intent)
            } catch (e: Exception) {
                // Fallback to browser if app not installed
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://music.youtube.com/search?q=${Uri.encode(query)}"))
                context.startActivity(webIntent)
            }
        }

        holder.btnSpotify.setOnClickListener {
            val query = "track:${item.title} artist:${item.artist}"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("spotify:search:${Uri.encode(query)}")
            }
            try {
                context.startActivity(intent)
            } catch (e: Exception) {
                // Fallback to browser
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/search/${Uri.encode(query)}"))
                context.startActivity(webIntent)
            }
        }
    }

    override fun getItemCount() = items.size
}
