package com.maxmpz.poweramp.companion;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
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
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RecommendationEngine.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "com.maxmpz.poweramp.companion.RecommendationEngine$generateInsights$2", f = "RecommendationEngine.kt", i = {}, l = {202}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class RecommendationEngine$generateInsights$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ String $apiKey;
    final /* synthetic */ String $blacklist;
    final /* synthetic */ List<Pair<String, String>> $recentTracks;
    final /* synthetic */ List<Pair<String, String>> $topTracks;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecommendationEngine$generateInsights$2(String str, List<Pair<String, String>> list, List<Pair<String, String>> list2, String str2, Continuation<? super RecommendationEngine$generateInsights$2> continuation) {
        super(2, continuation);
        this.$apiKey = str;
        this.$topTracks = list;
        this.$recentTracks = list2;
        this.$blacklist = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RecommendationEngine$generateInsights$2(this.$apiKey, this.$topTracks, this.$recentTracks, this.$blacklist, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((RecommendationEngine$generateInsights$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object $result2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    GenerativeModel model = new GenerativeModel("gemini-2.0-flash", this.$apiKey, null, null, null, null, null, null, 252, null);
                    if (this.$topTracks.isEmpty() && this.$recentTracks.isEmpty()) {
                        return "Deine Bibliothek scheint noch ziemlich leer oder frisch zu sein. Hör erst ein paar Songs, damit ich deinen Musikgeschmack analysieren kann! ✨";
                    }
                    StringBuilder promptLines = new StringBuilder();
                    promptLines.append("Du bist ein Musik-Experte, DJ und eine Art 'Spotify Wrapped' Analysator. Analysiere den folgenden Musikgeschmack basierend auf den meistgehörten und zuletzt hinzugefügten Songs aus einer lokalen Poweramp Bibliothek.\n\n");
                    if (!this.$topTracks.isEmpty()) {
                        promptLines.append("MEISTGEHÖRTE SONGS (All-Time Favorites):\n");
                        for (Pair pair : CollectionsKt.take(this.$topTracks, 40)) {
                            promptLines.append("- " + pair.getFirst() + " von " + pair.getSecond() + "\n");
                        }
                    }
                    if (!this.$recentTracks.isEmpty()) {
                        promptLines.append("\nZULETZT GEHÖRT/HINZUGEFÜGT (Aktueller Vibe):\n");
                        for (Pair pair2 : CollectionsKt.take(this.$recentTracks, 20)) {
                            promptLines.append("- " + pair2.getFirst() + " von " + pair2.getSecond() + "\n");
                        }
                    }
                    String blacklistInstruction = !StringsKt.isBlank(this.$blacklist) ? "WICHTIG: Folgende Künstler oder Genres GÄNZLICH IGNORIEREN UND AUSSCHLIESSEN (Blacklist): " + this.$blacklist + "\n" : "";
                    promptLines.append("\nAUFGABE:\n");
                    promptLines.append(blacklistInstruction);
                    promptLines.append("1. Schreibe eine sehr kurze, knackige, lustige und emotionale Charakter-Analyse (max 4 Sätze) zu diesem Musikgeschmack.\n");
                    promptLines.append("2. Schlage dann GENAU 3 empfehlenswerte, komplett NEUE Künstler oder Songs vor, die NICHT in der obigen Liste stehen, aber perfekt zu diesem aktuellen Vibe passen (Music Discovery!).\n");
                    promptLines.append("Verwende Emojis. Mache es cool und modern. Antworte auf Deutsch.");
                    this.label = 1;
                    Object generateContent = model.generateContent(promptLines.toString(), this);
                    if (generateContent == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result2 = $result;
                    $result = generateContent;
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    $result2 = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            GenerateContentResponse response = (GenerateContentResponse) $result;
            String text = response.getText();
            return text == null ? "Konnte keine Insights generieren." : text;
        } catch (Exception e2) {
            e = e2;
            $result = $result2;
            e.printStackTrace();
            return "Fehler bei der KI-Analyse: " + e.getMessage();
        }
    }
}
