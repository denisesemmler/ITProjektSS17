package de.hdm.itprojekt.shared.bo;

import java.util.Date;

public class Ausschreibung extends BusinessObjekt {
	
	private static final long serialVersionUID = 1L;
	
	private int ausschreibungID = 0;
	private String bezeichnung = "";
	private String beschreibung = "";
	private Date bewerbungsfrist = null;
	
	
	/**
	 * 
	 * @return
	 */
	public int getAusschreibungID() {
		return ausschreibungID;
	}
	/**
	 * 
	 * @param ausschreibungID 
	 * AusschreibungID setzen
	 * 
	 */
	public void setAusschreibungID(int ausschreibungID) {
		this.ausschreibungID = ausschreibungID;
			}
	/**
	 * @param getBezeichnung
	 * @return bezeichnung
	 */
	
	public String getBezeichnung() {
		return bezeichnung;
	}
	
	/**
	 * 
	 * @param bezeichnung
	 */
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getBeschreibung() {
		return beschreibung;
	}
	
	/**
	 * 
	 * @param beschreibung
	 */
	
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getBewerbungsfrist() {
		return bewerbungsfrist;
	}
	/**
	 * 
	 * @param bewerbungsfrist
	 */
	
	public void setBewerbungsfrist(Date bewerbungsfrist) {
		this.bewerbungsfrist = bewerbungsfrist;
	}
	
	
	
	
	
	
	
}
