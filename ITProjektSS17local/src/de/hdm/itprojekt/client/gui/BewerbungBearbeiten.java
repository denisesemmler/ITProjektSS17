package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Profil;

/**
 * Klasse, für das Bearbeiten von Bewerbungen
 * 
 * @author Moritz Bittner, Philipp Müller
 *
 */
public class BewerbungBearbeiten extends VerticalPanel {

	private Vector<Bewerbung> bVector = new Vector<Bewerbung>();

	private VerticalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();

	private Label bewerbungLabel = new Label("Bewerbung auswählen:");
	private Label ausschreibenLabel = new Label("Anschreiben ändern:");
	private TextArea bewerbungTextArea = new TextArea();
	private ListBox bewerbungsListbox = new ListBox();

	private Button speichernButton = new Button("Speichern", new SaveChangesClickHandler());

	Profil p = new Profil();

	/**
	 * Konstruktor für Bewerbung bearbeiten
	 */
	public BewerbungBearbeiten() {

		bewerbungTextArea.addStyleName("textarea");

		mainPanel.add(editorPanel);
		editorPanel.add(bewerbungLabel);
		editorPanel.add(bewerbungsListbox);
		editorPanel.add(ausschreibenLabel);
		editorPanel.add(bewerbungTextArea);
		bewerbungsListbox.addChangeHandler(new OnChangeHandler());

		editorPanel.add(speichernButton);

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
	 * Callback für Speichern von Änderungen
	 */
	private class SaveChangesCallback implements AsyncCallback<Void> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");

		}

		public void onSuccess(Void result) {
			Window.alert("Änderungen gespeichert");
			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(new BewerbungVerwalten());

		}

	}

	/**
	 * Callbakck für Auslesen von Bewerbung einer Bewerbung-ID
	 */
	private class GetBewerbungByIdCallback implements AsyncCallback<Vector<Bewerbung>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl was schief gelaufen");
		}

		public void onSuccess(Vector<Bewerbung> result) {
			for (int i = 0; i < result.size(); i++) {
				Bewerbung b1 = result.elementAt(i);
				bVector.add(b1);
				bewerbungsListbox.addItem(b1.getTitel());
			}
			bewerbungTextArea.setText(bVector.elementAt(bewerbungsListbox.getSelectedIndex()).getBewerbungsText());

		}
	}

	/**
	 * Callback für Speichern der Änderungen
	 *
	 */
	private class SaveChangesClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			// Eingabeüberprüfung
			if (bewerbungTextArea.getText().matches("")) {
				Window.alert("Bitte Wert eintragen!");
				return;
			}

			try {
				Bewerbung bewerbung = bVector.elementAt(bewerbungsListbox.getSelectedIndex());
				bewerbung.setBewerbungsText(bewerbungTextArea.getText());
				ClientSideSettings.getProjektAdministration().updateBewerbung(bewerbung, new SaveChangesCallback());

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}
	}

	/**
	 * ClickHandler der auf Änderungen reagiert und entsprechend befüllt
	 */
	private class OnChangeHandler implements ChangeHandler {

		@Override
		public void onChange(ChangeEvent event) {
			try {
				bewerbungTextArea.setText(bVector.elementAt(bewerbungsListbox.getSelectedIndex()).getBewerbungsText());

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}

		}

	}

	/**
	 * Callback für auslesen des Partnerprofil
	 */
	private class GetPartnerProfileCallback implements AsyncCallback<Profil> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");

		}

		public void onSuccess(Profil result) {

			p.setId(result.getId());

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
