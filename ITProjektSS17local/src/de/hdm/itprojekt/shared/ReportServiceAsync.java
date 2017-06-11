package de.hdm.itprojekt.shared;

import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Ausschreibung;

public interface ReportServiceAsync {

	//void init(AsyncCallback<Void> initReportGeneratorCallback);
	
	void getAllAusschreibungen(AsyncCallback<List<Ausschreibung>> callback);

}
