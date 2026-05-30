package com.maxmpz.poweramp.companion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.progressindicator.LinearProgressIndicator

class InsightsBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var tvInsightsContent: TextView
    private lateinit var insightsProgressIndicator: LinearProgressIndicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_insights_bottom_sheet, container, false)
        tvInsightsContent = view.findViewById(R.id.tvInsightsContent)
        insightsProgressIndicator = view.findViewById(R.id.insightsProgressIndicator)
        return view
    }

    fun setInsightsText(text: String) {
        if (::tvInsightsContent.isInitialized) {
            tvInsightsContent.text = text
            insightsProgressIndicator.visibility = View.GONE
        }
    }

    fun showError(error: String) {
        if (::tvInsightsContent.isInitialized) {
            tvInsightsContent.text = "Fehler bei der Analyse:\n$error"
            tvInsightsContent.setTextColor(resources.getColor(android.R.color.holo_red_dark, null))
            insightsProgressIndicator.visibility = View.GONE
        }
    }

    companion object {
        const val TAG = "InsightsBottomSheet"
    }
}
