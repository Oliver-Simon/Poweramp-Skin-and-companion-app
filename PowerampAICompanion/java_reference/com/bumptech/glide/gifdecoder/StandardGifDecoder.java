package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.gifdecoder.GifDecoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes.dex */
public class StandardGifDecoder implements GifDecoder {
    private static final int BYTES_PER_INTEGER = 4;
    private static final int COLOR_TRANSPARENT_BLACK = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MASK_INT_LOWEST_BYTE = 255;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    private static final String TAG = StandardGifDecoder.class.getSimpleName();
    private int[] act;
    private Bitmap.Config bitmapConfig;
    private final GifDecoder.BitmapProvider bitmapProvider;
    private byte[] block;
    private int downsampledHeight;
    private int downsampledWidth;
    private int framePointer;
    private GifHeader header;
    private Boolean isFirstFrameTransparent;
    private byte[] mainPixels;
    private int[] mainScratch;
    private GifHeaderParser parser;
    private final int[] pct;
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private int sampleSize;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;

    public StandardGifDecoder(GifDecoder.BitmapProvider provider, GifHeader gifHeader, ByteBuffer rawData) {
        this(provider, gifHeader, rawData, 1);
    }

    public StandardGifDecoder(GifDecoder.BitmapProvider provider, GifHeader gifHeader, ByteBuffer rawData, int sampleSize) {
        this(provider);
        setData(gifHeader, rawData, sampleSize);
    }

