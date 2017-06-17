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
			 * �ber das Mapping k�nnen sowohl Objekte als auch deren Attribute in die Datenbank 
			 * geschrieben werden, als auch von der Datenbank ausgelesen werden.
			 * </p>
			 * <p>
			 * Es werden Methoden zum Erstellen, �ndern, L�schen und Ausgeben von Nutzern
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
				 * @param id - Prim�rschl�ssel von Ausschreibung
				 * @return Ausschreibungs Objekt, das die gesuchte ID enth�lt
				 */
				public Ausschreibung findById(int id) {
					// Datenbankverbindung �ffnen
					Connection con = DBConnection.connection();
					
					try {
						// Neues SQL Statement anlegen
						Statement stmt = con.createStatement();
						// SQL Query ausf�hren
						ResultSet rs = stmt.executeQuery("SELECT * FROM Ausschreibung " +
								"WHERE idAusschreibung = " + id);
						// Bei Treffer 
						if(rs.next()) {
							// Neues Ausschreibung Objekt erzeugen
							Ausschreibung a = new Ausschreibung();
							// Id, Bezeichnung, Ausschreibungsbeschreibung, und bewberungsfrist den Daten aus der DB f�llen.
							a.setId(rs.getInt("idAusschreibung"));
							a.setTitel(rs.getString("titel"));
							a.setBeschreibung(rs.getString("beschreibung"));
							a.setBewerbungsfrist(rs.getTimestamp("bewerbungsfrist"));
							a.setStatus(rs.getString("status"));
							a.setProjekt_idProjekt(rs.getInt("Projekt_idProjekt"));
							a.setProfil_idSuchprofil(rs.getInt("Profil_idSuchprofil"));
							// Objekt zur�ckgeben
							return a;
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
				 * Suche einer Ausschreibung anhand seines Namens.
				 * Da der Ausschreibungsname nicht eindeutig ist, k�nnen mehrere 
				 * Ergebnisse ausgegeben werden. Alle gefundenen Ausschreibungen werden in einem
				 * Vektor gespeichert.
				 * 
				 * @param name Ausschreibungsname der gesuchten Ausschreibung
				 * @return Vektor mit allen zu den Suchparametern gefundenen Ausschreibungen.
				 */
				public Vector<Ausschreibung> findByName(String name) {
					// Datenbankverbindung 
					Connection con = DBConnection.connection();
					//Ergebnis-Vector anlegen
					Vector<Ausschreibung> result = new Vector<Ausschreibung>();
					
					try {
						// neues SQL Statement anlegen
						Statement stmt = con.createStatement();
						// SQL Query ausführen
						ResultSet rs = stmt.executeQuery("SELECT * FROM Ausschreibung " +
								"WHERE name = '" + name +"'");
						// Für jeden gefundenen Treffer...
						while (rs.next()) {
							// ... neues Ausschreibungs Objekt anlegen
							Ausschreibung a = new Ausschreibung();
							a.setId(rs.getInt("idAusschreibung"));
							a.setTitel(rs.getString("titel"));
							a.setBeschreibung(rs.getString("beschreibung"));
							a.setBewerbungsfrist(rs.getTimestamp("bewerbungsfrist"));
							a.setStatus(rs.getString("status"));
							a.setProjekt_idProjekt(rs.getInt("Projekt_idProjekt"));
							a.setProfil_idSuchprofil(rs.getInt("Profil_idSuchprofil"));
							// ... Objekt dem Ergebnisvektor hinzufügen
							result.add(a);
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
				 * Ausgabe aller Ausschreibungs Datensätze
				 * 
				 * @return Vektor mit allen Ausschreibungen
				 */
				
				public Vector<Ausschreibung> findAllAusschreibungen() {		// 3) Abfrage von allen Ausschreibungen
					// Datenbankverbindung öffnen
					Connection con = DBConnection.connection();
					//Ergebnis-ArrayList anlegen
					Vector<Ausschreibung> result = new Vector<Ausschreibung>();
					
					try {
						// neues SQL Statement anlegen
						Statement stmt = con.createStatement();
						// SQL Query ausführen
						// TODO evtl. OrderBy ergänzen
						ResultSet rs = stmt.executeQuery("SELECT * " +
								"FROM Ausschreibung");
						// Für jeden Eintrag neues Ausschreibungs Objekt erzeugen
						while(rs.next()) {
							Ausschreibung a = new Ausschreibung();
							a.setId(rs.getInt("idAusschreibung"));
							a.setTitel(rs.getString("titel"));
							a.setBeschreibung(rs.getString("beschreibung"));
							a.setBewerbungsfrist(rs.getTimestamp("bewerbungsfrist"));
							a.setStatus(rs.getString("status"));
							a.setProjekt_idProjekt(rs.getInt("Projekt_idProjekt"));
							a.setProfil_idSuchprofil(rs.getInt("Profil_idSuchprofil"));
							// ... Objekt dem Ergebnisvektor hinzufügen
							result.add(a);
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
				 * Neue Ausschreibung in der Datenbank anlegen.
				 * 
				 * @param a Ausschreibung Objekt, das in die Datenbank eingef�gt werden soll
				 */
				public Ausschreibung insert(Ausschreibung a) {
					
					// Datenbankverbindung �ffnen
					Connection con = DBConnection.connection();
					System.out.println("dbconnection: " + con);
					
					try {
						// neues SQL Statement anlegen
						Statement stmt = con.createStatement();
						// SQL Query ausf�hren um die h�chste id zu erhalten
						ResultSet rs = stmt.executeQuery("SELECT MAX(idAusschreibung) AS maxId FROM Ausschreibung");
						if(rs.next()) {
							// id um 1 erh�hen, damit ein neuer Eintrag erzeugt wird
							a.setId(rs.getInt("maxId") + 1);
							// neues SQL Statement
							stmt = con.createStatement();
							// SQL Query ausf�hren um Datensatz in DB zu schreiben
							stmt.executeUpdate("INSERT INTO Ausschreibung (idAusschreibung, titel, beschreibung, bewerbungsfrist, status, Projekt_idProjekt, Profil_idSuchprofil) " +
									"VALUES "
									+ "('" 
									+ a.getId() 
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
									+ a.getId() 
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
				 *Ausschreibungsdaten eines bestehenden Ausschreibung in der Datenbank �ndern
				 * 
				 * @param a das bereits ge�nderte Ausschreibungsobjekt
				 */
				public Ausschreibung update(Ausschreibung a) {
					// Datenbankverbindung �ffnen
					Connection con = DBConnection.connection();
					
					try {
						// neues SQL Statement anlegen
						Statement stmt = con.createStatement();
						// SQL Query ausf�hren
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
								+ a.getId());
						
						
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
								+ a.getId());
						
					}
					// Error Handling
					catch (SQLException e) {
						e.printStackTrace();
					}
					
					return a;
				}
				
				/**
				 * Diese Methode l�scht eine Ausschreibung in der Datenbank die dazugeh�rigen Ausschreibung-Referenzen in allen Tabellen
				 * 
				 * @param a die zu l�schende Ausschreibung
				 */
				public void delete(Ausschreibung a) {
					// Datenbankverbindung �ffnen
					Connection con = DBConnection.connection();
				
					try {
					// neues SQL Statement anlegen
					Statement stmt = con.createStatement();
					// SQL Query ausf�hren
					stmt.executeUpdate("DELETE FROM Ausschreibung WHERE idAusschreibung = " + a.getId());
				}
					// Error Handling
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
//@Philipp --> Methode hinzugef�gt von Patricia
				public Vector<Ausschreibung> findByProjekt(int projektId) {
					// Datenbankverbindung 
					Connection con = DBConnection.connection();
					//Ergebnis-Vector anlegen
					Vector<Ausschreibung> result = new Vector<Ausschreibung>();
					
					try {
						// neues SQL Statement anlegen
						Statement stmt = con.createStatement();
						// SQL Query ausführen
						ResultSet rs = stmt.executeQuery("SELECT * FROM Ausschreibung " +
								"WHERE Projekt_idProjekt = '" + projektId +"'");
						// Für jeden gefundenen Treffer...
						while (rs.next()) {
							// ... neues Ausschreibungs Objekt anlegen
							Ausschreibung a = new Ausschreibung();
							a.setId(rs.getInt("idAusschreibung"));
							a.setTitel(rs.getString("titel"));
							a.setBeschreibung(rs.getString("beschreibung"));
							a.setBewerbungsfrist(rs.getTimestamp("bewerbungsfrist"));
							a.setStatus(rs.getString("status"));
							a.setProjekt_idProjekt(rs.getInt("Projekt_idProjekt"));
							a.setProfil_idSuchprofil(rs.getInt("Profil_idSuchprofil"));
							// ... Objekt dem Ergebnisvektor hinzufügen
							result.add(a);
						}
					}
					// Error Handling
					catch (SQLException e) {
						e.printStackTrace();
					}
					// Ergebnis zurückgeben
					return result;
				}
				
//Methode von Patricia hinzugefügt
				public Vector<Ausschreibung> findAllAusschreibungByTeilnehmerId(int teilnehmerId) {
					// Datenbankverbindung �ffnen
					Connection con = DBConnection.connection();
					
					//Vector Ergebniss-Liste anlegen
					Vector<Ausschreibung> result = new Vector<Ausschreibung>();
					
					try {
						// Neues SQL Statement anlegen
						Statement stmt = con.createStatement();
						
						// SQL Query ausf�hren
						ResultSet rs = stmt.executeQuery("SELECT * FROM Ausschreibung " +
								"WHERE Teilnehmer_idTeilnehmer = " + teilnehmerId);
						
						// Bei Treffer 
						while(rs.next()) {
							
							// Neues Projekt Objekt erzeugen
							Ausschreibung a = new Ausschreibung();
							
							// Id, Projektname, Projektbeschreibung, startdatum und enddatum den Daten aus der DB f�llen
							
							a.setId(rs.getInt("idAusschreibung"));
							a.setTitel(rs.getString("titel"));
							a.setBeschreibung(rs.getString("beschreibung"));
							a.setBewerbungsfrist(rs.getTimestamp("bewerbungsfrist"));
							a.setStatus(rs.getString("status"));
							a.setProjekt_idProjekt(rs.getInt("Projekt_idProjekt"));
							a.setProfil_idSuchprofil(rs.getInt("Profil_idSuchprofil"));
							
							// ... Objekt dem Ergebnisvektor hinzufügen
							result.add(a);
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





