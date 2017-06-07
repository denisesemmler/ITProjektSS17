package de.hdm.itprojekt.client.gui;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;


public class ProjektAnlegen extends VerticalPanel{

	/**
	 * Erstellen der Panels
	 */
	private VerticalPanel mainPanel = this;
	private VerticalPanel naviPanel = new VerticalPanel();
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
			new NavigationsButtonHandler());
	private Button naviButton = new Button("Navigation",
			new NavigationsButtonHandler());
	/**
	 * Erstellen der TextBoxen
	 */
	private TextBox projektNameBox = new TextBox();
	/**
	 * Erstellen DatePicker
	 */
	private DatePicker startPicker = new DatePicker();
	private DatePicker endPicker = new DatePicker();
	
	
	/**
	 * Konstruktor für Anlegen der GUI
	 */
	public ProjektAnlegen() {
		mainPanel.add(naviPanel);
		mainPanel.add(editorPanel);
		
		naviPanel.add(naviButton);
		
		editorPanel.add(attributePanel);
		editorPanel.add(datePanel);
		
		attributePanel.add(marktplatzLabel);
		attributePanel.add(marktplatzListbox);
		attributePanel.add(projektNameLabel);
		attributePanel.add(projektNameBox);
		
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
	
	private class NavigationsButtonHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
	        
			Button active = (Button) event.getSource();
			
			switch(active.getText()){
			case "Anlegen":
				Window.alert("Projekt angelegt");
			break;
			case "Navigation":
				Window.alert("Weiterleiten zu Navigation");
				
				RootPanel.get("stockWatcher").clear();
		    	RootPanel.get("stockWatcher").add(new Navigation());
			}
			
	    	  
	    	 
	    	 //RootPanel.get("stockWather").add(loginButton);
	    	 //RootPanel.get("stockWatcher").add(showcase); 
	    	 		    	
	     }
	   };
}
