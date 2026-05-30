package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public final class SequentialExecutor implements Executor {
    private static final Logger log = Logger.getLogger(SequentialExecutor.class.getName());
    private final Executor executor;
    private final Deque<Runnable> queue = new ArrayDeque();
    private WorkerRunningState workerRunningState = WorkerRunningState.IDLE;
    private long workerRunCount = 0;
    private final QueueWorker worker = new QueueWorker();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public enum WorkerRunningState {
        IDLE,
        QUEUING,
        QUEUED,
        RUNNING
    }

    static /* synthetic */ long access$308(SequentialExecutor x0) {
        long j = x0.workerRunCount;
        x0.workerRunCount = 1 + j;
        return j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SequentialExecutor(Executor executor) {
        this.executor = (Executor) Preconditions.checkNotNull(executor);
    }

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable task) {
        Preconditions.checkNotNull(task);
        synchronized (this.queue) {
            if (this.workerRunningState != WorkerRunningState.RUNNING && this.workerRunningState != WorkerRunningState.QUEUED) {
                long oldRunCount = this.workerRunCount;
                Runnable submittedTask = new Runnable(this) { // from class: com.google.common.util.concurrent.SequentialExecutor.1
                    @Override // java.lang.Runnable
                    public void run() {
                        task.run();
                    }

                    public String toString() {
                        return task.toString();
                    }
                };
                this.queue.add(submittedTask);
                this.workerRunningState = WorkerRunningState.QUEUING;
                try {
                    this.executor.execute(this.worker);
                    removed = this.workerRunningState != WorkerRunningState.QUEUING;
                    boolean alreadyMarkedQueued = removed;
                    if (alreadyMarkedQueued) {
                        return;
                    }
                    synchronized (this.queue) {
                        if (this.workerRunCount == oldRunCount && this.workerRunningState == WorkerRunningState.QUEUING) {
                            this.workerRunningState = WorkerRunningState.QUEUED;
                        }
                    }
                    return;
                } catch (Error | RuntimeException t) {
                    synchronized (this.queue) {
                        if ((this.workerRunningState != WorkerRunningState.IDLE && this.workerRunningState != WorkerRunningState.QUEUING) || !this.queue.removeLastOccurrence(submittedTask)) {
                            removed = false;
                        }
                        if (!(t instanceof RejectedExecutionException) || removed) {
                            throw t;
                        }
                    }
                    return;
                }
            }
            this.queue.add(task);
        }
    }

    /* loaded from: classes9.dex */
    private final class QueueWorker implements Runnable {

        @CheckForNull
        Runnable task;

        private QueueWorker() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                workOnQueue();
            } catch (Error e) {
                synchronized (SequentialExecutor.this.queue) {
                    SequentialExecutor.this.workerRunningState = WorkerRunningState.IDLE;
                    throw e;
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0056, code lost:
        
            r0 = r0 | java.lang.Thread.interrupted();
            r2 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0058, code lost:
        
            r9.task.run();
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0060, code lost:
        
            r3 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0092, code lost:
        
            r9.task = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0095, code lost:
        
            throw r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0062, code lost:
        
            r3 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0063, code lost:
        
            r4 = com.google.common.util.concurrent.SequentialExecutor.log;
            r5 = java.util.logging.Level.SEVERE;
            r6 = java.lang.String.valueOf(r9.task);
            r4.log(r5, new java.lang.StringBuilder(java.lang.String.valueOf(r6).length() + 35).append("Exception while executing runnable ").append(r6).toString(), (java.lang.Throwable) r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x008f, code lost:
        
            r9.task = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0050, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:?, code lost:
        
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void workOnQueue() {
            /*
                r9 = this;
                r0 = 0
                r1 = 0
            L2:
                com.google.common.util.concurrent.SequentialExecutor r2 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L99
                java.util.Deque r2 = com.google.common.util.concurrent.SequentialExecutor.access$100(r2)     // Catch: java.lang.Throwable -> L99
                monitor-enter(r2)     // Catch: java.lang.Throwable -> L99
                if (r1 != 0) goto L2d
                com.google.common.util.concurrent.SequentialExecutor r3 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L96
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.common.util.concurrent.SequentialExecutor.access$200(r3)     // Catch: java.lang.Throwable -> L96
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r4 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch: java.lang.Throwable -> L96
                if (r3 != r4) goto L20
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L96
                if (r0 == 0) goto L1f
                java.lang.Thread r2 = java.lang.Thread.currentThread()
                r2.interrupt()
            L1f:
                return
            L20:
                com.google.common.util.concurrent.SequentialExecutor r3 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L96
                com.google.common.util.concurrent.SequentialExecutor.access$308(r3)     // Catch: java.lang.Throwable -> L96
                com.google.common.util.concurrent.SequentialExecutor r3 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L96
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r4 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch: java.lang.Throwable -> L96
                com.google.common.util.concurrent.SequentialExecutor.access$202(r3, r4)     // Catch: java.lang.Throwable -> L96
                r1 = 1
            L2d:
                com.google.common.util.concurrent.SequentialExecutor r3 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L96
                java.util.Deque r3 = com.google.common.util.concurrent.SequentialExecutor.access$100(r3)     // Catch: java.lang.Throwable -> L96
                java.lang.Object r3 = r3.poll()     // Catch: java.lang.Throwable -> L96
                java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch: java.lang.Throwable -> L96
                r9.task = r3     // Catch: java.lang.Throwable -> L96
                java.lang.Runnable r3 = r9.task     // Catch: java.lang.Throwable -> L96
                if (r3 != 0) goto L51
                com.google.common.util.concurrent.SequentialExecutor r3 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L96
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r4 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.IDLE     // Catch: java.lang.Throwable -> L96
                com.google.common.util.concurrent.SequentialExecutor.access$202(r3, r4)     // Catch: java.lang.Throwable -> L96
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L96
                if (r0 == 0) goto L50
                java.lang.Thread r2 = java.lang.Thread.currentThread()
                r2.interrupt()
            L50:
                return
            L51:
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L96
                boolean r2 = java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L99
                r0 = r0 | r2
                r2 = 0
                java.lang.Runnable r3 = r9.task     // Catch: java.lang.Throwable -> L60 java.lang.RuntimeException -> L62
                r3.run()     // Catch: java.lang.Throwable -> L60 java.lang.RuntimeException -> L62
                r9.task = r2     // Catch: java.lang.Throwable -> L99
            L5f:
                goto L2
            L60:
                r3 = move-exception
                goto L92
            L62:
                r3 = move-exception
                java.util.logging.Logger r4 = com.google.common.util.concurrent.SequentialExecutor.access$400()     // Catch: java.lang.Throwable -> L60
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L60
                java.lang.Runnable r6 = r9.task     // Catch: java.lang.Throwable -> L60
                java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L60
                java.lang.String r7 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L60
                int r7 = r7.length()     // Catch: java.lang.Throwable -> L60
                int r7 = r7 + 35
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L60
                r8.<init>(r7)     // Catch: java.lang.Throwable -> L60
                java.lang.String r7 = "Exception while executing runnable "
                java.lang.StringBuilder r7 = r8.append(r7)     // Catch: java.lang.Throwable -> L60
                java.lang.StringBuilder r6 = r7.append(r6)     // Catch: java.lang.Throwable -> L60
                java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L60
                r4.log(r5, r6, r3)     // Catch: java.lang.Throwable -> L60
                r9.task = r2     // Catch: java.lang.Throwable -> L99
                goto L5f
            L92:
                r9.task = r2     // Catch: java.lang.Throwable -> L99
                throw r3     // Catch: java.lang.Throwable -> L99
            L96:
                r3 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L96
                throw r3     // Catch: java.lang.Throwable -> L99
            L99:
                r2 = move-exception
                if (r0 == 0) goto La3
                java.lang.Thread r3 = java.lang.Thread.currentThread()
                r3.interrupt()
            La3:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SequentialExecutor.QueueWorker.workOnQueue():void");
        }

        public String toString() {
            Runnable currentlyRunning = this.task;
            if (currentlyRunning == null) {
                String valueOf = String.valueOf(SequentialExecutor.this.workerRunningState);
                return new StringBuilder(String.valueOf(valueOf).length() + 32).append("SequentialExecutorWorker{state=").append(valueOf).append("}").toString();
            }
            String valueOf2 = String.valueOf(currentlyRunning);
            return new StringBuilder(String.valueOf(valueOf2).length() + 34).append("SequentialExecutorWorker{running=").append(valueOf2).append("}").toString();
        }
    }

    public String toString() {
        int identityHashCode = System.identityHashCode(this);
        String valueOf = String.valueOf(this.executor);
        return new StringBuilder(String.valueOf(valueOf).length() + 32).append("SequentialExecutor@").append(identityHashCode).append("{").append(valueOf).append("}").toString();
    }
}
