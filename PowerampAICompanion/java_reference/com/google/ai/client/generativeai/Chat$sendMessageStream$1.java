package com.google.ai.client.generativeai;

import android.graphics.Bitmap;
import com.google.ai.client.generativeai.type.BlobPart;
import com.google.ai.client.generativeai.type.Candidate;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.ImagePart;
import com.google.ai.client.generativeai.type.Part;
import com.google.ai.client.generativeai.type.TextPart;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: Chat.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lcom/google/ai/client/generativeai/type/GenerateContentResponse;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.google.ai.client.generativeai.Chat$sendMessageStream$1", f = "Chat.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class Chat$sendMessageStream$1 extends SuspendLambda implements Function2<GenerateContentResponse, Continuation<? super Unit>, Object> {
    final /* synthetic */ LinkedList<Bitmap> $bitmaps;
    final /* synthetic */ LinkedList<BlobPart> $blobs;
    final /* synthetic */ StringBuilder $text;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Chat$sendMessageStream$1(StringBuilder sb, LinkedList<Bitmap> linkedList, LinkedList<BlobPart> linkedList2, Continuation<? super Chat$sendMessageStream$1> continuation) {
        super(2, continuation);
        this.$text = sb;
        this.$bitmaps = linkedList;
        this.$blobs = linkedList2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Chat$sendMessageStream$1 chat$sendMessageStream$1 = new Chat$sendMessageStream$1(this.$text, this.$bitmaps, this.$blobs, continuation);
        chat$sendMessageStream$1.L$0 = obj;
        return chat$sendMessageStream$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(GenerateContentResponse generateContentResponse, Continuation<? super Unit> continuation) {
        return ((Chat$sendMessageStream$1) create(generateContentResponse, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                GenerateContentResponse it = (GenerateContentResponse) this.L$0;
                for (Part part : ((Candidate) CollectionsKt.first((List) it.getCandidates())).getContent().getParts()) {
                    if (part instanceof TextPart) {
                        this.$text.append(((TextPart) part).getText());
                    } else if (part instanceof ImagePart) {
                        this.$bitmaps.add(((ImagePart) part).getImage());
                    } else if (part instanceof BlobPart) {
                        this.$blobs.add(part);
                    }
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
