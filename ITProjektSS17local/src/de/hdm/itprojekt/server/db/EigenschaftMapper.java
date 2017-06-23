package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Profil;

/**
 * p> Mapper-Klasse zur Abbildung von <code>Eigenschaft</code> Objekten auf die
 * Datenbank. Ueber das Mapping koennen sowohl Objekte als auch deren Attribute
 * in die Datenbank geschrieben werden, als auch von der Datenbank ausgelesen
 * werden.
 * </p>
 * <p>
 * Es werden Methoden zum Erstellen, aendern, Loeschen und Ausgeben von
 * Eigenschaften * bereitgestellt.
 * </p>
 * 
 * @author Marko
 *
 */

public class EigenschaftMapper {

	/**
	 * Die statische Variable eigenschaftMapper stellt sicher, dass es von der
	 * Klasse EigenschaftMapper nur eine einzige Instanz gibt bzw. die Variable
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 * @author Marko
	 */

	private static EigenschaftMapper eigenschaftMapper = null;

	/**
	 * Der private Konstruktor verhindert, dass eine Instanz der Klasse
	 * EigenschaftMapper per <code>new</code> erzeugt werden kann.
	 */

	private EigenschaftMapper() {
	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static EigenschaftMapper eigenschaftMapper() {
		if (eigenschaftMapper == null) {
			eigenschaftMapper = new EigenschaftMapper();
		}

		return eigenschaftMapper;
	}

	/**
	 * Methode die eine Eigenschaft einfÃ¼gt
	 * 
	 * @param Eigenschaft
	 *            e
	 */

	public Eigenschaft insert(Eigenschaft e) {

		// Connection zur DB auf
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Bisher grÃ¶ÃŸte EigenschaftsID bestimmen
			ResultSet rs = stmt.executeQuery("SELECT MAX(idEigenschaft) AS maxid " + "FROM Eigenschaft");

			if (rs.next()) {
				// Abgefragte EigenschaftsID++
				e.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// In DB einfuegen
				stmt.executeUpdate("INSERT INTO Eigenschaft (idEigenschaft, name, wert, Profil_idProfil)" + "VALUES ('" + e.getId() + "','"
						+ e.getName() + "','" + e.getWert() + "','" + e.getProfil_idProfil() + "')");

			}
		}

		catch (SQLException s) {
			s.printStackTrace();
		}

		return e;
	}

	public Eigenschaft update(Eigenschaft e) {

		// Connection zur DB auf
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE Eigenschaft SET name='" + e.getName() + "'wert='" + e.getWert() + "'WHERE id='"
					+ e.getId() + "'");

		}

		catch (SQLException s) {
			s.printStackTrace();
		}

		return e;
	}

	
	//Eigenschaften werden anhand ihrer einzigartigen ID gesucht
	public Vector<Eigenschaft> findByProfil(Profil profilId) {

		// Datenbankverbindung öffnen
		Connection con = DBConnection.connection();
		Vector<Eigenschaft> result = new Vector<Eigenschaft>();
		try {
			// Neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Eigenschaft " + "WHERE Profil_idProfil = " + profilId.getIdProfil());
			// Bei Treffer
			while (rs.next()) {
				// Neues Eigenschaft Objekt erzeugen
				Eigenschaft e = new Eigenschaft();
				e.setId(rs.getInt("idEigenschaft"));
				e.setName(rs.getString("name"));
				e.setWert(rs.getString("wert"));
				result.add(e);
				
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return result;

	}

	public void delete(Eigenschaft e) {
		// Datenbankverbindung öffnen
		Connection con = DBConnection.connection();

		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			stmt.executeUpdate("DELETE FROM Eigenschaft WHERE idEigenschaft = " + e.getId());
		}
		// Error Handling
		catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

}
