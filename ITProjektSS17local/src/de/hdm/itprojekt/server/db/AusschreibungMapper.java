package de.hdm.itprojekt.server.db;
import java.sql.*;
import java.util.Vector;

import de.hdm.thies.bankProjekt.server.db.DBConnection;
import de.hdm.thies.bankProjekt.shared.bo.Customer;

public class AusschreibungMapper {
	
	private static AusschreibungMapper ausschreibungMapper = null;
	
	protected AusschreibungMapper(){ //gesch�tzter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden k�nnen.
		
	}
    public static AusschreibungMapper  ausschreibungMapper() {
        if (ausschreibungMapper == null) {
        	ausschreibungMapper = new AusschreibungMapper ();
        }
        return ausschreibungMapper;
	
    }
    
    
    public Vector<Ausschreibung> findAll() {   //Hole alle Ausschreibungen aus Datenbank
        Connection con = DBConnection.connection();
        // Ergebnisvektor vorbereiten
        Vector<Ausschreibung> result = new Vector<Ausschreibung>();

        try {
          Statement stmt = con.createStatement();

          ResultSet rs = stmt.executeQuery("SELECT id, titel, ersteller, erstelldatum "
               + "FROM ausschreibung "  + "ORDER BY id");

          // F�r jeden Eintrag im Suchergebnis wird nun ein Ausschreibung-Objekt
          // erstellt.
          while (rs.next()) {
            Ausschreibung a = new Ausschreibung();
            a.setId(rs.getInt("id"));
            a.setTitel(rs.getString("titel"));
            a.setErsteller(rs.getString("ersteller"));
            a.setDatum(rs.getDate("erstelldatum"));

            // Hinzuf�gen des neuen Objekts zum Ergebnisvektor
            result.addElement(a);
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Ergebnisvektor zur�ckgeben
        return result;
      }
    
    
    public Ausschreibung insert(Ausschreibung a) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zun�chst schauen wir nach, welches der momentan h�chste
           * Prim�rschl�sselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM ausschreibung ");

          // Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * a erh�lt den bisher maximalen, nun um 1 inkrementierten
             * Prim�rschl�ssel.
             */
        	a.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tats�chliche Einf�geoperation
            stmt.executeUpdate("INSERT INTO ausschreibung (id, ...) "
                + "VALUES (" + a.getId() + ",'"  "','"
                "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
         * R�ckgabe, der evtl. korrigierten Ausschreibung.
         * 
         * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte �bergeben werden, wäre die Anpassung des Auschreibung-Objekts auch
         * ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
         * explizite R�ckgabe von a ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode ver�ndert hat.
         */
        return a;
      }
    
    public Ausschreibung update(Ausschreibung a) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("UPDATE ausschreibung " + "SET firstName=\""
              +  + "\", " + "lastName=\"" +  + "\" "
              + "WHERE id=" + ); // Hier SQL Statement

        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Um Analogie zu insert(Ausschreibung a) zu wahren, geben wir a zur�ck
        return a;
      }

      /**
       * L�schen der Daten eines Ausschreibung-Objekts aus der Datenbank.
       * 
       * Parameter a das aus der DB zu l�schende "Objekt"
       */
      public void delete(Ausschreibung a) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("DELETE FROM ausschreibung " + "WHERE id=" +);
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }

}
