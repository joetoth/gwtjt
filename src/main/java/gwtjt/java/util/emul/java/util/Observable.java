
/*
 * This file is based on code from the Apache Harmony Project.
 * http://svn.apache.org/repos/asf/harmony/enhanced/classlib/trunk/modules/luni/src/main/java/java/util/Observable.java
 */

package java.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable is used to notify a group of Observer objects when a change
 * occurs.
 */
public class Observable {

    private List<Observer> observers = new ArrayList<Observer>();

    private boolean changed = false;

    /**
     * Constructs a new Observable object.
     */
    public Observable() {
        super();
    }

    /**
     * Adds the specified Observer to the list of observers.
     *
     * @param observer
     *            the Observer to add
     */
    public void addObserver(Observer observer) {
        if (observer == null) {
            throw new NullPointerException();
        }
        synchronized (this) {
            if (!observers.contains(observer))
                observers.add(observer);
        }
    }

    /**
     * Clears the changed flag for this Observable. After calling
     * <code>clearChanged()</code>, <code>hasChanged()</code> will return
     * false.
     */
    protected void clearChanged() {
        changed = false;
    }

    /**
     * Answers the number of Observers in the list of observers.
     *
     * @return the number of observers
     */
    public int countObservers() {
        return observers.size();
    }

    /**
     * Removes the specified Observer from the list of observers.
     *
     * @param observer
     *            the Observer to remove
     */
    public synchronized void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Removes all Observers from the list of observers.
     */
    public synchronized void deleteObservers() {
        observers.clear();
    }

    /**
     * Answers the changed flag for this Observable.
     *
     * @return true when the changed flag for this Observable is set, false
     *         otherwise
     */
    public boolean hasChanged() {
        return changed;
    }

    /**
     * If <code>hasChanged()</code> returns true, calls the
     * <code>update()</code> method for every Observer in the list of
     * observers using null as the argument. Afterwards calls
     * <code>clearChanged()</code>.
     *
     * Equivalent to calling <code>notifyObservers(null)</code>
     */
    public void notifyObservers() {
        notifyObservers(null);
    }

    /**
     * If <code>hasChanged()</code> returns true, calls the
     * <code>update()</code> method for every Observer in the list of
     * observers using the specified argument. Afterwards calls
     * <code>clearChanged()</code>.
     *
     * @param data
     *            the argument passed to update()
     */
    @SuppressWarnings("unchecked")
    public void notifyObservers(Object data) {
        int size = 0;
        Observer[] arrays = null;
        synchronized (this) {
            if (hasChanged()) {
                clearChanged();
                size = observers.size();
                arrays = new Observer[size];
                observers.toArray(arrays);
            }
        }
        if (arrays != null) {
            for (Observer observer : arrays) {
                observer.update(this, data);
            }
        }
    }

    /**
     * Sets the changed flag for this Observable. After calling
     * <code>setChanged()</code>, <code>hasChanged()</code> will return
     * true.
     */
    protected void setChanged() {
        changed = true;
    }
}