package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BewerbungBewerten extends VerticalPanel{

	private VerticalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();

	private Label bewerbungBearbeiten = new Label("Wählen Sie hier die zu bewertende Bewerbung aus");
	
	
	public BewerbungBewerten() {
		
		mainPanel.add(editorPanel);
		editorPanel.add(bewerbungBearbeiten);
		
		
	}
	
}
