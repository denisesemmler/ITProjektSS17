package de.hdm.itprojekt.shared.bo;

/**
 * Klasse Beteiligung um Verträge zwischen  Bewerbung und Ausschreibenden zu ermöglichen
 * @author Denise
 *
 */

public class Beteiligung extends BusinessObjekt {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Attribute erstellen 
	 */
	private int idBeteiligung 		= 0;
	private String stellungnahme 	= "";
	
	/**
	 * Fremdschlüsselbeziehung zu Projekt
	 */
	public int projektID;
	
	/**
	 * Fremdschlüsselbeziehung zu Bewerbung zum Abrufen der Bewertung
	 */
	private int bewerbungID;
	
	
	/**
	 * @return the stellungnahme
	 */
	public String getStellungnahme() {
		return stellungnahme;
	}
	/**
	 * @param stellungnahme the stellungnahme to set
	 */
	public void setStellungnahme(String stellungnahme) {
		this.stellungnahme = stellungnahme;
	}
	

	/**
	 * @return the idBeteiligung
	 */
	public int getIdBeteiligung() {
		return idBeteiligung;
	}
	/**
	 * @param idBeteiligung the idBeteiligung to set
	 */
	public void setIdBeteiligung(int idBeteiligung) {
		this.idBeteiligung = idBeteiligung;
	}

	

	/**
	 * @return the idProjekt
	 */
	public int getProjektID() {
		return projektID;
	}
	/**
	 * @param idProjekt the projektID to set
	 */
	public void setProjektID(int projektID) {
		this.projektID = projektID;
	}
	/**
	 * @return the bewerbungID
	 */
	public int getBewerbungID() {
		return bewerbungID;
	}
	/**
	 * @param bewerbungID the bewerbungID to set
	 */
	public void setBewerbungID(int bewerbungID) {
		this.bewerbungID = bewerbungID;
	}
	
	
	

}
