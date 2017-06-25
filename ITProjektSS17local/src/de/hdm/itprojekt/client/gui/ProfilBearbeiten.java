package de.hdm.itprojekt.client.gui;

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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Profil;
import de.hdm.itprojekt.shared.bo.Teilnehmer;

public class ProfilBearbeiten extends VerticalPanel {
	Vector <Eigenschaft> eigenschaften = new Vector<Eigenschaft>();
	Vector <ListBox> eigenschaftListVector = new Vector<ListBox>();
	Vector <Label> eigenschaftLabelVector = new Vector<Label>();
	
	private VerticalPanel mainPanel = this;
	VerticalPanel labelsPanel = new VerticalPanel();
	VerticalPanel eigenschaftenVert = new VerticalPanel();
	private Label firstNameLabel = new Label("Vorname: ");
	private TextBox firstNameBox = new TextBox();
	private Label lastNameLabel = new Label("Nachname: ");
	private TextBox lastNameBox = new TextBox();
	private Label zusatzLabel = new Label("Zusatz: ");
	private TextBox zusatzBox = new TextBox();
	private Label strasseLabel = new Label("Straße: ");
	private TextBox strasseBox = new TextBox();
	private Label plzLabel = new Label("PLZ: ");
	private TextBox plzBox = new TextBox();
	private Label ortLabel = new Label("Ort: ");
	private TextBox ortBox = new TextBox();

	private Label kenntnisseLabel = new Label("Deine Fähigkeiten: ");
	private Button speichern = new Button("Speichern", new CreateTeilnehmerClickHandler());

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

		ClientSideSettings.getProjektAdministration()
				.getProfilIdCurrentUser(ClientSideSettings.getCurrentUser().getId(), new GetProfileCallback());

	}

	private class CreateTeilnehmerClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			try {
				Teilnehmer t = ClientSideSettings.getCurrentUser();
				t.setVorname(firstNameBox.getText());
				t.setNachname(lastNameBox.getText());
				t.setZusatz(zusatzBox.getText());
				t.setStrasse(strasseBox.getText());
				t.setPlz(Integer.parseInt(plzBox.getText()));
				t.setOrt(ortBox.getText());
				ClientSideSettings.getProjektAdministration().updateTeilnehmer(t, new UpdateTeilnehmerCallback());
				
				
				
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}
	};

	private class GetEigenschaftCallback implements AsyncCallback<Vector<Eigenschaft>> {

		public void onFailure(Throwable caught) {
			Window.alert("Dat läuft noch nit so Eigenschaft finden!");

		}

		public void onSuccess(Vector<Eigenschaft> result) {

			labelsPanel.add(eigenschaftenVert);

			for (int i = 0; i < result.size(); i++) {
				HorizontalPanel eigenschaftenPanel = new HorizontalPanel();
				Label abschlussLabel = new Label();
				Label berufserfahrungLabel = new Label();
				Label eigenschaftLabel = new Label();
				ListBox abschlussListBox = new ListBox();
				ListBox berufserfahrungListBox = new ListBox();
				ListBox eigenschaftWertBox = new ListBox();
				eigenschaften.add(result.elementAt(i));

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
					//berufserfahrungListBox.addChangeHandler(new OnChangeHandler());
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
					//abschlussListBox.addChangeHandler(new OnChangeHandler());
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
				//eigenschaftWertBox.addChangeHandler(new OnChangeHandler());
					eigenschaftWertBox.setSelectedIndex(result.elementAt(i).getWert());
					eigenschaftListVector.add(eigenschaftWertBox);

				}

			}
			labelsPanel.add(speichern);
			Window.alert("Deine Eigenschaften wurden gefunden!");

		}

	}

	private class UpdateTeilnehmerCallback implements AsyncCallback {

		public void onFailure(Throwable caught) {
			Window.alert("Dat läuft noch nit so!");

		}

		public void onSuccess(Object result) {
			Vector<Eigenschaft> eigenschaftDB = new Vector<Eigenschaft>();
			for(int i=0; i< eigenschaften.size(); i++){
				int id= eigenschaften.elementAt(i).getId();
				
				Eigenschaft e = new Eigenschaft ();
				e.setId(id);
				e.setName(eigenschaftLabelVector.elementAt(i).getText());
				e.setWert(eigenschaftListVector.elementAt(i).getSelectedIndex());
				eigenschaftDB.add(e);	
				
			}
			ClientSideSettings.getProjektAdministration().updateEigenschaft(eigenschaftDB, new UpdateEigenschaftenCallback());	
			
			Window.alert("Dein Profil wurde aktualisiert!");
		}

	}

	private class GetProfileCallback implements AsyncCallback<Profil> {

		public void onFailure(Throwable caught) {
			Window.alert("Dat läuft noch nit so Profil finden!");

		}

		public void onSuccess(Profil result) {

			p.setId(result.getId());
			ClientSideSettings.getProjektAdministration().findNameAndWertFromEigenschaften(p.getId(),
					new GetEigenschaftCallback());
			Window.alert("Dein Profil wurde gefunden!");

		}

	}
	
	private class UpdateEigenschaftenCallback implements AsyncCallback {

		public void onFailure(Throwable caught) {
			Window.alert("Dat läuft noch nit so Profil finden!");

		}

		public void onSuccess(Object result) {

			Window.alert("Dein Eigenschaften wurden geaendert!");
			RootPanel.get("Content").clear();
		}

	}
	

}
