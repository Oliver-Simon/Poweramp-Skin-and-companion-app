package com.maxmpz.poweramp.companion

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenerateFragment : Fragment(R.layout.fragment_generate) {

    private val viewModel: GenerateViewModel by activityViewModels()
    
    private val backPressedCallback = object : androidx.activity.OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            val adapter = rvSuggestions.adapter as? TrackAdapter
            adapter?.isSelectionModeEnabled = false
            isEnabled = false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // ... (setup views)
        setupViews(view)
        
        // Restore State
        if (viewModel.generatedTracks.isNotEmpty()) {
            displayTracks(viewModel.generatedTracks)
        }
        if (viewModel.lastPrompt.isNotEmpty()) {
            etPrompt.setText(viewModel.lastPrompt)
        }
        if (viewModel.isGenerating) {
            showLoading(true)
        }
        
        val btnSelectSeedPlaylist: MaterialButton = view.findViewById(R.id.btnSelectSeedPlaylist)
        if (viewModel.selectedSeedPlaylistName != null) {
            btnSelectSeedPlaylist.text = "Basis: ${viewModel.selectedSeedPlaylistName}"
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedCallback)
    }

    private lateinit var tvStatus: TextView
    private lateinit var svLog: ScrollView
    private lateinit var etPrompt: TextInputEditText
    private lateinit var etMaxTracks: TextInputEditText
    private lateinit var etMaxDuration: TextInputEditText
    private lateinit var btnGenerate: MaterialButton
    private lateinit var btnSmartGenerate: MaterialButton
    private lateinit var rvSuggestions: RecyclerView
    private lateinit var layoutExport: LinearLayout
    private lateinit var btnSendToPlaylist: MaterialButton
    private lateinit var btnSendToQueue: MaterialButton
    private lateinit var btnPlayNext: MaterialButton
    private lateinit var progressIndicator: com.google.android.material.progressindicator.CircularProgressIndicator
    private lateinit var layoutHistory: View
    private lateinit var cgHistory: com.google.android.material.chip.ChipGroup

    private fun setupViews(view: View) {
        tvStatus = view.findViewById(R.id.tvStatus)
        svLog = view.findViewById(R.id.svLog)
        etPrompt = view.findViewById(R.id.etPrompt)
        etMaxTracks = view.findViewById(R.id.etMaxTracks)
        etMaxDuration = view.findViewById(R.id.etMaxDuration)
        btnGenerate = view.findViewById(R.id.btnGenerate)
        btnSmartGenerate = view.findViewById(R.id.btnSmartGenerate)
        rvSuggestions = view.findViewById(R.id.rvSuggestions)
        layoutExport = view.findViewById(R.id.layoutExport)
        btnSendToPlaylist = view.findViewById(R.id.btnSendToPlaylist)
        btnSendToQueue = view.findViewById(R.id.btnSendToQueue)
        btnPlayNext = view.findViewById(R.id.btnPlayNext)
        progressIndicator = view.findViewById(R.id.progressIndicator)
        layoutHistory = view.findViewById(R.id.layoutHistory)
        cgHistory = view.findViewById(R.id.cgHistory)
        val btnClearLog: MaterialButton = view.findViewById(R.id.btnClearLog)
        val btnCopyLog: MaterialButton = view.findViewById(R.id.btnCopyLog)

        btnClearLog.setOnClickListener {
            tvStatus.text = "Bereit zur Wiedergabelisten-Erstellung."
        }
        
        btnCopyLog.setOnClickListener {
            val clipboard = requireContext().getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            val clip = android.content.ClipData.newPlainText("Poweramp Log", tvStatus.text.toString())
            clipboard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "Log kopiert!", Toast.LENGTH_SHORT).show()
        }

        loadHistoryChips()

        val btnToggleSelection: TextView = view.findViewById(R.id.btnToggleSelection)
        btnToggleSelection.setOnClickListener {
            val adapter = rvSuggestions.adapter as? TrackAdapter
            if (adapter != null) {
                adapter.isSelectionModeEnabled = !adapter.isSelectionModeEnabled
                btnToggleSelection.text = if (adapter.isSelectionModeEnabled) "AUSWAHL AUFHEBEN" else "MEHRERE AUSWÄHLEN"
                backPressedCallback.isEnabled = adapter.isSelectionModeEnabled
            }
        }
        
        val btnSelectSeedPlaylist: MaterialButton = view.findViewById(R.id.btnSelectSeedPlaylist)
        btnSelectSeedPlaylist.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                val playlists = (requireActivity() as MainActivity).getPowerampController().getPlaylists()
                withContext(Dispatchers.Main) {
                    if (playlists.isEmpty()) {
                        Toast.makeText(requireContext(), "Keine Playlists gefunden.", Toast.LENGTH_SHORT).show()
                        return@withContext
                    }
                    val names = playlists.map { it.second }.toTypedArray()
                    val ids = playlists.map { it.first }
                    
                    val dialog = com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Basis-Playlist wählen")
                        .setItems(names) { _, which ->
                            viewModel.selectedSeedPlaylistId = ids[which]
                            viewModel.selectedSeedPlaylistName = names[which]
                            btnSelectSeedPlaylist.text = "Basis: ${names[which]}"
                        }
                        .setNegativeButton("Abbrechen", null)
                        .setNeutralButton("Leeren") { _, _ ->
                            viewModel.selectedSeedPlaylistId = null
                            viewModel.selectedSeedPlaylistName = null
                            btnSelectSeedPlaylist.text = "Basis-Playlist auswählen (Optional)"
                        }
                        .create()
                    dialog.show()
                }
            }
        }

        rvSuggestions.layoutManager = LinearLayoutManager(requireContext())
        
        val etTweakPrompt = view.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etTweakPrompt)
        view.findViewById<View>(R.id.btnSendTweak).setOnClickListener {
            val tweakText = etTweakPrompt.text.toString().trim()
            if (tweakText.isNotEmpty()) {
                etTweakPrompt.setText("")
                val mainActivity = requireActivity() as MainActivity
                if (mainActivity.hasPermission()) {
                    generateSuggestions(tweakText, isTweak = true)
                } else {
                    mainActivity.checkPermissions()
                }
            }
        }

        val defaultPresets = listOf(
            "Megamix", "Vergessene Favoriten", "Gerade gerne", "Tagesmix", "Neu", "Ruhig", "Party", "Filmmusik", "Abend", 
            "Underrated", "Workout", "Jogging", "Glücklich", "Melancholisch", "Aktueller Song"
        )
        
        fun loadPresets(): List<String> {
            val prefs = requireActivity().getSharedPreferences("PowerampCompanionPrefs", android.content.Context.MODE_PRIVATE)
            val saved = prefs.getString("preset_order", null)
            if (saved != null) {
                try {
                    val array = org.json.JSONArray(saved)
                    val list = mutableListOf<String>()
                    for (i in 0 until array.length()) list.add(array.getString(i))
                    defaultPresets.forEach { if (!list.contains(it)) list.add(it) }
                    return list
                } catch (e: Exception) { }
            }
            return defaultPresets
        }
        
        fun savePresets(presets: List<String>) {
            val prefs = requireActivity().getSharedPreferences("PowerampCompanionPrefs", android.content.Context.MODE_PRIVATE)
            val array = org.json.JSONArray()
            presets.forEach { array.put(it) }
            prefs.edit().putString("preset_order", array.toString()).apply()
        }

        val rvPresets = view.findViewById<RecyclerView>(R.id.rvPresets)
        val btnTogglePresets = view.findViewById<MaterialButton>(R.id.btnTogglePresets)
        
        rvPresets.layoutManager = androidx.recyclerview.widget.GridLayoutManager(requireContext(), 3)
        val presetAdapter = PresetAdapter(
            presets = loadPresets().toMutableList(),
            onPresetClick = { promptText ->
                val mainActivity = requireActivity() as MainActivity
                if (mainActivity.hasPermission()) {
                    var finalPrompt = promptText
                    if (promptText == "Aktueller Song") {
                        if (mainActivity.currentPlayingInfo != null) {
                            finalPrompt = "Musik ähnlich wie: ${mainActivity.currentPlayingInfo}"
                        } else {
                            finalPrompt = "Spiele einen ähnlichen Vibe wie der letzte Song nochmal"
                        }
                    }
                    etPrompt.setText(finalPrompt)
                    generateSuggestions(finalPrompt)
                } else {
                    mainActivity.checkPermissions()
                }
            },
            onOrderChanged = { newOrder ->
                savePresets(newOrder)
            }
        )
        rvPresets.adapter = presetAdapter
        
        btnTogglePresets.setOnClickListener {
            presetAdapter.isExpanded = !presetAdapter.isExpanded
            btnTogglePresets.text = if (presetAdapter.isExpanded) "Weniger anzeigen" else "Mehr anzeigen"
        }
        
        val itemTouchHelper = androidx.recyclerview.widget.ItemTouchHelper(object : androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback(
            androidx.recyclerview.widget.ItemTouchHelper.UP or androidx.recyclerview.widget.ItemTouchHelper.DOWN or 
            androidx.recyclerview.widget.ItemTouchHelper.LEFT or androidx.recyclerview.widget.ItemTouchHelper.RIGHT, 0) {
            
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                if (!presetAdapter.isExpanded) {
                    Toast.makeText(requireContext(), "Bitte klappe die Liste auf, um zu sortieren", Toast.LENGTH_SHORT).show()
                    return false
                }
                presetAdapter.moveItem(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

            override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
                super.clearView(recyclerView, viewHolder)
                presetAdapter.commitOrder()
            }
        })
        itemTouchHelper.attachToRecyclerView(rvPresets)

        btnGenerate.setOnClickListener {
            val mainActivity = requireActivity() as MainActivity
            if (mainActivity.hasPermission()) {
                generateSuggestions(null)
            } else {
                mainActivity.checkPermissions()
            }
        }

        btnSmartGenerate.setOnClickListener {
            val mainActivity = requireActivity() as MainActivity
            if (mainActivity.hasPermission()) {
                val prompt = etPrompt.text.toString().trim()
                if (prompt.isEmpty()) {
                    Toast.makeText(requireContext(), "Bitte zuerst einen Prompt eingeben.", Toast.LENGTH_SHORT).show()
                } else {
                    generateSuggestions(prompt)
                }
            } else {
                mainActivity.checkPermissions()
            }
        }

        btnSendToPlaylist.setOnClickListener {
            if (viewModel.generatedTracks.isNotEmpty()) {
                val adapter = rvSuggestions.adapter as? TrackAdapter
                val tracksToExport = if (adapter?.selectedTracks?.isNotEmpty() == true) {
                    adapter.selectedTracks.toList()
                } else {
                    viewModel.generatedTracks
                }

                logMessage("Exportiere nach Poweramp...")
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    (requireActivity() as MainActivity).getPowerampController().sendToPoweramp(tracksToExport.map { it.id })
                    withContext(Dispatchers.Main) {
                        logMessage("Poweramp Wiedergabeliste erstellt!")
                        adapter?.isSelectionModeEnabled = false
                    }
                }
            }
        }

        btnSendToQueue.setOnClickListener {
            if (viewModel.generatedTracks.isNotEmpty()) {
                val adapter = rvSuggestions.adapter as? TrackAdapter
                val tracksToExport = if (adapter?.selectedTracks?.isNotEmpty() == true) {
                    adapter.selectedTracks.toList()
                } else {
                    viewModel.generatedTracks
                }

                logMessage("Zur Warteschlange hinzufügen...")
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    (requireActivity() as MainActivity).getPowerampController().sendToPowerampQueue(tracksToExport.map { it.id })
                    withContext(Dispatchers.Main) {
                        logMessage("Tracks zur Warteschlange hinzugefügt!")
                        adapter?.isSelectionModeEnabled = false
                    }
                }
            }
        }

        btnPlayNext.setOnClickListener {
            if (viewModel.generatedTracks.isNotEmpty()) {
                val adapter = rvSuggestions.adapter as? TrackAdapter
                val tracksToExport = if (adapter?.selectedTracks?.isNotEmpty() == true) {
                    adapter.selectedTracks.toList()
                } else {
                    viewModel.generatedTracks
                }

                logMessage("Als Nächstes einreihen...")
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    (requireActivity() as MainActivity).getPowerampController().sendToPowerampQueueNext(tracksToExport.map { it.id })
                    withContext(Dispatchers.Main) {
                        logMessage("Wird als Nächstes gespielt!")
                        adapter?.isSelectionModeEnabled = false
                    }
                }
            }
        }
    }

    private fun generateSuggestions(prompt: String?, isTweak: Boolean = false) {
        val adapter = rvSuggestions.adapter as? TrackAdapter
        if (adapter != null) {
            adapter.isSelectionModeEnabled = false
            view?.findViewById<TextView>(R.id.btnToggleSelection)?.text = "MEHRERE AUSWÄHLEN"
            backPressedCallback.isEnabled = false
        }
        showLoading(true)
        if (!isTweak) viewModel.lastPrompt = prompt ?: ""
        viewModel.isGenerating = true

        val maxTracksStr = etMaxTracks.text.toString()
        val maxDurationStr = etMaxDuration.text.toString()
        val limitTracks = maxTracksStr.toIntOrNull()
        val limitDurationMs = maxDurationStr.toIntOrNull()?.let { it * 60 * 1000L }
        
        val disableAi = view?.findViewById<com.google.android.material.materialswitch.MaterialSwitch>(R.id.switchDisableAi)?.isChecked == true
        
        val tweakTracks = if (isTweak) viewModel.generatedTracks else null

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            try {
                val engine = (requireActivity() as MainActivity).getRecommendationEngine()
                val rawTracks = engine.parseAndRecommend(prompt, viewModel.selectedSeedPlaylistId, tweakTracks, disableAi) { logStr ->
                    logMessage(logStr)
                }
                
                var finalTracks = rawTracks
                if (limitTracks != null && limitTracks > 0) finalTracks = finalTracks.take(limitTracks)
                if (limitDurationMs != null && limitDurationMs > 0) {
                    var currentLength = 0L
                    finalTracks = finalTracks.takeWhile { 
                        currentLength += it.durationMs
                        currentLength < limitDurationMs 
                    }
                }

                withContext(Dispatchers.Main) {
                    showLoading(false)
                    viewModel.isGenerating = false
                    if (finalTracks.isEmpty()) {
                        logMessage("Keine Tracks für deine Anfrage gefunden!")
                        rvSuggestions.adapter = null
                    } else {
                        viewModel.generatedTracks = finalTracks
                        // Save full playlist state to history before displaying
                        val usedPrompt = prompt ?: viewModel.selectedSeedPlaylistName ?: "Smart Mix"
                        saveToHistory(usedPrompt, finalTracks)
                        loadHistoryChips()
                        displayTracks(finalTracks)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showLoading(false)
                    viewModel.isGenerating = false
                    logMessage("Error: ${e.message}")
                }
            }
        }
    }

    private fun showLoading(active: Boolean) {
        btnGenerate.isEnabled = !active
        btnSmartGenerate.isEnabled = !active
        progressIndicator.visibility = if (active) View.VISIBLE else View.GONE
        if (active) {
            layoutExport.visibility = View.GONE
            view?.findViewById<TextView>(R.id.btnToggleSelection)?.visibility = View.GONE
        }
    }

    private fun displayTracks(tracks: List<PowerampTrack>) {
        if (tracks.isEmpty()) return
        logMessage("${tracks.size} passende Tracks gefunden!")

        val layoutTweak = view?.findViewById<View>(R.id.layoutTweak)
        val isTweakBarEnabled = requireActivity().getSharedPreferences("PowerampCompanionPrefs", android.content.Context.MODE_PRIVATE).getBoolean("show_tweak_bar", true)
        layoutTweak?.visibility = if (isTweakBarEnabled) View.VISIBLE else View.GONE

        val btnToggleSelection = view?.findViewById<TextView>(R.id.btnToggleSelection)

        val adapter = TrackAdapter(
            tracks = tracks,
            onTrackClick = { clickedTrack ->
                (requireActivity() as MainActivity).getPowerampController().playTrack(clickedTrack.id)
            },
            onQueueClick = { clickedTrack ->
                logMessage("\"${clickedTrack.title}\" zur Warteschlange...")
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    (requireActivity() as MainActivity).getPowerampController().sendToPowerampQueue(listOf(clickedTrack.id))
                    withContext(Dispatchers.Main) { logMessage("\"${clickedTrack.title}\" hinzugefügt!") }
                }
            },
            onPlayNextClick = { clickedTrack ->
                logMessage("\"${clickedTrack.title}\" als nächstes spielen...")
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    (requireActivity() as MainActivity).getPowerampController().sendToPowerampQueueNext(listOf(clickedTrack.id))
                    withContext(Dispatchers.Main) { logMessage("\"${clickedTrack.title}\" wird als nächstes gespielt!") }
                }
            },
            onLongClick = {
                layoutExport.visibility = View.VISIBLE
                btnToggleSelection?.visibility = View.VISIBLE
                btnToggleSelection?.text = "AUSWAHL AUFHEBEN"
                backPressedCallback.isEnabled = true
            },
            onSelectionChangedBatch = { count ->
                btnToggleSelection?.text = if (count == 0) "MEHRERE AUSWÄHLEN" else "$count AUSGEWÄHLT"
                if (count == -1) { // Custom signal for selecting 0 or exiting
                     backPressedCallback.isEnabled = false
                }
            }
        )
        rvSuggestions.adapter = adapter
        layoutExport.visibility = View.VISIBLE
        btnToggleSelection?.visibility = View.VISIBLE
        btnToggleSelection?.text = "MEHRERE AUSWÄHLEN"
    }

    private fun saveToHistory(prompt: String, tracks: List<PowerampTrack>) {
        if (prompt.isBlank() || tracks.isEmpty()) return
        val prefs = requireActivity().getSharedPreferences("PowerampCompanionPrefs", android.content.Context.MODE_PRIVATE)
        val raw = prefs.getString("gen_history_v2", "[]")
        val arr = org.json.JSONArray(raw)
        
        val historyList = mutableListOf<org.json.JSONObject>()
        for (i in 0 until arr.length()) {
            historyList.add(arr.getJSONObject(i))
        }

        val iterator = historyList.iterator()
        while(iterator.hasNext()) {
            if (iterator.next().getString("prompt") == prompt) iterator.remove()
        }
        
        val trackArr = org.json.JSONArray()
        for (t in tracks) {
            val tObj = org.json.JSONObject()
            tObj.put("id", t.id)
            tObj.put("title", t.title)
            tObj.put("artist", t.artist)
            tObj.put("durationMs", t.durationMs)
            tObj.put("playedTimes", t.playedTimes)
            tObj.put("albumArtUri", t.albumArtUri?.toString() ?: "")
            trackArr.put(tObj)
        }
        
        val stateObj = org.json.JSONObject()
        stateObj.put("prompt", prompt)
        stateObj.put("tracks", trackArr)
        
        historyList.add(0, stateObj)
        
        val trimmed = org.json.JSONArray()
        historyList.take(5).forEach { trimmed.put(it) } // Increased history footprint slightly
        
        prefs.edit().putString("gen_history_v2", trimmed.toString()).apply()
    }

    private fun loadHistoryChips() {
        val prefs = requireActivity().getSharedPreferences("PowerampCompanionPrefs", android.content.Context.MODE_PRIVATE)
        val raw = prefs.getString("gen_history_v2", "[]")
        val arr = org.json.JSONArray(raw)
        if (arr.length() == 0) {
            layoutHistory.visibility = View.GONE
            return
        }
        layoutHistory.visibility = View.VISIBLE
        cgHistory.removeAllViews()
        
        for (i in 0 until arr.length()) {
            val stateObj = arr.getJSONObject(i)
            val text = stateObj.getString("prompt")
            
            val chip = com.google.android.material.chip.Chip(requireContext()).apply {
                this.text = if (text.length > 28) text.take(25) + "…" else text
                isCheckable = false
                setOnClickListener {
                    etPrompt.setText(text)
                    val tracksJson = stateObj.optJSONArray("tracks") ?: org.json.JSONArray()
                    val restoredTracks = mutableListOf<PowerampTrack>()
                    for (j in 0 until tracksJson.length()) {
                        val tObj = tracksJson.getJSONObject(j)
                        val uriStr = tObj.optString("albumArtUri", "")
                        restoredTracks.add(PowerampTrack(
                            id = tObj.getLong("id"),
                            title = tObj.getString("title"),
                            artist = tObj.getString("artist"),
                            durationMs = tObj.optLong("durationMs", 0L),
                            playedTimes = tObj.optInt("playedTimes", 0),
                            albumArtUri = if (uriStr.isNotBlank()) android.net.Uri.parse(uriStr) else null
                        ))
                    }
                    
                    if (restoredTracks.isNotEmpty()) {
                        logMessage("Historische Playlist '$text' komplett wiederhergestellt. (${restoredTracks.size} Songs)")
                        
                        var finalTracks = restoredTracks.toList()
                        
                        val maxTracksStr = etMaxTracks.text.toString()
                        val maxDurationStr = etMaxDuration.text.toString()
                        val limitTracks = maxTracksStr.toIntOrNull()
                        val limitDurationMs = maxDurationStr.toIntOrNull()?.let { it * 60 * 1000L }
                        
                        if (limitTracks != null && limitTracks > 0) finalTracks = finalTracks.take(limitTracks)
                        if (limitDurationMs != null && limitDurationMs > 0) {
                            var currentLength = 0L
                            finalTracks = finalTracks.takeWhile { 
                                currentLength += it.durationMs
                                currentLength < limitDurationMs 
                            }
                        }
                        
                        viewModel.generatedTracks = finalTracks
                        displayTracks(finalTracks)
                    } else {
                        // Fallback fallback triggering if legacy logic applied
                        generateSuggestions(text)
                    }
                }
            }
            cgHistory.addView(chip)
        }
    }

    private fun logMessage(message: String) {
        if (!isAdded) return
        val time = java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault()).format(java.util.Date())
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            tvStatus.append("\n[$time] $message")
            svLog.post { svLog.fullScroll(View.FOCUS_DOWN) }
        }
    }
}
