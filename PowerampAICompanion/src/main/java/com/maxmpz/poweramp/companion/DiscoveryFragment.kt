package com.maxmpz.poweramp.companion

import android.content.ClipData
import android.content.ClipboardManager
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DiscoveryFragment : Fragment(R.layout.fragment_discovery) {
    
    private lateinit var rvDiscovery: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressDiscovery: CircularProgressIndicator
    private lateinit var layoutEmpty: androidx.core.widget.NestedScrollView
    private var currentDiscoveryType: String = "ALL"
    private var selectedPlaylistId: Long? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        rvDiscovery = view.findViewById(R.id.rvDiscovery)
        swipeRefresh = view.findViewById(R.id.swipeRefreshDiscovery)
        progressDiscovery = view.findViewById(R.id.progressDiscovery)
        layoutEmpty = view.findViewById(R.id.layoutEmptyDiscovery)
        
        rvDiscovery.layoutManager = LinearLayoutManager(requireContext())
        
        val toggleGroup: MaterialButtonToggleGroup = view.findViewById(R.id.toggleDiscoveryType)
        val togglePlaylistGroup: MaterialButtonToggleGroup = view.findViewById(R.id.togglePlaylistMode)
        val btnSelectPlaylist: com.google.android.material.button.MaterialButton = view.findViewById(R.id.btnSelectPlaylist)
        
        toggleGroup.check(R.id.btnDiscoveryAll)
        toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                currentDiscoveryType = when (checkedId) {
                    R.id.btnDiscoveryTracks -> "SONG"
                    R.id.btnDiscoveryAlbums -> "ALBUM"
                    R.id.btnDiscoveryArtists -> "ARTIST"
                    else -> "ALL"
                }
                startDiscovery(isRefresh = true)
            }
        }

        togglePlaylistGroup.check(R.id.btnPlaylistLocal)
        togglePlaylistGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                currentDiscoveryType = when (checkedId) {
                    R.id.btnPlaylistGlobal -> "PLAYLIST_GLOBAL"
                    else -> "PLAYLIST_LOCAL"
                }
                startDiscovery(isRefresh = true)
            }
        }

        btnSelectPlaylist.setOnClickListener {
            val mainActivity = activity as? MainActivity ?: return@setOnClickListener
            val playlists = mainActivity.getPowerampController().getPlaylists()
            if (playlists.isEmpty()) {
                Toast.makeText(requireContext(), "Keine Playlists gefunden.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            val names = playlists.map { it.second }.toTypedArray()
            val listDialog = AlertDialog.Builder(requireContext())
            listDialog.setTitle("Playlist auswählen")
            
            // Add a "Keine Playlist / Zurücksetzen" option
            val options = arrayOf("❌ Keine Playlist verwenden") + names
            
            listDialog.setItems(options) { _, which ->
                if (which == 0) {
                    // Reset
                    selectedPlaylistId = null
                    btnSelectPlaylist.text = "💿 Aus Playlist inspirieren..."
                    togglePlaylistGroup.visibility = View.GONE
                    toggleGroup.visibility = View.VISIBLE
                    toggleGroup.check(R.id.btnDiscoveryAll)
                } else {
                    val actualIndex = which - 1
                    val selected = playlists[actualIndex]
                    selectedPlaylistId = selected.first
                    btnSelectPlaylist.text = "💿 Playlist: ${selected.second}"
                    
                    toggleGroup.visibility = View.GONE
                    togglePlaylistGroup.visibility = View.VISIBLE
                    togglePlaylistGroup.check(R.id.btnPlaylistLocal)
                    currentDiscoveryType = "PLAYLIST_LOCAL"
                    startDiscovery(isRefresh = true)
                }
            }
            listDialog.show()
        }

        swipeRefresh.setOnRefreshListener {
            startDiscovery(isRefresh = true)
        }
        
        view.findViewById<View>(R.id.fragment_discovery_title)?.setOnLongClickListener {
            showDebugDialog()
            true
        }

        startDiscovery()
    }

    private fun showDebugDialog() {
        val message = RecommendationEngine.lastDebugInfo
        AlertDialog.Builder(requireContext())
            .setTitle("🔍 Discovery Debug Info")
            .setMessage(message)
            .setPositiveButton("Kopieren") { _, _ ->
                val clipboard = requireContext().getSystemService(android.content.Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("DiscoveryDebug", message)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(context, "Kopiert!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Schließen", null)
            .show()
    }

    private fun startDiscovery(isRefresh: Boolean = false) {
        val currentContextForPrefs = context ?: return
        val mainActivity = activity as? MainActivity ?: return
        
        val prefs = currentContextForPrefs.getSharedPreferences("PowerampCompanionPrefs", android.content.Context.MODE_PRIVATE)
        val apiKey = prefs.getString("gemini_api_key", "")

        if (apiKey.isNullOrBlank()) {
            Toast.makeText(currentContextForPrefs, "Bitte Gemini API Key in den Einstellungen hinterlegen!", Toast.LENGTH_LONG).show()
            layoutEmpty.visibility = View.VISIBLE
            swipeRefresh.isRefreshing = false
            return
        }

        if (isRefresh) {
            swipeRefresh.isRefreshing = true
        } else {
            progressDiscovery.visibility = View.VISIBLE
        }
        layoutEmpty.visibility = View.GONE

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                Log.d("DiscoveryFragment", "Starting discovery scan for type: $currentDiscoveryType")
                val engine = mainActivity.getRecommendationEngine()
                
                // RecommendationEngine.scanForGaps is a suspend function using withContext(Dispatchers.IO)
                val results = engine.scanForGaps(apiKey, currentDiscoveryType, selectedPlaylistId)

                withContext(Dispatchers.Main) {
                    val activeContext = context ?: return@withContext
                    Log.d("DiscoveryFragment", "Discovery scan finished, found ${results.size} items")
                    
                    progressDiscovery.visibility = View.GONE
                    swipeRefresh.isRefreshing = false
                    
                    if (results.isEmpty()) {
                        layoutEmpty.visibility = View.VISIBLE
                        Toast.makeText(activeContext, "Keine neuen Entdeckungen gefunden.", Toast.LENGTH_LONG).show()
                        showDebugDialog()
                    } else {
                        layoutEmpty.visibility = View.GONE
                        rvDiscovery.adapter = DiscoveryAdapter(results, activeContext)
                    }
                }
            } catch (e: Exception) {
                Log.e("DiscoveryFragment", "Error during discovery Radar scan", e)
                withContext(Dispatchers.Main) {
                    val activeContext = context ?: return@withContext
                    progressDiscovery.visibility = View.GONE
                    swipeRefresh.isRefreshing = false
                    Toast.makeText(activeContext, "Fehler beim Discovery Radar: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

