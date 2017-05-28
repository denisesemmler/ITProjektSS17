package de.hdm.itprojekt.server.report;

import java.sql.Date;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.db.ProjektMapper;
import de.hdm.itprojekt.server.db.TeilnehmerMapper;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bs.ProjektAdministration;

@SuppressWarnings("serial")
public class ProjektAdministrationImpl extends RemoteServiceServlet implements ProjektAdministration {

	private ProjektMapper pMapper = null;
	
	
	
	public ProjektAdministrationImpl() throws IllegalArgumentException {
		/*
		 * Der No-Argument-Constructor muss vorhanden sein.
		 */
	}

	@Override
	//Init ist eine Initialisierungsmethode, diese Methode MUSS für jede Instanz von "ProjektAdministrationImpl" gerufen werden!
	public void init() throws IllegalArgumentException {
		pMapper = ProjektMapper.projektMapper();
		
	}

	@Override
	public Projekt createProjekt(String projektName, String projektBeschreibung, Date startDatum, Date endDatum)
			throws IllegalArgumentException {

		Projekt p = new Projekt();
		p.setProjektName(projektName);
		p.setProjektbeschreibung(projektBeschreibung);
		p.setStartDatum(startDatum);
		p.setEndDatum(endDatum);
		
		//setzen einer vorläufigen Projekt-Nr diese ist mit der DB konsistent
		p.setId(1);
		
		//Objekt in DB speichern
		return this.pMapper.insert(p);
	
	}

}
