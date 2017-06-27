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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.shared.bo.Profil;
import de.hdm.itprojekt.shared.bo.Projekt;

public class AusschreibungAnlegen extends VerticalPanel {

	private Vector<Projekt> pVector = new Vector<Projekt>();
	private Vector<String> eigenschaftName = new Vector<String>();
	private Vector<Integer> eigenschaftWert = new Vector<Integer>();

	/**
	 * Erstellen der Panels
	 */
	private VerticalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();
	private VerticalPanel attributePanel = new VerticalPanel();
	private VerticalPanel suchPanel = new VerticalPanel();

	/**
	 * Erstellen der Labels
	 */
	private Label projektLabel = new Label("Projekt w�hlen: ");
	private Label ausschreibungTitelLabel = new Label("Titel der Ausschreibung: ");
	private Label stellenbeschreibungLabel = new Label("Stellenbeschreibung: ");
	private Label bewerbungsfristLabel = new Label("Bewerbungsfrist festlegen: ");

	private TextBox ausschreibungTitelBox = new TextBox();
	private TextBox stellenbeschreibungBox = new TextBox();

	private DatePicker bewerbungsfrist = new DatePicker();
	/**
	 * Erstellen der ListBox
	 */
	private ListBox projektListbox = new ListBox();

	/**
	 * Erstellen der Buttons
	 */
	private Button ausschreibungAnlegenButton = new Button("Anlegen", new AnlegenClickHandler());

	/**
	 * Widgets f�r Suchprofil
	 */
	private HorizontalPanel naviPanel = new HorizontalPanel();
	private HorizontalPanel msoffice = new HorizontalPanel();
	private HorizontalPanel msproject = new HorizontalPanel();
	private HorizontalPanel sap = new HorizontalPanel();
	private HorizontalPanel aris = new HorizontalPanel();
	private HorizontalPanel java = new HorizontalPanel();
	private HorizontalPanel c = new HorizontalPanel();
	private HorizontalPanel catia = new HorizontalPanel();
	private HorizontalPanel sql = new HorizontalPanel();

