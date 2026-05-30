package io.ktor.client.request.forms;

import com.maxmpz.poweramp.player.TrackProviderConsts;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestKt;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpStatement;
import io.ktor.http.HttpMethod;
import io.ktor.http.Parameters;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.PartData;
import io.ktor.util.reflect.TypeInfoJvmKt;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

/* compiled from: formBuilders.kt */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aD\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\f\u001aL\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001aF\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0019\b\u0006\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a>\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0019\b\u0006\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001aD\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\f\u001aL\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001aF\u0010\u0018\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a>\u0010\u0018\u001a\u00020\u0017*\u00020\u00022\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"prepareForm", "Lio/ktor/client/statement/HttpStatement;", "Lio/ktor/client/HttpClient;", "formParameters", "Lio/ktor/http/Parameters;", "encodeInQuery", "", "block", "Lkotlin/Function1;", "Lio/ktor/client/request/HttpRequestBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/client/HttpClient;Lio/ktor/http/Parameters;ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", TrackProviderConsts.COLUMN_URL, "", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Lio/ktor/http/Parameters;ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prepareFormWithBinaryData", "formData", "", "Lio/ktor/http/content/PartData;", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/ktor/client/HttpClient;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitForm", "Lio/ktor/client/statement/HttpResponse;", "submitFormWithBinaryData", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class FormBuildersKt {
    public static /* synthetic */ Object submitForm$default(HttpClient $this$submitForm_u24default, Parameters formParameters, boolean encodeInQuery, Function1 block, Continuation $completion, int i, Object obj) {
        Parameters formParameters2 = (i & 1) != 0 ? Parameters.INSTANCE.getEmpty() : formParameters;
        boolean encodeInQuery2 = (i & 2) != 0 ? false : encodeInQuery;
        FormBuildersKt$submitForm$2 block2 = (i & 4) != 0 ? new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.forms.FormBuildersKt$submitForm$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                invoke2(httpRequestBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HttpRequestBuilder $this$null) {
                Intrinsics.checkNotNullParameter($this$null, "$this$null");
            }
        } : block;
        HttpRequestBuilder $this$submitForm_u24lambda_u240 = new HttpRequestBuilder();
        if (encodeInQuery2) {
            $this$submitForm_u24lambda_u240.setMethod(HttpMethod.INSTANCE.getGet());
            $this$submitForm_u24lambda_u240.getUrl().getParameters().appendAll(formParameters2);
        } else {
            $this$submitForm_u24lambda_u240.setMethod(HttpMethod.INSTANCE.getPost());
            Object body$iv = new FormDataContent(formParameters2);
            if (body$iv instanceof OutgoingContent) {
                $this$submitForm_u24lambda_u240.setBody(body$iv);
                $this$submitForm_u24lambda_u240.setBodyType(null);
            } else {
                $this$submitForm_u24lambda_u240.setBody(body$iv);
                KType kType$iv$iv = Reflection.typeOf(FormDataContent.class);
                Type reifiedType$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv);
                $this$submitForm_u24lambda_u240.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv, Reflection.getOrCreateKotlinClass(FormDataContent.class), kType$iv$iv));
            }
        }
        block2.invoke($this$submitForm_u24lambda_u240);
        return new HttpStatement($this$submitForm_u24lambda_u240, $this$submitForm_u24default).execute($completion);
    }

    public static final Object submitForm(HttpClient $this$submitForm, Parameters formParameters, boolean encodeInQuery, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpResponse> continuation) {
        HttpRequestBuilder $this$submitForm_u24lambda_u240 = new HttpRequestBuilder();
        if (encodeInQuery) {
            $this$submitForm_u24lambda_u240.setMethod(HttpMethod.INSTANCE.getGet());
            $this$submitForm_u24lambda_u240.getUrl().getParameters().appendAll(formParameters);
        } else {
            $this$submitForm_u24lambda_u240.setMethod(HttpMethod.INSTANCE.getPost());
            Object body$iv = new FormDataContent(formParameters);
            if (body$iv instanceof OutgoingContent) {
                $this$submitForm_u24lambda_u240.setBody(body$iv);
                $this$submitForm_u24lambda_u240.setBodyType(null);
            } else {
                $this$submitForm_u24lambda_u240.setBody(body$iv);
                KType kType$iv$iv = Reflection.typeOf(FormDataContent.class);
                Type reifiedType$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv);
                $this$submitForm_u24lambda_u240.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv, Reflection.getOrCreateKotlinClass(FormDataContent.class), kType$iv$iv));
            }
        }
        function1.invoke($this$submitForm_u24lambda_u240);
        return new HttpStatement($this$submitForm_u24lambda_u240, $this$submitForm).execute(continuation);
    }

    private static final Object submitForm$$forInline(HttpClient $this$submitForm, Parameters formParameters, boolean encodeInQuery, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpResponse> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestBuilder $this$submitForm_u24lambda_u240 = httpRequestBuilder;
        if (encodeInQuery) {
            $this$submitForm_u24lambda_u240.setMethod(HttpMethod.INSTANCE.getGet());
            $this$submitForm_u24lambda_u240.getUrl().getParameters().appendAll(formParameters);
        } else {
            $this$submitForm_u24lambda_u240.setMethod(HttpMethod.INSTANCE.getPost());
            Object body$iv = new FormDataContent(formParameters);
            if (body$iv instanceof OutgoingContent) {
                $this$submitForm_u24lambda_u240.setBody(body$iv);
                $this$submitForm_u24lambda_u240.setBodyType(null);
            } else {
                $this$submitForm_u24lambda_u240.setBody(body$iv);
                KType kType$iv$iv = Reflection.typeOf(FormDataContent.class);
                Type reifiedType$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv);
                $this$submitForm_u24lambda_u240.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv, Reflection.getOrCreateKotlinClass(FormDataContent.class), kType$iv$iv));
            }
        }
        function1.invoke($this$submitForm_u24lambda_u240);
        Unit unit = Unit.INSTANCE;
        HttpRequestBuilder builder$iv$iv = httpRequestBuilder;
        return new HttpStatement(builder$iv$iv, $this$submitForm).execute(continuation);
    }

    public static final Object submitForm(HttpClient $this$submitForm, String url, Parameters formParameters, boolean encodeInQuery, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpResponse> continuation) {
        HttpRequestBuilder $this$submitForm_u24lambda_u240$iv = new HttpRequestBuilder();
        if (encodeInQuery) {
            $this$submitForm_u24lambda_u240$iv.setMethod(HttpMethod.INSTANCE.getGet());
            $this$submitForm_u24lambda_u240$iv.getUrl().getParameters().appendAll(formParameters);
        } else {
            $this$submitForm_u24lambda_u240$iv.setMethod(HttpMethod.INSTANCE.getPost());
            Object body$iv$iv = new FormDataContent(formParameters);
            if (body$iv$iv instanceof OutgoingContent) {
                $this$submitForm_u24lambda_u240$iv.setBody(body$iv$iv);
                $this$submitForm_u24lambda_u240$iv.setBodyType(null);
            } else {
                $this$submitForm_u24lambda_u240$iv.setBody(body$iv$iv);
                KType kType$iv$iv$iv = Reflection.typeOf(FormDataContent.class);
                Type reifiedType$iv$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv$iv);
                $this$submitForm_u24lambda_u240$iv.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv$iv, Reflection.getOrCreateKotlinClass(FormDataContent.class), kType$iv$iv$iv));
            }
        }
        HttpRequestKt.url($this$submitForm_u24lambda_u240$iv, url);
        function1.invoke($this$submitForm_u24lambda_u240$iv);
        return new HttpStatement($this$submitForm_u24lambda_u240$iv, $this$submitForm).execute(continuation);
    }

    public static /* synthetic */ Object submitFormWithBinaryData$default(HttpClient $this$submitFormWithBinaryData_u24default, List formData, Function1 block, Continuation $completion, int i, Object obj) {
        FormBuildersKt$submitFormWithBinaryData$2 block2;
        if ((i & 2) == 0) {
            block2 = block;
        } else {
            block2 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.forms.FormBuildersKt$submitFormWithBinaryData$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        HttpRequestBuilder $this$submitFormWithBinaryData_u24lambda_u242 = new HttpRequestBuilder();
        $this$submitFormWithBinaryData_u24lambda_u242.setMethod(HttpMethod.INSTANCE.getPost());
        Object body$iv = new MultiPartFormDataContent(formData, null, null, 6, null);
        if (body$iv instanceof OutgoingContent) {
            $this$submitFormWithBinaryData_u24lambda_u242.setBody(body$iv);
            $this$submitFormWithBinaryData_u24lambda_u242.setBodyType(null);
        } else {
            $this$submitFormWithBinaryData_u24lambda_u242.setBody(body$iv);
            KType kType$iv$iv = Reflection.typeOf(MultiPartFormDataContent.class);
            Type reifiedType$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv);
            $this$submitFormWithBinaryData_u24lambda_u242.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv, Reflection.getOrCreateKotlinClass(MultiPartFormDataContent.class), kType$iv$iv));
        }
        block2.invoke($this$submitFormWithBinaryData_u24lambda_u242);
        return new HttpStatement($this$submitFormWithBinaryData_u24lambda_u242, $this$submitFormWithBinaryData_u24default).execute($completion);
    }

    public static final Object submitFormWithBinaryData(HttpClient $this$submitFormWithBinaryData, List<? extends PartData> list, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpResponse> continuation) {
        HttpRequestBuilder $this$submitFormWithBinaryData_u24lambda_u242 = new HttpRequestBuilder();
        $this$submitFormWithBinaryData_u24lambda_u242.setMethod(HttpMethod.INSTANCE.getPost());
        Object body$iv = new MultiPartFormDataContent(list, null, null, 6, null);
        if (body$iv instanceof OutgoingContent) {
            $this$submitFormWithBinaryData_u24lambda_u242.setBody(body$iv);
            $this$submitFormWithBinaryData_u24lambda_u242.setBodyType(null);
        } else {
            $this$submitFormWithBinaryData_u24lambda_u242.setBody(body$iv);
            KType kType$iv$iv = Reflection.typeOf(MultiPartFormDataContent.class);
            Type reifiedType$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv);
            $this$submitFormWithBinaryData_u24lambda_u242.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv, Reflection.getOrCreateKotlinClass(MultiPartFormDataContent.class), kType$iv$iv));
        }
        function1.invoke($this$submitFormWithBinaryData_u24lambda_u242);
        return new HttpStatement($this$submitFormWithBinaryData_u24lambda_u242, $this$submitFormWithBinaryData).execute(continuation);
    }

    private static final Object submitFormWithBinaryData$$forInline(HttpClient $this$submitFormWithBinaryData, List<? extends PartData> list, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpResponse> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestBuilder $this$submitFormWithBinaryData_u24lambda_u242 = httpRequestBuilder;
        $this$submitFormWithBinaryData_u24lambda_u242.setMethod(HttpMethod.INSTANCE.getPost());
        Object body$iv = new MultiPartFormDataContent(list, null, null, 6, null);
        if (body$iv instanceof OutgoingContent) {
            $this$submitFormWithBinaryData_u24lambda_u242.setBody(body$iv);
            $this$submitFormWithBinaryData_u24lambda_u242.setBodyType(null);
        } else {
            $this$submitFormWithBinaryData_u24lambda_u242.setBody(body$iv);
            KType kType$iv$iv = Reflection.typeOf(MultiPartFormDataContent.class);
            Type reifiedType$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv);
            $this$submitFormWithBinaryData_u24lambda_u242.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv, Reflection.getOrCreateKotlinClass(MultiPartFormDataContent.class), kType$iv$iv));
        }
        function1.invoke($this$submitFormWithBinaryData_u24lambda_u242);
        Unit unit = Unit.INSTANCE;
        HttpRequestBuilder builder$iv$iv = httpRequestBuilder;
        return new HttpStatement(builder$iv$iv, $this$submitFormWithBinaryData).execute(continuation);
    }

    public static /* synthetic */ Object submitFormWithBinaryData$default(HttpClient $this$submitFormWithBinaryData_u24default, String url, List formData, Function1 block, Continuation $completion, int i, Object obj) {
        FormBuildersKt$submitFormWithBinaryData$5 block2;
        if ((i & 4) == 0) {
            block2 = block;
        } else {
            block2 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.forms.FormBuildersKt$submitFormWithBinaryData$5
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        HttpRequestBuilder $this$submitFormWithBinaryData_u24lambda_u242$iv = new HttpRequestBuilder();
        $this$submitFormWithBinaryData_u24lambda_u242$iv.setMethod(HttpMethod.INSTANCE.getPost());
        Object body$iv$iv = new MultiPartFormDataContent(formData, null, null, 6, null);
        if (body$iv$iv instanceof OutgoingContent) {
            $this$submitFormWithBinaryData_u24lambda_u242$iv.setBody(body$iv$iv);
            $this$submitFormWithBinaryData_u24lambda_u242$iv.setBodyType(null);
        } else {
            $this$submitFormWithBinaryData_u24lambda_u242$iv.setBody(body$iv$iv);
            KType kType$iv$iv$iv = Reflection.typeOf(MultiPartFormDataContent.class);
            Type reifiedType$iv$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv$iv);
            $this$submitFormWithBinaryData_u24lambda_u242$iv.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv$iv, Reflection.getOrCreateKotlinClass(MultiPartFormDataContent.class), kType$iv$iv$iv));
        }
        HttpRequestKt.url($this$submitFormWithBinaryData_u24lambda_u242$iv, url);
        block2.invoke($this$submitFormWithBinaryData_u24lambda_u242$iv);
        return new HttpStatement($this$submitFormWithBinaryData_u24lambda_u242$iv, $this$submitFormWithBinaryData_u24default).execute($completion);
    }

    public static final Object submitFormWithBinaryData(HttpClient $this$submitFormWithBinaryData, String url, List<? extends PartData> list, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpResponse> continuation) {
        HttpRequestBuilder $this$submitFormWithBinaryData_u24lambda_u242$iv = new HttpRequestBuilder();
        $this$submitFormWithBinaryData_u24lambda_u242$iv.setMethod(HttpMethod.INSTANCE.getPost());
        Object body$iv$iv = new MultiPartFormDataContent(list, null, null, 6, null);
        if (body$iv$iv instanceof OutgoingContent) {
            $this$submitFormWithBinaryData_u24lambda_u242$iv.setBody(body$iv$iv);
            $this$submitFormWithBinaryData_u24lambda_u242$iv.setBodyType(null);
        } else {
            $this$submitFormWithBinaryData_u24lambda_u242$iv.setBody(body$iv$iv);
            KType kType$iv$iv$iv = Reflection.typeOf(MultiPartFormDataContent.class);
            Type reifiedType$iv$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv$iv);
            $this$submitFormWithBinaryData_u24lambda_u242$iv.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv$iv, Reflection.getOrCreateKotlinClass(MultiPartFormDataContent.class), kType$iv$iv$iv));
        }
        HttpRequestKt.url($this$submitFormWithBinaryData_u24lambda_u242$iv, url);
        function1.invoke($this$submitFormWithBinaryData_u24lambda_u242$iv);
        return new HttpStatement($this$submitFormWithBinaryData_u24lambda_u242$iv, $this$submitFormWithBinaryData).execute(continuation);
    }

    private static final Object submitFormWithBinaryData$$forInline(HttpClient $this$submitFormWithBinaryData, String url, List<? extends PartData> list, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpResponse> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestBuilder $this$submitFormWithBinaryData_u24lambda_u242$iv = httpRequestBuilder;
        $this$submitFormWithBinaryData_u24lambda_u242$iv.setMethod(HttpMethod.INSTANCE.getPost());
        Object body$iv$iv = new MultiPartFormDataContent(list, null, null, 6, null);
        if (body$iv$iv instanceof OutgoingContent) {
            $this$submitFormWithBinaryData_u24lambda_u242$iv.setBody(body$iv$iv);
            $this$submitFormWithBinaryData_u24lambda_u242$iv.setBodyType(null);
        } else {
            $this$submitFormWithBinaryData_u24lambda_u242$iv.setBody(body$iv$iv);
            KType kType$iv$iv$iv = Reflection.typeOf(MultiPartFormDataContent.class);
            Type reifiedType$iv$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv$iv);
            $this$submitFormWithBinaryData_u24lambda_u242$iv.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv$iv, Reflection.getOrCreateKotlinClass(MultiPartFormDataContent.class), kType$iv$iv$iv));
        }
        HttpRequestBuilder $this$submitFormWithBinaryData_u24lambda_u243 = $this$submitFormWithBinaryData_u24lambda_u242$iv;
        HttpRequestKt.url($this$submitFormWithBinaryData_u24lambda_u243, url);
        function1.invoke($this$submitFormWithBinaryData_u24lambda_u243);
        Unit unit = Unit.INSTANCE;
        Unit unit2 = Unit.INSTANCE;
        HttpRequestBuilder builder$iv$iv$iv = httpRequestBuilder;
        return new HttpStatement(builder$iv$iv$iv, $this$submitFormWithBinaryData).execute(continuation);
    }

    public static /* synthetic */ Object prepareForm$default(HttpClient $this$prepareForm_u24default, Parameters formParameters, boolean encodeInQuery, Function1 block, Continuation $completion, int i, Object obj) {
        Parameters formParameters2 = (i & 1) != 0 ? Parameters.INSTANCE.getEmpty() : formParameters;
        boolean encodeInQuery2 = (i & 2) != 0 ? false : encodeInQuery;
        FormBuildersKt$prepareForm$2 block2 = (i & 4) != 0 ? new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.forms.FormBuildersKt$prepareForm$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                invoke2(httpRequestBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HttpRequestBuilder $this$null) {
                Intrinsics.checkNotNullParameter($this$null, "$this$null");
            }
        } : block;
        HttpRequestBuilder $this$prepareForm_u24lambda_u244 = new HttpRequestBuilder();
        if (encodeInQuery2) {
            $this$prepareForm_u24lambda_u244.setMethod(HttpMethod.INSTANCE.getGet());
            $this$prepareForm_u24lambda_u244.getUrl().getParameters().appendAll(formParameters2);
        } else {
            $this$prepareForm_u24lambda_u244.setMethod(HttpMethod.INSTANCE.getPost());
            Object body$iv = new FormDataContent(formParameters2);
            if (body$iv instanceof OutgoingContent) {
                $this$prepareForm_u24lambda_u244.setBody(body$iv);
                $this$prepareForm_u24lambda_u244.setBodyType(null);
            } else {
                $this$prepareForm_u24lambda_u244.setBody(body$iv);
                KType kType$iv$iv = Reflection.typeOf(FormDataContent.class);
                Type reifiedType$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv);
                $this$prepareForm_u24lambda_u244.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv, Reflection.getOrCreateKotlinClass(FormDataContent.class), kType$iv$iv));
            }
        }
        block2.invoke($this$prepareForm_u24lambda_u244);
        return new HttpStatement($this$prepareForm_u24lambda_u244, $this$prepareForm_u24default);
    }

    public static final Object prepareForm(HttpClient $this$prepareForm, Parameters formParameters, boolean encodeInQuery, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpStatement> continuation) {
        HttpRequestBuilder $this$prepareForm_u24lambda_u244 = new HttpRequestBuilder();
        if (encodeInQuery) {
            $this$prepareForm_u24lambda_u244.setMethod(HttpMethod.INSTANCE.getGet());
            $this$prepareForm_u24lambda_u244.getUrl().getParameters().appendAll(formParameters);
        } else {
            $this$prepareForm_u24lambda_u244.setMethod(HttpMethod.INSTANCE.getPost());
            Object body$iv = new FormDataContent(formParameters);
            if (body$iv instanceof OutgoingContent) {
                $this$prepareForm_u24lambda_u244.setBody(body$iv);
                $this$prepareForm_u24lambda_u244.setBodyType(null);
            } else {
                $this$prepareForm_u24lambda_u244.setBody(body$iv);
                KType kType$iv$iv = Reflection.typeOf(FormDataContent.class);
                Type reifiedType$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv);
                $this$prepareForm_u24lambda_u244.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv, Reflection.getOrCreateKotlinClass(FormDataContent.class), kType$iv$iv));
            }
        }
        function1.invoke($this$prepareForm_u24lambda_u244);
        return new HttpStatement($this$prepareForm_u24lambda_u244, $this$prepareForm);
    }

    private static final Object prepareForm$$forInline(HttpClient $this$prepareForm, Parameters formParameters, boolean encodeInQuery, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpStatement> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestBuilder $this$prepareForm_u24lambda_u244 = httpRequestBuilder;
        if (encodeInQuery) {
            $this$prepareForm_u24lambda_u244.setMethod(HttpMethod.INSTANCE.getGet());
            $this$prepareForm_u24lambda_u244.getUrl().getParameters().appendAll(formParameters);
        } else {
            $this$prepareForm_u24lambda_u244.setMethod(HttpMethod.INSTANCE.getPost());
            Object body$iv = new FormDataContent(formParameters);
            if (body$iv instanceof OutgoingContent) {
                $this$prepareForm_u24lambda_u244.setBody(body$iv);
                $this$prepareForm_u24lambda_u244.setBodyType(null);
            } else {
                $this$prepareForm_u24lambda_u244.setBody(body$iv);
                KType kType$iv$iv = Reflection.typeOf(FormDataContent.class);
                Type reifiedType$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv);
                $this$prepareForm_u24lambda_u244.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv, Reflection.getOrCreateKotlinClass(FormDataContent.class), kType$iv$iv));
            }
        }
        function1.invoke($this$prepareForm_u24lambda_u244);
        Unit unit = Unit.INSTANCE;
        HttpRequestBuilder builder$iv$iv = httpRequestBuilder;
        return new HttpStatement(builder$iv$iv, $this$prepareForm);
    }

    public static final Object prepareForm(HttpClient $this$prepareForm, String url, Parameters formParameters, boolean encodeInQuery, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpStatement> continuation) {
        HttpRequestBuilder $this$prepareForm_u24lambda_u244$iv = new HttpRequestBuilder();
        if (encodeInQuery) {
            $this$prepareForm_u24lambda_u244$iv.setMethod(HttpMethod.INSTANCE.getGet());
            $this$prepareForm_u24lambda_u244$iv.getUrl().getParameters().appendAll(formParameters);
        } else {
            $this$prepareForm_u24lambda_u244$iv.setMethod(HttpMethod.INSTANCE.getPost());
            Object body$iv$iv = new FormDataContent(formParameters);
            if (body$iv$iv instanceof OutgoingContent) {
                $this$prepareForm_u24lambda_u244$iv.setBody(body$iv$iv);
                $this$prepareForm_u24lambda_u244$iv.setBodyType(null);
            } else {
                $this$prepareForm_u24lambda_u244$iv.setBody(body$iv$iv);
                KType kType$iv$iv$iv = Reflection.typeOf(FormDataContent.class);
                Type reifiedType$iv$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv$iv);
                $this$prepareForm_u24lambda_u244$iv.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv$iv, Reflection.getOrCreateKotlinClass(FormDataContent.class), kType$iv$iv$iv));
            }
        }
        HttpRequestKt.url($this$prepareForm_u24lambda_u244$iv, url);
        function1.invoke($this$prepareForm_u24lambda_u244$iv);
        return new HttpStatement($this$prepareForm_u24lambda_u244$iv, $this$prepareForm);
    }

    public static /* synthetic */ Object prepareFormWithBinaryData$default(HttpClient $this$prepareFormWithBinaryData_u24default, List formData, Function1 block, Continuation $completion, int i, Object obj) {
        FormBuildersKt$prepareFormWithBinaryData$2 block2;
        if ((i & 2) == 0) {
            block2 = block;
        } else {
            block2 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.forms.FormBuildersKt$prepareFormWithBinaryData$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        HttpRequestBuilder $this$prepareFormWithBinaryData_u24lambda_u246 = new HttpRequestBuilder();
        $this$prepareFormWithBinaryData_u24lambda_u246.setMethod(HttpMethod.INSTANCE.getPost());
        Object body$iv = new MultiPartFormDataContent(formData, null, null, 6, null);
        if (body$iv instanceof OutgoingContent) {
            $this$prepareFormWithBinaryData_u24lambda_u246.setBody(body$iv);
            $this$prepareFormWithBinaryData_u24lambda_u246.setBodyType(null);
        } else {
            $this$prepareFormWithBinaryData_u24lambda_u246.setBody(body$iv);
            KType kType$iv$iv = Reflection.typeOf(MultiPartFormDataContent.class);
            Type reifiedType$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv);
            $this$prepareFormWithBinaryData_u24lambda_u246.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv, Reflection.getOrCreateKotlinClass(MultiPartFormDataContent.class), kType$iv$iv));
        }
        block2.invoke($this$prepareFormWithBinaryData_u24lambda_u246);
        return new HttpStatement($this$prepareFormWithBinaryData_u24lambda_u246, $this$prepareFormWithBinaryData_u24default);
    }

    public static final Object prepareFormWithBinaryData(HttpClient $this$prepareFormWithBinaryData, List<? extends PartData> list, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpStatement> continuation) {
        HttpRequestBuilder $this$prepareFormWithBinaryData_u24lambda_u246 = new HttpRequestBuilder();
        $this$prepareFormWithBinaryData_u24lambda_u246.setMethod(HttpMethod.INSTANCE.getPost());
        Object body$iv = new MultiPartFormDataContent(list, null, null, 6, null);
        if (body$iv instanceof OutgoingContent) {
            $this$prepareFormWithBinaryData_u24lambda_u246.setBody(body$iv);
            $this$prepareFormWithBinaryData_u24lambda_u246.setBodyType(null);
        } else {
            $this$prepareFormWithBinaryData_u24lambda_u246.setBody(body$iv);
            KType kType$iv$iv = Reflection.typeOf(MultiPartFormDataContent.class);
            Type reifiedType$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv);
            $this$prepareFormWithBinaryData_u24lambda_u246.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv, Reflection.getOrCreateKotlinClass(MultiPartFormDataContent.class), kType$iv$iv));
        }
        function1.invoke($this$prepareFormWithBinaryData_u24lambda_u246);
        return new HttpStatement($this$prepareFormWithBinaryData_u24lambda_u246, $this$prepareFormWithBinaryData);
    }

    private static final Object prepareFormWithBinaryData$$forInline(HttpClient $this$prepareFormWithBinaryData, List<? extends PartData> list, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpStatement> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestBuilder $this$prepareFormWithBinaryData_u24lambda_u246 = httpRequestBuilder;
        $this$prepareFormWithBinaryData_u24lambda_u246.setMethod(HttpMethod.INSTANCE.getPost());
        Object body$iv = new MultiPartFormDataContent(list, null, null, 6, null);
        if (body$iv instanceof OutgoingContent) {
            $this$prepareFormWithBinaryData_u24lambda_u246.setBody(body$iv);
            $this$prepareFormWithBinaryData_u24lambda_u246.setBodyType(null);
        } else {
            $this$prepareFormWithBinaryData_u24lambda_u246.setBody(body$iv);
            KType kType$iv$iv = Reflection.typeOf(MultiPartFormDataContent.class);
            Type reifiedType$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv);
            $this$prepareFormWithBinaryData_u24lambda_u246.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv, Reflection.getOrCreateKotlinClass(MultiPartFormDataContent.class), kType$iv$iv));
        }
        function1.invoke($this$prepareFormWithBinaryData_u24lambda_u246);
        Unit unit = Unit.INSTANCE;
        HttpRequestBuilder builder$iv$iv = httpRequestBuilder;
        return new HttpStatement(builder$iv$iv, $this$prepareFormWithBinaryData);
    }

    public static /* synthetic */ Object prepareFormWithBinaryData$default(HttpClient $this$prepareFormWithBinaryData_u24default, String url, List formData, Function1 block, Continuation $completion, int i, Object obj) {
        FormBuildersKt$prepareFormWithBinaryData$5 block2;
        if ((i & 4) == 0) {
            block2 = block;
        } else {
            block2 = new Function1<HttpRequestBuilder, Unit>() { // from class: io.ktor.client.request.forms.FormBuildersKt$prepareFormWithBinaryData$5
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(HttpRequestBuilder httpRequestBuilder) {
                    invoke2(httpRequestBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(HttpRequestBuilder $this$null) {
                    Intrinsics.checkNotNullParameter($this$null, "$this$null");
                }
            };
        }
        HttpRequestBuilder $this$prepareFormWithBinaryData_u24lambda_u246$iv = new HttpRequestBuilder();
        $this$prepareFormWithBinaryData_u24lambda_u246$iv.setMethod(HttpMethod.INSTANCE.getPost());
        Object body$iv$iv = new MultiPartFormDataContent(formData, null, null, 6, null);
        if (body$iv$iv instanceof OutgoingContent) {
            $this$prepareFormWithBinaryData_u24lambda_u246$iv.setBody(body$iv$iv);
            $this$prepareFormWithBinaryData_u24lambda_u246$iv.setBodyType(null);
        } else {
            $this$prepareFormWithBinaryData_u24lambda_u246$iv.setBody(body$iv$iv);
            KType kType$iv$iv$iv = Reflection.typeOf(MultiPartFormDataContent.class);
            Type reifiedType$iv$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv$iv);
            $this$prepareFormWithBinaryData_u24lambda_u246$iv.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv$iv, Reflection.getOrCreateKotlinClass(MultiPartFormDataContent.class), kType$iv$iv$iv));
        }
        HttpRequestKt.url($this$prepareFormWithBinaryData_u24lambda_u246$iv, url);
        block2.invoke($this$prepareFormWithBinaryData_u24lambda_u246$iv);
        return new HttpStatement($this$prepareFormWithBinaryData_u24lambda_u246$iv, $this$prepareFormWithBinaryData_u24default);
    }

    public static final Object prepareFormWithBinaryData(HttpClient $this$prepareFormWithBinaryData, String url, List<? extends PartData> list, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpStatement> continuation) {
        HttpRequestBuilder $this$prepareFormWithBinaryData_u24lambda_u246$iv = new HttpRequestBuilder();
        $this$prepareFormWithBinaryData_u24lambda_u246$iv.setMethod(HttpMethod.INSTANCE.getPost());
        Object body$iv$iv = new MultiPartFormDataContent(list, null, null, 6, null);
        if (body$iv$iv instanceof OutgoingContent) {
            $this$prepareFormWithBinaryData_u24lambda_u246$iv.setBody(body$iv$iv);
            $this$prepareFormWithBinaryData_u24lambda_u246$iv.setBodyType(null);
        } else {
            $this$prepareFormWithBinaryData_u24lambda_u246$iv.setBody(body$iv$iv);
            KType kType$iv$iv$iv = Reflection.typeOf(MultiPartFormDataContent.class);
            Type reifiedType$iv$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv$iv);
            $this$prepareFormWithBinaryData_u24lambda_u246$iv.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv$iv, Reflection.getOrCreateKotlinClass(MultiPartFormDataContent.class), kType$iv$iv$iv));
        }
        HttpRequestKt.url($this$prepareFormWithBinaryData_u24lambda_u246$iv, url);
        function1.invoke($this$prepareFormWithBinaryData_u24lambda_u246$iv);
        return new HttpStatement($this$prepareFormWithBinaryData_u24lambda_u246$iv, $this$prepareFormWithBinaryData);
    }

    private static final Object prepareFormWithBinaryData$$forInline(HttpClient $this$prepareFormWithBinaryData, String url, List<? extends PartData> list, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super HttpStatement> continuation) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestBuilder $this$prepareFormWithBinaryData_u24lambda_u246$iv = httpRequestBuilder;
        $this$prepareFormWithBinaryData_u24lambda_u246$iv.setMethod(HttpMethod.INSTANCE.getPost());
        Object body$iv$iv = new MultiPartFormDataContent(list, null, null, 6, null);
        if (body$iv$iv instanceof OutgoingContent) {
            $this$prepareFormWithBinaryData_u24lambda_u246$iv.setBody(body$iv$iv);
            $this$prepareFormWithBinaryData_u24lambda_u246$iv.setBodyType(null);
        } else {
            $this$prepareFormWithBinaryData_u24lambda_u246$iv.setBody(body$iv$iv);
            KType kType$iv$iv$iv = Reflection.typeOf(MultiPartFormDataContent.class);
            Type reifiedType$iv$iv$iv = TypesJVMKt.getJavaType(kType$iv$iv$iv);
            $this$prepareFormWithBinaryData_u24lambda_u246$iv.setBodyType(TypeInfoJvmKt.typeInfoImpl(reifiedType$iv$iv$iv, Reflection.getOrCreateKotlinClass(MultiPartFormDataContent.class), kType$iv$iv$iv));
        }
        HttpRequestBuilder $this$prepareFormWithBinaryData_u24lambda_u247 = $this$prepareFormWithBinaryData_u24lambda_u246$iv;
        HttpRequestKt.url($this$prepareFormWithBinaryData_u24lambda_u247, url);
        function1.invoke($this$prepareFormWithBinaryData_u24lambda_u247);
        Unit unit = Unit.INSTANCE;
        Unit unit2 = Unit.INSTANCE;
        HttpRequestBuilder builder$iv$iv$iv = httpRequestBuilder;
        return new HttpStatement(builder$iv$iv$iv, $this$prepareFormWithBinaryData);
    }
}
