package io.ktor.events;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.util.collections.CopyOnWriteHashMap;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

/* compiled from: Events.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J'\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\u00052\u0006\u0010\f\u001a\u0002H\n¢\u0006\u0002\u0010\rJ8\u0010\u000e\u001a\u00020\u000f\"\u0004\b\u0000\u0010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\u00052\u001c\u0010\u0010\u001a\u0018\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\t0\u0011j\b\u0012\u0004\u0012\u0002H\n`\u0012J8\u0010\u0013\u001a\u00020\t\"\u0004\b\u0000\u0010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\u00052\u001c\u0010\u0010\u001a\u0018\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\t0\u0011j\b\u0012\u0004\u0012\u0002H\n`\u0012R$\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\u0015"}, d2 = {"Lio/ktor/events/Events;", "", "()V", "handlers", "Lio/ktor/util/collections/CopyOnWriteHashMap;", "Lio/ktor/events/EventDefinition;", "Lkotlinx/coroutines/internal/LockFreeLinkedListHead;", "getHandlers$annotations", "raise", "", ExifInterface.GPS_DIRECTION_TRUE, "definition", "value", "(Lio/ktor/events/EventDefinition;Ljava/lang/Object;)V", "subscribe", "Lkotlinx/coroutines/DisposableHandle;", "handler", "Lkotlin/Function1;", "Lio/ktor/events/EventHandler;", "unsubscribe", "HandlerRegistration", "ktor-events"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class Events {
    private final CopyOnWriteHashMap<EventDefinition<?>, LockFreeLinkedListHead> handlers = new CopyOnWriteHashMap<>();

    private static /* synthetic */ void getHandlers$annotations() {
    }

    public final <T> DisposableHandle subscribe(EventDefinition<T> definition, Function1<? super T, Unit> handler) {
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(handler, "handler");
        HandlerRegistration registration = new HandlerRegistration(handler);
        this.handlers.computeIfAbsent(definition, new Function1<EventDefinition<?>, LockFreeLinkedListHead>() { // from class: io.ktor.events.Events$subscribe$1
            @Override // kotlin.jvm.functions.Function1
            public final LockFreeLinkedListHead invoke(EventDefinition<?> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new LockFreeLinkedListHead();
            }
        }).addLast(registration);
        return registration;
    }

    public final <T> void unsubscribe(EventDefinition<T> definition, Function1<? super T, Unit> handler) {
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(handler, "handler");
        LockFreeLinkedListHead this_$iv = this.handlers.get(definition);
        if (this_$iv == null) {
            return;
        }
        Object next = this_$iv.getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        for (LockFreeLinkedListNode cur$iv = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(cur$iv, this_$iv); cur$iv = cur$iv.getNextNode()) {
            if (cur$iv instanceof HandlerRegistration) {
                HandlerRegistration it = (HandlerRegistration) cur$iv;
                if (Intrinsics.areEqual(it.getHandler(), handler)) {
                    it.mo2065remove();
                }
            }
        }
    }

    public final <T> void raise(EventDefinition<T> definition, T value) {
        Unit unit;
        Intrinsics.checkNotNullParameter(definition, "definition");
        Throwable th = null;
        LockFreeLinkedListHead this_$iv = this.handlers.get(definition);
        if (this_$iv != null) {
            Object next = this_$iv.getNext();
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            for (LockFreeLinkedListNode cur$iv = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(cur$iv, this_$iv); cur$iv = cur$iv.getNextNode()) {
                if (cur$iv instanceof HandlerRegistration) {
                    HandlerRegistration registration = (HandlerRegistration) cur$iv;
                    try {
                        Function1<?, Unit> handler = registration.getHandler();
                        Intrinsics.checkNotNull(handler, "null cannot be cast to non-null type kotlin.Function1<T of io.ktor.events.Events.raise$lambda$2, kotlin.Unit>{ io.ktor.events.EventsKt.EventHandler<T of io.ktor.events.Events.raise$lambda$2> }");
                        ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(handler, 1)).invoke(value);
                    } catch (Throwable e) {
                        Throwable th2 = th;
                        if (th2 != null) {
                            ExceptionsKt.addSuppressed(th2, e);
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        if (unit == null) {
                            th = e;
                        }
                    }
                }
            }
        }
        if (th != null) {
            Throwable it = th;
            throw it;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Events.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00050\u0004j\u0006\u0012\u0002\b\u0003`\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u0005H\u0016R#\u0010\u0003\u001a\u0014\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00050\u0004j\u0006\u0012\u0002\b\u0003`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lio/ktor/events/Events$HandlerRegistration;", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/DisposableHandle;", "handler", "Lkotlin/Function1;", "", "Lio/ktor/events/EventHandler;", "(Lkotlin/jvm/functions/Function1;)V", "getHandler", "()Lkotlin/jvm/functions/Function1;", "dispose", "ktor-events"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class HandlerRegistration extends LockFreeLinkedListNode implements DisposableHandle {
        private final Function1<?, Unit> handler;

        public HandlerRegistration(Function1<?, Unit> handler) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            this.handler = handler;
        }

        public final Function1<?, Unit> getHandler() {
            return this.handler;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            mo2065remove();
        }
    }
}
