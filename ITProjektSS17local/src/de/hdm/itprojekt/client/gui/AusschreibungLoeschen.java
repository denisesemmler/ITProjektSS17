package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class AusschreibungLoeschen extends VerticalPanel {
	private VerticalPanel mainPanel = this;
	private Label AusschreibungLabel = new Label("Ausschreibung: ");
	private ListBox AusschreibungListbox = new ListBox();
	
	private Button ausschreibungLoeschenButton = new Button("Loeschen",
			new NavigationsButtonHandler());
	
	public AusschreibungLoeschen(){
		mainPanel.add(AusschreibungLabel);
		mainPanel.add(AusschreibungListbox);
		AusschreibungListbox.addItem("Ausschreibung 1");
		
		mainPanel.add(ausschreibungLoeschenButton);
	}
	
	private class NavigationsButtonHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
	        
			Button active = (Button) event.getSource();
			
			switch(active.getText()){
			case "Loeschen":
				Window.alert("Ausschreibung geloescht");
				RootPanel.get("Content").clear();
			break;
				    	 		    	
	     }
	   };
	}
}
