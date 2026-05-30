package com.google.ai.client.generativeai.type;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: GenerateContentResponse.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u001c\u0010%\u001a\u0004\u0018\u0001H&\"\n\b\u0000\u0010&\u0018\u0001*\u00020'H\u0082\b¢\u0006\u0002\u0010(J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u001fH\u0002R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR#\u0010\f\u001a\u0004\u0018\u00010\r8FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R!\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u00038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0015\u0010\u000bR\u001d\u0010\u0017\u001a\u0004\u0018\u00010\u00188FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0013\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\u001e\u001a\u0004\u0018\u00010\u001f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u0013\u001a\u0004\b \u0010!R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u0006,"}, d2 = {"Lcom/google/ai/client/generativeai/type/GenerateContentResponse;", "", "candidates", "", "Lcom/google/ai/client/generativeai/type/Candidate;", "promptFeedback", "Lcom/google/ai/client/generativeai/type/PromptFeedback;", "usageMetadata", "Lcom/google/ai/client/generativeai/type/UsageMetadata;", "(Ljava/util/List;Lcom/google/ai/client/generativeai/type/PromptFeedback;Lcom/google/ai/client/generativeai/type/UsageMetadata;)V", "getCandidates", "()Ljava/util/List;", "functionCall", "Lcom/google/ai/client/generativeai/type/FunctionCallPart;", "getFunctionCall$annotations", "()V", "getFunctionCall", "()Lcom/google/ai/client/generativeai/type/FunctionCallPart;", "functionCall$delegate", "Lkotlin/Lazy;", "functionCalls", "getFunctionCalls", "functionCalls$delegate", "functionResponse", "Lcom/google/ai/client/generativeai/type/FunctionResponsePart;", "getFunctionResponse", "()Lcom/google/ai/client/generativeai/type/FunctionResponsePart;", "functionResponse$delegate", "getPromptFeedback", "()Lcom/google/ai/client/generativeai/type/PromptFeedback;", "text", "", "getText", "()Ljava/lang/String;", "text$delegate", "getUsageMetadata", "()Lcom/google/ai/client/generativeai/type/UsageMetadata;", "firstPartAs", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/google/ai/client/generativeai/type/Part;", "()Lcom/google/ai/client/generativeai/type/Part;", "warn", "", "message", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class GenerateContentResponse {
    private final List<Candidate> candidates;

    /* renamed from: functionCall$delegate, reason: from kotlin metadata */
    private final Lazy functionCall;

    /* renamed from: functionCalls$delegate, reason: from kotlin metadata */
    private final Lazy functionCalls;

    /* renamed from: functionResponse$delegate, reason: from kotlin metadata */
    private final Lazy functionResponse;
    private final PromptFeedback promptFeedback;

    /* renamed from: text$delegate, reason: from kotlin metadata */
    private final Lazy text;
    private final UsageMetadata usageMetadata;

    @Deprecated(message = "Use functionCalls instead", replaceWith = @ReplaceWith(expression = "functionCalls", imports = {}))
    public static /* synthetic */ void getFunctionCall$annotations() {
    }

    public GenerateContentResponse(List<Candidate> candidates, PromptFeedback promptFeedback, UsageMetadata usageMetadata) {
        Intrinsics.checkNotNullParameter(candidates, "candidates");
        this.candidates = candidates;
        this.promptFeedback = promptFeedback;
        this.usageMetadata = usageMetadata;
        this.text = LazyKt.lazy(new Function0<String>() { // from class: com.google.ai.client.generativeai.type.GenerateContentResponse$text$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Iterable $this$filter$iv = ((Candidate) CollectionsKt.first((List) GenerateContentResponse.this.getCandidates())).getContent().getParts();
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    Part it = (Part) element$iv$iv;
                    if ((it instanceof TextPart) || (it instanceof ExecutableCodePart) || (it instanceof CodeExecutionResultPart)) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                return CollectionsKt.joinToString$default((List) destination$iv$iv, " ", null, null, 0, null, new Function1<Part, CharSequence>() { // from class: com.google.ai.client.generativeai.type.GenerateContentResponse$text$2.2
                    @Override // kotlin.jvm.functions.Function1
                    public final CharSequence invoke(Part it2) {
                        Intrinsics.checkNotNullParameter(it2, "it");
                        if (it2 instanceof TextPart) {
                            return ((TextPart) it2).getText();
                        }
                        if (it2 instanceof ExecutableCodePart) {
                            String lowerCase = ((ExecutableCodePart) it2).getLanguage().toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                            return "\n```" + lowerCase + "\n" + ((ExecutableCodePart) it2).getCode() + "\n```";
                        }
                        if (!(it2 instanceof CodeExecutionResultPart)) {
                            throw new RuntimeException("unreachable");
                        }
                        return "\n```\n" + ((CodeExecutionResultPart) it2).getOutput() + "\n```";
                    }
                }, 30, null);
            }
        });
        this.functionCall = LazyKt.lazy(new Function0<FunctionCallPart>() { // from class: com.google.ai.client.generativeai.type.GenerateContentResponse$functionCall$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FunctionCallPart invoke() {
                GenerateContentResponse this_$iv = GenerateContentResponse.this;
                FunctionCallPart functionCallPart = null;
                if (this_$iv.getCandidates().isEmpty()) {
                    this_$iv.warn("No candidates were found, but was asked to get a candidate.");
                } else {
                    Iterable $this$partition$iv$iv = ((Candidate) CollectionsKt.first((List) this_$iv.getCandidates())).getContent().getParts();
                    ArrayList first$iv$iv = new ArrayList();
                    ArrayList second$iv$iv = new ArrayList();
                    for (Object element$iv$iv : $this$partition$iv$iv) {
                        Part it$iv = (Part) element$iv$iv;
                        if (it$iv instanceof FunctionCallPart) {
                            first$iv$iv.add(element$iv$iv);
                        } else {
                            second$iv$iv.add(element$iv$iv);
                        }
                    }
                    Pair pair = new Pair(first$iv$iv, second$iv$iv);
                    List parts$iv = (List) pair.component1();
                    List otherParts$iv = (List) pair.component2();
                    String type$iv = Reflection.getOrCreateKotlinClass(FunctionCallPart.class).getSimpleName();
                    if (type$iv == null) {
                        type$iv = "of the part type you asked for";
                    }
                    if (parts$iv.isEmpty()) {
                        if (!otherParts$iv.isEmpty()) {
                            this_$iv.warn("We didn't find any " + type$iv + ", but we did find other part types. Did you ask for the right type?");
                        }
                    } else {
                        if (parts$iv.size() > 1) {
                            this_$iv.warn("Multiple " + type$iv + " were found, returning the first one.");
                        } else if (!otherParts$iv.isEmpty()) {
                            this_$iv.warn("Returning the only " + type$iv + " found, but other part types were present as well.");
                        }
                        Object first = CollectionsKt.first((List<? extends Object>) parts$iv);
                        if (first == null) {
                            throw new NullPointerException("null cannot be cast to non-null type com.google.ai.client.generativeai.type.FunctionCallPart");
                        }
                        functionCallPart = (FunctionCallPart) first;
                    }
                }
                return functionCallPart;
            }
        });
        this.functionCalls = LazyKt.lazy(new Function0<List<? extends FunctionCallPart>>() { // from class: com.google.ai.client.generativeai.type.GenerateContentResponse$functionCalls$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final List<? extends FunctionCallPart> invoke() {
                Iterable $this$filterIsInstance$iv = ((Candidate) CollectionsKt.first((List) GenerateContentResponse.this.getCandidates())).getContent().getParts();
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filterIsInstance$iv) {
                    if (element$iv$iv instanceof FunctionCallPart) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                return (List) destination$iv$iv;
            }
        });
        this.functionResponse = LazyKt.lazy(new Function0<FunctionResponsePart>() { // from class: com.google.ai.client.generativeai.type.GenerateContentResponse$functionResponse$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FunctionResponsePart invoke() {
                GenerateContentResponse this_$iv = GenerateContentResponse.this;
                FunctionResponsePart functionResponsePart = null;
                if (this_$iv.getCandidates().isEmpty()) {
                    this_$iv.warn("No candidates were found, but was asked to get a candidate.");
                } else {
                    Iterable $this$partition$iv$iv = ((Candidate) CollectionsKt.first((List) this_$iv.getCandidates())).getContent().getParts();
                    ArrayList first$iv$iv = new ArrayList();
                    ArrayList second$iv$iv = new ArrayList();
                    for (Object element$iv$iv : $this$partition$iv$iv) {
                        Part it$iv = (Part) element$iv$iv;
                        if (it$iv instanceof FunctionResponsePart) {
                            first$iv$iv.add(element$iv$iv);
                        } else {
                            second$iv$iv.add(element$iv$iv);
                        }
                    }
                    Pair pair = new Pair(first$iv$iv, second$iv$iv);
                    List parts$iv = (List) pair.component1();
                    List otherParts$iv = (List) pair.component2();
                    String type$iv = Reflection.getOrCreateKotlinClass(FunctionResponsePart.class).getSimpleName();
                    if (type$iv == null) {
                        type$iv = "of the part type you asked for";
                    }
                    if (parts$iv.isEmpty()) {
                        if (!otherParts$iv.isEmpty()) {
                            this_$iv.warn("We didn't find any " + type$iv + ", but we did find other part types. Did you ask for the right type?");
                        }
                    } else {
                        if (parts$iv.size() > 1) {
                            this_$iv.warn("Multiple " + type$iv + " were found, returning the first one.");
                        } else if (!otherParts$iv.isEmpty()) {
                            this_$iv.warn("Returning the only " + type$iv + " found, but other part types were present as well.");
                        }
                        Object first = CollectionsKt.first((List<? extends Object>) parts$iv);
                        if (first == null) {
                            throw new NullPointerException("null cannot be cast to non-null type com.google.ai.client.generativeai.type.FunctionResponsePart");
                        }
                        functionResponsePart = (FunctionResponsePart) first;
                    }
                }
                return functionResponsePart;
            }
        });
    }

    public final List<Candidate> getCandidates() {
        return this.candidates;
    }

    public final PromptFeedback getPromptFeedback() {
        return this.promptFeedback;
    }

    public final UsageMetadata getUsageMetadata() {
        return this.usageMetadata;
    }

    public final String getText() {
        return (String) this.text.getValue();
    }

    public final FunctionCallPart getFunctionCall() {
        return (FunctionCallPart) this.functionCall.getValue();
    }

    public final List<FunctionCallPart> getFunctionCalls() {
        return (List) this.functionCalls.getValue();
    }

    public final FunctionResponsePart getFunctionResponse() {
        return (FunctionResponsePart) this.functionResponse.getValue();
    }

    private final /* synthetic */ <T extends Part> T firstPartAs() {
        if (getCandidates().isEmpty()) {
            warn("No candidates were found, but was asked to get a candidate.");
            return null;
        }
        List<Part> $this$partition$iv = ((Candidate) CollectionsKt.first((List) getCandidates())).getContent().getParts();
        ArrayList first$iv = new ArrayList();
        ArrayList second$iv = new ArrayList();
        for (Object element$iv : $this$partition$iv) {
            Part it = (Part) element$iv;
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (it instanceof Part) {
                first$iv.add(element$iv);
            } else {
                second$iv.add(element$iv);
            }
        }
        Pair pair = new Pair(first$iv, second$iv);
        List parts = (List) pair.component1();
        List otherParts = (List) pair.component2();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        String type = Reflection.getOrCreateKotlinClass(Part.class).getSimpleName();
        if (type == null) {
            type = "of the part type you asked for";
        }
        if (parts.isEmpty()) {
            if (!otherParts.isEmpty()) {
                warn("We didn't find any " + type + ", but we did find other part types. Did you ask for the right type?");
            }
            return null;
        }
        if (parts.size() > 1) {
            warn("Multiple " + type + " were found, returning the first one.");
        } else if (!otherParts.isEmpty()) {
            warn("Returning the only " + type + " found, but other part types were present as well.");
        }
        Object first = CollectionsKt.first((List<? extends Object>) parts);
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) first;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void warn(String message) {
        Log.w("GenerateContentResponse", message);
    }
}
