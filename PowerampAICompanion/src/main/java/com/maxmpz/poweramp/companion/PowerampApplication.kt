package com.maxmpz.poweramp.companion

import android.app.Application
import com.google.android.material.color.DynamicColors

class PowerampApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Apply Material You Dynamic Colors globally for the entire app
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}
