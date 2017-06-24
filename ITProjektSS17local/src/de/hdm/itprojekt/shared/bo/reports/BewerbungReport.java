package de.hdm.itprojekt.shared.bo.reports;

import de.hdm.itprojekt.shared.bo.Bewerbung;

public class BewerbungReport extends Bewerbung{

	/**
	 * 
	 */
	private static final long serialVersionUID = 828990810588810297L;

	private String projektName;
	private String bewerbungName;
	private String bewerberName;
	
	public BewerbungReport() {
		
	}
	public BewerbungReport(Bewerbung bewerbung) {
		this.setAusschreibungID(bewerbung.getAusschreibungID());
		this.setBewerbungsText(bewerbung.getBewerbungsText());
		this.setBewertung(bewerbung.getBewertung());
		this.setCreationDate(bewerbung.getCreationDate());
		this.setErstellDatum(bewerbung.getErstellDatum());
		this.setId(bewerbung.getId());
		this.setIdBewerbung(bewerbung.getIdBewerbung());
		this.setIdProfil(bewerbung.getIdProfil());
		this.setStatus(bewerbung.getStatus());
	}
	public String getProjektName() {
		return projektName;
	}
	public void setProjektName(String projektName) {
		this.projektName = projektName;
	}
	public String getBewerbungName() {
		return bewerbungName;
	}
	public void setBewerbungName(String bewerbungName) {
		this.bewerbungName = bewerbungName;
	}
	public String getBewerberName() {
		return bewerberName;
	}
	public void setBewerberName(String bewerberName) {
		this.bewerberName = bewerberName;
	}
}
