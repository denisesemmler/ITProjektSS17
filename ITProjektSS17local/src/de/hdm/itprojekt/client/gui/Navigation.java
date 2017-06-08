package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;


public class Navigation extends HorizontalPanel{
	
	private Button navProjektVerwaltenButton = new Button("Projekte verwalten",
			new NavigationsButtonHandler());
	private Button navAusschreibungVerwaltenButton = new Button("Ausschreibungen verwalten",
			new NavigationsButtonHandler());
	private Button navBewerbungVerwaltenButton = new Button("Bewerbungen verwalten",
			new NavigationsButtonHandler());
	private Button navProfilVerwaltenButton = new Button("Profil verwalten",
			new NavigationsButtonHandler());
	private Button navAbmeldenButton = new Button("Abmelden",
			new NavigationsButtonHandler());

	public Navigation(){
		//CSS Style Zuweisung 
		this.setStylePrimaryName("Navi");
		navProjektVerwaltenButton.setStylePrimaryName("navi-button");
		navAusschreibungVerwaltenButton.setStylePrimaryName("navi-button");
		navBewerbungVerwaltenButton.setStylePrimaryName("navi-button");
		navProfilVerwaltenButton.setStylePrimaryName("navi-button");
		navAbmeldenButton.setStylePrimaryName("navi-button");
		
		this.add(navProjektVerwaltenButton);
		this.add(navAusschreibungVerwaltenButton);
		this.add(navBewerbungVerwaltenButton);
		this.add(navProfilVerwaltenButton);
		this.add(navAbmeldenButton);
	}
	
	private class NavigationsButtonHandler implements ClickHandler {
		public void onClick(ClickEvent e) {

			Button active = (Button) e.getSource();
			
			switch (active.getText()){
			case "Projekte verwalten":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new ProjektVerwalten());
				break;
			case "Ausschreibungen verwalten":
				Window.alert("Not implemented... yet");
				break;
			case "Bewerbungen verwalten":
				Window.alert("Not implemented... yet");
				break;
			case "Profil verwalten":
				Window.alert("Not implemented... yet");
				break;
			case "Abmelden":
				Window.alert("Not implemented... yet \nBut Hell Yeah.. go fuck yourself!");
				break;
			
			}
		}
	}
}

