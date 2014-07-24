package my.client;

import com.google.common.base.Function;

public class GwtJtDivElement extends GwtJtElement {
  String align;
  Function<Context, String> alignFn;

  public GwtJtDivElement(int depth) {
    super(depth);
  }

  public GwtJtDivElement align(String align) {
    this.align = align;
    return this;
  }

  public GwtJtDivElement align(final Function<Context, String> alignFn) {
    this.alignFn = alignFn;
    return this;
  }

  public void run(Context context) {
    align = alignFn.apply(context);
  }

  public String getAlign() {
    return align;
  }
}
