package de.hdm.itprojekt.shared.bs;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Projekt;

public interface ProjektAdministrationAsync {

	void init(AsyncCallback<Void> callback);

	void createProjekt(String projektName, String projektBezeichnung, Date startDatum, Date endDatum,
			AsyncCallback<Projekt> callback);

}
