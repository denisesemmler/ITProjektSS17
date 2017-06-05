/**
 * 
 */
package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

/**
 * @author Denise
 * Klasse Partnerprofil zur Darstellung der Projektmarktplatzteilnehmer 
 *
 */
public class Profil extends BusinessObjekt {


	private static final long serialVersionUID = 1L;
	private int idProfil =0;
	private Timestamp erstellDatum;
	private Timestamp aenderungsDatum;
	private Eigenschaft e		= new Eigenschaft("","");
	

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
 * @return the aenderungsDatum
 */
public Timestamp getAenderungsDatum() {
	return aenderungsDatum;
}


/**
 * @param aenderungsDatum the aenderungsDatum to set
 */
public void setAenderungsDatum(Timestamp aenderungsDatum) {
	this.aenderungsDatum = aenderungsDatum;
}


/**
 * @return the e
 */
public Eigenschaft getE() {
	return e;
}


/**
 * @param e the e to set
 */
public void setE(Eigenschaft e) {
	this.e = e;
}
	
	
	
}
