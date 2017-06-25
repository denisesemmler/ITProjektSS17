package de.hdm.itprojekt.shared.report;

import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class Report  extends VerticalPanel{

	/**
	   * Titel des Repors.
	   */
	  private String title = "Report";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
