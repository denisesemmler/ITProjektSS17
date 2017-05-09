package de.hdm.itprojekt.server.db;
import java.sql.*;

public class EigenschaftMapper {
	
private static EigenschaftMapper eigenschaftMapper = null;
	
	protected EigenschaftMapper(){ //geschützter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden können.
		
	}
    public static EigenschaftMapper  eigenschaftMapper() {
        if (eigenschaftMapper == null) {
        	eigenschaftMapper = new EigenschaftMapper();
        }
        return eigenschaftMapper;
	
    }
    
    public Eigenschaft insert(Eigenschaft eigen) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zunächst schauen wir nach, welches der momentan höchste
           * Primärschlüsselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM eigenschaft ");

          // Wenn wir etwas zurÃ¼ckerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * eigen erhält den bisher maximalen, nun um 1 inkrementierten
             * Primärschlüssel.
             */
            eigen.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO eigenschaft (id, ...) "
                + "VALUES (" + eigen.getId() + ",'"  "','"
                "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
          * Rückgabe, der evtl. korrigierten Eigenschaft.
         * 
          * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte übergeben werden, wäre die Anpassung des Auschreibung-Objekts auch
         * ohne diese explizite Rückgabe außerhalb dieser Methode sichtbar. Die
         * explizite Rückgabe von eigen ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
         */
        return eigen;
      }

    public Eigenschaft update(Eigenschaft eigen) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("UPDATE eigenschaft " + "SET firstName=\""
              +  + "\", " + "lastName=\"" +  + "\" "
              + "WHERE id=" + eigen.getId()
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Um Analogie zu insert(Eigenschaft eigen) zu wahren, geben wir eigen zurück
        return eigen;
      }

      /**
       * Löschen der Daten eines Eigenschaft-Objekts aus der Datenbank.
       * 
       * @param eigen das aus der DB zu löschende "Objekt"
       */
      public void delete(Eigenschaft eigen) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("DELETE FROM eigenschaft " + "WHERE id=" + eigen.getId());
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
}
