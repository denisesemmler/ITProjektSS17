package de.hdm.itprojekt.server;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.db.AusschreibungMapper;
import de.hdm.itprojekt.server.db.ProjektMapper;
import de.hdm.itprojekt.shared.ReportService;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.reports.AusschreibungReport;

public class ReportServiceImpl extends RemoteServiceServlet implements ReportService  {	
	public List<AusschreibungReport> getAllAusschreibungen() {
		List<AusschreibungReport> report = new ArrayList<AusschreibungReport>();
		
		List<Ausschreibung> ausschreibungen = AusschreibungMapper.ausschreibungMapper().findAllAusschreibungen();
		for(Ausschreibung ausschreibung: ausschreibungen) {
			AusschreibungReport reportEntry = new AusschreibungReport(ausschreibung);
			
		
			Projekt projekt = ProjektMapper.projektMapper().findById(1);
	
			reportEntry.setProjektName(projekt.getName());
			
			report.add(reportEntry);
		}
		return report;
	}	
}
