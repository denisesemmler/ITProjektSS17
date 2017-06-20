package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProfilAnzeigen extends VerticalPanel {
		
	
	private VerticalPanel mainPanel = this;
	VerticalPanel labelsPanel  = new VerticalPanel();
	private Label firstNameLabel = new Label("Vorname: " + ClientSideSettings.getCurrentUser().getVorname()); 
	private Label lastNameLabel = new Label("Nachname: " + ClientSideSettings.getCurrentUser().getNachname());
	private Label zusatzLabel = new Label("Zusatz: " + ClientSideSettings.getCurrentUser().getZusatz());
	private Label strasseLabel = new Label("Straﬂe: " + ClientSideSettings.getCurrentUser().getStrasse());
	private Label plzLabel = new Label("PLZ: " + Integer.toString(ClientSideSettings.getCurrentUser().getPlz()));
	private Label ortLabel = new Label("Ort: " + ClientSideSettings.getCurrentUser().getOrt());
	
	
	public ProfilAnzeigen() {
		
		firstNameLabel.addStyleName("Content-label");
 		lastNameLabel.addStyleName("Content-label");
 		zusatzLabel.addStyleName("Content-label");
 		strasseLabel.addStyleName("Content-label");
 		plzLabel.addStyleName("Content-label");
 		ortLabel.addStyleName("Content-label");
		
 		mainPanel.add(labelsPanel);
 		
 		RootPanel.get("Content").add(new HTML("<h3>Mein Profil: </h3>"));
 		labelsPanel.add(firstNameLabel);
 		labelsPanel.add(lastNameLabel);
 		labelsPanel.add(zusatzLabel);
 		labelsPanel.add(strasseLabel);
 		labelsPanel.add(plzLabel);
 		labelsPanel.add(ortLabel);
 		
	}
	
	
	
	
	
}
