package com.maxmpz.poweramp.companion;

import android.content.Context;
import com.maxmpz.poweramp.companion.db.ScrobbleDatabase;
import com.maxmpz.poweramp.companion.db.ScrobbleEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: StatsEngine.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.StatsEngine$getListeningStreak$2", f = "StatsEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
final class StatsEngine$getListeningStreak$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends Integer, ? extends Integer>>, Object> {
    int label;
    final /* synthetic */ StatsEngine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatsEngine$getListeningStreak$2(StatsEngine statsEngine, Continuation<? super StatsEngine$getListeningStreak$2> continuation) {
        super(2, continuation);
        this.this$0 = statsEngine;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StatsEngine$getListeningStreak$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends Integer, ? extends Integer>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Pair<Integer, Integer>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Pair<Integer, Integer>> continuation) {
        return ((StatsEngine$getListeningStreak$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Context context;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                ScrobbleDatabase.Companion companion = ScrobbleDatabase.INSTANCE;
                context = this.this$0.context;
                ScrobbleDatabase db = companion.getDatabase(context);
                Iterable allScrobbles = db.scrobbleDao().getAllScrobbles();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-DDD", Locale.getDefault());
                Iterable iterable = allScrobbles;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    arrayList.add(sdf.format(new Date(((ScrobbleEntity) it.next()).getTimestamp())));
                }
                SortedSet daysWithScrobbles = CollectionsKt.toSortedSet((List) arrayList);
                if (daysWithScrobbles.isEmpty()) {
                    return new Pair(Boxing.boxInt(0), Boxing.boxInt(0));
                }
                Calendar cal = Calendar.getInstance();
                int currentStreak = 0;
                if (!daysWithScrobbles.contains(sdf.format(cal.getTime()))) {
                    cal.add(6, -1);
                }
                while (daysWithScrobbles.contains(sdf.format(cal.getTime()))) {
                    currentStreak++;
                    cal.add(6, -1);
                }
                int longestStreak = 0;
                int tempStreak = 0;
                Calendar cal2 = Calendar.getInstance();
                Iterator<T> it2 = allScrobbles.iterator();
                if (!it2.hasNext()) {
                    throw new NoSuchElementException();
                }
                long timestamp = ((ScrobbleEntity) it2.next()).getTimestamp();
                while (it2.hasNext()) {
                    long timestamp2 = ((ScrobbleEntity) it2.next()).getTimestamp();
                    if (timestamp > timestamp2) {
                        timestamp = timestamp2;
                    }
                }
                cal2.setTimeInMillis(timestamp);
                String today = sdf.format(Calendar.getInstance().getTime());
                while (sdf.format(cal2.getTime()).compareTo(today) <= 0) {
                    if (daysWithScrobbles.contains(sdf.format(cal2.getTime()))) {
                        tempStreak++;
                        longestStreak = Math.max(longestStreak, tempStreak);
                    } else {
                        tempStreak = 0;
                    }
                    cal2.add(6, 1);
                }
                return new Pair(Boxing.boxInt(currentStreak), Boxing.boxInt(longestStreak));
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
