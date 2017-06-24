package de.hdm.itprojekt.shared.report;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.client.gui.ClientSideSettings;
import de.hdm.itprojekt.shared.ReportServiceAsync;
import de.hdm.itprojekt.shared.bo.reports.BewerbungReport;

public class BewerbungZuAusschreibung extends SimpleReport {

	BewerbungZuAusschreibung() {
		super("Ihre Bewerbungen");
    	ReportServiceAsync reportGenerator = ClientSideSettings.getReportGenerator();
    	
    	final AsyncCallback<List<BewerbungReport>> initReportGeneratorCallback = new AsyncCallback<List<BewerbungReport>>() {
            @Override
    		public void onFailure(Throwable caught) {
              ClientSideSettings.getLogger().severe("Der ReportGenerator konnte nicht initialisiert werden!");
            }
            @Override
    		public void onSuccess(List<BewerbungReport> result) {
            	renderResults(result);
            }
          };
    	reportGenerator.getAllBewerbungenUser(ClientSideSettings.getCurrentUser().getId(), initReportGeneratorCallback);        
	}
	
	private void renderResults(List<BewerbungReport> bewerbungen) {
		
	}

}
