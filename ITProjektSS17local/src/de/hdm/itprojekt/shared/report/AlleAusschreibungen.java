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
		
		//int total = AusschreibungMapper.ausschreibungMapper().findAllAusschreibungen().size();
		Label test = new Label("Anzahl Berichte: ");
		
        try{
        	ReportServiceAsync reportGenerator = ClientSideSettings.getReportGenerator();
        	Window.alert((reportGenerator.getClass()) + "");
        	reportGenerator.getAllAusschreibungen(new AlleAusschreibungenCallback());
	        
        } catch(Exception e){
        	Window.alert(e.toString());
        	System.out.println(e.toString());
        	e.printStackTrace();
        }
        
		this.add(test);
	}
	
	private class AlleAusschreibungenCallback implements AsyncCallback<List<Ausschreibung>> {
		public void onFailure(Throwable caught) {
			Window.alert("Dat läuft noch nit so!");
			
		}		

		@Override
		public void onSuccess(List<Ausschreibung> result) {
			Window.alert("läuft");
			
		}
	}
	
}
