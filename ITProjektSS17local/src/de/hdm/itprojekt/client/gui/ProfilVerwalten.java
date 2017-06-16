package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProfilVerwalten extends VerticalPanel {

	private VerticalPanel mainPanel = this;

	// Erstellen von Buttons
	private Button profilAndernButton = new Button("Bearbeiten", new NavigationsButtonHandler());
	private Button profilLoschenButton = new Button("Loschen", new NavigationsButtonHandler());

	public ProfilVerwalten() {
		RootPanel.get("Content").add(new HTML("<h2>Was willst Du tun?</h2>"));
		mainPanel.add(profilAndernButton);
		mainPanel.add(profilLoschenButton);
	}

	private class NavigationsButtonHandler implements ClickHandler {
		public void onClick(ClickEvent event) {

			Button active = (Button) event.getSource();

			switch (active.getText()) {
			case "Bearbeiten":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new ProfilBearbeiten());
				break;
			case "Loschen":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new ProfilLoeschen());
				break;
			}
		}
	};
}
