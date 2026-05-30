package com.google.ai.client.generativeai.internal.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import androidx.exifinterface.media.ExifInterface;
import com.google.ai.client.generativeai.common.RequestOptions;
import com.google.ai.client.generativeai.common.client.FunctionCallingConfig;
import com.google.ai.client.generativeai.common.client.GenerationConfig;
import com.google.ai.client.generativeai.common.client.Tool;
import com.google.ai.client.generativeai.common.client.ToolConfig;
import com.google.ai.client.generativeai.common.server.BlockReason;
import com.google.ai.client.generativeai.common.server.CitationMetadata;
import com.google.ai.client.generativeai.common.server.CitationSources;
import com.google.ai.client.generativeai.common.server.FinishReason;
import com.google.ai.client.generativeai.common.server.HarmProbability;
import com.google.ai.client.generativeai.common.server.SafetyRating;
import com.google.ai.client.generativeai.common.shared.Blob;
import com.google.ai.client.generativeai.common.shared.BlobPart;
import com.google.ai.client.generativeai.common.shared.CodeExecutionResult;
import com.google.ai.client.generativeai.common.shared.Content;
import com.google.ai.client.generativeai.common.shared.ExecutableCode;
import com.google.ai.client.generativeai.common.shared.FileData;
import com.google.ai.client.generativeai.common.shared.FunctionCall;
import com.google.ai.client.generativeai.common.shared.FunctionResponse;
import com.google.ai.client.generativeai.common.shared.HarmBlockMethod;
import com.google.ai.client.generativeai.common.shared.HarmBlockThreshold;
import com.google.ai.client.generativeai.common.shared.Outcome;
import com.google.ai.client.generativeai.common.shared.SafetySetting;
import com.google.ai.client.generativeai.type.BlockThreshold;
import com.google.ai.client.generativeai.type.Candidate;
import com.google.ai.client.generativeai.type.CodeExecutionResultPart;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.ContentKt;
import com.google.ai.client.generativeai.type.CountTokensResponse;
import com.google.ai.client.generativeai.type.ExecutableCodePart;
import com.google.ai.client.generativeai.type.ExecutionOutcome;
import com.google.ai.client.generativeai.type.FileDataPart;
import com.google.ai.client.generativeai.type.FunctionCallPart;
import com.google.ai.client.generativeai.type.FunctionCallingConfig;
import com.google.ai.client.generativeai.type.FunctionDeclaration;
import com.google.ai.client.generativeai.type.FunctionResponsePart;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.HarmCategory;
import com.google.ai.client.generativeai.type.ImagePart;
import com.google.ai.client.generativeai.type.Part;
import com.google.ai.client.generativeai.type.PromptFeedback;
import com.google.ai.client.generativeai.type.Schema;
import com.google.ai.client.generativeai.type.SerializationException;
import com.google.ai.client.generativeai.type.TextPart;
import com.google.ai.client.generativeai.type.UsageMetadata;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonObject;
import org.json.JSONObject;

