package io.ktor.utils.io;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.BuffersKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.InputArraysKt;
import io.ktor.utils.io.core.InputPrimitivesKt;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UTF8Kt;
import io.ktor.utils.io.core.internal.UnsafeKt;
import io.ktor.utils.io.internal.AwaitingSlot;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ByteChannelSequential.kt */
@Metadata(d1 = {"\u0000à\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b<\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b&\u0018\u00002\u00030Ö\u00012\u00030×\u00012\u00030Ø\u00012\u00020{2\u00030Ù\u00012\u00030Ú\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000e\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0004¢\u0006\u0004\b\u000f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0004¢\u0006\u0004\b\u0010\u0010\rJ\u001b\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0013J\u001b\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0013J\u0013\u0010\u0018\u001a\u00020\u000bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u000bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0019J\u0013\u0010\u001c\u001a\u00020\u0003H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u0019J\u001b\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\tH\u0084@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0013J\u000f\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b\u001f\u0010 J\u0019\u0010#\u001a\u00020\u00032\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016¢\u0006\u0004\b#\u0010$J#\u0010(\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\t2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&H\u0002¢\u0006\u0004\b(\u0010)J\u0019\u0010*\u001a\u00020\u00032\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016¢\u0006\u0004\b*\u0010$J\u000f\u0010+\u001a\u00020\u000bH\u0002¢\u0006\u0004\b+\u0010,J\u0017\u0010.\u001a\u00020\t2\u0006\u0010-\u001a\u00020\tH\u0016¢\u0006\u0004\b.\u0010/J\u001b\u0010.\u001a\u0002002\u0006\u00101\u001a\u000200H\u0096@ø\u0001\u0000¢\u0006\u0004\b.\u00102J#\u00104\u001a\u0002002\u0006\u00101\u001a\u0002002\u0006\u00103\u001a\u000200H\u0082@ø\u0001\u0000¢\u0006\u0004\b4\u00105J\u000f\u00106\u001a\u00020\u000bH\u0016¢\u0006\u0004\b6\u0010,J\u0017\u00108\u001a\u00020\u000b2\u0006\u00107\u001a\u00020\tH\u0016¢\u0006\u0004\b8\u0010\rJ\u000f\u00109\u001a\u00020\u000bH\u0002¢\u0006\u0004\b9\u0010,J\u000f\u0010:\u001a\u00020\u000bH\u0002¢\u0006\u0004\b:\u0010,J\u0017\u0010:\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020&H\u0002¢\u0006\u0004\b:\u0010;J\u000f\u0010<\u001a\u00020\u000bH\u0016¢\u0006\u0004\b<\u0010,J\u000f\u0010=\u001a\u00020\u0003H\u0002¢\u0006\u0004\b=\u0010>J\u000f\u0010?\u001a\u00020\u000bH\u0002¢\u0006\u0004\b?\u0010,JA\u0010G\u001a\u0002002\u0006\u0010A\u001a\u00020@2\u0006\u0010B\u001a\u0002002\u0006\u0010C\u001a\u0002002\u0006\u0010D\u001a\u0002002\u0006\u00101\u001a\u000200H\u0086@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\bE\u0010FJ\u000f\u0010H\u001a\u00020\u000bH\u0004¢\u0006\u0004\bH\u0010,J\u001b\u0010M\u001a\u00020\t2\u0006\u0010J\u001a\u00020IH\u0080@ø\u0001\u0000¢\u0006\u0004\bK\u0010LJ\u001b\u0010M\u001a\u00020\t2\u0006\u0010J\u001a\u00020\u0001H\u0096@ø\u0001\u0000¢\u0006\u0004\bM\u0010NJ+\u0010M\u001a\u00020\t2\u0006\u0010J\u001a\u00020O2\u0006\u0010C\u001a\u00020\t2\u0006\u0010P\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0004\bM\u0010QJ\u000f\u0010R\u001a\u00020\tH\u0004¢\u0006\u0004\bR\u0010SJ\u0013\u0010T\u001a\u00020\u0003H\u0096@ø\u0001\u0000¢\u0006\u0004\bT\u0010\u0019J\u0013\u0010U\u001a\u00020\u0003H\u0082@ø\u0001\u0000¢\u0006\u0004\bU\u0010\u0019J\u0013\u0010W\u001a\u00020VH\u0096@ø\u0001\u0000¢\u0006\u0004\bW\u0010\u0019J\u0013\u0010X\u001a\u00020VH\u0082@ø\u0001\u0000¢\u0006\u0004\bX\u0010\u0019J\u0013\u0010Z\u001a\u00020YH\u0096@ø\u0001\u0000¢\u0006\u0004\bZ\u0010\u0019J\u0013\u0010[\u001a\u00020YH\u0082@ø\u0001\u0000¢\u0006\u0004\b[\u0010\u0019J\u0013\u0010]\u001a\u00020\\H\u0096@ø\u0001\u0000¢\u0006\u0004\b]\u0010\u0019J\u0013\u0010^\u001a\u00020\\H\u0082@ø\u0001\u0000¢\u0006\u0004\b^\u0010\u0019J#\u0010_\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020I2\u0006\u0010-\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0004\b_\u0010`J#\u0010_\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u00012\u0006\u0010-\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0004\b_\u0010aJ+\u0010_\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020O2\u0006\u0010C\u001a\u00020\t2\u0006\u0010P\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0004\b_\u0010QJ#\u0010b\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020I2\u0006\u0010-\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0004\bb\u0010`J+\u0010b\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020O2\u0006\u0010C\u001a\u00020\t2\u0006\u0010P\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0004\bb\u0010QJ\u0013\u0010c\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0004\bc\u0010\u0019J\u0013\u0010d\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0004\bd\u0010\u0019J\u0013\u0010e\u001a\u000200H\u0096@ø\u0001\u0000¢\u0006\u0004\be\u0010\u0019J\u0013\u0010f\u001a\u000200H\u0082@ø\u0001\u0000¢\u0006\u0004\bf\u0010\u0019J\u001b\u0010i\u001a\u00020h2\u0006\u0010g\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0004\bi\u0010\u0013J#\u0010k\u001a\u00020h2\u0006\u0010j\u001a\u00020&2\u0006\u0010g\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0004\bk\u0010lJ\u001b\u0010n\u001a\u00020h2\u0006\u0010m\u001a\u000200H\u0096@ø\u0001\u0000¢\u0006\u0004\bn\u00102J#\u0010o\u001a\u00020h2\u0006\u0010j\u001a\u00020&2\u0006\u0010m\u001a\u000200H\u0082@ø\u0001\u0000¢\u0006\u0004\bo\u0010pJ(\u0010u\u001a\u00020\u000b2\u0017\u0010t\u001a\u0013\u0012\u0004\u0012\u00020r\u0012\u0004\u0012\u00020\u000b0q¢\u0006\u0002\bsH\u0017¢\u0006\u0004\bu\u0010vJ\u0013\u0010x\u001a\u00020wH\u0096@ø\u0001\u0000¢\u0006\u0004\bx\u0010\u0019J\u0013\u0010y\u001a\u00020wH\u0082@ø\u0001\u0000¢\u0006\u0004\by\u0010\u0019J<\u0010~\u001a\u00020\u000b2'\u0010t\u001a#\b\u0001\u0012\u0004\u0012\u00020{\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0|\u0012\u0006\u0012\u0004\u0018\u00010}0z¢\u0006\u0002\bsH\u0097@ø\u0001\u0000¢\u0006\u0004\b~\u0010\u007fJ \u0010\u0081\u0001\u001a\u0005\u0018\u00010\u0080\u00012\u0006\u0010m\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0005\b\u0081\u0001\u0010\u0013J8\u0010\u0086\u0001\u001a\u00020\u0003\"\u000f\b\u0000\u0010\u0084\u0001*\b0\u0082\u0001j\u0003`\u0083\u00012\u0007\u0010\u0085\u0001\u001a\u00028\u00002\u0006\u0010m\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u001c\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0011\u001a\u00020\tH\u0016¢\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001J\u001c\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0011\u001a\u00020\tH\u0002¢\u0006\u0006\b\u008a\u0001\u0010\u0089\u0001J\u0012\u0010\u008b\u0001\u001a\u00020{H\u0016¢\u0006\u0006\b\u008b\u0001\u0010\u008c\u0001J\"\u0010\u008f\u0001\u001a\u0002002\u0006\u0010J\u001a\u00020\u00002\u0006\u0010m\u001a\u000200H\u0000¢\u0006\u0006\b\u008d\u0001\u0010\u008e\u0001J\u001e\u0010\u0091\u0001\u001a\u00020\t2\u0007\u0010\u0090\u0001\u001a\u00020\u0001H\u0096@ø\u0001\u0000¢\u0006\u0005\b\u0091\u0001\u0010NJ.\u0010\u0091\u0001\u001a\u00020\t2\u0007\u0010\u0090\u0001\u001a\u00020O2\u0006\u0010C\u001a\u00020\t2\u0006\u0010P\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0005\b\u0091\u0001\u0010QJ\u001e\u0010\u0092\u0001\u001a\u00020\t2\u0007\u0010\u0090\u0001\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0005\b\u0092\u0001\u0010NJ.\u0010\u0092\u0001\u001a\u00020\t2\u0007\u0010\u0090\u0001\u001a\u00020O2\u0006\u0010C\u001a\u00020\t2\u0006\u0010P\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0005\b\u0092\u0001\u0010QJ\u001f\u0010\u0094\u0001\u001a\u00020\u000b2\u0007\u0010\u0093\u0001\u001a\u00020VH\u0096@ø\u0001\u0000¢\u0006\u0006\b\u0094\u0001\u0010\u0095\u0001J\u001f\u0010\u0097\u0001\u001a\u00020\u000b2\u0007\u0010\u0096\u0001\u001a\u00020YH\u0096@ø\u0001\u0000¢\u0006\u0006\b\u0097\u0001\u0010\u0098\u0001J\u001f\u0010\u009a\u0001\u001a\u00020\u000b2\u0007\u0010\u0099\u0001\u001a\u00020\\H\u0096@ø\u0001\u0000¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J7\u0010¡\u0001\u001a\u00020\u000b2\u0007\u0010\u009c\u0001\u001a\u00020@2\u0007\u0010\u009d\u0001\u001a\u00020\t2\u0007\u0010\u009e\u0001\u001a\u00020\tH\u0096@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0006\b\u009f\u0001\u0010 \u0001J\u001e\u0010¡\u0001\u001a\u00020\u000b2\u0007\u0010\u0090\u0001\u001a\u00020IH\u0096@ø\u0001\u0000¢\u0006\u0005\b¡\u0001\u0010LJ.\u0010¡\u0001\u001a\u00020\u000b2\u0007\u0010\u0090\u0001\u001a\u00020O2\u0006\u0010C\u001a\u00020\t2\u0006\u0010P\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0005\b¡\u0001\u0010QJ\u001e\u0010£\u0001\u001a\u00020\u000b2\u0007\u0010¢\u0001\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0005\b£\u0001\u0010\u0013J\u001e\u0010¥\u0001\u001a\u00020\u000b2\u0007\u0010¤\u0001\u001a\u000200H\u0096@ø\u0001\u0000¢\u0006\u0005\b¥\u0001\u00102J\u001f\u0010§\u0001\u001a\u00020\u000b2\u0007\u0010¦\u0001\u001a\u00020hH\u0096@ø\u0001\u0000¢\u0006\u0006\b§\u0001\u0010¨\u0001J\u001f\u0010ª\u0001\u001a\u00020\u000b2\u0007\u0010©\u0001\u001a\u00020wH\u0096@ø\u0001\u0000¢\u0006\u0006\bª\u0001\u0010«\u0001J?\u0010\u00ad\u0001\u001a\u00020\u000b2(\u0010¬\u0001\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0|\u0012\u0006\u0012\u0004\u0018\u00010}0z¢\u0006\u0002\bsH\u0097@ø\u0001\u0000¢\u0006\u0005\b\u00ad\u0001\u0010\u007fR\u001c\u0010\u0004\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\u000e\n\u0005\b\u0004\u0010®\u0001\u001a\u0005\b¯\u0001\u0010>R\u0016\u0010±\u0001\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b°\u0001\u0010SR\u0016\u0010³\u0001\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b²\u0001\u0010SR)\u0010¸\u0001\u001a\u00020\u00032\u0007\u0010´\u0001\u001a\u00020\u00038D@DX\u0084\u000e¢\u0006\u000f\u001a\u0005\bµ\u0001\u0010>\"\u0006\b¶\u0001\u0010·\u0001R.\u0010½\u0001\u001a\u0004\u0018\u00010!2\t\u0010´\u0001\u001a\u0004\u0018\u00010!8F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b¹\u0001\u0010º\u0001\"\u0006\b»\u0001\u0010¼\u0001R\u0017\u0010¾\u0001\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¾\u0001\u0010¿\u0001R\u001c\u0010Á\u0001\u001a\u00070}j\u0003`À\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÁ\u0001\u0010Â\u0001R\u0016\u0010Ã\u0001\u001a\u00020\u00038BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bÃ\u0001\u0010>R\u0016\u0010Ä\u0001\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÄ\u0001\u0010>R\u0016\u0010Å\u0001\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÅ\u0001\u0010>R\u001f\u0010Æ\u0001\u001a\u00020h8\u0004X\u0084\u0004¢\u0006\u0010\n\u0006\bÆ\u0001\u0010Ç\u0001\u001a\u0006\bÈ\u0001\u0010É\u0001R\u0018\u0010Ë\u0001\u001a\u00030Ê\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bË\u0001\u0010Ì\u0001R\u0017\u0010Ï\u0001\u001a\u0002008VX\u0096\u0004¢\u0006\b\u001a\u0006\bÍ\u0001\u0010Î\u0001R\u0017\u0010Ñ\u0001\u001a\u0002008VX\u0096\u0004¢\u0006\b\u001a\u0006\bÐ\u0001\u0010Î\u0001R\u001f\u0010Ò\u0001\u001a\u00020&8\u0004X\u0084\u0004¢\u0006\u0010\n\u0006\bÒ\u0001\u0010¿\u0001\u001a\u0006\bÓ\u0001\u0010Ô\u0001\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006Õ\u0001"}, d2 = {"Lio/ktor/utils/io/ByteChannelSequentialBase;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "initial", "", "autoFlush", "Lio/ktor/utils/io/pool/ObjectPool;", "pool", "<init>", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;ZLio/ktor/utils/io/pool/ObjectPool;)V", "", "count", "", "addBytesRead", "(I)V", "addBytesWritten", "afterRead", "afterWrite", "atLeast", "await", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitAtLeastNBytesAvailableForRead$ktor_io", "awaitAtLeastNBytesAvailableForRead", "awaitAtLeastNBytesAvailableForWrite$ktor_io", "awaitAtLeastNBytesAvailableForWrite", "awaitContent", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitFreeSpace", "awaitInternalAtLeast1$ktor_io", "awaitInternalAtLeast1", "awaitSuspend", "Lio/ktor/utils/io/WriterSuspendSession;", "beginWriteSession", "()Lio/ktor/utils/io/WriterSuspendSession;", "", "cause", "cancel", "(Ljava/lang/Throwable;)Z", "remaining", "Lio/ktor/utils/io/core/BytePacketBuilder;", "closeable", "checkClosed", "(ILio/ktor/utils/io/core/BytePacketBuilder;)V", "close", "completeReading", "()V", "n", "discard", "(I)I", "", "max", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "discarded0", "discardSuspend", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "endReadSession", "written", "endWriteSession", "ensureNotClosed", "ensureNotFailed", "(Lio/ktor/utils/io/core/BytePacketBuilder;)V", "flush", "flushImpl", "()Z", "flushWrittenBytes", "Lio/ktor/utils/io/bits/Memory;", "destination", "destinationOffset", TypedValues.CycleType.S_WAVE_OFFSET, "min", "peekTo-lBXzO7A", "(Ljava/nio/ByteBuffer;JJJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "peekTo", "prepareFlushedBytes", "Lio/ktor/utils/io/core/Buffer;", "dst", "readAvailable$ktor_io", "(Lio/ktor/utils/io/core/Buffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAvailable", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "length", "([BIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAvailableClosed", "()I", "readBoolean", "readBooleanSlow", "", "readByte", "readByteSlow", "", "readDouble", "readDoubleSlow", "", "readFloat", "readFloatSlow", "readFully", "(Lio/ktor/utils/io/core/Buffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readFullySuspend", "readInt", "readIntSlow", "readLong", "readLongSlow", ContentDisposition.Parameters.Size, "Lio/ktor/utils/io/core/ByteReadPacket;", "readPacket", "builder", "readPacketSuspend", "(Lio/ktor/utils/io/core/BytePacketBuilder;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "limit", "readRemaining", "readRemainingSuspend", "(Lio/ktor/utils/io/core/BytePacketBuilder;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/Function1;", "Lio/ktor/utils/io/ReadSession;", "Lkotlin/ExtensionFunctionType;", "consumer", "readSession", "(Lkotlin/jvm/functions/Function1;)V", "", "readShort", "readShortSlow", "Lkotlin/Function2;", "Lio/ktor/utils/io/SuspendableReadSession;", "Lkotlin/coroutines/Continuation;", "", "readSuspendableSession", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "readUTF8Line", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "out", "readUTF8LineTo", "(Ljava/lang/Appendable;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "request", "(I)Lio/ktor/utils/io/core/internal/ChunkBuffer;", "requestNextView", "startReadSession", "()Lio/ktor/utils/io/SuspendableReadSession;", "transferTo$ktor_io", "(Lio/ktor/utils/io/ByteChannelSequentialBase;J)J", "transferTo", "src", "writeAvailable", "writeAvailableSuspend", "b", "writeByte", "(BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "d", "writeDouble", "(DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "f", "writeFloat", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "memory", "startIndex", "endIndex", "writeFully-JT6ljtQ", "(Ljava/nio/ByteBuffer;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFully", "i", "writeInt", "l", "writeLong", "packet", "writePacket", "(Lio/ktor/utils/io/core/ByteReadPacket;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "s", "writeShort", "(SLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "visitor", "writeSuspendSession", "Z", "getAutoFlush", "getAvailableForRead", "availableForRead", "getAvailableForWrite", "availableForWrite", "<anonymous parameter 0>", "getClosed", "setClosed", "(Z)V", "closed", "getClosedCause", "()Ljava/lang/Throwable;", "setClosedCause", "(Ljava/lang/Throwable;)V", "closedCause", "flushBuffer", "Lio/ktor/utils/io/core/BytePacketBuilder;", "Lkotlinx/atomicfu/locks/SynchronizedObject;", "flushMutex", "Ljava/lang/Object;", "isCancelled", "isClosedForRead", "isClosedForWrite", "readable", "Lio/ktor/utils/io/core/ByteReadPacket;", "getReadable", "()Lio/ktor/utils/io/core/ByteReadPacket;", "Lio/ktor/utils/io/internal/AwaitingSlot;", "slot", "Lio/ktor/utils/io/internal/AwaitingSlot;", "getTotalBytesRead", "()J", "totalBytesRead", "getTotalBytesWritten", "totalBytesWritten", "writable", "getWritable", "()Lio/ktor/utils/io/core/BytePacketBuilder;", "ktor-io", "Lio/ktor/utils/io/ByteChannel;", "Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/utils/io/ByteWriteChannel;", "Lio/ktor/utils/io/HasReadSession;", "Lio/ktor/utils/io/HasWriteSession;"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public abstract class ByteChannelSequentialBase implements ByteChannel, ByteReadChannel, ByteWriteChannel, SuspendableReadSession, HasReadSession, HasWriteSession {
    private volatile /* synthetic */ int _availableForRead;
    private volatile /* synthetic */ Object _closed;
    private volatile /* synthetic */ Object _lastReadView;
    private volatile /* synthetic */ long _totalBytesRead;
    private volatile /* synthetic */ long _totalBytesWritten;
    private final boolean autoFlush;
    private volatile /* synthetic */ int channelSize;
    private final BytePacketBuilder flushBuffer;
    private final Object flushMutex;
    private volatile /* synthetic */ int lastReadAvailable$delegate;
    private volatile /* synthetic */ Object lastReadView$delegate;
    private final ByteReadPacket readable;
    private final AwaitingSlot slot;
    private final BytePacketBuilder writable;
    private static final /* synthetic */ AtomicLongFieldUpdater _totalBytesRead$FU = AtomicLongFieldUpdater.newUpdater(ByteChannelSequentialBase.class, "_totalBytesRead");
    private static final /* synthetic */ AtomicLongFieldUpdater _totalBytesWritten$FU = AtomicLongFieldUpdater.newUpdater(ByteChannelSequentialBase.class, "_totalBytesWritten");
    private static final /* synthetic */ AtomicIntegerFieldUpdater _availableForRead$FU = AtomicIntegerFieldUpdater.newUpdater(ByteChannelSequentialBase.class, "_availableForRead");
    private static final /* synthetic */ AtomicIntegerFieldUpdater channelSize$FU = AtomicIntegerFieldUpdater.newUpdater(ByteChannelSequentialBase.class, "channelSize");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _closed$FU = AtomicReferenceFieldUpdater.newUpdater(ByteChannelSequentialBase.class, Object.class, "_closed");

    @Override // io.ktor.utils.io.SuspendableReadSession
    public Object await(int i, Continuation<? super Boolean> continuation) {
        return await$suspendImpl(this, i, continuation);
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
    public Object readAvailable(ChunkBuffer chunkBuffer, Continuation<? super Integer> continuation) {
        return readAvailable$suspendImpl(this, chunkBuffer, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readAvailable(byte[] bArr, int i, int i2, Continuation<? super Integer> continuation) {
        return readAvailable$suspendImpl(this, bArr, i, i2, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readBoolean(Continuation<? super Boolean> continuation) {
        return readBoolean$suspendImpl(this, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readByte(Continuation<? super Byte> continuation) {
        return readByte$suspendImpl(this, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readDouble(Continuation<? super Double> continuation) {
        return readDouble$suspendImpl(this, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readFloat(Continuation<? super Float> continuation) {
        return readFloat$suspendImpl(this, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readFully(ChunkBuffer chunkBuffer, int i, Continuation<? super Unit> continuation) {
        return readFully$suspendImpl(this, chunkBuffer, i, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readFully(byte[] bArr, int i, int i2, Continuation<? super Unit> continuation) {
        return readFully$suspendImpl(this, bArr, i, i2, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readInt(Continuation<? super Integer> continuation) {
        return readInt$suspendImpl(this, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readLong(Continuation<? super Long> continuation) {
        return readLong$suspendImpl(this, continuation);
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
    public Object readShort(Continuation<? super Short> continuation) {
        return readShort$suspendImpl(this, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    @Deprecated(message = "Use read instead.")
    public Object readSuspendableSession(Function2<? super SuspendableReadSession, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        return readSuspendableSession$suspendImpl(this, function2, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readUTF8Line(int i, Continuation<? super String> continuation) {
        return readUTF8Line$suspendImpl(this, i, continuation);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public <A extends Appendable> Object readUTF8LineTo(A a, int i, Continuation<? super Boolean> continuation) {
        return readUTF8LineTo$suspendImpl(this, a, i, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeAvailable(ChunkBuffer chunkBuffer, Continuation<? super Integer> continuation) {
        return writeAvailable$suspendImpl(this, chunkBuffer, continuation);
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
    public Object writeFully(byte[] bArr, int i, int i2, Continuation<? super Unit> continuation) {
        return writeFully$suspendImpl(this, bArr, i, i2, continuation);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    /* renamed from: writeFully-JT6ljtQ */
    public Object mo225writeFullyJT6ljtQ(ByteBuffer byteBuffer, int i, int i2, Continuation<? super Unit> continuation) {
        return m226writeFullyJT6ljtQ$suspendImpl(this, byteBuffer, i, i2, continuation);
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

    public ByteChannelSequentialBase(ChunkBuffer initial, boolean autoFlush, ObjectPool<ChunkBuffer> pool) {
        Intrinsics.checkNotNullParameter(initial, "initial");
        Intrinsics.checkNotNullParameter(pool, "pool");
        this.autoFlush = autoFlush;
        this._lastReadView = ChunkBuffer.INSTANCE.getEmpty();
        this._totalBytesRead = 0L;
        this._totalBytesWritten = 0L;
        this._availableForRead = 0;
        this.channelSize = 0;
        this._closed = null;
        this.writable = new BytePacketBuilder(pool);
        this.readable = new ByteReadPacket(initial, pool);
        this.lastReadAvailable$delegate = 0;
        this.lastReadView$delegate = ChunkBuffer.INSTANCE.getEmpty();
        this.slot = new AwaitingSlot();
        this.flushMutex = new Object();
        this.flushBuffer = new BytePacketBuilder(null, 1, null);
        int count = (int) BuffersKt.remainingAll(initial);
        afterWrite(count);
        _availableForRead$FU.addAndGet(this, count);
    }

    public /* synthetic */ ByteChannelSequentialBase(ChunkBuffer chunkBuffer, boolean z, ObjectPool objectPool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(chunkBuffer, z, (i & 4) != 0 ? ChunkBuffer.INSTANCE.getPool() : objectPool);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public boolean getAutoFlush() {
        return this.autoFlush;
    }

    private final boolean isCancelled() {
        CloseElement closeElement = (CloseElement) this._closed;
        return (closeElement != null ? closeElement.getCause() : null) != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean getClosed() {
        return this._closed != null;
    }

    protected final void setClosed(boolean z) {
        throw new IllegalStateException("Setting is not allowed for closed".toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BytePacketBuilder getWritable() {
        return this.writable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ByteReadPacket getReadable() {
        return this.readable;
    }

    /* renamed from: getLastReadAvailable, reason: from getter */
    private final int getLastReadAvailable$delegate() {
        return this.lastReadAvailable$delegate;
    }

    private final void setLastReadAvailable(int i) {
        this.lastReadAvailable$delegate = i;
    }

    private final ChunkBuffer getLastReadView() {
        return (ChunkBuffer) this.lastReadView$delegate;
    }

    private final void setLastReadView(ChunkBuffer chunkBuffer) {
        this.lastReadView$delegate = chunkBuffer;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    /* renamed from: getAvailableForRead, reason: from getter */
    public int get_availableForRead() {
        return this._availableForRead;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public int getAvailableForWrite() {
        return Math.max(0, 4088 - this.channelSize);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public boolean isClosedForRead() {
        return isCancelled() || (getClosed() && this.channelSize == 0);
    }

    @Override // io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel
    public boolean isClosedForWrite() {
        return getClosed();
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    /* renamed from: getTotalBytesRead, reason: from getter */
    public long get_totalBytesRead() {
        return this._totalBytesRead;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    /* renamed from: getTotalBytesWritten, reason: from getter */
    public long get_totalBytesWritten() {
        return this._totalBytesWritten;
    }

    @Override // io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel
    public final Throwable getClosedCause() {
        CloseElement closeElement = (CloseElement) this._closed;
        if (closeElement != null) {
            return closeElement.getCause();
        }
        return null;
    }

    public final void setClosedCause(Throwable th) {
        throw new IllegalStateException("Closed cause shouldn't be changed directly".toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object awaitAtLeastNBytesAvailableForWrite$ktor_io(final int r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$1
            r0.<init>(r6, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L36;
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
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r2 = (io.ktor.utils.io.ByteChannelSequentialBase) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L63
        L36:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
        L3a:
            int r3 = r2.getAvailableForWrite()
            if (r3 >= r7) goto L64
            boolean r3 = r2.getClosed()
            if (r3 != 0) goto L64
            boolean r3 = r2.flushImpl()
            if (r3 != 0) goto L3a
            io.ktor.utils.io.internal.AwaitingSlot r3 = r2.slot
            io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$2 r4 = new io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$2
            r4.<init>()
            kotlin.jvm.functions.Function0 r4 = (kotlin.jvm.functions.Function0) r4
            r0.L$0 = r2
            r0.I$0 = r7
            r5 = 1
            r0.label = r5
            java.lang.Object r3 = r3.sleep(r4, r0)
            if (r3 != r1) goto L63
            return r1
        L63:
            goto L3a
        L64:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.awaitAtLeastNBytesAvailableForWrite$ktor_io(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[LOOP:0: B:13:0x003a->B:21:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object awaitAtLeastNBytesAvailableForRead$ktor_io(final int r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$1
            r0.<init>(r6, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L36;
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
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r2 = (io.ktor.utils.io.ByteChannelSequentialBase) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5d
        L36:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
        L3a:
            int r3 = r2.get_availableForRead()
            if (r3 >= r7) goto L5e
            boolean r3 = r2.isClosedForRead()
            if (r3 != 0) goto L5e
            io.ktor.utils.io.internal.AwaitingSlot r3 = r2.slot
            io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$2 r4 = new io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$2
            r4.<init>()
            kotlin.jvm.functions.Function0 r4 = (kotlin.jvm.functions.Function0) r4
            r0.L$0 = r2
            r0.I$0 = r7
            r5 = 1
            r0.label = r5
            java.lang.Object r3 = r3.sleep(r4, r0)
            if (r3 != r1) goto L5d
            return r1
        L5d:
            goto L3a
        L5e:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.awaitAtLeastNBytesAvailableForRead$ktor_io(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public void flush() {
        flushImpl();
    }

    private final boolean flushImpl() {
        if (this.writable.isEmpty()) {
            this.slot.resume();
            return false;
        }
        flushWrittenBytes();
        this.slot.resume();
        return true;
    }

    private final void flushWrittenBytes() {
        synchronized (this.flushMutex) {
            int size = this.writable.getSize();
            ChunkBuffer buffer = this.writable.stealAll$ktor_io();
            Intrinsics.checkNotNull(buffer);
            this.flushBuffer.writeChunkBuffer$ktor_io(buffer);
            _availableForRead$FU.addAndGet(this, size);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void prepareFlushedBytes() {
        synchronized (this.flushMutex) {
            UnsafeKt.unsafeAppend(this.readable, this.flushBuffer);
        }
    }

    private final void ensureNotClosed() {
        if (getClosed()) {
            Throwable closedCause = getClosedCause();
            if (closedCause != null) {
                throw closedCause;
            }
            throw new ClosedWriteChannelException("Channel " + this + " is already closed");
        }
    }

    private final void ensureNotFailed() {
        Throwable it = getClosedCause();
        if (it != null) {
            throw it;
        }
    }

    private final void ensureNotFailed(BytePacketBuilder closeable) {
        Throwable cause = getClosedCause();
        if (cause != null) {
            closeable.release();
            throw cause;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeByte$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r5, byte r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialBase$writeByte$1
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialBase$writeByte$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$writeByte$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$writeByte$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeByte$1
            r0.<init>(r5, r7)
        L19:
            java.lang.Object r7 = r0.result
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
            byte r5 = r0.B$0
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r6 = (io.ktor.utils.io.ByteChannelSequentialBase) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4a
        L37:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r5
            r0.B$0 = r6
            r0.label = r3
            java.lang.Object r2 = r5.awaitAtLeastNBytesAvailableForWrite$ktor_io(r3, r0)
            if (r2 != r1) goto L47
            return r1
        L47:
            r4 = r6
            r6 = r5
            r5 = r4
        L4a:
            io.ktor.utils.io.core.BytePacketBuilder r1 = r6.writable
            byte r2 = (byte) r5
            r1.writeByte(r2)
            r6.afterWrite(r3)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeByte$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, byte, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeShort$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r5, short r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialBase$writeShort$1
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialBase$writeShort$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$writeShort$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$writeShort$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeShort$1
            r0.<init>(r5, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
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
            short r5 = r0.S$0
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r6 = (io.ktor.utils.io.ByteChannelSequentialBase) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4b
        L37:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r5
            r0.S$0 = r6
            r2 = 1
            r0.label = r2
            java.lang.Object r2 = r5.awaitAtLeastNBytesAvailableForWrite$ktor_io(r3, r0)
            if (r2 != r1) goto L48
            return r1
        L48:
            r4 = r6
            r6 = r5
            r5 = r4
        L4b:
            io.ktor.utils.io.core.BytePacketBuilder r1 = r6.writable
            io.ktor.utils.io.core.Output r1 = (io.ktor.utils.io.core.Output) r1
            short r2 = (short) r5
            io.ktor.utils.io.core.OutputPrimitivesKt.writeShort(r1, r2)
            r6.afterWrite(r3)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeShort$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, short, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeInt$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r5, int r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialBase$writeInt$1
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialBase$writeInt$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$writeInt$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$writeInt$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeInt$1
            r0.<init>(r5, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
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
            int r5 = r0.I$0
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r6 = (io.ktor.utils.io.ByteChannelSequentialBase) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4b
        L37:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r5
            r0.I$0 = r6
            r2 = 1
            r0.label = r2
            java.lang.Object r2 = r5.awaitAtLeastNBytesAvailableForWrite$ktor_io(r3, r0)
            if (r2 != r1) goto L48
            return r1
        L48:
            r4 = r6
            r6 = r5
            r5 = r4
        L4b:
            io.ktor.utils.io.core.BytePacketBuilder r1 = r6.writable
            io.ktor.utils.io.core.Output r1 = (io.ktor.utils.io.core.Output) r1
            io.ktor.utils.io.core.OutputPrimitivesKt.writeInt(r1, r5)
            r6.afterWrite(r3)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeInt$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeLong$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r6, long r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteChannelSequentialBase$writeLong$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.ByteChannelSequentialBase$writeLong$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$writeLong$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$writeLong$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeLong$1
            r0.<init>(r6, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 8
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2e:
            long r6 = r0.J$0
            java.lang.Object r8 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r8 = (io.ktor.utils.io.ByteChannelSequentialBase) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L4c
        L38:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r6
            r0.J$0 = r7
            r2 = 1
            r0.label = r2
            java.lang.Object r2 = r6.awaitAtLeastNBytesAvailableForWrite$ktor_io(r3, r0)
            if (r2 != r1) goto L49
            return r1
        L49:
            r4 = r7
            r8 = r6
            r6 = r4
        L4c:
            io.ktor.utils.io.core.BytePacketBuilder r1 = r8.writable
            io.ktor.utils.io.core.Output r1 = (io.ktor.utils.io.core.Output) r1
            io.ktor.utils.io.core.OutputPrimitivesKt.writeLong(r1, r6)
            r8.afterWrite(r3)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeLong$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeFloat$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r5, float r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialBase$writeFloat$1
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialBase$writeFloat$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$writeFloat$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$writeFloat$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeFloat$1
            r0.<init>(r5, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
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
            float r5 = r0.F$0
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r6 = (io.ktor.utils.io.ByteChannelSequentialBase) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4b
        L37:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r5
            r0.F$0 = r6
            r2 = 1
            r0.label = r2
            java.lang.Object r2 = r5.awaitAtLeastNBytesAvailableForWrite$ktor_io(r3, r0)
            if (r2 != r1) goto L48
            return r1
        L48:
            r4 = r6
            r6 = r5
            r5 = r4
        L4b:
            io.ktor.utils.io.core.BytePacketBuilder r1 = r6.writable
            io.ktor.utils.io.core.Output r1 = (io.ktor.utils.io.core.Output) r1
            io.ktor.utils.io.core.OutputPrimitivesKt.writeFloat(r1, r5)
            r6.afterWrite(r3)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeFloat$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, float, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeDouble$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r6, double r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteChannelSequentialBase$writeDouble$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.ByteChannelSequentialBase$writeDouble$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$writeDouble$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$writeDouble$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeDouble$1
            r0.<init>(r6, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 8
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2e:
            double r6 = r0.D$0
            java.lang.Object r8 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r8 = (io.ktor.utils.io.ByteChannelSequentialBase) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L4c
        L38:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r6
            r0.D$0 = r7
            r2 = 1
            r0.label = r2
            java.lang.Object r2 = r6.awaitAtLeastNBytesAvailableForWrite$ktor_io(r3, r0)
            if (r2 != r1) goto L49
            return r1
        L49:
            r4 = r7
            r8 = r6
            r6 = r4
        L4c:
            io.ktor.utils.io.core.BytePacketBuilder r1 = r8.writable
            io.ktor.utils.io.core.Output r1 = (io.ktor.utils.io.core.Output) r1
            io.ktor.utils.io.core.OutputPrimitivesKt.writeDouble(r1, r6)
            r8.afterWrite(r3)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeDouble$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, double, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writePacket$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, io.ktor.utils.io.core.ByteReadPacket r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase$writePacket$1
            if (r0 == 0) goto L14
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$writePacket$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$writePacket$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$writePacket$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writePacket$1
            r0.<init>(r4, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2c:
            java.lang.Object r4 = r0.L$1
            io.ktor.utils.io.core.ByteReadPacket r4 = (io.ktor.utils.io.core.ByteReadPacket) r4
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r5 = (io.ktor.utils.io.ByteChannelSequentialBase) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4c
        L38:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4
            r0.L$1 = r5
            r2 = 1
            r0.label = r2
            java.lang.Object r2 = r4.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            if (r2 != r1) goto L49
            return r1
        L49:
            r3 = r5
            r5 = r4
            r4 = r3
        L4c:
            long r1 = r4.getRemaining()
            int r1 = (int) r1
            io.ktor.utils.io.core.BytePacketBuilder r2 = r5.writable
            r2.writePacket(r4)
            r5.afterWrite(r1)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writePacket$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, io.ktor.utils.io.core.ByteReadPacket, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeFully$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r7, io.ktor.utils.io.core.Buffer r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteChannelSequentialBase$writeFully$1
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.ByteChannelSequentialBase$writeFully$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$writeFully$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$writeFully$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeFully$1
            r0.<init>(r7, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            java.lang.Object r7 = r0.L$1
            io.ktor.utils.io.core.Buffer r7 = (io.ktor.utils.io.core.Buffer) r7
            java.lang.Object r8 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r8 = (io.ktor.utils.io.ByteChannelSequentialBase) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L4c
        L38:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r7
            r0.L$1 = r8
            r2 = 1
            r0.label = r2
            java.lang.Object r2 = r7.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            if (r2 != r1) goto L49
            return r1
        L49:
            r6 = r8
            r8 = r7
            r7 = r6
        L4c:
            r1 = r7
            r2 = 0
            int r3 = r1.getWritePosition()
            int r4 = r1.getReadPosition()
            int r3 = r3 - r4
            io.ktor.utils.io.core.BytePacketBuilder r1 = r8.writable
            io.ktor.utils.io.core.Output r1 = (io.ktor.utils.io.core.Output) r1
            r2 = 2
            r4 = 0
            r5 = 0
            io.ktor.utils.io.core.OutputKt.writeFully$default(r1, r7, r5, r2, r4)
            r8.afterWrite(r3)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeFully$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, io.ktor.utils.io.core.Buffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0057 -> B:12:0x005a). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeFully$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, byte[] r5, int r6, int r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteChannelSequentialBase$writeFully$2
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ByteChannelSequentialBase$writeFully$2 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$writeFully$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$writeFully$2 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeFully$2
            r0.<init>(r4, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3c;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2c:
            int r4 = r0.I$1
            int r5 = r0.I$0
            java.lang.Object r6 = r0.L$1
            byte[] r6 = (byte[]) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r7 = (io.ktor.utils.io.ByteChannelSequentialBase) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5a
        L3c:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
            int r3 = r6 + r7
            r7 = r4
            r6 = r5
            r5 = r2
            r4 = r3
        L46:
            if (r5 >= r4) goto L70
            r0.L$0 = r7
            r0.L$1 = r6
            r0.I$0 = r5
            r0.I$1 = r4
            r2 = 1
            r0.label = r2
            java.lang.Object r2 = r7.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            if (r2 != r1) goto L5a
            return r1
        L5a:
            int r2 = r7.getAvailableForWrite()
            int r3 = r4 - r5
            int r2 = java.lang.Math.min(r2, r3)
            io.ktor.utils.io.core.BytePacketBuilder r3 = r7.writable
            io.ktor.utils.io.core.Output r3 = (io.ktor.utils.io.core.Output) r3
            io.ktor.utils.io.core.OutputKt.writeFully(r3, r6, r5, r2)
            int r5 = r5 + r2
            r7.afterWrite(r2)
            goto L46
        L70:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeFully$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0055 -> B:12:0x0058). Please report as a decompilation issue!!! */
    /* renamed from: writeFully-JT6ljtQ$suspendImpl, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object m226writeFullyJT6ljtQ$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, java.nio.ByteBuffer r5, int r6, int r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteChannelSequentialBase$writeFully$3
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ByteChannelSequentialBase$writeFully$3 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$writeFully$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$writeFully$3 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeFully$3
            r0.<init>(r4, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3c;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2c:
            int r4 = r0.I$1
            int r5 = r0.I$0
            java.lang.Object r6 = r0.L$1
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r7 = (io.ktor.utils.io.ByteChannelSequentialBase) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L58
        L3c:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
            r6 = r5
            r5 = r7
            r7 = r4
            r4 = r2
        L44:
            if (r4 >= r5) goto L6e
            r0.L$0 = r7
            r0.L$1 = r6
            r0.I$0 = r5
            r0.I$1 = r4
            r2 = 1
            r0.label = r2
            java.lang.Object r2 = r7.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            if (r2 != r1) goto L58
            return r1
        L58:
            int r2 = r7.getAvailableForWrite()
            int r3 = r5 - r4
            int r2 = java.lang.Math.min(r2, r3)
            io.ktor.utils.io.core.BytePacketBuilder r3 = r7.writable
            io.ktor.utils.io.core.Output r3 = (io.ktor.utils.io.core.Output) r3
            io.ktor.utils.io.core.OutputKt.m451writeFullyUAd2zVI(r3, r6, r4, r2)
            int r4 = r4 + r2
            r7.afterWrite(r2)
            goto L44
        L6e:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.m226writeFullyJT6ljtQ$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, java.nio.ByteBuffer, int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object writeAvailable$suspendImpl(ByteChannelSequentialBase $this, ChunkBuffer src, Continuation<? super Integer> continuation) {
        ChunkBuffer this_$iv = src;
        int srcRemaining = this_$iv.getWritePosition() - this_$iv.getReadPosition();
        if (srcRemaining == 0) {
            return Boxing.boxInt(0);
        }
        int size = Math.min(srcRemaining, $this.getAvailableForWrite());
        if (size == 0) {
            return $this.writeAvailableSuspend(src, continuation);
        }
        OutputKt.writeFully($this.writable, src, size);
        $this.afterWrite(size);
        return Boxing.boxInt(size);
    }

    static /* synthetic */ Object writeAvailable$suspendImpl(ByteChannelSequentialBase $this, byte[] src, int offset, int length, Continuation<? super Integer> continuation) {
        if (length == 0) {
            return Boxing.boxInt(0);
        }
        int size = Math.min(length, $this.getAvailableForWrite());
        if (size == 0) {
            return $this.writeAvailableSuspend(src, offset, length, continuation);
        }
        OutputKt.writeFully((Output) $this.writable, src, offset, size);
        $this.afterWrite(size);
        return Boxing.boxInt(size);
    }

    @Deprecated(message = "Use write { } instead.")
    static /* synthetic */ Object writeSuspendSession$suspendImpl(ByteChannelSequentialBase $this, Function2<? super WriterSuspendSession, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        WriterSuspendSession session = $this.beginWriteSession();
        Object invoke = function2.invoke(session, continuation);
        return invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke : Unit.INSTANCE;
    }

    @Override // io.ktor.utils.io.HasWriteSession
    public WriterSuspendSession beginWriteSession() {
        return new WriterSuspendSession() { // from class: io.ktor.utils.io.ByteChannelSequentialBase$beginWriteSession$1
            @Override // io.ktor.utils.io.WriterSession
            public ChunkBuffer request(int min) {
                if (ByteChannelSequentialBase.this.getAvailableForWrite() == 0) {
                    return null;
                }
                return ByteChannelSequentialBase.this.getWritable().prepareWriteHead(min);
            }

            @Override // io.ktor.utils.io.WriterSession
            public void written(int n) {
                ByteChannelSequentialBase.this.getWritable().afterHeadWrite();
                ByteChannelSequentialBase.this.afterWrite(n);
            }

            @Override // io.ktor.utils.io.WriterSession
            public void flush() {
                ByteChannelSequentialBase.this.flush();
            }

            @Override // io.ktor.utils.io.WriterSuspendSession
            public Object tryAwait(int n, Continuation<? super Unit> continuation) {
                Object awaitAtLeastNBytesAvailableForWrite$ktor_io;
                return (ByteChannelSequentialBase.this.getAvailableForWrite() >= n || (awaitAtLeastNBytesAvailableForWrite$ktor_io = ByteChannelSequentialBase.this.awaitAtLeastNBytesAvailableForWrite$ktor_io(n, continuation)) != IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : awaitAtLeastNBytesAvailableForWrite$ktor_io;
            }
        };
    }

    @Override // io.ktor.utils.io.HasWriteSession
    public void endWriteSession(int written) {
        this.writable.afterHeadWrite();
        afterWrite(written);
    }

    static /* synthetic */ Object readByte$suspendImpl(ByteChannelSequentialBase $this, Continuation<? super Byte> continuation) {
        ByteReadPacket $this$isNotEmpty$iv = $this.readable;
        if (!$this$isNotEmpty$iv.getEndOfInput()) {
            byte readByte = $this.readable.readByte();
            $this.afterRead(1);
            return Boxing.boxByte(readByte);
        }
        return $this.readByteSlow(continuation);
    }

    static /* synthetic */ void checkClosed$default(ByteChannelSequentialBase byteChannelSequentialBase, int i, BytePacketBuilder bytePacketBuilder, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: checkClosed");
        }
        if ((i2 & 2) != 0) {
            bytePacketBuilder = null;
        }
        byteChannelSequentialBase.checkClosed(i, bytePacketBuilder);
    }

    private final void checkClosed(int remaining, BytePacketBuilder closeable) {
        Throwable it = getClosedCause();
        if (it != null) {
            if (closeable != null) {
                closeable.close();
                throw it;
            }
            throw it;
        }
        if (getClosed() && get_availableForRead() < remaining) {
            if (closeable != null) {
                closeable.close();
            }
            throw new EOFException(remaining + " bytes required but EOF reached");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0022. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0041 -> B:12:0x0044). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readByteSlow(kotlin.coroutines.Continuation<? super java.lang.Byte> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readByteSlow$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ByteChannelSequentialBase$readByteSlow$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readByteSlow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readByteSlow$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readByteSlow$1
            r0.<init>(r7, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L35;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L2d:
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r2 = (io.ktor.utils.io.ByteChannelSequentialBase) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L44
        L35:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r7
        L39:
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r4 = r2.awaitSuspend(r3, r0)
            if (r4 != r1) goto L44
            return r1
        L44:
            io.ktor.utils.io.core.ByteReadPacket r4 = r2.readable
            r5 = 0
            boolean r6 = r4.getEndOfInput()
            if (r6 != 0) goto L4f
            r4 = r3
            goto L50
        L4f:
            r4 = 0
        L50:
            if (r4 == 0) goto L67
            io.ktor.utils.io.core.ByteReadPacket r1 = r2.readable
            byte r1 = r1.readByte()
            java.lang.Byte r1 = kotlin.coroutines.jvm.internal.Boxing.boxByte(r1)
            r4 = r1
            java.lang.Number r4 = (java.lang.Number) r4
            r4.byteValue()
            r4 = 0
            r2.afterRead(r3)
            return r1
        L67:
            r4 = 2
            r5 = 0
            checkClosed$default(r2, r3, r5, r4, r5)
            goto L39
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readByteSlow(kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readShort$suspendImpl(ByteChannelSequentialBase $this, Continuation<? super Short> continuation) {
        if ($this.readable.hasBytes(2)) {
            short readShort = InputPrimitivesKt.readShort($this.readable);
            $this.afterRead(2);
            return Boxing.boxShort(readShort);
        }
        return $this.readShortSlow(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readShortSlow(kotlin.coroutines.Continuation<? super java.lang.Short> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readShortSlow$1
            if (r0 == 0) goto L14
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$readShortSlow$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readShortSlow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readShortSlow$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readShortSlow$1
            r0.<init>(r5, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            switch(r2) {
                case 0: goto L35;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L2d:
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
            kotlin.ResultKt.throwOnFailure(r6)
            goto L46
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            r2 = r5
            r0.L$0 = r2
            r4 = 1
            r0.label = r4
            java.lang.Object r4 = r2.awaitSuspend(r3, r0)
            if (r4 != r1) goto L45
            return r1
        L45:
            r1 = r2
        L46:
            io.ktor.utils.io.core.ByteReadPacket r2 = r1.readable
            io.ktor.utils.io.core.Input r2 = (io.ktor.utils.io.core.Input) r2
            short r2 = io.ktor.utils.io.core.InputPrimitivesKt.readShort(r2)
            r1.afterRead(r3)
            short r3 = (short) r2
            java.lang.Short r3 = kotlin.coroutines.jvm.internal.Boxing.boxShort(r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readShortSlow(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void afterRead(int count) {
        addBytesRead(count);
        this.slot.resume();
    }

    static /* synthetic */ Object readInt$suspendImpl(ByteChannelSequentialBase $this, Continuation<? super Integer> continuation) {
        if ($this.readable.hasBytes(4)) {
            int readInt = InputPrimitivesKt.readInt($this.readable);
            $this.afterRead(4);
            return Boxing.boxInt(readInt);
        }
        return $this.readIntSlow(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readIntSlow(kotlin.coroutines.Continuation<? super java.lang.Integer> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readIntSlow$1
            if (r0 == 0) goto L14
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$readIntSlow$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readIntSlow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readIntSlow$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readIntSlow$1
            r0.<init>(r5, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            switch(r2) {
                case 0: goto L35;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L2d:
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
            kotlin.ResultKt.throwOnFailure(r6)
            goto L46
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            r2 = r5
            r0.L$0 = r2
            r4 = 1
            r0.label = r4
            java.lang.Object r4 = r2.awaitSuspend(r3, r0)
            if (r4 != r1) goto L45
            return r1
        L45:
            r1 = r2
        L46:
            io.ktor.utils.io.core.ByteReadPacket r2 = r1.readable
            io.ktor.utils.io.core.Input r2 = (io.ktor.utils.io.core.Input) r2
            int r2 = io.ktor.utils.io.core.InputPrimitivesKt.readInt(r2)
            r1.afterRead(r3)
            java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readIntSlow(kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readLong$suspendImpl(ByteChannelSequentialBase $this, Continuation<? super Long> continuation) {
        if ($this.readable.hasBytes(8)) {
            long readLong = InputPrimitivesKt.readLong($this.readable);
            $this.afterRead(8);
            return Boxing.boxLong(readLong);
        }
        return $this.readLongSlow(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readLongSlow(kotlin.coroutines.Continuation<? super java.lang.Long> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readLongSlow$1
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialBase$readLongSlow$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readLongSlow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readLongSlow$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readLongSlow$1
            r0.<init>(r6, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 8
            switch(r2) {
                case 0: goto L36;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L2e:
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L47
        L36:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r6
            r0.L$0 = r2
            r4 = 1
            r0.label = r4
            java.lang.Object r4 = r2.awaitSuspend(r3, r0)
            if (r4 != r1) goto L46
            return r1
        L46:
            r1 = r2
        L47:
            io.ktor.utils.io.core.ByteReadPacket r2 = r1.readable
            io.ktor.utils.io.core.Input r2 = (io.ktor.utils.io.core.Input) r2
            long r4 = io.ktor.utils.io.core.InputPrimitivesKt.readLong(r2)
            r1.afterRead(r3)
            java.lang.Long r2 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readLongSlow(kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readFloat$suspendImpl(ByteChannelSequentialBase $this, Continuation<? super Float> continuation) {
        if ($this.readable.hasBytes(4)) {
            float readFloat = InputPrimitivesKt.readFloat($this.readable);
            $this.afterRead(4);
            return Boxing.boxFloat(readFloat);
        }
        return $this.readFloatSlow(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readFloatSlow(kotlin.coroutines.Continuation<? super java.lang.Float> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readFloatSlow$1
            if (r0 == 0) goto L14
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$readFloatSlow$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readFloatSlow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readFloatSlow$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readFloatSlow$1
            r0.<init>(r5, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            switch(r2) {
                case 0: goto L35;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L2d:
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
            kotlin.ResultKt.throwOnFailure(r6)
            goto L46
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            r2 = r5
            r0.L$0 = r2
            r4 = 1
            r0.label = r4
            java.lang.Object r4 = r2.awaitSuspend(r3, r0)
            if (r4 != r1) goto L45
            return r1
        L45:
            r1 = r2
        L46:
            io.ktor.utils.io.core.ByteReadPacket r2 = r1.readable
            io.ktor.utils.io.core.Input r2 = (io.ktor.utils.io.core.Input) r2
            float r2 = io.ktor.utils.io.core.InputPrimitivesKt.readFloat(r2)
            r1.afterRead(r3)
            java.lang.Float r3 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readFloatSlow(kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readDouble$suspendImpl(ByteChannelSequentialBase $this, Continuation<? super Double> continuation) {
        if ($this.readable.hasBytes(8)) {
            double readDouble = InputPrimitivesKt.readDouble($this.readable);
            $this.afterRead(8);
            return Boxing.boxDouble(readDouble);
        }
        return $this.readDoubleSlow(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readDoubleSlow(kotlin.coroutines.Continuation<? super java.lang.Double> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readDoubleSlow$1
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialBase$readDoubleSlow$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readDoubleSlow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readDoubleSlow$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readDoubleSlow$1
            r0.<init>(r6, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 8
            switch(r2) {
                case 0: goto L36;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L2e:
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L47
        L36:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r6
            r0.L$0 = r2
            r4 = 1
            r0.label = r4
            java.lang.Object r4 = r2.awaitSuspend(r3, r0)
            if (r4 != r1) goto L46
            return r1
        L46:
            r1 = r2
        L47:
            io.ktor.utils.io.core.ByteReadPacket r2 = r1.readable
            io.ktor.utils.io.core.Input r2 = (io.ktor.utils.io.core.Input) r2
            double r4 = io.ktor.utils.io.core.InputPrimitivesKt.readDouble(r2)
            r1.afterRead(r3)
            java.lang.Double r2 = kotlin.coroutines.jvm.internal.Boxing.boxDouble(r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readDoubleSlow(kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readRemaining$suspendImpl(ByteChannelSequentialBase $this, long limit, Continuation<? super ByteReadPacket> continuation) {
        $this.ensureNotFailed();
        BytePacketBuilder builder = new BytePacketBuilder(null, 1, null);
        long size = Math.min(limit, $this.readable.getRemaining());
        builder.writePacket($this.readable, size);
        $this.afterRead((int) size);
        long newLimit = limit - builder.getSize();
        if (newLimit == 0 || $this.isClosedForRead()) {
            $this.ensureNotFailed(builder);
            return builder.build();
        }
        return $this.readRemainingSuspend(builder, limit, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readRemainingSuspend(io.ktor.utils.io.core.BytePacketBuilder r10, long r11, kotlin.coroutines.Continuation<? super io.ktor.utils.io.core.ByteReadPacket> r13) {
        /*
            r9 = this;
            boolean r0 = r13 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readRemainingSuspend$1
            if (r0 == 0) goto L14
            r0 = r13
            io.ktor.utils.io.ByteChannelSequentialBase$readRemainingSuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readRemainingSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readRemainingSuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readRemainingSuspend$1
            r0.<init>(r9, r13)
        L19:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3a;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2c:
            long r10 = r0.J$0
            java.lang.Object r12 = r0.L$1
            io.ktor.utils.io.core.BytePacketBuilder r12 = (io.ktor.utils.io.core.BytePacketBuilder) r12
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r2 = (io.ktor.utils.io.ByteChannelSequentialBase) r2
            kotlin.ResultKt.throwOnFailure(r13)
            goto L85
        L3a:
            kotlin.ResultKt.throwOnFailure(r13)
            r2 = r9
            r7 = r11
            r12 = r10
            r10 = r7
        L41:
            int r3 = r12.getSize()
            long r3 = (long) r3
            int r3 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r3 >= 0) goto L87
            int r3 = r12.getSize()
            long r3 = (long) r3
            long r3 = r10 - r3
            io.ktor.utils.io.core.ByteReadPacket r5 = r2.readable
            long r5 = r5.getRemaining()
            long r3 = java.lang.Math.min(r3, r5)
            io.ktor.utils.io.core.ByteReadPacket r5 = r2.readable
            r12.writePacket(r5, r3)
            int r5 = (int) r3
            r2.afterRead(r5)
            r2.ensureNotFailed(r12)
            boolean r3 = r2.isClosedForRead()
            if (r3 != 0) goto L86
            int r3 = r12.getSize()
            int r4 = (int) r10
            if (r3 != r4) goto L75
            goto L86
        L75:
            r0.L$0 = r2
            r0.L$1 = r12
            r0.J$0 = r10
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.awaitSuspend(r3, r0)
            if (r3 != r1) goto L85
            return r1
        L85:
            goto L41
        L86:
        L87:
            r2.ensureNotFailed(r12)
            io.ktor.utils.io.core.ByteReadPacket r10 = r12.build()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readRemainingSuspend(io.ktor.utils.io.core.BytePacketBuilder, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readPacket$suspendImpl(ByteChannelSequentialBase $this, int size, Continuation<? super ByteReadPacket> continuation) {
        checkClosed$default($this, size, null, 2, null);
        BytePacketBuilder builder = new BytePacketBuilder(null, 1, null);
        int partSize = (int) Math.min(size, $this.readable.getRemaining());
        int remaining = size - partSize;
        builder.writePacket($this.readable, partSize);
        $this.afterRead(partSize);
        $this.checkClosed(remaining, builder);
        if (remaining > 0) {
            return $this.readPacketSuspend(builder, remaining, continuation);
        }
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readPacketSuspend(io.ktor.utils.io.core.BytePacketBuilder r9, int r10, kotlin.coroutines.Continuation<? super io.ktor.utils.io.core.ByteReadPacket> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readPacketSuspend$1
            if (r0 == 0) goto L14
            r0 = r11
            io.ktor.utils.io.ByteChannelSequentialBase$readPacketSuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readPacketSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readPacketSuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readPacketSuspend$1
            r0.<init>(r8, r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3b;
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
            java.lang.Object r10 = r0.L$1
            io.ktor.utils.io.core.BytePacketBuilder r10 = (io.ktor.utils.io.core.BytePacketBuilder) r10
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r2 = (io.ktor.utils.io.ByteChannelSequentialBase) r2
            kotlin.ResultKt.throwOnFailure(r11)
            r3 = r9
            goto L6d
        L3b:
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = r8
            r3 = r10
            r10 = r9
        L41:
            if (r3 <= 0) goto L6e
            long r4 = (long) r3
            io.ktor.utils.io.core.ByteReadPacket r9 = r2.readable
            long r6 = r9.getRemaining()
            long r4 = java.lang.Math.min(r4, r6)
            int r9 = (int) r4
            int r3 = r3 - r9
            io.ktor.utils.io.core.ByteReadPacket r4 = r2.readable
            r10.writePacket(r4, r9)
            r2.afterRead(r9)
            r2.checkClosed(r3, r10)
            if (r3 <= 0) goto L41
            r0.L$0 = r2
            r0.L$1 = r10
            r0.I$0 = r3
            r9 = 1
            r0.label = r9
            java.lang.Object r9 = r2.awaitSuspend(r9, r0)
            if (r9 != r1) goto L6d
            return r1
        L6d:
            goto L41
        L6e:
            r2.checkClosed(r3, r10)
            io.ktor.utils.io.core.ByteReadPacket r9 = r10.build()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readPacketSuspend(io.ktor.utils.io.core.BytePacketBuilder, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    protected final int readAvailableClosed() {
        Throwable it = getClosedCause();
        if (it != null) {
            throw it;
        }
        if (get_availableForRead() > 0) {
            prepareFlushedBytes();
            return -1;
        }
        return -1;
    }

    static /* synthetic */ Object readAvailable$suspendImpl(ByteChannelSequentialBase $this, ChunkBuffer dst, Continuation<? super Integer> continuation) {
        Intrinsics.checkNotNull(dst, "null cannot be cast to non-null type io.ktor.utils.io.core.Buffer");
        return $this.readAvailable$ktor_io(dst, continuation);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readAvailable$ktor_io(io.ktor.utils.io.core.Buffer r8, kotlin.coroutines.Continuation<? super java.lang.Integer> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$2
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$2 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$2 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$2
            r0.<init>(r7, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2c:
            java.lang.Object r8 = r0.L$1
            io.ktor.utils.io.core.Buffer r8 = (io.ktor.utils.io.core.Buffer) r8
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L7c
        L38:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r7
            java.lang.Throwable r3 = r2.getClosedCause()
            if (r3 != 0) goto Lae
            boolean r3 = r2.getClosed()
            if (r3 == 0) goto L54
            int r3 = r2.get_availableForRead()
            if (r3 != 0) goto L54
            r1 = -1
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
            return r1
        L54:
            r3 = r8
            r4 = 0
            int r5 = r3.getLimit()
            int r6 = r3.getWritePosition()
            int r5 = r5 - r6
            if (r5 != 0) goto L67
            r1 = 0
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
            return r1
        L67:
            int r3 = r2.get_availableForRead()
            if (r3 != 0) goto L7d
            r0.L$0 = r2
            r0.L$1 = r8
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.awaitSuspend(r3, r0)
            if (r3 != r1) goto L7b
            return r1
        L7b:
            r1 = r2
        L7c:
            r2 = r1
        L7d:
            io.ktor.utils.io.core.ByteReadPacket r1 = r2.readable
            boolean r1 = r1.canRead()
            if (r1 != 0) goto L88
            r2.prepareFlushedBytes()
        L88:
            r1 = r8
            r3 = 0
            int r4 = r1.getLimit()
            int r5 = r1.getWritePosition()
            int r4 = r4 - r5
            long r3 = (long) r4
            io.ktor.utils.io.core.ByteReadPacket r1 = r2.readable
            long r5 = r1.getRemaining()
            long r3 = java.lang.Math.min(r3, r5)
            int r1 = (int) r3
            io.ktor.utils.io.core.ByteReadPacket r3 = r2.readable
            io.ktor.utils.io.core.Input r3 = (io.ktor.utils.io.core.Input) r3
            io.ktor.utils.io.core.InputArraysKt.readFully(r3, r8, r1)
            r2.afterRead(r1)
            java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
            return r3
        Lae:
            r8 = 0
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readAvailable$ktor_io(io.ktor.utils.io.core.Buffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readFully$suspendImpl(ByteChannelSequentialBase $this, ChunkBuffer dst, int n, Continuation<? super Unit> continuation) {
        Intrinsics.checkNotNull(dst, "null cannot be cast to non-null type io.ktor.utils.io.core.Buffer");
        Object readFully = $this.readFully((Buffer) dst, n, continuation);
        return readFully == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? readFully : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object readFully(Buffer dst, int n, Continuation<? super Unit> continuation) {
        if (!(n <= dst.getLimit() - dst.getWritePosition())) {
            throw new IllegalArgumentException(("Not enough space in the destination buffer to write " + n + " bytes").toString());
        }
        if (!(n >= 0)) {
            throw new IllegalArgumentException("n shouldn't be negative".toString());
        }
        if (getClosedCause() != null) {
            Throwable closedCause = getClosedCause();
            Intrinsics.checkNotNull(closedCause);
            throw closedCause;
        }
        if (this.readable.getRemaining() >= n) {
            InputArraysKt.readFully(this.readable, dst, n);
            Unit unit = Unit.INSTANCE;
            afterRead(n);
            Unit it = Unit.INSTANCE;
            return it;
        }
        if (getClosed()) {
            throw new EOFException("Channel is closed and not enough bytes available: required " + n + " but " + get_availableForRead() + " available");
        }
        Object readFullySuspend = readFullySuspend(dst, n, continuation);
        return readFullySuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? readFullySuspend : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0063 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readFullySuspend(io.ktor.utils.io.core.Buffer r6, int r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$1
            r0.<init>(r5, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3e;
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
            goto L64
        L30:
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$1
            io.ktor.utils.io.core.Buffer r7 = (io.ktor.utils.io.core.Buffer) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r2 = (io.ktor.utils.io.ByteChannelSequentialBase) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L55
        L3e:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r5
            r0.L$0 = r2
            r0.L$1 = r6
            r0.I$0 = r7
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.awaitSuspend(r7, r0)
            if (r3 != r1) goto L52
            return r1
        L52:
            r4 = r7
            r7 = r6
            r6 = r4
        L55:
            r3 = 0
            r0.L$0 = r3
            r0.L$1 = r3
            r3 = 2
            r0.label = r3
            java.lang.Object r6 = r2.readFully(r7, r6, r0)
            if (r6 != r1) goto L64
            return r1
        L64:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readFullySuspend(io.ktor.utils.io.core.Buffer, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readAvailable$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r6, byte[] r7, int r8, int r9, kotlin.coroutines.Continuation<? super java.lang.Integer> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$4
            if (r0 == 0) goto L14
            r0 = r10
            io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$4 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$4) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$4 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$4
            r0.<init>(r6, r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3c;
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
            java.lang.Object r9 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r9 = (io.ktor.utils.io.ByteChannelSequentialBase) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L7d
        L3c:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Throwable r2 = r6.getClosedCause()
            if (r2 != 0) goto La9
            boolean r2 = r6.getClosed()
            if (r2 == 0) goto L57
            int r2 = r6.get_availableForRead()
            if (r2 != 0) goto L57
            r1 = -1
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
            return r1
        L57:
            if (r9 != 0) goto L5f
            r1 = 0
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
            return r1
        L5f:
            int r2 = r6.get_availableForRead()
            if (r2 != 0) goto L83
            r0.L$0 = r6
            r0.L$1 = r7
            r0.I$0 = r8
            r0.I$1 = r9
            r2 = 1
            r0.label = r2
            java.lang.Object r2 = r6.awaitSuspend(r2, r0)
            if (r2 != r1) goto L77
            return r1
        L77:
            r5 = r9
            r9 = r6
            r6 = r5
            r5 = r8
            r8 = r7
            r7 = r5
        L7d:
            r5 = r9
            r9 = r6
            r6 = r5
            r5 = r8
            r8 = r7
            r7 = r5
        L83:
            io.ktor.utils.io.core.ByteReadPacket r1 = r6.readable
            boolean r1 = r1.canRead()
            if (r1 != 0) goto L8e
            r6.prepareFlushedBytes()
        L8e:
            long r1 = (long) r9
            io.ktor.utils.io.core.ByteReadPacket r3 = r6.readable
            long r3 = r3.getRemaining()
            long r1 = java.lang.Math.min(r1, r3)
            int r1 = (int) r1
            io.ktor.utils.io.core.ByteReadPacket r2 = r6.readable
            io.ktor.utils.io.core.Input r2 = (io.ktor.utils.io.core.Input) r2
            io.ktor.utils.io.core.InputArraysKt.readFully(r2, r7, r8, r1)
            r6.afterRead(r1)
            java.lang.Integer r2 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r1)
            return r2
        La9:
            r6 = 0
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readAvailable$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readFully$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r7, byte[] r8, int r9, int r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            boolean r0 = r11 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readFully$6
            if (r0 == 0) goto L14
            r0 = r11
            io.ktor.utils.io.ByteChannelSequentialBase$readFully$6 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readFully$6) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readFully$6 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readFully$6
            r0.<init>(r7, r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L41;
                case 1: goto L30;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L7d
        L30:
            int r7 = r0.I$1
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$1
            byte[] r9 = (byte[]) r9
            java.lang.Object r10 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r10 = (io.ktor.utils.io.ByteChannelSequentialBase) r10
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = r11
            goto L5c
        L41:
            kotlin.ResultKt.throwOnFailure(r11)
            r0.L$0 = r7
            r0.L$1 = r8
            r0.I$0 = r9
            r0.I$1 = r10
            r2 = 1
            r0.label = r2
            java.lang.Object r2 = r7.readAvailable(r8, r9, r10, r0)
            if (r2 != r1) goto L56
            return r1
        L56:
            r6 = r10
            r10 = r7
            r7 = r6
            r6 = r9
            r9 = r8
            r8 = r6
        L5c:
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            if (r2 != r7) goto L67
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L67:
            r3 = -1
            if (r2 == r3) goto L80
            int r3 = r8 + r2
            int r4 = r7 - r2
            r5 = 0
            r0.L$0 = r5
            r0.L$1 = r5
            r5 = 2
            r0.label = r5
            java.lang.Object r7 = r10.readFullySuspend(r9, r3, r4, r0)
            if (r7 != r1) goto L7d
            return r1
        L7d:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L80:
            java.io.EOFException r1 = new java.io.EOFException
            java.lang.String r3 = "Unexpected end of stream"
            r1.<init>(r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readFully$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0069 -> B:12:0x0070). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readFullySuspend(byte[] r9, int r10, int r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r8 = this;
            boolean r0 = r12 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$2
            if (r0 == 0) goto L14
            r0 = r12
            io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$2 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$2 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$2
            r0.<init>(r8, r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L43;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2c:
            int r9 = r0.I$2
            int r10 = r0.I$1
            int r11 = r0.I$0
            java.lang.Object r2 = r0.L$1
            byte[] r2 = (byte[]) r2
            java.lang.Object r3 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r3 = (io.ktor.utils.io.ByteChannelSequentialBase) r3
            kotlin.ResultKt.throwOnFailure(r12)
            r4 = r3
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r12
            goto L70
        L43:
            kotlin.ResultKt.throwOnFailure(r12)
            r2 = r8
            r3 = 0
            r7 = r2
            r2 = r9
            r9 = r3
            r3 = r7
            r7 = r11
            r11 = r10
            r10 = r7
        L4f:
            if (r9 >= r10) goto L88
            int r4 = r11 + r9
            int r5 = r10 - r9
            r0.L$0 = r3
            r0.L$1 = r2
            r0.I$0 = r11
            r0.I$1 = r10
            r0.I$2 = r9
            r6 = 1
            r0.label = r6
            java.lang.Object r4 = r3.readAvailable(r2, r4, r5, r0)
            if (r4 != r1) goto L69
            return r1
        L69:
            r7 = r0
            r0 = r12
            r12 = r4
            r4 = r3
            r3 = r2
            r2 = r1
            r1 = r7
        L70:
            java.lang.Number r12 = (java.lang.Number) r12
            int r12 = r12.intValue()
            r5 = -1
            if (r12 == r5) goto L80
            int r9 = r9 + r12
            r12 = r0
            r0 = r1
            r1 = r2
            r2 = r3
            r3 = r4
            goto L4f
        L80:
            java.io.EOFException r2 = new java.io.EOFException
            java.lang.String r5 = "Unexpected end of stream"
            r2.<init>(r5)
            throw r2
        L88:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readFullySuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object readBoolean$suspendImpl(ByteChannelSequentialBase $this, Continuation<? super Boolean> continuation) {
        if ($this.readable.canRead()) {
            boolean z = $this.readable.readByte() == 1;
            $this.afterRead(1);
            return Boxing.boxBoolean(z);
        }
        return $this.readBooleanSlow(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readBooleanSlow(kotlin.coroutines.Continuation<? super java.lang.Boolean> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readBooleanSlow$1
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialBase$readBooleanSlow$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readBooleanSlow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readBooleanSlow$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readBooleanSlow$1
            r0.<init>(r6, r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            switch(r2) {
                case 0: goto L3a;
                case 1: goto L32;
                case 2: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L2d:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r7
            goto L59
        L32:
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r2 = (io.ktor.utils.io.ByteChannelSequentialBase) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L49
        L3a:
            kotlin.ResultKt.throwOnFailure(r7)
            r2 = r6
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r4 = r2.awaitSuspend(r3, r0)
            if (r4 != r1) goto L49
            return r1
        L49:
            r4 = 0
            r5 = 2
            checkClosed$default(r2, r3, r4, r5, r4)
            r0.L$0 = r4
            r0.label = r5
            java.lang.Object r2 = r2.readBoolean(r0)
            if (r2 != r1) goto L59
            return r1
        L59:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readBooleanSlow(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void completeReading() {
        Buffer this_$iv = getLastReadView();
        int remaining = this_$iv.getWritePosition() - this_$iv.getReadPosition();
        int delta = getLastReadAvailable$delegate() - remaining;
        if (getLastReadView() != Buffer.INSTANCE.getEmpty()) {
            UnsafeKt.completeReadHead(this.readable, getLastReadView());
        }
        if (delta > 0) {
            afterRead(delta);
        }
        setLastReadAvailable(0);
        setLastReadView(ChunkBuffer.INSTANCE.getEmpty());
    }

    static /* synthetic */ Object await$suspendImpl(ByteChannelSequentialBase $this, int atLeast, Continuation<? super Boolean> continuation) {
        if (!(atLeast >= 0)) {
            throw new IllegalArgumentException(("atLeast parameter shouldn't be negative: " + atLeast).toString());
        }
        if (!(((long) atLeast) <= 4088)) {
            throw new IllegalArgumentException(("atLeast parameter shouldn't be larger than max buffer size of 4088: " + atLeast).toString());
        }
        $this.completeReading();
        return atLeast == 0 ? Boxing.boxBoolean(!$this.isClosedForRead()) : $this.readable.getRemaining() >= ((long) atLeast) ? Boxing.boxBoolean(true) : $this.awaitSuspend(atLeast, continuation);
    }

    public final Object awaitInternalAtLeast1$ktor_io(Continuation<? super Boolean> continuation) {
        ByteReadPacket $this$isNotEmpty$iv = this.readable;
        if ($this$isNotEmpty$iv.getEndOfInput()) {
            return awaitSuspend(1, continuation);
        }
        return Boxing.boxBoolean(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object awaitSuspend(int r7, kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteChannelSequentialBase$awaitSuspend$1
            if (r0 == 0) goto L14
            r0 = r8
            io.ktor.utils.io.ByteChannelSequentialBase$awaitSuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$awaitSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$awaitSuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$awaitSuspend$1
            r0.<init>(r6, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            switch(r2) {
                case 0: goto L38;
                case 1: goto L2e;
                default: goto L26;
            }
        L26:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2e:
            int r7 = r0.I$0
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
            kotlin.ResultKt.throwOnFailure(r8)
            goto L51
        L38:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
            if (r7 < 0) goto L40
            r5 = r4
            goto L41
        L40:
            r5 = r3
        L41:
            if (r5 == 0) goto L6e
            r0.L$0 = r2
            r0.I$0 = r7
            r0.label = r4
            java.lang.Object r5 = r2.awaitAtLeastNBytesAvailableForRead$ktor_io(r7, r0)
            if (r5 != r1) goto L50
            return r1
        L50:
            r1 = r2
        L51:
            r1.prepareFlushedBytes()
            java.lang.Throwable r2 = r1.getClosedCause()
            if (r2 != 0) goto L6c
            boolean r2 = r1.isClosedForRead()
            if (r2 != 0) goto L67
            int r2 = r1.get_availableForRead()
            if (r2 < r7) goto L67
            r3 = r4
        L67:
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r7
        L6c:
            r7 = 0
            throw r2
        L6e:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Failed requirement."
            java.lang.String r3 = r3.toString()
            r1.<init>(r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.awaitSuspend(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // io.ktor.utils.io.ReadSession
    public int discard(int n) {
        Throwable it = getClosedCause();
        if (it != null) {
            throw it;
        }
        if (n == 0) {
            return 0;
        }
        int discard = this.readable.discard(n);
        afterRead(n);
        requestNextView(1);
        return discard;
    }

    @Override // io.ktor.utils.io.ReadSession
    public ChunkBuffer request(int atLeast) {
        Throwable it = getClosedCause();
        if (it != null) {
            throw it;
        }
        completeReading();
        return requestNextView(atLeast);
    }

    private final ChunkBuffer requestNextView(int atLeast) {
        ByteReadPacket $this$isEmpty$iv = this.readable;
        if ($this$isEmpty$iv.getEndOfInput()) {
            prepareFlushedBytes();
        }
        ChunkBuffer view = this.readable.prepareReadHead$ktor_io(atLeast);
        if (view == null) {
            setLastReadView(ChunkBuffer.INSTANCE.getEmpty());
            setLastReadAvailable(0);
        } else {
            setLastReadView(view);
            ChunkBuffer this_$iv = view;
            setLastReadAvailable(this_$iv.getWritePosition() - this_$iv.getReadPosition());
        }
        return view;
    }

    static /* synthetic */ Object discard$suspendImpl(ByteChannelSequentialBase $this, long max, Continuation<? super Long> continuation) {
        ByteChannelSequentialBase $this2;
        long discarded = $this.readable.discard(max);
        $this.afterRead((int) discarded);
        if (discarded == max) {
            $this2 = $this;
        } else {
            if (!$this.isClosedForRead()) {
                return $this.discardSuspend(max, discarded, continuation);
            }
            $this2 = $this;
        }
        $this2.ensureNotFailed();
        return Boxing.boxLong(discarded);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0052 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0053 -> B:12:0x0059). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object discardSuspend(long r8, long r10, kotlin.coroutines.Continuation<? super java.lang.Long> r12) {
        /*
            r7 = this;
            boolean r0 = r12 instanceof io.ktor.utils.io.ByteChannelSequentialBase$discardSuspend$1
            if (r0 == 0) goto L14
            r0 = r12
            io.ktor.utils.io.ByteChannelSequentialBase$discardSuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$discardSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$discardSuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$discardSuspend$1
            r0.<init>(r7, r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3c;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2c:
            long r8 = r0.J$1
            long r10 = r0.J$0
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r2 = (io.ktor.utils.io.ByteChannelSequentialBase) r2
            kotlin.ResultKt.throwOnFailure(r12)
            r3 = r2
            r2 = r1
            r1 = r0
            r0 = r12
            goto L59
        L3c:
            kotlin.ResultKt.throwOnFailure(r12)
            r2 = r7
            r3 = r10
            r10 = r8
            r8 = r3
        L43:
            r0.L$0 = r2
            r0.J$0 = r10
            r0.J$1 = r8
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.await(r3, r0)
            if (r3 != r1) goto L53
            return r1
        L53:
            r6 = r0
            r0 = r12
            r12 = r3
            r3 = r2
            r2 = r1
            r1 = r6
        L59:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L7e
            io.ktor.utils.io.core.ByteReadPacket r12 = r3.readable
            long r4 = r10 - r8
            long r4 = r12.discard(r4)
            int r12 = (int) r4
            r3.afterRead(r12)
            long r8 = r8 + r4
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 >= 0) goto L7e
            boolean r12 = r3.isClosedForRead()
            if (r12 == 0) goto L79
            goto L7e
        L79:
            r12 = r0
            r0 = r1
            r1 = r2
            r2 = r3
            goto L43
        L7e:
            r3.ensureNotFailed()
            java.lang.Long r10 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r8)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.discardSuspend(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    @Deprecated(message = "Use read instead.")
    public void readSession(Function1<? super ReadSession, Unit> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        try {
            consumer.invoke(this);
        } finally {
            completeReading();
        }
    }

    @Override // io.ktor.utils.io.HasReadSession
    public SuspendableReadSession startReadSession() {
        return this;
    }

    @Override // io.ktor.utils.io.HasReadSession
    public void endReadSession() {
        completeReading();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    @kotlin.Deprecated(message = "Use read instead.")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readSuspendableSession$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r3, kotlin.jvm.functions.Function2<? super io.ktor.utils.io.SuspendableReadSession, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r4, kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            boolean r0 = r5 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readSuspendableSession$1
            if (r0 == 0) goto L14
            r0 = r5
            io.ktor.utils.io.ByteChannelSequentialBase$readSuspendableSession$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readSuspendableSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readSuspendableSession$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readSuspendableSession$1
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
            io.ktor.utils.io.ByteChannelSequentialBase r3 = (io.ktor.utils.io.ByteChannelSequentialBase) r3
            kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Throwable -> L4b
            goto L44
        L34:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.L$0 = r3     // Catch: java.lang.Throwable -> L4b
            r2 = 1
            r0.label = r2     // Catch: java.lang.Throwable -> L4b
            java.lang.Object r2 = r4.invoke(r3, r0)     // Catch: java.lang.Throwable -> L4b
            if (r2 != r1) goto L44
            return r1
        L44:
            r3.completeReading()
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L4b:
            r4 = move-exception
            r3.completeReading()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readSuspendableSession$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ <A extends Appendable> Object readUTF8LineTo$suspendImpl(final ByteChannelSequentialBase $this, A a, int limit, Continuation<? super Boolean> continuation) {
        if ($this.isClosedForRead()) {
            Throwable cause = $this.getClosedCause();
            if (cause != null) {
                throw cause;
            }
            return Boxing.boxBoolean(false);
        }
        return UTF8Kt.decodeUTF8LineLoopSuspend(a, limit, new ByteChannelSequentialBase$readUTF8LineTo$2($this, null), new Function1<Integer, Unit>() { // from class: io.ktor.utils.io.ByteChannelSequentialBase$readUTF8LineTo$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int it) {
                ByteChannelSequentialBase.this.afterRead(it);
            }
        }, continuation);
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
    public static /* synthetic */ java.lang.Object readUTF8Line$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r5, int r6, kotlin.coroutines.Continuation<? super java.lang.String> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialBase$readUTF8Line$1
            if (r0 == 0) goto L14
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialBase$readUTF8Line$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$readUTF8Line$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$readUTF8Line$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readUTF8Line$1
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
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readUTF8Line$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public boolean cancel(Throwable cause) {
        if (getClosedCause() != null || getClosed()) {
            return false;
        }
        return close(cause == null ? new CancellationException("Channel cancelled") : cause);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public boolean close(Throwable cause) {
        CloseElement closeElement = cause == null ? CloseElementKt.getCLOSED_SUCCESS() : new CloseElement(cause);
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_closed$FU, this, null, closeElement)) {
            return false;
        }
        if (cause != null) {
            this.readable.release();
            this.writable.release();
            this.flushBuffer.release();
        } else {
            flush();
        }
        this.slot.cancel(cause);
        return true;
    }

    public final long transferTo$ktor_io(ByteChannelSequentialBase dst, long limit) {
        Intrinsics.checkNotNullParameter(dst, "dst");
        long size = this.readable.getRemaining();
        if (size <= limit) {
            dst.writable.writePacket(this.readable);
            dst.afterWrite((int) size);
            afterRead((int) size);
            return size;
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer r5, kotlin.coroutines.Continuation<? super java.lang.Integer> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$1
            if (r0 == 0) goto L14
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$1
            r0.<init>(r4, r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3d;
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
            goto L5e
        L31:
            java.lang.Object r5 = r0.L$1
            io.ktor.utils.io.core.internal.ChunkBuffer r5 = (io.ktor.utils.io.core.internal.ChunkBuffer) r5
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r2 = (io.ktor.utils.io.ByteChannelSequentialBase) r2
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4f
        L3d:
            kotlin.ResultKt.throwOnFailure(r6)
            r2 = r4
            r0.L$0 = r2
            r0.L$1 = r5
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.awaitAtLeastNBytesAvailableForWrite$ktor_io(r3, r0)
            if (r3 != r1) goto L4f
            return r1
        L4f:
            r3 = 0
            r0.L$0 = r3
            r0.L$1 = r3
            r3 = 2
            r0.label = r3
            java.lang.Object r5 = r2.writeAvailable(r5, r0)
            if (r5 != r1) goto L5e
            return r1
        L5e:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0068 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeAvailableSuspend(byte[] r6, int r7, int r8, kotlin.coroutines.Continuation<? super java.lang.Integer> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$2
            if (r0 == 0) goto L14
            r0 = r9
            io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$2 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$2 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$2
            r0.<init>(r5, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L41;
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
            goto L69
        L31:
            int r6 = r0.I$1
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$1
            byte[] r8 = (byte[]) r8
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r2 = (io.ktor.utils.io.ByteChannelSequentialBase) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L5a
        L41:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r5
            r0.L$0 = r2
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r3 = 1
            r0.label = r3
            java.lang.Object r3 = r2.awaitAtLeastNBytesAvailableForWrite$ktor_io(r3, r0)
            if (r3 != r1) goto L57
            return r1
        L57:
            r4 = r8
            r8 = r6
            r6 = r4
        L5a:
            r3 = 0
            r0.L$0 = r3
            r0.L$1 = r3
            r3 = 2
            r0.label = r3
            java.lang.Object r6 = r2.writeAvailable(r8, r7, r6, r0)
            if (r6 != r1) goto L69
            return r1
        L69:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeAvailableSuspend(byte[], int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void afterWrite(int count) {
        addBytesWritten(count);
        if (getClosed()) {
            this.writable.release();
            ensureNotClosed();
        }
        if (getAutoFlush() || getAvailableForWrite() == 0) {
            flush();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object awaitFreeSpace$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r3, kotlin.coroutines.Continuation<? super kotlin.Unit> r4) {
        /*
            boolean r0 = r4 instanceof io.ktor.utils.io.ByteChannelSequentialBase$awaitFreeSpace$1
            if (r0 == 0) goto L14
            r0 = r4
            io.ktor.utils.io.ByteChannelSequentialBase$awaitFreeSpace$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$awaitFreeSpace$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r4 = r0.label
            int r4 = r4 - r2
            r0.label = r4
            goto L19
        L14:
            io.ktor.utils.io.ByteChannelSequentialBase$awaitFreeSpace$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$awaitFreeSpace$1
            r0.<init>(r3, r4)
        L19:
            java.lang.Object r4 = r0.result
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
            io.ktor.utils.io.ByteChannelSequentialBase r3 = (io.ktor.utils.io.ByteChannelSequentialBase) r3
            kotlin.ResultKt.throwOnFailure(r4)
            goto L46
        L34:
            kotlin.ResultKt.throwOnFailure(r4)
            r3.flush()
            r0.L$0 = r3
            r2 = 1
            r0.label = r2
            java.lang.Object r2 = r3.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            if (r2 != r1) goto L46
            return r1
        L46:
            r3.ensureNotClosed()
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.awaitFreeSpace$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object awaitContent$suspendImpl(ByteChannelSequentialBase $this, Continuation<? super Unit> continuation) {
        Object await = $this.await(1, continuation);
        return await == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? await : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    @Override // io.ktor.utils.io.ByteReadChannel
    /* renamed from: peekTo-lBXzO7A */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object mo224peekTolBXzO7A(java.nio.ByteBuffer r18, long r19, long r21, long r23, long r25, kotlin.coroutines.Continuation<? super java.lang.Long> r27) {
        /*
            r17 = this;
            r0 = r27
            boolean r1 = r0 instanceof io.ktor.utils.io.ByteChannelSequentialBase$peekTo$1
            if (r1 == 0) goto L18
            r1 = r0
            io.ktor.utils.io.ByteChannelSequentialBase$peekTo$1 r1 = (io.ktor.utils.io.ByteChannelSequentialBase$peekTo$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L18
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r2 = r17
            goto L1f
        L18:
            io.ktor.utils.io.ByteChannelSequentialBase$peekTo$1 r1 = new io.ktor.utils.io.ByteChannelSequentialBase$peekTo$1
            r2 = r17
            r1.<init>(r2, r0)
        L1f:
            java.lang.Object r0 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            switch(r4) {
                case 0: goto L3e;
                case 1: goto L34;
                default: goto L2a;
            }
        L2a:
            r16 = r0
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L34:
            java.lang.Object r3 = r1.L$0
            kotlin.jvm.internal.Ref$LongRef r3 = (kotlin.jvm.internal.Ref.LongRef) r3
            kotlin.ResultKt.throwOnFailure(r0)
            r16 = r0
            goto L6a
        L3e:
            kotlin.ResultKt.throwOnFailure(r0)
            r4 = r17
            r14 = r19
            r6 = r23
            r8 = r21
            r11 = r25
            r13 = r18
            kotlin.jvm.internal.Ref$LongRef r10 = new kotlin.jvm.internal.Ref$LongRef
            r10.<init>()
            io.ktor.utils.io.ByteChannelSequentialBase$peekTo$2 r5 = new io.ktor.utils.io.ByteChannelSequentialBase$peekTo$2
            r16 = 0
            r5.<init>(r6, r8, r10, r11, r13, r14, r16)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r1.L$0 = r10
            r16 = r0
            r0 = 1
            r1.label = r0
            java.lang.Object r0 = r4.readSuspendableSession(r5, r1)
            if (r0 != r3) goto L69
            return r3
        L69:
            r3 = r10
        L6a:
            long r4 = r3.element
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.mo224peekTolBXzO7A(java.nio.ByteBuffer, long, long, long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void addBytesRead(int count) {
        if (!(count >= 0)) {
            throw new IllegalArgumentException(("Can't read negative amount of bytes: " + count).toString());
        }
        channelSize$FU.getAndAdd(this, -count);
        _totalBytesRead$FU.addAndGet(this, count);
        _availableForRead$FU.getAndAdd(this, -count);
        if (!(this.channelSize >= 0)) {
            throw new IllegalStateException(("Readable bytes count is negative: " + get_availableForRead() + ", " + count + " in " + this).toString());
        }
        if (!(get_availableForRead() >= 0)) {
            throw new IllegalStateException(("Readable bytes count is negative: " + get_availableForRead() + ", " + count + " in " + this).toString());
        }
    }

    private final void addBytesWritten(int count) {
        if (!(count >= 0)) {
            throw new IllegalArgumentException(("Can't write negative amount of bytes: " + count).toString());
        }
        channelSize$FU.getAndAdd(this, count);
        _totalBytesWritten$FU.addAndGet(this, count);
        if (!(this.channelSize >= 0)) {
            throw new IllegalStateException(("Readable bytes count is negative: " + this.channelSize + ", " + count + " in " + this).toString());
        }
    }
}
