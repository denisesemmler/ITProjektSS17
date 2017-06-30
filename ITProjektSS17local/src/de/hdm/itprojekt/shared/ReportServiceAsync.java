package de.hdm.itprojekt.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.reports.AusschreibungReport;
import de.hdm.itprojekt.shared.bo.reports.BewerbungReport;
import de.hdm.itprojekt.shared.bo.reports.FanInFanOut;

public interface ReportServiceAsync {
	void getAllAusschreibungen(AsyncCallback<List<AusschreibungReport>> callback);
	void getAllAusschreibungenUser(int teilnehmerId, AsyncCallback<List<AusschreibungReport>> callback);
	void getAllBewerbungenUser(int teilnehmerId, AsyncCallback<List<BewerbungReport>> callback);
	void getAllBewerbungenForUser(int teilnehmerId, AsyncCallback<List<BewerbungReport>> callback);
	void getProjektverpflechtungen(int teilnehmerId, AsyncCallback<List<BewerbungReport>> callback);
	void getFanInFanOut(AsyncCallback<List<FanInFanOut>> callback);
	void getVorschlaege(int teilnehmerId, AsyncCallback<List<AusschreibungReport>> callback);
}
