package gwtjt.client;

import gwtjt.client.beans.FieldProxy;
import gwtjt.client.beans.MutableObservable;
import gwtjt.client.beans.Setter;
import gwtjt.client.functions.GwtJtGenericFunction;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public class GwtJtElement {

  private int depth;
  private Optional<String> tagName = Optional.absent();
  private final Map<String, GwtJtGenericFunction<String>> attributeFunctions = new HashMap<>();
  private final Map<String, GwtJtGenericFunction<Void>> eventFunctions = new HashMap<>();
  private Optional<GwtJtGenericFunction<String>> innerTextFn = Optional.absent();

  // GwtJtFunction filterFn;

  private Optional<FieldProxy> bindField = Optional.absent();
  private Optional<InputBinding> inputBinder = Optional.absent();

  private Optional<RepeatBinding> repeat = Optional.absent();
  private Optional<GwtJt> gwtJt = Optional.absent();

  public GwtJtElement(int depth, String tagName) {
    this.depth = depth;
    this.tagName = Optional.of(tagName);
  }

  public GwtJtElement(int depth, GwtJt gwtJt) {
    this.depth = depth;
    this.gwtJt = Optional.of(gwtJt);
  }

  public int getDepth() {
    return depth;
  }
  
  protected void overrideDepth(int depth) {
    this.depth = depth;
  }

  public Optional<String> getTagName() {
    return tagName;
  }
  
  public Optional<GwtJt> getGwtJt() {
    return gwtJt;
  }

  public GwtJtElement bind(final MutableObservable mutable, final Enum property) {
    checkValidInputType();
    this.bindField = Optional.of(new FieldProxy(mutable, property));
    return this;
  }

  private void checkValidInputType() {
    if (gwtJt.isPresent()) {
      throw new RuntimeException("Can not bind, gwtJt present");
    }

    if (!tagName.get().equals("textarea") && !tagName.get().equals("input")) {
      throw new RuntimeException("Can not bind, tagName must be textArea or input");
    }
  }

  public <TYPE, FIELD> GwtJtElement bind(TYPE object, Function<TYPE, FIELD> getter,
      Setter<TYPE, String> setter) {
    checkValidInputType();
    this.inputBinder = Optional.of(new InputBinding(object, setter, getter));
    return this;
  }

  public GwtJtElement repeat(final Collection<?> collection, String as) {
    return repeat(new GwtJtGenericFunction<Collection<?>>() {
      @Override
      public Collection<?> apply(Context context) {
        return collection;
      }
    }, as);
  }

  public GwtJtElement repeat(GwtJtGenericFunction<Collection<?>> modelFn, String as) {
    this.repeat = Optional.of(new RepeatBinding(modelFn, as));
    return this;
  }

  public Map<String, GwtJtGenericFunction<Void>> getEventFunctions() {
    return eventFunctions;
  }

  public Map<String, GwtJtGenericFunction<String>> getAttributeFunctions() {
    return attributeFunctions;
  }

  public Optional<GwtJtGenericFunction<String>> getInnerTextFn() {
    return innerTextFn;
  }

  public <T> Optional<FieldProxy> getBindField() {
    return bindField;
  }

  public Optional<RepeatBinding> getRepeat() {
    return repeat;
  }

  // component binding, attribute binding, innerText binding, repeat binding
  public GwtJtElement attr(String name, final String value) {
    return attr(name, new GwtJtGenericFunction<String>() {
      @Override
      public String apply(Context context) {
        return value;
      }
    });
  }

  public GwtJtElement attr(String name, GwtJtGenericFunction<String> attributeFn) {
    // TODO: check function with name doesn't already exist
    attributeFunctions.put(name, attributeFn);
    return this;
  }

  public GwtJtElement event(String name, GwtJtGenericFunction<Void> eventFn) {
    // TODO: Make a list
    eventFunctions.put(name, eventFn);
    return this;
  }

  // InnerText
  public GwtJtElement innerText(final String innerText) {
    return innerText(new GwtJtGenericFunction<String>() {
      @Override
      public String apply(Context context) {
        return innerText;
      }
    });
  }

  public <F> GwtJtElement innerText(GwtJtGenericFunction<String> innerTextFn) {
    this.innerTextFn = Optional.of(innerTextFn);
    return this;
  }

  public Optional<InputBinding> getInputBinder() {
    return inputBinder;
  }

  //
  // // Style
  // public GwtJtElement style(String style) {
  // functionsMap.put(Function.Style, new GwtJtConstantFunction(style));
  // return this;
  // }
  //
  // public <F> GwtJtElement style(GwtJtFunction func) {
  // functionsMap.put(Function.Style, func);
  // return this;
  // }
  //
  // // Click
  // public GwtJtElement onclick(GwtJtFunction func) {
  // eventFunctionsMap.put(EventFunction.Onclick, func);
  // return this;
  // }
}