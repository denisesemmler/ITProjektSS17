package de.hdm.itprojekt.client.gui;

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
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Profil;

/**
 * Klasse für die Startseite nach der Anmeldung, die die das Profil anzeigt
 * 
 * @author Philipp Mueller
 *
 */
public class ProfilAnlegen extends VerticalPanel {

	// Vektoren für die Eigenschaften erstellen
	private Vector<String> eigenschaftName = new Vector<String>();
	private Vector<Integer> eigenschaftWert = new Vector<Integer>();

	// Die verschiedenen Panels erstellen
	private VerticalPanel mainPanel = this;
	private HorizontalPanel abschlussPanel = new HorizontalPanel();
	private HorizontalPanel erfahrungPanel = new HorizontalPanel();
	private HorizontalPanel naviPanel = new HorizontalPanel();
	private HorizontalPanel msoffice = new HorizontalPanel();
	private HorizontalPanel msproject = new HorizontalPanel();
	private HorizontalPanel sap = new HorizontalPanel();
	private HorizontalPanel aris = new HorizontalPanel();
	private HorizontalPanel java = new HorizontalPanel();
	private HorizontalPanel c = new HorizontalPanel();
	private HorizontalPanel catia = new HorizontalPanel();
	private HorizontalPanel sql = new HorizontalPanel();

	// Labels erstellen
	private Label eigenschaftLabel = new Label("Deine Fähigkeiten:");
	private Label schulabschlussLabel = new Label("Höchster Schulabschluss");
	private Label berufserfahrungLabel = new Label("Berufserfahrung");
	private Label msofficeLabel = new Label("Microsoft Office");
	private Label msprojectLabel = new Label("Microsoft Project");
	private Label sapLabel = new Label("SAP/ERP");
	private Label arisLabel = new Label("ARIS");
	private Label javaLabel = new Label("Java");
	private Label cLabel = new Label("C/C++");
	private Label catiaLabel = new Label("CATIA");
	private Label sqlLabel = new Label("SQL/DB");

	// ListBoxen erstellen
	private ListBox schulabschlussListBox = new ListBox();
	private ListBox berufserfahrungListBox = new ListBox();
	private ListBox msofficeListBox = new ListBox();
	private ListBox msprojectListBox = new ListBox();
	private ListBox sapListBox = new ListBox();
	private ListBox arisListBox = new ListBox();
	private ListBox javaListBox = new ListBox();
	private ListBox cListBox = new ListBox();
	private ListBox catiaListBox = new ListBox();
	private ListBox sqlListBox = new ListBox();

	// Speicher-Button erstellen
	private Button speichern = new Button("Speichern", new AddEigenschaftClickHandler());

	int currentUserId = 0;
	Profil p = new Profil();

