package org.slf4j.event;

import java.util.List;
import org.slf4j.Marker;

/* loaded from: classes9.dex */
public interface LoggingEvent {
    Object[] getArgumentArray();

    List<Object> getArguments();

    List<KeyValuePair> getKeyValuePairs();

    Level getLevel();

    String getLoggerName();

    List<Marker> getMarkers();

    String getMessage();

    String getThreadName();

    Throwable getThrowable();

    long getTimeStamp();

    default String getCallerBoundary() {
        return null;
    }
}
