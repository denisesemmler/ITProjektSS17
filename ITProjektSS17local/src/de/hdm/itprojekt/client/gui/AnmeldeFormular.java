package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;




public class AnmeldeFormular extends VerticalPanel{
	
		private VerticalPanel mainPanel = this;
		private VerticalPanel labelsPanel = new VerticalPanel();
		private HorizontalPanel naviPanel = new HorizontalPanel();
		private HorizontalPanel msoffice = new HorizontalPanel();
		private HorizontalPanel msproject = new HorizontalPanel();
		private HorizontalPanel sap = new HorizontalPanel();
		private HorizontalPanel aris = new HorizontalPanel();
		private HorizontalPanel java = new HorizontalPanel();
		private HorizontalPanel c = new HorizontalPanel();
		private HorizontalPanel catia = new HorizontalPanel();
		private HorizontalPanel sql = new HorizontalPanel();
		
		private Label firstNameLabel = new Label("Vorname: "); 
	 	private TextBox firstNameBox = new TextBox(); 
	 	private Label lastNameLabel = new Label("Nachname: ");
	 	private TextBox lastNameBox = new TextBox(); 
	 	private Label zusatzLabel = new Label("Zusatz: ");
	 	private TextBox zusatzBox = new TextBox(); 
	 	private Label strasseLabel = new Label("Stra�e: ");
	 	private TextBox strasseBox = new TextBox(); 
	 	private Label plzLabel = new Label("PLZ: ");
	 	private TextBox plzBox = new TextBox(); 
	 	private Label ortLabel = new Label("Ort: ");
	 	private TextBox ortBox = new TextBox(); 
	 	private Label eigenschaftLabel = new Label("Deine F�higkeiten: ");
	 	private Label schulabschlussLabel = new Label ("H�chster Schulabschluss: ");
	 	private Label berufserfahrungLabel = new Label ("Berufserfahrung: ");
	 	private Label msofficeLabel = new Label ("Microsoft Office: ");
	 	private Label msprojectLabel = new Label ("Microsoft Project: ");
	 	private Label sapLabel = new Label ("SAP/ERP: ");
	 	private Label arisLabel = new Label ("ARIS: ");
	 	private Label javaLabel = new Label ("Java: ");
	 	private Label cLabel = new Label ("C/C++: ");
	 	private Label catiaLabel = new Label ("CATIA: ");
	 	private Label sqlLabel = new Label ("SQL/DB : ");
	 	
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
	 	
	 	private Button speichern = new Button("Speichern", new CreateTeilnehmerClickHandler());
	 	
	 	//Teilnehmer neuerTeilnehmer = null;
	 	
	 	public AnmeldeFormular() {
	 		
	 		//CSS Styling
	 		firstNameLabel.addStyleName("Content-label");
	 		lastNameLabel.addStyleName("Content-label");
	 		zusatzLabel.addStyleName("Content-label");
	 		strasseLabel.addStyleName("Content-label");
	 		plzLabel.addStyleName("Content-label");
	 		ortLabel.addStyleName("Content-label");
	 		firstNameBox.addStyleName("gwt-TextBox");
	 		lastNameBox.addStyleName("gwt-TextBox");
	 		eigenschaftLabel.addStyleName("labeluberschrift");
	 		msofficeLabel.addStyleName("labelkennt");
	 		msprojectLabel.addStyleName("labelkennt");
	 		sapLabel.addStyleName("labelkennt");
	 		arisLabel.addStyleName("labelkennt");
	 		javaLabel.addStyleName("labelkennt");
	 		cLabel.addStyleName("labelkennt");
	 		catiaLabel.addStyleName("labelkennt");
	 		sqlLabel.addStyleName("labelkennt");
	 		
	 		mainPanel.add(labelsPanel);
	 		
	 		labelsPanel.add(new HTML(
					"<p>Willkommen bei Pr0ject, es scheint als ob du noch nicht bei uns angemeldet bist, "
					+ "bitte gebe deine Daten hier ein!</p><br />"));
	 		
	 		//Elemente hinzuf�gen
	 		labelsPanel.add(firstNameLabel);
	 		labelsPanel.add(firstNameBox);
	 		
	 		labelsPanel.add(lastNameLabel);
	 		labelsPanel.add(lastNameBox);
	 		
	 		labelsPanel.add(zusatzLabel);
	 		labelsPanel.add(zusatzBox);
	 		
	 		labelsPanel.add(strasseLabel);
	 		labelsPanel.add(strasseBox);
	 		
	 		labelsPanel.add(plzLabel);
	 		labelsPanel.add(plzBox);
	 		
	 		labelsPanel.add(ortLabel);
	 		labelsPanel.add(ortBox);
	 		
	 		labelsPanel.add(eigenschaftLabel);
	 		labelsPanel.add(schulabschlussLabel);
	 		
	 		labelsPanel.add(schulabschlussListBox);	 		
	 		schulabschlussListBox.addItem("Hauptschulabschluss");
	 		schulabschlussListBox.addItem("Mittlere Reife");
	 		schulabschlussListBox.addItem("Fachhochschulreife");
	 		schulabschlussListBox.addItem("Abitur");
	 		schulabschlussListBox.addItem("Bachelor");
	 		schulabschlussListBox.addItem("Master");
	 		
	 		labelsPanel.add(berufserfahrungLabel);
	 		labelsPanel.add(berufserfahrungListBox);
	 		berufserfahrungListBox.addItem("weniger als 1 Jahr");
	 		berufserfahrungListBox.addItem("1 - 5 Jahre");
	 		berufserfahrungListBox.addItem("6 - 10 Jahre");
	 		berufserfahrungListBox.addItem("mehr als 10 Jahre"); 		
	 		
	 		labelsPanel.add(msoffice);
	 		labelsPanel.add(msproject);
	 		labelsPanel.add(sap);
	 		labelsPanel.add(aris);
	 		labelsPanel.add(java);
	 		labelsPanel.add(c);
	 		labelsPanel.add(catia);
	 		labelsPanel.add(sql);	 		
	 		
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
	 		
	 		labelsPanel.add(speichern);
	 	}
	 	
	 	
	 	
	 	private class CreateTeilnehmerClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) {
				
				try {
					ClientSideSettings.getProjektAdministration().createTeilnehmer(firstNameBox.getText(),
							lastNameBox.getText(), zusatzBox.getText(), strasseBox.getText(), Integer.parseInt(plzBox.getText()),
							ortBox.getText(), ClientSideSettings.getCurrentUser().getEmail(), 1, 2,  new CreateTeilnehmerCallback());
					ClientSideSettings.getCurrentUser().setExisting(true);
					
				} catch (Exception e) {
					Window.alert(e.toString());
					e.printStackTrace();
				}

				// Altes Zeug
				Button active = (Button) event.getSource();

				switch (active.getText()) {
				case "Anlegen":
					Window.alert("Deine Daten wurden gespeichert");
					break;
				/**
				 * case "Navigation":
				 * 
				 * RootPanel.get("Content").clear(); RootPanel.get("Navi").add(new
				 * Navigation());
				 **/
				}
			}
		};
	 	
		private class CreateTeilnehmerCallback implements AsyncCallback {

			public void onFailure(Throwable caught) {
				Window.alert("Dat l�uft noch nit so!");

			}

			public void onSuccess(Object result) {
				Window.alert("Deine Daten wurden gespeichert!");
				RootPanel.get("Content").clear();
				RootPanel.get("Navi").clear();
				naviPanel.add(new Navigation());
				RootPanel.get("Navi").add(naviPanel);
				
			}

		}
}
