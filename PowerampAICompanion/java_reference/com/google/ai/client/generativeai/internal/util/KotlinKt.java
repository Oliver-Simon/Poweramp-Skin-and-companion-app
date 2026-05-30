package com.google.ai.client.generativeai.internal.util;

import androidx.exifinterface.media.ExifInterface;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: kotlin.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\b\u0003\n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0000\u001a\n \u0002*\u0004\u0018\u0001H\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0003*\u00020\u0004H\u0080\b¢\u0006\u0002\u0010\u0005\u001a\u0014\u0010\u0006\u001a\u00060\u0007j\u0002`\b*\u00060\u0007j\u0002`\bH\u0000¨\u0006\t"}, d2 = {"getAnnotation", ExifInterface.GPS_DIRECTION_TRUE, "kotlin.jvm.PlatformType", "", "Ljava/lang/reflect/Field;", "(Ljava/lang/reflect/Field;)Ljava/lang/annotation/Annotation;", "removeLast", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "generativeai_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class KotlinKt {
    public static final StringBuilder removeLast(StringBuilder $this$removeLast) {
        Intrinsics.checkNotNullParameter($this$removeLast, "<this>");
        if ($this$removeLast.length() == 0) {
            throw new IndexOutOfBoundsException("StringBuilder is empty.");
        }
        StringBuilder deleteCharAt = $this$removeLast.deleteCharAt($this$removeLast.length() - 1);
        Intrinsics.checkNotNullExpressionValue(deleteCharAt, "deleteCharAt(length - 1)");
        return deleteCharAt;
    }

    public static final /* synthetic */ <T extends Annotation> T getAnnotation(Field field) {
        Intrinsics.checkNotNullParameter(field, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) field.getAnnotation(Annotation.class);
    }
}
