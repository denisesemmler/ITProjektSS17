package de.hdm.itprojekt.client.gui;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ITProjektSS17local;


	/**
	 * Diese Gui-Klasse erbt vom PopupPanel. 
	 * Sie ist fuer die Darstellung der Logoutabfrage zustaendig
	 * 
	 * @author Philipp
	 * 
	 * @version 1.0
	 *
	 */
	public class LogOutPopUp extends PopupPanel {
		/**
		 * Instanz-Variablen werden deklariert
		 */
		//Zur besseren Darstellung zwei Panels
		private VerticalPanel popupPanel = new VerticalPanel();
		private HorizontalPanel buttonPanel = new HorizontalPanel();
		
		// Label und zwei Button, zum befuellen der bereits deklarierten Panels
		public final Button jaButton = new Button("Ja", new LogOutClickHandler());
		public final Button neinButton = new Button
				("Nein", new HideClickHandler());
		private Label logOutText = new Label
				("Möchtest du dich wirklich abmelden?");

		/**
		 * Im No-Argument-Konstruktor wird das Panel sichtbar gemacht, 
		 * Styles werden den sichtbaren Elementen zugewiesen und 
		 * die Elemente werden dem Panel hinzugefuegt
		 */
		public LogOutPopUp() {
			super(true);
			jaButton.setStylePrimaryName("logout-ja");
			neinButton.setStylePrimaryName("logout-nein");
			logOutText.setStylePrimaryName("logout-Text");
			popupPanel.add(logOutText);
			buttonPanel.add(jaButton);
			buttonPanel.add(neinButton);
			popupPanel.add(buttonPanel);
			setWidget(popupPanel);
		}

		/**
		 * ClickHandler zum schliessen des Popups, der den das Objekt, welches
		 * in der ItProjektSS17-Klasse erstellt wurde holt und die sichtbarkeit 
		 * veraendert, abschliessend wird es dem in der ItProjektSS17 befindlichen
		 * gleichgesetzt
		 */
		public class HideClickHandler implements ClickHandler {
			public void onClick(ClickEvent e) {

				LogOutPopUp hideIt = Navigation.getLogOutPop();
				hideIt.hide();
				Navigation.setLogOutPop(hideIt);
			}
		}

		/**
		 *  ClickHandler zum durchfuehren des Logouts
		 */
		private class LogOutClickHandler implements ClickHandler {
			public void onClick(ClickEvent e) {
				Window.open(ITProjektSS17local.logOutUrl, "_self", "");
			}
		}

	}


