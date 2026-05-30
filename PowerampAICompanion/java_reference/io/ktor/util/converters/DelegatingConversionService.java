package io.ktor.util.converters;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.util.reflect.TypeInfo;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* compiled from: DataConversion.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0013Bk\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012+\u0010\u0004\u001a'\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u0005\u0012+\u0010\f\u001a'\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u000eJ \u0010\u000f\u001a\u0004\u0018\u00010\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0016R3\u0010\u0004\u001a'\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R3\u0010\f\u001a'\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lio/ktor/util/converters/DelegatingConversionService;", "Lio/ktor/util/converters/ConversionService;", "klass", "Lkotlin/reflect/KClass;", "decoder", "Lkotlin/Function1;", "", "", "Lkotlin/ParameterName;", "name", "values", "", "encoder", "value", "(Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "fromValues", "type", "Lio/ktor/util/reflect/TypeInfo;", "toValues", "Configuration", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class DelegatingConversionService implements ConversionService {
    private final Function1<List<String>, Object> decoder;
    private final Function1<Object, List<String>> encoder;
    private final KClass<?> klass;

    /* JADX WARN: Multi-variable type inference failed */
    public DelegatingConversionService(KClass<?> klass, Function1<? super List<String>, ? extends Object> function1, Function1<Object, ? extends List<String>> function12) {
        Intrinsics.checkNotNullParameter(klass, "klass");
        this.klass = klass;
        this.decoder = function1;
        this.encoder = function12;
    }

    @Override // io.ktor.util.converters.ConversionService
    public Object fromValues(List<String> values, TypeInfo type) {
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(type, "type");
        Function1 currentDecoder = this.decoder;
        if (currentDecoder == null) {
            throw new IllegalStateException("Decoder was not specified for type '" + this.klass + '\'');
        }
        return currentDecoder.invoke(values);
    }

    @Override // io.ktor.util.converters.ConversionService
    public List<String> toValues(Object value) {
        Function1 currentDecoder = this.encoder;
        if (currentDecoder == null) {
            throw new IllegalStateException("Encoder was not specified for type '" + this.klass + '\'');
        }
        return currentDecoder.invoke(value);
    }

    /* compiled from: DataConversion.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0015\b\u0001\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J/\u0010\u0017\u001a\u00020\u00182'\u0010\u0019\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\t0\b¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00028\u00000\u0007J/\u0010\u001a\u001a\u00020\u00182'\u0010\u0019\u001a#\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007R=\u0010\u0006\u001a%\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\t0\b¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R=\u0010\u0011\u001a%\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0018\u00010\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001b"}, d2 = {"Lio/ktor/util/converters/DelegatingConversionService$Configuration;", ExifInterface.GPS_DIRECTION_TRUE, "", "klass", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)V", "decoder", "Lkotlin/Function1;", "", "", "Lkotlin/ParameterName;", "name", "values", "getDecoder$ktor_utils", "()Lkotlin/jvm/functions/Function1;", "setDecoder$ktor_utils", "(Lkotlin/jvm/functions/Function1;)V", "encoder", "value", "getEncoder$ktor_utils", "setEncoder$ktor_utils", "getKlass$ktor_utils", "()Lkotlin/reflect/KClass;", "decode", "", "converter", "encode", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Configuration<T> {
        private Function1<? super List<String>, ? extends T> decoder;
        private Function1<? super T, ? extends List<String>> encoder;
        private final KClass<T> klass;

        public Configuration(KClass<T> klass) {
            Intrinsics.checkNotNullParameter(klass, "klass");
            this.klass = klass;
        }

        public final KClass<T> getKlass$ktor_utils() {
            return this.klass;
        }

        public final Function1<List<String>, T> getDecoder$ktor_utils() {
            return this.decoder;
        }

        public final void setDecoder$ktor_utils(Function1<? super List<String>, ? extends T> function1) {
            this.decoder = function1;
        }

        public final Function1<T, List<String>> getEncoder$ktor_utils() {
            return this.encoder;
        }

        public final void setEncoder$ktor_utils(Function1<? super T, ? extends List<String>> function1) {
            this.encoder = function1;
        }

        public final void decode(Function1<? super List<String>, ? extends T> converter) {
            Intrinsics.checkNotNullParameter(converter, "converter");
            if (this.decoder != null) {
                throw new IllegalStateException("Decoder has already been set for type '" + this.klass + '\'');
            }
            this.decoder = converter;
        }

        public final void encode(Function1<? super T, ? extends List<String>> converter) {
            Intrinsics.checkNotNullParameter(converter, "converter");
            if (this.encoder != null) {
                throw new IllegalStateException("Encoder has already been set for type '" + this.klass + '\'');
            }
            this.encoder = converter;
        }
    }
}
