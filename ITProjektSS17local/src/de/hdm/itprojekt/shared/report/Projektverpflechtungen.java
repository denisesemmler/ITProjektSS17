package de.hdm.itprojekt.shared.report;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;

import de.hdm.itprojekt.client.gui.ClientSideSettings;
import de.hdm.itprojekt.shared.ReportServiceAsync;
import de.hdm.itprojekt.shared.bo.reports.BewerbungReport;

public class Projektverpflechtungen extends SimpleReport {

	public Projektverpflechtungen() {
		// Report Generator
		super("Projektverpflechtungen");

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
		reportGenerator.getProjektverpflechtungen(ClientSideSettings.getCurrentUser().getId(),
				initReportGeneratorCallback);
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
					+ bewerbung.getBewerbungName() + "</td>" + "<td>" + bewerbung.getProjektName() + "</td>" + "</tr>";

			// Zelle mit Referenzen
			String refRow = "<tr><td colspan='3'><ul>";
			for (BewerbungReport ref : bewerbung.getReferenz()) {
				refRow = refRow + "<li>Projekt: " + ref.getProjektName() + "<ul>"
						+ "<li>Beworben als: " + ref.getBewerbungName() + "</li>" + "<li>Bewertung: "
						+ ref.getBewertung() + "</li>" + "<li>Status: " + ref.getStatus() + "</li>" + "</ul></li>";
			}

			refRow = refRow + "</ul></td></tr>";
			
			// HTMl generieren
			HTML entry = new HTML(
					"<table class='reportTable'><tr><th>Name des Bewerbers</th><th>Auf Stelle</th><th>Projekt</th></tr>"
							+ dataRow + refRow + "</table>");

			this.add(entry);
		}
	}

}
