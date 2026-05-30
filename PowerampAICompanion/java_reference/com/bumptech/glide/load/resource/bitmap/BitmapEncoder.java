package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;

/* loaded from: classes.dex */
public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    private static final String TAG = "BitmapEncoder";
    private final ArrayPool arrayPool;
    public static final Option<Integer> COMPRESSION_QUALITY = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);
    public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");

    public BitmapEncoder(ArrayPool arrayPool) {
        this.arrayPool = arrayPool;
    }

    @Deprecated
    public BitmapEncoder() {
        this.arrayPool = null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0060, code lost:
    
        if (r7 != null) goto L42;
     */
    @Override // com.bumptech.glide.load.Encoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean encode(com.bumptech.glide.load.engine.Resource<android.graphics.Bitmap> r12, java.io.File r13, com.bumptech.glide.load.Options r14) {
        /*
            r11 = this;
            java.lang.String r0 = "BitmapEncoder"
            java.lang.Object r1 = r12.get()
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            android.graphics.Bitmap$CompressFormat r2 = r11.getFormat(r1, r14)
            int r3 = r1.getWidth()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            int r4 = r1.getHeight()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.String r5 = "encode: [%dx%d] %s"
            com.bumptech.glide.util.pool.GlideTrace.beginSectionFormat(r5, r3, r4, r2)
            long r3 = com.bumptech.glide.util.LogTime.getLogTime()     // Catch: java.lang.Throwable -> Lcb
            com.bumptech.glide.load.Option<java.lang.Integer> r5 = com.bumptech.glide.load.resource.bitmap.BitmapEncoder.COMPRESSION_QUALITY     // Catch: java.lang.Throwable -> Lcb
            java.lang.Object r5 = r14.get(r5)     // Catch: java.lang.Throwable -> Lcb
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch: java.lang.Throwable -> Lcb
            int r5 = r5.intValue()     // Catch: java.lang.Throwable -> Lcb
            r6 = 0
            r7 = 0
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53
            r8.<init>(r13)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53
            r7 = r8
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r8 = r11.arrayPool     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53
            if (r8 == 0) goto L46
            com.bumptech.glide.load.data.BufferedOutputStream r8 = new com.bumptech.glide.load.data.BufferedOutputStream     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r9 = r11.arrayPool     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53
            r8.<init>(r7, r9)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53
            r7 = r8
        L46:
            r1.compress(r2, r5, r7)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53
            r7.close()     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53
            r6 = 1
            goto L62
        L4e:
            goto L66
        L4f:
            r8 = move-exception
            goto L4e
        L51:
            r0 = move-exception
            goto Lc2
        L53:
            r8 = move-exception
            r9 = 3
            boolean r9 = android.util.Log.isLoggable(r0, r9)     // Catch: java.lang.Throwable -> L51
            if (r9 == 0) goto L60
            java.lang.String r9 = "Failed to encode Bitmap"
            android.util.Log.d(r0, r9, r8)     // Catch: java.lang.Throwable -> L51
        L60:
            if (r7 == 0) goto L66
        L62:
            r7.close()     // Catch: java.io.IOException -> L4f java.lang.Throwable -> Lcb
            goto L4e
        L66:
            r8 = 2
            boolean r8 = android.util.Log.isLoggable(r0, r8)     // Catch: java.lang.Throwable -> Lcb
            if (r8 == 0) goto Lbd
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lcb
            r8.<init>()     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r9 = "Compressed with type: "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: java.lang.Throwable -> Lcb
            java.lang.StringBuilder r8 = r8.append(r2)     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r9 = " of size "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: java.lang.Throwable -> Lcb
            int r9 = com.bumptech.glide.util.Util.getBitmapByteSize(r1)     // Catch: java.lang.Throwable -> Lcb
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r9 = " in "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: java.lang.Throwable -> Lcb
            double r9 = com.bumptech.glide.util.LogTime.getElapsedMillis(r3)     // Catch: java.lang.Throwable -> Lcb
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r9 = ", options format: "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: java.lang.Throwable -> Lcb
            com.bumptech.glide.load.Option<android.graphics.Bitmap$CompressFormat> r9 = com.bumptech.glide.load.resource.bitmap.BitmapEncoder.COMPRESSION_FORMAT     // Catch: java.lang.Throwable -> Lcb
            java.lang.Object r9 = r14.get(r9)     // Catch: java.lang.Throwable -> Lcb
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r9 = ", hasAlpha: "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: java.lang.Throwable -> Lcb
            boolean r9 = r1.hasAlpha()     // Catch: java.lang.Throwable -> Lcb
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch: java.lang.Throwable -> Lcb
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> Lcb
            android.util.Log.v(r0, r8)     // Catch: java.lang.Throwable -> Lcb
        Lbd:
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            return r6
        Lc2:
            if (r7 == 0) goto Lc9
            r7.close()     // Catch: java.io.IOException -> Lc8 java.lang.Throwable -> Lcb
            goto Lc9
        Lc8:
            r8 = move-exception
        Lc9:
            throw r0     // Catch: java.lang.Throwable -> Lcb
        Lcb:
            r0 = move-exception
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.BitmapEncoder.encode(com.bumptech.glide.load.engine.Resource, java.io.File, com.bumptech.glide.load.Options):boolean");
    }

    private Bitmap.CompressFormat getFormat(Bitmap bitmap, Options options) {
        Bitmap.CompressFormat format = (Bitmap.CompressFormat) options.get(COMPRESSION_FORMAT);
        if (format != null) {
            return format;
        }
        if (bitmap.hasAlpha()) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    @Override // com.bumptech.glide.load.ResourceEncoder
    public EncodeStrategy getEncodeStrategy(Options options) {
        return EncodeStrategy.TRANSFORMED;
    }
}
