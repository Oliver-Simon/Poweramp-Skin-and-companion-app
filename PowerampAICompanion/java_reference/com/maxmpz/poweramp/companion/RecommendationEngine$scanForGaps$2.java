package com.maxmpz.poweramp.companion;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RecommendationEngine.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/maxmpz/poweramp/companion/DiscoveryItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.RecommendationEngine$scanForGaps$2", f = "RecommendationEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class RecommendationEngine$scanForGaps$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends DiscoveryItem>>, Object> {
    final /* synthetic */ String $type;
    int label;
    final /* synthetic */ RecommendationEngine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecommendationEngine$scanForGaps$2(RecommendationEngine recommendationEngine, String str, Continuation<? super RecommendationEngine$scanForGaps$2> continuation) {
        super(2, continuation);
        this.this$0 = recommendationEngine;
        this.$type = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RecommendationEngine$scanForGaps$2(this.this$0, this.$type, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends DiscoveryItem>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<DiscoveryItem>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<DiscoveryItem>> continuation) {
        return ((RecommendationEngine$scanForGaps$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:131:0x02bb A[Catch: Exception -> 0x03d1, TryCatch #0 {Exception -> 0x03d1, blocks: (B:8:0x001a, B:10:0x002c, B:12:0x0038, B:13:0x0093, B:16:0x009f, B:18:0x00ab, B:21:0x00bb, B:23:0x01a6, B:25:0x01ac, B:28:0x01b6, B:35:0x01be, B:36:0x01f1, B:38:0x01f7, B:40:0x0203, B:42:0x020b, B:46:0x0246, B:49:0x025a, B:56:0x0216, B:57:0x021a, B:59:0x0220, B:61:0x0232, B:81:0x00c3, B:82:0x00f5, B:84:0x00fb, B:86:0x0107, B:88:0x0110, B:92:0x014f, B:99:0x015f, B:101:0x0196, B:105:0x011b, B:106:0x011f, B:108:0x0125, B:110:0x0138, B:123:0x029d, B:125:0x02a3, B:127:0x02ab, B:129:0x02b5, B:131:0x02bb, B:132:0x02eb, B:134:0x02f1, B:136:0x0303, B:138:0x030c, B:139:0x034d, B:141:0x0353, B:143:0x035f, B:146:0x036f, B:152:0x039d), top: B:7:0x001a }] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x030c A[Catch: Exception -> 0x03d1, TryCatch #0 {Exception -> 0x03d1, blocks: (B:8:0x001a, B:10:0x002c, B:12:0x0038, B:13:0x0093, B:16:0x009f, B:18:0x00ab, B:21:0x00bb, B:23:0x01a6, B:25:0x01ac, B:28:0x01b6, B:35:0x01be, B:36:0x01f1, B:38:0x01f7, B:40:0x0203, B:42:0x020b, B:46:0x0246, B:49:0x025a, B:56:0x0216, B:57:0x021a, B:59:0x0220, B:61:0x0232, B:81:0x00c3, B:82:0x00f5, B:84:0x00fb, B:86:0x0107, B:88:0x0110, B:92:0x014f, B:99:0x015f, B:101:0x0196, B:105:0x011b, B:106:0x011f, B:108:0x0125, B:110:0x0138, B:123:0x029d, B:125:0x02a3, B:127:0x02ab, B:129:0x02b5, B:131:0x02bb, B:132:0x02eb, B:134:0x02f1, B:136:0x0303, B:138:0x030c, B:139:0x034d, B:141:0x0353, B:143:0x035f, B:146:0x036f, B:152:0x039d), top: B:7:0x001a }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r25) {
        /*
            Method dump skipped, instructions count: 1034
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.RecommendationEngine$scanForGaps$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence invokeSuspend$lambda$0(Pair it) {
        return (CharSequence) it.getFirst();
    }
}
