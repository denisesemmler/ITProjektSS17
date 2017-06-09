package de.hdm.itprojekt.shared.bs;

import java.sql.Timestamp;
import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Teilnehmer;

@RemoteServiceRelativePath("projektadministration")
public interface ProjektAdministration extends RemoteService {

	/**
	 * Initialisierung des Objekts. Muss direkt nach der Instanzierung gerufen werden.
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException;
	
	/**
	 * Methode zum Anlegen eines Projekts
	 * 
	 */
	public Projekt createProjekt(String projektName, String projektBezeichnung, Date startDatum, Date endDatum) throws IllegalArgumentException;
	
	public void updateProjekt(Projekt p) throws IllegalArgumentException;
	
	public void deleteProjekt(Projekt p) throws IllegalArgumentException;
	
	//Prof. Thies Fragen wann IllegalArgumentException verwendet wird
	public Teilnehmer createTeilnehmer(String name, String zusatz, String emailAdresse, int rolle);

	
	
	

}
