package io.ktor.client.plugins.cookies;

import io.ktor.http.Cookie;
import io.ktor.util.date.GMTDate;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: AcceptAllCookiesStorage.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0018\u00002\u00020\u0019B\u0007¢\u0006\u0004\b\u0001\u0010\u0002J#\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0096@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000e\u0010\u0002J!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\u0004\u001a\u00020\u0003H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lio/ktor/client/plugins/cookies/AcceptAllCookiesStorage;", "<init>", "()V", "Lio/ktor/http/Url;", "requestUrl", "Lio/ktor/http/Cookie;", "cookie", "", "addCookie", "(Lio/ktor/http/Url;Lio/ktor/http/Cookie;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "timestamp", "cleanup", "(J)V", "close", "", "get", "(Lio/ktor/http/Url;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "container", "Ljava/util/List;", "Lkotlinx/coroutines/sync/Mutex;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "ktor-client-core", "Lio/ktor/client/plugins/cookies/CookiesStorage;"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class AcceptAllCookiesStorage implements CookiesStorage {
    private final List<Cookie> container = new ArrayList();
    private volatile /* synthetic */ long oldestCookie = 0;
    private final Mutex mutex = MutexKt.Mutex$default(false, 1, null);

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0066 A[Catch: all -> 0x009e, TryCatch #0 {all -> 0x009e, blocks: (B:14:0x005c, B:16:0x0066, B:17:0x0069, B:18:0x007a, B:20:0x0080, B:23:0x008e, B:28:0x0092), top: B:13:0x005c }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0080 A[Catch: all -> 0x009e, TryCatch #0 {all -> 0x009e, blocks: (B:14:0x005c, B:16:0x0066, B:17:0x0069, B:18:0x007a, B:20:0x0080, B:23:0x008e, B:28:0x0092), top: B:13:0x005c }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // io.ktor.client.plugins.cookies.CookiesStorage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object get(io.ktor.http.Url r14, kotlin.coroutines.Continuation<? super java.util.List<io.ktor.http.Cookie>> r15) {
        /*
            r13 = this;
            boolean r0 = r15 instanceof io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$get$1
            if (r0 == 0) goto L14
            r0 = r15
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$get$1 r0 = (io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$get$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L19
        L14:
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$get$1 r0 = new io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$get$1
            r0.<init>(r13, r15)
        L19:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L3e;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L2c:
            r14 = 0
            r1 = 0
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r3 = r0.L$1
            io.ktor.http.Url r3 = (io.ktor.http.Url) r3
            java.lang.Object r4 = r0.L$0
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage r4 = (io.ktor.client.plugins.cookies.AcceptAllCookiesStorage) r4
            kotlin.ResultKt.throwOnFailure(r15)
            goto L5a
        L3e:
            kotlin.ResultKt.throwOnFailure(r15)
            r4 = r13
            r3 = r14
            kotlinx.coroutines.sync.Mutex r2 = r4.mutex
            r14 = 0
            r5 = 0
            r0.L$0 = r4
            r0.L$1 = r3
            r0.L$2 = r2
            r6 = 1
            r0.label = r6
            java.lang.Object r6 = r2.lock(r14, r0)
            if (r6 != r1) goto L58
            return r1
        L58:
            r1 = r14
            r14 = r5
        L5a:
            r5 = 0
            long r6 = io.ktor.util.date.DateJvmKt.getTimeMillis()     // Catch: java.lang.Throwable -> L9e
            long r8 = r4.oldestCookie     // Catch: java.lang.Throwable -> L9e
            int r8 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r8 < 0) goto L69
            r4.cleanup(r6)     // Catch: java.lang.Throwable -> L9e
        L69:
            java.util.List<io.ktor.http.Cookie> r6 = r4.container     // Catch: java.lang.Throwable -> L9e
            java.lang.Iterable r6 = (java.lang.Iterable) r6     // Catch: java.lang.Throwable -> L9e
            r4 = 0
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L9e
            r7.<init>()     // Catch: java.lang.Throwable -> L9e
            java.util.Collection r7 = (java.util.Collection) r7     // Catch: java.lang.Throwable -> L9e
            r8 = 0
            java.util.Iterator r9 = r6.iterator()     // Catch: java.lang.Throwable -> L9e
        L7a:
            boolean r6 = r9.hasNext()     // Catch: java.lang.Throwable -> L9e
            if (r6 == 0) goto L92
            java.lang.Object r6 = r9.next()     // Catch: java.lang.Throwable -> L9e
            r10 = r6
            io.ktor.http.Cookie r10 = (io.ktor.http.Cookie) r10     // Catch: java.lang.Throwable -> L9e
            r11 = 0
            boolean r12 = io.ktor.client.plugins.cookies.CookiesStorageKt.matches(r10, r3)     // Catch: java.lang.Throwable -> L9e
            if (r12 == 0) goto L7a
            r7.add(r6)     // Catch: java.lang.Throwable -> L9e
            goto L7a
        L92:
            r3 = r7
            java.util.List r3 = (java.util.List) r3     // Catch: java.lang.Throwable -> L9e
            r2.unlock(r1)
            return r3
        L9e:
            r3 = move-exception
            r2.unlock(r1)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.get(io.ktor.http.Url, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0073 A[Catch: all -> 0x00a9, TryCatch #0 {all -> 0x00a9, blocks: (B:14:0x0065, B:16:0x0073, B:18:0x008f, B:20:0x009a, B:21:0x009f), top: B:13:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // io.ktor.client.plugins.cookies.CookiesStorage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object addCookie(io.ktor.http.Url r11, io.ktor.http.Cookie r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r10 = this;
            boolean r0 = r13 instanceof io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$1
            if (r0 == 0) goto L14
            r0 = r13
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$1 r0 = (io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L19
        L14:
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$1 r0 = new io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$1
            r0.<init>(r10, r13)
        L19:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L42;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L2c:
            r11 = 0
            r12 = 0
            java.lang.Object r1 = r0.L$3
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r2 = r0.L$2
            io.ktor.http.Cookie r2 = (io.ktor.http.Cookie) r2
            java.lang.Object r3 = r0.L$1
            io.ktor.http.Url r3 = (io.ktor.http.Url) r3
            java.lang.Object r4 = r0.L$0
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage r4 = (io.ktor.client.plugins.cookies.AcceptAllCookiesStorage) r4
            kotlin.ResultKt.throwOnFailure(r13)
            goto L61
        L42:
            kotlin.ResultKt.throwOnFailure(r13)
            r4 = r10
            r2 = r12
            r3 = r11
            kotlinx.coroutines.sync.Mutex r11 = r4.mutex
            r12 = 0
            r5 = 0
            r0.L$0 = r4
            r0.L$1 = r3
            r0.L$2 = r2
            r0.L$3 = r11
            r6 = 1
            r0.label = r6
            java.lang.Object r6 = r11.lock(r12, r0)
            if (r6 != r1) goto L5f
            return r1
        L5f:
            r1 = r11
            r11 = r5
        L61:
            r5 = 0
            r6 = r2
            r7 = 0
            java.lang.String r8 = r6.getName()     // Catch: java.lang.Throwable -> La9
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8     // Catch: java.lang.Throwable -> La9
            boolean r8 = kotlin.text.StringsKt.isBlank(r8)     // Catch: java.lang.Throwable -> La9
            if (r8 != 0) goto L9f
        L73:
            java.util.List<io.ktor.http.Cookie> r6 = r4.container     // Catch: java.lang.Throwable -> La9
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$2$2 r7 = new io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$2$2     // Catch: java.lang.Throwable -> La9
            r7.<init>()     // Catch: java.lang.Throwable -> La9
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7     // Catch: java.lang.Throwable -> La9
            kotlin.collections.CollectionsKt.removeAll(r6, r7)     // Catch: java.lang.Throwable -> La9
            java.util.List<io.ktor.http.Cookie> r6 = r4.container     // Catch: java.lang.Throwable -> La9
            io.ktor.http.Cookie r7 = io.ktor.client.plugins.cookies.CookiesStorageKt.fillDefaults(r2, r3)     // Catch: java.lang.Throwable -> La9
            r6.add(r7)     // Catch: java.lang.Throwable -> La9
            io.ktor.util.date.GMTDate r3 = r2.getExpires()     // Catch: java.lang.Throwable -> La9
            if (r3 == 0) goto L9e
            long r6 = r3.getTimestamp()     // Catch: java.lang.Throwable -> La9
            r2 = 0
            long r8 = r4.oldestCookie     // Catch: java.lang.Throwable -> La9
            int r3 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r3 <= 0) goto L9c
            r4.oldestCookie = r6     // Catch: java.lang.Throwable -> La9
        L9c:
        L9e:
        L9f:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> La9
            r1.unlock(r12)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        La9:
            r2 = move-exception
            r1.unlock(r12)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.addCookie(io.ktor.http.Url, io.ktor.http.Cookie, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    private final void cleanup(final long timestamp) {
        Iterable $this$fold$iv;
        int $i$f$fold;
        CollectionsKt.removeAll((List) this.container, (Function1) new Function1<Cookie, Boolean>() { // from class: io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$cleanup$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Cookie cookie) {
                Intrinsics.checkNotNullParameter(cookie, "cookie");
                GMTDate expires = cookie.getExpires();
                if (expires == null) {
                    return false;
                }
                long expires2 = expires.getTimestamp();
                return Boolean.valueOf(expires2 < timestamp);
            }
        });
        Iterable $this$fold$iv2 = this.container;
        int $i$f$fold2 = 0;
        long accumulator$iv = Long.MAX_VALUE;
        for (Object element$iv : $this$fold$iv2) {
            Cookie cookie = (Cookie) element$iv;
            long acc = accumulator$iv;
            GMTDate expires = cookie.getExpires();
            if (expires != null) {
                $this$fold$iv = $this$fold$iv2;
                $i$f$fold = $i$f$fold2;
                long it = expires.getTimestamp();
                acc = Math.min(acc, it);
            } else {
                $this$fold$iv = $this$fold$iv2;
                $i$f$fold = $i$f$fold2;
            }
            accumulator$iv = acc;
            $this$fold$iv2 = $this$fold$iv;
            $i$f$fold2 = $i$f$fold;
        }
        this.oldestCookie = accumulator$iv;
    }
}
