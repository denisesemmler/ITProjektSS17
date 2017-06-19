package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;


import de.hdm.itprojekt.shared.bo.Projektmarktplatz;

public class BewerbungAnlegen extends VerticalPanel {

	/**
	 * Erstellen der Panels
	 */
	private VerticalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();
	private VerticalPanel attributePanel = new VerticalPanel();

	/**
	 * Erstellen der Labels
	 */
	private Label marktplatzLabel = new Label("Projektmarktplatz wählen:");

	/**
	 * Erstellen der ListBox
	 */
	private ListBox marktplatzListbox = new ListBox();

	/**
	 * Erstellen der Buttons
	 */
	private Button marktplatzSuchenButton = new Button("Projekte suchen", new SuchenClickHandler());

	private Vector <Projektmarktplatz> pmVector = new Vector<Projektmarktplatz>();
	
	/**
	 * Konstruktor für Anlegen der GUI
	 */
	public BewerbungAnlegen() {

		// CSS Styling
		marktplatzLabel.addStyleName("Content-Label");

		mainPanel.add(editorPanel);

		editorPanel.add(attributePanel);

		attributePanel.add(marktplatzLabel);
		attributePanel.add(marktplatzListbox);
		mainPanel.add(marktplatzSuchenButton);

		try {
			ClientSideSettings.getProjektAdministration().findAllProjektmarktplatz(new GetAllMarktplatzCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}

	}

	private class GetAllMarktplatzCallback implements AsyncCallback<Vector<Projektmarktplatz>> {

		public void onFailure(Throwable caught) {
			Window.alert("Läuft garnit");
		}

		public void onSuccess(Vector<Projektmarktplatz> result) {

			for (int i = 0; i < result.size(); i++) {
				Projektmarktplatz pm1 = result.elementAt(i);
				pmVector.add(pm1);
				marktplatzListbox.addItem(pm1.getBezeichnung());
			}

		}
	}

	private class SuchenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			// TODO Konstruktor für nächste Klasse (Projekt Suchen)

		}
	};
}
