package io.ktor.util;

import com.maxmpz.poweramp.equalizer.PeqAPI;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* compiled from: StringValues.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\u0010 \n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH&J\u001e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000fH&J\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH&J\u001e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000fH&J\b\u0010\u0011\u001a\u00020\rH&J\b\u0010\u0012\u001a\u00020\u0007H&J\u0011\u0010\u0013\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH¦\u0002J\u0018\u0010\u0013\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&J \u0010\u0014\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00170\u00160\u0015H&J\u0013\u0010\u0018\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\tH¦\u0002J\u0018\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00172\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\u001a\u001a\u00020\u0003H&J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u0015H&J\u0010\u0010\u001c\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\u001c\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&J\b\u0010\u001d\u001a\u00020\u0007H&J\u0019\u0010\u001e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH¦\u0002R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u001f"}, d2 = {"Lio/ktor/util/StringValuesBuilder;", "", "caseInsensitiveName", "", "getCaseInsensitiveName", "()Z", "append", "", "name", "", "value", "appendAll", "stringValues", "Lio/ktor/util/StringValues;", "values", "", "appendMissing", "build", "clear", "contains", "entries", "", "", "", "get", "getAll", "isEmpty", PeqAPI.EXTRA_NAMES, "remove", "removeKeysWithNoEntries", "set", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public interface StringValuesBuilder {
    void append(String name, String value);

    void appendAll(StringValues stringValues);

    void appendAll(String name, Iterable<String> values);

    void appendMissing(StringValues stringValues);

    void appendMissing(String name, Iterable<String> values);

    StringValues build();

    void clear();

    boolean contains(String name);

    boolean contains(String name, String value);

    Set<Map.Entry<String, List<String>>> entries();

    String get(String name);

    List<String> getAll(String name);

    boolean getCaseInsensitiveName();

    boolean isEmpty();

    Set<String> names();

    void remove(String name);

    boolean remove(String name, String value);

    void removeKeysWithNoEntries();

    void set(String name, String value);
}
