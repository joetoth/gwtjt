package gwtjt.client.example;

import gwtjt.client.beans.MutableObservable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;

public class CopyOfBucket implements MutableObservable<CopyOfBucket.Property> {
  public enum Property {
    Name, Items
  }

  private transient PropertyChangeSupport support;
  String name;
  Collection<BucketItem> items = new ArrayList<>();

  public CopyOfBucket() {
    support = new PropertyChangeSupport(this);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    String oldValue = this.name;
    this.name = name;
    support.firePropertyChange(Property.Name.name(), oldValue, name);
  }
  
  
  
  public void setItems(Collection<BucketItem> items) {
    this.items = items;
  }

  public Collection<BucketItem> getItems() {
    return items;
  }

  @Override
  public <T> T get(Property property) {
    switch (property) {
    case Name:
      return (T) getName();
    case Items:
      return (T) getItems();
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
