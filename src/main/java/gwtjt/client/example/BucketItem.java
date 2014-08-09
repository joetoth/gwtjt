package gwtjt.client.example;

import gwtjt.client.beans.MutableObservable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BucketItem implements MutableObservable<BucketItem.Property> {
  public enum Property {
    Name
  }

  private transient PropertyChangeSupport support;
  String name;

  public BucketItem(String name) {
    support = new PropertyChangeSupport(this);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    String oldValue = this.name;
    this.name = name;
    support.firePropertyChange(Property.Name.name(), oldValue, name);
  }

  @Override
  public <T> T get(Property property) {
    switch (property) {
    case Name:
      return (T) getName();
    default:
      break;
    }
    return null;
  }

  @Override
  public void set(Property enum1, Object value) {
    switch (enum1) {
    case Name:
      setName((String) value);
      break;
    }
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }
}
