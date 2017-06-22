package de.hdm.itprojekt.client.gui;

	import java.util.ArrayList;
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
import de.hdm.itprojekt.shared.bo.Teilnehmer;



	

	public class ProfilAnlegen extends VerticalPanel {
		
		private Vector<String> eigenschaftName = new Vector<String>(); 
		private Vector<String> eigenschaftWert = new Vector<String>(); 
		
		private VerticalPanel mainPanel= this;
		private HorizontalPanel eigenschaft = new HorizontalPanel();
		
		//private VerticalPanel speichern = new VerticalPanel();
		private VerticalPanel eigenschaft2 = new VerticalPanel();
		private VerticalPanel addEigenschaft = new VerticalPanel();
		
		private HorizontalPanel naviPanel = new HorizontalPanel();
		private HorizontalPanel msoffice = new HorizontalPanel();
		private HorizontalPanel msproject = new HorizontalPanel();
		private HorizontalPanel sap = new HorizontalPanel();
		private HorizontalPanel aris = new HorizontalPanel();
		private HorizontalPanel java = new HorizontalPanel();
		private HorizontalPanel c = new HorizontalPanel();
		private HorizontalPanel catia = new HorizontalPanel();
		private HorizontalPanel sql = new HorizontalPanel();
		
		private Label eigenschaftLabel = new Label("Deine Fähigkeiten: ");
	 	private Label schulabschlussLabel = new Label ("Höchster Schulabschluss: ");
	 	private Label berufserfahrungLabel = new Label ("Berufserfahrung: ");
	 	private Label msofficeLabel = new Label ("Microsoft Office: ");
	 	private Label msprojectLabel = new Label ("Microsoft Project: ");
	 	private Label sapLabel = new Label ("SAP/ERP: ");
	 	private Label arisLabel = new Label ("ARIS: ");
	 	private Label javaLabel = new Label ("Java: ");
	 	private Label cLabel = new Label ("C/C++: ");
	 	private Label catiaLabel = new Label ("CATIA: ");
	 	private Label sqlLabel = new Label ("SQL/DB : ");
	 	
	 	private ListBox eigenschaftadd = new ListBox();
		private ListBox eigenschaftaddwert = new ListBox();
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
	 	
	 	private Button speichern = new Button("Speichern", new AddEigenschaftClickHandler());
	 	
	 	//private Button speichernProfil = new Button("Speichern", new SpeicherProfilClickHandler());
	 	Profil p = new Profil();
	 	
	 	public ProfilAnlegen(){
			
	 		mainPanel.add(eigenschaftLabel);
	 		mainPanel.add(schulabschlussLabel);
	 		
	 		mainPanel.add(schulabschlussListBox);	 		
	 		schulabschlussListBox.addItem("Hauptschulabschluss");
	 		schulabschlussListBox.addItem("Mittlere Reife");
	 		schulabschlussListBox.addItem("Fachhochschulreife");
	 		schulabschlussListBox.addItem("Abitur");
	 		schulabschlussListBox.addItem("Bachelor");
	 		schulabschlussListBox.addItem("Master");
	 		
	 		mainPanel.add(berufserfahrungLabel);
	 		mainPanel.add(berufserfahrungListBox);
	 		berufserfahrungListBox.addItem("weniger als 1 Jahr");
	 		berufserfahrungListBox.addItem("1 - 5 Jahre");
	 		berufserfahrungListBox.addItem("6 - 10 Jahre");
	 		berufserfahrungListBox.addItem("mehr als 10 Jahre"); 
	 		
	 		/*mainPanel.add(eigenschaft);
	 		 		
	 		eigenschaft.add(eigenschaftadd);
	 		eigenschaft.add(eigenschaftaddwert);
	 		eigenschaft.add(addE);
	 		*/
	 		mainPanel.add(msoffice);
	 		mainPanel.add(msproject);
	 		mainPanel.add(sap);
	 		mainPanel.add(aris);
	 		mainPanel.add(java);
	 		mainPanel.add(c);
	 		mainPanel.add(catia);
	 		mainPanel.add(sql);	 		
	 		
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
	 		
	 		/*eigenschaftadd.addItem("MS Office");
	 		eigenschaftadd.addItem("MS Project");
	 		eigenschaftadd.addItem("SAP/ERP");
	 		eigenschaftadd.addItem("ARIS");
	 		eigenschaftadd.addItem("Java");
	 		eigenschaftadd.addItem("C/C++");
	 		eigenschaftadd.addItem("CATIA");
	 		eigenschaftadd.addItem("SQL/Datenbank");
	 		
	 		eigenschaftaddwert.addItem("Wenig Kenntnisse");
	 		eigenschaftaddwert.addItem("Gute Kenntnisse");
	 		eigenschaftaddwert.addItem("Sehr gute Kenntnisse");*/
	 		
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
			
			mainPanel.add(speichern);
	 	
	 		ClientSideSettings.getProjektAdministration().createProfil(ClientSideSettings.getCurrentUser().getId(), new CreateProfilCallback());
		}
			
			private class AddEigenschaftClickHandler implements ClickHandler {

				public void onClick(ClickEvent event) {
					
					try {
						eigenschaftName.add(msofficeLabel.getText());
						eigenschaftName.add(msprojectLabel.getText());
						eigenschaftName.add(sapLabel.getText());
						eigenschaftName.add(arisLabel.getText());
						eigenschaftName.add(javaLabel.getText());
						eigenschaftName.add(cLabel.getText());
						eigenschaftName.add(catiaLabel.getText());
						eigenschaftName.add(sqlLabel.getText());
						
						eigenschaftWert.add(msofficeListBox.getItemText(msofficeListBox.getSelectedIndex()));
						eigenschaftWert.add(msprojectListBox.getItemText(msprojectListBox.getSelectedIndex()));
						eigenschaftWert.add(sapListBox.getItemText(sapListBox.getSelectedIndex()));
						eigenschaftWert.add(arisListBox.getItemText(arisListBox.getSelectedIndex()));
						eigenschaftWert.add(javaListBox.getItemText(javaListBox.getSelectedIndex()));
						eigenschaftWert.add(cListBox.getItemText(cListBox.getSelectedIndex()));
						eigenschaftWert.add(catiaListBox.getItemText(catiaListBox.getSelectedIndex()));
						eigenschaftWert.add(sqlListBox.getItemText(sqlListBox.getSelectedIndex()));
						
						for (int i =0; i< eigenschaftWert.size(); i++){
							String name = eigenschaftName.elementAt(i);
							String wert = eigenschaftWert.elementAt(i);
							ClientSideSettings.getProjektAdministration().createEigenschaft(name, wert , p.getId(), new CreateEigenschaftCallback());
						}
					
						
						
						
						
										
				 		
						
						
					} catch (Exception e) {
						Window.alert(e.toString());
						e.printStackTrace();
					}

										
				}
			};
			private class CreateProfilCallback implements AsyncCallback<Profil> {

				public void onFailure(Throwable caught) {
					Window.alert("Dat läuft noch nit so Profil!");

				}

				public void onSuccess(Profil result) {
					Window.alert("Dein Profil wurde angelegt!");
					p.setId(result.getId());
					
				
				}

			}
			
			private class CreateEigenschaftCallback implements AsyncCallback {
				int idx = 0;
				public void onFailure(Throwable caught) {
					Window.alert("Dat läuft noch nit so Eigenschaft!");

				}

				public void onSuccess(Object result) {
					
					
					Window.alert("Deine Eigenschaft wurden angelegt!");
			
					idx++;
					
					if(idx == eigenschaftName.size())
					RootPanel.get("Content").clear();
					RootPanel.get("Navi").clear();
					naviPanel.add(new Navigation());
					RootPanel.get("Navi").add(naviPanel);
					RootPanel.get("Content").add(
							new ProfilAnzeigen());
			 		
				
				}

			}
		

			
	}
