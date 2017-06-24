package de.hdm.itprojekt.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.client.gui.ClientSideSettings;
import de.hdm.itprojekt.server.db.AusschreibungMapper;
import de.hdm.itprojekt.server.db.BewerbungMapper;
import de.hdm.itprojekt.server.db.ProfilMapper;
import de.hdm.itprojekt.server.db.ProjektMapper;
import de.hdm.itprojekt.server.db.TeilnehmerMapper;
import de.hdm.itprojekt.shared.ReportService;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Profil;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Teilnehmer;
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
			
			Teilnehmer teilnehmer = TeilnehmerMapper.teilnehmerMapper().findById(ausschreibung.getTeilnehmer_idTeilnehmer());
			reportEntry.setAnsprechpartnerName(teilnehmer.getVorname() + " " + teilnehmer.getNachname());
			
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
			
			Teilnehmer teilnehmer = TeilnehmerMapper.teilnehmerMapper().findById(ausschreibung.getTeilnehmer_idTeilnehmer());
			reportEntry.setAnsprechpartnerName(teilnehmer.getVorname() + " " + teilnehmer.getNachname());
			
			report.add(reportEntry);
		}
		return report;
	}


	@Override
	public List<BewerbungReport> getAllBewerbungenUser(int teilnehmerId) {
		List<BewerbungReport> report = new ArrayList<BewerbungReport>();
		
		List<Ausschreibung> ausschreibungen = AusschreibungMapper.ausschreibungMapper().findAllAusschreibungByTeilnehmerId(teilnehmerId);
		for(Ausschreibung ausschreibung: ausschreibungen) {
			Vector<Bewerbung> bewerbungen = BewerbungMapper.bewerbungMapper().findByAusschreibungsId(ausschreibung.getId());
			
			for(Bewerbung bewerbung: bewerbungen) {
				BewerbungReport reportEntry = new BewerbungReport(bewerbung);
				
				Projekt projekt = ProjektMapper.projektMapper().findById(ausschreibung.getProjekt_idProjekt());
				reportEntry.setProjektName(projekt.getName());
				
				System.out.println("Jiayi:: ProfilId is "+ bewerbung.getIdProfil());
				
				Profil profil = ProfilMapper.profilMapper().findById(bewerbung.getIdProfil());
				
				System.out.println("Jiayi:: id is "+ profil.getTeilnehmer_idTeilnehmer());
				
				Teilnehmer teilnehmer = TeilnehmerMapper.teilnehmerMapper().findById(profil.getTeilnehmer_idTeilnehmer());
				reportEntry.setBewerberName(teilnehmer.getVorname() + " " + teilnehmer.getNachname());
				
				reportEntry.setBewerbungName(ausschreibung.getTitel());
				
				report.add(reportEntry);
			}
		}
		
		return report;
	}	
}
