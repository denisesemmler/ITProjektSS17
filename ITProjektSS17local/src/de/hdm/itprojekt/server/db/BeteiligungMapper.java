package de.hdm.itprojekt.server.db;
import java.sql.*;

import de.hdm.thies.bankProjekt.server.db.DBConnection;
import de.hdm.thies.bankProjekt.shared.bo.Customer;

public class BeteiligungMapper {
	
private static BeteiligungMapper beteiligungMapper = null;
	
	protected BeteiligungMapper(){ //gesch�tzter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden k�nnen.
		
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
           * Zun�chst schauen wir nach, welches der momentan h�chste
           * Prim�rschl�sselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM beteiligung ");

          // Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * beteiligung erh�lt den bisher maximalen, nun um 1 inkrementierten
             * Prim�rschl�ssel.
             */
        	beteiligung.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tats�chliche Einf�geoperation
            stmt.executeUpdate("INSERT INTO beteiligung (id, ...) "
                + "VALUES (" + beteiligung.getId() + ",'"  "','"
                "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
         * R�ckgabe, der evtl. korrigierten Beteiligung.
         * 
          * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte �bergeben werden, w�re die Anpassung des Auschreibung-Objekts auch
         * ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
         * explizite R�ckgabe von beteiligung ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode ver�ndert hat.
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

        // Um Analogie zu insert(Beteiligung beteiligung) zu wahren, geben wir beteiligung zur�ck
        return beteiligung;
      }

      /**
       * L�schen der Daten eines Beteiligung-Objekts aus der Datenbank.
       * 
       * @param beteiligung das aus der DB zu l�schende "Objekt"
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
