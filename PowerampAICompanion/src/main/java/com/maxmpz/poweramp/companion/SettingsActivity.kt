package com.maxmpz.poweramp.companion

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.io.File
import java.io.FileOutputStream

class SettingsActivity : AppCompatActivity() {

    private lateinit var tvCurrentCsv: TextView
    private lateinit var btnLoadCsv: MaterialButton
    private lateinit var btnBack: ImageButton
    private lateinit var switchAutoDj: com.google.android.material.materialswitch.MaterialSwitch
    private lateinit var switchShuffleQueue: com.google.android.material.materialswitch.MaterialSwitch
    private lateinit var switchQueueRemaining: com.google.android.material.materialswitch.MaterialSwitch
    private lateinit var switchMiniPlayer: com.google.android.material.materialswitch.MaterialSwitch
    private lateinit var switchTweakBar: com.google.android.material.materialswitch.MaterialSwitch
    
    private lateinit var etGeminiKey: com.google.android.material.textfield.TextInputEditText
    private lateinit var etAiModel: com.google.android.material.textfield.TextInputEditText
    private lateinit var etAiBaseUrl: com.google.android.material.textfield.TextInputEditText
    private lateinit var spinnerProvider: android.widget.AutoCompleteTextView
    private lateinit var layoutAiBaseUrl: com.google.android.material.textfield.TextInputLayout
    private lateinit var etBlacklist: com.google.android.material.textfield.TextInputEditText
    private lateinit var etLastfmUsername: com.google.android.material.textfield.TextInputEditText
    private lateinit var etLastfmApiKey: com.google.android.material.textfield.TextInputEditText
    private lateinit var btnSaveSettings: MaterialButton
    private var lastSelectedProvider: String = "Gemini"

    private val pickCsvLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                saveCsvToInternalStorage(uri)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        tvCurrentCsv = findViewById(R.id.tvCurrentCsv)
        btnLoadCsv = findViewById(R.id.btnLoadCsv)
        btnBack = findViewById(R.id.btnBack)
        switchAutoDj = findViewById(R.id.switchAutoDj)
        switchShuffleQueue = findViewById(R.id.switchShuffleQueue)
        switchQueueRemaining = findViewById(R.id.switchQueueRemaining)
        switchMiniPlayer = findViewById(R.id.switchMiniPlayer)
        switchTweakBar = findViewById(R.id.switchTweakBar)
        
        etGeminiKey = findViewById(R.id.etGeminiKey)
        etAiModel = findViewById(R.id.etAiModel)
        etAiBaseUrl = findViewById(R.id.etAiBaseUrl)
        spinnerProvider = findViewById(R.id.spinnerProvider)
        layoutAiBaseUrl = findViewById(R.id.layoutAiBaseUrl)
        etBlacklist = findViewById(R.id.etBlacklist)
        etLastfmUsername = findViewById(R.id.etLastfmUsername)
        etLastfmApiKey = findViewById(R.id.etLastfmApiKey)
        btnSaveSettings = findViewById(R.id.btnSaveGeminiKey)

        setupProviderSpinner()

        updateCsvLabel()
        
        // Load existing key and blacklist if there is one
        val prefs = getSharedPreferences("PowerampCompanionPrefs", Context.MODE_PRIVATE)
        
        val existingProvider = prefs.getString("ai_provider", "Gemini") ?: "Gemini"
        lastSelectedProvider = existingProvider
        spinnerProvider.setText(existingProvider, false)
        updateLayoutVisibility(existingProvider)

        loadProviderSpecificSettings(existingProvider)

        val existingModel = prefs.getString("ai_model", "gemini-2.0-flash")
        etAiModel.setText(existingModel)

        val existingBaseUrl = prefs.getString("ai_base_url", "")
        etAiBaseUrl.setText(existingBaseUrl)

        // Initial key is loaded by loadProviderSpecificSettings
        val existingBlacklist = prefs.getString("blacklist_filter", null)
        if (existingBlacklist != null) {
            etBlacklist.setText(existingBlacklist)
        }
        
        val existingLastfmUser = prefs.getString("lastfm_username", "Oliver_Simon")
        etLastfmUsername.setText(existingLastfmUser)
        
        val existingLastfmKey = prefs.getString("lastfm_api_key", "fff31d8d380551799d26dec16e3212c2")
        etLastfmApiKey.setText(existingLastfmKey)
        
        
        val isAutoDjEnabled = prefs.getBoolean("auto_dj_enabled", false)
        switchAutoDj.isChecked = isAutoDjEnabled
        
        val isShuffleQueueEnabled = prefs.getBoolean("shuffle_queue_enabled", false)
        switchShuffleQueue.isChecked = isShuffleQueueEnabled
        
