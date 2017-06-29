package de.hdm.itprojekt.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Profil;

public class AusschreibungBearbeiten extends VerticalPanel {

	private Vector<Ausschreibung> aVector = new Vector<Ausschreibung>();
	private Vector<Eigenschaft> eigenschaften = new Vector<Eigenschaft>();
	private Vector<ListBox> eigenschaftListVector = new Vector<ListBox>();
	private Vector<Label> eigenschaftLabelVector = new Vector<Label>();
	/**
	 * Erstellen der Panels
	 */
	private VerticalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();
	private VerticalPanel attributePanel = new VerticalPanel();
	private VerticalPanel eigenschaftenVert = new VerticalPanel();

	/**
	 * Erstellen der Labels
	 */
	private Label ausschreibungLabel = new Label("Zu ändernde Ausschreibung");
	private Label ausschreibungTitelLabel = new Label("Titel der Ausschreibung: ");
	private Label stellenbeschreibungLabel = new Label("Stellenbeschreibung: ");
	private Label bewerbungsfristLabel = new Label("Bewerbungsfrist ändern: ");
	private Label kenntnisseLabel = new Label("Voraussetzungen der Ausschreibung: ");

	private ListBox ausschreibungListbox = new ListBox();

	// Erstellen der TextBoxen und Areas
	private TextBox ausschreibungTitelBox = new TextBox();
	private TextArea stellenbeschreibungArea = new TextArea();

	private DatePicker bewerbungsfrist = new DatePicker();

	/**
	 * Erstellen der Buttons
	 */
	private Button ausschreibungAndernButton = new Button("Änderungen speichern", new SpeichernClickHandler());

	// Profil-Objekt erstellen
	Profil p = new Profil();

	/**
	 * Konstruktor für Anlegen der GUI
	 */
	public AusschreibungBearbeiten() {

		// CSS Styling
		ausschreibungTitelLabel.addStyleName("Content-Label");
		stellenbeschreibungLabel.addStyleName("Content-Label");
		bewerbungsfristLabel.addStyleName("Content-label");

		// Panels anzeigen
		mainPanel.add(editorPanel);

		editorPanel.add(attributePanel);

		attributePanel.add(ausschreibungLabel);
		attributePanel.add(ausschreibungListbox);

		// Change Handler für ListBox hinzufügen
		ausschreibungListbox.addChangeHandler(new OnChangeHandler());

		attributePanel.add(ausschreibungTitelLabel);
		attributePanel.add(ausschreibungTitelBox);

		attributePanel.add(stellenbeschreibungLabel);
		attributePanel.add(stellenbeschreibungArea);

		attributePanel.add(bewerbungsfristLabel);
		attributePanel.add(bewerbungsfrist);

		attributePanel.add(kenntnisseLabel);

		// Ausschreibungen des Nutzers finden
		try {
			ClientSideSettings.getProjektAdministration().findAllAusschreibungByTeilnehmerId(
					ClientSideSettings.getCurrentUser().getId(), new GetAllAusschreibungenCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}
	}

	private class OnChangeHandler implements ChangeHandler {

		@Override
		public void onChange(ChangeEvent event) {
			try { // Bei Änderung der Auswahl in der Listbox, die Daten der
					// geäwhlten Ausschreibung anzeigen
				ausschreibungTitelBox.setText(aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getTitel());
				stellenbeschreibungArea
						.setText(aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getBeschreibung());
				bewerbungsfrist
						.setValue(aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getBewerbungsfrist());
				// Anforderungen des Suchprofils laden
				ClientSideSettings.getProjektAdministration().findNameAndWertFromEigenschaften(
						aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getProfil_idSuchprofil(),
						new GetEigenschaftCallback());
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}

		}

	}

	private class GetAllAusschreibungenCallback implements AsyncCallback<Vector<Ausschreibung>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");
		}

