package de.hdm.itprojekt.shared.report;

import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;

import de.hdm.itprojekt.client.gui.ClientSideSettings;
import de.hdm.itprojekt.shared.ReportServiceAsync;
import de.hdm.itprojekt.shared.bo.reports.FanInFanOut;

/**
 * Erstellt eine Fan in Fan out Analyse für alle Teilnehmer
 * 
 * @author Jiayi
 *
 */
public class FanInFanOutReport extends SimpleReport {

	public FanInFanOutReport() {
		super("Fan-In/Fan-Out Anaylse");
		// Report Generator
		ReportServiceAsync reportGenerator = ClientSideSettings.getReportGenerator();

		// Callback des Service calls
		final AsyncCallback<List<FanInFanOut>> initReportGeneratorCallback = new AsyncCallback<List<FanInFanOut>>() {
			@Override
			public void onFailure(Throwable caught) {
				ClientSideSettings.getLogger().severe("Der ReportGenerator konnte nicht initialisiert werden!");
			}

			@Override
			public void onSuccess(List<FanInFanOut> result) {
				setSize(result.size());
				render(result);
			}
		};
		reportGenerator.getFanInFanOut(initReportGeneratorCallback);
	}

	@Override
	protected void setSize(int i) {
		Label test = new Label("Anzahl Berichte: " + i);
		this.add(test);
	}

	private void render(List<FanInFanOut> report) {
		CellTable<FanInFanOut> table = new CellTable<FanInFanOut>();

		// Spalte für Teilnehmername definieren
		TextColumn<FanInFanOut> nameColumn = new TextColumn<FanInFanOut>() {
			@Override
			public String getValue(FanInFanOut report) {
				return "" + report.getTeilnehmerName();
			}
		};

		// Spalte für Fan Out definieren
		TextColumn<FanInFanOut> fanOutColumn = new TextColumn<FanInFanOut>() {
			@Override
			public String getValue(FanInFanOut report) {
				return "" + report.getFanOut();
			}
		};

		// Spalte für Fan In definieren
		TextColumn<FanInFanOut> fanInColumn = new TextColumn<FanInFanOut>() {
			@Override
			public String getValue(FanInFanOut report) {
				return "" + report.getFanIn();
			}
		};

		// Spalte für Analyse definieren
		TextColumn<FanInFanOut> analyseColumn = new TextColumn<FanInFanOut>() {
			@Override
			public String getValue(FanInFanOut report) {
				return "" + report.getAnalyse();
			}
		};

		// Spalten einen Namen geben
		table.addColumn(nameColumn, "Name");
		table.addColumn(fanOutColumn, "getätigte Bewerbungen");
		table.addColumn(fanInColumn, "getätigte Ausschreibungen");
		table.addColumn(analyseColumn, "Verhältnis");

		// Daten der Tabelle hinzufügen
		table.setRowCount(report.size(), true);
		table.setRowData(0, report);

		this.add(table);
	}

}
