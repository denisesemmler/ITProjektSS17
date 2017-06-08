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
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;


public class ProjektBearbeiten extends VerticalPanel{
	
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
	private ListBox projektListbox = new ListBox();
	
	/**
	 * Erstellen der Buttons
	 */
	private Button projektSpeichernButton = new Button("Speichern",
			new NavigationsButtonHandler());

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
	
	public ProjektBearbeiten(){
		
		//CSS Styles
		projektSpeichernButton.setStylePrimaryName("grotte-button");
		
		mainPanel.add(editorPanel);
		
		editorPanel.add(attributePanel);
		editorPanel.add(datePanel);
		
		attributePanel.add(marktplatzLabel);
		attributePanel.add(projektListbox);
		projektListbox.addItem("Projekt 1 von User");
		projektListbox.addItem("Projekt 2 von User");
		projektListbox.addItem("Projekt X von User");
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
		
		mainPanel.add(projektSpeichernButton);
	
	}

	private class NavigationsButtonHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
	        
			Button active = (Button) event.getSource();
			
			switch(active.getText()){
			case "Speichern":
				Window.alert("Projekt gespeichert");
				RootPanel.get("Content").clear();
			break;
				    	 		    	
	     }
	   };
	}
}
