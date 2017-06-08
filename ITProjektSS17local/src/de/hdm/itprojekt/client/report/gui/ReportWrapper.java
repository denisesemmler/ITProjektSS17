package de.hdm.itprojekt.client.report.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class ReportWrapper extends HorizontalPanel {
	private ReportNavigation navigation = new ReportNavigation();
	
	public ReportWrapper() {
		this.add(navigation);
	}
}
