package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;				

/**
 * Umsetzung der Projektklasse. Als Attribute dienen idProjekt, idTeilnehmer und idProjekt
 * Projektname, Projektbeschreibung,  Startdatum und Enddatum
 * 
 * @author denissemmler
 *
 */

public class Projekt extends BusinessObjekt{
	
	private static final long serialVersionUID = 1L;
	private int idProjekt = 0;
	private String 	projektName = "";
	private String 	projektbeschreibung = "";
	private Timestamp	startDatum;
	private Timestamp	endDatum;

	/**
	 * Fremschlüsselbeziehung zu Teilnehmer
	 */
	private int idTeilnehmer;
	
	/**
	 * Fremschlüsselbeziehung zu Projekt
	 */
	private int idProjekt;
	/**
	 * @return the projektName
	 */
	public String getProjektName() {
		return projektName;
	}
	/**
	 * @param projektName the projektName to set
	 */
	public void setProjektName(String projektName) {
		this.projektName = projektName;
	}
	/**
	 * @return the projektbeschreibung
	 */
	public String getProjektbeschreibung() {
		return projektbeschreibung;
	}
	/**
	 * @param projektbeschreibung the projektbeschreibung to set
	 */
	public void setProjektbeschreibung(String projektbeschreibung) {
		this.projektbeschreibung = projektbeschreibung;
	}
	/**
	 * @return the startDatum
	 */
	public Timestamp getStartDatum() {
		return startDatum;
	}
	/**
	 * @param startDatum the startDatum to set
	 */
	public void setStartDatum(Timestamp startDatum) {
		this.startDatum = startDatum;
	}
	/**
	 * @return the endDatum
	 */
	public Timestamp getEndDatum() {
		return endDatum;
	}
	/**
	 * @param endDatum the endDatum to set
	 */
	public void setEndDatum(Timestamp endDatum) {
		this.endDatum = endDatum;
	}
	
	
	
	

}