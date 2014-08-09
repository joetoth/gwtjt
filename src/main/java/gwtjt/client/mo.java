package gwtjt.client;

import gwtjt.client.autobean.MyFactory;
import gwtjt.client.example.TextExample;
import gwtjt.shared.ISuperModel;
import gwtjt.shared.SimpleName;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanVisitor;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class mo implements EntryPoint {
  private static int _ = 1;
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */


  // Instantiate the factory
  MyFactory factory = GWT.create(MyFactory.class);
  // In non-GWT code, use AutoBeanFactorySource.create(MyFactory.class);

  Observable o;
  /**
   * This is the entry point method.
   */
  @Override
  public void onModuleLoad() {
    GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
      @Override
      public void onUncaughtException(Throwable e) {
        e.printStackTrace();
      }
    });

    SimpleName s = new SimpleName();

    AutoBean<ISuperModel> ism = factory.superModel();

    ism.accept(new AutoBeanVisitor() {
      @Override
      public boolean visitValueProperty(String propertyName, Object value, PropertyContext ctx) {
        ctx.set(value);
        GWT.log("visitVal-" + propertyName);
        return true;
      }
    });

    GWT.log("aftervisti");
    final ISuperModel i = ism.as();
    i.setId(444);
    GWT.log("setted");

    i.setName("aaaa");
    i.setName("aaaa");
    i.setId(233);
    GWT.log("xxxsetted");
    i.getName();
    GWT.log("setted");
    i.getId();

    List<String> abc = new ArrayList<>();
    abc.add("asdf");

    GwtJtBinder.bind("t", new TextExample());
//    GwtJtBinder.bind("t", new Targeting(Arrays.asList("First", "cat")));
  }
}
