package com.maxmpz.poweramp.companion;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.maxmpz.poweramp.player.PowerampAPI;
import com.maxmpz.poweramp.player.TableDefs;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000[\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u000f\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0015H\u0002J\b\u0010\u0019\u001a\u00020\u0015H\u0002J(\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0012\u0010\"\u001a\u00020\u00152\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\u0012\u0010%\u001a\u00020\u00152\b\u0010#\u001a\u0004\u0018\u00010$H\u0002J\u0006\u0010&\u001a\u00020\u000bJ\u0006\u0010'\u001a\u00020\rJ\b\u0010(\u001a\u00020\u0015H\u0002J\b\u0010)\u001a\u00020\u0015H\u0002J\b\u0010*\u001a\u00020\u0015H\u0002J\u0010\u0010+\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u001eH\u0002J\u0010\u0010-\u001a\u00020\u00152\u0006\u0010.\u001a\u00020/H\u0002J\b\u00100\u001a\u00020\u0005H\u0002J\u0006\u00101\u001a\u00020 J\u0006\u00102\u001a\u00020\u0015J\b\u00103\u001a\u00020\u0015H\u0014J\b\u00104\u001a\u00020\u0015H\u0014R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u001c\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u0013*\u0004\u0018\u00010\u00050\u00050\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/maxmpz/poweramp/companion/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "currentPlayingInfo", "", "getCurrentPlayingInfo", "()Ljava/lang/String;", "setCurrentPlayingInfo", "(Ljava/lang/String;)V", "powerampController", "Lcom/maxmpz/poweramp/companion/PowerampController;", "recommendationEngine", "Lcom/maxmpz/poweramp/companion/RecommendationEngine;", "trackReceiver", "com/maxmpz/poweramp/companion/MainActivity$trackReceiver$1", "Lcom/maxmpz/poweramp/companion/MainActivity$trackReceiver$1;", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "checkPowerampInstalled", "setupMiniPlayer", "updateMiniPlayer", "title", "artist", "trackId", "", "isPlaying", "", "updateMiniPlayerPlaybackState", "onNewIntent", "intent", "Landroid/content/Intent;", "handleNavIntent", "getPowerampController", "getRecommendationEngine", "showInsights", "refreshQueueStatus", "openPowerampQueue", "formatDuration", "ms", "switchFragment", "fragment", "Landroidx/fragment/app/Fragment;", "getRequiredPermission", "hasPermission", "checkPermissions", "onResume", "onPause", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class MainActivity extends AppCompatActivity {
    private String currentPlayingInfo;
    private PowerampController powerampController;
    private RecommendationEngine recommendationEngine;
    private final MainActivity$trackReceiver$1 trackReceiver = new BroadcastReceiver() { // from class: com.maxmpz.poweramp.companion.MainActivity$trackReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            PowerampController powerampController;
            PowerampController powerampController2 = null;
            if (!Intrinsics.areEqual(intent != null ? intent.getAction() : null, PowerampAPI.ACTION_TRACK_CHANGED)) {
                if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, PowerampAPI.ACTION_STATUS_CHANGED)) {
                    MainActivity.this.updateMiniPlayerPlaybackState(intent.getIntExtra("state", -1) == 1);
                    return;
                }
                return;
            }
            Bundle bundleExtra = intent.getBundleExtra("track");
            if (bundleExtra != null) {
                String string = bundleExtra.getString("title");
                if (string == null) {
                    string = "Unknown";
                }
                String str = string;
                String string2 = bundleExtra.getString("artist");
                if (string2 == null) {
                    string2 = "Unknown Artist";
                }
                String str2 = string2;
                long j = bundleExtra.getLong(PowerampAPI.Track.REAL_ID, -1L);
                long j2 = bundleExtra.getLong("id", -1L);
                int i = bundleExtra.getInt("cat", -1);
                powerampController = MainActivity.this.powerampController;
                if (powerampController == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("powerampController");
                } else {
                    powerampController2 = powerampController;
                }
                powerampController2.setCurrentQueueId(i == 800 ? j2 : -1L);
                MainActivity.this.setCurrentPlayingInfo(str + " by " + str2);
                MainActivity.this.updateMiniPlayer(str, str2, j, true);
            }
            MainActivity.this.refreshQueueStatus();
        }
    };
    private final ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.maxmpz.poweramp.companion.MainActivity$$ExternalSyntheticLambda10
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            MainActivity.requestPermissionLauncher$lambda$0(MainActivity.this, ((Boolean) obj).booleanValue());
        }
    });

    public final String getCurrentPlayingInfo() {
        return this.currentPlayingInfo;
    }

    public final void setCurrentPlayingInfo(String str) {
        this.currentPlayingInfo = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestPermissionLauncher$lambda$0(MainActivity this$0, boolean isGranted) {
        if (isGranted) {
            Toast.makeText(this$0, "Berechtigung erteilt.", 0).show();
        } else {
            Toast.makeText(this$0, "Fehlende Berechtigung für Musik-Scan.", 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.powerampController = new PowerampController(this);
        MainActivity mainActivity = this;
        PowerampController powerampController = this.powerampController;
        if (powerampController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("powerampController");
            powerampController = null;
        }
        this.recommendationEngine = new RecommendationEngine(mainActivity, powerampController);
        setupMiniPlayer();
        checkPermissions();
        ((ImageButton) findViewById(R.id.btnSettings)).setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.MainActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.onCreate$lambda$1(MainActivity.this, view);
            }
        });
        ((ImageButton) findViewById(R.id.btnOpenPoweramp)).setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.MainActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.onCreate$lambda$2(MainActivity.this, view);
            }
        });
        ((ImageButton) findViewById(R.id.btnInsights)).setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.MainActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.showInsights();
            }
        });
        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() { // from class: com.maxmpz.poweramp.companion.MainActivity$$ExternalSyntheticLambda5
            @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
            public final boolean onNavigationItemSelected(MenuItem menuItem) {
                boolean onCreate$lambda$4;
                onCreate$lambda$4 = MainActivity.onCreate$lambda$4(MainActivity.this, menuItem);
                return onCreate$lambda$4;
            }
        });
        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.nav_generate);
        }
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new MainActivity$onCreate$5(this, null), 2, null);
        handleNavIntent(getIntent());
        checkPowerampInstalled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(MainActivity this$0, View it) {
        this$0.startActivity(new Intent(this$0, (Class<?>) SettingsActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(MainActivity this$0, View it) {
        Intent intent = this$0.getPackageManager().getLaunchIntentForPackage(PowerampAPI.PACKAGE_NAME);
        if (intent != null) {
            this$0.startActivity(intent);
        } else {
            Toast.makeText(this$0, "Poweramp not installed!", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$4(MainActivity this$0, MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_generate) {
            this$0.switchFragment(new GenerateFragment());
            return true;
        }
        if (itemId == R.id.nav_explore) {
            this$0.switchFragment(new ExploreFragment());
            return true;
        }
        if (itemId == R.id.nav_discovery) {
            this$0.switchFragment(new DiscoveryFragment());
            return true;
        }
        return false;
    }

    private final void checkPowerampInstalled() {
        try {
            getPackageManager().getPackageInfo(PowerampAPI.PACKAGE_NAME, 0);
        } catch (PackageManager.NameNotFoundException e) {
            Snackbar.make(findViewById(android.R.id.content), "⚠️ Poweramp ist nicht installiert — einige Funktionen sind nicht verfügbar.", -2).setAction("Installieren", new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.MainActivity$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.checkPowerampInstalled$lambda$5(MainActivity.this, view);
                }
            }).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkPowerampInstalled$lambda$5(MainActivity this$0, View it) {
        try {
            this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.maxmpz.audioplayer")));
        } catch (Exception e) {
            this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.maxmpz.audioplayer")));
        }
    }

    private final void setupMiniPlayer() {
        LinearLayout layoutMiniPlayer = (LinearLayout) findViewById(R.id.layoutMiniPlayer);
        ImageButton btnPlayPause = (ImageButton) findViewById(R.id.btnMiniPlayerPlayPause);
        ImageButton btnPrev = (ImageButton) findViewById(R.id.btnMiniPlayerPrev);
        ImageButton btnNext = (ImageButton) findViewById(R.id.btnMiniPlayerNext);
        btnPrev.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.MainActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.setupMiniPlayer$lambda$7(MainActivity.this, view);
            }
        });
        btnPlayPause.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.MainActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.setupMiniPlayer$lambda$9(MainActivity.this, view);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.MainActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.setupMiniPlayer$lambda$11(MainActivity.this, view);
            }
        });
        layoutMiniPlayer.setOnClickListener(new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.MainActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.setupMiniPlayer$lambda$12(MainActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMiniPlayer$lambda$7(MainActivity this$0, View it) {
        Intent intent = new Intent(PowerampAPI.ACTION_API_COMMAND);
        intent.setPackage(PowerampAPI.PACKAGE_NAME);
        intent.putExtra("cmd", 5);
        this$0.startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMiniPlayer$lambda$9(MainActivity this$0, View it) {
        Intent intent = new Intent(PowerampAPI.ACTION_API_COMMAND);
        intent.setPackage(PowerampAPI.PACKAGE_NAME);
        intent.putExtra("cmd", 1);
        this$0.startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMiniPlayer$lambda$11(MainActivity this$0, View it) {
        Intent intent = new Intent(PowerampAPI.ACTION_API_COMMAND);
        intent.setPackage(PowerampAPI.PACKAGE_NAME);
        intent.putExtra("cmd", 4);
        this$0.startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupMiniPlayer$lambda$12(MainActivity this$0, View it) {
        Intent intent = this$0.getPackageManager().getLaunchIntentForPackage(PowerampAPI.PACKAGE_NAME);
        if (intent != null) {
            this$0.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateMiniPlayer(String title, String artist, long trackId, boolean isPlaying) {
        SharedPreferences prefs = getSharedPreferences("PowerampCompanionPrefs", 0);
        if (!prefs.getBoolean("show_mini_player", true)) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutMiniPlayer);
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
                return;
            }
            return;
        }
        LinearLayout layoutMiniPlayer = (LinearLayout) findViewById(R.id.layoutMiniPlayer);
        TextView tvTitle = (TextView) findViewById(R.id.tvMiniPlayerTitle);
        TextView tvArtist = (TextView) findViewById(R.id.tvMiniPlayerArtist);
        ImageView ivArt = (ImageView) findViewById(R.id.ivMiniPlayerArt);
        layoutMiniPlayer.setVisibility(0);
        tvTitle.setText(title);
        tvArtist.setText(artist);
        Uri albumArtUri = PowerampAPI.AA_ROOT_URI.buildUpon().appendEncodedPath("files").appendEncodedPath(String.valueOf(trackId)).build();
        Glide.with((FragmentActivity) this).load(albumArtUri).placeholder(android.R.drawable.ic_menu_gallery).error(android.R.drawable.ic_menu_gallery).into(ivArt);
        updateMiniPlayerPlaybackState(isPlaying);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateMiniPlayerPlaybackState(boolean isPlaying) {
        ImageButton btnPlayPause = (ImageButton) findViewById(R.id.btnMiniPlayerPlayPause);
        if (isPlaying) {
            btnPlayPause.setImageResource(android.R.drawable.ic_media_pause);
        } else {
            btnPlayPause.setImageResource(android.R.drawable.ic_media_play);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleNavIntent(intent);
    }

    private final void handleNavIntent(Intent intent) {
        if (Intrinsics.areEqual(intent != null ? intent.getStringExtra("nav_tab") : null, "generate")) {
            BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
            bottomNav.setSelectedItemId(R.id.nav_generate);
        }
    }

    public final PowerampController getPowerampController() {
        PowerampController powerampController = this.powerampController;
        if (powerampController != null) {
            return powerampController;
        }
        Intrinsics.throwUninitializedPropertyAccessException("powerampController");
        return null;
    }

    public final RecommendationEngine getRecommendationEngine() {
        RecommendationEngine recommendationEngine = this.recommendationEngine;
        if (recommendationEngine != null) {
            return recommendationEngine;
        }
        Intrinsics.throwUninitializedPropertyAccessException("recommendationEngine");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showInsights() {
        SharedPreferences prefs = getSharedPreferences("PowerampCompanionPrefs", 0);
        String apiKey = prefs.getString("gemini_api_key", "");
        String string = prefs.getString("blacklist_filter", "");
        String blacklist = string == null ? "" : string;
        String str = apiKey;
        if (str == null || str.length() == 0) {
            Snackbar.make(findViewById(android.R.id.content), "Bitte API Key in den Einstellungen hinterlegen!", 0).setAction("Settings", new View.OnClickListener() { // from class: com.maxmpz.poweramp.companion.MainActivity$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.showInsights$lambda$13(MainActivity.this, view);
                }
            }).show();
            return;
        }
        InsightsBottomSheetFragment bottomSheet = new InsightsBottomSheetFragment();
        bottomSheet.show(getSupportFragmentManager(), InsightsBottomSheetFragment.TAG);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new MainActivity$showInsights$2(this, apiKey, blacklist, bottomSheet, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showInsights$lambda$13(MainActivity this$0, View it) {
        this$0.startActivity(new Intent(this$0, (Class<?>) SettingsActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshQueueStatus() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new MainActivity$refreshQueueStatus$1(this, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openPowerampQueue() {
        try {
            Intent intent = new Intent(PowerampAPI.ACTION_OPEN_LIBRARY);
            intent.setComponent(new ComponentName(PowerampAPI.PACKAGE_NAME, PowerampAPI.ACTIVITY_STARTUP));
            intent.setData(PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath(TableDefs.Queue.TABLE).build());
            intent.addFlags(268435456);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Konnte Poweramp Queue nicht öffnen.", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatDuration(long ms) {
        long totalSeconds = ms / 1000;
        long j = 3600;
        long hours = totalSeconds / j;
        long j2 = 60;
        long minutes = (totalSeconds % j) / j2;
        long seconds = totalSeconds % j2;
        if (hours > 0) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds)}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        }
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String format2 = String.format("%d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(minutes), Long.valueOf(seconds)}, 2));
        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
        return format2;
    }

    private final void switchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }

    private final String getRequiredPermission() {
        if (Build.VERSION.SDK_INT >= 33) {
            return "android.permission.READ_MEDIA_AUDIO";
        }
        return "android.permission.READ_EXTERNAL_STORAGE";
    }

    public final boolean hasPermission() {
        return ContextCompat.checkSelfPermission(this, getRequiredPermission()) == 0;
    }

    public final void checkPermissions() {
        if (!hasPermission()) {
            this.requestPermissionLauncher.launch(getRequiredPermission());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(PowerampAPI.ACTION_TRACK_CHANGED);
        filter.addAction(PowerampAPI.ACTION_STATUS_CHANGED);
        ContextCompat.registerReceiver(this, this.trackReceiver, filter, 2);
        SharedPreferences prefs = getSharedPreferences("PowerampCompanionPrefs", 0);
        if (prefs.getBoolean("auto_dj_enabled", false)) {
            Intent serviceIntent = new Intent(this, (Class<?>) AutoDjService.class);
            ContextCompat.startForegroundService(this, serviceIntent);
        }
        refreshQueueStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        unregisterReceiver(this.trackReceiver);
    }
}
