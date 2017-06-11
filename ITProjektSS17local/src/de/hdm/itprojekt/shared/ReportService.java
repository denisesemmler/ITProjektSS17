package de.hdm.itprojekt.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.Ausschreibung;

@RemoteServiceRelativePath("reportgenerator")

public interface ReportService  extends RemoteService{
	List<Ausschreibung> getAllAusschreibungen();
}
