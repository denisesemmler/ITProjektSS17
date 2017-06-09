package de.hdm.itprojekt.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.db.AusschreibungMapper;
import de.hdm.itprojekt.server.db.BeteiligungMapper;
import de.hdm.itprojekt.server.db.BewerbungMapper;
import de.hdm.itprojekt.server.db.EigenschaftMapper;
import de.hdm.itprojekt.server.db.ProfilMapper;
import de.hdm.itprojekt.server.db.ProjektMapper;
import de.hdm.itprojekt.server.db.TeilnehmerMapper;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Beteiligung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Profil;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Teilnehmer;
import de.hdm.itprojekt.shared.bs.ProjektAdministration;

@SuppressWarnings("serial")
public class ProjektAdministrationImpl extends RemoteServiceServlet implements ProjektAdministration {

	private ProjektMapper pMapper = null;
	private AusschreibungMapper aMapper = null;
	private ProfilMapper pfMapper = null;
	private TeilnehmerMapper tMapper = null;
	private EigenschaftMapper eMapper = null;
	private BewerbungMapper bMapper = null;
	private BeteiligungMapper btMapper = null;
	


	public ProjektAdministrationImpl() throws IllegalArgumentException {
		/*
		 * Der No-Argument-Constructor muss vorhanden sein.
		 */
	}

	@Override
	// Init ist eine Initialisierungsmethode, diese Methode MUSS für jede
	// Instanz von "ProjektAdministrationImpl" gerufen werden!
	public void init() throws IllegalArgumentException {
		pMapper = ProjektMapper.projektMapper();
		aMapper = AusschreibungMapper.ausschreibungMapper();
		pfMapper = ProfilMapper.profilMapper();
		tMapper = TeilnehmerMapper.teilnehmerMapper();
		eMapper = EigenschaftMapper.eigenschaftMapper();
		bMapper = BewerbungMapper.bewerbungMapper();
		btMapper = BeteiligungMapper.beteiligungMapper();
	}

	//Methode um ein Projekt anzulegen
	@Override
	public Projekt createProjekt(String projektName, String projektBeschreibung, Date startDatum, Date endDatum, int TeilnehmerID, int MarktplatzID)
			throws IllegalArgumentException {

		Projekt p = new Projekt();
		p.setName(projektName);
		p.setBeschreibung(projektBeschreibung);
		p.setStartDatum(startDatum);
		p.setEndDatum(endDatum);
		p.setTeilnehmer_idTeilnehmer(TeilnehmerID);
		p.setProjektmarktplatz_idProjektmarkplatz(MarktplatzID);
		//setzen einer vorläufigen Projekt-Nr diese ist mit der DB konsistent
		//p.setId(1);

		//Objekt in DB speichern
		return this.pMapper.insert(p);

	}

	//Methode um ein Projekt zu bearbeiten
	@Override
	public void updateProjekt(Projekt p) throws IllegalArgumentException {
		pMapper.update(p);
	}

	/**
	 * Diese Methode löscht ein Projekt mit all ihren Abhängigkeiten (gemäß tablesV3).
	 * Diese sind: {@link Ausschreibung}, {@link Profil}, {@link Eigenschaft}, {@link Bewerbung}, {@link Beteiligung}
	 * @param p ist das Objekt eines Projekts, dass gelöscht werden soll.
	 */
	@Override
	public void deleteProjekt(Projekt p) throws IllegalArgumentException {
		Vector<Ausschreibung> ausschreibung = this.getAusschreibung(p);

		if (ausschreibung != null) {
			for (Ausschreibung a : ausschreibung) {
				this.delete(a);
			}
		}
	}
	//Methode zum Löschen von Ausschreibungen, da das Projekt sonst nicht gelöscht werden kann
	private void delete(Ausschreibung a) throws IllegalArgumentException {		
		this.deleteProfil(a);
		
		//Hier wird die Ausschreibung aus der DB entfernt
		this.aMapper.delete(a);
	}	

	//Methode zum löschen des Profils, da die Ausschreibung sonst nicht gelöscht werden kann
	private void deleteProfil(Ausschreibung a) {
		
		//Hier wird das Suchprofil zur Ausschreibung von der DB gelesen
		Profil suchProfil = pfMapper.findById(a.getProfil_idSuchprofil());
		
		//Hier werden die Eigenschaften zum Profil gelesen
		Vector<Eigenschaft> profilEigenschaften= eMapper.findByProfil(suchProfil);
		
		//Hier werden die Eigenschaften aus der DB entfernt
		for (Eigenschaft e : profilEigenschaften){
			eMapper.delete(e);
		}
		
		//Hier wird eine Liste aller Bewerbungen zur Ausschreibungen von der DB gelesen
		ArrayList<Bewerbung> bewerbungenZuProfil = bMapper.findByAusschreibungsId(a.getIdAusschreibung());
		
		//Hier werden die Bewerbungen aus der DB entfernt, aber erst wenn die dazugehörige Beteiligung entfernt ist
		for(Bewerbung b : bewerbungenZuProfil){
			this.deleteBewerbung(b);
		}
		
		//Hier werden die Teilnehmer zum Profil gelesen
		Teilnehmer teilnehmer = tMapper.findByProfil(suchProfil);
		teilnehmer.setProfil_idProfil(0);
	}

	//Methode um die Beteiligung zu löschen, da die Bewerbung vorher nicht gelöscht werden kann
	private void deleteBewerbung(Bewerbung b) {
		Beteiligung beteiligung = btMapper.findByBewerbung(b);
		btMapper.delete(beteiligung);
		bMapper.delete(b);		
	}

	/**
	 * Diese Methode liest alle Ausschreibungen zu einem Projekt.
	 * @param p das Projekt zu dem alle Ausschreibungen gelesen werden sollen.
	 * @return Vector aller Ausschreibungen zum übergebenen Projekt p.
	 */
	private Vector<Ausschreibung> getAusschreibung(Projekt p) {
		return aMapper.findByProjekt(p);
	}
	

	
	
	
	
	
	public Teilnehmer createTeilnehemr(String name, String zusatz, String emailAdresse, int rolle) throws IllegalArgumentException {
		
		Teilnehmer teilnehmer = new Teilnehmer();
		
		teilnehmer.setName(name);
		teilnehmer.setZusatz(zusatz);
		teilnehmer.setEmail(emailAdresse);
		teilnehmer.setRolle(rolle);
		
		Teilnehmer t = this.tMapper.insert(teilnehmer);
		
		
		
		return t;
		
		
	}

	@Override
	public Teilnehmer createTeilnehmer(String name, String zusatz, String emailAdresse, int rolle) {
		return null;
	}

}
