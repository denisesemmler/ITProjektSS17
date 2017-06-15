package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;



public class AnmeldeFormular extends VerticalPanel{
	
		private Label firstNameLabel = new Label("Vorname: "); 
	 	private TextBox firstNameBox = new TextBox(); 
	 	private Label lastNameLabel = new Label("Nachname: ");
	 	private TextBox lastNameBox = new TextBox(); 
	 	private Label zusatzLabel = new Label("Ort: ");
	 	private TextBox zusatzBox = new TextBox(); 
	 	private Label strasseLabel = new Label("Straﬂe: ");
	 	private TextBox strasseBox = new TextBox(); 
	 	private Label plzLabel = new Label("PLZ: ");
	 	private IntegerBox plzBox = new IntegerBox(); 
	 	private Label ortLabel = new Label("Ort: ");
	 	private TextBox ortBox = new TextBox(); 
	 	private Button speichern = new Button("Speichern", new CreateTeilnehmerClickHandler());
	 	
	 	public AnmeldeFormular() {
	 		
	 		//CSS Styling
	 		firstNameLabel.addStyleName("Content-label");
	 		lastNameLabel.addStyleName("Content-label");
	 		zusatzLabel.addStyleName("Content-label");
	 		strasseLabel.addStyleName("Content-label");
	 		plzLabel.addStyleName("Content-label");
	 		ortLabel.addStyleName("Content-label");
	 		
	 		VerticalPanel labelsPanel = new VerticalPanel();
	 		
	 		
	 		labelsPanel.add(firstNameBox);
	 		labelsPanel.add(lastNameBox);
	 		labelsPanel.add(zusatzBox);
	 		labelsPanel.add(strasseBox);
	 		labelsPanel.add(plzBox);
	 		labelsPanel.add(ortBox);
	 		labelsPanel.add(speichern);
	 	}
	 	
	 	
	 	
	 	private class CreateTeilnehmerClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) {
				// Projekt project = new Projekt();
				try {

					ClientSideSettings.getProjektAdministration().createTeilnehmer(firstNameBox.getText(),
							lastNameBox.getText(), zusatzBox.getText(), strasseBox.getText(), plzBox.getValue(),
							ortBox.getText(), ClientSideSettings.getCurrentUser().getEmail(), 1, new CreateTeilnehmerCallback());
				} catch (Exception e) {
					Window.alert(e.toString());
					e.printStackTrace();
				}

				// Altes Zeug
				Button active = (Button) event.getSource();

				switch (active.getText()) {
				case "Anlegen":
					Window.alert("Deine Daten wurden gespeichert");
					break;
				/**
				 * case "Navigation":
				 * 
				 * RootPanel.get("Content").clear(); RootPanel.get("Navi").add(new
				 * Navigation());
				 **/
				}
			}
		};
	 	
		private class CreateTeilnehmerCallback implements AsyncCallback {

			public void onFailure(Throwable caught) {
				Window.alert("Dat l‰uft noch nit so!");

			}

			public void onSuccess(Object result) {
				RootPanel.get("Content").clear();

			}

		}
}
