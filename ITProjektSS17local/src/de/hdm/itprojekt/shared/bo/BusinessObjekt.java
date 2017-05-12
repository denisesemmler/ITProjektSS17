package de.hdm.itprojekt.shared.bo;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author Thies
 * @version 1.0
 * @author Denise 
 * @version 1.1
 *
 */


public class BusinessObjekt implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	
	protected Timestamp creationDate = new Timestamp(System.currentTimeMillis());

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
	    /*
	     * Klassennamen und ID des Objekts werden zur�ck gegeben
	     */
	    return this.getClass().getName() + " #" + this.id;
	  }

	  /**
	   * <p>
	   * Feststellen der <em>inhaltlichen</em> Gleichheit zweier
	   * <code>BusinessObject</code>-Objekte. Die Gleichheit wird in diesem Beispiel auf eine
	   * identische ID beschränkt.
	   * </p>
	   * <p>
	   * <b>ACHTUNG:</b> Die inhaltliche Gleichheit nicht mit dem Vergleich der
	   * <em>Identität</em> eines Objekts mit einem anderen verwechseln!!! Dies
	   * würde durch den Operator <code>==</code> bestimmt. Bei Unklarheit hierzu
	   * können Sie nocheinmal in die Definition des Sprachkerns von Java schauen.
	   * Die Methode <code>equals(...)</code> ist für jeden Referenzdatentyp
	   * definiert, da sie bereits in der Klasse <code>Object</code> in einfachster
	   * Form realisiert ist. Dort ist sie allerdings auf die simple Bestimmung der
	   * Gleicheit der Java-internen Objekt-ID der verglichenen Objekte beschränkt.
	   * In unseren eigenen Klassen können wir diese Methode überschreiben und ihr
	   * mehr Intelligenz verleihen.
	   * </p>
	   */
	  @Override
	public boolean equals(Object o) {
	    /*
	     * Abfragen, ob ein Objekt ungleich NULL ist und ob ein Objekt gecastet
	     * werden kann, sind immer wichtig!
	     */
	    if (o != null && o instanceof BusinessObjekt) {
	      BusinessObjekt bo = (BusinessObjekt) o;
	      try {
	        if (bo.getId() == this.id)
	          return true;
	      }
	      catch (IllegalArgumentException e) {
	        /*
	         * Wenn irgendetwas schief geht, dann geben wir sicherheitshalber false
	         * zurück.
	         */
	        return false;
	      }
	    }
	    /*
	     * Wenn bislang keine Gleichheit bestimmt werden konnte, dann müssen
	     * schließlich false zurückgeben.
	     */
	    return false;
	  }
	  
	  /**
		 * Aktuelles datum festlegen
		 * 
		 * @return
		 */
	
	  public void setCreationDate(Timestamp creationDate) {
			this.creationDate = creationDate;
			}
/**
 * 
 * @return aktuelles Datum bekommen
 */
	  public Timestamp getCreationDate() {
			return this.creationDate;
		}
		
	  /**
	   * <p>
	   * Erzeugen einer ganzen Zahl, die für das <code>BusinessObject</code> charakteristisch ist.
	   * </p>
	   * <p>
	   * Zusammen mit <code>equals</code> sollte diese Methode immer definiert werden. Manche Java-Klassen
	   * verwendenden <code>hashCode</code>, um initial ein Objekt (z.B. in einer Hashtable) zu identifizieren. Erst danach
	   * würde mit <code>equals</code> festgestellt, ob es sich tatsächlich um das gesuchte Objekt handelt.
	   */
	  @Override
	public int hashCode() {
		  return this.id;
	  }

	
	

}
