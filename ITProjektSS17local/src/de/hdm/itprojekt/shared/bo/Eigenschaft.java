package de.hdm.itprojekt.shared.bo;

/**
 * Klasse Eighenschaft zur Repräsentation von Eigenschaften eines Profils
 * 
 * @author Denise
 *
 */

public class Eigenschaft extends BusinessObjekt{
	

	
	private static final long serialVersionUID = 1L;
	
		/**
		 * 	Name der Eigenschaft 
		 */
	private String name	= "";	
	
	/**
	 * Wert der Eigenschaft
	 */
	private String wert	= "";
		
		public Eigenschaft (String n, String w){
			this.name =n;
			}

		/**
		 * @return  name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return  wert
		 */
		public String getWert() {
			return wert;
		}

		/**
		 * @param wert the wert to set
		 */
		public void setWert(String wert) {
			this.wert = wert;
		}
		
	}