		public void onSuccess(Vector<Ausschreibung> result) {
			// Alle gefundenen Ausschreibungen in Vektor laden
			for (int i = 0; i < result.size(); i++) {
				Ausschreibung aus = result.elementAt(i);
				aVector.add(aus);
				ausschreibungListbox.addItem(aus.getTitel());
			}
			// Daten der ersten Ausschreibung anzeigen
			ausschreibungTitelBox.setText(aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getTitel());
			stellenbeschreibungArea
					.setText(aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getBeschreibung());
			bewerbungsfrist.setValue(aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getBewerbungsfrist());
			ClientSideSettings.getProjektAdministration().findNameAndWertFromEigenschaften(
					aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getProfil_idSuchprofil(),
					new GetEigenschaftCallback());

		}
	}

	private class GetEigenschaftCallback implements AsyncCallback<Vector<Eigenschaft>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");

		}

		public void onSuccess(Vector<Eigenschaft> result) {
			// Zuerst Panel clearen, damit immer nur eigenschaften der aktuellen
			// Ausschreibung gezeigt werden
			eigenschaftenVert.clear();
			attributePanel.add(eigenschaftenVert);

			// Labels und ListBoxen erstellen für Eigenschaften
			for (int i = 0; i < result.size(); i++) {
				HorizontalPanel eigenschaftenPanel = new HorizontalPanel();
				Label abschlussLabel = new Label();
				Label berufserfahrungLabel = new Label();
				Label eigenschaftLabel = new Label();
				ListBox abschlussListBox = new ListBox();
				ListBox berufserfahrungListBox = new ListBox();
				ListBox eigenschaftWertBox = new ListBox();
				eigenschaften.add(result.elementAt(i));

				// Eigenschaften laden und anzeigen
				if (result.elementAt(i).getName().equals("Berufserfahrung")) {
					eigenschaftenVert.add(eigenschaftenPanel);
					eigenschaftenPanel.add(berufserfahrungLabel);
					berufserfahrungLabel.setText(result.elementAt(i).getName());
					eigenschaftLabelVector.add(berufserfahrungLabel);
					eigenschaftenPanel.add(berufserfahrungListBox);
					berufserfahrungListBox.addItem("weniger als 1 Jahr");
					berufserfahrungListBox.addItem("1 - 5 Jahre");
					berufserfahrungListBox.addItem("6 - 10 Jahre");
					berufserfahrungListBox.addItem("mehr als 10 Jahre");
					berufserfahrungListBox.setSelectedIndex(result.elementAt(i).getWert());
					eigenschaftListVector.add(berufserfahrungListBox);

				} else if (result.elementAt(i).getName().equals("Hoechster Schulabschluss")) {
					eigenschaftenVert.add(eigenschaftenPanel);
					eigenschaftenPanel.add(abschlussLabel);
					abschlussLabel.setText(result.elementAt(i).getName());
					eigenschaftLabelVector.add(abschlussLabel);
					eigenschaftenPanel.add(abschlussListBox);
					abschlussListBox.addItem("Hauptschulabschluss");
					abschlussListBox.addItem("Mittlere Reife");
					abschlussListBox.addItem("Fachhochschulreife");
					abschlussListBox.addItem("Abitur");
					abschlussListBox.addItem("Bachelor");
					abschlussListBox.addItem("Master");
					abschlussListBox.setSelectedIndex(result.elementAt(i).getWert());
					eigenschaftListVector.add(abschlussListBox);

				} else {

					eigenschaftenVert.add(eigenschaftenPanel);
					eigenschaftenPanel.add(eigenschaftLabel);
					eigenschaftLabel.setText(result.elementAt(i).getName());
					eigenschaftLabelVector.add(eigenschaftLabel);
					eigenschaftenPanel.add(eigenschaftWertBox);
					eigenschaftWertBox.addItem("Keine Kenntnisse");
					eigenschaftWertBox.addItem("Wenig Kenntnisse");
					eigenschaftWertBox.addItem("Gute Kenntnisse");
					eigenschaftWertBox.setSelectedIndex(result.elementAt(i).getWert());
					eigenschaftListVector.add(eigenschaftWertBox);

				}

			}
			// Änderungs Button hinzufügen
			mainPanel.add(ausschreibungAndernButton);

		}

	}

	private class SpeichernClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			// Bei Speicherung neue Daten im Objekt speichern und updaten sowie
			// Profil updaten
			try {
				int id = aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getId();
				Ausschreibung a = new Ausschreibung();
				a.setId(id);
				a.setTitel(ausschreibungTitelBox.getText());
				a.setBeschreibung(stellenbeschreibungArea.getText());
				a.setBewerbungsfrist(new java.sql.Date((bewerbungsfrist.getValue()).getTime()));
				ClientSideSettings.getProjektAdministration().updateAusschreibung(a, new SpeichernCallback());

				ClientSideSettings.getProjektAdministration().getProfilIdCurrentUser(a.getProfil_idSuchprofil(),
						new GetProfileCallback());

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}
	};

	private class SpeichernCallback implements AsyncCallback<Void> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");

		}

		// Wenn Daten der AUsschreibung geändert, dann Eigenschaften im
		// Suchprofil aktualisieren
		public void onSuccess(Void result) {
			Vector<Eigenschaft> eigenschaftDB = new Vector<Eigenschaft>();
			for (int i = 0; i < eigenschaften.size(); i++) {
				int id = eigenschaften.elementAt(i).getId();

				Eigenschaft e = new Eigenschaft();
				e.setId(id);
				e.setName(eigenschaftLabelVector.elementAt(i).getText());
				e.setWert(eigenschaftListVector.elementAt(i).getSelectedIndex());
				eigenschaftDB.add(e);

			}
			ClientSideSettings.getProjektAdministration().updateEigenschaft(eigenschaftDB,
					new UpdateEigenschaftenCallback());

		}

	}

	private class UpdateEigenschaftenCallback implements AsyncCallback<Void> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");

		}

		public void onSuccess(Void result) {
			// Wenn Eigenschaften auch aktualisiert, RootPanel clearen und auf
			// Übersichtsseite zurück
			Window.alert("Dein Ausschreibung wurde geändert!");
			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(new AusschreibungVerwalten());
		}

	}

	private class GetProfileCallback implements AsyncCallback<Profil> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");

		}

		public void onSuccess(Profil result) {
			// Änderungsdatum des Profils akualisieren
			Date aenderungsDatum = new Date();
			result.setAenderungsDatum(new java.sql.Date(aenderungsDatum.getTime()));
			ClientSideSettings.getProjektAdministration().updateProfil(result, new UpdateProfileCallback());

		}

		private class UpdateProfileCallback implements AsyncCallback<Void> {

			public void onFailure(Throwable caught) {
				Window.alert("Da ist wohl etwas schief gelaufen!");

			}

			public void onSuccess(Void result) {

			}

		}
	}
}