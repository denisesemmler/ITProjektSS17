package de.hdm.itprojekt.shared.bs;

import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Teilnehmer;

public interface ProjektAdministrationAsync {

	void init(AsyncCallback<Void> callback);

	void createProjekt(String projektName, String projektBezeichnung, Timestamp startDatum, Timestamp endDatum,
			AsyncCallback<Projekt> callback);

	void updateProjekt(Projekt p, AsyncCallback<Void> callback);

	void deleteProjekt(Projekt p, AsyncCallback<Void> callback);

	void createTeilnehmer(String name, String zusatz, String emailAdresse, int rolle,
			AsyncCallback<Teilnehmer> asyncCallback);
	
	

}
