package gwtjt.client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.gwt.core.shared.GWT;

import elemental.client.Browser;
import elemental.dom.Element;
import elemental.dom.Node;
import elemental.dom.NodeList;
import elemental.events.Event;
import elemental.events.EventListener;
import elemental.html.InputElement;
import gwtjt.client.beans.ObservableCollection;
import gwtjt.client.beans.ObservablePropertyChanges;
import gwtjt.client.functions.GwtJtGenericFunction;

public class GwtJtBinder {
  private String id;
  private GwtJt gwtJt;
  private Multimap<GwtJtElement, GwtJtElement> tree = LinkedHashMultimap.create();

  public GwtJtBinder(String id, GwtJt gwtJt) {
    this.id = id;
    this.gwtJt = gwtJt;
    createTree();
    // GWT.log("null -> " + tree.get(null).size());
    print((GwtJtElement) null, "", true);
  }

  public static void bind(String id, GwtJt gwtJt) {
    new GwtJtBinder(id, gwtJt).bind();
  }

  private void print(GwtJtElement element, String prefix, boolean isTail) {
    GWT.log(prefix + (isTail ? "└── " : "├── ") + (element == null ? "ROOT" : element.getTagName()));
    Collection<GwtJtElement> children = tree.get(element);
    for (int i = 0; i < children.size() - 1; i++) {
      print(Iterables.get(children, i), prefix + (isTail ? "    " : "│   "), false);
    }
    if (children.size() >= 1) {
      print(Iterables.get(children, (children.size() - 1)), prefix + (isTail ? "    " : "│   "),
          true);
    }
  }

  // render(parent, element, context)

  private ImmutableMap.Builder<String, Object> initContext() {
    return ImmutableMap.<String, Object> builder().putAll(gwtJt.getObserverables());
  }

  private void r(List<GwtJtElement> appendTo, List<GwtJtElement> elements, int depth) {
    for (GwtJtElement element : elements) {
      if (element.getGwtJt().isPresent()) {
        r(appendTo, element.getGwtJt().get().getElements(), element.getDepth());
      } else {
        element.overrideDepth(element.getDepth() + depth);
        appendTo.add(element);
      }
    }
  }

  private List<GwtJtElement> getElements() {
    List<GwtJtElement> all = new ArrayList<>();
    r(all, gwtJt.getElements(), 0);
    return all;
  }

  private void createTree() {
    GwtJtElement parent = null;
    List<GwtJtElement> breadcumbs = new ArrayList<>();
    breadcumbs.add(null);
    GwtJtElement previousElement = null;
    for (GwtJtElement element : getElements()) {
      int difference = element.getDepth()
          - (previousElement == null ? 0 : previousElement.getDepth());
      GWT.log("put: " + element.getTagName() + " on "
          + (parent == null ? "ROOT" : parent.getTagName()) + " .. " + difference);
      if (difference > 1) {
        // TODO: (more identification information to debug)
        throw new RuntimeException("Invalid element depth, please reduce by " + (1 - difference));
      } else if (difference == 1) {
        // child
        // add to breadcrumb
        parent = previousElement;
        breadcumbs.add(parent);
      } else if (difference == 0) {
        // parent stays the same
      } else {
        // find parent
        for (int i = 0; i < Math.abs(difference); i++) {
          breadcumbs.remove(breadcumbs.size() - 1);
        }
        parent = Iterators.getLast(breadcumbs.iterator());
        GWT.log("parent> " + (parent == null ? "ROOT" : parent.getTagName()));
      }
      tree.put(parent, element);
      previousElement = element;
    }
  }

  private void bind() {
    Element rootDomElement = Browser.getDocument().getElementById(id);
    // Element previousDomElement = rootDomElement;
    // GWT.log("id" + id);
    // int previousDepth = -1;
    // bindx(gwtJt.getElements(), previousDepth, previousDomElement,
    // ImmutableMap.<String, Object> of());
    for (GwtJtElement element : tree.get(null)) {
      attach(element, rootDomElement, initContext().build());
    }
    print(rootDomElement, "", true);
  }

