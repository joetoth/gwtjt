package gwtjt.client.binder;


public class TextAreaBinder {
//  public static void bind (GwtJtElement jElement, Element element, FieldProxy<String> proxy) {
//    element.setAttribute("value", proxy.get());
//
//            Object model = call(element.getModelFn().get(), scopedModels);
//            if (model instanceof Mutable) {
//              TextAreaBinder.bind(textAreaElement, new FieldProxy<String>((Mutable) model, element
//                  .getProperty().get()));
//            } else {
//              throw new RuntimeException("TextArea must be backed by Mutable field");
//            }
//          }
//  }
//  public static void bind(final TextAreaElement textAreaElement, final FieldProxy<String> proxy) {
//    textAreaElement.setValue(proxy.get());
//    textAreaElement.addEventListener(Event.INPUT, new EventListener() {
//      @Override
//      public void handleEvent(Event evt) {
//        proxy.set(textAreaElement.getValue());
//      }
//    }, false);
//
//    if (proxy.isObservable()) {
//      proxy.observe(new PropertyChangeListener() {
//        @Override
//        public void propertyChange(PropertyChangeEvent evt) {
//          GWT.log("its changin");
//          textAreaElement.setValue((String) evt.getNewValue());
//        }
//      });
//    }
//  }
//
//  public static void bind(final InputElement textAreaElement, final FieldProxy<String> proxy) {
//    textAreaElement.setValue(proxy.get());
//    textAreaElement.addEventListener(Event.INPUT, new EventListener() {
//      @Override
//      public void handleEvent(Event evt) {
//        proxy.set(textAreaElement.getValue());
//      }
//    }, false);
//
//    if (proxy.isObservable()) {
//      proxy.observe(new PropertyChangeListener() {
//        @Override
//        public void propertyChange(PropertyChangeEvent evt) {
//          GWT.log("inpt is changin");
//          textAreaElement.setValue((String) evt.getNewValue());
//        }
//      });
//    }
//  }
}
