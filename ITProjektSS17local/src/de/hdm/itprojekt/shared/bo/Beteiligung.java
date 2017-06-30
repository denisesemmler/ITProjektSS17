package de.hdm.itprojekt.shared.bo;

import java.util.Date;

/**
 * Klasse Beteiligung um Verträge zwischen Bewerbung und Ausschreibenden zu
 * ermöglichen
 * 
 * @author Denise
 *
 */

public class Beteiligung extends BusinessObjekt {

	private static final long serialVersionUID = 1L;

	/**
	 * Attribute erstellen
	 */
	private String stellungnahme = "";
	private int manntage;
	private Date startdatum;
	private Date enddatum;

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
	 * @param stellungnahme
	 *            the stellungnahme to set
	 */
	public void setStellungnahme(String stellungnahme) {
		this.stellungnahme = stellungnahme;
	}

	/**
	 * @return the idProjekt
	 */
	public int getProjektID() {
		return projektID;
	}

	/**
	 * @param idProjekt
	 *            the projektID to set
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
	 * @param bewerbungID
	 *            the bewerbungID to set
	 */
	public void setBewerbungID(int bewerbungID) {
		this.bewerbungID = bewerbungID;
	}

	/**
	 * @return Manntage wird ausgegeben
	 */
	public int getManntage() {
		return manntage;
	}

	/**
	 * @param Manntage
	 *            wird gesetzt
	 */
	public void setManntage(int manntage) {
		this.manntage = manntage;
	}

	/**
	 * @return Startdatum wird aausgegeben
	 */
	public Date getStartdatum() {
		return startdatum;
	}

	/**
	 * @param Startdatum
	 *            wird gesetzt
	 */
	public void setStartdatum(Date startdatum) {
		this.startdatum = startdatum;
	}

	/**
	 * @return Enddatum wird ausgegeben
	 */
	public Date getEnddatum() {
		return enddatum;
	}

	/**
	 * @param Enddatum
	 *            wird gesetzt
	 */
	public void setEnddatum(Date enddatum) {
		this.enddatum = enddatum;
	}

	@Override
	public String toString() {
		return "Beteiligung [stellungnahme=" + stellungnahme + ", manntage=" + manntage + ", startdatum=" + startdatum
				+ ", enddatum=" + enddatum + ", projektID=" + projektID + ", bewerbungID=" + bewerbungID + ", getId()="
				+ getId() + ", getCreationDate()=" + getCreationDate() + "]";
	}
	
	

}
