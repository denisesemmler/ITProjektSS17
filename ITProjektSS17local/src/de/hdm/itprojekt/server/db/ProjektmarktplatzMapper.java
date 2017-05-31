package de.hdm.itprojekt.server.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import de.hdm.itprojekt.shared.bo.Projektmarktplatz;


public class ProjektmarktplatzMapper {
	


/*
 * Test für Installation
 */
	
	/**
	 * <p>
	 * Mapper-Klasse zur Abbildung von <code>Projektmarktpl�tzen</code> Objekten auf die Datenbank.
	 * �ber das Mapping k�nnen sowohl Objekte als auch deren Attribute in die Datenbank 
	 * geschrieben werden, als auch von der Datenbank ausgelesen werden.
	 * </p>
	 * <p>
	 * Es werden Methoden zum Erstellen, �ndern, L�schen und Ausgeben von Nutzern
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
		 * @param id - Prim�rschl�ssel von Projektmarktplatz
		 * @return Projektmarktplatz Objekt, das die gesuchte ID enth�lt
		 */
		public Projektmarktplatz findById(int id) {
			// Datenbankverbindung �ffnen
			Connection con = DBConnection.connection();
			
			try {
				// Neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausf�hren
				ResultSet rs = stmt.executeQuery("SELECT * FROM Projektmarktplatz " +
						"WHERE idProjektmarktplatz = " + id);
				// Bei Treffer 
				if(rs.next()) {
					// Neues Projektmarktplatz Objekt erzeugen
					Projektmarktplatz pm = new Projektmarktplatz();
					// Id und Bezeichnung mit den Daten aus der DB f�llen
					pm.setId(rs.getInt("idProjektmarktplatz"));
					pm.setBezeichnung(rs.getString("bezeichnung"));
				
					// Objekt zur�ckgeben
					return pm;
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
		 * Suche eines Projektmarktplatz anhand seiner Bezeichnung.
		 * Da die Bezeichnung eindeutig ist, k�nnen mehrere 
		 * Ergebnisse ausgegeben werden. Alle gefundenen Projektmarktpl�tze werden in einem
		 * Vektor gespeichert.
		 * 
		 * @param bezeichnung Bezeichnung des gesuchten Projektmarktplatzes
		 * @return Vektor mit allen zu den Suchparametern gefundenen Projektmarktpl�tzen.
		 */
		public ArrayList<Projektmarktplatz> findByBezeichnung(String bezeichnung) {
			// Datenbankverbindung 
			Connection con = DBConnection.connection();
			//Ergebnis-ArrayList anlegen
			ArrayList<Projektmarktplatz> result = new ArrayList<Projektmarktplatz>();
			
			try {
				// neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausführen
				ResultSet rs = stmt.executeQuery("SELECT * FROM Projektmarktplatz " +
						"WHERE bezeichnung = '" + bezeichnung +"'");
				// Für jeden gefundenen Treffer...
				while (rs.next()) {
					// ... neues Projektmarkplatz Objekt anlegen
					Projektmarktplatz pm = new Projektmarktplatz();
					// ... Id und Bezeichnung mit den Daten aus der DB füllen
					pm.setId(rs.getInt("idProjektmarktplatz"));
					pm.setBezeichnung(rs.getString("bezeichnung"));
					// ... Objekt dem Ergebnisvektor hinzufügen
					result.add(pm);
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
		 * Ausgabe aller Projektmarktplatz Datensätze
		 * 
		 * @return Vektor mit allen Projektmarktpl�tzen
		 */
		
		public ArrayList<Projektmarktplatz> findAllProjektmarkplaetze() {
			// Datenbankverbindung öffnen
			Connection con = DBConnection.connection();
			//Ergebnis-ArrayList anlegen
			ArrayList<Projektmarktplatz> result = new ArrayList<Projektmarktplatz>();
			
			try {
				// neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausführen
				// TODO evtl. OrderBy ergänzen
				ResultSet rs = stmt.executeQuery("SELECT * " +
						"FROM Projektmarktplatz");
				// Für jeden Eintrag neues Projektmarktplatz Objekt erzeugen
				while(rs.next()) {
					Projektmarktplatz pm = new Projektmarktplatz();
					pm.setId(rs.getInt("idProjektmarktplatz"));
					pm.setBezeichnung(rs.getString("bezeichnung"));
					// Projektmarktplatz dem Ergebnisvektor hinzufügen
					result.add(pm);
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
		 * Neuer Projektmarktplatz in der Datenbank anlegen.
		 * 
		 * @param pm Projektmarktplatz Objekt, das in die Datenbank eingef�gt werden soll
		 */
		public Projektmarktplatz insert(Projektmarktplatz pm) {
			
			// Datenbankverbindung �ffnen
			Connection con = DBConnection.connection();
			System.out.println("dbconnection: " + con);
			
			try {
				// neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausf�hren um die h�chste id zu erhalten
				ResultSet rs = stmt.executeQuery("SELECT MAX(idProjektmarktplatz) AS maxId FROM Projektmarktplatz");
				if(rs.next()) {
					// id um 1 erh�hen, damit ein neuer Eintrag erzeugt wird
					pm.setId(rs.getInt("maxId") + 1);
					// neues SQL Statement
					stmt = con.createStatement();
					// SQL Query ausf�hren um Datensatz in DB zu schreiben
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
		 *Projektmarktplatzdaten eines bestehenden Projektmarktplatzes in der Datenbank �ndern
		 * 
		 * @param pm das bereits ge�nderte Projektmarktplatzobjekt
		 */
		public Projektmarktplatz update(Projektmarktplatz pm) {
			// Datenbankverbindung �ffnen
			Connection con = DBConnection.connection();
			
			try {
				// neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausf�hren
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
		 * Diese Methode l�scht einen Projektmarktplatz in der Datenbank die dazugehörigen Projektmarktplatz-Referenzen in allen Tabellen
		 * 
		 * @param pm der zu l�schende Projektmarktplatz
		 */
		public void delete(Projektmarktplatz pm) {
			// Datenbankverbindung �ffnen
			Connection con = DBConnection.connection();
		
			try {
			// neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausf�hren
			stmt.executeUpdate("DELETE FROM Projektmarktplatz WHERE idProjektmarktplatz = " + pm.getId());
		}
			// Error Handling
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


