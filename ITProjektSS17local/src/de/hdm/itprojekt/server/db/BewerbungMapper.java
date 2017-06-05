/**
 * 
 */
package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.itprojekt.shared.bo.Bewerbung;
/**
 * 
	/**
	 * <p>
	 * Mapper-Klasse zur Abbildung von <code>Bewerbung</code> Objekten auf die Datenbank.
	 * Über das Mapping können sowohl Objekte als auch deren Attribute in die Datenbank 
	 * geschrieben werden, als auch von der Datenbank ausgelesen werden.
	 * </p>
	 * <p>
	 * Es werden Methoden zum Erstellen, Ãndern, Löschen und Ausgeben von Bewerbungen
	 * bereitgestellt.
	 * 
 * @author denisesemmler
 *
 */
public class BewerbungMapper {
	private static BewerbungMapper bewerbungMapper = null;
	
	
	/**
	 * Privater Konstruktor verhindert das Erzeugen neuer Instanzen
	 * mittels des <code>new</code> Keywords.
	 */
	private BewerbungMapper() {
		
	}
	
	/**
	 * Singleton
	 * @return
	 */
	public static BewerbungMapper bewerbungMapper() {
		if(bewerbungMapper == null) {
			bewerbungMapper = new BewerbungMapper();
		}
		
		return bewerbungMapper;
	}
	
	
	/**
	 * Suche einer Bewerbung anhand seiner eindeutigen ID.
	 * 
	 * @param id - Primärschlüssel von Bewerbung
	 * @return Bewerbungs Objekt, das die gesuchte ID enthält
	 */
	public Bewerbung findById(int id) {
		// Datenbankverbindung öffnen
		Connection con = DBConnection.connection();
		
		try {
			// Neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			ResultSet rs = stmt.executeQuery("SELECT * FROM Bewerbung " +
					"WHERE idBewerbung = " + id);
			// Bei Treffer 
			if(rs.next()) {
				// Neues Bewerbung Objekt erzeugen
				Bewerbung b = new Bewerbung();
				// Id, name, zusatz Email und Rolle mit den Daten aus der DB füllen
				b.setId(rs.getInt("idBewerbung"));
				b.setBewerbungsText(rs.getString("bewerbungstext"));
				b.setErstellDatum(rs.getTimestamp("erstellDatum"));
				b.setAusschreibungID(rs.getInt("Ausschreibung_idAusschreibung"));
				b.setStatus(rs.getString("status"));
				b.setBewertung(rs.getFloat("bewertung"));
				b.setIdProfil(rs.getInt("Profil_idProfil"));
				// Objekt zurückgeben
				return b;
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
	
	
	
	/**
	 * Suche alle Bewerbungen einer Auschreibung anhand der AusschreibungsID. 
	 * Voraussetzung: 
	 * eine Ausschreibung ist immer einem Teilnehmer zugeordnet!!
	 *
	 * Es können mehrere Ergebnisse ausgegeben werden. Alle gefundenen Bewerbungen werden in einem
	 * Vektor gespeichert.
	 * 
	 * @param 
	 * @param lastName zusatz des gesuchten Teilnehmer
	 * @return Vektor mit allen zu den Suchparametern gefundenen Teilnehmer
	 */
	public ArrayList<Bewerbung> findByAusschreibungsId(int ausschreibungsID) {
		// Datenbankverbindung 
		Connection con = DBConnection.connection();
		//Ergebnis-ArrayList anlegen
		ArrayList<Bewerbung> result = new ArrayList<Bewerbung>();
		
		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			ResultSet rs = stmt.executeQuery("SELECT * FROM Bewerbung " +
					"WHERE AusschreibungID = '" + ausschreibungsID +"'");
			// Für jeden gefundenen Treffer...
			while (rs.next()) {
				// ... neues Bewerbung Objekt anlegen
				Bewerbung b = new Bewerbung();
				// Id, bewerbungstext, und Erstelldatum mit den Daten aus der DB füllen
				b.setId(rs.getInt("idBewerbung"));
				b.setBewerbungsText(rs.getString("bewerbungstext"));
				b.setErstellDatum(rs.getTimestamp("erstellDatum"));
				// ... Objekt dem Ergebnisvektor hinzufügen
				result.add(b);
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		// Ergebnis zurÃ¼ckgeben
		return result;
	}

	
	/**
	 * Ausgabe aller Teilnehmer Datensätze
	 * 
	 * Suche alle Bewerbungen anhand der Teilnehmer ID 
	 * 
	 * @return Vektor mit allen registrierten Teilnehmer
	 */
	public ArrayList<Bewerbung> findByTeilnehmerId(int teilnehmerId) {
		// Datenbankverbindung 
		Connection con = DBConnection.connection();
		//Ergebnis-ArrayList anlegen
		ArrayList<Bewerbung> result = new ArrayList<Bewerbung>();
		
		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			ResultSet rs = stmt.executeQuery("SELECT * FROM Bewerbung " +
					"WHERE teilnehmerId = '" + teilnehmerId+ "'");
			// Für jeden gefundenen Treffer...
			while (rs.next()) {
				// ... neues Bewerbung Objekt anlegen
				Bewerbung b = new Bewerbung();
				// Id, bewerbungstext, und Erstelldatum mit den Daten aus der DB füllen
				b.setId(rs.getInt("idBewerbung"));
				b.setBewerbungsText(rs.getString("bewerbungstext"));
				b.setErstellDatum(rs.getTimestamp("erstellDatum"));
				// ... Objekt dem Ergebnisvektor hinzufügen
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
	 * Neuer Bewerbung in der Datenbank anlegen.
	 * 
	 * @param b Bewerbung Objekt, das in die Datenbank eingefügt werden soll
	 */
	public Bewerbung insert(Bewerbung b) {
		
		// Datenbankverbindung öffnen
		Connection con = DBConnection.connection();
		System.out.println("dbconnection: " + con);
		
		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen um die höchste id zu erhalten
			ResultSet rs = stmt.executeQuery("SELECT MAX(idBewerbung) AS maxId FROM Bewerbung");
			if(rs.next()) {
				// id um 1 erhöhen, damit ein neuer Eintrag erzeugt wird
				b.setId(rs.getInt("maxId") + 1);
				// neues SQL Statement
				stmt = con.createStatement();
				// SQL Query ausführen um Datensatz in DB zu schreiben
				stmt.executeUpdate("INSERT INTO Bewerbung (idBewerbung, bewerbungstext, erstelldatum, idTeilnehmer, AusschreibungsID) " +
						"VALUES "
						+ "('" 
						+ b.getId()
						+ "', '" 
						+ b.getBewerbungsText()
						+ "', '" 
						+ b.getErstellDatum()
						+ "', '" 
						+ b.getIdBewerbung()
						+ "', '" 
						+ b.getBewertung()
						+ "', '" 
						+ b.getStatus()
						+ "')");	
				
				System.out.println("INSERT INTO Bewerbung (idBewerbung, bewerbungstext, erstelldatum, idTeilnehmer, AusschreibungsID) " +
						"VALUES "
						+ "('" 
						+ b.getId()
						+ "', '" 
						+ b.getBewerbungsText()
						+ "', '" 
						+ b.getErstellDatum()
						+ "', '" 
						+ b.getIdBewerbung()
						+ "', '" 
						+ b.getBewertung()
						+ "', '" 
						+ b.getStatus()
						+ "')");	
					
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 *Bewerbungsdaten einer bestehenden Bewerbung in der Datenbank ändern
	 * 
	 * @param u das bereits geänderte Bewerbungobjekt
	 */
	public Bewerbung update(Bewerbung b) {
		// Datenbankverbindung öffnen
		Connection con = DBConnection.connection();
		
		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			stmt.executeUpdate("UPDATE Bewerbung "
					+ "SET bewerbungstext = '" 
					+ b.getBewerbungsText()
	
					+ "' WHERE idBewerbung = " 
					+ b.getId());
			
			
			System.out.println("UPDATE Bewerbung "
					+ "SET bewerbungstext = '" 
					+ b.getBewerbungsText()
					+ "' WHERE idBewerbung = " 
					+ b.getId());
			
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * Diese Methode löscht einer Bewerbung in der Datenbank die dazugehörigen Teilnehmer-Referenzen in allen Tabellen
	 * 
	 * @param u der zu löschende Bewerbung
	 */
	public void delete(Bewerbung b) {
		// Datenbankverbindung öffnen
		Connection con = DBConnection.connection();
	
		try {
		// neues SQL Statement anlegen
		Statement stmt = con.createStatement();
		// SQL Query ausführen
		stmt.executeUpdate("DELETE FROM Bewerbung WHERE idBewerbung = " + b.getId());
	}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

}
