package gwtjt.client;

import com.google.common.collect.ImmutableMap;

public class Context {
  private final ImmutableMap<String, ?> context;

  public Context(ImmutableMap<String, ?> context) {
    this.context = context;
  }

  public <T> T get(String key) {
    if (!context.containsKey(key)) {
      throw new RuntimeException("Cannot find key in context: " + key);
    }
    return (T) context.get(key);
  }
}
