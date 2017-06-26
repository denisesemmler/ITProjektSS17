package de.hdm.itprojekt.shared.report;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;

import de.hdm.itprojekt.client.gui.ClientSideSettings;
import de.hdm.itprojekt.shared.ReportServiceAsync;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.reports.BewerbungReport;

public class BewerbungZuAusschreibung extends SimpleReport {

	public BewerbungZuAusschreibung() {
		super("Ihre Bewerbungen");
    	ReportServiceAsync reportGenerator = ClientSideSettings.getReportGenerator();
    	
    	final AsyncCallback<List<BewerbungReport>> initReportGeneratorCallback = new AsyncCallback<List<BewerbungReport>>() {
            @Override
    		public void onFailure(Throwable caught) {
              ClientSideSettings.getLogger().severe("Der ReportGenerator konnte nicht initialisiert werden!");
            }
            @Override
    		public void onSuccess(List<BewerbungReport> result) {
            	setSize(result.size());
            	renderResults(result);
            }
          };
    	reportGenerator.getAllBewerbungenUser(ClientSideSettings.getCurrentUser().getId(), initReportGeneratorCallback);        
	}
	
	private void setSize(int i) {
		Label test = new Label("Anzahl Berichte: "+i);
		this.add(test);
	}
	
	private void renderResults(List<BewerbungReport> bewerbungen) {
		for(BewerbungReport bewerbung: bewerbungen) {
			String dataRow = "<tr>"
							+ "<td>" + bewerbung.getBewerberName() + "</td>"
							+ "<td>" + bewerbung.getBewerbungName() + "</td>"
							+ "<td>" + bewerbung.getProjektName() + "</td>"
							+ "</tr>";
			
			String eigenschaftenRow = "<tr><td colspan='3'><ul>";
			for(Eigenschaft eigenschaft: bewerbung.getEigenschaften()) {
				eigenschaftenRow = eigenschaftenRow + "<li>" + eigenschaft.getName() + ": " + eigenschaft.getWertAsString() + "</li>";
			}
			eigenschaftenRow = eigenschaftenRow + "</ul></td></tr>";
			HTML entry = new HTML("<table class='reportTable'><tr><th>Name des Bewerbers</th><th>Auf Stelle</th><th>Projekt</th></tr>" +dataRow+eigenschaftenRow + "</table>");
			
			this.add(entry);
		}
	}

}
