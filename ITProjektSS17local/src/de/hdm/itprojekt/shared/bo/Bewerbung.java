/**
 * 
 */
package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

/**
 * @author Denise
 *
 */
public class Bewerbung extends BusinessObjekt{
	
	private static final long serialVersionUID = 1L;
	private Timestamp erstellDatum = null;
	private String bewerbungsText= "";
	

	/**
	 * Fremdschlüsselbeziehung zu Teilnehmer
	 */
	private int idTeilnehmer;
	
	/**
	 * Fremdschlüsselbeziehung zu Ausschreibung
	 */
	private int ausschreibungID;
	
	
	
	/**
	 * @return the erstellDatum
	 */
	public Timestamp getErstellDatum() {
		return erstellDatum;
	}
	/**
	 * @param erstellDatum the erstellDatum to set
	 */
	public void setErstellDatum(Timestamp erstellDatum) {
		this.erstellDatum = erstellDatum;
	}
	/**
	 * @return the bewerbungsText
	 */
	public String getBewerbungsText() {
		return bewerbungsText;
	}
	/**
	 * @param bewerbungsText the bewerbungsText to set
	 */
	public void setBewerbungsText(String bewerbungsText) {
		this.bewerbungsText = bewerbungsText;
	}
	/**
	 * @return the ausschreibungID
	 */
	public int getAusschreibungID() {
		return ausschreibungID;
	}
	/**
	 * @param ausschreibungID the ausschreibungID to set
	 */
	public void setAusschreibungID(int ausschreibungID) {
		this.ausschreibungID = ausschreibungID;
	}
	/**
	 * @return the idTeilnehmer
	 */
	public int getIdTeilnehmer() {
		return idTeilnehmer;
	}
	/**
	 * @param idTeilnehmer the idTeilnehmer to set
	 */
	public void setIdTeilnehmer(int idTeilnehmer) {
		this.idTeilnehmer = idTeilnehmer;
	}
	
	
	
	

}
