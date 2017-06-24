package de.hdm.itprojekt.shared.report;

import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;

import de.hdm.itprojekt.client.gui.ClientSideSettings;
import de.hdm.itprojekt.shared.ReportServiceAsync;
import de.hdm.itprojekt.shared.bo.reports.AusschreibungReport;
import de.hdm.itprojekt.shared.bo.reports.BewerbungReport;

public class AlleBewerbungenNutzer extends SimpleReport{
	public AlleBewerbungenNutzer() {
		super("Alle Bewerbungen für Nutzer");
		
ReportServiceAsync reportGenerator = ClientSideSettings.getReportGenerator();
    	
    	final AsyncCallback<List<BewerbungReport>> initReportGeneratorCallback = new AsyncCallback<List<BewerbungReport>>() {
            @Override
    		public void onFailure(Throwable caught) {
              ClientSideSettings.getLogger().severe("Der ReportGenerator konnte nicht initialisiert werden!");
            }
            @Override
    		public void onSuccess(List<BewerbungReport> result) {
            	setSize(result.size());
            	renderTable(result);
            }
          };
    	reportGenerator.getAllBewerbungenForUser(ClientSideSettings.getCurrentUser().getId(), initReportGeneratorCallback);        
	}
	
	private void setSize(int i) {
		Label test = new Label("Anzahl Berichte: "+i);
		this.add(test);
	}
	
	
	private void renderTable(List<BewerbungReport> ausschreibungen) {
		CellTable<BewerbungReport> table = new CellTable<BewerbungReport>();
		
		TextColumn<BewerbungReport> stelleColumn = new TextColumn<BewerbungReport>() {
			@Override
			public String getValue(BewerbungReport bewerbung) {
				return bewerbung.getBewerbungName();
			}
		};
		
		TextColumn<BewerbungReport> projektColumn = new TextColumn<BewerbungReport>() {
			@Override
			public String getValue(BewerbungReport bewerbung) {
				return bewerbung.getProjektName();
			}
		};
		
		TextColumn<BewerbungReport> ansprechpartnerColumn = new TextColumn<BewerbungReport>() {
			@Override
			public String getValue(BewerbungReport bewerbung) {
				return bewerbung.getAnsprechpartnerName();
			}
		};
		
		TextColumn<BewerbungReport> bewerbungsfristColumn = new TextColumn<BewerbungReport>() {
			@Override
			public String getValue(BewerbungReport bewerbung) {
				return bewerbung.getFrist().toString();
			}
		};
		
		
		
		//idColumn.setSortable(true);
		
		table.addColumn(stelleColumn, "Stelle");
		table.addColumn(projektColumn, "Projekt");
		table.addColumn(ansprechpartnerColumn, "Ansprechpartner");
		table.addColumn(bewerbungsfristColumn, "Bewerbungsfrist");

		
		table.setRowCount(ausschreibungen.size(), true);
	    table.setRowData(0, ausschreibungen);
	    
		this.add(table);
	}
}
