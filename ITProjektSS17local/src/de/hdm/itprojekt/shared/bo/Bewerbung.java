/**
 * 
 */
package de.hdm.itprojekt.shared.bo;

import java.util.Date;

/**
 * @author Denise
 *
 */
public class Bewerbung extends BusinessObjekt {

	private static final long serialVersionUID = 1L;
	private Date erstellDatum = null;
	private String bewerbungsText = "";
	private String titel = "";
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
	public Date getErstellDatum() {
		return erstellDatum;
	}

	/**
	 * @param erstellDatum
	 *            the erstellDatum to set
	 */
	public void setErstellDatum(Date erstellDatum) {
		this.erstellDatum = erstellDatum;
	}

	/**
	 * @return the bewerbungsText
	 */
	public String getBewerbungsText() {
		return bewerbungsText;
	}

	/**
	 * @param bewerbungsText
	 *            the bewerbungsText to set
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
	 * @param ausschreibungID
	 *            the ausschreibungID to set
	 */
	public void setAusschreibungID(int ausschreibungID) {
		this.ausschreibungID = ausschreibungID;
	}

	/**
	 * @return the idProfil
	 */
	public int getIdProfil() {
		return idProfil;
	}

	/**
	 * @param idProfil
	 *            the idProfil to set
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
	 * @param bewertung
	 *            the bewertung to set
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
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

}
