package com.maxmpz.poweramp.companion;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QueueActionReceiver.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\u000b"}, d2 = {"Lcom/maxmpz/poweramp/companion/QueueActionReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class QueueActionReceiver extends BroadcastReceiver {
    public static final String ACTION_AUTO_EXPAND = "com.maxmpz.poweramp.companion.ACTION_AUTO_EXPAND";
    public static final String ACTION_OPEN_APP = "com.maxmpz.poweramp.companion.ACTION_OPEN_APP";
    public static final String EXTRA_TRACK_ARTIST = "extra_track_artist";
    public static final String EXTRA_TRACK_TITLE = "extra_track_title";
    public static final int NOTIFICATION_ID_QUEUE_PROMPT = 42;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Object systemService = context.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager nm = (NotificationManager) systemService;
        nm.cancel(42);
        String action = intent.getAction();
        if (action != null) {
            switch (action.hashCode()) {
                case -2000570716:
                    if (action.equals(ACTION_AUTO_EXPAND)) {
                        String title = intent.getStringExtra(EXTRA_TRACK_TITLE);
                        if (title == null) {
                            title = "Unknown";
                        }
                        String stringExtra = intent.getStringExtra(EXTRA_TRACK_ARTIST);
                        String artist = stringExtra != null ? stringExtra : "Unknown";
                        Log.d("QueueActionReceiver", "Auto-expand triggered for: " + title + " by " + artist);
                        Intent expandIntent = new Intent(context, (Class<?>) AutoDjService.class);
                        expandIntent.setAction(AutoDjService.ACTION_EXPAND_QUEUE);
                        expandIntent.putExtra(EXTRA_TRACK_TITLE, title);
                        expandIntent.putExtra(EXTRA_TRACK_ARTIST, artist);
                        context.startService(expandIntent);
                        return;
                    }
                    return;
                case -855824046:
                    if (action.equals(ACTION_OPEN_APP)) {
                        Intent launchIntent = new Intent(context, (Class<?>) MainActivity.class);
                        launchIntent.addFlags(335544320);
                        launchIntent.putExtra("nav_tab", "generate");
                        context.startActivity(launchIntent);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
