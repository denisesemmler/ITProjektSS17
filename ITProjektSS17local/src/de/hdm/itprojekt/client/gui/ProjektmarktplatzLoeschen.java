package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;



public class ProjektmarktplatzLoeschen extends VerticalPanel{
	
	private VerticalPanel mainPanel = this;
	private Label marktplatzNameLabel = new Label("Projektname: ");
	private ListBox marktplatzListbox = new ListBox();

	private Button loeschenButton = new Button("Löschen", new DeleteClickHandler());

	
	
	public ProjektmarktplatzLoeschen(){
		mainPanel.add(marktplatzNameLabel);
		mainPanel.add(marktplatzListbox);
		marktplatzListbox.addItem("Auslesen noch nit implementiert");
		mainPanel.add(loeschenButton);
	}
	
	private class DeleteCallback implements AsyncCallback {

		public void onFailure(Throwable caught) {
			Window.alert("Dat läuft noch nit so!");

		}

		public void onSuccess(Object result) {
			RootPanel.get("Content").clear();

		}

	}
	private class DeleteClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			
			try {

				/*ClientSideSettings.getProjektAdministration().createProjekt(projektNameBox.getText(),
						projektBeschreibungArea.getText(), (startPicker.getValue()), (endPicker.getValue()),
						/* ClientSideSettings.getCurrentUser().getId() 1, 1, new CreateProjectCallback());*/
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}
	};
	
}
