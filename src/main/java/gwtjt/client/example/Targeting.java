package gwtjt.client.example;

import elemental.client.Browser;
import gwtjt.client.GwtJt;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public class Targeting extends GwtJt {

  interface TargetingResources extends ClientBundle {
    @Source("gwtjt/client/example/TargetingCss.css")
    TargetingCss css();
  } 
  
  TargetingResources rs = GWT.create(TargetingResources.class);

  LeftSideNavigation nav;

  public Targeting(List<String> types) {
    rs.css().ensureInjected();
    
    nav = new LeftSideNavigation(types) {
      @Override
      void onClick(String item) {
        Browser.getWindow().alert(item + " clicked");
      }
    };

    Picker picker = new Picker();
    Cart cart = new Cart();

    _("div").attr("class", rs.css().targetWrapper());
    __(nav);
    __(picker);
    __(cart);
  }
}
