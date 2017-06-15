package de.hdm.itprojekt.server;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.client.gui.ClientSideSettings;
import de.hdm.itprojekt.server.db.AusschreibungMapper;
import de.hdm.itprojekt.server.db.BeteiligungMapper;
import de.hdm.itprojekt.server.db.BewerbungMapper;
import de.hdm.itprojekt.server.db.EigenschaftMapper;
import de.hdm.itprojekt.server.db.ProfilMapper;
import de.hdm.itprojekt.server.db.ProjektMapper;
import de.hdm.itprojekt.server.db.ProjektmarktplatzMapper;
import de.hdm.itprojekt.server.db.TeilnehmerMapper;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Beteiligung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Profil;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Projektmarktplatz;
import de.hdm.itprojekt.shared.bo.Teilnehmer;
import de.hdm.itprojekt.shared.bs.ProjektAdministration;

/**
 * 
 * @author Patricia
 *
 */
@SuppressWarnings("serial")
public class ProjektAdministrationImpl extends RemoteServiceServlet implements ProjektAdministration {
	
	private ProjektmarktplatzMapper pmMapper = null;
	private ProjektMapper pMapper = null;
	private AusschreibungMapper aMapper = null;
	private ProfilMapper pfMapper = null;
	private TeilnehmerMapper tMapper = null;
	private EigenschaftMapper eMapper = null;
	private BewerbungMapper bMapper = null;
	private BeteiligungMapper btMapper = null;
	private Teilnehmer user;
	private static final long serialVersionUID = 1L;

	public ProjektAdministrationImpl() throws IllegalArgumentException {
		/*
		 * Der No-Argument-Constructor muss vorhanden sein.
		 */
	}

	@Override

	// Init ist eine Initialisierungsmethode, diese Methode MUSS für jede

	// Instanz von "ProjektAdministrationImpl" gerufen werden!
	public void init() throws IllegalArgumentException {
		pmMapper = ProjektmarktplatzMapper.projektmarktplatzMapper();
		pMapper = ProjektMapper.projektMapper();
		aMapper = AusschreibungMapper.ausschreibungMapper();
		pfMapper = ProfilMapper.profilMapper();
		tMapper = TeilnehmerMapper.teilnehmerMapper();
		eMapper = EigenschaftMapper.eigenschaftMapper();
		bMapper = BewerbungMapper.bewerbungMapper();
		btMapper = BeteiligungMapper.beteiligungMapper();
	}
	
	/*
<<<<<<< HEAD
	 * Methoden f�r Projektmarktpl�tze
=======
	 * Methoden für Projektmarktplätze
>>>>>>> branch 'Philipp' of https://github.com/denisesemmler/ITProjektSS17.git
	 */

	/**
	 * Diese Methode implementiert denn UC Projektmarktplatz anlegen
	 */
	@Override
	public Projektmarktplatz createProjektmarktplatz(String projektmarktplatzBez, int idTeilnehmer) throws IllegalArgumentException {

		Projektmarktplatz pm = new Projektmarktplatz();
		
		pm.setBezeichnung(projektmarktplatzBez);
		pm.setTeilnehmer_idTeilnehmer(idTeilnehmer);

		// Objekt in DB speichern
		return this.pmMapper.insert(pm);
	}

	/*
<<<<<<< HEAD
	 * Methoden f�r Projekte
=======
	 * Methoden für Projekte
>>>>>>> branch 'Philipp' of https://github.com/denisesemmler/ITProjektSS17.git
	 */

	/**
	 * Diese Methode implementiert denn UC Projekt anlegen
	 */
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

		// setzen einer vorläufigen Projekt-Nr diese ist mit der DB konsistent

		// p.setId(1);

