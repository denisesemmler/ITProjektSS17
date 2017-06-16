package de.hdm.itprojekt.client.gui;
import java.util.logging.Logger;
import com.google.gwt.core.client.GWT;
import de.hdm.itprojekt.shared.*;
import de.hdm.itprojekt.shared.bo.Teilnehmer;
import de.hdm.itprojekt.shared.bs.ProjektAdministration;
import de.hdm.itprojekt.shared.bs.ProjektAdministrationAsync;

public class ClientSideSettings extends CommonSettings{

	/**
      Diese Klasse beinhaltet Eigenscahften und Dienste, die für 
      Klassen der client-seite relevant sind.      
	 * 
	 * @author Philipp
	 * 
	    * currentUser wird gesetzt.
		   */
		private static Teilnehmer currentUser = null;
		
		  /**
		   * Remote Service Proxy. Hiermit wird eine Verbindung mit dem Server-seitigen Dienst
		   * <code>pr0jectAdmin</code> hergestellt.
		   */
		  private static ProjektAdministrationAsync pr0jectAdmin = null;

		  /**
		   * Remote Service Proxy. Hiermit wird eine Verbindung mit dem Server-seitigen Dienst
		   * <code>reportGenerator</code> hergestellt.
		   */
		  
		  private static ReportServiceAsync reportGenerator = null;
		  private static LoginServiceAsync loginService = null;

		  /**
		   * Name des Client-Loggers.
		   */
		  private static final String LOGGER_NAME = "pr0ject client";
		  
		  /**
		   * Instanz des Client-seitigen Loggers.
		   */
		  private static final Logger log = Logger.getLogger(LOGGER_NAME);

		  /**
		   * <p>
		   * Auslesen des Client-Loggers.
		   * </p>
		   * 
		   * @return die Logger-Instanz für die Server-Seite
		   */
		  public static Logger getLogger() {
		    return log;
		  }

		  /**
		   * <p>
		   * Anlegen und Auslesen der applikationsweit eindeutigen ProjektAdministration. Diese
		   * Methode erstellt die ProjektAdministration, wenn sie noch nicht existiert. Ansonsten 
		   * wird das bereits angelegte Objekt übergeben.
		   * </p>
		   * 
		   * @return eindeutige Instanz des Typs <code>ProjektAdministrationAsync</code>
		   * @author Philipp&Patricia 
		   */
		  public static ProjektAdministrationAsync getProjektAdministration() {
		    if (pr0jectAdmin == null) {
		      // falls noch nicht geschehen, per GWT.create ein Objekt von ProjektAdministration erzeugen
		      pr0jectAdmin = GWT.create(ProjektAdministration.class);
		    }

		    // Zurückgeben des Objekts
		    return pr0jectAdmin;
		  }

		  /**
		   * <p>
		   * Anlegen und Auslesen dey applikationsweit eindeutigen ReportGenerator. Diese
		   * Methode erstellt den ReportGenerator, wenn er noch nicht existiert. Ansonsten 
		   * wird das bereits angelegte Objekt übergeben.
		   * </p>
		   * 
		   * @return eindeutige Instanz des Typs <code>ReportServiceAsync</code>
		   * @author Philipp & Patricia
		   */
		  public static ReportServiceAsync getReportGenerator() {
		    if (reportGenerator == null) {
		      // Instantiierung von ReportGenerator
		      reportGenerator = GWT.create(ReportService.class);
		    }
		    // Rückgabe des ReportGenerator
		    return reportGenerator;
		  }

		  public static LoginServiceAsync getLoginService() {
			    if (loginService == null) {
			      // Instantiierung von ReportGenerator
			      loginService = GWT.create(LoginService.class);
			    }
			    // Rückgabe des ReportGenerator
			    return loginService;
			  }

		public static Teilnehmer getCurrentUser() {
			return currentUser;
		}

		public static void setCurrentUser(Teilnehmer currentUser) {
			ClientSideSettings.currentUser = currentUser;
		}

	}

