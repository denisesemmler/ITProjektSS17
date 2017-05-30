package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.itprojekt.shared.bo.Eigenschaft;

/**
 * p>
 * Mapper-Klasse zur Abbildung von <code>Eigenschaft</code> Objekten auf die Datenbank.
 * Ueber das Mapping koennen sowohl Objekte als auch deren Attribute in die Datenbank 
 * geschrieben werden, als auch von der Datenbank ausgelesen werden.
 * </p>
 * <p>
 * Es werden Methoden zum Erstellen, aendern, Loeschen und Ausgeben von Eigenschaften * bereitgestellt.
 * </p>
 * @author Marko
 *
 */

public class EigenschaftMapper {

	/**
	 * Die statische Variable eigenschaftMapper stellt sicher, dass es von der
	 * Klasse EigenschaftMapper nur eine einzige Instanz gibt bzw. die
	 * Variable speichert die einzige Instanz dieser Klasse.
	 * @author Marko
	 */
	
	private static EigenschaftMapper eigenschaftMapper = null;
	
	/**
	 * Der private Konstruktor verhindert, dass eine Instanz der Klasse
	 * EigenschaftMapper per <code>new</code> erzeugt werden kann.
	 */
	
	private EigenschaftMapper() {
	}

}


