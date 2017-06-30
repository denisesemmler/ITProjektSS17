package de.hdm.itprojekt.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.db.AusschreibungMapper;
import de.hdm.itprojekt.server.db.BewerbungMapper;
import de.hdm.itprojekt.server.db.EigenschaftMapper;
import de.hdm.itprojekt.server.db.ProfilMapper;
import de.hdm.itprojekt.server.db.ProjektMapper;
import de.hdm.itprojekt.server.db.TeilnehmerMapper;
import de.hdm.itprojekt.shared.ReportService;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Profil;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Teilnehmer;
import de.hdm.itprojekt.shared.bo.reports.AusschreibungReport;
import de.hdm.itprojekt.shared.bo.reports.BewerbungReport;
import de.hdm.itprojekt.shared.bo.reports.FanInFanOut;

/**
 * Backend Service f�r die unterschiedlichen Berichte
 * 
 * @author Jiayi
 *
 */
public class ReportServiceImpl extends RemoteServiceServlet implements ReportService {

	private static final long serialVersionUID = -7548005663369824973L;

	@Override
	public List<AusschreibungReport> getAllAusschreibungen() {
		// Leere Liste erstellen
		List<AusschreibungReport> report = new ArrayList<AusschreibungReport>();

		// Alle Ausschreibungen laden
		List<Ausschreibung> ausschreibungen = AusschreibungMapper.ausschreibungMapper().findAllAusschreibungen();
		for (Ausschreibung ausschreibung : ausschreibungen) {

			// BO erstellen
			AusschreibungReport reportEntry = new AusschreibungReport(ausschreibung);

			// Prokjektname hinzufügen
			Projekt projekt = ProjektMapper.projektMapper().findById(reportEntry.getProjekt_idProjekt());
			reportEntry.setProjektName(projekt.getName());

			// Ansprechpartner hinzufügen
			Teilnehmer teilnehmer = TeilnehmerMapper.teilnehmerMapper()
					.findById(ausschreibung.getTeilnehmer_idTeilnehmer());
			reportEntry.setAnsprechpartnerName(teilnehmer.getVorname() + " " + teilnehmer.getNachname());

			// Der Liste hinzufügen
			report.add(reportEntry);
		}
		return report;
	}

	@Override
	public List<AusschreibungReport> getAllAusschreibungenUser(int teilnehmerId) {
		// Leere Liste erstellen
		List<AusschreibungReport> report = new ArrayList<AusschreibungReport>();

		// Alle ausschreibungen von Teilnehmer laden
		List<Ausschreibung> ausschreibungen = AusschreibungMapper.ausschreibungMapper()
				.findAllAusschreibungByTeilnehmerId(teilnehmerId);
		for (Ausschreibung ausschreibung : ausschreibungen) {
			// BO erstellen
			AusschreibungReport reportEntry = new AusschreibungReport(ausschreibung);

			// Prokjektname hinzufügen
			Projekt projekt = ProjektMapper.projektMapper().findById(reportEntry.getProjekt_idProjekt());
			reportEntry.setProjektName(projekt.getName());

			// Ansprechpartner hinzufügen
			Teilnehmer teilnehmer = TeilnehmerMapper.teilnehmerMapper()
					.findById(ausschreibung.getTeilnehmer_idTeilnehmer());
			reportEntry.setAnsprechpartnerName(teilnehmer.getVorname() + " " + teilnehmer.getNachname());

			// Der Liste hinzufügen
			report.add(reportEntry);
		}
		return report;
	}

	@Override
	public List<BewerbungReport> getAllBewerbungenUser(int teilnehmerId) {
		// Leere Liste erstellen
		List<BewerbungReport> report = new ArrayList<BewerbungReport>();

		List<Ausschreibung> ausschreibungen = AusschreibungMapper.ausschreibungMapper()
				.findAllAusschreibungByTeilnehmerId(teilnehmerId);
		for (Ausschreibung ausschreibung : ausschreibungen) {
			Vector<Bewerbung> bewerbungen = BewerbungMapper.bewerbungMapper()
					.findByAusschreibungsId(ausschreibung.getId());

			for (Bewerbung bewerbung : bewerbungen) {
				BewerbungReport reportEntry = new BewerbungReport(bewerbung);

				// Projektname ermitteln
				Projekt projekt = ProjektMapper.projektMapper().findById(ausschreibung.getProjekt_idProjekt());
				reportEntry.setProjektName(projekt.getName());

				Profil profil = ProfilMapper.profilMapper().findById(bewerbung.getIdProfil());

				// Teilnehmername ermitteln
				Teilnehmer teilnehmer = TeilnehmerMapper.teilnehmerMapper()
						.findById(profil.getTeilnehmer_idTeilnehmer());
				reportEntry.setBewerberName(teilnehmer.getVorname() + " " + teilnehmer.getNachname());

				reportEntry.setBewerbungName(ausschreibung.getTitel());

				// Eigenschaften ermitteln
				List<Eigenschaft> eigenschaften = EigenschaftMapper.eigenschaftMapper()
						.findByProfil(bewerbung.getIdProfil());
				reportEntry.setEigenschaften(eigenschaften);

				// Der Liste hinzufügen
				report.add(reportEntry);
			}
		}

		return report;
	}

