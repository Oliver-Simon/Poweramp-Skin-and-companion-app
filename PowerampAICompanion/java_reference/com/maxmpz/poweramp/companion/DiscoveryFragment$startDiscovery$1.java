package com.maxmpz.poweramp.companion;

import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DiscoveryFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.DiscoveryFragment$startDiscovery$1", f = "DiscoveryFragment.kt", i = {}, l = {101, 103, 118}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class DiscoveryFragment$startDiscovery$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $apiKey;
    final /* synthetic */ MainActivity $mainActivity;
    int label;
    final /* synthetic */ DiscoveryFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiscoveryFragment$startDiscovery$1(DiscoveryFragment discoveryFragment, MainActivity mainActivity, String str, Continuation<? super DiscoveryFragment$startDiscovery$1> continuation) {
        super(2, continuation);
        this.this$0 = discoveryFragment;
        this.$mainActivity = mainActivity;
        this.$apiKey = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DiscoveryFragment$startDiscovery$1(this.this$0, this.$mainActivity, this.$apiKey, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DiscoveryFragment$startDiscovery$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0009. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007f  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 0
            java.lang.String r3 = "DiscoveryFragment"
            switch(r1) {
                case 0: goto L24;
                case 1: goto L1d;
                case 2: goto L19;
                case 3: goto L14;
                default: goto Lc;
            }
        Lc:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L14:
            kotlin.ResultKt.throwOnFailure(r10)
            goto La9
        L19:
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L22
            goto L80
        L1d:
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L22
            r1 = r10
            goto L61
        L22:
            r1 = move-exception
            goto L85
        L24:
            kotlin.ResultKt.throwOnFailure(r10)
            com.maxmpz.poweramp.companion.DiscoveryFragment r1 = r9.this$0     // Catch: java.lang.Exception -> L22
            java.lang.String r1 = com.maxmpz.poweramp.companion.DiscoveryFragment.access$getCurrentDiscoveryType$p(r1)     // Catch: java.lang.Exception -> L22
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L22
            r4.<init>()     // Catch: java.lang.Exception -> L22
            java.lang.String r5 = "Starting discovery scan for type: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Exception -> L22
            java.lang.StringBuilder r1 = r4.append(r1)     // Catch: java.lang.Exception -> L22
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> L22
            android.util.Log.e(r3, r1)     // Catch: java.lang.Exception -> L22
            com.maxmpz.poweramp.companion.MainActivity r1 = r9.$mainActivity     // Catch: java.lang.Exception -> L22
            com.maxmpz.poweramp.companion.RecommendationEngine r1 = r1.getRecommendationEngine()     // Catch: java.lang.Exception -> L22
            java.lang.String r4 = r9.$apiKey     // Catch: java.lang.Exception -> L22
            com.maxmpz.poweramp.companion.DiscoveryFragment r5 = r9.this$0     // Catch: java.lang.Exception -> L22
            java.lang.String r5 = com.maxmpz.poweramp.companion.DiscoveryFragment.access$getCurrentDiscoveryType$p(r5)     // Catch: java.lang.Exception -> L22
            r6 = r9
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Exception -> L22
            r7 = 1
            r9.label = r7     // Catch: java.lang.Exception -> L22
            java.lang.Object r4 = r1.scanForGaps(r4, r5, r6)     // Catch: java.lang.Exception -> L22
            if (r4 != r0) goto L5f
            return r0
        L5f:
            r1 = r10
            r10 = r4
        L61:
            java.util.List r10 = (java.util.List) r10     // Catch: java.lang.Exception -> L81
            kotlinx.coroutines.MainCoroutineDispatcher r4 = kotlinx.coroutines.Dispatchers.getMain()     // Catch: java.lang.Exception -> L81
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4     // Catch: java.lang.Exception -> L81
            com.maxmpz.poweramp.companion.DiscoveryFragment$startDiscovery$1$1 r5 = new com.maxmpz.poweramp.companion.DiscoveryFragment$startDiscovery$1$1     // Catch: java.lang.Exception -> L81
            com.maxmpz.poweramp.companion.DiscoveryFragment r6 = r9.this$0     // Catch: java.lang.Exception -> L81
            r5.<init>(r10, r6, r2)     // Catch: java.lang.Exception -> L81
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch: java.lang.Exception -> L81
            r6 = r9
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Exception -> L81
            r7 = 2
            r9.label = r7     // Catch: java.lang.Exception -> L81
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r4, r5, r6)     // Catch: java.lang.Exception -> L81
            if (r2 != r0) goto L7f
            return r0
        L7f:
            r10 = r1
        L80:
            goto Laa
        L81:
            r10 = move-exception
            r8 = r1
            r1 = r10
            r10 = r8
        L85:
            java.lang.String r4 = "Error during discovery"
            r5 = r1
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            android.util.Log.e(r3, r4, r5)
            kotlinx.coroutines.MainCoroutineDispatcher r3 = kotlinx.coroutines.Dispatchers.getMain()
            kotlin.coroutines.CoroutineContext r3 = (kotlin.coroutines.CoroutineContext) r3
            com.maxmpz.poweramp.companion.DiscoveryFragment$startDiscovery$1$2 r4 = new com.maxmpz.poweramp.companion.DiscoveryFragment$startDiscovery$1$2
            com.maxmpz.poweramp.companion.DiscoveryFragment r5 = r9.this$0
            r4.<init>(r5, r1, r2)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r2 = r9
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r5 = 3
            r9.label = r5
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r3, r4, r2)
            if (r1 != r0) goto La9
            return r0
        La9:
        Laa:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.DiscoveryFragment$startDiscovery$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DiscoveryFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.maxmpz.poweramp.companion.DiscoveryFragment$startDiscovery$1$1", f = "DiscoveryFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.maxmpz.poweramp.companion.DiscoveryFragment$startDiscovery$1$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<DiscoveryItem> $results;
        int label;
        final /* synthetic */ DiscoveryFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(List<DiscoveryItem> list, DiscoveryFragment discoveryFragment, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$results = list;
            this.this$0 = discoveryFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$results, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v25, types: [android.view.View] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CircularProgressIndicator circularProgressIndicator;
            SwipeRefreshLayout swipeRefreshLayout;
            View view;
            RecyclerView recyclerView;
            ?? r0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    Log.e("DiscoveryFragment", "Discovery scan finished, found " + this.$results.size() + " items");
                    circularProgressIndicator = this.this$0.progressDiscovery;
                    RecyclerView recyclerView2 = null;
                    if (circularProgressIndicator == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("progressDiscovery");
                        circularProgressIndicator = null;
                    }
                    circularProgressIndicator.setVisibility(8);
                    swipeRefreshLayout = this.this$0.swipeRefresh;
                    if (swipeRefreshLayout == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("swipeRefresh");
                        swipeRefreshLayout = null;
                    }
                    swipeRefreshLayout.setRefreshing(false);
                    if (this.$results.isEmpty()) {
                        r0 = this.this$0.layoutEmpty;
                        if (r0 == 0) {
                            Intrinsics.throwUninitializedPropertyAccessException("layoutEmpty");
                        } else {
                            recyclerView2 = r0;
                        }
                        recyclerView2.setVisibility(0);
                        Toast.makeText(this.this$0.getContext(), "Keine neuen Entdeckungen gefunden.", 1).show();
                        this.this$0.showDebugDialog();
                    } else {
                        view = this.this$0.layoutEmpty;
                        if (view == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("layoutEmpty");
                            view = null;
                        }
                        view.setVisibility(8);
                        recyclerView = this.this$0.rvDiscovery;
                        if (recyclerView == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("rvDiscovery");
                        } else {
                            recyclerView2 = recyclerView;
                        }
                        recyclerView2.setAdapter(new DiscoveryAdapter(this.$results, this.this$0.requireContext()));
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DiscoveryFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.maxmpz.poweramp.companion.DiscoveryFragment$startDiscovery$1$2", f = "DiscoveryFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.maxmpz.poweramp.companion.DiscoveryFragment$startDiscovery$1$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Exception $e;
        int label;
        final /* synthetic */ DiscoveryFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(DiscoveryFragment discoveryFragment, Exception exc, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = discoveryFragment;
            this.$e = exc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, this.$e, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CircularProgressIndicator circularProgressIndicator;
            SwipeRefreshLayout swipeRefreshLayout;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    circularProgressIndicator = this.this$0.progressDiscovery;
                    SwipeRefreshLayout swipeRefreshLayout2 = null;
                    if (circularProgressIndicator == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("progressDiscovery");
                        circularProgressIndicator = null;
                    }
                    circularProgressIndicator.setVisibility(8);
                    swipeRefreshLayout = this.this$0.swipeRefresh;
                    if (swipeRefreshLayout == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("swipeRefresh");
                    } else {
                        swipeRefreshLayout2 = swipeRefreshLayout;
                    }
                    swipeRefreshLayout2.setRefreshing(false);
                    Toast.makeText(this.this$0.requireContext(), "Fehler beim Discovery Radar: " + this.$e.getMessage(), 1).show();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
