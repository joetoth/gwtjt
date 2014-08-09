package gwtjt.client.functions;


@Deprecated() // Was used instead of Context to pass n number of scoped variables to a function
public interface GwtJtFunction1<F, T> extends GwtJtFunction {
  public T apply(F f);
}
