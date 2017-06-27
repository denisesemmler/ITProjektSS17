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
	private int idProfil = 0;
	private Date erstellDatum = null;
	private Date aenderungsDatum = null;
	private int suchprofil = 0;
	
	private int Teilnehmer_idTeilnehmer = 0;
	
public Profil(){
	
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


public int getTeilnehmer_idTeilnehmer() {
	return Teilnehmer_idTeilnehmer;
}

public void setTeilnehmer_idTeilnehmer(int teilnehmer_idTeilnehmer) {
	Teilnehmer_idTeilnehmer = teilnehmer_idTeilnehmer;
}
public int getSuchprofil() {
	return suchprofil;
}
public void setSuchprofil(int suchprofil) {
	this.suchprofil = suchprofil;
}
	
	
	
}
