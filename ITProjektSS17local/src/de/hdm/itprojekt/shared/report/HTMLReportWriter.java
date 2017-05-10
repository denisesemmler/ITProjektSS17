package de.hdm.itprojekt.shared.report;

import java.util.Vector;

import de.hdm.thies.bankProjekt.shared.report.AllAccountsOfCustomerReport;
import de.hdm.thies.bankProjekt.shared.report.Row;

public class HTMLReportWriter extends ReportWriter{

	
	/**
	 * HTML Text des Report
	 */
	  private String reportText = null;

	  /**
	   * Löschen des Report Text
	   */
	  public void resetReportText() {
	    this.reportText = null;
	  }
	
	public void process(AlleAusschreibungen r) {
	    
		
	    this.resetReportText();

	    /*
	     * StringBuffer für Ergebnis
	     */
	    StringBuffer result = new StringBuffer();

	    
	    //Übersetzen des Report in HTML
	    
	    result.append("<H1>" + r.getTitle() + "</H1>");
	    result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
	    
	    /*
	    result.append("<td valign=\"top\"><b>" + paragraph2HTML(r.getHeaderData())
	        + "</b></td>");
	    result.append("<td valign=\"top\">" + paragraph2HTML(r.getImprint())
	        + "</td>");
	    result.append("</tr><tr><td></td><td>" + r.getCreated().toString()
	        + "</td></tr></table>");
	    */
	    
	    Vector<Row> rows = r.getRows(); 
	    result.append("<table style=\"width:400px\">");

	    for (int i = 0; i < rows.size(); i++) {
	      Row row = rows.elementAt(i);
	      result.append("<tr>");
	      for (int k = 0; k < row.getNumColumns(); k++) {
	        if (i == 0) {
	          result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnAt(k)
	              + "</td>");
	        }
	        else {
	          if (i > 1) {
	            result.append("<td style=\"border-top:1px solid silver\">"
	                + row.getColumnAt(k) + "</td>");
	          }
	          else {
	            result.append("<td valign=\"top\">" + row.getColumnAt(k) + "</td>");
	          }
	        }
	      }
	      result.append("</tr>");
	    }

	    result.append("</table>");

	    /*
	     * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
	     * reportText-Variable zugewiesen. Dadurch wird es mÃ¶glich, anschlieÃŸend das
	     * Ergebnis mittels getReportText() auszulesen.
	     */
	    this.reportText = result.toString();
	  }
	
}
