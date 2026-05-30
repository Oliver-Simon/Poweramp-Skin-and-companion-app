package io.ktor.utils.io;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.BufferPrimitivesJvmKt;
import io.ktor.utils.io.core.ByteBuffersKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.OutputArraysJVMKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import io.ktor.utils.io.internal.CancellableReusableContinuation;
import io.ktor.utils.io.internal.ClosedElement;
import io.ktor.utils.io.internal.FailedLookAhead;
import io.ktor.utils.io.internal.JoiningState;
import io.ktor.utils.io.internal.ObjectPoolKt;
import io.ktor.utils.io.internal.ReadSessionImpl;
import io.ktor.utils.io.internal.ReadWriteBufferState;
import io.ktor.utils.io.internal.ReadWriteBufferStateKt;
import io.ktor.utils.io.internal.RingBufferCapacity;
import io.ktor.utils.io.internal.TerminatedLookAhead;
import io.ktor.utils.io.internal.WriteSessionImpl;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ClosedReceiveChannelException;

/* compiled from: ByteBufferChannel.kt */
@Metadata(d1 = {"\u0000Ò\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0004\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\bB\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0010\u0018\u0000 ó\u00022\u00030õ\u00022\u00030ö\u00022\u00030÷\u00022\u00020k2\u00030ø\u00022\u00030ù\u0002:\u0002ó\u0002B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004B)\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0003\u0010\fJ\u001f\u0010\u0010\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0019J\u0013\u0010\u001b\u001a\u00020\u0014H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u0014H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001cJ\u0013\u0010\u001e\u001a\u00020\u0014H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001cJ/\u0010\"\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\n2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 H\u0082@ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J\u0011\u0010%\u001a\u0004\u0018\u00010$H\u0016¢\u0006\u0004\b%\u0010&J'\u0010*\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\nH\u0000¢\u0006\u0004\b(\u0010)J\u0019\u0010-\u001a\u00020\u00052\b\u0010,\u001a\u0004\u0018\u00010+H\u0016¢\u0006\u0004\b-\u0010.J\u0019\u0010/\u001a\u00020\u00052\b\u0010,\u001a\u0004\u0018\u00010+H\u0016¢\u0006\u0004\b/\u0010.JP\u00105\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u000526\u00104\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u000501H\u0082\b¢\u0006\u0004\b5\u00106J\u0017\u00107\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\nH\u0016¢\u0006\u0004\b7\u00108J-\u0010@\u001a\u00020:2\u0006\u00109\u001a\u00020\u00002\u0006\u0010;\u001a\u00020:2\b\u0010=\u001a\u0004\u0018\u00010<H\u0080@ø\u0001\u0000¢\u0006\u0004\b>\u0010?J\u000f\u0010D\u001a\u00020AH\u0000¢\u0006\u0004\bB\u0010CJ,\u0010G\u001a\u00020\u00142\u0017\u0010F\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bEH\u0082Hø\u0001\u0000¢\u0006\u0004\bG\u0010HJ4\u0010I\u001a\u00020\u00142\u0006\u0010=\u001a\u00020<2\u0017\u0010!\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bEH\u0082Hø\u0001\u0000¢\u0006\u0004\bI\u0010JJ\u001b\u0010L\u001a\u00020:2\u0006\u0010K\u001a\u00020:H\u0096@ø\u0001\u0000¢\u0006\u0004\bL\u0010MJ#\u0010O\u001a\u00020:2\u0006\u0010N\u001a\u00020:2\u0006\u0010K\u001a\u00020:H\u0082@ø\u0001\u0000¢\u0006\u0004\bO\u0010PJA\u0010S\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000e2\u0017\u0010R\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bEH\u0082\b¢\u0006\u0004\bS\u0010TJ\u000f\u0010U\u001a\u00020\u0014H\u0016¢\u0006\u0004\bU\u0010VJ\u0017\u0010X\u001a\u00020\u00142\u0006\u0010W\u001a\u00020\nH\u0016¢\u0006\u0004\bX\u00108J\u0017\u0010Y\u001a\u00020\u00142\u0006\u0010=\u001a\u00020<H\u0002¢\u0006\u0004\bY\u0010ZJ\u000f\u0010[\u001a\u00020\u0014H\u0016¢\u0006\u0004\b[\u0010VJ\u0017\u0010]\u001a\u00020\u00142\u0006\u0010\\\u001a\u00020\nH\u0002¢\u0006\u0004\b]\u00108J\u0011\u0010`\u001a\u0004\u0018\u00010<H\u0000¢\u0006\u0004\b^\u0010_J#\u0010d\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u00002\u0006\u0010a\u001a\u00020\u0005H\u0080@ø\u0001\u0000¢\u0006\u0004\bb\u0010cJ+\u0010e\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u00002\u0006\u0010a\u001a\u00020\u00052\u0006\u0010=\u001a\u00020<H\u0082@ø\u0001\u0000¢\u0006\u0004\be\u0010fJ.\u0010i\u001a\u00028\u0000\"\u0004\b\u0000\u0010g2\u0017\u00104\u001a\u0013\u0012\u0004\u0012\u00020h\u0012\u0004\u0012\u00028\u00000 ¢\u0006\u0002\bEH\u0017¢\u0006\u0004\bi\u0010jJB\u0010n\u001a\u00028\u0000\"\u0004\b\u0000\u0010g2'\u00104\u001a#\b\u0001\u0012\u0004\u0012\u00020k\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000l\u0012\u0006\u0012\u0004\u0018\u00010m01¢\u0006\u0002\bEH\u0097@ø\u0001\u0000¢\u0006\u0004\bn\u0010oJ\u000f\u0010p\u001a\u00020\bH\u0002¢\u0006\u0004\bp\u0010qJA\u0010x\u001a\u00020:2\u0006\u0010s\u001a\u00020r2\u0006\u0010t\u001a\u00020:2\u0006\u0010u\u001a\u00020:2\u0006\u0010\u001f\u001a\u00020:2\u0006\u0010K\u001a\u00020:H\u0096@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\bv\u0010wJ\u001f\u0010|\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010y\u001a\u00020\nH\u0000¢\u0006\u0004\bz\u0010{J/\u0010~\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\n2\u0012\u0010}\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 H\u0096@ø\u0001\u0000¢\u0006\u0004\b~\u0010#J/\u0010\u0081\u0001\u001a\u00020\n2\u0007\u0010\u0080\u0001\u001a\u00020\u007f2\b\b\u0002\u00107\u001a\u00020\n2\b\b\u0002\u0010K\u001a\u00020\nH\u0002¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J\u001b\u0010\u0081\u0001\u001a\u00020\n2\u0007\u0010\u0080\u0001\u001a\u00020\u0001H\u0002¢\u0006\u0006\b\u0081\u0001\u0010\u0083\u0001J-\u0010\u0081\u0001\u001a\u00020\n2\b\u0010\u0080\u0001\u001a\u00030\u0084\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0085\u0001\u001a\u00020\nH\u0002¢\u0006\u0006\b\u0081\u0001\u0010\u0086\u0001J \u0010\u0088\u0001\u001a\u00020\n2\b\u0010\u0080\u0001\u001a\u00030\u0087\u0001H\u0096@ø\u0001\u0000¢\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001J\u001f\u0010\u0088\u0001\u001a\u00020\n2\u0007\u0010\u0080\u0001\u001a\u00020\u0001H\u0096@ø\u0001\u0000¢\u0006\u0006\b\u0088\u0001\u0010\u008a\u0001J1\u0010\u0088\u0001\u001a\u00020\n2\b\u0010\u0080\u0001\u001a\u00030\u0084\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0085\u0001\u001a\u00020\nH\u0096@ø\u0001\u0000¢\u0006\u0006\b\u0088\u0001\u0010\u008b\u0001J.\u0010\u0088\u0001\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\n2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 H\u0016¢\u0006\u0006\b\u0088\u0001\u0010\u008c\u0001J \u0010\u008d\u0001\u001a\u00020\n2\b\u0010\u0080\u0001\u001a\u00030\u0087\u0001H\u0082@ø\u0001\u0000¢\u0006\u0006\b\u008d\u0001\u0010\u0089\u0001J\u001f\u0010\u008d\u0001\u001a\u00020\n2\u0007\u0010\u0080\u0001\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0006\b\u008d\u0001\u0010\u008a\u0001J1\u0010\u008d\u0001\u001a\u00020\n2\b\u0010\u0080\u0001\u001a\u00030\u0084\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0085\u0001\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0006\b\u008d\u0001\u0010\u008b\u0001J1\u0010\u008e\u0001\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\n2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 H\u0082@ø\u0001\u0000¢\u0006\u0005\b\u008e\u0001\u0010#J\u0015\u0010\u008f\u0001\u001a\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0005\b\u008f\u0001\u0010\u001cJ\u0016\u0010\u0091\u0001\u001a\u00030\u0090\u0001H\u0086@ø\u0001\u0000¢\u0006\u0005\b\u0091\u0001\u0010\u001cJ\u0016\u0010\u0093\u0001\u001a\u00030\u0092\u0001H\u0086@ø\u0001\u0000¢\u0006\u0005\b\u0093\u0001\u0010\u001cJ\u0016\u0010\u0095\u0001\u001a\u00030\u0094\u0001H\u0086@ø\u0001\u0000¢\u0006\u0005\b\u0095\u0001\u0010\u001cJ(\u0010\u0096\u0001\u001a\u00020\u00142\b\u0010\u0080\u0001\u001a\u00030\u0087\u00012\u0006\u0010\u0017\u001a\u00020\nH\u0096@ø\u0001\u0000¢\u0006\u0006\b\u0096\u0001\u0010\u0097\u0001J\u001f\u0010\u0096\u0001\u001a\u00020\n2\u0007\u0010\u0080\u0001\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0006\b\u0096\u0001\u0010\u008a\u0001J1\u0010\u0096\u0001\u001a\u00020\u00142\b\u0010\u0080\u0001\u001a\u00030\u0084\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0085\u0001\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0006\b\u0096\u0001\u0010\u008b\u0001J(\u0010\u0098\u0001\u001a\u00020\u00142\b\u0010\u0080\u0001\u001a\u00030\u0087\u00012\u0006\u0010\u0017\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0006\b\u0098\u0001\u0010\u0097\u0001J(\u0010\u0098\u0001\u001a\u00020\n2\u0007\u0010\u0080\u0001\u001a\u00020\u00012\u0007\u0010\u0099\u0001\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0006\b\u0098\u0001\u0010\u009a\u0001J1\u0010\u0098\u0001\u001a\u00020\u00142\b\u0010\u0080\u0001\u001a\u00030\u0084\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0085\u0001\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0006\b\u0098\u0001\u0010\u008b\u0001J\u0015\u0010\u009b\u0001\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0005\b\u009b\u0001\u0010\u001cJ\u0015\u0010\u009c\u0001\u001a\u00020:H\u0086@ø\u0001\u0000¢\u0006\u0005\b\u009c\u0001\u0010\u001cJ\u001e\u0010\u009e\u0001\u001a\u00030\u009d\u00012\u0006\u0010Q\u001a\u00020\nH\u0096@ø\u0001\u0000¢\u0006\u0005\b\u009e\u0001\u0010\u0019J1\u0010¡\u0001\u001a\u00030\u009d\u00012\u0006\u0010Q\u001a\u00020\n2\b\u0010 \u0001\u001a\u00030\u009f\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0006\b¡\u0001\u0010¢\u0001JC\u0010¦\u0001\u001a\u00028\u0000\"\n\b\u0000\u0010¤\u0001*\u00030£\u00012\u0006\u0010Q\u001a\u00020\n2\u0018\u0010¥\u0001\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00028\u00000 ¢\u0006\u0002\bEH\u0082Hø\u0001\u0000¢\u0006\u0005\b¦\u0001\u0010#J\u001e\u0010§\u0001\u001a\u00030\u009d\u00012\u0006\u0010;\u001a\u00020:H\u0096@ø\u0001\u0000¢\u0006\u0005\b§\u0001\u0010MJ\u001e\u0010¨\u0001\u001a\u00030\u009d\u00012\u0006\u0010;\u001a\u00020:H\u0082@ø\u0001\u0000¢\u0006\u0005\b¨\u0001\u0010MJ,\u0010ª\u0001\u001a\u00020\u00142\u0018\u0010}\u001a\u0014\u0012\u0005\u0012\u00030©\u0001\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bEH\u0017¢\u0006\u0006\bª\u0001\u0010«\u0001J\u0016\u0010\u00ad\u0001\u001a\u00030¬\u0001H\u0086@ø\u0001\u0000¢\u0006\u0005\b\u00ad\u0001\u0010\u001cJ\u001d\u0010®\u0001\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0005\b®\u0001\u0010\u0019J\u001d\u0010¯\u0001\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0005\b¯\u0001\u0010\u0019J\u001d\u0010°\u0001\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0005\b°\u0001\u0010\u0019J\u001b\u0010±\u0001\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\nH\u0082\b¢\u0006\u0006\b±\u0001\u0010²\u0001J?\u0010´\u0001\u001a\u00020\u00142(\u0010}\u001a$\b\u0001\u0012\u0005\u0012\u00030³\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140l\u0012\u0006\u0012\u0004\u0018\u00010m01¢\u0006\u0002\bEH\u0097@ø\u0001\u0000¢\u0006\u0005\b´\u0001\u0010oJ \u0010¶\u0001\u001a\u0005\u0018\u00010µ\u00012\u0006\u0010;\u001a\u00020\nH\u0096@ø\u0001\u0000¢\u0006\u0005\b¶\u0001\u0010\u0019J8\u0010»\u0001\u001a\u00020\u0005\"\u000f\b\u0000\u0010¹\u0001*\b0·\u0001j\u0003`¸\u00012\u0007\u0010º\u0001\u001a\u00028\u00002\u0006\u0010;\u001a\u00020\nH\u0096@ø\u0001\u0000¢\u0006\u0006\b»\u0001\u0010¼\u0001J-\u0010½\u0001\u001a\u00020\u00052\r\u0010º\u0001\u001a\b0·\u0001j\u0003`¸\u00012\u0006\u0010;\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0006\b½\u0001\u0010¼\u0001J-\u0010¾\u0001\u001a\u00020\u00052\r\u0010º\u0001\u001a\b0·\u0001j\u0003`¸\u00012\u0006\u0010;\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0006\b¾\u0001\u0010¼\u0001J2\u0010¿\u0001\u001a\u00020\u00052\u001d\u0010!\u001a\u0019\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000501¢\u0006\u0002\bEH\u0082\b¢\u0006\u0006\b¿\u0001\u0010À\u0001J\u001a\u0010Á\u0001\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\bH\u0002¢\u0006\u0006\bÁ\u0001\u0010Â\u0001J\u001b\u0010Ã\u0001\u001a\u00030\u009d\u00012\u0006\u0010;\u001a\u00020:H\u0002¢\u0006\u0006\bÃ\u0001\u0010Ä\u0001J&\u0010Ç\u0001\u001a\u0004\u0018\u00010\u00012\u0007\u0010Å\u0001\u001a\u00020\n2\u0007\u0010Æ\u0001\u001a\u00020\nH\u0016¢\u0006\u0006\bÇ\u0001\u0010È\u0001J\u0012\u0010Ë\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0006\bÉ\u0001\u0010Ê\u0001J&\u0010Î\u0001\u001a\u0004\u0018\u00010\u00002\u0007\u0010Ì\u0001\u001a\u00020\u00002\u0007\u0010Í\u0001\u001a\u00020<H\u0002¢\u0006\u0006\bÎ\u0001\u0010Ï\u0001J\u0011\u0010Ð\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\bÐ\u0001\u0010VJ\u0011\u0010Ò\u0001\u001a\u00020\u0014H\u0000¢\u0006\u0005\bÑ\u0001\u0010VJ\u001c\u0010Ó\u0001\u001a\u00020\u00142\b\u0010,\u001a\u0004\u0018\u00010+H\u0002¢\u0006\u0006\bÓ\u0001\u0010Ô\u0001J\u0011\u0010Õ\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\bÕ\u0001\u0010VJ#\u0010Õ\u0001\u001a\u00020\u00142\u000e\u0010×\u0001\u001a\t\u0012\u0004\u0012\u00020+0Ö\u0001H\u0082\b¢\u0006\u0006\bÕ\u0001\u0010Ø\u0001J\u0011\u0010Ù\u0001\u001a\u00020\u0014H\u0002¢\u0006\u0005\bÙ\u0001\u0010VJ#\u0010Û\u0001\u001a\u00020<2\u0007\u0010Ú\u0001\u001a\u00020\u00002\u0006\u0010a\u001a\u00020\u0005H\u0002¢\u0006\u0006\bÛ\u0001\u0010Ü\u0001J\u0014\u0010Ý\u0001\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0006\bÝ\u0001\u0010Þ\u0001J\u0014\u0010à\u0001\u001a\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0006\bß\u0001\u0010Þ\u0001J\u0012\u0010á\u0001\u001a\u00020\u0005H\u0002¢\u0006\u0006\bá\u0001\u0010â\u0001J\u0013\u0010ã\u0001\u001a\u00030³\u0001H\u0016¢\u0006\u0006\bã\u0001\u0010ä\u0001J(\u0010æ\u0001\u001a\u00020m2\u0006\u0010Q\u001a\u00020\n2\r\u0010å\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050lH\u0002¢\u0006\u0005\bæ\u0001\u0010\u0019J\u0013\u0010ç\u0001\u001a\u00030µ\u0001H\u0016¢\u0006\u0006\bç\u0001\u0010è\u0001J\u001a\u0010é\u0001\u001a\u00020\u00052\u0006\u0010=\u001a\u00020<H\u0002¢\u0006\u0006\bé\u0001\u0010ê\u0001J\u001b\u0010ì\u0001\u001a\u00020\u00052\u0007\u0010ë\u0001\u001a\u00020\u0005H\u0002¢\u0006\u0006\bì\u0001\u0010í\u0001J\u0012\u0010ï\u0001\u001a\u00020\u0005H\u0000¢\u0006\u0006\bî\u0001\u0010â\u0001J\u001c\u0010ñ\u0001\u001a\u00020\n2\b\u0010ð\u0001\u001a\u00030\u009d\u0001H\u0002¢\u0006\u0006\bñ\u0001\u0010ò\u0001J\u001d\u0010ô\u0001\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020\nH\u0080@ø\u0001\u0000¢\u0006\u0005\bó\u0001\u0010\u0019J1\u0010õ\u0001\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\n2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 H\u0096@ø\u0001\u0000¢\u0006\u0005\bõ\u0001\u0010#J\u001a\u0010ö\u0001\u001a\u00020\n2\u0006\u00109\u001a\u00020\u007fH\u0002¢\u0006\u0006\bö\u0001\u0010÷\u0001J\u001a\u0010ö\u0001\u001a\u00020\n2\u0006\u00109\u001a\u00020\u0001H\u0002¢\u0006\u0006\bö\u0001\u0010\u0083\u0001J,\u0010ö\u0001\u001a\u00020\n2\u0007\u00109\u001a\u00030\u0084\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0085\u0001\u001a\u00020\nH\u0002¢\u0006\u0006\bö\u0001\u0010\u0086\u0001J\u001f\u0010ø\u0001\u001a\u00020\n2\u0007\u00109\u001a\u00030\u0087\u0001H\u0096@ø\u0001\u0000¢\u0006\u0006\bø\u0001\u0010\u0089\u0001J\u001e\u0010ø\u0001\u001a\u00020\n2\u0006\u00109\u001a\u00020\u0001H\u0096@ø\u0001\u0000¢\u0006\u0006\bø\u0001\u0010\u008a\u0001J0\u0010ø\u0001\u001a\u00020\n2\u0007\u00109\u001a\u00030\u0084\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0085\u0001\u001a\u00020\nH\u0096@ø\u0001\u0000¢\u0006\u0006\bø\u0001\u0010\u008b\u0001J.\u0010ø\u0001\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\n2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 H\u0016¢\u0006\u0006\bø\u0001\u0010\u008c\u0001J\u001f\u0010ù\u0001\u001a\u00020\n2\u0007\u00109\u001a\u00030\u0087\u0001H\u0082@ø\u0001\u0000¢\u0006\u0006\bù\u0001\u0010\u0089\u0001J\u001e\u0010ù\u0001\u001a\u00020\n2\u0006\u00109\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0006\bù\u0001\u0010\u008a\u0001J \u0010û\u0001\u001a\u00020\u00142\b\u0010ú\u0001\u001a\u00030\u0090\u0001H\u0096@ø\u0001\u0000¢\u0006\u0006\bû\u0001\u0010ü\u0001J \u0010þ\u0001\u001a\u00020\u00142\b\u0010ý\u0001\u001a\u00030\u0092\u0001H\u0096@ø\u0001\u0000¢\u0006\u0006\bþ\u0001\u0010ÿ\u0001J \u0010\u0081\u0002\u001a\u00020\u00142\b\u0010\u0080\u0002\u001a\u00030\u0094\u0001H\u0096@ø\u0001\u0000¢\u0006\u0006\b\u0081\u0002\u0010\u0082\u0002J7\u0010\u0088\u0002\u001a\u00020\u00142\u0007\u0010\u0083\u0002\u001a\u00020r2\u0007\u0010\u0084\u0002\u001a\u00020\n2\u0007\u0010\u0085\u0002\u001a\u00020\nH\u0096@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0006\b\u0086\u0002\u0010\u0087\u0002J\u001e\u0010\u0088\u0002\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u007fH\u0096@ø\u0001\u0000¢\u0006\u0006\b\u0088\u0002\u0010\u0089\u0002J\u001e\u0010\u0088\u0002\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u0001H\u0096@ø\u0001\u0000¢\u0006\u0006\b\u0088\u0002\u0010\u008a\u0001J0\u0010\u0088\u0002\u001a\u00020\u00142\u0007\u00109\u001a\u00030\u0084\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0085\u0001\u001a\u00020\nH\u0096@ø\u0001\u0000¢\u0006\u0006\b\u0088\u0002\u0010\u008b\u0001J\u001e\u0010\u008a\u0002\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u007fH\u0082@ø\u0001\u0000¢\u0006\u0006\b\u008a\u0002\u0010\u0089\u0002J\u001e\u0010\u008a\u0002\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0006\b\u008a\u0002\u0010\u008a\u0001J0\u0010\u008a\u0002\u001a\u00020\u00142\u0007\u00109\u001a\u00030\u0084\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0085\u0001\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0006\b\u008a\u0002\u0010\u008b\u0001J\u001e\u0010\u008c\u0002\u001a\u00020\u00142\u0007\u0010\u008b\u0002\u001a\u00020\nH\u0096@ø\u0001\u0000¢\u0006\u0005\b\u008c\u0002\u0010\u0019J\u001e\u0010\u008e\u0002\u001a\u00020\u00142\u0007\u0010\u008d\u0002\u001a\u00020:H\u0096@ø\u0001\u0000¢\u0006\u0005\b\u008e\u0002\u0010MJ \u0010\u008f\u0002\u001a\u00020\u00142\b\u0010ð\u0001\u001a\u00030\u009d\u0001H\u0096@ø\u0001\u0000¢\u0006\u0006\b\u008f\u0002\u0010\u0090\u0002J \u0010\u0091\u0002\u001a\u00020\u00142\b\u0010ð\u0001\u001a\u00030\u009d\u0001H\u0082@ø\u0001\u0000¢\u0006\u0006\b\u0091\u0002\u0010\u0090\u0002JQ\u0010\u0093\u0002\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020\n2\u0017\u0010F\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bE2\u0018\u0010\u0092\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bEH\u0082Hø\u0001\u0000¢\u0006\u0006\b\u0093\u0002\u0010\u0094\u0002J \u0010\u0096\u0002\u001a\u00020\u00142\b\u0010\u0095\u0002\u001a\u00030¬\u0001H\u0096@ø\u0001\u0000¢\u0006\u0006\b\u0096\u0002\u0010\u0097\u0002J0\u0010\u0098\u0002\u001a\u00020\n2\u0007\u00109\u001a\u00030\u0084\u00012\u0006\u0010u\u001a\u00020\n2\u0007\u0010\u0085\u0001\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0006\b\u0098\u0002\u0010\u008b\u0001J\u001d\u0010\u0098\u0002\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0005\b\u0098\u0002\u0010\u0019J*\u0010\u009b\u0002\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020\n2\u000e\u0010\u009a\u0002\u001a\t\u0012\u0004\u0012\u00020\u00140\u0099\u0002H\u0002¢\u0006\u0006\b\u009b\u0002\u0010\u009c\u0002J\u001a\u0010\u009d\u0002\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020\nH\u0002¢\u0006\u0006\b\u009d\u0002\u0010²\u0001J>\u0010\u009e\u0002\u001a\u00020\u00142'\u00104\u001a#\b\u0001\u0012\u0004\u0012\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140l\u0012\u0006\u0012\u0004\u0018\u00010m01¢\u0006\u0002\bEH\u0097@ø\u0001\u0000¢\u0006\u0005\b\u009e\u0002\u0010oJ)\u0010\u009f\u0002\u001a\u00020\u00142\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050 H\u0096@ø\u0001\u0000¢\u0006\u0005\b\u009f\u0002\u0010HJ7\u0010 \u0002\u001a\u00020\u00052\u0007\u0010\u0080\u0001\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000e2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050 H\u0002¢\u0006\u0006\b \u0002\u0010¡\u0002J&\u0010¢\u0002\u001a\u00020\u00052\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050 H\u0002¢\u0006\u0006\b¢\u0002\u0010£\u0002J)\u0010¤\u0002\u001a\u00020\u00142\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050 H\u0082@ø\u0001\u0000¢\u0006\u0005\b¤\u0002\u0010HJ9\u0010¦\u0002\u001a\u00020\u00142$\u0010!\u001a \u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00140¥\u0002¢\u0006\u0002\bEH\u0082\b¢\u0006\u0006\b¦\u0002\u0010§\u0002J%\u0010¨\u0002\u001a\u00020\u0014*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\nH\u0002¢\u0006\u0005\b¨\u0002\u0010)J%\u0010©\u0002\u001a\u00020\u0014*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\nH\u0002¢\u0006\u0005\b©\u0002\u0010)J\u0015\u0010ª\u0002\u001a\u00020\u0014*\u00020\u0001H\u0002¢\u0006\u0005\bª\u0002\u0010\u0004J\u001f\u0010¬\u0002\u001a\u00020\n*\u00020\u00012\u0007\u0010«\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\b¬\u0002\u0010\u00ad\u0002J(\u0010°\u0002\u001a\u00020\u0014*\u00020\u00012\u0007\u0010®\u0002\u001a\u00020\n2\u0007\u0010¯\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\b°\u0002\u0010±\u0002J\u001d\u0010²\u0002\u001a\u00020\u0014*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\nH\u0002¢\u0006\u0005\b²\u0002\u0010{J@\u0010³\u0002\u001a\u00020\u0005*\u00020\u00012\u0006\u0010Q\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2\u0017\u0010R\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bEH\u0082\b¢\u0006\u0006\b³\u0002\u0010´\u0002J]\u0010µ\u0002\u001a\u00020\u0014*\u00020\u00012\u0006\u0010Q\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2\u0017\u0010F\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bE2\u0018\u0010\u0092\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140 ¢\u0006\u0002\bEH\u0082Hø\u0001\u0000¢\u0006\u0006\bµ\u0002\u0010¶\u0002R\u001b\u0010·\u0002\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b·\u0002\u0010¸\u0002R\u001d\u0010\u0006\u001a\u00020\u00058\u0016X\u0096\u0004¢\u0006\u000f\n\u0005\b\u0006\u0010¹\u0002\u001a\u0006\bº\u0002\u0010â\u0001R\u0017\u0010½\u0002\u001a\u00020\n8VX\u0096\u0004¢\u0006\b\u001a\u0006\b»\u0002\u0010¼\u0002R\u0017\u0010¿\u0002\u001a\u00020\n8VX\u0096\u0004¢\u0006\b\u001a\u0006\b¾\u0002\u0010¼\u0002R0\u0010Æ\u0002\u001a\u0005\u0018\u00010À\u00022\n\u0010Á\u0002\u001a\u0005\u0018\u00010À\u00028B@BX\u0082\u000e¢\u0006\u0010\u001a\u0006\bÂ\u0002\u0010Ã\u0002\"\u0006\bÄ\u0002\u0010Å\u0002R\u0019\u0010É\u0002\u001a\u0004\u0018\u00010+8VX\u0096\u0004¢\u0006\b\u001a\u0006\bÇ\u0002\u0010È\u0002R\u0017\u0010Ê\u0002\u001a\u00020\u00058VX\u0096\u0004¢\u0006\b\u001a\u0006\bÊ\u0002\u0010â\u0001R\u0017\u0010Ë\u0002\u001a\u00020\u00058VX\u0096\u0004¢\u0006\b\u001a\u0006\bË\u0002\u0010â\u0001R\u001b\u0010Í\u0001\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÍ\u0001\u0010Ì\u0002R\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\t\u0010Í\u0002R:\u0010Ò\u0002\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010l2\u000f\u0010Á\u0002\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010l8B@BX\u0082\u000e¢\u0006\u0010\u001a\u0006\bÎ\u0002\u0010Ï\u0002\"\u0006\bÐ\u0002\u0010Ñ\u0002R\u0019\u0010Ó\u0002\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÓ\u0002\u0010Ô\u0002R\u001f\u0010ª\u0001\u001a\u00030Õ\u00028\u0002X\u0082\u0004¢\u0006\u000f\n\u0006\bª\u0001\u0010Ö\u0002\u0012\u0005\b×\u0002\u0010VR\u001e\u0010Ù\u0002\u001a\t\u0012\u0004\u0012\u00020\u00050Ø\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÙ\u0002\u0010Ú\u0002R\u001d\u0010\u000b\u001a\u00020\n8\u0000X\u0080\u0004¢\u0006\u000f\n\u0005\b\u000b\u0010Ô\u0002\u001a\u0006\bÛ\u0002\u0010¼\u0002R\u0016\u0010Ý\u0002\u001a\u00020A8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bÜ\u0002\u0010CR2\u0010ß\u0002\u001a\u00020:2\u0007\u0010Þ\u0002\u001a\u00020:8\u0016@PX\u0096\u000e¢\u0006\u0018\n\u0006\bß\u0002\u0010à\u0002\u001a\u0006\bá\u0002\u0010â\u0002\"\u0006\bã\u0002\u0010ä\u0002R2\u0010å\u0002\u001a\u00020:2\u0007\u0010Þ\u0002\u001a\u00020:8\u0016@PX\u0096\u000e¢\u0006\u0018\n\u0006\bå\u0002\u0010à\u0002\u001a\u0006\bæ\u0002\u0010â\u0002\"\u0006\bç\u0002\u0010ä\u0002R:\u0010ê\u0002\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010l2\u000f\u0010Á\u0002\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010l8B@BX\u0082\u000e¢\u0006\u0010\u001a\u0006\bè\u0002\u0010Ï\u0002\"\u0006\bé\u0002\u0010Ñ\u0002R\u0019\u0010ë\u0002\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bë\u0002\u0010Ô\u0002R\u0018\u0010í\u0002\u001a\u00030ì\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bí\u0002\u0010î\u0002R\u001e\u0010ï\u0002\u001a\t\u0012\u0004\u0012\u00020\u00140Ø\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bï\u0002\u0010Ú\u0002R)\u0010ð\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140l\u0012\u0004\u0012\u00020m0 8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bð\u0002\u0010ñ\u0002R\u0019\u0010ò\u0002\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bò\u0002\u0010Ô\u0002\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006ô\u0002"}, d2 = {"Lio/ktor/utils/io/ByteBufferChannel;", "Ljava/nio/ByteBuffer;", "content", "<init>", "(Ljava/nio/ByteBuffer;)V", "", "autoFlush", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;", "pool", "", "reservedSize", "(ZLio/ktor/utils/io/pool/ObjectPool;I)V", "buffer", "Lio/ktor/utils/io/internal/RingBufferCapacity;", "capacity", "afterBufferVisited", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/internal/RingBufferCapacity;)I", "Lkotlinx/coroutines/Job;", "job", "", "attachJob", "(Lkotlinx/coroutines/Job;)V", "n", "awaitAtLeast", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitAtLeastSuspend", "awaitClose", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitContent", "awaitFreeSpace", "min", "Lkotlin/Function1;", "block", "awaitFreeSpaceOrDelegate", "(ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/ktor/utils/io/WriterSuspendSession;", "beginWriteSession", "()Lio/ktor/utils/io/WriterSuspendSession;", "count", "bytesWrittenFromSession$ktor_io", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/internal/RingBufferCapacity;I)V", "bytesWrittenFromSession", "", "cause", "cancel", "(Ljava/lang/Throwable;)Z", "close", "last", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "visitor", "consumeEachBufferRangeFast", "(ZLkotlin/jvm/functions/Function2;)Z", "consumed", "(I)V", "src", "", "limit", "Lio/ktor/utils/io/internal/JoiningState;", "joined", "copyDirect$ktor_io", "(Lio/ktor/utils/io/ByteBufferChannel;JLio/ktor/utils/io/internal/JoiningState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyDirect", "Lio/ktor/utils/io/internal/ReadWriteBufferState;", "currentState$ktor_io", "()Lio/ktor/utils/io/internal/ReadWriteBufferState;", "currentState", "Lkotlin/ExtensionFunctionType;", "channelWriter", "delegatePrimitive", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delegateSuspend", "(Lio/ktor/utils/io/internal/JoiningState;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "max", "discard", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "discarded0", "discardSuspend", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", ContentDisposition.Parameters.Size, "writer", "doWritePrimitive", "(ILjava/nio/ByteBuffer;Lio/ktor/utils/io/internal/RingBufferCapacity;Lkotlin/jvm/functions/Function1;)V", "endReadSession", "()V", "written", "endWriteSession", "ensureClosedJoined", "(Lio/ktor/utils/io/internal/JoiningState;)V", "flush", "minWriteSize", "flushImpl", "getJoining$ktor_io", "()Lio/ktor/utils/io/internal/JoiningState;", "getJoining", "delegateClose", "joinFrom$ktor_io", "(Lio/ktor/utils/io/ByteBufferChannel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "joinFrom", "joinFromSuspend", "(Lio/ktor/utils/io/ByteBufferChannel;ZLio/ktor/utils/io/internal/JoiningState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "R", "Lio/ktor/utils/io/LookAheadSession;", "lookAhead", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Lio/ktor/utils/io/LookAheadSuspendSession;", "Lkotlin/coroutines/Continuation;", "", "lookAheadSuspend", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newBuffer", "()Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;", "Lio/ktor/utils/io/bits/Memory;", "destination", "destinationOffset", TypedValues.CycleType.S_WAVE_OFFSET, "peekTo-lBXzO7A", "(Ljava/nio/ByteBuffer;JJJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "peekTo", "lockedSpace", "prepareWriteBuffer$ktor_io", "(Ljava/nio/ByteBuffer;I)V", "prepareWriteBuffer", "consumer", "read", "Lio/ktor/utils/io/core/Buffer;", "dst", "readAsMuchAsPossible", "(Lio/ktor/utils/io/core/Buffer;II)I", "(Ljava/nio/ByteBuffer;)I", "", "length", "([BII)I", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "readAvailable", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "([BIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(ILkotlin/jvm/functions/Function1;)I", "readAvailableSuspend", "readBlockSuspend", "readBoolean", "", "readByte", "", "readDouble", "", "readFloat", "readFully", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readFullySuspend", "rc0", "(Ljava/nio/ByteBuffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readInt", "readLong", "Lio/ktor/utils/io/core/ByteReadPacket;", "readPacket", "Lio/ktor/utils/io/core/BytePacketBuilder;", "builder", "readPacketSuspend", "(ILio/ktor/utils/io/core/BytePacketBuilder;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", ExifInterface.GPS_DIRECTION_TRUE, "getter", "readPrimitive", "readRemaining", "readRemainingSuspend", "Lio/ktor/utils/io/ReadSession;", "readSession", "(Lkotlin/jvm/functions/Function1;)V", "", "readShort", "readSuspend", "readSuspendImpl", "readSuspendLoop", "readSuspendPredicate", "(I)Z", "Lio/ktor/utils/io/SuspendableReadSession;", "readSuspendableSession", "", "readUTF8Line", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "out", "readUTF8LineTo", "(Ljava/lang/Appendable;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readUTF8LineToAscii", "readUTF8LineToUtf8Suspend", "reading", "(Lkotlin/jvm/functions/Function2;)Z", "releaseBuffer", "(Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;)V", "remainingPacket", "(J)Lio/ktor/utils/io/core/ByteReadPacket;", "skip", "atLeast", "request", "(II)Ljava/nio/ByteBuffer;", "resolveChannelInstance$ktor_io", "()Lio/ktor/utils/io/ByteBufferChannel;", "resolveChannelInstance", "current", "joining", "resolveDelegation", "(Lio/ktor/utils/io/ByteBufferChannel;Lio/ktor/utils/io/internal/JoiningState;)Lio/ktor/utils/io/ByteBufferChannel;", "restoreStateAfterRead", "restoreStateAfterWrite$ktor_io", "restoreStateAfterWrite", "resumeClosed", "(Ljava/lang/Throwable;)V", "resumeReadOp", "Lkotlin/Function0;", "exception", "(Lkotlin/jvm/functions/Function0;)V", "resumeWriteOp", "delegate", "setupDelegateTo", "(Lio/ktor/utils/io/ByteBufferChannel;Z)Lio/ktor/utils/io/internal/JoiningState;", "setupStateForRead", "()Ljava/nio/ByteBuffer;", "setupStateForWrite$ktor_io", "setupStateForWrite", "shouldResumeReadOp", "()Z", "startReadSession", "()Lio/ktor/utils/io/SuspendableReadSession;", "continuation", "suspensionForSize", "toString", "()Ljava/lang/String;", "tryCompleteJoining", "(Lio/ktor/utils/io/internal/JoiningState;)Z", "forceTermination", "tryReleaseBuffer", "(Z)Z", "tryTerminate$ktor_io", "tryTerminate", "packet", "tryWritePacketPart", "(Lio/ktor/utils/io/core/ByteReadPacket;)I", "tryWriteSuspend$ktor_io", "tryWriteSuspend", "write", "writeAsMuchAsPossible", "(Lio/ktor/utils/io/core/Buffer;)I", "writeAvailable", "writeAvailableSuspend", "b", "writeByte", "(BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "d", "writeDouble", "(DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "f", "writeFloat", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "memory", "startIndex", "endIndex", "writeFully-JT6ljtQ", "(Ljava/nio/ByteBuffer;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFully", "(Lio/ktor/utils/io/core/Buffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFullySuspend", "i", "writeInt", "l", "writeLong", "writePacket", "(Lio/ktor/utils/io/core/ByteReadPacket;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writePacketSuspend", "bufferWriter", "writePrimitive", "(ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "s", "writeShort", "(SLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeSuspend", "Lkotlinx/coroutines/CancellableContinuation;", "c", "writeSuspendBlock", "(ILkotlinx/coroutines/CancellableContinuation;)V", "writeSuspendPredicate", "writeSuspendSession", "writeWhile", "writeWhileLoop", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/internal/RingBufferCapacity;Lkotlin/jvm/functions/Function1;)Z", "writeWhileNoSuspend", "(Lkotlin/jvm/functions/Function1;)Z", "writeWhileSuspend", "Lkotlin/Function3;", "writing", "(Lkotlin/jvm/functions/Function3;)V", "bytesRead", "bytesWritten", "carry", "idx", "carryIndex", "(Ljava/nio/ByteBuffer;I)I", "position", "available", "prepareBuffer", "(Ljava/nio/ByteBuffer;II)V", "rollBytes", "tryWritePrimitive", "(Ljava/nio/ByteBuffer;ILio/ktor/utils/io/internal/RingBufferCapacity;Lkotlin/jvm/functions/Function1;)Z", "writeSuspendPrimitive", "(Ljava/nio/ByteBuffer;ILio/ktor/utils/io/internal/RingBufferCapacity;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "attachedJob", "Lkotlinx/coroutines/Job;", "Z", "getAutoFlush", "getAvailableForRead", "()I", "availableForRead", "getAvailableForWrite", "availableForWrite", "Lio/ktor/utils/io/internal/ClosedElement;", "value", "getClosed", "()Lio/ktor/utils/io/internal/ClosedElement;", "setClosed", "(Lio/ktor/utils/io/internal/ClosedElement;)V", "closed", "getClosedCause", "()Ljava/lang/Throwable;", "closedCause", "isClosedForRead", "isClosedForWrite", "Lio/ktor/utils/io/internal/JoiningState;", "Lio/ktor/utils/io/pool/ObjectPool;", "getReadOp", "()Lkotlin/coroutines/Continuation;", "setReadOp", "(Lkotlin/coroutines/Continuation;)V", "readOp", "readPosition", "I", "Lio/ktor/utils/io/internal/ReadSessionImpl;", "Lio/ktor/utils/io/internal/ReadSessionImpl;", "getReadSession$annotations", "Lio/ktor/utils/io/internal/CancellableReusableContinuation;", "readSuspendContinuationCache", "Lio/ktor/utils/io/internal/CancellableReusableContinuation;", "getReservedSize$ktor_io", "getState", "state", "<set-?>", "totalBytesRead", "J", "getTotalBytesRead", "()J", "setTotalBytesRead$ktor_io", "(J)V", "totalBytesWritten", "getTotalBytesWritten", "setTotalBytesWritten$ktor_io", "getWriteOp", "setWriteOp", "writeOp", "writePosition", "Lio/ktor/utils/io/internal/WriteSessionImpl;", "writeSession", "Lio/ktor/utils/io/internal/WriteSessionImpl;", "writeSuspendContinuationCache", "writeSuspension", "Lkotlin/jvm/functions/Function1;", "writeSuspensionSize", "Companion", "ktor-io", "Lio/ktor/utils/io/ByteChannel;", "Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/utils/io/ByteWriteChannel;", "Lio/ktor/utils/io/HasReadSession;", "Lio/ktor/utils/io/HasWriteSession;"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public class ByteBufferChannel implements ByteChannel, ByteReadChannel, ByteWriteChannel, LookAheadSuspendSession, HasReadSession, HasWriteSession {
    private static final int ReservedLongIndex = -8;
    private volatile /* synthetic */ Object _closed;
    private volatile /* synthetic */ Object _readOp;
    private volatile /* synthetic */ Object _state;
    volatile /* synthetic */ Object _writeOp;
    private volatile Job attachedJob;
    private final boolean autoFlush;
    private volatile JoiningState joining;
    private final ObjectPool<ReadWriteBufferState.Initial> pool;
    private int readPosition;
    private final ReadSessionImpl readSession;
    private final CancellableReusableContinuation<Boolean> readSuspendContinuationCache;
    private final int reservedSize;
    private volatile long totalBytesRead;
    private volatile long totalBytesWritten;
    private int writePosition;
    private final WriteSessionImpl writeSession;
    private final CancellableReusableContinuation<Unit> writeSuspendContinuationCache;
    private final Function1<Continuation<? super Unit>, Object> writeSuspension;
    private volatile int writeSuspensionSize;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, Object.class, "_state");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _closed$FU = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, Object.class, "_closed");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _readOp$FU = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, Object.class, "_readOp");
    static final /* synthetic */ AtomicReferenceFieldUpdater _writeOp$FU = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, Object.class, "_writeOp");

    private static /* synthetic */ void getReadSession$annotations() {
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object awaitContent(Continuation<? super Unit> continuation) {
        return awaitContent$suspendImpl(this, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object awaitFreeSpace(Continuation<? super Unit> continuation) {
        return awaitFreeSpace$suspendImpl(this, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object discard(long j, Continuation<? super Long> continuation) {
        return discard$suspendImpl(this, j, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    @Deprecated(message = "Use read { } instead.")
    public <R> Object lookAheadSuspend(Function2<? super LookAheadSuspendSession, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
        return lookAheadSuspend$suspendImpl(this, function2, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    /* renamed from: peekTo-lBXzO7A, reason: not valid java name */
    public Object mo224peekTolBXzO7A(ByteBuffer byteBuffer, long j, long j2, long j3, long j4, Continuation<? super Long> continuation) {
        return m222peekTolBXzO7A$suspendImpl(this, byteBuffer, j, j2, j3, j4, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object read(int i, Function1<? super ByteBuffer, Unit> function1, Continuation<? super Unit> continuation) {
        return read$suspendImpl(this, i, function1, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readAvailable(ChunkBuffer chunkBuffer, Continuation<? super Integer> continuation) {
        return readAvailable$suspendImpl(this, chunkBuffer, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readAvailable(ByteBuffer byteBuffer, Continuation<? super Integer> continuation) {
        return readAvailable$suspendImpl(this, byteBuffer, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readAvailable(byte[] bArr, int i, int i2, Continuation<? super Integer> continuation) {
        return readAvailable$suspendImpl(this, bArr, i, i2, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readFully(ChunkBuffer chunkBuffer, int i, Continuation<? super Unit> continuation) {
        return readFully$suspendImpl(this, chunkBuffer, i, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readPacket(int i, Continuation<? super ByteReadPacket> continuation) {
        return readPacket$suspendImpl(this, i, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readRemaining(long j, Continuation<? super ByteReadPacket> continuation) {
        return readRemaining$suspendImpl(this, j, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    @Deprecated(message = "Use read { } instead.")
    public Object readSuspendableSession(Function2<? super SuspendableReadSession, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        return readSuspendableSession$suspendImpl(this, function2, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readUTF8Line(int i, Continuation<? super String> continuation) {
        return readUTF8Line$suspendImpl(this, i, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public <A extends Appendable> Object readUTF8LineTo(A a, int i, Continuation<? super Boolean> continuation) {
        return readUTF8LineToAscii(a, i, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object write(int i, Function1<? super ByteBuffer, Unit> function1, Continuation<? super Unit> continuation) {
        return write$suspendImpl(this, i, function1, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeAvailable(ChunkBuffer chunkBuffer, Continuation<? super Integer> continuation) {
        return writeAvailable$suspendImpl(this, chunkBuffer, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeAvailable(ByteBuffer byteBuffer, Continuation<? super Integer> continuation) {
        return writeAvailable$suspendImpl(this, byteBuffer, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeAvailable(byte[] bArr, int i, int i2, Continuation<? super Integer> continuation) {
        return writeAvailable$suspendImpl(this, bArr, i, i2, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeByte(byte b, Continuation<? super Unit> continuation) {
        return writeByte$suspendImpl(this, b, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeDouble(double d, Continuation<? super Unit> continuation) {
        return writeDouble$suspendImpl(this, d, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeFloat(float f, Continuation<? super Unit> continuation) {
        return writeFloat$suspendImpl(this, f, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeFully(Buffer buffer, Continuation<? super Unit> continuation) {
        return writeFully$suspendImpl(this, buffer, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeFully(ByteBuffer byteBuffer, Continuation<? super Unit> continuation) {
        return writeFully$suspendImpl(this, byteBuffer, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeFully(byte[] bArr, int i, int i2, Continuation<? super Unit> continuation) {
        return writeFully$suspendImpl(this, bArr, i, i2, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    /* renamed from: writeFully-JT6ljtQ, reason: not valid java name */
    public Object mo225writeFullyJT6ljtQ(ByteBuffer byteBuffer, int i, int i2, Continuation<? super Unit> continuation) {
        return m223writeFullyJT6ljtQ$suspendImpl(this, byteBuffer, i, i2, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeInt(int i, Continuation<? super Unit> continuation) {
        return writeInt$suspendImpl(this, i, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeLong(long j, Continuation<? super Unit> continuation) {
        return writeLong$suspendImpl(this, j, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writePacket(ByteReadPacket byteReadPacket, Continuation<? super Unit> continuation) {
        return writePacket$suspendImpl(this, byteReadPacket, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeShort(short s, Continuation<? super Unit> continuation) {
        return writeShort$suspendImpl(this, s, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    @Deprecated(message = "Use write { } instead.")
    public Object writeSuspendSession(Function2<? super WriterSuspendSession, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        return writeSuspendSession$suspendImpl(this, function2, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeWhile(Function1<? super ByteBuffer, Boolean> function1, Continuation<? super Unit> continuation) {
        return writeWhile$suspendImpl(this, function1, continuation);
    }

    public ByteBufferChannel(boolean autoFlush, ObjectPool<ReadWriteBufferState.Initial> pool, int reservedSize) {
        Intrinsics.checkNotNullParameter(pool, "pool");
        this.autoFlush = autoFlush;
        this.pool = pool;
        this.reservedSize = reservedSize;
        this._state = ReadWriteBufferState.IdleEmpty.INSTANCE;
        this._closed = null;
        this._readOp = null;
        this._writeOp = null;
        this.readSession = new ReadSessionImpl(this);
        this.writeSession = new WriteSessionImpl(this);
        this.readSuspendContinuationCache = new CancellableReusableContinuation<>();
        this.writeSuspendContinuationCache = new CancellableReusableContinuation<>();
        this.writeSuspension = new Function1<Continuation<? super Unit>, Object>() { // from class: io.ktor.utils.io.ByteBufferChannel$writeSuspension$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Continuation<? super Unit> ucont) {
                int size;
                ClosedElement closed;
                boolean writeSuspendPredicate;
                boolean shouldResumeReadOp;
                Continuation current$iv;
                boolean z;
                boolean writeSuspendPredicate2;
                boolean writeSuspendPredicate3;
                Throwable it;
                Intrinsics.checkNotNullParameter(ucont, "ucont");
                size = ByteBufferChannel.this.writeSuspensionSize;
                while (true) {
                    closed = ByteBufferChannel.this.getClosed();
                    if (closed != null && (it = closed.getSendException()) != null) {
                        ByteBufferChannelKt.rethrowClosed(it);
                        throw new KotlinNothingValueException();
                    }
                    writeSuspendPredicate = ByteBufferChannel.this.writeSuspendPredicate(size);
                    if (!writeSuspendPredicate) {
                        Result.Companion companion = Result.INSTANCE;
                        ucont.resumeWith(Result.m510constructorimpl(Unit.INSTANCE));
                        break;
                    }
                    ByteBufferChannel byteBufferChannel = ByteBufferChannel.this;
                    ByteBufferChannel byteBufferChannel2 = ByteBufferChannel.this;
                    ByteBufferChannel updater$iv = ByteBufferChannel.this;
                    Continuation continuation$iv = IntrinsicsKt.intercepted(ucont);
                    ByteBufferChannel byteBufferChannel3 = ByteBufferChannel.this;
                    while (true) {
                        current$iv = byteBufferChannel2.getWriteOp();
                        z = true;
                        if (!(current$iv == null)) {
                            throw new IllegalStateException("Operation is already in progress".toString());
                        }
                        writeSuspendPredicate2 = byteBufferChannel3.writeSuspendPredicate(size);
                        if (!writeSuspendPredicate2) {
                            z = false;
                            break;
                        }
                        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(ByteBufferChannel._writeOp$FU, updater$iv, null, continuation$iv)) {
                            writeSuspendPredicate3 = byteBufferChannel3.writeSuspendPredicate(size);
                            if (!writeSuspendPredicate3 && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(ByteBufferChannel._writeOp$FU, updater$iv, continuation$iv, null)) {
                                z = false;
                            }
                        }
                    }
                    if (z) {
                        break;
                    }
                }
                ByteBufferChannel.this.flushImpl(size);
                shouldResumeReadOp = ByteBufferChannel.this.shouldResumeReadOp();
                if (shouldResumeReadOp) {
                    ByteBufferChannel.this.resumeReadOp();
                }
                return IntrinsicsKt.getCOROUTINE_SUSPENDED();
            }
        };
    }

    public /* synthetic */ ByteBufferChannel(boolean z, ObjectPool objectPool, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i2 & 2) != 0 ? ObjectPoolKt.getBufferObjectPool() : objectPool, (i2 & 4) != 0 ? 8 : i);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public boolean getAutoFlush() {
        return this.autoFlush;
    }

    /* renamed from: getReservedSize$ktor_io, reason: from getter */
    public final int getReservedSize() {
        return this.reservedSize;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel(ByteBuffer content) {
        this(false, ObjectPoolKt.getBufferObjectNoPool(), 0);
        Intrinsics.checkNotNullParameter(content, "content");
        ByteBuffer slice = content.slice();
        Intrinsics.checkNotNullExpressionValue(slice, "content.slice()");
        ReadWriteBufferState.Initial $this$_init__u24lambda_u240 = new ReadWriteBufferState.Initial(slice, 0);
        $this$_init__u24lambda_u240.capacity.resetForRead();
        this._state = $this$_init__u24lambda_u240.startWriting$ktor_io();
        restoreStateAfterWrite$ktor_io();
        ByteWriteChannelKt.close(this);
        tryTerminate$ktor_io();
    }

    private final ReadWriteBufferState getState() {
        return (ReadWriteBufferState) this._state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClosedElement getClosed() {
        return (ClosedElement) this._closed;
    }

    private final void setClosed(ClosedElement value) {
        this._closed = value;
    }

    private final Continuation<Boolean> getReadOp() {
        return (Continuation) this._readOp;
    }

    private final void setReadOp(Continuation<? super Boolean> continuation) {
        this._readOp = continuation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Continuation<Unit> getWriteOp() {
        return (Continuation) this._writeOp;
    }

    private final void setWriteOp(Continuation<? super Unit> continuation) {
        this._writeOp = continuation;
    }

    public final ReadWriteBufferState currentState$ktor_io() {
        return getState();
    }

    /* renamed from: getJoining$ktor_io, reason: from getter */
    public final JoiningState getJoining() {
        return this.joining;
    }

    @Override // io.ktor.utils.io.ByteChannel
    public void attachJob(Job job) {
        Intrinsics.checkNotNullParameter(job, "job");
        Job job2 = this.attachedJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        this.attachedJob = job;
        Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new Function1<Throwable, Unit>() { // from class: io.ktor.utils.io.ByteBufferChannel$attachJob$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable cause) {
                ByteBufferChannel.this.attachedJob = null;
                if (cause == null) {
                    return;
                }
                ByteBufferChannel.this.cancel(ExceptionUtilsKt.unwrapCancellationException(cause));
            }
        }, 2, null);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    /* renamed from: getAvailableForRead */
    public int get_availableForRead() {
        RingBufferCapacity this_$iv = getState().capacity;
        return this_$iv._availableForRead$internal;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public int getAvailableForWrite() {
        RingBufferCapacity this_$iv = getState().capacity;
        return this_$iv._availableForWrite$internal;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public boolean isClosedForRead() {
        return getState() == ReadWriteBufferState.Terminated.INSTANCE && getClosed() != null;
    }

    @Override // io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel
    public boolean isClosedForWrite() {
        return getClosed() != null;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    /* renamed from: getTotalBytesRead, reason: from getter */
    public long get_totalBytesRead() {
        return this.totalBytesRead;
    }

    public void setTotalBytesRead$ktor_io(long j) {
        this.totalBytesRead = j;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    /* renamed from: getTotalBytesWritten, reason: from getter */
    public long get_totalBytesWritten() {
        return this.totalBytesWritten;
    }

    public void setTotalBytesWritten$ktor_io(long j) {
        this.totalBytesWritten = j;
    }

    @Override // io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel
    public Throwable getClosedCause() {
        ClosedElement closed = getClosed();
        if (closed != null) {
            return closed.getCause();
        }
        return null;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public boolean close(Throwable cause) {
        ClosedElement newClosed;
        JoiningState it;
        if (getClosed() != null) {
            return false;
        }
        if (cause == null) {
            newClosed = ClosedElement.INSTANCE.getEmptyCause();
        } else {
            newClosed = new ClosedElement(cause);
        }
        getState().capacity.flush();
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_closed$FU, this, null, newClosed)) {
            return false;
        }
        getState().capacity.flush();
        if (getState().capacity.isEmpty() || cause != null) {
            tryTerminate$ktor_io();
        }
        resumeClosed(cause);
        if (getState() == ReadWriteBufferState.Terminated.INSTANCE && (it = this.joining) != null) {
            ensureClosedJoined(it);
        }
        if (cause != null) {
            Job job = this.attachedJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.readSuspendContinuationCache.close(cause);
            this.writeSuspendContinuationCache.close(cause);
            return true;
        }
        this.writeSuspendContinuationCache.close(new ClosedWriteChannelException(ByteBufferChannelKt.DEFAULT_CLOSE_MESSAGE));
        this.readSuspendContinuationCache.close((CancellableReusableContinuation<Boolean>) Boolean.valueOf(getState().capacity.flush()));
        return true;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public boolean cancel(Throwable cause) {
        return close(cause == null ? new CancellationException("Channel has been cancelled") : cause);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void flushImpl(int minWriteSize) {
        ReadWriteBufferState currentState;
        ByteBufferChannel delegatedTo;
        JoiningState joiningState = this.joining;
        if (joiningState != null && (delegatedTo = joiningState.getDelegatedTo()) != null) {
            delegatedTo.flush();
        }
        do {
            currentState = getState();
            if (currentState == ReadWriteBufferState.Terminated.INSTANCE) {
                return;
            } else {
                currentState.capacity.flush();
            }
        } while (currentState != getState());
        RingBufferCapacity this_$iv = currentState.capacity;
        int avw = this_$iv._availableForWrite$internal;
        RingBufferCapacity this_$iv2 = currentState.capacity;
        int avr = this_$iv2._availableForRead$internal;
        if (avr >= 1) {
            resumeReadOp();
        }
        JoiningState joining = this.joining;
        if (avw >= minWriteSize) {
            if (joining == null || getState() == ReadWriteBufferState.Terminated.INSTANCE) {
                resumeWriteOp();
            }
        }
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public void flush() {
        flushImpl(1);
    }

    public final void prepareWriteBuffer$ktor_io(ByteBuffer buffer, int lockedSpace) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        prepareBuffer(buffer, this.writePosition, lockedSpace);
    }

    private final void prepareBuffer(ByteBuffer $this$prepareBuffer, int position, int available) {
        if (!(position >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(available >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int bufferLimit = $this$prepareBuffer.capacity() - this.reservedSize;
        int virtualLimit = position + available;
        $this$prepareBuffer.limit(RangesKt.coerceAtMost(virtualLimit, bufferLimit));
        $this$prepareBuffer.position(position);
    }

    public final ByteBuffer setupStateForWrite$ktor_io() {
        ReadWriteBufferState.Initial initial;
        ReadWriteBufferState newState;
        Continuation existing = getWriteOp();
        if (existing != null) {
            throw new IllegalStateException("Write operation is already in progress: " + existing);
        }
        ReadWriteBufferState.Initial allocated = null;
        while (true) {
            Object cur$iv = this._state;
            ReadWriteBufferState state = (ReadWriteBufferState) cur$iv;
            ReadWriteBufferState readWriteBufferState = null;
            if (this.joining != null) {
                if (allocated != null) {
                    ReadWriteBufferState.Initial it = allocated;
                    releaseBuffer(it);
                }
                return null;
            }
            if (getClosed() != null) {
                if (allocated != null) {
                    ReadWriteBufferState.Initial it2 = allocated;
                    releaseBuffer(it2);
                }
                ClosedElement closed = getClosed();
                Intrinsics.checkNotNull(closed);
                ByteBufferChannelKt.rethrowClosed(closed.getSendException());
                throw new KotlinNothingValueException();
            }
            if (state == ReadWriteBufferState.IdleEmpty.INSTANCE) {
                if (allocated == null) {
                    ReadWriteBufferState.Initial it3 = newBuffer();
                    initial = it3;
                    allocated = it3;
                } else {
                    initial = allocated;
                }
                Object allocatedState = allocated.startWriting$ktor_io();
                newState = (ReadWriteBufferState) allocatedState;
            } else {
                Object allocatedState2 = ReadWriteBufferState.Terminated.INSTANCE;
                if (state == allocatedState2) {
                    if (allocated != null) {
                        ReadWriteBufferState.Initial it4 = allocated;
                        releaseBuffer(it4);
                    }
                    if (this.joining != null) {
                        return null;
                    }
                    ClosedElement closed2 = getClosed();
                    Intrinsics.checkNotNull(closed2);
                    ByteBufferChannelKt.rethrowClosed(closed2.getSendException());
                    throw new KotlinNothingValueException();
                }
                initial = allocated;
                newState = state.startWriting$ktor_io();
            }
            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, cur$iv, newState)) {
                if (getClosed() != null) {
                    restoreStateAfterWrite$ktor_io();
                    tryTerminate$ktor_io();
                    ClosedElement closed3 = getClosed();
                    Intrinsics.checkNotNull(closed3);
                    ByteBufferChannelKt.rethrowClosed(closed3.getSendException());
                    throw new KotlinNothingValueException();
                }
                ByteBuffer buffer = newState.getWriteBuffer();
                if (initial != null) {
                    ReadWriteBufferState.Initial allocated2 = initial;
                    if (state == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("old");
                    } else {
                        readWriteBufferState = state;
                    }
                    if (readWriteBufferState != ReadWriteBufferState.IdleEmpty.INSTANCE) {
                        releaseBuffer(allocated2);
                    }
                }
                int i = this.writePosition;
                RingBufferCapacity this_$iv = newState.capacity;
                prepareBuffer(buffer, i, this_$iv._availableForWrite$internal);
                return buffer;
            }
            allocated = initial;
        }
    }

    public final void restoreStateAfterWrite$ktor_io() {
        Object cur$iv;
        Object upd$iv;
        ReadWriteBufferState.IdleNonEmpty it;
        Object toRelease = null;
        do {
            cur$iv = this._state;
            ReadWriteBufferState writeStopped = ((ReadWriteBufferState) cur$iv).stopWriting$ktor_io();
            if ((writeStopped instanceof ReadWriteBufferState.IdleNonEmpty) && writeStopped.capacity.isEmpty()) {
                toRelease = writeStopped;
                upd$iv = (ReadWriteBufferState) ReadWriteBufferState.IdleEmpty.INSTANCE;
            } else {
                upd$iv = writeStopped;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, cur$iv, upd$iv));
        if (upd$iv == ReadWriteBufferState.IdleEmpty.INSTANCE && (it = (ReadWriteBufferState.IdleNonEmpty) toRelease) != null) {
            releaseBuffer(it.getInitial());
        }
    }

    private final ByteBuffer setupStateForRead() {
        Object cur$iv;
        Throwable cause;
        ReadWriteBufferState newState;
        Throwable it;
        do {
            cur$iv = this._state;
            ReadWriteBufferState state = (ReadWriteBufferState) cur$iv;
            if (Intrinsics.areEqual(state, ReadWriteBufferState.Terminated.INSTANCE) ? true : Intrinsics.areEqual(state, ReadWriteBufferState.IdleEmpty.INSTANCE)) {
                ClosedElement closed = getClosed();
                if (closed == null || (cause = closed.getCause()) == null) {
                    return null;
                }
                ByteBufferChannelKt.rethrowClosed(cause);
                throw new KotlinNothingValueException();
            }
            ClosedElement closed2 = getClosed();
            if (closed2 != null && (it = closed2.getCause()) != null) {
                ByteBufferChannelKt.rethrowClosed(it);
                throw new KotlinNothingValueException();
            }
            RingBufferCapacity this_$iv = state.capacity;
            if (this_$iv._availableForRead$internal == 0) {
                return null;
            }
            newState = state.startReading$ktor_io();
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, cur$iv, newState));
        ByteBuffer $this$setupStateForRead_u24lambda_u2414 = newState.getReadBuffer();
        int i = this.readPosition;
        RingBufferCapacity this_$iv2 = newState.capacity;
        prepareBuffer($this$setupStateForRead_u24lambda_u2414, i, this_$iv2._availableForRead$internal);
        return $this$setupStateForRead_u24lambda_u2414;
    }

    private final void restoreStateAfterRead() {
        Object cur$iv;
        ReadWriteBufferState.IdleEmpty newState;
        Object toRelease = null;
        do {
            cur$iv = this._state;
            ReadWriteBufferState state = (ReadWriteBufferState) cur$iv;
            ReadWriteBufferState.IdleNonEmpty it = (ReadWriteBufferState.IdleNonEmpty) toRelease;
            if (it != null) {
                it.capacity.resetForWrite();
                resumeWriteOp();
                toRelease = null;
            }
            ReadWriteBufferState readStopped = state.stopReading$ktor_io();
            if ((readStopped instanceof ReadWriteBufferState.IdleNonEmpty) && getState() == state && readStopped.capacity.tryLockForRelease()) {
                toRelease = readStopped;
                newState = ReadWriteBufferState.IdleEmpty.INSTANCE;
            } else {
                newState = readStopped;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, cur$iv, newState));
        if (newState == ReadWriteBufferState.IdleEmpty.INSTANCE) {
            ReadWriteBufferState.IdleNonEmpty it2 = (ReadWriteBufferState.IdleNonEmpty) toRelease;
            if (it2 != null) {
                releaseBuffer(it2.getInitial());
            }
            resumeWriteOp();
            return;
        }
        if ((newState instanceof ReadWriteBufferState.IdleNonEmpty) && newState.capacity.isEmpty() && newState.capacity.tryLockForRelease() && AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, newState, ReadWriteBufferState.IdleEmpty.INSTANCE)) {
            newState.capacity.resetForWrite();
            releaseBuffer(((ReadWriteBufferState.IdleNonEmpty) newState).getInitial());
            resumeWriteOp();
        }
    }

    private final JoiningState setupDelegateTo(ByteBufferChannel delegate, boolean delegateClose) {
        if (!(this != delegate)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        JoiningState joined = new JoiningState(delegate, delegateClose);
        this.joining = joined;
        ClosedElement alreadyClosed = getClosed();
        if (alreadyClosed == null) {
            flush();
            return joined;
        }
        if (alreadyClosed.getCause() != null) {
            delegate.close(alreadyClosed.getCause());
        } else if (delegateClose && getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            ByteWriteChannelKt.close(delegate);
        } else {
            delegate.flush();
        }
        return joined;
    }

    private final boolean tryCompleteJoining(JoiningState joined) {
        if (!tryReleaseBuffer(true)) {
            return false;
        }
        ensureClosedJoined(joined);
        Continuation continuation = (Continuation) _readOp$FU.getAndSet(this, null);
        if (continuation != null) {
            Result.Companion companion = Result.INSTANCE;
            continuation.resumeWith(Result.m510constructorimpl(ResultKt.createFailure(new IllegalStateException("Joining is in progress"))));
        }
        resumeWriteOp();
        return true;
    }

    public final boolean tryTerminate$ktor_io() {
        if (getClosed() == null || !tryReleaseBuffer(false)) {
            return false;
        }
        JoiningState it = this.joining;
        if (it != null) {
            ensureClosedJoined(it);
        }
        resumeReadOp();
        resumeWriteOp();
        return true;
    }

    private final boolean tryReleaseBuffer(boolean forceTermination) {
        Object cur$iv;
        Object upd$iv;
        ReadWriteBufferState.Initial initial = null;
        do {
            cur$iv = this._state;
            ReadWriteBufferState state = (ReadWriteBufferState) cur$iv;
            ClosedElement closed = getClosed();
            if (initial != null) {
                ReadWriteBufferState.Initial buffer = initial;
                initial = null;
                if ((closed != null ? closed.getCause() : null) == null) {
                    buffer.capacity.resetForWrite();
                }
                resumeWriteOp();
            }
            if (state == ReadWriteBufferState.Terminated.INSTANCE) {
                return true;
            }
            if (state == ReadWriteBufferState.IdleEmpty.INSTANCE) {
                upd$iv = (ReadWriteBufferState) ReadWriteBufferState.Terminated.INSTANCE;
            } else if (closed != null && (state instanceof ReadWriteBufferState.IdleNonEmpty) && (state.capacity.tryLockForRelease() || closed.getCause() != null)) {
                if (closed.getCause() != null) {
                    state.capacity.forceLockForRelease();
                }
                initial = ((ReadWriteBufferState.IdleNonEmpty) state).getInitial();
                upd$iv = (ReadWriteBufferState) ReadWriteBufferState.Terminated.INSTANCE;
            } else if (forceTermination && (state instanceof ReadWriteBufferState.IdleNonEmpty) && state.capacity.tryLockForRelease()) {
                initial = ((ReadWriteBufferState.IdleNonEmpty) state).getInitial();
                upd$iv = (ReadWriteBufferState) ReadWriteBufferState.Terminated.INSTANCE;
            } else {
                return false;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_state$FU, this, cur$iv, upd$iv));
        if (initial != null) {
            ReadWriteBufferState.Initial buffer2 = initial;
            if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
                releaseBuffer(buffer2);
            }
        }
        return true;
    }

    private final int carryIndex(ByteBuffer $this$carryIndex, int idx) {
        return idx >= $this$carryIndex.capacity() - this.reservedSize ? idx - ($this$carryIndex.capacity() - this.reservedSize) : idx;
    }

    private final void writing(Function3<? super ByteBufferChannel, ? super ByteBuffer, ? super RingBufferCapacity, Unit> block) {
        ByteBufferChannel current;
        JoiningState it = this.joining;
        if (it == null || (current = resolveDelegation(this, it)) == null) {
            current = this;
        }
        ByteBuffer buffer = current.setupStateForWrite$ktor_io();
        if (buffer == null) {
            return;
        }
        RingBufferCapacity capacity = current.getState().capacity;
        long before = current.get_totalBytesWritten();
        try {
            ClosedElement it2 = current.getClosed();
            if (it2 != null) {
                ByteBufferChannelKt.rethrowClosed(it2.getSendException());
                throw new KotlinNothingValueException();
            }
            block.invoke(current, buffer, capacity);
        } finally {
            if (capacity.isFull() || current.getAutoFlush()) {
                current.flush();
            }
            if (current != this) {
                setTotalBytesWritten$ktor_io(get_totalBytesWritten() + (current.get_totalBytesWritten() - before));
            }
            current.restoreStateAfterWrite$ktor_io();
            current.tryTerminate$ktor_io();
        }
    }

    private final boolean reading(Function2<? super ByteBuffer, ? super RingBufferCapacity, Boolean> block) {
        ByteBuffer buffer = setupStateForRead();
        if (buffer == null) {
            return false;
        }
        RingBufferCapacity capacity = getState().capacity;
        try {
            if (capacity._availableForRead$internal == 0) {
                return false;
            }
            return block.invoke(buffer, capacity).booleanValue();
        } finally {
            restoreStateAfterRead();
            tryTerminate$ktor_io();
        }
    }

    private final int readAsMuchAsPossible(ByteBuffer dst) {
        int consumed = 0;
        ByteBuffer buffer$iv = setupStateForRead();
        if (buffer$iv != null) {
            RingBufferCapacity capacity$iv = getState().capacity;
            try {
                if (capacity$iv._availableForRead$internal != 0) {
                    int bufferLimit = buffer$iv.capacity() - this.reservedSize;
                    while (true) {
                        int dstRemaining = dst.remaining();
                        if (dstRemaining == 0) {
                            break;
                        }
                        int position = this.readPosition;
                        int bufferRemaining = bufferLimit - position;
                        int part = capacity$iv.tryReadAtMost(Math.min(bufferRemaining, dstRemaining));
                        if (part == 0) {
                            break;
                        }
                        buffer$iv.limit(position + part);
                        buffer$iv.position(position);
                        try {
                            dst.put(buffer$iv);
                            bytesRead(buffer$iv, capacity$iv, part);
                            consumed += part;
                        } catch (Throwable th) {
                            th = th;
                            restoreStateAfterRead();
                            tryTerminate$ktor_io();
                            throw th;
                        }
                    }
                    restoreStateAfterRead();
                    tryTerminate$ktor_io();
                    return consumed;
                }
                restoreStateAfterRead();
                tryTerminate$ktor_io();
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return consumed;
    }

    static /* synthetic */ int readAsMuchAsPossible$default(ByteBufferChannel byteBufferChannel, Buffer this_$iv, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readAsMuchAsPossible");
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = this_$iv.getLimit() - this_$iv.getWritePosition();
        }
        return byteBufferChannel.readAsMuchAsPossible(this_$iv, i, i2);
    }

    private final int readAsMuchAsPossible(Buffer dst, int consumed, int max) {
        ByteBufferChannel byteBufferChannel;
        ByteBuffer $this$readAsMuchAsPossible_u24lambda_u2426;
        int currentMax = max;
        int currentConsumed = consumed;
        do {
            int part = 0;
            ByteBuffer buffer$iv = setupStateForRead();
            if (buffer$iv == null) {
                byteBufferChannel = this;
                $this$readAsMuchAsPossible_u24lambda_u2426 = null;
            } else {
                RingBufferCapacity capacity$iv = getState().capacity;
                try {
                    if (capacity$iv._availableForRead$internal != 0) {
                        int dstSize = dst.getLimit() - dst.getWritePosition();
                        part = capacity$iv.tryReadAtMost(Math.min(buffer$iv.remaining(), Math.min(dstSize, currentMax)));
                        if (part <= 0) {
                            byteBufferChannel = this;
                            $this$readAsMuchAsPossible_u24lambda_u2426 = null;
                        } else {
                            if (dstSize < buffer$iv.remaining()) {
                                buffer$iv.limit(buffer$iv.position() + dstSize);
                            }
                            try {
                                BufferPrimitivesJvmKt.writeFully(dst, buffer$iv);
                                byteBufferChannel = this;
                                try {
                                    byteBufferChannel.bytesRead(buffer$iv, capacity$iv, part);
                                    $this$readAsMuchAsPossible_u24lambda_u2426 = 1;
                                } catch (Throwable th) {
                                    th = th;
                                    restoreStateAfterRead();
                                    tryTerminate$ktor_io();
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                        restoreStateAfterRead();
                        tryTerminate$ktor_io();
                    } else {
                        restoreStateAfterRead();
                        tryTerminate$ktor_io();
                        byteBufferChannel = this;
                        $this$readAsMuchAsPossible_u24lambda_u2426 = null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            currentConsumed += part;
            currentMax -= part;
            if ($this$readAsMuchAsPossible_u24lambda_u2426 != null) {
                if (!(dst.getLimit() > dst.getWritePosition())) {
                    break;
                }
            } else {
                break;
            }
        } while (byteBufferChannel.getState().capacity._availableForRead$internal > 0);
        return currentConsumed;
    }

    private final int readAsMuchAsPossible(byte[] dst, int offset, int length) {
        int consumed;
        int consumed2 = 0;
        ByteBuffer buffer$iv = setupStateForRead();
        if (buffer$iv != null) {
            RingBufferCapacity capacity$iv = getState().capacity;
            try {
                if (capacity$iv._availableForRead$internal != 0) {
                    int bufferLimit = buffer$iv.capacity() - this.reservedSize;
                    while (true) {
                        int lengthRemaining = length - consumed2;
                        if (lengthRemaining == 0) {
                            consumed = consumed2;
                            break;
                        }
                        try {
                            int position = this.readPosition;
                            int bufferRemaining = bufferLimit - position;
                            int part = capacity$iv.tryReadAtMost(Math.min(bufferRemaining, lengthRemaining));
                            if (part == 0) {
                                consumed = consumed2;
                                break;
                            }
                            buffer$iv.limit(position + part);
                            buffer$iv.position(position);
                            int consumed3 = consumed2;
                            try {
                                buffer$iv.get(dst, offset + consumed2, part);
                                bytesRead(buffer$iv, capacity$iv, part);
                                consumed2 = consumed3 + part;
                            } catch (Throwable th) {
                                th = th;
                                restoreStateAfterRead();
                                tryTerminate$ktor_io();
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    restoreStateAfterRead();
                    tryTerminate$ktor_io();
                    return consumed;
                }
                restoreStateAfterRead();
                tryTerminate$ktor_io();
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return 0;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public final Object readFully(byte[] dst, int offset, int length, Continuation<? super Unit> continuation) {
        Object readFullySuspend;
        int consumed = readAsMuchAsPossible(dst, offset, length);
        return (consumed >= length || (readFullySuspend = readFullySuspend(dst, offset + consumed, length - consumed, continuation)) != IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : readFullySuspend;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public final Object readFully(ByteBuffer dst, Continuation<? super Integer> continuation) {
        int rc = readAsMuchAsPossible(dst);
        return !dst.hasRemaining() ? Boxing.boxInt(rc) : readFullySuspend(dst, rc, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x005b -> B:12:0x0061). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readFullySuspend(java.nio.ByteBuffer r7, int r8, kotlin.coroutines.Continuation<? super java.lang.Integer> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel$readFullySuspend$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readFullySuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readFullySuspend$1
            r0.<init>(r6, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3e;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$1
            java.nio.ByteBuffer r8 = (java.nio.ByteBuffer) r8
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r9
            goto L61
        L3e:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r6
            r3 = r8
            r8 = r7
            r7 = r3
        L45:
            boolean r3 = r8.hasRemaining()
            if (r3 == 0) goto L96
            r0.L$0 = r2
            r0.L$1 = r8
            r0.I$0 = r7
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.readSuspend(r3, r0)
            if (r3 != r1) goto L5b
            return r1
        L5b:
            r5 = r0
            r0 = r9
            r9 = r3
            r3 = r2
            r2 = r1
            r1 = r5
        L61:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L73
            int r9 = r3.readAsMuchAsPossible(r8)
            int r7 = r7 + r9
            r9 = r0
            r0 = r1
            r1 = r2
            r2 = r3
            goto L45
        L73:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r9 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Unexpected EOF: expected "
            java.lang.StringBuilder r2 = r2.append(r4)
            int r4 = r8.remaining()
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r4 = " more bytes"
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            r9.<init>(r2)
            throw r9
        L96:
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readFullySuspend(java.nio.ByteBuffer, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readFully$suspendImpl(ByteBufferChannel $this, ChunkBuffer dst, int n, Continuation<? super Unit> continuation) {
        int rc = readAsMuchAsPossible$default($this, dst, 0, n, 2, null);
        if (rc == n) {
            return Unit.INSTANCE;
        }
        Object readFullySuspend = $this.readFullySuspend(dst, n - rc, continuation);
        return readFullySuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? readFullySuspend : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0071 -> B:12:0x0078). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readFullySuspend(io.ktor.utils.io.core.internal.ChunkBuffer r12, int r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof io.ktor.utils.io.ByteBufferChannel$readFullySuspend$2
            if (r0 == 0) goto L14
            r0 = r14
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$2 r0 = (io.ktor.utils.io.ByteBufferChannel$readFullySuspend$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$2 r0 = new io.ktor.utils.io.ByteBufferChannel$readFullySuspend$2
            r0.<init>(r11, r14)
        L19:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L41;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L2c:
            int r12 = r0.I$1
            int r13 = r0.I$0
            java.lang.Object r2 = r0.L$1
            io.ktor.utils.io.core.internal.ChunkBuffer r2 = (io.ktor.utils.io.core.internal.ChunkBuffer) r2
            java.lang.Object r3 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r3 = (io.ktor.utils.io.ByteBufferChannel) r3
            kotlin.ResultKt.throwOnFailure(r14)
            r4 = r3
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r14
            goto L78
        L41:
            kotlin.ResultKt.throwOnFailure(r14)
            r2 = r11
            r3 = 0
            r10 = r2
            r2 = r12
            r12 = r3
            r3 = r10
        L4a:
            r4 = r2
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4
            r5 = 0
            int r6 = r4.getLimit()
            int r7 = r4.getWritePosition()
            r8 = 1
            if (r6 <= r7) goto L5b
            r4 = r8
            goto L5c
        L5b:
            r4 = 0
        L5c:
            if (r4 == 0) goto Lb4
            if (r12 >= r13) goto Lb4
            r0.L$0 = r3
            r0.L$1 = r2
            r0.I$0 = r13
            r0.I$1 = r12
            r0.label = r8
            java.lang.Object r4 = r3.readSuspend(r8, r0)
            if (r4 != r1) goto L71
            return r1
        L71:
            r10 = r0
            r0 = r14
            r14 = r4
            r4 = r3
            r3 = r2
            r2 = r1
            r1 = r10
        L78:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L93
            r5 = r3
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5
            int r7 = r13 - r12
            r8 = 2
            r9 = 0
            r6 = 0
            int r14 = readAsMuchAsPossible$default(r4, r5, r6, r7, r8, r9)
            int r12 = r12 + r14
            r14 = r0
            r0 = r1
            r1 = r2
            r2 = r3
            r3 = r4
            goto L4a
        L93:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r14 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unexpected EOF: expected "
            java.lang.StringBuilder r2 = r2.append(r3)
            int r3 = r13 - r12
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = " more bytes"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r14.<init>(r2)
            throw r14
        Lb4:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readFullySuspend(io.ktor.utils.io.core.internal.ChunkBuffer, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0064 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0065 -> B:12:0x006c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readFullySuspend(byte[] r8, int r9, int r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r7 = this;
            boolean r0 = r11 instanceof io.ktor.utils.io.ByteBufferChannel$readFullySuspend$3
            if (r0 == 0) goto L14
            r0 = r11
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel$readFullySuspend$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$readFullySuspend$3
            r0.<init>(r7, r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L43;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2c:
            int r8 = r0.I$2
            int r9 = r0.I$1
            int r10 = r0.I$0
            java.lang.Object r2 = r0.L$1
            byte[] r2 = (byte[]) r2
            java.lang.Object r3 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r3 = (io.ktor.utils.io.ByteBufferChannel) r3
            kotlin.ResultKt.throwOnFailure(r11)
            r4 = r3
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r11
            goto L6c
        L43:
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = r7
            r3 = 0
            r6 = r2
            r2 = r8
            r8 = r3
            r3 = r6
            r6 = r10
            r10 = r9
            r9 = r6
        L51:
            r0.L$0 = r3
            r0.L$1 = r2
            r0.I$0 = r10
            r0.I$1 = r9
            r0.I$2 = r8
            r4 = 1
            r0.label = r4
            java.lang.Object r4 = r3.readSuspend(r4, r0)
            if (r4 != r1) goto L65
            return r1
        L65:
            r6 = r0
            r0 = r11
            r11 = r4
            r4 = r3
            r3 = r2
            r2 = r1
            r1 = r6
        L6c:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L85
            int r10 = r10 + r8
            int r9 = r9 - r8
            int r8 = r4.readAsMuchAsPossible(r3, r10, r9)
            if (r8 < r9) goto L7f
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L7f:
            r11 = r0
            r0 = r1
            r1 = r2
            r2 = r3
            r3 = r4
            goto L51
        L85:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r11 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "Unexpected EOF: expected "
            java.lang.StringBuilder r2 = r2.append(r5)
            java.lang.StringBuilder r2 = r2.append(r9)
            java.lang.String r5 = " more bytes"
            java.lang.StringBuilder r2 = r2.append(r5)
            java.lang.String r2 = r2.toString()
            r11.<init>(r2)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readFullySuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public int readAvailable(int min, Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        boolean z = true;
        if (!(min > 0)) {
            throw new IllegalArgumentException("min should be positive".toString());
        }
        if (!(min <= 4088)) {
            throw new IllegalArgumentException(("Min(" + min + ") shouldn't be greater than 4088").toString());
        }
        int result = 0;
        ByteBuffer buffer$iv = setupStateForRead();
        if (buffer$iv != null) {
            RingBufferCapacity capacity$iv = getState().capacity;
            try {
                if (capacity$iv._availableForRead$internal == 0) {
                    restoreStateAfterRead();
                    tryTerminate$ktor_io();
                } else {
                    int locked = capacity$iv.tryReadAtLeast(min);
                    if (locked > 0 && locked >= min) {
                        int position = buffer$iv.position();
                        int limit = buffer$iv.limit();
                        block.invoke(buffer$iv);
                        try {
                            if (!(limit == buffer$iv.limit())) {
                                throw new IllegalStateException("Buffer limit shouldn't be modified.".toString());
                            }
                            result = buffer$iv.position() - position;
                            read = result >= 0;
                            if (!read) {
                                throw new IllegalStateException("Position shouldn't been moved backwards.".toString());
                            }
                            bytesRead(buffer$iv, capacity$iv, result);
                            if (result < locked) {
                                capacity$iv.completeWrite(locked - result);
                                capacity$iv.flush();
                            }
                            restoreStateAfterRead();
                            tryTerminate$ktor_io();
                            read = z;
                        } catch (Throwable th) {
                            th = th;
                            restoreStateAfterRead();
                            tryTerminate$ktor_io();
                            throw th;
                        }
                    }
                    z = false;
                    restoreStateAfterRead();
                    tryTerminate$ktor_io();
                    read = z;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        if (read) {
            return result;
        }
        return -1;
    }

    static /* synthetic */ Object readAvailable$suspendImpl(ByteBufferChannel $this, byte[] dst, int offset, int length, Continuation<? super Integer> continuation) {
        int i;
        int consumed = $this.readAsMuchAsPossible(dst, offset, length);
        if (consumed == 0 && $this.getClosed() != null) {
            if ($this.getState().capacity.flush()) {
                i = $this.readAsMuchAsPossible(dst, offset, length);
            } else {
                i = -1;
            }
        } else if (consumed > 0 || length == 0) {
            i = consumed;
        } else {
            return $this.readAvailableSuspend(dst, offset, length, continuation);
        }
        return Boxing.boxInt(i);
    }

    static /* synthetic */ Object readAvailable$suspendImpl(ByteBufferChannel $this, ByteBuffer dst, Continuation<? super Integer> continuation) {
        int i;
        int consumed = $this.readAsMuchAsPossible(dst);
        if (consumed == 0 && $this.getClosed() != null) {
            if ($this.getState().capacity.flush()) {
                i = $this.readAsMuchAsPossible(dst);
            } else {
                i = -1;
            }
        } else if (consumed > 0 || !dst.hasRemaining()) {
            i = consumed;
        } else {
            return $this.readAvailableSuspend(dst, continuation);
        }
        return Boxing.boxInt(i);
    }

    static /* synthetic */ Object readAvailable$suspendImpl(ByteBufferChannel $this, ChunkBuffer dst, Continuation<? super Integer> continuation) {
        int i;
        int consumed = readAsMuchAsPossible$default($this, dst, 0, 0, 6, null);
        if (consumed == 0 && $this.getClosed() != null) {
            if ($this.getState().capacity.flush()) {
                i = readAsMuchAsPossible$default($this, dst, 0, 0, 6, null);
            } else {
                i = -1;
            }
        } else {
            if (consumed <= 0) {
                ChunkBuffer $this$canWrite$iv = dst;
                if ($this$canWrite$iv.getLimit() > $this$canWrite$iv.getWritePosition()) {
                    return $this.readAvailableSuspend(dst, continuation);
                }
            }
            i = consumed;
        }
        return Boxing.boxInt(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readAvailableSuspend(byte[] r6, int r7, int r8, kotlin.coroutines.Continuation<? super java.lang.Integer> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1
            r0.<init>(r5, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L42;
                case 1: goto L31;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2c:
            kotlin.ResultKt.throwOnFailure(r9)
            r6 = r9
            goto L78
        L31:
            int r6 = r0.I$1
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$1
            byte[] r8 = (byte[]) r8
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)
            r3 = r9
            goto L5b
        L42:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r5
            r0.L$0 = r2
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.readSuspend(r3, r0)
            if (r3 != r1) goto L58
            return r1
        L58:
            r4 = r8
            r8 = r6
            r6 = r4
        L5b:
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 != 0) goto L69
            r6 = -1
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r6
        L69:
            r3 = 0
            r0.L$0 = r3
            r0.L$1 = r3
            r3 = 2
            r0.label = r3
            java.lang.Object r6 = r2.readAvailable(r8, r7, r6, r0)
            if (r6 != r1) goto L78
            return r1
        L78:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readAvailableSuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readAvailableSuspend(java.nio.ByteBuffer r5, kotlin.coroutines.Continuation<? super java.lang.Integer> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$2
            if (r0 == 0) goto L14
            r0 = r6
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$2 r0 = (io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$2 r0 = new io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$2
            r0.<init>(r4, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3e;
                case 1: goto L31;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2c:
            kotlin.ResultKt.throwOnFailure(r6)
            r5 = r6
            goto L6d
        L31:
            java.lang.Object r5 = r0.L$1
            java.nio.ByteBuffer r5 = (java.nio.ByteBuffer) r5
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r6)
            r3 = r6
            goto L50
        L3e:
            kotlin.ResultKt.throwOnFailure(r6)
            r2 = r4
            r0.L$0 = r2
            r0.L$1 = r5
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.readSuspend(r3, r0)
            if (r3 != r1) goto L50
            return r1
        L50:
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 != 0) goto L5e
            r5 = -1
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            return r5
        L5e:
            r3 = 0
            r0.L$0 = r3
            r0.L$1 = r3
            r3 = 2
            r0.label = r3
            java.lang.Object r5 = r2.readAvailable(r5, r0)
            if (r5 != r1) goto L6d
            return r1
        L6d:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readAvailableSuspend(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer r5, kotlin.coroutines.Continuation<? super java.lang.Integer> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3
            if (r0 == 0) goto L14
            r0 = r6
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3
            r0.<init>(r4, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3e;
                case 1: goto L31;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2c:
            kotlin.ResultKt.throwOnFailure(r6)
            r5 = r6
            goto L6d
        L31:
            java.lang.Object r5 = r0.L$1
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = (io.ktor.utils.io.core.internal.ChunkBuffer) r5
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r6)
            r3 = r6
            goto L50
        L3e:
            kotlin.ResultKt.throwOnFailure(r6)
            r2 = r4
            r0.L$0 = r2
            r0.L$1 = r5
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.readSuspend(r3, r0)
            if (r3 != r1) goto L50
            return r1
        L50:
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 != 0) goto L5e
            r5 = -1
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            return r5
        L5e:
            r3 = 0
            r0.L$0 = r3
            r0.L$1 = r3
            r3 = 2
            r0.label = r3
            java.lang.Object r5 = r2.readAvailable(r5, r0)
            if (r5 != r1) goto L6d
            return r1
        L6d:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object readPacket$suspendImpl(ByteBufferChannel byteBufferChannel, int i, Continuation<? super ByteReadPacket> continuation) {
        Throwable cause;
        ClosedElement closed = byteBufferChannel.getClosed();
        if (closed != null && (cause = closed.getCause()) != null) {
            ByteBufferChannelKt.rethrowClosed(cause);
            throw new KotlinNothingValueException();
        }
        if (i == 0) {
            return ByteReadPacket.INSTANCE.getEmpty();
        }
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        ByteBuffer borrow = ObjectPoolKt.getBufferPool().borrow();
        int i2 = i;
        while (i2 > 0) {
            try {
                borrow.clear();
                if (borrow.remaining() > i2) {
                    borrow.limit(i2);
                }
                int readAsMuchAsPossible = byteBufferChannel.readAsMuchAsPossible(borrow);
                if (readAsMuchAsPossible == 0) {
                    break;
                }
                borrow.flip();
                OutputArraysJVMKt.writeFully(bytePacketBuilder, borrow);
                i2 -= readAsMuchAsPossible;
            } catch (Throwable th) {
                ObjectPoolKt.getBufferPool().recycle(borrow);
                bytePacketBuilder.release();
                throw th;
            }
        }
        if (i2 == 0) {
            ObjectPoolKt.getBufferPool().recycle(borrow);
            return bytePacketBuilder.build();
        }
        return byteBufferChannel.readPacketSuspend(i2, bytePacketBuilder, borrow, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0050 A[Catch: all -> 0x0042, TRY_ENTER, TryCatch #0 {all -> 0x0042, blocks: (B:13:0x003a, B:19:0x0050, B:21:0x0059, B:22:0x005c, B:26:0x008d), top: B:12:0x003a }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008d A[Catch: all -> 0x0042, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0042, blocks: (B:13:0x003a, B:19:0x0050, B:21:0x0059, B:22:0x005c, B:26:0x008d), top: B:12:0x003a }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x006e -> B:15:0x0074). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readPacketSuspend(int r7, io.ktor.utils.io.core.BytePacketBuilder r8, java.nio.ByteBuffer r9, kotlin.coroutines.Continuation<? super io.ktor.utils.io.core.ByteReadPacket> r10) {
        /*
            r6 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel$readPacketSuspend$1
            if (r0 == 0) goto L14
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$readPacketSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readPacketSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$readPacketSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readPacketSuspend$1
            r0.<init>(r6, r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L45;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$2
            java.nio.ByteBuffer r8 = (java.nio.ByteBuffer) r8
            java.lang.Object r9 = r0.L$1
            io.ktor.utils.io.core.BytePacketBuilder r9 = (io.ktor.utils.io.core.BytePacketBuilder) r9
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L42
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r10
            goto L74
        L42:
            r7 = move-exception
            goto L99
        L45:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r6
            r3 = r7
            r7 = r9
            r9 = r8
            r8 = r7
            r7 = r3
        L4e:
            if (r7 <= 0) goto L8d
            r8.clear()     // Catch: java.lang.Throwable -> L42
            int r3 = r8.remaining()     // Catch: java.lang.Throwable -> L42
            if (r3 <= r7) goto L5c
            r8.limit(r7)     // Catch: java.lang.Throwable -> L42
        L5c:
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L42
            r0.L$1 = r9     // Catch: java.lang.Throwable -> L42
            r0.L$2 = r8     // Catch: java.lang.Throwable -> L42
            r0.I$0 = r7     // Catch: java.lang.Throwable -> L42
            r3 = 1
            r0.label = r3     // Catch: java.lang.Throwable -> L42
            java.lang.Object r3 = r2.readFully(r8, r0)     // Catch: java.lang.Throwable -> L42
            if (r3 != r1) goto L6e
            return r1
        L6e:
            r5 = r0
            r0 = r10
            r10 = r3
            r3 = r2
            r2 = r1
            r1 = r5
        L74:
            java.lang.Number r10 = (java.lang.Number) r10     // Catch: java.lang.Throwable -> L89
            int r10 = r10.intValue()     // Catch: java.lang.Throwable -> L89
            r8.flip()     // Catch: java.lang.Throwable -> L89
            r4 = r9
            io.ktor.utils.io.core.Output r4 = (io.ktor.utils.io.core.Output) r4     // Catch: java.lang.Throwable -> L89
            io.ktor.utils.io.core.OutputArraysJVMKt.writeFully(r4, r8)     // Catch: java.lang.Throwable -> L89
            int r7 = r7 - r10
            r10 = r0
            r0 = r1
            r1 = r2
            r2 = r3
            goto L4e
        L89:
            r7 = move-exception
            r10 = r0
            r0 = r1
            goto L99
        L8d:
            io.ktor.utils.io.core.ByteReadPacket r7 = r9.build()     // Catch: java.lang.Throwable -> L42
            io.ktor.utils.io.pool.ObjectPool r9 = io.ktor.utils.io.internal.ObjectPoolKt.getBufferPool()
            r9.recycle(r8)
            return r7
        L99:
            r9.release()     // Catch: java.lang.Throwable -> L9e
            throw r7     // Catch: java.lang.Throwable -> L9e
        L9e:
            r7 = move-exception
            io.ktor.utils.io.pool.ObjectPool r9 = io.ktor.utils.io.internal.ObjectPoolKt.getBufferPool()
            r9.recycle(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readPacketSuspend(int, io.ktor.utils.io.core.BytePacketBuilder, java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // io.ktor.utils.io.ByteReadChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readBoolean(kotlin.coroutines.Continuation<? super java.lang.Boolean> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.ktor.utils.io.ByteBufferChannel$readBoolean$1
            if (r0 == 0) goto L14
            r0 = r5
            io.ktor.utils.io.ByteBufferChannel$readBoolean$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readBoolean$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$readBoolean$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readBoolean$1
            r0.<init>(r4, r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L32;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            kotlin.ResultKt.throwOnFailure(r5)
            r2 = r5
            goto L3f
        L32:
            kotlin.ResultKt.throwOnFailure(r5)
            r2 = r4
            r0.label = r3
            java.lang.Object r2 = r2.readByte(r0)
            if (r2 != r1) goto L3f
            return r1
        L3f:
            java.lang.Number r2 = (java.lang.Number) r2
            byte r1 = r2.byteValue()
            if (r1 == 0) goto L48
            goto L49
        L48:
            r3 = 0
        L49:
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readBoolean(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0027. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARN: Type inference failed for: r14v4, types: [T, java.lang.Byte] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00c6 -> B:12:0x00cf). Please report as a decompilation issue!!! */
    @Override // io.ktor.utils.io.ByteReadChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readByte(kotlin.coroutines.Continuation<? super java.lang.Byte> r19) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readByte(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0027. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARN: Type inference failed for: r14v4, types: [T, java.lang.Short] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00c6 -> B:12:0x00cf). Please report as a decompilation issue!!! */
    @Override // io.ktor.utils.io.ByteReadChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readShort(kotlin.coroutines.Continuation<? super java.lang.Short> r19) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readShort(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0027. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARN: Type inference failed for: r14v4, types: [T, java.lang.Integer] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00c6 -> B:12:0x00cf). Please report as a decompilation issue!!! */
    @Override // io.ktor.utils.io.ByteReadChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readInt(kotlin.coroutines.Continuation<? super java.lang.Integer> r19) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readInt(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0027. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARN: Type inference failed for: r14v4, types: [T, java.lang.Long] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00c7 -> B:12:0x00d0). Please report as a decompilation issue!!! */
    @Override // io.ktor.utils.io.ByteReadChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readLong(kotlin.coroutines.Continuation<? super java.lang.Long> r20) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readLong(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0027. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARN: Type inference failed for: r14v4, types: [T, java.lang.Integer] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00d2 -> B:12:0x00db). Please report as a decompilation issue!!! */
    @Override // io.ktor.utils.io.ByteReadChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readFloat(kotlin.coroutines.Continuation<? super java.lang.Float> r19) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readFloat(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0027. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARN: Type inference failed for: r14v4, types: [T, java.lang.Long] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00d3 -> B:12:0x00dc). Please report as a decompilation issue!!! */
    @Override // io.ktor.utils.io.ByteReadChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readDouble(kotlin.coroutines.Continuation<? super java.lang.Double> r20) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readDouble(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T extends Number> Object readPrimitive(int size, Function1<? super ByteBuffer, ? extends T> function1, Continuation<? super T> continuation) {
        do {
            Ref.ObjectRef result = new Ref.ObjectRef();
            ByteBuffer buffer$iv = setupStateForRead();
            boolean rc = false;
            if (buffer$iv != null) {
                RingBufferCapacity capacity$iv = getState().capacity;
                try {
                    if (capacity$iv._availableForRead$internal != 0) {
                        RingBufferCapacity it = capacity$iv;
                        ByteBuffer $this$readPrimitive_u24lambda_u2439 = buffer$iv;
                        if (it.tryReadExact(size)) {
                            if ($this$readPrimitive_u24lambda_u2439.remaining() < size) {
                                rollBytes($this$readPrimitive_u24lambda_u2439, size);
                            }
                            result.element = function1.invoke($this$readPrimitive_u24lambda_u2439);
                            bytesRead($this$readPrimitive_u24lambda_u2439, it, size);
                            rc = true;
                        }
                        Boolean valueOf = Boolean.valueOf(rc);
                        Boolean bool = valueOf;
                        rc = valueOf.booleanValue();
                    }
                } finally {
                    restoreStateAfterRead();
                    tryTerminate$ktor_io();
                }
            }
            if (rc) {
                if (result.element != 0) {
                    return (Number) result.element;
                }
                Intrinsics.throwUninitializedPropertyAccessException("result");
                return null;
            }
        } while (((Boolean) readSuspend(size, continuation)).booleanValue());
        throw new ClosedReceiveChannelException("EOF while " + size + " bytes expected");
    }

    private final void rollBytes(ByteBuffer $this$rollBytes, int n) {
        int remaining = $this$rollBytes.remaining();
        $this$rollBytes.limit($this$rollBytes.position() + n);
        int i = n - remaining;
        for (int i2 = 0; i2 < i; i2++) {
            $this$rollBytes.put($this$rollBytes.capacity() + ReservedLongIndex + i2, $this$rollBytes.get(i2));
        }
    }

    private final void carry(ByteBuffer $this$carry) {
        int base = $this$carry.capacity() - this.reservedSize;
        int position = $this$carry.position();
        for (int i = base; i < position; i++) {
            $this$carry.put(i - base, $this$carry.get(i));
        }
    }

    public final void bytesWrittenFromSession$ktor_io(ByteBuffer buffer, RingBufferCapacity capacity, int count) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(capacity, "capacity");
        bytesWritten(buffer, capacity, count);
    }

    private final void bytesWritten(ByteBuffer $this$bytesWritten, RingBufferCapacity capacity, int count) {
        if (!(count >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        this.writePosition = carryIndex($this$bytesWritten, this.writePosition + count);
        capacity.completeWrite(count);
        setTotalBytesWritten$ktor_io(get_totalBytesWritten() + count);
    }

    private final void bytesRead(ByteBuffer $this$bytesRead, RingBufferCapacity capacity, int count) {
        if (!(count >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        this.readPosition = carryIndex($this$bytesRead, this.readPosition + count);
        capacity.completeRead(count);
        setTotalBytesRead$ktor_io(get_totalBytesRead() + count);
        resumeWriteOp();
    }

    public final ByteBufferChannel resolveChannelInstance$ktor_io() {
        ByteBufferChannel resolveDelegation;
        JoiningState it = this.joining;
        return (it == null || (resolveDelegation = resolveDelegation(this, it)) == null) ? this : resolveDelegation;
    }

    private final ByteBufferChannel resolveDelegation(ByteBufferChannel current, JoiningState joining) {
        ByteBufferChannel currentChannel = current;
        JoiningState currentJoining = joining;
        while (currentChannel.getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            ByteBufferChannel joinedTo = currentJoining.getDelegatedTo();
            JoiningState joiningState = joinedTo.joining;
            if (joiningState == null) {
                return joinedTo;
            }
            currentJoining = joiningState;
            currentChannel = joinedTo;
        }
        return null;
    }

    private final Object delegateSuspend(JoiningState joined, Function1<? super ByteBufferChannel, Unit> function1, Continuation<? super Unit> continuation) {
        while (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
            writeSuspend(1, continuation);
        }
        function1.invoke(joined.getDelegatedTo());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0213, code lost:
    
        if (r6 != false) goto L110;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0024. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01b1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x015f -> B:28:0x0162). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeByte$suspendImpl(io.ktor.utils.io.ByteBufferChannel r9, byte r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instructions count: 570
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeByte$suspendImpl(io.ktor.utils.io.ByteBufferChannel, byte, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0211, code lost:
    
        if (r6 != false) goto L110;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0024. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01af A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x015c -> B:28:0x015f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeShort$suspendImpl(io.ktor.utils.io.ByteBufferChannel r9, short r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instructions count: 568
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeShort$suspendImpl(io.ktor.utils.io.ByteBufferChannel, short, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0209, code lost:
    
        if (r11 != false) goto L110;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0025. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01a9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00df A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x0158 -> B:28:0x015b). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeInt$suspendImpl(io.ktor.utils.io.ByteBufferChannel r9, int r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instructions count: 560
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeInt$suspendImpl(io.ktor.utils.io.ByteBufferChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x020b, code lost:
    
        if (r7 != false) goto L110;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0026. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01ad A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x015c -> B:28:0x015f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeLong$suspendImpl(io.ktor.utils.io.ByteBufferChannel r11, long r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            Method dump skipped, instructions count: 562
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeLong$suspendImpl(io.ktor.utils.io.ByteBufferChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object writeDouble$suspendImpl(ByteBufferChannel $this, double d, Continuation<? super Unit> continuation) {
        Object writeLong = $this.writeLong(Double.doubleToRawLongBits(d), continuation);
        return writeLong == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeLong : Unit.INSTANCE;
    }

    static /* synthetic */ Object writeFloat$suspendImpl(ByteBufferChannel $this, float f, Continuation<? super Unit> continuation) {
        Object writeInt = $this.writeInt(Float.floatToRawIntBits(f), continuation);
        return writeInt == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeInt : Unit.INSTANCE;
    }

    private final Object writePrimitive(int size, Function1<? super ByteBufferChannel, Unit> function1, Function1<? super ByteBuffer, Unit> function12, Continuation<? super Unit> continuation) {
        boolean z;
        boolean z2;
        JoiningState joiningState = this.joining;
        if (joiningState != null) {
            JoiningState it = joiningState;
            ByteBufferChannel resolveDelegation = resolveDelegation(this, it);
            if (resolveDelegation != null) {
                ByteBufferChannel it2 = resolveDelegation;
                function1.invoke(it2);
                return Unit.INSTANCE;
            }
        }
        ByteBuffer buffer = setupStateForWrite$ktor_io();
        if (buffer != null) {
            RingBufferCapacity capacity = getState().capacity;
            if (!capacity.tryWriteExact(size)) {
                z = false;
            } else {
                prepareWriteBuffer$ktor_io(buffer, size);
                ByteBuffer $this$doWritePrimitive_u24lambda_u2451$iv$iv = buffer;
                if ($this$doWritePrimitive_u24lambda_u2451$iv$iv.remaining() < size) {
                    $this$doWritePrimitive_u24lambda_u2451$iv$iv.limit($this$doWritePrimitive_u24lambda_u2451$iv$iv.capacity());
                    function12.invoke($this$doWritePrimitive_u24lambda_u2451$iv$iv);
                    carry($this$doWritePrimitive_u24lambda_u2451$iv$iv);
                } else {
                    function12.invoke($this$doWritePrimitive_u24lambda_u2451$iv$iv);
                }
                bytesWritten($this$doWritePrimitive_u24lambda_u2451$iv$iv, capacity, size);
                Unit unit = Unit.INSTANCE;
                if (capacity.isFull() || getAutoFlush()) {
                    flush();
                }
                restoreStateAfterWrite$ktor_io();
                tryTerminate$ktor_io();
                z = true;
            }
            if (z) {
                return Unit.INSTANCE;
            }
            while (true) {
                try {
                    writeSuspend(size, continuation);
                    if (this.joining != null) {
                        restoreStateAfterWrite$ktor_io();
                        ByteBufferChannel this_$iv$iv = this;
                        JoiningState joined$iv$iv = this_$iv$iv.joining;
                        Intrinsics.checkNotNull(joined$iv$iv);
                        if (this_$iv$iv.getState() == ReadWriteBufferState.Terminated.INSTANCE) {
                            function1.invoke(joined$iv$iv.getDelegatedTo());
                            Unit unit2 = Unit.INSTANCE;
                        } else {
                            while (true) {
                                ByteBufferChannel this_$iv$iv2 = this_$iv$iv;
                                if (this_$iv$iv.getState() == ReadWriteBufferState.Terminated.INSTANCE) {
                                    break;
                                }
                                this_$iv$iv.writeSuspend(1, continuation);
                                this_$iv$iv = this_$iv$iv2;
                            }
                            ByteBufferChannel $this$delegatePrimitive_u24lambda_u2452$iv$iv = joined$iv$iv.getDelegatedTo();
                            function1.invoke($this$delegatePrimitive_u24lambda_u2452$iv$iv);
                            Unit unit3 = Unit.INSTANCE;
                            Unit unit4 = Unit.INSTANCE;
                            Unit unit5 = Unit.INSTANCE;
                        }
                        Unit unit6 = Unit.INSTANCE;
                    } else {
                        if (!capacity.tryWriteExact(size)) {
                            z2 = false;
                        } else {
                            prepareWriteBuffer$ktor_io(buffer, size);
                            ByteBuffer $this$doWritePrimitive_u24lambda_u2451$iv$iv$iv = buffer;
                            if ($this$doWritePrimitive_u24lambda_u2451$iv$iv$iv.remaining() < size) {
                                $this$doWritePrimitive_u24lambda_u2451$iv$iv$iv.limit($this$doWritePrimitive_u24lambda_u2451$iv$iv$iv.capacity());
                                function12.invoke($this$doWritePrimitive_u24lambda_u2451$iv$iv$iv);
                                carry($this$doWritePrimitive_u24lambda_u2451$iv$iv$iv);
                            } else {
                                function12.invoke($this$doWritePrimitive_u24lambda_u2451$iv$iv$iv);
                            }
                            bytesWritten($this$doWritePrimitive_u24lambda_u2451$iv$iv$iv, capacity, size);
                            Unit unit7 = Unit.INSTANCE;
                            if (capacity.isFull() || getAutoFlush()) {
                                flush();
                            }
                            restoreStateAfterWrite$ktor_io();
                            tryTerminate$ktor_io();
                            z2 = true;
                        }
                        if (z2) {
                            Unit unit8 = Unit.INSTANCE;
                            break;
                        }
                    }
                } catch (Throwable cause$iv) {
                    restoreStateAfterWrite$ktor_io();
                    tryTerminate$ktor_io();
                    throw cause$iv;
                }
            }
            return Unit.INSTANCE;
        }
        JoiningState joined$iv = this.joining;
        Intrinsics.checkNotNull(joined$iv);
        if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            function1.invoke(joined$iv.getDelegatedTo());
            Unit unit9 = Unit.INSTANCE;
        } else {
            while (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
                writeSuspend(1, continuation);
            }
            ByteBufferChannel $this$delegatePrimitive_u24lambda_u2452$iv = joined$iv.getDelegatedTo();
            function1.invoke($this$delegatePrimitive_u24lambda_u2452$iv);
            Unit unit10 = Unit.INSTANCE;
            Unit unit11 = Unit.INSTANCE;
            Unit unit12 = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    private final boolean tryWritePrimitive(ByteBuffer $this$tryWritePrimitive, int size, RingBufferCapacity capacity, Function1<? super ByteBuffer, Unit> function1) {
        if (!capacity.tryWriteExact(size)) {
            return false;
        }
        prepareWriteBuffer$ktor_io($this$tryWritePrimitive, size);
        if ($this$tryWritePrimitive.remaining() < size) {
            $this$tryWritePrimitive.limit($this$tryWritePrimitive.capacity());
            function1.invoke($this$tryWritePrimitive);
            carry($this$tryWritePrimitive);
        } else {
            function1.invoke($this$tryWritePrimitive);
        }
        bytesWritten($this$tryWritePrimitive, capacity, size);
        if (capacity.isFull() || getAutoFlush()) {
            flush();
        }
        restoreStateAfterWrite$ktor_io();
        tryTerminate$ktor_io();
        return true;
    }

    private final void doWritePrimitive(int size, ByteBuffer buffer, RingBufferCapacity capacity, Function1<? super ByteBuffer, Unit> writer) {
        if (buffer.remaining() < size) {
            buffer.limit(buffer.capacity());
            writer.invoke(buffer);
            carry(buffer);
        } else {
            writer.invoke(buffer);
        }
        bytesWritten(buffer, capacity, size);
        if (capacity.isFull() || getAutoFlush()) {
            flush();
        }
        restoreStateAfterWrite$ktor_io();
        tryTerminate$ktor_io();
    }

    private final Object writeSuspendPrimitive(ByteBuffer $this$writeSuspendPrimitive, int size, RingBufferCapacity capacity, Function1<? super ByteBufferChannel, Unit> function1, Function1<? super ByteBuffer, Unit> function12, Continuation<? super Unit> continuation) {
        boolean z;
        do {
            try {
                writeSuspend(size, continuation);
                z = true;
                if (this.joining != null) {
                    restoreStateAfterWrite$ktor_io();
                    JoiningState joined$iv = this.joining;
                    Intrinsics.checkNotNull(joined$iv);
                    if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
                        function1.invoke(joined$iv.getDelegatedTo());
                        Unit unit = Unit.INSTANCE;
                    } else {
                        while (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
                            writeSuspend(1, continuation);
                        }
                        ByteBufferChannel $this$delegatePrimitive_u24lambda_u2452$iv = joined$iv.getDelegatedTo();
                        function1.invoke($this$delegatePrimitive_u24lambda_u2452$iv);
                        Unit unit2 = Unit.INSTANCE;
                        Unit unit3 = Unit.INSTANCE;
                        Unit unit4 = Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                }
                if (!capacity.tryWriteExact(size)) {
                    z = false;
                } else {
                    prepareWriteBuffer$ktor_io($this$writeSuspendPrimitive, size);
                    ByteBuffer $this$doWritePrimitive_u24lambda_u2451$iv$iv = $this$writeSuspendPrimitive;
                    if ($this$doWritePrimitive_u24lambda_u2451$iv$iv.remaining() < size) {
                        $this$doWritePrimitive_u24lambda_u2451$iv$iv.limit($this$doWritePrimitive_u24lambda_u2451$iv$iv.capacity());
                        function12.invoke($this$doWritePrimitive_u24lambda_u2451$iv$iv);
                        carry($this$doWritePrimitive_u24lambda_u2451$iv$iv);
                    } else {
                        function12.invoke($this$doWritePrimitive_u24lambda_u2451$iv$iv);
                    }
                    bytesWritten($this$doWritePrimitive_u24lambda_u2451$iv$iv, capacity, size);
                    Unit unit5 = Unit.INSTANCE;
                    if (capacity.isFull() || getAutoFlush()) {
                        flush();
                    }
                }
            } finally {
                restoreStateAfterWrite$ktor_io();
                tryTerminate$ktor_io();
            }
        } while (!z);
        return Unit.INSTANCE;
    }

    private final Object delegatePrimitive(Function1<? super ByteBufferChannel, Unit> function1, Continuation<? super Unit> continuation) {
        JoiningState joined = this.joining;
        Intrinsics.checkNotNull(joined);
        if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            function1.invoke(joined.getDelegatedTo());
            return Unit.INSTANCE;
        }
        while (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
            writeSuspend(1, continuation);
        }
        ByteBufferChannel $this$delegatePrimitive_u24lambda_u2452 = joined.getDelegatedTo();
        function1.invoke($this$delegatePrimitive_u24lambda_u2452);
        Unit unit = Unit.INSTANCE;
        Unit unit2 = Unit.INSTANCE;
        return Unit.INSTANCE;
    }

    static /* synthetic */ Object awaitFreeSpace$suspendImpl(ByteBufferChannel $this, Continuation<? super Unit> continuation) {
        Object writeSuspend = $this.writeSuspend(1, continuation);
        return writeSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeSuspend : Unit.INSTANCE;
    }

    static /* synthetic */ Object writeAvailable$suspendImpl(ByteBufferChannel $this, ByteBuffer src, Continuation<? super Integer> continuation) {
        ByteBufferChannel it;
        ByteBufferChannel it2;
        JoiningState it3 = $this.joining;
        if (it3 != null && (it2 = $this.resolveDelegation($this, it3)) != null) {
            return it2.writeAvailable(src, continuation);
        }
        int copied = $this.writeAsMuchAsPossible(src);
        if (copied > 0) {
            return Boxing.boxInt(copied);
        }
        JoiningState it4 = $this.joining;
        return (it4 == null || (it = $this.resolveDelegation($this, it4)) == null) ? $this.writeAvailableSuspend(src, continuation) : it.writeAvailableSuspend(src, continuation);
    }

    static /* synthetic */ Object writeAvailable$suspendImpl(ByteBufferChannel $this, ChunkBuffer src, Continuation<? super Integer> continuation) {
        ByteBufferChannel it;
        ByteBufferChannel it2;
        JoiningState it3 = $this.joining;
        if (it3 != null && (it2 = $this.resolveDelegation($this, it3)) != null) {
            return it2.writeAvailable(src, continuation);
        }
        int copied = $this.writeAsMuchAsPossible(src);
        if (copied > 0) {
            return Boxing.boxInt(copied);
        }
        JoiningState it4 = $this.joining;
        return (it4 == null || (it = $this.resolveDelegation($this, it4)) == null) ? $this.writeAvailableSuspend(src, continuation) : it.writeAvailableSuspend(src, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0082 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeAvailableSuspend(java.nio.ByteBuffer r7, kotlin.coroutines.Continuation<? super java.lang.Integer> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$1
            r0.<init>(r6, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L45;
                case 1: goto L39;
                case 2: goto L31;
                case 3: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            kotlin.ResultKt.throwOnFailure(r8)
            r7 = r8
            goto L83
        L31:
            r7 = 0
            r1 = 0
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r1
            r1 = r8
            goto L74
        L39:
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L57
        L45:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
            r0.L$0 = r2
            r0.L$1 = r7
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.writeSuspend(r3, r0)
            if (r3 != r1) goto L57
            return r1
        L57:
            io.ktor.utils.io.internal.JoiningState r3 = r2.joining
            r4 = 0
            if (r3 == 0) goto L75
            r5 = 0
            io.ktor.utils.io.ByteBufferChannel r3 = r2.resolveDelegation(r2, r3)
            if (r3 == 0) goto L75
            r2 = 0
            r0.L$0 = r4
            r0.L$1 = r4
            r4 = 2
            r0.label = r4
            java.lang.Object r7 = r3.writeAvailableSuspend(r7, r0)
            if (r7 != r1) goto L72
            return r1
        L72:
            r1 = r7
            r7 = r5
        L74:
            return r1
        L75:
            r0.L$0 = r4
            r0.L$1 = r4
            r3 = 3
            r0.label = r3
            java.lang.Object r7 = r2.writeAvailable(r7, r0)
            if (r7 != r1) goto L83
            return r1
        L83:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeAvailableSuspend(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0082 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer r7, kotlin.coroutines.Continuation<? super java.lang.Integer> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$3
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$3
            r0.<init>(r6, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L45;
                case 1: goto L39;
                case 2: goto L31;
                case 3: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            kotlin.ResultKt.throwOnFailure(r8)
            r7 = r8
            goto L83
        L31:
            r7 = 0
            r1 = 0
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r1
            r1 = r8
            goto L74
        L39:
            java.lang.Object r7 = r0.L$1
            io.ktor.utils.io.core.internal.ChunkBuffer r7 = (io.ktor.utils.io.core.internal.ChunkBuffer) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L57
        L45:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
            r0.L$0 = r2
            r0.L$1 = r7
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.writeSuspend(r3, r0)
            if (r3 != r1) goto L57
            return r1
        L57:
            io.ktor.utils.io.internal.JoiningState r3 = r2.joining
            r4 = 0
            if (r3 == 0) goto L75
            r5 = 0
            io.ktor.utils.io.ByteBufferChannel r3 = r2.resolveDelegation(r2, r3)
            if (r3 == 0) goto L75
            r2 = 0
            r0.L$0 = r4
            r0.L$1 = r4
            r4 = 2
            r0.label = r4
            java.lang.Object r7 = r3.writeAvailableSuspend(r7, r0)
            if (r7 != r1) goto L72
            return r1
        L72:
            r1 = r7
            r7 = r5
        L74:
            return r1
        L75:
            r0.L$0 = r4
            r0.L$1 = r4
            r3 = 3
            r0.label = r3
            java.lang.Object r7 = r2.writeAvailable(r7, r0)
            if (r7 != r1) goto L83
            return r1
        L83:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object writeFully$suspendImpl(ByteBufferChannel $this, ByteBuffer src, Continuation<? super Unit> continuation) {
        Object writeFullySuspend;
        ByteBufferChannel it;
        JoiningState it2 = $this.joining;
        if (it2 != null && (it = $this.resolveDelegation($this, it2)) != null) {
            Object writeFully = it.writeFully(src, continuation);
            return writeFully == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeFully : Unit.INSTANCE;
        }
        $this.writeAsMuchAsPossible(src);
        return (src.hasRemaining() && (writeFullySuspend = $this.writeFullySuspend(src, continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? writeFullySuspend : Unit.INSTANCE;
    }

    static /* synthetic */ Object writeFully$suspendImpl(ByteBufferChannel $this, Buffer src, Continuation<? super Unit> continuation) {
        $this.writeAsMuchAsPossible(src);
        if (!(src.getWritePosition() > src.getReadPosition())) {
            return Unit.INSTANCE;
        }
        Object writeFullySuspend = $this.writeFullySuspend(src, continuation);
        return writeFullySuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeFullySuspend : Unit.INSTANCE;
    }

    /* renamed from: writeFully-JT6ljtQ$suspendImpl, reason: not valid java name */
    static /* synthetic */ Object m223writeFullyJT6ljtQ$suspendImpl(ByteBufferChannel $this, ByteBuffer memory, int startIndex, int endIndex, Continuation<? super Unit> continuation) {
        ByteBuffer slice = Memory.m245slice87lwejk(memory, startIndex, endIndex - startIndex);
        Object writeFully = $this.writeFully(slice, continuation);
        return writeFully == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeFully : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0053 -> B:15:0x0056). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeFullySuspend(java.nio.ByteBuffer r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$1
            r0.<init>(r6, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3e;
                case 1: goto L32;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            r7 = 0
            r1 = 0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L73
        L32:
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L56
        L3e:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
        L42:
            boolean r3 = r7.hasRemaining()
            if (r3 == 0) goto L7a
            r0.L$0 = r2
            r0.L$1 = r7
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.tryWriteSuspend$ktor_io(r3, r0)
            if (r3 != r1) goto L56
            return r1
        L56:
            io.ktor.utils.io.internal.JoiningState r3 = r2.joining
            if (r3 == 0) goto L76
            r4 = 0
            io.ktor.utils.io.ByteBufferChannel r3 = r2.resolveDelegation(r2, r3)
            if (r3 == 0) goto L76
            r2 = 0
            r5 = 0
            r0.L$0 = r5
            r0.L$1 = r5
            r5 = 2
            r0.label = r5
            java.lang.Object r7 = r3.writeFully(r7, r0)
            if (r7 != r1) goto L71
            return r1
        L71:
            r1 = r2
            r7 = r4
        L73:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        L76:
            r2.writeAsMuchAsPossible(r7)
            goto L42
        L7a:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeFullySuspend(java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x005e -> B:15:0x0061). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeFullySuspend(io.ktor.utils.io.core.Buffer r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3
            if (r0 == 0) goto L14
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3
            r0.<init>(r8, r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3e;
                case 1: goto L32;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2c:
            r9 = 0
            r1 = 0
            kotlin.ResultKt.throwOnFailure(r10)
            goto L7e
        L32:
            java.lang.Object r9 = r0.L$1
            io.ktor.utils.io.core.Buffer r9 = (io.ktor.utils.io.core.Buffer) r9
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L61
        L3e:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r8
        L42:
            r3 = r9
            r4 = 0
            int r5 = r3.getWritePosition()
            int r6 = r3.getReadPosition()
            r7 = 1
            if (r5 <= r6) goto L51
            r3 = r7
            goto L52
        L51:
            r3 = 0
        L52:
            if (r3 == 0) goto L85
            r0.L$0 = r2
            r0.L$1 = r9
            r0.label = r7
            java.lang.Object r3 = r2.tryWriteSuspend$ktor_io(r7, r0)
            if (r3 != r1) goto L61
            return r1
        L61:
            io.ktor.utils.io.internal.JoiningState r3 = r2.joining
            if (r3 == 0) goto L81
            r4 = 0
            io.ktor.utils.io.ByteBufferChannel r3 = r2.resolveDelegation(r2, r3)
            if (r3 == 0) goto L81
            r2 = 0
            r5 = 0
            r0.L$0 = r5
            r0.L$1 = r5
            r5 = 2
            r0.label = r5
            java.lang.Object r9 = r3.writeFully(r9, r0)
            if (r9 != r1) goto L7c
            return r1
        L7c:
            r1 = r2
            r9 = r4
        L7e:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        L81:
            r2.writeAsMuchAsPossible(r9)
            goto L42
        L85:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeFullySuspend(io.ktor.utils.io.core.Buffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object awaitClose(Continuation<? super Unit> continuation) {
        if (getClosed() != null) {
            return Unit.INSTANCE;
        }
        JoiningState joined = this.joining;
        if (joined == null) {
            if (getClosed() != null) {
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("Only works for joined.".toString());
        }
        Object awaitClose = joined.awaitClose(continuation);
        return awaitClose == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitClose : Unit.INSTANCE;
    }

    public final Object joinFrom$ktor_io(ByteBufferChannel src, boolean delegateClose, Continuation<? super Unit> continuation) {
        if (src.getClosed() != null && src.getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            if (delegateClose) {
                ClosedElement closed = src.getClosed();
                Intrinsics.checkNotNull(closed);
                close(closed.getCause());
            }
            return Unit.INSTANCE;
        }
        ClosedElement closed2 = getClosed();
        if (closed2 != null) {
            if (src.getClosed() == null) {
                ByteBufferChannelKt.rethrowClosed(closed2.getSendException());
                throw new KotlinNothingValueException();
            }
            return Unit.INSTANCE;
        }
        JoiningState joined = src.setupDelegateTo(this, delegateClose);
        if (src.tryCompleteJoining(joined)) {
            Object awaitClose = src.awaitClose(continuation);
            return awaitClose == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? awaitClose : Unit.INSTANCE;
        }
        Object joinFromSuspend = joinFromSuspend(src, delegateClose, joined, continuation);
        return joinFromSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? joinFromSuspend : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object joinFromSuspend(io.ktor.utils.io.ByteBufferChannel r8, boolean r9, io.ktor.utils.io.internal.JoiningState r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r7 = this;
            boolean r0 = r11 instanceof io.ktor.utils.io.ByteBufferChannel$joinFromSuspend$1
            if (r0 == 0) goto L14
            r0 = r11
            io.ktor.utils.io.ByteBufferChannel$joinFromSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$joinFromSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$joinFromSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$joinFromSuspend$1
            r0.<init>(r7, r11)
        L19:
            r6 = r0
            java.lang.Object r11 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            switch(r1) {
                case 0: goto L3f;
                case 1: goto L31;
                case 2: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2d:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L80
        L31:
            boolean r8 = r6.Z$0
            java.lang.Object r9 = r6.L$1
            io.ktor.utils.io.ByteBufferChannel r9 = (io.ktor.utils.io.ByteBufferChannel) r9
            java.lang.Object r10 = r6.L$0
            io.ktor.utils.io.ByteBufferChannel r10 = (io.ktor.utils.io.ByteBufferChannel) r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto L5d
        L3f:
            kotlin.ResultKt.throwOnFailure(r11)
            r1 = r7
            r2 = r8
            r5 = r10
            r6.L$0 = r1
            r6.L$1 = r2
            r6.Z$0 = r9
            r8 = 1
            r6.label = r8
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.lang.Object r8 = r1.copyDirect$ktor_io(r2, r3, r5, r6)
            if (r8 != r0) goto L5a
            return r0
        L5a:
            r8 = r9
            r10 = r1
            r9 = r2
        L5d:
            if (r8 == 0) goto L6e
            boolean r1 = r9.isClosedForRead()
            if (r1 == 0) goto L6e
            r8 = r10
            io.ktor.utils.io.ByteWriteChannel r8 = (io.ktor.utils.io.ByteWriteChannel) r8
            io.ktor.utils.io.ByteWriteChannelKt.close(r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L6e:
            r10.flush()
            r8 = 0
            r6.L$0 = r8
            r6.L$1 = r8
            r8 = 2
            r6.label = r8
            java.lang.Object r8 = r9.awaitClose(r6)
            if (r8 != r0) goto L80
            return r0
        L80:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.joinFromSuspend(io.ktor.utils.io.ByteBufferChannel, boolean, io.ktor.utils.io.internal.JoiningState, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:116:0x016c, code lost:
    
        r4 = r37;
        r11 = r13;
        r13 = r14;
        r7 = r17;
        r14 = r18;
        r10 = r19;
        r6 = r26;
        r8 = r27;
        r2 = r29;
        r37 = r35;
        r35 = r15;
        r15 = r1;
        r1 = r34;
        r34 = r36;
        r36 = r25;
        r12 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x045e, code lost:
    
        if (r11.tryCompleteJoining(r10) == false) goto L176;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0027. Please report as an issue. */
    /* JADX WARN: Path cross not found for [B:49:0x0458, B:43:0x044f], limit reached: 238 */
    /* JADX WARN: Removed duplicated region for block: B:101:0x03b2 A[Catch: all -> 0x04ab, TryCatch #4 {all -> 0x04ab, blocks: (B:99:0x03ac, B:101:0x03b2, B:104:0x03bd, B:105:0x03d5, B:108:0x03b8), top: B:98:0x03ac }] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x03bd A[Catch: all -> 0x04ab, TryCatch #4 {all -> 0x04ab, blocks: (B:99:0x03ac, B:101:0x03b2, B:104:0x03bd, B:105:0x03d5, B:108:0x03b8), top: B:98:0x03ac }] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0306 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x04d7 A[Catch: all -> 0x0071, TryCatch #7 {all -> 0x0071, blocks: (B:13:0x0046, B:16:0x0122, B:18:0x0128, B:20:0x012e, B:22:0x0136, B:26:0x03eb, B:29:0x03f3, B:31:0x03ff, B:32:0x0408, B:34:0x040e, B:36:0x0417, B:57:0x0475, B:59:0x0479, B:64:0x0140, B:121:0x04d1, B:123:0x04d7, B:126:0x04e2, B:127:0x04ef, B:128:0x04f5, B:129:0x04dd, B:197:0x04f8, B:198:0x04fb, B:201:0x006b, B:232:0x011d), top: B:7:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x04e2 A[Catch: all -> 0x0071, TryCatch #7 {all -> 0x0071, blocks: (B:13:0x0046, B:16:0x0122, B:18:0x0128, B:20:0x012e, B:22:0x0136, B:26:0x03eb, B:29:0x03f3, B:31:0x03ff, B:32:0x0408, B:34:0x040e, B:36:0x0417, B:57:0x0475, B:59:0x0479, B:64:0x0140, B:121:0x04d1, B:123:0x04d7, B:126:0x04e2, B:127:0x04ef, B:128:0x04f5, B:129:0x04dd, B:197:0x04f8, B:198:0x04fb, B:201:0x006b, B:232:0x011d), top: B:7:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0128 A[Catch: all -> 0x0071, TryCatch #7 {all -> 0x0071, blocks: (B:13:0x0046, B:16:0x0122, B:18:0x0128, B:20:0x012e, B:22:0x0136, B:26:0x03eb, B:29:0x03f3, B:31:0x03ff, B:32:0x0408, B:34:0x040e, B:36:0x0417, B:57:0x0475, B:59:0x0479, B:64:0x0140, B:121:0x04d1, B:123:0x04d7, B:126:0x04e2, B:127:0x04ef, B:128:0x04f5, B:129:0x04dd, B:197:0x04f8, B:198:0x04fb, B:201:0x006b, B:232:0x011d), top: B:7:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x04f8 A[Catch: all -> 0x0071, TryCatch #7 {all -> 0x0071, blocks: (B:13:0x0046, B:16:0x0122, B:18:0x0128, B:20:0x012e, B:22:0x0136, B:26:0x03eb, B:29:0x03f3, B:31:0x03ff, B:32:0x0408, B:34:0x040e, B:36:0x0417, B:57:0x0475, B:59:0x0479, B:64:0x0140, B:121:0x04d1, B:123:0x04d7, B:126:0x04e2, B:127:0x04ef, B:128:0x04f5, B:129:0x04dd, B:197:0x04f8, B:198:0x04fb, B:201:0x006b, B:232:0x011d), top: B:7:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x03eb A[Catch: all -> 0x0071, TRY_ENTER, TryCatch #7 {all -> 0x0071, blocks: (B:13:0x0046, B:16:0x0122, B:18:0x0128, B:20:0x012e, B:22:0x0136, B:26:0x03eb, B:29:0x03f3, B:31:0x03ff, B:32:0x0408, B:34:0x040e, B:36:0x0417, B:57:0x0475, B:59:0x0479, B:64:0x0140, B:121:0x04d1, B:123:0x04d7, B:126:0x04e2, B:127:0x04ef, B:128:0x04f5, B:129:0x04dd, B:197:0x04f8, B:198:0x04fb, B:201:0x006b, B:232:0x011d), top: B:7:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x040e A[Catch: all -> 0x0071, TryCatch #7 {all -> 0x0071, blocks: (B:13:0x0046, B:16:0x0122, B:18:0x0128, B:20:0x012e, B:22:0x0136, B:26:0x03eb, B:29:0x03f3, B:31:0x03ff, B:32:0x0408, B:34:0x040e, B:36:0x0417, B:57:0x0475, B:59:0x0479, B:64:0x0140, B:121:0x04d1, B:123:0x04d7, B:126:0x04e2, B:127:0x04ef, B:128:0x04f5, B:129:0x04dd, B:197:0x04f8, B:198:0x04fb, B:201:0x006b, B:232:0x011d), top: B:7:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x044f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0458  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0174 A[Catch: all -> 0x04af, TryCatch #5 {all -> 0x04af, blocks: (B:72:0x016e, B:74:0x0174, B:76:0x017e), top: B:71:0x016e }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01ce A[Catch: all -> 0x01ff, TRY_LEAVE, TryCatch #14 {all -> 0x01ff, blocks: (B:86:0x01ca, B:88:0x01ce), top: B:85:0x01ca }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x03ff -> B:16:0x0122). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:62:0x049d -> B:15:0x049f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:63:0x04a3 -> B:16:0x0122). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object copyDirect$ktor_io(io.ktor.utils.io.ByteBufferChannel r34, long r35, io.ktor.utils.io.internal.JoiningState r37, kotlin.coroutines.Continuation<? super java.lang.Long> r38) {
        /*
            Method dump skipped, instructions count: 1306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.copyDirect$ktor_io(io.ktor.utils.io.ByteBufferChannel, long, io.ktor.utils.io.internal.JoiningState, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void ensureClosedJoined(JoiningState joined) {
        ClosedElement closed = getClosed();
        if (closed == null) {
            return;
        }
        this.joining = null;
        if (!joined.getDelegateClose()) {
            joined.getDelegatedTo().flush();
            joined.complete();
            return;
        }
        ReadWriteBufferState it = joined.getDelegatedTo().getState();
        boolean writing = (it instanceof ReadWriteBufferState.Writing) || (it instanceof ReadWriteBufferState.ReadingWriting);
        if (closed.getCause() != null || !writing) {
            joined.getDelegatedTo().close(closed.getCause());
        } else {
            joined.getDelegatedTo().flush();
        }
        joined.complete();
    }

    private final int writeAsMuchAsPossible(ByteBuffer src) {
        ByteBufferChannel byteBufferChannel;
        int possibleSize;
        int $i$f$writing = 0;
        JoiningState it$iv = this.joining;
        if (it$iv == null || (byteBufferChannel = resolveDelegation(this, it$iv)) == null) {
            byteBufferChannel = this;
        }
        ByteBufferChannel current$iv = byteBufferChannel;
        ByteBuffer byteBuffer = current$iv.setupStateForWrite$ktor_io();
        if (byteBuffer == null) {
            return 0;
        }
        ByteBuffer buffer$iv = byteBuffer;
        RingBufferCapacity capacity$iv = current$iv.getState().capacity;
        long before$iv = current$iv.get_totalBytesWritten();
        try {
            ClosedElement it$iv2 = current$iv.getClosed();
            try {
                if (it$iv2 != null) {
                    ByteBufferChannelKt.rethrowClosed(it$iv2.getSendException());
                    throw new KotlinNothingValueException();
                }
                ByteBufferChannel $this$writeAsMuchAsPossible_u24lambda_u2477 = current$iv;
                int written = 0;
                int srcLimit = src.limit();
                while (true) {
                    int srcRemaining = srcLimit - src.position();
                    if (srcRemaining != 0 && (possibleSize = capacity$iv.tryWriteAtMost(Math.min(srcRemaining, buffer$iv.remaining()))) != 0) {
                        if (!(possibleSize > 0)) {
                            throw new IllegalArgumentException("Failed requirement.".toString());
                        }
                        int $i$f$writing2 = $i$f$writing;
                        try {
                            src.limit(src.position() + possibleSize);
                            buffer$iv.put(src);
                            written += possibleSize;
                            ByteBuffer buffer$iv2 = buffer$iv;
                            $this$writeAsMuchAsPossible_u24lambda_u2477.prepareBuffer(buffer$iv, $this$writeAsMuchAsPossible_u24lambda_u2477.carryIndex(buffer$iv, $this$writeAsMuchAsPossible_u24lambda_u2477.writePosition + written), capacity$iv._availableForWrite$internal);
                            buffer$iv = buffer$iv2;
                            $i$f$writing = $i$f$writing2;
                        } catch (Throwable th) {
                            th = th;
                            if (capacity$iv.isFull() || current$iv.getAutoFlush()) {
                                current$iv.flush();
                            }
                            if (current$iv != this) {
                                setTotalBytesWritten$ktor_io(get_totalBytesWritten() + (current$iv.get_totalBytesWritten() - before$iv));
                            }
                            current$iv.restoreStateAfterWrite$ktor_io();
                            current$iv.tryTerminate$ktor_io();
                            throw th;
                        }
                    }
                }
                src.limit(srcLimit);
                $this$writeAsMuchAsPossible_u24lambda_u2477.bytesWritten(buffer$iv, capacity$iv, written);
                if (capacity$iv.isFull() || current$iv.getAutoFlush()) {
                    current$iv.flush();
                }
                if (current$iv != this) {
                    setTotalBytesWritten$ktor_io(get_totalBytesWritten() + (current$iv.get_totalBytesWritten() - before$iv));
                }
                current$iv.restoreStateAfterWrite$ktor_io();
                current$iv.tryTerminate$ktor_io();
                return written;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int writeAsMuchAsPossible(io.ktor.utils.io.core.Buffer r20) {
        /*
            r19 = this;
            r1 = r19
            r2 = 0
            io.ktor.utils.io.internal.JoiningState r0 = r1.joining
            if (r0 == 0) goto Le
            r3 = 0
            io.ktor.utils.io.ByteBufferChannel r0 = r1.resolveDelegation(r1, r0)
            if (r0 != 0) goto Lf
        Le:
            r0 = r1
        Lf:
            r3 = r0
            java.nio.ByteBuffer r0 = r3.setupStateForWrite$ktor_io()
            if (r0 != 0) goto L18
            r0 = 0
            return r0
        L18:
            r4 = r0
            io.ktor.utils.io.internal.ReadWriteBufferState r0 = r3.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r5 = r0.capacity
            long r6 = r3.get_totalBytesWritten()
            io.ktor.utils.io.internal.ClosedElement r0 = r3.getClosed()     // Catch: java.lang.Throwable -> Lb4
            if (r0 != 0) goto L9e
            r0 = r3
            io.ktor.utils.io.ByteBufferChannel r0 = (io.ktor.utils.io.ByteBufferChannel) r0     // Catch: java.lang.Throwable -> Lb4
            r8 = r4
            r9 = r5
            r10 = 0
            r11 = 0
        L31:
            r12 = r20
            r13 = 0
            int r14 = r12.getWritePosition()     // Catch: java.lang.Throwable -> Lb4
            int r15 = r12.getReadPosition()     // Catch: java.lang.Throwable -> Lb4
            int r14 = r14 - r15
            int r12 = r8.remaining()     // Catch: java.lang.Throwable -> Lb4
            int r12 = java.lang.Math.min(r14, r12)     // Catch: java.lang.Throwable -> Lb4
            int r12 = r9.tryWriteAtMost(r12)     // Catch: java.lang.Throwable -> Lb4
            if (r12 == 0) goto L6f
            r13 = r20
            io.ktor.utils.io.core.BufferUtilsJvmKt.readFully(r13, r8, r12)     // Catch: java.lang.Throwable -> L6d
            int r11 = r11 + r12
            int r15 = r0.writePosition     // Catch: java.lang.Throwable -> L6d
            int r15 = r15 + r11
            int r15 = r0.carryIndex(r8, r15)     // Catch: java.lang.Throwable -> L6d
            r16 = r9
            r17 = 0
            r18 = r2
            r2 = r16
            r16 = r4
            int r4 = r2._availableForWrite$internal     // Catch: java.lang.Throwable -> Lb2
            r0.prepareBuffer(r8, r15, r4)     // Catch: java.lang.Throwable -> Lb2
            r4 = r16
            r2 = r18
            goto L31
        L6d:
            r0 = move-exception
            goto Lb7
        L6f:
            r13 = r20
            r18 = r2
            r16 = r4
            r0.bytesWritten(r8, r9, r11)     // Catch: java.lang.Throwable -> Lb2
            boolean r0 = r5.isFull()
            if (r0 != 0) goto L85
            boolean r0 = r3.getAutoFlush()
            if (r0 == 0) goto L88
        L85:
            r3.flush()
        L88:
            if (r3 == r1) goto L97
            long r4 = r1.get_totalBytesWritten()
            long r8 = r3.get_totalBytesWritten()
            long r8 = r8 - r6
            long r4 = r4 + r8
            r1.setTotalBytesWritten$ktor_io(r4)
        L97:
            r3.restoreStateAfterWrite$ktor_io()
            r3.tryTerminate$ktor_io()
            return r11
        L9e:
            r13 = r20
            r18 = r2
            r16 = r4
            r2 = 0
            java.lang.Throwable r4 = r0.getSendException()     // Catch: java.lang.Throwable -> Lb2
            io.ktor.utils.io.ByteBufferChannelKt.access$rethrowClosed(r4)     // Catch: java.lang.Throwable -> Lb2
            kotlin.KotlinNothingValueException r4 = new kotlin.KotlinNothingValueException     // Catch: java.lang.Throwable -> Lb2
            r4.<init>()     // Catch: java.lang.Throwable -> Lb2
            throw r4     // Catch: java.lang.Throwable -> Lb2
        Lb2:
            r0 = move-exception
            goto Lbb
        Lb4:
            r0 = move-exception
            r13 = r20
        Lb7:
            r18 = r2
            r16 = r4
        Lbb:
            boolean r2 = r5.isFull()
            if (r2 != 0) goto Lc7
            boolean r2 = r3.getAutoFlush()
            if (r2 == 0) goto Lca
        Lc7:
            r3.flush()
        Lca:
            if (r3 == r1) goto Ld9
            long r8 = r1.get_totalBytesWritten()
            long r10 = r3.get_totalBytesWritten()
            long r10 = r10 - r6
            long r8 = r8 + r10
            r1.setTotalBytesWritten$ktor_io(r8)
        Ld9:
            r3.restoreStateAfterWrite$ktor_io()
            r3.tryTerminate$ktor_io()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeAsMuchAsPossible(io.ktor.utils.io.core.Buffer):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00d1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int writeAsMuchAsPossible(byte[] r19, int r20, int r21) {
        /*
            Method dump skipped, instructions count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeAsMuchAsPossible(byte[], int, int):int");
    }

    static /* synthetic */ Object writeFully$suspendImpl(ByteBufferChannel $this, byte[] src, int offset, int length, Continuation<? super Unit> continuation) {
        Object writeFullySuspend;
        ByteBufferChannel it;
        JoiningState it2 = $this.joining;
        if (it2 != null && (it = $this.resolveDelegation($this, it2)) != null) {
            Object writeFully = it.writeFully(src, offset, length, continuation);
            return writeFully == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeFully : Unit.INSTANCE;
        }
        int rem = length;
        int off = offset;
        while (rem > 0) {
            int s = $this.writeAsMuchAsPossible(src, off, rem);
            if (s == 0) {
                break;
            }
            off += s;
            rem -= s;
        }
        return (rem != 0 && (writeFullySuspend = $this.writeFullySuspend(src, off, rem, continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? writeFullySuspend : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x005c -> B:12:0x0062). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeFullySuspend(byte[] r6, int r7, int r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5 r0 = (io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5 r0 = new io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5
            r0.<init>(r5, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L40;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2c:
            int r6 = r0.I$1
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$1
            byte[] r8 = (byte[]) r8
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r9
            goto L62
        L40:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r5
            r3 = r8
            r8 = r6
            r6 = r3
        L48:
            if (r6 <= 0) goto L6f
            r0.L$0 = r2
            r0.L$1 = r8
            r0.I$0 = r7
            r0.I$1 = r6
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.writeAvailable(r8, r7, r6, r0)
            if (r3 != r1) goto L5c
            return r1
        L5c:
            r4 = r0
            r0 = r9
            r9 = r3
            r3 = r2
            r2 = r1
            r1 = r4
        L62:
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            int r7 = r7 + r9
            int r6 = r6 - r9
            r9 = r0
            r0 = r1
            r1 = r2
            r2 = r3
            goto L48
        L6f:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeFullySuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object writeAvailable$suspendImpl(ByteBufferChannel $this, byte[] src, int offset, int length, Continuation<? super Integer> continuation) {
        ByteBufferChannel it;
        JoiningState it2 = $this.joining;
        if (it2 != null && (it = $this.resolveDelegation($this, it2)) != null) {
            return it.writeAvailable(src, offset, length, continuation);
        }
        int size = $this.writeAsMuchAsPossible(src, offset, length);
        return size > 0 ? Boxing.boxInt(size) : $this.writeSuspend(src, offset, length, continuation);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0082  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x005a -> B:14:0x005d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeSuspend(byte[] r8, int r9, int r10, kotlin.coroutines.Continuation<? super java.lang.Integer> r11) {
        /*
            r7 = this;
            boolean r0 = r11 instanceof io.ktor.utils.io.ByteBufferChannel$writeSuspend$1
            if (r0 == 0) goto L14
            r0 = r11
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$writeSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeSuspend$1
            r0.<init>(r7, r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L43;
                case 1: goto L33;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2c:
            r8 = 0
            r9 = 0
            kotlin.ResultKt.throwOnFailure(r11)
            r10 = r11
            goto L7b
        L33:
            int r8 = r0.I$1
            int r9 = r0.I$0
            java.lang.Object r10 = r0.L$1
            byte[] r10 = (byte[]) r10
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r11)
            goto L5d
        L43:
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = r7
            r6 = r10
            r10 = r8
            r8 = r6
        L4a:
            r0.L$0 = r2
            r0.L$1 = r10
            r0.I$0 = r9
            r0.I$1 = r8
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.tryWriteSuspend$ktor_io(r3, r0)
            if (r3 != r1) goto L5d
            return r1
        L5d:
            io.ktor.utils.io.internal.JoiningState r3 = r2.joining
            if (r3 == 0) goto L7c
            r4 = 0
            io.ktor.utils.io.ByteBufferChannel r3 = r2.resolveDelegation(r2, r3)
            if (r3 == 0) goto L7c
            r2 = 0
            r5 = 0
            r0.L$0 = r5
            r0.L$1 = r5
            r5 = 2
            r0.label = r5
            java.lang.Object r8 = r3.writeSuspend(r10, r9, r8, r0)
            if (r8 != r1) goto L78
            return r1
        L78:
            r10 = r8
            r9 = r2
            r8 = r4
        L7b:
            return r10
        L7c:
            int r3 = r2.writeAsMuchAsPossible(r10, r9, r8)
            if (r3 <= 0) goto L4a
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeSuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public int writeAvailable(int min, Function1<? super ByteBuffer, Unit> block) {
        ByteBufferChannel current$iv;
        Intrinsics.checkNotNullParameter(block, "block");
        if (!(min > 0)) {
            throw new IllegalArgumentException("min should be positive".toString());
        }
        if (!(min <= 4088)) {
            throw new IllegalArgumentException(("Min(" + min + ") shouldn't be greater than 4088").toString());
        }
        int result = 0;
        boolean written = false;
        JoiningState it$iv = this.joining;
        if (it$iv == null || (current$iv = resolveDelegation(this, it$iv)) == null) {
            current$iv = this;
        }
        ByteBuffer buffer$iv = current$iv.setupStateForWrite$ktor_io();
        if (buffer$iv != null) {
            RingBufferCapacity capacity$iv = current$iv.getState().capacity;
            long before$iv = current$iv.get_totalBytesWritten();
            try {
                ClosedElement it$iv2 = current$iv.getClosed();
                if (it$iv2 != null) {
                    ByteBufferChannelKt.rethrowClosed(it$iv2.getSendException());
                    throw new KotlinNothingValueException();
                }
                ByteBufferChannel $this$writeAvailable_u24lambda_u2490 = current$iv;
                int locked = capacity$iv.tryWriteAtLeast(min);
                if (locked > 0) {
                    $this$writeAvailable_u24lambda_u2490.prepareBuffer(buffer$iv, $this$writeAvailable_u24lambda_u2490.writePosition, locked);
                    int position = buffer$iv.position();
                    int l = buffer$iv.limit();
                    block.invoke(buffer$iv);
                    int position2 = buffer$iv.limit();
                    if (!(l == position2)) {
                        throw new IllegalStateException("Buffer limit modified".toString());
                    }
                    result = buffer$iv.position() - position;
                    if (!(result >= 0)) {
                        throw new IllegalStateException("Position has been moved backward: pushback is not supported".toString());
                    }
                    if (result < 0) {
                        throw new IllegalStateException();
                    }
                    $this$writeAvailable_u24lambda_u2490.bytesWritten(buffer$iv, capacity$iv, result);
                    if (result < locked) {
                        capacity$iv.completeRead(locked - result);
                    }
                    written = true;
                }
            } finally {
                if (capacity$iv.isFull() || current$iv.getAutoFlush()) {
                    current$iv.flush();
                }
                if (current$iv != this) {
                    setTotalBytesWritten$ktor_io(get_totalBytesWritten() + (current$iv.get_totalBytesWritten() - before$iv));
                }
                current$iv.restoreStateAfterWrite$ktor_io();
                current$iv.tryTerminate$ktor_io();
            }
        }
        if (written) {
            return result;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Incorrect condition in loop: B:14:0x0057 */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object write$suspendImpl(io.ktor.utils.io.ByteBufferChannel r6, int r7, kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, kotlin.Unit> r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel$write$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$write$1 r0 = (io.ktor.utils.io.ByteBufferChannel$write$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$write$1 r0 = new io.ktor.utils.io.ByteBufferChannel$write$1
            r0.<init>(r6, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L3b;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2d:
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r8 = (io.ktor.utils.io.ByteBufferChannel) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L6c
        L3b:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = 0
            if (r7 <= 0) goto L43
            r4 = r3
            goto L44
        L43:
            r4 = r2
        L44:
            if (r4 == 0) goto L91
            r4 = 4088(0xff8, float:5.729E-42)
            if (r7 > r4) goto L4b
            r2 = r3
        L4b:
            if (r2 == 0) goto L6d
            r5 = r8
            r8 = r6
            r6 = r7
            r7 = r5
        L51:
            int r2 = r8.writeAvailable(r6, r7)
            if (r2 < 0) goto L5d
        L5a:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L5d:
            r0.L$0 = r8
            r0.L$1 = r7
            r0.I$0 = r6
            r0.label = r3
            java.lang.Object r2 = r8.awaitFreeSpaceOrDelegate(r6, r7, r0)
            if (r2 != r1) goto L6c
            return r1
        L6c:
            goto L51
        L6d:
            r6 = 0
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r1 = "Min("
            java.lang.StringBuilder r8 = r8.append(r1)
            java.lang.StringBuilder r8 = r8.append(r7)
            java.lang.String r1 = ") should'nt be greater than (4088)"
            java.lang.StringBuilder r8 = r8.append(r1)
            java.lang.String r6 = r8.toString()
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r6 = r6.toString()
            r7.<init>(r6)
            throw r7
        L91:
            r6 = 0
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "min should be positive"
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.write$suspendImpl(io.ktor.utils.io.ByteBufferChannel, int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object awaitFreeSpaceOrDelegate(int r7, kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, kotlin.Unit> r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1 r0 = (io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1 r0 = new io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1
            r0.<init>(r6, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L40;
                case 1: goto L32;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            r7 = 0
            r8 = 0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L71
        L32:
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L54
        L40:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r6
            r0.L$0 = r2
            r0.L$1 = r8
            r0.I$0 = r7
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.writeSuspend(r7, r0)
            if (r3 != r1) goto L54
            return r1
        L54:
            io.ktor.utils.io.internal.JoiningState r3 = r2.joining
            if (r3 == 0) goto L75
            r4 = 0
            io.ktor.utils.io.ByteBufferChannel r2 = r2.resolveDelegation(r2, r3)
            if (r2 == 0) goto L74
            r3 = 0
            r5 = 0
            r0.L$0 = r5
            r0.L$1 = r5
            r5 = 2
            r0.label = r5
            java.lang.Object r7 = r2.write(r7, r8, r0)
            if (r7 != r1) goto L6f
            return r1
        L6f:
            r8 = r3
            r7 = r4
        L71:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L74:
        L75:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.awaitFreeSpaceOrDelegate(int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object writeWhile$suspendImpl(ByteBufferChannel $this, Function1<? super ByteBuffer, Boolean> function1, Continuation<? super Unit> continuation) {
        if (!$this.writeWhileNoSuspend(function1)) {
            return Unit.INSTANCE;
        }
        ClosedElement it = $this.getClosed();
        if (it != null) {
            ByteBufferChannelKt.rethrowClosed(it.getSendException());
            throw new KotlinNothingValueException();
        }
        Object writeWhileSuspend = $this.writeWhileSuspend(function1, continuation);
        return writeWhileSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writeWhileSuspend : Unit.INSTANCE;
    }

    private final boolean writeWhileNoSuspend(Function1<? super ByteBuffer, Boolean> block) {
        ByteBufferChannel current$iv;
        boolean continueWriting = true;
        JoiningState it$iv = this.joining;
        if (it$iv == null || (current$iv = resolveDelegation(this, it$iv)) == null) {
            current$iv = this;
        }
        ByteBuffer buffer$iv = current$iv.setupStateForWrite$ktor_io();
        if (buffer$iv != null) {
            RingBufferCapacity capacity$iv = current$iv.getState().capacity;
            long before$iv = current$iv.get_totalBytesWritten();
            try {
                ClosedElement it$iv2 = current$iv.getClosed();
                if (it$iv2 != null) {
                    ByteBufferChannelKt.rethrowClosed(it$iv2.getSendException());
                    throw new KotlinNothingValueException();
                }
                ByteBufferChannel $this$writeWhileNoSuspend_u24lambda_u2496 = current$iv;
                continueWriting = $this$writeWhileNoSuspend_u24lambda_u2496.writeWhileLoop(buffer$iv, capacity$iv, block);
            } finally {
                if (capacity$iv.isFull() || current$iv.getAutoFlush()) {
                    current$iv.flush();
                }
                if (current$iv != this) {
                    setTotalBytesWritten$ktor_io(get_totalBytesWritten() + (current$iv.get_totalBytesWritten() - before$iv));
                }
                current$iv.restoreStateAfterWrite$ktor_io();
                current$iv.tryTerminate$ktor_io();
            }
        }
        return continueWriting;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0027. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00eb A[Catch: all -> 0x016e, TryCatch #2 {all -> 0x016e, blocks: (B:19:0x00e7, B:21:0x00eb, B:23:0x00f1, B:25:0x00f5, B:28:0x00c3), top: B:18:0x00e7 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00e4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00e5 -> B:18:0x00e7). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeWhileSuspend(kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, java.lang.Boolean> r18, kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
        /*
            Method dump skipped, instructions count: 436
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeWhileSuspend(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean writeWhileLoop(ByteBuffer dst, RingBufferCapacity capacity, Function1<? super ByteBuffer, Boolean> block) {
        boolean continueWriting = true;
        int bufferLimit = dst.capacity() - this.reservedSize;
        while (continueWriting) {
            int locked = capacity.tryWriteAtLeast(1);
            if (locked == 0) {
                break;
            }
            int position = this.writePosition;
            int l = RangesKt.coerceAtMost(position + locked, bufferLimit);
            dst.limit(l);
            dst.position(position);
            try {
                continueWriting = block.invoke(dst).booleanValue();
                if (!(dst.limit() == l)) {
                    throw new IllegalStateException("Buffer limit modified.".toString());
                }
                int actuallyWritten = dst.position() - position;
                if (!(actuallyWritten >= 0)) {
                    throw new IllegalStateException("Position has been moved backward: pushback is not supported.".toString());
                }
                bytesWritten(dst, capacity, actuallyWritten);
                if (actuallyWritten < locked) {
                    capacity.completeRead(locked - actuallyWritten);
                }
            } catch (Throwable cause) {
                capacity.completeRead(locked);
                throw cause;
            }
        }
        return continueWriting;
    }

    @Override // io.ktor.utils.io.HasReadSession
    public SuspendableReadSession startReadSession() {
        return this.readSession;
    }

    @Override // io.ktor.utils.io.HasReadSession
    public void endReadSession() {
        this.readSession.completed();
        ReadWriteBufferState state = getState();
        if ((state instanceof ReadWriteBufferState.Reading) || (state instanceof ReadWriteBufferState.ReadingWriting)) {
            restoreStateAfterRead();
            tryTerminate$ktor_io();
        }
    }

    @Override // io.ktor.utils.io.HasWriteSession
    public WriterSuspendSession beginWriteSession() {
        WriteSessionImpl it = this.writeSession;
        it.begin();
        return it;
    }

    @Override // io.ktor.utils.io.HasWriteSession
    public void endWriteSession(int written) {
        this.writeSession.written(written);
        this.writeSession.complete();
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    @Deprecated(message = "Use read { } instead.")
    public void readSession(final Function1<? super ReadSession, Unit> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        lookAhead(new Function1<LookAheadSession, Unit>() { // from class: io.ktor.utils.io.ByteBufferChannel$readSession$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LookAheadSession lookAheadSession) {
                invoke2(lookAheadSession);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LookAheadSession lookAhead) {
                ReadSessionImpl readSessionImpl;
                ReadSessionImpl readSessionImpl2;
                Intrinsics.checkNotNullParameter(lookAhead, "$this$lookAhead");
                try {
                    Function1<ReadSession, Unit> function1 = consumer;
                    readSessionImpl2 = this.readSession;
                    function1.invoke(readSessionImpl2);
                } finally {
                    readSessionImpl = this.readSession;
                    readSessionImpl.completed();
                }
            }
        });
    }

    @Deprecated(message = "Use read { } instead.")
    static /* synthetic */ Object readSuspendableSession$suspendImpl(ByteBufferChannel $this, Function2<? super SuspendableReadSession, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object lookAheadSuspend = $this.lookAheadSuspend(new ByteBufferChannel$readSuspendableSession$2(function2, $this, null), continuation);
        return lookAheadSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? lookAheadSuspend : Unit.INSTANCE;
    }

    static /* synthetic */ Object read$suspendImpl(ByteBufferChannel $this, int min, Function1<? super ByteBuffer, Unit> function1, Continuation<? super Unit> continuation) {
        ByteBufferChannel byteBufferChannel;
        boolean z = true;
        if (!(min >= 0)) {
            throw new IllegalArgumentException("min should be positive or zero".toString());
        }
        ByteBuffer buffer$iv = $this.setupStateForRead();
        if (buffer$iv == null) {
            byteBufferChannel = $this;
        } else {
            RingBufferCapacity capacity$iv = $this.getState().capacity;
            try {
                if (capacity$iv._availableForRead$internal == 0) {
                    $this.restoreStateAfterRead();
                    $this.tryTerminate$ktor_io();
                    byteBufferChannel = $this;
                } else {
                    int av = capacity$iv._availableForRead$internal;
                    if (av <= 0) {
                        byteBufferChannel = $this;
                    } else if (av < min) {
                        byteBufferChannel = $this;
                    } else {
                        int position = buffer$iv.position();
                        int oldLimit = buffer$iv.limit();
                        try {
                            function1.invoke(buffer$iv);
                        } catch (Throwable th) {
                            th = th;
                        }
                        try {
                            if (!(oldLimit == buffer$iv.limit())) {
                                throw new IllegalStateException("Buffer limit modified.".toString());
                            }
                            int delta = buffer$iv.position() - position;
                            read = delta >= 0;
                            if (!read) {
                                throw new IllegalStateException("Position has been moved backward: pushback is not supported.".toString());
                            }
                            if (!capacity$iv.tryReadExact(delta)) {
                                throw new IllegalStateException("Check failed.".toString());
                            }
                            byteBufferChannel = $this;
                            byteBufferChannel.bytesRead(buffer$iv, capacity$iv, delta);
                            $this.restoreStateAfterRead();
                            $this.tryTerminate$ktor_io();
                            read = z;
                        } catch (Throwable th2) {
                            th = th2;
                            $this.restoreStateAfterRead();
                            $this.tryTerminate$ktor_io();
                            throw th;
                        }
                    }
                    z = false;
                    $this.restoreStateAfterRead();
                    $this.tryTerminate$ktor_io();
                    read = z;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        if (read) {
            return Unit.INSTANCE;
        }
        if (byteBufferChannel.isClosedForRead() && min > 0) {
            throw new EOFException("Got EOF but at least " + min + " bytes were expected");
        }
        Object readBlockSuspend = $this.readBlockSuspend(min, function1, continuation);
        return readBlockSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? readBlockSuspend : Unit.INSTANCE;
    }

    /* JADX WARN: Finally extract failed */
    static /* synthetic */ Object discard$suspendImpl(ByteBufferChannel $this, long max, Continuation<? super Long> continuation) {
        long discarded;
        if (!(max >= 0)) {
            throw new IllegalArgumentException(("max shouldn't be negative: " + max).toString());
        }
        ByteBuffer buffer$iv = $this.setupStateForRead();
        if (buffer$iv != null) {
            RingBufferCapacity capacity$iv = $this.getState().capacity;
            try {
                if (capacity$iv._availableForRead$internal != 0) {
                    int n = capacity$iv.tryReadAtMost((int) Math.min(2147483647L, max));
                    $this.bytesRead(buffer$iv, capacity$iv, n);
                    long discarded2 = 0 + n;
                    $this.restoreStateAfterRead();
                    $this.tryTerminate$ktor_io();
                    discarded = discarded2;
                    if (discarded == max && !$this.isClosedForRead()) {
                        return $this.discardSuspend(discarded, max, continuation);
                    }
                    return Boxing.boxLong(discarded);
                }
                $this.restoreStateAfterRead();
                $this.tryTerminate$ktor_io();
            } catch (Throwable th) {
                $this.restoreStateAfterRead();
                $this.tryTerminate$ktor_io();
                throw th;
            }
        }
        discarded = 0;
        if (discarded == max) {
        }
        return Boxing.boxLong(discarded);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0027. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ef A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x00c9 -> B:12:0x00ce). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object discardSuspend(long r19, long r21, kotlin.coroutines.Continuation<? super java.lang.Long> r23) {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.discardSuspend(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readBlockSuspend(int r6, kotlin.jvm.functions.Function1<? super java.nio.ByteBuffer, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel$readBlockSuspend$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$readBlockSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readBlockSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$readBlockSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readBlockSuspend$1
            r0.<init>(r5, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3f;
                case 1: goto L30;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2c:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L92
        L30:
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r8)
            r3 = r8
            goto L57
        L3f:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r5
            r3 = 1
            int r4 = kotlin.ranges.RangesKt.coerceAtLeast(r6, r3)
            r0.L$0 = r2
            r0.L$1 = r7
            r0.I$0 = r6
            r0.label = r3
            java.lang.Object r3 = r2.readSuspend(r4, r0)
            if (r3 != r1) goto L57
            return r1
        L57:
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            if (r3 != 0) goto L83
            if (r6 > 0) goto L64
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L64:
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Got EOF but at least "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r6)
            java.lang.String r2 = " bytes were expected"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r7.<init>(r1)
            throw r7
        L83:
            r3 = 0
            r0.L$0 = r3
            r0.L$1 = r3
            r3 = 2
            r0.label = r3
            java.lang.Object r6 = r2.read(r6, r7, r0)
            if (r6 != r1) goto L92
            return r1
        L92:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readBlockSuspend(int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object writePacket$suspendImpl(ByteBufferChannel $this, ByteReadPacket packet, Continuation<? super Unit> continuation) {
        ByteBufferChannel it;
        ByteBufferChannel it2;
        JoiningState it3 = $this.joining;
        if (it3 != null && (it2 = $this.resolveDelegation($this, it3)) != null) {
            Object writePacket = it2.writePacket(packet, continuation);
            return writePacket == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writePacket : Unit.INSTANCE;
        }
        while (!packet.getEndOfInput() && $this.tryWritePacketPart(packet) != 0) {
            try {
            } catch (Throwable cause) {
                packet.release();
                throw cause;
            }
        }
        if (packet.getRemaining() <= 0) {
            return Unit.INSTANCE;
        }
        JoiningState it4 = $this.joining;
        if (it4 == null || (it = $this.resolveDelegation($this, it4)) == null) {
            Object writePacketSuspend = $this.writePacketSuspend(packet, continuation);
            return writePacketSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writePacketSuspend : Unit.INSTANCE;
        }
        Object writePacket2 = it.writePacket(packet, continuation);
        return writePacket2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? writePacket2 : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0021. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005a A[Catch: all -> 0x0043, TryCatch #0 {all -> 0x0043, blocks: (B:13:0x0032, B:14:0x0084, B:19:0x003f, B:20:0x0067, B:22:0x006b, B:24:0x0072, B:28:0x008a, B:30:0x004e, B:34:0x005a), top: B:7:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [io.ktor.utils.io.core.ByteReadPacket] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v8, types: [io.ktor.utils.io.core.ByteReadPacket] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x0064 -> B:20:0x0067). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writePacketSuspend(io.ktor.utils.io.core.ByteReadPacket r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel$writePacketSuspend$1
            if (r0 == 0) goto L14
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$writePacketSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$writePacketSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$writePacketSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writePacketSuspend$1
            r0.<init>(r8, r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L45;
                case 1: goto L36;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2c:
            r9 = 0
            r1 = 0
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.core.ByteReadPacket r2 = (io.ktor.utils.io.core.ByteReadPacket) r2
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L43
            goto L84
        L36:
            java.lang.Object r9 = r0.L$1
            r2 = r9
            io.ktor.utils.io.core.ByteReadPacket r2 = (io.ktor.utils.io.core.ByteReadPacket) r2
            java.lang.Object r9 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r9 = (io.ktor.utils.io.ByteBufferChannel) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L43
            goto L67
        L43:
            r9 = move-exception
            goto L95
        L45:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r8
            r7 = r2
            r2 = r9
            r9 = r7
        L4c:
            r3 = r2
            r4 = 0
            boolean r5 = r3.getEndOfInput()     // Catch: java.lang.Throwable -> L43
            r6 = 1
            if (r5 != 0) goto L57
            r3 = r6
            goto L58
        L57:
            r3 = 0
        L58:
            if (r3 == 0) goto L8e
            r0.L$0 = r9     // Catch: java.lang.Throwable -> L43
            r0.L$1 = r2     // Catch: java.lang.Throwable -> L43
            r0.label = r6     // Catch: java.lang.Throwable -> L43
            java.lang.Object r3 = r9.writeSuspend(r6, r0)     // Catch: java.lang.Throwable -> L43
            if (r3 != r1) goto L67
            return r1
        L67:
            io.ktor.utils.io.internal.JoiningState r3 = r9.joining     // Catch: java.lang.Throwable -> L43
            if (r3 == 0) goto L8a
            r4 = 0
            io.ktor.utils.io.ByteBufferChannel r5 = r9.resolveDelegation(r9, r3)     // Catch: java.lang.Throwable -> L43
            if (r5 == 0) goto L8a
            r9 = 0
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L43
            r3 = 0
            r0.L$1 = r3     // Catch: java.lang.Throwable -> L43
            r3 = 2
            r0.label = r3     // Catch: java.lang.Throwable -> L43
            java.lang.Object r3 = r5.writePacket(r2, r0)     // Catch: java.lang.Throwable -> L43
            if (r3 != r1) goto L82
            return r1
        L82:
            r1 = r9
            r9 = r4
        L84:
            kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L43
            r2.release()
            return r3
        L8a:
            r9.tryWritePacketPart(r2)     // Catch: java.lang.Throwable -> L43
            goto L4c
        L8e:
            r2.release()
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L95:
            r2.release()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writePacketSuspend(io.ktor.utils.io.core.ByteReadPacket, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final int tryWritePacketPart(ByteReadPacket packet) {
        ByteBufferChannel byteBufferChannel;
        int copied = 0;
        JoiningState it$iv = this.joining;
        if (it$iv == null || (byteBufferChannel = resolveDelegation(this, it$iv)) == null) {
            byteBufferChannel = this;
        }
        ByteBufferChannel current$iv = byteBufferChannel;
        ByteBuffer buffer$iv = current$iv.setupStateForWrite$ktor_io();
        if (buffer$iv != null) {
            RingBufferCapacity capacity$iv = current$iv.getState().capacity;
            long before$iv = current$iv.get_totalBytesWritten();
            try {
                ClosedElement it$iv2 = current$iv.getClosed();
                try {
                    if (it$iv2 != null) {
                        ByteBufferChannelKt.rethrowClosed(it$iv2.getSendException());
                        throw new KotlinNothingValueException();
                    }
                    ByteBufferChannel $this$tryWritePacketPart_u24lambda_u24116 = current$iv;
                    int size = capacity$iv.tryWriteAtMost((int) Math.min(packet.getRemaining(), buffer$iv.remaining()));
                    if (size > 0) {
                        buffer$iv.limit(buffer$iv.position() + size);
                        ByteBuffersKt.readFully(packet, buffer$iv);
                        $this$tryWritePacketPart_u24lambda_u24116.bytesWritten(buffer$iv, capacity$iv, size);
                    }
                    copied = size;
                    if (capacity$iv.isFull() || current$iv.getAutoFlush()) {
                        current$iv.flush();
                    }
                    if (current$iv != this) {
                        setTotalBytesWritten$ktor_io(get_totalBytesWritten() + (current$iv.get_totalBytesWritten() - before$iv));
                    }
                    current$iv.restoreStateAfterWrite$ktor_io();
                    current$iv.tryTerminate$ktor_io();
                } catch (Throwable th) {
                    th = th;
                    if (capacity$iv.isFull() || current$iv.getAutoFlush()) {
                        current$iv.flush();
                    }
                    if (current$iv != this) {
                        setTotalBytesWritten$ktor_io(get_totalBytesWritten() + (current$iv.get_totalBytesWritten() - before$iv));
                    }
                    current$iv.restoreStateAfterWrite$ktor_io();
                    current$iv.tryTerminate$ktor_io();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return copied;
    }

    /* JADX WARN: Finally extract failed */
    @Override // io.ktor.utils.io.ByteReadChannel
    @Deprecated(message = "Use read { } instead.")
    public <R> R lookAhead(Function1<? super LookAheadSession, ? extends R> visitor) {
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        Throwable it = getClosedCause();
        if (it != null) {
            return visitor.invoke(new FailedLookAhead(it));
        }
        if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            return visitor.invoke(TerminatedLookAhead.INSTANCE);
        }
        R r = null;
        boolean continueReading = false;
        if (setupStateForRead() != null) {
            RingBufferCapacity capacity$iv = getState().capacity;
            try {
                if (capacity$iv._availableForRead$internal != 0) {
                    r = visitor.invoke(this);
                    restoreStateAfterRead();
                    tryTerminate$ktor_io();
                    continueReading = true;
                } else {
                    restoreStateAfterRead();
                    tryTerminate$ktor_io();
                }
            } catch (Throwable th) {
                restoreStateAfterRead();
                tryTerminate$ktor_io();
                throw th;
            }
        }
        if (!continueReading) {
            Throwable it2 = getClosedCause();
            if (it2 != null) {
                return visitor.invoke(new FailedLookAhead(it2));
            }
            return visitor.invoke(TerminatedLookAhead.INSTANCE);
        }
        Intrinsics.checkNotNull(r);
        return r;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0022. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    @kotlin.Deprecated(message = "Use read { } instead.")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ <R> java.lang.Object lookAheadSuspend$suspendImpl(io.ktor.utils.io.ByteBufferChannel r13, kotlin.jvm.functions.Function2<? super io.ktor.utils.io.LookAheadSuspendSession, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r14, kotlin.coroutines.Continuation<? super R> r15) {
        /*
            Method dump skipped, instructions count: 436
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.lookAheadSuspend$suspendImpl(io.ktor.utils.io.ByteBufferChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /* JADX WARN: Type inference failed for: r3v3, types: [io.ktor.utils.io.internal.WriteSessionImpl] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    @kotlin.Deprecated(message = "Use write { } instead.")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeSuspendSession$suspendImpl(io.ktor.utils.io.ByteBufferChannel r3, kotlin.jvm.functions.Function2<? super io.ktor.utils.io.WriterSuspendSession, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r4, kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            boolean r0 = r5 instanceof io.ktor.utils.io.ByteBufferChannel$writeSuspendSession$1
            if (r0 == 0) goto L14
            r0 = r5
            io.ktor.utils.io.ByteBufferChannel$writeSuspendSession$1 r0 = (io.ktor.utils.io.ByteBufferChannel$writeSuspendSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$writeSuspendSession$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeSuspendSession$1
            r0.<init>(r3, r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L34;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
            r3.<init>(r4)
            throw r3
        L2c:
            java.lang.Object r3 = r0.L$0
            io.ktor.utils.io.internal.WriteSessionImpl r3 = (io.ktor.utils.io.internal.WriteSessionImpl) r3
            kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Throwable -> L50
            goto L49
        L34:
            kotlin.ResultKt.throwOnFailure(r5)
            io.ktor.utils.io.internal.WriteSessionImpl r3 = r3.writeSession
            r3.begin()
            r0.L$0 = r3     // Catch: java.lang.Throwable -> L50
            r2 = 1
            r0.label = r2     // Catch: java.lang.Throwable -> L50
            java.lang.Object r2 = r4.invoke(r3, r0)     // Catch: java.lang.Throwable -> L50
            if (r2 != r1) goto L49
            return r1
        L49:
            r3.complete()
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L50:
            r4 = move-exception
            r3.complete()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeSuspendSession$suspendImpl(io.ktor.utils.io.ByteBufferChannel, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // io.ktor.utils.io.LookAheadSession
    /* renamed from: consumed */
    public void mo501consumed(int n) {
        if (!(n >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        ReadWriteBufferState s = getState();
        if (!s.capacity.tryReadExact(n)) {
            throw new IllegalStateException("Unable to consume " + n + " bytes: not enough available bytes");
        }
        if (n > 0) {
            bytesRead(s.getReadBuffer(), s.capacity, n);
        }
    }

    @Override // io.ktor.utils.io.LookAheadSuspendSession
    public final Object awaitAtLeast(int n, Continuation<? super Boolean> continuation) {
        if (!(n >= 0)) {
            throw new IllegalArgumentException(("atLeast parameter shouldn't be negative: " + n).toString());
        }
        if (!(n <= 4088)) {
            throw new IllegalArgumentException(("atLeast parameter shouldn't be larger than max buffer size of 4088: " + n).toString());
        }
        RingBufferCapacity this_$iv = getState().capacity;
        if (this_$iv._availableForRead$internal >= n) {
            if (getState().getIdle() || (getState() instanceof ReadWriteBufferState.Writing)) {
                setupStateForRead();
            }
            return Boxing.boxBoolean(true);
        }
        if (getState().getIdle() || (getState() instanceof ReadWriteBufferState.Writing)) {
            return awaitAtLeastSuspend(n, continuation);
        }
        return n == 1 ? readSuspendImpl(1, continuation) : readSuspend(n, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object awaitAtLeastSuspend(int r5, kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteBufferChannel$awaitAtLeastSuspend$1
            if (r0 == 0) goto L14
            r0 = r6
            io.ktor.utils.io.ByteBufferChannel$awaitAtLeastSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel$awaitAtLeastSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$awaitAtLeastSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$awaitAtLeastSuspend$1
            r0.<init>(r4, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L37;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2d:
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r5 = (io.ktor.utils.io.ByteBufferChannel) r5
            kotlin.ResultKt.throwOnFailure(r6)
            r2 = r5
            r5 = r6
            goto L46
        L37:
            kotlin.ResultKt.throwOnFailure(r6)
            r2 = r4
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r5 = r2.readSuspend(r5, r0)
            if (r5 != r1) goto L46
            return r1
        L46:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L5b
            io.ktor.utils.io.internal.ReadWriteBufferState r1 = r2.getState()
            boolean r1 = r1.getIdle()
            if (r1 == 0) goto L5b
            r2.setupStateForRead()
        L5b:
            if (r5 == 0) goto L5e
            goto L5f
        L5e:
            r3 = 0
        L5f:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.awaitAtLeastSuspend(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // io.ktor.utils.io.LookAheadSession
    public ByteBuffer request(int skip, int atLeast) {
        ReadWriteBufferState s = getState();
        RingBufferCapacity this_$iv = s.capacity;
        int available = this_$iv._availableForRead$internal;
        int rp = this.readPosition;
        if (available < atLeast + skip) {
            return null;
        }
        if (s.getIdle() || (!(s instanceof ReadWriteBufferState.Reading) && !(s instanceof ReadWriteBufferState.ReadingWriting))) {
            if (setupStateForRead() == null) {
                return null;
            }
            return request(skip, atLeast);
        }
        ByteBuffer buffer = s.getReadBuffer();
        int position = carryIndex(buffer, rp + skip);
        prepareBuffer(buffer, position, available - skip);
        if (buffer.remaining() >= atLeast) {
            return buffer;
        }
        return null;
    }

    private final boolean consumeEachBufferRangeFast(boolean last, Function2<? super ByteBuffer, ? super Boolean, Boolean> visitor) {
        ByteBuffer buffer$iv = setupStateForRead();
        boolean rc = false;
        if (buffer$iv != null) {
            RingBufferCapacity capacity$iv = getState().capacity;
            try {
                if (capacity$iv._availableForRead$internal != 0) {
                    while (true) {
                        if (!buffer$iv.hasRemaining() && !last) {
                            restoreStateAfterRead();
                            tryTerminate$ktor_io();
                            rc = last;
                            break;
                        }
                        boolean rc2 = visitor.invoke(buffer$iv, Boolean.valueOf(last)).booleanValue();
                        afterBufferVisited(buffer$iv, capacity$iv);
                        if (!rc2 || (last && !buffer$iv.hasRemaining())) {
                            break;
                        }
                    }
                    return true;
                }
            } finally {
                restoreStateAfterRead();
                tryTerminate$ktor_io();
            }
        }
        if (rc || getClosed() == null) {
            return rc;
        }
        visitor.invoke(ReadWriteBufferStateKt.getEmptyByteBuffer(), true);
        return true;
    }

    private final int afterBufferVisited(ByteBuffer buffer, RingBufferCapacity capacity) {
        int consumed = buffer.position() - this.readPosition;
        if (consumed > 0) {
            if (!capacity.tryReadExact(consumed)) {
                throw new IllegalStateException("Consumed more bytes than available");
            }
            bytesRead(buffer, capacity, consumed);
            prepareBuffer(buffer, this.readPosition, capacity._availableForRead$internal);
        }
        return consumed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object readUTF8LineToAscii(Appendable out, int limit, Continuation<? super Boolean> continuation) {
        if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            Throwable cause = getClosedCause();
            if (cause != null) {
                throw cause;
            }
            return Boxing.boxBoolean(false);
        }
        return readUTF8LineToUtf8Suspend(out, limit, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(9:72|73|74|75|76|77|78|79|(1:81)(5:82|36|37|(1:39)|93)) */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0128, code lost:
    
        r6 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x012b, code lost:
    
        r6 = r16;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0028. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x00a4  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:82:0x0122 -> B:36:0x0123). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:86:0x0131 -> B:37:0x00dc). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readUTF8LineToUtf8Suspend(java.lang.Appendable r19, int r20, kotlin.coroutines.Continuation<? super java.lang.Boolean> r21) {
        /*
            Method dump skipped, instructions count: 440
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readUTF8LineToUtf8Suspend(java.lang.Appendable, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0055 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readUTF8Line$suspendImpl(io.ktor.utils.io.ByteBufferChannel r5, int r6, kotlin.coroutines.Continuation<? super java.lang.String> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteBufferChannel$readUTF8Line$1
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.utils.io.ByteBufferChannel$readUTF8Line$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readUTF8Line$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$readUTF8Line$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readUTF8Line$1
            r0.<init>(r5, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L36;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2c:
            java.lang.Object r5 = r0.L$0
            java.lang.StringBuilder r5 = (java.lang.StringBuilder) r5
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r5
            r5 = r7
            goto L4d
        L36:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r3 = r2
            java.lang.Appendable r3 = (java.lang.Appendable) r3
            r0.L$0 = r2
            r4 = 1
            r0.label = r4
            java.lang.Object r5 = r5.readUTF8LineTo(r3, r6, r0)
            if (r5 != r1) goto L4d
            return r1
        L4d:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 != 0) goto L57
            r5 = 0
            return r5
        L57:
            java.lang.String r5 = r2.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readUTF8Line$suspendImpl(io.ktor.utils.io.ByteBufferChannel, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readRemaining$suspendImpl(ByteBufferChannel $this, long limit, Continuation<? super ByteReadPacket> continuation) {
        if ($this.isClosedForWrite()) {
            Throwable it = $this.getClosedCause();
            if (it != null) {
                ByteBufferChannelKt.rethrowClosed(it);
                throw new KotlinNothingValueException();
            }
            return $this.remainingPacket(limit);
        }
        return $this.readRemainingSuspend(limit, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ByteReadPacket remainingPacket(long limit) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        long j = limit;
        try {
            BytePacketBuilder bytePacketBuilder2 = bytePacketBuilder;
            ChunkBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(bytePacketBuilder2, 1, null);
            while (true) {
                ChunkBuffer chunkBuffer = prepareWriteHead;
                try {
                    ChunkBuffer chunkBuffer2 = chunkBuffer;
                    if (chunkBuffer2.getLimit() - chunkBuffer2.getWritePosition() > j) {
                        chunkBuffer2.resetForWrite((int) j);
                    }
                    j -= readAsMuchAsPossible$default(this, chunkBuffer2, 0, 0, 6, null);
                    if (j > 0 && !isClosedForRead()) {
                        prepareWriteHead = UnsafeKt.prepareWriteHead(bytePacketBuilder2, 1, chunkBuffer);
                    } else {
                        bytePacketBuilder2.afterHeadWrite();
                        return bytePacketBuilder.build();
                    }
                } catch (Throwable th) {
                    bytePacketBuilder2.afterHeadWrite();
                    throw th;
                }
            }
        } catch (Throwable th2) {
            bytePacketBuilder.release();
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0028. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0097 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x012e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v15, types: [io.ktor.utils.io.core.Output] */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v2, types: [io.ktor.utils.io.core.Output] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00df -> B:15:0x00ed). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readRemainingSuspend(long r20, kotlin.coroutines.Continuation<? super io.ktor.utils.io.core.ByteReadPacket> r22) {
        /*
            Method dump skipped, instructions count: 380
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readRemainingSuspend(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeReadOp() {
        Continuation $this$resumeReadOp_u24lambda_u24134 = (Continuation) _readOp$FU.getAndSet(this, null);
        if ($this$resumeReadOp_u24lambda_u24134 != null) {
            ClosedElement closed = getClosed();
            Throwable closedCause = closed != null ? closed.getCause() : null;
            if (closedCause == null) {
                Result.Companion companion = Result.INSTANCE;
                $this$resumeReadOp_u24lambda_u24134.resumeWith(Result.m510constructorimpl(true));
            } else {
                Result.Companion companion2 = Result.INSTANCE;
                $this$resumeReadOp_u24lambda_u24134.resumeWith(Result.m510constructorimpl(ResultKt.createFailure(closedCause)));
            }
        }
    }

    private final void resumeReadOp(Function0<? extends Throwable> exception) {
        Continuation continuation = (Continuation) _readOp$FU.getAndSet(this, null);
        if (continuation != null) {
            Result.Companion companion = Result.INSTANCE;
            continuation.resumeWith(Result.m510constructorimpl(ResultKt.createFailure(exception.invoke())));
        }
    }

    private final void resumeWriteOp() {
        Continuation writeOp;
        ClosedElement closed;
        do {
            writeOp = getWriteOp();
            if (writeOp == null) {
                return;
            }
            closed = getClosed();
            if (closed == null && this.joining != null) {
                ReadWriteBufferState state = getState();
                if (!(state instanceof ReadWriteBufferState.Writing) && !(state instanceof ReadWriteBufferState.ReadingWriting) && state != ReadWriteBufferState.Terminated.INSTANCE) {
                    return;
                }
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_writeOp$FU, this, writeOp, null));
        Result.Companion companion = Result.INSTANCE;
        writeOp.resumeWith(Result.m510constructorimpl(closed == null ? Unit.INSTANCE : ResultKt.createFailure(closed.getSendException())));
    }

    private final void resumeClosed(Throwable cause) {
        Continuation continuation = (Continuation) _readOp$FU.getAndSet(this, null);
        if (continuation != null) {
            if (cause != null) {
                Result.Companion companion = Result.INSTANCE;
                continuation.resumeWith(Result.m510constructorimpl(ResultKt.createFailure(cause)));
            } else {
                RingBufferCapacity this_$iv = getState().capacity;
                Boolean valueOf = Boolean.valueOf(this_$iv._availableForRead$internal > 0);
                Result.Companion companion2 = Result.INSTANCE;
                continuation.resumeWith(Result.m510constructorimpl(valueOf));
            }
        }
        Continuation continuation2 = (Continuation) _writeOp$FU.getAndSet(this, null);
        if (continuation2 != null) {
            Result.Companion companion3 = Result.INSTANCE;
            continuation2.resumeWith(Result.m510constructorimpl(ResultKt.createFailure(cause == null ? new ClosedWriteChannelException(ByteBufferChannelKt.DEFAULT_CLOSE_MESSAGE) : cause)));
        }
    }

    static /* synthetic */ Object awaitContent$suspendImpl(ByteBufferChannel $this, Continuation<? super Unit> continuation) {
        Object readSuspend = $this.readSuspend(1, continuation);
        return readSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? readSuspend : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object readSuspend(int size, Continuation<? super Boolean> continuation) {
        RingBufferCapacity capacity = getState().capacity;
        if (capacity._availableForRead$internal >= size) {
            return Boxing.boxBoolean(true);
        }
        ClosedElement closedValue = getClosed();
        if (closedValue != null) {
            Throwable it = closedValue.getCause();
            if (it != null) {
                ByteBufferChannelKt.rethrowClosed(it);
                throw new KotlinNothingValueException();
            }
            RingBufferCapacity afterCapacity = getState().capacity;
            boolean flush = afterCapacity.flush();
            boolean result = flush && afterCapacity._availableForRead$internal >= size;
            if (getReadOp() != null) {
                throw new IllegalStateException("Read operation is already in progress");
            }
            return Boxing.boxBoolean(result);
        }
        if (size == 1) {
            return readSuspendImpl(1, continuation);
        }
        return readSuspendLoop(size, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x00a4 -> B:12:0x00aa). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readSuspendLoop(int r9, kotlin.coroutines.Continuation<? super java.lang.Boolean> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1
            if (r0 == 0) goto L14
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1
            r0.<init>(r8, r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            switch(r2) {
                case 0: goto L3c;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2d:
            int r9 = r0.I$0
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            kotlin.ResultKt.throwOnFailure(r10)
            r4 = r2
            r2 = r1
            r1 = r0
            r0 = r10
            goto Laa
        L3c:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r8
        L40:
            io.ktor.utils.io.internal.ReadWriteBufferState r4 = r2.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r4 = r4.capacity
            r5 = 0
            int r4 = r4._availableForRead$internal
            r5 = 1
            if (r4 < r9) goto L52
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r1
        L52:
            io.ktor.utils.io.internal.ClosedElement r4 = r2.getClosed()
            if (r4 == 0) goto L97
            r1 = 0
            java.lang.Throwable r6 = r4.getCause()
            if (r6 != 0) goto L8a
            io.ktor.utils.io.internal.ReadWriteBufferState r4 = r2.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r4 = r4.capacity
            boolean r6 = r4.flush()
            if (r6 == 0) goto L72
            r6 = 0
            int r4 = r4._availableForRead$internal
            if (r4 < r9) goto L72
            r9 = r5
            goto L73
        L72:
            r9 = r3
        L73:
            kotlin.coroutines.Continuation r4 = r2.getReadOp()
            if (r4 != 0) goto L82
            if (r9 == 0) goto L7d
            r3 = r5
        L7d:
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r9
        L82:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "Read operation is already in progress"
            r3.<init>(r4)
            throw r3
        L8a:
            java.lang.Throwable r3 = r4.getCause()
            io.ktor.utils.io.ByteBufferChannelKt.access$rethrowClosed(r3)
            kotlin.KotlinNothingValueException r3 = new kotlin.KotlinNothingValueException
            r3.<init>()
            throw r3
        L97:
            r0.L$0 = r2
            r0.I$0 = r9
            r0.label = r5
            java.lang.Object r4 = r2.readSuspendImpl(r9, r0)
            if (r4 != r1) goto La4
            return r1
        La4:
            r7 = r0
            r0 = r10
            r10 = r4
            r4 = r2
            r2 = r1
            r1 = r7
        Laa:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 != 0) goto Lb7
            java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r10
        Lb7:
            r10 = r0
            r0 = r1
            r1 = r2
            r2 = r4
            goto L40
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readSuspendLoop(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean readSuspendPredicate(int size) {
        ReadWriteBufferState state = getState();
        RingBufferCapacity this_$iv = state.capacity;
        return this_$iv._availableForRead$internal < size && (this.joining == null || getWriteOp() == null || !(state == ReadWriteBufferState.IdleEmpty.INSTANCE || (state instanceof ReadWriteBufferState.IdleNonEmpty)));
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x0114, code lost:
    
        if (r4 == false) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x011a, code lost:
    
        return kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED();
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00cd A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.Object suspensionForSize(int r14, kotlin.coroutines.Continuation<? super java.lang.Boolean> r15) {
        /*
            Method dump skipped, instructions count: 297
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.suspensionForSize(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readSuspendImpl(int r9, kotlin.coroutines.Continuation<? super java.lang.Boolean> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1
            if (r0 == 0) goto L14
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1 r0 = (io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1
            r0.<init>(r8, r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L39;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2c:
            int r9 = r0.I$0
            java.lang.Object r9 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r9 = (io.ktor.utils.io.ByteBufferChannel) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L37
            r1 = r10
            goto L95
        L37:
            r1 = move-exception
            goto L98
        L39:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r8
            r3 = r2
            r4 = 0
            io.ktor.utils.io.internal.ReadWriteBufferState r5 = r3.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r6 = r5.capacity
            r7 = 0
            int r6 = r6._availableForRead$internal
            r7 = 1
            if (r6 >= r9) goto L5f
            io.ktor.utils.io.internal.JoiningState r6 = r3.joining
            if (r6 == 0) goto L5d
            kotlin.coroutines.Continuation r6 = r3.getWriteOp()
            if (r6 == 0) goto L5d
            io.ktor.utils.io.internal.ReadWriteBufferState$IdleEmpty r3 = io.ktor.utils.io.internal.ReadWriteBufferState.IdleEmpty.INSTANCE
            if (r5 == r3) goto L5f
            boolean r3 = r5 instanceof io.ktor.utils.io.internal.ReadWriteBufferState.IdleNonEmpty
            if (r3 != 0) goto L5f
        L5d:
            r3 = r7
            goto L60
        L5f:
            r3 = 0
        L60:
            if (r3 != 0) goto L68
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)
            return r9
        L68:
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L96
            r0.I$0 = r9     // Catch: java.lang.Throwable -> L96
            r0.label = r7     // Catch: java.lang.Throwable -> L96
            r3 = r0
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3     // Catch: java.lang.Throwable -> L96
            r4 = 0
            io.ktor.utils.io.internal.CancellableReusableContinuation<java.lang.Boolean> r5 = r2.readSuspendContinuationCache     // Catch: java.lang.Throwable -> L96
            r6 = r5
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Throwable -> L96
            r2.suspensionForSize(r9, r6)     // Catch: java.lang.Throwable -> L96
            kotlin.coroutines.Continuation r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r3)     // Catch: java.lang.Throwable -> L96
            java.lang.Object r9 = r5.completeSuspendBlock(r9)     // Catch: java.lang.Throwable -> L96
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()     // Catch: java.lang.Throwable -> L96
            if (r9 != r3) goto L90
            r3 = r0
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3     // Catch: java.lang.Throwable -> L96
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r3)     // Catch: java.lang.Throwable -> L96
        L90:
            if (r9 != r1) goto L93
            return r1
        L93:
            r1 = r9
            r9 = r2
        L95:
            return r1
        L96:
            r1 = move-exception
            r9 = r2
        L98:
            r2 = 0
            r9.setReadOp(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readSuspendImpl(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldResumeReadOp() {
        return this.joining != null && (getState() == ReadWriteBufferState.IdleEmpty.INSTANCE || (getState() instanceof ReadWriteBufferState.IdleNonEmpty));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean writeSuspendPredicate(int size) {
        JoiningState joined = this.joining;
        ReadWriteBufferState state = getState();
        ClosedElement closed = getClosed();
        if (closed != null) {
            return false;
        }
        if (joined == null) {
            RingBufferCapacity this_$iv = state.capacity;
            if (this_$iv._availableForWrite$internal >= size || state == ReadWriteBufferState.IdleEmpty.INSTANCE) {
                return false;
            }
            return true;
        }
        if (state == ReadWriteBufferState.Terminated.INSTANCE || (state instanceof ReadWriteBufferState.Writing) || (state instanceof ReadWriteBufferState.ReadingWriting)) {
            return false;
        }
        return true;
    }

    public final Object tryWriteSuspend$ktor_io(int size, Continuation<? super Unit> continuation) {
        Throwable it;
        if (!writeSuspendPredicate(size)) {
            ClosedElement closed = getClosed();
            if (closed != null && (it = closed.getSendException()) != null) {
                ByteBufferChannelKt.rethrowClosed(it);
                throw new KotlinNothingValueException();
            }
            return Unit.INSTANCE;
        }
        this.writeSuspensionSize = size;
        if (this.attachedJob != null) {
            Object invoke = this.writeSuspension.invoke(continuation);
            if (invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke : Unit.INSTANCE;
        }
        CancellableReusableContinuation c = this.writeSuspendContinuationCache;
        this.writeSuspension.invoke(c);
        Object completeSuspendBlock = c.completeSuspendBlock(IntrinsicsKt.intercepted(continuation));
        if (completeSuspendBlock == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return completeSuspendBlock == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? completeSuspendBlock : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeSuspend(int r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof io.ktor.utils.io.ByteBufferChannel$writeSuspend$3
            if (r0 == 0) goto L14
            r0 = r11
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel$writeSuspend$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$writeSuspend$3
            r0.<init>(r9, r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L37;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2c:
            r10 = 0
            int r2 = r0.I$0
            java.lang.Object r3 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r3 = (io.ktor.utils.io.ByteBufferChannel) r3
            kotlin.ResultKt.throwOnFailure(r11)
            goto L77
        L37:
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = r9
            r3 = r2
            r2 = r10
        L3d:
            boolean r10 = r3.writeSuspendPredicate(r2)
            if (r10 == 0) goto L79
            r10 = 0
            r0.L$0 = r3
            r0.I$0 = r2
            r4 = 1
            r0.label = r4
            r5 = r0
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r6 = 0
            kotlinx.coroutines.CancellableContinuationImpl r7 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r5)
            r7.<init>(r8, r4)
            r7.initCancellability()
            r4 = r7
            kotlinx.coroutines.CancellableContinuation r4 = (kotlinx.coroutines.CancellableContinuation) r4
            r8 = 0
            access$writeSuspendBlock(r3, r2, r4)
            java.lang.Object r4 = r7.getResult()
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r4 != r5) goto L74
            r5 = r0
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r5)
        L74:
            if (r4 != r1) goto L77
            return r1
        L77:
            goto L3d
        L79:
            io.ktor.utils.io.internal.ClosedElement r10 = r3.getClosed()
            if (r10 == 0) goto L90
            java.lang.Throwable r10 = r10.getSendException()
            if (r10 != 0) goto L86
            goto L90
        L86:
            r1 = 0
            io.ktor.utils.io.ByteBufferChannelKt.access$rethrowClosed(r10)
            kotlin.KotlinNothingValueException r2 = new kotlin.KotlinNothingValueException
            r2.<init>()
            throw r2
        L90:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeSuspend(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0069, code lost:
    
        if (r4 == false) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006b, code lost:
    
        flushImpl(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0072, code lost:
    
        if (shouldResumeReadOp() == false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0074, code lost:
    
        resumeReadOp();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0077, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void writeSuspendBlock(int r10, kotlinx.coroutines.CancellableContinuation<? super kotlin.Unit> r11) {
        /*
            r9 = this;
        L1:
            io.ktor.utils.io.internal.ClosedElement r0 = r9.getClosed()
            if (r0 == 0) goto L18
            java.lang.Throwable r0 = r0.getSendException()
            if (r0 != 0) goto Le
            goto L18
        Le:
            r1 = 0
            io.ktor.utils.io.ByteBufferChannelKt.access$rethrowClosed(r0)
            kotlin.KotlinNothingValueException r2 = new kotlin.KotlinNothingValueException
            r2.<init>()
            throw r2
        L18:
            boolean r0 = r9.writeSuspendPredicate(r10)
            if (r0 != 0) goto L2d
            r0 = r11
            kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0
            kotlin.Result$Companion r1 = kotlin.Result.INSTANCE
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            java.lang.Object r1 = kotlin.Result.m510constructorimpl(r1)
            r0.resumeWith(r1)
            goto L6b
        L2d:
            r0 = r9
            r1 = r9
            r2 = 0
        L30:
            r3 = 0
            kotlin.coroutines.Continuation r3 = r9.getWriteOp()
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L3d
            r6 = r4
            goto L3e
        L3d:
            r6 = r5
        L3e:
            if (r6 == 0) goto L78
            r6 = 0
            boolean r6 = r9.writeSuspendPredicate(r10)
            if (r6 != 0) goto L49
            r4 = r5
            goto L69
        L49:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r6 = io.ktor.utils.io.ByteBufferChannel._writeOp$FU
            r7 = r11
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r8 = 0
            boolean r6 = androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r6, r0, r8, r7)
            if (r6 == 0) goto L30
            r6 = 0
            boolean r6 = r9.writeSuspendPredicate(r10)
            if (r6 != 0) goto L69
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r6 = io.ktor.utils.io.ByteBufferChannel._writeOp$FU
            r7 = r11
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            boolean r6 = androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r6, r0, r7, r8)
            if (r6 != 0) goto L68
            goto L69
        L68:
            r4 = r5
        L69:
            if (r4 == 0) goto L1
        L6b:
            r9.flushImpl(r10)
            boolean r0 = r9.shouldResumeReadOp()
            if (r0 == 0) goto L77
            r9.resumeReadOp()
        L77:
            return
        L78:
            r4 = 0
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "Operation is already in progress"
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeSuspendBlock(int, kotlinx.coroutines.CancellableContinuation):void");
    }

    private final ReadWriteBufferState.Initial newBuffer() {
        ReadWriteBufferState.Initial borrow = this.pool.borrow();
        ReadWriteBufferState.Initial $this$newBuffer_u24lambda_u24151 = borrow;
        $this$newBuffer_u24lambda_u24151.capacity.resetForWrite();
        return borrow;
    }

    private final void releaseBuffer(ReadWriteBufferState.Initial buffer) {
        this.pool.recycle(buffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /* renamed from: peekTo-lBXzO7A$suspendImpl, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object m222peekTolBXzO7A$suspendImpl(io.ktor.utils.io.ByteBufferChannel r16, final java.nio.ByteBuffer r17, final long r18, final long r20, long r22, final long r24, kotlin.coroutines.Continuation<? super java.lang.Long> r26) {
        /*
            r0 = r26
            boolean r1 = r0 instanceof io.ktor.utils.io.ByteBufferChannel$peekTo$1
            if (r1 == 0) goto L18
            r1 = r0
            io.ktor.utils.io.ByteBufferChannel$peekTo$1 r1 = (io.ktor.utils.io.ByteBufferChannel$peekTo$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L18
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r2 = r16
            goto L1f
        L18:
            io.ktor.utils.io.ByteBufferChannel$peekTo$1 r1 = new io.ktor.utils.io.ByteBufferChannel$peekTo$1
            r2 = r16
            r1.<init>(r2, r0)
        L1f:
            java.lang.Object r3 = r1.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            switch(r4) {
                case 0: goto L41;
                case 1: goto L32;
                default: goto L2a;
            }
        L2a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L32:
            java.lang.Object r0 = r1.L$0
            r2 = r0
            kotlin.jvm.internal.Ref$IntRef r2 = (kotlin.jvm.internal.Ref.IntRef) r2
            kotlin.ResultKt.throwOnFailure(r3)     // Catch: java.io.EOFException -> L3d
            r26 = r3
            goto L8b
        L3d:
            r0 = move-exception
            r26 = r3
            goto L93
        L41:
            kotlin.ResultKt.throwOnFailure(r3)
            r2 = r16
            r4 = r18
            r6 = r22
            r8 = r20
            r10 = r24
            r12 = r17
            kotlin.jvm.internal.Ref$IntRef r13 = new kotlin.jvm.internal.Ref$IntRef
            r13.<init>()
            long r14 = r6 + r8
            r26 = r3
            r3 = 4088(0xff8, double:2.0197E-320)
            long r3 = kotlin.ranges.RangesKt.coerceAtMost(r14, r3)
            int r3 = (int) r3
            io.ktor.utils.io.ByteBufferChannel$peekTo$2 r4 = new io.ktor.utils.io.ByteBufferChannel$peekTo$2     // Catch: java.io.EOFException -> L91
            r22 = r18
            r16 = r4
            r17 = r8
            r19 = r10
            r21 = r12
            r24 = r13
            r16.<init>()     // Catch: java.io.EOFException -> L8c
            r11 = r16
            r6 = r17
            r8 = r19
            r10 = r21
            r4 = r22
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11     // Catch: java.io.EOFException -> L91
            r1.L$0 = r13     // Catch: java.io.EOFException -> L91
            r12 = 1
            r1.label = r12     // Catch: java.io.EOFException -> L91
            java.lang.Object r11 = r2.read(r3, r11, r1)     // Catch: java.io.EOFException -> L91
            if (r11 != r0) goto L8a
            return r0
        L8a:
            r2 = r13
        L8b:
            goto L93
        L8c:
            r0 = move-exception
            r13 = r24
            r2 = r13
            goto L93
        L91:
            r0 = move-exception
            r2 = r13
        L93:
            int r0 = r2.element
            long r3 = (long) r0
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.m222peekTolBXzO7A$suspendImpl(io.ktor.utils.io.ByteBufferChannel, java.nio.ByteBuffer, long, long, long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public String toString() {
        return "ByteBufferChannel(" + hashCode() + ", " + getState() + ')';
    }
}
