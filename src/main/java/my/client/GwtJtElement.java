package my.client;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Optional;

public class GwtJtElement {
  int depth;
  List<String> classes = new ArrayList<>();
  String innerText;
  GwtJtFunction innerTextFn;
  // Optional<?> model = Optional.absent();
  Optional<GwtJtFunction> modelFn = Optional.absent();
  GwtJtFunction filterFn;
  Enum<?> property;
  private Optional<GwtJtFunction> renderer = Optional.absent();

  public GwtJtElement(int depth) {
    this.depth = depth;
  }

  public int getDepth() {
    return depth;
  }

  public GwtJtElement classes(String... classes) {
    for (String cls : classes) {
      this.classes.add(cls);
    }
    return this;
  }

  public GwtJtElement innerText(String innerText) {
    this.innerText = innerText;
    return this;
  }

  public <T> GwtJtElement innerText(GwtJtFunction1<T, String> innerTextFn) {
    this.innerTextFn = innerTextFn;
    return this;
  }

  public <F, G> GwtJtElement innerText(GwtJtFunction2<F, G, String> innerTextFn) {
    this.innerTextFn = innerTextFn;
    return this;
  }

  public <T> GwtJtElement model(final T model) {
    this.modelFn = Optional.of((GwtJtFunction) new GwtJtFunction1<Void, T>() {
      @Override
      public T apply(Void f) {
        return model;
      }
    });
    return this;
  }

  public <F, T> GwtJtElement filter(GwtJtFunction1<F, T> filterFn) {
    this.filterFn = filterFn;
    return this;
  }

  public <F, T> GwtJtElement model(GwtJtFunction1<F, T> modelFn) {
    this.modelFn = Optional.of((GwtJtFunction) modelFn);
    return this;
  }

  public <F, G, T> GwtJtElement model(GwtJtFunction2<F, G, T> modelFn) {
    this.modelFn = Optional.of((GwtJtFunction) modelFn);
    return this;
  }

  public <E extends Enum<E>> GwtJtElement property(E e) {
    property = e;
    return this;
  }

  public <F> GwtJtElement renderer(GwtJtFunction1<F, String> renderer) {
    this.renderer = Optional.of((GwtJtFunction) renderer);
    return this;
  }

  public Enum<?> getProperty() {
    return property;
  }

  public Optional<GwtJtFunction> getModelFn() {
    return modelFn;
  }

  public <T> Optional<GwtJtFunction> getRenderer() {
    return renderer;
  }

}