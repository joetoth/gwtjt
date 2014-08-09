package gwtjt.client.old;


public class GwtJtElementCreator {
//  public static interface OnCreate {
//    public void visitDiv(DivElement divElement);
//
//    public void visitTextArea(TextAreaElement textAreaElement);
//  }
//
//  public static <T extends Element> T create(GwtJtElement element, OnCreate onCreate) {
//    Element domElement = null;
//    if (element instanceof GwtJtDivElement) {
//      DivElement divElement = Browser.getDocument().createDivElement();
//      onCreate.visitDiv(divElement);
//      domElement = divElement;
//    } else if (element instanceof GwtJtTextAreaElement) {
//      TextAreaElement textAreaElement = Browser.getDocument().createTextAreaElement();
//      onCreate.visitTextArea(textAreaElement);
//      domElement = textAreaElement;
//    } else if (element instanceof GwtJtAnchorElement) {
//      Element anchorElement = Browser.getDocument().createElement("a");
//      // anchorElement.setHref("http://google.com");
//      // anchorElement.setName("abc");
//      anchorElement.setAttribute("href", "http://google.com");
//      GWT.log("set here");
//      domElement = anchorElement;
//    } else {
//      Element e = Browser.getDocument().createElement(element.getTagName());
//      domElement = e;
//    }
//    return (T) domElement;
//  }
}
