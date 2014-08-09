package gwtjt.client.beans;

public interface Mutable<E extends Enum<E>> {
  void set(E enum1, Object value);

  <T> T get(E property);
}
