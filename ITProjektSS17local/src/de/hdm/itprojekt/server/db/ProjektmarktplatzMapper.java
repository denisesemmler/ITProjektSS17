package de.hdm.itprojekt.server.db;
import java.sql.*;

public class ProjektmarktplatzMapper {

	private static ProjektmarktplatzMapper projektmarktplatzMapper = null;
	
	protected ProjektmarktplatzMapper(){ //gesch�tzter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden k�nnen.
		
	}
    public static ProjektmarktplatzMapper  projektmarktplatzMapper() {
        if (projektmarktplatzMapper == null) {
        	projektmarktplatzMapper = new ProjektmarktplatzMapper ();
        }
        return projektmarktplatzMapper;
	
    }
    
    public Projektmarktplatz insert(Projektmarktplatz projektmarkt) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zun�chst schauen wir nach, welches der momentan h�chste
           * Prim�rschl�sselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM projektmarktplatz ");

          // Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * projektmarkt erh�lt den bisher maximalen, nun um 1 inkrementierten
             * Prim�rschl�ssel.
             */
        	projektmarkt.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tats�chliche Einf�geoperation
            stmt.executeUpdate("INSERT INTO projektmarktplatz (id, ...) "
                + "VALUES (" + projektmarkt.getId() + ",'"  "','"
                "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
         * R�ckgabe, des evtl. korrigierten Projektmarktplatzes.
         * 
          * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte �bergeben werden, wäre die Anpassung des Auschreibung-Objekts auch
         * ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
         * explizite R�ckgabe von projektmarkt ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode ver�ndert hat.
         */
        return projektmarkt;
      }
	
    public Projektmarktplatz update(Projektmarktplatz projektmarkt) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("UPDATE projektmarktplatz " + "SET firstName=\""
              +  + "\", " + "lastName=\"" +  + "\" "
              + "WHERE id=" + projektmarkt.getId()
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Um Analogie zu insert(Projektmarktplatz projektmarkt) zu wahren, geben wir projektmarkt zur�ck
        return projektmarkt;
      }

      /**
       * L�schen der Daten eines Projektmarktplatz-Objekts aus der Datenbank.
       * 
       * @param projektmarkt das aus der DB zu l�schende "Objekt"
       */
      public void delete(Projektmarktplatz projektmarkt) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("DELETE FROM projektmarktplatz " + "WHERE id=" + projektmarkt.getId());
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
	
}
