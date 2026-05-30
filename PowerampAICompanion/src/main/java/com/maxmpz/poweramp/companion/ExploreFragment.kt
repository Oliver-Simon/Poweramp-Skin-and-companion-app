package com.maxmpz.poweramp.companion

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.chip.ChipGroup
import com.google.android.material.progressindicator.CircularProgressIndicator
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CancellationException

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private lateinit var rvStats: RecyclerView
    private lateinit var progressStats: CircularProgressIndicator
    private lateinit var toggleType: MaterialButtonToggleGroup
    private lateinit var cgTimeframe: ChipGroup
    private lateinit var cgYearSelection: ChipGroup
    private lateinit var scrollTimeframe: View
    private lateinit var scrollYearSelection: View
    private lateinit var tvTimeMachineLabel: android.widget.TextView
    private lateinit var btnViewToggle: android.widget.ImageButton
    private lateinit var layoutChart: View
    private lateinit var chartTimeDistribution: HourlyBarChartView
    
    private val viewModel: ExploreViewModel by activityViewModels()
    
    private lateinit var statsEngine: StatsEngine
    private lateinit var statsAdapter: StatsAdapter
    
    private var loadJob: Job? = null
    private var isRestoringState = false
    private lateinit var layoutExport: View
    private lateinit var layoutEmptyState: View
    private lateinit var btnPlaylist: com.google.android.material.button.MaterialButton
    private lateinit var btnQueue: com.google.android.material.button.MaterialButton
    private lateinit var btnPlayNext: com.google.android.material.button.MaterialButton
    private lateinit var btnTimeMachine: android.widget.ImageButton
    
    private enum class ClickMode { PLAY, QUEUE, PLAY_NEXT }
    
    private val backPressedCallback = object : androidx.activity.OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            exitSelectionMode()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        statsEngine = StatsEngine(requireContext())
        
        rvStats = view.findViewById(R.id.rvStats)
        progressStats = view.findViewById(R.id.progressStats)
        toggleType = view.findViewById(R.id.toggleType)
        cgTimeframe = view.findViewById(R.id.cgTimeframe)
        cgYearSelection = view.findViewById(R.id.cgYearSelection)
        scrollTimeframe = view.findViewById(R.id.scrollTimeframe)
        scrollYearSelection = view.findViewById(R.id.scrollYearSelection)
        tvTimeMachineLabel = view.findViewById(R.id.tvTimeMachineLabel)
        btnViewToggle = view.findViewById(R.id.btnViewToggle)
        
        layoutExport = view.findViewById(R.id.layoutExportExplore)
        layoutEmptyState = view.findViewById(R.id.layoutEmptyState)
        btnPlaylist = view.findViewById(R.id.btnSendToPlaylistExplore)
        btnQueue = view.findViewById(R.id.btnSendToQueueExplore)
        btnPlayNext = view.findViewById(R.id.btnPlayNextExplore)
        btnTimeMachine = view.findViewById(R.id.btnTimeMachine)
        
        layoutChart = view.findViewById(R.id.layoutChart)
        chartTimeDistribution = view.findViewById(R.id.chartTimeDistribution)

        updateViewMode()
        
        statsAdapter = StatsAdapter(
            items = emptyList(),
            onTrackClick = { statItem ->
                if (statItem.type == StatsEngine.ItemType.TRACK && statItem.pampId != -1L) {
                    (requireActivity() as MainActivity).getPowerampController().playTrack(statItem.pampId)
                } else {
                    handleStatClick(statItem, ClickMode.PLAY)
                }
            },
            onQueueClick = { statItem ->
                if (statItem.type == StatsEngine.ItemType.TRACK && statItem.pampId != -1L) {
                    (requireActivity() as MainActivity).getPowerampController().sendToPowerampQueue(listOf(statItem.pampId))
                } else {
                    handleStatClick(statItem, ClickMode.QUEUE)
                }
            },
            onPlayNextClick = { statItem ->
                if (statItem.type == StatsEngine.ItemType.TRACK && statItem.pampId != -1L) {
                    (requireActivity() as MainActivity).getPowerampController().sendToPowerampQueueNext(listOf(statItem.pampId))
                } else {
                    handleStatClick(statItem, ClickMode.PLAY_NEXT)
                }
            },
            onLongClick = { enterSelectionMode() },
            onSelectionChangedBatch = { count ->
                viewModel.selectedItems.clear()
                viewModel.selectedItems.addAll(statsAdapter.selectedItems)
                updateSelectionUI(count)
            }
        ).apply {
            isGridView = viewModel.isGridView(viewModel.currentType)
        }
        rvStats.adapter = statsAdapter

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedCallback)

        layoutExport.visibility = View.GONE
        
        btnQueue.setOnClickListener { exportStats(EXPORT_MODE_QUEUE) }
        btnPlayNext.setOnClickListener { exportStats(EXPORT_MODE_PLAY_NEXT) }
        
        btnTimeMachine.setOnClickListener {
            viewModel.isTimeMachineMode = !viewModel.isTimeMachineMode
            updateTimeMachineUI()
            loadStats()
        }

        btnViewToggle.setOnClickListener {
            val newGridMode = !viewModel.isGridView(viewModel.currentType)
            viewModel.setGridView(viewModel.currentType, newGridMode)
            rvStats.recycledViewPool.clear()
            statsAdapter.isGridView = newGridMode
            updateViewMode()
            rvStats.invalidateItemDecorations()
        }

        setupListeners()
        restoreUIState() 
        loadStats()
    }

    private fun updateTimeMachineUI() {
        if (viewModel.isTimeMachineMode) {
            scrollTimeframe.visibility = View.GONE
            scrollYearSelection.visibility = View.VISIBLE
            tvTimeMachineLabel.visibility = View.VISIBLE
            btnTimeMachine.setColorFilter(requireContext().getColor(android.R.color.holo_orange_dark))
            if (cgYearSelection.checkedChipId == View.NO_ID) {
                cgYearSelection.check(R.id.chip1Year)
            }
        } else {
            scrollTimeframe.visibility = View.VISIBLE
            scrollYearSelection.visibility = View.GONE
            tvTimeMachineLabel.visibility = View.GONE
            btnTimeMachine.clearColorFilter()
        }
    }

    private fun restoreUIState() {
        isRestoringState = true 
        val typeButtonId = when (viewModel.currentType) {
            StatsEngine.ItemType.TRACK -> R.id.btnTypeTracks
            StatsEngine.ItemType.ARTIST -> R.id.btnTypeArtists
            StatsEngine.ItemType.ALBUM -> R.id.btnTypeAlbums
            StatsEngine.ItemType.TIME -> R.id.btnTypeTime
            StatsEngine.ItemType.GENRE -> R.id.btnTypeTracks // Fallback for now
        }
        toggleType.check(typeButtonId)

        val chipId = when (viewModel.currentRange) {
            StatsEngine.TimeRange.LAST_24_HOURS -> R.id.chip24Hours
            StatsEngine.TimeRange.LAST_7_DAYS   -> R.id.chip7Days
            StatsEngine.TimeRange.LAST_30_DAYS  -> R.id.chip30Days
            StatsEngine.TimeRange.LAST_60_DAYS  -> R.id.chip60Days
            StatsEngine.TimeRange.LAST_180_DAYS -> R.id.chip180Days
            StatsEngine.TimeRange.LAST_365_DAYS -> R.id.chip365Days
            StatsEngine.TimeRange.ALL_TIME      -> R.id.chipAllTime
        }
        cgTimeframe.check(chipId)
        
        val yearChipId = when (viewModel.timeMachineYear) {
            1 -> R.id.chip1Year
            2 -> R.id.chip2Years
            3 -> R.id.chip3Years
            4 -> R.id.chip4Years
            5 -> R.id.chip5Years
            6 -> R.id.chip6Years
            7 -> R.id.chip7Years
            8 -> R.id.chip8Years
            9 -> R.id.chip9Years
            10 -> R.id.chip10Years
            else -> R.id.chip1Year
        }
        cgYearSelection.check(yearChipId)
        updateTimeMachineUI()
        isRestoringState = false 
    }

    private fun setupListeners() {
        toggleType.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked && !isRestoringState) {
                val newType = when (checkedId) {
                    R.id.btnTypeTracks -> StatsEngine.ItemType.TRACK
                    R.id.btnTypeArtists -> StatsEngine.ItemType.ARTIST
                    R.id.btnTypeAlbums -> StatsEngine.ItemType.ALBUM
                    R.id.btnTypeTime -> StatsEngine.ItemType.TIME
                    else -> StatsEngine.ItemType.TRACK
                }
                if (newType != viewModel.currentType) {
                    viewModel.currentType = newType
                    rvStats.recycledViewPool.clear()
                    statsAdapter.isGridView = viewModel.isGridView(newType)
                    updateViewMode()
                    exitSelectionMode()
                    loadStats()
                }
            }
        }

        cgTimeframe.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty() && !isRestoringState) {
                val newRange = when (checkedIds[0]) {
                    R.id.chip24Hours -> StatsEngine.TimeRange.LAST_24_HOURS
                    R.id.chip7Days   -> StatsEngine.TimeRange.LAST_7_DAYS
                    R.id.chip30Days  -> StatsEngine.TimeRange.LAST_30_DAYS
                    R.id.chip60Days  -> StatsEngine.TimeRange.LAST_60_DAYS
                    R.id.chip180Days -> StatsEngine.TimeRange.LAST_180_DAYS
                    R.id.chip365Days -> StatsEngine.TimeRange.LAST_365_DAYS
                    R.id.chipAllTime -> StatsEngine.TimeRange.ALL_TIME
                    else             -> StatsEngine.TimeRange.LAST_7_DAYS
                }
                if (newRange != viewModel.currentRange) {
                    viewModel.currentRange = newRange
                    loadStats()
                }
            }
        }
        
        cgYearSelection.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty() && !isRestoringState) {
                val newYear = when (checkedIds[0]) {
                    R.id.chip1Year -> 1
                    R.id.chip2Years -> 2
                    R.id.chip3Years -> 3
                    R.id.chip4Years -> 4
                    R.id.chip5Years -> 5
                    R.id.chip6Years -> 6
                    R.id.chip7Years -> 7
                    R.id.chip8Years -> 8
                    R.id.chip9Years -> 9
                    R.id.chip10Years -> 10
                    else -> 1
                }
                if (newYear != viewModel.timeMachineYear) {
                    viewModel.timeMachineYear = newYear
                    loadStats()
                }
            }
        }
    }

    private fun loadStats() {
        val range = viewModel.currentRange
        val type = viewModel.currentType
        val timeYear = if (viewModel.isTimeMachineMode) viewModel.timeMachineYear else null
        val cacheKey = "${type}_${range}_${timeYear ?: "none"}"
        
        Log.d("StatsDEBUG", "loadStats: cacheKey=$cacheKey isTimeMachine=${viewModel.isTimeMachineMode} year=$timeYear")

        val cachedItems = viewModel.cache[cacheKey]
        val hasData = if (type == StatsEngine.ItemType.TIME) {
            viewModel.timeDistCache.containsKey(cacheKey)
        } else {
            !cachedItems.isNullOrEmpty()
        }

        if (!hasData) {
            Log.d("StatsDEBUG", "No data in cache for $cacheKey, showing progress")
            progressStats.visibility = View.VISIBLE
            rvStats.visibility = View.GONE
            layoutChart.visibility = View.GONE
            layoutEmptyState.visibility = View.GONE
            layoutExport.visibility = View.GONE
        } else {
            Log.d("StatsDEBUG", "Data found in cache for $cacheKey, count=${cachedItems?.size}")
            if (type == StatsEngine.ItemType.TIME) {
                viewModel.timeDistCache[cacheKey]?.let { dist ->
                    chartTimeDistribution.setData(dist)
                }
                rvStats.visibility = View.GONE
                layoutEmptyState.visibility = View.GONE
                layoutExport.visibility = View.GONE
                layoutChart.visibility = View.VISIBLE
            } else {
                cachedItems?.let { statsAdapter.updateData(it) }
                layoutEmptyState.visibility = View.GONE
                layoutChart.visibility = View.GONE
                rvStats.visibility = View.VISIBLE
                layoutExport.visibility = View.VISIBLE
                updateSelectionUI(0)
            }

            val lastUpdate = viewModel.lastUpdateMap[cacheKey] ?: 0L
            val needsRefresh = System.currentTimeMillis() - lastUpdate > 300000 // 5 minutes
            if (!needsRefresh) {
                Log.d("StatsDEBUG", "Cache is fresh for $cacheKey, skipping reload")
                progressStats.visibility = View.GONE
                return
            }
            Log.d("StatsDEBUG", "Cache needs refresh for $cacheKey")
            progressStats.visibility = View.VISIBLE
        }

        loadJob?.cancel()
        loadJob = viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            try {
                Log.d("StatsDEBUG", "Starting loadJob for $cacheKey")
                val stats = when (type) {
                    StatsEngine.ItemType.TRACK -> statsEngine.getTopTracks(range, 50, timeYear)
                    StatsEngine.ItemType.ARTIST -> statsEngine.getTopArtists(range, 50, timeYear)
                    StatsEngine.ItemType.ALBUM -> statsEngine.getTopAlbums(range, 50, timeYear)
                    else -> emptyList()
                }
                
                val hourlyDist = if (type == StatsEngine.ItemType.TIME) {
                    statsEngine.getHourlyDistribution(range, timeYear) // Now passing timeYear
                } else null

                withContext(Dispatchers.Main) {
                    Log.d("StatsDEBUG", "loadJob finished for $cacheKey, results=${stats.size}")
                    progressStats.visibility = View.GONE
                    if (type == StatsEngine.ItemType.TIME && hourlyDist != null) {
                        val distArray = IntArray(24) { hourlyDist[it] ?: 0 }
                        viewModel.timeDistCache[cacheKey] = distArray
                        chartTimeDistribution.setData(distArray)
                        rvStats.visibility = View.GONE
                        layoutEmptyState.visibility = View.GONE
                        layoutExport.visibility = View.GONE
                        layoutChart.visibility = View.VISIBLE
                    } else if (stats.isEmpty() && !hasData) {
                        Log.d("StatsDEBUG", "Showing empty state for $cacheKey")
                        rvStats.visibility = View.GONE
                        layoutChart.visibility = View.GONE
                        layoutEmptyState.visibility = View.VISIBLE
                    } else if (stats.isNotEmpty() || hasData) {
                        // Even if empty, if we had data, we should update to show the (now empty) fresh results
                        viewModel.cache[cacheKey] = stats
                        viewModel.lastUpdateMap[cacheKey] = System.currentTimeMillis()
                        statsAdapter.updateData(stats)
                        layoutEmptyState.visibility = View.GONE
                        layoutChart.visibility = View.GONE
                        rvStats.visibility = View.VISIBLE
                        layoutExport.visibility = View.VISIBLE
                        updateSelectionUI(0)
                    }
                }
            } catch (e: CancellationException) {
                Log.d("StatsDEBUG", "loadJob cancelled for $cacheKey")
            } catch (e: Exception) {
                Log.e("StatsDEBUG", "Error loading stats for $cacheKey", e)
            }
        }
    }

    private fun enterSelectionMode() {
        viewModel.isSelectionMode = true
        statsAdapter.isSelectionMode = true
        backPressedCallback.isEnabled = true
        updateSelectionUI(0)
    }

    private fun exitSelectionMode() {
        viewModel.isSelectionMode = false
        statsAdapter.isSelectionMode = false
        viewModel.selectedItems.clear()
        backPressedCallback.isEnabled = false
        updateSelectionUI(0)
    }

    private fun updateSelectionUI(count: Int) {
        if (statsAdapter.isSelectionMode) {
            btnPlaylist.text = if (count > 0) "PLAYLIST ($count)" else "PLAYLIST"
            btnQueue.text = if (count > 0) "QUEUE ($count)" else "QUEUE"
        } else {
            btnPlaylist.text = "ALLE IN PLAYLIST"
            btnQueue.text = "ALLE IN QUEUE"
        }
    }

    companion object {
        private const val EXPORT_MODE_QUEUE = 0
        private const val EXPORT_MODE_PLAYLIST = 1
        private const val EXPORT_MODE_PLAY_NEXT = 2
    }

    private fun exportStats(mode: Int) {
        val targets = if (statsAdapter.isSelectionMode && statsAdapter.selectedItems.isNotEmpty()) {
            statsAdapter.selectedItems.toList()
        } else {
            statsAdapter.getItems()
        }

        if (targets.isEmpty()) return
        Toast.makeText(requireContext(), "Exportiere ${targets.size} Einträge...", Toast.LENGTH_SHORT).show()
        
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val controller = (requireActivity() as MainActivity).getPowerampController()
            val allTrackIds = mutableListOf<Long>()
            
            for (item in targets) {
                when (item.type) {
                    StatsEngine.ItemType.TRACK -> {
                        if (item.pampId != -1L) allTrackIds.add(item.pampId)
                    }
                    StatsEngine.ItemType.ARTIST -> {
                        allTrackIds.addAll(findArtistTracks(requireContext(), item.title).map { it.id })
                    }
                    StatsEngine.ItemType.ALBUM -> {
                        allTrackIds.addAll(findAlbumTracks(requireContext(), item.title, item.subtitle, item.pampId).map { it.id })
                    }
                    StatsEngine.ItemType.TIME -> {}
                    StatsEngine.ItemType.GENRE -> {
                        // For now we don't have findGenreTracks implemented, 
                        // but we need to handle it to make the when exhaustive.
                    }
                }
            }
            
            if (allTrackIds.isNotEmpty()) {
                when (mode) {
                    EXPORT_MODE_PLAYLIST -> controller.sendToPoweramp(allTrackIds)
                    EXPORT_MODE_QUEUE -> controller.sendToPowerampQueue(allTrackIds)
                    EXPORT_MODE_PLAY_NEXT -> controller.sendToPowerampQueueNext(allTrackIds)
                }
                withContext(Dispatchers.Main) { exitSelectionMode() }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Keine Songs für den Export gefunden.", Toast.LENGTH_SHORT).show()
                    exitSelectionMode()
                }
            }
        }
    }

    private fun handleStatClick(statItem: StatsEngine.StatItem, mode: ClickMode) {
        if (statsAdapter.isSelectionMode) {
            statsAdapter.toggleSelection(statItem)
            return
        }

        if (statItem.type == StatsEngine.ItemType.TRACK) {
            val controller = (requireActivity() as MainActivity).getPowerampController()
            
            if (statItem.pampId != -1L) {
                when (mode) {
                    ClickMode.PLAY -> controller.playTrack(statItem.pampId)
                    ClickMode.QUEUE -> controller.sendToPowerampQueue(listOf(statItem.pampId))
                    ClickMode.PLAY_NEXT -> controller.sendToPowerampQueueNext(listOf(statItem.pampId))
                }
                return
            }

            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                val foundTracks = controller.findTracks(listOf(Pair(statItem.subtitle, statItem.title)))
                withContext(Dispatchers.Main) {
                    if (foundTracks.isNotEmpty()) {
                        val trackId = foundTracks.first().id
                        when (mode) {
                            ClickMode.PLAY -> {
                                controller.playTrack(trackId)
                                Toast.makeText(requireContext(), "Spiele: ${statItem.title}", Toast.LENGTH_SHORT).show()
                            }
                            ClickMode.QUEUE -> {
                                controller.sendToPowerampQueue(listOf(trackId))
                                Toast.makeText(requireContext(), "Warteschlange: ${statItem.title}", Toast.LENGTH_SHORT).show()
                            }
                            ClickMode.PLAY_NEXT -> {
                                controller.sendToPowerampQueueNext(listOf(trackId))
                                Toast.makeText(requireContext(), "Als nächstes: ${statItem.title}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(requireContext(), "Nicht in lokaler Poweramp-Mediathek gefunden.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            if (mode == ClickMode.PLAY) {
                openPowerampCategory(statItem)
            } else {
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    val controller = (requireActivity() as MainActivity).getPowerampController()
                    val query = when (statItem.type) {
                        StatsEngine.ItemType.ARTIST -> listOf(Pair("MAGIC_TOKEN_KEYWORD_SEARCH|${statItem.title}", ""))
                        StatsEngine.ItemType.ALBUM -> listOf(Pair("MAGIC_TOKEN_ALBUM|${statItem.pampId}", statItem.title))
                        else -> emptyList()
                    }
                    val foundTracks = controller.findTracks(query)
                    withContext(Dispatchers.Main) {
                        if (foundTracks.isNotEmpty()) {
                            val trackIds = foundTracks.map { it.id }
                            if (mode == ClickMode.QUEUE) {
                                controller.sendToPowerampQueue(trackIds)
                                Toast.makeText(requireContext(), "${foundTracks.size} Songs zur Warteschlange hinzugefügt.", Toast.LENGTH_SHORT).show()
                            } else {
                                controller.sendToPowerampQueueNext(trackIds)
                                Toast.makeText(requireContext(), "${foundTracks.size} Songs werden als nächstes gespielt.", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(requireContext(), "Keine Tracks für dieses Element gefunden.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun openPowerampCategory(statItem: StatsEngine.StatItem) {
        try {
            val category = when (statItem.type) {
                StatsEngine.ItemType.ARTIST -> "artists"
                StatsEngine.ItemType.ALBUM -> "albums"
                else -> return
            }

            val uri = if (statItem.pampId != -1L) {
                com.maxmpz.poweramp.player.PowerampAPI.ROOT_URI.buildUpon()
                    .appendEncodedPath(category)
                    .appendEncodedPath(statItem.pampId.toString())
                    .appendEncodedPath("files")
                    .build()
            } else {
                com.maxmpz.poweramp.player.PowerampAPI.ROOT_URI.buildUpon()
                    .appendEncodedPath(category)
                    .build()
            }

            val intent = Intent(com.maxmpz.poweramp.player.PowerampAPI.ACTION_OPEN_LIBRARY).apply {
                component = android.content.ComponentName(
                    com.maxmpz.poweramp.player.PowerampAPI.PACKAGE_NAME,
                    com.maxmpz.poweramp.player.PowerampAPI.ACTIVITY_STARTUP
                )
                data = uri
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            try {
                startActivity(intent)
            } catch (e2: Exception) {
                val launchIntent = requireContext().packageManager
                    .getLaunchIntentForPackage(com.maxmpz.poweramp.player.PowerampAPI.PACKAGE_NAME)
                if (launchIntent != null) startActivity(launchIntent)
                else Toast.makeText(requireContext(), "Poweramp nicht installiert.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Fehler beim Öffnen von Poweramp.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun findAlbumTracks(context: android.content.Context, albumName: String, artistName: String, albumPampId: Long = -1L): List<PowerampTrack> {
        val foundTracks = mutableListOf<PowerampTrack>()
        if (albumPampId != -1L) {
            val albumFilesUri = com.maxmpz.poweramp.player.PowerampAPI.ROOT_URI.buildUpon()
                .appendEncodedPath("albums")
                .appendEncodedPath(albumPampId.toString())
                .appendEncodedPath("files")
                .build()
            try {
                val cursor = context.contentResolver.query(albumFilesUri, arrayOf("_id", "title_tag", "duration"), null, null, "track_number ASC")
                cursor?.use { c ->
                    val idCol = c.getColumnIndexOrThrow("_id")
                    val titleCol = c.getColumnIndex("title_tag")
                    val durCol = c.getColumnIndex("duration")
                    while (c.moveToNext()) {
                        foundTracks.add(PowerampTrack(c.getLong(idCol), c.getString(titleCol) ?: "", artistName, if (durCol != -1) c.getLong(durCol) else 0L))
                    }
                }
            } catch (e: Exception) {}
            if (foundTracks.isNotEmpty()) return foundTracks
        }

        val filesUri = com.maxmpz.poweramp.player.PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build()
        try {
            val cursor = context.contentResolver.query(filesUri, arrayOf("_id", "title_tag", "album_tag", "artist_tag", "duration"), null, null, null)
            cursor?.use { c ->
                val idCol = c.getColumnIndexOrThrow("_id")
                val titleCol = c.getColumnIndex("title_tag")
                val albumCol = c.getColumnIndex("album_tag")
                val artistCol = c.getColumnIndex("artist_tag")
                val durCol = c.getColumnIndex("duration")
                while (c.moveToNext()) {
                    val album = if (albumCol >= 0) c.getString(albumCol) ?: "" else ""
                    val artist = if (artistCol >= 0) c.getString(artistCol) ?: "" else ""
                    if (album.contains(albumName, ignoreCase = true) && artist.contains(artistName, ignoreCase = true)) {
                        foundTracks.add(PowerampTrack(c.getLong(idCol), c.getString(titleCol) ?: "", artistName, if (durCol != -1) c.getLong(durCol) else 0L))
                    }
                }
            }
        } catch (e: Exception) {}
        return foundTracks
    }


    private fun findArtistTracks(context: android.content.Context, artistName: String): List<PowerampTrack> {
        val foundTracks = mutableListOf<PowerampTrack>()
        val filesUri = com.maxmpz.poweramp.player.PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath("files").build()
        val projection = arrayOf("_id", "artist_tag", "title_tag", "name", "duration")
        
        try {
            val cursor = context.contentResolver.query(filesUri, projection, "artist_tag LIKE ?", arrayOf("%$artistName%"), "played_times DESC LIMIT 30")
            cursor?.use { c ->
                val idCol = c.getColumnIndexOrThrow("_id")
                val artistCol = c.getColumnIndex("artist_tag")
                val titleCol = c.getColumnIndex("title_tag")
                val nameCol = c.getColumnIndex("name")
                val durCol = c.getColumnIndex("duration")
                
                while (c.moveToNext()) {
                    val id = c.getLong(idCol)
                    val artist = if (artistCol >= 0) c.getString(artistCol) ?: "" else ""
                    val title = if (titleCol >= 0) c.getString(titleCol) ?: "" else ""
                    val name = if (nameCol >= 0) c.getString(nameCol) ?: "" else ""
                    val duration = if (durCol >= 0) c.getLong(durCol) else 0L
                    
                    foundTracks.add(PowerampTrack(id, title.ifEmpty { name }, artist.ifEmpty { "Unknown" }, duration))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return foundTracks
    }

    private fun updateViewMode() {
        if (viewModel.isGridView(viewModel.currentType)) {
            rvStats.layoutManager = androidx.recyclerview.widget.GridLayoutManager(requireContext(), 2)
            btnViewToggle.setImageResource(R.drawable.ic_list)
        } else {
            rvStats.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
            btnViewToggle.setImageResource(R.drawable.ic_grid)
        }
    }
}
