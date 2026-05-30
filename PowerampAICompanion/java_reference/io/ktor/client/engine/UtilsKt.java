package io.ktor.client.engine;

import io.ktor.client.utils.HeadersKt;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.InternalAPI;
import io.ktor.util.PlatformUtils;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;

/* compiled from: Utils.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u001a\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0080Hø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\u0011\u0010\r\u001a\u00020\u000eH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001aP\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u001426\u0010\u0015\u001a2\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\t0\u0016H\u0007\u001a\b\u0010\u001b\u001a\u00020\u001cH\u0002\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\u0003\u001a\u00020\u00028\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"DATE_HEADERS", "", "", "KTOR_DEFAULT_USER_AGENT", "getKTOR_DEFAULT_USER_AGENT$annotations", "()V", "getKTOR_DEFAULT_USER_AGENT", "()Ljava/lang/String;", "attachToUserJob", "", "callJob", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mergeHeaders", "requestHeaders", "Lio/ktor/http/Headers;", "content", "Lio/ktor/http/content/OutgoingContent;", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "key", "value", "needUserAgent", "", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class UtilsKt {
    private static final String KTOR_DEFAULT_USER_AGENT = "Ktor client";
    private static final Set<String> DATE_HEADERS = SetsKt.setOf((Object[]) new String[]{HttpHeaders.INSTANCE.getDate(), HttpHeaders.INSTANCE.getExpires(), HttpHeaders.INSTANCE.getLastModified(), HttpHeaders.INSTANCE.getIfModifiedSince(), HttpHeaders.INSTANCE.getIfUnmodifiedSince()});

    @InternalAPI
    public static /* synthetic */ void getKTOR_DEFAULT_USER_AGENT$annotations() {
    }

    public static final String getKTOR_DEFAULT_USER_AGENT() {
        return KTOR_DEFAULT_USER_AGENT;
    }

    @InternalAPI
    public static final void mergeHeaders(final Headers requestHeaders, final OutgoingContent content, final Function2<? super String, ? super String, Unit> block) {
        String type;
        String length;
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(block, "block");
        HeadersKt.buildHeaders(new Function1<HeadersBuilder, Unit>() { // from class: io.ktor.client.engine.UtilsKt$mergeHeaders$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(HeadersBuilder headersBuilder) {
                invoke2(headersBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(HeadersBuilder buildHeaders) {
                Intrinsics.checkNotNullParameter(buildHeaders, "$this$buildHeaders");
                buildHeaders.appendAll(Headers.this);
                buildHeaders.appendAll(content.getHeaders());
            }
        }).forEach(new Function2<String, List<? extends String>, Unit>() { // from class: io.ktor.client.engine.UtilsKt$mergeHeaders$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, List<? extends String> list) {
                invoke2(str, (List<String>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String key, List<String> values) {
                Set set;
                Intrinsics.checkNotNullParameter(key, "key");
                Intrinsics.checkNotNullParameter(values, "values");
                if (Intrinsics.areEqual(HttpHeaders.INSTANCE.getContentLength(), key) || Intrinsics.areEqual(HttpHeaders.INSTANCE.getContentType(), key)) {
                    return;
                }
                set = UtilsKt.DATE_HEADERS;
                if (set.contains(key)) {
                    List<String> $this$forEach$iv = values;
                    Function2<String, String, Unit> function2 = block;
                    for (Object element$iv : $this$forEach$iv) {
                        String value = (String) element$iv;
                        function2.invoke(key, value);
                    }
                    return;
                }
                block.invoke(key, CollectionsKt.joinToString$default(values, ",", null, null, 0, null, null, 62, null));
            }
        });
        boolean missingAgent = requestHeaders.get(HttpHeaders.INSTANCE.getUserAgent()) == null && content.getHeaders().get(HttpHeaders.INSTANCE.getUserAgent()) == null;
        if (missingAgent && needUserAgent()) {
            block.invoke(HttpHeaders.INSTANCE.getUserAgent(), KTOR_DEFAULT_USER_AGENT);
        }
        ContentType contentType = content.getContentType();
        if ((contentType == null || (type = contentType.toString()) == null) && (type = content.getHeaders().get(HttpHeaders.INSTANCE.getContentType())) == null) {
            type = requestHeaders.get(HttpHeaders.INSTANCE.getContentType());
        }
        Long contentLength = content.getContentLength();
        if ((contentLength == null || (length = contentLength.toString()) == null) && (length = content.getHeaders().get(HttpHeaders.INSTANCE.getContentLength())) == null) {
            length = requestHeaders.get(HttpHeaders.INSTANCE.getContentLength());
        }
        if (type != null) {
            String it = type;
            block.invoke(HttpHeaders.INSTANCE.getContentType(), it);
        }
        if (length != null) {
            String it2 = length;
            block.invoke(HttpHeaders.INSTANCE.getContentLength(), it2);
        }
    }

    @InternalAPI
    public static final Object callContext(Continuation<? super CoroutineContext> continuation) {
        CoroutineContext.Element element = continuation.get$context().get(KtorCallContextElement.INSTANCE);
        Intrinsics.checkNotNull(element);
        return ((KtorCallContextElement) element).getCallContext();
    }

    public static final Object attachToUserJob(Job callJob, Continuation<? super Unit> continuation) {
        Job userJob = (Job) continuation.get$context().get(Job.INSTANCE);
        if (userJob == null) {
            return Unit.INSTANCE;
        }
        DisposableHandle cleanupHandler = Job.DefaultImpls.invokeOnCompletion$default(userJob, true, false, new UtilsKt$attachToUserJob$cleanupHandler$1(callJob), 2, null);
        callJob.invokeOnCompletion(new UtilsKt$attachToUserJob$2(cleanupHandler));
        return Unit.INSTANCE;
    }

    private static final Object attachToUserJob$$forInline(Job callJob, Continuation<? super Unit> continuation) {
        Continuation continuation2 = null;
        continuation2.get$context();
        throw null;
    }

    private static final boolean needUserAgent() {
        return !PlatformUtils.INSTANCE.getIS_BROWSER();
    }
}
