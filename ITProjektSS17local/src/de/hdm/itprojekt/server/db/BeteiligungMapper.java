/**
 * 
 */
package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.itprojekt.shared.bo.Beteiligung;

/**
 * p>
 * Mapper-Klasse zur Abbildung von <code>Beteiligung</code> Objekten auf die Datenbank.
 * �ber das Mapping k�nnen sowohl Objekte als auch deren Attribute in die Datenbank 
 * geschrieben werden, als auch von der Datenbank ausgelesen werden.
 * </p>
 * <p>
 * Es werden Methoden zum Erstellen, �ndern, L�schen und Ausgeben von Beteiligungen * bereitgestellt.
 * </p>
 * @author Denise
 *
 */
public class BeteiligungMapper {
	
	private static BeteiligungMapper beteiligungMapper = null;
	
	
	/**
	 * Privater Konstruktor verhindert das Erzeugen neuer Instanzen
	 * mittels des <code>new</code> Keywords.
	 */
	private BeteiligungMapper() {
		
	}
	
	/**
	 * Singleton
	 * @return
	 */
	public static BeteiligungMapper beteiligungMapper() {
		if(beteiligungMapper == null) {
			beteiligungMapper = new BeteiligungMapper();
		}
		
		return beteiligungMapper;
	}
	
	
	/**
	 * Suche einer Beteiligung anhand seiner einzigartigen ID.
	 * 
	 * @param id - Prim�rschl�ssel von Beteiligung
	 * @return Beteiligung Objekt, das die gesuchte ID enth�lt
	 */
	public Beteiligung findById(int id) {
		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();
		
		try {
			// Neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausf�hren
			ResultSet rs = stmt.executeQuery("SELECT * FROM Beteiligung " +
					"WHERE idBeteiligung = " + id);
			// Bei Treffer 
			if(rs.next()) {
				// Neues Beteiligung Objekt erzeugen
				Beteiligung b = new Beteiligung();
				// Id, stellungname mit den Daten aus der DB f�llen
				b.setIdBeteiligung(rs.getInt("idBeteiligung"));
				b.setStellungnahme(rs.getString("stellungnahme"));
				b.setProjektID(rs.getInt("Projekt_idProjekt"));
				b.setBewerbungID(rs.getInt("Bewerbung_idBewerbung"));
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
	 * Suche Beteiligungen anhand eines Projektnamens.
	 * Da Projekt mehrere Beteiligungen enthalten k�nnen, k�nnen mehrere 
	 * Ergebnisse ausgegeben werden. Alle gefundenen Beteilungen werden in einem
	 * Vektor gespeichert.
	 * 
	 * @param projektname name des gesuchten Projektes
	 * @return Vektor mit allen zu den Suchparametern gefundenen Beteiligungen
	 */
	public ArrayList<Beteiligung> findByName(String projektName) {
		// Datenbankverbindung 
		Connection con = DBConnection.connection();
		//Ergebnis-ArrayList anlegen
		ArrayList<Beteiligung> result = new ArrayList<Beteiligung>();
		
		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			ResultSet rs = stmt.executeQuery("SELECT * FROM Beteiligung " +
					"WHERE name = '" + projektName +"'");
			// Für jeden gefundenen Treffer...
			while (rs.next()) {
				// ... neues User Objekt anlegen
				Beteiligung b = new Beteiligung();
				// ... Id, Projetname, Bewertung und Beschreibung mit den Daten aus der DB f�llen
				b.setIdBeteiligung(rs.getInt("idBeteiligung"));
				b.setProjektID(rs.getInt("Projekt_idProjekt"));
				b.setBewerbungID(rs.getInt("Bewerbung_idBewerbung"));
				b.setStellungnahme(rs.getString("stellungnahme"));
				// ... Objekt dem Ergebnisvektor hinzufügen
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
	 * Ausgabe aller Beteiligung Datens�tze
	 * 
	 * @return Vektor mit allen Beteiligung
	 */
	public ArrayList<Beteiligung> findAllBeteiligung() {
		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();
		//Ergebnis-ArrayList anlegen
		ArrayList<Beteiligung> result = new ArrayList<Beteiligung>();
		
		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			// TODO evtl. OrderBy ergänzen
			ResultSet rs = stmt.executeQuery("SELECT * " +
					"FROM Beteiligung");
			// Für jeden Eintrag neues User Objekt erzeugen
			while(rs.next()) {
				Beteiligung b = new Beteiligung();
				b.setIdBeteiligung(rs.getInt("idBeteiligung"));
				b.setStellungnahme(rs.getString("stellungnahme"));
				b.setProjektID(rs.getInt("Projekt_idProjekt"));
				b.setBewerbungID(rs.getInt("Bewerbung_idBewerbung"));
				// Teilnehmer dem Ergebnisvektor hinzufügen
				result.add(b);
			}
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		// Ergebnisvektor zur�ckgeben
		return result;
	}
	
	/**
	 * Neue Beteiligung in der Datenbank anlegen.
	 * 
	 * @param b Beteiligung Objekt, das in die Datenbank eingef�gt werden soll
	 */
	public Beteiligung insert(Beteiligung b) {
		
		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();
		System.out.println("dbconnection: " + con);
		
		try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausf�hren um die h�chste id zu erhalten
			ResultSet rs = stmt.executeQuery("SELECT MAX(idBeteiligung) AS maxId FROM Beteiligung");
			if(rs.next()) {
				// id um 1 erh�hen, damit ein neuer Eintrag erzeugt wird
				b.setIdBeteiligung(rs.getInt("maxId") + 1);
				// neues SQL Statement
				stmt = con.createStatement();
				// SQL Query ausf�hren um Datensatz in DB zu schreiben
				stmt.executeUpdate("INSERT INTO Beteiligung (idBeteiligung, bewertung, stellungnahme, ausschreibender, bewerbender) " +
						"VALUES "
						+ "('" 
						+ b.getIdBeteiligung() 
						+ "', '" 
						+ b.getBewerbungID()
						+ "', '" 
						+ b.getStellungnahme()						
						+ "', '" 
						+ b.getProjektID()
						+ "')");	
				
				System.out.println("INSERT INTO Beteiligung (idBeteiligung, bewertung, stellungnahme, ausschreibender, bewerbender) " +
						"VALUES "
						+ "('" 
						+ b.getIdBeteiligung() 
						+ "', '" 
						+ b.getBewerbungID()
						+ "', '" 
						+ b.getStellungnahme()						
						+ "', '"
						+ b.getProjektID()
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
	 * Diese Methode l�scht einen Beteiligung in der Datenbank die dazugeh�rigen Beteiligung-Referenzen in allen Tabellen
	 * 
	 * @param u die zu l�schende Beteiligung 
	 */
	public void delete(Beteiligung b) {
		// Datenbankverbindung �ffnen
		Connection con = DBConnection.connection();
	
		try {
		// neues SQL Statement anlegen
		Statement stmt = con.createStatement();
		// SQL Query ausf�hren
		stmt.executeUpdate("DELETE FROM Beteiligung WHERE idBeteiligung = " + b.getIdBeteiligung());
	}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
