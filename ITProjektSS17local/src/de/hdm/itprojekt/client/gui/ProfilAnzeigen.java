package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProfilAnzeigen extends HorizontalPanel {
		
	
	private HorizontalPanel mainPanel = this;
	VerticalPanel labelsPanel  = new VerticalPanel();
	VerticalPanel dataPanel = new VerticalPanel ();
	
	private Label firstNameLabel = new Label("Vorname: "); 
	private Label firstNameDataLabel = new Label(ClientSideSettings.getCurrentUser().getVorname());
	
	private Label lastNameLabel = new Label("Nachname: ");
	private Label lastNameDataLabel = new Label(ClientSideSettings.getCurrentUser().getNachname());
	
	private Label zusatzLabel = new Label("Zusatz: ");
	private Label zusatzDataLabel = new Label(ClientSideSettings.getCurrentUser().getZusatz());
	
	private Label strasseLabel = new Label("Straﬂe: ");
	private Label strasseDataLabel = new Label(ClientSideSettings.getCurrentUser().getStrasse());
	
	private Label plzLabel = new Label("PLZ: ");
	private Label plzDataLabel = new Label(Integer.toString(ClientSideSettings.getCurrentUser().getPlz()));
	
	private Label ortLabel = new Label("Ort: ");
	private Label ortDataLabel = new Label(ClientSideSettings.getCurrentUser().getOrt());
	
	
	public ProfilAnzeigen() {
		
		firstNameLabel.addStyleName("Content-label");
 		lastNameLabel.addStyleName("Content-label");
 		zusatzLabel.addStyleName("Content-label");
 		strasseLabel.addStyleName("Content-label");
 		plzLabel.addStyleName("Content-label");
 		ortLabel.addStyleName("Content-label");
		
 		mainPanel.add(labelsPanel);
 		mainPanel.add(dataPanel);
 		
 		RootPanel.get("Content").add(new HTML("<h3>Mein Profil: </h3>"));
 		labelsPanel.add(firstNameLabel);
 		dataPanel.add(firstNameDataLabel);
 		
 		labelsPanel.add(lastNameLabel);
 		dataPanel.add(lastNameDataLabel);
 		
 		if(ClientSideSettings.getCurrentUser().getZusatz() != ""){
 		labelsPanel.add(zusatzLabel);
 		dataPanel.add(zusatzDataLabel);
 		}
 		
 		labelsPanel.add(strasseLabel);
 		dataPanel.add(strasseDataLabel);
 		
 		labelsPanel.add(plzLabel);
 		dataPanel.add(plzDataLabel);
 		
 		labelsPanel.add(ortLabel);
 		dataPanel.add(ortDataLabel);
 		
	}
	
	
	
	
	
}