	private Label eigenschaftLabel = new Label("Deine F�higkeiten:");
	private Label schulabschlussLabel = new Label("Hoechster Schulabschluss");
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
	 * Konstruktor f�r Anlegen der GUI
	 */
	public AusschreibungAnlegen() {

		this.currentUserId = ClientSideSettings.getCurrentUser().getId();
	
		// CSS Styling
		projektLabel.addStyleName("Content-Label");
		ausschreibungTitelLabel.addStyleName("Content-Label");
		stellenbeschreibungLabel.addStyleName("Content-Label");
		bewerbungsfristLabel.addStyleName("Content-label");

		mainPanel.add(editorPanel);

		editorPanel.add(attributePanel);

		mainPanel.add(suchPanel);

		attributePanel.add(projektLabel);
		attributePanel.add(projektListbox);

		attributePanel.add(ausschreibungTitelLabel);
		attributePanel.add(ausschreibungTitelBox);

		attributePanel.add(stellenbeschreibungLabel);
		attributePanel.add(stellenbeschreibungBox);

		attributePanel.add(bewerbungsfristLabel);
		attributePanel.add(bewerbungsfrist);

		// Suchprofil
		suchPanel.add(eigenschaftLabel);
		suchPanel.add(schulabschlussLabel);

		suchPanel.add(schulabschlussListBox);
		schulabschlussListBox.addItem("Hauptschulabschluss");
		schulabschlussListBox.addItem("Mittlere Reife");
		schulabschlussListBox.addItem("Fachhochschulreife");
		schulabschlussListBox.addItem("Abitur");
		schulabschlussListBox.addItem("Bachelor");
		schulabschlussListBox.addItem("Master");

		suchPanel.add(berufserfahrungLabel);
		suchPanel.add(berufserfahrungListBox);
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
		msoffice.add(msofficeListBox);

		msproject.add(msprojectLabel);
		msproject.add(msprojectListBox);

		sap.add(sapLabel);
		sap.add(sapListBox);

		aris.add(arisLabel);
		aris.add(arisListBox);

		java.add(javaLabel);
		java.add(javaListBox);

		c.add(cLabel);
		c.add(cListBox);

		catia.add(catiaLabel);
		catia.add(catiaListBox);

		sql.add(sqlLabel);
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

		mainPanel.add(ausschreibungAnlegenButton);

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
			

				ClientSideSettings.getProjektAdministration().createProfil(ClientSideSettings.getCurrentUser().getId(),
						1, new CreateProfilCallback());
				Window.alert("Deine Daten wurden gespeichert!");

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}

		}
	};

	private class CreateAusschreibungCallback implements AsyncCallback {

		public void onFailure(Throwable caught) {
			Window.alert("Dat l�uft noch nit so!");

		}

		public void onSuccess(Object result) {
			
			Window.alert("Ausschreibung angelegt!");
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

			ClientSideSettings.getProjektAdministration().createEigenschaft(eigenschaftName, eigenschaftWert,
					p.getId(), new CreateEigenschaftCallback());

		}

	}

	private class GetAllProjekteByIDCallback implements AsyncCallback<Vector<Projekt>> {

		public void onFailure(Throwable caught) {
			Window.alert("Nein das falsch");
		}

		public void onSuccess(Vector<Projekt> result) {
			// Window.alert("Joo Sucess");
			// Window.alert(ClientSideSettings.getCurrentUser().getVorname());
			for (int i = 0; i < result.size(); i++) {
				Projekt p1 = result.elementAt(i);
				pVector.add(p1);
				projektListbox.addItem(p1.getName());
			}

		}
	}

	/*
	 * Added eigenschaften an Vector
	 */
	/**
	 * private class AddEigenschaftClickHandler implements ClickHandler {
	 * 
	 * public void onClick(ClickEvent event) {
	 * 
	 * try { eigenschaftName.add(schulabschlussLabel.getText());
	 * eigenschaftName.add(berufserfahrungLabel.getText());
	 * eigenschaftName.add(msofficeLabel.getText());
	 * eigenschaftName.add(msprojectLabel.getText());
	 * eigenschaftName.add(sapLabel.getText());
	 * eigenschaftName.add(arisLabel.getText());
	 * eigenschaftName.add(javaLabel.getText());
	 * eigenschaftName.add(cLabel.getText());
	 * eigenschaftName.add(catiaLabel.getText());
	 * eigenschaftName.add(sqlLabel.getText());
	 * 
	 * eigenschaftWert.add(schulabschlussListBox.getSelectedIndex());
	 * eigenschaftWert.add(berufserfahrungListBox.getSelectedIndex());
	 * eigenschaftWert.add(msofficeListBox.getSelectedIndex());
	 * eigenschaftWert.add(msprojectListBox.getSelectedIndex());
	 * eigenschaftWert.add(sapListBox.getSelectedIndex());
	 * eigenschaftWert.add(arisListBox.getSelectedIndex());
	 * eigenschaftWert.add(javaListBox.getSelectedIndex());
	 * eigenschaftWert.add(cListBox.getSelectedIndex());
	 * eigenschaftWert.add(catiaListBox.getSelectedIndex());
	 * eigenschaftWert.add(sqlListBox.getSelectedIndex());
	 * 
	 * ClientSideSettings.getProjektAdministration().createEigenschaft(eigenschaftName,
	 * eigenschaftWert, p.getId(), new CreateEigenschaftCallback());
	 * 
	 * ClientSideSettings.getProjektAdministration().createProfil(ClientSideSettings.getCurrentUser().getId(),
	 * 0, new CreateProfilCallback()); Window.alert("Deine Daten wurden
	 * gespeichert!");
	 * 
	 * } catch (Exception e) { Window.alert(e.toString()); e.printStackTrace();
	 * }
	 * 
	 * } };
	 **/

	private class CreateEigenschaftCallback implements AsyncCallback {

		public void onFailure(Throwable caught) {
			Window.alert("Dat l�uft noch nit so Eigenschaft!");

		}

		public void onSuccess(Object result) {

			Window.alert("Deine Eigenschaften wurden angelegt!");
		

			RootPanel.get("Content").clear();
			RootPanel.get("Navi").clear();
			naviPanel.add(new Navigation());
			RootPanel.get("Navi").add(naviPanel);
			RootPanel.get("Content").add(new ProfilAnzeigen());

		}

	}

	
	private class CreateProfilCallback implements AsyncCallback<Profil> {

		public void onFailure(Throwable caught) {
			Window.alert("Dat l�uft noch nit so!");

		}

		public void onSuccess(Profil result) {
			p.setId(result.getId());
			Window.alert("Dein Profil wurde gespeichert!");
			int pid = pVector.elementAt(projektListbox.getSelectedIndex()).getId();
			ClientSideSettings.getProjektAdministration().createAusschreibung(stellenbeschreibungBox.getText(),
					bewerbungsfrist.getValue(), ausschreibungTitelBox.getText(), "laufend", pid, p.getId(),
					ClientSideSettings.getCurrentUser().getId(), new CreateAusschreibungCallback());

		}

	}

}
