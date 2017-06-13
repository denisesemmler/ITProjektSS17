package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;




public class ProjektmarktplatzAnlegen extends VerticalPanel{

	private VerticalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();
	
	private Label marktplatzLabel = new Label("Bezeichnung:");
	
	private TextBox projektmarktplatzNameBox = new TextBox();
	
	private Button projektmarktplatzAnlegenButton = new Button("Anlegen", new CreateProjektmarktplatzClickHandler());
	
	public ProjektmarktplatzAnlegen(){
		//CSS Styling
		marktplatzLabel.addStyleName("Content-Label");
		
		mainPanel.add(editorPanel);
		editorPanel.add(marktplatzLabel);
		editorPanel.add(projektmarktplatzNameBox);
		editorPanel.add(projektmarktplatzAnlegenButton);
	}
	
	private class CreateProjektmarktplatzCallback implements AsyncCallback {

		public void onFailure(Throwable caught) {
			Window.alert("Dat läuft noch nit so!");

		}

		public void onSuccess(Object result) {
			Window.alert("Dat läuft!");
			RootPanel.get("Content").clear();
			

		}

	}
	
	private class CreateProjektmarktplatzClickHandler implements ClickHandler {
		
		public void onClick(ClickEvent event) {
			// Projekt project = new Projekt();
			try {

				ClientSideSettings.getProjektAdministration().
				createProjektmarktplatz(projektmarktplatzNameBox.getText(),
						new CreateProjektmarktplatzCallback());
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}

		}
	}
}