  private void attach(final GwtJtElement element, final Element parent,
      final ImmutableMap<String, Object> contextMap) {

    // TODO: If element has children ensure it does not have innerText

    // TODO: repeat - recursive
    if (!element.getRepeat().isPresent()) {
      Element nextDomElement = createElement(element, contextMap);
      parent.appendChild(nextDomElement);
      GWT.log("1. Appending " + nextDomElement.getTagName() + " to " + parent.getTagName());
      for (GwtJtElement subElement : tree.get(element)) {
        attach(subElement, nextDomElement, contextMap);
      }
    } else {
      final RepeatBinding binding = element.getRepeat().get();

      // Create element for each object
      final Context context = new Context(contextMap);
      Collection<?> collection = binding.getCollection(context);
      for (Object o : binding.getCollection(context)) {
        Element repeatDomElement = createElement(element, contextMap);
        parent.appendChild(repeatDomElement);
        GWT.log("2. Appending " + repeatDomElement.getTagName() + " to " + parent.getTagName());

        // Create elements for each child
        ImmutableMap.Builder<String, Object> subContextBuilder = ImmutableMap.builder();
        subContextBuilder.putAll(contextMap);
        subContextBuilder.put(binding.getAs(), o);
        for (GwtJtElement subElement : tree.get(element)) {
          attach(subElement, repeatDomElement, subContextBuilder.build());
        }
      }

      if (collection instanceof ObservableCollection) {
        final ObservableCollection<?> observableCollection = (ObservableCollection) collection;

        observableCollection.addObserver(new Observer() {
          @Override
          public void update(Observable observable, Object arg) {
            GWT.log("Collection size: " + observableCollection.size());
            removeChildren(parent);
            for (Object o : observableCollection) {
              Element repeatDomElement = createElement(element, contextMap);
              parent.appendChild(repeatDomElement);
              GWT.log("3. Appending " + repeatDomElement.getTagName() + " to "
                  + parent.getTagName());

              // Create elements for each child
              ImmutableMap.Builder<String, Object> subContextBuilder = ImmutableMap.builder();
              subContextBuilder.putAll(contextMap);
              subContextBuilder.put(binding.getAs(), o);
              for (GwtJtElement subElement : tree.get(element)) {
                attach(subElement, repeatDomElement, subContextBuilder.build());
              }
            }
          }

        });
      }
    }
  }

