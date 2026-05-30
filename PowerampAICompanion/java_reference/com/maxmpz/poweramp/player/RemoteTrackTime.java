package com.maxmpz.poweramp.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import androidx.core.content.ContextCompat;

/* loaded from: classes3.dex */
public class RemoteTrackTime {
    private static final boolean LOG = false;
    private static final String TAG = "RemoteTrackTime";
    private static final int UPDATE_DELAY = 1000;
    private final Context mContext;
    private boolean mPlaying;
    int mPosition;
    int mStartPosition;
    long mStartTimeMs;
    TrackTimeListener mTrackTimeListener;
    final Handler mHandler = new Handler();
    private final BroadcastReceiver mTrackPosSyncReceiver = new BroadcastReceiver() { // from class: com.maxmpz.poweramp.player.RemoteTrackTime.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int pos = intent.getIntExtra("pos", 0);
            RemoteTrackTime.this.updateTrackPosition(pos);
        }
    };
    protected final Runnable mTickRunnable = new Runnable() { // from class: com.maxmpz.poweramp.player.RemoteTrackTime.2
        @Override // java.lang.Runnable
        public void run() {
            RemoteTrackTime.this.mPosition = (((int) ((System.currentTimeMillis() - RemoteTrackTime.this.mStartTimeMs) + 500)) / 1000) + RemoteTrackTime.this.mStartPosition;
            if (RemoteTrackTime.this.mTrackTimeListener != null) {
                RemoteTrackTime.this.mTrackTimeListener.onTrackPositionChanged(RemoteTrackTime.this.mPosition);
            }
            RemoteTrackTime.this.mHandler.removeCallbacks(RemoteTrackTime.this.mTickRunnable);
            RemoteTrackTime.this.mHandler.postDelayed(RemoteTrackTime.this.mTickRunnable, 1000L);
        }
    };

    /* loaded from: classes3.dex */
    public interface TrackTimeListener {
        void onTrackDurationChanged(int i);

        void onTrackPositionChanged(int i);
    }

    public RemoteTrackTime(Context context) {
        this.mContext = context;
    }

    public void registerAndLoadStatus() {
        IntentFilter filter = new IntentFilter(PowerampAPI.ACTION_TRACK_POS_SYNC);
        ContextCompat.registerReceiver(this.mContext, this.mTrackPosSyncReceiver, filter, 2);
        PowerampAPIHelper.sendPAIntent(this.mContext, new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra("cmd", 16));
        if (this.mPlaying) {
            this.mHandler.removeCallbacks(this.mTickRunnable);
            this.mHandler.postDelayed(this.mTickRunnable, 0L);
        }
    }

    public void unregister() {
        try {
            this.mContext.unregisterReceiver(this.mTrackPosSyncReceiver);
        } catch (Exception e) {
        }
        this.mHandler.removeCallbacks(this.mTickRunnable);
    }

    public void setTrackTimeListener(TrackTimeListener l) {
        this.mTrackTimeListener = l;
    }

    public void updateTrackDuration(int duration) {
        if (this.mTrackTimeListener != null) {
            this.mTrackTimeListener.onTrackDurationChanged(duration);
        }
    }

    public void updateTrackPosition(int position) {
        this.mPosition = position;
        if (this.mPlaying) {
            this.mStartTimeMs = System.currentTimeMillis();
            this.mStartPosition = this.mPosition;
        }
        if (this.mTrackTimeListener != null) {
            this.mTrackTimeListener.onTrackPositionChanged(position);
        }
    }

    public void startSongProgress() {
        if (!this.mPlaying) {
            this.mStartTimeMs = System.currentTimeMillis();
            this.mStartPosition = this.mPosition;
            this.mHandler.removeCallbacks(this.mTickRunnable);
            this.mHandler.postDelayed(this.mTickRunnable, 1000L);
            this.mPlaying = true;
        }
    }

    public void stopSongProgress() {
        if (this.mPlaying) {
            this.mHandler.removeCallbacks(this.mTickRunnable);
            this.mPlaying = false;
        }
    }
}
