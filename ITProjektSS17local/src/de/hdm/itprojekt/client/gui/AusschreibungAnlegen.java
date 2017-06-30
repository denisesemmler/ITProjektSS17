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
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Profil;
import de.hdm.itprojekt.shared.bo.Projekt;

public class AusschreibungAnlegen extends HorizontalPanel {

	private Vector<Projekt> pVector = new Vector<Projekt>();
	private Vector<String> eigenschaftName = new Vector<String>();
	private Vector<Integer> eigenschaftWert = new Vector<Integer>();

	/**
	 * Erstellen der Panels
	 */
	private HorizontalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();
	private VerticalPanel attributePanel = new VerticalPanel();
	private VerticalPanel suchPanel = new VerticalPanel();

	/**
	 * Erstellen der Labels
	 */
	private Label projektLabel = new Label("Projekt wählen: ");
	private Label ausschreibungTitelLabel = new Label("Titel der Ausschreibung: ");
	private Label stellenbeschreibungLabel = new Label("Stellenbeschreibung: ");
	private Label bewerbungsfristLabel = new Label("Bewerbungsfrist festlegen: ");

	// Erstellen der TextBox und Area
	private TextBox ausschreibungTitelBox = new TextBox();
	private TextArea stellenbeschreibungArea = new TextArea();

	private DatePicker bewerbungsfrist = new DatePicker();
	/**
	 * 
	 * Erstellen der ListBox
	 */
	private ListBox projektListbox = new ListBox();

	/**
	 * Erstellen der Buttons
	 */
	private Button ausschreibungAnlegenButton = new Button("Anlegen", new AnlegenClickHandler());

	/**
	 * Widgets für Suchprofil
	 */
	private HorizontalPanel schulabschluss = new HorizontalPanel();
	private HorizontalPanel berufserfahrung = new HorizontalPanel();
	private HorizontalPanel naviPanel = new HorizontalPanel();
	private HorizontalPanel msoffice = new HorizontalPanel();
	private HorizontalPanel msproject = new HorizontalPanel();
	private HorizontalPanel sap = new HorizontalPanel();
	private HorizontalPanel aris = new HorizontalPanel();
	private HorizontalPanel java = new HorizontalPanel();
	private HorizontalPanel c = new HorizontalPanel();
	private HorizontalPanel catia = new HorizontalPanel();
	private HorizontalPanel sql = new HorizontalPanel();

	private Label eigenschaftLabel = new Label("Voraussetzungen für diese Stelle:");
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

	int currentUserId = 0;
	Profil p = new Profil();

