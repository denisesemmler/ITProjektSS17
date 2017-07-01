package de.hdm.itprojekt.shared.report;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;

import de.hdm.itprojekt.client.gui.ClientSideSettings;
import de.hdm.itprojekt.shared.ReportServiceAsync;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.reports.BewerbungReport;

/**
 * Erstellt einen Bericht mit allen Bewerbungen zu den Ausschreibungen vom eingeloggten Teilnehmer
 * 
 * @author Jiayi
 *
 */
public class BewerbungZuAusschreibung extends SimpleReport {

	public BewerbungZuAusschreibung() {
		super("Bewerbungen auf meine Ausschreibungen");
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
		reportGenerator.getAllBewerbungenUser(ClientSideSettings.getCurrentUser().getId(), initReportGeneratorCallback);
	}

	@Override
	protected void setSize(int i) {
		Label test = new Label("Anzahl Berichte: " + i);
		this.add(test);
	}

	private void render(List<BewerbungReport> bewerbungen) {
		for (BewerbungReport bewerbung : bewerbungen) {
			// Zeile für Daten über Bewerbung
			String dataRow = "<tr>" + "<td>" + bewerbung.getBewerberName() + "</td>" + "<td>"
					+ bewerbung.getBewerbungName() + "</td>" + "<td>" + bewerbung.getProjektName() + "</td>" + "<td>"
					+ bewerbung.getTitel() + "</td>" + "<td>" + bewerbung.getBewerbungsText() + "</td>" + "</tr>";

			// Zelle mit den Eigenschaften
			String eigenschaftenRow = "<tr><td colspan='5'>Eigenschaften: <ul>";
			for (Eigenschaft eigenschaft : bewerbung.getEigenschaften()) {
				eigenschaftenRow = eigenschaftenRow + "<li>" + eigenschaft.getName() + ": "
						+ eigenschaft.getWertAsString() + "</li>";
			}
			eigenschaftenRow = eigenschaftenRow + "</ul></td></tr>";

			// HTMl generieren
			HTML entry = new HTML(
					"<table class='reportTable'><tr><th>Name des Bewerbers</th><th>Auf Stelle</th><th>Projekt</th><th>Bewerbungstitel</th><th>Bewerbungstext</th></tr>"
							+ dataRow + eigenschaftenRow + "</table>");

			this.add(entry);
		}
	}

}
