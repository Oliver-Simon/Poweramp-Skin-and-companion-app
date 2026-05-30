package com.google.common.hash;

import com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
/* loaded from: classes.dex */
final class FarmHashFingerprint64 extends AbstractNonStreamingHashFunction {
    static final HashFunction FARMHASH_FINGERPRINT_64 = new FarmHashFingerprint64();
    private static final long K0 = -4348849565147123417L;
    private static final long K1 = -5435081209227447693L;
    private static final long K2 = -7286425919675154353L;

    FarmHashFingerprint64() {
    }

    @Override // com.google.common.hash.AbstractNonStreamingHashFunction, com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] input, int off, int len) {
        Preconditions.checkPositionIndexes(off, off + len, input.length);
        return HashCode.fromLong(fingerprint(input, off, len));
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    public String toString() {
        return "Hashing.farmHashFingerprint64()";
    }

    static long fingerprint(byte[] bytes, int offset, int length) {
        if (length <= 32) {
            if (length <= 16) {
                return hashLength0to16(bytes, offset, length);
            }
            return hashLength17to32(bytes, offset, length);
        }
        if (length <= 64) {
            return hashLength33To64(bytes, offset, length);
        }
        return hashLength65Plus(bytes, offset, length);
    }

    private static long shiftMix(long val) {
        return (val >>> 47) ^ val;
    }

    private static long hashLength16(long u, long v, long mul) {
        long a = (u ^ v) * mul;
        long b = (v ^ (a ^ (a >>> 47))) * mul;
        return (b ^ (b >>> 47)) * mul;
    }

    private static void weakHashLength32WithSeeds(byte[] bytes, int offset, long seedA, long seedB, long[] output) {
        long part1 = LittleEndianByteArray.load64(bytes, offset);
        long part2 = LittleEndianByteArray.load64(bytes, offset + 8);
        long part3 = LittleEndianByteArray.load64(bytes, offset + 16);
        long part4 = LittleEndianByteArray.load64(bytes, offset + 24);
        long seedA2 = seedA + part1;
        long seedB2 = Long.rotateRight(seedB + seedA2 + part4, 21);
        long seedA3 = seedA2 + part2 + part3;
        long seedB3 = seedB2 + Long.rotateRight(seedA3, 44);
        output[0] = seedA3 + part4;
        output[1] = seedB3 + seedA2;
    }

    private static long hashLength0to16(byte[] bytes, int offset, int length) {
        if (length >= 8) {
            long mul = (length * 2) + K2;
            long a = LittleEndianByteArray.load64(bytes, offset) + K2;
            long b = LittleEndianByteArray.load64(bytes, (offset + length) - 8);
            long c = (Long.rotateRight(b, 37) * mul) + a;
            long d = (Long.rotateRight(a, 25) + b) * mul;
            return hashLength16(c, d, mul);
        }
        if (length >= 4) {
            long a2 = LittleEndianByteArray.load32(bytes, offset) & 4294967295L;
            return hashLength16(length + (a2 << 3), LittleEndianByteArray.load32(bytes, (offset + length) - 4) & 4294967295L, (length * 2) + K2);
        }
        if (length <= 0) {
            return K2;
        }
        byte a3 = bytes[offset];
        byte b2 = bytes[(length >> 1) + offset];
        byte c2 = bytes[(length - 1) + offset];
        int y = (a3 & 255) + ((b2 & 255) << 8);
        int z = ((c2 & 255) << 2) + length;
        return shiftMix((y * K2) ^ (z * K0)) * K2;
    }

    private static long hashLength17to32(byte[] bytes, int offset, int length) {
        long mul = (length * 2) + K2;
        long a = LittleEndianByteArray.load64(bytes, offset) * K1;
        long b = LittleEndianByteArray.load64(bytes, offset + 8);
        long c = LittleEndianByteArray.load64(bytes, (offset + length) - 8) * mul;
        long d = LittleEndianByteArray.load64(bytes, (offset + length) - 16) * K2;
        return hashLength16(Long.rotateRight(a + b, 43) + Long.rotateRight(c, 30) + d, Long.rotateRight(K2 + b, 18) + a + c, mul);
    }

