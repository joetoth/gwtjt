package gwtjt.client.example;

import gwtjt.client.Context;
import gwtjt.client.GwtJt;
import gwtjt.client.functions.GwtJtGenericFunction;

import java.util.List;

abstract public class LeftSideNavigation extends GwtJt {
  public LeftSideNavigation(List<String> items) {
    _("ul").attr("class", "options");
    __("li").repeat(items, "option"); // ng-class="{selected: option.selected}">
    ___("a").attr("href", "#tab").innerText(name()).event("click", click());
  }

  private GwtJtGenericFunction<String> name() {
    return new GwtJtGenericFunction<String>() {
      @Override
      public String apply(Context context) {
        return context.get("option");
      }
    };
  }

  private GwtJtGenericFunction<Void> click() {
    return new GwtJtGenericFunction<Void>() {

      @Override
      public Void apply(Context context) {
        onClick(context.<String> get("option"));
        return null;
      }
    };
  }

  abstract void onClick(String item);
}
