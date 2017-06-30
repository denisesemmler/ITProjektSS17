package de.hdm.itprojekt.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Profil;
import de.hdm.itprojekt.shared.bo.Teilnehmer;

public class ProfilBearbeiten extends VerticalPanel {

	// Vektoren für Eigenschaften erstellen
	private Vector<Eigenschaft> eigenschaften = new Vector<Eigenschaft>();
	private Vector<ListBox> eigenschaftListVector = new Vector<ListBox>();
	private Vector<Label> eigenschaftLabelVector = new Vector<Label>();

	// Panels erstellen
	private VerticalPanel mainPanel = this;
	private VerticalPanel labelsPanel = new VerticalPanel();
	private VerticalPanel eigenschaftenVert = new VerticalPanel();
	private Label firstNameLabel = new Label("Vorname: ");
	private TextBox firstNameBox = new TextBox();
	private Label lastNameLabel = new Label("Nachname: ");
	private TextBox lastNameBox = new TextBox();
	private Label firmaLabel = new Label("Firma: ");
	private TextBox firmaBox = new TextBox();
	private Label zusatzLabel = new Label("Zusatz: ");
	private TextBox zusatzBox = new TextBox();
	private Label strasseLabel = new Label("Straße: ");
	private TextBox strasseBox = new TextBox();
	private Label plzLabel = new Label("PLZ: ");
	private TextBox plzBox = new TextBox();
	private Label ortLabel = new Label("Ort: ");
	private TextBox ortBox = new TextBox();

	private Label kenntnisseLabel = new Label("Deine Fähigkeiten: ");
	// Speicher Button erstellen
	private Button speichern = new Button("Speichern", new CreateTeilnehmerClickHandler());

	// Profil Objekt erstellen
	Profil p = new Profil();

	public ProfilBearbeiten() {

		// CSS Styling
		firstNameLabel.addStyleName("Content-label");
		lastNameLabel.addStyleName("Content-label");
		zusatzLabel.addStyleName("Content-label");
		strasseLabel.addStyleName("Content-label");
		plzLabel.addStyleName("Content-label");
		ortLabel.addStyleName("Content-label");

		mainPanel.add(labelsPanel);

		// Elemente hinzufügen
		labelsPanel.add(firstNameLabel);
		labelsPanel.add(firstNameBox);
		firstNameBox.setText(ClientSideSettings.getCurrentUser().getVorname());

		labelsPanel.add(lastNameLabel);
		labelsPanel.add(lastNameBox);
		lastNameBox.setText(ClientSideSettings.getCurrentUser().getNachname());

		labelsPanel.add(firmaLabel);
		labelsPanel.add(firmaBox);
		firmaBox.setText(ClientSideSettings.getCurrentUser().getFirma());

		labelsPanel.add(zusatzLabel);
		labelsPanel.add(zusatzBox);
		zusatzBox.setText(ClientSideSettings.getCurrentUser().getZusatz());

		labelsPanel.add(strasseLabel);
		labelsPanel.add(strasseBox);
		strasseBox.setText(ClientSideSettings.getCurrentUser().getStrasse());

		labelsPanel.add(plzLabel);
		labelsPanel.add(plzBox);
		plzBox.setText(Integer.toString(ClientSideSettings.getCurrentUser().getPlz()));

		labelsPanel.add(ortLabel);
		labelsPanel.add(ortBox);
		ortBox.setText(ClientSideSettings.getCurrentUser().getOrt());

		labelsPanel.add(kenntnisseLabel);

		// Profil-ID des Users feststellen
		ClientSideSettings.getProjektAdministration()
				.getProfilIdCurrentUser(ClientSideSettings.getCurrentUser().getId(), new GetProfileCallback());

	}

	private class CreateTeilnehmerClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			try {
				// Teilnehmer Daten änderungen zuweisen
				Teilnehmer t = ClientSideSettings.getCurrentUser();
				t.setId(ClientSideSettings.getCurrentUser().getId());
				t.setVorname(firstNameBox.getText());
				t.setNachname(lastNameBox.getText());
				t.setZusatz(zusatzBox.getText());
				t.setStrasse(strasseBox.getText());
				t.setPlz(Integer.parseInt(plzBox.getText()));
				t.setOrt(ortBox.getText());
				ClientSideSettings.getProjektAdministration().updateTeilnehmer(t, new UpdateTeilnehmerCallback());

				// Änderungsdatum des Profils aktualisieren
				Date aenderungsDatum = new Date();
				p.setAenderungsDatum(new java.sql.Date(aenderungsDatum.getTime()));
				ClientSideSettings.getProjektAdministration().updateProfil(p, new UpdateProfileCallback());

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}
	};

	private class GetEigenschaftCallback implements AsyncCallback<Vector<Eigenschaft>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");

		}

		public void onSuccess(Vector<Eigenschaft> result) {

			labelsPanel.add(eigenschaftenVert);
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

				} else if (result.elementAt(i).getName().equals("Höchster Schulabschluss")) {
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
			// Speicher Button hinzufügen
			labelsPanel.add(speichern);

		}

	}

	private class UpdateTeilnehmerCallback implements AsyncCallback<Void> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");

		}

		public void onSuccess(Void result) {
			// Wenn Teilnehmer Daten geändert wurden, dann Eigenschaften des
			// Teilnehmers updaten
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

	private class GetProfileCallback implements AsyncCallback<Profil> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");

		}

		public void onSuccess(Profil result) {
			// Profil ID des Nutzers im Profil Objekt speichern und dann
			// Eigenschaften des Nutzer abrufen
			p.setId(result.getId());
			ClientSideSettings.getProjektAdministration().findNameAndWertFromEigenschaften(p.getId(),
					new GetEigenschaftCallback());

		}

	}

	private class UpdateProfileCallback implements AsyncCallback<Void> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");

		}

		public void onSuccess(Void result) {

		}

	}

	private class UpdateEigenschaftenCallback implements AsyncCallback<Void> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");

		}

		public void onSuccess(Void result) {
			// Wurden Eigenschaften erfolgreich aktualisiert, zurück auf Profil
			// anzeigen
			Window.alert("Dein Profil wurde aktualisiert!");
			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(new ProfilAnzeigen());
		}

	}

}
