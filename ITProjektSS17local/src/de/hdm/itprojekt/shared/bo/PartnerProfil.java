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
public class PartnerProfil extends BusinessObjekt {

	private static final long serialVersionUID = 1L;
	/**
	 * Email-Adresse des Teilnehmers 
	 */
	private String 	email		= "";
	
	/**
	 * Name des Teilnehmers anlegen
	 */
	private String name 	= "";
	
	/**
	 * Wohnort oder UN-Sitz des Teilnehmers
	 */
	private String 	ort			= "";
	
	
	/**
	 * Straße des Teilnehmers
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
	
	private Eigenschaft be		= new Eigenschaft("","");
	
	
	
 /**
  * 
  * Innere KLasse Eigenshaft erstellt um individuelle Eigenschaften beim 
  * erstellen von Partnerprofilen zu ermöglichen
  * 
  *
  */
class Eigenschaft {

	String name;
	
	public Eigenschaft (String n, String w){
		this.name =n;
		}
	
}


/**
 * @return the profilName
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
 * @return the ort
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
 * @return the strasse
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
 * @return the be
 */
public Eigenschaft getBe() {
	return be;
}


/**
 * @param be the be to set
 */
public void setBe(Eigenschaft be) {
	this.be = be;
}


/**
 * @return the rolle
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
 * @return the projektLeiter
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
 * @return the email
 */
public String getEmail() {
	return email;
}


/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}
	
	
	
}
