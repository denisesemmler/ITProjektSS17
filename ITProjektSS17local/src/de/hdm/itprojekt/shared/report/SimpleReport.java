package de.hdm.itprojekt.shared.report;

import java.util.Date;
import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HTML;

import de.hdm.itprojekt.shared.bo.BusinessObjekt;



public abstract class SimpleReport extends Report {
 
  private static final long serialVersionUID = 1L;

  /**
   * Erstellt Report mit Titel und Uhrzeit
   * @param title
   */
  SimpleReport(String title) {
	  Date date = new Date();
	  DateTimeFormat dtf = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm"); 
	  String time = dtf.format(date);
	  
	  HTML html = new HTML("<h2>"+title+"</h2><h3>Erstellt am "+time+"</h3>");
	  this.add(html);
  }
  
  /**
   * Zeigt an wie viele Berichte vorhanden sind
   * @param i
   */
  protected abstract void setSize(int i);
  
}
