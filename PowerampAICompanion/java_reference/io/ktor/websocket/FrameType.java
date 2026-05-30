package io.ktor.websocket;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* compiled from: FrameType.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0001\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lio/ktor/websocket/FrameType;", "", "controlFrame", "", "opcode", "", "(Ljava/lang/String;IZI)V", "getControlFrame", "()Z", "getOpcode", "()I", "TEXT", "BINARY", "CLOSE", "PING", "PONG", "Companion", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes9.dex */
public enum FrameType {
    TEXT(false, 1),
    BINARY(false, 2),
    CLOSE(true, 8),
    PING(true, 9),
    PONG(true, 10);


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final FrameType[] byOpcodeArray;
    private static final int maxOpcode;
    private final boolean controlFrame;
    private final int opcode;

    FrameType(boolean controlFrame, int opcode) {
        this.controlFrame = controlFrame;
        this.opcode = opcode;
    }

    public final boolean getControlFrame() {
        return this.controlFrame;
    }

    public final int getOpcode() {
        return this.opcode;
    }

    /* JADX WARN: Type inference failed for: r8v2, types: [kotlin.collections.IntIterator] */
    static {
        FrameType frameType;
        FrameType[] values = values();
        if (values.length == 0) {
            frameType = null;
        } else {
            frameType = values[0];
            int lastIndex$iv = ArraysKt.getLastIndex(values);
            if (lastIndex$iv != 0) {
                int maxValue$iv = frameType.opcode;
                ?? it = new IntRange(1, lastIndex$iv).iterator();
                while (it.hasNext()) {
                    int i$iv = it.nextInt();
                    FrameType frameType2 = values[i$iv];
                    int v$iv = frameType2.opcode;
                    if (maxValue$iv < v$iv) {
                        frameType = frameType2;
                        maxValue$iv = v$iv;
                    }
                }
            }
        }
        Intrinsics.checkNotNull(frameType);
        maxOpcode = frameType.opcode;
        int i = maxOpcode + 1;
        FrameType[] frameTypeArr = new FrameType[i];
        int i2 = 0;
        while (i2 < i) {
            FrameType[] values2 = values();
            Object single$iv = null;
            boolean found$iv = false;
            int length = values2.length;
            int i3 = 0;
            while (true) {
                if (i3 < length) {
                    FrameType frameType3 = values2[i3];
                    FrameType it2 = frameType3.opcode == i2 ? 1 : null;
                    if (it2 != null) {
                        if (found$iv) {
                            single$iv = null;
                            break;
                        } else {
                            single$iv = frameType3;
                            found$iv = true;
                        }
                    }
                    i3++;
                } else if (!found$iv) {
                    single$iv = null;
                }
            }
            frameTypeArr[i2] = single$iv;
            i2++;
        }
        byOpcodeArray = frameTypeArr;
    }

    /* compiled from: FrameType.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\bH\u0086\u0002R\u0018\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lio/ktor/websocket/FrameType$Companion;", "", "()V", "byOpcodeArray", "", "Lio/ktor/websocket/FrameType;", "[Lio/ktor/websocket/FrameType;", "maxOpcode", "", "get", "opcode", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FrameType get(int opcode) {
            boolean z = false;
            if (opcode >= 0 && opcode <= FrameType.maxOpcode) {
                z = true;
            }
            if (z) {
                return FrameType.byOpcodeArray[opcode];
            }
            return null;
        }
    }
}
