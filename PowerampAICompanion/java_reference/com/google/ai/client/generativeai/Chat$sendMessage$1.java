package com.google.ai.client.generativeai;

import com.google.ai.client.generativeai.type.Content;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Chat.kt */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.ai.client.generativeai.Chat", f = "Chat.kt", i = {0, 0}, l = {60}, m = "sendMessage", n = {"this", "prompt"}, s = {"L$0", "L$1"})
/* loaded from: classes.dex */
public final class Chat$sendMessage$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ Chat this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Chat$sendMessage$1(Chat chat, Continuation<? super Chat$sendMessage$1> continuation) {
        super(continuation);
        this.this$0 = chat;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.sendMessage((Content) null, this);
    }
}
