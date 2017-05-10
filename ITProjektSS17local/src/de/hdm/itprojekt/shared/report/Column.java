package de.hdm.itprojekt.shared.report;
import java.io.Serializable;


public class Column implements Serializable {

  
  private static final long serialVersionUID = 1L;

  /**
   * Wert der Spalte
   */
  private String value = null;

 
  public Column() {
  }

  /**
   * @param s der Wert, der durch das Column-Objekt dargestellt werden soll.
   */
  public Column(String s) {
    this.value = s;
  }

  /**
   * Auslesen des Spaltenwerts.
   * @return Eintrag als String
   */
  public String getValue() {
    return value;
  }

  /**
   * Setzen des Spaltenwerts.
   * 
   * @param value neuer Spaltenwert
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Umwandeln in String
   */
public String toString() {
    return this.value;
  }
}
