package de.hdm.itprojekt.client.gui;

import java.sql.Timestamp;
import java.util.Date;

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

import de.hdm.itprojekt.shared.bo.Projekt;


public class ProjektAnlegen extends VerticalPanel{

	/**
	 * Erstellen der Panels
	 */
	private VerticalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();
	private VerticalPanel attributePanel = new VerticalPanel();
	private HorizontalPanel datePanel = new HorizontalPanel();
	private VerticalPanel startPanel = new VerticalPanel();
	private VerticalPanel endPanel = new VerticalPanel();
	
	/**
	 * Erstellen der Labels 
	 */
	private Label marktplatzLabel = new Label ("Select Projektmarktplatz");
	private Label projektNameLabel = new Label("Projektname: ");
	private Label projektBeschreibungLabel = new Label("Projektbeschreibung: ");
	private Label startDateLabel = new Label("Start Datum: ");
	private Label endDateLabel = new Label("End Datum: ");
	
	/**
	 * Erstellen der ListBox
	 */
	private ListBox marktplatzListbox = new ListBox();
	
	/**
	 * Erstellen der Buttons
	 */
	private Button projektAnlegenButton = new Button("Anlegen",
			new CreateProjectClickHandler());
	
	/**
	 * Erstellen der TextBoxen
	 */
	private TextBox projektNameBox = new TextBox();
	private TextArea projektBeschreibungArea = new TextArea();
	/**
	 * Erstellen DatePicker
	 */
	private DatePicker startPicker = new DatePicker();
	private DatePicker endPicker = new DatePicker();
	
	
	/**
	 * Konstruktor für Anlegen der GUI
	 */
	public ProjektAnlegen() {
		
		//CSS Styling
		marktplatzLabel.addStyleName("Content-Label");
		projektNameLabel.addStyleName("Content-Label");
		projektBeschreibungLabel.addStyleName("Content-Label");
		startDateLabel.addStyleName("Content-Label");
		endDateLabel.addStyleName("Content-Label");
		
		mainPanel.add(editorPanel);
		
		editorPanel.add(attributePanel);
		editorPanel.add(datePanel);
		
		attributePanel.add(marktplatzLabel);
		attributePanel.add(marktplatzListbox);
		marktplatzListbox.addItem("IT");
		marktplatzListbox.addItem("Bau");
		marktplatzListbox.addItem("Landwirtschaft");
		attributePanel.add(projektNameLabel);
		attributePanel.add(projektNameBox);
		attributePanel.add(projektBeschreibungLabel);
		attributePanel.add(projektBeschreibungArea);
		
		datePanel.add(startPanel);
		datePanel.add(endPanel);
		
		startPanel.add(startDateLabel);
		startPanel.add(startPicker);
		
		endPanel.add(endDateLabel);
		endPanel.add(endPicker);
		
		//Set default Value 
		startPicker.setValue(new Date(), true);
		endPicker.setValue(new Date(), true);
		
		mainPanel.add(projektAnlegenButton);
		
	}
	
	private class CreateProjectCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Object result) {
			RootPanel.get("content").clear();
			Window.alert("Dat läuft noch nit so!");
			
		}
		
	}
	
	private class CreateProjectClickHandler implements ClickHandler {
		
		public void onClick(ClickEvent event) {			
	        Projekt project = new Projekt();
	        try{
	        
	        ClientSideSettings.getProjektAdministration().
	        createProjekt(projektNameBox.getText(), projektBeschreibungArea.getText(),
	        		(startPicker.getValue()),(endPicker.getValue()), new CreateProjectCallback ());
	        } catch(Exception e){
	        	Window.alert(e.toString());
	        	e.printStackTrace();
	        }
			
			//Altes Zeug
			Button active = (Button) event.getSource();
			
			switch(active.getText()){
			case "Anlegen":
				Window.alert("Projekt angelegt");
			break;
			/**case "Navigation":
					
				RootPanel.get("Content").clear();
		    	RootPanel.get("Navi").add(new Navigation());**/
			}	    	 		    	
	     }
	   };
}
