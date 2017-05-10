package de.hdm.itprojekt.shared.report;
import java.io.Serializable;
import java.util.Vector;
public class Row implements Serializable {

 
  private static final long serialVersionUID = 1L;
  /**
   * Speicherplatz für Spalten der Zeile
   */
  private Vector<Column> columns = new Vector<Column>();

  /**
   * Spalte hinzufügen 
   * @param Spaltenobjekt c
   */
  public void addColumn(Column c) {
    this.columns.addElement(c);
  }

  /**
   * Entfernen einer Spalte
   * @param das zu entfernende Spaltenobjekt c
   */
  public void removeColumn(Column c) {
    this.columns.removeElement(c);
  }

  /**
   * Auslesen der Spalten-Objekte aus Vector.
   * 
   * @return <code>Vector</code>mit Spalten
   */
  public Vector<Column> getColumns() {
    return this.columns;
  }

  /**
   * Auslesen Anzahl Spalten.
   * @return int Anzahl der Spalten
   */
  public int getNumColumns() {
    return this.columns.size();
  }

  /**
   * Auslesen eines einzelnen Spalten-Objekts an index i.
   * 
   * @param Index i für Spalte
   * @return das Spaltenobjekt mit Index i.
   */
  public Column getColumnAt(int i) {
    return this.columns.elementAt(i);
  }
}
