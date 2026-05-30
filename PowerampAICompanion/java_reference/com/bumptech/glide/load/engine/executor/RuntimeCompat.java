package com.bumptech.glide.load.engine.executor;

/* loaded from: classes.dex */
final class RuntimeCompat {
    private static final String CPU_LOCATION = "/sys/devices/system/cpu/";
    private static final String CPU_NAME_REGEX = "cpu[0-9]+";
    private static final String TAG = "GlideRuntimeCompat";

    private RuntimeCompat() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int availableProcessors() {
        int cpus = Runtime.getRuntime().availableProcessors();
        return cpus;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int getCoreCountPre17() {
        /*
            java.lang.String r0 = "GlideRuntimeCompat"
            r1 = 0
            android.os.StrictMode$ThreadPolicy r2 = android.os.StrictMode.allowThreadDiskReads()
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L1f
            java.lang.String r4 = "/sys/devices/system/cpu/"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L1f
            java.lang.String r4 = "cpu[0-9]+"
            java.util.regex.Pattern r4 = java.util.regex.Pattern.compile(r4)     // Catch: java.lang.Throwable -> L1f
            com.bumptech.glide.load.engine.executor.RuntimeCompat$1 r5 = new com.bumptech.glide.load.engine.executor.RuntimeCompat$1     // Catch: java.lang.Throwable -> L1f
            r5.<init>()     // Catch: java.lang.Throwable -> L1f
            java.io.File[] r0 = r3.listFiles(r5)     // Catch: java.lang.Throwable -> L1f
            r1 = r0
            goto L2c
        L1f:
            r3 = move-exception
            r4 = 6
            boolean r4 = android.util.Log.isLoggable(r0, r4)     // Catch: java.lang.Throwable -> L3b
            if (r4 == 0) goto L2c
            java.lang.String r4 = "Failed to calculate accurate cpu count"
            android.util.Log.e(r0, r4, r3)     // Catch: java.lang.Throwable -> L3b
        L2c:
            android.os.StrictMode.setThreadPolicy(r2)
            if (r1 == 0) goto L34
            int r0 = r1.length
            goto L35
        L34:
            r0 = 0
        L35:
            r3 = 1
            int r0 = java.lang.Math.max(r3, r0)
            return r0
        L3b:
            r0 = move-exception
            android.os.StrictMode.setThreadPolicy(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.executor.RuntimeCompat.getCoreCountPre17():int");
    }
}
