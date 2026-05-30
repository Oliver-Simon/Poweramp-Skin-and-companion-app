package com.maxmpz.poweramp.companion

import androidx.lifecycle.ViewModel

class ExploreViewModel : ViewModel() {
    var cachedStats: List<StatsEngine.StatItem> = emptyList()
    var currentType: StatsEngine.ItemType = StatsEngine.ItemType.TRACK
    var currentRange: StatsEngine.TimeRange = StatsEngine.TimeRange.LAST_7_DAYS
    
    // Unified Multi-Category Cache: key is "type_range_year"
    val cache = mutableMapOf<String, List<StatsEngine.StatItem>>()
    val timeDistCache = mutableMapOf<String, IntArray>()
    val lastUpdateMap = mutableMapOf<String, Long>()

    private val gridViewMap = mutableMapOf<StatsEngine.ItemType, Boolean>().apply {
        put(StatsEngine.ItemType.ARTIST, true)
        put(StatsEngine.ItemType.ALBUM, true)
    }
    
    fun isGridView(type: StatsEngine.ItemType): Boolean = gridViewMap[type] ?: false
    fun setGridView(type: StatsEngine.ItemType, value: Boolean) {
        gridViewMap[type] = value
    }

    var isSelectionMode: Boolean = false
    val selectedItems = mutableSetOf<StatsEngine.StatItem>()
    
    // Time Machine State
    var isTimeMachineMode: Boolean = false
    var timeMachineYear: Int = 1
}
