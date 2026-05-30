package com.maxmpz.poweramp.companion;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GenerateFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.GenerateFragment$generateSuggestions$1", f = "GenerateFragment.kt", i = {}, l = {250, 266}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class GenerateFragment$generateSuggestions$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Long $limitDurationMs;
    final /* synthetic */ Integer $limitTracks;
    final /* synthetic */ String $prompt;
    int label;
    final /* synthetic */ GenerateFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenerateFragment$generateSuggestions$1(GenerateFragment generateFragment, String str, Integer num, Long l, Continuation<? super GenerateFragment$generateSuggestions$1> continuation) {
        super(2, continuation);
        this.this$0 = generateFragment;
        this.$prompt = str;
        this.$limitTracks = num;
        this.$limitDurationMs = l;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GenerateFragment$generateSuggestions$1(this.this$0, this.$prompt, this.$limitTracks, this.$limitDurationMs, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GenerateFragment$generateSuggestions$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0009. Please report as an issue. */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x00fd: MOVE (r0 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY] A[D('$result' java.lang.Object)]) (LINE:266), block:B:43:0x00fd */
    /* JADX WARN: Type inference failed for: r5v13, types: [java.util.List, T] */
    /* JADX WARN: Type inference failed for: r5v18, types: [java.util.List, T] */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.util.List, T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object $result2;
        Object $result3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
        } catch (Exception e) {
            this.label = 2;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass3(this.this$0, e, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            $result3 = $result2;
        }
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                FragmentActivity requireActivity = this.this$0.requireActivity();
                Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.maxmpz.poweramp.companion.MainActivity");
                RecommendationEngine engine = ((MainActivity) requireActivity).getRecommendationEngine();
                String str = this.$prompt;
                final GenerateFragment generateFragment = this.this$0;
                ?? parseAndRecommend = engine.parseAndRecommend(str, new Function1() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$generateSuggestions$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Unit invokeSuspend$lambda$0;
                        invokeSuspend$lambda$0 = GenerateFragment$generateSuggestions$1.invokeSuspend$lambda$0(GenerateFragment.this, (String) obj);
                        return invokeSuspend$lambda$0;
                    }
                });
                Ref.ObjectRef finalTracks = new Ref.ObjectRef();
                finalTracks.element = parseAndRecommend;
                if (this.$limitTracks != null && this.$limitTracks.intValue() > 0) {
                    finalTracks.element = CollectionsKt.take((Iterable) finalTracks.element, this.$limitTracks.intValue());
                }
                if (this.$limitDurationMs != null && this.$limitDurationMs.longValue() > 0) {
                    Ref.LongRef currentLength = new Ref.LongRef();
                    Iterable iterable = (Iterable) finalTracks.element;
                    Long l = this.$limitDurationMs;
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : iterable) {
                        currentLength.element += ((PowerampTrack) obj).getDurationMs();
                        if (currentLength.element < l.longValue()) {
                            arrayList.add(obj);
                        } else {
                            finalTracks.element = arrayList;
                        }
                    }
                    finalTracks.element = arrayList;
                }
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(this.this$0, finalTracks, this.$prompt, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            case 2:
                $result3 = $result;
                ResultKt.throwOnFailure($result3);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$0(GenerateFragment this$0, String logStr) {
        this$0.logMessage(logStr);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GenerateFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.maxmpz.poweramp.companion.GenerateFragment$generateSuggestions$1$2", f = "GenerateFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.maxmpz.poweramp.companion.GenerateFragment$generateSuggestions$1$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<List<PowerampTrack>> $finalTracks;
        final /* synthetic */ String $prompt;
        int label;
        final /* synthetic */ GenerateFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(GenerateFragment generateFragment, Ref.ObjectRef<List<PowerampTrack>> objectRef, String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = generateFragment;
            this.$finalTracks = objectRef;
            this.$prompt = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, this.$finalTracks, this.$prompt, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            GenerateViewModel viewModel;
            GenerateViewModel viewModel2;
            RecyclerView recyclerView;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.this$0.showLoading(false);
                    viewModel = this.this$0.getViewModel();
                    viewModel.setGenerating(false);
                    if (this.$finalTracks.element.isEmpty()) {
                        this.this$0.logMessage("Keine Tracks für deine Anfrage gefunden!");
                        recyclerView = this.this$0.rvSuggestions;
                        if (recyclerView == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("rvSuggestions");
                            recyclerView = null;
                        }
                        recyclerView.setAdapter(null);
                    } else {
                        viewModel2 = this.this$0.getViewModel();
                        viewModel2.setGeneratedTracks(this.$finalTracks.element);
                        String usedPrompt = this.$prompt;
                        if (usedPrompt == null) {
                            usedPrompt = "Smart Mix";
                        }
                        this.this$0.saveToHistory(usedPrompt);
                        this.this$0.loadHistoryChips();
                        this.this$0.displayTracks(this.$finalTracks.element);
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GenerateFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "com.maxmpz.poweramp.companion.GenerateFragment$generateSuggestions$1$3", f = "GenerateFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.maxmpz.poweramp.companion.GenerateFragment$generateSuggestions$1$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Exception $e;
        int label;
        final /* synthetic */ GenerateFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(GenerateFragment generateFragment, Exception exc, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.this$0 = generateFragment;
            this.$e = exc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(this.this$0, this.$e, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            GenerateViewModel viewModel;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.this$0.showLoading(false);
                    viewModel = this.this$0.getViewModel();
                    viewModel.setGenerating(false);
                    this.this$0.logMessage("Error: " + this.$e.getMessage());
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
