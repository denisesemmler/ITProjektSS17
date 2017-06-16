package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;



public class BewerbungAnlegen extends VerticalPanel {

	/**
	 * Erstellen der Panels
	 */
	private VerticalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();
	private VerticalPanel attributePanel = new VerticalPanel();

	/**
	 * Erstellen der Labels
	 */
	private Label marktplatzLabel = new Label("Projektmarktplatz wählen:");

	/**
	 * Erstellen der ListBox
	 */
	private ListBox marktplatzListbox = new ListBox();

	/**
	 * Erstellen der Buttons
	 */
	private Button marktplatzSuchenButton = new Button("Suchen", new SuchenClickHandler());

	
	
	/**
	 * Konstruktor für Anlegen der GUI
	 */
	public BewerbungAnlegen() {
		
		try {
			//Marktpätze aus DB lesen
			/**
			 * ClientSideSettings.getProjektAdministration().createProjekt(projektNameBox.getText(),
					projektBeschreibungArea.getText(), (startPicker.getValue()), (endPicker.getValue()),
					 ClientSideSettings.getCurrentUser().getId() 1, 1, new CreateProjectCallback());
			 */
			
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}

		// CSS Styling
		marktplatzLabel.addStyleName("Content-Label");

		mainPanel.add(editorPanel);

		editorPanel.add(attributePanel);

		attributePanel.add(marktplatzLabel);
		attributePanel.add(marktplatzListbox);
		marktplatzListbox.addItem("IT");
		marktplatzListbox.addItem("Bau");
		marktplatzListbox.addItem("Landwirtschaft");
		mainPanel.add(marktplatzSuchenButton);

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
	private class SuchenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			
			//TODO Konstruktor für nächste Klasse (Projekt Suchen)
			
		}
	};
}
