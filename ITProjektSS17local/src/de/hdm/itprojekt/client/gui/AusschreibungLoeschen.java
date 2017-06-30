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

/**
 * Klasse für das Löschen einer Ausschreibung
 * 
 * @author Moritz Bittner
 *
 */
public class AusschreibungLoeschen extends VerticalPanel {
	// Vektor für Ausschreibungen
	private Vector<Ausschreibung> aVector = new Vector<Ausschreibung>();

	// Panels ertstellen
	private VerticalPanel mainPanel = this;
	private Label ausschreibungLabel = new Label("Ausschreibung: ");
	private ListBox ausschreibungListbox = new ListBox();

	// Löschen-Butten erstellen
	private Button ausschreibungLoeschenButton = new Button("Löschen", new DeleteButtonHandler());

	/**
	 * Konstruktor für Ausschreibung Löschen
	 */
	public AusschreibungLoeschen() {
		// GUI laden
		mainPanel.add(ausschreibungLabel);
		mainPanel.add(ausschreibungListbox);

		mainPanel.add(ausschreibungLoeschenButton);

		// Alle Ausschreibungen des Teilnehmer finden
		try {
			ClientSideSettings.getProjektAdministration().findAllAusschreibungByTeilnehmerId(
					ClientSideSettings.getCurrentUser().getId(), new GetAllAusschreibungenCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Callback, der alle Ausschreibungen des Nutzers ausließt
	 */
	private class GetAllAusschreibungenCallback implements AsyncCallback<Vector<Ausschreibung>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");
		}

		public void onSuccess(Vector<Ausschreibung> result) {
			// Gefundene Ausschreibungen in ListBox laden
			for (int i = 0; i < result.size(); i++) {
				Ausschreibung aus = result.elementAt(i);
				aVector.add(aus);
				ausschreibungListbox.addItem(aus.getTitel());
			}

		}
	}

	/**
	 * Clickhandler, der Löschen einer Ausschreibung veranlasst
	 */
	private class DeleteButtonHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			try {
				// Ausgewählte Ausschreibung löschen
				Ausschreibung a = aVector.elementAt(ausschreibungListbox.getSelectedIndex());
				ClientSideSettings.getProjektAdministration().deleteAusschreibung(a, new DeleteCallback());

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}

		}
	};

	/**
	 * Callback für das Löschen von Ausschreibungn
	 */
	private class DeleteCallback implements AsyncCallback<Void> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");

		}

		public void onSuccess(Void result) {
			// Nach erfolgreicher Löschung, auf Verwalten seite zurück
			Window.alert("Deine Ausschreibung wurde gelöscht!");
			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(new AusschreibungVerwalten());

		}

	}
}