	public ProfilAnlegen() {
		//CSS-Styling
	    schulabschlussListBox.setStylePrimaryName("label1");
		berufserfahrungListBox.setStylePrimaryName("label1");
		abschlussPanel.setStylePrimaryName("label1");
		erfahrungPanel.setStylePrimaryName("label1");
		msoffice.setStylePrimaryName("label1");
		msproject.setStylePrimaryName("label1");
		sap.setStylePrimaryName("label1");
		aris.setStylePrimaryName("label1");
		java.setStylePrimaryName("label1");
		c.setStylePrimaryName("label1");
		catia.setStylePrimaryName("label1");
		sql.setStylePrimaryName("label1");
		
		// aktuellen UserId und dazugehörigen ProfilId suchen
		this.currentUserId = ClientSideSettings.getCurrentUser().getId();
		ClientSideSettings.getProjektAdministration().getProfilIdCurrentUser(currentUserId,
				new GetPartnerProfileCallback());

		// GUI erstellen
		mainPanel.add(eigenschaftLabel);
		eigenschaftLabel.setStylePrimaryName("label2");
		schulabschlussLabel.setWidth("200px");
		mainPanel.add(abschlussPanel);
		abschlussPanel.add(schulabschlussLabel);

		abschlussPanel.add(schulabschlussListBox);
		schulabschlussListBox.addItem("Hauptschulabschluss");
		schulabschlussListBox.addItem("Mittlere Reife");
		schulabschlussListBox.addItem("Fachhochschulreife");
		schulabschlussListBox.addItem("Abitur");
		schulabschlussListBox.addItem("Bachelor");
		schulabschlussListBox.addItem("Master");
		
		mainPanel.add(erfahrungPanel);
		erfahrungPanel.add(berufserfahrungLabel);
		berufserfahrungLabel.setWidth("200px");
		erfahrungPanel.add(berufserfahrungListBox);
		berufserfahrungListBox.addItem("weniger als 1 Jahr");
		berufserfahrungListBox.addItem("1 - 5 Jahre");
		berufserfahrungListBox.addItem("6 - 10 Jahre");
		berufserfahrungListBox.addItem("mehr als 10 Jahre");

		mainPanel.add(msoffice);
		mainPanel.add(msproject);
		mainPanel.add(sap);
		mainPanel.add(aris);
		mainPanel.add(java);
		mainPanel.add(c);
		mainPanel.add(catia);
		mainPanel.add(sql);

		msoffice.add(msofficeLabel);
		msofficeLabel.setWidth("200px");
		msoffice.add(msofficeListBox);

		msproject.add(msprojectLabel);
		msprojectLabel.setWidth("200px");
		msproject.add(msprojectListBox);

		sap.add(sapLabel);
		sapLabel.setWidth("200px");
		sap.add(sapListBox);

		aris.add(arisLabel);
		arisLabel.setWidth("200px");
		aris.add(arisListBox);

		java.add(javaLabel);
		javaLabel.setWidth("200px");
		java.add(javaListBox);

		c.add(cLabel);
		cLabel.setWidth("200px");
		c.add(cListBox);

		catia.add(catiaLabel);
		catiaLabel.setWidth("200px");
		catia.add(catiaListBox);

		sql.add(sqlLabel);
		sqlLabel.setWidth("200px");
		sql.add(sqlListBox);

		msofficeListBox.addItem("Keine Kenntnisse");
		msofficeListBox.addItem("Wenig Kenntnisse");
		msofficeListBox.addItem("Gute Kenntnisse");

		msprojectListBox.addItem("Keine Kenntnisse");
		msprojectListBox.addItem("Wenig Kenntnisse");
		msprojectListBox.addItem("Gute Kenntnisse");

		sapListBox.addItem("Keine Kenntnisse");
		sapListBox.addItem("Wenig Kenntnisse");
		sapListBox.addItem("Gute Kenntnisse");

		arisListBox.addItem("Keine Kenntnisse");
		arisListBox.addItem("Wenig Kenntnisse");
		arisListBox.addItem("Gute Kenntnisse");

		javaListBox.addItem("Keine Kenntnisse");
		javaListBox.addItem("Wenig Kenntnisse");
		javaListBox.addItem("Gute Kenntnisse");

		cListBox.addItem("Keine Kenntnisse");
		cListBox.addItem("Wenig Kenntnisse");
		cListBox.addItem("Gute Kenntnisse");

		catiaListBox.addItem("Keine Kenntnisse");
		catiaListBox.addItem("Wenig Kenntnisse");
		catiaListBox.addItem("Gute Kenntnisse");

		sqlListBox.addItem("Keine Kenntnisse");
		sqlListBox.addItem("Wenig Kenntnisse");
		sqlListBox.addItem("Gute Kenntnisse");

		// Button zum Panel hinzufügen
		mainPanel.add(speichern);

	}

	private class AddEigenschaftClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			try {
				// Eigenschaftname und Wert in Vektoren laden
				eigenschaftName.add(schulabschlussLabel.getText());
				eigenschaftName.add(berufserfahrungLabel.getText());
				eigenschaftName.add(msofficeLabel.getText());
				eigenschaftName.add(msprojectLabel.getText());
				eigenschaftName.add(sapLabel.getText());
				eigenschaftName.add(arisLabel.getText());
				eigenschaftName.add(javaLabel.getText());
				eigenschaftName.add(cLabel.getText());
				eigenschaftName.add(catiaLabel.getText());
				eigenschaftName.add(sqlLabel.getText());

				eigenschaftWert.add(schulabschlussListBox.getSelectedIndex());
				eigenschaftWert.add(berufserfahrungListBox.getSelectedIndex());
				eigenschaftWert.add(msofficeListBox.getSelectedIndex());
				eigenschaftWert.add(msprojectListBox.getSelectedIndex());
				eigenschaftWert.add(sapListBox.getSelectedIndex());
				eigenschaftWert.add(arisListBox.getSelectedIndex());
				eigenschaftWert.add(javaListBox.getSelectedIndex());
				eigenschaftWert.add(cListBox.getSelectedIndex());
				eigenschaftWert.add(catiaListBox.getSelectedIndex());
				eigenschaftWert.add(sqlListBox.getSelectedIndex());
				// Diese dann an die Applikationslogik übergeben um diese dann in der DB zu speichern
				ClientSideSettings.getProjektAdministration().createEigenschaft(eigenschaftName, eigenschaftWert,
						p.getId(), new CreateEigenschaftCallback());

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}

		}
	};

	private class CreateEigenschaftCallback implements AsyncCallback<Vector<Eigenschaft>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");

		}

		public void onSuccess(Vector<Eigenschaft> result) {

			Window.alert("Dein Profil wurde angelegt!");
			// Wenn Eigenschaften angelegt wurden, dann User existing setzen,
			// damit dieser beim nächsten Aufruf der Seite direkt auf die
			// Startseite kommt
			ClientSideSettings.getCurrentUser().setProfilExisting(true);

			RootPanel.get("Content").clear();
			RootPanel.get("Navi").clear();
			naviPanel.add(new Navigation());
			RootPanel.get("Navi").add(naviPanel);
			RootPanel.get("Content").add(new ProfilAnzeigen());

		}

	}

	private class GetPartnerProfileCallback implements AsyncCallback<Profil> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");

		}

		public void onSuccess(Profil result) {
			// Profil Id in profil-objekt speichern
			p.setId(result.getId());

		}

	}
}