	@Override
	public List<BewerbungReport> getAllBewerbungenForUser(int teilnehmerId) {
		// Leere Liste erstellen
		List<BewerbungReport> report = new ArrayList<BewerbungReport>();

		// Profil des Teilnehmers
		Profil profil = ProfilMapper.profilMapper().findByTeilnehmerId(teilnehmerId);

		// Alle Bewerbungen des Teilnehmers
		Vector<Bewerbung> bewerbungen = BewerbungMapper.bewerbungMapper().findBewerbungByTeilnehmerId(profil.getId());

		for (Bewerbung bewerbung : bewerbungen) {

			// BO erstellen
			BewerbungReport reportEntry = new BewerbungReport(bewerbung);

			// Ausschreibungsname ermitteln
			Ausschreibung ausschreibung = AusschreibungMapper.ausschreibungMapper()
					.findById(bewerbung.getAusschreibungID());
			reportEntry.setBewerbungName(ausschreibung.getTitel());

			// Projektname ermitteln
			Projekt projekt = ProjektMapper.projektMapper().findById(ausschreibung.getProjekt_idProjekt());
			reportEntry.setProjektName(projekt.getName());

			// Ansprechpartner ermitteln
			Teilnehmer teilnehmer = TeilnehmerMapper.teilnehmerMapper()
					.findById(ausschreibung.getTeilnehmer_idTeilnehmer());
			reportEntry.setAnsprechpartnerName(teilnehmer.getVorname() + " " + teilnehmer.getNachname());

			// Bo der Liste hinzufügen
			reportEntry.setFrist(ausschreibung.getBewerbungsfrist());

			// Der Liste hinzufügen
			report.add(reportEntry);
		}

		return report;
	}

	@Override
	public List<BewerbungReport> getProjektverpflechtungen(int teilnehmerId) {
		// Leere Liste erstellen
		List<BewerbungReport> report = new ArrayList<BewerbungReport>();

		// Alle Ausschreibungen eines Teilnehmers
		List<Ausschreibung> ausschreibungen = AusschreibungMapper.ausschreibungMapper()
				.findAllAusschreibungByTeilnehmerId(teilnehmerId);
		for (Ausschreibung ausschreibung : ausschreibungen) {

			// Alle Bewerbungen auf eine Ausschreibung
			Vector<Bewerbung> bewerbungen = BewerbungMapper.bewerbungMapper()
					.findByAusschreibungsId(ausschreibung.getId());
			for (Bewerbung bewerbung : bewerbungen) {

				// BO erstellen
				BewerbungReport reportEntry = new BewerbungReport(bewerbung);

				// Projektname ermitteln
				Projekt projekt = ProjektMapper.projektMapper().findById(ausschreibung.getProjekt_idProjekt());
				reportEntry.setProjektName(projekt.getName());

				// Profil des Bewerbers
				Profil profil = ProfilMapper.profilMapper().findById(bewerbung.getIdProfil());

				// Name des Bewerbers ermitteln
				Teilnehmer teilnehmer = TeilnehmerMapper.teilnehmerMapper()
						.findById(profil.getTeilnehmer_idTeilnehmer());
				reportEntry.setBewerberName(teilnehmer.getVorname() + " " + teilnehmer.getNachname());

				// Bo der Liste hinzufügen
				reportEntry.setBewerbungName(ausschreibung.getTitel());

				// Verflechtungen ermitteln
				Vector<Bewerbung> referencen = BewerbungMapper.bewerbungMapper()
						.findBewerbungByTeilnehmerId(profil.getId());

				List<BewerbungReport> refs = new ArrayList<BewerbungReport>();

				// Verflechtungen durchiterieren und dem BO hinzufügen
				for (Bewerbung ref : referencen) {
					BewerbungReport refEntry = new BewerbungReport(ref);

					Ausschreibung refAusschreibung = AusschreibungMapper.ausschreibungMapper()
							.findById(ref.getAusschreibungID());

					Projekt refProjekt = ProjektMapper.projektMapper()
							.findById(refAusschreibung.getProjekt_idProjekt());
					refEntry.setProjektName(refProjekt.getName());

					refEntry.setBewerbungName(refAusschreibung.getTitel());

					refs.add(refEntry);
				}
				reportEntry.setReferenz(refs);

				// Der Liste hinzufügen
				report.add(reportEntry);
			}
		}

		return report;
	}

