package io.ktor.http;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentType;
import io.ktor.util.CharsetKt;
import io.ktor.util.CollectionsKt;
import io.ktor.util.TextKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: FileContentType.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0012\u0010\f\u001a\u00020\u0004*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002\u001a\u0012\u0010\u000f\u001a\u00020\u0004*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0002\u001a\u0010\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0003*\u00020\u0004\u001a\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0002\u001a\u0018\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0002\u001a<\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u0002H\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170\u00030\u0001\"\u0004\b\u0000\u0010\u0016\"\u0004\b\u0001\u0010\u0017*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u00170\u00190\u0018H\u0000\u001a\u0012\u0010\u001a\u001a\u00020\u0004*\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000\u001a\f\u0010\u001b\u001a\u00020\u0004*\u00020\u0002H\u0000\"-\u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\"-\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\n\u0010\u0006¨\u0006\u001c"}, d2 = {"contentTypesByExtensions", "", "", "", "Lio/ktor/http/ContentType;", "getContentTypesByExtensions", "()Ljava/util/Map;", "contentTypesByExtensions$delegate", "Lkotlin/Lazy;", "extensionsByContentType", "getExtensionsByContentType", "extensionsByContentType$delegate", "defaultForFileExtension", "Lio/ktor/http/ContentType$Companion;", "extension", "defaultForFilePath", "path", "fileExtensions", "fromFileExtension", "ext", "fromFilePath", "groupByPairs", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "Lkotlin/sequences/Sequence;", "Lkotlin/Pair;", "selectDefault", "toContentType", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class FileContentTypeKt {
    private static final Lazy contentTypesByExtensions$delegate = LazyKt.lazy(new Function0<Map<String, List<? extends ContentType>>>() { // from class: io.ktor.http.FileContentTypeKt$contentTypesByExtensions$2
        @Override // kotlin.jvm.functions.Function0
        public final Map<String, List<? extends ContentType>> invoke() {
            Map $this$invoke_u24lambda_u240 = CollectionsKt.caseInsensitiveMap();
            $this$invoke_u24lambda_u240.putAll(FileContentTypeKt.groupByPairs(kotlin.collections.CollectionsKt.asSequence(MimesKt.getMimes())));
            return $this$invoke_u24lambda_u240;
        }
    });
    private static final Lazy extensionsByContentType$delegate = LazyKt.lazy(new Function0<Map<ContentType, ? extends List<? extends String>>>() { // from class: io.ktor.http.FileContentTypeKt$extensionsByContentType$2
        @Override // kotlin.jvm.functions.Function0
        public final Map<ContentType, ? extends List<? extends String>> invoke() {
            return FileContentTypeKt.groupByPairs(SequencesKt.map(kotlin.collections.CollectionsKt.asSequence(MimesKt.getMimes()), new Function1<Pair<? extends String, ? extends ContentType>, Pair<? extends ContentType, ? extends String>>() { // from class: io.ktor.http.FileContentTypeKt$extensionsByContentType$2.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Pair<? extends ContentType, ? extends String> invoke(Pair<? extends String, ? extends ContentType> pair) {
                    return invoke2((Pair<String, ContentType>) pair);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final Pair<ContentType, String> invoke2(Pair<String, ContentType> pair) {
                    Intrinsics.checkNotNullParameter(pair, "<name for destructuring parameter 0>");
                    String first = pair.component1();
                    ContentType second = pair.component2();
                    return TuplesKt.to(second, first);
                }
            }));
        }
    });

    public static final ContentType defaultForFileExtension(ContentType.Companion $this$defaultForFileExtension, String extension) {
        Intrinsics.checkNotNullParameter($this$defaultForFileExtension, "<this>");
        Intrinsics.checkNotNullParameter(extension, "extension");
        return selectDefault(fromFileExtension(ContentType.INSTANCE, extension));
    }

    public static final ContentType defaultForFilePath(ContentType.Companion $this$defaultForFilePath, String path) {
        Intrinsics.checkNotNullParameter($this$defaultForFilePath, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        return selectDefault(fromFilePath(ContentType.INSTANCE, path));
    }

    public static final List<ContentType> fromFilePath(ContentType.Companion $this$fromFilePath, String path) {
        Intrinsics.checkNotNullParameter($this$fromFilePath, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        int slashIndex = StringsKt.lastIndexOfAny$default((CharSequence) path, CharsetKt.toCharArray("/\\"), 0, false, 6, (Object) null);
        int index = StringsKt.indexOf$default((CharSequence) path, '.', slashIndex + 1, false, 4, (Object) null);
        if (index == -1) {
            return kotlin.collections.CollectionsKt.emptyList();
        }
        String substring = path.substring(index + 1);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
        return fromFileExtension($this$fromFilePath, substring);
    }

    public static final List<ContentType> fromFileExtension(ContentType.Companion $this$fromFileExtension, String ext) {
        Intrinsics.checkNotNullParameter($this$fromFileExtension, "<this>");
        Intrinsics.checkNotNullParameter(ext, "ext");
        String current = TextKt.toLowerCasePreservingASCIIRules(StringsKt.removePrefix(ext, (CharSequence) "."));
        while (true) {
            if (current.length() > 0) {
                List type = getContentTypesByExtensions().get(current);
                if (type == null) {
                    current = StringsKt.substringAfter(current, ".", "");
                } else {
                    return type;
                }
            } else {
                return kotlin.collections.CollectionsKt.emptyList();
            }
        }
    }

    public static final List<String> fileExtensions(ContentType $this$fileExtensions) {
        Intrinsics.checkNotNullParameter($this$fileExtensions, "<this>");
        List<String> list = getExtensionsByContentType().get($this$fileExtensions);
        if (list != null) {
            return list;
        }
        List<String> list2 = getExtensionsByContentType().get($this$fileExtensions.withoutParameters());
        return list2 == null ? kotlin.collections.CollectionsKt.emptyList() : list2;
    }

    private static final Map<String, List<ContentType>> getContentTypesByExtensions() {
        return (Map) contentTypesByExtensions$delegate.getValue();
    }

    private static final Map<ContentType, List<String>> getExtensionsByContentType() {
        return (Map) extensionsByContentType$delegate.getValue();
    }

    public static final ContentType selectDefault(List<ContentType> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        ContentType contentType = (ContentType) kotlin.collections.CollectionsKt.firstOrNull((List) list);
        if (contentType == null) {
            contentType = ContentType.Application.INSTANCE.getOctetStream();
        }
        return (Intrinsics.areEqual(contentType.getContentType(), "text") && ContentTypesKt.charset(contentType) == null) ? ContentTypesKt.withCharset(contentType, Charsets.UTF_8) : contentType;
    }

    public static final <A, B> Map<A, List<B>> groupByPairs(Sequence<? extends Pair<? extends A, ? extends B>> sequence) {
        ArrayList answer$iv$iv$iv;
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Map $this$mapValues$iv = new LinkedHashMap();
        for (Pair pair : sequence) {
            Pair it = pair;
            A first = it.getFirst();
            Object value$iv$iv$iv = $this$mapValues$iv.get(first);
            if (value$iv$iv$iv == null) {
                answer$iv$iv$iv = new ArrayList();
                $this$mapValues$iv.put(first, answer$iv$iv$iv);
            } else {
                answer$iv$iv$iv = value$iv$iv$iv;
            }
            List list$iv$iv = (List) answer$iv$iv$iv;
            list$iv$iv.add(pair);
        }
        int $i$f$mapValues = 0;
        Map destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity($this$mapValues$iv.size()));
        Iterable $this$associateByTo$iv$iv$iv = $this$mapValues$iv.entrySet();
        for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
            Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
            Object key = it$iv$iv.getKey();
            Map.Entry e = (Map.Entry) element$iv$iv$iv;
            Iterable $this$map$iv = (Iterable) e.getValue();
            int $i$f$mapValues2 = $i$f$mapValues;
            Collection destination$iv$iv2 = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Pair it2 = (Pair) item$iv$iv;
                destination$iv$iv2.add(it2.getSecond());
            }
            destination$iv$iv.put(key, (List) destination$iv$iv2);
            $i$f$mapValues = $i$f$mapValues2;
        }
        return destination$iv$iv;
    }

    public static final ContentType toContentType(String $this$toContentType) {
        Intrinsics.checkNotNullParameter($this$toContentType, "<this>");
        try {
            return ContentType.INSTANCE.parse($this$toContentType);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Failed to parse " + $this$toContentType, e);
        }
    }
}
