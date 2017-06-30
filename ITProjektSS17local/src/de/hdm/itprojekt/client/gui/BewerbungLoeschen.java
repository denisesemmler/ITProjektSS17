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

import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Profil;

/**
 * Klasse für Löschen eigener Berwerbungen
 * 
 * @author Moritz Bittner
 *
 */
public class BewerbungLoeschen extends VerticalPanel {

	private VerticalPanel mainPanel = this;
	private Label bewerbungNameLabel = new Label("Bewerbung: ");
	private ListBox bewerbungListbox = new ListBox();

	private Button loeschenButton = new Button("Bewerbung zurückziehen", new DeleteClickHandler());

	private Vector<Bewerbung> bVector = new Vector<Bewerbung>();

	Profil p = new Profil();

	/**
	 * Konsturktor für das Löschen von Bewerbungen
	 */
	public BewerbungLoeschen() {
		mainPanel.add(bewerbungNameLabel);
		mainPanel.add(bewerbungListbox);

		mainPanel.add(loeschenButton);

		int currentUserId = ClientSideSettings.getCurrentUser().getId();
		// Get current user
		try {
			ClientSideSettings.getProjektAdministration().getProfilIdCurrentUser(currentUserId,
					new GetPartnerProfileCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Callback für Löschen
	 */
	private class DeleteCallback implements AsyncCallback<Void> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");

		}

		public void onSuccess(Void result) {
			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(new BewerbungVerwalten());

		}

	}

	/**
	 * Alle Bewerbungen des Users auslesen Callback
	 */
	private class GetBewerbungByIdCallback implements AsyncCallback<Vector<Bewerbung>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");
		}

		public void onSuccess(Vector<Bewerbung> result) {
			for (Bewerbung b : result) {
				bewerbungListbox.addItem(b.getTitel());
			}
			for (int i = 0; i < result.size(); i++) {
				Bewerbung b1 = result.elementAt(i);
				bVector.add(b1);
			}
		}
	}

	/**
	 * ClickHandler, der das Löschen veranlasst
	 */
	private class DeleteClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			try {
				int id = bVector.elementAt(bewerbungListbox.getSelectedIndex()).getId();
				Bewerbung b = new Bewerbung();
				b.setId(id);
				ClientSideSettings.getProjektAdministration().deleteBewerbung(b, new DeleteCallback());

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}

	};

	/**
	 * Auslesen des Profil Callback
	 */
	private class GetPartnerProfileCallback implements AsyncCallback<Profil> {

		public void onFailure(Throwable caught) {
			Window.alert("Dat l�uft noch nit so Profil finden!");

		}

		public void onSuccess(Profil result) {

			p.setId(result.getId());
			Window.alert("Dein Profil wurde gefunden!");

			try {
				ClientSideSettings.getProjektAdministration().findBewerbungByTeilnehmerid(p.getId(),
						new GetBewerbungByIdCallback());
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}

		}

	}

}
