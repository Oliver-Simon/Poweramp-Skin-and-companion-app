package io.ktor.util;

import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.InputArraysKt;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: Base64.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\f\u001a\n\u0010\n\u001a\u00020\r*\u00020\u0001\u001a\n\u0010\u000e\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u000f\u001a\u00020\u0001*\u00020\f\u001a\n\u0010\u000f\u001a\u00020\u0001*\u00020\r\u001a\n\u0010\u000f\u001a\u00020\u0001*\u00020\u0001\u001a\r\u0010\u0010\u001a\u00020\u0005*\u00020\u0005H\u0080\b\u001a\r\u0010\u0011\u001a\u00020\t*\u00020\u0007H\u0080\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"BASE64_ALPHABET", "", "BASE64_INVERSE_ALPHABET", "", "BASE64_MASK", "", "BASE64_MASK_INT", "", "BASE64_PAD", "", "decodeBase64Bytes", "Lio/ktor/utils/io/core/Input;", "Lio/ktor/utils/io/core/ByteReadPacket;", "", "decodeBase64String", "encodeBase64", "fromBase64", "toBase64", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class Base64Kt {
    private static final String BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final int[] BASE64_INVERSE_ALPHABET;
    private static final byte BASE64_MASK = 63;
    private static final int BASE64_MASK_INT = 63;
    private static final char BASE64_PAD = '=';

    static {
        int[] iArr = new int[256];
        for (int i = 0; i < 256; i++) {
            iArr[i] = StringsKt.indexOf$default((CharSequence) BASE64_ALPHABET, (char) i, 0, false, 6, (Object) null);
        }
        BASE64_INVERSE_ALPHABET = iArr;
    }

    public static final String encodeBase64(String $this$encodeBase64) {
        Intrinsics.checkNotNullParameter($this$encodeBase64, "<this>");
        BytePacketBuilder builder$iv = new BytePacketBuilder(null, 1, null);
        try {
            io.ktor.utils.io.core.StringsKt.writeText$default(builder$iv, $this$encodeBase64, 0, 0, (Charset) null, 14, (Object) null);
            return encodeBase64(builder$iv.build());
        } catch (Throwable t$iv) {
            builder$iv.release();
            throw t$iv;
        }
    }

    public static final String encodeBase64(byte[] $this$encodeBase64) {
        int writeOffset;
        Intrinsics.checkNotNullParameter($this$encodeBase64, "<this>");
        int position = 0;
        int writeOffset2 = 0;
        char[] charArray = new char[(($this$encodeBase64.length * 8) / 6) + 3];
        while (position + 3 <= $this$encodeBase64.length) {
            int first = $this$encodeBase64[position];
            int second = $this$encodeBase64[position + 1];
            int third = $this$encodeBase64[position + 2];
            position += 3;
            int chunk = ((first & 255) << 16) | ((second & 255) << 8) | (third & 255);
            int index = 3;
            while (-1 < index) {
                charArray[writeOffset2] = BASE64_ALPHABET.charAt((chunk >> (index * 6)) & 63);
                index--;
                writeOffset2++;
            }
        }
        int first2 = $this$encodeBase64.length;
        int remaining = first2 - position;
        if (remaining == 0) {
            return StringsKt.concatToString(charArray, 0, writeOffset2);
        }
        int chunk2 = remaining == 1 ? (($this$encodeBase64[position] & 255) << 16) | 0 | 0 : (($this$encodeBase64[position] & 255) << 16) | (($this$encodeBase64[position + 1] & 255) << 8) | 0;
        int padSize = ((3 - remaining) * 8) / 6;
        int index2 = 3;
        if (padSize <= 3) {
            while (true) {
                writeOffset = writeOffset2 + 1;
                charArray[writeOffset2] = BASE64_ALPHABET.charAt((chunk2 >> (index2 * 6)) & 63);
                if (index2 == padSize) {
                    break;
                }
                index2--;
                writeOffset2 = writeOffset;
            }
            writeOffset2 = writeOffset;
        }
        int i = 0;
        while (i < padSize) {
            charArray[writeOffset2] = BASE64_PAD;
            i++;
            writeOffset2++;
        }
        return StringsKt.concatToString(charArray, 0, writeOffset2);
    }

    public static final String encodeBase64(ByteReadPacket $this$encodeBase64) {
        Intrinsics.checkNotNullParameter($this$encodeBase64, "<this>");
        return encodeBase64(io.ktor.utils.io.core.StringsKt.readBytes$default($this$encodeBase64, 0, 1, null));
    }

    public static final String decodeBase64String(String $this$decodeBase64String) {
        Intrinsics.checkNotNullParameter($this$decodeBase64String, "<this>");
        byte[] bytes$iv = decodeBase64Bytes($this$decodeBase64String);
        Charset charset$iv = Charsets.UTF_8;
        int length$iv = bytes$iv.length;
        return new String(bytes$iv, 0, length$iv, charset$iv);
    }

    public static final byte[] decodeBase64Bytes(String $this$decodeBase64Bytes) {
        String str;
        Intrinsics.checkNotNullParameter($this$decodeBase64Bytes, "<this>");
        BytePacketBuilder builder$iv = new BytePacketBuilder(null, 1, null);
        try {
            BytePacketBuilder bytePacketBuilder = builder$iv;
            int index$iv = StringsKt.getLastIndex($this$decodeBase64Bytes);
            while (true) {
                if (-1 < index$iv) {
                    char it = $this$decodeBase64Bytes.charAt(index$iv);
                    char it2 = it == '=' ? (char) 1 : (char) 0;
                    if (it2 != 0) {
                        index$iv--;
                    } else {
                        str = $this$decodeBase64Bytes.substring(0, index$iv + 1);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                        break;
                    }
                } else {
                    str = "";
                    break;
                }
            }
            String $this$dropLastWhile$iv = str;
            io.ktor.utils.io.core.StringsKt.writeText$default(bytePacketBuilder, $this$dropLastWhile$iv, 0, 0, (Charset) null, 14, (Object) null);
            return io.ktor.utils.io.core.StringsKt.readBytes(decodeBase64Bytes(builder$iv.build()));
        } catch (Throwable t$iv) {
            builder$iv.release();
            throw t$iv;
        }
    }

    public static final Input decodeBase64Bytes(ByteReadPacket $this$decodeBase64Bytes) {
        ByteReadPacket byteReadPacket = $this$decodeBase64Bytes;
        Intrinsics.checkNotNullParameter(byteReadPacket, "<this>");
        int $i$f$buildPacket = 0;
        BytePacketBuilder builder$iv = new BytePacketBuilder(null, 1, null);
        try {
            byte[] data = new byte[4];
            while (byteReadPacket.getRemaining() > 0) {
                int read = InputArraysKt.readAvailable$default((Input) byteReadPacket, data, 0, 0, 6, (Object) null);
                int index$iv = 0;
                int accumulator$iv = 0;
                int length = data.length;
                int i = 0;
                while (i < length) {
                    int element$iv = data[i];
                    int index$iv2 = index$iv + 1;
                    int result = accumulator$iv;
                    int $this$fromBase64$iv = $i$f$buildPacket;
                    try {
                        accumulator$iv = result | (((byte) (((byte) BASE64_INVERSE_ALPHABET[element$iv & 255]) & 63)) << ((3 - index$iv) * 6));
                        i++;
                        index$iv = index$iv2;
                        $i$f$buildPacket = $this$fromBase64$iv;
                    } catch (Throwable th) {
                        t$iv = th;
                        builder$iv.release();
                        throw t$iv;
                    }
                }
                int $i$f$buildPacket2 = $i$f$buildPacket;
                int index = data.length - 2;
                int length2 = data.length - read;
                if (length2 <= index) {
                    while (true) {
                        int origin = (accumulator$iv >> (index * 8)) & 255;
                        builder$iv.writeByte((byte) origin);
                        if (index != length2) {
                            index--;
                        }
                    }
                }
                byteReadPacket = $this$decodeBase64Bytes;
                $i$f$buildPacket = $i$f$buildPacket2;
            }
            return builder$iv.build();
        } catch (Throwable th2) {
            t$iv = th2;
        }
    }

    public static final char toBase64(int $this$toBase64) {
        return BASE64_ALPHABET.charAt($this$toBase64);
    }

    public static final byte fromBase64(byte $this$fromBase64) {
        return (byte) (((byte) BASE64_INVERSE_ALPHABET[$this$fromBase64 & 255]) & 63);
    }
}
