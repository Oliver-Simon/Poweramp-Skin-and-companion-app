package org.eclipse.jdt.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes9.dex */
public @interface NonNullByDefault {
    DefaultLocation[] value() default {DefaultLocation.PARAMETER, DefaultLocation.RETURN_TYPE, DefaultLocation.FIELD, DefaultLocation.TYPE_BOUND, DefaultLocation.TYPE_ARGUMENT};
}
