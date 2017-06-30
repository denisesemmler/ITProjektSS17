
package de.hdm.itprojekt.shared.report;

import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;

import de.hdm.itprojekt.client.gui.ClientSideSettings;
import de.hdm.itprojekt.shared.ReportServiceAsync;
import de.hdm.itprojekt.shared.bo.reports.BewerbungReport;

/**
 * Erstellt einen Bericht aller Bewerbungen vom eingeloggten Teilnehmer
 * 
 * @author Jiayi
 *
 */
public class AlleBewerbungenNutzer extends SimpleReport {
	public AlleBewerbungenNutzer() {
		super("Ihre Bewerbung - " + ClientSideSettings.getCurrentUser().getVorname() + " "
				+ ClientSideSettings.getCurrentUser().getNachname());
		// Report Generator
		ReportServiceAsync reportGenerator = ClientSideSettings.getReportGenerator();

		// Callback des Service calls
		final AsyncCallback<List<BewerbungReport>> initReportGeneratorCallback = new AsyncCallback<List<BewerbungReport>>() {
			@Override
			public void onFailure(Throwable caught) {
				ClientSideSettings.getLogger().severe("Der ReportGenerator konnte nicht initialisiert werden!");
			}

			@Override
			public void onSuccess(List<BewerbungReport> result) {
				setSize(result.size());
				render(result);
			}
		};
		reportGenerator.getAllBewerbungenForUser(ClientSideSettings.getCurrentUser().getId(),
				initReportGeneratorCallback);

	}

	@Override
	protected void setSize(int i) {
		Label test = new Label("Anzahl Berichte: " + i);
		this.add(test);
	}

	private void render(List<BewerbungReport> ausschreibungen) {
		CellTable<BewerbungReport> table = new CellTable<BewerbungReport>();

		// Spalte für Stellennamen definieren
		TextColumn<BewerbungReport> stelleColumn = new TextColumn<BewerbungReport>() {
			@Override
			public String getValue(BewerbungReport bewerbung) {
				return bewerbung.getBewerbungName();
			}
		};

		// Spalte für Projektname definieren
		TextColumn<BewerbungReport> projektColumn = new TextColumn<BewerbungReport>() {
			@Override
			public String getValue(BewerbungReport bewerbung) {
				return bewerbung.getProjektName();
			}
		};

		// Spalte für Ansprechpartner definieren
		TextColumn<BewerbungReport> ansprechpartnerColumn = new TextColumn<BewerbungReport>() {
			@Override
			public String getValue(BewerbungReport bewerbung) {
				return bewerbung.getAnsprechpartnerName();
			}
		};

		// Spalte für Bewerbungsfrist definieren
		TextColumn<BewerbungReport> bewerbungsfristColumn = new TextColumn<BewerbungReport>() {
			@Override
			public String getValue(BewerbungReport bewerbung) {
				return bewerbung.getFrist().toString();
			}
		};

		// Spalte für Titel der Bewerbung definieren
		TextColumn<BewerbungReport> bewerbungsTitelColumn = new TextColumn<BewerbungReport>() {
			@Override
			public String getValue(BewerbungReport bewerbung) {
				return bewerbung.getTitel();
			}
		};

		// Spalte für Text der Bewerbung definieren
		TextColumn<BewerbungReport> bewerbungsTextColumn = new TextColumn<BewerbungReport>() {
			@Override
			public String getValue(BewerbungReport bewerbung) {
				return bewerbung.getBewerbungsText();
			}
		};

		// Spalte für Status der Bewerbung definieren
		TextColumn<BewerbungReport> statusColumn = new TextColumn<BewerbungReport>() {
			@Override
			public String getValue(BewerbungReport bewerbung) {
				return bewerbung.getStatus();
			}
		};

		// Spalten einen Namen geben
		table.addColumn(projektColumn, "Projekt");
		table.addColumn(stelleColumn, "Stelle");

		table.addColumn(bewerbungsTitelColumn, "Bewerbungstitel");
		table.addColumn(bewerbungsTextColumn, "Bewerbungstext");

		table.addColumn(ansprechpartnerColumn, "Ansprechpartner");
		table.addColumn(bewerbungsfristColumn, "Bewerbungsfrist");
		table.addColumn(statusColumn, "Status");

		// Daten der Tabelle hinzufügen
		table.setRowCount(ausschreibungen.size(), true);
		table.setRowData(0, ausschreibungen);

		this.add(table);
	}
}