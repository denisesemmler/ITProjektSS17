package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author Patricia
 *
 */

public class BewerbungBewerten extends VerticalPanel {

	private VerticalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();
	private ListBox marktplatzListBox = new ListBox();
	private ListBox projektListBox = new ListBox();
	private ListBox ausschreibungListBox = new ListBox();
	private ListBox bewerbungListBox = new ListBox();

	private Label bewerbungBewertenLabel = new Label("<h2>Wählen Sie hier die zu bewertende Bewerbung aus</h2>");

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

}