	/**
	 * Konstruktor für Anlegen der GUI
	 */
	public AusschreibungAnlegen() {

		this.currentUserId = ClientSideSettings.getCurrentUser().getId();

		// CSS Styling
		projektLabel.addStyleName("Content-Label");
		ausschreibungTitelLabel.addStyleName("Content-Label");
		stellenbeschreibungLabel.addStyleName("Content-Label");
		bewerbungsfristLabel.addStyleName("Content-label");
		suchPanel.setStylePrimaryName("verticalrand");
		eigenschaftLabel.setStylePrimaryName("label1");
		schulabschluss.setStylePrimaryName("label1");
		berufserfahrung.setStylePrimaryName("label1");
		msoffice.setStylePrimaryName("label1");
		msproject.setStylePrimaryName("label1");
		sap.setStylePrimaryName("label1");
		aris.setStylePrimaryName("label1");
		java.setStylePrimaryName("label1");
		c.setStylePrimaryName("label1");
		catia.setStylePrimaryName("label1");
		sql.setStylePrimaryName("label1");
		attributePanel.setStylePrimaryName("label1");
		projektLabel.setStylePrimaryName("label1");
		ausschreibungTitelLabel.setStylePrimaryName("label1");
		stellenbeschreibungLabel.setStylePrimaryName("label1");
		bewerbungsfristLabel.setStylePrimaryName("label1");

		mainPanel.add(editorPanel);

		editorPanel.add(attributePanel);

		mainPanel.add(suchPanel);

		attributePanel.add(projektLabel);
		attributePanel.add(projektListbox);

		attributePanel.add(ausschreibungTitelLabel);
		attributePanel.add(ausschreibungTitelBox);

		attributePanel.add(stellenbeschreibungLabel);
		attributePanel.add(stellenbeschreibungArea);

		attributePanel.add(bewerbungsfristLabel);
		attributePanel.add(bewerbungsfrist);

		// Suchprofil GUI erstellen
		suchPanel.add(eigenschaftLabel);
		suchPanel.add(schulabschluss);
		suchPanel.add(berufserfahrung);

		schulabschluss.add(schulabschlussLabel);
		schulabschlussLabel.setWidth("200px");
		schulabschluss.add(schulabschlussListBox);

		schulabschlussListBox.addItem("Hauptschulabschluss");
		schulabschlussListBox.addItem("Mittlere Reife");
		schulabschlussListBox.addItem("Fachhochschulreife");
		schulabschlussListBox.addItem("Abitur");
		schulabschlussListBox.addItem("Bachelor");
		schulabschlussListBox.addItem("Master");

		berufserfahrung.add(berufserfahrungLabel);
		berufserfahrungLabel.setWidth("200px");
		berufserfahrung.add(berufserfahrungListBox);

		berufserfahrungListBox.addItem("weniger als 1 Jahr");
		berufserfahrungListBox.addItem("1 - 5 Jahre");
		berufserfahrungListBox.addItem("6 - 10 Jahre");
		berufserfahrungListBox.addItem("mehr als 10 Jahre");

		suchPanel.add(msoffice);
		suchPanel.add(msproject);
		suchPanel.add(sap);
		suchPanel.add(aris);
		suchPanel.add(java);
		suchPanel.add(c);
		suchPanel.add(catia);
		suchPanel.add(sql);

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

		suchPanel.add(ausschreibungAnlegenButton);

		// Alle Projekt des Teilnehmers suchen
		try {
			ClientSideSettings.getProjektAdministration().findAllProjektByTeilnehmerId(
					ClientSideSettings.getCurrentUser().getId(), new GetAllProjekteByIDCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}
	}

	private class AnlegenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			try {
				// Suchprofil erstellen
				ClientSideSettings.getProjektAdministration().createProfil(ClientSideSettings.getCurrentUser().getId(),
						1, new CreateProfilCallback());

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}

		}
	};

	private class CreateAusschreibungCallback implements AsyncCallback<Ausschreibung> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");

		}

		public void onSuccess(Ausschreibung result) {

			// ausgewählte Eigenschaften in die Vektoren laden
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

			// Die Vektoren an die Applikationslogik übergeben um in die DB zu
			// speichern
			ClientSideSettings.getProjektAdministration().createEigenschaft(eigenschaftName, eigenschaftWert, p.getId(),
					new CreateEigenschaftCallback());

		}

	}

	private class GetAllProjekteByIDCallback implements AsyncCallback<Vector<Projekt>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");
		}

		public void onSuccess(Vector<Projekt> result) {
			// Projekte des Nutzers anzeigen
			for (int i = 0; i < result.size(); i++) {
				Projekt p1 = result.elementAt(i);
				pVector.add(p1);
				projektListbox.addItem(p1.getName());
			}

		}
	}

	private class CreateEigenschaftCallback implements AsyncCallback<Vector<Eigenschaft>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");

		}

		public void onSuccess(Vector<Eigenschaft> result) {

			Window.alert("Deine Ausschreibung wurde angelegt!");
			// Wenn Eigenschaften gespeichert auf Verwalten wechseln
			RootPanel.get("Content").clear();
			RootPanel.get("Navi").clear();
			naviPanel.add(new Navigation());
			RootPanel.get("Navi").add(naviPanel);
			RootPanel.get("Content").add(new AusschreibungVerwalten());

		}

	}

	private class CreateProfilCallback implements AsyncCallback<Profil> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");

		}

		public void onSuccess(Profil result) {
			// Id des eben erstellen Suchprofils finden und dann Ausschreibung
			// anlegen
			p.setId(result.getId());

			int pid = pVector.elementAt(projektListbox.getSelectedIndex()).getId();
			ClientSideSettings.getProjektAdministration().createAusschreibung(stellenbeschreibungArea.getText(),
					bewerbungsfrist.getValue(), ausschreibungTitelBox.getText(), "laufend", pid, p.getId(),
					ClientSideSettings.getCurrentUser().getId(), new CreateAusschreibungCallback());

		}

	}

}
