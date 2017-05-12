package de.hdm.itprojekt.shared.report;

import java.util.Vector;


/**
 * Einfacher Report, der Reports in Tabellenform mithilfe von Rows und Coulumns erstellen kann.
 * @see Row
 * @see Column
 */
public abstract class SimpleReport extends Report {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Tabelle mit Positionsdaten. Die Tabelle wird zeilenweise in diesem
   * <code>Vector</code> abgelegt.
   */
  private Vector<Row> table = new Vector<Row>();

  /**
   * Hinzufügen einer Row.
   * @param r hinzuzufügende Row.
   */
  public void addRow(Row r) {
    this.table.addElement(r);
  }

  /**
   * Entfernen einer Row.
   * @param r zu entfernende Row.
   */
  public void removeRow(Row r) {
    this.table.removeElement(r);
  }

  /**
   * Auslesen aller Positionsdaten.
   * @return die Tabelle der Positionsdaten
   */
  public Vector<Row> getRows() {
    return this.table;
  }
}