    public StandardGifDecoder(GifDecoder.BitmapProvider provider) {
        this.pct = new int[256];
        this.bitmapConfig = Bitmap.Config.ARGB_8888;
        this.bitmapProvider = provider;
        this.header = new GifHeader();
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getWidth() {
        return this.header.width;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getHeight() {
        return this.header.height;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public ByteBuffer getData() {
        return this.rawData;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getStatus() {
        return this.status;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getDelay(int n) {
        if (n < 0 || n >= this.header.frameCount) {
            return -1;
        }
        int delay = this.header.frames.get(n).delay;
        return delay;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getNextDelay() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            return 0;
        }
        return getDelay(this.framePointer);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getFrameCount() {
        return this.header.frameCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    @Deprecated
    public int getLoopCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        return this.header.loopCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getNetscapeLoopCount() {
        return this.header.loopCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getTotalIterationCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        if (this.header.loopCount == 0) {
            return 0;
        }
        return this.header.loopCount + 1;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getByteSize() {
        return this.rawData.limit() + this.mainPixels.length + (this.mainScratch.length * 4);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized Bitmap getNextFrame() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Unable to decode frame, frameCount=" + this.header.frameCount + ", framePointer=" + this.framePointer);
            }
            this.status = 1;
        }
        if (this.status != 1 && this.status != 2) {
            this.status = 0;
            if (this.block == null) {
                this.block = this.bitmapProvider.obtainByteArray(255);
            }
            GifFrame currentFrame = this.header.frames.get(this.framePointer);
            GifFrame previousFrame = null;
            int previousIndex = this.framePointer - 1;
            if (previousIndex >= 0) {
                previousFrame = this.header.frames.get(previousIndex);
            }
            this.act = currentFrame.lct != null ? currentFrame.lct : this.header.gct;
            if (this.act == null) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "No valid color table found for frame #" + this.framePointer);
                }
                this.status = 1;
                return null;
            }
            if (currentFrame.transparency) {
                System.arraycopy(this.act, 0, this.pct, 0, this.act.length);
                this.act = this.pct;
                this.act[currentFrame.transIndex] = 0;
                if (currentFrame.dispose == 2 && this.framePointer == 0) {
                    this.isFirstFrameTransparent = true;
                }
            }
            return setPixels(currentFrame, previousFrame);
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Unable to decode frame, status=" + this.status);
        }
        return null;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int read(InputStream is, int contentLength) {
        if (is != null) {
            int capacity = contentLength > 0 ? contentLength + 4096 : 16384;
            try {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream(capacity);
                byte[] data = new byte[16384];
                while (true) {
                    int nRead = is.read(data, 0, data.length);
                    if (nRead == -1) {
                        break;
                    }
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                read(buffer.toByteArray());
            } catch (IOException e) {
                Log.w(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (is != null) {
            try {
                is.close();
            } catch (IOException e2) {
                Log.w(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void clear() {
        this.header = null;
        if (this.mainPixels != null) {
            this.bitmapProvider.release(this.mainPixels);
        }
        if (this.mainScratch != null) {
            this.bitmapProvider.release(this.mainScratch);
        }
        if (this.previousImage != null) {
            this.bitmapProvider.release(this.previousImage);
        }
        this.previousImage = null;
        this.rawData = null;
        this.isFirstFrameTransparent = null;
        if (this.block != null) {
            this.bitmapProvider.release(this.block);
        }
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(GifHeader header, byte[] data) {
        setData(header, ByteBuffer.wrap(data));
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(GifHeader header, ByteBuffer buffer) {
        setData(header, buffer, 1);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(GifHeader header, ByteBuffer buffer, int sampleSize) {
        if (sampleSize <= 0) {
            throw new IllegalArgumentException("Sample size must be >=0, not: " + sampleSize);
        }
        int sampleSize2 = Integer.highestOneBit(sampleSize);
        this.status = 0;
        this.header = header;
        this.framePointer = -1;
        this.rawData = buffer.asReadOnlyBuffer();
        this.rawData.position(0);
        this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        this.savePrevious = false;
        Iterator<GifFrame> it = header.frames.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            GifFrame frame = it.next();
            if (frame.dispose == 3) {
                this.savePrevious = true;
                break;
            }
        }
        this.sampleSize = sampleSize2;
        this.downsampledWidth = header.width / sampleSize2;
        this.downsampledHeight = header.height / sampleSize2;
        this.mainPixels = this.bitmapProvider.obtainByteArray(header.width * header.height);
        this.mainScratch = this.bitmapProvider.obtainIntArray(this.downsampledWidth * this.downsampledHeight);
    }

    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized int read(byte[] data) {
        this.header = getHeaderParser().setData(data).parseHeader();
        if (data != null) {
            setData(this.header, data);
        }
        return this.status;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void setDefaultBitmapConfig(Bitmap.Config config) {
        if (config != Bitmap.Config.ARGB_8888 && config != Bitmap.Config.RGB_565) {
            throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + Bitmap.Config.ARGB_8888 + " or " + Bitmap.Config.RGB_565);
        }
        this.bitmapConfig = config;
    }

    private Bitmap setPixels(GifFrame currentFrame, GifFrame previousFrame) {
        int[] dest = this.mainScratch;
        if (previousFrame == null) {
            if (this.previousImage != null) {
                this.bitmapProvider.release(this.previousImage);
            }
            this.previousImage = null;
            Arrays.fill(dest, 0);
        }
        if (previousFrame != null && previousFrame.dispose == 3 && this.previousImage == null) {
            Arrays.fill(dest, 0);
        }
        if (previousFrame != null && previousFrame.dispose > 0) {
            if (previousFrame.dispose != 2) {
                if (previousFrame.dispose == 3 && this.previousImage != null) {
                    this.previousImage.getPixels(dest, 0, this.downsampledWidth, 0, 0, this.downsampledWidth, this.downsampledHeight);
                }
            } else {
                int c = 0;
                if (!currentFrame.transparency) {
                    c = this.header.bgColor;
                    if (currentFrame.lct != null && this.header.bgIndex == currentFrame.transIndex) {
                        c = 0;
                    }
                }
                int downsampledIH = previousFrame.ih / this.sampleSize;
                int downsampledIY = previousFrame.iy / this.sampleSize;
                int downsampledIW = previousFrame.iw / this.sampleSize;
                int downsampledIX = previousFrame.ix / this.sampleSize;
                int topLeft = (this.downsampledWidth * downsampledIY) + downsampledIX;
                int bottomLeft = (this.downsampledWidth * downsampledIH) + topLeft;
                int left = topLeft;
                while (left < bottomLeft) {
                    int right = left + downsampledIW;
                    for (int pointer = left; pointer < right; pointer++) {
                        dest[pointer] = c;
                    }
                    int right2 = this.downsampledWidth;
                    left += right2;
                }
            }
        }
        decodeBitmapData(currentFrame);
        if (currentFrame.interlace || this.sampleSize != 1) {
            copyCopyIntoScratchRobust(currentFrame);
        } else {
            copyIntoScratchFast(currentFrame);
        }
        if (this.savePrevious && (currentFrame.dispose == 0 || currentFrame.dispose == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            this.previousImage.setPixels(dest, 0, this.downsampledWidth, 0, 0, this.downsampledWidth, this.downsampledHeight);
        }
        Bitmap result = getNextBitmap();
        result.setPixels(dest, 0, this.downsampledWidth, 0, 0, this.downsampledWidth, this.downsampledHeight);
        return result;
    }

    private void copyIntoScratchFast(GifFrame currentFrame) {
        GifFrame gifFrame = currentFrame;
        int[] dest = this.mainScratch;
        int downsampledIH = gifFrame.ih;
        int downsampledIY = gifFrame.iy;
        int downsampledIW = gifFrame.iw;
        int downsampledIX = gifFrame.ix;
        boolean isFirstFrame = this.framePointer == 0;
        int width = this.downsampledWidth;
        byte[] mainPixels = this.mainPixels;
        int[] act = this.act;
        byte transparentColorIndex = -1;
        int i = 0;
        while (i < downsampledIH) {
            int line = i + downsampledIY;
            int k = line * width;
            int dx = k + downsampledIX;
            int dlim = dx + downsampledIW;
            if (k + width < dlim) {
                dlim = k + width;
            }
            int sx = gifFrame.iw * i;
            int dx2 = dx;
            while (dx2 < dlim) {
                int dx3 = dx2;
                byte dx4 = mainPixels[sx];
                int[] dest2 = dest;
                int currentColorIndex = dx4 & 255;
                if (currentColorIndex != transparentColorIndex) {
                    int color = act[currentColorIndex];
                    if (color != 0) {
                        dest2[dx3] = color;
                    } else {
                        transparentColorIndex = dx4;
                    }
                }
                sx++;
                dx2 = dx3 + 1;
                dest = dest2;
            }
            i++;
            gifFrame = currentFrame;
        }
        this.isFirstFrameTransparent = Boolean.valueOf((this.isFirstFrameTransparent != null && this.isFirstFrameTransparent.booleanValue()) || (this.isFirstFrameTransparent == null && isFirstFrame && transparentColorIndex != -1));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void copyCopyIntoScratchRobust(GifFrame currentFrame) {
        int sx;
        int dx;
        int[] dest = this.mainScratch;
        int downsampledIH = currentFrame.ih / this.sampleSize;
        int downsampledIY = currentFrame.iy / this.sampleSize;
        int downsampledIW = currentFrame.iw / this.sampleSize;
        int downsampledIX = currentFrame.ix / this.sampleSize;
        int pass = 1;
        int inc = 8;
        int iline = 0;
        boolean isFirstFrame = this.framePointer == 0;
        int sampleSize = this.sampleSize;
        int downsampledWidth = this.downsampledWidth;
        int downsampledHeight = this.downsampledHeight;
        byte[] mainPixels = this.mainPixels;
        int[] act = this.act;
        Boolean isFirstFrameTransparent = this.isFirstFrameTransparent;
        Boolean isFirstFrameTransparent2 = isFirstFrameTransparent;
        int line = 0;
        while (line < downsampledIH) {
            int line2 = line;
            int i = line;
            if (currentFrame.interlace) {
                if (iline >= downsampledIH) {
                    pass++;
                    switch (pass) {
                        case 2:
                            iline = 4;
                            break;
                        case 3:
                            iline = 2;
                            inc = 4;
                            break;
                        case 4:
                            iline = 1;
                            inc = 2;
                            break;
                    }
                }
                line2 = iline;
                iline += inc;
            }
            int line3 = line2 + downsampledIY;
            int line4 = downsampledIH;
            boolean isNotDownsampling = sampleSize == 1;
            if (line3 < downsampledHeight) {
                int k = line3 * downsampledWidth;
                int dx2 = k + downsampledIX;
                int dlim = dx2 + downsampledIW;
                boolean isNotDownsampling2 = isNotDownsampling;
                if (k + downsampledWidth < dlim) {
                    dlim = k + downsampledWidth;
                }
                int sx2 = currentFrame.iw * i * sampleSize;
                if (isNotDownsampling2) {
                    int dx3 = sx2;
                    int currentColorIndex = dx2;
                    while (currentColorIndex < dlim) {
                        int dx4 = currentColorIndex;
                        int currentColorIndex2 = mainPixels[dx3] & 255;
                        int averageColor = act[currentColorIndex2];
                        if (averageColor != 0) {
                            dest[dx4] = averageColor;
                        } else if (isFirstFrame && isFirstFrameTransparent2 == null) {
                            isFirstFrameTransparent2 = true;
                        }
                        dx3 += sampleSize;
                        currentColorIndex = dx4 + 1;
                    }
                    sx = downsampledIY;
                    dx = downsampledIW;
                } else {
                    int sx3 = sx2 + ((dlim - dx2) * sampleSize);
                    dx = downsampledIW;
                    int downsampledIW2 = dx2;
                    sx = downsampledIY;
                    int downsampledIY2 = sx2;
                    while (downsampledIW2 < dlim) {
                        int dlim2 = dlim;
                        int averageColor2 = averageColorsNear(downsampledIY2, sx3, currentFrame.iw);
                        if (averageColor2 != 0) {
                            dest[downsampledIW2] = averageColor2;
                        } else if (isFirstFrame && isFirstFrameTransparent2 == null) {
                            isFirstFrameTransparent2 = true;
                        }
                        downsampledIY2 += sampleSize;
                        downsampledIW2++;
                        dlim = dlim2;
                    }
                }
            } else {
                sx = downsampledIY;
                dx = downsampledIW;
            }
            line = i + 1;
            downsampledIH = line4;
            downsampledIY = sx;
            downsampledIW = dx;
        }
        if (this.isFirstFrameTransparent == null) {
            this.isFirstFrameTransparent = Boolean.valueOf(isFirstFrameTransparent2 == null ? false : isFirstFrameTransparent2.booleanValue());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x006a, code lost:
    
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int averageColorsNear(int r10, int r11, int r12) {
        /*
            r9 = this;
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = r10
        L6:
            int r6 = r9.sampleSize
            int r6 = r6 + r10
            if (r5 >= r6) goto L35
            byte[] r6 = r9.mainPixels
            int r6 = r6.length
            if (r5 >= r6) goto L35
            if (r5 >= r11) goto L35
            byte[] r6 = r9.mainPixels
            r6 = r6[r5]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int[] r7 = r9.act
            r7 = r7[r6]
            if (r7 == 0) goto L32
            int r8 = r7 >> 24
            r8 = r8 & 255(0xff, float:3.57E-43)
            int r0 = r0 + r8
            int r8 = r7 >> 16
            r8 = r8 & 255(0xff, float:3.57E-43)
            int r1 = r1 + r8
            int r8 = r7 >> 8
            r8 = r8 & 255(0xff, float:3.57E-43)
            int r2 = r2 + r8
            r8 = r7 & 255(0xff, float:3.57E-43)
            int r3 = r3 + r8
            int r4 = r4 + 1
        L32:
            int r5 = r5 + 1
            goto L6
        L35:
            int r5 = r10 + r12
        L37:
            int r6 = r10 + r12
            int r7 = r9.sampleSize
            int r6 = r6 + r7
            if (r5 >= r6) goto L68
            byte[] r6 = r9.mainPixels
            int r6 = r6.length
            if (r5 >= r6) goto L68
            if (r5 >= r11) goto L68
            byte[] r6 = r9.mainPixels
            r6 = r6[r5]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int[] r7 = r9.act
            r7 = r7[r6]
            if (r7 == 0) goto L65
            int r8 = r7 >> 24
            r8 = r8 & 255(0xff, float:3.57E-43)
            int r0 = r0 + r8
            int r8 = r7 >> 16
            r8 = r8 & 255(0xff, float:3.57E-43)
            int r1 = r1 + r8
            int r8 = r7 >> 8
            r8 = r8 & 255(0xff, float:3.57E-43)
            int r2 = r2 + r8
            r8 = r7 & 255(0xff, float:3.57E-43)
            int r3 = r3 + r8
            int r4 = r4 + 1
        L65:
            int r5 = r5 + 1
            goto L37
        L68:
            if (r4 != 0) goto L6c
            r5 = 0
            return r5
        L6c:
            int r5 = r0 / r4
            int r5 = r5 << 24
            int r6 = r1 / r4
            int r6 = r6 << 16
            r5 = r5 | r6
            int r6 = r2 / r4
            int r6 = r6 << 8
            r5 = r5 | r6
            int r6 = r3 / r4
            r5 = r5 | r6
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.averageColorsNear(int, int, int):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6, types: [short] */
    /* JADX WARN: Type inference failed for: r9v7 */
    private void decodeBitmapData(GifFrame gifFrame) {
        int i;
        int i2;
        int i3;
        int i4;
        short s;
        StandardGifDecoder standardGifDecoder = this;
        if (gifFrame != null) {
            standardGifDecoder.rawData.position(gifFrame.bufferFrameStart);
        }
        if (gifFrame == null) {
            i = standardGifDecoder.header.width;
            i2 = standardGifDecoder.header.height;
        } else {
            i = gifFrame.iw;
            i2 = gifFrame.ih;
        }
        int i5 = i * i2;
        if (standardGifDecoder.mainPixels == null || standardGifDecoder.mainPixels.length < i5) {
            standardGifDecoder.mainPixels = standardGifDecoder.bitmapProvider.obtainByteArray(i5);
        }
        byte[] bArr = standardGifDecoder.mainPixels;
        if (standardGifDecoder.prefix == null) {
            standardGifDecoder.prefix = new short[4096];
        }
        short[] sArr = standardGifDecoder.prefix;
        if (standardGifDecoder.suffix == null) {
            standardGifDecoder.suffix = new byte[4096];
        }
        byte[] bArr2 = standardGifDecoder.suffix;
        if (standardGifDecoder.pixelStack == null) {
            standardGifDecoder.pixelStack = new byte[FragmentTransaction.TRANSIT_FRAGMENT_OPEN];
        }
        byte[] bArr3 = standardGifDecoder.pixelStack;
        int readByte = standardGifDecoder.readByte();
        int i6 = 1 << readByte;
        int i7 = i6 + 1;
        int i8 = i6 + 2;
        int i9 = -1;
        int i10 = readByte + 1;
        int i11 = (1 << i10) - 1;
        for (int i12 = 0; i12 < i6; i12++) {
            sArr[i12] = 0;
            bArr2[i12] = (byte) i12;
        }
        byte[] bArr4 = standardGifDecoder.block;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        while (true) {
            if (i20 >= i5) {
                break;
            }
            if (i16 != 0) {
                i3 = i20;
            } else {
                i16 = standardGifDecoder.readBlock();
                if (i16 <= 0) {
                    standardGifDecoder.status = 3;
                    break;
                } else {
                    i3 = i20;
                    i13 = 0;
                }
            }
            i18 += (bArr4[i13] & 255) << i17;
            i13++;
            i16--;
            int i21 = i15;
            int i22 = i17 + 8;
            while (true) {
                if (i22 < i10) {
                    i15 = i21;
                    i17 = i22;
                    i20 = i3;
                    i19 = i19;
                    standardGifDecoder = this;
                    break;
                }
                int i23 = i18 & i11;
                i18 >>= i10;
                i22 -= i10;
                if (i23 == i6) {
                    i10 = readByte + 1;
                    i11 = (1 << i10) - 1;
                    i8 = i6 + 2;
                    i9 = -1;
                } else {
                    if (i23 == i7) {
                        i17 = i22;
                        i20 = i3;
                        i15 = i21;
                        break;
                    }
                    if (i9 == -1) {
                        bArr[i19] = bArr2[i23 == true ? 1 : 0];
                        i19++;
                        i3++;
                        i9 = i23 == true ? 1 : 0;
                        i21 = i23 == true ? 1 : 0;
                        standardGifDecoder = this;
                    } else {
                        if (i23 >= i8) {
                            int i24 = i21;
                            i4 = i19;
                            bArr3[i14] = (byte) i24;
                            i14++;
                            s = i9;
                        } else {
                            i4 = i19;
                            s = i23;
                        }
                        while (s >= i6) {
                            bArr3[i14] = bArr2[s];
                            i14++;
                            s = sArr[s];
                        }
                        int i25 = bArr2[s] & 255;
                        bArr[i4] = (byte) i25;
                        int i26 = i4 + 1;
                        i3++;
                        while (i14 > 0) {
                            i14--;
                            bArr[i26] = bArr3[i14];
                            i26++;
                            i3++;
                        }
                        int i27 = i26;
                        if (i8 < 4096) {
                            sArr[i8] = (short) i9;
                            bArr2[i8] = (byte) i25;
                            i8++;
                            if ((i8 & i11) == 0 && i8 < 4096) {
                                i10++;
                                i11 += i8;
                            }
                        }
                        i9 = i23 == true ? 1 : 0;
                        i19 = i27;
                        i21 = i25;
                        standardGifDecoder = this;
                    }
                }
            }
        }
        Arrays.fill(bArr, i19, i5, (byte) 0);
    }

    private int readByte() {
        return this.rawData.get() & 255;
    }

    private int readBlock() {
        int blockSize = readByte();
        if (blockSize <= 0) {
            return blockSize;
        }
        this.rawData.get(this.block, 0, Math.min(blockSize, this.rawData.remaining()));
        return blockSize;
    }

    private Bitmap getNextBitmap() {
        Bitmap.Config config = (this.isFirstFrameTransparent == null || this.isFirstFrameTransparent.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.bitmapConfig;
        Bitmap result = this.bitmapProvider.obtain(this.downsampledWidth, this.downsampledHeight, config);
        result.setHasAlpha(true);
        return result;
    }
}
