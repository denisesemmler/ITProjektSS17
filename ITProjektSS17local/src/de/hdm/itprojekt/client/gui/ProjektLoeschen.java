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

import de.hdm.itprojekt.shared.bo.Projekt;

/**
 * Klasse für Löschen von Projekte
 * 
 * @author Moritz Bittner
 *
 */
public class ProjektLoeschen extends VerticalPanel {

	private Vector<Projekt> pVector = new Vector<Projekt>();

	private VerticalPanel mainPanel = this;

	private Label projektNameLabel = new Label("Projektname: ");

	private ListBox projektListbox = new ListBox();

	private Button projektLoeschenButton = new Button("Löschen", new DeleteClickHandler());

	/**
	 * Konstruktor für Löschen von Projekte
	 */
	public ProjektLoeschen() {
		projektNameLabel.addStyleName("Content-label");

		mainPanel.add(projektNameLabel);
		mainPanel.add(projektListbox);

		mainPanel.add(projektLoeschenButton);

		try {
			ClientSideSettings.getProjektAdministration().findAllProjektByTeilnehmerId(
					ClientSideSettings.getCurrentUser().getId(), new GetAllProjekteByIDCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Löschen Callback
	 *
	 */
	private class DeleteCallback implements AsyncCallback<Void> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");

		}

		public void onSuccess(Void result) {
			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(new ProjektVerwalten());
		}

	}

	/**
	 * Callback, der alle Projekte des Users in Vecotr speichert
	 */
	private class GetAllProjekteByIDCallback implements AsyncCallback<Vector<Projekt>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");
		}

		public void onSuccess(Vector<Projekt> result) {

			for (int i = 0; i < result.size(); i++) {
				Projekt p1 = result.elementAt(i);
				pVector.add(p1);
				projektListbox.addItem(p1.getName());
			}

		}
	}

	/**
	 * Clickhandler, der Löschen des Projekts veranlasst
	 */
	private class DeleteClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			try {

				Projekt p = pVector.elementAt(projektListbox.getSelectedIndex());
				ClientSideSettings.getProjektAdministration().deleteProjekt(p, new DeleteCallback());

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}

	};
}