		// Objekt in DB speichern
		return this.pMapper.insert(p);

	}



	// Methoden für Projekt

	/**
	 * Diese Methode implementiert denn UC Projekt bearbeiten
	 */
	@Override
	public void updateProjekt(Projekt p) throws IllegalArgumentException {
		pMapper.update(p);
	}

	/**
<<<<<<< HEAD
	 * Diese Methode l�scht ein Projekt mit all ihren Abh�ngigkeiten (gem��
=======
	 * Diese Methode löscht ein Projekt mit all ihren Abhängigkeiten (gemäß
>>>>>>> branch 'Philipp' of https://github.com/denisesemmler/ITProjektSS17.git
	 * tablesV3). Diese sind: {@link Ausschreibung}, {@link Profil},
	 * {@link Eigenschaft}, {@link Bewerbung}, {@link Beteiligung}
	 * 
	 * @param p
<<<<<<< HEAD
	 *            ist das Objekt eines Projekts, dass gel�scht werden soll.
=======
	 *            ist das Objekt eines Projekts, dass gelöscht werden soll.
>>>>>>> branch 'Philipp' of https://github.com/denisesemmler/ITProjektSS17.git
	 * 
<<<<<<< HEAD
	 *            Implementiert denn UC Projekt l�schen
=======
	 *            Implementiert denn UC Projekt löschen
>>>>>>> branch 'Philipp' of https://github.com/denisesemmler/ITProjektSS17.git
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

	/*
<<<<<<< HEAD
	 * Methode zum l�schen des Profils, da die Ausschreibung sonst nicht
	 * gel�scht werden kann
=======
	 * Methode zum löschen des Profils, da die Ausschreibung sonst nicht
	 * gelöscht werden kann
>>>>>>> branch 'Philipp' of https://github.com/denisesemmler/ITProjektSS17.git
	 */
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

		// dazugehörige Beteiligung entfernt ist

		for (Bewerbung b : bewerbungenZuProfil) {
			this.deleteBewerbung(b);
		}

		// Hier werden die Teilnehmer zum Profil gelesen
		Teilnehmer teilnehmer = tMapper.findByProfil(suchProfil);
		teilnehmer.setProfil_idProfil(0);
	}

	/**
	 * Diese Methode liest alle Ausschreibungen zu einem Projekt.
	 * 
	 * @param p
	 *            das Projekt zu dem alle Ausschreibungen gelesen werden sollen.
<<<<<<< HEAD
	 * @return Vector aller Ausschreibungen zum �bergebenen Projekt p.
=======
	 * @return Vector aller Ausschreibungen zum übergebenen Projekt p.
>>>>>>> branch 'Philipp' of https://github.com/denisesemmler/ITProjektSS17.git
	 */
	private Vector<Ausschreibung> getAusschreibung(Projekt p) {
		return aMapper.findByProjekt(p);
	}

	// Methoden für Ausschreibung

	/**
	 * Diese Methode implementiert denn UC Ausschreibung erstellen
	 */
	@Override
	public Ausschreibung createAusschreibung(String beschreibung, Date bewerbungsfrist, String titel,
			int projekt_idProjekt, int profil_idSuchprofil) throws IllegalArgumentException {

		// Neues Objekt erstellen
		Ausschreibung ausschreibung = new Ausschreibung();


		// Werte zum Objekt hinzufügen

		ausschreibung.setBeschreibung(beschreibung);
		// kurze schreibweise einer Typkonvertierung
		ausschreibung.setBewerbungsfrist(new Timestamp(bewerbungsfrist.getTime())); // Timestamp?
		ausschreibung.setTitel(titel);
		ausschreibung.setProjekt_idProjekt(projekt_idProjekt);
		ausschreibung.setProfil_idSuchprofil(profil_idSuchprofil);

		// In die DB schreiben
		return aMapper.insert(ausschreibung);
	}

	/**
	 * Diese Methode implementiert denn UC Ausschreibung bearbeiten
	 */
	@Override
	public void updateAusschreibung(Ausschreibung a) throws IllegalArgumentException {

		// Update ohne Prüfung in DB

		aMapper.update(a);
	}

	/**

	 * Diese Methode implementiert denn UC Ausschreibung l�schen

	 * Diese Methode implementiert denn UC Ausschreibung löschen

	 */
	/*

	 * Methode zum Löschen von Ausschreibungen, da das Projekt sonst nicht
	 * gelöscht werden kann

	 */
	public void deleteAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		this.deleteProfil(a);

		// Hier wird die Ausschreibung aus der DB entfernt
		this.aMapper.delete(a);
	}


	// Methoden für Bewerbung

	/**
	 * Diese Methode implementiert denn UC Auf Ausschreibung bewerben
	 */
	@Override
	public Bewerbung createBewerbung(String bewerbungsText, Date erstellDatum, float bewertung, String status,
			int idProfil, int ausschreibungID) throws IllegalArgumentException {

		// Neues Objekt erstellen
		Bewerbung bewerbung = new Bewerbung();

		// ausführliche Typkonvertierung

		Timestamp dbErstellDatum = new Timestamp(erstellDatum.getTime());


		// Werte zum Objekt hinzuf�gen

		// Werte zum Objekt hinzufügen

		bewerbung.setBewerbungsText(bewerbungsText);
		bewerbung.setErstellDatum(dbErstellDatum);// Timestamp?
		bewerbung.setBewertung(bewertung);
		bewerbung.setStatus(status);
		bewerbung.setIdProfil(idProfil);
		bewerbung.setAusschreibungID(ausschreibungID);

		// in die DB schreiben
		return bMapper.insert(bewerbung);
	}

	/**
	 * Diese Methode implementiert denn UC Bewerbung bearbeiten
	 */
	@Override
	public void updateBewerbung(Bewerbung b) throws IllegalArgumentException {

		// Update ohne Prüfung in DB

		bMapper.update(b);
	}

	/**
<<<<<<< HEAD
	 * Diese Methode implementiert denn UC Bewerbung zur�ckziehen
=======
	 * Diese Methode implementiert denn UC Bewerbung zurückziehen

	 */


	// Methode um die Beteiligung zu löschen, da die Bewerbung vorher nicht
	// gelöscht werden kann

	public void deleteBewerbung(Bewerbung b) {
		Beteiligung beteiligung = btMapper.findByBewerbung(b);
		btMapper.delete(beteiligung);
		bMapper.delete(b);
	}

	
	
	
	
	//TODO

	// Methoden für Teilnehmer

	
	public void setUser(Teilnehmer t) {
		user = t;
	}

	@Override
	public Teilnehmer createTeilnehmer(String vorname, String nachname, String zusatz, String strasse, 
									   int plz, String ort, String emailAdresse, int rolle, int ProfilID, int projektleiter)
			throws IllegalArgumentException {

		Teilnehmer teilnehmer = new Teilnehmer();

		teilnehmer.setVorname(vorname);
		teilnehmer.setNachname(nachname);
		teilnehmer.setZusatz(zusatz);
		teilnehmer.setStrasse(strasse);
		teilnehmer.setPlz(plz);
		teilnehmer.setOrt(ort);
		teilnehmer.setEmail(emailAdresse);
		teilnehmer.setRolle(rolle);
		teilnehmer.setProfil_idProfil(ProfilID);
		teilnehmer.setProjektLeiter(projektleiter);

		//Teilnehmer t = this.tMapper.insert(teilnehmer);

		return this.tMapper.insert(teilnehmer);

	}

	
	public Teilnehmer login(String requestUri) {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		Teilnehmer logInf = new Teilnehmer();

		if (user != null) {
			Teilnehmer existingPerson = tMapper.findByEmail(user.getEmail());
			
			
			if(existingPerson != null){
				ClientSideSettings.getLogger().info("Userobjekt E-Mail = " + user.getEmail()
						+ "  Bestehender User: E-Mail  =" + existingPerson.getEmail());

				existingPerson.setLoggedIn(true);
				existingPerson.setLogoutUrl(userService.createLogoutURL(requestUri));

				return existingPerson;

			}

			
			logInf.setLoggedIn(true);
			//logInf.setName(user.getNickname());
			logInf.setLogoutUrl(userService.createLogoutURL(requestUri));
			logInf.setEmail(user.getEmail());
		} else {
			logInf.setLoggedIn(false);
			logInf.setLoginUrl(userService.createLoginURL(requestUri));
			logInf.setLogoutUrl(userService.createLogoutURL(requestUri));
		}
		return logInf;
	}

	public Projektmarktplatz createProjektmarktplatz(String projektmarktplatzBez) throws IllegalArgumentException {

		Projektmarktplatz pm = new Projektmarktplatz();
		pm.setBezeichnung(projektmarktplatzBez);

		// Objekt in DB speichern
		return this.pmMapper.insert(pm);

	}


	/*@Override
	public void createTeilnehmer(String vorname, String nachname, String zusatz, String strasse, int plz, String ort,
			String emailAdresse, int rolle) {
		// TODO Auto-generated method stub
		
	}*/


}
