package de.hdm.itprojekt.server.report;

import java.util.Date;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.report.AlleAusschreibungen;
import de.hdm.itprojekt.shared.report.Column;
import de.hdm.itprojekt.shared.report.Row;

public class ReportGeneratorImpl {

	
	//Verbindung zu Marktplatzadministration fehlt
	
	//Instantiierung von Administration fehlt
	
	//get und set Administration fehlt
	
	//Imressum fehlt
	
	
	
	//Ger�st. Da fehlende Komponente
	
	
	public AlleAusschreibungen createAlleAusschreibungenReport() //Alle Ausschreibungen werden ausgegeben.
		      throws IllegalArgumentException {

		    //if (this.getBankVerwaltung() == null)
		    //  return null;

		
		    /*
		     * Zunächst legen wir uns einen leeren Report an.
		     */
		    AlleAusschreibungen result = new AlleAusschreibungen();

		    // Jeder Report hat einen Titel
		    result.setTitle("Alle Konten aller Kunden");

		    // Imressum hinzuf�gen
		    //this.addImprint(result);

		    /*
		     * Datum der Erstellung 
		     */
		    //result.setCreated(new Date());

		  


		    
		    
		    //Erstellen des Rport
		    
		    
		    /*
		     * Zunächst legen wir eine Kopfzeile für die Konto-Tabelle an.
		     */
		    Row headline = new Row();

		    /*
		     * Wir wollen Zeilen mit 2 Spalten in der Tabelle erzeugen. In die erste
		     * Spalte schreiben wir die jeweilige Kontonummer und in die zweite den
		     * aktuellen Kontostand. In der Kopfzeile legen wir also entsprechende
		     * Überschriften ab.
		     */
		    headline.addColumn(new Column("Bezeichnung"));
		    headline.addColumn(new Column("Beschreibung"));

		    // Hinzufügen der Kopfzeile
		    result.addRow(headline);

		    /*
		     * Nun werden sämtliche Konten des Kunden ausgelesen und deren Kto.-Nr. und
		     * Kontostand sukzessive in die Tabelle eingetragen.
		     */
		    Vector<Ausschreibung> ausschreibungen = this.administration.getAusschreibungenOf(a); //Get Ausschreibungen of Marktplatz

		    for (Ausschreibung a : ausschreibungen) {
		      
		    	// Eine leere Zeile anlegen.
		      Row ausschreibungRow = new Row();

		      // Erste Spalte: Bezeichnung hinzuf�gen
		      ausschreibungRow.addColumn(new Column(String.valueOf(a.getBezeichnung())));

		      // Zweite Spalte: Beschreibung hinzuf�gen			Bank Verwaltung wird nach Banalnce gefragt
		      ausschreibungRow.addColumn(new Column(String.valueOf(this.administration
		          .getBeschreibung(a))));

		      // hinzuf�gen der Zeile an den Report
		      result.addRow(ausschreibungRow);
		    }

		    /*
		     * R�ckgabe des fertigen Report
		     */
		    return result; //Datenstruktur des Reports. Es kann noch nichts ausgegeben werden.
		  }
	
	
}
