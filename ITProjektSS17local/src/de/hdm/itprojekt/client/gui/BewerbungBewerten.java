package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author Patricia
 *
 */

public class BewerbungBewerten extends VerticalPanel {

	private VerticalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();
	
	//Dropdown Menü
	private ListBox marktplatzListBox = new ListBox();
	private ListBox projektListBox = new ListBox();
	private ListBox ausschreibungListBox = new ListBox();
	private ListBox bewerbungListBox = new ListBox();

	//Überschrifts Label
	private Label bewerbungBewertenLabel = new Label("Wählen Sie hier die zu bewertende Bewerbung aus");
	
	//Label für die aufgerufene Bewerbung
	private Label erstellDatum = new Label("Erstell Datum");
	private Label bewerbungsText = new Label ("Bewerbungs Text");
	private Label idBewerbung = new Label ("ID Bewerbung");
	private Label bewertung = new Label ("Bewertung");
	private Label status = new Label ("Status");
	private Label stellungnahme = new Label ("Stellungnahme");
	
	//Button zum speichern
	private Button bewertungAbgebenButton = new Button("Bewertung abgeben", new SaveChangesClickHandler());

	public BewerbungBewerten() {

		mainPanel.add(editorPanel);
		editorPanel.add(bewerbungBewertenLabel);

		/*
		 * gedanklich den Klick aus der Verwaltung 
		 * "RootPanel.get("Content").add(new BewerbungBewerten());
		 * mit dem Konstruiktoraufruf verbinden
		 */
		editorPanel.add(marktplatzListBox);
		marktplatzListBox.addChangeHandler(new MarktplatzOnChangeHandler());

		editorPanel.add(projektListBox);
		projektListBox.addChangeHandler(new ProjektOnChangeHandler());

		editorPanel.add(ausschreibungListBox);
		projektListBox.addChangeHandler(new AusschreibungOnChangeHandler());

		editorPanel.add(bewerbungListBox);
		projektListBox.addChangeHandler(new BewerbungOnChangeHandler());
		
		/*
		 * ausgewählte Bewerbung soll angezeigt werden, 
		 * dazu werden horizontale Panels auf vertikale Panels gelegt
		 */
		HorizontalPanel idPanel = new HorizontalPanel();
		idPanel.add(idBewerbung);
		editorPanel.add(idPanel);
		
		HorizontalPanel datumPanel = new HorizontalPanel();
		datumPanel.add(erstellDatum);
		editorPanel.add(datumPanel);
		
		HorizontalPanel bewerbungPanel = new HorizontalPanel();
		bewerbungPanel.add(bewerbungsText);
		editorPanel.add(bewerbungPanel);
		
		HorizontalPanel statusPanel = new HorizontalPanel();
		statusPanel.add(status);
		editorPanel.add(statusPanel);
		
		HorizontalPanel bewertungPanel = new HorizontalPanel();
		bewertungPanel.add(bewertung);
		editorPanel.add(bewertungPanel);
		
		HorizontalPanel stellungnahmePanel = new HorizontalPanel();
		stellungnahmePanel.add(stellungnahme);
		editorPanel.add(stellungnahmePanel);
		
		//Button zum mainPanel hinzugefügt
		mainPanel.add(bewertungAbgebenButton);

	}
	

	// Innerclass Marktplatz Handler
	private class MarktplatzOnChangeHandler implements ChangeHandler {
		/**
		 * Diese Methode wird ausgeführt wenn im Projetkmarktplatz Handler 
		 * in der ListBox ein Element ausgewählt wird.
		 */
		@Override
		public void onChange(ChangeEvent event) {
			
		}
	}
	
	//Innerclass Projekt Handler
	private class ProjektOnChangeHandler implements ChangeHandler {
		/**
		 * Diese Methode wird ausgeführt wenn im Projekt Handler 
		 * in der ListBox ein Element ausgewählt wird.
		 */
		@Override
		public void onChange(ChangeEvent event) {
			
		}
		
	}
	
	//Innerclass Ausschreibung Handler
	private class AusschreibungOnChangeHandler implements ChangeHandler {
		/**
		 * Diese Methode wird ausgeführt wenn im Ausschreibung Handler 
		 * in der ListBox ein Element ausgewählt wird.
		 */
		@Override
		public void onChange(ChangeEvent event) {
			
		}
		
	}
	
	//Innerclass Bewerbung Handler
	private class BewerbungOnChangeHandler implements ChangeHandler {
		/**
		 * Diese Methode wird ausgeführt wenn im Bewerbung Handler 
		 * in der ListBox ein Element ausgewählt wird.
		 */
		@Override
		public void onChange(ChangeEvent event) {
			
		}
		
	}

	//Innerclass Bewertung abgeben Speicher Button Handler
	private class SaveChangesClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
		}
		
	}
	
	
}
