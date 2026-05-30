package com.maxmpz.poweramp.companion;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.ContentKt;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.GenerationConfig;
import com.google.ai.client.generativeai.type.GenerationConfigKt;
import com.maxmpz.poweramp.player.RouterConsts;
import com.maxmpz.poweramp.player.TableDefs;
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
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: RecommendationEngine.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u0000 02\u00020\u0001:\u0002/0B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J.\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000f0\u000eJ6\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002JR\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0018\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00160\t2\u0018\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00160\t2\u0006\u0010\u0013\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\u0018J&\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\t2\u0006\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\u001cJ\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020\fH\u0002J&\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\t2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020&H\u0002J\u0012\u0010'\u001a\u0004\u0018\u00010\f2\u0006\u0010\u001f\u001a\u00020\fH\u0002J\u001a\u0010(\u001a\u0004\u0018\u00010\f2\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020\fH\u0002J6\u0010)\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010*\u001a\u0004\u0018\u00010\u00112\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J$\u0010+\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00160\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u0012\u0010,\u001a\u0004\u0018\u00010\f2\u0006\u0010-\u001a\u00020\fH\u0002J\u0012\u0010.\u001a\u0004\u0018\u00010\f2\u0006\u0010-\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/maxmpz/poweramp/companion/RecommendationEngine;", "", "context", "Landroid/content/Context;", "powerampController", "Lcom/maxmpz/poweramp/companion/PowerampController;", "<init>", "(Landroid/content/Context;Lcom/maxmpz/poweramp/companion/PowerampController;)V", "parseAndRecommend", "", "Lcom/maxmpz/poweramp/companion/PowerampTrack;", "prompt", "", "onLog", "Lkotlin/Function1;", "", "extractIntentWithGemini", "Lcom/maxmpz/poweramp/companion/DiscoveryIntent;", "apiKey", "blacklist", "generateInsights", "topTracks", "Lkotlin/Pair;", "recentTracks", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scanForGaps", "Lcom/maxmpz/poweramp/companion/DiscoveryItem;", "type", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isSongMissingLocally", "", "artist", "title", "fetchFromItunes", "Lcom/maxmpz/poweramp/companion/RecommendationEngine$ItunesResult;", "query", "entity", "limit", "", "fetchRepresentativeArtworkForArtist", "fetchArtworkUrl", "runHybridPlaylisting", "intent", "runLocalHeuristics", "extractJsonArray", "input", "extractJsonObject", "ItunesResult", "Companion", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class RecommendationEngine {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static String lastDebugInfo = "No scan performed yet.";
    private final Context context;
    private final PowerampController powerampController;

    public RecommendationEngine(Context context, PowerampController powerampController) {
        this.context = context;
        this.powerampController = powerampController;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List parseAndRecommend$default(RecommendationEngine recommendationEngine, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: com.maxmpz.poweramp.companion.RecommendationEngine$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Unit parseAndRecommend$lambda$0;
                    parseAndRecommend$lambda$0 = RecommendationEngine.parseAndRecommend$lambda$0((String) obj2);
                    return parseAndRecommend$lambda$0;
                }
            };
        }
        return recommendationEngine.parseAndRecommend(str, function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit parseAndRecommend$lambda$0(String it) {
        return Unit.INSTANCE;
    }

    public final List<PowerampTrack> parseAndRecommend(String prompt, Function1<? super String, Unit> onLog) {
        SharedPreferences prefs = this.context.getSharedPreferences("PowerampCompanionPrefs", 0);
        String apiKey = prefs.getString("gemini_api_key", "");
        String string = prefs.getString("blacklist_filter", "");
        String blacklist = string != null ? string : "";
        boolean isMagicToken = prompt != null && StringsKt.startsWith$default(prompt, "MAGIC_TOKEN_", false, 2, (Object) null);
        DiscoveryIntent intent = null;
        if (!isMagicToken) {
            String str = apiKey;
            if (!(str == null || StringsKt.isBlank(str)) && prompt != null && !StringsKt.isBlank(prompt)) {
                try {
                    onLog.invoke("Gemini API Key detected. Extracting intent from prompt...");
                    intent = extractIntentWithGemini(apiKey, prompt, blacklist, onLog);
                } catch (Exception e) {
                    e.printStackTrace();
                    onLog.invoke("Error during Gemini intent extraction: " + e.getMessage());
                }
            }
        }
        if (intent != null) {
            List hybridTracks = runHybridPlaylisting(prompt, intent, onLog);
            if (!hybridTracks.isEmpty()) {
                return hybridTracks;
            }
        }
        onLog.invoke("No Gemini Key, empty prompt, or API failed. Using local DB heuristics...");
        List recommendedPairs = runLocalHeuristics(prompt);
        if (recommendedPairs.isEmpty() || (recommendedPairs.size() == 1 && Intrinsics.areEqual(recommendedPairs.get(0).getFirst(), "MAGIC_TOKEN_FALLBACK"))) {
            onLog.invoke("Fallback activated: Selecting top favorite tracks instead.");
            return this.powerampController.findTracks(CollectionsKt.listOf(new Pair("MAGIC_TOKEN_FALLBACK", "MAGIC_TOKEN_FALLBACK")));
        }
        onLog.invoke("Analyzed Last.fm / DB mapping.");
        List mappedTracks = this.powerampController.findTracks(recommendedPairs);
        if (mappedTracks.isEmpty()) {
            onLog.invoke("No exact matches found. Generating smart fallback mix...");
            return this.powerampController.findTracks(CollectionsKt.listOf(new Pair("MAGIC_TOKEN_FALLBACK", "MAGIC_TOKEN_FALLBACK")));
        }
        return mappedTracks;
    }

    private final DiscoveryIntent extractIntentWithGemini(String apiKey, final String prompt, final String blacklist, Function1<? super String, Unit> onLog) {
        Object runBlocking$default;
        String responseText = "{}";
        GenerativeModel generativeModel = new GenerativeModel("gemini-2.0-flash", apiKey, GenerationConfigKt.generationConfig(new Function1() { // from class: com.maxmpz.poweramp.companion.RecommendationEngine$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit extractIntentWithGemini$lambda$1;
                extractIntentWithGemini$lambda$1 = RecommendationEngine.extractIntentWithGemini$lambda$1((GenerationConfig.Builder) obj);
                return extractIntentWithGemini$lambda$1;
            }
        }), null, null, null, null, null, 248, null);
        Content inputContent = ContentKt.content$default(null, new Function1() { // from class: com.maxmpz.poweramp.companion.RecommendationEngine$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit extractIntentWithGemini$lambda$2;
                extractIntentWithGemini$lambda$2 = RecommendationEngine.extractIntentWithGemini$lambda$2(prompt, blacklist, (Content.Builder) obj);
                return extractIntentWithGemini$lambda$2;
            }
        }, 1, null);
        try {
            runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new RecommendationEngine$extractIntentWithGemini$responseText$response$1(generativeModel, inputContent, null), 1, null);
            GenerateContentResponse response = (GenerateContentResponse) runBlocking$default;
            String text = response.getText();
            if (text == null) {
                text = "{}";
            }
            Log.d("RecommendationEngine", "Gemini Intent Response: " + text);
            responseText = text;
        } catch (Exception e) {
            e.printStackTrace();
            String message = e.getMessage();
            onLog.invoke("Gemini Intent API Fehler: " + (message != null ? StringsKt.take(message, 100) : null));
        }
        try {
            String jsonStr = extractJsonObject(responseText);
            if (jsonStr == null) {
                onLog.invoke("Gemini did not return a valid JSON object.");
                return null;
            }
            JSONObject jsonObject = new JSONObject(jsonStr);
            List tags = new ArrayList();
            JSONArray tagsArray = jsonObject.optJSONArray("tags");
            if (tagsArray != null) {
                int length = tagsArray.length();
                for (int i = 0; i < length; i++) {
                    tags.add(tagsArray.getString(i));
                }
            }
            List artists = new ArrayList();
            JSONArray artistsArray = jsonObject.optJSONArray(TableDefs.Artists.TABLE);
            if (artistsArray != null) {
                int length2 = artistsArray.length();
                for (int i2 = 0; i2 < length2; i2++) {
                    artists.add(artistsArray.getString(i2));
                }
            }
            List keywords = new ArrayList();
            JSONArray keywordsArray = jsonObject.optJSONArray("keywords");
            if (keywordsArray != null) {
                int i3 = 0;
                for (int length3 = keywordsArray.length(); i3 < length3; length3 = length3) {
                    keywords.add(keywordsArray.getString(i3));
                    i3++;
                }
            }
            onLog.invoke("Intent extracted: Tags=" + tags + ", Artists=" + artists + ", Keywords=" + keywords);
            return new DiscoveryIntent(tags, artists, keywords);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit extractIntentWithGemini$lambda$1(GenerationConfig.Builder $this$generationConfig) {
        $this$generationConfig.responseMimeType = "application/json";
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit extractIntentWithGemini$lambda$2(String $prompt, String $blacklist, Content.Builder $this$content) {
        $this$content.addText(StringsKt.trimIndent("\n                You are a smart AI music assistant. Analyze the user's music request and extract the core intent into a JSON object.\n                \n                User request: \"" + $prompt + "\"\n                Blacklist (ignore these): \"" + $blacklist + "\"\n                \n                Return exactly a JSON object with this schema:\n                {\n                    \"tags\": [\"genre or mood tag 1\", \"tag 2\"],\n                    \"artists\": [\"artist name 1\"],\n                    \"keywords\": [\"any other specific keywords like 'soundtrack' or 'ost'\"]\n                }\n                \n                Keep lists concise (max 3 items each). If none apply, leave the array empty.\n            "));
        return Unit.INSTANCE;
    }

    public final Object generateInsights(String apiKey, List<Pair<String, String>> list, List<Pair<String, String>> list2, String blacklist, Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new RecommendationEngine$generateInsights$2(apiKey, list, list2, blacklist, null), continuation);
    }

    public static /* synthetic */ Object scanForGaps$default(RecommendationEngine recommendationEngine, String str, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "ALL";
        }
        return recommendationEngine.scanForGaps(str, str2, continuation);
    }

    public final Object scanForGaps(String apiKey, String type, Continuation<? super List<DiscoveryItem>> continuation) {
        lastDebugInfo = "Local Smart Scan started (Type: " + type + ")...";
        return BuildersKt.withContext(Dispatchers.getIO(), new RecommendationEngine$scanForGaps$2(this, type, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isSongMissingLocally(String artist, String title) {
        List matches = this.powerampController.findTracks(CollectionsKt.listOf(new Pair(artist, title)));
        return matches.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: RecommendationEngine.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J3\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/maxmpz/poweramp/companion/RecommendationEngine$ItunesResult;", "", "artist", "", "title", "imageUrl", "genre", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getArtist", "()Ljava/lang/String;", "getTitle", "getImageUrl", "getGenre", "component1", "component2", "component3", "component4", "copy", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "hashCode", "", "toString", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public static final /* data */ class ItunesResult {
        private final String artist;
        private final String genre;
        private final String imageUrl;
        private final String title;

        public static /* synthetic */ ItunesResult copy$default(ItunesResult itunesResult, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = itunesResult.artist;
            }
            if ((i & 2) != 0) {
                str2 = itunesResult.title;
            }
            if ((i & 4) != 0) {
                str3 = itunesResult.imageUrl;
            }
            if ((i & 8) != 0) {
                str4 = itunesResult.genre;
            }
            return itunesResult.copy(str, str2, str3, str4);
        }

        /* renamed from: component1, reason: from getter */
        public final String getArtist() {
            return this.artist;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* renamed from: component3, reason: from getter */
        public final String getImageUrl() {
            return this.imageUrl;
        }

        /* renamed from: component4, reason: from getter */
        public final String getGenre() {
            return this.genre;
        }

        public final ItunesResult copy(String artist, String title, String imageUrl, String genre) {
            return new ItunesResult(artist, title, imageUrl, genre);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ItunesResult)) {
                return false;
            }
            ItunesResult itunesResult = (ItunesResult) other;
            return Intrinsics.areEqual(this.artist, itunesResult.artist) && Intrinsics.areEqual(this.title, itunesResult.title) && Intrinsics.areEqual(this.imageUrl, itunesResult.imageUrl) && Intrinsics.areEqual(this.genre, itunesResult.genre);
        }

        public int hashCode() {
            return (((((this.artist.hashCode() * 31) + this.title.hashCode()) * 31) + this.imageUrl.hashCode()) * 31) + (this.genre == null ? 0 : this.genre.hashCode());
        }

        public String toString() {
            return "ItunesResult(artist=" + this.artist + ", title=" + this.title + ", imageUrl=" + this.imageUrl + ", genre=" + this.genre + ")";
        }

        public ItunesResult(String artist, String title, String imageUrl, String genre) {
            this.artist = artist;
            this.title = title;
            this.imageUrl = imageUrl;
            this.genre = genre;
        }

        public final String getArtist() {
            return this.artist;
        }

        public final String getGenre() {
            return this.genre;
        }

        public final String getImageUrl() {
            return this.imageUrl;
        }

        public final String getTitle() {
            return this.title;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<ItunesResult> fetchFromItunes(String query, String entity, int limit) {
        HttpURLConnection connection;
        List out = new ArrayList();
        try {
            String encoded = Uri.encode(query);
            try {
                URL url = new URL("https://itunes.apple.com/search?term=" + encoded + "&limit=" + limit + "&entity=" + entity);
                URLConnection openConnection = url.openConnection();
                Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                HttpURLConnection connection2 = (HttpURLConnection) openConnection;
                Reader inputStreamReader = new InputStreamReader(connection2.getInputStream(), Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    String response = TextStreamsKt.readText(bufferedReader);
                    CloseableKt.closeFinally(bufferedReader, null);
                    JSONObject json = new JSONObject(response);
                    JSONArray jsonResults = json.getJSONArray("results");
                    int i = 0;
                    int length = jsonResults.length();
                    while (i < length) {
                        JSONObject obj = jsonResults.getJSONObject(i);
                        String artistName = obj.optString("artistName");
                        String title = "";
                        if (artistName == null) {
                            artistName = "";
                        }
                        String collectionName = obj.optString("collectionName");
                        if (collectionName == null) {
                            collectionName = "";
                        }
                        String encoded2 = encoded;
                        String trackName = obj.optString("trackName");
                        if (trackName == null) {
                            trackName = "";
                        }
                        String trackName2 = trackName;
                        String genre = obj.optString("primaryGenreName");
                        URL url2 = url;
                        String artwork = StringsKt.replace$default(obj.optString("artworkUrl100"), "100x100", "600x600", false, 4, (Object) null);
                        if (!Intrinsics.areEqual(entity, "musicArtist")) {
                            connection = connection2;
                        } else {
                            try {
                                if (StringsKt.isBlank(artwork)) {
                                    connection = connection2;
                                } else {
                                    connection = connection2;
                                    if (Intrinsics.areEqual(artwork, AbstractJsonLexerKt.NULL)) {
                                    }
                                }
                                String fetchRepresentativeArtworkForArtist = fetchRepresentativeArtworkForArtist(artistName);
                                if (fetchRepresentativeArtworkForArtist == null) {
                                    fetchRepresentativeArtworkForArtist = "https://cdn-icons-png.flaticon.com/512/3043/3043665.png";
                                }
                                artwork = fetchRepresentativeArtworkForArtist;
                            } catch (Exception e) {
                                e = e;
                                e.printStackTrace();
                                return out;
                            }
                        }
                        if (Intrinsics.areEqual(entity, "album")) {
                            title = collectionName;
                        } else if (!Intrinsics.areEqual(entity, "musicArtist")) {
                            title = trackName2;
                        }
                        if (!StringsKt.isBlank(artistName) || !StringsKt.isBlank(trackName2)) {
                            out.add(new ItunesResult(artistName, title, artwork, genre));
                        }
                        i++;
                        encoded = encoded2;
                        url = url2;
                        connection2 = connection;
                    }
                } finally {
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
        }
        return out;
    }

    private final String fetchRepresentativeArtworkForArtist(String artist) {
        try {
            String encoded = Uri.encode(artist);
            URL url = new URL("https://itunes.apple.com/search?term=" + encoded + "&limit=1&entity=musicTrack");
            URLConnection openConnection = url.openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection connection = (HttpURLConnection) openConnection;
            Reader inputStreamReader = new InputStreamReader(connection.getInputStream(), Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                String response = TextStreamsKt.readText(bufferedReader);
                CloseableKt.closeFinally(bufferedReader, null);
                JSONObject json = new JSONObject(response);
                JSONArray results = json.getJSONArray("results");
                if (results.length() > 0) {
                    return StringsKt.replace$default(results.getJSONObject(0).optString("artworkUrl100"), "100x100", "600x600", false, 4, (Object) null);
                }
            } finally {
            }
        } catch (Exception e) {
        }
        return null;
    }

    private final String fetchArtworkUrl(String artist, String title) {
        try {
        } catch (Exception e) {
            e = e;
        }
        try {
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return null;
        }
        try {
            String query = Uri.encode(artist + " " + title);
            URL url = new URL("https://itunes.apple.com/search?term=" + query + "&limit=1&entity=musicTrack");
            URLConnection openConnection = url.openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection connection = (HttpURLConnection) openConnection;
            connection.setRequestMethod("GET");
            Reader inputStreamReader = new InputStreamReader(connection.getInputStream(), Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                String response = TextStreamsKt.readText(bufferedReader);
                CloseableKt.closeFinally(bufferedReader, null);
                JSONObject json = new JSONObject(response);
                JSONArray results = json.getJSONArray("results");
                if (results.length() <= 0) {
                    return null;
                }
                JSONObject firstMatch = results.getJSONObject(0);
                return StringsKt.replace$default(firstMatch.optString("artworkUrl100"), "100x100", "600x600", false, 4, (Object) null);
            } finally {
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0298 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x025f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<com.maxmpz.poweramp.companion.PowerampTrack> runHybridPlaylisting(java.lang.String r29, com.maxmpz.poweramp.companion.DiscoveryIntent r30, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r31) {
        /*
            Method dump skipped, instructions count: 1109
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.RecommendationEngine.runHybridPlaylisting(java.lang.String, com.maxmpz.poweramp.companion.DiscoveryIntent, kotlin.jvm.functions.Function1):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x003e, code lost:
    
        if (r11 == null) goto L6;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01e0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> runLocalHeuristics(java.lang.String r24) {
        /*
            Method dump skipped, instructions count: 1027
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maxmpz.poweramp.companion.RecommendationEngine.runLocalHeuristics(java.lang.String):java.util.List");
    }

    private final String extractJsonArray(String input) {
        int start = StringsKt.indexOf$default((CharSequence) input, "[", 0, false, 6, (Object) null);
        int end = StringsKt.lastIndexOf$default((CharSequence) input, "]", 0, false, 6, (Object) null);
        if (start != -1 && end != -1 && end > start) {
            String substring = input.substring(start, end + 1);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            return substring;
        }
        return null;
    }

    private final String extractJsonObject(String input) {
        int start = StringsKt.indexOf$default((CharSequence) input, "{", 0, false, 6, (Object) null);
        int end = StringsKt.lastIndexOf$default((CharSequence) input, "}", 0, false, 6, (Object) null);
        if (start != -1 && end != -1 && end > start) {
            String substring = input.substring(start, end + 1);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            return substring;
        }
        return null;
    }

    /* compiled from: RecommendationEngine.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/maxmpz/poweramp/companion/RecommendationEngine$Companion;", "", "<init>", "()V", "lastDebugInfo", "", "getLastDebugInfo", "()Ljava/lang/String;", "setLastDebugInfo", "(Ljava/lang/String;)V", "PowerampAICompanion_debug"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getLastDebugInfo() {
            return RecommendationEngine.lastDebugInfo;
        }

        public final void setLastDebugInfo(String str) {
            RecommendationEngine.lastDebugInfo = str;
        }
    }
}
