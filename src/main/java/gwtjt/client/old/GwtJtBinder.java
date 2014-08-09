package gwtjt.client.old;


public class GwtJtBinder {
//  private String id;
//  private GwtJt gwtJt;
//
//  public GwtJtBinder(String id, GwtJt gwtJt) {
//    this.id = id;
//    this.gwtJt = gwtJt;
//  }
//
//  public static void bind(String id, GwtJt gwtJt) {
//    new GwtJtBinder(id, gwtJt).bind();
//  }
//
//  private ImmutableMap.Builder<String, Object> initContext() {
//    return ImmutableMap.<String, Object> builder().putAll(gwtJt.getObserverables());
//  }
//
//  private void bind() {
//    Element rootDomElement = Browser.getDocument().getElementById(id);
//    Element previousDomElement = rootDomElement;
//    GWT.log("id" + id);
//    int previousDepth = -1;
//    bindx(gwtJt.getElements(), previousDepth, previousDomElement,
//        Optional.<Map<String, Object>> absent());
//    print(rootDomElement, "", true);
//  }
//
//  private void bindx(final List<GwtJtElement> elements, int previousDepth,
//      Element previousDomElement, Optional<Map<String, Object>> subObject) {
//
//    for (int i = 0; i < elements.size(); i++) {
//      final GwtJtElement element = elements.get(i);
//
//      ImmutableMap.Builder<String, Object> contextBuilder = initContext();
//      if (subObject.isPresent()) {
//        for (Map.Entry<String, Object> entry : subObject.get().entrySet()) {
//          contextBuilder.put(entry.getKey(), entry.getValue());
//        }
//      }
//      ImmutableMap<String, Object> contextMap = contextBuilder.build();
//
//      // TODO: If element has children ensure it does not have innerText
//
//      // TODO: repeat - recursive
//      if (!element.getRepeat().isPresent()) {
//        final Element nextDomElement = createElement(element, contextMap);
//        getParentElement(previousDepth, element, previousDomElement).appendChild(nextDomElement);
//      } else {
//        final RepeatBinding binding = element.getRepeat().get();
//        final List<GwtJtElement> subElements = new ArrayList<>();
//        GWT.log("elements: " + elements.size() + " - " + (i + 1));
//        if (i + 1 < elements.size()) {
//          int subDepth = elements.get(i + 1).getDepth();
//          GWT.log("subDepth: " + subDepth);
//          while (element.getDepth() != subDepth) {
//            i++;
//            GWT.log("i: " + i);
//            if (i == elements.size()) {
//              break;
//            }
//            subElements.add(elements.get(i));
//          }
//        }
//
//        for (Object o : binding.<Collection<?>> getCollection()) {
//          bindx(subElements, element.getDepth(), nextDomElement, Optional.of(o), binding.getAs());
//        }
//
//        if (binding.isObservable()) {
//          final ObservableCollection<?> collection = binding.getCollection();
//          collection.addObserver(new Observer() {
//            @Override
//            public void update(Observable observable, Object arg) {
//              GWT.log("Collection size: " + collection.size());
//              removeChildren(nextDomElement.getParentElement());
//              for (Object o : collection) {
//                bindx(subElements, element.getDepth(), nextDomElement, Optional.of(o),
//                    binding.getAs());
//              }
//            }
//          });
//        }
//      }
//
//      previousDomElement = nextDomElement;
//      previousDepth = element.getDepth();
//    }
//  }
//
//  private Element createElement(final GwtJtElement element, ImmutableMap<String, Object> contextMap) {
//    final Element nextDomElement = Browser.getDocument().createElement(element.getTagName());
//    final Context context = new Context(contextMap);
//
//    // InputBinder
//    // TODO: checkboxes, radio
//    if (element.getInputBinder().isPresent()) {
//      final InputBinding binder = element.getInputBinder().get();
//
//      // TODO: instead of manually calling set here, trigger a property changed
//      // event for all objects after everything is done loading
//      nextDomElement.setAttribute("value", (String) binder.getGetter().apply(binder.getObject()));
//
//      nextDomElement.addEventListener(Event.INPUT, new EventListener() {
//        @Override
//        public void handleEvent(Event evt) {
//          binder.getSetter().apply(binder.getObject(), nextDomElement.getAttribute("value"));
//        }
//      }, false);
//
//      if (isObservable(binder.getObject())) {
//        observe(binder.getObject(), new PropertyChangeListener() {
//          @Override
//          public void propertyChange(PropertyChangeEvent evt) {
//            String newValue = (String) binder.getGetter().apply(evt.getSource());
//            GWT.log("value change: " + evt.getNewValue());
//            nextDomElement.setAttribute("value", newValue);
//          }
//        });
//      }
//    }
//
//    // InnerTextFn
//    // Call innerText function once and add a listener in each observable
//    // object of the context to update (all the time currently -eek- but
//    // should be idempodent)
//    if (element.getInnerTextFn().isPresent()) {
//
//      // TODO: instead of manually calling set here, trigger a property changed
//      // event for all objects after everything is done loading
//      String innerText = element.getInnerTextFn().get().apply(context);
//      nextDomElement.setInnerText(innerText);
//
//      for (Object o : contextMap.values()) {
//        if (isObservable(o)) {
//          ObservablePropertyChanges observable = (ObservablePropertyChanges) o;
//          observable.addPropertyChangeListener(new PropertyChangeListener() {
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//              String innerText = element.getInnerTextFn().get().apply(context);
//              nextDomElement.setInnerText(innerText);
//            }
//          });
//        }
//      }
//    }
//
//    // Attribute Functions
//    // For each attribute function, call it once and add a listener in each
//    // observable object of the context to update (all the time currently
//    // -eek- but should be idempodent)
//    for (final Map.Entry<String, GwtJtGenericFunction<String>> entry : element
//        .getAttributeFunctions().entrySet()) {
//
//      // TODO: instead of manually calling set here, trigger a property changed
//      // event for all objects after everything is done loading
//      String value = entry.getValue().apply(context);
//      nextDomElement.setAttribute(entry.getKey(), value);
//
//      for (Object o : contextMap.values()) {
//        if (isObservable(o)) {
//          ObservablePropertyChanges observable = (ObservablePropertyChanges) o;
//          observable.addPropertyChangeListener(new PropertyChangeListener() {
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//              String value = entry.getValue().apply(context);
//              nextDomElement.setAttribute(entry.getKey(), value);
//            }
//          });
//        }
//      }
//    }
//
//    registerEventListeners(element, nextDomElement, context);
//    return nextDomElement;
//  }
//
//  private void registerEventListeners(GwtJtElement element, Element domElement,
//      final Context context) {
//    for (final Map.Entry<String, GwtJtGenericFunction<Void>> entry : element.getEventFunctions()
//        .entrySet()) {
//      GWT.log("registering " + entry.getKey() + " to " + element.getTagName());
//      domElement.addEventListener(entry.getKey(), new EventListener() {
//        @Override
//        public void handleEvent(Event evt) {
//          entry.getValue().apply(context);
//        }
//      }, false);
//    }
//  }
//
//  private boolean isObservable(Object o) {
//    return o instanceof ObservablePropertyChanges;
//  }
//
//  private void observe(Object o, PropertyChangeListener propertyChangeListener) {
//    ObservablePropertyChanges changes = (ObservablePropertyChanges) o;
//    changes.addPropertyChangeListener(propertyChangeListener);
//  }
//
//  private Element getParentElement(int previousDepth, GwtJtElement currentJElement,
//      Element previousElement) {
//    int difference = currentJElement.getDepth() - previousDepth;
//    GWT.log("difference: " + difference);
//    if (difference > 1) {
//      // TODO: (more identification information to debug)
//      throw new RuntimeException("Invalid element depth, please reduce by " + (1 - difference));
//    } else if (difference == 1) {
//      return previousElement;
//    } else if (difference == 0) {
//      return previousElement.getParentElement();
//    }
//
//    for (int i = difference; i != 0; i++) {
//      previousElement = previousElement.getParentElement();
//    }
//    return previousElement;
//  }
//
//  private void removeChildren(Element element) {
//    NodeList children = element.getChildNodes();
//    GWT.log("Removing children: " + children.getLength());
//    for (int i = 0; i < children.getLength(); i++) {
//      element.removeChild(children.item(i));
//    }
//  }
//
//  private void print(Node root, String prefix, boolean isTail) {
//    GWT.log(prefix + (isTail ? "└── " : "├── ") + root.getNodeName());
//    NodeList children = root.getChildNodes();
//    for (int i = 0; i < children.getLength() - 1; i++) {
//      print(children.item(i), prefix + (isTail ? "    " : "│   "), false);
//    }
//    if (children.getLength() >= 1) {
//      print(children.item(children.getLength() - 1), prefix + (isTail ? "    " : "│   "), true);
//    }
//  }
}
