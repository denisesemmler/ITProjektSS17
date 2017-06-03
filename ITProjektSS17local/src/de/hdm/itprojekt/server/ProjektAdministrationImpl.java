package de.hdm.itprojekt.server;

import java.sql.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.db.AusschreibungMapper;
import de.hdm.itprojekt.server.db.ProjektMapper;
import de.hdm.itprojekt.shared.ProjektAdministration;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Profil;
import de.hdm.itprojekt.shared.bo.Projekt;

@SuppressWarnings("serial")
public class ProjektAdministrationImpl extends RemoteServiceServlet implements ProjektAdministration {

	private ProjektMapper pMapper = null;
	private AusschreibungMapper aMapper = null;

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

	}

	@Override
	public Projekt createProjekt(String projektName, String projektBeschreibung, Date startDatum, Date endDatum)
			throws IllegalArgumentException {

		Projekt p = new Projekt();
		p.setProjektName(projektName);
		p.setProjektbeschreibung(projektBeschreibung);
		p.setStartDatum(startDatum);
		p.setEndDatum(endDatum);

		// setzen einer vorläufigen Projekt-Nr diese ist mit der DB konsistent
		p.setId(1);

		// Objekt in DB speichern
		return this.pMapper.insert(p);

	}

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
	//delete Ausschreibungsmethode
	//TODO warten auf Klärung bezüglich Profil
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
	 * @return Vector aller Ausschreibungen zum übergebenen Projekt p.
	 */
	private Vector<Ausschreibung> getAusschreibung(Projekt p) {
		return aMapper.findByProjekt(p);
	}

}
