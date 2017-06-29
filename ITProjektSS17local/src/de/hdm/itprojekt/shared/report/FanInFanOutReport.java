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
import de.hdm.itprojekt.shared.bo.reports.FanInFanOut;

public class FanInFanOutReport extends SimpleReport {

	public FanInFanOutReport() {
		super("Fan-In/Fan-Out Anaylse");
ReportServiceAsync reportGenerator = ClientSideSettings.getReportGenerator();
    	
    	final AsyncCallback<List<FanInFanOut>> initReportGeneratorCallback = new AsyncCallback<List<FanInFanOut>>() {
            @Override
    		public void onFailure(Throwable caught) {
              ClientSideSettings.getLogger().severe("Der ReportGenerator konnte nicht initialisiert werden!");
            }
            @Override
    		public void onSuccess(List<FanInFanOut> result) {
            	setSize(result.size());
            	renderTable(result);
            }
          };
    	reportGenerator.getFanInFanOut(initReportGeneratorCallback);
	}
	
	private void setSize(int i) {
		Label test = new Label("Anzahl Berichte: "+i);
		this.add(test);
	}
	
	private void renderTable(List<FanInFanOut> report) {
		CellTable<FanInFanOut> table = new CellTable<FanInFanOut>();
		
		TextColumn<FanInFanOut> nameColumn = new TextColumn<FanInFanOut>() {
			@Override
			public String getValue(FanInFanOut report) {
				return "" + report.getTeilnehmerName();
			}
		};
		
		TextColumn<FanInFanOut> fanOutColumn = new TextColumn<FanInFanOut>() {
			@Override
			public String getValue(FanInFanOut report) {
				return "" + report.getFanOut();
			}
		};
		
		TextColumn<FanInFanOut> fanInColumn = new TextColumn<FanInFanOut>() {
			@Override
			public String getValue(FanInFanOut report) {
				return "" + report.getFanIn();
			}
		};
		
		TextColumn<FanInFanOut> analyseColumn = new TextColumn<FanInFanOut>() {
			@Override
			public String getValue(FanInFanOut report) {
				return "" + report.getAnalyse();
			}
		};
		
		
		//idColumn.setSortable(true);
		
		table.addColumn(nameColumn, "Name");
		table.addColumn(fanOutColumn, "Fan-Out/Bewerbung");
		table.addColumn(fanInColumn, "Fan-In/Ausschreibung");
		table.addColumn(analyseColumn, "Fan-In/Fan-Out Analyse");
		
		table.setRowCount(report.size(), true);
	    table.setRowData(0, report);
	    
		this.add(table);
	}
	
}
