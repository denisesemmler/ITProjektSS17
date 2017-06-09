package de.hdm.itprojekt.shared.bs;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Teilnehmer;

/*diese Annotation definiert denn relativen Pfad zum Service, sprich unter der URL /itprojektss17local/projektadministration
 * der entsprende URL muss in den Pfad der Web xml hinterlegt werden.
 */
@RemoteServiceRelativePath("projektadministration")
public interface ProjektAdministration extends RemoteService {

	/**
	 * Initialisierung des Objekts. Muss direkt nach der Instanzierung gerufen werden.
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException;
	
	/*
	 * Methode zum anlegen eines Projekts
	 */
	public Projekt createProjekt(String projektName, String projektBeschreibung, Date startDatum, Date endDatum, int TeilnehmerID, int MarktplatzID) throws IllegalArgumentException;
	
	public void updateProjekt(Projekt p) throws IllegalArgumentException;
	
	public void deleteProjekt(Projekt p) throws IllegalArgumentException;
	
	/*
	 * Methoden zum anlegen von Ausschreibungen
	 */
	public Ausschreibung createAusschreibung(String beschreibung, Date bewerbungsfrist, String titel, int projekt_idProjekt, int profil_idSuchprofil) throws IllegalArgumentException;
	
	public void updateAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	
	public void deleteAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	
	
	
	//Prof. Thies Fragen wann IllegalArgumentException verwendet wird
	public Teilnehmer createTeilnehmer(String name, String zusatz, String emailAdresse, int rolle);

	
	
	

}
