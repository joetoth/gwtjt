package my.client;

import java.util.ArrayList;
import java.util.List;

public class GwtJt {
  private List<GwtJtElement> elements = new ArrayList<>();

  public class ElementChoices {
    public int depth;

    public ElementChoices(int depth) {
      this.depth = depth;
    }

    public GwtJtDivElement div() {
      GwtJtDivElement e = new GwtJtDivElement(depth);
      elements.add(e);
      return e;
    }

    public GwtJtTextAreaElement textArea() {
      GwtJtTextAreaElement e = new GwtJtTextAreaElement(depth);
      elements.add(e);
      return e;
    }
  }

  private ElementChoices createElement(int depth) {
    return new ElementChoices(depth);
  }

  public ElementChoices _() {
    return createElement(0);
  }

  public ElementChoices __() {
    return createElement(1);
  }

  public ElementChoices ___() {
    return createElement(2);
  }

  public List<GwtJtElement> getElements() {
    return elements;
  }

  public static void main(String[] args) {

  }
}
