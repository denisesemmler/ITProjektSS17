package de.hdm.itprojekt.client.gui;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Profil;

/**
 * Klasse für Anlegen einer Bewerbung
 * 
 * @author Moritz Bittner, Philipp Müller
 *
 */
public class BewerbungAnlegen extends VerticalPanel {

	private VerticalPanel mainPanel = this;

	private Label textLabel = new Label("Anschreiben:");
	private TextArea textA = new TextArea();
	private Label titelLabel = new Label("Bewerbungstitel:");
	private TextBox titelA = new TextBox();

	Button sendenButton = new Button("Senden", new BewerbungSendenClickHandler());

	Date date = new Date();
	private int ausschreibungID;

	Profil p = new Profil();

	/**
	 * Bewerbung auf übergebene Ausschreibung anlegen
	 * 
	 * @param aID
	 *            Ausschreibung auf die User sich bewirbt
	 */
	public BewerbungAnlegen(int aID) {
		
		//CSS-Styling
		textA.addStyleName("textarea");
		textLabel.addStyleName("label1");
		titelLabel.addStyleName("label1");
		titelA.addStyleName("label1");
	
		mainPanel.add(titelLabel);
		mainPanel.add(titelA);
		mainPanel.add(textLabel);
		mainPanel.add(textA);

		mainPanel.add(sendenButton);
		ausschreibungID = aID;

		int currentUserId = ClientSideSettings.getCurrentUser().getId();

		// Auslesen des aktuellen User
		try {
			ClientSideSettings.getProjektAdministration().getProfilIdCurrentUser(currentUserId,
					new GetPartnerProfileCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}

	}

	/**
	 * ClickHandler, der Bewerbung auf ProfilId und AusschreibungID onClick
	 * ausliest
	 */
	private class BewerbungSendenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			//Eingabeüberprüfung
			if (titelA.getText().matches("")) {
				Window.alert("Bitte Wert eintragen!");
				return;
			}
			if (textA.getText().matches("")) {
				Window.alert("Bitte Wert eintragen!");
				return;
			}
			
			ClientSideSettings.getProjektAdministration().findBewerbungByProfilIdAndAusschreibungId(p.getId(),
					ausschreibungID, new BewerbungByProfilIdAndAusschreibungsIdCallback());

		}
	};

	/**
	 * Callback, der bei Bewerbung anlegen
	 */
	private class BewerbungSendenCallback implements AsyncCallback<Bewerbung> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");
		}

		public void onSuccess(Bewerbung result) {
			//Wenn Bewerbung angelegt wurde zurück auf Bewerbung verwalten
			Window.alert("Bewerbung wurde eingereicht");
			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(new BewerbungVerwalten());
		}

	}

	/**
	 * Partnerprofil auslesen Callback
	 */
	private class GetPartnerProfileCallback implements AsyncCallback<Profil> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");

		}

		public void onSuccess(Profil result) {
			//Profil Id speichern
			p.setId(result.getId());


		}

	}

	/**
	 * Bewerbung des Profil auf Ausschreibung auslesen Callback
	 */
	private class BewerbungByProfilIdAndAusschreibungsIdCallback implements AsyncCallback<Bewerbung> {

		@Override
		public void onFailure(Throwable caught) {

		}

		@Override
		public void onSuccess(Bewerbung result) {

			if (result == null) {

				try {

					ClientSideSettings.getProjektAdministration().createBewerbung(textA.getText(), date, 0.0f,
							"Laufend", titelA.getText(), p.getId(), ausschreibungID, new BewerbungSendenCallback());
				} catch (Exception e) {
					Window.alert(e.toString());
					e.printStackTrace();
				}
			} else {
				Window.alert("Bewerbung nicht möglich. Es existiert bereits eine Bewerbung.");
			}

		}

	}
}
