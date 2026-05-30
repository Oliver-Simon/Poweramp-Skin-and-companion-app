package io.ktor.serialization.kotlinx.json;

import io.ktor.utils.io.ByteWriteChannel;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.serialization.KSerializer;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: Collect.kt */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collectIndexed$2", "Lkotlinx/coroutines/flow/FlowCollector;", "index", "", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1<T> implements FlowCollector<T> {
    final /* synthetic */ ByteWriteChannel $channel$inlined;
    final /* synthetic */ Charset $charset$inlined;
    final /* synthetic */ JsonArraySymbols $jsonArraySymbols$inlined;
    final /* synthetic */ KSerializer $serializer$inlined;
    private int index;
    final /* synthetic */ KotlinxSerializationJsonExtensions this$0;

    /* compiled from: Collect.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1", f = "KotlinxSerializationJsonExtensions.kt", i = {0, 0, 1}, l = {124, 127}, m = "emit", n = {"this", "value", "this"}, s = {"L$0", "L$1", "L$0"})
    /* renamed from: io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1$1, reason: invalid class name */
    /* loaded from: classes9.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1.this.emit(null, this);
        }
    }

    public KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1(ByteWriteChannel byteWriteChannel, JsonArraySymbols jsonArraySymbols, KotlinxSerializationJsonExtensions kotlinxSerializationJsonExtensions, KSerializer kSerializer, Charset charset) {
        this.$channel$inlined = byteWriteChannel;
        this.$jsonArraySymbols$inlined = jsonArraySymbols;
        this.this$0 = kotlinxSerializationJsonExtensions;
        this.$serializer$inlined = kSerializer;
        this.$charset$inlined = charset;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object emit(T r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r12
            io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1$1 r0 = (io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L19
        L14:
            io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1$1 r0 = new io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1$1
            r0.<init>(r12)
        L19:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L41;
                case 1: goto L36;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L2c:
            r11 = 0
            java.lang.Object r1 = r0.L$0
            io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1 r1 = (io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1) r1
            kotlin.ResultKt.throwOnFailure(r12)
            goto Lb7
        L36:
            r11 = 0
            java.lang.Object r2 = r0.L$1
            java.lang.Object r3 = r0.L$0
            io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1 r3 = (io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1) r3
            kotlin.ResultKt.throwOnFailure(r12)
            goto L6d
        L41:
            kotlin.ResultKt.throwOnFailure(r12)
            r3 = r10
            int r2 = r3.index
            int r4 = r2 + 1
            r3.index = r4
            r4 = 0
            if (r2 < 0) goto Lc0
        L4f:
            r4 = r0
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r4 = 0
            if (r2 <= 0) goto L6f
            io.ktor.utils.io.ByteWriteChannel r2 = r3.$channel$inlined
            io.ktor.serialization.kotlinx.json.JsonArraySymbols r5 = r3.$jsonArraySymbols$inlined
            byte[] r5 = r5.getObjectSeparator()
            r0.L$0 = r3
            r0.L$1 = r11
            r6 = 1
            r0.label = r6
            java.lang.Object r2 = io.ktor.utils.io.ByteWriteChannelKt.writeFully(r2, r5, r0)
            if (r2 != r1) goto L6b
            return r1
        L6b:
            r2 = r11
            r11 = r4
        L6d:
            r4 = r11
            r11 = r2
        L6f:
            io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions r2 = r3.this$0
            kotlinx.serialization.json.Json r2 = io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions.access$getFormat$p(r2)
            kotlinx.serialization.KSerializer r5 = r3.$serializer$inlined
            kotlinx.serialization.SerializationStrategy r5 = (kotlinx.serialization.SerializationStrategy) r5
            java.lang.String r11 = r2.encodeToString(r5, r11)
            io.ktor.utils.io.ByteWriteChannel r2 = r3.$channel$inlined
            r5 = 0
            java.nio.charset.Charset r6 = r3.$charset$inlined
            java.nio.charset.Charset r7 = kotlin.text.Charsets.UTF_8
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r7)
            if (r6 == 0) goto L8f
            byte[] r6 = kotlin.text.StringsKt.encodeToByteArray(r11)
            goto La6
        L8f:
            java.nio.charset.Charset r6 = r3.$charset$inlined
            java.nio.charset.CharsetEncoder r6 = r6.newEncoder()
            java.lang.String r7 = "charset.newEncoder()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            r7 = r11
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            r8 = 0
            int r9 = r11.length()
            byte[] r6 = io.ktor.utils.io.charsets.CharsetJVMKt.encodeToByteArray(r6, r7, r8, r9)
        La6:
            r0.L$0 = r3
            r11 = 0
            r0.L$1 = r11
            r11 = 2
            r0.label = r11
            java.lang.Object r11 = io.ktor.utils.io.ByteWriteChannelKt.writeFully(r2, r6, r0)
            if (r11 != r1) goto Lb5
            return r1
        Lb5:
            r1 = r3
            r11 = r4
        Lb7:
            io.ktor.utils.io.ByteWriteChannel r2 = r1.$channel$inlined
            r2.flush()
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        Lc0:
            java.lang.ArithmeticException r11 = new java.lang.ArithmeticException
            java.lang.String r1 = "Index overflow has happened"
            r11.<init>(r1)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions$serialize$$inlined$collectIndexed$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
