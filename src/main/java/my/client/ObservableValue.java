package my.client;

import java.util.LinkedList;

public class ObservableValue<T> implements Observable {
  T t;
  private LinkedList<Listener<T>> listeners = new LinkedList<>();

  public void addListener(Listener<T> l) {
    listeners.add(l);
  }

  public void removeListener(Listener<T> l) {
    listeners.remove(l);
  }

  public ObservableValue(T t) {
    this.t = t;
  }

  public void set(T t) {
    this.t = t;
  }

  public T get() {
    return t;
  }

  public static interface Listener<T> {
    void onUpdate(T before, T after);
  }
}
