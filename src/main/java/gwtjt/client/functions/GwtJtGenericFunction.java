package gwtjt.client.functions;

import gwtjt.client.Context;

public interface GwtJtGenericFunction<T> extends GwtJtFunction {

  public abstract T apply(Context context);

}