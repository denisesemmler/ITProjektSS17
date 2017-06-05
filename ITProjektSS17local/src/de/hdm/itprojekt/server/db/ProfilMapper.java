package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import de.hdm.itprojekt.profil.server.db.DBConnection;
import de.hdm.itprojekt.server.db.ProfilMapper;
import de.hdm.itprojekt.shared.bo.Profil;

/**
 * <p>
 * Mapper-Klasse zur Abbildung von <code>Profil</code> Objekten auf die Datenbank.
 * �ber das Mapping k�nnen sowohl Objekte als auch deren Attribute in die Datenbank 
 * geschrieben werden, als auch von der Datenbank ausgelesen werden.
 * </p>
 * <p>
 * Es werden Methoden zum Erstellen, �ndern, L�schen und Ausgeben von Profilen
 * bereitgestellt.
 * </p>
 * @author Denise**/

public class ProfilMapper {
	
	private static ProfilMapper profilMapper = null;
	

	/**
	 * Privater Konstruktor verhindert das Erzeugen neuer Instanzen
	 * mittels des <code>new</code> Keywords.
	 */
	private ProfilMapper() {	
	}

	/**
	 * Singleton
	 * @return
	 */
	public static ProfilMapper profilMapper() {
		if(profilMapper == null) {
			profilMapper = new ProfilMapper();
		}
		
		return profilMapper;
	}
	
	/**
	 * Suche ein Profil anhand seiner einzigartigen ID.
	 * 
	 * @param id - Primärschlüssel von Profil
	 * @return Profil Objekt, das die gesuchte ID enth�lt
	 */
	public Profil findById(int id) {
		// Datenbankverbindung  �ffnen
		Connection con = DBConnection.connection();
		
		try {
			// Neues SQL Statement anlegen
			Statement stmt = con.createStatement();
			// SQL Query ausführen
			ResultSet rs = stmt.executeQuery("SELECT idProfil, erstelldatum, erstelldatum, aenderungsdatum FROM Profil " 
					+ "WHERE idProfil = " 
					+ id);
			// Bei Treffer 
			if(rs.next()) {
				// Neues Source Objekt erzeugen
				Profil p = new Profil();
				// Id und Source mit den Daten aus der DB füllen
				p.setId(rs.getInt("idProfil"));
				p.setErstellDatum(rs.getTimestamp("erstelldatum"));
				p.setAenderungsDatum(rs.getTimestamp("aenderungsdatum"));
				

		//	p.setidTeilnehmer(rs.getInt("idTeilnehmer"));

			
				// Objekt zurückgeben
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
	
}
