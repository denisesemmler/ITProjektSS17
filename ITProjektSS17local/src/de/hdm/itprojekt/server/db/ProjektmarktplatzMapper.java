package de.hdm.itprojekt.server.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import de.hdm.itprojekt.shared.bo.Projektmarktplatz;


public class ProjektmarktplatzMapper {
	



	
	/**
	 * <p>
	 * Mapper-Klasse zur Abbildung von <code>Projektmarktplätzen</code> Objekten auf die Datenbank.
	 * Über das Mapping können sowohl Objekte als auch deren Attribute in die Datenbank 
	 * geschrieben werden, als auch von der Datenbank ausgelesen werden.
	 * </p>
	 * <p>
	 * Es werden Methoden zum Erstellen, Ãndern, Löschen und Ausgeben von Nutzern
	 * bereitgestellt.
	 * </p>
	 * @author Philipp
	 */	
		private static ProjektmarktplatzMapper projektmarktplatzMapper = null;
		
		
		/**
		 * Privater Konstruktor verhindert das Erzeugen neuer Instanzen
		 * mittels des <code>new</code> Keywords.
		 */
		private ProjektmarktplatzMapper() {
			
		}
		
		/**
		 * Singleton
		 * @return
		 */
		public static ProjektmarktplatzMapper projektmarktplatzMapper() {
			if(projektmarktplatzMapper == null) {
				projektmarktplatzMapper = new ProjektmarktplatzMapper();
			}
			
			return projektmarktplatzMapper;
		}
		
		
		/**
		 * Suche eines Projektmarktplatzes anhand seiner einzigartigen ID.
		 * 
		 * @param id - Primärschlüssel von Projektmarktplatz
		 * @return Projektmarktplatz Objekt, das die gesuchte ID enthält
		 */
		public Projektmarktplatz findById(int id) {
			// Datenbankverbindung öffnen
			Connection con = DBConnection.connection();
			
			try {
				// Neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausführen
				ResultSet rs = stmt.executeQuery("SELECT * FROM Projektmarktplatz " +
						"WHERE idProjektmarktplatz = " + id);
				// Bei Treffer 
				if(rs.next()) {
					// Neues Projektmarktplatz Objekt erzeugen
					Projektmarktplatz pm = new Projektmarktplatz();
					// Id und Bezeichnung mit den Daten aus der DB füllen
					pm.setId(rs.getInt("idProjektmarktplatz"));
					pm.setBezeichnung(rs.getString("bezeichnung"));
				
					// Objekt zurückgeben
					return pm;
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
		 * Suche eines Projektmarktplatz anhand seiner Bezeichnung.
		 * Da die Bezeichnung eindeutig ist, können mehrere 
		 * Ergebnisse ausgegeben werden. Alle gefundenen Projektmarktplätze werden in einem
		 * Vektor gespeichert.
		 * 
		 * @param bezeichnung Bezeichnung des gesuchten Projektmarktplatzes
		 * @return Vektor mit allen zu den Suchparametern gefundenen Projektmarktplätzen.
		 */
		public ArrayList<Projektmarktplatz> findByBezeichnung(String bezeichnung) {
			// Datenbankverbindung 
			Connection con = DBConnection.connection();
			//Ergebnis-ArrayList anlegen
			ArrayList<Projektmarktplatz> result = new ArrayList<Projektmarktplatz>();
			
			try {
				// neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausfÃ¼hren
				ResultSet rs = stmt.executeQuery("SELECT * FROM Projektmarktplatz " +
						"WHERE bezeichnung = '" + bezeichnung +"'");
				// FÃ¼r jeden gefundenen Treffer...
				while (rs.next()) {
					// ... neues Projektmarkplatz Objekt anlegen
					Projektmarktplatz pm = new Projektmarktplatz();
					// ... Id und Bezeichnung mit den Daten aus der DB fÃ¼llen
					pm.setId(rs.getInt("idProjektmarktplatz"));
					pm.setBezeichnung(rs.getString("bezeichnung"));
					// ... Objekt dem Ergebnisvektor hinzufÃ¼gen
					result.add(pm);
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
		 * Ausgabe aller Projektmarktplatz DatensÃ¤tze
		 * 
		 * @return Vektor mit allen Projektmarktplätzen
		 */
		
		public ArrayList<Projektmarktplatz> findAllProjektmarkplaetze() {
			// Datenbankverbindung Ã¶ffnen
			Connection con = DBConnection.connection();
			//Ergebnis-ArrayList anlegen
			ArrayList<Projektmarktplatz> result = new ArrayList<Projektmarktplatz>();
			
			try {
				// neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausfÃ¼hren
				// TODO evtl. OrderBy ergÃ¤nzen
				ResultSet rs = stmt.executeQuery("SELECT * " +
						"FROM Projektmarktplatz");
				// FÃ¼r jeden Eintrag neues Projektmarktplatz Objekt erzeugen
				while(rs.next()) {
					Projektmarktplatz pm = new Projektmarktplatz();
					pm.setId(rs.getInt("idProjektmarktplatz"));
					pm.setBezeichnung(rs.getString("bezeichnung"));
					// Projektmarktplatz dem Ergebnisvektor hinzufÃ¼gen
					result.add(pm);
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
		 * Neuer Projektmarktplatz in der Datenbank anlegen.
		 * 
		 * @param pm Projektmarktplatz Objekt, das in die Datenbank eingefügt werden soll
		 */
		public Projektmarktplatz insert(Projektmarktplatz pm) {
			
			// Datenbankverbindung öffnen
			Connection con = DBConnection.connection();
			System.out.println("dbconnection: " + con);
			
			try {
				// neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausführen um die höchste id zu erhalten
				ResultSet rs = stmt.executeQuery("SELECT MAX(idProjektmarktplatz) AS maxId FROM Projektmarktplatz");
				if(rs.next()) {
					// id um 1 erhöhen, damit ein neuer Eintrag erzeugt wird
					pm.setId(rs.getInt("maxId") + 1);
					// neues SQL Statement
					stmt = con.createStatement();
					// SQL Query ausführen um Datensatz in DB zu schreiben
					stmt.executeUpdate("INSERT INTO Projektmarktplatz (idProjektmarktplatz, bezeichnung) " +
							"VALUES "
							+ "('" 
							+ pm.getId() 
							+ "', '" 
							+ pm.getBezeichnung()
							+ "', '" 
							+ "')");	
					
					System.out.println("INSERT INTO Projektmarktplatz (idProjektmarktplatz, bezeichnung) " +
							"VALUES "
							+ "('" 
							+ pm.getId() 
							+ "', '" 
							+ pm.getBezeichnung()
							+ "', '" 
							+ "')");	
				}
			}
			// Error Handling
			catch (SQLException e) {
				e.printStackTrace();
			}
			return pm;
		}
		
		/**
		 *Projektmarktplatzdaten eines bestehenden Projektmarktplatzes in der Datenbank ändern
		 * 
		 * @param pm das bereits geänderte Projektmarktplatzobjekt
		 */
		public Projektmarktplatz update(Projektmarktplatz pm) {
			// Datenbankverbindung öffnen
			Connection con = DBConnection.connection();
			
			try {
				// neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausführen
				stmt.executeUpdate("UPDATE Projektmarktplatz "
						+ "SET bezeichnung = '" 
						+ pm.getBezeichnung()
					    + "' WHERE idProjektmarktplatz = " 
						+ pm.getId());
				
				
				System.out.println("UPDATE Projektmarktplatz "
						+ "SET bezeichnung = '" 
						+ pm.getBezeichnung()
					    + "' WHERE idProjektmarktplatz = " 
						+ pm.getId());
				
			}
			// Error Handling
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			return pm;
		}
		
		/**
		 * Diese Methode löscht einen Projektmarktplatz in der Datenbank die dazugehÃ¶rigen Projektmarktplatz-Referenzen in allen Tabellen
		 * 
		 * @param pm der zu löschende Projektmarktplatz
		 */
		public void delete(Projektmarktplatz pm) {
			// Datenbankverbindung öffnen
			Connection con = DBConnection.connection();
		
			try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			stmt.executeUpdate("DELETE FROM Projektmarktplatz WHERE idProjektmarktplatz = " + pm.getId());
		}
			// Error Handling
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


