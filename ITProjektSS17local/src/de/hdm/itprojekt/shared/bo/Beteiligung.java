package de.hdm.itprojekt.shared.bo;

import java.sql.Date;
import java.sql.Timestamp;

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
	public Profil ausschreibender;
	public Profil bewerbender;
	private String projektName;
	
	/**
	 * Fremdschlüsselbeziehung zu Projekt
	 */
	private int idProjekt;
	
	/**
	 * Fremdschlüsselbeziehung zu Bewerbung zum Abrufen der Bewertung
	 */
	private int idBewerbung;
	
	
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
	 * @return the ausschreibender
	 */
	public Profil getAusschreibender() {
		return ausschreibender;
	}
	/**
	 * @param ausschreibender the ausschreibender to set
	 */
	public void setAusschreibender(Profil ausschreibender) {
		this.ausschreibender = ausschreibender;
	}
	/**
	 * @return the bewerbender
	 */
	public Profil getBewerbender() {
		return bewerbender;
	}
	/**
	 * @param bewerbender the bewerbender to set
	 */
	public void setBewerbender(Profil bewerbender) {
		this.bewerbender = bewerbender;
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
	 * @return the idProjekt
	 */
	public int getIdProjekt() {
		return idProjekt;
	}
	/**
	 * @param idProjekt the idProjekt to set
	 */
	public void setIdProjekt(int idProjekt) {
		this.idProjekt = idProjekt;
	}
	/**
	 * @return the idBewerbung
	 */
	public int getIdBewerbung() {
		return idBewerbung;
	}
	/**
	 * @param idBewerbung the idBewerbung to set
	 */
	public void setIdBewerbung(int idBewerbung) {
		this.idBewerbung = idBewerbung;
	}
	
	
	

}
