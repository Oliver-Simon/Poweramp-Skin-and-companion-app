package io.ktor.util.cio;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.InputStream;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;

/* compiled from: InputStreamAdapters.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"toByteReadChannel", "Lio/ktor/utils/io/ByteReadChannel;", "Ljava/io/InputStream;", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "context", "Lkotlin/coroutines/CoroutineContext;", "parent", "Lkotlinx/coroutines/Job;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class InputStreamAdaptersKt {
    public static /* synthetic */ ByteReadChannel toByteReadChannel$default(InputStream inputStream, ObjectPool objectPool, CoroutineContext coroutineContext, Job job, int i, Object obj) {
        CompletableJob Job$default;
        if ((i & 1) != 0) {
            objectPool = ByteBufferPoolKt.getKtorDefaultPool();
        }
        if ((i & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        if ((i & 4) != 0) {
            Job$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
            job = Job$default;
        }
        return toByteReadChannel(inputStream, objectPool, coroutineContext, job);
    }

    public static final ByteReadChannel toByteReadChannel(InputStream $this$toByteReadChannel, ObjectPool<ByteBuffer> pool, CoroutineContext context, Job parent) {
        Intrinsics.checkNotNullParameter($this$toByteReadChannel, "<this>");
        Intrinsics.checkNotNullParameter(pool, "pool");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parent, "parent");
        return CoroutinesKt.writer(CoroutineScopeKt.CoroutineScope(context), (CoroutineContext) parent, true, (Function2<? super WriterScope, ? super Continuation<? super Unit>, ? extends Object>) new InputStreamAdaptersKt$toByteReadChannel$1(pool, $this$toByteReadChannel, null)).getChannel();
    }
}
