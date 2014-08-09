package gwtjt.client;

import gwtjt.client.beans.ObservableCollection;
import gwtjt.client.functions.GwtJtGenericFunction;

import java.util.Collection;

public class RepeatBinding {
  private final String as;
  private GwtJtGenericFunction<Collection<?>> collection;

  public RepeatBinding(GwtJtGenericFunction<Collection<?>> collection, String as) {
    super();
    this.collection = collection;
    this.as = as;
  }

  public Collection<?> getCollection(Context context) {
    return collection.apply(context);
  }

  public String getAs() {
    return as;
  }

  public boolean isObservable() {
    return collection instanceof ObservableCollection;
  }
}
