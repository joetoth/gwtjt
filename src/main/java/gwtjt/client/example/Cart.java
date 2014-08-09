package gwtjt.client.example;

import gwtjt.client.Context;
import gwtjt.client.GwtJt;
import gwtjt.client.beans.ObservableValue;
import gwtjt.client.beans.Setter;
import gwtjt.client.functions.GwtJtGenericFunction;
import gwtjt.shared.SimpleName;

import java.util.Collection;

import com.google.common.base.Function;
import com.google.gwt.core.shared.GWT;

public class Cart extends GwtJt {

  ObservableValue<String> title;
  SimpleName simpleName = new SimpleName();

  private final Collection<BucketItem> observableItems;
  Bucket b1 = new Bucket();

  public Cart() {
    b1.setName("b1");
    Bucket b2 = new Bucket();
    b2.setName("b2");
    b1.items.add(new BucketItem("first item"));
    b1.items.add(new BucketItem("second "));

    observe("bucket", b1);
    observableItems = observe("items", b1.getItems());

    _("div");
    __("input").attr("type", "text").bind(b1, bucketNameGetter(), bucketNameSetter());
    __("button").innerText("Add").event("click", addItem());
    __("div");
    ___("div").attr("id", "wrapper");
    ____("section").attr("class", "ux-targeting");
    _____("section").attr("class", "cart");
    ______("header").attr("class", "ux-targeting-header");
    _______("h3").innerText("Selected targeting");
    _______("a").attr("class", "remove").event("onclick", remove());
    ______("div").attr("class", "targets");
    _______("div").attr("class", "cart-instructions"); // TODO: hide condition
    ________("p").innerText("Choose targeting values from the left and add them to this cart.");
    _______("dl");// .model(ImmutableList.of(b1, b2));
    ________("dt").innerText(bucketName());
    ________("dd");
    _________("div");
    __________("span").innerText(bucketsEmptyText());
    _________("div").attr("class", "bucket");
    __________("ol");
    ___________("li").repeat(observableItems, "item"); // repeat included items
    ____________("span").repeat(things(), "thing");
    ____________("div").attr("class", "item");
    _____________("ul").attr("class", "crumbs");
    ______________("li"); // .model("{{crumb}}"); //
    _____________("span").innerText(itemName());
    _____________("a").attr("class", "remove").event("click", remove());
  }
  
  private GwtJtGenericFunction<Collection<?>> things() {
    return new GwtJtGenericFunction<Collection<?>>() {
      @Override
      public Collection<?> apply(Context context) {
        return context.<Bucket>get("bucket").getItems();
      }
    };
  }

  private Function<Bucket, String> bucketNameGetter() {
    return new Function<Bucket, String>() {
      @Override
      public String apply(Bucket input) {
        return input.getName();
      }
    };
  }

  private Setter<Bucket, String> bucketNameSetter() {
    return new Setter<Bucket, String>() {
      @Override
      public void apply(Bucket object, String value) {
        object.setName(value);
      }
    };
  }

  private GwtJtGenericFunction<String> itemName() {
    return new GwtJtGenericFunction<String>() {

      @Override
      public String apply(Context context) {
        return context.<BucketItem> get("item").getName();
      }
    };
  }

  private GwtJtGenericFunction<String> bucketsEmptyText() {
    return new GwtJtGenericFunction<String>() {
      @Override
      public String apply(Context context) {
        return "";
//        return "(All " + context.<Bucket> get("bucket").getName() + ")";
      }
    };
  }

  // Can be replace with an interpolation or Java 8 b -> b.getName();
  private GwtJtGenericFunction<String> bucketName() {
    return new GwtJtGenericFunction<String>() {
      @Override
      public String apply(Context c) {
        return "";
//        return c.<Bucket> get("bucket").getName();
      }
    };
  }
  
  private GwtJtGenericFunction<Void> addItem() {
    return new GwtJtGenericFunction<Void>() {
      @Override
      public Void apply(Context context) {
        observableItems.add(new BucketItem(b1.getName()));
        return null;
      }
    };
  }

  private GwtJtGenericFunction<Void> remove() {
    return new GwtJtGenericFunction<Void>() {
      @Override
      public Void apply(Context context) {
        GWT.log("removing");
        observableItems.remove(context.get("item"));
        return null;
      }
    };
  }
}
