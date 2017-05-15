package de.hdm.itprojekt.shared.bo;

/**
 * Umsetzung der Teilnehmerklasse. Als Attribute dienen Name, Zusatz, Email, und Rolle
 * 
 *
 */
public class Teilnehmer extends BusinessObjekt {

	private static final long serialVersionUID = 1L;

	/**
	 * Name des Teilnehmers
	 */
	/**
	 * 
	 */
	private String name = "";

	/**
	 * Zusatz des Teilnehmers
	 */
	private String zusatz = "";

	/**
	 * Email Adresse des Teilnehmers
	 */
	private String emailAdresse = "";
	
	/**
	 * Rolle des Teilnehmers
	 */
	private int rolle=0;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the zusatz
	 */
	public String getZusatz() {
		return zusatz;
	}

	/**
	 * @param zusatz the zusatz to set
	 */
	public void setZusatz(String zusatz) {
		this.zusatz = zusatz;
	}

	/**
	 * @return the emailAdresse
	 */
	public String getEmailAdresse() {
		return emailAdresse;
	}

	/**
	 * @param emailAdresse the emailAdresse to set
	 */
	public void setEmailAdresse(String emailAdresse) {
		this.emailAdresse = emailAdresse;
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

	

}
