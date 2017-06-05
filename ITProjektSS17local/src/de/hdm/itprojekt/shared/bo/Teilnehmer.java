package de.hdm.itprojekt.shared.bo;

/**
 * Umsetzung der Teilnehmerklasse. Als Attribute dienen Name, Zusatz, Email, und Rolle
 * 
 *
 */
public class Teilnehmer extends BusinessObjekt {

	private static final long serialVersionUID = 1L;
	private int idTeilnehmer	= 0;
	private String name 		= "";
	private String zusatz 		= "";
	private String 	ort			= "";
	private int plz 			= 0;
	private String strasse		= "";
	private String email		= "";
	private int profil_idProfil	= 0;
	/**
	 * bei Rolle  wird angegeben ob Teilnehmers UN/ Team oder Person ist
	 */	
	private int		rolle		= 0;
	
	/**
	 * erstellt ein Teilnehmer ein Projekt wird der boolean 
	 * projektleiter auf true gesetzt
	 */
	private int	projektLeiter	= 2;
		
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
	public String getEmail() {
		return email;
	}

	/**
	 * @param emailAdresse the emailAdresse to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the idTeilnehmer
	 */
	public int getIdTeilnehmer() {
		return idTeilnehmer;
	}

	/**
	 * @param idTeilnehmer the idTeilnehmer to set
	 */
	public void setIdTeilnehmer(int idTeilnehmer) {
		this.idTeilnehmer = idTeilnehmer;
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
	 * @return the projektLeiter
	 */
	public int getProjektLeiter() {
		return projektLeiter;
	}

	/**
	 * @param projektLeiter the projektLeiter to set
	 */
	public void setProjektLeiter(int projektLeiter) {
		this.projektLeiter = projektLeiter;
	}

	/**
	 * @return the profil_idProfil
	 */
	public int getProfil_idProfil() {
		return profil_idProfil;
	}

	/**
	 * @param profil_idProfil the profil_idProfil to set
	 */
	public void setProfil_idProfil(int profil_idProfil) {
		this.profil_idProfil = profil_idProfil;
	}

	

}