	@Override
	public List<FanInFanOut> getFanInFanOut() {
		// Leere Liste erstellen
		List<FanInFanOut> report = new ArrayList<>();

		// Liste aller Teilnehmer
		List<Teilnehmer> allTeilnehmer = TeilnehmerMapper.teilnehmerMapper().findAllTeilnehmer();

		for (Teilnehmer teilnehmer : allTeilnehmer) {

			// Bo erstellen
			FanInFanOut reportEntry = new FanInFanOut();

			// Name des Teilnehmers
			reportEntry.setTeilnehmerName(teilnehmer.getVorname() + " " + teilnehmer.getNachname());

			Profil profil = ProfilMapper.profilMapper().findByTeilnehmerId(teilnehmer.getId());

			// Fan out ermitteln
			int fanOut = BewerbungMapper.bewerbungMapper().findBewerbungByTeilnehmerId(profil.getId()).size();
			reportEntry.setFanOut(fanOut);

			// Fan in ermitteln
			int fanIn = AusschreibungMapper.ausschreibungMapper().findAllAusschreibungByTeilnehmerId(teilnehmer.getId())
					.size();
			reportEntry.setFanIn(fanIn);

			// Der Liste hinzufügen
			report.add(reportEntry);
		}
		return report;
	}

	@Override
	public List<AusschreibungReport> getVorschlaege(int teilnehmerId) {
		// Leere Liste erstellen
		List<AusschreibungReport> report = new ArrayList<>();

		// Profil des Teilnehmers
		Profil profil = ProfilMapper.profilMapper().findByTeilnehmerId(teilnehmerId);

		// eigenschaften des Teilnehmers
		List<Eigenschaft> eigenschaftenTeilnehmer = EigenschaftMapper.eigenschaftMapper().findByProfil(profil.getId());

		// Liste aller Ausschreibungen
		List<Ausschreibung> ausschreibungen = AusschreibungMapper.ausschreibungMapper().findAllAusschreibungen();

		for (Ausschreibung ausschreibung : ausschreibungen) {
			// Liste geforderdet Eigenschaften einer Ausschreibung
			List<Eigenschaft> eigenschaftenProfil = EigenschaftMapper.eigenschaftMapper()
					.findByProfil(ausschreibung.getProfil_idSuchprofil());
			int same = 0;

			// Vergleich der Eigenschaften der Ausschreibung und des Teilnehmers
			for (Eigenschaft eigenschaftProfil : eigenschaftenProfil) {
				for (Eigenschaft eigenschaftTeilnehmer : eigenschaftenTeilnehmer) {
					if (eigenschaftTeilnehmer.getName().equals(eigenschaftProfil.getName())) {
						if (eigenschaftTeilnehmer.getWert() == eigenschaftProfil.getWert()) {
							same++;
						}
					}
				}
			}

			// Falls mindestens sechs Eigenschaften übereinstimmen wird dieses
			// hinzugefügt.
			if (same >= 6) {
				AusschreibungReport reportEntry = new AusschreibungReport(ausschreibung);

				// Projektname ermitteln
				Projekt projekt = ProjektMapper.projektMapper().findById(ausschreibung.getProjekt_idProjekt());
				reportEntry.setProjektName(projekt.getName());

				// Teilnehmername ermitteln
				Teilnehmer teilnehmer = TeilnehmerMapper.teilnehmerMapper()
						.findById(ausschreibung.getTeilnehmer_idTeilnehmer());
				reportEntry.setAnsprechpartnerName(teilnehmer.getVorname() + " " + teilnehmer.getNachname());

				// Der Liste hinzufügen
				report.add(reportEntry);
			}

		}
		return report;
	}
}
