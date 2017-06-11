package de.hdm.itprojekt.shared.report;


import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import de.hdm.itprojekt.client.gui.ClientSideSettings;

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
            	renderTable(result);
            }
          };
    	reportGenerator.getAllAusschreibungen(initReportGeneratorCallback);        
	}
	
	
	private void setSize(int i) {
		Label test = new Label("Anzahl Berichte: "+i);
		this.add(test);
	}
	
	private void renderTable(List<Ausschreibung> ausschreibungen) {
		CellTable<Ausschreibung> table = new CellTable<Ausschreibung>();
		
		TextColumn<Ausschreibung> idColumn = new TextColumn<Ausschreibung>() {
			@Override
			public String getValue(Ausschreibung ausschreibung) {
				return "" + ausschreibung.getId();
			}
		};
		
		TextColumn<Ausschreibung> titleColumn = new TextColumn<Ausschreibung>() {
			@Override
			public String getValue(Ausschreibung ausschreibung) {
				return ausschreibung.getTitel();
			}
		};
		
		TextColumn<Ausschreibung> bewerbungsfristColumn = new TextColumn<Ausschreibung>() {
			@Override
			public String getValue(Ausschreibung ausschreibung) {
				
				DateTimeFormat dtf = DateTimeFormat.getFormat("dd.MM.yyyy");
				return dtf.format(ausschreibung.getBewerbungsfrist());
			}
		};
		
		TextColumn<Ausschreibung> ansprechpartnerColumn = new TextColumn<Ausschreibung>() {
			@Override
			public String getValue(Ausschreibung ausschreibung) {
				return ""+ ausschreibung.getProfil_idSuchprofil();
			}
		};
		
		TextColumn<Ausschreibung> projektColumn = new TextColumn<Ausschreibung>() {
			@Override
			public String getValue(Ausschreibung ausschreibung) {
				return ""+ ausschreibung.getProjekt_idProjekt();
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
