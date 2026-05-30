package io.ktor.client.plugins.cache.storage;

import io.ktor.http.Url;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: FileCacheStorage.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.cache.storage.FileCacheStorage$store$2", f = "FileCacheStorage.kt", i = {0}, l = {72, 73}, m = "invokeSuspend", n = {"urlHex"}, s = {"L$0"})
/* loaded from: classes9.dex */
final class FileCacheStorage$store$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CachedResponseData $data;
    final /* synthetic */ Url $url;
    Object L$0;
    int label;
    final /* synthetic */ FileCacheStorage this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileCacheStorage$store$2(FileCacheStorage fileCacheStorage, Url url, CachedResponseData cachedResponseData, Continuation<? super FileCacheStorage$store$2> continuation) {
        super(2, continuation);
        this.this$0 = fileCacheStorage;
        this.$url = url;
        this.$data = cachedResponseData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FileCacheStorage$store$2(this.this$0, this.$url, this.$data, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FileCacheStorage$store$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0006. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:13:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0094 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0095  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            switch(r1) {
                case 0: goto L23;
                case 1: goto L17;
                case 2: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L11:
            r0 = r14
            kotlin.ResultKt.throwOnFailure(r15)
            goto L97
        L17:
            r1 = r14
            java.lang.Object r2 = r1.L$0
            java.lang.String r2 = (java.lang.String) r2
            kotlin.ResultKt.throwOnFailure(r15)
            r3 = r2
            r2 = r1
            r1 = r15
            goto L45
        L23:
            kotlin.ResultKt.throwOnFailure(r15)
            r1 = r14
            io.ktor.client.plugins.cache.storage.FileCacheStorage r2 = r1.this$0
            io.ktor.http.Url r3 = r1.$url
            java.lang.String r2 = io.ktor.client.plugins.cache.storage.FileCacheStorage.access$key(r2, r3)
            io.ktor.client.plugins.cache.storage.FileCacheStorage r3 = r1.this$0
            r4 = r1
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r1.L$0 = r2
            r5 = 1
            r1.label = r5
            java.lang.Object r3 = io.ktor.client.plugins.cache.storage.FileCacheStorage.access$readCache(r3, r2, r4)
            if (r3 != r0) goto L40
            return r0
        L40:
            r13 = r1
            r1 = r15
            r15 = r3
            r3 = r2
            r2 = r13
        L45:
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            io.ktor.client.plugins.cache.storage.CachedResponseData r4 = r2.$data
            r5 = 0
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Collection r6 = (java.util.Collection) r6
            r7 = 0
            java.util.Iterator r8 = r15.iterator()
        L56:
            boolean r15 = r8.hasNext()
            if (r15 == 0) goto L76
            java.lang.Object r15 = r8.next()
            r9 = r15
            io.ktor.client.plugins.cache.storage.CachedResponseData r9 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r9
            r10 = 0
            java.util.Map r11 = r9.getVaryKeys()
            java.util.Map r12 = r4.getVaryKeys()
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r12)
            if (r9 != 0) goto L56
            r6.add(r15)
            goto L56
        L76:
            r15 = r6
            java.util.List r15 = (java.util.List) r15
            java.util.Collection r15 = (java.util.Collection) r15
            io.ktor.client.plugins.cache.storage.CachedResponseData r4 = r2.$data
            java.util.List r15 = kotlin.collections.CollectionsKt.plus(r15, r4)
            io.ktor.client.plugins.cache.storage.FileCacheStorage r4 = r2.this$0
            r5 = r2
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r6 = 0
            r2.L$0 = r6
            r6 = 2
            r2.label = r6
            java.lang.Object r15 = io.ktor.client.plugins.cache.storage.FileCacheStorage.access$writeCache(r4, r3, r15, r5)
            if (r15 != r0) goto L95
            return r0
        L95:
            r15 = r1
            r0 = r2
        L97:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.FileCacheStorage$store$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
