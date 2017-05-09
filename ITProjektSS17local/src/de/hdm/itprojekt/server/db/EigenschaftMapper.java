package de.hdm.itprojekt.server.db;
import java.sql.*;

public class EigenschaftMapper {
	
private static EigenschaftMapper eigenschaftMapper = null;
	
	protected EigenschaftMapper(){ //gesch�tzter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden k�nnen.
		
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
           * Zun�chst schauen wir nach, welches der momentan h�chste
           * Prim�rschl�sselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM eigenschaft ");

          // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * eigen erh�lt den bisher maximalen, nun um 1 inkrementierten
             * Prim�rschl�ssel.
             */
            eigen.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tats�chliche Einf�geoperation
            stmt.executeUpdate("INSERT INTO eigenschaft (id, ...) "
                + "VALUES (" + eigen.getId() + ",'"  "','"
                "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
          * R�ckgabe, der evtl. korrigierten Eigenschaft.
         * 
          * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte �bergeben werden, w�re die Anpassung des Auschreibung-Objekts auch
         * ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
         * explizite R�ckgabe von eigen ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode ver�ndert hat.
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

        // Um Analogie zu insert(Eigenschaft eigen) zu wahren, geben wir eigen zur�ck
        return eigen;
      }

      /**
       * L�schen der Daten eines Eigenschaft-Objekts aus der Datenbank.
       * 
       * @param eigen das aus der DB zu l�schende "Objekt"
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