  private Element createElement(final GwtJtElement element, ImmutableMap<String, Object> contextMap) {
    GWT.log("Element being created: <" + element.getTagName().get() + ">");
    final Element nextDomElement = Browser.getDocument().createElement(element.getTagName().get());
    final Context context = new Context(contextMap);

    // InputBinder
    // TODO: checkboxes, radio
    if (element.getInputBinder().isPresent()) {
      final InputBinding binder = element.getInputBinder().get();

      // TODO: instead of manually calling set here, trigger a property changed
      // event for all objects after everything is done loading
      nextDomElement.setAttribute("value", (String) binder.getGetter().apply(binder.getObject()));

      nextDomElement.addEventListener(Event.INPUT, new EventListener() {
        @Override
        public void handleEvent(Event evt) {
          InputElement input = (InputElement) nextDomElement;
          binder.getSetter().apply(binder.getObject(), input.getValue());
        }
      }, false);

      if (isObservable(binder.getObject())) {
        observe(binder.getObject(), new PropertyChangeListener() {
          @Override
          public void propertyChange(PropertyChangeEvent evt) {
            String newValue = (String) binder.getGetter().apply(evt.getSource());
            GWT.log("value change: " + evt.getNewValue());
            nextDomElement.setAttribute("value", newValue);
          }
        });
      }
    }

    // InnerTextFn
    // Call innerText function once and add a listener in each observable
    // object of the context to update (all the time currently -eek- but
    // should be idempodent)
    if (element.getInnerTextFn().isPresent()) {

      // TODO: instead of manually calling set here, trigger a property changed
      // event for all objects after everything is done loading
      String innerText = element.getInnerTextFn().get().apply(context);
      nextDomElement.setInnerText(innerText);

      for (Object o : contextMap.values()) {
        if (isObservable(o)) {
          ObservablePropertyChanges observable = (ObservablePropertyChanges) o;
          observable.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
              String innerText = element.getInnerTextFn().get().apply(context);
              nextDomElement.setInnerText(innerText);
            }
          });
        }
      }
    }

    // Attribute Functions
    // For each attribute function, call it once and add a listener in each
    // observable object of the context to update (all the time currently
    // -eek- but should be idempodent)
    for (final Map.Entry<String, GwtJtGenericFunction<String>> entry : element
        .getAttributeFunctions().entrySet()) {

      // TODO: instead of manually calling set here, trigger a property changed
      // event for all objects after everything is done loading
      String value = entry.getValue().apply(context);
      nextDomElement.setAttribute(entry.getKey(), value);

      for (Object o : contextMap.values()) {
        if (isObservable(o)) {
          ObservablePropertyChanges observable = (ObservablePropertyChanges) o;
          observable.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
              String value = entry.getValue().apply(context);
              nextDomElement.setAttribute(entry.getKey(), value);
            }
          });
        }
      }
    }

    registerEventListeners(element, nextDomElement, context);
    return nextDomElement;
  }

  private void registerEventListeners(GwtJtElement element, Element domElement,
      final Context context) {
    for (final Map.Entry<String, GwtJtGenericFunction<Void>> entry : element.getEventFunctions()
        .entrySet()) {
      GWT.log("registering " + entry.getKey() + " to " + element.getTagName());
      domElement.addEventListener(entry.getKey(), new EventListener() {
        @Override
        public void handleEvent(Event evt) {
          entry.getValue().apply(context);
        }
      }, false);
    }
  }

  private boolean isObservable(Object o) {
    return o instanceof ObservablePropertyChanges;
  }

  private void observe(Object o, PropertyChangeListener propertyChangeListener) {
    ObservablePropertyChanges changes = (ObservablePropertyChanges) o;
    changes.addPropertyChangeListener(propertyChangeListener);
  }

  private Element getParentElement(int previousDepth, GwtJtElement currentJElement,
      Element previousElement) {
    int difference = currentJElement.getDepth() - previousDepth;
    GWT.log("difference: " + difference);
    if (difference > 1) {
      // TODO: (more identification information to debug)
      throw new RuntimeException("Invalid element depth, please reduce by " + (1 - difference));
    } else if (difference == 1) {
      return previousElement;
    } else if (difference == 0) {
      return previousElement.getParentElement();
    }

    for (int i = difference; i != 0; i++) {
      previousElement = previousElement.getParentElement();
    }
    return previousElement;
  }

  private void removeChildren(Element element) {
    NodeList children = element.getChildNodes();
    GWT.log("Removing children: " + children.getLength() + " from <" + element.getTagName() + ">");
    int childrenLength = children.getLength();
    for (int i = 0; i < childrenLength; i++) {
      element.removeChild(children.item(0));
    }
  }

  private void print(Node root, String prefix, boolean isTail) {
    GWT.log(prefix + (isTail ? "└── " : "├── ") + root.getNodeName());
    NodeList children = root.getChildNodes();
    for (int i = 0; i < children.getLength() - 1; i++) {
      print(children.item(i), prefix + (isTail ? "    " : "│   "), false);
    }
    if (children.getLength() >= 1) {
      print(children.item(children.getLength() - 1), prefix + (isTail ? "    " : "│   "), true);
    }
  }
}
