package gwtjt.client.autobean;

import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;

public class Magic {
  public static <T> T __intercept(AutoBean<?> bean, T returnValue) {
    GWT.log("intercept:" + bean.getClass() + "--"+returnValue);
    return returnValue;
  }
  
  public static int _() {
   return 1; 
  }
}
