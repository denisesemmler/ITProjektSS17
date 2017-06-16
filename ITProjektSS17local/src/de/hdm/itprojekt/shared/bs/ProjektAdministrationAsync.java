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
	 * F�r Projektmarktpl�tze
	 */
	void createProjektmarktplatz(String projektmarktplatzBez, int idTeilnehmer, AsyncCallback<Projektmarktplatz> callback);
	
	void updateProjektmarktplatz(Projektmarktplatz pm, AsyncCallback<Void> callback);
	
	void deleteProjektmarktplatz(Projektmarktplatz pm, AsyncCallback<Void> callback);
	
	/*
	 * F�r Projekte
	 */
	void createProjekt(String projektName, String projektBeschreibung, Date startDatum, Date endDatum, int TeilnehmerID,
			int MarktplatzID, AsyncCallback<Projekt> callback);

	void updateProjekt(Projekt p, AsyncCallback<Void> callback);

	void deleteProjekt(Projekt p, AsyncCallback<Void> callback);

	/*
	 * F�r Ausschreibungen
	 */
	void createAusschreibung(String beschreibung, Date bewerbungsfrist, String titel, int projekt_idProjekt,
			int profil_idSuchprofil, AsyncCallback<Ausschreibung> callback);
	
	void updateAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);
	
	void deleteAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);
	
	/*
	 * F�r Bewerbungen
	 */
	void createBewerbung(String bewerbungstext, Date erstelldatum, float bewertung, String status, int profil_idProfil,
			int ausschreibung_idAusschreibung, AsyncCallback<Bewerbung> callback);
	
	void updateBewerbung(Bewerbung b, AsyncCallback<Void> callback);
	
	void deleteBewerbung(Bewerbung b, AsyncCallback<Void> callback);
	
	/*
	 * F�r Teilnehmer
	 */
	
	void setUser(Teilnehmer t, AsyncCallback callback);

	void login(String requestUri, AsyncCallback<Teilnehmer> callback);

	void createTeilnehmer(String vorname, String nachname, String zusatz, String strasse, int plz, String ort,
			String emailAdresse, int rolle, int ProfilID, int projektleiter, AsyncCallback<Teilnehmer> callback);











}
