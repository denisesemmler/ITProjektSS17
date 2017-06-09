package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReportServiceAsync {

	void init(AsyncCallback<Void> initReportGeneratorCallback);

}
