package com.maxmpz.poweramp.companion

import androidx.lifecycle.ViewModel

class GenerateViewModel : ViewModel() {
    var generatedTracks: List<PowerampTrack> = emptyList()
    var lastPrompt: String = ""
    var isGenerating: Boolean = false
    var selectedSeedPlaylistId: Long? = null
    var selectedSeedPlaylistName: String? = null
}
