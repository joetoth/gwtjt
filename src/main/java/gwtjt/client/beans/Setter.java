package gwtjt.client.beans;

public interface Setter<T,V> {
  public void apply(T object, V value);
}
