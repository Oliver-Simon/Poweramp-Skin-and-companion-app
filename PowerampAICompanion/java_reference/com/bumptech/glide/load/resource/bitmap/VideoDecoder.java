package com.bumptech.glide.load.resource.bitmap;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.MediaDataSource;
import android.media.MediaExtractor;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.documentfile.provider.DocumentsContractApi19$$ExternalSyntheticAutoCloseableDispatcher0;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class VideoDecoder<T> implements ResourceDecoder<T, Bitmap> {
    public static final long DEFAULT_FRAME = -1;
    static final int DEFAULT_FRAME_OPTION = 2;
    private static final String TAG = "VideoDecoder";
    private static final String WEBM_MIME_TYPE = "video/webm";
    private final BitmapPool bitmapPool;
    private final MediaMetadataRetrieverFactory factory;
    private final MediaInitializer<T> initializer;
    public static final Option<Long> TARGET_FRAME = Option.disk("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new Option.CacheKeyUpdater<Long>() { // from class: com.bumptech.glide.load.resource.bitmap.VideoDecoder.1
        private final ByteBuffer buffer = ByteBuffer.allocate(8);

        @Override // com.bumptech.glide.load.Option.CacheKeyUpdater
        public void update(byte[] keyBytes, Long value, MessageDigest messageDigest) {
            messageDigest.update(keyBytes);
            synchronized (this.buffer) {
                this.buffer.position(0);
                messageDigest.update(this.buffer.putLong(value.longValue()).array());
            }
        }
    });
    public static final Option<Integer> FRAME_OPTION = Option.disk("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", 2, new Option.CacheKeyUpdater<Integer>() { // from class: com.bumptech.glide.load.resource.bitmap.VideoDecoder.2
        private final ByteBuffer buffer = ByteBuffer.allocate(4);

        @Override // com.bumptech.glide.load.Option.CacheKeyUpdater
        public void update(byte[] keyBytes, Integer value, MessageDigest messageDigest) {
            if (value == null) {
                return;
            }
            messageDigest.update(keyBytes);
            synchronized (this.buffer) {
                this.buffer.position(0);
                messageDigest.update(this.buffer.putInt(value.intValue()).array());
            }
        }
    });
    private static final MediaMetadataRetrieverFactory DEFAULT_FACTORY = new MediaMetadataRetrieverFactory();
    private static final List<String> PIXEL_T_BUILD_ID_PREFIXES_REQUIRING_HDR_180_ROTATION_FIX = Collections.unmodifiableList(Arrays.asList("TP1A", "TD1A.220804.031"));

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface MediaInitializer<T> {
        void initializeExtractor(MediaExtractor mediaExtractor, T t) throws IOException;

        void initializeRetriever(MediaMetadataRetriever mediaMetadataRetriever, T t);
    }

    public static ResourceDecoder<AssetFileDescriptor, Bitmap> asset(BitmapPool bitmapPool) {
        return new VideoDecoder(bitmapPool, new AssetFileDescriptorInitializer());
    }

    public static ResourceDecoder<ParcelFileDescriptor, Bitmap> parcel(BitmapPool bitmapPool) {
        return new VideoDecoder(bitmapPool, new ParcelFileDescriptorInitializer());
    }

    public static ResourceDecoder<ByteBuffer, Bitmap> byteBuffer(BitmapPool bitmapPool) {
        return new VideoDecoder(bitmapPool, new ByteBufferInitializer());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VideoDecoder(BitmapPool bitmapPool, MediaInitializer<T> initializer) {
        this(bitmapPool, initializer, DEFAULT_FACTORY);
    }

    VideoDecoder(BitmapPool bitmapPool, MediaInitializer<T> initializer, MediaMetadataRetrieverFactory factory) {
        this.bitmapPool = bitmapPool;
        this.initializer = initializer;
        this.factory = factory;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public boolean handles(T data, Options options) {
        return true;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(T resource, int outWidth, int outHeight, Options options) throws IOException {
        Integer frameOption;
        DownsampleStrategy downsampleStrategy;
        long frameTimeMicros = ((Long) options.get(TARGET_FRAME)).longValue();
        if (frameTimeMicros >= 0 || frameTimeMicros == -1) {
            Integer frameOption2 = (Integer) options.get(FRAME_OPTION);
            if (frameOption2 != null) {
                frameOption = frameOption2;
            } else {
                frameOption = 2;
            }
            DownsampleStrategy downsampleStrategy2 = (DownsampleStrategy) options.get(DownsampleStrategy.OPTION);
            if (downsampleStrategy2 != null) {
                downsampleStrategy = downsampleStrategy2;
            } else {
                downsampleStrategy = DownsampleStrategy.DEFAULT;
            }
            MediaMetadataRetriever mediaMetadataRetriever = this.factory.build();
            try {
                this.initializer.initializeRetriever(mediaMetadataRetriever, resource);
                Bitmap result = decodeFrame(resource, mediaMetadataRetriever, frameTimeMicros, frameOption.intValue(), outWidth, outHeight, downsampleStrategy);
                return BitmapResource.obtain(result, this.bitmapPool);
            } finally {
                if (Build.VERSION.SDK_INT >= 29) {
                    DocumentsContractApi19$$ExternalSyntheticAutoCloseableDispatcher0.m(mediaMetadataRetriever);
                } else {
                    mediaMetadataRetriever.release();
                }
            }
        }
        throw new IllegalArgumentException("Requested frame must be non-negative, or DEFAULT_FRAME, given: " + frameTimeMicros);
    }

    private Bitmap decodeFrame(T resource, MediaMetadataRetriever mediaMetadataRetriever, long frameTimeMicros, int frameOption, int outWidth, int outHeight, DownsampleStrategy strategy) {
        if (isUnsupportedFormat(resource, mediaMetadataRetriever)) {
            throw new IllegalStateException("Cannot decode VP8 video on CrOS.");
        }
        Bitmap result = null;
        if (Build.VERSION.SDK_INT >= 27 && outWidth != Integer.MIN_VALUE && outHeight != Integer.MIN_VALUE && strategy != DownsampleStrategy.NONE) {
            result = decodeScaledFrame(mediaMetadataRetriever, frameTimeMicros, frameOption, outWidth, outHeight, strategy);
        }
        if (result == null) {
            result = decodeOriginalFrame(mediaMetadataRetriever, frameTimeMicros, frameOption);
        }
        Bitmap result2 = correctHdr180DegVideoFrameOrientation(mediaMetadataRetriever, result);
        if (result2 == null) {
            throw new VideoDecoderException();
        }
        return result2;
    }

    private static Bitmap correctHdr180DegVideoFrameOrientation(MediaMetadataRetriever mediaMetadataRetriever, Bitmap frame) {
        if (!isHdr180RotationFixRequired()) {
            return frame;
        }
        boolean requiresHdr180RotationFix = false;
        try {
            if (isHDR(mediaMetadataRetriever)) {
                String rotationString = mediaMetadataRetriever.extractMetadata(24);
                int rotation = Integer.parseInt(rotationString);
                requiresHdr180RotationFix = Math.abs(rotation) == 180;
            }
        } catch (NumberFormatException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Exception trying to extract HDR transfer function or rotation");
            }
        }
        if (!requiresHdr180RotationFix) {
            return frame;
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Applying HDR 180 deg thumbnail correction");
        }
        Matrix rotationMatrix = new Matrix();
        rotationMatrix.postRotate(180.0f, frame.getWidth() / 2.0f, frame.getHeight() / 2.0f);
        return Bitmap.createBitmap(frame, 0, 0, frame.getWidth(), frame.getHeight(), rotationMatrix, true);
    }

    private static boolean isHDR(MediaMetadataRetriever mediaMetadataRetriever) throws NumberFormatException {
        String colorTransferString = mediaMetadataRetriever.extractMetadata(36);
        String colorStandardString = mediaMetadataRetriever.extractMetadata(35);
        int colorTransfer = Integer.parseInt(colorTransferString);
        int colorStandard = Integer.parseInt(colorStandardString);
        return (colorTransfer == 7 || colorTransfer == 6) && colorStandard == 6;
    }

    static boolean isHdr180RotationFixRequired() {
        if (Build.MODEL.startsWith("Pixel") && Build.VERSION.SDK_INT == 33) {
            return isTBuildRequiringRotationFix();
        }
        return Build.VERSION.SDK_INT >= 30 && Build.VERSION.SDK_INT < 33;
    }

    private static boolean isTBuildRequiringRotationFix() {
        for (String buildId : PIXEL_T_BUILD_ID_PREFIXES_REQUIRING_HDR_180_ROTATION_FIX) {
            if (Build.ID.startsWith(buildId)) {
                return true;
            }
        }
        return false;
    }

    private static Bitmap decodeScaledFrame(MediaMetadataRetriever mediaMetadataRetriever, long frameTimeMicros, int frameOption, int outWidth, int outHeight, DownsampleStrategy strategy) {
        try {
            int originalWidth = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
            int originalHeight = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
            int orientation = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
            if (orientation == 90 || orientation == 270) {
                originalWidth = originalHeight;
                originalHeight = originalWidth;
            }
            try {
                float scaleFactor = strategy.getScaleFactor(originalWidth, originalHeight, outWidth, outHeight);
                int decodeWidth = Math.round(originalWidth * scaleFactor);
                int decodeHeight = Math.round(originalHeight * scaleFactor);
                return mediaMetadataRetriever.getScaledFrameAtTime(frameTimeMicros, frameOption, decodeWidth, decodeHeight);
            } catch (Throwable th) {
                t = th;
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Exception trying to decode a scaled frame on oreo+, falling back to a fullsize frame", t);
                    return null;
                }
                return null;
            }
        } catch (Throwable th2) {
            t = th2;
        }
    }

    private static Bitmap decodeOriginalFrame(MediaMetadataRetriever mediaMetadataRetriever, long frameTimeMicros, int frameOption) {
        return mediaMetadataRetriever.getFrameAtTime(frameTimeMicros, frameOption);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0070, code lost:
    
        if (r4 != null) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean isUnsupportedFormat(T r12, android.media.MediaMetadataRetriever r13) {
        /*
            r11 = this;
            java.lang.String r0 = "VideoDecoder"
            java.lang.String r1 = android.os.Build.DEVICE
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L14
            java.lang.String r1 = android.os.Build.DEVICE
            java.lang.String r4 = ".+_cheets|cheets_.+"
            boolean r1 = r1.matches(r4)
            if (r1 == 0) goto L14
            r1 = r2
            goto L15
        L14:
            r1 = r3
        L15:
            if (r1 != 0) goto L18
            return r3
        L18:
            r4 = 0
            r5 = 12
            java.lang.String r5 = r13.extractMetadata(r5)     // Catch: java.lang.Throwable -> L63
            java.lang.String r6 = "video/webm"
            boolean r6 = r6.equals(r5)     // Catch: java.lang.Throwable -> L63
            if (r6 != 0) goto L30
        L2a:
            if (r4 == 0) goto L2f
            r4.release()
        L2f:
            return r3
        L30:
            android.media.MediaExtractor r6 = new android.media.MediaExtractor     // Catch: java.lang.Throwable -> L63
            r6.<init>()     // Catch: java.lang.Throwable -> L63
            r4 = r6
            com.bumptech.glide.load.resource.bitmap.VideoDecoder$MediaInitializer<T> r6 = r11.initializer     // Catch: java.lang.Throwable -> L63
            r6.initializeExtractor(r4, r12)     // Catch: java.lang.Throwable -> L63
            int r6 = r4.getTrackCount()     // Catch: java.lang.Throwable -> L63
            r7 = 0
        L40:
            if (r7 >= r6) goto L5e
            android.media.MediaFormat r8 = r4.getTrackFormat(r7)     // Catch: java.lang.Throwable -> L63
            java.lang.String r9 = "mime"
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.Throwable -> L63
            java.lang.String r10 = "video/x-vnd.on2.vp8"
            boolean r10 = r10.equals(r9)     // Catch: java.lang.Throwable -> L63
            if (r10 == 0) goto L5b
        L57:
            r4.release()
            return r2
        L5b:
            int r7 = r7 + 1
            goto L40
        L5e:
        L5f:
            r4.release()
            goto L73
        L63:
            r2 = move-exception
            r5 = 3
            boolean r5 = android.util.Log.isLoggable(r0, r5)     // Catch: java.lang.Throwable -> L74
            if (r5 == 0) goto L70
            java.lang.String r5 = "Exception trying to extract track info for a webm video on CrOS."
            android.util.Log.d(r0, r5, r2)     // Catch: java.lang.Throwable -> L74
        L70:
            if (r4 == 0) goto L73
            goto L5f
        L73:
            return r3
        L74:
            r0 = move-exception
            if (r4 == 0) goto L7a
            r4.release()
        L7a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.VideoDecoder.isUnsupportedFormat(java.lang.Object, android.media.MediaMetadataRetriever):boolean");
    }

    /* loaded from: classes.dex */
    static class MediaMetadataRetrieverFactory {
        MediaMetadataRetrieverFactory() {
        }

        public MediaMetadataRetriever build() {
            return new MediaMetadataRetriever();
        }
    }

    /* loaded from: classes.dex */
    private static final class AssetFileDescriptorInitializer implements MediaInitializer<AssetFileDescriptor> {
        private AssetFileDescriptorInitializer() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.VideoDecoder.MediaInitializer
        public void initializeRetriever(MediaMetadataRetriever retriever, AssetFileDescriptor data) {
            retriever.setDataSource(data.getFileDescriptor(), data.getStartOffset(), data.getLength());
        }

        @Override // com.bumptech.glide.load.resource.bitmap.VideoDecoder.MediaInitializer
        public void initializeExtractor(MediaExtractor extractor, AssetFileDescriptor data) throws IOException {
            extractor.setDataSource(data.getFileDescriptor(), data.getStartOffset(), data.getLength());
        }
    }

    /* loaded from: classes.dex */
    static final class ParcelFileDescriptorInitializer implements MediaInitializer<ParcelFileDescriptor> {
        @Override // com.bumptech.glide.load.resource.bitmap.VideoDecoder.MediaInitializer
        public void initializeRetriever(MediaMetadataRetriever retriever, ParcelFileDescriptor data) {
            retriever.setDataSource(data.getFileDescriptor());
        }

        @Override // com.bumptech.glide.load.resource.bitmap.VideoDecoder.MediaInitializer
        public void initializeExtractor(MediaExtractor extractor, ParcelFileDescriptor data) throws IOException {
            extractor.setDataSource(data.getFileDescriptor());
        }
    }

    /* loaded from: classes.dex */
    static final class ByteBufferInitializer implements MediaInitializer<ByteBuffer> {
        ByteBufferInitializer() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.VideoDecoder.MediaInitializer
        public void initializeRetriever(MediaMetadataRetriever retriever, ByteBuffer data) {
            retriever.setDataSource(getMediaDataSource(data));
        }

        @Override // com.bumptech.glide.load.resource.bitmap.VideoDecoder.MediaInitializer
        public void initializeExtractor(MediaExtractor extractor, ByteBuffer data) throws IOException {
            extractor.setDataSource(getMediaDataSource(data));
        }

        private MediaDataSource getMediaDataSource(final ByteBuffer data) {
            return new MediaDataSource() { // from class: com.bumptech.glide.load.resource.bitmap.VideoDecoder.ByteBufferInitializer.1
                @Override // android.media.MediaDataSource
                public int readAt(long position, byte[] buffer, int offset, int size) {
                    if (position >= data.limit()) {
                        return -1;
                    }
                    data.position((int) position);
                    int numBytesRead = Math.min(size, data.remaining());
                    data.get(buffer, offset, numBytesRead);
                    return numBytesRead;
                }

                @Override // android.media.MediaDataSource
                public long getSize() {
                    return data.limit();
                }

                @Override // java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class VideoDecoderException extends RuntimeException {
        private static final long serialVersionUID = -2556382523004027815L;

        VideoDecoderException() {
            super("MediaMetadataRetriever failed to retrieve a frame without throwing, check the adb logs for .*MetadataRetriever.* prior to this exception for details");
        }
    }
}
