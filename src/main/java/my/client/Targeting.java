package my.client;

import java.util.Collection;

import my.shared.SuperModel;

import com.google.common.base.Function;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.SimplePanel;

public class Targeting extends SimplePanel {

  // published
  ObservableList<?> items;

  static Element ol = DOM.createElement("ol");

  public Targeting(ObservableList<?> items) {
    super(ol);

//    el("ol").el("li").repeat(items);
//    textarea().set(new Function<F, T>() { })

    // TODO Auto-generated constructor stub
    // <ul><li>items<li></ul>
    // ol().li(Iterator<String>)
    // OListElement ol = Browser.getDocument().createOListElement();
    // LIElement li = Browser.getDocument().createLIElement();
    // container.getElement().appendChild(newChild)
    // li.setTextContent("");
    // ol.appendChild(li);
    // initWidget(container);
    this.items = items;

    items.addListener(new ObservableList.Listener() {

      @Override
      public void onItemsAdded(ObservableList source, Collection items) {
        add(items);
      }

      @Override
      public void onItemsRemoved(ObservableList source, Collection items) {
        // TODO Auto-generated method stub

      }

      @Override
      public void onStracturalChange(ObservableList source) {
        // TODO Auto-generated method stub

      }

    });

    Element li = DOM.createElement("li");
    ol.appendChild(li);
    li.setInnerText("first");
  }
  
  private Function<SuperModel, Void>setName() {
    return new Function<SuperModel, Void>() {
      @Override
      public Void apply(SuperModel input) {
//        input.setName(name);
        return null;
      }
    };
  }

  public void add(Collection items) {
    for (Object object : items) {
      Element li = DOM.createElement("li");
      li.setInnerText(object.toString());
      ol.appendChild(li);
    }
  }

  // @Override
  // protected com.google.gwt.user.client.Element getContainerElement() {
  // return DOM.asOld(ol);
  // }
}
