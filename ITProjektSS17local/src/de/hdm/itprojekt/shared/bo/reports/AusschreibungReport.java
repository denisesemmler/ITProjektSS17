package de.hdm.itprojekt.shared.bo.reports;

import java.util.List;

import de.hdm.itprojekt.shared.bo.Ausschreibung;

public class AusschreibungReport extends Ausschreibung{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8083128205102356812L;
	private String projektName;
	
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
	
}
