package com.maxmpz.poweramp.companion

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class OnThisDayActivity : AppCompatActivity() {

    private lateinit var layoutContainers: LinearLayout
    private lateinit var progressView: View
    private lateinit var scrollViewContent: View
    private lateinit var btnBack: ImageButton
    private lateinit var cgYearSelection: ChipGroup
    
    private lateinit var statsEngine: StatsEngine
    private lateinit var powerampController: PowerampController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_this_day)

        layoutContainers = findViewById(R.id.layoutContainers)
        progressView = findViewById(R.id.progressView)
        scrollViewContent = findViewById(R.id.scrollViewContent)
        btnBack = findViewById(R.id.btnBack)
        cgYearSelection = findViewById(R.id.cgYearSelection)
        
        btnBack.setOnClickListener { finish() }

        powerampController = PowerampController(this)
        statsEngine = StatsEngine(this)
        
        progressView.visibility = View.GONE

        cgYearSelection.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                val selectedId = checkedIds.first()
                val yearsAgo = when (selectedId) {
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
                loadMemoryForYear(yearsAgo)
            } else {
                layoutContainers.removeAllViews()
                scrollViewContent.visibility = View.GONE
            }
        }
    }

    private fun loadMemoryForYear(yearsAgo: Int) {
        progressView.visibility = View.VISIBLE
        scrollViewContent.visibility = View.GONE
        layoutContainers.removeAllViews()

        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val pastYear = currentYear - yearsAgo

        CoroutineScope(Dispatchers.IO).launch {
            val stats = statsEngine.getTracksOnThisDay(yearsAgo)
            
            withContext(Dispatchers.Main) {
                progressView.visibility = View.GONE
                if (stats.isNotEmpty()) {
                    val sectionView = createMemorySection(pastYear, yearsAgo, stats)
                    layoutContainers.addView(sectionView)
                } else {
                    val emptyText = TextView(this@OnThisDayActivity).apply {
                        text = "Keine Erinnerungen für $pastYear gefunden. Höre fleißig weiter Musik!"
                        textSize = 16f
                        setPadding(48, 48, 48, 48)
                    }
                    layoutContainers.addView(emptyText)
                }
                scrollViewContent.visibility = View.VISIBLE
            }
        }
    }

    private fun createMemorySection(year: Int, yearsAgo: Int, stats: List<StatsEngine.StatItem>): View {
        val inflater = LayoutInflater.from(this)
        
        // We can reuse a generic layout or build it in code. We'll build a simple wrapper.
        val wrapper = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 32, 0, 32) }
        }

        // Header Layout
        val headerLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(32, 16, 32, 16)
        }

        val titleText = TextView(this).apply {
            text = "Heute vor $yearsAgo Jahr${if (yearsAgo > 1) "en" else ""} ($year)"
            textSize = 18f
            setTypeface(null, android.graphics.Typeface.BOLD)
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        }
        
        val playBtn = MaterialButton(this, null, com.google.android.material.R.attr.materialIconButtonFilledTonalStyle).apply {
            setIconResource(android.R.drawable.ic_media_play)
            setOnClickListener {
                val trackIds = stats.filter { it.pampId != -1L }.map { it.pampId }
                if (trackIds.isNotEmpty()) {
                    powerampController.sendToPoweramp(trackIds)
                    Toast.makeText(this@OnThisDayActivity, "Wiedergabe für $year gestartet", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@OnThisDayActivity, "Songs nicht mehr lokal verfügbar", Toast.LENGTH_SHORT).show()
                }
            }
        }

        headerLayout.addView(titleText)
        headerLayout.addView(playBtn)
        wrapper.addView(headerLayout)

        // RecyclerView
        val rv = RecyclerView(this).apply {
            layoutManager = LinearLayoutManager(this@OnThisDayActivity, LinearLayoutManager.VERTICAL, false)
            val adapter = StatsAdapter(stats, 
                onTrackClick = {
                    if (it.pampId != -1L) powerampController.playTrack(it.pampId)
                }, 
                onQueueClick = {
                    if (it.pampId != -1L) powerampController.sendToPowerampQueue(listOf(it.pampId))
                },
                onPlayNextClick = {
                    if (it.pampId != -1L) powerampController.sendToPowerampQueueNext(listOf(it.pampId))
                },
                onLongClick = {},
                onSelectionChangedBatch = {}
            )
            adapter.isGridView = false // Use vertical list for 100+ songs
            this.adapter = adapter
            // Optional: limit height so it doesn't take infinite space if there's other elements, 
            // but since it's inside a NestedScrollView, we let it wrap content.
            isNestedScrollingEnabled = false 
            setPadding(16, 0, 16, 0)
            clipToPadding = false
        }
        wrapper.addView(rv)

        return wrapper
    }
}
