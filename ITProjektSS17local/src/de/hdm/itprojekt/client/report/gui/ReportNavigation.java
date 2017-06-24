package de.hdm.itprojekt.client.report.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.itprojekt.shared.report.AlleAusschreibungen;
import de.hdm.itprojekt.shared.report.AlleAusschreibungenNutzer;
import de.hdm.itprojekt.shared.report.AlleBewerbungenNutzer;
import de.hdm.itprojekt.shared.report.BewerbungZuAusschreibung;
public class ReportNavigation extends VerticalPanel implements ClickHandler{
	private Button allAds  = new Button("Alle Ausschreibungen", this);
	private Button adsForProfile  = new Button("Alle Ausschreibungen für Profil", this);
	private Button adsForApplication  = new Button("Bewerbungen für Ausschreiben", this);
	private Button openApplication  = new Button("Alle offenen Bewerbungen", this);
	ReportWrapper parent;
	
	public ReportNavigation(ReportWrapper parent){		
		this.add(allAds);
		this.add(adsForProfile);
		this.add(adsForApplication);
		this.add(openApplication);
		
		this.parent = parent;
	}
	
	public void onClick(ClickEvent event) {
		Widget sender = (Widget) event.getSource();

		if(sender == allAds) {
			this.parent.setReport(new AlleAusschreibungen());
		} else if (sender == adsForProfile){
			this.parent.setReport(new AlleAusschreibungenNutzer());
		} else if (sender == adsForApplication){
			this.parent.setReport(new BewerbungZuAusschreibung());
		} else if (sender == openApplication){
			Window.alert("Not yet implemented");
		}
	}
}
