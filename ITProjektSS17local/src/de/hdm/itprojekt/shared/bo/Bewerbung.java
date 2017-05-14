/**
 * 
 */
package de.hdm.itprojekt.shared.bo;

import java.sql.Date;

/**
 * @author Denise
 *
 */
public class Bewerbung extends BusinessObjekt{
	
	private static final long serialVersionUID = 1L;
	
	private Date erstellDatum = null;
	private String bewerbungsText= "";
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
	
	
	
	

}
