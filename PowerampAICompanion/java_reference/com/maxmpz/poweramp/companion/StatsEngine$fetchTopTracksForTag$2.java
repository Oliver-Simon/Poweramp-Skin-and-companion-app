package com.maxmpz.poweramp.companion;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StatsEngine.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlin/Pair;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.StatsEngine$fetchTopTracksForTag$2", f = "StatsEngine.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class StatsEngine$fetchTopTracksForTag$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Pair<? extends String, ? extends String>>>, Object> {
    final /* synthetic */ String $tag;
    int label;
    final /* synthetic */ StatsEngine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatsEngine$fetchTopTracksForTag$2(StatsEngine statsEngine, String str, Continuation<? super StatsEngine$fetchTopTracksForTag$2> continuation) {
        super(2, continuation);
        this.this$0 = statsEngine;
        this.$tag = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StatsEngine$fetchTopTracksForTag$2(this.this$0, this.$tag, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Pair<? extends String, ? extends String>>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<Pair<String, String>>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<Pair<String, String>>> continuation) {
        return ((StatsEngine$fetchTopTracksForTag$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Context context;
        String artistName;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                context = this.this$0.context;
                SharedPreferences prefs = context.getSharedPreferences("PowerampCompanionPrefs", 0);
                String apiKey = prefs.getString("lastfm_api_key", "");
                if (apiKey == null) {
                    apiKey = "";
                }
                if (StringsKt.isBlank(apiKey)) {
                    return CollectionsKt.emptyList();
                }
                List results = new ArrayList();
                try {
                    String encodedTag = Uri.encode(this.$tag);
                    URL url = new URL("https://ws.audioscrobbler.com/2.0/?method=tag.gettoptracks&tag=" + encodedTag + "&api_key=" + apiKey + "&format=json&limit=100");
                    URLConnection openConnection = url.openConnection();
                    Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                    HttpURLConnection connection = (HttpURLConnection) openConnection;
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    if (connection.getResponseCode() == 200) {
                        Reader inputStreamReader = new InputStreamReader(connection.getInputStream(), Charsets.UTF_8);
                        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                        try {
                            String response = TextStreamsKt.readText(bufferedReader);
                            CloseableKt.closeFinally(bufferedReader, null);
                            JSONObject json = new JSONObject(response);
                            JSONObject optJSONObject = json.optJSONObject("tracks");
                            JSONArray tracks = optJSONObject != null ? optJSONObject.optJSONArray("track") : null;
                            if (tracks != null) {
                                int length = tracks.length();
                                for (int i = 0; i < length; i++) {
                                    JSONObject trackObj = tracks.getJSONObject(i);
                                    String trackName = trackObj.optString("name", "");
                                    JSONObject optJSONObject2 = trackObj.optJSONObject("artist");
                                    if (optJSONObject2 == null || (artistName = optJSONObject2.optString("name", "")) == null) {
                                        artistName = "";
                                    }
                                    if (!StringsKt.isBlank(trackName) && !StringsKt.isBlank(artistName)) {
                                        results.add(new Pair(artistName, trackName));
                                    }
                                }
                            }
                            Log.d("StatsEngine", "Fetched " + results.size() + " top tracks globally for tag: " + this.$tag);
                        } finally {
                        }
                    } else {
                        Log.e("StatsEngine", "Last.fm Tag API returned HTTP " + connection.getResponseCode());
                    }
                } catch (Exception e) {
                    Log.e("StatsEngine", "Error fetching top tracks for tag: " + this.$tag, e);
                }
                return results;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
