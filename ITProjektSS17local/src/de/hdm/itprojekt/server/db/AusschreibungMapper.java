package de.hdm.itprojekt.server.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Projekt;

public class AusschreibungMapper {
	

			/**
			 * <p>
			 * Mapper-Klasse zur Abbildung von <code>Ausschreibungen</code> Objekten auf die Datenbank.
			 * Über das Mapping können sowohl Objekte als auch deren Attribute in die Datenbank 
			 * geschrieben werden, als auch von der Datenbank ausgelesen werden.
			 * </p>
			 * <p>
			 * Es werden Methoden zum Erstellen, Ãndern, Löschen und Ausgeben von Nutzern
			 * bereitgestellt.
			 * </p>
			 * @author Philipp
			 */	
				private static AusschreibungMapper ausschreibungMapper = null;
								
				/**
				 * Privater Konstruktor verhindert das Erzeugen neuer Instanzen
				 * mittels des <code>new</code> Keywords.
				 */
				private AusschreibungMapper() {
				}
				
				/**
				 * Singleton
				 * @return
				 */
				public static AusschreibungMapper ausschreibungMapper() {
					if(ausschreibungMapper == null) {
						ausschreibungMapper = new AusschreibungMapper();
					}
					
					return ausschreibungMapper;
				}
				
				
				/**
				 * Suche einer Ausschreibung anhand seiner einzigartigen ID.
				 * 
				 * @param id - Primärschlüssel von Ausschreibung
				 * @return Ausschreibungs Objekt, das die gesuchte ID enthält
				 */
				public Ausschreibung findById(int id) {
					// Datenbankverbindung öffnen
					Connection con = DBConnection.connection();
					
					try {
						// Neues SQL Statement anlegen
						Statement stmt = con.createStatement();
						// SQL Query ausführen
						ResultSet rs = stmt.executeQuery("SELECT * FROM Ausschreibung " +
								"WHERE idAusschreibung = " + id);
						// Bei Treffer 
						if(rs.next()) {
							// Neues Ausschreibung Objekt erzeugen
							Ausschreibung a = new Ausschreibung();
							// Id, Bezeichnung, Ausschreibungsbeschreibung, und bewberungsfrist den Daten aus der DB füllen
							a.setIdAusschreibung(rs.getInt("idAusschreibung"));
							a.setTitel(rs.getString("titel"));
							a.setBeschreibung(rs.getString("beschreibung"));
							a.setBewerbungsfrist(rs.getTimestamp("bewerbungsfrist"));
							a.setStatus(rs.getString("status"));
							a.setProjekt_idProjekt(rs.getInt("Projekt_idProjekt"));
							a.setProfil_idSuchprofil(rs.getInt("Profil_idSuchprofil"));
							// Objekt zurückgeben
							return a;
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
				 * Suche einer Ausschreibung anhand seines Namens.
				 * Da der Ausschreibungsname nicht eindeutig ist, können mehrere 
				 * Ergebnisse ausgegeben werden. Alle gefundenen Ausschreibungen werden in einem
				 * Vektor gespeichert.
				 * 
				 * @param name Ausschreibungsname der gesuchten Ausschreibung
				 * @return Vektor mit allen zu den Suchparametern gefundenen Ausschreibungen.
				 */
				public ArrayList<Ausschreibung> findByName(String name) {
					// Datenbankverbindung 
					Connection con = DBConnection.connection();
					//Ergebnis-ArrayList anlegen
					ArrayList<Ausschreibung> result = new ArrayList<Ausschreibung>();
					
					try {
						// neues SQL Statement anlegen
						Statement stmt = con.createStatement();
						// SQL Query ausfÃ¼hren
						ResultSet rs = stmt.executeQuery("SELECT * FROM Ausschreibung " +
								"WHERE name = '" + name +"'");
						// FÃ¼r jeden gefundenen Treffer...
						while (rs.next()) {
							// ... neues Ausschreibungs Objekt anlegen
							Ausschreibung a = new Ausschreibung();
							a.setIdAusschreibung(rs.getInt("idAusschreibung"));
							a.setTitel(rs.getString("titel"));
							a.setBeschreibung(rs.getString("beschreibung"));
							a.setBewerbungsfrist(rs.getTimestamp("bewerbungsfrist"));
							a.setStatus(rs.getString("status"));
							a.setProjekt_idProjekt(rs.getInt("Projekt_idProjekt"));
							a.setProfil_idSuchprofil(rs.getInt("Profil_idSuchprofil"));
							// ... Objekt dem Ergebnisvektor hinzufÃ¼gen
							result.add(a);
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
				 * Ausgabe aller Ausschreibungs DatensÃ¤tze
				 * 
				 * @return Vektor mit allen Ausschreibungen
				 */
				
				public ArrayList<Ausschreibung> findAllAusschreibungen() {		// 3) Abfrage von allen Ausschreibungen
					// Datenbankverbindung Ã¶ffnen
					Connection con = DBConnection.connection();
					//Ergebnis-ArrayList anlegen
					ArrayList<Ausschreibung> result = new ArrayList<Ausschreibung>();
					
					try {
						// neues SQL Statement anlegen
						Statement stmt = con.createStatement();
						// SQL Query ausfÃ¼hren
						// TODO evtl. OrderBy ergÃ¤nzen
						ResultSet rs = stmt.executeQuery("SELECT * " +
								"FROM Ausschreibung");
						// FÃ¼r jeden Eintrag neues Ausschreibungs Objekt erzeugen
						while(rs.next()) {
							Ausschreibung a = new Ausschreibung();
							a.setIdAusschreibung(rs.getInt("idAusschreibung"));
							a.setTitel(rs.getString("titel"));
							a.setBeschreibung(rs.getString("beschreibung"));
							a.setBewerbungsfrist(rs.getTimestamp("bewerbungsfrist"));
							a.setStatus(rs.getString("status"));
							a.setProjekt_idProjekt(rs.getInt("Projekt_idProjekt"));
							a.setProfil_idSuchprofil(rs.getInt("Profil_idSuchprofil"));
							// ... Objekt dem Ergebnisvektor hinzufÃ¼gen
							result.add(a);
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
				 * Neue Ausschreibung in der Datenbank anlegen.
				 * 
				 * @param a Ausschreibung Objekt, das in die Datenbank eingefügt werden soll
				 */
				public Ausschreibung insert(Ausschreibung a) {
					
					// Datenbankverbindung öffnen
					Connection con = DBConnection.connection();
					System.out.println("dbconnection: " + con);
					
					try {
						// neues SQL Statement anlegen
						Statement stmt = con.createStatement();
						// SQL Query ausführen um die höchste id zu erhalten
						ResultSet rs = stmt.executeQuery("SELECT MAX(idAusschreibung) AS maxId FROM Ausschreibung");
						if(rs.next()) {
							// id um 1 erhöhen, damit ein neuer Eintrag erzeugt wird
							a.setIdAusschreibung(rs.getInt("maxId") + 1);
							// neues SQL Statement
							stmt = con.createStatement();
							// SQL Query ausführen um Datensatz in DB zu schreiben
							stmt.executeUpdate("INSERT INTO Ausschreibung (idAusschreibung, titel, beschreibung, bewerbungsfrist, status, Projekt_idProjekt, Profil_idSuchprofil) " +
									"VALUES "
									+ "('" 
									+ a.getIdAusschreibung() 
									+ "', '" 
									+ a.getTitel()
									+ "', '" 
									+ a.getBeschreibung()
									+ "', '" 
									+ a.getBewerbungsfrist()
									+ "', '" 
									+ a.getStatus()
									+ "', '" 
									+ a.getProjekt_idProjekt()
									+ "', '" 
									+ a.getProfil_idSuchprofil()
									+ "')");	
							
							System.out.println("INSERT INTO Ausschreibung (idAusschreibung, titel, beschreibung, bewerbungsfrist, status, Projekt_idProjekt, Profil_idSuchprofil) " +
									"VALUES "
									+ "('" 
									+ a.getIdAusschreibung() 
									+ "', '" 
									+ a.getTitel()
									+ "', '" 
									+ a.getBeschreibung()
									+ "', '" 
									+ a.getBewerbungsfrist()
									+ "', '" 
									+ a.getStatus()
									+ "', '" 
									+ a.getProjekt_idProjekt()
									+ "', '" 
									+ a.getProfil_idSuchprofil()
									+ "')");	
						}
					}
					// Error Handling
					catch (SQLException e) {
						e.printStackTrace();
					}
					return a;
				}
				
				/**
				 *Ausschreibungsdaten eines bestehenden Ausschreibung in der Datenbank ändern
				 * 
				 * @param a das bereits geänderte Ausschreibungsobjekt
				 */
				public Ausschreibung update(Ausschreibung a) {
					// Datenbankverbindung öffnen
					Connection con = DBConnection.connection();
					
					try {
						// neues SQL Statement anlegen
						Statement stmt = con.createStatement();
						// SQL Query ausführen
						stmt.executeUpdate("UPDATE Ausschreibung "
								+ "SET titel = '" 
								+ a.getTitel()
								+ "', beschreibung = '" 
								+ a.getBeschreibung()
								+ "', bewerbungsfrist= '" 
								+ a.getBewerbungsfrist()
								+ "', status= '" 
								+ a.getStatus()
								+ "' WHERE idAusschreibung = " 
								+ a.getIdAusschreibung());
						
						
						System.out.println("UPDATE Ausschreibung "
								+ "SET titel = '" 
								+ a.getTitel()
								+ "', beschreibung = '" 
								+ a.getBeschreibung()
								+ "', bewerbungsfrist= '" 
								+ a.getBewerbungsfrist()
								+ "', status= '" 
								+ a.getStatus()
								+ "' WHERE idAusschreibung = " 
								+ a.getIdAusschreibung());
						
					}
					// Error Handling
					catch (SQLException e) {
						e.printStackTrace();
					}
					
					return a;
				}
				
				/**
				 * Diese Methode löscht eine Ausschreibung in der Datenbank die dazugehörigen Ausschreibung-Referenzen in allen Tabellen
				 * 
				 * @param a die zu löschende Ausschreibung
				 */
				public void delete(Ausschreibung a) {
					// Datenbankverbindung öffnen
					Connection con = DBConnection.connection();
				
					try {
					// neues SQL Statement anlegen
					Statement stmt = con.createStatement();
					// SQL Query ausführen
					stmt.executeUpdate("DELETE FROM Ausschreibung WHERE idAusschreibung = " + a.getIdAusschreibung());
				}
					// Error Handling
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
//@Philipp --> Methode hinzugefügt von Patricia
				public Vector<Ausschreibung> findByProjekt(Projekt p) {
					// Datenbankverbindung 
					Connection con = DBConnection.connection();
					//Ergebnis-ArrayList anlegen
					Vector<Ausschreibung> result = new Vector<Ausschreibung>();
					
					try {
						// neues SQL Statement anlegen
						Statement stmt = con.createStatement();
						// SQL Query ausfÃ¼hren
						ResultSet rs = stmt.executeQuery("SELECT * FROM Ausschreibung " +
								"WHERE Projekt_idProjekt = '" + p.getIdProjekt() +"'");
						// FÃ¼r jeden gefundenen Treffer...
						while (rs.next()) {
							// ... neues Ausschreibungs Objekt anlegen
							Ausschreibung a = new Ausschreibung();
							a.setIdAusschreibung(rs.getInt("idAusschreibung"));
							a.setTitel(rs.getString("titel"));
							a.setBeschreibung(rs.getString("beschreibung"));
							a.setBewerbungsfrist(rs.getTimestamp("bewerbungsfrist"));
							a.setStatus(rs.getString("status"));
							a.setProjekt_idProjekt(rs.getInt("Projekt_idProjekt"));
							a.setProfil_idSuchprofil(rs.getInt("Profil_idSuchprofil"));
							// ... Objekt dem Ergebnisvektor hinzufÃ¼gen
							result.add(a);
						}
					}
					// Error Handling
					catch (SQLException e) {
						e.printStackTrace();
					}
					// Ergebnis zurÃ¼ckgeben
					return result;
				}
				
			}





