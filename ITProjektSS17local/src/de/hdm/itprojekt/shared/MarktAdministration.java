package de.hdm.itprojekt.shared;

import de.hdm.itprojekt.shared.bo.Projektmarktplatz;
import de.hdm.itprojekt.shared.bo.Teilnehmer;


public interface MarktAdministration extends RemoteService{

	
	/**
	   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
	   * RPC zusätzlich zum No Argument Constructor der implementierenden Klasse
	   * {@link BankVerwaltungImpl} notwendig. Bitte diese Methode direkt nach der
	   * Instantiierung aufrufen.
	   * 
	   * @throws IllegalArgumentException
	   */
	public void init() throws IllegalArgumentException;
	
	/**
	   * Einen Teilnehmer anlegen.
	   * 
	   * @param name Name
	   * @param zusatz Zusatz
	   * @param email Email
	   * @param rolle Rolle
	   * @return Ein fertiges Kunden-Objekt.
	   * @throws IllegalArgumentException
	   */
	  public Teilnehmer createTeilnehmer(String name, String zusatz, String email, int rolle )
	      throws IllegalArgumentException;
	  
	  /**
	   * Auslesen des zugeordneten Projektmarktplatz.
	   * 
	   * @return Projektmarktplatz-Objekt
	   * @throws IllegalArgumentException
	   */
	  public Projektmarktplatz getProjektmarktplatz() throws IllegalArgumentException;
	  
	  /**
	   * Setzen des zugeordneten Projektmarktplatz.
	   * 
	   * @para Projektmarktplatz-Objekt
	   * @throws IllegalArgumentException
	   */
	  public void setProjektmarktplatz(Projektmarktplatz p) throws IllegalArgumentException;
	 
}
