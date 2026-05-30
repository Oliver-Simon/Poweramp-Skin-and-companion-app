package io.ktor.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlatformUtilsJvm.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0002\u001a\u00020\u0003*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005\"\u0018\u0010\u0006\u001a\u00020\u0003*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005\"\u0015\u0010\u0007\u001a\u00020\b*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"DEVELOPMENT_MODE_KEY", "", "isDevelopmentMode", "", "Lio/ktor/util/PlatformUtils;", "(Lio/ktor/util/PlatformUtils;)Z", "isNewMemoryModel", "platform", "Lio/ktor/util/Platform;", "getPlatform", "(Lio/ktor/util/PlatformUtils;)Lio/ktor/util/Platform;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class PlatformUtilsJvmKt {
    private static final String DEVELOPMENT_MODE_KEY = "io.ktor.development";

    public static final Platform getPlatform(PlatformUtils $this$platform) {
        Intrinsics.checkNotNullParameter($this$platform, "<this>");
        return Platform.Jvm;
    }

    public static final boolean isDevelopmentMode(PlatformUtils $this$isDevelopmentMode) {
        Intrinsics.checkNotNullParameter($this$isDevelopmentMode, "<this>");
        String property = System.getProperty(DEVELOPMENT_MODE_KEY);
        return property != null && Boolean.parseBoolean(property);
    }

    public static final boolean isNewMemoryModel(PlatformUtils $this$isNewMemoryModel) {
        Intrinsics.checkNotNullParameter($this$isNewMemoryModel, "<this>");
        return true;
    }
}
