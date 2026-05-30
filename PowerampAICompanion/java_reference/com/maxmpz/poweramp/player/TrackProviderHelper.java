package com.maxmpz.poweramp.player;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/* loaded from: classes3.dex */
public class TrackProviderHelper {
    private static final boolean LOG = false;
    private static final String TAG = "TrackProviderHelper";

    public static float[] bytesToFloats(byte[] waveBytes) {
        if (waveBytes.length == 0) {
            return new float[0];
        }
        int floatSize = waveBytes.length / 4;
        float[] wave = new float[floatSize];
        ByteBuffer bb = ByteBuffer.wrap(waveBytes);
        bb.asFloatBuffer().get(wave);
        return wave;
    }

    public static byte[] floatsToBytes(float[] wave) {
        if (wave.length == 0) {
            return new byte[0];
        }
        ByteBuffer bb = ByteBuffer.allocate(wave.length * 4);
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(wave);
        return bb.array();
    }
}
