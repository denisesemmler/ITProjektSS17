package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class ProjektLoeschen extends VerticalPanel{

	private VerticalPanel mainPanel = this;
	private Label projektNameLabel = new Label("Projektname: ");
	private ListBox projektListbox = new ListBox();
	
	private Button projektLoeschenButton = new Button("Loeschen",
			new NavigationsButtonHandler());
	
	public ProjektLoeschen(){
		mainPanel.add(projektNameLabel);
		mainPanel.add(projektListbox);
		projektListbox.addItem("Projekt 1 von User");
		projektListbox.addItem("Projekt 2 von User");
		projektListbox.addItem("Projekt X von User");
		mainPanel.add(projektLoeschenButton);
	}
	
	private class NavigationsButtonHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
	        
			Button active = (Button) event.getSource();
			
			switch(active.getText()){
			case "Loeschen":
				Window.alert("Projekt geloescht");
				RootPanel.get("Content").clear();
			break;
				    	 		    	
	     }
	   };
	}
}
