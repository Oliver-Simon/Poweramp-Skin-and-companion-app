package io.ktor.websocket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: WebSocketExtensionHeader.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"parseWebSocketExtensions", "", "Lio/ktor/websocket/WebSocketExtensionHeader;", "value", "", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class WebSocketExtensionHeaderKt {
    public static final List<WebSocketExtensionHeader> parseWebSocketExtensions(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        int i = 1;
        char c = 0;
        Iterable $this$map$iv = StringsKt.split$default((CharSequence) value, new String[]{","}, false, 0, 6, (Object) null);
        int i2 = 10;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            String[] strArr = new String[i];
            strArr[c] = ";";
            List extension = StringsKt.split$default((CharSequence) it, strArr, false, 0, 6, (Object) null);
            String name = StringsKt.trim((CharSequence) CollectionsKt.first(extension)).toString();
            Iterable $this$map$iv2 = CollectionsKt.drop(extension, i);
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, i2));
            for (Object item$iv$iv2 : $this$map$iv2) {
                String it2 = (String) item$iv$iv2;
                destination$iv$iv2.add(StringsKt.trim((CharSequence) it2).toString());
            }
            List parameters = (List) destination$iv$iv2;
            destination$iv$iv.add(new WebSocketExtensionHeader(name, parameters));
            i = 1;
            i2 = 10;
            c = 0;
        }
        return (List) destination$iv$iv;
    }
}