        switchAutoDj.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("auto_dj_enabled", isChecked).apply()
            if (isChecked) {
                // Determine if we need to request POST_NOTIFICATIONS first on Tiramisu+
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                    if (androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 101)
                    }
                }
                val serviceIntent = Intent(this, AutoDjService::class.java)
                androidx.core.content.ContextCompat.startForegroundService(this, serviceIntent)
            } else {
                val serviceIntent = Intent(this, AutoDjService::class.java)
                stopService(serviceIntent)
            }
        }
        
        switchShuffleQueue.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("shuffle_queue_enabled", isChecked).apply()
        }
        
        val isQueueRemaining = prefs.getBoolean("queue_remaining_duration", true) // Default to true (remaining)
        switchQueueRemaining.isChecked = isQueueRemaining
        
        switchQueueRemaining.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("queue_remaining_duration", isChecked).apply()
        }
        
        val isMiniPlayerEnabled = prefs.getBoolean("show_mini_player", true) // Default to true
        switchMiniPlayer.isChecked = isMiniPlayerEnabled
        
        switchMiniPlayer.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("show_mini_player", isChecked).apply()
        }
        
        val isTweakBarEnabled = prefs.getBoolean("show_tweak_bar", true) // Default to true
        switchTweakBar.isChecked = isTweakBarEnabled
        
        switchTweakBar.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("show_tweak_bar", isChecked).apply()
        }

        btnBack.setOnClickListener {
            finish()
        }

        btnLoadCsv.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "*/*" // Mime type for CSV can be tricky on Android, */* is safer
            }
            pickCsvLauncher.launch(intent)
        }
        
        btnSaveSettings.setOnClickListener {
            val key = etGeminiKey.text.toString().trim()
            val provider = spinnerProvider.text.toString().trim()
            val model = etAiModel.text.toString().trim()
            val baseUrl = etAiBaseUrl.text.toString().trim()
            val blacklist = etBlacklist.text.toString().trim()
            val lastfmUser = etLastfmUsername.text.toString().trim()
            val lastfmKey = etLastfmApiKey.text.toString().trim()
            
            prefs.edit()
                .putString("ai_provider", provider)
                .putString("ai_key_$provider", key) // Save specific key
                .putString("gemini_api_key", key) // Legacy/Global key
                .putString("ai_model", model)
                .putString("ai_base_url", baseUrl)
                .putString("blacklist_filter", blacklist)
                .putString("lastfm_username", lastfmUser)
                .putString("lastfm_api_key", lastfmKey)
                .apply()
            Toast.makeText(this, "Settings Saved!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupProviderSpinner() {
        val providers = arrayOf("Gemini", "Groq", "OpenRouter", "Custom OpenAI")
        val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, providers)
        spinnerProvider.setAdapter(adapter)

        spinnerProvider.setOnItemClickListener { parent, _, position, _ ->
            val selected = parent.getItemAtPosition(position) as String
            
            // Save current key to the PREVIOUS provider's slot before switching
            val currentKey = etGeminiKey.text.toString().trim()
            val prefs = getSharedPreferences("PowerampCompanionPrefs", Context.MODE_PRIVATE)
            prefs.edit().putString("ai_key_$lastSelectedProvider", currentKey).apply()
            
            lastSelectedProvider = selected
            updateLayoutVisibility(selected)
            
            // Load the new provider's settings (Key and Model)
            loadProviderSpecificSettings(selected)
        }
    }

    private fun loadProviderSpecificSettings(provider: String) {
        val prefs = getSharedPreferences("PowerampCompanionPrefs", Context.MODE_PRIVATE)
        
        // 1. Load API Key
        val key = prefs.getString("ai_key_$provider", "")
        if (key.isNullOrBlank() && provider == "Gemini") {
            // Fallback for users upgrading who only have gemini_api_key
            etGeminiKey.setText(prefs.getString("gemini_api_key", ""))
        } else {
            etGeminiKey.setText(key)
        }

        // 2. Load Model & Defaults
        val model = prefs.getString("ai_model_$provider", null)
        if (model != null) {
            etAiModel.setText(model)
        } else {
            // Set sensible defaults if no specific model saved
            when (provider) {
                "Gemini" -> etAiModel.setText("gemini-2.0-flash")
                "Groq" -> etAiModel.setText("llama-3.3-70b-versatile")
                "OpenRouter" -> etAiModel.setText("google/gemini-flash-1.5")
                "Custom OpenAI" -> etAiModel.setText("gpt-4o")
            }
        }
    }

    private fun updateLayoutVisibility(provider: String) {
        layoutAiBaseUrl.visibility = if (provider == "Custom OpenAI") android.view.View.VISIBLE else android.view.View.GONE
    }

    private fun saveCsvToInternalStorage(uri: Uri) {
        try {
            contentResolver.openInputStream(uri)?.use { inputStream ->
                val outFile = File(filesDir, "custom_lastfm.csv")
                FileOutputStream(outFile).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            
            // Save preference
            val prefs = getSharedPreferences("PowerampCompanionPrefs", Context.MODE_PRIVATE)
            prefs.edit().putBoolean("use_custom_csv", true).apply()
            
            updateCsvLabel()
            Toast.makeText(this, "Loaded New Last.fm Data!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to load CSV", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateCsvLabel() {
        val prefs = getSharedPreferences("PowerampCompanionPrefs", Context.MODE_PRIVATE)
        val useCustom = prefs.getBoolean("use_custom_csv", false)
        if (useCustom) {
            tvCurrentCsv.text = "Current CSV: Custom (Loaded)"
        } else {
            tvCurrentCsv.text = "Current CSV: Default (App Bundle)"
        }
    }
}
