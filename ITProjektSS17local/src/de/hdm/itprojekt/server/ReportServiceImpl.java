package de.hdm.itprojekt.server;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.client.gui.ClientSideSettings;
import de.hdm.itprojekt.server.db.AusschreibungMapper;
import de.hdm.itprojekt.server.db.BewerbungMapper;
import de.hdm.itprojekt.server.db.ProjektMapper;
import de.hdm.itprojekt.shared.ReportService;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.reports.AusschreibungReport;
import de.hdm.itprojekt.shared.bo.reports.BewerbungReport;

public class ReportServiceImpl extends RemoteServiceServlet implements ReportService  {	
	
	@Override
	public List<AusschreibungReport> getAllAusschreibungen() {
		List<AusschreibungReport> report = new ArrayList<AusschreibungReport>();
		
		List<Ausschreibung> ausschreibungen = AusschreibungMapper.ausschreibungMapper().findAllAusschreibungen();
		for(Ausschreibung ausschreibung: ausschreibungen) {
			AusschreibungReport reportEntry = new AusschreibungReport(ausschreibung);
			
			Projekt projekt = ProjektMapper.projektMapper().findById(reportEntry.getProjekt_idProjekt());
	
			reportEntry.setProjektName(projekt.getName());
			
			report.add(reportEntry);
		}
		return report;
	}	
	
	@Override
	public List<AusschreibungReport> getAllAusschreibungenUser(int teilnehmerId) {
		List<AusschreibungReport> report = new ArrayList<AusschreibungReport>();
		
		List<Ausschreibung> ausschreibungen = AusschreibungMapper.ausschreibungMapper().findAllAusschreibungByTeilnehmerId(teilnehmerId);
		for(Ausschreibung ausschreibung: ausschreibungen) {
			AusschreibungReport reportEntry = new AusschreibungReport(ausschreibung);
		
			Projekt projekt = ProjektMapper.projektMapper().findById(reportEntry.getProjekt_idProjekt());
	
			reportEntry.setProjektName(projekt.getName());
			
			report.add(reportEntry);
		}
		return report;
	}


	@Override
	public List<BewerbungReport> getAllBewerbungenUser(int teilnehmerId) {
		List<BewerbungReport> report = new ArrayList<BewerbungReport>();
		
		List<Bewerbung> bewerbungen = BewerbungMapper.bewerbungMapper().findBewerbungByTeilnehmerId(teilnehmerId);
		
		for(Bewerbung bewerbung: bewerbungen) {
			BewerbungReport reportEntry = new BewerbungReport(bewerbung);
			
			report.add(reportEntry);
		}
		return report;
	}	
}
