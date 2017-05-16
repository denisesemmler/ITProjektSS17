/**
 * 
 */
package de.hdm.itprojekt.shared.bo;

/**
 * @author Denise
 * Klasse Partnerprofil zur Darstellung der Projektmarktplatzteilnehmer 
 * und zur Suche von Projektteilnehmern zu realisieren
 *
 */
public class Profil extends BusinessObjekt {


	private static final long serialVersionUID = 1L;

	/**
	 * Name des Profil anlegen
	 */
	private String name 	= "";
	
	/**
	 * Wohn- oder Einsatzort  des Teilnehmers
	 */
	private String 	ort			= "";
	
	/**
	 * Postleitzahl des Teilnehmers oder Einsatzort
	 */
	private int plz = 0;
	
	/**
	 * Straﬂe des Teilnehmers
	 */
	private String 	strasse		= "";
	
	/**
	 * bei Rolle  wird angegeben ob Teilnehmers UN/ Team oder Person ist
	 */	
	private int		rolle		= 0;
	
	/**
	 * erstellt ein Teilnehmer ein Projekt wird der boolean 
	 * projektleiter auf true gesetzt
	 */
	private boolean	projektLeiter	= false;
	
	private Eigenschaft e		= new Eigenschaft("","");
	


/**
 * @return profilName
 */
public String getName() {
	return name;
}


/**
 * @param profilName the profilName to set
 */
public void setName(String name) {
	this.name = name;
}


/**
 * @return  ort
 */
public String getOrt() {
	return ort;
}


/**
 * @param ort the ort to set
 */
public void setOrt(String ort) {
	this.ort = ort;
}


/**
 * @return  strasse
 */
public String getStrasse() {
	return strasse;
}


/**
 * @param strasse the strasse to set
 */
public void setStrasse(String strasse) {
	this.strasse = strasse;
}


/**
 * @return be
 */
public Eigenschaft getE() {
	return e;
}


/**
 * @param be the be to set
 */
public void setE(Eigenschaft e) {
	this.e = e;
}


/**
 * @return rolle
 */
public int getRolle() {
	return rolle;
}


/**
 * @param rolle the rolle to set
 */
public void setRolle(int rolle) {
	this.rolle = rolle;
}


/**
 * @return projektLeiter
 */
public boolean isProjektLeiter() {
	return projektLeiter;
}


/**
 * @param projektl the projektLeiter to set
 */
public void setProjektLeiter(boolean projektl) {
	this.projektLeiter = projektl;
}


/**
 * @return the plz
 */
public int getPlz() {
	return plz;
}


/**
 * @param plz the plz to set
 */
public void setPlz(int plz) {
	this.plz = plz;
}
	
	
	
}
