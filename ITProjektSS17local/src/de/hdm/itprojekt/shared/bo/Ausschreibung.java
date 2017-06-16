package de.hdm.itprojekt.shared.bo;

import java.sql.Timestamp;

public class Ausschreibung extends BusinessObjekt {
	
	private static final long serialVersionUID = 1L;
	
	private int idAusschreibung 		= 0;
	private String titel				= "";
	private String beschreibung 		= "";
	private Timestamp bewerbungsfrist 	= null;
	private int Projekt_idProjekt		= 0;
	private int Profil_idSuchprofil 	= 0;
	private String status				= "laufend";
	
	public Ausschreibung() {
		
	}
	
	public Ausschreibung(Ausschreibung ausschreibung) {
		this.setTitel(ausschreibung.getTitel());
		this.setBeschreibung(ausschreibung.getBeschreibung());
		this.setBewerbungsfrist(ausschreibung.getBewerbungsfrist());
		this.setProjekt_idProjekt(ausschreibung.getProjekt_idProjekt());
		this.setProfil_idSuchprofil(ausschreibung.getProfil_idSuchprofil());
		this.setStatus(ausschreibung.getStatus());
		
	}

	/**
	 * 
	 * @return
	 */
	public String getBeschreibung() {
		return beschreibung;
	}
	
	/**
	 * 
	 * @param beschreibung
	 */
	
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	/**
	 * 
	 * @return
	 */
	public Timestamp getBewerbungsfrist() {
		return bewerbungsfrist;
	}
	/**
	 * 
	 * @param bewerbungsfrist
	 */
	
	public void setBewerbungsfrist(Timestamp bewerbungsfrist) {
		this.bewerbungsfrist = bewerbungsfrist;
	}
	/**
	 * @return the titel
	 */
	public String getTitel() {
		return titel;
	}
	/**
	 * @param titel the titel to set
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}
	/**
	 * @return the projekt_idProjekt
	 */
	public int getProjekt_idProjekt() {
		return Projekt_idProjekt;
	}
	/**
	 * @param projekt_idProjekt the projekt_idProjekt to set
	 */
	public void setProjekt_idProjekt(int projekt_idProjekt) {
		Projekt_idProjekt = projekt_idProjekt;
	}
	/**
	 * @return the profil_idSuchprofil
	 */
	public int getProfil_idSuchprofil() {
		return Profil_idSuchprofil;
	}
	/**
	 * @param profil_idSuchprofil the profil_idSuchprofil to set
	 */
	public void setProfil_idSuchprofil(int profil_idSuchprofil) {
		Profil_idSuchprofil = profil_idSuchprofil;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the idAusschreibung
	 */
	public int getIdAusschreibung() {
		return idAusschreibung;
	}
	/**
	 * @param idAusschreibung the idAusschreibung to set
	 */
	public void setIdAusschreibung(int idAusschreibung) {
		this.idAusschreibung = idAusschreibung;
	}
	
	
	
	
	
	
	
}
