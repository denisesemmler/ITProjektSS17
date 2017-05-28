package de.hdm.itprojekt.server.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import de.hdm.itprojekt.shared.bo.Ausschreibung;

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
							// Id, Bezeichnung, Ausschreibungsbeschreibung, und bewberungsfrist den Daten aus der DB f�llen
							a.setAusschreibungID(rs.getInt("idAusschreibung"));
							a.setBezeichnung(rs.getString("name"));
							a.setBeschreibung(rs.getString("beschreibung"));
							a.setBewerbungsfrist(rs.getDate("bewerbungsfrist"));
						
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
				public ArrayList<Ausschreibung> findByName(String name) {
					// Datenbankverbindung 
					Connection con = DBConnection.connection();
					//Ergebnis-ArrayList anlegen
					ArrayList<Ausschreibung> result = new ArrayList<Ausschreibung>();
					
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
							a.setAusschreibungID(rs.getInt("idAusschreibung"));
							a.setBezeichnung(rs.getString("name"));
							a.setBeschreibung(rs.getString("beschreibung"));
							a.setBewerbungsfrist(rs.getDate("bewerbungsfrist"));
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
				
				public ArrayList<Ausschreibung> findAllAusschreibungen() {
					// Datenbankverbindung öffnen
					Connection con = DBConnection.connection();
					//Ergebnis-ArrayList anlegen
					ArrayList<Ausschreibung> result = new ArrayList<Ausschreibung>();
					
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
							a.setAusschreibungID(rs.getInt("idAusschreibung"));
							a.setBezeichnung(rs.getString("name"));
							a.setBeschreibung(rs.getString("beschreibung"));
							a.setBewerbungsfrist(rs.getDate("bewerbungsfrist"));
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
							a.setAusschreibungID(rs.getInt("maxId") + 1);
							// neues SQL Statement
							stmt = con.createStatement();
							// SQL Query ausf�hren um Datensatz in DB zu schreiben
							stmt.executeUpdate("INSERT INTO Ausschreibung (idAusschreibung, name, beschreibung, bewerbungsfrist) " +
									"VALUES "
									+ "('" 
									+ a.getAusschreibungID() 
									+ "', '" 
									+ a.getBezeichnung()
									+ "', '" 
									+ a.getBeschreibung()
									+ "', '" 
									+ a.getBewerbungsfrist()
									+ "')");	
							
							System.out.println("INSERT INTO Ausschreibung (idAusschreibung, name, beschreibung, bewerbungsfrist) " +
									"VALUES "
									+ "('" 
									+ a.getAusschreibungID() 
									+ "', '" 
									+ a.getBezeichnung()
									+ "', '" 
									+ a.getBeschreibung()
									+ "', '" 
									+ a.getBewerbungsfrist()
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
								+ "SET name = '" 
								+ a.getBezeichnung()
								+ "', beschreibung = '" 
								+ a.getBeschreibung()
								+ "', bewerbungsfrist= '" 
								+ a.getBewerbungsfrist()
								+ "' WHERE idAusschreibung = " 
								+ a.getAusschreibungID());
						
						
						System.out.println("UPDATE Ausschreibung "
								+ "SET name = '" 
								+ a.getBezeichnung()
								+ "', beschreibung = '" 
								+ a.getBeschreibung()
								+ "', bewerbungsfrist= '" 
								+ a.getBewerbungsfrist()
								+ "' WHERE idAusschreibung = " 
								+ a.getAusschreibungID());
						
					}
					// Error Handling
					catch (SQLException e) {
						e.printStackTrace();
					}
					
					return a;
				}
				
				/**
				 * Diese Methode l�scht eine Ausschreibung in der Datenbank die dazugehörigen Ausschreibung-Referenzen in allen Tabellen
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
					stmt.executeUpdate("DELETE FROM Ausschreibung WHERE idAusschreibung = " + a.getAusschreibungID());
				}
					// Error Handling
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			}




