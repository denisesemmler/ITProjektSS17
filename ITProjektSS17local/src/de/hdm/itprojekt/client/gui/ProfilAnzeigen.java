package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.report.AlleAusschreibungenNutzer;
import de.hdm.itprojekt.shared.report.AlleBewerbungenNutzer;

/**
 * Klasse für die Startseite nach der Anmeldung, die die das Profil anzeigt
 * @author Philipp Mueller
 *
 */
public class ProfilAnzeigen extends HorizontalPanel {
	//Panels erstellen
	private HorizontalPanel mainPanel = this;
	VerticalPanel labelsPanel = new VerticalPanel();
	VerticalPanel dataPanel = new VerticalPanel();
	VerticalPanel buttonsPanel = new VerticalPanel();

	// Labels erstellen
	private Label firstNameLabel = new Label("Vorname: ");
	private Label firstNameDataLabel = new Label(ClientSideSettings.getCurrentUser().getVorname());

	private Label lastNameLabel = new Label("Nachname: ");
	private Label lastNameDataLabel = new Label(ClientSideSettings.getCurrentUser().getNachname());

	private Label firmaLabel = new Label("Firma: ");
	private Label firmaDataLabel = new Label(ClientSideSettings.getCurrentUser().getFirma());

	private Label zusatzLabel = new Label("Zusatz: ");
	private Label zusatzDataLabel = new Label(ClientSideSettings.getCurrentUser().getZusatz());

	private Label strasseLabel = new Label("Straße: ");
	private Label strasseDataLabel = new Label(ClientSideSettings.getCurrentUser().getStrasse());

	private Label plzLabel = new Label("PLZ: ");
	private Label plzDataLabel = new Label(Integer.toString(ClientSideSettings.getCurrentUser().getPlz()));

	private Label ortLabel = new Label("Ort: ");
	private Label ortDataLabel = new Label(ClientSideSettings.getCurrentUser().getOrt());
	
	//Buttons erstellen
	private Button matching = new Button("Passende Ausschreibungen", new ButtonClickHandler());
	private Button ausschreibungen = new Button("Meine Ausschreibungen", new ButtonClickHandler());
	private Button bewerbungen = new Button("Meine Bewerbungen", new ButtonClickHandler());

	public ProfilAnzeigen() {
		//CSS-Styling
		firstNameLabel.addStyleName("label1");
		lastNameLabel.addStyleName("label1");
		zusatzLabel.addStyleName("label1");
		strasseLabel.addStyleName("label1");
		plzLabel.addStyleName("label1");
		ortLabel.addStyleName("label1");
		firstNameDataLabel.addStyleName("label1");
		lastNameDataLabel.addStyleName("label1");
		zusatzDataLabel.addStyleName("label1");
		strasseDataLabel.addStyleName("label1");
		plzDataLabel.addStyleName("label1");
		ortDataLabel.addStyleName("label1");
		labelsPanel.addStyleName("verticalrand");
		dataPanel.addStyleName("verticalrand");
		
		//Panels hinzufügen
		mainPanel.add(labelsPanel);
		mainPanel.add(dataPanel);
		mainPanel.add(buttonsPanel);

		RootPanel.get("Content").add(new HTML("<h3>Willkommen bei Pr0ject, "
				+ ClientSideSettings.getCurrentUser().getVorname() + "</h3> <br /> " + "<h3>Mein Profil: </h3>"));

		labelsPanel.add(firstNameLabel);
		dataPanel.add(firstNameDataLabel);

		labelsPanel.add(lastNameLabel);
		dataPanel.add(lastNameDataLabel);

		//Feld Firma oder Zusatz nur anzeigen wenn nicht leer
		if (ClientSideSettings.getCurrentUser().getFirma() != "") {
			labelsPanel.add(firmaLabel);
			dataPanel.add(firmaDataLabel);
		}

		if (ClientSideSettings.getCurrentUser().getZusatz() != "") {
			labelsPanel.add(zusatzLabel);
			dataPanel.add(zusatzDataLabel);
		}

		labelsPanel.add(strasseLabel);
		dataPanel.add(strasseDataLabel);

		labelsPanel.add(plzLabel);
		dataPanel.add(plzDataLabel);

		labelsPanel.add(ortLabel);
		dataPanel.add(ortDataLabel);
		
		buttonsPanel.add(matching);
		buttonsPanel.add(ausschreibungen);
		buttonsPanel.add(bewerbungen);
	}
	private class ButtonClickHandler implements ClickHandler {
		public void onClick(ClickEvent e) {

			Button active = (Button) e.getSource();

			//Swich Case, die den Text des aktiven Button abfängt
			switch (active.getText()) {
			case "Passende Ausschreibungen":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new Matching());
				break;
			case "Meine Ausschreibungen":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new AlleAusschreibungenNutzer());
				break;
			case "Meine Bewerbungen":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new AlleBewerbungenNutzer());
				break;
			}
		}
	}
}
