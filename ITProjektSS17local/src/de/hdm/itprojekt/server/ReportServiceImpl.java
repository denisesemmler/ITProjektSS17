package de.hdm.itprojekt.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.db.AusschreibungMapper;
import de.hdm.itprojekt.server.db.BeteiligungMapper;
import de.hdm.itprojekt.server.db.BewerbungMapper;
import de.hdm.itprojekt.server.db.EigenschaftMapper;
import de.hdm.itprojekt.server.db.ProfilMapper;
import de.hdm.itprojekt.server.db.ProjektMapper;
import de.hdm.itprojekt.server.db.TeilnehmerMapper;
import de.hdm.itprojekt.shared.ReportService;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.reports.AusschreibungReport;
import de.hdm.itprojekt.shared.bs.ProjektAdministration;
import de.hdm.itprojekt.shared.report.AlleAusschreibungen;
import de.hdm.itprojekt.shared.report.Column;
import de.hdm.itprojekt.shared.report.Row;

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
