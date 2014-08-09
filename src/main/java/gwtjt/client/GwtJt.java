package gwtjt.client;

import gwtjt.client.beans.ObservableCollection;
import gwtjt.client.beans.ObservablePropertyChanges;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GwtJt {
  private List<GwtJtElement> elements = new ArrayList<>();
  private Map<String, Object> observables = new HashMap<>();

  // public class ElementChoices {
  // public int depth;
  //
  // public ElementChoices(int depth) {
  // this.depth = depth;
  // }
  //
  // public GwtJtDivElement div() {
  // GwtJtDivElement e = new GwtJtDivElement(depth);
  // elements.add(e);
  // return e;
  // }
  //
  // public GwtJtTextAreaElement textArea() {
  // GwtJtTextAreaElement e = new GwtJtTextAreaElement(depth);
  // elements.add(e);
  // return e;
  // }
  //
  // public GwtJtAnchorElement anchor() {
  // GwtJtAnchorElement e = new GwtJtAnchorElement(depth);
  // elements.add(e);
  // return e;
  // }
  // }
  //
  // private ElementChoices createElement(int depth) {
  // return new ElementChoices(depth);
  // }
  //
  // public ElementChoices _() {
  // return createElement(0);
  // }
  //
  // public ElementChoices __() {
  // return createElement(1);
  // }
  //
  // public ElementChoices ___() {
  // return createElement(2);
  // }
  //
  // public ElementChoices ____() {
  // return createElement(3);
  // }
  //
  // public ElementChoices _____() {
  // return createElement(4);
  // }
  //
  // public ElementChoices ______() {
  // return createElement(5);
  // }
  //
  // public ElementChoices _______() {
  // return createElement(6);
  // }
  //
  // public ElementChoices ________() {
  // return createElement(7);
  // }
  //
  // public ElementChoices _________() {
  // return createElement(8);
  // }
  //
  // public ElementChoices __________() {
  // return createElement(9);
  // }

  public GwtJtElement _(String tagName) {
    GwtJtElement element = new GwtJtElement(0, tagName);
    elements.add(element);
    return element;
  }

  public GwtJtElement __(String tagName) {
    GwtJtElement element = new GwtJtElement(1, tagName);
    elements.add(element);
    return element;
  }

  public GwtJtElement ___(String tagName) {
    GwtJtElement element = new GwtJtElement(2, tagName);
    elements.add(element);
    return element;
  }

  public GwtJtElement ____(String tagName) {
    GwtJtElement element = new GwtJtElement(3, tagName);
    elements.add(element);
    return element;
  }

  public GwtJtElement _____(String tagName) {
    GwtJtElement element = new GwtJtElement(4, tagName);
    elements.add(element);
    return element;
  }

  public GwtJtElement ______(String tagName) {
    GwtJtElement element = new GwtJtElement(5, tagName);
    elements.add(element);
    return element;
  }

  public GwtJtElement _______(String tagName) {
    GwtJtElement element = new GwtJtElement(6, tagName);
    elements.add(element);
    return element;
  }

  public GwtJtElement ________(String tagName) {
    GwtJtElement element = new GwtJtElement(7, tagName);
    elements.add(element);
    return element;
  }

  public GwtJtElement _________(String tagName) {
    GwtJtElement element = new GwtJtElement(8, tagName);
    elements.add(element);
    return element;
  }

  public GwtJtElement __________(String tagName) {
    GwtJtElement element = new GwtJtElement(9, tagName);
    elements.add(element);
    return element;
  }

  public GwtJtElement ___________(String tagName) {
    GwtJtElement element = new GwtJtElement(10, tagName);
    elements.add(element);
    return element;
  }

  public GwtJtElement ____________(String tagName) {
    GwtJtElement element = new GwtJtElement(11, tagName);
    elements.add(element);
    return element;
  }

  public GwtJtElement _____________(String tagName) {
    GwtJtElement element = new GwtJtElement(12, tagName);
    elements.add(element);
    return element;
  }

  public GwtJtElement ______________(String tagName) {
    GwtJtElement element = new GwtJtElement(13, tagName);
    elements.add(element);
    return element;
  }
  public GwtJtElement _(GwtJt gwtJt) {
    GwtJtElement element = new GwtJtElement(0, gwtJt);
    elements.add(element);
    return element;
  }

  public GwtJtElement __(GwtJt gwtJt) {
    GwtJtElement element = new GwtJtElement(1, gwtJt);
    elements.add(element);
    return element;
  }

  public List<GwtJtElement> getElements() {
    return elements;
  }

  public void observe(String key, ObservablePropertyChanges value) {
    observables.put(key, value);
  }
  
  public <T> ObservableCollection<T> observe(String key, Collection<T> collection) {
    ObservableCollection<T> o = new ObservableCollection<T>(collection);
    observables.put(key, o);
    return o;
  }

  public void bind(String key, Object value) {
    observables.put(key, value);
  }

  public Map<String, Object> getObserverables() {
    return observables;
  }
}
