package de.hdm.itprojekt.client;


import java.util.ArrayList;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.gui.LogOutPopUp;
import de.hdm.itprojekt.client.gui.AnmeldeFormular;
import de.hdm.itprojekt.client.gui.ClientSideSettings;
import de.hdm.itprojekt.client.gui.Navigation;
import de.hdm.itprojekt.client.gui.ProjektAnlegen;
import de.hdm.itprojekt.client.gui.ProjektLoeschen;
//import de.client.ClientSideSettings;
//import de.hdm.itprojekt.shared.LogOutPopUp;
import de.hdm.itprojekt.shared.EditorServiceAsync;
import de.hdm.itprojekt.shared.LoginServiceAsync;
import de.hdm.itprojekt.shared.bo.Teilnehmer;
import de.hdm.itprojekt.shared.bs.ProjektAdministration;
import de.hdm.itprojekt.shared.bs.ProjektAdministrationAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ITProjektSS17local implements EntryPoint {

	//Zwei Panels fuer die GUI
		private VerticalPanel loginPanel = new VerticalPanel();
		private HorizontalPanel emailPanel = new HorizontalPanel();
		private HorizontalPanel passwordPanel = new HorizontalPanel();
		private HorizontalPanel naviPanel = new HorizontalPanel();
		
		
		//Begruessungstext im Label
		private Label loginLabel = new Label(
				"Log in with your Google-Account and get started with Pr0ject");
		
		//Email und Password Label
		private Label emailLabel = new Label("Email:");
		private Label passwordLabel = new Label("Password:");
		//Zur An- und Abmeldung
		private Anchor signInLink = new Anchor("Anmelden");
		private final Button loginButton = new Button("Anmelden");
		private final Anchor logOutLink = new Anchor("Abmelden");
		private Button logOutButton = new Button("Abmelden");
		public static LogOutPopUp logOutPop = new LogOutPopUp();
		public static String logOutUrl;
		
		//Textboxen
		TextBox emailTextBox = new TextBox();
		PasswordTextBox passwordTextBox = new PasswordTextBox();
		
		//ProjektAnlegen Panel
		//HorizontalPanel showcase = new ProjektAnlegen();
		
		//Zur Kommunikation mit der Datenbank
		private final ProjektAdministrationAsync pr0jectAdmin = ClientSideSettings.getProjektAdministration();
		private final LoginServiceAsync loginService = ClientSideSettings.getLoginService();

	  /**
	   * Entry point method.
	   */
	  public void onModuleLoad() {
		  
		  //VlientSideSettings müssen noch erstellt werden in de.hdm.itproject.client
		  //pr0jectAdmin = ClientSideSettings.getProjektAdministration();
		  //loginService = ClientSideSettings.getLoginService();
		  
			/*
			 * Wir rufen die Methode login mit den Uebergabeparametern request Uri &
			 * AsyncCallback vom Datentyp Profil auf - welche uns ein Callback mit
			 * einem Profil zurueckgibt Als naechstes setzten wir den User als
			 * eingelogt ueber die Methode editorService.setUser
			 * 
			 * Nun wird geprueft, ob das zurueckgegebene Profil eingeolgt ist Wenn
			 * dies der Fall ist, dann wird geprueft ob, sich der User das erste Mal
			 * anmeldet, dies wird ueber result.getFname() == null geprÃ¼ft
			 * 
			 * Beim ersten anmelden wird eine Instanz der Klasse
			 * ProfilERstellenEditor erstellt und dem RootPanel hinzugefuegt Der
			 * User kann nun sein noch leeres Profil anlegen
			 * 
			 * Ist bereits ein Profil angelegt, so wird dieses Profil mit dem
			 * erstellen eines Objektes der Klasse ProfilAnzeigenEditor angezeigt
			 */
		  
			
			loginService.login(GWT.getHostPageBaseURL() + "ITProjektSS17local.html",
					new AsyncCallback<Teilnehmer>() {
				
						public void onFailure(Throwable caught) {
							RootPanel.get("Zusatz").add(
									new Label("Entry.login " + caught.toString()));
						}

						public void onSuccess(Teilnehmer result) {
							pr0jectAdmin.setUser(result, new SetUserCallback());
							if (result.isLoggedIn()) {
								if (result.isExisting() == false) {
									try {
										RootPanel.get("Content").add(
												new AnmeldeFormular());
									} catch (Exception e1) {
										e1.printStackTrace();
									}
								} else {
									RootPanel.get("Content").add(
											new HTML(
												"<h2>Willkommen bei pr0ject, "
															+ result.getVorname()
															+ "</h2>"));
									RootPanel.get("Content").add(
											new ProjektLoeschen());
								}

								/*
								 * Hier wird der LogoutUrl auf den, 
								 * durch das Einloggen neu erstellten Link 
								 * aktualisiert
								 * 
								 * Weiter Bekommt der LogOutButton 
								 * einen ClickHandler zugewiesen, welcher die 
								 * Position und die Sichtbarkeit des 
								 * Popups LogOutPop festlegt
								 */
								
								logOutLink.setHref(result.getLogoutUrl());
								logOutUrl = logOutLink.getHref();
								logOutButton.setStylePrimaryName("navi-button");
								
								logOutButton.addClickHandler(new ClickHandler() {
									public void onClick(ClickEvent e) {
										getLogOutPop().setPopupPositionAndShow(
												new PopupPanel.PositionCallback() {
													public void setPosition(
															int offsetWidth,
															int offsetHeight) {
														int left = logOutButton
															.getAbsoluteLeft()-80;
														int top = logOutButton
															.getAbsoluteTop()+45;
														getLogOutPop()
																.setPopupPosition(
																		left, top);
														getLogOutPop().show();
													}

												});
									}
								});
								
								/*
								 * Eine neue Instanz der Klasse Navigation wird
								 * erstellt und dem RootPanel angefuegt
								 */
								
								naviPanel.add(new Navigation());
								naviPanel.add(logOutButton);
								naviPanel.addStyleName("navi-panel");
								RootPanel.get("Navi").add(naviPanel);
								
								
								/*
								 * Ist das Ergebnis der Abfrage, ob der User 
								 * eingelogt ist negativ, so wird eine 
								 * GUI angezeigt, die dem User die Anmeldung
								 * ermoeglicht
								 */
							} else {
								signInLink.setHref(result.getLoginUrl());
								loginLabel.setStylePrimaryName("login-label");
								loginPanel.add(loginLabel);
								loginButton.addClickHandler(new ClickHandler() {
									public void onClick(ClickEvent e) {
										Window.open(signInLink.getHref(), "_self",
												"");
									}
								});
								loginButton.setStylePrimaryName("login-button");
								loginPanel.add(loginButton);
								RootPanel.get("Inhalt").add(loginPanel);
							}
						}

			});
	  
	  
	  /*
		 * Der getter und setter des LogOutPopUp, die in der Klasse LogOutPopUp
		 * im Package "de.client.gui" zur Uebergabe des 
		 * Objektes aus der Klasse LGrotte benutz wird
		 */
		
	

	
			
		
	/**	  RootPanel.get("Content").add(loginPanel);
		  //RootPanel.get("Navi_Container").add(naviPanel);
		  
		  loginPanel.add(loginLabel);
		  
		  loginPanel.add(emailPanel);
		  emailPanel.add(emailLabel);		  
		  emailPanel.add(emailTextBox);
		  
		  loginPanel.add(passwordPanel);
		  passwordPanel.add(passwordLabel);		
		  passwordPanel.add(passwordTextBox);
		  
		  loginPanel.add(loginButton);
		  
		  loginButton.addClickHandler(new ClickHandler() {
		      @Override
			public void onClick(ClickEvent event) {
		        
		    	  RootPanel.get("Content").clear();
		    	  RootPanel.get("Navi").add(new Navigation());
		    	 		    	 		    	 		    	
		     }
		   });	**/	  		  
	    
}

	  public static LogOutPopUp getLogOutPop() {
			return logOutPop;
		}

		public static void setLogOutPop(LogOutPopUp hideIt) {
			logOutPop = hideIt;
		}

/*
 * Hier wird der AsyncCallback der nach dem setzten des aktuellen User
 * zurueckgegeben wird festgelegt
 */

private class SetUserCallback implements AsyncCallback {
	public void onFailure(Throwable caught) {
	}

	public void onSuccess(Object result) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
}