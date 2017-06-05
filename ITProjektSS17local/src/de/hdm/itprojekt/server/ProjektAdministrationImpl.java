package de.hdm.itprojekt.server;

import java.sql.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.db.AusschreibungMapper;
import de.hdm.itprojekt.server.db.ProjektMapper;
import de.hdm.itprojekt.server.db.TeilnehmerMapper;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Profil;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Teilnehmer;
import de.hdm.itprojekt.shared.bs.ProjektAdministration;

@SuppressWarnings("serial")
public class ProjektAdministrationImpl extends RemoteServiceServlet implements ProjektAdministration {

	private ProjektMapper pMapper = null;
	private AusschreibungMapper aMapper = null;
	private TeilnehmerMapper tMapper = null;


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
		tMapper = TeilnehmerMapper.teilnehmerMapper();
	}

	@Override
	public Projekt createProjekt(String projektName, String projektBeschreibung, Date startDatum, Date endDatum)
			throws IllegalArgumentException {

		Projekt p = new Projekt();
		p.setProjektName(projektName);
		p.setProjektbeschreibung(projektBeschreibung);
		p.setStartDatum(startDatum);
		p.setEndDatum(endDatum);

		// setzen einer vorl�ufigen Projekt-Nr diese ist mit der DB konsistent
		p.setId(1);

		// Objekt in DB speichern
		return this.pMapper.insert(p);

	}

	@Override
	public void updateProjekt(Projekt p) throws IllegalArgumentException {
		pMapper.update(p);
	}

	/**
	 * Diese Methode l�scht ein Projekt mit all ihren Abh�ngigkeiten (gem�� tablesV3).
	 * Diese sind: {@link Ausschreibung}, {@link Profil}, {@link Eigenschaft}, {@link Bewerbung}, {@link Beteiligung}
	 * @param p ist das Objekt eines Projekts, dass gel�scht werden soll.
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
	//delete Ausschreibungsmethode
	//TODO warten auf Kl�rung bez�glich Profil
	private void delete(Ausschreibung a) throws IllegalArgumentException {
//		Vector<Profil> partnerprofil = this.getProfil(a);
//				
//		if(partnerprofil != null) {
//			for (Profil pp : partnerprofil) {
//				this.delete(pp);	
//			}
//		}
		
		//ausschreibung aus der DB entfernen
		this.aMapper.delete(a);
	}


	/**
	 * Diese Methode liest alle Ausschreibungen zu einem Projekt.
	 * @param p das Projekt zu dem alle Ausschreibungen gelesen werden sollen.
	 * @return Vector aller Ausschreibungen zum �bergebenen Projekt p.
	 */
	private Vector<Ausschreibung> getAusschreibung(Projekt p) {
		return aMapper.findByProjekt(p);
	}
	
	public Teilnehmer createTeilnehemr(String name, String zusatz, String emailAdresse, int rolle) throws IllegalArgumentException {
		
		Teilnehmer teilnehmer = new Teilnehmer();
		
		teilnehmer.setName(name);
		teilnehmer.setZusatz(zusatz);
		teilnehmer.setEmailAdresse(emailAdresse);
		teilnehmer.setRolle(rolle);
		
		Teilnehmer t = this.tMapper.insert(teilnehmer);
		
		
		
		return t;
		
		
	}

}
