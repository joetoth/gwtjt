package gwtjt.client.functions;

import gwtjt.client.Context;

public class GwtJtAttributeFunctionImpl implements GwtJtGenericFunction<String> {
  private final String value;

  public GwtJtAttributeFunctionImpl(String value) {
    super();
    this.value = value;
  }

  @Override
  public String apply(Context context) {
    return value;
  }
}
