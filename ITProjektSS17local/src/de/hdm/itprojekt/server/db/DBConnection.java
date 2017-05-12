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
		 *
		 * @see AccountMapper.accountMapper()
		 * @see CustomerMapper.customerMapper()
		 */
		private static Connection con = null;
		
		
		private static String googleUrl = "jdbc:google:mysql://project-id:itprojektss17-database/itprojekt?user=root&password=12345";
	    private static String localUrl = "jdbc:mysql://localhost:3306/itprojekt?user=root&password=12345";
	

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
		 *
		 * <b>Nachteil:</b> Bei Zusammenbruch der Verbindung zur Datenbank - dies
		 * kann z.B. durch ein unbeabsichtigtes Herunterfahren der Datenbank
		 * ausgeloest werden - wird keine neue Verbindung aufgebaut, so dass die in
		 * einem solchen Fall die gesamte Software neu zu starten ist. In einer
		 * robusten Loesung wuerde man hier die Klasse dahingehend modifizieren,
		 * dass bei einer nicht mehr funktionsfuehigen Verbindung stets versucht
		 * wuerde eine neue Verbindung aufzubauen. Dies wuerde allerdings ebenfalls
		 * den Rahmen dieses Projekts sprengen.
		 *
		 * @return DAS <code>DBConncetion</code>-Objekt.
		 * @see con
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
		
			public static void main(String[] args) throws SQLException  {
				//System.out.println(connection());
				DBConnection.connection().createStatement().executeQuery("USE itprojekt");
				ResultSet rs = DBConnection.connection().createStatement().executeQuery("SELECT * FROM projekt");
				//Test ob Verbindung zu lokaler DB funktioniert
				
				while (rs.next()){
					System.out.println("Projekt ID: "+ rs.getString("id"));
					System.out.println(rs.getString("name"));
									
				}
			}

}
