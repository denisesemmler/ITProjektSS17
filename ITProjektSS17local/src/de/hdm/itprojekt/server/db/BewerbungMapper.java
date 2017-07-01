/**
 * 
 */
package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itprojekt.server.ServersideSettings;
import de.hdm.itprojekt.shared.bo.Bewerbung;

/**
 * 
 * /**
 * <p>
 * Mapper-Klasse zur Abbildung von <code>Bewerbung</code> Objekten auf die
 * Datenbank. �ber das Mapping k�nnen sowohl Objekte als auch deren Attribute in
 * die Datenbank geschrieben werden, als auch von der Datenbank ausgelesen
 * werden.
 * </p>
 * <p>
 * Es werden Methoden zum Erstellen, �ndern, L�schen und Ausgeben von
 * Bewerbungen bereitgestellt.
 * 
 * @author denisesemmler
 *
 */
public class BewerbungMapper {
	private static BewerbungMapper bewerbungMapper = null;

	/**
	 * Privater Konstruktor verhindert das Erzeugen neuer Instanzen mittels des
	 * <code>new</code> Keywords.
	 */
	private BewerbungMapper() {

	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static BewerbungMapper bewerbungMapper() {
		if (bewerbungMapper == null) {
			bewerbungMapper = new BewerbungMapper();
		}

		return bewerbungMapper;
	}

	/**
	 * Suche einer Bewerbung anhand seiner eindeutigen ID.
	 * 
	 * @param id
	 *            - Prim�rschl�ssel von Bewerbung
	 * @return Bewerbungs Objekt, das die gesuchte ID enth�lt
	 */
	public Bewerbung findById(int id) {
		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();

		try {
			// Neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausf�hren
			ResultSet rs = stmt.executeQuery("SELECT * FROM Bewerbung " + "WHERE idBewerbung = " + id);
			// Bei Treffer
			if (rs.next()) {
				// Neues Bewerbung Objekt erzeugen
				Bewerbung b = new Bewerbung();
				// Id, name, zusatz Email und Rolle mit den Daten aus der DB
				// f�llen
				b.setId(rs.getInt("idBewerbung"));
				b.setBewerbungsText(rs.getString("bewerbungstext"));
				b.setErstellDatum(rs.getTimestamp("erstellDatum"));
				b.setAusschreibungID(rs.getInt("Ausschreibung_idAusschreibung"));
				b.setStatus(rs.getString("status"));
				b.setBewertung(rs.getFloat("bewertung"));
				b.setIdProfil(rs.getInt("Profil_idProfil"));
				b.setTitel(rs.getString("titel"));
				// Objekt zur�ckgeben
				return b;
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		// Falls nichts gefunden wurde null zur�ckgeben
		return null;
	}

	/**
	 * Suche alle Bewerbungen einer Ausschreibung anhand der AusschreibungsID.
	 * Voraussetzung: eine Ausschreibung ist immer einem Teilnehmer zugeordnet!!
	 *
	 * Es k�nnen mehrere Ergebnisse ausgegeben werden. Alle gefundenen
	 * Bewerbungen werden in einem Vektor gespeichert.
	 * 
	 * @param
	 * @param lastName
	 *            zusatz des gesuchten Teilnehmer
	 * @return Vektor mit allen zu den Suchparametern gefundenen Teilnehmer
	 */
	public Vector<Bewerbung> findByAusschreibungsId(int ausschreibungsID) {
		// Datenbankverbindung
		Connection con = DBConnection.connection();
		// Ergebnis-ArrayList anlegen
		Vector<Bewerbung> result = new Vector<Bewerbung>();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausf�hren
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM Bewerbung " + "WHERE Ausschreibung_idAusschreibung = '" + ausschreibungsID + "'");
			// F�r jeden gefundenen Treffer...
			while (rs.next()) {
				// ... neues Bewerbung Objekt anlegen
				Bewerbung b = new Bewerbung();
				// Id, bewerbungstext, und Erstelldatum mit den Daten aus der DB
				// f�llen
				b.setId(rs.getInt("idBewerbung"));
				b.setBewerbungsText(rs.getString("bewerbungstext"));
				b.setErstellDatum(rs.getTimestamp("erstellDatum"));
				b.setAusschreibungID(rs.getInt("Ausschreibung_idAusschreibung"));
				b.setStatus(rs.getString("status"));
				b.setBewertung(rs.getFloat("bewertung"));
				b.setIdProfil(rs.getInt("Profil_idProfil"));
				b.setTitel(rs.getString("titel"));
				result.add(b);
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		// Ergebnis zurückgeben
		return result;
	}

	/**
	 * Ausgabe aller Teilnehmer Datens�tze
	 * 
	 * Suche alle Bewerbungen anhand der Teilnehmer ID
	 * 
	 * @return Vektor mit allen registrierten Teilnehmer
	 */
	public Vector<Bewerbung> findBewerbungByTeilnehmerId(int profilId) {
		// Datenbankverbindung
		Connection con = DBConnection.connection();
		// Ergebnis-Vector anlegen
		Vector<Bewerbung> result = new Vector<Bewerbung>();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();

			// SQL Query ausf�hren
			ResultSet rs = stmt.executeQuery("SELECT * FROM Bewerbung " + "WHERE Profil_idProfil = '" + profilId + "'");

			System.out.println("SELECT * FROM Bewerbung " + "WHERE Profil_idProfil = '" + profilId + "'");

			// F�r jeden gefundenen Treffer...
			while (rs.next()) {

				// ... neues Bewerbung Objekt anlegen
				Bewerbung b = new Bewerbung();

				// Id, bewerbungstext, und Erstelldatum mit den Daten aus der DB
				// f�llen
				b.setId(rs.getInt("idBewerbung"));
				b.setBewerbungsText(rs.getString("bewerbungstext"));
				b.setErstellDatum(rs.getTimestamp("erstellDatum"));
				b.setAusschreibungID(rs.getInt("Ausschreibung_idAusschreibung"));
				b.setStatus(rs.getString("status"));
				b.setBewertung(rs.getFloat("bewertung"));
				b.setIdProfil(rs.getInt("Profil_idProfil"));
				b.setTitel(rs.getString("titel"));
				// ... Objekt dem Ergebnisvektor hinzuf�gen
				result.add(b);
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		// Ergebnis zur�ckgeben
		return result;
	}

	/**
	 * Neuer Bewerbung in der Datenbank anlegen.
	 * 
	 * @param b
	 *            Bewerbung Objekt, das in die Datenbank eingef�gt werden soll
	 */
	public Bewerbung insert(Bewerbung b) {

		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();
		System.out.println("dbconnection: " + con);

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausf�hren um die h�chste id zu erhalten
			ResultSet rs = stmt.executeQuery("SELECT MAX(idBewerbung) AS maxId FROM Bewerbung");
			if (rs.next()) {
				// id um 1 erh�hen, damit ein neuer Eintrag erzeugt wird
				b.setId(rs.getInt("maxId") + 1);
				// neues SQL Statement
				stmt = con.createStatement();

				String sql = "INSERT INTO Bewerbung (idBewerbung, bewerbungstext, erstelldatum, bewertung, Profil_idProfil, Ausschreibung_idAusschreibung, status, titel) "
						+ "VALUES " + "('" + b.getId() + "', '" + b.getBewerbungsText() + "', '" + b.getErstellDatum()
						+ "', '" + b.getBewertung() + "', '" + b.getIdProfil() + "', '" + b.getAusschreibungID()
						+ "', '" + b.getStatus() + "', '" + b.getTitel() + "')";
				// SQL Query ausf�hren um Datensatz in DB zu schreiben
				stmt.executeUpdate(sql);

				ServersideSettings.getLogger().info(sql);

			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * Bewerbungsdaten einer bestehenden Bewerbung in der Datenbank �ndern
	 * 
	 * @param u
	 *            das bereits ge�nderte Bewerbungobjekt
	 */
	public Bewerbung update(Bewerbung b) {
		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();

			// SQL Zusammenbauen mit der StringBuilder Klasse. Einzelnen Strings
			// Werden mit Append konkatiniert.
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE Bewerbung SET idBewerbung = ");
			sb.append(b.getId());
			sb.append(" , ");
			sb.append(" bewerbungstext= '");
			sb.append(b.getBewerbungsText());
			sb.append("' , ");
			sb.append("erstelldatum = '");
			sb.append(b.getErstellDatum());
			sb.append("' , ");
			sb.append("bewertung = ");
			sb.append(b.getBewertung());
			sb.append(" , ");
			sb.append("Profil_idProfil = ");
			sb.append(b.getIdProfil());
			sb.append(" , ");
			sb.append("Ausschreibung_idAusschreibung = ");
			sb.append(b.getAusschreibungID());
			sb.append(" , ");
			sb.append("status = '");
			sb.append(b.getStatus());
			sb.append("' , ");
			sb.append("titel = '");
			sb.append(b.getTitel());
			sb.append("' ");
			sb.append("WHERE idBewerbung =");
			sb.append(b.getId());

			ServersideSettings.getLogger().info(sb.toString());
			// SQL Query ausfuehren
			stmt.executeUpdate(sb.toString());

		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}

		return b;
	}

	/**
	 * Diese Methode l�scht einer Bewerbung in der Datenbank die dazugeh�rigen
	 * Teilnehmer-Referenzen in allen Tabellen
	 * 
	 * @param u
	 *            der zu l�schende Bewerbung
	 */
	public void delete(Bewerbung b) {
		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();

			String sql = "DELETE FROM Bewerbung WHERE idBewerbung = " + b.getId();

			// SQL Query ausf�hren
			stmt.executeUpdate(sql);

			ServersideSettings.getLogger().info(sql);
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Bewerbung findByProfilIdAndAusschreibungsId(int profilId, int ausschreibungID) {
		// Datenbankverbindung
		Connection con = DBConnection.connection();
		// Ergebnis-Vector anlegen
		Bewerbung result = new Bewerbung();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();

			// SQL Query ausf�hren
			ResultSet rs = stmt.executeQuery("SELECT * FROM Bewerbung " + "WHERE Profil_idProfil = '" + profilId + "'"
					+ "AND Ausschreibung_idAusschreibung = '" + ausschreibungID + "'");

			// F�r jeden gefundenen Treffer...
			if (rs.next()) {

				// Id, bewerbungstext, und Erstelldatum mit den Daten aus der DB
				// f�llen
				result.setId(rs.getInt("idBewerbung"));
				result.setBewerbungsText(rs.getString("bewerbungstext"));
				result.setErstellDatum(rs.getTimestamp("erstellDatum"));
				// ... Objekt dem Ergebnisvektor hinzuf�gen

			} else {
				result = null;
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		// Ergebnis zur�ckgeben
		return result;
	}

}
