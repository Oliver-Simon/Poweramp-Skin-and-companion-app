package com.google.ai.client.generativeai.java;

import androidx.concurrent.futures.SuspendToFutureAdapter;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.CountTokensResponse;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.reactive.ReactiveFlowKt;
import org.reactivestreams.Publisher;

/* compiled from: GenerativeModelFutures.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\b&\u0018\u0000 \u00152\u00020\u0001:\u0002\u0015\u0016B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J'\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\bH&¢\u0006\u0002\u0010\tJ'\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00042\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\bH&¢\u0006\u0002\u0010\tJ'\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\bH&¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&J\u0016\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0014H&¨\u0006\u0017"}, d2 = {"Lcom/google/ai/client/generativeai/java/GenerativeModelFutures;", "", "()V", "countTokens", "Lcom/google/common/util/concurrent/ListenableFuture;", "Lcom/google/ai/client/generativeai/type/CountTokensResponse;", "prompt", "", "Lcom/google/ai/client/generativeai/type/Content;", "([Lcom/google/ai/client/generativeai/type/Content;)Lcom/google/common/util/concurrent/ListenableFuture;", "generateContent", "Lcom/google/ai/client/generativeai/type/GenerateContentResponse;", "generateContentStream", "Lorg/reactivestreams/Publisher;", "([Lcom/google/ai/client/generativeai/type/Content;)Lorg/reactivestreams/Publisher;", "getGenerativeModel", "Lcom/google/ai/client/generativeai/GenerativeModel;", "startChat", "Lcom/google/ai/client/generativeai/java/ChatFutures;", "history", "", "Companion", "FuturesImpl", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class GenerativeModelFutures {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final GenerativeModelFutures from(GenerativeModel generativeModel) {
        return INSTANCE.from(generativeModel);
    }

    public abstract ListenableFuture<CountTokensResponse> countTokens(Content... prompt);

    public abstract ListenableFuture<GenerateContentResponse> generateContent(Content... prompt);

    public abstract Publisher<GenerateContentResponse> generateContentStream(Content... prompt);

    /* renamed from: getGenerativeModel */
    public abstract GenerativeModel getModel();

    public abstract ChatFutures startChat();

    public abstract ChatFutures startChat(List<Content> history);

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GenerativeModelFutures.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J'\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\"\u00020\nH\u0016¢\u0006\u0002\u0010\u000bJ'\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00062\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\"\u00020\nH\u0016¢\u0006\u0002\u0010\u000bJ'\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\"\u00020\nH\u0016¢\u0006\u0002\u0010\u0010J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0016\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/google/ai/client/generativeai/java/GenerativeModelFutures$FuturesImpl;", "Lcom/google/ai/client/generativeai/java/GenerativeModelFutures;", "model", "Lcom/google/ai/client/generativeai/GenerativeModel;", "(Lcom/google/ai/client/generativeai/GenerativeModel;)V", "countTokens", "Lcom/google/common/util/concurrent/ListenableFuture;", "Lcom/google/ai/client/generativeai/type/CountTokensResponse;", "prompt", "", "Lcom/google/ai/client/generativeai/type/Content;", "([Lcom/google/ai/client/generativeai/type/Content;)Lcom/google/common/util/concurrent/ListenableFuture;", "generateContent", "Lcom/google/ai/client/generativeai/type/GenerateContentResponse;", "generateContentStream", "Lorg/reactivestreams/Publisher;", "([Lcom/google/ai/client/generativeai/type/Content;)Lorg/reactivestreams/Publisher;", "getGenerativeModel", "startChat", "Lcom/google/ai/client/generativeai/java/ChatFutures;", "history", "", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class FuturesImpl extends GenerativeModelFutures {
        private final GenerativeModel model;

        public FuturesImpl(GenerativeModel model) {
            Intrinsics.checkNotNullParameter(model, "model");
            this.model = model;
        }

        @Override // com.google.ai.client.generativeai.java.GenerativeModelFutures
        public ListenableFuture<GenerateContentResponse> generateContent(Content... prompt) {
            Intrinsics.checkNotNullParameter(prompt, "prompt");
            return SuspendToFutureAdapter.launchFuture$default(SuspendToFutureAdapter.INSTANCE, null, false, new GenerativeModelFutures$FuturesImpl$generateContent$1(this, prompt, null), 3, null);
        }

        @Override // com.google.ai.client.generativeai.java.GenerativeModelFutures
        public Publisher<GenerateContentResponse> generateContentStream(Content... prompt) {
            Intrinsics.checkNotNullParameter(prompt, "prompt");
            return ReactiveFlowKt.asPublisher$default(this.model.generateContentStream((Content[]) Arrays.copyOf(prompt, prompt.length)), null, 1, null);
        }

        @Override // com.google.ai.client.generativeai.java.GenerativeModelFutures
        public ListenableFuture<CountTokensResponse> countTokens(Content... prompt) {
            Intrinsics.checkNotNullParameter(prompt, "prompt");
            return SuspendToFutureAdapter.launchFuture$default(SuspendToFutureAdapter.INSTANCE, null, false, new GenerativeModelFutures$FuturesImpl$countTokens$1(this, prompt, null), 3, null);
        }

        @Override // com.google.ai.client.generativeai.java.GenerativeModelFutures
        public ChatFutures startChat() {
            return startChat(CollectionsKt.emptyList());
        }

        @Override // com.google.ai.client.generativeai.java.GenerativeModelFutures
        public ChatFutures startChat(List<Content> history) {
            Intrinsics.checkNotNullParameter(history, "history");
            return ChatFutures.INSTANCE.from(this.model.startChat(history));
        }

        @Override // com.google.ai.client.generativeai.java.GenerativeModelFutures
        /* renamed from: getGenerativeModel, reason: from getter */
        public GenerativeModel getModel() {
            return this.model;
        }
    }

    /* compiled from: GenerativeModelFutures.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/google/ai/client/generativeai/java/GenerativeModelFutures$Companion;", "", "()V", TypedValues.TransitionType.S_FROM, "Lcom/google/ai/client/generativeai/java/GenerativeModelFutures;", "model", "Lcom/google/ai/client/generativeai/GenerativeModel;", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final GenerativeModelFutures from(GenerativeModel model) {
            Intrinsics.checkNotNullParameter(model, "model");
            return new FuturesImpl(model);
        }
    }
}
