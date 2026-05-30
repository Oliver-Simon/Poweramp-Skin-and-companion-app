package io.ktor.util;

import com.maxmpz.poweramp.player.RouterConsts;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CryptoJvm.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0083@\u0018\u00002\u00020\u0001B\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\b\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\tH\u0096\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u001fHÖ\u0001¢\u0006\u0004\b \u0010!R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"Lio/ktor/util/DigestImpl;", "Lio/ktor/util/Digest;", "delegate", "Ljava/security/MessageDigest;", "constructor-impl", "(Ljava/security/MessageDigest;)Ljava/security/MessageDigest;", "getDelegate", "()Ljava/security/MessageDigest;", "build", "", "build-impl", "(Ljava/security/MessageDigest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "equals", "", RouterConsts.DEVICE_NAME_OTHER, "", "equals-impl", "(Ljava/security/MessageDigest;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/security/MessageDigest;)I", "plusAssign", "", "bytes", "plusAssign-impl", "(Ljava/security/MessageDigest;[B)V", "reset", "reset-impl", "(Ljava/security/MessageDigest;)V", "toString", "", "toString-impl", "(Ljava/security/MessageDigest;)Ljava/lang/String;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
/* loaded from: classes9.dex */
final class DigestImpl implements Digest {
    private final MessageDigest delegate;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ DigestImpl m208boximpl(MessageDigest messageDigest) {
        return new DigestImpl(messageDigest);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static MessageDigest m210constructorimpl(MessageDigest delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        return delegate;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m211equalsimpl(MessageDigest messageDigest, Object obj) {
        return (obj instanceof DigestImpl) && Intrinsics.areEqual(messageDigest, ((DigestImpl) obj).m217unboximpl());
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m212equalsimpl0(MessageDigest messageDigest, MessageDigest messageDigest2) {
        return Intrinsics.areEqual(messageDigest, messageDigest2);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m213hashCodeimpl(MessageDigest messageDigest) {
        return messageDigest.hashCode();
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m216toStringimpl(MessageDigest messageDigest) {
        return "DigestImpl(delegate=" + messageDigest + ')';
    }

    public boolean equals(Object obj) {
        return m211equalsimpl(this.delegate, obj);
    }

    public int hashCode() {
        return m213hashCodeimpl(this.delegate);
    }

    public String toString() {
        return m216toStringimpl(this.delegate);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ MessageDigest m217unboximpl() {
        return this.delegate;
    }

    private /* synthetic */ DigestImpl(MessageDigest delegate) {
        this.delegate = delegate;
    }

    public final MessageDigest getDelegate() {
        return this.delegate;
    }

    @Override // io.ktor.util.Digest
    public void plusAssign(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        m214plusAssignimpl(this.delegate, bytes);
    }

    /* renamed from: plusAssign-impl, reason: not valid java name */
    public static void m214plusAssignimpl(MessageDigest arg0, byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        arg0.update(bytes);
    }

    @Override // io.ktor.util.Digest
    public void reset() {
        m215resetimpl(this.delegate);
    }

    /* renamed from: reset-impl, reason: not valid java name */
    public static void m215resetimpl(MessageDigest arg0) {
        arg0.reset();
    }

    /* renamed from: build-impl, reason: not valid java name */
    public static Object m209buildimpl(MessageDigest arg0, Continuation<? super byte[]> continuation) {
        byte[] digest = arg0.digest();
        Intrinsics.checkNotNullExpressionValue(digest, "delegate.digest()");
        return digest;
    }

    @Override // io.ktor.util.Digest
    public Object build(Continuation<? super byte[]> continuation) {
        return m209buildimpl(this.delegate, continuation);
    }
}
