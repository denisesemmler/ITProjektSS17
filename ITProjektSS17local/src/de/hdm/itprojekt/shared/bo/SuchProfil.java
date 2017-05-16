package de.hdm.itprojekt.shared.bo;

import java.sql.Date;

public class SuchProfil extends BusinessObjekt {
	

/**
	 * @author Denise
	 * Klasse Suchprofil zur Darstellung der Projektmarktplatzteilnehmer 
	 * und zur Suche von Projektteilnehmern zu realisieren
	 *
	 */

		private static final long serialVersionUID = 1L;
		
		/**
		 * Titel des Suchprofils
		 */
		private String 	titel	 		= "";
		/**
		 * Erstellungsdatum festlegen
		 */
		private Date erstellDatum		= null;
		/**
		 * Aenderungsdatum festelgen
		 */
		private Date aenderungsDatum	= null;
		
		/**
		 * Eigenschaftsname festlegen
		 */
		private Eigenschaft se			= new Eigenschaft("","");
		
		/**
		 * TO-DO Zusammensetzung Eigenschaften-Wertepaar realisieren
		 */
		
		
	 /**
	  * 
	  * Innere KLasse Eigenschaft erstellt um individuelle Eigenschaften beim 
	  * erstellen von Suchprofilen zu ermöglichen
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
	 * @return the name
	 */
	public String getTitel() {
		return titel;
	}



	/**
	 * @param name the name to set
	 */
	public void setTitel(String t) {
		this.titel = t;
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
	 * @return the aenderungsDatum zurückgeben 
	 */
	public Date getAenderungsDatum() {
		return aenderungsDatum;
	}



	/**
	 * @param aenderungsDatum the aenderungsDatum setzen
	 */
	public void setAenderungsDatum(Date aenderungsDatum) {
		this.aenderungsDatum = aenderungsDatum;
	}



	/**
	 * @return Egenschaft zurückgeben
	 */
	public Eigenschaft getSe() {
		return se;
	}



	/**
	 * @param be the be to set
	 */
	public void setSe(Eigenschaft se) {
		this.se = se;
	}


		
		
	}


