package gwtjt.client.old;


public class CopyOfGwtJtBinder {
// Bind value change elements to field
      // Assume tagName is input or textarea
      // TODO: currently only support input type=text, need to support rest +
      // textarea
      // OLD
      // if (element.getBindField().isPresent()) {
      // final FieldProxy proxy = element.getBindField().get();
      // nextDomElement.setAttribute("value", (String) proxy.get());
      // nextDomElement.addEventListener(Event.INPUT, new EventListener() {
      // @Override
      // public void handleEvent(Event evt) {
      // proxy.set(nextDomElement.getAttribute("value"));
      // }
      // }, false);
      //
      // if (proxy.isObservable()) {
      // proxy.observe(new PropertyChangeListener() {
      // @Override
      // public void propertyChange(PropertyChangeEvent evt) {
      // if (evt.getPropertyName().toLowerCase()
      // .equals(proxy.getProperty().name().toLowerCase())) {
      // GWT.log("value change: " + evt.getNewValue());
      // nextDomElement.setAttribute("value", (String) evt.getNewValue());
      // }
      // }
      // });
      // }
      // }
  // private void callFunctions(GwtJtBaseElement element, Element domElement,
  // List<Object> scopedModels) {
  // for (Map.Entry<GwtJtBaseElement.Function, GwtJtFunction> entry :
  // element.getFunctions()
  // .entrySet()) {
  // Object o = call(entry.getValue(), scopedModels);
  // switch (entry.getKey()) {
  // case Align:
  // break;
  // case ClassName:
  // domElement.setClassName((String) o);
  // break;
  // case InnerText:
  // domElement.setInnerText((String) o);
  // break;
  // case Style:
  // domElement.getStyle().setCssText((String) o);
  // break;
  // }
  // }
  // }
  //
  // private Object call(GwtJtFunction func, List<Object> context) {
  // if (func instanceof GwtJtAttributeFunctionImpl) {
  // GwtJtGenericFunction constFunc = (GwtJtGenericFunction) func;
  // return constFunc.apply();
  // } else if (func instanceof GwtJtFunction1) {
  // GwtJtFunction1 f1 = (GwtJtFunction1) func;
  // return f1.apply(context.get(0));
  // }
  // throw new RuntimeException("Error: " + func.getClass());
  // }

  // private void setIfMutable(Optional<Enum> property, Object model, Object
  // value) {
  // // test if ObserableList/Set/Collection
  // // test if ObserableMap
  // if (model instanceof Mutable) {
  // Mutable mo = (Mutable) model;
  // mo.set(property.get(), value);
  // }
  // }
  
//  private String id;
//  private GwtJt gwtJt;
//  private Map<MutableObservable, Bound> bindings = new HashMap<>();
//
//  public CopyOfGwtJtBinder(String id, GwtJt gwtJt) {
//    this.id = id;
//    this.gwtJt = gwtJt;
//  }
//
//  public static void bind(String id, GwtJt gwtJt) {
//    new CopyOfGwtJtBinder(id, gwtJt).bind();
//  }
//
//  private static class Bound {
//    GwtJtElement element;
//    Element domElement;
//
//    public Bound(GwtJtElement element, Element domElement) {
//      super();
//      this.element = element;
//      this.domElement = domElement;
//    }
//  }
//  // parent
//  // - child
//  // - child
//  // - - grandchild
//
//  private void bind() {
//    Element previousElement = Browser.getDocument().getElementById(id);
//    GWT.log("id" + id);
//    for (GwtJtElement element : gwtJt.getElements()) {
//      GWT.log("eeee-" + element);
//      // 3 types of elements...Base
//      // ones with extra properties
//      // ones that are a 2 way binding with the model
//      Element nextElement = null;
//      if (element instanceof GwtJtTextAreaElement) {
//        GWT.log("ta-" + element);
//        final GwtJtTextAreaElement taElement = (GwtJtTextAreaElement) element;
//
//        final TextAreaElement tt = Browser.getDocument().createTextAreaElement();
//        nextElement = tt;
//
//        if (taElement.getModelFn().isPresent()) {
//          GwtJtFunction1 modelFn = (GwtJtFunction1) taElement.getModelFn().get();
//          Object modelFnResult = modelFn.apply(null);
//          if (modelFnResult instanceof MutableObservable) {
//            final MutableObservable o = (MutableObservable) modelFnResult;
//            bindings.put(o, new Bound(element, tt));
//            o.addPropertyChangeListener(new PropertyChangeListener() {
//
//              @Override
//              public void propertyChange(PropertyChangeEvent evt) {
//                GWT.log("div-" + evt.getPropertyName());
//                if (evt.getPropertyName().equals(taElement.getProperty().name())) {
//                  Bound bound = bindings.get(o);
//                  GwtJtFunction1<Object, String> renderer = (GwtJtFunction1<Object, String>) bound.element
//                      .getRenderer().get();
//
//                  bound.domElement.setInnerText(renderer.apply(o.get(taElement.getProperty())));
//                }
//                // GWT.log("textare-" + evt.getPropertyName());
//                // if
//                // (evt.getPropertyName().equals(taElement.getProperty().name()))
//                // {
//                // Bound bound = bindings.get(o);
//                // GwtJtFunction1<Object, String> renderer =
//                // (GwtJtFunction1<Object, String>) bound.element
//                // .getRenderer().get();
//                // bound.domElement.setInnerText(renderer.apply(o));
//                // }
//              }
//            });
//            tt.setOninput(new EventListener() {
//              @Override
//              public void handleEvent(Event evt) {
//                GWT.log("div-" + taElement.getProperty());
//                o.set(taElement.getProperty(), tt.getValue());
//              }
//            });
//          } else {
//            tt.setValue(taElement.getModelFn().toString());
//          }
//        }
//      }
//
//      if (element instanceof GwtJtDivElement) {
//        GWT.log("diveee-" + element);
//        final GwtJtDivElement taElement = (GwtJtDivElement) element;
//
//        final DivElement divElement = Browser.getDocument().createDivElement();
//        nextElement = divElement;
//
//        if (taElement.getModelFn().isPresent()) {
//          GwtJtFunction1 modelFn = (GwtJtFunction1) taElement.getModelFn().get();
//          Object modelFnResult = modelFn.apply(null);
//
//          if (modelFnResult instanceof MutableObservable) {
//            final MutableObservable o = (MutableObservable) modelFnResult;
//            bindings.put(o, new Bound(element, divElement));
//            o.addPropertyChangeListener(new PropertyChangeListener() {
//
//              @Override
//              public void propertyChange(PropertyChangeEvent evt) {
//                GWT.log("div-" + evt.getPropertyName());
//                if (evt.getPropertyName().equals(taElement.getProperty().name())) {
//                  Bound bound = bindings.get(o);
//                  GwtJtFunction1<Object, String> renderer = (GwtJtFunction1<Object, String>) bound.element
//                      .getRenderer().get();
//
//                  bound.domElement.setInnerText(renderer.apply(o.get(taElement.getProperty())));
//                }
//              }
//            });
//          }
//        }
//      }
//
//      previousElement.appendChild(nextElement);
//    }
//  }
}
