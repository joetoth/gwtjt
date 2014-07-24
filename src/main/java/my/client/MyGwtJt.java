package my.client;

import java.util.List;

import my.shared.Child;
import my.shared.SimpleName;
import my.shared.SuperModel;

import com.google.common.base.Function;

public class MyGwtJt extends GwtJt {
  // Out -> In
  // Chain specific element to base element
  
  // TODO: HasValue adapter

  ObservableValue<String> title;
  SimpleName simpleName = new SimpleName();
  
  public MyGwtJt() {
    _().textArea().model(simpleName).property(SimpleName.Property.NAME);
    _().div().classes("red").renderer(innerText()).model(simpleName).property(SimpleName.Property.NAME);
  }
  
  public MyGwtJt(String id, String group, SuperModel superModel, ObservableList<String> names) {

//    _().textArea().model(simpleName).<String, String>filter(t -> t);
//    _().div().classes("red").model(simpleName).property(SimpleName.Property.NAME);

//    _().div().classes("red");
//    _().div().align(alignFn()).classes("red").model(superModel);
//
//    _().div().model(superModel.getChildren());
//    __().div().innerText(childInnerFn()).model(toyListFn());
////    __().div().innerText(childInnerFn()).<Child, List<String>> model(c -> c.getToys());
//    ___().div().innerText(nameToyFn());
//    ___().div().<Child, String>innerText((child, toy) -> "rinme" + child.getName() + "toy" + toy);
  }

  private GwtJtFunction2<Child, String, String> nameToyFn() {
    return new GwtJtFunction2<Child, String, String>() {
      @Override
      public String apply(Child child, String toy) {
        return "toy: " + toy;
      }
    };
  }

  private GwtJtFunction1<Child, List<String>> toyListFn() {
    return new GwtJtFunction1<Child, List<String>>() {

      @Override
      public List<String> apply(Child input) {
        return input.getToys();
      }
    };
  }

  private GwtJtFunction1<Child, String> childInnerFn() {
    return new GwtJtFunction1<Child, String>() {
      @Override
      public String apply(Child input) {
        return "-" + input.getName() + "-";
      }
    };
  }

  private GwtJtFunction1<String, String> innerText() {
    return new GwtJtFunction1<String, String>() {
      @Override
      public String apply(String input) {
        return "-" + input + "-";
      }
    };
  }

  private Function<Context, String> alignFn() {
    return new Function<Context, String>() {
      @Override
      public String apply(Context input) {
        return "right";
      }
    };
  }

}
