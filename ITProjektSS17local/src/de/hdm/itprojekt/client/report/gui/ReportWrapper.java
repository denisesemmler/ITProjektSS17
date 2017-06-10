package de.hdm.itprojekt.client.report.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;

import de.hdm.itprojekt.shared.report.Report;

public class ReportWrapper extends HorizontalPanel {
	private ReportNavigation navigation = new ReportNavigation(this);
	private Report report;
	
	public ReportWrapper() {
		this.add(navigation);
	}
	
	public void setReport(Report report) {
		if(this.report != null) {
			this.remove(this.report);
		}
		this.report = report;
		this.add(this.report);
	}
	
	
}
