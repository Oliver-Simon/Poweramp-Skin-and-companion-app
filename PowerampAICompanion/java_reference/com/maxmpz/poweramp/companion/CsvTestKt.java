package com.maxmpz.poweramp.companion;

import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.text.StringsKt;

/* compiled from: CsvTest.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"}, d2 = {"main", "", "PowerampAICompanion_debug"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class CsvTestKt {
    public static final void main() {
        File csvFile = new File("/home/oliver/AndroidStudioProjects/powerampapi/Oliver_Simon (1).csv");
        if (!csvFile.exists()) {
            System.out.println((Object) "CSV not found");
            return;
        }
        List<String> lines = CollectionsKt.take(CollectionsKt.drop(FilesKt.readLines$default(csvFile, null, 1, null), 1), 5);
        for (String line : lines) {
            List parts = StringsKt.split$default((CharSequence) line, new String[]{",\""}, false, 0, 6, (Object) null);
            if (parts.size() >= 4) {
                StringsKt.replace$default((String) parts.get(0), "\"", "", false, 4, (Object) null);
                StringsKt.replace$default((String) parts.get(1), "\"", "", false, 4, (Object) null);
                StringsKt.replace$default((String) parts.get(2), "\"", "", false, 4, (Object) null);
                System.out.println((Object) "CSV -> Artist: '$artist', Track: '$track'");
            }
        }
    }
}
