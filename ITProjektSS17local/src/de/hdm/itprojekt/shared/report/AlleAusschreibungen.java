package de.hdm.itprojekt.shared.report;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.itprojekt.client.gui.ClientSideSettings;

import de.hdm.itprojekt.server.db.AusschreibungMapper;
import de.hdm.itprojekt.shared.ReportServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;

public class AlleAusschreibungen extends SimpleReport {
      
	public AlleAusschreibungen() {
    	ReportServiceAsync reportGenerator = ClientSideSettings.getReportGenerator();
    	final AsyncCallback<List<Ausschreibung>> initReportGeneratorCallback = new AsyncCallback<List<Ausschreibung>>() {
            @Override
    		public void onFailure(Throwable caught) {
              ClientSideSettings.getLogger().severe(
                  "Der ReportGenerator konnte nicht initialisiert werden!");
            }

            @Override
    		public void onSuccess(List<Ausschreibung> result) {
            	setSize(result.size());
            	
            }
          };
    	reportGenerator.getAllAusschreibungen(initReportGeneratorCallback);        
	}
	
	
	private void setSize(int i) {
		Label test = new Label("Anzahl Berichte: "+i);
		this.add(test);
	}
}
