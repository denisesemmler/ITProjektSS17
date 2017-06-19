package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.itprojekt.client.ITProjektSS17local;
import de.hdm.itprojekt.client.report.gui.ReportWrapper;
import de.hdm.itprojekt.client.gui.LogOutPopUp;

public class Navigation extends HorizontalPanel {

	private Button navProjektmarktplatzVerwaltenButton = new Button("Projektmarktplätze verwalten",
			new NavigationsButtonHandler());
	private Button navProjektVerwaltenButton = new Button("Projekte verwalten", new NavigationsButtonHandler());
	private Button navAusschreibungVerwaltenButton = new Button("Ausschreibungen verwalten",
			new NavigationsButtonHandler());
	private Button navBewerbungVerwaltenButton = new Button("Bewerbungen verwalten", new NavigationsButtonHandler());
	private Button navProfilVerwaltenButton = new Button("Profil verwalten", new NavigationsButtonHandler());
	private Button navBerichteButton = new Button("Berichte", new NavigationsButtonHandler());
	private Button logOutButton = new Button("Abmelden", new NavigationsButtonHandler());
	public static LogOutPopUp logOutPop = new LogOutPopUp();

	public Navigation() {
		// CSS Style Zuweisung
		this.setStylePrimaryName("Navi");
		navProjektmarktplatzVerwaltenButton.setStylePrimaryName("navi-button");
		navProjektVerwaltenButton.setStylePrimaryName("navi-button");
		navAusschreibungVerwaltenButton.setStylePrimaryName("navi-button");
		navBewerbungVerwaltenButton.setStylePrimaryName("navi-button");
		navProfilVerwaltenButton.setStylePrimaryName("navi-button");
		navBerichteButton.setStylePrimaryName("navi-button");
		logOutButton.setStylePrimaryName("navi-button");

		if (ClientSideSettings.getCurrentUser().isExisting()) {
			this.add(navProjektmarktplatzVerwaltenButton);
			this.add(navProjektVerwaltenButton);
			this.add(navAusschreibungVerwaltenButton);
			this.add(navBewerbungVerwaltenButton);
			this.add(navProfilVerwaltenButton);
			this.add(navProfilVerwaltenButton);
			this.add(navBerichteButton);
		}
		this.add(logOutButton);

	}

	private class NavigationsButtonHandler implements ClickHandler {
		public void onClick(ClickEvent e) {

			Button active = (Button) e.getSource();

			switch (active.getText()) {
			case "Projektmarktplätze verwalten":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new ProjektmarktplatzVerwalten());
				break;
			case "Projekte verwalten":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new ProjektVerwalten());
				break;
			case "Ausschreibungen verwalten":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new AusschreibungVerwalten());
				break;
			case "Bewerbungen verwalten":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new BewerbungVerwalten());
				break;
			case "Profil verwalten":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new ProfilVerwalten());
				break;
			case "Berichte":
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new ReportWrapper());
				break;
			case "Abmelden":
				getLogOutPop().setPopupPositionAndShow(new PopupPanel.PositionCallback() {
					public void setPosition(int offsetWidth, int offsetHeight) {
						int left = logOutButton.getAbsoluteLeft() - 80;
						int top = logOutButton.getAbsoluteTop() + 45;
						getLogOutPop().setPopupPosition(left, top);
						getLogOutPop().show();
					}
				});
				break;
			}
		}
	}

	public static LogOutPopUp getLogOutPop() {
		return logOutPop;
	}

	public static void setLogOutPop(LogOutPopUp hideIt) {
		logOutPop = hideIt;
	}
}
