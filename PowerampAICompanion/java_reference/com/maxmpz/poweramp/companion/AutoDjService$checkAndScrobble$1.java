package com.maxmpz.poweramp.companion;

import android.util.Log;
import com.maxmpz.poweramp.companion.db.ScrobbleDao;
import com.maxmpz.poweramp.companion.db.ScrobbleDatabase;
import com.maxmpz.poweramp.companion.db.ScrobbleEntity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AutoDjService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.AutoDjService$checkAndScrobble$1", f = "AutoDjService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class AutoDjService$checkAndScrobble$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $threshold;
    final /* synthetic */ Ref.LongRef $totalPlayedMs;
    int label;
    final /* synthetic */ AutoDjService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoDjService$checkAndScrobble$1(AutoDjService autoDjService, Ref.LongRef longRef, long j, Continuation<? super AutoDjService$checkAndScrobble$1> continuation) {
        super(2, continuation);
        this.this$0 = autoDjService;
        this.$totalPlayedMs = longRef;
        this.$threshold = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AutoDjService$checkAndScrobble$1(this.this$0, this.$totalPlayedMs, this.$threshold, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AutoDjService$checkAndScrobble$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        long j;
        String str;
        String str2;
        String str3;
        long j2;
        String str4;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                try {
                    ScrobbleDatabase db = ScrobbleDatabase.INSTANCE.getDatabase(this.this$0);
                    ScrobbleDao scrobbleDao = db.scrobbleDao();
                    j = this.this$0.currentScrobbleTrackId;
                    str = this.this$0.currentScrobbleTitle;
                    str2 = this.this$0.currentScrobbleArtist;
                    str3 = this.this$0.currentScrobbleAlbum;
                    long currentTimeMillis = System.currentTimeMillis();
                    j2 = this.this$0.currentScrobbleDuration;
                    scrobbleDao.insert(new ScrobbleEntity(0L, j, str, str2, str3, currentTimeMillis, j2, 1, null));
                    str4 = this.this$0.currentScrobbleTitle;
                    long j3 = 1000;
                    Log.d("AutoDjService", "Scrobble saved: " + str4 + " (Played " + (this.$totalPlayedMs.element / j3) + "s, Threshold: " + (this.$threshold / j3) + "s)");
                } catch (Exception e) {
                    Log.e("AutoDjService", "Scrobble failed: " + e.getMessage());
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
