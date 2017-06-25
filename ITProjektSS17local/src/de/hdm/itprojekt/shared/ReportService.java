package de.hdm.itprojekt.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.reports.AusschreibungReport;
import de.hdm.itprojekt.shared.bo.reports.BewerbungReport;
import de.hdm.itprojekt.shared.bo.reports.FanInFanOut;

@RemoteServiceRelativePath("reportgenerator")

public interface ReportService  extends RemoteService{
	/**
	 * Erzeugt Liste mit allen Ausschreibungen.
	 * @return Liste mit allen Ausschreibungen.
	 */
	List<AusschreibungReport> getAllAusschreibungen();
	
	/**
	 * Erzeugt Liste mit allen Ausschreibungen, die ein Teilnehmer erstellt hat.
	 * @param teilnehmerId Id eines Teilnehmers.
	 * @return Liste mit allen Ausschreibungen, die ein Teilnehmer erstellt hat.
	 */
	List<AusschreibungReport> getAllAusschreibungenUser(int teilnehmerId);
	
	/**
	 * Erzeugt Liste aller Bewerbungen auf Ausschreibungen, die ein Teilnehmer erstellt hat.
	 * @param teilnehmerId Id eines Teilnehmers.
	 * @returnListe aller Bewerbungen auf Ausschreibungen, die ein Teilnehmer erstellt hat.
	 */
	List<BewerbungReport> getAllBewerbungenUser(int teilnehmerId);
	
	/**
	 * Erzeugt Liste aller Bewerbungen, die ein Teilnehmer erstellt hat.
	 * @param teilnehmerId Id eines Teilnehmers.
	 * @returnListe aller Bewerbungen, die ein Teilnehmer erstellt hat.
	 */
	List<BewerbungReport> getAllBewerbungenForUser(int teilnehmerId);
	
	/**
	 * Erzeugt Liste von Projektverpflechtungen.
	 * @param teilnehmerId Id eines Teilnehmers.
	 * @return Liste von Projektverpflechtungen.
	 */
	List<BewerbungReport> getProjektverpflechtungen(int teilnehmerId);
	
	/**
	 * Erzeugt Liste mit Fan in Fan out.
	 * @return Liste mit Fan in Fan out.
	 */
	List<FanInFanOut> getFanInFanOut();
}
