package com.maxmpz.poweramp.widgetpackcommon;

import android.graphics.Bitmap;

/* loaded from: classes2.dex */
public class WidgetUpdateData {
    private static final boolean LOG = false;
    private static final String TAG = "WidgetUpdateData";
    public String album;
    public Bitmap albumArtBitmap;
    public boolean albumArtNoAnim;
    public boolean albumArtResolved;
    public String albumArtSource;
    public long albumArtTimestamp;
    public int apiVersion;
    public String artist;
    public int flags;
    public boolean hasTrack;
    public int listSize;
    public boolean playing;
    public int posInList;
    public boolean supportsCatNav;
    public String title;
    public int shuffle = 0;
    public int repeat = 0;

    public String toString() {
        return super.toString() + " hasTrack=" + this.hasTrack + " title=" + this.title + " album=" + this.album + " artist=" + this.artist + " supportsCatNav=" + this.supportsCatNav + " posInList=" + this.posInList + " listSize=" + this.listSize + " flags=0x" + Integer.toHexString(this.flags) + " albumArtBitmap=" + this.albumArtBitmap + " albumArtTimestamp=" + this.albumArtTimestamp + " albumArtSource=" + this.albumArtSource + " playing=" + this.playing + " shuffle=" + this.shuffle + " repeat=" + this.repeat;
    }

    public void resetTrackData() {
        this.hasTrack = false;
        this.artist = null;
        this.album = null;
        this.title = null;
        this.supportsCatNav = false;
        this.posInList = 0;
        this.listSize = 0;
    }
}
