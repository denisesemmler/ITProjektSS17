package de.hdm.itprojekt.server.db;
import java.sql.*;

public class ProjektMapper {
	
private static ProjektMapper projektMapper = null;
	
	protected ProjektMapper(){ //gesch�tzter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden k�nnen.
		
	}
    public static ProjektMapper  projektMapper() {
        if (projektMapper == null) {
        	projektMapper = new ProjektMapper ();
        }
        return projektMapper;
	
    }
    
    public Projekt insert(Projekt p) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zun�chst schauen wir nach, welches der momentan h�chste
           * Prim�rschl�sselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM projekt ");

          // Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * p erh�lt den bisher maximalen, nun um 1 inkrementierten
             * Prim��schl�ssel.
             */
        	p.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tats�chliche Einf�geoperation
            stmt.executeUpdate("INSERT INTO projekt (id, ...) "
                + "VALUES (" + p.getId() + ",'"  "','"
                "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
         * R�ckgabe, des evtl. korrigierten Projekts.
         * 
         * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte �bergeben werden, wäre die Anpassung des Auschreibung-Objekts auch
         * ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
         * explizite R�ckgabe von p ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode ver�ndert hat.
         
         */
        return p;
      }
    
    public Projekt update(Projekt p) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("UPDATE profil " + "SET firstName=\""
              +  + "\", " + "lastName=\"" +  + "\" "
              + "WHERE id=" + p.getId()
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Um Analogie zu insert(Projekt p) zu wahren, geben wir p zur�ck
        return p;
      }

      /**
       * L�schen der Daten eines Projekt-Objekts aus der Datenbank.
       * 
       * @param p das aus der DB zu l�schende "Objekt"
       */
      public void delete(Projekt p) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("DELETE FROM projekt " + "WHERE id=" + p.getId());
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
}
