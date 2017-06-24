package de.hdm.itprojekt.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.reports.AusschreibungReport;
import de.hdm.itprojekt.shared.bo.reports.BewerbungReport;

@RemoteServiceRelativePath("reportgenerator")

public interface ReportService  extends RemoteService{
	List<AusschreibungReport> getAllAusschreibungen();
	List<AusschreibungReport> getAllAusschreibungenUser(int teilnehmerId);
	List<BewerbungReport> getAllBewerbungenUser(int teilnehmerId);
	List<BewerbungReport> getAllBewerbungenForUser(int teilnehmerId);
}
