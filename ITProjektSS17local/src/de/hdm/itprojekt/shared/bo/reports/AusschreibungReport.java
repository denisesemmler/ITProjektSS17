package de.hdm.itprojekt.shared.bo.reports;

import de.hdm.itprojekt.shared.bo.Ausschreibung;

/**
 * BusinessObject für Reports
 * @author Jiayi
 *
 */
public class AusschreibungReport extends Ausschreibung{

	private static final long serialVersionUID = 8083128205102356812L;
	private String projektName;
	private String ansprechpartnerName;
	
	public AusschreibungReport(Ausschreibung ausschreibung) {
		super(ausschreibung);
	}
	public AusschreibungReport() {
		// TODO Auto-generated constructor stub
	}
	public String getProjektName() {
		return projektName;
	}
	public void setProjektName(String projektName) {
		this.projektName = projektName;
	}
	public String getAnsprechpartnerName() {
		return ansprechpartnerName;
	}
	public void setAnsprechpartnerName(String ansprechpartnerName) {
		this.ansprechpartnerName = ansprechpartnerName;
	}
	
}
