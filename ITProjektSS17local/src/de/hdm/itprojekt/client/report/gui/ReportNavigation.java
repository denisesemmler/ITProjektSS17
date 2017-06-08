package de.hdm.itprojekt.client.report.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.itprojekt.client.gui.ProjektVerwalten;

public class ReportNavigation extends VerticalPanel implements ClickHandler{
	private Button allAds  = new Button("Alle Ausschreibungen", this);
	private Button adsForProfile  = new Button("Alle Ausschreibungen für Profil", this);
	private Button adsForApplication  = new Button("Bewerbungen für Ausschreiben", this);
	private Button openApplication  = new Button("Alle offenen Bewerbungen", this);

	public ReportNavigation(){		
		this.add(allAds);
		this.add(adsForProfile);
		this.add(adsForApplication);
		this.add(openApplication);
	}
	
	public void onClick(ClickEvent event) {
		Widget sender = (Widget) event.getSource();

		if(sender == allAds) {
			Window.alert("Not yet implemented");
		} else if (sender == adsForProfile){
			Window.alert("Not yet implemented");
		} else if (sender == adsForApplication){
			Window.alert("Not yet implemented");
		} else if (sender == openApplication){
			Window.alert("Not yet implemented");
		}
	}
}
