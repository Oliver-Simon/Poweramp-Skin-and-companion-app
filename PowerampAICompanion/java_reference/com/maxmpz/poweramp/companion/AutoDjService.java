package com.maxmpz.poweramp.companion;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.maxmpz.poweramp.player.PowerampAPI;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;

/* compiled from: AutoDjService.kt */
@Metadata(d1 = {"\u0000u\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001 \u0018\u0000 ;2\u00020\u0001:\u0001;B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020#H\u0002J\b\u0010%\u001a\u00020#H\u0016J\"\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020'H\u0016J\b\u0010,\u001a\u00020#H\u0016J\u0014\u0010-\u001a\u0004\u0018\u00010.2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J \u0010/\u001a\u00020#2\u0006\u00100\u001a\u00020\u00102\u0006\u00101\u001a\u00020\u00132\u0006\u00102\u001a\u00020\u0013H\u0002J\u0018\u00103\u001a\u00020#2\u0006\u00104\u001a\u00020\u00132\u0006\u00105\u001a\u00020\u0013H\u0002J\u001e\u00106\u001a\u00020#2\u0006\u00101\u001a\u00020\u00132\u0006\u00102\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u00107J\b\u00108\u001a\u000209H\u0002J\b\u0010:\u001a\u00020#H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010!¨\u0006<"}, d2 = {"Lcom/maxmpz/poweramp/companion/AutoDjService;", "Landroid/app/Service;", "<init>", "()V", "isServiceRunning", "", "powerampController", "Lcom/maxmpz/poweramp/companion/PowerampController;", "recommendationEngine", "Lcom/maxmpz/poweramp/companion/RecommendationEngine;", "scrobbleJob", "Lkotlinx/coroutines/Job;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "isGenerating", "promptShownForTrackId", "", "currentScrobbleTrackId", "currentScrobbleTitle", "", "currentScrobbleArtist", "currentScrobbleAlbum", "currentScrobbleDuration", "accumulatedPlayTimeMs", "lastPlayResumeTime", "isPlaying", "isScrobbled", "handler", "Landroid/os/Handler;", "scrobbleCheckRunnable", "Ljava/lang/Runnable;", "trackReceiver", "com/maxmpz/poweramp/companion/AutoDjService$trackReceiver$1", "Lcom/maxmpz/poweramp/companion/AutoDjService$trackReceiver$1;", "scheduleScrobbleCheck", "", "checkAndScrobble", "onCreate", "onStartCommand", "", "intent", "Landroid/content/Intent;", PowerampAPI.Track.FLAGS, "startId", "onDestroy", "onBind", "Landroid/os/IBinder;", "checkQueueStatus", "trackId", "currentTitle", "currentArtist", "showQueueEndPrompt", "trackTitle", "trackArtist", "generateAndEnqueue", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildPersistentNotification", "Landroid/app/Notification;", "createNotificationChannels", "Companion", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class AutoDjService extends Service {
    public static final String ACTION_EXPAND_QUEUE = "com.maxmpz.poweramp.companion.EXPAND_QUEUE";
    public static final String ACTION_STOP = "com.maxmpz.poweramp.companion.STOP_AUTO_DJ";
    private static final String CHANNEL_PERSISTENT = "AutoDjServiceChannel";
    private static final String CHANNEL_PROMPT = "AutoDjPromptChannel";
    private static final int NOTIF_PERSISTENT = 1;
    private long accumulatedPlayTimeMs;
    private String currentScrobbleAlbum;
    private String currentScrobbleArtist;
    private long currentScrobbleDuration;
    private String currentScrobbleTitle;
    private long currentScrobbleTrackId;
    private final Handler handler;
    private boolean isGenerating;
    private boolean isPlaying;
    private boolean isScrobbled;
    private boolean isServiceRunning;
    private long lastPlayResumeTime;
    private PowerampController powerampController;
    private long promptShownForTrackId;
    private RecommendationEngine recommendationEngine;
    private final Runnable scrobbleCheckRunnable;
    private Job scrobbleJob;
    private final CoroutineScope serviceScope;
    private final AutoDjService$trackReceiver$1 trackReceiver;

    /* JADX WARN: Type inference failed for: r0v7, types: [com.maxmpz.poweramp.companion.AutoDjService$trackReceiver$1] */
    public AutoDjService() {
        CompletableJob Job$default;
        CoroutineDispatcher io2 = Dispatchers.getIO();
        Job$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
        this.serviceScope = CoroutineScopeKt.CoroutineScope(io2.plus(Job$default));
        this.promptShownForTrackId = -1L;
        this.currentScrobbleTrackId = -1L;
        this.currentScrobbleTitle = "";
        this.currentScrobbleArtist = "";
        this.currentScrobbleAlbum = "";
        this.handler = new Handler(Looper.getMainLooper());
        this.scrobbleCheckRunnable = new Runnable() { // from class: com.maxmpz.poweramp.companion.AutoDjService$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AutoDjService.this.checkAndScrobble();
            }
        };
        this.trackReceiver = new BroadcastReceiver() { // from class: com.maxmpz.poweramp.companion.AutoDjService$trackReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Handler handler;
                Runnable runnable;
                boolean z;
                boolean z2;
                long j;
                long j2;
                Handler handler2;
                Runnable runnable2;
                boolean z3;
                String action = intent != null ? intent.getAction() : null;
                if (action != null) {
                    switch (action.hashCode()) {
                        case -65310725:
                            if (action.equals(PowerampAPI.ACTION_TRACK_CHANGED)) {
                                Bundle trackBundle = intent.getBundleExtra("track");
                                long idLong = intent.getLongExtra("id", -1L);
                                String trackTitle = intent.getStringExtra("title");
                                if (trackTitle == null) {
                                    trackTitle = trackBundle != null ? trackBundle.getString("title") : null;
                                    if (trackTitle == null) {
                                        trackTitle = "Unknown";
                                    }
                                }
                                String trackArtist = intent.getStringExtra("artist");
                                if (trackArtist == null) {
                                    trackArtist = trackBundle != null ? trackBundle.getString("artist") : null;
                                    if (trackArtist == null) {
                                        trackArtist = "Unknown Artist";
                                    }
                                }
                                String trackAlbum = intent.getStringExtra("album");
                                if (trackAlbum == null) {
                                    String string = trackBundle != null ? trackBundle.getString("album") : null;
                                    trackAlbum = string == null ? "Unknown Album" : string;
                                }
                                long duration = intent.getIntExtra(TypedValues.TransitionType.S_DURATION, 0);
                                if (idLong == -1) {
                                    return;
                                }
                                AutoDjService.this.checkAndScrobble();
                                AutoDjService.this.currentScrobbleTrackId = idLong;
                                AutoDjService.this.currentScrobbleTitle = trackTitle;
                                AutoDjService.this.currentScrobbleArtist = trackArtist;
                                AutoDjService.this.currentScrobbleAlbum = trackAlbum;
                                AutoDjService.this.currentScrobbleDuration = duration * 1000;
                                AutoDjService.this.accumulatedPlayTimeMs = 0L;
                                boolean z4 = false;
                                AutoDjService.this.isScrobbled = false;
                                handler = AutoDjService.this.handler;
                                runnable = AutoDjService.this.scrobbleCheckRunnable;
                                handler.removeCallbacks(runnable);
                                boolean paused = intent.getBooleanExtra("paused", false);
                                int state = intent.getIntExtra("state", -1);
                                AutoDjService autoDjService = AutoDjService.this;
                                if (!paused && (state == 1 || state == -1)) {
                                    z4 = true;
                                }
                                autoDjService.isPlaying = z4;
                                z = AutoDjService.this.isPlaying;
                                if (z) {
                                    AutoDjService.this.lastPlayResumeTime = System.currentTimeMillis();
                                    AutoDjService.this.scheduleScrobbleCheck();
                                }
                                AutoDjService.this.checkQueueStatus(idLong, trackTitle, trackArtist);
                                return;
                            }
                            return;
                        case 1555274284:
                            if (action.equals(PowerampAPI.ACTION_STATUS_CHANGED)) {
                                boolean paused2 = intent.getBooleanExtra("paused", false);
                                boolean newIsPlaying = !paused2;
                                if (newIsPlaying) {
                                    z3 = AutoDjService.this.isPlaying;
                                    if (!z3) {
                                        AutoDjService.this.isPlaying = true;
                                        AutoDjService.this.lastPlayResumeTime = System.currentTimeMillis();
                                        AutoDjService.this.scheduleScrobbleCheck();
                                        return;
                                    }
                                }
                                if (!newIsPlaying) {
                                    z2 = AutoDjService.this.isPlaying;
                                    if (z2) {
                                        AutoDjService.this.isPlaying = false;
                                        AutoDjService autoDjService2 = AutoDjService.this;
                                        j = AutoDjService.this.accumulatedPlayTimeMs;
                                        long currentTimeMillis = System.currentTimeMillis();
                                        j2 = AutoDjService.this.lastPlayResumeTime;
                                        autoDjService2.accumulatedPlayTimeMs = j + (currentTimeMillis - j2);
                                        handler2 = AutoDjService.this.handler;
                                        runnable2 = AutoDjService.this.scrobbleCheckRunnable;
                                        handler2.removeCallbacks(runnable2);
                                        AutoDjService.this.checkAndScrobble();
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleScrobbleCheck() {
        if (this.isScrobbled || this.currentScrobbleTrackId == -1 || this.currentScrobbleDuration == 0) {
            return;
        }
        long threshold = Math.min(30000L, this.currentScrobbleDuration / 2);
        long remaining = threshold - this.accumulatedPlayTimeMs;
        if (remaining > 0) {
            this.handler.removeCallbacks(this.scrobbleCheckRunnable);
            this.handler.postDelayed(this.scrobbleCheckRunnable, remaining);
        } else {
            checkAndScrobble();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkAndScrobble() {
        if (this.isScrobbled || this.currentScrobbleTrackId == -1 || this.currentScrobbleDuration == 0) {
            return;
        }
        Ref.LongRef totalPlayedMs = new Ref.LongRef();
        totalPlayedMs.element = this.accumulatedPlayTimeMs;
        if (this.isPlaying) {
            totalPlayedMs.element += System.currentTimeMillis() - this.lastPlayResumeTime;
        }
        long threshold = Math.min(30000L, this.currentScrobbleDuration / 2);
        if (totalPlayedMs.element >= threshold) {
            this.isScrobbled = true;
            BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new AutoDjService$checkAndScrobble$1(this, totalPlayedMs, threshold, null), 3, null);
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.powerampController = new PowerampController(this);
        AutoDjService autoDjService = this;
        PowerampController powerampController = this.powerampController;
        if (powerampController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("powerampController");
            powerampController = null;
        }
        this.recommendationEngine = new RecommendationEngine(autoDjService, powerampController);
        createNotificationChannels();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent != null ? intent.getAction() : null;
        if (action != null) {
            switch (action.hashCode()) {
                case 1532885327:
                    if (action.equals(ACTION_EXPAND_QUEUE)) {
                        String title = intent.getStringExtra(QueueActionReceiver.EXTRA_TRACK_TITLE);
                        if (title == null) {
                            title = "Unknown";
                        }
                        String stringExtra = intent.getStringExtra(QueueActionReceiver.EXTRA_TRACK_ARTIST);
                        String artist = stringExtra != null ? stringExtra : "Unknown";
                        BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new AutoDjService$onStartCommand$1(this, title, artist, null), 3, null);
                        return 2;
                    }
                    break;
                case 1704200220:
                    if (action.equals(ACTION_STOP)) {
                        stopSelf();
                        return 2;
                    }
                    break;
            }
        }
        if (!this.isServiceRunning) {
            startForeground(1, buildPersistentNotification());
            IntentFilter filter = new IntentFilter();
            filter.addAction(PowerampAPI.ACTION_TRACK_CHANGED);
            filter.addAction(PowerampAPI.ACTION_STATUS_CHANGED);
            ContextCompat.registerReceiver(this, this.trackReceiver, filter, 2);
            this.isServiceRunning = true;
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.maxmpz.poweramp.companion.AutoDjService$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AutoDjService.onStartCommand$lambda$2(AutoDjService.this);
                }
            });
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onStartCommand$lambda$2(AutoDjService this$0) {
        Toast.makeText(this$0, "Auto-DJ aktiv 🎵", 0).show();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (this.isServiceRunning) {
            unregisterReceiver(this.trackReceiver);
            this.isServiceRunning = false;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkQueueStatus(long trackId, String currentTitle, String currentArtist) {
        if (!this.isGenerating && this.promptShownForTrackId != trackId) {
            BuildersKt__Builders_commonKt.launch$default(this.serviceScope, null, null, new AutoDjService$checkQueueStatus$1(this, trackId, currentTitle, currentArtist, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showQueueEndPrompt(String trackTitle, String trackArtist) {
        Object systemService = getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager nm = (NotificationManager) systemService;
        Intent autoIntent = new Intent(this, (Class<?>) QueueActionReceiver.class);
        autoIntent.setAction(QueueActionReceiver.ACTION_AUTO_EXPAND);
        autoIntent.putExtra(QueueActionReceiver.EXTRA_TRACK_TITLE, trackTitle);
        autoIntent.putExtra(QueueActionReceiver.EXTRA_TRACK_ARTIST, trackArtist);
        PendingIntent autoPi = PendingIntent.getBroadcast(this, 10, autoIntent, 201326592);
        Intent openIntent = new Intent(this, (Class<?>) QueueActionReceiver.class);
        openIntent.setAction(QueueActionReceiver.ACTION_OPEN_APP);
        PendingIntent openPi = PendingIntent.getBroadcast(this, 11, openIntent, 201326592);
        Intent intent = new Intent(this, (Class<?>) MainActivity.class);
        intent.addFlags(268435456);
        Unit unit = Unit.INSTANCE;
        PendingIntent tapPi = PendingIntent.getActivity(this, 12, intent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_PROMPT).setSmallIcon(android.R.drawable.ic_media_play).setContentTitle("🎵 Warteschlange fast leer!").setContentText("Letzte(r) Track: " + trackTitle + " · Soll Auto-DJ die Queue erweitern?").setStyle(new NotificationCompat.BigTextStyle().bigText("Der letzte Track läuft: \"" + trackTitle + "\" von " + trackArtist + ".\n\nWähle eine Option, um die Queue zu erweitern.")).setContentIntent(tapPi).setAutoCancel(true).setPriority(1).setDefaults(3).addAction(android.R.drawable.ic_media_play, "🤖 Automatisch erweitern", autoPi).addAction(android.R.drawable.ic_menu_view, "📱 App öffnen", openPi).build();
        nm.notify(42, notification);
        Log.d("AutoDjService", "Queue-end prompt notification shown.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(6:(2:3|(7:5|6|7|8|14|15|16))|7|8|14|15|16) */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x003e, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x016d, code lost:
    
        android.util.Log.e("AutoDjService", "Auto-expand failed: " + r0.getMessage());
        r5 = r5;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002c  */
    /* JADX WARN: Type inference failed for: r5v0, types: [int, com.maxmpz.poweramp.companion.AutoDjService] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object generateAndEnqueue(java.lang.String r21, java.lang.String r22, kotlin.coroutines.Continuation<? super kotlin.Unit> r23) {
        /*
            Method dump skipped, instructions count: 410
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.AutoDjService.generateAndEnqueue(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Notification buildPersistentNotification() {
        PendingIntent tapPi = PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        Intent intent = new Intent(this, (Class<?>) AutoDjService.class);
        intent.setAction(ACTION_STOP);
        Unit unit = Unit.INSTANCE;
        PendingIntent stopPi = PendingIntent.getService(this, 1, intent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        return new NotificationCompat.Builder(this, CHANNEL_PERSISTENT).setContentTitle("Poweramp AI Auto-DJ").setContentText("Überwacht deine Queue und benachrichtigt dich am Ende.").setSmallIcon(android.R.drawable.ic_media_play).setContentIntent(tapPi).setOngoing(true).addAction(android.R.drawable.ic_menu_close_clear_cancel, "Stoppen", stopPi).build();
    }

    private final void createNotificationChannels() {
        NotificationManager nm = (NotificationManager) getSystemService(NotificationManager.class);
        if (nm == null) {
            return;
        }
        nm.createNotificationChannel(new NotificationChannel(CHANNEL_PERSISTENT, "Auto-DJ Service", 2));
        NotificationChannel promptChannel = new NotificationChannel(CHANNEL_PROMPT, "Auto-DJ Queue-Ende Benachrichtigung", 4);
        promptChannel.setDescription("Laut – erscheint wenn die Warteschlange fast leer ist");
        promptChannel.setSound(RingtoneManager.getDefaultUri(2), new AudioAttributes.Builder().setUsage(5).setContentType(4).build());
        promptChannel.enableVibration(true);
        promptChannel.setVibrationPattern(new long[]{0, 250, 100, 250});
        nm.createNotificationChannel(promptChannel);
    }
}
