package io.ktor.util;

import com.maxmpz.poweramp.equalizer.PeqAPI;
import com.maxmpz.poweramp.player.RouterConsts;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StringValues.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B+\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u001a\b\u0002\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005¢\u0006\u0002\u0010\bJ\u0011\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0006H\u0096\u0002J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0016J \u0010\u0010\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u00120\u0011H\u0016J\u0013\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0096\u0002J(\u0010\u0016\u001a\u00020\u00172\u001e\u0010\u0018\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0007\u0012\u0004\u0012\u00020\u00170\u0019H\u0016J\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u0006H\u0096\u0002J\u0018\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00072\u0006\u0010\u000e\u001a\u00020\u0006H\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0003H\u0016J\u0018\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00072\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060\u0011H\u0016J\b\u0010!\u001a\u00020\u0006H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR&\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\""}, d2 = {"Lio/ktor/util/StringValuesImpl;", "Lio/ktor/util/StringValues;", "caseInsensitiveName", "", "values", "", "", "", "(ZLjava/util/Map;)V", "getCaseInsensitiveName", "()Z", "getValues", "()Ljava/util/Map;", "contains", "name", "value", "entries", "", "", "equals", RouterConsts.DEVICE_NAME_OTHER, "", "forEach", "", "body", "Lkotlin/Function2;", "get", "getAll", "hashCode", "", "isEmpty", "listForKey", PeqAPI.EXTRA_NAMES, "toString", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* renamed from: io.ktor.util.StringValuesImpl, reason: from toString */
/* loaded from: classes9.dex */
public class StringValues implements io.ktor.util.StringValues {
    private final boolean caseInsensitiveName;
    private final Map<String, List<String>> values;

    /* JADX WARN: Multi-variable type inference failed */
    public StringValues() {
        this(false, null, 3, 0 == true ? 1 : 0);
    }

    public StringValues(boolean caseInsensitiveName, Map<String, ? extends List<String>> values) {
        Intrinsics.checkNotNullParameter(values, "values");
        this.caseInsensitiveName = caseInsensitiveName;
        Map newMap = this.caseInsensitiveName ? CollectionsKt.caseInsensitiveMap() : new LinkedHashMap();
        for (Map.Entry element$iv : values.entrySet()) {
            String key = element$iv.getKey();
            List<String> value = element$iv.getValue();
            int size = value.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                int it = i;
                arrayList.add(value.get(it));
            }
            newMap.put(key, arrayList);
        }
        this.values = newMap;
    }

    public /* synthetic */ StringValues(boolean z, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? MapsKt.emptyMap() : map);
    }

    @Override // io.ktor.util.StringValues
    public final boolean getCaseInsensitiveName() {
        return this.caseInsensitiveName;
    }

    protected final Map<String, List<String>> getValues() {
        return this.values;
    }

    @Override // io.ktor.util.StringValues
    public String get(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        List<String> listForKey = listForKey(name);
        if (listForKey != null) {
            return (String) kotlin.collections.CollectionsKt.firstOrNull((List) listForKey);
        }
        return null;
    }

    @Override // io.ktor.util.StringValues
    public List<String> getAll(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return listForKey(name);
    }

    @Override // io.ktor.util.StringValues
    public boolean contains(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return listForKey(name) != null;
    }

    @Override // io.ktor.util.StringValues
    public boolean contains(String name, String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        List<String> listForKey = listForKey(name);
        if (listForKey != null) {
            return listForKey.contains(value);
        }
        return false;
    }

    @Override // io.ktor.util.StringValues
    public Set<String> names() {
        return CollectionsJvmKt.unmodifiable(this.values.keySet());
    }

    @Override // io.ktor.util.StringValues
    public boolean isEmpty() {
        return this.values.isEmpty();
    }

    @Override // io.ktor.util.StringValues
    public Set<Map.Entry<String, List<String>>> entries() {
        return CollectionsJvmKt.unmodifiable(this.values.entrySet());
    }

    @Override // io.ktor.util.StringValues
    public void forEach(Function2<? super String, ? super List<String>, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        for (Map.Entry<String, List<String>> entry : this.values.entrySet()) {
            String key = entry.getKey();
            List value = entry.getValue();
            body.invoke(key, value);
        }
    }

    private final List<String> listForKey(String name) {
        return this.values.get(name);
    }

    public String toString() {
        return "StringValues(case=" + (!this.caseInsensitiveName) + ") " + entries();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if ((other instanceof io.ktor.util.StringValues) && this.caseInsensitiveName == ((io.ktor.util.StringValues) other).getCaseInsensitiveName()) {
            return StringValuesKt.access$entriesEquals(entries(), ((io.ktor.util.StringValues) other).entries());
        }
        return false;
    }

    public int hashCode() {
        return StringValuesKt.access$entriesHashCode(entries(), Boolean.hashCode(this.caseInsensitiveName) * 31);
    }
}
