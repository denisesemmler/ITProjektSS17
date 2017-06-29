package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * GUI Klasse die das VerticalPanel vererbt bekommt für Verwalten von Projekten
 * 
 * @author Moritz Bittner
 *
 */
public class ProjektVerwalten extends VerticalPanel {

	private VerticalPanel mainPanel = this;

	// Erstellen von Buttons
	private Button projektAnlegenButton = new Button("Anlegen", new NavigationsButtonHandler());
	private Button projektAndernButton = new Button("Bearbeiten", new NavigationsButtonHandler());
	private Button projektLoschenButton = new Button("Löschen", new NavigationsButtonHandler());

	/**
	 * Konstruktor, der Widgets anfügt
	 */
	public ProjektVerwalten() {
		RootPanel.get("Content").add(new HTML("<h2>Projekt verwalten</h2>"));
		mainPanel.add(projektAnlegenButton);
		mainPanel.add(projektAndernButton);
		mainPanel.add(projektLoschenButton);
	}

	/**
	 * Clickhandler der Clcikevents abfängt und je nach click weiterleitet
	 *
	 */
	private class NavigationsButtonHandler implements ClickHandler {
		public void onClick(ClickEvent event) {

			Button active = (Button) event.getSource();

			switch (active.getText()) {
			case "Anlegen":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new ProjektAnlegen());
				break;
			case "Bearbeiten":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new ProjektBearbeiten());
				break;
			case "Löschen":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new ProjektLoeschen());
				break;
			}
		}
	};
}
