package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class ProjektVerwalten extends VerticalPanel{
	
	private VerticalPanel mainPanel = this;
	
	//Erstellen von Buttons
	private Button projektAnlegenButton = new Button("Anlegen",
			new NavigationsButtonHandler());
	private Button projektAndernButton = new Button("Andern",
			new NavigationsButtonHandler());
	private Button projektLoschenButton = new Button("Loschen",
			new NavigationsButtonHandler());

	public ProjektVerwalten() {
		RootPanel.get("Content").add(new HTML("<h2>Was willst Du tun?</h2>"));
		mainPanel.add(projektAnlegenButton);
		mainPanel.add(projektAndernButton);
		mainPanel.add(projektLoschenButton);
	}
	
	private class NavigationsButtonHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
	        
			Button active = (Button) event.getSource();
			
			switch(active.getText()){
			case "Anlegen":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new ProjektAnlegen());
			break;
			case "Andern":
				RootPanel.get("Content").clear();
				//Page zum Ändern
				Window.alert("Not implemented... yet");
				break;
			case "Loschen":					
				RootPanel.get("Content").clear();
		    	//Page zum Löschen
				Window.alert("Not implemented... yet");
				break;
			}	    	 		    	
	     }
	   };
}
