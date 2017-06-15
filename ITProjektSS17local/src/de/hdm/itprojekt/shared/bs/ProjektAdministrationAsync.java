package de.hdm.itprojekt.shared.bs;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Projektmarktplatz;
import de.hdm.itprojekt.shared.bo.Teilnehmer;



/**
 * 
 * @author Patricia
 *
 */
public interface ProjektAdministrationAsync {

	void init(AsyncCallback<Void> callback);
	
	/*
	 * Für Projektmarktplätze
	 */
	void createProjektmarktplatz(String projektmarktplatzBez, AsyncCallback<Projektmarktplatz> callback);
	
	/*
	 * Für Projekte
	 */
	void createProjekt(String projektName, String projektBeschreibung, Date startDatum, Date endDatum, int TeilnehmerID,
			int MarktplatzID, AsyncCallback<Projekt> callback);

	void updateProjekt(Projekt p, AsyncCallback<Void> callback);

	void deleteProjekt(Projekt p, AsyncCallback<Void> callback);

	/*
	 * Für Ausschreibungen
	 */
	void createAusschreibung(String beschreibung, Date bewerbungsfrist, String titel, int projekt_idProjekt,
			int profil_idSuchprofil, AsyncCallback<Ausschreibung> callback);
	
	void updateAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);
	
	void deleteAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);
	
	/*
	 * Für Bewerbungen
	 */
	void createBewerbung(String bewerbungstext, Date erstelldatum, float bewertung, String status, int profil_idProfil,
			int ausschreibung_idAusschreibung, AsyncCallback<Bewerbung> callback);
	
	void updateBewerbung(Bewerbung b, AsyncCallback<Void> callback);
	
	void deleteBewerbung(Bewerbung b, AsyncCallback<Void> callback);
	
	
	
	
	/*
	 * Für Teilnehmer
	 */
	
	void setUser(Teilnehmer t, AsyncCallback callback);
	
	void createTeilnehmer(String name, String zusatz, String emailAdresse, int rolle,
			AsyncCallback<Teilnehmer> asyncCallback);

	void login(String requestUri, AsyncCallback<Teilnehmer>callback);









}
