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

public class AlleAusschreibungen extends SimpleReport {
      
	public AlleAusschreibungen() {
		super("Alle Ausschreibungen");
    	ReportServiceAsync reportGenerator = ClientSideSettings.getReportGenerator();
    	
    	final AsyncCallback<List<AusschreibungReport>> initReportGeneratorCallback = new AsyncCallback<List<AusschreibungReport>>() {
            @Override
    		public void onFailure(Throwable caught) {
              ClientSideSettings.getLogger().severe("Der ReportGenerator konnte nicht initialisiert werden!");
            }
            @Override
    		public void onSuccess(List<AusschreibungReport> result) {
            	setSize(result.size());
            	renderTable(result);
            }
          };
    	reportGenerator.getAllAusschreibungen(initReportGeneratorCallback);        
	}
	
	private void setSize(int i) {
		Label test = new Label("Anzahl Berichte: "+i);
		this.add(test);
	}
	
	private void renderTable(List<AusschreibungReport> ausschreibungen) {
		CellTable<AusschreibungReport> table = new CellTable<AusschreibungReport>();
		
		TextColumn<AusschreibungReport> idColumn = new TextColumn<AusschreibungReport>() {
			@Override
			public String getValue(AusschreibungReport ausschreibung) {
				return "" + ausschreibung.getId();
			}
		};
		
		TextColumn<AusschreibungReport> titleColumn = new TextColumn<AusschreibungReport>() {
			@Override
			public String getValue(AusschreibungReport ausschreibung) {
				return ausschreibung.getTitel();
			}
		};
		
		TextColumn<AusschreibungReport> bewerbungsfristColumn = new TextColumn<AusschreibungReport>() {
			@Override
			public String getValue(AusschreibungReport ausschreibung) {
				
				DateTimeFormat dtf = DateTimeFormat.getFormat("dd.MM.yyyy");
				return dtf.format(ausschreibung.getBewerbungsfrist());
			}
		};
		
		TextColumn<AusschreibungReport> ansprechpartnerColumn = new TextColumn<AusschreibungReport>() {
			@Override
			public String getValue(AusschreibungReport ausschreibung) {
				return ""+ ausschreibung.getProfil_idSuchprofil();
			}
		};
		
		TextColumn<AusschreibungReport> projektColumn = new TextColumn<AusschreibungReport>() {
			@Override
			public String getValue(AusschreibungReport ausschreibung) {
				return ""+ ausschreibung.getProjektName();
			}
		};
		
		//idColumn.setSortable(true);
		
		table.addColumn(idColumn, "ID");
		table.addColumn(titleColumn, "Titel");
		table.addColumn(bewerbungsfristColumn, "Bewerbungsfrist");
		table.addColumn(ansprechpartnerColumn, "Ansprechpartner");
		table.addColumn(projektColumn, "Projekt");
		
		table.setRowCount(ausschreibungen.size(), true);
	    table.setRowData(0, ausschreibungen);
	    
		this.add(table);
	}
}
