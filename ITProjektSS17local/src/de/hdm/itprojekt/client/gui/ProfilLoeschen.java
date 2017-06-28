package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProfilLoeschen extends VerticalPanel {

	private Label deleteLabel = new Label("Willst du dein Profil wirklich L�schen?");
	private Button deleteButton = new Button("Loeschen", new DeleteTeilnehmerClickHandler());

	ProfilLoeschen() {
		deleteLabel.addStyleName("Content-label");

		this.add(deleteLabel);
		this.add(deleteButton);
	}

	private class DeleteTeilnehmerClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			// Projekt project = new Projekt();
			try {
				/*
				 * ClientSideSettings.getProjektAdministration().
				 * createTeilnehmer(firstNameBox.getText(),
				 * lastNameBox.getText(), zusatzBox.getText(),
				 * strasseBox.getText(), Integer.parseInt(plzBox.getText()),
				 * ortBox.getText(),
				 * ClientSideSettings.getCurrentUser().getEmail(), 1, new
				 * CreateTeilnehmerCallback());
				 */
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}
	};
}
