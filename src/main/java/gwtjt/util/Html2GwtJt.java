package gwtjt.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Html2GwtJt {

  public static void main(String[] args) throws FileNotFoundException, SAXException, IOException,
      ParserConfigurationException {
    // Get the DOM Builder Factory
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    // Get the DOM Builder
    DocumentBuilder builder = factory.newDocumentBuilder();

    // Load and Parse the XML document
    // document contains the complete XML as a Tree.
    Document document = builder.parse(new FileInputStream(
        "/home/joetoth/projects/targeting/lib/list_picker.html"));

    // Iterating through the nodes and extracting the data.
    NodeList nodeList = document.getDocumentElement().getChildNodes();
    for (int i = 0; i < nodeList.getLength(); i++) {
      print(1, nodeList.item(i));
    }
  }

  private static void print(int depth, Node node) {
    if (!(node instanceof Element)) {
      return;
    }
    Element element = (Element) node;

    for (int i = 0; i < depth; i++) {
      System.out.print("_");
    }
    System.out.print("(\"" + element.getTagName() + "\")");

    NamedNodeMap attrs = element.getAttributes();
    for (int i = 0; i < attrs.getLength(); i++) {
      System.out.print(".attr(\"" + attrs.item(i).getNodeName() + "\", \""
          + attrs.item(i).getNodeValue().replace("\"", "\\\"") + "\")");
    }

    NodeList childNodes = element.getChildNodes();
    for (int j = 0; j < childNodes.getLength(); j++) {
      if (childNodes.item(j) instanceof Text) {
      printInnerText(childNodes.item(j));
      }
    }

    System.out.println(";");

    for (int j = 0; j < childNodes.getLength(); j++) {
      Node cNode = childNodes.item(j);
      print(depth + 1, cNode);
    }
  }

  private static void printInnerText(Node node) {
      String data = node.getTextContent().trim();
      if (!data.equals("")) {
        System.out.print(".innerText(\"" + data + "\")");
      }
  }
}
