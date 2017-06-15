package de.hdm.itprojekt.client.gui;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProjektmarktplatzBearbeiten extends VerticalPanel{

	private VerticalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();
	
	private Label marktplatzLabel = new Label ("Projektmarktplatz auswählen");
	private Label marktplatzNameLabel = new Label("Marktplatzname: ");
	private ListBox marktplatzListbox = new ListBox();
	private TextBox marktplatzNameBox = new TextBox();
	
	private Button speichernButton = new Button("Speichern", new SaveChangesClickHandler());

	
	public ProjektmarktplatzBearbeiten(){
		
		//CSS Styles
		speichernButton.setStylePrimaryName("grotte-button");
		
		mainPanel.add(editorPanel);
		editorPanel.add(marktplatzLabel);
		editorPanel.add(marktplatzListbox);
		editorPanel.add(marktplatzNameLabel);
		editorPanel.add(marktplatzNameBox);
		
		
		marktplatzListbox.addItem("Auslesen Funktion fehlt noch");
		
		
		
	}
	private class SaveChangesCallback implements AsyncCallback {

		public void onFailure(Throwable caught) {
			Window.alert("Dat läuft noch nit so!");

		}

		public void onSuccess(Object result) {
			RootPanel.get("Content").clear();

		}

	}
	private class SaveChangesClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			
			try {
				Window.alert("Noch nit implementiert in Impl");
				/*ClientSideSettings.getProjektAdministration().createProjekt(projektNameBox.getText(),
						projektBeschreibungArea.getText(), (startPicker.getValue()), (endPicker.getValue()),
						/* ClientSideSettings.getCurrentUser().getId() *//*1, 1, new CreateProjectCallback());*/
				
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}
	};
	
	
}
