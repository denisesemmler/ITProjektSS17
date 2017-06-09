package de.hdm.itprojekt.server;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
	// Init ist eine Initialisierungsmethode, diese Methode MUSS f�r jede
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

	/*
	 * Methoden f�r Projekte
	 */

	// Methode um ein Projekt anzulegen
	@Override
	public Projekt createProjekt(String projektName, String projektBeschreibung, Date startDatum, Date endDatum,
			int TeilnehmerID, int MarktplatzID) throws IllegalArgumentException {

		Projekt p = new Projekt();
		p.setName(projektName);
		p.setBeschreibung(projektBeschreibung);
		p.setStartDatum(new java.sql.Date(startDatum.getTime()));
		p.setEndDatum(new java.sql.Date(endDatum.getTime()));
		p.setTeilnehmer_idTeilnehmer(TeilnehmerID);
		p.setProjektmarktplatz_idProjektmarkplatz(MarktplatzID);
		// setzen einer vorl�ufigen Projekt-Nr diese ist mit der DB konsistent
		// p.setId(1);

		// Objekt in DB speichern
		return this.pMapper.insert(p);

	}

	// Methode um ein Projekt zu bearbeiten
	@Override
	public void updateProjekt(Projekt p) throws IllegalArgumentException {
		pMapper.update(p);
	}

	/**
	 * Diese Methode l�scht ein Projekt mit all ihren Abh�ngigkeiten (gem��
	 * tablesV3). Diese sind: {@link Ausschreibung}, {@link Profil},
	 * {@link Eigenschaft}, {@link Bewerbung}, {@link Beteiligung}
	 * 
	 * @param p
	 *            ist das Objekt eines Projekts, dass gel�scht werden soll.
	 */
	@Override
	public void deleteProjekt(Projekt p) throws IllegalArgumentException {
		Vector<Ausschreibung> ausschreibung = this.getAusschreibung(p);

		if (ausschreibung != null) {
			for (Ausschreibung a : ausschreibung) {
				this.deleteAusschreibung(a);
			}
		}
	}


	// Methode zum l�schen des Profils, da die Ausschreibung sonst nicht
	// gel�scht werden kann
	private void deleteProfil(Ausschreibung a) {

		// Hier wird das Suchprofil zur Ausschreibung von der DB gelesen
		Profil suchProfil = pfMapper.findById(a.getProfil_idSuchprofil());

		// Hier werden die Eigenschaften zum Profil gelesen
		Vector<Eigenschaft> profilEigenschaften = eMapper.findByProfil(suchProfil);

		// Hier werden die Eigenschaften aus der DB entfernt
		for (Eigenschaft e : profilEigenschaften) {
			eMapper.delete(e);
		}

		// Hier wird eine Liste aller Bewerbungen zur Ausschreibungen von der DB
		// gelesen
		ArrayList<Bewerbung> bewerbungenZuProfil = bMapper.findByAusschreibungsId(a.getIdAusschreibung());

		// Hier werden die Bewerbungen aus der DB entfernt, aber erst wenn die
		// dazugeh�rige Beteiligung entfernt ist
		for (Bewerbung b : bewerbungenZuProfil) {
			this.deleteBewerbung(b);
		}

		// Hier werden die Teilnehmer zum Profil gelesen
		Teilnehmer teilnehmer = tMapper.findByProfil(suchProfil);
		teilnehmer.setProfil_idProfil(0);
	}

	// Methode um die Beteiligung zu l�schen, da die Bewerbung vorher nicht
	// gel�scht werden kann
	private void deleteBewerbung(Bewerbung b) {
		Beteiligung beteiligung = btMapper.findByBewerbung(b);
		btMapper.delete(beteiligung);
		bMapper.delete(b);
	}

	/**
	 * Diese Methode liest alle Ausschreibungen zu einem Projekt.
	 * 
	 * @param p
	 *            das Projekt zu dem alle Ausschreibungen gelesen werden sollen.
	 * @return Vector aller Ausschreibungen zum �bergebenen Projekt p.
	 */
	private Vector<Ausschreibung> getAusschreibung(Projekt p) {
		return aMapper.findByProjekt(p);
	}

	/*
	 * Methoden f�r Ausschreibungen
	 */

	@Override
	public Ausschreibung createAusschreibung(String beschreibung, Date bewerbungsfrist, String titel,
			int projekt_idProjekt, int profil_idSuchprofil) throws IllegalArgumentException {
		
		//Neues Objekt erstellen
		Ausschreibung ausschreibung = new Ausschreibung();
		
		//Werte zum Objekt hinzuf�gen
		ausschreibung.setBeschreibung(beschreibung);
		ausschreibung.setBewerbungsfrist(new Timestamp(bewerbungsfrist.getTime()));
		ausschreibung.setTitel(titel);
		ausschreibung.setProjekt_idProjekt(projekt_idProjekt);
		ausschreibung.setProfil_idSuchprofil(profil_idSuchprofil);
		
		//In die DB schreiben
		return aMapper.insert(ausschreibung);
	}
	
	
	@Override
	public void updateAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		//Update ohne Pr�fung in DB
		aMapper.update(a);
	}

	// Methode zum L�schen von Ausschreibungen, da das Projekt sonst nicht
	// gel�scht werden kann
	public void deleteAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		this.deleteProfil(a);
		
		// Hier wird die Ausschreibung aus der DB entfernt
		this.aMapper.delete(a);
	}
	

	/*
	 * Methoden f�r Teilnehmer
	 */

	public Teilnehmer createTeilnehemr(String name, String zusatz, String emailAdresse, int rolle)
			throws IllegalArgumentException {

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
