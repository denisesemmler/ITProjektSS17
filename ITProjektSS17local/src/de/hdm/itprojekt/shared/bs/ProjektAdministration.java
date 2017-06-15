package de.hdm.itprojekt.shared.bs;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Projektmarktplatz;
import de.hdm.itprojekt.shared.bo.Teilnehmer;




/*diese Annotation definiert denn relativen Pfad zum Service, sprich unter der URL /itprojektss17local/projektadministration
 * der entsprende URL muss in den Pfad der Web xml hinterlegt werden.
 */

/**
 * 
 * @author Patricia
 *
 */
@RemoteServiceRelativePath("projektadministration")
public interface ProjektAdministration extends RemoteService {

	/**
	 * Initialisierung des Objekts. Muss direkt nach der Instanzierung gerufen werden.
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException;
	
	/*
	 * Methoden zum Anlegen eines Projektmarktplatz
	 */
	public Projektmarktplatz createProjektmarktplatz(String projektmarktplatzBez) throws IllegalArgumentException;
	
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
	
	/*
	 * Methoden zum anlegen von Bewerbungen
	 */
	public Bewerbung createBewerbung(String bewerbungstext, Date erstelldatum, float bewertung, String status, int profil_idProfil, int ausschreibung_idAusschreibung) throws IllegalArgumentException;
	
	public void updateBewerbung(Bewerbung b) throws IllegalArgumentException;
	
	public void deleteBewerbung(Bewerbung b) throws IllegalArgumentException;
	
	
	/*
	 * Methoden zum anlegen von Teilnehmern
	 */
	public Teilnehmer createTeilnehmer(String name, String zusatz, String emailAdresse, int rolle) throws IllegalArgumentException;

	void setUser(Teilnehmer t);

	public Teilnehmer login(String requestUri);

	
	
	

}
