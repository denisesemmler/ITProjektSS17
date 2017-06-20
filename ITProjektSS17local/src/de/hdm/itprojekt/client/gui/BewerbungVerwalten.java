package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BewerbungVerwalten extends VerticalPanel {

	private VerticalPanel mainPanel = this;

	// Erstellen von Buttons
	private Button bewerbungAnlegenButton = new Button("Anlegen", new NavigationsButtonHandler());
	private Button bewerbungAndernButton = new Button("Bearbeiten", new NavigationsButtonHandler());
	private Button bewerbungLoschenButton = new Button("Loschen", new NavigationsButtonHandler());

	public BewerbungVerwalten() {
		RootPanel.get("Content").add(new HTML("<h2>Was willst Du tun?</h2>"));
		mainPanel.add(bewerbungAnlegenButton);
		mainPanel.add(bewerbungAndernButton);
		mainPanel.add(bewerbungLoschenButton);
	}

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
			case "Loschen":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new BewerbungLoeschen());
				break;
			}
		}
	};
}
