package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;




public class AusschreibungAnlegen extends VerticalPanel{
	


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
			projektListbox.addItem("IT");
			
			attributePanel.add(ausschreibungTitelLabel);
			attributePanel.add(ausschreibungTitelBox);
			
			attributePanel.add(stellenbeschreibungLabel);
			attributePanel.add(stellenbeschreibungBox);
			
			attributePanel.add(bewerbungsfristLabel);
			attributePanel.add(bewerbungsfrist);
			
			mainPanel.add(ausschreibungAnlegenButton);

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
				
				//TODO Konstruktor für nächste Klasse (Projekt Suchen)
				
			}
		};

}
