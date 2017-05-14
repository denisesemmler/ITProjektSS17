package de.hdm.itprojekt.shared.bo;

import java.sql.Date;				//shift + alt+ s; r

public class Projekt extends BusinessObjekt{
	
	private static final long serialVersionUID = 1L;
	
	private String 	projektName = "";
	private String 	projektbeschreibung = "";
	private Date 	startDatum = null;
	private Date	endDatum = null;
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
	public Date getStartDatum() {
		return startDatum;
	}
	/**
	 * @param startDatum the startDatum to set
	 */
	public void setStartDatum(Date startDatum) {
		this.startDatum = startDatum;
	}
	/**
	 * @return the endDatum
	 */
	public Date getEndDatum() {
		return endDatum;
	}
	/**
	 * @param endDatum the endDatum to set
	 */
	public void setEndDatum(Date endDatum) {
		this.endDatum = endDatum;
	}
	
	
	
	

}
