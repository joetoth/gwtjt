package my.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Function;
import com.google.gwt.text.shared.AbstractRenderer;

import elemental.dom.Element;

public class Elementular<T, E extends Element> {
  private Elementular<?, ?> parent;
  private List<Elementular<?, ?>> children = new ArrayList<>();

  private Collection<T> repeat;
  // private Object value;
  private String tagName;
  private Function<?, ?> onChange;
  private AbstractRenderer<T> renderer = new AbstractRenderer<T>() {
    @Override
    public String render(T object) {
      return "";
    }
  };

  public Elementular() {
    // TODO Auto-generated constructor stub
  }

  private Elementular(String tagName, Elementular<?, ?> parent) {
    this.parent = parent;
    this.tagName = tagName;
  }

  public <P> Elementular<P, ?> parent() {
    return (Elementular<P, ?>) parent;
  }

  public <C, CE extends Element> Elementular<C, CE> child(String tagName, Element element) {
    Elementular<C, CE> child = new Elementular<C, CE>(tagName, this);
    children.add(child);
    return child;
  }

  public Elementular<T, E> repeat(Collection<T> repeat) {
    this.repeat = repeat;
    return this;
  }

  public Elementular<T, E> renderer(AbstractRenderer<T> renderer) {
    this.renderer = renderer;
    return this;
  }

  // public Elementular value(Object value) {
  // this.value = value;
  // return this;
  // }

  public List<Elementular<?, ?>> getChildren() {
    return children;
  }

  public Elementular<?, ?> getParent() {
    return parent;
  }

  public Collection<?> getRepeat() {
    return repeat;
  }

  // public Object getValue() {
  // return value;
  // }

  public String getTagName() {
    return tagName;
  }

  public AbstractRenderer getRenderer() {
    return renderer;
  }
}
