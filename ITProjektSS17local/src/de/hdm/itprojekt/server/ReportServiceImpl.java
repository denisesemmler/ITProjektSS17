package de.hdm.itprojekt.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.client.gui.ClientSideSettings;
import de.hdm.itprojekt.server.db.AusschreibungMapper;
import de.hdm.itprojekt.server.db.BewerbungMapper;
import de.hdm.itprojekt.server.db.EigenschaftMapper;
import de.hdm.itprojekt.server.db.ProfilMapper;
import de.hdm.itprojekt.server.db.ProjektMapper;
import de.hdm.itprojekt.server.db.TeilnehmerMapper;
import de.hdm.itprojekt.shared.ReportService;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Profil;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Teilnehmer;
import de.hdm.itprojekt.shared.bo.reports.AusschreibungReport;
import de.hdm.itprojekt.shared.bo.reports.BewerbungReport;
import de.hdm.itprojekt.shared.bo.reports.FanInFanOut;

/**
 * Backend Service f�r die unterschiedlichen Berichte
 * @author Jiayi
 *
 */
public class ReportServiceImpl extends RemoteServiceServlet implements ReportService  {	
	
	private static final long serialVersionUID = -7548005663369824973L;

	@Override
	/**
	 * 
	 */
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
				
				Profil profil = ProfilMapper.profilMapper().findById(bewerbung.getIdProfil());
				
				
				Teilnehmer teilnehmer = TeilnehmerMapper.teilnehmerMapper().findById(profil.getTeilnehmer_idTeilnehmer());
				reportEntry.setBewerberName(teilnehmer.getVorname() + " " + teilnehmer.getNachname());
				
				reportEntry.setBewerbungName(ausschreibung.getTitel());
				
				List<Eigenschaft> eigenschaften = EigenschaftMapper.eigenschaftMapper().findByProfil(bewerbung.getIdProfil());
				reportEntry.setEigenschaften(eigenschaften);
				
				report.add(reportEntry);
			}
		}
		
		return report;
	}

	@Override
	public List<BewerbungReport> getAllBewerbungenForUser(int teilnehmerId) {
		List<BewerbungReport> report = new ArrayList<BewerbungReport>();
		
		Profil profil = ProfilMapper.profilMapper().findByTeilnehmerId(teilnehmerId);
		
		Vector<Bewerbung> bewerbungen = BewerbungMapper.bewerbungMapper().findBewerbungByTeilnehmerId(profil.getId());
		
		for(Bewerbung bewerbung: bewerbungen) {
			if(bewerbung.getStatus().toLowerCase().equals("laufend")) {
				BewerbungReport reportEntry = new BewerbungReport(bewerbung);
				
				Ausschreibung ausschreibung = AusschreibungMapper.ausschreibungMapper().findById(bewerbung.getAusschreibungID());
				reportEntry.setBewerbungName(ausschreibung.getTitel());
				
				Projekt projekt = ProjektMapper.projektMapper().findById(ausschreibung.getProjekt_idProjekt());
				reportEntry.setProjektName(projekt.getName());
				
				Teilnehmer teilnehmer = TeilnehmerMapper.teilnehmerMapper().findById(ausschreibung.getTeilnehmer_idTeilnehmer());
				reportEntry.setAnsprechpartnerName(teilnehmer.getVorname() + " " + teilnehmer.getNachname());
				
				reportEntry.setFrist(ausschreibung.getBewerbungsfrist());
				
				report.add(reportEntry);
			}
		}
		
		return report;
	}	
	
	@Override
	public List<BewerbungReport> getProjektverpflechtungen(int teilnehmerId) {
		List<BewerbungReport> report = new ArrayList<BewerbungReport>();
		
		List<Ausschreibung> ausschreibungen = AusschreibungMapper.ausschreibungMapper().findAllAusschreibungByTeilnehmerId(teilnehmerId);
		for(Ausschreibung ausschreibung: ausschreibungen) {
			Vector<Bewerbung> bewerbungen = BewerbungMapper.bewerbungMapper().findByAusschreibungsId(ausschreibung.getId());
			
			for(Bewerbung bewerbung: bewerbungen) {
				BewerbungReport reportEntry = new BewerbungReport(bewerbung);
				
				Projekt projekt = ProjektMapper.projektMapper().findById(ausschreibung.getProjekt_idProjekt());
				reportEntry.setProjektName(projekt.getName());
				
				Profil profil = ProfilMapper.profilMapper().findById(bewerbung.getIdProfil());
				
				
				Teilnehmer teilnehmer = TeilnehmerMapper.teilnehmerMapper().findById(profil.getTeilnehmer_idTeilnehmer());
				reportEntry.setBewerberName(teilnehmer.getVorname() + " " + teilnehmer.getNachname());
				
				reportEntry.setBewerbungName(ausschreibung.getTitel());
				
				
				Vector<Bewerbung> referencen = BewerbungMapper.bewerbungMapper().findBewerbungByTeilnehmerId(profil.getId());
				
				List<BewerbungReport> refs = new ArrayList<BewerbungReport>();
				for(Bewerbung ref: referencen) {
					BewerbungReport refEntry = new BewerbungReport(ref);
					
					Ausschreibung refAusschreibung = AusschreibungMapper.ausschreibungMapper().findById(ref.getAusschreibungID());
					
					Projekt refProjekt = ProjektMapper.projektMapper().findById(refAusschreibung.getProjekt_idProjekt());
					refEntry.setProjektName(refProjekt.getName());
					
					refEntry.setBewerbungName(refAusschreibung.getTitel());
								
					
					refs.add(refEntry);
				}
				reportEntry.setReferenz(refs);
				report.add(reportEntry);
			}
		}
		
		return report;
	}

	@Override
	public List<FanInFanOut> getFanInFanOut() {
		List<FanInFanOut> report = new ArrayList<>();
		
		List<Teilnehmer> allTeilnehmer = TeilnehmerMapper.teilnehmerMapper().findAllTeilnehmer();
		
		for(Teilnehmer teilnehmer: allTeilnehmer) {
			FanInFanOut reportEntry = new FanInFanOut();
			
			reportEntry.setTeilnehmerName(teilnehmer.getVorname() + " " + teilnehmer.getNachname());
			
			Profil profil = ProfilMapper.profilMapper().findByTeilnehmerId(teilnehmer.getId());
			
			int fanOut = BewerbungMapper.bewerbungMapper().findBewerbungByTeilnehmerId(profil.getId()).size();
			reportEntry.setFanOut(fanOut);
			
			int fanIn = AusschreibungMapper.ausschreibungMapper().findAllAusschreibungByTeilnehmerId(teilnehmer.getId()).size();
			reportEntry.setFanIn(fanIn);
			
			report.add(reportEntry);
		}
		return report;
	}
}
