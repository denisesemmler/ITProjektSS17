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
	private int idBewerbung=0;
	private float bewertung;
	private String status;
	
	/**
	 * Fremdschlüsselbeziehung zu Profil
	 */
	private int idProfil;
	
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
	/**
	 * @return the idProfil
	 */
	public int getIdProfil() {
		return idProfil;
	}
	/**
	 * @param idProfil the idProfil to set
	 */
	public void setIdProfil(int idProfil) {
		this.idProfil = idProfil;
	}
	/**
	 * @return the bewertung
	 */
	public float getBewertung() {
		return bewertung;
	}
	/**
	 * @param bewertung the bewertung to set
	 */
	public void setBewertung(float bewertung) {
		this.bewertung = bewertung;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
