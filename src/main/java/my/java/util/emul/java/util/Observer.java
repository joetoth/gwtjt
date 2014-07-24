/*
 * This file is based on code from the Apache Harmony Project.
 * http://svn.apache.org/repos/asf/harmony/enhanced/classlib/trunk/modules/luni/src/main/java/java/util/Observer.java
 */

package java.util;

/**
 * Observer must be implemented by objects which are added to an Observable.
 */
public interface Observer {

    /**
     * When the specified observable object's <code>notifyObservers</code>
     * method is called and the observable object has changed, this method is
     * called.
     * 
     * @param observable
     *            the observable object
     * @param data
     *            the data passed to <code>notifyObservers</code>
     */
    void update(Observable observable, Object data);
}