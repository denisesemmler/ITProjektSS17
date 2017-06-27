package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Profil;

public class Matching extends  VerticalPanel {
	
	//ben�tigte Vektoren erstellen
	Vector<Ausschreibung> aVector = new Vector<Ausschreibung>();
		
	//Panels erstellen
	VerticalPanel mainPanel = this;
	VerticalPanel matchingPanel = new VerticalPanel();
	
	//Label-Attribute erstellen
	private Label ueberschriftLabel = new Label("F�r dich passende Ausschreibungen");
	private Label passendeAusschreibungLabel = new Label ("Passende Ausschreibungen:");
	
	//Button erstellen
	private Button ausschreibungAnzeigenButton = new Button("Ausschreibung anzeigen & bewerben", new AusschreibungAnzeigenClickHandler());
	
	//ListBox erstellen
	private ListBox passendeAusschreibungListBox = new ListBox();
	

	
	
	public Matching(){
		//matchingPanel dem mainPanel hinzuf�gen, sowie Labels,ListBox und Button hinzuf�gen
		mainPanel.add(matchingPanel);
		matchingPanel.add(ueberschriftLabel);
		matchingPanel.add(passendeAusschreibungLabel);
		matchingPanel.add(passendeAusschreibungListBox);
		matchingPanel.add(ausschreibungAnzeigenButton);
		//matchingPanel.add(bewerbenButton);
		//ProfilId des aktuellen Users finden
		ClientSideSettings.getProjektAdministration().getProfilIdCurrentUser(ClientSideSettings.getCurrentUser().getId(), new FindProfilCallback());
				
		
		
	}
	
	// Wenn ProfilUserId erhalten, dann matching durchf�hren
	private class FindProfilCallback implements AsyncCallback<Profil> {

		public void onFailure(Throwable caught) {
			Window.alert("L�uft garnit");
		}

		public void onSuccess(Profil result) {
			Window.alert("T Profil gefunden");
			ClientSideSettings.getProjektAdministration().matchingAusschreibung(result.getId(), new GetMatchingAusschreibungenCallback());
			
		}
	}
	
	private class GetMatchingAusschreibungenCallback implements AsyncCallback<Vector<Ausschreibung>> {

		public void onFailure(Throwable caught) {
			Window.alert("L�uft garnit");
		}

		public void onSuccess(Vector<Ausschreibung> result) {
			aVector = result;
			// Alle passende Ausschreibung im Vector result in ListBox laden
			for(int i=0; i<result.size(); i++){
				passendeAusschreibungListBox.addItem(aVector.elementAt(i).getTitel());
			}
				
			
		}
	}
	
	private class AusschreibungAnzeigenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			// TODO Callback
			
			try {
				//Ausschreibungs Objekt, dass mit dem in der ListBox gew�hlten Objekt gef�llt wird.
				Ausschreibung a = new Ausschreibung();
				a.setId(aVector.elementAt(passendeAusschreibungListBox.getSelectedIndex()).getId());
				a.setBeschreibung(aVector.elementAt(passendeAusschreibungListBox.getSelectedIndex()).getBeschreibung());
				a.setTitel(aVector.elementAt(passendeAusschreibungListBox.getSelectedIndex()).getTitel());
				a.setBewerbungsfrist(aVector.elementAt(passendeAusschreibungListBox.getSelectedIndex()).getBewerbungsfrist());
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new EinzelAusschreibung(a));
				
				
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
			
		}
	};
	
}
