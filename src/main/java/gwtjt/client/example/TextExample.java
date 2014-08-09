package gwtjt.client.example;

import gwtjt.client.Context;
import gwtjt.client.GwtJt;
import gwtjt.client.beans.Setter;
import gwtjt.client.functions.GwtJtGenericFunction;

import java.util.Collection;

import com.google.common.base.Function;

public class TextExample extends GwtJt {
  private final Collection<BucketItem> observableItems;
  Bucket b1 = new Bucket();

  public TextExample() {
    b1.setName("b1");
    b1.items.add(new BucketItem("first item"));
    b1.items.add(new BucketItem("second "));

    observe("bucket", b1);
    observableItems = observe("items", b1.getItems()); // IGNORE

    _("div");
    __("input").attr("type", "text").bind(b1, bucketNameGetter(), bucketNameSetter());
 // __("input").attr("type", "text").<Bucket, String>bind(b1, Bucket::getName, Bucket::setName);
    __("button").innerText("Add").event("click", addItem());
    __("span").innerText(bucketName());
    __("div");
    ___("ol");
    ____("li").repeat(observableItems, "item"); // repeat included items
    _____("a").attr("class", "remove").innerText(itemName()).event("click", remove());
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

  private GwtJtGenericFunction<String> bucketName() {
    return new GwtJtGenericFunction<String>() {
      @Override
      public String apply(Context context) {
        return "Bucket Name: " + context.<Bucket>get("bucket").getName();
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

  private GwtJtGenericFunction<Void> addItem() {
    return new GwtJtGenericFunction<Void>() {
      @Override
      public Void apply(Context context) {
        context.<Collection<BucketItem>>get("items").add(new BucketItem(b1.getName()));
        return null;
      }
    };
  }

  private GwtJtGenericFunction<Void> remove() {
    return new GwtJtGenericFunction<Void>() {
      @Override
      public Void apply(Context context) {
        context.<Collection<?>>get("items").remove(context.get("item"));
        return null;
      }
    };
  }
}
