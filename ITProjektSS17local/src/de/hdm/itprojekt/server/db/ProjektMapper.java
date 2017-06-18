package de.hdm.itprojekt.server.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Projekt;


public class ProjektMapper {
	
		/**
		 * <p>
		 * Mapper-Klasse zur Abbildung von <code>Projekten</code> Objekten auf die Datenbank.
		 * �ber das Mapping k�nnen sowohl Objekte als auch deren Attribute in die Datenbank 
		 * geschrieben werden, als auch von der Datenbank ausgelesen werden.
		 * </p>
		 * <p>
		 * Es werden Methoden zum Erstellen, �ndern, L�schen und Ausgeben von Nutzern
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
			 * @param id - Prim�rschl�ssel von Projekts
			 * @return Projekt Objekt, das die gesuchte ID enth�lt
			 */
			public Projekt findById(int id) {
				// Datenbankverbindung �ffnen
				Connection con = DBConnection.connection();
				
				try {
					// Neues SQL Statement anlegen
					Statement stmt = con.createStatement();
					// SQL Query ausf�hren
					ResultSet rs = stmt.executeQuery("SELECT * FROM Projekt " +
							"WHERE idProjekt = " + id);
					// Bei Treffer 
					if(rs.next()) {
						// Neues Projekt Objekt erzeugen
						Projekt p = new Projekt();
						// Id, Projektname, Projektbeschreibung, startdatum und enddatum den Daten aus der DB f�llen
						p.setId(rs.getInt("idProjekt"));
						p.setName(rs.getString("name"));
						p.setBeschreibung(rs.getString("beschreibung"));
						p.setStartDatum(rs.getTimestamp("startdatum"));
						p.setEndDatum(rs.getTimestamp("enddatum"));
						p.setProjektmarktplatz_idProjektmarkplatz(rs.getInt("Projektmarktplatz_idProjektmarktplatz"));
						p.setTeilnehmer_idTeilnehmer(rs.getInt("Teilnehmer_idTeilnehmer"));
						// Objekt zur�ckgeben
						return p;
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
			 * Suche eines Projekt anhand seines Namens.
			 * Da der Projektname nicht eindeutig ist, k�nnen mehrere 
			 * Ergebnisse ausgegeben werden. Alle gefundenen Projekte werden in einem
			 * Vektor gespeichert.
			 * 
			 * @param name Projektname des gesuchten Projekts
			 * @return Vektor mit allen zu den Suchparametern gefundenen Projekten.
			 */
			public Vector<Projekt> findByName(String name) {
				// Datenbankverbindung 
				Connection con = DBConnection.connection();
				//Ergebnis-Vector anlegen
				Vector<Projekt> result = new Vector<Projekt>();
				
				try {
					// neues SQL Statement anlegen
					Statement stmt = con.createStatement();
					// SQL Query ausführen
					ResultSet rs = stmt.executeQuery("SELECT * FROM Projekt " +
							"WHERE name = '" + name +"'");
					// Für jeden gefundenen Treffer...
					while (rs.next()) {
						// ... neues Projekt Objekt anlegen
						Projekt p = new Projekt();
						p.setId(rs.getInt("idProjekt"));
						p.setName(rs.getString("name"));
						p.setBeschreibung(rs.getString("beschreibung"));
						p.setStartDatum(rs.getTimestamp("startdatum"));
						p.setEndDatum(rs.getTimestamp("enddatum"));
						p.setProjektmarktplatz_idProjektmarkplatz(rs.getInt("Projektmarktplatz_idProjektmarktplatz"));
						p.setTeilnehmer_idTeilnehmer(rs.getInt("Teilnehmer_idTeilnehmer"));
						// ... Objekt dem Ergebnisvektor hinzufügen
						result.add(p);
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
			 * Ausgabe aller Projekt Datensätze
			 * 
			 * @return Vektor mit allen Projekten
			 */
			
			public Vector<Projekt> findAllProjekte() {
				// Datenbankverbindung öffnen
				Connection con = DBConnection.connection();
				//Ergebnis-Vector anlegen
				Vector<Projekt> result = new Vector<Projekt>();
				
				try {
					// neues SQL Statement anlegen
					Statement stmt = con.createStatement();
					// SQL Query ausführen
					// TODO evtl. OrderBy ergänzen
					ResultSet rs = stmt.executeQuery("SELECT * " +
							"FROM Projekt");
					// Für jeden Eintrag neues Projekt Objekt erzeugen
					while(rs.next()) {
						Projekt p = new Projekt();
						p.setId(rs.getInt("idProjekt"));
						p.setName(rs.getString("name"));
						p.setBeschreibung(rs.getString("beschreibung"));
						p.setStartDatum(rs.getTimestamp("startdatum"));
						p.setEndDatum(rs.getTimestamp("enddatum"));
						p.setProjektmarktplatz_idProjektmarkplatz(rs.getInt("projektmarktplatz_idProjektmarkplatz"));
						p.setTeilnehmer_idTeilnehmer(rs.getInt("teilnehmer_idTeilnehmer"));
						// ... Objekt dem Ergebnisvektor hinzufügen
						result.add(p);
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
			 * Neues Porjekt in der Datenbank anlegen.
			 * 
			 * @param p Projekt Objekt, das in die Datenbank eingef�gt werden soll
			 */
			public Projekt insert(Projekt p) {
				
				// Datenbankverbindung �ffnen
				Connection con = DBConnection.connection();
				System.out.println("dbconnection: " + con);
				
				try {
					// neues SQL Statement anlegen
					Statement stmt = con.createStatement();
					// SQL Query ausf�hren um die h�chste id zu erhalten
					ResultSet rs = stmt.executeQuery("SELECT MAX(idProjekt) AS maxId FROM Projekt");
					if(rs.next()) {
						// id um 1 erh�hen, damit ein neuer Eintrag erzeugt wird
						p.setId(rs.getInt("maxId") + 1);
						// neues SQL Statement
						stmt = con.createStatement();
						// SQL Query ausf�hren um Datensatz in DB zu schreiben
						stmt.executeUpdate("INSERT INTO Projekt (idProjekt, name, startdatum, enddatum, beschreibung, Projektmarktplatz_idProjektmarktplatz, Teilnehmer_idTeilnehmer  ) " +
								"VALUES "
								+ "('" 
								+ p.getId() 
								+ "', '" 
								+ p.getName()
								+ "', '" 
								+ p.getStartDatum()
								+ "', '" 
								+ p.getEndDatum()
								+ "', '" 
								+ p.getBeschreibung()
								+ "', '" 
								+ p.getProjektmarktplatz_idProjektmarkplatz()
								+ "', '" 
								+ p.getTeilnehmer_idTeilnehmer()
								+ "')");	
						
						System.out.println("INSERT INTO Projekt (idProjekt, name, startdatum, enddatum, beschreibung, Projektmarktplatz_idProjektmarkplatz, Teilnehmer_idTeilnehmer  ) " +
								"VALUES "
								+ "('" 
								+ p.getId() 
								+ "', '" 
								+ p.getName()
								+ "', '" 
								+ p.getStartDatum()
								+ "', '" 
								+ p.getEndDatum()
								+ "', '" 
								+ p.getBeschreibung()
								+ "', '" 
								+ p.getProjektmarktplatz_idProjektmarkplatz()
								+ "', '" 
								+ p.getTeilnehmer_idTeilnehmer()
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
			 *Projektdaten eines bestehenden Projekts in der Datenbank �ndern
			 * 
			 * @param p das bereits ge�nderte Projektobjekt
			 */
			public Projekt update(Projekt p) {
				// Datenbankverbindung �ffnen
				Connection con = DBConnection.connection();
				
				try {
					// neues SQL Statement anlegen
					Statement stmt = con.createStatement();
					// SQL Query ausf�hren
					stmt.executeUpdate("UPDATE Projekt "
							+ "SET name = '" 
							+ p.getName()
							+ "', startdatum = '" 
							+ p.getStartDatum()
							+ "', enddatum = '" 
							+ p.getEndDatum()
							+ "', beschreibung = '" 
							+ p.getBeschreibung()
							+ "' WHERE idProjekt = " 
							+ p.getId());
							
					System.out.println("UPDATE Projekt "
							+ "SET name = '" 
							+ p.getName()
							+ "', startdatum = '" 
							+ p.getStartDatum()
							+ "', enddatum = '" 
							+ p.getEndDatum()
							+ "', beschreibung = '" 
							+ p.getBeschreibung()
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
			 * Diese Methode l�scht ein Projekt in der Datenbank die dazugehörigen Projekt-Referenzen in allen Tabellen
			 * 
			 * @param p das zu l�schende Projekt
			 */
			public void delete(Projekt p) {
				// Datenbankverbindung �ffnen
				Connection con = DBConnection.connection();
			
				try {
				// neues SQL Statement anlegen
				Statement stmt = con.createStatement();
				// SQL Query ausf�hren
				stmt.executeUpdate("DELETE FROM Projekt WHERE idProjekt = " + p.getId());
			}
				// Error Handling
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
//Methode von Patricia hinzugefügt
			public Vector<Projekt> findAllProjektmarktplatzById(int id) {
				// Datenbankverbindung �ffnen
				Connection con = DBConnection.connection();
				
				//Vector Ergebniss-Liste anlegen
				Vector<Projekt> result = new Vector<Projekt>();
				
				try {
					// Neues SQL Statement anlegen
					Statement stmt = con.createStatement();
					
					// SQL Query ausf�hren
					ResultSet rs = stmt.executeQuery("SELECT * FROM Projekt " +
							"WHERE Projektmarktplatz_idProjektmarktplatz = " + id);
					
					// Bei Treffer 
					while(rs.next()) {
						
						// Neues Projekt Objekt erzeugen
						Projekt p = new Projekt();
						
						// Id, Projektname, Projektbeschreibung, startdatum und enddatum den Daten aus der DB f�llen
						p.setId(rs.getInt("idProjekt"));
						p.setName(rs.getString("name"));
						p.setBeschreibung(rs.getString("beschreibung"));
						p.setStartDatum(rs.getDate("startdatum"));
						p.setEndDatum(rs.getDate("enddatum"));
						p.setProjektmarktplatz_idProjektmarkplatz(rs.getInt("projektmarktplatz_idProjektmarkplatz"));
						p.setTeilnehmer_idTeilnehmer(rs.getInt("teilnehmer_idTeilnehmer"));
						
						// Objekt muss zu result hinzugefügt werden
						result.add(p);
					}
				} 
				// Error Handling
				catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
				// Ergebnis wird zurückgegeben
				return result;
			}

//Methode von Patricia hinzugefügt
			public Vector<Projekt> findAllProjektByTeilnehmerId(int teilnehmerId) {
				// Datenbankverbindung �ffnen
				Connection con = DBConnection.connection();
				
				//Vector Ergebniss-Liste anlegen
				Vector<Projekt> result = new Vector<Projekt>();
				
				try {
					// Neues SQL Statement anlegen
					Statement stmt = con.createStatement();
					
					// SQL Query ausf�hren
					ResultSet rs = stmt.executeQuery("SELECT * FROM Projekt WHERE Teilnehmer_idTeilnehmer=" + teilnehmerId);
					
					
					// Bei Treffer 
					while(rs.next()) {
						
						// Neues Projekt Objekt erzeugen
						Projekt p = new Projekt();
						
						// Id, Projektname, Projektbeschreibung, startdatum und enddatum den Daten aus der DB f�llen
						p.setId(rs.getInt("idProjekt"));
						p.setName(rs.getString("name"));
						p.setBeschreibung(rs.getString("beschreibung"));
						p.setStartDatum(rs.getDate("startdatum"));
						p.setEndDatum(rs.getDate("enddatum"));
						p.setProjektmarktplatz_idProjektmarkplatz(rs.getInt("Projektmarktplatz_idProjektmarkplatz"));
						p.setTeilnehmer_idTeilnehmer(rs.getInt("Teilnehmer_idTeilnehmer"));
						
						// Objekt muss zu result hinzugefügt werden
						result.add(p);
					}
				} 
				// Error Handling
				catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
				// Ergebnis wird zurückgegeben
				return result;
			}
			
			
			
		}



