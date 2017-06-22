package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import de.hdm.itprojekt.profil.server.db.DBConnection;
import de.hdm.itprojekt.server.db.ProfilMapper;
import de.hdm.itprojekt.shared.bo.Profil;
import de.hdm.itprojekt.shared.bo.Teilnehmer;

/**
 * <p>
 * Mapper-Klasse zur Abbildung von <code>Profil</code> Objekten auf die
 * Datenbank. Über das Mapping können sowohl Objekte als auch deren Attribute in
 * die Datenbank geschrieben werden, als auch von der Datenbank ausgelesen
 * werden.
 * </p>
 * <p>
 * Es werden Methoden zum Erstellen, Ãndern, Löschen und Ausgeben von Profilen
 * bereitgestellt.
 * </p>
 * 
 * @author Denise
 **/

public class ProfilMapper {

	private static ProfilMapper profilMapper = null;

	/**
	 * Privater Konstruktor verhindert das Erzeugen neuer Instanzen mittels des
	 * <code>new</code> Keywords.
	 */
	private ProfilMapper() {
	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static ProfilMapper profilMapper() {
		if (profilMapper == null) {
			profilMapper = new ProfilMapper();
		}

		return profilMapper;
	}

	/**
	 * Suche ein Profil anhand seiner einzigartigen ID.
	 * 
	 * @param id
	 *            - PrimÃ¤rschlÃ¼ssel von Profil
	 * @return Profil Objekt, das die gesuchte ID enthält
	 * 
	 * */
	public Profil insert(Profil p) {

		// Datenbankverbindung öffnen
		Connection con = DBConnection.connection();
		System.out.println("dbconnection: " + con);

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen um die höchste id zu erhalten
			ResultSet rs = stmt.executeQuery("SELECT MAX(idProfil) AS maxId FROM Profil");
			if (rs.next()) {
				// id um 1 erhöhen, damit ein neuer Eintrag erzeugt wird
				p.setId(rs.getInt("maxId") + 1);
				// neues SQL Statement
				stmt = con.createStatement();
				// SQL Query ausführen um Datensatz in DB zu schreiben
				stmt.executeUpdate(
						"INSERT INTO Profil (idProfil, erstelldatum, Teilnehmer_idTeilnehmer) "
								+ "VALUES " + "('" + p.getId() + "', '"  + p.getErstellDatum() + "', '" + p.getTeilnehmer_idTeilnehmer() + "')");

				System.out.println(
						"idProfil, erstelldatum, Teilnehmer_idTeilnehmer) "
								+ "VALUES " + "('" + p.getId() + "', '"  + p.getErstellDatum() + "', '" + p.getTeilnehmer_idTeilnehmer() + "')");

			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	public Profil findById(int id) {
		// Datenbankverbindung öffnen
		Connection con = DBConnection.connection();

		try {
			// Neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausfÃ¼hren
			ResultSet rs = stmt.executeQuery("SELECT idProfil, erstelldatum, erstelldatum, aenderungsdatum FROM Profil "
					+ "WHERE idProfil = " + id);
			// Bei Treffer
			if (rs.next()) {
				// Neues Source Objekt erzeugen
				Profil p = new Profil();
				// Id und Source mit den Daten aus der DB fÃ¼llen
				p.setId(rs.getInt("idProfil"));
				p.setErstellDatum(rs.getTimestamp("erstelldatum"));
				p.setAenderungsDatum(rs.getTimestamp("aenderungsdatum"));

				// p.setidTeilnehmer(rs.getInt("idTeilnehmer"));

				// Objekt zurÃ¼ckgeben
				return p;
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		// Falls nichts gefunden wurde null zurückgeben
		return null;
	}
	
	public Profil findByTeilnehmerId(int id) {
		// Datenbankverbindung öffnen
		Connection con = DBConnection.connection();

		try {
			// Neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausfÃ¼hren
			ResultSet rs = stmt.executeQuery("SELECT * FROM Profil WHERE Teilnehmer_idTeilnehmer = " + id);
			// Bei Treffer
			if (rs.next()) {
				// Neues Source Objekt erzeugen
				Profil p = new Profil();
				// Id und Source mit den Daten aus der DB fÃ¼llen
				p.setId(rs.getInt("idProfil"));
				p.setErstellDatum(rs.getDate("erstelldatum"));
				p.setAenderungsDatum(rs.getDate("aenderungsdatum"));

				

				// Objekt zurÃ¼ckgeben
				return p;
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public int findByTeilnehmerIdINT(int id) {
		// Datenbankverbindung öffnen
		Connection con = DBConnection.connection();

		try {
			// Neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausfÃ¼hren
			ResultSet rs = stmt.executeQuery("SELECT idProfil, erstelldatum, erstelldatum, aenderungsdatum FROM Profil "
					+ "WHERE idTeilnehmer_Teilnehmer = " + id);
			// Bei Treffer
			if (rs.next()) {
				// Neues Source Objekt erzeugen
				int p;
				// Id und Source mit den Daten aus der DB fÃ¼llen
				p = rs.getInt("idProfil");				

				// Objekt zurÃ¼ckgeben
				return p;
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		// Falls nichts gefunden wurde null zurückgeben
		return 0;
	}


	public void delete(Profil p) {
		// Datenbankverbindung öffnen
		Connection con = DBConnection.connection();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			stmt.executeUpdate("DELETE FROM Profil WHERE idProfil = " + p.getId());
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
