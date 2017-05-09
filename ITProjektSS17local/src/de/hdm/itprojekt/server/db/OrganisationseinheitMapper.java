package de.hdm.itprojekt.server.db;
import java.sql.*;

public class OrganisationseinheitMapper {
	
private static OrganisationseinheitMapper organisationseinheitMapper = null;
	
	protected OrganisationseinheitMapper(){ //gesch�tzter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden k�nnen.
		
	}
    public static OrganisationseinheitMapper  organisationseinheitMapper() {
        if (organisationseinheitMapper == null) {
        	organisationseinheitMapper = new OrganisationseinheitMapper();
        }
        return organisationseinheitMapper;
	
    }
    
    public Organisationseinheit insert(Organisationseinheit o) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zun�chst schauen wir nach, welches der momentan h�chste
           * Prim�rschl�sselwert ist..
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM organisationseinheit ");

          // Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * o erh�lt den bisher maximalen, nun um 1 inkrementierten
             * Prim�rschl�ssel.
             */
            o.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tats�chliche Einf�geoperation
            stmt.executeUpdate("INSERT INTO organisationseinheit (id, ...) "
                + "VALUES (" + o.getId() + ",'"  "','"
                "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
         * R�ckgabe, des evtl. korrigierten Organisationseinheit.
         * 
        * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte �bergeben werden, wäre die Anpassung des Auschreibung-Objekts auch
         * ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
         * explizite R�ckgabe von o ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode ver�ndert hat.
         */
        return o;
      }

    public Organisationseinheit update(Organisationseinheit o) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("UPDATE organisationseinheit " + "SET firstName=\""
              +  + "\", " + "lastName=\"" +  + "\" "
              + "WHERE id=" + o.getId()
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Um Analogie zu insert(Organisationseinheit o) zu wahren, geben wir bewertung zur�ck
        return o;
      }

      /**
       * L�schen der Daten eines Orga-Objekts aus der Datenbank.
       * 
       * @param o das aus der DB zu l�schende "Objekt"
       */
      public void delete(Organisationseinheit o) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("DELETE FROM organisationseinheit " + "WHERE id=" + o.getId());
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
}
