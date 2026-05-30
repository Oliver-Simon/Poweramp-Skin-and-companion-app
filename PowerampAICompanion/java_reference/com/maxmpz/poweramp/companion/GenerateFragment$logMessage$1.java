package com.maxmpz.poweramp.companion;

import android.widget.ScrollView;
import android.widget.TextView;
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
/* compiled from: GenerateFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.GenerateFragment$logMessage$1", f = "GenerateFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class GenerateFragment$logMessage$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $message;
    final /* synthetic */ String $time;
    int label;
    final /* synthetic */ GenerateFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenerateFragment$logMessage$1(GenerateFragment generateFragment, String str, String str2, Continuation<? super GenerateFragment$logMessage$1> continuation) {
        super(2, continuation);
        this.this$0 = generateFragment;
        this.$time = str;
        this.$message = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new GenerateFragment$logMessage$1(this.this$0, this.$time, this.$message, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GenerateFragment$logMessage$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        TextView textView;
        ScrollView scrollView;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                textView = this.this$0.tvStatus;
                ScrollView scrollView2 = null;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tvStatus");
                    textView = null;
                }
                textView.append("\n[" + this.$time + "] " + this.$message);
                scrollView = this.this$0.svLog;
                if (scrollView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("svLog");
                } else {
                    scrollView2 = scrollView;
                }
                final GenerateFragment generateFragment = this.this$0;
                scrollView2.post(new Runnable() { // from class: com.maxmpz.poweramp.companion.GenerateFragment$logMessage$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        GenerateFragment$logMessage$1.invokeSuspend$lambda$0(GenerateFragment.this);
                    }
                });
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(GenerateFragment this$0) {
        ScrollView scrollView;
        scrollView = this$0.svLog;
        if (scrollView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("svLog");
            scrollView = null;
        }
        scrollView.fullScroll(130);
    }
}
