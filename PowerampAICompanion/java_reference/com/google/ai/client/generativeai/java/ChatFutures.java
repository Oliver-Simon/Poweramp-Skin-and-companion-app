package com.google.ai.client.generativeai.java;

import androidx.concurrent.futures.SuspendToFutureAdapter;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.ai.client.generativeai.Chat;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.reactive.ReactiveFlowKt;
import org.reactivestreams.Publisher;

/* compiled from: ChatFutures.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \f2\u00020\u0001:\u0002\f\rB\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH&J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\b\u001a\u00020\tH&¨\u0006\u000e"}, d2 = {"Lcom/google/ai/client/generativeai/java/ChatFutures;", "", "()V", "getChat", "Lcom/google/ai/client/generativeai/Chat;", "sendMessage", "Lcom/google/common/util/concurrent/ListenableFuture;", "Lcom/google/ai/client/generativeai/type/GenerateContentResponse;", "prompt", "Lcom/google/ai/client/generativeai/type/Content;", "sendMessageStream", "Lorg/reactivestreams/Publisher;", "Companion", "FuturesImpl", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ChatFutures {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final ChatFutures from(Chat chat) {
        return INSTANCE.from(chat);
    }

    public abstract Chat getChat();

    public abstract ListenableFuture<GenerateContentResponse> sendMessage(Content prompt);

    public abstract Publisher<GenerateContentResponse> sendMessageStream(Content prompt);

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ChatFutures.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/google/ai/client/generativeai/java/ChatFutures$FuturesImpl;", "Lcom/google/ai/client/generativeai/java/ChatFutures;", "chat", "Lcom/google/ai/client/generativeai/Chat;", "(Lcom/google/ai/client/generativeai/Chat;)V", "getChat", "sendMessage", "Lcom/google/common/util/concurrent/ListenableFuture;", "Lcom/google/ai/client/generativeai/type/GenerateContentResponse;", "prompt", "Lcom/google/ai/client/generativeai/type/Content;", "sendMessageStream", "Lorg/reactivestreams/Publisher;", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class FuturesImpl extends ChatFutures {
        private final Chat chat;

        public FuturesImpl(Chat chat) {
            Intrinsics.checkNotNullParameter(chat, "chat");
            this.chat = chat;
        }

        @Override // com.google.ai.client.generativeai.java.ChatFutures
        public ListenableFuture<GenerateContentResponse> sendMessage(Content prompt) {
            Intrinsics.checkNotNullParameter(prompt, "prompt");
            return SuspendToFutureAdapter.launchFuture$default(SuspendToFutureAdapter.INSTANCE, null, false, new ChatFutures$FuturesImpl$sendMessage$1(this, prompt, null), 3, null);
        }

        @Override // com.google.ai.client.generativeai.java.ChatFutures
        public Publisher<GenerateContentResponse> sendMessageStream(Content prompt) {
            Intrinsics.checkNotNullParameter(prompt, "prompt");
            return ReactiveFlowKt.asPublisher$default(this.chat.sendMessageStream(prompt), null, 1, null);
        }

        @Override // com.google.ai.client.generativeai.java.ChatFutures
        public Chat getChat() {
            return this.chat;
        }
    }

    /* compiled from: ChatFutures.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/google/ai/client/generativeai/java/ChatFutures$Companion;", "", "()V", TypedValues.TransitionType.S_FROM, "Lcom/google/ai/client/generativeai/java/ChatFutures;", "chat", "Lcom/google/ai/client/generativeai/Chat;", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ChatFutures from(Chat chat) {
            Intrinsics.checkNotNullParameter(chat, "chat");
            return new FuturesImpl(chat);
        }
    }
}
