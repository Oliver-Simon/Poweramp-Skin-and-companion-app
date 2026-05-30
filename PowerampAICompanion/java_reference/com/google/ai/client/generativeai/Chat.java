package com.google.ai.client.generativeai;

import android.graphics.Bitmap;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.ContentKt;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.InvalidStateException;
import com.maxmpz.poweramp.player.PowerampAPI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: Chat.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0002J\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0014H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00172\u0006\u0010\u0010\u001a\u00020\u0011J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00172\u0006\u0010\u0010\u001a\u00020\u0006J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00172\u0006\u0010\u0010\u001a\u00020\u0014J\f\u0010\u0018\u001a\u00020\r*\u00020\u0006H\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lcom/google/ai/client/generativeai/Chat;", "", "model", "Lcom/google/ai/client/generativeai/GenerativeModel;", "history", "", "Lcom/google/ai/client/generativeai/type/Content;", "(Lcom/google/ai/client/generativeai/GenerativeModel;Ljava/util/List;)V", "getHistory", "()Ljava/util/List;", PowerampAPI.EXTRA_LOCK, "Ljava/util/concurrent/Semaphore;", "attemptLock", "", "sendMessage", "Lcom/google/ai/client/generativeai/type/GenerateContentResponse;", "prompt", "Landroid/graphics/Bitmap;", "(Landroid/graphics/Bitmap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lcom/google/ai/client/generativeai/type/Content;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendMessageStream", "Lkotlinx/coroutines/flow/Flow;", "assertComesFromUser", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Chat {
    private final List<Content> history;
    private Semaphore lock;
    private final GenerativeModel model;

    public Chat(GenerativeModel model, List<Content> history) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(history, "history");
        this.model = model;
        this.history = history;
        this.lock = new Semaphore(1);
    }

    public /* synthetic */ Chat(GenerativeModel generativeModel, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(generativeModel, (i & 2) != 0 ? new ArrayList() : arrayList);
    }

    public final List<Content> getHistory() {
        return this.history;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object sendMessage(com.google.ai.client.generativeai.type.Content r10, kotlin.coroutines.Continuation<? super com.google.ai.client.generativeai.type.GenerateContentResponse> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.google.ai.client.generativeai.Chat$sendMessage$1
            if (r0 == 0) goto L14
            r0 = r11
            com.google.ai.client.generativeai.Chat$sendMessage$1 r0 = (com.google.ai.client.generativeai.Chat$sendMessage$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            com.google.ai.client.generativeai.Chat$sendMessage$1 r0 = new com.google.ai.client.generativeai.Chat$sendMessage$1
            r0.<init>(r9, r11)
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
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2c:
            java.lang.Object r10 = r0.L$1
            com.google.ai.client.generativeai.type.Content r10 = (com.google.ai.client.generativeai.type.Content) r10
            java.lang.Object r1 = r0.L$0
            com.google.ai.client.generativeai.Chat r1 = (com.google.ai.client.generativeai.Chat) r1
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L39
            r3 = r11
            goto L7c
        L39:
            r10 = move-exception
            goto La0
        L3b:
            kotlin.ResultKt.throwOnFailure(r11)
            r2 = r9
            r2.assertComesFromUser(r10)
            r2.attemptLock()
            com.google.ai.client.generativeai.GenerativeModel r3 = r2.model     // Catch: java.lang.Throwable -> L9e
            kotlin.jvm.internal.SpreadBuilder r4 = new kotlin.jvm.internal.SpreadBuilder     // Catch: java.lang.Throwable -> L9e
            r5 = 2
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L9e
            java.util.List<com.google.ai.client.generativeai.type.Content> r5 = r2.history     // Catch: java.lang.Throwable -> L9e
            java.util.Collection r5 = (java.util.Collection) r5     // Catch: java.lang.Throwable -> L9e
            r6 = 0
            r7 = r5
            r8 = 0
            com.google.ai.client.generativeai.type.Content[] r8 = new com.google.ai.client.generativeai.type.Content[r8]     // Catch: java.lang.Throwable -> L9e
            java.lang.Object[] r8 = r7.toArray(r8)     // Catch: java.lang.Throwable -> L9e
            r4.addSpread(r8)     // Catch: java.lang.Throwable -> L9e
            r4.add(r10)     // Catch: java.lang.Throwable -> L9e
            int r5 = r4.size()     // Catch: java.lang.Throwable -> L9e
            com.google.ai.client.generativeai.type.Content[] r5 = new com.google.ai.client.generativeai.type.Content[r5]     // Catch: java.lang.Throwable -> L9e
            java.lang.Object[] r4 = r4.toArray(r5)     // Catch: java.lang.Throwable -> L9e
            com.google.ai.client.generativeai.type.Content[] r4 = (com.google.ai.client.generativeai.type.Content[]) r4     // Catch: java.lang.Throwable -> L9e
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L9e
            r0.L$1 = r10     // Catch: java.lang.Throwable -> L9e
            r5 = 1
            r0.label = r5     // Catch: java.lang.Throwable -> L9e
            java.lang.Object r3 = r3.generateContent(r4, r0)     // Catch: java.lang.Throwable -> L9e
            if (r3 != r1) goto L7b
            return r1
        L7b:
            r1 = r2
        L7c:
            com.google.ai.client.generativeai.type.GenerateContentResponse r3 = (com.google.ai.client.generativeai.type.GenerateContentResponse) r3     // Catch: java.lang.Throwable -> L39
            java.util.List<com.google.ai.client.generativeai.type.Content> r2 = r1.history     // Catch: java.lang.Throwable -> L39
            r2.add(r10)     // Catch: java.lang.Throwable -> L39
            java.util.List<com.google.ai.client.generativeai.type.Content> r10 = r1.history     // Catch: java.lang.Throwable -> L39
            java.util.List r2 = r3.getCandidates()     // Catch: java.lang.Throwable -> L39
            java.lang.Object r2 = kotlin.collections.CollectionsKt.first(r2)     // Catch: java.lang.Throwable -> L39
            com.google.ai.client.generativeai.type.Candidate r2 = (com.google.ai.client.generativeai.type.Candidate) r2     // Catch: java.lang.Throwable -> L39
            com.google.ai.client.generativeai.type.Content r2 = r2.getContent()     // Catch: java.lang.Throwable -> L39
            r10.add(r2)     // Catch: java.lang.Throwable -> L39
            java.util.concurrent.Semaphore r10 = r1.lock
            r10.release()
            return r3
        L9e:
            r10 = move-exception
            r1 = r2
        La0:
            java.util.concurrent.Semaphore r2 = r1.lock
            r2.release()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.Chat.sendMessage(com.google.ai.client.generativeai.type.Content, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object sendMessage(final String prompt, Continuation<? super GenerateContentResponse> continuation) {
        Content content = ContentKt.content$default(null, new Function1<Content.Builder, Unit>() { // from class: com.google.ai.client.generativeai.Chat$sendMessage$content$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Content.Builder builder) {
                invoke2(builder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Content.Builder content2) {
                Intrinsics.checkNotNullParameter(content2, "$this$content");
                content2.addText(prompt);
            }
        }, 1, null);
        return sendMessage(content, continuation);
    }

    public final Object sendMessage(final Bitmap prompt, Continuation<? super GenerateContentResponse> continuation) {
        Content content = ContentKt.content$default(null, new Function1<Content.Builder, Unit>() { // from class: com.google.ai.client.generativeai.Chat$sendMessage$content$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Content.Builder builder) {
                invoke2(builder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Content.Builder content2) {
                Intrinsics.checkNotNullParameter(content2, "$this$content");
                content2.addImage(prompt);
            }
        }, 1, null);
        return sendMessage(content, continuation);
    }

    public final Flow<GenerateContentResponse> sendMessageStream(Content prompt) {
        Intrinsics.checkNotNullParameter(prompt, "prompt");
        assertComesFromUser(prompt);
        attemptLock();
        GenerativeModel generativeModel = this.model;
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        Collection $this$toTypedArray$iv = this.history;
        spreadBuilder.addSpread($this$toTypedArray$iv.toArray(new Content[0]));
        spreadBuilder.add(prompt);
        Flow flow = generativeModel.generateContentStream((Content[]) spreadBuilder.toArray(new Content[spreadBuilder.size()]));
        LinkedList bitmaps = new LinkedList();
        LinkedList blobs = new LinkedList();
        StringBuilder text = new StringBuilder();
        return FlowKt.onCompletion(FlowKt.onEach(flow, new Chat$sendMessageStream$1(text, bitmaps, blobs, null)), new Chat$sendMessageStream$2(this, prompt, bitmaps, blobs, text, null));
    }

    public final Flow<GenerateContentResponse> sendMessageStream(final String prompt) {
        Intrinsics.checkNotNullParameter(prompt, "prompt");
        Content content = ContentKt.content$default(null, new Function1<Content.Builder, Unit>() { // from class: com.google.ai.client.generativeai.Chat$sendMessageStream$content$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Content.Builder builder) {
                invoke2(builder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Content.Builder content2) {
                Intrinsics.checkNotNullParameter(content2, "$this$content");
                content2.addText(prompt);
            }
        }, 1, null);
        return sendMessageStream(content);
    }

    public final Flow<GenerateContentResponse> sendMessageStream(final Bitmap prompt) {
        Intrinsics.checkNotNullParameter(prompt, "prompt");
        Content content = ContentKt.content$default(null, new Function1<Content.Builder, Unit>() { // from class: com.google.ai.client.generativeai.Chat$sendMessageStream$content$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Content.Builder builder) {
                invoke2(builder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Content.Builder content2) {
                Intrinsics.checkNotNullParameter(content2, "$this$content");
                content2.addImage(prompt);
            }
        }, 1, null);
        return sendMessageStream(content);
    }

    private final void assertComesFromUser(Content $this$assertComesFromUser) {
        if (!CollectionsKt.contains(CollectionsKt.listOf((Object[]) new String[]{"user", "function"}), $this$assertComesFromUser.getRole())) {
            throw new InvalidStateException("Chat prompts should come from the 'user' or 'function' role.", null, 2, null);
        }
    }

    private final void attemptLock() {
        if (!this.lock.tryAcquire()) {
            throw new InvalidStateException("This chat instance currently has an ongoing request, please wait for it to complete before sending more messages", null, 2, null);
        }
    }
}
