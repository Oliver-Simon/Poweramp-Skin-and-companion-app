package com.google.ai.client.generativeai;

import android.graphics.Bitmap;
import androidx.exifinterface.media.ExifInterface;
import com.google.ai.client.generativeai.common.APIController;
import com.google.ai.client.generativeai.common.CountTokensRequest;
import com.google.ai.client.generativeai.common.GenerateContentRequest;
import com.google.ai.client.generativeai.common.util.UtilKt;
import com.google.ai.client.generativeai.internal.util.ConversionsKt;
import com.google.ai.client.generativeai.type.Candidate;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.ContentKt;
import com.google.ai.client.generativeai.type.CountTokensResponse;
import com.google.ai.client.generativeai.type.FinishReason;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.GenerationConfig;
import com.google.ai.client.generativeai.type.PromptBlockedException;
import com.google.ai.client.generativeai.type.PromptFeedback;
import com.google.ai.client.generativeai.type.RequestOptions;
import com.google.ai.client.generativeai.type.ResponseStoppedException;
import com.google.ai.client.generativeai.type.SafetySetting;
import com.google.ai.client.generativeai.type.SerializationException;
import com.google.ai.client.generativeai.type.Tool;
import com.google.ai.client.generativeai.type.ToolConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: GenerativeModel.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001Bi\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012Bq\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J!\u0010$\u001a\u00020%2\u0012\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110'\"\u00020\u0011H\u0002¢\u0006\u0002\u0010(J!\u0010)\u001a\u00020*2\u0012\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110'\"\u00020\u0011H\u0002¢\u0006\u0002\u0010+J\u0019\u0010,\u001a\u00020-2\u0006\u0010&\u001a\u00020.H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010/J%\u0010,\u001a\u00020-2\u0012\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110'\"\u00020\u0011H\u0086@ø\u0001\u0000¢\u0006\u0002\u00100J\u0019\u0010,\u001a\u00020-2\u0006\u0010&\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0002\u00101J\u0019\u00102\u001a\u0002032\u0006\u0010&\u001a\u00020.H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010/J%\u00102\u001a\u0002032\u0012\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110'\"\u00020\u0011H\u0086@ø\u0001\u0000¢\u0006\u0002\u00100J\u0019\u00102\u001a\u0002032\u0006\u0010&\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0002\u00101J\u0014\u00104\u001a\b\u0012\u0004\u0012\u000203052\u0006\u0010&\u001a\u00020.J%\u00104\u001a\b\u0012\u0004\u0012\u000203052\u0012\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110'\"\u00020\u0011¢\u0006\u0002\u00106J\u0014\u00104\u001a\b\u0012\u0004\u0012\u000203052\u0006\u0010&\u001a\u00020\u0003J\u0016\u00107\u001a\u0002082\u000e\b\u0002\u00109\u001a\b\u0012\u0004\u0012\u00020\u00110\bJ\f\u0010:\u001a\u000203*\u000203H\u0002R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006;"}, d2 = {"Lcom/google/ai/client/generativeai/GenerativeModel;", "", "modelName", "", "apiKey", "generationConfig", "Lcom/google/ai/client/generativeai/type/GenerationConfig;", "safetySettings", "", "Lcom/google/ai/client/generativeai/type/SafetySetting;", "requestOptions", "Lcom/google/ai/client/generativeai/type/RequestOptions;", "tools", "Lcom/google/ai/client/generativeai/type/Tool;", "toolConfig", "Lcom/google/ai/client/generativeai/type/ToolConfig;", "systemInstruction", "Lcom/google/ai/client/generativeai/type/Content;", "(Ljava/lang/String;Ljava/lang/String;Lcom/google/ai/client/generativeai/type/GenerationConfig;Ljava/util/List;Lcom/google/ai/client/generativeai/type/RequestOptions;Ljava/util/List;Lcom/google/ai/client/generativeai/type/ToolConfig;Lcom/google/ai/client/generativeai/type/Content;)V", "controller", "Lcom/google/ai/client/generativeai/common/APIController;", "(Ljava/lang/String;Ljava/lang/String;Lcom/google/ai/client/generativeai/type/GenerationConfig;Ljava/util/List;Ljava/util/List;Lcom/google/ai/client/generativeai/type/ToolConfig;Lcom/google/ai/client/generativeai/type/Content;Lcom/google/ai/client/generativeai/type/RequestOptions;Lcom/google/ai/client/generativeai/common/APIController;)V", "getApiKey", "()Ljava/lang/String;", "getGenerationConfig", "()Lcom/google/ai/client/generativeai/type/GenerationConfig;", "getModelName", "getRequestOptions", "()Lcom/google/ai/client/generativeai/type/RequestOptions;", "getSafetySettings", "()Ljava/util/List;", "getSystemInstruction", "()Lcom/google/ai/client/generativeai/type/Content;", "getToolConfig", "()Lcom/google/ai/client/generativeai/type/ToolConfig;", "getTools", "constructCountTokensRequest", "Lcom/google/ai/client/generativeai/common/CountTokensRequest;", "prompt", "", "([Lcom/google/ai/client/generativeai/type/Content;)Lcom/google/ai/client/generativeai/common/CountTokensRequest;", "constructRequest", "Lcom/google/ai/client/generativeai/common/GenerateContentRequest;", "([Lcom/google/ai/client/generativeai/type/Content;)Lcom/google/ai/client/generativeai/common/GenerateContentRequest;", "countTokens", "Lcom/google/ai/client/generativeai/type/CountTokensResponse;", "Landroid/graphics/Bitmap;", "(Landroid/graphics/Bitmap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "([Lcom/google/ai/client/generativeai/type/Content;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateContent", "Lcom/google/ai/client/generativeai/type/GenerateContentResponse;", "generateContentStream", "Lkotlinx/coroutines/flow/Flow;", "([Lcom/google/ai/client/generativeai/type/Content;)Lkotlinx/coroutines/flow/Flow;", "startChat", "Lcom/google/ai/client/generativeai/Chat;", "history", "validate", "generativeai_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class GenerativeModel {
    private final String apiKey;
    private final APIController controller;
    private final GenerationConfig generationConfig;
    private final String modelName;
    private final RequestOptions requestOptions;
    private final List<SafetySetting> safetySettings;
    private final Content systemInstruction;
    private final ToolConfig toolConfig;
    private final List<Tool> tools;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GenerativeModel(String modelName, String apiKey) {
        this(modelName, apiKey, null, null, null, null, null, null, 252, null);
        Intrinsics.checkNotNullParameter(modelName, "modelName");
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GenerativeModel(String modelName, String apiKey, GenerationConfig generationConfig) {
        this(modelName, apiKey, generationConfig, null, null, null, null, null, 248, null);
        Intrinsics.checkNotNullParameter(modelName, "modelName");
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GenerativeModel(String modelName, String apiKey, GenerationConfig generationConfig, List<SafetySetting> list) {
        this(modelName, apiKey, generationConfig, list, null, null, null, null, 240, null);
        Intrinsics.checkNotNullParameter(modelName, "modelName");
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GenerativeModel(String modelName, String apiKey, GenerationConfig generationConfig, List<SafetySetting> list, RequestOptions requestOptions) {
        this(modelName, apiKey, generationConfig, list, requestOptions, null, null, null, 224, null);
        Intrinsics.checkNotNullParameter(modelName, "modelName");
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        Intrinsics.checkNotNullParameter(requestOptions, "requestOptions");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GenerativeModel(String modelName, String apiKey, GenerationConfig generationConfig, List<SafetySetting> list, RequestOptions requestOptions, List<Tool> list2) {
        this(modelName, apiKey, generationConfig, list, requestOptions, list2, null, null, 192, null);
        Intrinsics.checkNotNullParameter(modelName, "modelName");
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        Intrinsics.checkNotNullParameter(requestOptions, "requestOptions");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GenerativeModel(String modelName, String apiKey, GenerationConfig generationConfig, List<SafetySetting> list, RequestOptions requestOptions, List<Tool> list2, ToolConfig toolConfig) {
        this(modelName, apiKey, generationConfig, list, requestOptions, list2, toolConfig, null, 128, null);
        Intrinsics.checkNotNullParameter(modelName, "modelName");
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        Intrinsics.checkNotNullParameter(requestOptions, "requestOptions");
    }

    public GenerativeModel(String modelName, String apiKey, GenerationConfig generationConfig, List<SafetySetting> list, List<Tool> list2, ToolConfig toolConfig, Content systemInstruction, RequestOptions requestOptions, APIController controller) {
        Intrinsics.checkNotNullParameter(modelName, "modelName");
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        Intrinsics.checkNotNullParameter(requestOptions, "requestOptions");
        Intrinsics.checkNotNullParameter(controller, "controller");
        this.modelName = modelName;
        this.apiKey = apiKey;
        this.generationConfig = generationConfig;
        this.safetySettings = list;
        this.tools = list2;
        this.toolConfig = toolConfig;
        this.systemInstruction = systemInstruction;
        this.requestOptions = requestOptions;
        this.controller = controller;
    }

    public /* synthetic */ GenerativeModel(String str, String str2, GenerationConfig generationConfig, List list, List list2, ToolConfig toolConfig, Content content, RequestOptions requestOptions, APIController aPIController, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : generationConfig, (i & 8) != 0 ? null : list, (i & 16) != 0 ? null : list2, (i & 32) != 0 ? null : toolConfig, (i & 64) != 0 ? null : content, (i & 128) != 0 ? new RequestOptions((Long) null, (String) null, 3, (DefaultConstructorMarker) null) : requestOptions, aPIController);
    }

    public final String getModelName() {
        return this.modelName;
    }

    public final String getApiKey() {
        return this.apiKey;
    }

    public final GenerationConfig getGenerationConfig() {
        return this.generationConfig;
    }

    public final List<SafetySetting> getSafetySettings() {
        return this.safetySettings;
    }

    public final List<Tool> getTools() {
        return this.tools;
    }

    public final ToolConfig getToolConfig() {
        return this.toolConfig;
    }

    public final Content getSystemInstruction() {
        return this.systemInstruction;
    }

    public final RequestOptions getRequestOptions() {
        return this.requestOptions;
    }

    public /* synthetic */ GenerativeModel(String str, String str2, GenerationConfig generationConfig, List list, RequestOptions requestOptions, List list2, ToolConfig toolConfig, Content content, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : generationConfig, (i & 8) != 0 ? null : list, (i & 16) != 0 ? new RequestOptions((Long) null, (String) null, 3, (DefaultConstructorMarker) null) : requestOptions, (i & 32) != 0 ? null : list2, (i & 64) != 0 ? null : toolConfig, (i & 128) != 0 ? null : content);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public GenerativeModel(String modelName, String apiKey, GenerationConfig generationConfig, List<SafetySetting> list, RequestOptions requestOptions, List<Tool> list2, ToolConfig toolConfig, Content systemInstruction) {
        this(UtilKt.fullModelName(modelName), apiKey, generationConfig, list, list2, toolConfig, systemInstruction != null ? new Content("system", systemInstruction.getParts()) : null, requestOptions, new APIController(apiKey, modelName, ConversionsKt.toInternal(requestOptions), "genai-android/0.9.0", null, 16, null));
        Intrinsics.checkNotNullParameter(modelName, "modelName");
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        Intrinsics.checkNotNullParameter(requestOptions, "requestOptions");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object generateContent(com.google.ai.client.generativeai.type.Content[] r7, kotlin.coroutines.Continuation<? super com.google.ai.client.generativeai.type.GenerateContentResponse> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.google.ai.client.generativeai.GenerativeModel$generateContent$1
            if (r0 == 0) goto L14
            r0 = r8
            com.google.ai.client.generativeai.GenerativeModel$generateContent$1 r0 = (com.google.ai.client.generativeai.GenerativeModel$generateContent$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            com.google.ai.client.generativeai.GenerativeModel$generateContent$1 r0 = new com.google.ai.client.generativeai.GenerativeModel$generateContent$1
            r0.<init>(r6, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L35;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            java.lang.Object r7 = r0.L$0
            com.google.ai.client.generativeai.GenerativeModel r7 = (com.google.ai.client.generativeai.GenerativeModel) r7
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L5f
            r3 = r8
            goto L54
        L35:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
            com.google.ai.client.generativeai.common.APIController r3 = r2.controller     // Catch: java.lang.Throwable -> L5f
            int r4 = r7.length     // Catch: java.lang.Throwable -> L5f
            java.lang.Object[] r4 = java.util.Arrays.copyOf(r7, r4)     // Catch: java.lang.Throwable -> L5f
            com.google.ai.client.generativeai.type.Content[] r4 = (com.google.ai.client.generativeai.type.Content[]) r4     // Catch: java.lang.Throwable -> L5f
            com.google.ai.client.generativeai.common.GenerateContentRequest r4 = r2.constructRequest(r4)     // Catch: java.lang.Throwable -> L5f
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L5f
            r5 = 1
            r0.label = r5     // Catch: java.lang.Throwable -> L5f
            java.lang.Object r3 = r3.generateContent(r4, r0)     // Catch: java.lang.Throwable -> L5f
            if (r3 != r1) goto L53
            return r1
        L53:
            r7 = r2
        L54:
            com.google.ai.client.generativeai.common.GenerateContentResponse r3 = (com.google.ai.client.generativeai.common.GenerateContentResponse) r3     // Catch: java.lang.Throwable -> L5f
            com.google.ai.client.generativeai.type.GenerateContentResponse r1 = com.google.ai.client.generativeai.internal.util.ConversionsKt.toPublic(r3)     // Catch: java.lang.Throwable -> L5f
            com.google.ai.client.generativeai.type.GenerateContentResponse r7 = r7.validate(r1)     // Catch: java.lang.Throwable -> L5f
            return r7
        L5f:
            r7 = move-exception
            com.google.ai.client.generativeai.type.GoogleGenerativeAIException$Companion r1 = com.google.ai.client.generativeai.type.GoogleGenerativeAIException.INSTANCE
            com.google.ai.client.generativeai.type.GoogleGenerativeAIException r1 = r1.from(r7)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.GenerativeModel.generateContent(com.google.ai.client.generativeai.type.Content[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Flow<GenerateContentResponse> generateContentStream(Content... prompt) {
        Intrinsics.checkNotNullParameter(prompt, "prompt");
        final Flow $this$map$iv = FlowKt.m2039catch(this.controller.generateContentStream(constructRequest((Content[]) Arrays.copyOf(prompt, prompt.length))), new GenerativeModel$generateContentStream$1(null));
        return new Flow<GenerateContentResponse>() { // from class: com.google.ai.client.generativeai.GenerativeModel$generateContentStream$$inlined$map$1

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {1, 8, 0}, xi = 48)
            /* renamed from: com.google.ai.client.generativeai.GenerativeModel$generateContentStream$$inlined$map$1$2, reason: invalid class name */
            /* loaded from: classes.dex */
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;
                final /* synthetic */ GenerativeModel this$0;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                @DebugMetadata(c = "com.google.ai.client.generativeai.GenerativeModel$generateContentStream$$inlined$map$1$2", f = "GenerativeModel.kt", i = {}, l = {223}, m = "emit", n = {}, s = {})
                /* renamed from: com.google.ai.client.generativeai.GenerativeModel$generateContentStream$$inlined$map$1$2$1, reason: invalid class name */
                /* loaded from: classes.dex */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, GenerativeModel generativeModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = generativeModel;
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
                /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
                    /*
                        r8 = this;
                        boolean r0 = r10 instanceof com.google.ai.client.generativeai.GenerativeModel$generateContentStream$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L14
                        r0 = r10
                        com.google.ai.client.generativeai.GenerativeModel$generateContentStream$$inlined$map$1$2$1 r0 = (com.google.ai.client.generativeai.GenerativeModel$generateContentStream$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L14
                        int r10 = r0.label
                        int r10 = r10 - r2
                        r0.label = r10
                        goto L19
                    L14:
                        com.google.ai.client.generativeai.GenerativeModel$generateContentStream$$inlined$map$1$2$1 r0 = new com.google.ai.client.generativeai.GenerativeModel$generateContentStream$$inlined$map$1$2$1
                        r0.<init>(r10)
                    L19:
                        java.lang.Object r10 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        switch(r2) {
                            case 0: goto L31;
                            case 1: goto L2c;
                            default: goto L24;
                        }
                    L24:
                        java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                        java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                        r9.<init>(r10)
                        throw r9
                    L2c:
                        r9 = 0
                        kotlin.ResultKt.throwOnFailure(r10)
                        goto L53
                    L31:
                        kotlin.ResultKt.throwOnFailure(r10)
                        r2 = r8
                        kotlinx.coroutines.flow.FlowCollector r3 = r2.$this_unsafeFlow
                        r4 = 0
                        r5 = r0
                        kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                        com.google.ai.client.generativeai.common.GenerateContentResponse r9 = (com.google.ai.client.generativeai.common.GenerateContentResponse) r9
                        r5 = 0
                        com.google.ai.client.generativeai.GenerativeModel r6 = r2.this$0
                        com.google.ai.client.generativeai.type.GenerateContentResponse r7 = com.google.ai.client.generativeai.internal.util.ConversionsKt.toPublic(r9)
                        com.google.ai.client.generativeai.type.GenerateContentResponse r9 = com.google.ai.client.generativeai.GenerativeModel.access$validate(r6, r7)
                        r2 = 1
                        r0.label = r2
                        java.lang.Object r9 = r3.emit(r9, r0)
                        if (r9 != r1) goto L52
                        return r1
                    L52:
                        r9 = r4
                    L53:
                        kotlin.Unit r9 = kotlin.Unit.INSTANCE
                        return r9
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.GenerativeModel$generateContentStream$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super GenerateContentResponse> flowCollector, Continuation $completion) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, this), $completion);
                return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        };
    }

    public final Object generateContent(final String prompt, Continuation<? super GenerateContentResponse> continuation) {
        return generateContent(new Content[]{ContentKt.content$default(null, new Function1<Content.Builder, Unit>() { // from class: com.google.ai.client.generativeai.GenerativeModel$generateContent$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Content.Builder builder) {
                invoke2(builder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Content.Builder content) {
                Intrinsics.checkNotNullParameter(content, "$this$content");
                content.addText(prompt);
            }
        }, 1, null)}, continuation);
    }

    public final Flow<GenerateContentResponse> generateContentStream(final String prompt) {
        Intrinsics.checkNotNullParameter(prompt, "prompt");
        return generateContentStream(ContentKt.content$default(null, new Function1<Content.Builder, Unit>() { // from class: com.google.ai.client.generativeai.GenerativeModel$generateContentStream$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Content.Builder builder) {
                invoke2(builder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Content.Builder content) {
                Intrinsics.checkNotNullParameter(content, "$this$content");
                content.addText(prompt);
            }
        }, 1, null));
    }

    public final Object generateContent(final Bitmap prompt, Continuation<? super GenerateContentResponse> continuation) {
        return generateContent(new Content[]{ContentKt.content$default(null, new Function1<Content.Builder, Unit>() { // from class: com.google.ai.client.generativeai.GenerativeModel$generateContent$5
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Content.Builder builder) {
                invoke2(builder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Content.Builder content) {
                Intrinsics.checkNotNullParameter(content, "$this$content");
                content.addImage(prompt);
            }
        }, 1, null)}, continuation);
    }

    public final Flow<GenerateContentResponse> generateContentStream(final Bitmap prompt) {
        Intrinsics.checkNotNullParameter(prompt, "prompt");
        return generateContentStream(ContentKt.content$default(null, new Function1<Content.Builder, Unit>() { // from class: com.google.ai.client.generativeai.GenerativeModel$generateContentStream$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Content.Builder builder) {
                invoke2(builder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Content.Builder content) {
                Intrinsics.checkNotNullParameter(content, "$this$content");
                content.addImage(prompt);
            }
        }, 1, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Chat startChat$default(GenerativeModel generativeModel, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = CollectionsKt.emptyList();
        }
        return generativeModel.startChat(list);
    }

    public final Chat startChat(List<Content> history) {
        Intrinsics.checkNotNullParameter(history, "history");
        return new Chat(this, CollectionsKt.toMutableList((Collection) history));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object countTokens(com.google.ai.client.generativeai.type.Content[] r7, kotlin.coroutines.Continuation<? super com.google.ai.client.generativeai.type.CountTokensResponse> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.google.ai.client.generativeai.GenerativeModel$countTokens$1
            if (r0 == 0) goto L14
            r0 = r8
            com.google.ai.client.generativeai.GenerativeModel$countTokens$1 r0 = (com.google.ai.client.generativeai.GenerativeModel$countTokens$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            com.google.ai.client.generativeai.GenerativeModel$countTokens$1 r0 = new com.google.ai.client.generativeai.GenerativeModel$countTokens$1
            r0.<init>(r6, r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L31;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            kotlin.ResultKt.throwOnFailure(r8)
            r7 = r8
            goto L4c
        L31:
            kotlin.ResultKt.throwOnFailure(r8)
            r2 = r6
            com.google.ai.client.generativeai.common.APIController r3 = r2.controller
            int r4 = r7.length
            java.lang.Object[] r4 = java.util.Arrays.copyOf(r7, r4)
            com.google.ai.client.generativeai.type.Content[] r4 = (com.google.ai.client.generativeai.type.Content[]) r4
            com.google.ai.client.generativeai.common.CountTokensRequest r4 = r2.constructCountTokensRequest(r4)
            r5 = 1
            r0.label = r5
            java.lang.Object r7 = r3.countTokens(r4, r0)
            if (r7 != r1) goto L4c
            return r1
        L4c:
            com.google.ai.client.generativeai.common.CountTokensResponse r7 = (com.google.ai.client.generativeai.common.CountTokensResponse) r7
            com.google.ai.client.generativeai.type.CountTokensResponse r7 = com.google.ai.client.generativeai.internal.util.ConversionsKt.toPublic(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ai.client.generativeai.GenerativeModel.countTokens(com.google.ai.client.generativeai.type.Content[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object countTokens(final String prompt, Continuation<? super CountTokensResponse> continuation) {
        return countTokens(new Content[]{ContentKt.content$default(null, new Function1<Content.Builder, Unit>() { // from class: com.google.ai.client.generativeai.GenerativeModel$countTokens$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Content.Builder builder) {
                invoke2(builder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Content.Builder content) {
                Intrinsics.checkNotNullParameter(content, "$this$content");
                content.addText(prompt);
            }
        }, 1, null)}, continuation);
    }

    public final Object countTokens(final Bitmap prompt, Continuation<? super CountTokensResponse> continuation) {
        return countTokens(new Content[]{ContentKt.content$default(null, new Function1<Content.Builder, Unit>() { // from class: com.google.ai.client.generativeai.GenerativeModel$countTokens$5
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Content.Builder builder) {
                invoke2(builder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Content.Builder content) {
                Intrinsics.checkNotNullParameter(content, "$this$content");
                content.addImage(prompt);
            }
        }, 1, null)}, continuation);
    }

    private final GenerateContentRequest constructRequest(Content... prompt) {
        ArrayList arrayList;
        ArrayList arrayList2;
        String str = this.modelName;
        Collection destination$iv$iv = new ArrayList(prompt.length);
        for (Content content : prompt) {
            destination$iv$iv.add(ConversionsKt.toInternal(content));
        }
        ArrayList arrayList3 = (List) destination$iv$iv;
        Iterable iterable = this.safetySettings;
        if (iterable == null) {
            arrayList = null;
        } else {
            Iterable $this$map$iv = iterable;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                SafetySetting it = (SafetySetting) item$iv$iv;
                destination$iv$iv2.add(ConversionsKt.toInternal(it));
            }
            arrayList = (List) destination$iv$iv2;
        }
        GenerationConfig generationConfig = this.generationConfig;
        com.google.ai.client.generativeai.common.client.GenerationConfig internal = generationConfig != null ? ConversionsKt.toInternal(generationConfig) : null;
        Iterable iterable2 = this.tools;
        if (iterable2 == null) {
            arrayList2 = null;
        } else {
            Iterable $this$map$iv2 = iterable2;
            Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
            for (Object item$iv$iv2 : $this$map$iv2) {
                Tool it2 = (Tool) item$iv$iv2;
                destination$iv$iv3.add(ConversionsKt.toInternal(it2));
            }
            arrayList2 = (List) destination$iv$iv3;
        }
        ToolConfig toolConfig = this.toolConfig;
        com.google.ai.client.generativeai.common.client.ToolConfig internal2 = toolConfig != null ? ConversionsKt.toInternal(toolConfig) : null;
        Content content2 = this.systemInstruction;
        return new GenerateContentRequest(str, arrayList3, arrayList, internal, arrayList2, internal2, content2 != null ? ConversionsKt.toInternal(content2) : null);
    }

    private final CountTokensRequest constructCountTokensRequest(Content... prompt) {
        return CountTokensRequest.INSTANCE.forGenAI(constructRequest((Content[]) Arrays.copyOf(prompt, prompt.length)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final GenerateContentResponse validate(GenerateContentResponse generateContentResponse) {
        Object obj;
        int i = 2;
        Throwable th = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        if (generateContentResponse.getCandidates().isEmpty() && generateContentResponse.getPromptFeedback() == null) {
            throw new SerializationException("Error deserializing response, found no valid fields", null, 2, null);
        }
        PromptFeedback promptFeedback = generateContentResponse.getPromptFeedback();
        if (promptFeedback != null && promptFeedback.getBlockReason() != null) {
            throw new PromptBlockedException(generateContentResponse, th, i, objArr3 == true ? 1 : 0);
        }
        List<Candidate> candidates = generateContentResponse.getCandidates();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = candidates.iterator();
        while (it.hasNext()) {
            FinishReason finishReason = ((Candidate) it.next()).getFinishReason();
            if (finishReason != null) {
                arrayList.add(finishReason);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            if (((FinishReason) obj) != FinishReason.STOP) {
                break;
            }
        }
        if (((FinishReason) obj) == null) {
            return generateContentResponse;
        }
        throw new ResponseStoppedException(generateContentResponse, objArr2 == true ? 1 : 0, i, objArr == true ? 1 : 0);
    }
}
