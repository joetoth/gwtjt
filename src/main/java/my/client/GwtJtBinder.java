package my.client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import my.client.beans.MutableObservable;

import com.google.gwt.core.shared.GWT;

import elemental.client.Browser;
import elemental.dom.Element;
import elemental.events.Event;
import elemental.events.EventListener;
import elemental.html.DivElement;
import elemental.html.TextAreaElement;

public class GwtJtBinder {
  private String id;
  private GwtJt gwtJt;
  private Map<MutableObservable, Bound> bindings = new HashMap<>();

  public GwtJtBinder(String id, GwtJt gwtJt) {
    this.id = id;
    this.gwtJt = gwtJt;
  }

  public static void bind(String id, GwtJt gwtJt) {
    new GwtJtBinder(id, gwtJt).bind();
  }

  private static class Bound {
    GwtJtElement element;
    Element domElement;

    public Bound(GwtJtElement element, Element domElement) {
      super();
      this.element = element;
      this.domElement = domElement;
    }
  }

  private void bind() {
    Element previousElement = Browser.getDocument().getElementById(id);
    GWT.log("id" + id);
    for (GwtJtElement element : gwtJt.getElements()) {
                GWT.log("eeee-" + element);
      // 3 types of elements...Base
      // ones with extra properties
      // ones that are a 2 way binding with the model
      Element nextElement = null;
      if (element instanceof GwtJtTextAreaElement) {
                GWT.log("ta-" + element);
        final GwtJtTextAreaElement taElement = (GwtJtTextAreaElement) element;

        final TextAreaElement tt = Browser.getDocument().createTextAreaElement();
        nextElement = tt;

        if (taElement.getModelFn().isPresent()) {
          GwtJtFunction1 modelFn = (GwtJtFunction1) taElement.getModelFn().get();
          Object modelFnResult = modelFn.apply(null);
          if (modelFnResult instanceof MutableObservable) {
            final MutableObservable o = (MutableObservable) modelFnResult;
            bindings.put(o, new Bound(element, tt));
            o.addPropertyChangeListener(new PropertyChangeListener() {

              @Override
              public void propertyChange(PropertyChangeEvent evt) {
//                GWT.log("textare-" + evt.getPropertyName());
//                if (evt.getPropertyName().equals(taElement.getProperty().name())) {
//                  Bound bound = bindings.get(o);
//                  GwtJtFunction1<Object, String> renderer = (GwtJtFunction1<Object, String>) bound.element
//                      .getRenderer().get();
//                  bound.domElement.setInnerText(renderer.apply(o));
//                }
              }
            });
            tt.setOninput(new EventListener() {
              @Override
              public void handleEvent(Event evt) {
                GWT.log("div-" + taElement.getProperty());
                o.set(taElement.getProperty(), tt.getValue());
              }
            });
          } else {
            tt.setValue(taElement.getModelFn().toString());
          }
        }
      }

      if (element instanceof GwtJtDivElement) {
                GWT.log("diveee-" + element);
        final GwtJtDivElement taElement = (GwtJtDivElement) element;

        final DivElement divElement = Browser.getDocument().createDivElement();
        nextElement = divElement;

        if (taElement.getModelFn().isPresent()) {
          GwtJtFunction1 modelFn = (GwtJtFunction1) taElement.getModelFn().get();
          Object modelFnResult = modelFn.apply(null);

          if (modelFnResult instanceof MutableObservable) {
            final MutableObservable o = (MutableObservable) modelFnResult;
            bindings.put(o, new Bound(element, divElement));
            o.addPropertyChangeListener(new PropertyChangeListener() {

              @Override
              public void propertyChange(PropertyChangeEvent evt) {
                GWT.log("div-" + evt.getPropertyName());
                if (evt.getPropertyName().equals(taElement.getProperty().name())) {
                  Bound bound = bindings.get(o);
                  GwtJtFunction1<Object, String> renderer = (GwtJtFunction1<Object, String>) bound.element
                      .getRenderer().get();

                  bound.domElement.setInnerText(renderer.apply(o.get(taElement.getProperty())));
                }
              }
            });
          }
        }
      }

      previousElement.appendChild(nextElement);
    }
  }
}
