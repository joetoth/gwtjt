package my.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import my.shared.FieldVerifier;
import my.shared.ISuperModel;
import my.shared.SimpleName;
import my.shared.SuperModel;

import com.google.common.base.Function;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
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
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  private final Messages messages = GWT.create(Messages.class);
  
  private final ISuperModel sm = new SuperModel();

  // Instantiate the factory
  MyFactory factory = GWT.create(MyFactory.class);
  // In non-GWT code, use AutoBeanFactorySource.create(MyFactory.class);
  
  Observable o;

  /**
   * This is the entry point method.
   */
  @Override
  public void onModuleLoad() {
    SimpleName s = new SimpleName();
    final Button sendButton = new Button(messages.sendButton());
    final TextBox nameField = new TextBox();
    nameField.setText(messages.nameField());
    final Label errorLabel = new Label();

    // We can add style names to widgets
    sendButton.addStyleName("sendButton");

    // Add the nameField and sendButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    RootPanel.get("nameFieldContainer").add(nameField);
    RootPanel.get("sendButtonContainer").add(sendButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);
    
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
    final ObservableList<String> observe = new ObservableList<>(abc);
//    SuperModel sm = new SuperModel();
//
//    Elementular<Void, Element> el = new Elementular<Void, Element>();
//    el.child("ul")
//      .<String>child("li").repeat(observe).renderer(listRenderer());
//    
//    elementular.<String>child("div").renderer(nameRenderer());
//    elementular.<SuperModel>child("textarea").onchange(onChangeSetId());
//    TextAreaElement tt = Browser.getDocument().createTextAreaElement(); 
//    tt.setOnchange(new EventListener() {
//      @Override
//      public void handleEvent(Event evt) {
//        evt.
//        
//      }
//    });
//    tt.setAccessKey("");
//    GwtJt.find("id")
//      .div("#name.blah").classes("fromCssClass").onclick(fn)
//      
//
//    DomManager m = new DomManager(elementular, Browser.getDocument().getElementById("t"));
//    m.manage();
//   
//    Browser.getDocument().createTextAreaElement().
//
//    El container = el.model(sm)
//        .div("div")
//        ._div("div").cls("well")
//        ._input("_input").type("text").model("title")
//        ._br("_br")
//        .__textarea("..textarea").blah().something(".well").model("description")
//          .placeholder("Enter description").model("description").placeholder("Enter description")
//        ._div(".div")
//        .__p().repeat(observe)
//        .___p().inner(descriptionFn)
//        .___div().inner(anotherComponent);
//    Element element = Browser.getDocument().getElementById("t");
//    final TextAreaElement tt = Browser.getDocument().createTextAreaElement();
//    element.appendChild(tt);
//    tt.setOninput(new EventListener() {
//      
//      @Override
//      public void handleEvent(Event evt) {
//        GWT.log(tt.getValue());
//      }
//    });
    
    GwtJtBinder.bind("t", new MyGwtJt());
    


    // Focus the cursor on the name field when the app loads
    nameField.setFocus(true);
    nameField.selectAll();

    // Create the popup dialog box
    final DialogBox dialogBox = new DialogBox();
    dialogBox.setText("Remote Procedure Call");
    dialogBox.setAnimationEnabled(true);
    final Button closeButton = new Button("Close");
    // We can set the id of a widget by accessing its Element
    closeButton.getElement().setId("closeButton");
    final Label textToServerLabel = new Label();
    final HTML serverResponseLabel = new HTML();
    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");
    dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
    dialogVPanel.add(textToServerLabel);
    dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
    dialogVPanel.add(serverResponseLabel);
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
    dialogVPanel.add(closeButton);
    dialogBox.setWidget(dialogVPanel);

    // Add a handler to close the DialogBox
    closeButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        dialogBox.hide();
        sendButton.setEnabled(true);
        sendButton.setFocus(true);
      }
    });

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler, KeyUpHandler {
      /**
       * Fired when the user clicks on the sendButton.
       */
      @Override
      public void onClick(ClickEvent event) {
        observe.add("123");
        // sendNameToServer();
      }

      /**
       * Fired when the user types in the nameField.
       */
      @Override
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          sendNameToServer();
        }
      }

      /**
       * Send the name from the nameField to the server and wait for a response.
       */
      private void sendNameToServer() {
        i.setId(777);
        // First, we validate the input.
        errorLabel.setText("");
        String textToServer = nameField.getText();
        if (!FieldVerifier.isValidName(textToServer)) {
          errorLabel.setText("Please enter at least four characters");
          return;
        }

        // Then, we send the input to the server.
        sendButton.setEnabled(false);
        textToServerLabel.setText(textToServer);
        serverResponseLabel.setText("");
//        greetingService.greetServer(textToServer, new AsyncCallback<Optional<String>>() {
//          @Override
//          public void onFailure(Throwable caught) {
//            // Show the RPC error message to the user
//            dialogBox.setText("Remote Procedure Call - Failure");
//            serverResponseLabel.addStyleName("serverResponseLabelError");
//            serverResponseLabel.setHTML(SERVER_ERROR);
//            dialogBox.center();
//            closeButton.setFocus(true);
//          }
//
//          @Override
//          public void onSuccess(Optional<String> result) {
//            dialogBox.setText("Remote Procedure Call");
//            serverResponseLabel.removeStyleName("serverResponseLabelError");
//            serverResponseLabel.setHTML(result.get());
//            dialogBox.center();
//            closeButton.setFocus(true);
//          }
//        });
      }
    }

    // Add a handler to send the name to the server
    MyHandler handler = new MyHandler();
    sendButton.addClickHandler(handler);
    nameField.addKeyUpHandler(handler);
  }
  
  private Function<String, Integer> onChangeSetId() {
    return new Function<String, Integer>() {
      
      @Override
      public Integer apply(String input) {
        return Integer.valueOf(input);
      }
    };
  }

  private AbstractRenderer<String> listRenderer() {
    return new AbstractRenderer<String>() {
      @Override
      public String render(String object) {
        return object + 'r';
      }
    };
  }
  
  private AbstractRenderer<String> nameRenderer() {
    return new AbstractRenderer<String>() {
      @Override
      public String render(String object) {
        return "my name is jose";
      }
    };
  }
}
