package com.maxmpz.poweramp.companion;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.maxmpz.poweramp.companion.StatsEngine;
import com.maxmpz.poweramp.player.PowerampAPI;
import com.maxmpz.poweramp.player.TableDefs;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: StatsEngine.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/maxmpz/poweramp/companion/StatsEngine$StatItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.StatsEngine$getAllAlbumsFromPoweramp$2", f = "StatsEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
final class StatsEngine$getAllAlbumsFromPoweramp$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<StatsEngine.StatItem>>, Object> {
    int label;
    final /* synthetic */ StatsEngine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatsEngine$getAllAlbumsFromPoweramp$2(StatsEngine statsEngine, Continuation<? super StatsEngine$getAllAlbumsFromPoweramp$2> continuation) {
        super(2, continuation);
        this.this$0 = statsEngine;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StatsEngine$getAllAlbumsFromPoweramp$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<StatsEngine.StatItem>> continuation) {
        return ((StatsEngine$getAllAlbumsFromPoweramp$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Context context;
        String str;
        String str2;
        Uri albumArtUri;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                List albums = new ArrayList();
                Uri albumsUri = PowerampAPI.ROOT_URI.buildUpon().appendEncodedPath(TableDefs.Albums.TABLE).build();
                String[] projection = {"albums._id as _id", "album", "artist"};
                try {
                    context = this.this$0.context;
                    Cursor query = context.getContentResolver().query(albumsUri, projection, null, null, null);
                    if (query != null) {
                        Cursor cursor = query;
                        StatsEngine statsEngine = this.this$0;
                        try {
                            Cursor cursor2 = cursor;
                            int columnIndexOrThrow = cursor2.getColumnIndexOrThrow("_id");
                            int columnIndex = cursor2.getColumnIndex("album");
                            int columnIndex2 = cursor2.getColumnIndex("artist");
                            while (cursor2.moveToNext()) {
                                long j = cursor2.getLong(columnIndexOrThrow);
                                if (columnIndex < 0 || (str = cursor2.getString(columnIndex)) == null) {
                                    str = "";
                                }
                                if (columnIndex2 < 0 || (str2 = cursor2.getString(columnIndex2)) == null) {
                                    str2 = "";
                                }
                                if (!StringsKt.isBlank(str)) {
                                    statsEngine.getAlbumArt(TableDefs.Albums.TABLE, j);
                                    StatsEngine.ItemType itemType = StatsEngine.ItemType.ALBUM;
                                    albumArtUri = statsEngine.getAlbumArtUri(TableDefs.Albums.TABLE, j);
                                    albums.add(new StatsEngine.StatItem(str, str2, null, 0, 0L, albumArtUri, itemType, j, 20, null));
                                }
                            }
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(cursor, null);
                        } finally {
                        }
                    }
                } catch (Exception e) {
                    Log.e("StatsEngine", "getAllAlbumsFromPoweramp failed: " + e.getMessage());
                    e.printStackTrace();
                }
                return albums;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
