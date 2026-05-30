package com.maxmpz.poweramp.companion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputEditText;
import com.maxmpz.poweramp.player.TableDefs;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SettingsActivity.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\u0010\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001aH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0010\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00170\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/maxmpz/poweramp/companion/SettingsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "tvCurrentCsv", "Landroid/widget/TextView;", "btnLoadCsv", "Lcom/google/android/material/button/MaterialButton;", "btnBack", "Landroid/widget/ImageButton;", "switchAutoDj", "Lcom/google/android/material/materialswitch/MaterialSwitch;", "switchShuffleQueue", "switchQueueRemaining", "switchMiniPlayer", "etGeminiKey", "Lcom/google/android/material/textfield/TextInputEditText;", "etBlacklist", "etLastfmUsername", "etLastfmApiKey", "btnSaveSettings", "pickCsvLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "saveCsvToInternalStorage", TableDefs.SettingsSearchHistory.URI, "Landroid/net/Uri;", "updateCsvLabel", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class SettingsActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private MaterialButton btnLoadCsv;
    private MaterialButton btnSaveSettings;
    private TextInputEditText etBlacklist;
    private TextInputEditText etGeminiKey;
    private TextInputEditText etLastfmApiKey;
    private TextInputEditText etLastfmUsername;
    private final ActivityResultLauncher<Intent> pickCsvLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.maxmpz.poweramp.companion.SettingsActivity$$ExternalSyntheticLambda7
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            SettingsActivity.pickCsvLauncher$lambda$1(SettingsActivity.this, (ActivityResult) obj);
        }
    });
    private MaterialSwitch switchAutoDj;
    private MaterialSwitch switchMiniPlayer;
    private MaterialSwitch switchQueueRemaining;
    private MaterialSwitch switchShuffleQueue;
    private TextView tvCurrentCsv;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void pickCsvLauncher$lambda$1(SettingsActivity this$0, ActivityResult result) {
        Intent data;
        Uri data2;
        if (result.getResultCode() == -1 && (data = result.getData()) != null && (data2 = data.getData()) != null) {
            this$0.saveCsvToInternalStorage(data2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.tvCurrentCsv = (TextView) findViewById(R.id.tvCurrentCsv);
        this.btnLoadCsv = (MaterialButton) findViewById(R.id.btnLoadCsv);
        this.btnBack = (ImageButton) findViewById(R.id.btnBack);
        this.switchAutoDj = (MaterialSwitch) findViewById(R.id.switchAutoDj);
        this.switchShuffleQueue = (MaterialSwitch) findViewById(R.id.switchShuffleQueue);
        this.switchQueueRemaining = (MaterialSwitch) findViewById(R.id.switchQueueRemaining);
        this.switchMiniPlayer = (MaterialSwitch) findViewById(R.id.switchMiniPlayer);
        this.etGeminiKey = (TextInputEditText) findViewById(R.id.etGeminiKey);
        this.etBlacklist = (TextInputEditText) findViewById(R.id.etBlacklist);
        this.etLastfmUsername = (TextInputEditText) findViewById(R.id.etLastfmUsername);
        this.etLastfmApiKey = (TextInputEditText) findViewById(R.id.etLastfmApiKey);
        this.btnSaveSettings = (MaterialButton) findViewById(R.id.btnSaveGeminiKey);
        updateCsvLabel();
        final SharedPreferences prefs = getSharedPreferences("PowerampCompanionPrefs", 0);
        MaterialButton materialButton = null;
        String existingKey = prefs.getString("gemini_api_key", null);
        if (existingKey != null) {
            TextInputEditText textInputEditText = this.etGeminiKey;
            if (textInputEditText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("etGeminiKey");
                textInputEditText = null;
            }
            textInputEditText.setText(existingKey);
        }
        String existingBlacklist = prefs.getString("blacklist_filter", null);
        if (existingBlacklist != null) {
            TextInputEditText textInputEditText2 = this.etBlacklist;
            if (textInputEditText2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("etBlacklist");
                textInputEditText2 = null;
            }
            textInputEditText2.setText(existingBlacklist);
        }
        String existingLastfmUser = prefs.getString("lastfm_username", "Oliver_Simon");
        TextInputEditText textInputEditText3 = this.etLastfmUsername;
        if (textInputEditText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("etLastfmUsername");
            textInputEditText3 = null;
        }
        textInputEditText3.setText(existingLastfmUser);
        String existingLastfmKey = prefs.getString("lastfm_api_key", "fff31d8d380551799d26dec16e3212c2");
        TextInputEditText textInputEditText4 = this.etLastfmApiKey;
        if (textInputEditText4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("etLastfmApiKey");
            textInputEditText4 = null;
        }
        textInputEditText4.setText(existingLastfmKey);
        boolean isAutoDjEnabled = prefs.getBoolean("auto_dj_enabled", false);
        MaterialSwitch materialSwitch = this.switchAutoDj;
        if (materialSwitch == null) {
            Intrinsics.throwUninitializedPropertyAccessException("switchAutoDj");
            materialSwitch = null;
        }
        materialSwitch.setChecked(isAutoDjEnabled);
        boolean isShuffleQueueEnabled = prefs.getBoolean("shuffle_queue_enabled", false);
        MaterialSwitch materialSwitch2 = this.switchShuffleQueue;
        if (materialSwitch2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("switchShuffleQueue");
            materialSwitch2 = null;
        }
        materialSwitch2.setChecked(isShuffleQueueEnabled);
        MaterialSwitch materialSwitch3 = this.switchAutoDj;
        if (materialSwitch3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("switchAutoDj");
            materialSwitch3 = null;
        }
        materialSwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.maxmpz.poweramp.companion.SettingsActivity$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SettingsActivity.onCreate$lambda$2(prefs, this, compoundButton, z);
            }
        });
        MaterialSwitch materialSwitch4 = this.switchShuffleQueue;
        if (materialSwitch4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("switchShuffleQueue");
            materialSwitch4 = null;
        }
        materialSwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.maxmpz.poweramp.companion.SettingsActivity$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SettingsActivity.onCreate$lambda$3(prefs, compoundButton, z);
            }
        });
        boolean isQueueRemaining = prefs.getBoolean("queue_remaining_duration", true);
        MaterialSwitch materialSwitch5 = this.switchQueueRemaining;
        if (materialSwitch5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("switchQueueRemaining");
            materialSwitch5 = null;
        }
        materialSwitch5.setChecked(isQueueRemaining);
        MaterialSwitch materialSwitch6 = this.switchQueueRemaining;
        if (materialSwitch6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("switchQueueRemaining");
            materialSwitch6 = null;
        }
        materialSwitch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.maxmpz.poweramp.companion.SettingsActivity$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SettingsActivity.onCreate$lambda$4(prefs, compoundButton, z);
            }
        });
        boolean isMiniPlayerEnabled = prefs.getBoolean("show_mini_player", true);
        MaterialSwitch materialSwitch7 = this.switchMiniPlayer;
        if (materialSwitch7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("switchMiniPlayer");
            materialSwitch7 = null;
        }
        materialSwitch7.setChecked(isMiniPlayerEnabled);
        MaterialSwitch materialSwitch8 = this.switchMiniPlayer;
        if (materialSwitch8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("switchMiniPlayer");
            materialSwitch8 = null;
        }
        materialSwitch8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.maxmpz.poweramp.companion.SettingsActivity$$ExternalSyntheticLambda3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SettingsActivity.onCreate$lambda$5(prefs, compoundButton, z);
            }
        });
        ImageButton imageButton = this.btnBack;
        if (imageButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnBack");
            imageButton = null;
        }
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.SettingsActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsActivity.this.finish();
            }
        });
        MaterialButton materialButton2 = this.btnLoadCsv;
        if (materialButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnLoadCsv");
            materialButton2 = null;
        }
        materialButton2.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.SettingsActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsActivity.onCreate$lambda$8(SettingsActivity.this, view);
            }
        });
        MaterialButton materialButton3 = this.btnSaveSettings;
        if (materialButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnSaveSettings");
        } else {
            materialButton = materialButton3;
        }
        materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.SettingsActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SettingsActivity.onCreate$lambda$9(SettingsActivity.this, prefs, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(SharedPreferences $prefs, SettingsActivity this$0, CompoundButton compoundButton, boolean isChecked) {
        $prefs.edit().putBoolean("auto_dj_enabled", isChecked).apply();
        if (isChecked) {
            if (Build.VERSION.SDK_INT >= 33 && ContextCompat.checkSelfPermission(this$0, "android.permission.POST_NOTIFICATIONS") != 0) {
                this$0.requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, 101);
            }
            Intent serviceIntent = new Intent(this$0, (Class<?>) AutoDjService.class);
            ContextCompat.startForegroundService(this$0, serviceIntent);
            return;
        }
        Intent serviceIntent2 = new Intent(this$0, (Class<?>) AutoDjService.class);
        this$0.stopService(serviceIntent2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3(SharedPreferences $prefs, CompoundButton compoundButton, boolean isChecked) {
        $prefs.edit().putBoolean("shuffle_queue_enabled", isChecked).apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$4(SharedPreferences $prefs, CompoundButton compoundButton, boolean isChecked) {
        $prefs.edit().putBoolean("queue_remaining_duration", isChecked).apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$5(SharedPreferences $prefs, CompoundButton compoundButton, boolean isChecked) {
        $prefs.edit().putBoolean("show_mini_player", isChecked).apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$8(SettingsActivity this$0, View it) {
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        this$0.pickCsvLauncher.launch(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$9(SettingsActivity this$0, SharedPreferences $prefs, View it) {
        TextInputEditText textInputEditText = this$0.etGeminiKey;
        TextInputEditText textInputEditText2 = null;
        if (textInputEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("etGeminiKey");
            textInputEditText = null;
        }
        String key = StringsKt.trim((CharSequence) String.valueOf(textInputEditText.getText())).toString();
        TextInputEditText textInputEditText3 = this$0.etBlacklist;
        if (textInputEditText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("etBlacklist");
            textInputEditText3 = null;
        }
        String blacklist = StringsKt.trim((CharSequence) String.valueOf(textInputEditText3.getText())).toString();
        TextInputEditText textInputEditText4 = this$0.etLastfmUsername;
        if (textInputEditText4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("etLastfmUsername");
            textInputEditText4 = null;
        }
        String lastfmUser = StringsKt.trim((CharSequence) String.valueOf(textInputEditText4.getText())).toString();
        TextInputEditText textInputEditText5 = this$0.etLastfmApiKey;
        if (textInputEditText5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("etLastfmApiKey");
        } else {
            textInputEditText2 = textInputEditText5;
        }
        String lastfmKey = StringsKt.trim((CharSequence) String.valueOf(textInputEditText2.getText())).toString();
        $prefs.edit().putString("gemini_api_key", key).putString("blacklist_filter", blacklist).putString("lastfm_username", lastfmUser).putString("lastfm_api_key", lastfmKey).apply();
        Toast.makeText(this$0, "Settings Saved!", 0).show();
    }

    private final void saveCsvToInternalStorage(Uri uri) {
        try {
            InputStream openInputStream = getContentResolver().openInputStream(uri);
            if (openInputStream != null) {
                FileOutputStream fileOutputStream = openInputStream;
                try {
                    InputStream inputStream = fileOutputStream;
                    fileOutputStream = new FileOutputStream(new File(getFilesDir(), "custom_lastfm.csv"));
                    try {
                        long copyTo$default = ByteStreamsKt.copyTo$default(inputStream, fileOutputStream, 0, 2, null);
                        CloseableKt.closeFinally(fileOutputStream, null);
                        Long.valueOf(copyTo$default);
                        CloseableKt.closeFinally(fileOutputStream, null);
                    } finally {
                    }
                } finally {
                }
            }
            SharedPreferences prefs = getSharedPreferences("PowerampCompanionPrefs", 0);
            prefs.edit().putBoolean("use_custom_csv", true).apply();
            updateCsvLabel();
            Toast.makeText(this, "Loaded New Last.fm Data!", 0).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to load CSV", 0).show();
        }
    }

    private final void updateCsvLabel() {
        SharedPreferences prefs = getSharedPreferences("PowerampCompanionPrefs", 0);
        boolean useCustom = prefs.getBoolean("use_custom_csv", false);
        TextView textView = null;
        if (useCustom) {
            TextView textView2 = this.tvCurrentCsv;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tvCurrentCsv");
            } else {
                textView = textView2;
            }
            textView.setText("Current CSV: Custom (Loaded)");
            return;
        }
        TextView textView3 = this.tvCurrentCsv;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvCurrentCsv");
        } else {
            textView = textView3;
        }
        textView.setText("Current CSV: Default (App Bundle)");
    }
}
