package de.hdm.itprojekt.shared.bo;

/**
 * Klasse Eighenschaft zur Repräsentation von Eigenschaften eines Profils
 * 
 * @author Denise
 *
 */

public class Eigenschaft extends BusinessObjekt{
	

	
	private static final long serialVersionUID = 1L;
	private int idEigenschaft;
	private int profil_idProfil;
		/**
		 * 	Name der Eigenschaft 
		 */
	private String name	= "";	
	
	/**
	 * Wert der Eigenschaft
	 */
	private int wert	= 0;
		
		
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
		public int getWert() {
			return wert;
		}

		/**
		 * @param wert the wert to set
		 */
		public void setWert(int wert) {
			this.wert = wert;
		}

		/**
		 * @return the idEigenschaft
		 */
		public int getIdEigenschaft() {
			return idEigenschaft;
		}

		/**
		 * @param idEigenschaft the idEigenschaft to set
		 */
		public void setIdEigenschaft(int idEigenschaft) {
			this.idEigenschaft = idEigenschaft;
		}

		/**
		 * @return the profil_idProfil
		 */
		public int getProfil_idProfil() {
			return profil_idProfil;
		}

		/**
		 * @param profil_idProfil the profil_idProfil to set
		 */
		public void setProfil_idProfil(int profil_idProfil) {
			this.profil_idProfil = profil_idProfil;
		}
		
	}
