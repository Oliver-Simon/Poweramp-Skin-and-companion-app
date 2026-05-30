package com.poweramp.v3.dais.extra

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

class SkinInfoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skin_info)
    }

    fun startWithSampleSkin(view: View?) {
        Log.d(TAG, "startWithSampleSkin - Applying Dais Dark Extra")
        val pak = getPowerampPackageName(this)
        if (pak == null) {
            Toast.makeText(this, R.string.skin_poweramp_not_installed, Toast.LENGTH_LONG).show()
            return
        }
        val intent = Intent(Intent.ACTION_MAIN)
            .setClassName(pak, "com.maxmpz.audioplayer.StartupActivity")
            .putExtra("theme_pak", packageName)
            .putExtra("theme_id", R.style.SampleSkin)
        startActivity(intent)
        finish()
    }

    fun openPowerampThemeSettings(view: View?) {
        Log.d(TAG, "openPowerampThemeSettings")
        val pak = getPowerampPackageName(this)
        if (pak == null) {
            Toast.makeText(this, R.string.skin_poweramp_not_installed, Toast.LENGTH_LONG).show()
            return
        }
        val intent = Intent(Intent.ACTION_MAIN)
            .setClassName(pak, "com.maxmpz.audioplayer.SettingsActivity")
            .putExtra("open", "theme")
            .putExtra("theme_pak", packageName)
            .putExtra("theme_id", R.style.SampleSkin)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val TAG = "SkinInfoActivity"

        fun getPowerampPackageName(context: Context): String? {
            return try {
                val info = context.packageManager.resolveService(Intent("com.maxmpz.audioplayer.API_COMMAND"), 0)
                info?.serviceInfo?.packageName
            } catch (th: Throwable) {
                Log.e(TAG, "", th)
                null
            }
        }
    }
}
