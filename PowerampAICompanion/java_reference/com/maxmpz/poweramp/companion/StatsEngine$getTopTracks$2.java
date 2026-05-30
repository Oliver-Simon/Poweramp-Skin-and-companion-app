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
@DebugMetadata(c = "com.maxmpz.poweramp.companion.StatsEngine$getTopTracks$2", f = "StatsEngine.kt", i = {}, l = {147, 149}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class StatsEngine$getTopTracks$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends StatsEngine.StatItem>>, Object> {
    final /* synthetic */ int $limit;
    final /* synthetic */ StatsEngine.TimeRange $range;
    final /* synthetic */ Integer $timeMachineYear;
    int label;
    final /* synthetic */ StatsEngine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatsEngine$getTopTracks$2(Integer num, StatsEngine statsEngine, StatsEngine.TimeRange timeRange, int i, Continuation<? super StatsEngine$getTopTracks$2> continuation) {
        super(2, continuation);
        this.$timeMachineYear = num;
        this.this$0 = statsEngine;
        this.$range = timeRange;
        this.$limit = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StatsEngine$getTopTracks$2(this.$timeMachineYear, this.this$0, this.$range, this.$limit, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends StatsEngine.StatItem>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<StatsEngine.StatItem>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<StatsEngine.StatItem>> continuation) {
        return ((StatsEngine$getTopTracks$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0018. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02db A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02c1 A[Catch: all -> 0x03f4, TryCatch #1 {all -> 0x03f4, blocks: (B:41:0x023d, B:43:0x0249, B:44:0x0250, B:46:0x0257, B:47:0x025e, B:49:0x0265, B:50:0x026a, B:52:0x0274, B:53:0x0279, B:55:0x0280, B:58:0x028b, B:61:0x0293, B:64:0x029d, B:65:0x02a4, B:67:0x02ac, B:73:0x02ce, B:76:0x02e3, B:78:0x02eb, B:80:0x02f0, B:84:0x02f9, B:85:0x0303, B:90:0x0318, B:91:0x031e, B:93:0x0324, B:100:0x0336, B:102:0x0344, B:106:0x0352, B:108:0x0368, B:110:0x0372, B:118:0x0380, B:132:0x02c1, B:138:0x03ec), top: B:40:0x023d, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0133 A[LOOP:1: B:20:0x012d->B:22:0x0133, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0239 A[Catch: Exception -> 0x03fd, TRY_LEAVE, TryCatch #0 {Exception -> 0x03fd, blocks: (B:37:0x01f9, B:39:0x0239, B:140:0x03f0, B:149:0x03f8, B:150:0x03fb, B:41:0x023d, B:43:0x0249, B:44:0x0250, B:46:0x0257, B:47:0x025e, B:49:0x0265, B:50:0x026a, B:52:0x0274, B:53:0x0279, B:55:0x0280, B:58:0x028b, B:61:0x0293, B:64:0x029d, B:65:0x02a4, B:67:0x02ac, B:73:0x02ce, B:76:0x02e3, B:78:0x02eb, B:80:0x02f0, B:84:0x02f9, B:85:0x0303, B:90:0x0318, B:91:0x031e, B:93:0x0324, B:100:0x0336, B:102:0x0344, B:106:0x0352, B:108:0x0368, B:110:0x0372, B:118:0x0380, B:132:0x02c1, B:138:0x03ec, B:146:0x03f6), top: B:36:0x01f9, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02e3 A[SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r36) {
        /*
            Method dump skipped, instructions count: 1044
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.StatsEngine$getTopTracks$2.invokeSuspend(java.lang.Object):java.lang.Object");
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
