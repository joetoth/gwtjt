package my.shared;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import my.client.beans.MutableObservable;

import com.google.gwt.core.shared.GWT;

public class SimpleName implements Serializable, MutableObservable<SimpleName.Property> {
  public enum Property {
    NAME
  }

  private String name;

  private PropertyChangeSupport support;

  public SimpleName() {
    support = new PropertyChangeSupport(this);
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    GWT.log("added listener");
    support.addPropertyChangeListener(listener);
  }

  @Override
  public <T> T get(Property property) {
    switch (property) {
    case NAME:
      return (T) getName();
    default:
      break;
    }
    return null;
  }

  @Override
  public void set(Property property, Object value) {
    Object oldValue = null;
    switch (property) {
    case NAME:
      oldValue = name;
      setName((String) value);
      break;
    default:
      break;
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    String tmp = this.name;
    this.name = name;
    GWT.log("name change!!");
    support.firePropertyChange(Property.NAME.name(), tmp, name);
  }
}
