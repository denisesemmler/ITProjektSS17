package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.itprojekt.shared.bo.Profil;
import de.hdm.itprojekt.shared.bo.Teilnehmer;

/**
 * <p>
 * Mapper-Klasse zur Abbildung von <code>Teilnehmer</code> Objekten auf die
 * Datenbank. �ber das Mapping k�nnen sowohl Objekte als auch deren Attribute in
 * die Datenbank geschrieben werden, als auch von der Datenbank ausgelesen
 * werden.
 * </p>
 * <p>
 * Es werden Methoden zum Erstellen, �ndern, L�schen und Ausgeben von Nutzern
 * bereitgestellt.
 * </p>
 * 
 * @author Denise
 * 
 */

public class TeilnehmerMapper {

	private static TeilnehmerMapper teilnehmerMapper = null;

	/**
	 * Privater Konstruktor verhindert das Erzeugen neuer Instanzen mittels des
	 * <code>new</code> Keywords.
	 */
	private TeilnehmerMapper() {

	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static TeilnehmerMapper teilnehmerMapper() {
		if (teilnehmerMapper == null) {
			teilnehmerMapper = new TeilnehmerMapper();
		}

		return teilnehmerMapper;
	}

	/**
	 * Suche eines Nutzers anhand seiner eindeutigen ID.
	 * 
	 * @param id
	 *            - Prim�rschl�ssel von Teilnehmer
	 * @return Teilnehmer Objekt, das die gesuchte ID enth�lt
	 */
	public Teilnehmer findById(int id) {
		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();

		try {
			// Neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausf�hren
			ResultSet rs = stmt.executeQuery("SELECT * FROM Teilnehmer " + "WHERE idTeilnehmer = " + id);
			// Bei Treffer
			if (rs.next()) {
				// Neues Teilnehmer Objekt erzeugen
				Teilnehmer t = new Teilnehmer();
				// Id, name, zusatz Email und Rolle mit den Daten aus der DB
				// f�llen
				t.setIdTeilnehmer(rs.getInt("idTeilnehmer"));
				t.setName(rs.getString("name"));
				t.setZusatz(rs.getString("zusatz"));
				t.setEmail(rs.getString("email"));
				t.setRolle(rs.getInt("rolle"));
				t.setOrt(rs.getString("ort"));
				t.setPlz(rs.getInt("plz"));
				t.setStrasse(rs.getString("strasse"));
				t.setProfil_idProfil(rs.getInt("profil_idProfil"));
				t.setProjektLeiter(rs.getInt("projektleiter"));

				// Objekt zur�ckgeben
				return t;
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
	 * Suche eines Teilnehmers anhand seines Vor- und Nachnamens. Da Vor- und
	 * Nachname nicht eindeutig sind, k�nnen mehrere Ergebnisse ausgegeben
	 * werden. Alle gefundenen Nutzer werden in einem Vektor gespeichert.
	 * 
	 * @param firstName
	 *            name des gesuchten Teilnehmer
	 * @param lastName
	 *            zusatz des gesuchten Teilnehmer
	 * @return Vektor mit allen zu den Suchparametern gefundenen Teilnehmer
	 */
	public ArrayList<Teilnehmer> findByName(String name, String lastName) {
		// Datenbankverbindung
		Connection con = DBConnection.connection();
		// Ergebnis-ArrayList anlegen
		ArrayList<Teilnehmer> result = new ArrayList<Teilnehmer>();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			ResultSet rs = stmt.executeQuery("SELECT * FROM Teilnehmer " + "WHERE name = '" + name + "'");
			// Für jeden gefundenen Treffer...
			while (rs.next()) {
				// ... neues User Objekt anlegen
				Teilnehmer t = new Teilnehmer();
				// ... Id, Vorname, Nachname und Email mit den Daten aus der DB
				// füllen
				t.setIdTeilnehmer(rs.getInt("idTeilnehmer"));
				t.setName(rs.getString("name"));
				t.setZusatz(rs.getString("zusatz"));
				t.setEmail(rs.getString("email"));
				t.setRolle(rs.getInt("rolle"));
				t.setOrt(rs.getString("ort"));
				t.setPlz(rs.getInt("plz"));
				t.setStrasse(rs.getString("strasse"));
				t.setProfil_idProfil(rs.getInt("profil_idProfil"));
				t.setProjektLeiter(rs.getInt("projektleiter"));
				// ... Objekt dem Ergebnisvektor hinzufügen
				result.add(t);
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
	 * Suche eines Teilnehmer anhand seiner einzigartigen Email-Adresse.
	 * 
	 * @param email
	 *            Die Email-Adresse des gesuchten Teilnehmer
	 * @return Teilnehmer Objekt, das die gesuchte Email-Adresse enthält
	 */
	public Teilnehmer findByEmail(String emailAdresse) {
		// Datenbankverbindung öffnen
		Connection con = DBConnection.connection();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Teilnehmer WHERE email = '" +emailAdresse+ "'");
			// Wenn der Eintrag gefunden wurde
			if (rs.next()) {
				// Neues User Objekt anlegen
				Teilnehmer t = new Teilnehmer();
				// Das Objekt mit Daten aus der DB f�llen
				t.setIdTeilnehmer(rs.getInt("idTeilnehmer"));
				t.setName(rs.getString("name"));
				t.setZusatz(rs.getString("zusatz"));
				t.setEmail(rs.getString("email"));
				t.setRolle(rs.getInt("rolle"));
				t.setOrt(rs.getString("ort"));
				t.setPlz(rs.getInt("plz"));
				t.setStrasse(rs.getString("strasse"));
				t.setProfil_idProfil(rs.getInt("profil_idProfil"));
				t.setProjektLeiter(rs.getInt("projektleiter"));

				return t;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Ausgabe aller Teilnehmer Datensätze
	 * 
	 * @return Vektor mit allen registrierten Teilnehmer
	 */
	public ArrayList<Teilnehmer> findAllTeilnehmer() {
		// Datenbankverbindung öffnen
		Connection con = DBConnection.connection();
		// Ergebnis-ArrayList anlegen
		ArrayList<Teilnehmer> result = new ArrayList<Teilnehmer>();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			// TODO evtl. OrderBy ergänzen
			ResultSet rs = stmt.executeQuery("SELECT * " + "FROM Teilnehmer");
			// Für jeden Eintrag neues User Objekt erzeugen
			while (rs.next()) {
				Teilnehmer t = new Teilnehmer();
				t.setIdTeilnehmer(rs.getInt("idTeilnehmer"));
				t.setName(rs.getString("name"));
				t.setZusatz(rs.getString("zusatz"));
				t.setEmail(rs.getString("email"));
				t.setRolle(rs.getInt("rolle"));
				t.setOrt(rs.getString("ort"));
				t.setPlz(rs.getInt("plz"));
				t.setStrasse(rs.getString("strasse"));
				t.setProfil_idProfil(rs.getInt("profil_idProfil"));
				t.setProjektLeiter(rs.getInt("projektleiter"));
				// Teilnehmer dem Ergebnisvektor hinzufügen
				result.add(t);
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * Neuer Teilnehmer in der Datenbank anlegen.
	 * 
	 * @param t
	 *            Teilnehmer Objekt, das in die Datenbank eingef�gt werden soll
	 */
	public Teilnehmer insert(Teilnehmer t) {

		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();
		System.out.println("dbconnection: " + con);

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausf�hren um die h�chste id zu erhalten
			ResultSet rs = stmt.executeQuery("SELECT MAX(idTeilnehmer) AS maxId FROM Teilnehmer");
			if (rs.next()) {
				// id um 1 erh�hen, damit ein neuer Eintrag erzeugt wird
				t.setId(rs.getInt("maxId") + 1);
				// neues SQL Statement
				stmt = con.createStatement();
				// SQL Query ausf�hren um Datensatz in DB zu schreiben
				stmt.executeUpdate(
						"INSERT INTO Teilnehmer (idTeilnehmer, name, zusatz, email, rolle, ort, plz, strasse, Profil_idProfil, projektleiter) "
								+ "VALUES " + "('" + t.getIdTeilnehmer() + "', '" + t.getName() + "', '" + t.getZusatz()
								+ "', '" + t.getEmail() + "', '" + t.getRolle() + "', '" + t.getOrt() + "', '"
								+ t.getPlz() + "', '" + t.getStrasse() + "', '" + t.getProfil_idProfil() + "', '"
								+ t.getProjektLeiter() + "')");

				System.out.println(
						"idTeilnehmer, name, zusatz, email, rolle, ort, plz, strasse, Profil_idProfil, projektleiter) "
								+ "VALUES " + "('" + t.getIdTeilnehmer() + "', '" + t.getName() + "', '" + t.getZusatz()
								+ "', '" + t.getEmail() + "', '" + t.getRolle() + "', '" + t.getOrt() + "', '"
								+ t.getPlz() + "', '" + t.getStrasse() + "', '" + t.getProfil_idProfil() + "', '"
								+ t.getProjektLeiter() + "')");

			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * Teilnehmerdaten eines bestehenden Teilnehmers in der Datenbank �ndern
	 * 
	 * @param u
	 *            das bereits ge�nderte Teilnehmerobjekt
	 */
	public Teilnehmer update(Teilnehmer t) {
		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausf�hren
			stmt.executeUpdate("UPDATE Teilnehmer " + "SET name = '" + t.getName() + "', zusatz = '" + t.getZusatz()
					+ "', rolle = '" + t.getRolle() + "',email = '" + t.getEmail() + "',ort = '" + t.getOrt()
					+ "', plz = '" + t.getPlz() + "',strasse = '" + t.getStrasse() + "',Profil_idProfil = '"
					+ t.getProfil_idProfil() + "', projektleiter = '" + t.getProjektLeiter() + "' WHERE idTeilnehmer = "
					+ t.getIdTeilnehmer());

			System.out.println("UPDATE Teilnehmer " + "SET name = '" + t.getName() + "', zusatz = '" + t.getZusatz()
					+ "', rolle = '" + t.getRolle() + "',email = '" + t.getEmail() + "',ort = '" + t.getOrt()
					+ "', plz = '" + t.getPlz() + "',strasse = '" + t.getStrasse() + "',Profil_idProfil = '"
					+ t.getProfil_idProfil() + "', projektleiter = '" + t.getProjektLeiter() + "' WHERE idTeilnehmer = "
					+ t.getIdTeilnehmer());

		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}

		return t;
	}

	/**
	 * Diese Methode l�scht einen Teilnehmer in der Datenbank die dazugeh�rigen
	 * Nutzer-Referenzen in allen Tabellen
	 * 
	 * @param u
	 *            der zu l�schende Teilnehmer
	 */
	public void delete(Teilnehmer t) {
		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausf�hren
			stmt.executeUpdate("DELETE FROM Teilnehmer WHERE idTeilnehmer = " + t.getId());
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Suche von Teilnehmrn anhand der ID
	public Teilnehmer findByProfil(Profil suchProfil) {
		// Datenbankverbindung öffnen
		Connection con = DBConnection.connection();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Teilnehmer " + "WHERE Profil_idProfil = " + suchProfil.getIdProfil());
			// Wenn der Eintrag gefunden wurde
			if (rs.next()) {
				// Neues User Objekt anlegen
				Teilnehmer t = new Teilnehmer();
				// Das Objekt mit Daten aus der DB f�llen
				t.setIdTeilnehmer(rs.getInt("idTeilnehmer"));
				t.setName(rs.getString("name"));
				t.setZusatz(rs.getString("zusatz"));
				t.setEmail(rs.getString("email"));
				t.setRolle(rs.getInt("rolle"));
				t.setOrt(rs.getString("ort"));
				t.setPlz(rs.getInt("plz"));
				t.setStrasse(rs.getString("strasse"));
				t.setProfil_idProfil(rs.getInt("profil_idProfil"));
				t.setProjektLeiter(rs.getInt("projektleiter"));

				return t;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
