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
public class Partnerprofil extends BusinessObjekt {

	private static final long serialVersionUID = 1L;
	
	private String 	profilName 	= "";
	private String 	ort			= "";
	private String 	strasse		= "";
	private Eigenschaft be		= new Eigenschaft("","");
	
	
	
 /**
  * 
  * Innere KLasse Eigenshaft erstellt um individuelle Eigenschaften beim 
  * erstellen von Partnerprofilen zu ermöglichen
  * 
  *
  */
class Eigenschaft {
	
	private String name;
	private String wert;
	
	public Eigenschaft (String n, String w){
		this.name =n;
		this.wert =w;
		}
	
}


/**
 * @return the profilName
 */
public String getProfilName() {
	return profilName;
}


/**
 * @param profilName the profilName to set
 */
public void setProfilName(String profilName) {
	this.profilName = profilName;
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
	
	
	
}
