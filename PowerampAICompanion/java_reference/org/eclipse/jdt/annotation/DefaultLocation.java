package org.eclipse.jdt.annotation;

/* loaded from: classes9.dex */
public enum DefaultLocation {
    PARAMETER,
    RETURN_TYPE,
    FIELD,
    TYPE_PARAMETER,
    TYPE_BOUND,
    TYPE_ARGUMENT,
    ARRAY_CONTENTS;

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static DefaultLocation[] valuesCustom() {
        DefaultLocation[] valuesCustom = values();
        int length = valuesCustom.length;
        DefaultLocation[] defaultLocationArr = new DefaultLocation[length];
        System.arraycopy(valuesCustom, 0, defaultLocationArr, 0, length);
        return defaultLocationArr;
    }
}
