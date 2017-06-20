package de.hdm.itprojekt.shared.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;

import com.google.gwt.user.client.ui.HTML;



public abstract class SimpleReport extends Report {

 
  private static final long serialVersionUID = 1L;


  private Vector<Row> table = new Vector<Row>();

  SimpleReport(String title) {
	  Date date = new Date();
	  DateTimeFormat dtf = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm");
	  String time = dtf.format(date, TimeZone.createTimeZone(1));
	  
	  HTML html = new HTML("<h2>"+title+"</h2><h3>Erstellt am "+time+"</h3>");
	  this.add(html);
  }
 
  public void addRow(Row r) {
    this.table.addElement(r);
  }

  public void removeRow(Row r) {
    this.table.removeElement(r);
  }

  public Vector<Row> getRows() {
    return this.table;
  }
}
