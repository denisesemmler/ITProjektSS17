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

import de.hdm.itprojekt.shared.bo.Projektmarktplatz;

/**
 * GUI Klasse die das VerticalPanel vererbt bekommt
 * 
 * @author Moritz Bittner
 */

public class ProjektmarktplatzLoeschen extends VerticalPanel {

	// MainPanel um Widgets anzuf�gen
	private VerticalPanel mainPanel = this;
	// Für Marktplatz Auswahl
	private Label marktplatzNameLabel = new Label("Marktplatzname: ");
	private ListBox marktplatzListbox = new ListBox();
	// Button zum Löschen
	private Button loeschenButton = new Button("Löschen", new DeleteClickHandler());
	// Vector der Marktpl�tze speichert
	private Vector<Projektmarktplatz> pmVector = new Vector<Projektmarktplatz>();

	// Konstruktor der bei Laden des Widgets aufgerufen wird
	public ProjektmarktplatzLoeschen() {

		// Hinzuf�gen der Widgets
		mainPanel.add(marktplatzNameLabel);
		mainPanel.add(marktplatzListbox);
		mainPanel.add(loeschenButton);

		// Try f�r alle Marktpl�tze auslesen
		try {
			ClientSideSettings.getProjektAdministration().findAllProjektmarktplatz(new GetAllMarktplatzCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Callback für Löschen der PM
	 * 
	 * @author Moritz Bittner
	 *
	 */
	private class DeleteCallback implements AsyncCallback {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");

		}

		public void onSuccess(Object result) {
			RootPanel.get("Content").clear();

		}

	}

	/**
	 * Callback um alle PM auszulesen und an Listbox anhängen
	 * 
	 * @author Moritz Bittner
	 *
	 */
	private class GetAllMarktplatzCallback implements AsyncCallback<Vector<Projektmarktplatz>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");
		}

		public void onSuccess(Vector<Projektmarktplatz> result) {
			// Anh�ngen aller Elemente an Listbox
			for (Projektmarktplatz p : result) {
				marktplatzListbox.addItem(p.getBezeichnung());
			}
			// Anf�gen der PM an Vector
			for (int i = 0; i < result.size(); i++) {
				Projektmarktplatz pm1 = result.elementAt(i);
				pmVector.add(pm1);
			}
		}
	}

	/**
	 * Clickhandler f�r L�schen der Marktpl�tze
	 * 
	 * @author Moritz Bittner
	 *
	 */
	private class DeleteClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			try {
				int id = pmVector.elementAt(marktplatzListbox.getSelectedIndex()).getId();
				// Zu löschendes Objekt
				Projektmarktplatz pm = new Projektmarktplatz();
				pm.setId(id);
				// Löschen des PM
				ClientSideSettings.getProjektAdministration().deleteProjektmarktplatz(pm, new DeleteCallback());

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}

	};

}
