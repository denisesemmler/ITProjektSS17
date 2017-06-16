package de.hdm.itprojekt.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.reports.AusschreibungReport;

public interface ReportServiceAsync {
	void getAllAusschreibungen(AsyncCallback<List<AusschreibungReport>> callback);
}