/* compiled from: conversions.kt */
@Metadata(d1 = {"\u0000Ú\u0001\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0003H\u0002\u001a\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0000\u001a\f\u0010\t\u001a\u00020\f*\u00020\rH\u0000\u001a\f\u0010\t\u001a\u00020\u000e*\u00020\u000fH\u0000\u001a\f\u0010\t\u001a\u00020\u0010*\u00020\u0011H\u0000\u001a\f\u0010\t\u001a\u00020\u0012*\u00020\u0013H\u0000\u001a\f\u0010\t\u001a\u00020\u0014*\u00020\u0015H\u0000\u001a\f\u0010\t\u001a\u00020\u0016*\u00020\u0017H\u0000\u001a\f\u0010\t\u001a\u00020\u0018*\u00020\u0019H\u0000\u001a\f\u0010\t\u001a\u00020\u001a*\u00020\u001bH\u0000\u001a\u0018\u0010\t\u001a\u00020\u001c\"\u0004\b\u0000\u0010\u001d*\b\u0012\u0004\u0012\u0002H\u001d0\u001eH\u0000\u001a\f\u0010\t\u001a\u00020\u001f*\u00020 H\u0000\u001a\f\u0010\t\u001a\u00020!*\u00020\"H\u0000\u001a\f\u0010\t\u001a\u00020#*\u00020$H\u0000\u001a\f\u0010%\u001a\u00020&*\u00020'H\u0000\u001a\f\u0010%\u001a\u00020(*\u00020)H\u0000\u001a\f\u0010%\u001a\u00020**\u00020+H\u0000\u001a\f\u0010%\u001a\u00020,*\u00020-H\u0000\u001a\f\u0010%\u001a\u00020.*\u00020/H\u0000\u001a\f\u0010%\u001a\u000200*\u000201H\u0000\u001a\u0010\u0010%\u001a\u0004\u0018\u000102*\u0004\u0018\u000103H\u0000\u001a\f\u0010%\u001a\u000204*\u000205H\u0000\u001a\f\u0010%\u001a\u000206*\u000207H\u0000\u001a\f\u0010%\u001a\u000208*\u000209H\u0000\u001a\f\u0010%\u001a\u00020\r*\u00020\fH\u0000\u001a\f\u0010%\u001a\u00020\u0015*\u00020\u0014H\u0000\u001a\f\u0010%\u001a\u00020\u000f*\u00020\u000eH\u0000\u001a\f\u0010%\u001a\u00020\u0017*\u00020\u0016H\u0000\u001a\f\u0010%\u001a\u00020$*\u00020#H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"BASE_64_FLAGS", "", "decodeBitmapFromImage", "Landroid/graphics/Bitmap;", "kotlin.jvm.PlatformType", "input", "", "encodeBitmapToBase64Png", "", "toInternal", "Lcom/google/ai/client/generativeai/common/shared/HarmBlockThreshold;", "Lcom/google/ai/client/generativeai/type/BlockThreshold;", "Lcom/google/ai/client/generativeai/common/shared/Content;", "Lcom/google/ai/client/generativeai/type/Content;", "Lcom/google/ai/client/generativeai/common/shared/Outcome;", "Lcom/google/ai/client/generativeai/type/ExecutionOutcome;", "Lcom/google/ai/client/generativeai/common/client/FunctionDeclaration;", "Lcom/google/ai/client/generativeai/type/FunctionDeclaration;", "Lcom/google/ai/client/generativeai/common/client/GenerationConfig;", "Lcom/google/ai/client/generativeai/type/GenerationConfig;", "Lcom/google/ai/client/generativeai/common/shared/HarmCategory;", "Lcom/google/ai/client/generativeai/type/HarmCategory;", "Lcom/google/ai/client/generativeai/common/shared/Part;", "Lcom/google/ai/client/generativeai/type/Part;", "Lcom/google/ai/client/generativeai/common/RequestOptions;", "Lcom/google/ai/client/generativeai/type/RequestOptions;", "Lcom/google/ai/client/generativeai/common/shared/SafetySetting;", "Lcom/google/ai/client/generativeai/type/SafetySetting;", "Lcom/google/ai/client/generativeai/common/client/Schema;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/google/ai/client/generativeai/type/Schema;", "Lcom/google/ai/client/generativeai/common/client/Tool;", "Lcom/google/ai/client/generativeai/type/Tool;", "Lcom/google/ai/client/generativeai/common/client/ToolConfig;", "Lcom/google/ai/client/generativeai/type/ToolConfig;", "Lkotlinx/serialization/json/JsonObject;", "Lorg/json/JSONObject;", "toPublic", "Lcom/google/ai/client/generativeai/type/CountTokensResponse;", "Lcom/google/ai/client/generativeai/common/CountTokensResponse;", "Lcom/google/ai/client/generativeai/type/GenerateContentResponse;", "Lcom/google/ai/client/generativeai/common/GenerateContentResponse;", "Lcom/google/ai/client/generativeai/type/UsageMetadata;", "Lcom/google/ai/client/generativeai/common/UsageMetadata;", "Lcom/google/ai/client/generativeai/type/BlockReason;", "Lcom/google/ai/client/generativeai/common/server/BlockReason;", "Lcom/google/ai/client/generativeai/type/Candidate;", "Lcom/google/ai/client/generativeai/common/server/Candidate;", "Lcom/google/ai/client/generativeai/type/CitationMetadata;", "Lcom/google/ai/client/generativeai/common/server/CitationSources;", "Lcom/google/ai/client/generativeai/type/FinishReason;", "Lcom/google/ai/client/generativeai/common/server/FinishReason;", "Lcom/google/ai/client/generativeai/type/HarmProbability;", "Lcom/google/ai/client/generativeai/common/server/HarmProbability;", "Lcom/google/ai/client/generativeai/type/PromptFeedback;", "Lcom/google/ai/client/generativeai/common/server/PromptFeedback;", "Lcom/google/ai/client/generativeai/type/SafetyRating;", "Lcom/google/ai/client/generativeai/common/server/SafetyRating;", "generativeai_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ConversionsKt {
    private static final int BASE_64_FLAGS = 2;

    /* compiled from: conversions.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$6;
        public static final /* synthetic */ int[] $EnumSwitchMapping$7;
        public static final /* synthetic */ int[] $EnumSwitchMapping$8;

        static {
            int[] iArr = new int[HarmCategory.values().length];
            try {
                iArr[HarmCategory.HARASSMENT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[HarmCategory.HATE_SPEECH.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[HarmCategory.SEXUALLY_EXPLICIT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[HarmCategory.DANGEROUS_CONTENT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[HarmCategory.UNKNOWN.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[BlockThreshold.values().length];
            try {
                iArr2[BlockThreshold.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[BlockThreshold.ONLY_HIGH.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[BlockThreshold.MEDIUM_AND_ABOVE.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr2[BlockThreshold.LOW_AND_ABOVE.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr2[BlockThreshold.UNSPECIFIED.ordinal()] = 5;
            } catch (NoSuchFieldError e10) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[ExecutionOutcome.values().length];
            try {
                iArr3[ExecutionOutcome.UNSPECIFIED.ordinal()] = 1;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr3[ExecutionOutcome.OK.ordinal()] = 2;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr3[ExecutionOutcome.FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr3[ExecutionOutcome.DEADLINE_EXCEEDED.ordinal()] = 4;
            } catch (NoSuchFieldError e14) {
            }
            $EnumSwitchMapping$2 = iArr3;
            int[] iArr4 = new int[FunctionCallingConfig.Mode.values().length];
            try {
                iArr4[FunctionCallingConfig.Mode.ANY.ordinal()] = 1;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr4[FunctionCallingConfig.Mode.AUTO.ordinal()] = 2;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr4[FunctionCallingConfig.Mode.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError e17) {
            }
            $EnumSwitchMapping$3 = iArr4;
            int[] iArr5 = new int[FinishReason.values().length];
            try {
                iArr5[FinishReason.MAX_TOKENS.ordinal()] = 1;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr5[FinishReason.RECITATION.ordinal()] = 2;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr5[FinishReason.SAFETY.ordinal()] = 3;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr5[FinishReason.STOP.ordinal()] = 4;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr5[FinishReason.OTHER.ordinal()] = 5;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr5[FinishReason.UNSPECIFIED.ordinal()] = 6;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr5[FinishReason.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError e24) {
            }
            $EnumSwitchMapping$4 = iArr5;
            int[] iArr6 = new int[com.google.ai.client.generativeai.common.shared.HarmCategory.values().length];
            try {
                iArr6[com.google.ai.client.generativeai.common.shared.HarmCategory.HARASSMENT.ordinal()] = 1;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr6[com.google.ai.client.generativeai.common.shared.HarmCategory.HATE_SPEECH.ordinal()] = 2;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr6[com.google.ai.client.generativeai.common.shared.HarmCategory.SEXUALLY_EXPLICIT.ordinal()] = 3;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr6[com.google.ai.client.generativeai.common.shared.HarmCategory.DANGEROUS_CONTENT.ordinal()] = 4;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr6[com.google.ai.client.generativeai.common.shared.HarmCategory.UNKNOWN.ordinal()] = 5;
            } catch (NoSuchFieldError e29) {
            }
            $EnumSwitchMapping$5 = iArr6;
            int[] iArr7 = new int[HarmProbability.values().length];
            try {
                iArr7[HarmProbability.HIGH.ordinal()] = 1;
            } catch (NoSuchFieldError e30) {
            }
            try {
                iArr7[HarmProbability.MEDIUM.ordinal()] = 2;
            } catch (NoSuchFieldError e31) {
            }
            try {
                iArr7[HarmProbability.LOW.ordinal()] = 3;
            } catch (NoSuchFieldError e32) {
            }
            try {
                iArr7[HarmProbability.NEGLIGIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError e33) {
            }
            try {
                iArr7[HarmProbability.UNSPECIFIED.ordinal()] = 5;
            } catch (NoSuchFieldError e34) {
            }
            try {
                iArr7[HarmProbability.UNKNOWN.ordinal()] = 6;
            } catch (NoSuchFieldError e35) {
            }
            $EnumSwitchMapping$6 = iArr7;
            int[] iArr8 = new int[BlockReason.values().length];
            try {
                iArr8[BlockReason.UNSPECIFIED.ordinal()] = 1;
            } catch (NoSuchFieldError e36) {
            }
            try {
                iArr8[BlockReason.SAFETY.ordinal()] = 2;
            } catch (NoSuchFieldError e37) {
            }
            try {
                iArr8[BlockReason.OTHER.ordinal()] = 3;
            } catch (NoSuchFieldError e38) {
            }
            try {
                iArr8[BlockReason.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError e39) {
            }
            $EnumSwitchMapping$7 = iArr8;
            int[] iArr9 = new int[Outcome.values().length];
            try {
                iArr9[Outcome.UNSPECIFIED.ordinal()] = 1;
            } catch (NoSuchFieldError e40) {
            }
            try {
                iArr9[Outcome.OUTCOME_OK.ordinal()] = 2;
            } catch (NoSuchFieldError e41) {
            }
            try {
                iArr9[Outcome.OUTCOME_FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError e42) {
            }
            try {
                iArr9[Outcome.OUTCOME_DEADLINE_EXCEEDED.ordinal()] = 4;
            } catch (NoSuchFieldError e43) {
            }
            $EnumSwitchMapping$8 = iArr9;
        }
    }

    public static final RequestOptions toInternal(com.google.ai.client.generativeai.type.RequestOptions $this$toInternal) {
        Intrinsics.checkNotNullParameter($this$toInternal, "<this>");
        return new RequestOptions($this$toInternal.getTimeout(), $this$toInternal.getApiVersion(), (String) null, 4, (DefaultConstructorMarker) null);
    }

    public static final Content toInternal(com.google.ai.client.generativeai.type.Content $this$toInternal) {
        Intrinsics.checkNotNullParameter($this$toInternal, "<this>");
        String role = $this$toInternal.getRole();
        Iterable $this$map$iv = $this$toInternal.getParts();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Part it = (Part) item$iv$iv;
            destination$iv$iv.add(toInternal(it));
        }
        return new Content(role, (List) destination$iv$iv);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final com.google.ai.client.generativeai.common.shared.Part toInternal(Part part) {
        Intrinsics.checkNotNullParameter(part, "<this>");
        if (part instanceof TextPart) {
            return new com.google.ai.client.generativeai.common.shared.TextPart(((TextPart) part).getText());
        }
        if (part instanceof ImagePart) {
            return new BlobPart(new Blob("image/jpeg", encodeBitmapToBase64Png(((ImagePart) part).getImage())));
        }
        int i = 2;
        if (part instanceof com.google.ai.client.generativeai.type.BlobPart) {
            String mimeType = ((com.google.ai.client.generativeai.type.BlobPart) part).getMimeType();
            String encodeToString = Base64.encodeToString(((com.google.ai.client.generativeai.type.BlobPart) part).getBlob(), 2);
            Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(blob, BASE_64_FLAGS)");
            return new BlobPart(new Blob(mimeType, encodeToString));
        }
        if (part instanceof FunctionCallPart) {
            return new com.google.ai.client.generativeai.common.shared.FunctionCallPart(new FunctionCall(((FunctionCallPart) part).getName(), ((FunctionCallPart) part).getArgs()));
        }
        if (part instanceof FunctionResponsePart) {
            return new com.google.ai.client.generativeai.common.shared.FunctionResponsePart(new FunctionResponse(((FunctionResponsePart) part).getName(), toInternal(((FunctionResponsePart) part).getResponse())));
        }
        if (part instanceof FileDataPart) {
            return new com.google.ai.client.generativeai.common.shared.FileDataPart(new FileData(((FileDataPart) part).getMimeType(), ((FileDataPart) part).getUri()));
        }
        if (part instanceof ExecutableCodePart) {
            return new com.google.ai.client.generativeai.common.shared.ExecutableCodePart(new ExecutableCode(((ExecutableCodePart) part).getLanguage(), ((ExecutableCodePart) part).getCode()));
        }
        if (part instanceof CodeExecutionResultPart) {
            return new com.google.ai.client.generativeai.common.shared.CodeExecutionResultPart(new CodeExecutionResult(toInternal(((CodeExecutionResultPart) part).getOutcome()), ((CodeExecutionResultPart) part).getOutput()));
        }
        throw new SerializationException("The given subclass of Part (" + part.getClass().getSimpleName() + ") is not supported in the serialization yet.", null, i, 0 == true ? 1 : 0);
    }

    public static final SafetySetting toInternal(com.google.ai.client.generativeai.type.SafetySetting $this$toInternal) {
        Intrinsics.checkNotNullParameter($this$toInternal, "<this>");
        return new SafetySetting(toInternal($this$toInternal.getHarmCategory()), toInternal($this$toInternal.getThreshold()), (HarmBlockMethod) null, 4, (DefaultConstructorMarker) null);
    }

    public static final GenerationConfig toInternal(com.google.ai.client.generativeai.type.GenerationConfig $this$toInternal) {
        Intrinsics.checkNotNullParameter($this$toInternal, "<this>");
        Float temperature = $this$toInternal.getTemperature();
        Float topP = $this$toInternal.getTopP();
        Integer topK = $this$toInternal.getTopK();
        Integer candidateCount = $this$toInternal.getCandidateCount();
        Integer maxOutputTokens = $this$toInternal.getMaxOutputTokens();
        List<String> stopSequences = $this$toInternal.getStopSequences();
        String responseMimeType = $this$toInternal.getResponseMimeType();
        Schema<?> responseSchema = $this$toInternal.getResponseSchema();
        return new GenerationConfig(temperature, topP, topK, candidateCount, maxOutputTokens, stopSequences, responseMimeType, (Float) null, (Float) null, responseSchema != null ? toInternal(responseSchema) : null, 384, (DefaultConstructorMarker) null);
    }

    public static final com.google.ai.client.generativeai.common.shared.HarmCategory toInternal(HarmCategory $this$toInternal) {
        Intrinsics.checkNotNullParameter($this$toInternal, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[$this$toInternal.ordinal()]) {
            case 1:
                return com.google.ai.client.generativeai.common.shared.HarmCategory.HARASSMENT;
            case 2:
                return com.google.ai.client.generativeai.common.shared.HarmCategory.HATE_SPEECH;
            case 3:
                return com.google.ai.client.generativeai.common.shared.HarmCategory.SEXUALLY_EXPLICIT;
            case 4:
                return com.google.ai.client.generativeai.common.shared.HarmCategory.DANGEROUS_CONTENT;
            case 5:
                return com.google.ai.client.generativeai.common.shared.HarmCategory.UNKNOWN;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final HarmBlockThreshold toInternal(BlockThreshold $this$toInternal) {
        Intrinsics.checkNotNullParameter($this$toInternal, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$1[$this$toInternal.ordinal()]) {
            case 1:
                return HarmBlockThreshold.BLOCK_NONE;
            case 2:
                return HarmBlockThreshold.BLOCK_ONLY_HIGH;
            case 3:
                return HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE;
            case 4:
                return HarmBlockThreshold.BLOCK_LOW_AND_ABOVE;
            case 5:
                return HarmBlockThreshold.UNSPECIFIED;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final Outcome toInternal(ExecutionOutcome $this$toInternal) {
        Intrinsics.checkNotNullParameter($this$toInternal, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$2[$this$toInternal.ordinal()]) {
            case 1:
                return Outcome.UNSPECIFIED;
            case 2:
                return Outcome.OUTCOME_OK;
            case 3:
                return Outcome.OUTCOME_FAILED;
            case 4:
                return Outcome.OUTCOME_DEADLINE_EXCEEDED;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final Tool toInternal(com.google.ai.client.generativeai.type.Tool $this$toInternal) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter($this$toInternal, "<this>");
        Iterable functionDeclarations = $this$toInternal.getFunctionDeclarations();
        if (functionDeclarations == null) {
            arrayList = null;
        } else {
            Iterable $this$map$iv = functionDeclarations;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                FunctionDeclaration it = (FunctionDeclaration) item$iv$iv;
                destination$iv$iv.add(toInternal(it));
            }
            arrayList = (List) destination$iv$iv;
        }
        JSONObject codeExecution = $this$toInternal.getCodeExecution();
        return new Tool(arrayList, codeExecution != null ? toInternal(codeExecution) : null);
    }

    public static final ToolConfig toInternal(com.google.ai.client.generativeai.type.ToolConfig $this$toInternal) {
        FunctionCallingConfig.Mode mode;
        Intrinsics.checkNotNullParameter($this$toInternal, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$3[$this$toInternal.getFunctionCallingConfig().getMode().ordinal()]) {
            case 1:
                mode = FunctionCallingConfig.Mode.ANY;
                break;
            case 2:
                mode = FunctionCallingConfig.Mode.AUTO;
                break;
            case 3:
                mode = FunctionCallingConfig.Mode.NONE;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return new ToolConfig(new com.google.ai.client.generativeai.common.client.FunctionCallingConfig(mode));
    }

    public static final UsageMetadata toPublic(com.google.ai.client.generativeai.common.UsageMetadata $this$toPublic) {
        Intrinsics.checkNotNullParameter($this$toPublic, "<this>");
        Integer promptTokenCount = $this$toPublic.getPromptTokenCount();
        int intValue = promptTokenCount != null ? promptTokenCount.intValue() : 0;
        Integer candidatesTokenCount = $this$toPublic.getCandidatesTokenCount();
        int intValue2 = candidatesTokenCount != null ? candidatesTokenCount.intValue() : 0;
        Integer totalTokenCount = $this$toPublic.getTotalTokenCount();
        return new UsageMetadata(intValue, intValue2, totalTokenCount != null ? totalTokenCount.intValue() : 0);
    }

    public static final com.google.ai.client.generativeai.common.client.FunctionDeclaration toInternal(FunctionDeclaration $this$toInternal) {
        Intrinsics.checkNotNullParameter($this$toInternal, "<this>");
        String name = $this$toInternal.getName();
        String description = $this$toInternal.getDescription();
        Iterable $this$associate$iv = $this$toInternal.getParameters();
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv, 10)), 16);
        Map destination$iv$iv = new LinkedHashMap(capacity$iv);
        for (Object element$iv$iv : $this$associate$iv) {
            Schema it = (Schema) element$iv$iv;
            Pair pair = TuplesKt.to(it.getName(), toInternal(it));
            destination$iv$iv.put(pair.getFirst(), pair.getSecond());
        }
        return new com.google.ai.client.generativeai.common.client.FunctionDeclaration(name, description, new com.google.ai.client.generativeai.common.client.Schema("OBJECT", (String) null, (String) null, (Boolean) false, (List) null, destination$iv$iv, (List) $this$toInternal.getRequiredParameters(), (com.google.ai.client.generativeai.common.client.Schema) null, 150, (DefaultConstructorMarker) null));
    }

    public static final <T> com.google.ai.client.generativeai.common.client.Schema toInternal(Schema<T> schema) {
        Map map;
        Intrinsics.checkNotNullParameter(schema, "<this>");
        String name = schema.getType().getName();
        String description = schema.getDescription();
        String format = schema.getFormat();
        Boolean nullable = schema.getNullable();
        List<String> list = schema.getEnum();
        Map $this$mapValues$iv = schema.getProperties();
        if ($this$mapValues$iv == null) {
            map = null;
        } else {
            Map destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity($this$mapValues$iv.size()));
            Iterable $this$associateByTo$iv$iv$iv = $this$mapValues$iv.entrySet();
            for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
                Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
                Object key = it$iv$iv.getKey();
                Map.Entry it = (Map.Entry) element$iv$iv$iv;
                destination$iv$iv.put(key, toInternal((Schema) it.getValue()));
                $this$mapValues$iv = $this$mapValues$iv;
            }
            map = destination$iv$iv;
        }
        List<String> required = schema.getRequired();
        Schema<? extends Object> items = schema.getItems();
        return new com.google.ai.client.generativeai.common.client.Schema(name, description, format, nullable, list, map, required, items != null ? toInternal(items) : null);
    }

    public static final JsonObject toInternal(JSONObject $this$toInternal) {
        Intrinsics.checkNotNullParameter($this$toInternal, "<this>");
        Json this_$iv = Json.INSTANCE;
        String string$iv = $this$toInternal.toString();
        Intrinsics.checkNotNullExpressionValue(string$iv, "toString()");
        this_$iv.getSerializersModule();
        return (JsonObject) this_$iv.decodeFromString(JsonObject.INSTANCE.serializer(), string$iv);
    }

    public static final Candidate toPublic(com.google.ai.client.generativeai.common.server.Candidate $this$toPublic) {
        List safetyRatings;
        com.google.ai.client.generativeai.type.Content content;
        Iterable citationSources;
        Intrinsics.checkNotNullParameter($this$toPublic, "<this>");
        Iterable safetyRatings2 = $this$toPublic.getSafetyRatings();
        List citations = null;
        if (safetyRatings2 == null) {
            safetyRatings = null;
        } else {
            Iterable $this$map$iv = safetyRatings2;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                SafetyRating it = (SafetyRating) item$iv$iv;
                destination$iv$iv.add(toPublic(it));
            }
            safetyRatings = (List) destination$iv$iv;
        }
        if (safetyRatings == null) {
            safetyRatings = CollectionsKt.emptyList();
        }
        CitationMetadata citationMetadata = $this$toPublic.getCitationMetadata();
        if (citationMetadata != null && (citationSources = citationMetadata.getCitationSources()) != null) {
            Iterable $this$map$iv2 = citationSources;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
            for (Object item$iv$iv2 : $this$map$iv2) {
                CitationSources it2 = (CitationSources) item$iv$iv2;
                destination$iv$iv2.add(toPublic(it2));
            }
            citations = (List) destination$iv$iv2;
        }
        if (citations == null) {
            citations = CollectionsKt.emptyList();
        }
        com.google.ai.client.generativeai.type.FinishReason finishReason = toPublic($this$toPublic.getFinishReason());
        Content content2 = $this$toPublic.getContent();
        if (content2 == null || (content = toPublic(content2)) == null) {
            content = ContentKt.content("model", new Function1<Content.Builder, Unit>() { // from class: com.google.ai.client.generativeai.internal.util.ConversionsKt$toPublic$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Content.Builder builder) {
                    invoke2(builder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Content.Builder content3) {
                    Intrinsics.checkNotNullParameter(content3, "$this$content");
                }
            });
        }
        return new Candidate(content, safetyRatings, citations, finishReason);
    }

    public static final com.google.ai.client.generativeai.type.Content toPublic(com.google.ai.client.generativeai.common.shared.Content $this$toPublic) {
        Intrinsics.checkNotNullParameter($this$toPublic, "<this>");
        String role = $this$toPublic.getRole();
        Iterable $this$map$iv = $this$toPublic.getParts();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            com.google.ai.client.generativeai.common.shared.Part it = (com.google.ai.client.generativeai.common.shared.Part) item$iv$iv;
            destination$iv$iv.add(toPublic(it));
        }
        return new com.google.ai.client.generativeai.type.Content(role, (List) destination$iv$iv);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Part toPublic(com.google.ai.client.generativeai.common.shared.Part part) {
        Intrinsics.checkNotNullParameter(part, "<this>");
        if (part instanceof com.google.ai.client.generativeai.common.shared.TextPart) {
            return new TextPart(((com.google.ai.client.generativeai.common.shared.TextPart) part).getText());
        }
        Throwable th = null;
        Object[] objArr = 0;
        int i = 2;
        if (part instanceof BlobPart) {
            byte[] data = Base64.decode(((BlobPart) part).getInlineData().getData(), 2);
            if (StringsKt.contains$default((CharSequence) ((BlobPart) part).getInlineData().getMimeType(), (CharSequence) "image", false, 2, (Object) null)) {
                Intrinsics.checkNotNullExpressionValue(data, "data");
                Bitmap decodeBitmapFromImage = decodeBitmapFromImage(data);
                Intrinsics.checkNotNullExpressionValue(decodeBitmapFromImage, "decodeBitmapFromImage(data)");
                return new ImagePart(decodeBitmapFromImage);
            }
            String mimeType = ((BlobPart) part).getInlineData().getMimeType();
            Intrinsics.checkNotNullExpressionValue(data, "data");
            return new com.google.ai.client.generativeai.type.BlobPart(mimeType, data);
        }
        if (part instanceof com.google.ai.client.generativeai.common.shared.FunctionCallPart) {
            return new FunctionCallPart(((com.google.ai.client.generativeai.common.shared.FunctionCallPart) part).getFunctionCall().getName(), ((com.google.ai.client.generativeai.common.shared.FunctionCallPart) part).getFunctionCall().getArgs());
        }
        if (part instanceof com.google.ai.client.generativeai.common.shared.FunctionResponsePart) {
            return new FunctionResponsePart(((com.google.ai.client.generativeai.common.shared.FunctionResponsePart) part).getFunctionResponse().getName(), toPublic(((com.google.ai.client.generativeai.common.shared.FunctionResponsePart) part).getFunctionResponse().getResponse()));
        }
        if (part instanceof com.google.ai.client.generativeai.common.shared.FileDataPart) {
            return new FileDataPart(((com.google.ai.client.generativeai.common.shared.FileDataPart) part).getFileData().getFileUri(), ((com.google.ai.client.generativeai.common.shared.FileDataPart) part).getFileData().getMimeType());
        }
        if (part instanceof com.google.ai.client.generativeai.common.shared.ExecutableCodePart) {
            return new ExecutableCodePart(((com.google.ai.client.generativeai.common.shared.ExecutableCodePart) part).getExecutableCode().getLanguage(), ((com.google.ai.client.generativeai.common.shared.ExecutableCodePart) part).getExecutableCode().getCode());
        }
        if (part instanceof com.google.ai.client.generativeai.common.shared.CodeExecutionResultPart) {
            return new CodeExecutionResultPart(toPublic(((com.google.ai.client.generativeai.common.shared.CodeExecutionResultPart) part).getCodeExecutionResult().getOutcome()), ((com.google.ai.client.generativeai.common.shared.CodeExecutionResultPart) part).getCodeExecutionResult().getOutput());
        }
        throw new SerializationException("Unsupported part type \"" + part.getClass().getSimpleName() + "\" provided. This model may not be supported by this SDK.", th, i, objArr == true ? 1 : 0);
    }

    public static final com.google.ai.client.generativeai.type.CitationMetadata toPublic(CitationSources $this$toPublic) {
        Intrinsics.checkNotNullParameter($this$toPublic, "<this>");
        return new com.google.ai.client.generativeai.type.CitationMetadata($this$toPublic.getStartIndex(), $this$toPublic.getEndIndex(), $this$toPublic.getUri(), $this$toPublic.getLicense());
    }

    public static final com.google.ai.client.generativeai.type.SafetyRating toPublic(SafetyRating $this$toPublic) {
        Intrinsics.checkNotNullParameter($this$toPublic, "<this>");
        return new com.google.ai.client.generativeai.type.SafetyRating(toPublic($this$toPublic.getCategory()), toPublic($this$toPublic.getProbability()));
    }

    public static final PromptFeedback toPublic(com.google.ai.client.generativeai.common.server.PromptFeedback $this$toPublic) {
        List safetyRatings;
        Intrinsics.checkNotNullParameter($this$toPublic, "<this>");
        Iterable safetyRatings2 = $this$toPublic.getSafetyRatings();
        if (safetyRatings2 == null) {
            safetyRatings = null;
        } else {
            Iterable $this$map$iv = safetyRatings2;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                SafetyRating it = (SafetyRating) item$iv$iv;
                destination$iv$iv.add(toPublic(it));
            }
            safetyRatings = (List) destination$iv$iv;
        }
        if (safetyRatings == null) {
            safetyRatings = CollectionsKt.emptyList();
        }
        BlockReason blockReason = $this$toPublic.getBlockReason();
        return new PromptFeedback(blockReason != null ? toPublic(blockReason) : null, safetyRatings);
    }

    public static final com.google.ai.client.generativeai.type.FinishReason toPublic(FinishReason $this$toPublic) {
        switch ($this$toPublic == null ? -1 : WhenMappings.$EnumSwitchMapping$4[$this$toPublic.ordinal()]) {
            case -1:
                return null;
            case 0:
            default:
                throw new NoWhenBranchMatchedException();
            case 1:
                return com.google.ai.client.generativeai.type.FinishReason.MAX_TOKENS;
            case 2:
                return com.google.ai.client.generativeai.type.FinishReason.RECITATION;
            case 3:
                return com.google.ai.client.generativeai.type.FinishReason.SAFETY;
            case 4:
                return com.google.ai.client.generativeai.type.FinishReason.STOP;
            case 5:
                return com.google.ai.client.generativeai.type.FinishReason.OTHER;
            case 6:
                return com.google.ai.client.generativeai.type.FinishReason.UNSPECIFIED;
            case 7:
                return com.google.ai.client.generativeai.type.FinishReason.UNKNOWN;
        }
    }

    public static final HarmCategory toPublic(com.google.ai.client.generativeai.common.shared.HarmCategory $this$toPublic) {
        Intrinsics.checkNotNullParameter($this$toPublic, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$5[$this$toPublic.ordinal()]) {
            case 1:
                return HarmCategory.HARASSMENT;
            case 2:
                return HarmCategory.HATE_SPEECH;
            case 3:
                return HarmCategory.SEXUALLY_EXPLICIT;
            case 4:
                return HarmCategory.DANGEROUS_CONTENT;
            case 5:
                return HarmCategory.UNKNOWN;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final com.google.ai.client.generativeai.type.HarmProbability toPublic(HarmProbability $this$toPublic) {
        Intrinsics.checkNotNullParameter($this$toPublic, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$6[$this$toPublic.ordinal()]) {
            case 1:
                return com.google.ai.client.generativeai.type.HarmProbability.HIGH;
            case 2:
                return com.google.ai.client.generativeai.type.HarmProbability.MEDIUM;
            case 3:
                return com.google.ai.client.generativeai.type.HarmProbability.LOW;
            case 4:
                return com.google.ai.client.generativeai.type.HarmProbability.NEGLIGIBLE;
            case 5:
                return com.google.ai.client.generativeai.type.HarmProbability.UNSPECIFIED;
            case 6:
                return com.google.ai.client.generativeai.type.HarmProbability.UNKNOWN;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final com.google.ai.client.generativeai.type.BlockReason toPublic(BlockReason $this$toPublic) {
        Intrinsics.checkNotNullParameter($this$toPublic, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$7[$this$toPublic.ordinal()]) {
            case 1:
                return com.google.ai.client.generativeai.type.BlockReason.UNSPECIFIED;
            case 2:
                return com.google.ai.client.generativeai.type.BlockReason.SAFETY;
            case 3:
                return com.google.ai.client.generativeai.type.BlockReason.OTHER;
            case 4:
                return com.google.ai.client.generativeai.type.BlockReason.UNKNOWN;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final ExecutionOutcome toPublic(Outcome $this$toPublic) {
        Intrinsics.checkNotNullParameter($this$toPublic, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$8[$this$toPublic.ordinal()]) {
            case 1:
                return ExecutionOutcome.UNSPECIFIED;
            case 2:
                return ExecutionOutcome.OK;
            case 3:
                return ExecutionOutcome.FAILED;
            case 4:
                return ExecutionOutcome.DEADLINE_EXCEEDED;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final GenerateContentResponse toPublic(com.google.ai.client.generativeai.common.GenerateContentResponse $this$toPublic) {
        List list;
        Intrinsics.checkNotNullParameter($this$toPublic, "<this>");
        Iterable candidates = $this$toPublic.getCandidates();
        if (candidates == null) {
            list = null;
        } else {
            Iterable $this$map$iv = candidates;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                com.google.ai.client.generativeai.common.server.Candidate it = (com.google.ai.client.generativeai.common.server.Candidate) item$iv$iv;
                destination$iv$iv.add(toPublic(it));
            }
            list = (List) destination$iv$iv;
        }
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        com.google.ai.client.generativeai.common.server.PromptFeedback promptFeedback = $this$toPublic.getPromptFeedback();
        PromptFeedback promptFeedback2 = promptFeedback != null ? toPublic(promptFeedback) : null;
        com.google.ai.client.generativeai.common.UsageMetadata usageMetadata = $this$toPublic.getUsageMetadata();
        return new GenerateContentResponse(list, promptFeedback2, usageMetadata != null ? toPublic(usageMetadata) : null);
    }

    public static final CountTokensResponse toPublic(com.google.ai.client.generativeai.common.CountTokensResponse $this$toPublic) {
        Intrinsics.checkNotNullParameter($this$toPublic, "<this>");
        return new CountTokensResponse($this$toPublic.getTotalTokens());
    }

    public static final JSONObject toPublic(JsonObject $this$toPublic) {
        Intrinsics.checkNotNullParameter($this$toPublic, "<this>");
        return new JSONObject($this$toPublic.toString());
    }

    private static final String encodeBitmapToBase64Png(Bitmap input) {
        ByteArrayOutputStream it = new ByteArrayOutputStream();
        input.compress(Bitmap.CompressFormat.JPEG, 80, it);
        String encodeToString = Base64.encodeToString(it.toByteArray(), 2);
        Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(it.toByteArray(), BASE_64_FLAGS)");
        return encodeToString;
    }

    private static final Bitmap decodeBitmapFromImage(byte[] input) {
        return BitmapFactory.decodeByteArray(input, 0, input.length);
    }
}
