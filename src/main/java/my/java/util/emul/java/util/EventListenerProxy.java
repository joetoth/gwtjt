 /*
  * This file is based on code from the Apache Harmony Project.
  * http://svn.apache.org/repos/asf/harmony/enhanced/classlib/trunk/modules/luni/src/main/java/java/util/EventListenerProxy.java
  */

package java.util;

import java.util.EventListener;

/**
 * This abstract class provides a simple wrapper to types of EventListener.
 *
 */
public abstract class EventListenerProxy implements EventListener {

    private final EventListener listener;

    /**
     * @param listener
     */
    public EventListenerProxy(EventListener listener) {
        super();
        this.listener = listener;
    }

    public EventListener getListener() {
        return listener;
    }
}
