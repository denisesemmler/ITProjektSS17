package de.hdm.itprojekt.server.db;
import java.sql.*;

import de.hdm.thies.bankProjekt.server.db.DBConnection;
import de.hdm.thies.bankProjekt.shared.bo.Customer;

public class BewertungMapper {
	
private static BewertungMapper bewertungMapper = null;
	
	protected BewertungMapper(){ //gesch�tzter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden k�nnen.
		
	}
    public static BewertungMapper  bewertungMapper() {
        if (bewertungMapper == null) {
        	bewertungMapper = new BewertungMapper();
        }
        return bewertungMapper;
	
    }
    
    public Bewertung insert(Bewertung bewertung) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zun�chst schauen wir nach, welches der momentan h�chste
           * Prim�rschl�sselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM bewertung ");

          // Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * bewertung erh�lt den bisher maximalen, nun um 1 inkrementierten
             * Prim�rschl�ssel.
             */
        	bewertung.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tats�chliche Einf�geoperation
            stmt.executeUpdate("INSERT INTO bewertung (id, ...) "
                + "VALUES (" + bewertung.getId() + ",'"  "','"
                "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
            * R�ckgabe, der evtl. korrigierten Bewertung.
         * 
          * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte �bergeben werden, w�re die Anpassung des Auschreibung-Objekts auch
         * ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
         * explizite R�ckgabe von bewertung ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode ver�ndert hat.
         * */
        return bewertung;
      }
    
    public Bewertung update(Bewertung bewertung) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("UPDATE bewertung " + "SET firstName=\""
              +  + "\", " + "lastName=\"" +  + "\" "
              + "WHERE id=" + c.getId()
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Um Analogie zu insert(Bewertung bewertung) zu wahren, geben wir bewertung zur�ck
        return bewertung;
      }

      /**
       * Löschen der Daten eines Bewertung-Objekts aus der Datenbank.
       * 
       * @param bewertung das aus der DB zu l�schende "Objekt"
       */
      public void delete(Bewertung bewertung) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("DELETE FROM bewertung " + "WHERE id=" + bewertung.getId());
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
}
