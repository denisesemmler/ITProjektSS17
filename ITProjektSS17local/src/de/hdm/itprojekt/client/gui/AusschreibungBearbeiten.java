package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.client.ClientSideSettings;
import de.client.gui.MeinProfilEditor.ProfilEigenschaftenCallback;

public class AusschreibungBearbeiten extends VerticalPanel {
	
	


		/**
		 * Erstellen der Panels
		 */
		private VerticalPanel mainPanel = this;
		private VerticalPanel editorPanel = new VerticalPanel();
		private VerticalPanel attributePanel = new VerticalPanel();

		/**
		 * Erstellen der Labels
		 */
	
		private Label ausschreibungTitelLabel = new Label ("Titel der Ausschreibung: ");
		private Label stellenbeschreibungLabel = new Label ("Stellenbeschreibung: ");
		private Label bewerbungsfristLabel = new Label ("Bewerbungsfrist ändern: ");
		
		
		private TextBox ausschreibungTitelBox = new TextBox(); 
		private TextBox stellenbeschreibungBox = new TextBox();
		
		private DatePicker bewerbungsfrist = new DatePicker();
	

		/**
		 * Erstellen der Buttons
		 */
		private Button ausschreibungAndernButton = new Button("Aenderungen speichern", new SpeichernClickHandler());
		
		/**
		 * Konstruktor für Anlegen der GUI
		 */
		public AusschreibungBearbeiten() {
		
			// CSS Styling
			ausschreibungTitelLabel.addStyleName("Content-Label");
			stellenbeschreibungLabel.addStyleName("Content-Label");
			bewerbungsfristLabel.addStyleName("Content-label");

			mainPanel.add(editorPanel);

			editorPanel.add(attributePanel);
			
			attributePanel.add(ausschreibungTitelLabel);
			attributePanel.add(ausschreibungTitelBox);
			ausschreibungTitelBox.setText("Hallo i bims");
			
			attributePanel.add(stellenbeschreibungLabel);
			attributePanel.add(stellenbeschreibungBox);
			
			attributePanel.add(bewerbungsfristLabel);
			attributePanel.add(bewerbungsfrist);
			
			mainPanel.add(ausschreibungAndernButton);

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
		private class SpeichernClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) {
				
				//TODO Konstruktor für nächste Klasse (Projekt Suchen)
				
			}
		};


	}


