package de.hdm.itprojekt.server.db;

	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import de.hdm.itprojekt.shared.bo.Teilnehmer;

	/**
	 * <p>
	 * Mapper-Klasse zur Abbildung von <code>Teilnehmer</code> Objekten auf die Datenbank.
	 * Über das Mapping können sowohl Objekte als auch deren Attribute in die Datenbank 
	 * geschrieben werden, als auch von der Datenbank ausgelesen werden.
	 * </p>
	 * <p>
	 * Es werden Methoden zum Erstellen, Ãndern, Löschen und Ausgeben von Nutzern
	 * bereitgestellt.
	 * </p>
	 * @author Denise
	 * 
	 */

	public class TeilnehmerMapper {
		
		private static TeilnehmerMapper teilnehmerMapper = null;
		
		
		/**
		 * Privater Konstruktor verhindert das Erzeugen neuer Instanzen
		 * mittels des <code>new</code> Keywords.
		 */
		private TeilnehmerMapper() {
			
		}
		
		/**
		 * Singleton
		 * @return
		 */
		public static TeilnehmerMapper teilnehmerMapper() {
			if(teilnehmerMapper == null) {
				teilnehmerMapper = new TeilnehmerMapper();
			}
			
			return teilnehmerMapper;
		}
		
		
		/**
		 * Suche eines Nutzers anhand seiner eindeutigen ID.
		 * 
		 * @param id - Primärschlüssel von Teilnehmer
		 * @return Teilnehmer Objekt, das die gesuchte ID enthält
		 */
		public Teilnehmer findById(int id) {
			// Datenbankverbindung öffnen
			Connection con = DBConnection.connection();
			
			try {
				// Neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausführen
				ResultSet rs = stmt.executeQuery("SELECT * FROM Teilnehmer " +
						"WHERE idTeilnehmer = " + id);
				// Bei Treffer 
				if(rs.next()) {
					// Neues Teilnehmer Objekt erzeugen
					Teilnehmer t = new Teilnehmer();
					// Id, name, zusatz Email und Rolle mit den Daten aus der DB füllen
					t.setId(rs.getInt("idTeilnehmer"));
					t.setName(rs.getString("name"));
					t.setZusatz(rs.getString("zusatz"));
					t.setEmailAdresse(rs.getString("emailAddresse"));
					t.setRolle(rs.getInt("rolle"));
					// Objekt zurückgeben
					return t;
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
		 * Suche eines Teilnehmers anhand seines Vor- und Nachnamens.
		 * Da Vor- und Nachname nicht eindeutig sind, können mehrere 
		 * Ergebnisse ausgegeben werden. Alle gefundenen Nutzer werden in einem
		 * Vektor gespeichert.
		 * 
		 * @param firstName name des gesuchten Teilnehmer
		 * @param lastName zusatz des gesuchten Teilnehmer
		 * @return Vektor mit allen zu den Suchparametern gefundenen Teilnehmer
		 */
		public ArrayList<Teilnehmer> findByName(String name, String lastName) {
			// Datenbankverbindung 
			Connection con = DBConnection.connection();
			//Ergebnis-ArrayList anlegen
			ArrayList<Teilnehmer> result = new ArrayList<Teilnehmer>();
			
			try {
				// neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausfÃ¼hren
				ResultSet rs = stmt.executeQuery("SELECT * FROM Teilnehmer " +
						"WHERE name = '" + name +"'");
				// FÃ¼r jeden gefundenen Treffer...
				while (rs.next()) {
					// ... neues User Objekt anlegen
					Teilnehmer t = new Teilnehmer();
					// ... Id, Vorname, Nachname und Email mit den Daten aus der DB fÃ¼llen
					t.setId(rs.getInt("idTeilnehmer"));
					t.setName(rs.getString("name"));
					t.setZusatz(rs.getString("zusatz"));
					t.setEmailAdresse(rs.getString("emailAddresse"));
					t.setRolle(rs.getInt("rolle"));
					// ... Objekt dem Ergebnisvektor hinzufÃ¼gen
					result.add(t);
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
		 * Suche eines Teilnehmer anhand seiner einzigartigen Email-Adresse.
		 * 
		 * @param email Die Email-Adresse des gesuchten Teilnehmer
		 * @return Teilnehmer Objekt, das die gesuchte Email-Adresse enthÃ¤lt
		 */
		public Teilnehmer findByEmail(String emailAdresse) {
			// Datenbankverbindung Ã¶ffnen
			Connection con = DBConnection.connection();
			
			try {
				// neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausfÃ¼hren
				ResultSet rs = stmt.executeQuery("SELECT * FROM Teilnehmer " +
						"WHERE emailAddresse = '" + emailAdresse + "'");
				// Wenn der Eintrag gefunden wurde
				if(rs.next()) {
					// Neues User Objekt anlegen
					Teilnehmer t = new Teilnehmer();
					// Das Objekt mit Daten aus der DB füllen
					t.setId(rs.getInt("idTeilnehmer"));
					t.setName(rs.getString("name"));
					t.setZusatz(rs.getString("zusatz"));
					t.setEmailAdresse(rs.getString("emailAddresse"));
					t.setRolle(rs.getInt("rolle"));
					
					return t;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return null;
			}
		
		/**
		 * Ausgabe aller Teilnehmer DatensÃ¤tze
		 * 
		 * @return Vektor mit allen registrierten Teilnehmer
		 */
		public ArrayList<Teilnehmer> findAllTeilnehmer() {
			// Datenbankverbindung Ã¶ffnen
			Connection con = DBConnection.connection();
			//Ergebnis-ArrayList anlegen
			ArrayList<Teilnehmer> result = new ArrayList<Teilnehmer>();
			
			try {
				// neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausfÃ¼hren
				// TODO evtl. OrderBy ergÃ¤nzen
				ResultSet rs = stmt.executeQuery("SELECT * " +
						"FROM Teilnehmer");
				// FÃ¼r jeden Eintrag neues User Objekt erzeugen
				while(rs.next()) {
					Teilnehmer t = new Teilnehmer();
					t.setId(rs.getInt("idTeilnehmer"));
					t.setName(rs.getString("name"));
					t.setZusatz(rs.getString("zusatz"));
					t.setEmailAdresse(rs.getString("emailAddresse"));
					t.setRolle(rs.getInt("rolle"));
					// Teilnehmer dem Ergebnisvektor hinzufÃ¼gen
					result.add(t);
				}
			}
			// Error Handling
			catch (SQLException e) {
				e.printStackTrace();
			}
			// Ergebnisvektor zurÃ¼ckgeben
			return result;
		}
		
		/**
		 * Neuer Teilnehmer in der Datenbank anlegen.
		 * 
		 * @param t Teilnehmer Objekt, das in die Datenbank eingefügt werden soll
		 */
		public Teilnehmer insert(Teilnehmer t) {
			
			// Datenbankverbindung öffnen
			Connection con = DBConnection.connection();
			System.out.println("dbconnection: " + con);
			
			try {
				// neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausführen um die höchste id zu erhalten
				ResultSet rs = stmt.executeQuery("SELECT MAX(idTeilnehmer) AS maxId FROM Teilnehmer");
				if(rs.next()) {
					// id um 1 erhöhen, damit ein neuer Eintrag erzeugt wird
					t.setId(rs.getInt("maxId") + 1);
					// neues SQL Statement
					stmt = con.createStatement();
					// SQL Query ausführen um Datensatz in DB zu schreiben
					stmt.executeUpdate("INSERT INTO Teilnehmer (idTeilnehmer, name, zusatz, emailAdresse, rolle) " +
							"VALUES "
							+ "('" 
							+ t.getId() 
							+ "', '" 
							+ t.getName()
							+ "', '" 
							+ t.getZusatz()
							+ "', '" 
							+ t.getEmailAdresse()
							+ "', '" 
							+ t.getRolle()
							+ "')");	
					
					System.out.println("INSERT INTO Teilnehmer (idTeilnehmer, name, zusatz, emailAdresse, rolle) " +
							"VALUES "
							+ "('" 
							+ t.getId() 
							+ "', '" 
							+ t.getName()
							+ "', '" 
							+ t.getZusatz()
							+ "', '" 
							+ t.getEmailAdresse()
							+ "', '" 
							+ t.getRolle()
							+ "')");	
				}
			}
			// Error Handling
			catch (SQLException e) {
				e.printStackTrace();
			}
			return t;
		}
		
		/**
		 *Teilnehmerdaten eines bestehenden Teilnehmers in der Datenbank ändern
		 * 
		 * @param u das bereits geänderte Teilnehmerobjekt
		 */
		public Teilnehmer update(Teilnehmer t) {
			// Datenbankverbindung öffnen
			Connection con = DBConnection.connection();
			
			try {
				// neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausführen
				stmt.executeUpdate("UPDATE Teilnehmer "
						+ "SET name = '" 
						+ t.getName()
						+ "', zusatz = '" 
						+ t.getZusatz()
						+ "', rolle = '" 
						+ t.getRolle()
						+ "' WHERE idTeilnehmer = " 
						+ t.getId());
				
				
				System.out.println("UPDATE Teilnehmer "
						+ "SET name = '" 
						+ t.getName()
						+ "', zusatz = '" 
						+ t.getZusatz()
						+ "', rolle = '" 
						+ t.getRolle()
						+ "' WHERE idTeilnehmer = " 
						+ t.getId());
				
			}
			// Error Handling
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			return t;
		}
		
		/**
		 * Diese Methode löscht einen Teilnehmer in der Datenbank die dazugehörigen Nutzer-Referenzen in allen Tabellen
		 * 
		 * @param u der zu löschende Teilnehmer 
		 */
		public void delete(Teilnehmer t) {
			// Datenbankverbindung öffnen
			Connection con = DBConnection.connection();
		
			try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			stmt.executeUpdate("DELETE FROM Teilnehmer WHERE idTeilnehmer = " + t.getId());
		}
			// Error Handling
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
		
