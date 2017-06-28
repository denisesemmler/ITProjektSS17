package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Klasse für Verwalten von Bewerbungen
 * 
 * @author Moritz Bittner
 *
 */
public class BewerbungVerwalten extends VerticalPanel {

	private VerticalPanel mainPanel = this;

	// Erstellen von Buttons
	private Button bewerbungAnlegenButton = new Button("Anlegen", new NavigationsButtonHandler());
	private Button bewerbungAndernButton = new Button("Bearbeiten", new NavigationsButtonHandler());
	private Button bewerbungBewertenButton = new Button("Bewerten", new NavigationsButtonHandler());
	private Button bewerbungLoschenButton = new Button("Löschen", new NavigationsButtonHandler());

	/**
	 * Konstruktor der Widgets anfügt
	 */
	public BewerbungVerwalten() {
		RootPanel.get("Content").add(new HTML("<h2>Bewerbungen verwalten</h2>"));
		mainPanel.add(bewerbungAnlegenButton);
		mainPanel.add(bewerbungAndernButton);
		mainPanel.add(bewerbungBewertenButton);
		mainPanel.add(bewerbungLoschenButton);
	}

	/**
	 * ClickHandler, der Button Click abfängt und entsprächende Klasse lädt
	 */
	private class NavigationsButtonHandler implements ClickHandler {
		public void onClick(ClickEvent event) {

			Button active = (Button) event.getSource();

			switch (active.getText()) {
			case "Anlegen":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new AusschreibungAnzeigen());
				break;
			case "Bearbeiten":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new BewerbungBearbeiten());
				break;
			case "Bewerten":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new BewerbungBewerten());
				break;
			case "Löschen":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new BewerbungLoeschen());
				break;
			}
		}
	}
}
