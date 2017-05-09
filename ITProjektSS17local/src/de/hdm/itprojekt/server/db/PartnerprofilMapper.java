package de.hdm.itprojekt.server.db;
import java.sql.*;

public class PartnerprofilMapper {
	
private static PartnerprofilMapper partnerprofilMapper = null;
	
	protected PartnerprofilMapper(){ //gesch�tzter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden k�nnen.
		
	}
    public static PartnerprofilMapper  partnerprofilMapper() {
        if (partnerprofilMapper == null) {
        	partnerprofilMapper = new PartnerprofilMapper();
        }
        return partnerprofilMapper;
	
    }
    public Partnerprofil insert(Partnerprofil profil) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zun�chst schauen wir nach, welches der momentan h�chste
           * Prim�rschl�sselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM partnerprofil ");

          // Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * profil erh�lt den bisher maximalen, nun um 1 inkrementierten
             * Prim�rschl�ssel.
             */
        	profil.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tats�chliche Einf�geoperation
            stmt.executeUpdate("INSERT INTO partnerprofil (id, ...) "
                + "VALUES (" + profil.getId() + ",'"  "','"
                "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
         * R�ckgabe, des evtl. korrigierten Profils.
         * 
         * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte �bergeben werden, wäre die Anpassung des Auschreibung-Objekts auch
         * ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
         * explizite R�ckgabe von profil ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode ver�ndert hat.
         */
        return profil;
      }
    
    public Partnerprofil update(Partnerprofil profil) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("UPDATE partnerprofil " + "SET firstName=\""
              +  + "\", " + "lastName=\"" +  + "\" "
              + "WHERE id=" + profil.getId()
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Um Analogie zu insert(Partnerprofil profil) zu wahren, geben wir bewertung zur�ck
        return profil;
      }

      /**
       * L�schen der Daten eines Profil-Objekts aus der Datenbank.
       * 
       * @param profil das aus der DB zu l�schende "Objekt"
       */
      public void delete(Partnerprofil profil) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("DELETE FROM partnerprofil " + "WHERE id=" + profil.getId());
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
}
