package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;


import de.hdm.itprojekt.shared.bo.Projekt;





public class AusschreibungAnlegen extends VerticalPanel{
	
		private Vector<Projekt> pVector = new Vector<Projekt>();

		/**
		 * Erstellen der Panels
		 */
		private VerticalPanel mainPanel = this;
		private VerticalPanel editorPanel = new VerticalPanel();
		private VerticalPanel attributePanel = new VerticalPanel();

		/**
		 * Erstellen der Labels
		 */
		private Label projektLabel = new Label("Projekt wählen: ");
		private Label ausschreibungTitelLabel = new Label ("Titel der Ausschreibung: ");
		private Label stellenbeschreibungLabel = new Label ("Stellenbeschreibung: ");
		private Label bewerbungsfristLabel = new Label ("Bewerbungsfrist festlegen: ");
		
		
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
		 * Konstruktor für Anlegen der GUI
		 */
		public AusschreibungAnlegen() {
		
			// CSS Styling
			projektLabel.addStyleName("Content-Label");
			ausschreibungTitelLabel.addStyleName("Content-Label");
			stellenbeschreibungLabel.addStyleName("Content-Label");
			bewerbungsfristLabel.addStyleName("Content-label");


			mainPanel.add(editorPanel);

			editorPanel.add(attributePanel);

			attributePanel.add(projektLabel);
			attributePanel.add(projektListbox);
		
			
			attributePanel.add(ausschreibungTitelLabel);
			attributePanel.add(ausschreibungTitelBox);
			
			attributePanel.add(stellenbeschreibungLabel);
			attributePanel.add(stellenbeschreibungBox);
			
			attributePanel.add(bewerbungsfristLabel);
			attributePanel.add(bewerbungsfrist);
			
			mainPanel.add(ausschreibungAnlegenButton);
			
			try {
				ClientSideSettings.getProjektAdministration().findAllProjektByTeilnehmerId(ClientSideSettings.getCurrentUser().getId(),new GetAllProjekteByIDCallback());
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}

		
	/**
		private class GetMarktplatzCallback implements AsyncCallback {

			public void onFailure(Throwable caught) {
				Window.alert("Suchen läuft noch nit so!");

			}

			public void onSuccess(Projektmarktplatz result) {
				
				
				//RootPanel.get("Content").clear();
				//Entweder hier oder unten...
				//TODO Konstruktor für nächste Klasse (Projekt Suchen)

			}

		}
	*/
		private class AnlegenClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) {
				try {
					int pid = pVector.elementAt(projektListbox.getSelectedIndex()).getId();	
					ClientSideSettings.getProjektAdministration().createAusschreibung(stellenbeschreibungBox.getText(),
							bewerbungsfrist.getValue(), ausschreibungTitelBox.getText(), "laufend", pid, 2, ClientSideSettings.getCurrentUser().getId(), new CreateAusschreibungCallback());
				} catch (Exception e) {
					Window.alert(e.toString());
					e.printStackTrace();
				}
				
			}
		};

		private class CreateAusschreibungCallback implements AsyncCallback {

			public void onFailure(Throwable caught) {
				Window.alert("Dat läuft noch nit so!");

			}

			public void onSuccess(Object result) {
				RootPanel.get("Content").clear();
				Window.alert("Ausschreibung angelegt!");

			}

		}
		
		private class GetAllProjekteByIDCallback implements AsyncCallback<Vector<Projekt>> {

			public void onFailure(Throwable caught) {
				Window.alert("Nein das falsch");
			}

			public void onSuccess(Vector<Projekt> result) {
				//Window.alert("Joo Sucess");
				//Window.alert(ClientSideSettings.getCurrentUser().getVorname());
				for (int i = 0; i < result.size(); i++) {
					Projekt p1 = result.elementAt(i);
					pVector.add(p1);
					projektListbox.addItem(p1.getName());
				}
				
			}
		}

}
