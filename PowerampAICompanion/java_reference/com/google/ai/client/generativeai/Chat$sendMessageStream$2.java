package com.google.ai.client.generativeai;

import android.graphics.Bitmap;
import com.google.ai.client.generativeai.type.BlobPart;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.ContentKt;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Chat.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/google/ai/client/generativeai/type/GenerateContentResponse;", "it", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.ai.client.generativeai.Chat$sendMessageStream$2", f = "Chat.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class Chat$sendMessageStream$2 extends SuspendLambda implements Function3<FlowCollector<? super GenerateContentResponse>, Throwable, Continuation<? super Unit>, Object> {
    final /* synthetic */ LinkedList<Bitmap> $bitmaps;
    final /* synthetic */ LinkedList<BlobPart> $blobs;
    final /* synthetic */ Content $prompt;
    final /* synthetic */ StringBuilder $text;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ Chat this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Chat$sendMessageStream$2(Chat chat, Content content, LinkedList<Bitmap> linkedList, LinkedList<BlobPart> linkedList2, StringBuilder sb, Continuation<? super Chat$sendMessageStream$2> continuation) {
        super(3, continuation);
        this.this$0 = chat;
        this.$prompt = content;
        this.$bitmaps = linkedList;
        this.$blobs = linkedList2;
        this.$text = sb;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(FlowCollector<? super GenerateContentResponse> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
        Chat$sendMessageStream$2 chat$sendMessageStream$2 = new Chat$sendMessageStream$2(this.this$0, this.$prompt, this.$bitmaps, this.$blobs, this.$text, continuation);
        chat$sendMessageStream$2.L$0 = th;
        return chat$sendMessageStream$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Semaphore semaphore;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Throwable it = (Throwable) this.L$0;
                semaphore = this.this$0.lock;
                semaphore.release();
                if (it == null) {
                    final LinkedList<Bitmap> linkedList = this.$bitmaps;
                    final LinkedList<BlobPart> linkedList2 = this.$blobs;
                    final StringBuilder sb = this.$text;
                    Content content = ContentKt.content("model", new Function1<Content.Builder, Unit>() { // from class: com.google.ai.client.generativeai.Chat$sendMessageStream$2$content$1
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
                            Iterator<Bitmap> it2 = linkedList.iterator();
                            while (it2.hasNext()) {
                                Bitmap bitmap = it2.next();
                                Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
                                content2.addImage(bitmap);
                            }
                            Iterator<BlobPart> it3 = linkedList2.iterator();
                            while (it3.hasNext()) {
                                BlobPart blob = it3.next();
                                content2.addBlob(blob.getMimeType(), blob.getBlob());
                            }
                            if (!StringsKt.isBlank(sb)) {
                                String sb2 = sb.toString();
                                Intrinsics.checkNotNullExpressionValue(sb2, "text.toString()");
                                content2.addText(sb2);
                            }
                        }
                    });
                    this.this$0.getHistory().add(this.$prompt);
                    this.this$0.getHistory().add(content);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
