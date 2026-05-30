package io.ktor.http;

import io.ktor.http.HttpStatusCode;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HttpStatusCode.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0000\u001a\n\u0010\t\u001a\u00020\n*\u00020\u0001\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"ExceptionFailed", "Lio/ktor/http/HttpStatusCode;", "Lio/ktor/http/HttpStatusCode$Companion;", "getExceptionFailed$annotations", "(Lio/ktor/http/HttpStatusCode$Companion;)V", "getExceptionFailed", "(Lio/ktor/http/HttpStatusCode$Companion;)Lio/ktor/http/HttpStatusCode;", "allStatusCodes", "", "isSuccess", "", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class HttpStatusCodeKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use ExpectationFailed instead", replaceWith = @ReplaceWith(expression = "ExpectationFailed", imports = {"io.ktor.http.HttpStatusCode.Companion.ExpectationFailed"}))
    public static /* synthetic */ void getExceptionFailed$annotations(HttpStatusCode.Companion companion) {
    }

    public static final HttpStatusCode getExceptionFailed(HttpStatusCode.Companion $this$ExceptionFailed) {
        Intrinsics.checkNotNullParameter($this$ExceptionFailed, "<this>");
        return $this$ExceptionFailed.getExpectationFailed();
    }

    public static final List<HttpStatusCode> allStatusCodes() {
        return CollectionsKt.listOf((Object[]) new HttpStatusCode[]{HttpStatusCode.INSTANCE.getContinue(), HttpStatusCode.INSTANCE.getSwitchingProtocols(), HttpStatusCode.INSTANCE.getProcessing(), HttpStatusCode.INSTANCE.getOK(), HttpStatusCode.INSTANCE.getCreated(), HttpStatusCode.INSTANCE.getAccepted(), HttpStatusCode.INSTANCE.getNonAuthoritativeInformation(), HttpStatusCode.INSTANCE.getNoContent(), HttpStatusCode.INSTANCE.getResetContent(), HttpStatusCode.INSTANCE.getPartialContent(), HttpStatusCode.INSTANCE.getMultiStatus(), HttpStatusCode.INSTANCE.getMultipleChoices(), HttpStatusCode.INSTANCE.getMovedPermanently(), HttpStatusCode.INSTANCE.getFound(), HttpStatusCode.INSTANCE.getSeeOther(), HttpStatusCode.INSTANCE.getNotModified(), HttpStatusCode.INSTANCE.getUseProxy(), HttpStatusCode.INSTANCE.getSwitchProxy(), HttpStatusCode.INSTANCE.getTemporaryRedirect(), HttpStatusCode.INSTANCE.getPermanentRedirect(), HttpStatusCode.INSTANCE.getBadRequest(), HttpStatusCode.INSTANCE.getUnauthorized(), HttpStatusCode.INSTANCE.getPaymentRequired(), HttpStatusCode.INSTANCE.getForbidden(), HttpStatusCode.INSTANCE.getNotFound(), HttpStatusCode.INSTANCE.getMethodNotAllowed(), HttpStatusCode.INSTANCE.getNotAcceptable(), HttpStatusCode.INSTANCE.getProxyAuthenticationRequired(), HttpStatusCode.INSTANCE.getRequestTimeout(), HttpStatusCode.INSTANCE.getConflict(), HttpStatusCode.INSTANCE.getGone(), HttpStatusCode.INSTANCE.getLengthRequired(), HttpStatusCode.INSTANCE.getPreconditionFailed(), HttpStatusCode.INSTANCE.getPayloadTooLarge(), HttpStatusCode.INSTANCE.getRequestURITooLong(), HttpStatusCode.INSTANCE.getUnsupportedMediaType(), HttpStatusCode.INSTANCE.getRequestedRangeNotSatisfiable(), HttpStatusCode.INSTANCE.getExpectationFailed(), HttpStatusCode.INSTANCE.getUnprocessableEntity(), HttpStatusCode.INSTANCE.getLocked(), HttpStatusCode.INSTANCE.getFailedDependency(), HttpStatusCode.INSTANCE.getTooEarly(), HttpStatusCode.INSTANCE.getUpgradeRequired(), HttpStatusCode.INSTANCE.getTooManyRequests(), HttpStatusCode.INSTANCE.getRequestHeaderFieldTooLarge(), HttpStatusCode.INSTANCE.getInternalServerError(), HttpStatusCode.INSTANCE.getNotImplemented(), HttpStatusCode.INSTANCE.getBadGateway(), HttpStatusCode.INSTANCE.getServiceUnavailable(), HttpStatusCode.INSTANCE.getGatewayTimeout(), HttpStatusCode.INSTANCE.getVersionNotSupported(), HttpStatusCode.INSTANCE.getVariantAlsoNegotiates(), HttpStatusCode.INSTANCE.getInsufficientStorage()});
    }

    public static final boolean isSuccess(HttpStatusCode $this$isSuccess) {
        Intrinsics.checkNotNullParameter($this$isSuccess, "<this>");
        int value = $this$isSuccess.getValue();
        return 200 <= value && value < 300;
    }
}
