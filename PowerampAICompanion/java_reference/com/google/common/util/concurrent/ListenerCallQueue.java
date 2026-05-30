package com.google.common.util.concurrent;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public final class ListenerCallQueue<L> {
    private static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
    private final List<PerListenerQueue<L>> listeners = Collections.synchronizedList(new ArrayList());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Event<L> {
        void call(L l);
    }

    public void addListener(L listener, Executor executor) {
        Preconditions.checkNotNull(listener, "listener");
        Preconditions.checkNotNull(executor, "executor");
        this.listeners.add(new PerListenerQueue<>(listener, executor));
    }

    public void enqueue(Event<L> event) {
        enqueueHelper(event, event);
    }

    public void enqueue(Event<L> event, String label) {
        enqueueHelper(event, label);
    }

    private void enqueueHelper(Event<L> event, Object label) {
        Preconditions.checkNotNull(event, NotificationCompat.CATEGORY_EVENT);
        Preconditions.checkNotNull(label, "label");
        synchronized (this.listeners) {
            for (PerListenerQueue<L> queue : this.listeners) {
                queue.add(event, label);
            }
        }
    }

    public void dispatch() {
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).dispatch();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class PerListenerQueue<L> implements Runnable {
        final Executor executor;
        boolean isThreadScheduled;
        final L listener;
        final Queue<Event<L>> waitQueue = Queues.newArrayDeque();
        final Queue<Object> labelQueue = Queues.newArrayDeque();

        PerListenerQueue(L l, Executor executor) {
            this.listener = (L) Preconditions.checkNotNull(l);
            this.executor = (Executor) Preconditions.checkNotNull(executor);
        }

        synchronized void add(Event<L> event, Object label) {
            this.waitQueue.add(event);
            this.labelQueue.add(label);
        }

        void dispatch() {
            boolean scheduleEventRunner = false;
            synchronized (this) {
                if (!this.isThreadScheduled) {
                    this.isThreadScheduled = true;
                    scheduleEventRunner = true;
                }
            }
            if (scheduleEventRunner) {
                try {
                    this.executor.execute(this);
                } catch (RuntimeException e) {
                    synchronized (this) {
                        this.isThreadScheduled = false;
                        Logger logger = ListenerCallQueue.logger;
                        Level level = Level.SEVERE;
                        String valueOf = String.valueOf(this.listener);
                        String valueOf2 = String.valueOf(this.executor);
                        logger.log(level, new StringBuilder(String.valueOf(valueOf).length() + 42 + String.valueOf(valueOf2).length()).append("Exception while running callbacks for ").append(valueOf).append(" on ").append(valueOf2).toString(), (Throwable) e);
                        throw e;
                    }
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0028, code lost:
        
            r2.call(r11.listener);
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x002e, code lost:
        
            r4 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x002f, code lost:
        
            r5 = com.google.common.util.concurrent.ListenerCallQueue.logger;
            r6 = java.util.logging.Level.SEVERE;
            r7 = java.lang.String.valueOf(r11.listener);
            r8 = java.lang.String.valueOf(r3);
            r5.log(r6, new java.lang.StringBuilder((java.lang.String.valueOf(r7).length() + 37) + java.lang.String.valueOf(r8).length()).append("Exception while executing callback: ").append(r7).append(" ").append(r8).toString(), (java.lang.Throwable) r4);
         */
        /* JADX WARN: Removed duplicated region for block: B:21:0x001e  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0026 A[ORIG_RETURN, RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0079  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r11 = this;
                r0 = 1
            L1:
                r1 = 0
                monitor-enter(r11)     // Catch: java.lang.Throwable -> L76
                boolean r2 = r11.isThreadScheduled     // Catch: java.lang.Throwable -> L73
                com.google.common.base.Preconditions.checkState(r2)     // Catch: java.lang.Throwable -> L73
                java.util.Queue<com.google.common.util.concurrent.ListenerCallQueue$Event<L>> r2 = r11.waitQueue     // Catch: java.lang.Throwable -> L73
                java.lang.Object r2 = r2.poll()     // Catch: java.lang.Throwable -> L73
                com.google.common.util.concurrent.ListenerCallQueue$Event r2 = (com.google.common.util.concurrent.ListenerCallQueue.Event) r2     // Catch: java.lang.Throwable -> L73
                java.util.Queue<java.lang.Object> r3 = r11.labelQueue     // Catch: java.lang.Throwable -> L73
                java.lang.Object r3 = r3.poll()     // Catch: java.lang.Throwable -> L73
                if (r2 != 0) goto L27
                r11.isThreadScheduled = r1     // Catch: java.lang.Throwable -> L73
                r0 = 0
                monitor-exit(r11)     // Catch: java.lang.Throwable -> L73
                if (r0 == 0) goto L26
                monitor-enter(r11)
                r11.isThreadScheduled = r1     // Catch: java.lang.Throwable -> L23
                monitor-exit(r11)     // Catch: java.lang.Throwable -> L23
                goto L26
            L23:
                r1 = move-exception
                monitor-exit(r11)     // Catch: java.lang.Throwable -> L23
                throw r1
            L26:
                return
            L27:
                monitor-exit(r11)     // Catch: java.lang.Throwable -> L73
                L r4 = r11.listener     // Catch: java.lang.RuntimeException -> L2e java.lang.Throwable -> L76
                r2.call(r4)     // Catch: java.lang.RuntimeException -> L2e java.lang.Throwable -> L76
                goto L72
            L2e:
                r4 = move-exception
                java.util.logging.Logger r5 = com.google.common.util.concurrent.ListenerCallQueue.access$000()     // Catch: java.lang.Throwable -> L76
                java.util.logging.Level r6 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L76
                L r7 = r11.listener     // Catch: java.lang.Throwable -> L76
                java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch: java.lang.Throwable -> L76
                java.lang.String r8 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L76
                java.lang.String r9 = java.lang.String.valueOf(r7)     // Catch: java.lang.Throwable -> L76
                int r9 = r9.length()     // Catch: java.lang.Throwable -> L76
                int r9 = r9 + 37
                java.lang.String r10 = java.lang.String.valueOf(r8)     // Catch: java.lang.Throwable -> L76
                int r10 = r10.length()     // Catch: java.lang.Throwable -> L76
                int r9 = r9 + r10
                java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L76
                r10.<init>(r9)     // Catch: java.lang.Throwable -> L76
                java.lang.String r9 = "Exception while executing callback: "
                java.lang.StringBuilder r9 = r10.append(r9)     // Catch: java.lang.Throwable -> L76
                java.lang.StringBuilder r7 = r9.append(r7)     // Catch: java.lang.Throwable -> L76
                java.lang.String r9 = " "
                java.lang.StringBuilder r7 = r7.append(r9)     // Catch: java.lang.Throwable -> L76
                java.lang.StringBuilder r7 = r7.append(r8)     // Catch: java.lang.Throwable -> L76
                java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L76
                r5.log(r6, r7, r4)     // Catch: java.lang.Throwable -> L76
            L72:
                goto L1
            L73:
                r2 = move-exception
                monitor-exit(r11)     // Catch: java.lang.Throwable -> L73
                throw r2     // Catch: java.lang.Throwable -> L76
            L76:
                r2 = move-exception
                if (r0 == 0) goto L81
                monitor-enter(r11)
                r11.isThreadScheduled = r1     // Catch: java.lang.Throwable -> L7e
                monitor-exit(r11)     // Catch: java.lang.Throwable -> L7e
                goto L81
            L7e:
                r1 = move-exception
                monitor-exit(r11)     // Catch: java.lang.Throwable -> L7e
                throw r1
            L81:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ListenerCallQueue.PerListenerQueue.run():void");
        }
    }
}
