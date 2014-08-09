package gwtjt.client.beans;

import java.beans.PropertyChangeListener;

public class FieldProxy<T> {
  Mutable mutable;
  Enum property;

  public FieldProxy(Mutable mutable, Enum property) {
    this.mutable = mutable;
    this.property = property;
  }

  public void set(Object value) {
    mutable.set(property, value);
  }

  public T get() {
    return (T) mutable.get(property);
  }

  public Enum getProperty() {
    return property;
  }

  public void observe(PropertyChangeListener listener) {
    if (!isObservable()) {
      throw new RuntimeException(mutable.getClass() + "." + property.name() + " is not observable");
    }
    ((ObservablePropertyChanges) mutable).addPropertyChangeListener(listener);
  }

  public boolean isObservable() {
    return mutable instanceof ObservablePropertyChanges;
  }
}
