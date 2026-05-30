package io.ktor.util;

import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: Path.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0002\b\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0007\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0002\u001a\f\u0010\b\u001a\u00020\t*\u00020\nH\u0002\u001a\f\u0010\u000b\u001a\u00020\t*\u00020\nH\u0002\u001a\n\u0010\f\u001a\u00020\u0001*\u00020\u0001\u001a\f\u0010\r\u001a\u00020\u0001*\u00020\u0001H\u0002¨\u0006\u000e"}, d2 = {"combineSafe", "Ljava/io/File;", "dir", "relativePath", "dropLeadingTopDirs", "", "path", "", "isPathSeparator", "", "", "isPathSeparatorOrDot", "normalizeAndRelativize", "notRooted", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class PathKt {
    public static final File combineSafe(File $this$combineSafe, String relativePath) {
        Intrinsics.checkNotNullParameter($this$combineSafe, "<this>");
        Intrinsics.checkNotNullParameter(relativePath, "relativePath");
        return combineSafe($this$combineSafe, new File(relativePath));
    }

    public static final File normalizeAndRelativize(File $this$normalizeAndRelativize) {
        Intrinsics.checkNotNullParameter($this$normalizeAndRelativize, "<this>");
        return dropLeadingTopDirs(notRooted(FilesKt.normalize($this$normalizeAndRelativize)));
    }

    private static final File combineSafe(File dir, File relativePath) {
        File normalized = normalizeAndRelativize(relativePath);
        if (FilesKt.startsWith(normalized, "..")) {
            throw new IllegalArgumentException("Bad relative path " + relativePath);
        }
        if (normalized.isAbsolute()) {
            throw new IllegalStateException(("Bad relative path " + relativePath).toString());
        }
        return new File(dir, normalized.getPath());
    }

    private static final File notRooted(File $this$notRooted) {
        String str;
        if (!FilesKt.isRooted($this$notRooted)) {
            return $this$notRooted;
        }
        File current = $this$notRooted;
        while (true) {
            File parent = current.getParentFile();
            if (parent == null) {
                break;
            }
            current = parent;
        }
        String path = $this$notRooted.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "path");
        String $this$dropWhile$iv = StringsKt.drop(path, current.getName().length());
        int index$iv = 0;
        int length = $this$dropWhile$iv.length();
        while (true) {
            if (index$iv < length) {
                char it = $this$dropWhile$iv.charAt(index$iv);
                if (it == '\\' || it == '/') {
                    index$iv++;
                } else {
                    str = $this$dropWhile$iv.substring(index$iv);
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).substring(startIndex)");
                    break;
                }
            } else {
                str = "";
                break;
            }
        }
        return new File(str);
    }

    public static final int dropLeadingTopDirs(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        int startIndex = 0;
        int lastIndex = path.length() - 1;
        while (startIndex <= lastIndex) {
            char first = path.charAt(startIndex);
            if (isPathSeparator(first)) {
                startIndex++;
            } else if (first == '.') {
                if (startIndex == lastIndex) {
                    return startIndex + 1;
                }
                char second = path.charAt(startIndex + 1);
                if (isPathSeparator(second)) {
                    startIndex += 2;
                } else if (second == '.') {
                    if (startIndex + 2 == path.length()) {
                        startIndex += 2;
                    } else {
                        if (!isPathSeparator(path.charAt(startIndex + 2))) {
                            return startIndex;
                        }
                        startIndex += 3;
                    }
                } else {
                    return startIndex;
                }
            } else {
                return startIndex;
            }
        }
        return startIndex;
    }

    private static final boolean isPathSeparator(char $this$isPathSeparator) {
        return $this$isPathSeparator == '\\' || $this$isPathSeparator == '/';
    }

    private static final boolean isPathSeparatorOrDot(char $this$isPathSeparatorOrDot) {
        return $this$isPathSeparatorOrDot == '.' || isPathSeparator($this$isPathSeparatorOrDot);
    }

    private static final File dropLeadingTopDirs(File $this$dropLeadingTopDirs) {
        String path = $this$dropLeadingTopDirs.getPath();
        if (path == null) {
            path = "";
        }
        int startIndex = dropLeadingTopDirs(path);
        if (startIndex == 0) {
            return $this$dropLeadingTopDirs;
        }
        if (startIndex >= $this$dropLeadingTopDirs.getPath().length()) {
            return new File(".");
        }
        String path2 = $this$dropLeadingTopDirs.getPath();
        Intrinsics.checkNotNullExpressionValue(path2, "path");
        String substring = path2.substring(startIndex);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
        return new File(substring);
    }
}
