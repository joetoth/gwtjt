package my.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import my.client.ObservableList.Listener;
import elemental.client.Browser;
import elemental.dom.Element;

public class DomManager {
  Elementular<?, ?> elementular;
  Element parentElement;
  List<Element> elements = new ArrayList<Element>();

  public DomManager(Elementular<?, ?> elementular, Element parentElement) {
    this.elementular = elementular;
    this.parentElement = parentElement;
    watch();
  }

  public void manage() {
    if (elementular.getRepeat() != null) {
      for (Object o : elementular.getRepeat()) {
        createElement(o);
      }
    } else {
      createElement(null);
    }

  }

  void reset() {
    // remove all
    for (int i = 0; i < parentElement.getChildElementCount(); i++) {
      parentElement.removeChild(parentElement.getChildren().item(i));
    }
    manage();
  }

  Element createElement(Object object) {
    Element elem = Browser.getDocument().createElement(elementular.getTagName());
    elements.add(elem);
    parentElement.appendChild(elem);
    String rendered = elementular.getRenderer().render(object);
    elem.setTextContent(rendered);
    for (Elementular<?, ?> ch : elementular.getChildren()) {
      DomManager dm = new DomManager(ch, parentElement);
      dm.manage();
    }
    return elem;
  }

  private void watch() {
    if (elementular.getRepeat() instanceof ObservableList) {
      ObservableList<Object> o = (ObservableList<Object>) elementular.getRepeat();
      o.addListener(new Listener<Object>() {
        @Override
        public void onItemsAdded(ObservableList<Object> source, Collection<? extends Object> items) {
          reset();
        }

        @Override
        public void onItemsRemoved(ObservableList<Object> source, Collection<? extends Object> items) {
          reset();
        }

        @Override
        public void onStracturalChange(ObservableList<Object> source) {
          reset();
        }
      });
    }

  }
}
