package de.hdm.itprojekt.shared.bo;

import java.sql.Date;				//shift + alt+ s; r

public class Projekt extends BusinessObjekt{
	
	private static final long serialVersionUID = 1L;
	
	private String 	projektName = "";
	private String 	projektbeschreibung = "";
	private int 	startDatum = 0;
	private int 	endDatum = 0;
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
	public int getStartDatum() {
		return startDatum;
	}
	/**
	 * @param startDatum the startDatum to set
	 */
	public void setStartDatum(int startDatum) {
		this.startDatum = startDatum;
	}
	/**
	 * @return the endDatum
	 */
	public int getEndDatum() {
		return endDatum;
	}
	/**
	 * @param endDatum the endDatum to set
	 */
	public void setEndDatum(int endDatum) {
		this.endDatum = endDatum;
	}
	
	
	
	

}
