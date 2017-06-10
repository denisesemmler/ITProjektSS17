package de.hdm.itprojekt.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.Ausschreibung;

@RemoteServiceRelativePath("reportgenerator")

public interface ReportService  extends RemoteService{
	
	/**
	 * Initialisierung des objektes mit anschlieﬂendem No Argument Konstruktor
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException;
	
	List<Ausschreibung> getAllAusschreibungen() throws IllegalArgumentException;

}
