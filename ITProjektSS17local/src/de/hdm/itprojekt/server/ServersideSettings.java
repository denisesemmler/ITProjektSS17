package de.hdm.itprojekt.server;

import java.util.logging.Logger;

import de.hdm.itprojekt.shared.CommonSettings;

/**
 * Klasse für Serverseitige Eigenschaften und Dienste, wie beispielsweise ein Logger 
 * @author Patricia
 */
public class ServersideSettings extends CommonSettings {
  private static final String LOGGER_NAME = "ITPr0jektServer";
  private static final Logger log = Logger.getLogger(LOGGER_NAME);

  /** Zentraler serverseitiger Logger für die Applikation
   * 
   * @return die Logger-Instanz für die Server-Seite
   */
  public static Logger getLogger() {
    return log;
  }

}
