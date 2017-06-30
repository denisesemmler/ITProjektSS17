package de.hdm.itprojekt.client.report.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.itprojekt.shared.report.AlleAusschreibungen;
import de.hdm.itprojekt.shared.report.AlleAusschreibungenNutzer;
import de.hdm.itprojekt.shared.report.AlleBewerbungenNutzer;
import de.hdm.itprojekt.shared.report.BewerbungVorschlaege;
import de.hdm.itprojekt.shared.report.BewerbungZuAusschreibung;
import de.hdm.itprojekt.shared.report.FanInFanOutReport;
import de.hdm.itprojekt.shared.report.Projektverpflechtungen;

/**
 * Navigation, die auf die unterschiedlichen Berichte verlinkt.
 * @author Jiayi
 *
 */
public class ReportNavigation extends VerticalPanel implements ClickHandler{
	
	// Buttons init
	private Button allAds  = new Button("(3) Alle Ausschreibungen", this);
	private Button adsForProfile  = new Button("Alle Ausschreibungen, die Ich erstellt habe.", this);
	private Button vorschlag  = new Button("(4) Vorschläge", this);	
	private Button adsForApplication  = new Button("(5) Alle Bewerbungen,die sich bei Mir beworben haben.", this);
	private Button openApplication  = new Button("(6) Alle Bewerbungen von Ausschreibung des Benutzers", this);
	private Button projektverpflechtungen = new Button("(7) Projektverpflechtungen", this);
	private Button fanInFanOut = new Button("(8) Fan-In/Fan-Out Analyse", this);
	
	ReportWrapper parent;
	
	/**
	 * Constuctor 
	 * @param parent Verweis auf den Wrapper der Reports. Wrapper beinhaltet die Naviagtion selbst (linke Seite) und den Bericht (rechte Seite)
	 */
	public ReportNavigation(ReportWrapper parent){	
		
		// Buttons Style zuordnen
		parent.setStylePrimaryName("reportPanel");
		allAds.setStylePrimaryName("button1");
		vorschlag.setStylePrimaryName("button1");
		adsForProfile.setStylePrimaryName("button1");
		adsForApplication.setStylePrimaryName("button1");
		openApplication.setStylePrimaryName("button1");
		projektverpflechtungen.setStylePrimaryName("button1");
		fanInFanOut.setStylePrimaryName("button1");
		
		// Buttons den Panel hinzufügen
		this.add(allAds);
		this.add(adsForProfile);
		this.add(vorschlag);
		this.add(adsForApplication);
		this.add(openApplication);
		this.add(projektverpflechtungen);
		this.add(fanInFanOut);
		
		this.parent = parent;
	}
	/**
	 * Zeigt Bericht an, abhänig davon welcher Button gedrückt wurde
	 */
	public void onClick(ClickEvent event) {
		Widget sender = (Widget) event.getSource();

		if(sender == allAds) {
			this.parent.setReport(new AlleAusschreibungen());
		} else if (sender == adsForProfile){
			this.parent.setReport(new AlleAusschreibungenNutzer());
		} else if (sender == vorschlag) {
			this.parent.setReport(new BewerbungVorschlaege());
		} else if (sender == adsForApplication){
			this.parent.setReport(new BewerbungZuAusschreibung());
		} else if (sender == openApplication){
			this.parent.setReport(new AlleBewerbungenNutzer());
		} else if (sender == projektverpflechtungen){
			this.parent.setReport(new Projektverpflechtungen());
		} else if (sender == fanInFanOut){
			this.parent.setReport(new FanInFanOutReport());
		}
	}
}
