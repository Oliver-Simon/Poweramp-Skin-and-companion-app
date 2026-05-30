package io.ktor.utils.io.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.bits.PrimitiveArraysJvmKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BufferPrimitives.kt */
@Metadata(d1 = {"\u0000®\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0013\n\u0002\u0010\u0014\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b!\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\bø\u0001\u0000\u001a\u001c\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\n\u001a\u00020\r2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000e2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000f2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\n\u001a\u00020\u00102\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\n\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a3\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\n\u001a\u00020\u00122\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a3\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\n\u001a\u00020\u00152\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0016\u0010\u0017\u001a3\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\n\u001a\u00020\u00182\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0019\u0010\u001a\u001a3\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\n\u001a\u00020\u001b2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u001c\u0010\u001d\u001a)\u0010\u0006\u001a\u00020\u0007*\u00020\u001e2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007H\u0086\b\u001a\n\u0010\u001f\u001a\u00020 *\u00020\u0002\u001a\r\u0010\u001f\u001a\u00020 *\u00020\u001eH\u0086\b\u001as\u0010!\u001a\u0002H\"\"\u0004\b\u0000\u0010\"*\u00020\u00022\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020%26\u0010\u0003\u001a2\u0012\u0013\u0012\u00110'¢\u0006\f\b(\u0012\b\b$\u0012\u0004\b\b()\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b(\u0012\b\b$\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\"0&H\u0081\bø\u0001\u0000ø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001¢\u0006\u0002\u0010*\u001a\n\u0010+\u001a\u00020,*\u00020\u0002\u001a\r\u0010+\u001a\u00020,*\u00020\u001eH\u0086\b\u001a\u001c\u0010-\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\r2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000e2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000f2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u00102\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a3\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u00122\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b.\u0010/\u001a3\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u00152\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b0\u00101\u001a3\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u00182\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b2\u00103\u001a3\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u001b2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b4\u00105\u001a)\u0010-\u001a\u00020\u0001*\u00020\u001e2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007H\u0086\b\u001a\n\u00106\u001a\u00020\u0007*\u00020\u0002\u001a\r\u00106\u001a\u00020\u0007*\u00020\u001eH\u0086\b\u001a\n\u00107\u001a\u000208*\u00020\u0002\u001a\r\u00107\u001a\u000208*\u00020\u001eH\u0086\b\u001a\n\u00109\u001a\u00020:*\u00020\u0002\u001a\r\u00109\u001a\u00020:*\u00020\u001eH\u0086\b\u001a\u0012\u0010;\u001a\u00020<*\u00020\u0002ø\u0001\u0002¢\u0006\u0002\u0010=\u001a\u0015\u0010;\u001a\u00020<*\u00020\u001eH\u0086\bø\u0001\u0002¢\u0006\u0002\u0010>\u001a\u0012\u0010?\u001a\u00020@*\u00020\u0002ø\u0001\u0002¢\u0006\u0002\u0010A\u001a\u0015\u0010?\u001a\u00020@*\u00020\u001eH\u0086\bø\u0001\u0002¢\u0006\u0002\u0010B\u001a\u0012\u0010C\u001a\u00020D*\u00020\u0002ø\u0001\u0002¢\u0006\u0002\u0010E\u001a\u0015\u0010C\u001a\u00020D*\u00020\u001eH\u0086\bø\u0001\u0002¢\u0006\u0002\u0010F\u001a\u0012\u0010G\u001a\u00020H*\u00020\u0002ø\u0001\u0002¢\u0006\u0002\u0010I\u001a\u0015\u0010G\u001a\u00020H*\u00020\u001eH\u0086\bø\u0001\u0002¢\u0006\u0002\u0010J\u001a\u0012\u0010K\u001a\u00020\u0001*\u00020\u00022\u0006\u0010L\u001a\u00020 \u001a\u0015\u0010K\u001a\u00020\u0001*\u00020\u001e2\u0006\u0010L\u001a\u00020 H\u0086\b\u001ah\u0010M\u001a\u00020\u0001*\u00020\u00022\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020%26\u0010\u0003\u001a2\u0012\u0013\u0012\u00110'¢\u0006\f\b(\u0012\b\b$\u0012\u0004\b\b()\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b(\u0012\b\b$\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00010&H\u0081\bø\u0001\u0000ø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001\u001a\u0012\u0010N\u001a\u00020\u0001*\u00020\u00022\u0006\u0010L\u001a\u00020,\u001a\u0015\u0010N\u001a\u00020\u0001*\u00020\u001e2\u0006\u0010L\u001a\u00020,H\u0086\b\u001a\u0012\u0010O\u001a\u00020\u0001*\u00020\u00022\u0006\u0010P\u001a\u00020\u0002\u001a\u001a\u0010O\u001a\u00020\u0001*\u00020\u00022\u0006\u0010P\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0007\u001a&\u0010O\u001a\u00020\u0001*\u00020\u00022\u0006\u0010Q\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010O\u001a\u00020\u0001*\u00020\u00022\u0006\u0010Q\u001a\u00020\r2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010O\u001a\u00020\u0001*\u00020\u00022\u0006\u0010Q\u001a\u00020\u000e2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010O\u001a\u00020\u0001*\u00020\u00022\u0006\u0010Q\u001a\u00020\u000f2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010O\u001a\u00020\u0001*\u00020\u00022\u0006\u0010Q\u001a\u00020\u00102\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a&\u0010O\u001a\u00020\u0001*\u00020\u00022\u0006\u0010Q\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a3\u0010O\u001a\u00020\u0001*\u00020\u00022\u0006\u0010Q\u001a\u00020\u00122\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bR\u0010/\u001a3\u0010O\u001a\u00020\u0001*\u00020\u00022\u0006\u0010Q\u001a\u00020\u00152\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bS\u00101\u001a3\u0010O\u001a\u00020\u0001*\u00020\u00022\u0006\u0010Q\u001a\u00020\u00182\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bT\u00103\u001a3\u0010O\u001a\u00020\u0001*\u00020\u00022\u0006\u0010Q\u001a\u00020\u001b2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bU\u00105\u001a)\u0010O\u001a\u00020\u0001*\u00020\u001e2\u0006\u0010Q\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007H\u0086\b\u001a\u0012\u0010V\u001a\u00020\u0001*\u00020\u00022\u0006\u0010L\u001a\u00020\u0007\u001a\u0015\u0010V\u001a\u00020\u0001*\u00020\u001e2\u0006\u0010L\u001a\u00020\u0007H\u0086\b\u001a\u0012\u0010W\u001a\u00020\u0001*\u00020\u00022\u0006\u0010L\u001a\u000208\u001a\u0015\u0010W\u001a\u00020\u0001*\u00020\u001e2\u0006\u0010L\u001a\u000208H\u0086\b\u001a\u0012\u0010X\u001a\u00020\u0001*\u00020\u00022\u0006\u0010L\u001a\u00020:\u001a\u0015\u0010X\u001a\u00020\u0001*\u00020\u001e2\u0006\u0010L\u001a\u00020:H\u0086\b\u001a\u001f\u0010Y\u001a\u00020\u0001*\u00020\u00022\u0006\u0010L\u001a\u00020<ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bZ\u0010[\u001a\u001f\u0010Y\u001a\u00020\u0001*\u00020\u001e2\u0006\u0010L\u001a\u00020<ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bZ\u0010\\\u001a\u001f\u0010]\u001a\u00020\u0001*\u00020\u00022\u0006\u0010L\u001a\u00020@ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b^\u0010_\u001a\"\u0010]\u001a\u00020\u0001*\u00020\u001e2\u0006\u0010L\u001a\u00020@H\u0086\bø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b^\u0010`\u001a\u001f\u0010a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010L\u001a\u00020Dø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bb\u0010c\u001a\"\u0010a\u001a\u00020\u0001*\u00020\u001e2\u0006\u0010L\u001a\u00020DH\u0086\bø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bb\u0010d\u001a\u001f\u0010e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010L\u001a\u00020Hø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bf\u0010g\u001a\"\u0010e\u001a\u00020\u0001*\u00020\u001e2\u0006\u0010L\u001a\u00020HH\u0086\bø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\bf\u0010h\u0082\u0002\u0012\n\u0005\b\u009920\u0001\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006i"}, d2 = {"forEach", "", "Lio/ktor/utils/io/core/Buffer;", "block", "Lkotlin/Function1;", "", "readAvailable", "", "dst", "length", "destination", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "", "", "", "", "Lkotlin/UByteArray;", "readAvailable-o1GoV1E", "(Lio/ktor/utils/io/core/Buffer;[BII)I", "Lkotlin/UIntArray;", "readAvailable-o2ZM2JE", "(Lio/ktor/utils/io/core/Buffer;[III)I", "Lkotlin/ULongArray;", "readAvailable-pqYNikA", "(Lio/ktor/utils/io/core/Buffer;[JII)I", "Lkotlin/UShortArray;", "readAvailable-Wt3Bwxc", "(Lio/ktor/utils/io/core/Buffer;[SII)I", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "readDouble", "", "readExact", "R", ContentDisposition.Parameters.Size, "name", "", "Lkotlin/Function2;", "Lio/ktor/utils/io/bits/Memory;", "Lkotlin/ParameterName;", "memory", "(Lio/ktor/utils/io/core/Buffer;ILjava/lang/String;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "readFloat", "", "readFully", "readFully-o1GoV1E", "(Lio/ktor/utils/io/core/Buffer;[BII)V", "readFully-o2ZM2JE", "(Lio/ktor/utils/io/core/Buffer;[III)V", "readFully-pqYNikA", "(Lio/ktor/utils/io/core/Buffer;[JII)V", "readFully-Wt3Bwxc", "(Lio/ktor/utils/io/core/Buffer;[SII)V", "readInt", "readLong", "", "readShort", "", "readUByte", "Lkotlin/UByte;", "(Lio/ktor/utils/io/core/Buffer;)B", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)B", "readUInt", "Lkotlin/UInt;", "(Lio/ktor/utils/io/core/Buffer;)I", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)I", "readULong", "Lkotlin/ULong;", "(Lio/ktor/utils/io/core/Buffer;)J", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)J", "readUShort", "Lkotlin/UShort;", "(Lio/ktor/utils/io/core/Buffer;)S", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)S", "writeDouble", "value", "writeExact", "writeFloat", "writeFully", "src", "source", "writeFully-o1GoV1E", "writeFully-o2ZM2JE", "writeFully-pqYNikA", "writeFully-Wt3Bwxc", "writeInt", "writeLong", "writeShort", "writeUByte", "writeUByte-EK-6454", "(Lio/ktor/utils/io/core/Buffer;B)V", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;B)V", "writeUInt", "writeUInt-Qn1smSk", "(Lio/ktor/utils/io/core/Buffer;I)V", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;I)V", "writeULong", "writeULong-2TYgG_w", "(Lio/ktor/utils/io/core/Buffer;J)V", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;J)V", "writeUShort", "writeUShort-i8woANY", "(Lio/ktor/utils/io/core/Buffer;S)V", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;S)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class BufferPrimitivesKt {
    public static final void forEach(Buffer $this$forEach, Function1<? super Byte, Unit> block) {
        Intrinsics.checkNotNullParameter($this$forEach, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        ByteBuffer memory = $this$forEach.getMemory();
        int start = $this$forEach.getReadPosition();
        int endExclusive = $this$forEach.getWritePosition();
        for (int index = start; index < endExclusive; index++) {
            block.invoke(Byte.valueOf(memory.get(index)));
        }
        int rc$iv = endExclusive - start;
        $this$forEach.discardExact(rc$iv);
    }

    public static final byte readUByte(Buffer $this$readUByte) {
        Intrinsics.checkNotNullParameter($this$readUByte, "<this>");
        return UByte.m528constructorimpl($this$readUByte.readByte());
    }

    public static final byte readUByte(ChunkBuffer $this$readUByte) {
        Intrinsics.checkNotNullParameter($this$readUByte, "<this>");
        return readUByte((Buffer) $this$readUByte);
    }

    /* renamed from: writeUByte-EK-6454, reason: not valid java name */
    public static final void m405writeUByteEK6454(Buffer writeUByte, byte value) {
        Intrinsics.checkNotNullParameter(writeUByte, "$this$writeUByte");
        writeUByte.writeByte(value);
    }

    /* renamed from: writeUByte-EK-6454, reason: not valid java name */
    public static final void m406writeUByteEK6454(ChunkBuffer writeUByte, byte value) {
        Intrinsics.checkNotNullParameter(writeUByte, "$this$writeUByte");
        m405writeUByteEK6454((Buffer) writeUByte, value);
    }

    public static final short readShort(Buffer $this$readShort) {
        Intrinsics.checkNotNullParameter($this$readShort, "<this>");
        ByteBuffer memory$iv = $this$readShort.getMemory();
        int start$iv = $this$readShort.getReadPosition();
        int endExclusive$iv = $this$readShort.getWritePosition();
        if (endExclusive$iv - start$iv < 2) {
            throw new EOFException("Not enough bytes to read a short integer of size 2.");
        }
        Object value$iv = Short.valueOf(memory$iv.getShort(start$iv));
        $this$readShort.discardExact(2);
        return ((Number) value$iv).shortValue();
    }

    public static final short readShort(ChunkBuffer $this$readShort) {
        Intrinsics.checkNotNullParameter($this$readShort, "<this>");
        return readShort((Buffer) $this$readShort);
    }

    public static final short readUShort(Buffer $this$readUShort) {
        Intrinsics.checkNotNullParameter($this$readUShort, "<this>");
        ByteBuffer memory$iv = $this$readUShort.getMemory();
        int start$iv = $this$readUShort.getReadPosition();
        int endExclusive$iv = $this$readUShort.getWritePosition();
        if (endExclusive$iv - start$iv < 2) {
            throw new EOFException("Not enough bytes to read a short unsigned integer of size 2.");
        }
        UShort m785boximpl = UShort.m785boximpl(UShort.m791constructorimpl(memory$iv.getShort(start$iv)));
        $this$readUShort.discardExact(2);
        return m785boximpl.getData();
    }

    public static final short readUShort(ChunkBuffer $this$readUShort) {
        Intrinsics.checkNotNullParameter($this$readUShort, "<this>");
        return readUShort((Buffer) $this$readUShort);
    }

    public static final int readInt(Buffer $this$readInt) {
        Intrinsics.checkNotNullParameter($this$readInt, "<this>");
        ByteBuffer memory$iv = $this$readInt.getMemory();
        int start$iv = $this$readInt.getReadPosition();
        int endExclusive$iv = $this$readInt.getWritePosition();
        if (endExclusive$iv - start$iv < 4) {
            throw new EOFException("Not enough bytes to read a regular integer of size 4.");
        }
        Object value$iv = Integer.valueOf(memory$iv.getInt(start$iv));
        $this$readInt.discardExact(4);
        return ((Number) value$iv).intValue();
    }

    public static final int readInt(ChunkBuffer $this$readInt) {
        Intrinsics.checkNotNullParameter($this$readInt, "<this>");
        return readInt((Buffer) $this$readInt);
    }

    public static final int readUInt(Buffer $this$readUInt) {
        Intrinsics.checkNotNullParameter($this$readUInt, "<this>");
        ByteBuffer memory$iv = $this$readUInt.getMemory();
        int start$iv = $this$readUInt.getReadPosition();
        int endExclusive$iv = $this$readUInt.getWritePosition();
        if (endExclusive$iv - start$iv < 4) {
            throw new EOFException("Not enough bytes to read a regular unsigned integer of size 4.");
        }
        UInt m599boximpl = UInt.m599boximpl(UInt.m605constructorimpl(memory$iv.getInt(start$iv)));
        $this$readUInt.discardExact(4);
        int size$iv = m599boximpl.getData();
        return size$iv;
    }

    public static final int readUInt(ChunkBuffer $this$readUInt) {
        Intrinsics.checkNotNullParameter($this$readUInt, "<this>");
        return readUInt((Buffer) $this$readUInt);
    }

    public static final long readLong(Buffer $this$readLong) {
        Intrinsics.checkNotNullParameter($this$readLong, "<this>");
        ByteBuffer memory$iv = $this$readLong.getMemory();
        int start$iv = $this$readLong.getReadPosition();
        int endExclusive$iv = $this$readLong.getWritePosition();
        if (endExclusive$iv - start$iv < 8) {
            throw new EOFException("Not enough bytes to read a long integer of size 8.");
        }
        Object value$iv = Long.valueOf(memory$iv.getLong(start$iv));
        $this$readLong.discardExact(8);
        return ((Number) value$iv).longValue();
    }

    public static final long readLong(ChunkBuffer $this$readLong) {
        Intrinsics.checkNotNullParameter($this$readLong, "<this>");
        return readLong((Buffer) $this$readLong);
    }

    public static final long readULong(Buffer $this$readULong) {
        Intrinsics.checkNotNullParameter($this$readULong, "<this>");
        ByteBuffer memory$iv = $this$readULong.getMemory();
        int start$iv = $this$readULong.getReadPosition();
        int endExclusive$iv = $this$readULong.getWritePosition();
        if (endExclusive$iv - start$iv < 8) {
            throw new EOFException("Not enough bytes to read a long unsigned integer of size 8.");
        }
        ULong m678boximpl = ULong.m678boximpl(ULong.m684constructorimpl(memory$iv.getLong(start$iv)));
        $this$readULong.discardExact(8);
        return m678boximpl.getData();
    }

    public static final long readULong(ChunkBuffer $this$readULong) {
        Intrinsics.checkNotNullParameter($this$readULong, "<this>");
        return readULong((Buffer) $this$readULong);
    }

    public static final float readFloat(Buffer $this$readFloat) {
        Intrinsics.checkNotNullParameter($this$readFloat, "<this>");
        ByteBuffer memory$iv = $this$readFloat.getMemory();
        int start$iv = $this$readFloat.getReadPosition();
        int endExclusive$iv = $this$readFloat.getWritePosition();
        if (endExclusive$iv - start$iv < 4) {
            throw new EOFException("Not enough bytes to read a floating point number of size 4.");
        }
        Object value$iv = Float.valueOf(memory$iv.getFloat(start$iv));
        $this$readFloat.discardExact(4);
        return ((Number) value$iv).floatValue();
    }

    public static final float readFloat(ChunkBuffer $this$readFloat) {
        Intrinsics.checkNotNullParameter($this$readFloat, "<this>");
        return readFloat((Buffer) $this$readFloat);
    }

    public static final double readDouble(Buffer $this$readDouble) {
        Intrinsics.checkNotNullParameter($this$readDouble, "<this>");
        ByteBuffer memory$iv = $this$readDouble.getMemory();
        int start$iv = $this$readDouble.getReadPosition();
        int endExclusive$iv = $this$readDouble.getWritePosition();
        if (endExclusive$iv - start$iv < 8) {
            throw new EOFException("Not enough bytes to read a long floating point number of size 8.");
        }
        Object value$iv = Double.valueOf(memory$iv.getDouble(start$iv));
        $this$readDouble.discardExact(8);
        return ((Number) value$iv).doubleValue();
    }

    public static final double readDouble(ChunkBuffer $this$readDouble) {
        Intrinsics.checkNotNullParameter($this$readDouble, "<this>");
        return readDouble((Buffer) $this$readDouble);
    }

    public static final void writeShort(Buffer $this$writeShort, short value) {
        Intrinsics.checkNotNullParameter($this$writeShort, "<this>");
        ByteBuffer memory$iv = $this$writeShort.getMemory();
        int start$iv = $this$writeShort.getWritePosition();
        int endExclusive$iv = $this$writeShort.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv < 2) {
            throw new InsufficientSpaceException("short integer", 2, writeRemaining$iv);
        }
        memory$iv.putShort(start$iv, value);
        $this$writeShort.commitWritten(2);
    }

    public static final void writeShort(ChunkBuffer $this$writeShort, short value) {
        Intrinsics.checkNotNullParameter($this$writeShort, "<this>");
        writeShort((Buffer) $this$writeShort, value);
    }

    /* renamed from: writeUShort-i8woANY, reason: not valid java name */
    public static final void m411writeUShorti8woANY(Buffer writeUShort, short value) {
        Intrinsics.checkNotNullParameter(writeUShort, "$this$writeUShort");
        ByteBuffer memory$iv = writeUShort.getMemory();
        int start$iv = writeUShort.getWritePosition();
        int endExclusive$iv = writeUShort.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv < 2) {
            throw new InsufficientSpaceException("short unsigned integer", 2, writeRemaining$iv);
        }
        memory$iv.putShort(start$iv, value);
        writeUShort.commitWritten(2);
    }

    /* renamed from: writeUShort-i8woANY, reason: not valid java name */
    public static final void m412writeUShorti8woANY(ChunkBuffer writeUShort, short value) {
        Intrinsics.checkNotNullParameter(writeUShort, "$this$writeUShort");
        m411writeUShorti8woANY((Buffer) writeUShort, value);
    }

    public static final void writeInt(Buffer $this$writeInt, int value) {
        Intrinsics.checkNotNullParameter($this$writeInt, "<this>");
        ByteBuffer memory$iv = $this$writeInt.getMemory();
        int start$iv = $this$writeInt.getWritePosition();
        int endExclusive$iv = $this$writeInt.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv < 4) {
            throw new InsufficientSpaceException("regular integer", 4, writeRemaining$iv);
        }
        memory$iv.putInt(start$iv, value);
        $this$writeInt.commitWritten(4);
    }

    public static final void writeInt(ChunkBuffer $this$writeInt, int value) {
        Intrinsics.checkNotNullParameter($this$writeInt, "<this>");
        writeInt((Buffer) $this$writeInt, value);
    }

    /* renamed from: writeUInt-Qn1smSk, reason: not valid java name */
    public static final void m407writeUIntQn1smSk(Buffer writeUInt, int value) {
        Intrinsics.checkNotNullParameter(writeUInt, "$this$writeUInt");
        ByteBuffer memory$iv = writeUInt.getMemory();
        int start$iv = writeUInt.getWritePosition();
        int endExclusive$iv = writeUInt.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv < 4) {
            throw new InsufficientSpaceException("regular unsigned integer", 4, writeRemaining$iv);
        }
        memory$iv.putInt(start$iv, value);
        writeUInt.commitWritten(4);
    }

    /* renamed from: writeUInt-Qn1smSk, reason: not valid java name */
    public static final void m408writeUIntQn1smSk(ChunkBuffer writeUInt, int value) {
        Intrinsics.checkNotNullParameter(writeUInt, "$this$writeUInt");
        m407writeUIntQn1smSk((Buffer) writeUInt, value);
    }

    public static final void writeLong(Buffer $this$writeLong, long value) {
        Intrinsics.checkNotNullParameter($this$writeLong, "<this>");
        ByteBuffer memory$iv = $this$writeLong.getMemory();
        int start$iv = $this$writeLong.getWritePosition();
        int endExclusive$iv = $this$writeLong.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv >= 8) {
            memory$iv.putLong(start$iv, value);
            $this$writeLong.commitWritten(8);
            return;
        }
        throw new InsufficientSpaceException("long integer", 8, writeRemaining$iv);
    }

    public static final void writeLong(ChunkBuffer $this$writeLong, long value) {
        Intrinsics.checkNotNullParameter($this$writeLong, "<this>");
        writeLong((Buffer) $this$writeLong, value);
    }

    /* renamed from: writeULong-2TYgG_w, reason: not valid java name */
    public static final void m409writeULong2TYgG_w(Buffer writeULong, long value) {
        Intrinsics.checkNotNullParameter(writeULong, "$this$writeULong");
        ByteBuffer memory$iv = writeULong.getMemory();
        int start$iv = writeULong.getWritePosition();
        int endExclusive$iv = writeULong.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv < 8) {
            throw new InsufficientSpaceException("long unsigned integer", 8, writeRemaining$iv);
        }
        memory$iv.putLong(start$iv, value);
        writeULong.commitWritten(8);
    }

    /* renamed from: writeULong-2TYgG_w, reason: not valid java name */
    public static final void m410writeULong2TYgG_w(ChunkBuffer writeULong, long value) {
        Intrinsics.checkNotNullParameter(writeULong, "$this$writeULong");
        m409writeULong2TYgG_w((Buffer) writeULong, value);
    }

    public static final void writeFloat(Buffer $this$writeFloat, float value) {
        Intrinsics.checkNotNullParameter($this$writeFloat, "<this>");
        ByteBuffer memory$iv = $this$writeFloat.getMemory();
        int start$iv = $this$writeFloat.getWritePosition();
        int endExclusive$iv = $this$writeFloat.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv < 4) {
            throw new InsufficientSpaceException("floating point number", 4, writeRemaining$iv);
        }
        memory$iv.putFloat(start$iv, value);
        $this$writeFloat.commitWritten(4);
    }

    public static final void writeFloat(ChunkBuffer $this$writeFloat, float value) {
        Intrinsics.checkNotNullParameter($this$writeFloat, "<this>");
        writeFloat((Buffer) $this$writeFloat, value);
    }

    public static final void writeDouble(Buffer $this$writeDouble, double value) {
        Intrinsics.checkNotNullParameter($this$writeDouble, "<this>");
        ByteBuffer memory$iv = $this$writeDouble.getMemory();
        int start$iv = $this$writeDouble.getWritePosition();
        int endExclusive$iv = $this$writeDouble.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv >= 8) {
            memory$iv.putDouble(start$iv, value);
            $this$writeDouble.commitWritten(8);
            return;
        }
        throw new InsufficientSpaceException("long floating point number", 8, writeRemaining$iv);
    }

    public static final void writeDouble(ChunkBuffer $this$writeDouble, double value) {
        Intrinsics.checkNotNullParameter($this$writeDouble, "<this>");
        writeDouble((Buffer) $this$writeDouble, value);
    }

    public static /* synthetic */ void readFully$default(Buffer buffer, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        readFully(buffer, bArr, i, i2);
    }

    public static final void readFully(Buffer $this$readFully, byte[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ByteBuffer memory$iv = $this$readFully.getMemory();
        int start$iv = $this$readFully.getReadPosition();
        int endExclusive$iv = $this$readFully.getWritePosition();
        if (endExclusive$iv - start$iv < length) {
            throw new EOFException("Not enough bytes to read a byte array of size " + length + '.');
        }
        MemoryJvmKt.m254copyTo9zorpBc(memory$iv, destination, start$iv, length, offset);
        Unit unit = Unit.INSTANCE;
        $this$readFully.discardExact(length);
    }

    public static /* synthetic */ void readFully$default(ChunkBuffer $this$readFully_u24default, byte[] destination, int offset, int length, int i, Object obj) {
        if ((i & 2) != 0) {
            offset = 0;
        }
        if ((i & 4) != 0) {
            length = destination.length - offset;
        }
        Intrinsics.checkNotNullParameter($this$readFully_u24default, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        readFully((Buffer) $this$readFully_u24default, destination, offset, length);
    }

    public static final void readFully(ChunkBuffer $this$readFully, byte[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        readFully((Buffer) $this$readFully, destination, offset, length);
    }

    /* renamed from: readFully-o1GoV1E$default, reason: not valid java name */
    public static /* synthetic */ void m392readFullyo1GoV1E$default(Buffer buffer, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.m587getSizeimpl(bArr) - i;
        }
        m391readFullyo1GoV1E(buffer, bArr, i, i2);
    }

    /* renamed from: readFully-o1GoV1E, reason: not valid java name */
    public static final void m391readFullyo1GoV1E(Buffer readFully, byte[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter(readFully, "$this$readFully");
        Intrinsics.checkNotNullParameter(destination, "destination");
        readFully(readFully, destination, offset, length);
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        return readAvailable(buffer, bArr, i, i2);
    }

    public static final int readAvailable(Buffer $this$readAvailable, byte[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (!(offset >= 0)) {
            throw new IllegalArgumentException(("offset shouldn't be negative: " + offset).toString());
        }
        if (!(length >= 0)) {
            throw new IllegalArgumentException(("length shouldn't be negative: " + length).toString());
        }
        if (!(offset + length <= destination.length)) {
            throw new IllegalArgumentException(("offset + length should be less than the destination size: " + offset + " + " + length + " > " + destination.length).toString());
        }
        if (!($this$readAvailable.getWritePosition() > $this$readAvailable.getReadPosition())) {
            return -1;
        }
        int toBeRead = Math.min(length, $this$readAvailable.getWritePosition() - $this$readAvailable.getReadPosition());
        readFully($this$readAvailable, destination, offset, toBeRead);
        return toBeRead;
    }

    public static /* synthetic */ int readAvailable$default(ChunkBuffer $this$readAvailable_u24default, byte[] destination, int offset, int length, int i, Object obj) {
        if ((i & 2) != 0) {
            offset = 0;
        }
        if ((i & 4) != 0) {
            length = destination.length - offset;
        }
        Intrinsics.checkNotNullParameter($this$readAvailable_u24default, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        return readAvailable((Buffer) $this$readAvailable_u24default, destination, offset, length);
    }

    public static final int readAvailable(ChunkBuffer $this$readAvailable, byte[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        return readAvailable((Buffer) $this$readAvailable, destination, offset, length);
    }

    /* renamed from: readAvailable-o1GoV1E$default, reason: not valid java name */
    public static /* synthetic */ int m384readAvailableo1GoV1E$default(Buffer buffer, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.m587getSizeimpl(bArr) - i;
        }
        return m383readAvailableo1GoV1E(buffer, bArr, i, i2);
    }

    /* renamed from: readAvailable-o1GoV1E, reason: not valid java name */
    public static final int m383readAvailableo1GoV1E(Buffer readAvailable, byte[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter(readAvailable, "$this$readAvailable");
        Intrinsics.checkNotNullParameter(destination, "destination");
        return readAvailable(readAvailable, destination, offset, length);
    }

    public static /* synthetic */ void writeFully$default(Buffer buffer, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        writeFully(buffer, bArr, i, i2);
    }

    public static final void writeFully(Buffer $this$writeFully, byte[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        ByteBuffer memory$iv = $this$writeFully.getMemory();
        int start$iv = $this$writeFully.getWritePosition();
        int endExclusive$iv = $this$writeFully.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv < length) {
            throw new InsufficientSpaceException("byte array", length, writeRemaining$iv);
        }
        ByteBuffer order = ByteBuffer.wrap(source, offset, length).slice().order(java.nio.ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        ByteBuffer sourceMemory$iv = Memory.m235constructorimpl(order);
        Memory.m236copyToJT6ljtQ(sourceMemory$iv, memory$iv, 0, length, start$iv);
        $this$writeFully.commitWritten(length);
    }

    public static /* synthetic */ void writeFully$default(ChunkBuffer $this$writeFully_u24default, byte[] source, int offset, int length, int i, Object obj) {
        if ((i & 2) != 0) {
            offset = 0;
        }
        if ((i & 4) != 0) {
            length = source.length - offset;
        }
        Intrinsics.checkNotNullParameter($this$writeFully_u24default, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        writeFully((Buffer) $this$writeFully_u24default, source, offset, length);
    }

    public static final void writeFully(ChunkBuffer $this$writeFully, byte[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        writeFully((Buffer) $this$writeFully, source, offset, length);
    }

    /* renamed from: writeFully-o1GoV1E$default, reason: not valid java name */
    public static /* synthetic */ void m400writeFullyo1GoV1E$default(Buffer buffer, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UByteArray.m587getSizeimpl(bArr) - i;
        }
        m399writeFullyo1GoV1E(buffer, bArr, i, i2);
    }

    /* renamed from: writeFully-o1GoV1E, reason: not valid java name */
    public static final void m399writeFullyo1GoV1E(Buffer writeFully, byte[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter(writeFully, "$this$writeFully");
        Intrinsics.checkNotNullParameter(source, "source");
        writeFully(writeFully, source, offset, length);
    }

    public static /* synthetic */ void readFully$default(Buffer buffer, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        readFully(buffer, sArr, i, i2);
    }

    public static final void readFully(Buffer $this$readFully, short[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        int size$iv = length * 2;
        ByteBuffer memory$iv = $this$readFully.getMemory();
        int start$iv = $this$readFully.getReadPosition();
        int endExclusive$iv = $this$readFully.getWritePosition();
        if (endExclusive$iv - start$iv < size$iv) {
            throw new EOFException("Not enough bytes to read a short integers array of size " + size$iv + '.');
        }
        PrimitiveArraysJvmKt.m355loadShortArray9zorpBc(memory$iv, start$iv, destination, offset, length);
        Unit unit = Unit.INSTANCE;
        $this$readFully.discardExact(size$iv);
    }

    /* renamed from: readFully-Wt3Bwxc$default, reason: not valid java name */
    public static /* synthetic */ void m390readFullyWt3Bwxc$default(Buffer buffer, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m850getSizeimpl(sArr) - i;
        }
        m389readFullyWt3Bwxc(buffer, sArr, i, i2);
    }

    /* renamed from: readFully-Wt3Bwxc, reason: not valid java name */
    public static final void m389readFullyWt3Bwxc(Buffer readFully, short[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter(readFully, "$this$readFully");
        Intrinsics.checkNotNullParameter(destination, "destination");
        readFully(readFully, destination, offset, length);
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        return readAvailable(buffer, sArr, i, i2);
    }

    public static final int readAvailable(Buffer $this$readAvailable, short[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (!(offset >= 0)) {
            throw new IllegalArgumentException(("offset shouldn't be negative: " + offset).toString());
        }
        if (!(length >= 0)) {
            throw new IllegalArgumentException(("length shouldn't be negative: " + length).toString());
        }
        if (!(offset + length <= destination.length)) {
            throw new IllegalArgumentException(("offset + length should be less than the destination size: " + offset + " + " + length + " > " + destination.length).toString());
        }
        if (!($this$readAvailable.getWritePosition() > $this$readAvailable.getReadPosition())) {
            return -1;
        }
        int toBeRead = Math.min(length / 2, $this$readAvailable.getWritePosition() - $this$readAvailable.getReadPosition());
        readFully($this$readAvailable, destination, offset, toBeRead);
        return toBeRead;
    }

    /* renamed from: readAvailable-Wt3Bwxc$default, reason: not valid java name */
    public static /* synthetic */ int m382readAvailableWt3Bwxc$default(Buffer buffer, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m850getSizeimpl(sArr) - i;
        }
        return m381readAvailableWt3Bwxc(buffer, sArr, i, i2);
    }

    /* renamed from: readAvailable-Wt3Bwxc, reason: not valid java name */
    public static final int m381readAvailableWt3Bwxc(Buffer readAvailable, short[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter(readAvailable, "$this$readAvailable");
        Intrinsics.checkNotNullParameter(destination, "destination");
        return readAvailable(readAvailable, destination, offset, length);
    }

    public static /* synthetic */ void writeFully$default(Buffer buffer, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length - i;
        }
        writeFully(buffer, sArr, i, i2);
    }

    public static final void writeFully(Buffer $this$writeFully, short[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        int size$iv = length * 2;
        ByteBuffer memory$iv = $this$writeFully.getMemory();
        int start$iv = $this$writeFully.getWritePosition();
        int endExclusive$iv = $this$writeFully.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv < size$iv) {
            throw new InsufficientSpaceException("short integers array", size$iv, writeRemaining$iv);
        }
        PrimitiveArraysJvmKt.m375storeShortArray9zorpBc(memory$iv, start$iv, source, offset, length);
        $this$writeFully.commitWritten(size$iv);
    }

    /* renamed from: writeFully-Wt3Bwxc$default, reason: not valid java name */
    public static /* synthetic */ void m398writeFullyWt3Bwxc$default(Buffer buffer, short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UShortArray.m850getSizeimpl(sArr) - i;
        }
        m397writeFullyWt3Bwxc(buffer, sArr, i, i2);
    }

    /* renamed from: writeFully-Wt3Bwxc, reason: not valid java name */
    public static final void m397writeFullyWt3Bwxc(Buffer writeFully, short[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter(writeFully, "$this$writeFully");
        Intrinsics.checkNotNullParameter(source, "source");
        writeFully(writeFully, source, offset, length);
    }

    public static /* synthetic */ void readFully$default(Buffer buffer, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        readFully(buffer, iArr, i, i2);
    }

    public static final void readFully(Buffer $this$readFully, int[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        int size$iv = length * 4;
        ByteBuffer memory$iv = $this$readFully.getMemory();
        int start$iv = $this$readFully.getReadPosition();
        int endExclusive$iv = $this$readFully.getWritePosition();
        if (endExclusive$iv - start$iv < size$iv) {
            throw new EOFException("Not enough bytes to read a integers array of size " + size$iv + '.');
        }
        PrimitiveArraysJvmKt.m347loadIntArray9zorpBc(memory$iv, start$iv, destination, offset, length);
        Unit unit = Unit.INSTANCE;
        $this$readFully.discardExact(size$iv);
    }

    /* renamed from: readFully-o2ZM2JE$default, reason: not valid java name */
    public static /* synthetic */ void m394readFullyo2ZM2JE$default(Buffer buffer, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UIntArray.m666getSizeimpl(iArr) - i;
        }
        m393readFullyo2ZM2JE(buffer, iArr, i, i2);
    }

    /* renamed from: readFully-o2ZM2JE, reason: not valid java name */
    public static final void m393readFullyo2ZM2JE(Buffer readFully, int[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter(readFully, "$this$readFully");
        Intrinsics.checkNotNullParameter(destination, "destination");
        readFully(readFully, destination, offset, length);
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        return readAvailable(buffer, iArr, i, i2);
    }

    public static final int readAvailable(Buffer $this$readAvailable, int[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (!(offset >= 0)) {
            throw new IllegalArgumentException(("offset shouldn't be negative: " + offset).toString());
        }
        if (!(length >= 0)) {
            throw new IllegalArgumentException(("length shouldn't be negative: " + length).toString());
        }
        if (!(offset + length <= destination.length)) {
            throw new IllegalArgumentException(("offset + length should be less than the destination size: " + offset + " + " + length + " > " + destination.length).toString());
        }
        if (!($this$readAvailable.getWritePosition() > $this$readAvailable.getReadPosition())) {
            return -1;
        }
        int toBeRead = Math.min(length / 4, $this$readAvailable.getWritePosition() - $this$readAvailable.getReadPosition());
        readFully($this$readAvailable, destination, offset, toBeRead);
        return toBeRead;
    }

    /* renamed from: readAvailable-o2ZM2JE$default, reason: not valid java name */
    public static /* synthetic */ int m386readAvailableo2ZM2JE$default(Buffer buffer, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UIntArray.m666getSizeimpl(iArr) - i;
        }
        return m385readAvailableo2ZM2JE(buffer, iArr, i, i2);
    }

    /* renamed from: readAvailable-o2ZM2JE, reason: not valid java name */
    public static final int m385readAvailableo2ZM2JE(Buffer readAvailable, int[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter(readAvailable, "$this$readAvailable");
        Intrinsics.checkNotNullParameter(destination, "destination");
        return readAvailable(readAvailable, destination, offset, length);
    }

    public static /* synthetic */ void writeFully$default(Buffer buffer, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = iArr.length - i;
        }
        writeFully(buffer, iArr, i, i2);
    }

    public static final void writeFully(Buffer $this$writeFully, int[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        int size$iv = length * 4;
        ByteBuffer memory$iv = $this$writeFully.getMemory();
        int start$iv = $this$writeFully.getWritePosition();
        int endExclusive$iv = $this$writeFully.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv < size$iv) {
            throw new InsufficientSpaceException("integers array", size$iv, writeRemaining$iv);
        }
        PrimitiveArraysJvmKt.m367storeIntArray9zorpBc(memory$iv, start$iv, source, offset, length);
        $this$writeFully.commitWritten(size$iv);
    }

    /* renamed from: writeFully-o2ZM2JE$default, reason: not valid java name */
    public static /* synthetic */ void m402writeFullyo2ZM2JE$default(Buffer buffer, int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = UIntArray.m666getSizeimpl(iArr) - i;
        }
        m401writeFullyo2ZM2JE(buffer, iArr, i, i2);
    }

    /* renamed from: writeFully-o2ZM2JE, reason: not valid java name */
    public static final void m401writeFullyo2ZM2JE(Buffer writeFully, int[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter(writeFully, "$this$writeFully");
        Intrinsics.checkNotNullParameter(source, "source");
        writeFully(writeFully, source, offset, length);
    }

    public static /* synthetic */ void readFully$default(Buffer buffer, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        readFully(buffer, jArr, i, i2);
    }

    public static final void readFully(Buffer $this$readFully, long[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        int size$iv = length * 8;
        ByteBuffer memory$iv = $this$readFully.getMemory();
        int start$iv = $this$readFully.getReadPosition();
        int endExclusive$iv = $this$readFully.getWritePosition();
        if (endExclusive$iv - start$iv < size$iv) {
            throw new EOFException("Not enough bytes to read a long integers array of size " + size$iv + '.');
        }
        PrimitiveArraysJvmKt.m351loadLongArray9zorpBc(memory$iv, start$iv, destination, offset, length);
        Unit unit = Unit.INSTANCE;
        $this$readFully.discardExact(size$iv);
    }

    /* renamed from: readFully-pqYNikA$default, reason: not valid java name */
    public static /* synthetic */ void m396readFullypqYNikA$default(Buffer buffer, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m745getSizeimpl(jArr) - i;
        }
        m395readFullypqYNikA(buffer, jArr, i, i2);
    }

    /* renamed from: readFully-pqYNikA, reason: not valid java name */
    public static final void m395readFullypqYNikA(Buffer readFully, long[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter(readFully, "$this$readFully");
        Intrinsics.checkNotNullParameter(destination, "destination");
        readFully(readFully, destination, offset, length);
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        return readAvailable(buffer, jArr, i, i2);
    }

    public static final int readAvailable(Buffer $this$readAvailable, long[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (!(offset >= 0)) {
            throw new IllegalArgumentException(("offset shouldn't be negative: " + offset).toString());
        }
        if (!(length >= 0)) {
            throw new IllegalArgumentException(("length shouldn't be negative: " + length).toString());
        }
        if (!(offset + length <= destination.length)) {
            throw new IllegalArgumentException(("offset + length should be less than the destination size: " + offset + " + " + length + " > " + destination.length).toString());
        }
        if (!($this$readAvailable.getWritePosition() > $this$readAvailable.getReadPosition())) {
            return -1;
        }
        int toBeRead = Math.min(length / 8, $this$readAvailable.getWritePosition() - $this$readAvailable.getReadPosition());
        readFully($this$readAvailable, destination, offset, toBeRead);
        return toBeRead;
    }

    /* renamed from: readAvailable-pqYNikA$default, reason: not valid java name */
    public static /* synthetic */ int m388readAvailablepqYNikA$default(Buffer buffer, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m745getSizeimpl(jArr) - i;
        }
        return m387readAvailablepqYNikA(buffer, jArr, i, i2);
    }

    /* renamed from: readAvailable-pqYNikA, reason: not valid java name */
    public static final int m387readAvailablepqYNikA(Buffer readAvailable, long[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter(readAvailable, "$this$readAvailable");
        Intrinsics.checkNotNullParameter(destination, "destination");
        return readAvailable(readAvailable, destination, offset, length);
    }

    public static /* synthetic */ void writeFully$default(Buffer buffer, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length - i;
        }
        writeFully(buffer, jArr, i, i2);
    }

    public static final void writeFully(Buffer $this$writeFully, long[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        int size$iv = length * 8;
        ByteBuffer memory$iv = $this$writeFully.getMemory();
        int start$iv = $this$writeFully.getWritePosition();
        int endExclusive$iv = $this$writeFully.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv < size$iv) {
            throw new InsufficientSpaceException("long integers array", size$iv, writeRemaining$iv);
        }
        PrimitiveArraysJvmKt.m371storeLongArray9zorpBc(memory$iv, start$iv, source, offset, length);
        $this$writeFully.commitWritten(size$iv);
    }

    /* renamed from: writeFully-pqYNikA$default, reason: not valid java name */
    public static /* synthetic */ void m404writeFullypqYNikA$default(Buffer buffer, long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = ULongArray.m745getSizeimpl(jArr) - i;
        }
        m403writeFullypqYNikA(buffer, jArr, i, i2);
    }

    /* renamed from: writeFully-pqYNikA, reason: not valid java name */
    public static final void m403writeFullypqYNikA(Buffer writeFully, long[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter(writeFully, "$this$writeFully");
        Intrinsics.checkNotNullParameter(source, "source");
        writeFully(writeFully, source, offset, length);
    }

    public static /* synthetic */ void readFully$default(Buffer buffer, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        readFully(buffer, fArr, i, i2);
    }

    public static final void readFully(Buffer $this$readFully, float[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        int size$iv = length * 4;
        ByteBuffer memory$iv = $this$readFully.getMemory();
        int start$iv = $this$readFully.getReadPosition();
        int endExclusive$iv = $this$readFully.getWritePosition();
        if (endExclusive$iv - start$iv < size$iv) {
            throw new EOFException("Not enough bytes to read a floating point numbers array of size " + size$iv + '.');
        }
        PrimitiveArraysJvmKt.m343loadFloatArray9zorpBc(memory$iv, start$iv, destination, offset, length);
        Unit unit = Unit.INSTANCE;
        $this$readFully.discardExact(size$iv);
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        return readAvailable(buffer, fArr, i, i2);
    }

    public static final int readAvailable(Buffer $this$readAvailable, float[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (!(offset >= 0)) {
            throw new IllegalArgumentException(("offset shouldn't be negative: " + offset).toString());
        }
        if (!(length >= 0)) {
            throw new IllegalArgumentException(("length shouldn't be negative: " + length).toString());
        }
        if (!(offset + length <= destination.length)) {
            throw new IllegalArgumentException(("offset + length should be less than the destination size: " + offset + " + " + length + " > " + destination.length).toString());
        }
        if (!($this$readAvailable.getWritePosition() > $this$readAvailable.getReadPosition())) {
            return -1;
        }
        int toBeRead = Math.min(length / 4, $this$readAvailable.getWritePosition() - $this$readAvailable.getReadPosition());
        readFully($this$readAvailable, destination, offset, toBeRead);
        return toBeRead;
    }

    public static /* synthetic */ void writeFully$default(Buffer buffer, float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length - i;
        }
        writeFully(buffer, fArr, i, i2);
    }

    public static final void writeFully(Buffer $this$writeFully, float[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        int size$iv = length * 4;
        ByteBuffer memory$iv = $this$writeFully.getMemory();
        int start$iv = $this$writeFully.getWritePosition();
        int endExclusive$iv = $this$writeFully.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv < size$iv) {
            throw new InsufficientSpaceException("floating point numbers array", size$iv, writeRemaining$iv);
        }
        PrimitiveArraysJvmKt.m363storeFloatArray9zorpBc(memory$iv, start$iv, source, offset, length);
        $this$writeFully.commitWritten(size$iv);
    }

    public static /* synthetic */ void readFully$default(Buffer buffer, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        readFully(buffer, dArr, i, i2);
    }

    public static final void readFully(Buffer $this$readFully, double[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        int size$iv = length * 8;
        ByteBuffer memory$iv = $this$readFully.getMemory();
        int start$iv = $this$readFully.getReadPosition();
        int endExclusive$iv = $this$readFully.getWritePosition();
        if (endExclusive$iv - start$iv < size$iv) {
            throw new EOFException("Not enough bytes to read a floating point numbers array of size " + size$iv + '.');
        }
        PrimitiveArraysJvmKt.m339loadDoubleArray9zorpBc(memory$iv, start$iv, destination, offset, length);
        Unit unit = Unit.INSTANCE;
        $this$readFully.discardExact(size$iv);
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        return readAvailable(buffer, dArr, i, i2);
    }

    public static final int readAvailable(Buffer $this$readAvailable, double[] destination, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (!(offset >= 0)) {
            throw new IllegalArgumentException(("offset shouldn't be negative: " + offset).toString());
        }
        if (!(length >= 0)) {
            throw new IllegalArgumentException(("length shouldn't be negative: " + length).toString());
        }
        if (!(offset + length <= destination.length)) {
            throw new IllegalArgumentException(("offset + length should be less than the destination size: " + offset + " + " + length + " > " + destination.length).toString());
        }
        if (!($this$readAvailable.getWritePosition() > $this$readAvailable.getReadPosition())) {
            return -1;
        }
        int toBeRead = Math.min(length / 8, $this$readAvailable.getWritePosition() - $this$readAvailable.getReadPosition());
        readFully($this$readAvailable, destination, offset, toBeRead);
        return toBeRead;
    }

    public static /* synthetic */ void writeFully$default(Buffer buffer, double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length - i;
        }
        writeFully(buffer, dArr, i, i2);
    }

    public static final void writeFully(Buffer $this$writeFully, double[] source, int offset, int length) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        int size$iv = length * 8;
        ByteBuffer memory$iv = $this$writeFully.getMemory();
        int start$iv = $this$writeFully.getWritePosition();
        int endExclusive$iv = $this$writeFully.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv < size$iv) {
            throw new InsufficientSpaceException("floating point numbers array", size$iv, writeRemaining$iv);
        }
        PrimitiveArraysJvmKt.m359storeDoubleArray9zorpBc(memory$iv, start$iv, source, offset, length);
        $this$writeFully.commitWritten(size$iv);
    }

    public static /* synthetic */ int readFully$default(Buffer buffer, Buffer this_$iv, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = this_$iv.getLimit() - this_$iv.getWritePosition();
        }
        return readFully(buffer, this_$iv, i);
    }

    public static final int readFully(Buffer $this$readFully, Buffer dst, int length) {
        Intrinsics.checkNotNullParameter($this$readFully, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        if (!(length >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(length <= dst.getLimit() - dst.getWritePosition())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        ByteBuffer memory$iv = $this$readFully.getMemory();
        int start$iv = $this$readFully.getReadPosition();
        int endExclusive$iv = $this$readFully.getWritePosition();
        if (endExclusive$iv - start$iv < length) {
            throw new EOFException("Not enough bytes to read a buffer content of size " + length + '.');
        }
        Memory.m236copyToJT6ljtQ(memory$iv, dst.getMemory(), start$iv, length, dst.getWritePosition());
        dst.commitWritten(length);
        Unit unit = Unit.INSTANCE;
        $this$readFully.discardExact(length);
        return length;
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, Buffer this_$iv, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = this_$iv.getLimit() - this_$iv.getWritePosition();
        }
        return readAvailable(buffer, this_$iv, i);
    }

    public static final int readAvailable(Buffer $this$readAvailable, Buffer dst, int length) {
        Intrinsics.checkNotNullParameter($this$readAvailable, "<this>");
        Intrinsics.checkNotNullParameter(dst, "dst");
        if (!($this$readAvailable.getWritePosition() > $this$readAvailable.getReadPosition())) {
            return -1;
        }
        int readSize = Math.min(dst.getLimit() - dst.getWritePosition(), Math.min($this$readAvailable.getWritePosition() - $this$readAvailable.getReadPosition(), length));
        ByteBuffer memory$iv = $this$readAvailable.getMemory();
        int start$iv = $this$readAvailable.getReadPosition();
        int endExclusive$iv = $this$readAvailable.getWritePosition();
        if (endExclusive$iv - start$iv < readSize) {
            throw new EOFException("Not enough bytes to read a buffer content of size " + readSize + '.');
        }
        Memory.m236copyToJT6ljtQ(memory$iv, dst.getMemory(), start$iv, readSize, dst.getWritePosition());
        dst.commitWritten(readSize);
        Unit unit = Unit.INSTANCE;
        $this$readAvailable.discardExact(readSize);
        return readSize;
    }

    public static final void writeFully(Buffer $this$writeFully, Buffer src) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        int length = src.getWritePosition() - src.getReadPosition();
        ByteBuffer memory$iv = $this$writeFully.getMemory();
        int start$iv = $this$writeFully.getWritePosition();
        int endExclusive$iv = $this$writeFully.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv < length) {
            throw new InsufficientSpaceException("buffer readable content", length, writeRemaining$iv);
        }
        Memory.m236copyToJT6ljtQ(src.getMemory(), memory$iv, src.getReadPosition(), length, start$iv);
        src.discardExact(length);
        $this$writeFully.commitWritten(length);
    }

    public static final void writeFully(Buffer $this$writeFully, Buffer src, int length) {
        Intrinsics.checkNotNullParameter($this$writeFully, "<this>");
        Intrinsics.checkNotNullParameter(src, "src");
        if (!(length >= 0)) {
            throw new IllegalArgumentException(("length shouldn't be negative: " + length).toString());
        }
        if (!(length <= src.getWritePosition() - src.getReadPosition())) {
            throw new IllegalArgumentException(("length shouldn't be greater than the source read remaining: " + length + " > " + (src.getWritePosition() - src.getReadPosition())).toString());
        }
        if (!(length <= $this$writeFully.getLimit() - $this$writeFully.getWritePosition())) {
            throw new IllegalArgumentException(("length shouldn't be greater than the destination write remaining space: " + length + " > " + ($this$writeFully.getLimit() - $this$writeFully.getWritePosition())).toString());
        }
        ByteBuffer memory$iv = $this$writeFully.getMemory();
        int start$iv = $this$writeFully.getWritePosition();
        int endExclusive$iv = $this$writeFully.getLimit();
        int writeRemaining$iv = endExclusive$iv - start$iv;
        if (writeRemaining$iv < length) {
            throw new InsufficientSpaceException("buffer readable content", length, writeRemaining$iv);
        }
        Memory.m236copyToJT6ljtQ(src.getMemory(), memory$iv, src.getReadPosition(), length, start$iv);
        src.discardExact(length);
        $this$writeFully.commitWritten(length);
    }

    public static final <R> R readExact(Buffer $this$readExact, int size, String name, Function2<? super Memory, ? super Integer, ? extends R> block) {
        Intrinsics.checkNotNullParameter($this$readExact, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(block, "block");
        ByteBuffer memory = $this$readExact.getMemory();
        int start = $this$readExact.getReadPosition();
        int endExclusive = $this$readExact.getWritePosition();
        if (endExclusive - start < size) {
            throw new EOFException("Not enough bytes to read a " + name + " of size " + size + '.');
        }
        R invoke = block.invoke(Memory.m234boximpl(memory), Integer.valueOf(start));
        $this$readExact.discardExact(size);
        return invoke;
    }

    public static final void writeExact(Buffer $this$writeExact, int size, String name, Function2<? super Memory, ? super Integer, Unit> block) {
        Intrinsics.checkNotNullParameter($this$writeExact, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(block, "block");
        ByteBuffer memory = $this$writeExact.getMemory();
        int start = $this$writeExact.getWritePosition();
        int endExclusive = $this$writeExact.getLimit();
        int writeRemaining = endExclusive - start;
        if (writeRemaining < size) {
            throw new InsufficientSpaceException(name, size, writeRemaining);
        }
        block.invoke(Memory.m234boximpl(memory), Integer.valueOf(start));
        $this$writeExact.commitWritten(size);
    }
}
