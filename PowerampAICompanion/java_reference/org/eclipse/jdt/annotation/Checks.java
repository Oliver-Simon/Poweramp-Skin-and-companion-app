package org.eclipse.jdt.annotation;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* loaded from: classes9.dex */
public class Checks {
    @SafeVarargs
    public static <T> void assertNonNull(T... tArr) {
        for (int i = 0; i < tArr.length; i++) {
            if (tArr[i] == null) {
                throw new NullPointerException("Value in position " + i + " must not be null");
            }
        }
    }

    @SafeVarargs
    public static <T> void assertNonNullWithMessage(String message, T... tArr) {
        for (T v : tArr) {
            if (v == null) {
                throw new NullPointerException(message);
            }
        }
    }

    public static <T> void assertNonNullElements(Iterable<T> values) {
        int count = 0;
        for (T v : values) {
            if (v == null) {
                throw new NullPointerException("Value in position " + count + " must not be null");
            }
            count++;
        }
    }

    public static <T> void assertNonNullElements(Iterable<T> values, String message) {
        for (T v : values) {
            if (v == null) {
                throw new NullPointerException(message);
            }
        }
    }

    public static <T> T requireNonNull(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return value;
    }

    public static <T> T requireNonNull(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }

    public static String requireNonEmpty(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return value;
    }

    public static String requireNonEmpty(String value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public static <C extends Collection<?>> C requireNonEmpty(C value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return value;
    }

    public static <C extends Collection<?>> C requireNonEmpty(C value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public static boolean isNull(Object value) {
        return value == null;
    }

    @SafeVarargs
    public static <T> boolean isAnyNull(T... tArr) {
        for (T t : tArr) {
            if (t == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsNull(Iterable<?> values) {
        for (Object value : values) {
            if (value == null) {
                return true;
            }
        }
        return false;
    }

    public static <T> T asNullable(Optional<T> optional) {
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public static <T> T nonNullElse(T value, T fallbackValue) {
        if (value == null) {
            return fallbackValue;
        }
        return value;
    }

    public static <T> T nonNullElseGet(T value, Supplier<? extends T> fallbackSupplier) {
        if (value == null) {
            return fallbackSupplier.get();
        }
        return value;
    }

    public static <T> void ifNonNull(T value, Consumer<? super T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }

    public static <T, U> U applyIfNonNull(T value, Function<? super T, ? extends U> function) {
        if (value != null) {
            return function.apply(value);
        }
        return null;
    }

    public static <T, U> U applyIfNonNullElse(T value, Function<? super T, ? extends U> function, U fallbackValue) {
        if (value != null) {
            return function.apply(value);
        }
        return fallbackValue;
    }

    public static <T, U> U applyIfNonNullElseGet(T value, Function<? super T, ? extends U> function, Supplier<? extends U> fallbackSupplier) {
        if (value != null) {
            return function.apply(value);
        }
        return fallbackSupplier.get();
    }

    public static boolean unboxElse(Boolean boxedValue, boolean fallbackValue) {
        if (boxedValue == null) {
            return fallbackValue;
        }
        return boxedValue.booleanValue();
    }

    public static byte unboxElse(Byte boxedValue, byte fallbackValue) {
        if (boxedValue == null) {
            return fallbackValue;
        }
        return boxedValue.byteValue();
    }

    public static char unboxElse(Character boxedValue, char fallbackValue) {
        if (boxedValue == null) {
            return fallbackValue;
        }
        return boxedValue.charValue();
    }

    public static int unboxElse(Integer boxedValue, int fallbackValue) {
        if (boxedValue == null) {
            return fallbackValue;
        }
        return boxedValue.intValue();
    }

    public static long unboxElse(Long boxedValue, long fallbackValue) {
        if (boxedValue == null) {
            return fallbackValue;
        }
        return boxedValue.longValue();
    }

    public static short unboxElse(Short boxedValue, short fallbackValue) {
        if (boxedValue == null) {
            return fallbackValue;
        }
        return boxedValue.shortValue();
    }

    public static float unboxElse(Float boxedValue, float fallbackValue) {
        if (boxedValue == null) {
            return fallbackValue;
        }
        return boxedValue.floatValue();
    }

    public static double unboxElse(Double boxedValue, double fallbackValue) {
        if (boxedValue == null) {
            return fallbackValue;
        }
        return boxedValue.doubleValue();
    }
}
