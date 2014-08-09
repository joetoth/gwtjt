package gwtjt.client;

import gwtjt.client.beans.Setter;

import com.google.common.base.Function;

public class InputBinding<TYPE, FIELD> {
  TYPE object;
  Setter<FIELD, ?> setter;
  Function<TYPE, FIELD> getter;

  public InputBinding(TYPE object, Setter<FIELD, ?> setter, Function<TYPE, FIELD> getter) {
    super();
    this.object = object;
    this.setter = setter;
    this.getter = getter;
  }

  public Setter<FIELD, ?> getSetter() {
    return setter;
  }

  public Function<TYPE, FIELD> getGetter() {
    return getter;
  }
  
  public TYPE getObject() {
    return object;
  }
}
