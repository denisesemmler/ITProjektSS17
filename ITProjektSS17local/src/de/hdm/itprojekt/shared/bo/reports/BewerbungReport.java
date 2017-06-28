package de.hdm.itprojekt.shared.bo.reports;

import java.util.Date;
import java.util.List;

import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;

/**
 * BusinessObject f�r Reports
 * @author Jiayi
 *
 */
public class BewerbungReport extends Bewerbung{

	/**
	 * 
	 */
	private static final long serialVersionUID = 828990810588810297L;

	private String projektName;
	private String bewerbungName;
	private String bewerberName;
	private String ansprechpartnerName;
	private Date frist;
	private List<Eigenschaft> eigenschaften;
	private List<BewerbungReport> referenz;
	
	public BewerbungReport() {
		
	}
	public BewerbungReport(Bewerbung bewerbung) {
		this.setAusschreibungID(bewerbung.getAusschreibungID());
		this.setBewerbungsText(bewerbung.getBewerbungsText());
		this.setBewertung(bewerbung.getBewertung());
		this.setCreationDate(bewerbung.getCreationDate());
		this.setErstellDatum(bewerbung.getErstellDatum());
		this.setId(bewerbung.getId());
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
	public Date getFrist() {
		return frist;
	}
	public void setFrist(Date frist) {
		this.frist = frist;
	}
	public String getAnsprechpartnerName() {
		return ansprechpartnerName;
	}
	public void setAnsprechpartnerName(String ansprechpartnerName) {
		this.ansprechpartnerName = ansprechpartnerName;
	}
	public List<BewerbungReport> getReferenz() {
		return referenz;
	}
	public void setReferenz(List<BewerbungReport> referenz) {
		this.referenz = referenz;
	}
	public List<Eigenschaft> getEigenschaften() {
		return eigenschaften;
	}
	public void setEigenschaften(List<Eigenschaft> eigenschaften) {
		this.eigenschaften = eigenschaften;
	}
}
