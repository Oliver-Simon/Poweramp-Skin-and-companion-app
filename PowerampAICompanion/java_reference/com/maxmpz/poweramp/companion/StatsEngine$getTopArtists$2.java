package com.maxmpz.poweramp.companion;

import com.maxmpz.poweramp.companion.StatsEngine;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StatsEngine.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/maxmpz/poweramp/companion/StatsEngine$StatItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.StatsEngine$getTopArtists$2", f = "StatsEngine.kt", i = {}, l = {266, 268}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class StatsEngine$getTopArtists$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends StatsEngine.StatItem>>, Object> {
    final /* synthetic */ int $limit;
    final /* synthetic */ StatsEngine.TimeRange $range;
    final /* synthetic */ Integer $timeMachineYear;
    int label;
    final /* synthetic */ StatsEngine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatsEngine$getTopArtists$2(Integer num, StatsEngine statsEngine, StatsEngine.TimeRange timeRange, int i, Continuation<? super StatsEngine$getTopArtists$2> continuation) {
        super(2, continuation);
        this.$timeMachineYear = num;
        this.this$0 = statsEngine;
        this.$range = timeRange;
        this.$limit = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StatsEngine$getTopArtists$2(this.$timeMachineYear, this.this$0, this.$range, this.$limit, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends StatsEngine.StatItem>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<StatsEngine.StatItem>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<StatsEngine.StatItem>> continuation) {
        return ((StatsEngine$getTopArtists$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0148 A[LOOP:4: B:43:0x0142->B:45:0x0148, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x020f A[Catch: Exception -> 0x02d9, TRY_LEAVE, TryCatch #2 {Exception -> 0x02d9, blocks: (B:59:0x01fc, B:61:0x020f, B:115:0x02cc, B:123:0x02d4, B:124:0x02d7, B:63:0x0213, B:64:0x0221, B:68:0x022b, B:72:0x0233, B:79:0x023c, B:82:0x0249, B:83:0x0253, B:85:0x0259, B:92:0x0269, B:96:0x027c, B:104:0x028e, B:113:0x02c6, B:120:0x02d2), top: B:58:0x01fc, inners: #0, #1 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r26) {
        /*
            Method dump skipped, instructions count: 752
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.StatsEngine$getTopArtists$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    private static final String invokeSuspend$normalizeFast(String s) {
        if (s.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int inParen = 0;
        String lowerCase = s.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        int length = lowerCase.length();
        for (int i = 0; i < length; i++) {
            char charAt = lowerCase.charAt(i);
            if (charAt == '(' || charAt == '[') {
                inParen++;
            } else if ((charAt == ')' || charAt == ']') && inParen > 0) {
                inParen--;
            } else if (inParen == 0) {
                if (!('a' <= charAt && charAt < '{')) {
                    if (!('0' <= charAt && charAt < ':')) {
                    }
                }
                sb.append(charAt);
            }
        }
        return StringsKt.trim((CharSequence) sb.toString()).toString();
    }
}
