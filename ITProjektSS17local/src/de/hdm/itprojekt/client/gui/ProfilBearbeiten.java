package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.Teilnehmer;


public class ProfilBearbeiten extends VerticalPanel{

	
	private VerticalPanel mainPanel = this;
	VerticalPanel labelsPanel = new VerticalPanel();
	private Label firstNameLabel = new Label("Vorname: "); 
 	private TextBox firstNameBox = new TextBox(); 
 	private Label lastNameLabel = new Label("Nachname: ");
 	private TextBox lastNameBox = new TextBox(); 
 	private Label zusatzLabel = new Label("Zusatz: ");
 	private TextBox zusatzBox = new TextBox(); 
 	private Label strasseLabel = new Label("Straße: ");
 	private TextBox strasseBox = new TextBox(); 
 	private Label plzLabel = new Label("PLZ: ");
 	private TextBox plzBox = new TextBox(); 
 	private Label ortLabel = new Label("Ort: ");
 	private TextBox ortBox = new TextBox(); 
 	private Button speichern = new Button("Speichern", new CreateTeilnehmerClickHandler());
 	
 	public ProfilBearbeiten() {
 		
 		//CSS Styling
 		firstNameLabel.addStyleName("Content-label");
 		lastNameLabel.addStyleName("Content-label");
 		zusatzLabel.addStyleName("Content-label");
 		strasseLabel.addStyleName("Content-label");
 		plzLabel.addStyleName("Content-label");
 		ortLabel.addStyleName("Content-label");
 		
 		mainPanel.add(labelsPanel);
 		
 		//Elemente hinzufügen
 		labelsPanel.add(firstNameLabel);
 		labelsPanel.add(firstNameBox);
 		firstNameBox.setText(ClientSideSettings.getCurrentUser().getVorname());
 		
 		labelsPanel.add(lastNameLabel);
 		labelsPanel.add(lastNameBox);
 		lastNameBox.setText(ClientSideSettings.getCurrentUser().getNachname());
 		
 		labelsPanel.add(zusatzLabel);
 		labelsPanel.add(zusatzBox);
 		zusatzBox.setText(ClientSideSettings.getCurrentUser().getZusatz());
 		
 		labelsPanel.add(strasseLabel);
 		labelsPanel.add(strasseBox);
 		strasseBox.setText(ClientSideSettings.getCurrentUser().getStrasse());
 		
 		labelsPanel.add(plzLabel);
 		labelsPanel.add(plzBox);
 		plzBox.setText(Integer.toString(ClientSideSettings.getCurrentUser().getPlz()));
 		
 		labelsPanel.add(ortLabel);
 		labelsPanel.add(ortBox);
 		ortBox.setText(ClientSideSettings.getCurrentUser().getOrt());
 		
 		labelsPanel.add(speichern);
 	}
 	
 	
 	
 	private class CreateTeilnehmerClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			try {
				Teilnehmer t = ClientSideSettings.getCurrentUser();
				t.setVorname(firstNameBox.getText());
				t.setNachname(lastNameBox.getText());
				t.setZusatz(zusatzBox.getText());
				t.setStrasse(strasseBox.getText());
				t.setPlz(Integer.parseInt(plzBox.getText()));
				t.setOrt(ortBox.getText());
				ClientSideSettings.getProjektAdministration().updateTeilnehmer(t, new UpdateTeilnehmerCallback());
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}
	};
 	
	private class UpdateTeilnehmerCallback implements AsyncCallback {

		public void onFailure(Throwable caught) {
			Window.alert("Dat läuft noch nit so!");

		}

		public void onSuccess(Object result) {
			RootPanel.get("Content").clear();
			Window.alert("Dein Profil wurde aktualisiert!");
		}

	}
}
