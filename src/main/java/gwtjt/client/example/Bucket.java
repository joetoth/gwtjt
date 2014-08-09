package gwtjt.client.example;

import gwtjt.client.beans.ObservablePropertyChanges;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Bucket implements Serializable, ObservablePropertyChanges {

  private transient PropertyChangeSupport support;
  String name;
  Collection<BucketItem> items = new ArrayList<>();

  public Bucket() {
    support = new PropertyChangeSupport(this);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    String oldValue = this.name;
    this.name = name;
    support.firePropertyChange("name", oldValue, name);
  }
  
  public void setItems(Collection<BucketItem> items) {
    this.items = items;
  }

  public Collection<BucketItem> getItems() {
    return items;
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }
}
