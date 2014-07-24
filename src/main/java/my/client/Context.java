package my.client;

import java.util.HashMap;
import java.util.Map;

public class Context {
  Map<String, Object> model = new HashMap<>();
  
  public <T>T get(String key) {
    return (T) model.get(key);
  }
  
  public void set(String key, Object value) {
    model.put(key, value);
  }
}
