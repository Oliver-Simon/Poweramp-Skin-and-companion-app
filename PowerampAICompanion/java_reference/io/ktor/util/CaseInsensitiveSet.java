package io.ktor.util;

import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableSet;

/* compiled from: CaseInsensitiveSet.kt */
@InternalAPI
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010)\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\u0016\u0010\u0010\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0011\u0010\u0015\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\u0016\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012H\u0016J\b\u0010\u0017\u001a\u00020\tH\u0016J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0019H\u0096\u0002J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\u0016\u0010\u001b\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012H\u0016J\u0016\u0010\u001c\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012H\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u001d"}, d2 = {"Lio/ktor/util/CaseInsensitiveSet;", "", "", "initial", "", "(Ljava/lang/Iterable;)V", "()V", "backingMap", "Lio/ktor/util/CaseInsensitiveMap;", "", ContentDisposition.Parameters.Size, "", "getSize", "()I", "add", "element", "addAll", "elements", "", "clear", "", "contains", "containsAll", "isEmpty", "iterator", "", "remove", "removeAll", "retainAll", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class CaseInsensitiveSet implements Set<String>, KMutableSet {
    private final CaseInsensitiveMap<Boolean> backingMap;

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return (T[]) CollectionToArray.toArray(this, array);
    }

    public CaseInsensitiveSet() {
        this.backingMap = new CaseInsensitiveMap<>();
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ boolean contains(Object element) {
        if (element instanceof String) {
            return contains((String) element);
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ boolean remove(Object element) {
        if (element instanceof String) {
            return remove((String) element);
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CaseInsensitiveSet(Iterable<String> initial) {
        this();
        Intrinsics.checkNotNullParameter(initial, "initial");
        kotlin.collections.CollectionsKt.addAll(this, initial);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(String element) {
        Intrinsics.checkNotNullParameter(element, "element");
        if (this.backingMap.containsKey((Object) element)) {
            return false;
        }
        this.backingMap.put((CaseInsensitiveMap<Boolean>) element, (String) true);
        return true;
    }

    public int getSize() {
        return this.backingMap.size();
    }

    public boolean remove(String element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return Intrinsics.areEqual((Object) this.backingMap.remove((Object) element), (Object) true);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends String> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        boolean added = false;
        for (String element : elements) {
            if (add(element)) {
                added = true;
            }
        }
        return added;
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.backingMap.clear();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.backingMap.keySet().removeAll(elements);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.backingMap.keySet().retainAll(elements);
    }

    public boolean contains(String element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.backingMap.containsKey((Object) element);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<? extends Object> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.backingMap.keySet().containsAll(elements);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.backingMap.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<String> iterator() {
        return this.backingMap.keySet().iterator();
    }
}
