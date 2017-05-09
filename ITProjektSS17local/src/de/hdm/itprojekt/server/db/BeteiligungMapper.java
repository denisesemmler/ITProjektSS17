package de.hdm.itprojekt.server.db;
import java.sql.*;

import de.hdm.thies.bankProjekt.server.db.DBConnection;
import de.hdm.thies.bankProjekt.shared.bo.Customer;

public class BeteiligungMapper {
	
private static BeteiligungMapper beteiligungMapper = null;
	
	protected BeteiligungMapper(){ //geschützter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden können.
		
	}
    public static BeteiligungMapper  beteiligungMapper() {
        if (beteiligungMapper == null) {
        	beteiligungMapper = new BeteiligungMapper();
        }
        return beteiligungMapper;
	
    }
    
    public Beteiligung insert(Beteiligung beteiligung) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zunächst schauen wir nach, welches der momentan höchste
           * Primärschlüsselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM beteiligung ");

          // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * beteiligung erhält den bisher maximalen, nun um 1 inkrementierten
             * Primärschlüssel.
             */
        	beteiligung.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO beteiligung (id, ...) "
                + "VALUES (" + beteiligung.getId() + ",'"  "','"
                "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
         * Rückgabe, der evtl. korrigierten Beteiligung.
         * 
          * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte übergeben werden, wäre die Anpassung des Auschreibung-Objekts auch
         * ohne diese explizite Rückgabe außerhalb dieser Methode sichtbar. Die
         * explizite Rückgabe von beteiligung ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
         */
        return beteiligung;
      }
    
    public Beteiligung update(Beteiligung beteiligung) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("UPDATE beteiligung " + "SET firstName=\""
              +  + "\", " + "lastName=\"" +  + "\" "
              + "WHERE id=" + );

        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Um Analogie zu insert(Beteiligung beteiligung) zu wahren, geben wir beteiligung zurück
        return beteiligung;
      }

      /**
       * Löschen der Daten eines Beteiligung-Objekts aus der Datenbank.
       * 
       * @param beteiligung das aus der DB zu löschende "Objekt"
       */
      public void delete(Beteiligung beteiligung) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("DELETE FROM beteiligung " + "WHERE id=" + beteiligung.getId());
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
}
