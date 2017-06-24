package de.hdm.itprojekt.shared.bo.reports;

import de.hdm.itprojekt.shared.bo.Bewerbung;

public class BewerbungReport extends Bewerbung{

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
}
