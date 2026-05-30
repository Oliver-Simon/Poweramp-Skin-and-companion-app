package io.ktor.utils.io.pool;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.util.concurrent.Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0;
import io.ktor.http.LinkHeader;
import io.ktor.utils.io.pool.ObjectPool;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty1;

/* compiled from: DefaultPool.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b&\u0018\u0000 $*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001$B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u0011\u001a\u00028\u0000¢\u0006\u0002\u0010\u0012J\u0015\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0015J\u0006\u0010\u0016\u001a\u00020\u0017J\u0015\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u0005H\u0002J\r\u0010\u001b\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0012J\u0010\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0005H\u0002J\u0013\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00028\u0000¢\u0006\u0002\u0010\u0019J\u000f\u0010\u001f\u001a\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0002\u0010\u0012J\u0015\u0010 \u001a\u00020!2\u0006\u0010\u0014\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\"J\u0015\u0010#\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lio/ktor/utils/io/pool/DefaultPool;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lio/ktor/utils/io/pool/ObjectPool;", "capacity", "", "(I)V", "getCapacity", "()I", "instances", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "maxIndex", LinkHeader.Rel.Next, "", "shift", "top", "", "borrow", "()Ljava/lang/Object;", "clearInstance", "instance", "(Ljava/lang/Object;)Ljava/lang/Object;", "dispose", "", "disposeInstance", "(Ljava/lang/Object;)V", "popTop", "produceInstance", "pushTop", "index", "recycle", "tryPop", "tryPush", "", "(Ljava/lang/Object;)Z", "validateInstance", "Companion", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public abstract class DefaultPool<T> implements ObjectPool<T> {
    private static final AtomicLongFieldUpdater<DefaultPool<?>> Top;
    private final int capacity;
    private final AtomicReferenceArray<T> instances;
    private final int maxIndex;
    private final int[] next;
    private final int shift;
    private volatile long top;

    protected abstract T produceInstance();

    public DefaultPool(int capacity) {
        this.capacity = capacity;
        if (!(this.capacity > 0)) {
            throw new IllegalArgumentException(("capacity should be positive but it is " + this.capacity).toString());
        }
        if (!(this.capacity <= 536870911)) {
            throw new IllegalArgumentException(("capacity should be less or equal to 536870911 but it is " + this.capacity).toString());
        }
        this.maxIndex = Integer.highestOneBit((this.capacity * 4) - 1) * 2;
        this.shift = Integer.numberOfLeadingZeros(this.maxIndex) + 1;
        this.instances = new AtomicReferenceArray<>(this.maxIndex + 1);
        this.next = new int[this.maxIndex + 1];
    }

    @Override // io.ktor.utils.io.pool.ObjectPool, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        ObjectPool.DefaultImpls.close(this);
    }

    @Override // io.ktor.utils.io.pool.ObjectPool
    public final int getCapacity() {
        return this.capacity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T clearInstance(T instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validateInstance(T instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void disposeInstance(T instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
    }

    @Override // io.ktor.utils.io.pool.ObjectPool
    public final T borrow() {
        T clearInstance;
        T tryPop = tryPop();
        return (tryPop == null || (clearInstance = clearInstance(tryPop)) == null) ? produceInstance() : clearInstance;
    }

    @Override // io.ktor.utils.io.pool.ObjectPool
    public final void recycle(T instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        validateInstance(instance);
        if (!tryPush(instance)) {
            disposeInstance(instance);
        }
    }

    @Override // io.ktor.utils.io.pool.ObjectPool
    public final void dispose() {
        while (true) {
            T tryPop = tryPop();
            if (tryPop == null) {
                return;
            } else {
                disposeInstance(tryPop);
            }
        }
    }

    private final boolean tryPush(T instance) {
        int index = ((System.identityHashCode(instance) * (-1640531527)) >>> this.shift) + 1;
        for (int i = 0; i < 8; i++) {
            if (Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(this.instances, index, null, instance)) {
                pushTop(index);
                return true;
            }
            index--;
            if (index == 0) {
                index = this.maxIndex;
            }
        }
        return false;
    }

    private final T tryPop() {
        int index = popTop();
        if (index == 0) {
            return null;
        }
        return this.instances.getAndSet(index, null);
    }

    private final void pushTop(int index) {
        long top;
        long newTop;
        if (!(index > 0)) {
            throw new IllegalArgumentException("index should be positive".toString());
        }
        do {
            top = this.top;
            long topVersion = 1 + ((top >> 32) & 4294967295L);
            int topIndex = (int) (top & 4294967295L);
            newTop = index | (topVersion << 32);
            this.next[index] = topIndex;
        } while (!Top.compareAndSet(this, top, newTop));
    }

    private final int popTop() {
        long top;
        int topIndex;
        long newTop;
        do {
            top = this.top;
            if (top == 0) {
                return 0;
            }
            long newVersion = 1 + ((top >> 32) & 4294967295L);
            topIndex = (int) (top & 4294967295L);
            if (topIndex == 0) {
                return 0;
            }
            int next = this.next[topIndex];
            newTop = next | (newVersion << 32);
        } while (!Top.compareAndSet(this, top, newTop));
        return topIndex;
    }

    static {
        KProperty1 p$iv = new MutablePropertyReference1Impl() { // from class: io.ktor.utils.io.pool.DefaultPool$Companion$Top$1
            @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object receiver0) {
                long j;
                j = ((DefaultPool) receiver0).top;
                return Long.valueOf(j);
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KMutableProperty1
            public void set(Object receiver0, Object value) {
                ((DefaultPool) receiver0).top = ((Number) value).longValue();
            }
        };
        AtomicLongFieldUpdater<DefaultPool<?>> newUpdater = AtomicLongFieldUpdater.newUpdater(DefaultPool.class, p$iv.getName());
        Intrinsics.checkNotNullExpressionValue(newUpdater, "newUpdater(Owner::class.java, p.name)");
        Top = newUpdater;
    }
}
