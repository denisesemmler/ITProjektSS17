package de.hdm.itprojekt.client;


import java.util.ArrayList;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.gui.Navigation;
import de.hdm.itprojekt.client.gui.ProjektAnlegen;
//import de.client.ClientSideSettings;
//import de.hdm.itprojekt.shared.LogOutPopUp;
import de.hdm.itprojekt.shared.EditorServiceAsync;
import de.hdm.itprojekt.shared.LoginServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ITProjektSS17local implements EntryPoint {

	//Zwei Panels fuer die GUI
		private VerticalPanel loginPanel = new VerticalPanel();
		private HorizontalPanel emailPanel = new HorizontalPanel();
		private HorizontalPanel passwordPanel = new HorizontalPanel();
		
		
		
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
		//public static LogOutPopUp logOutPop = new LogOutPopUp();
		public static String logOutUrl;
		
		//Textboxen
		TextBox emailTextBox = new TextBox();
		PasswordTextBox passwordTextBox = new PasswordTextBox();
		
		//ProjektAnlegen Panel
		//HorizontalPanel showcase = new ProjektAnlegen();
		
		//Zur Kommunikation mit der Datenbank
		private EditorServiceAsync editorService;
		private LoginServiceAsync loginService;

	  /**
	   * Entry point method.
	   */
	  public void onModuleLoad() {
		  
		  //VlientSideSettings müssen noch erstellt werden in de.hdm.itproject.client
		  //editorService = ClientSideSettings.getEditorService();
		  //loginService = ClientSideSettings.getLoginService();
		  
		  RootPanel.get("Content").add(loginPanel);
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
		   });		  		  
	    }
}		      
		   