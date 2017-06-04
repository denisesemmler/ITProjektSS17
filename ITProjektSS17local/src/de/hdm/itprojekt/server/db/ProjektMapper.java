package de.hdm.itprojekt.server.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import de.hdm.itprojekt.shared.bo.Projekt;


public class ProjektMapper {



	
		/**
		 * <p>
		 * Mapper-Klasse zur Abbildung von <code>Projekten</code> Objekten auf die Datenbank.
		 * Über das Mapping können sowohl Objekte als auch deren Attribute in die Datenbank 
		 * geschrieben werden, als auch von der Datenbank ausgelesen werden.
		 * </p>
		 * <p>
		 * Es werden Methoden zum Erstellen, Ãndern, Löschen und Ausgeben von Nutzern
		 * bereitgestellt.
		 * </p>
		 * @author Philipp
		 */	
			private static ProjektMapper projektMapper = null;
			
			
			/**
			 * Privater Konstruktor verhindert das Erzeugen neuer Instanzen
			 * mittels des <code>new</code> Keywords.
			 */
			private ProjektMapper() {
				
			}
			
			/**
			 * Singleton
			 * @return
			 */
			public static ProjektMapper projektMapper() {
				if(projektMapper == null) {
					projektMapper = new ProjektMapper();
				}
				
				return projektMapper;
			}
			
			
			/**
			 * Suche eines Projekts anhand seiner einzigartigen ID.
			 * 
			 * @param id - Primärschlüssel von Projekts
			 * @return Projekt Objekt, das die gesuchte ID enthält
			 */
			public Projekt findById(int id) {
				// Datenbankverbindung öffnen
				Connection con = DBConnection.connection();
				
				try {
					// Neues SQL Statement anlegen
					Statement stmt = con.createStatement();
					// SQL Query ausführen
					ResultSet rs = stmt.executeQuery("SELECT * FROM Projekt " +
							"WHERE idProjekt = " + id);
					// Bei Treffer 
					if(rs.next()) {
						// Neues Projekt Objekt erzeugen
						Projekt p = new Projekt();
						// Id, Projektname, Projektbeschreibung, startdatum und enddatum den Daten aus der DB füllen
						p.setId(rs.getInt("idProjekt"));
						p.setProjektName(rs.getString("name"));
						p.setProjektbeschreibung(rs.getString("beschreibung"));
						p.setStartDatum(rs.getTimestamp("startdatum"));
						p.setEndDatum(rs.getTimestamp("enddatum"));
						// Objekt zurückgeben
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
			
			/**
			 * Suche eines Projekt anhand seines Namens.
			 * Da der Projektname nicht eindeutig ist, können mehrere 
			 * Ergebnisse ausgegeben werden. Alle gefundenen Projekte werden in einem
			 * Vektor gespeichert.
			 * 
			 * @param name Projektname des gesuchten Projekts
			 * @return Vektor mit allen zu den Suchparametern gefundenen Projekten.
			 */
			public ArrayList<Projekt> findByName(String name) {
				// Datenbankverbindung 
				Connection con = DBConnection.connection();
				//Ergebnis-ArrayList anlegen
				ArrayList<Projekt> result = new ArrayList<Projekt>();
				
				try {
					// neues SQL Statement anlegen
					Statement stmt = con.createStatement();
					// SQL Query ausfÃ¼hren
					ResultSet rs = stmt.executeQuery("SELECT * FROM Projekt " +
							"WHERE name = '" + name +"'");
					// FÃ¼r jeden gefundenen Treffer...
					while (rs.next()) {
						// ... neues Projekt Objekt anlegen
						Projekt p = new Projekt();
						p.setId(rs.getInt("idProjekt"));
						p.setProjektName(rs.getString("name"));
						p.setProjektbeschreibung(rs.getString("beschreibung"));
						p.setStartDatum(rs.getTimestamp("startdatum"));
						p.setEndDatum(rs.getTimestamp("enddatum"));
						// ... Objekt dem Ergebnisvektor hinzufÃ¼gen
						result.add(p);
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
			 * Ausgabe aller Projekt DatensÃ¤tze
			 * 
			 * @return Vektor mit allen Projekten
			 */
			
			public ArrayList<Projekt> findAllProjekte() {
				// Datenbankverbindung Ã¶ffnen
				Connection con = DBConnection.connection();
				//Ergebnis-ArrayList anlegen
				ArrayList<Projekt> result = new ArrayList<Projekt>();
				
				try {
					// neues SQL Statement anlegen
					Statement stmt = con.createStatement();
					// SQL Query ausfÃ¼hren
					// TODO evtl. OrderBy ergÃ¤nzen
					ResultSet rs = stmt.executeQuery("SELECT * " +
							"FROM Projekt");
					// FÃ¼r jeden Eintrag neues Projekt Objekt erzeugen
					while(rs.next()) {
						Projekt p = new Projekt();
						p.setId(rs.getInt("idProjekt"));
						p.setProjektName(rs.getString("name"));
						p.setProjektbeschreibung(rs.getString("beschreibung"));
						p.setStartDatum(rs.getTimestamp("startdatum"));
						p.setEndDatum(rs.getTimestamp("enddatum"));
						// ... Objekt dem Ergebnisvektor hinzufÃ¼gen
						result.add(p);
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
			 * Neues Porjekt in der Datenbank anlegen.
			 * 
			 * @param p Projekt Objekt, das in die Datenbank eingefügt werden soll
			 */
			public Projekt insert(Projekt p) {
				
				// Datenbankverbindung öffnen
				Connection con = DBConnection.connection();
				System.out.println("dbconnection: " + con);
				
				try {
					// neues SQL Statement anlegen
					Statement stmt = con.createStatement();
					// SQL Query ausführen um die höchste id zu erhalten
					ResultSet rs = stmt.executeQuery("SELECT MAX(idProjekt) AS maxId FROM Projekt");
					if(rs.next()) {
						// id um 1 erhöhen, damit ein neuer Eintrag erzeugt wird
						p.setId(rs.getInt("maxId") + 1);
						// neues SQL Statement
						stmt = con.createStatement();
						// SQL Query ausführen um Datensatz in DB zu schreiben
						stmt.executeUpdate("INSERT INTO Projekt (idProjekt, name, startdatum, enddatum, beschreibung) " +
								"VALUES "
								+ "('" 
								+ p.getId() 
								+ "', '" 
								+ p.getProjektName()
								+ "', '" 
								+ p.getStartDatum()
								+ "', '" 
								+ p.getEndDatum()
								+ "', '" 
								+ p.getProjektbeschreibung()
								+ "')");	
						
						System.out.println("INSERT INTO Projekt (idProjekt, name, startdatum, enddatum, beschreibung) " +
								"VALUES "
								+ "('" 
								+ p.getId() 
								+ "', '" 
								+ p.getProjektName()
								+ "', '" 
								+ p.getStartDatum()
								+ "', '" 
								+ p.getEndDatum()
								+ "', '" 
								+ p.getProjektbeschreibung()
								+ "')");	
					}
				}
				// Error Handling
				catch (SQLException e) {
					e.printStackTrace();
				}
				return p;
			}
			
			/**
			 *Projektdaten eines bestehenden Projekts in der Datenbank ändern
			 * 
			 * @param p das bereits geänderte Projektobjekt
			 */
			public Projekt update(Projekt p) {
				// Datenbankverbindung öffnen
				Connection con = DBConnection.connection();
				
				try {
					// neues SQL Statement anlegen
					Statement stmt = con.createStatement();
					// SQL Query ausführen
					stmt.executeUpdate("UPDATE Projekt "
							+ "SET name = '" 
							+ p.getProjektName()
							+ "', startdatum = '" 
							+ p.getStartDatum()
							+ "', enddatum = '" 
							+ p.getEndDatum()
							+ "', beschreibung = '" 
							+ p.getProjektbeschreibung()
						    + "' WHERE idProjekt = " 
							+ p.getId());
					
					
					System.out.println("UPDATE Projekt "
							+ "SET name = '" 
							+ p.getProjektName()
							+ "', startdatum = '" 
							+ p.getStartDatum()
							+ "', enddatum = '" 
							+ p.getEndDatum()
							+ "', beschreibung = '" 
							+ p.getProjektbeschreibung()
						    + "' WHERE idProjekt = " 
							+ p.getId());
					
				}
				// Error Handling
				catch (SQLException e) {
					e.printStackTrace();
				}
				
				return p;
			}
			
			/**
			 * Diese Methode löscht ein Projekt in der Datenbank die dazugehÃ¶rigen Projekt-Referenzen in allen Tabellen
			 * 
			 * @param p das zu löschende Projekt
			 */
			public void delete(Projekt p) {
				// Datenbankverbindung öffnen
				Connection con = DBConnection.connection();
			
				try {
				// neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausführen
				stmt.executeUpdate("DELETE FROM Projekt WHERE idProjekt = " + p.getId());
			}
				// Error Handling
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}



