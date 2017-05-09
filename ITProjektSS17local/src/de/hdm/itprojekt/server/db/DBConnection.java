package de.hdm.itprojekt.server.db;
import java.sql.*;

import com.google.appengine.api.utils.SystemProperty;


public class DBConnection {

	



			/**
			 * Die Klasse DBConnection wird nur einmal instantiiert. Man spricht hierbei
			 * von einem sogenannten <b>Singleton</b>.
			 * <p>
			 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
			 * fuer saemtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
			 * speichert die einzige Instanz dieser Klasse.
			 
			 */
			private static Connection con = null;
			
			/**
			 * Der Name des Datenbank-Servers.
			 */
			private static String dbHost = "localhost:3306";
			/**
			 * Der Name der Datenbank. Diese Datenbank wird entweder in Derby mit Hilfe von
			 * Dateien im Dateisystem realisiert. Dann ist der Name der Datenbank ist gleichzeit
			 * der Name des Verzeichnisses, in dem sich die Datenbankdateien befinden. Alternativ
			 * kann die Datenbank als MySQL-Datenbank realisiert werden.
			 */
			private static String dbName = "";
			/**
			 * Die URL, mit deren Hilfe die Datenbank angesprochen wird.
			 */
			
			
			 private static String googleUrl = "";
			 private static String localUrl = "";
			/**
			 * Diese statische Methode kann aufgrufen werden durch
			 * <code>DBConnection.connection()</code>. Sie stellt die
			 * Singleton-Eigenschaft sicher, indem Sie dafuer sorgt, dass nur eine
			 * einzige Instanz von <code>DBConnection</code> existiert.
			 * <p>
			 *
			 * <b>Fazit:</b> DBConnection sollte nicht mittels <code>new</code>
			 * instantiiert werden, sondern stets durch Aufruf dieser statischen
			 * Methode.
			 * <p>
			 */
			
				
			
			public static Connection connection() {
		        // Wenn es bisher keine Conncetion zur DB gab, ...
		        if (con == null) {
		            String url = null;
		            try {
		                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
		                    // Load the class that provides the new
		                    // "jdbc:google:mysql://" prefix.
		                    Class.forName("com.mysql.jdbc.GoogleDriver");
		                    url = googleUrl;
		                } else {
		                    // Local MySQL instance to use during development.
		                    Class.forName("com.mysql.jdbc.Driver");
		                    url = localUrl;
		                }
		                /*
		                 * Dann erst kann uns der DriverManager eine Verbindung mit den
		                 * oben in der Variable url angegebenen Verbindungsinformationen
		                 * aufbauen.
		                 * 
		                 * Diese Verbindung wird dann in der statischen Variable con
		                 * abgespeichert und fortan verwendet.
		                 */
		                con = DriverManager.getConnection(url);
		            } catch (Exception e) {
		                con = null;
		                e.printStackTrace();
		            }
		        }

		        // Zurückgegeben der Verbindung
		        return con;
		    }
		


}
