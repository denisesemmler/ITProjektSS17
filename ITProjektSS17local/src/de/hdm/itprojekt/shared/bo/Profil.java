/**
 * 
 */
package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Denise
 * Klasse Partnerprofil zur Darstellung der Projektmarktplatzteilnehmer 
 *
 */
public class Profil extends BusinessObjekt {


	private static final long serialVersionUID = 1L;
	private int idProfil =0;
	private Date erstellDatum;
	private Date aenderungsDatum;
	private Eigenschaft e		= new Eigenschaft("","");
	private int Teilnehmer_idTeilnehmer;
	

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
public Date getErstellDatum() {
	return erstellDatum;
}


/**
 * @param erstellDatum the erstellDatum to set
 */
public void setErstellDatum(Date erstellDatum) {
	this.erstellDatum = erstellDatum;
}


/**
 * @return the aenderungsDatum
 */
public Date getAenderungsDatum() {
	return aenderungsDatum;
}


/**
 * @param aenderungsDatum the aenderungsDatum to set
 */
public void setAenderungsDatum(Date aenderungsDatum) {
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

public int getTeilnehmer_idTeilnehmer() {
	return Teilnehmer_idTeilnehmer;
}

public void setTeilnehmer_idTeilnehmer(int teilnehmer_idTeilnehmer) {
	Teilnehmer_idTeilnehmer = teilnehmer_idTeilnehmer;
}
	
	
	
}