    private static long hashLength33To64(byte[] bytes, int offset, int length) {
        long mul = (length * 2) + K2;
        long a = LittleEndianByteArray.load64(bytes, offset) * K2;
        long b = LittleEndianByteArray.load64(bytes, offset + 8);
        long c = LittleEndianByteArray.load64(bytes, (offset + length) - 8) * mul;
        long d = LittleEndianByteArray.load64(bytes, (offset + length) - 16) * K2;
        long y = Long.rotateRight(a + b, 43) + Long.rotateRight(c, 30) + d;
        long z = hashLength16(y, a + Long.rotateRight(K2 + b, 18) + c, mul);
        long e = LittleEndianByteArray.load64(bytes, offset + 16) * mul;
        long f = LittleEndianByteArray.load64(bytes, offset + 24);
        long g = (y + LittleEndianByteArray.load64(bytes, (offset + length) - 32)) * mul;
        long h = (z + LittleEndianByteArray.load64(bytes, (offset + length) - 24)) * mul;
        return hashLength16(Long.rotateRight(e + f, 43) + Long.rotateRight(g, 30) + h, e + Long.rotateRight(f + a, 18) + g, mul);
    }

    private static long hashLength65Plus(byte[] bytes, int offset, int length) {
        byte[] bArr = bytes;
        long x = 2480279821605975764L;
        long z = shiftMix((2480279821605975764L * K2) + 113) * K2;
        long[] w = new long[2];
        long[] w2 = new long[2];
        long y = (K2 * 81) + LittleEndianByteArray.load64(bytes, offset);
        int end = offset + (((length - 1) / 64) * 64);
        int last64offset = (((length - 1) & 63) + end) - 63;
        int offset2 = offset;
        while (true) {
            int offset3 = offset2;
            long x2 = Long.rotateRight(y + x + w[0] + LittleEndianByteArray.load64(bArr, offset2 + 8), 37) * K1;
            long y2 = Long.rotateRight(x + w[1] + LittleEndianByteArray.load64(bArr, offset3 + 48), 42) * K1;
            long y3 = w2[1];
            long x3 = x2 ^ y3;
            long x4 = w[0];
            long y4 = y2 + x4 + LittleEndianByteArray.load64(bArr, offset3 + 40);
            long z2 = Long.rotateRight(w2[0] + z, 33) * K1;
            weakHashLength32WithSeeds(bArr, offset3, w[1] * K1, x3 + w2[0], w);
            long[] v = w;
            weakHashLength32WithSeeds(bArr, offset3 + 32, w2[1] + z2, LittleEndianByteArray.load64(bArr, offset3 + 16) + y4, w2);
            z = x3;
            offset2 = offset3 + 64;
            if (offset2 == end) {
                long mul = ((255 & z) << 1) + K1;
                w2[0] = w2[0] + ((length - 1) & 63);
                v[0] = v[0] + w2[0];
                w2[0] = w2[0] + v[0];
                long x5 = Long.rotateRight(z2 + y4 + v[0] + LittleEndianByteArray.load64(bArr, last64offset + 8), 37) * mul;
                long x6 = v[1];
                long y5 = Long.rotateRight(x6 + y4 + LittleEndianByteArray.load64(bArr, last64offset + 48), 42) * mul;
                long x7 = x5 ^ (w2[1] * 9);
                long x8 = v[0];
                long y6 = y5 + (x8 * 9) + LittleEndianByteArray.load64(bArr, last64offset + 40);
                long z3 = Long.rotateRight(w2[0] + z, 33) * mul;
                weakHashLength32WithSeeds(bArr, last64offset, v[1] * mul, x7 + w2[0], v);
                weakHashLength32WithSeeds(bArr, last64offset + 32, w2[1] + z3, LittleEndianByteArray.load64(bArr, last64offset + 16) + y6, w2);
                return hashLength16(hashLength16(v[0], w2[0], mul) + (shiftMix(y6) * K0) + x7, hashLength16(v[1], w2[1], mul) + z3, mul);
            }
            y = z2;
            x = y4;
            bArr = bytes;
            w = v;
        }
    }
}
