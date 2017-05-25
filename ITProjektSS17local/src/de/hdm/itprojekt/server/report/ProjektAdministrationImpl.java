package de.hdm.itprojekt.server.report;

import java.sql.Date;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hdm.itprojekt.server.db.TeilnehmerMapper;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bs.ProjektAdministration;

@SuppressWarnings("serial")
public class ProjektAdministrationImpl extends RemoteServiceServlet implements ProjektAdministration {

	//Bsp für die Mapper -->In Bearbeitung
	//TODO durch Projektmapper ersetzen
	private TeilnehmerMapper tMapper =null;
	
	
	
	public ProjektAdministrationImpl() throws IllegalArgumentException {
		/*
		 * Der No-Argument-Constructor muss einfach vorhanden sein.
		 */
	}

	@Override
	public void init() throws IllegalArgumentException {
		//bsp für die Mapper --> In Bearbeitung
		//TODO durch Projektmapper ersetzen
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
		
		//setzen einer Vorläufigen Projekt-Nr diese ist mit der DB konsistent
		p.setId(1);
		
		//Objekt in DB speichern
		//TODO gegen ProjektMapper ersetzen
		return null; //this.tMapper.insert(p);
	
	}

}
