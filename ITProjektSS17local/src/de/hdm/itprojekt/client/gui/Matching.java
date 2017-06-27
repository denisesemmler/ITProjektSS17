package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Profil;

public class Matching extends  VerticalPanel {
	
	//benötigte Vektoren erstellen
	Vector<Ausschreibung> aVector = new Vector<Ausschreibung>();
	Vector<Eigenschaft> eigenschaftenUser = new Vector<Eigenschaft>();
	Vector<Eigenschaft> eigenschaftenSuche = new Vector<Eigenschaft>();
	
	//Panels erstellen
	VerticalPanel mainPanel = this;
	VerticalPanel matchingPanel = new VerticalPanel();
	
	//Label-Attribute erstellen
	private Label ueberschriftLabel = new Label("Für dich passende Ausschreibungen");
	private Label passendeAusschreibungLabel = new Label ("Passende Ausschreibungen:");
	
	//Button erstellen
	//private Button bewerbenButton = new Button("Jetzt bewerben!", new BewerbenClickHandler());
	
	//ListBox erstellen
	private ListBox passendeAusschreibungListBox = new ListBox();
	
		
	public Matching(){
		//matchingPanel dem mainPanel hinzufügen, sowie Labels,ListBox und Button hinzufügen
		mainPanel.add(matchingPanel);
		matchingPanel.add(ueberschriftLabel);
		matchingPanel.add(passendeAusschreibungLabel);
		matchingPanel.add(passendeAusschreibungListBox);
		//matchingPanel.add(bewerbenButton);
		//ProfilId des aktuellen Users finden
		ClientSideSettings.getProjektAdministration().getProfilIdCurrentUser(ClientSideSettings.getCurrentUser().getId(), new FindProfilCallback());
				
		
		
	}
	
	// Wenn ProfilUserId erhalten, dann matching durchführen
	private class FindProfilCallback implements AsyncCallback<Profil> {

		public void onFailure(Throwable caught) {
			Window.alert("Läuft garnit");
		}

		public void onSuccess(Profil result) {
			Window.alert("T Profil gefunden");
			ClientSideSettings.getProjektAdministration().matchingAusschreibung(result.getId(), new GetMatchingAusschreibungenCallback());
			
		}
	}
	
	private class GetMatchingAusschreibungenCallback implements AsyncCallback<Vector<Ausschreibung>> {

		public void onFailure(Throwable caught) {
			Window.alert("Läuft garnit");
		}

		public void onSuccess(Vector<Ausschreibung> result) {
			// Alle passende Ausschreibung im Vector result in ListBox laden
			for(int i=0; i<result.size(); i++){
				passendeAusschreibungListBox.addItem(result.elementAt(i).getTitel());
			}
				
			
		}
	}
	
}
