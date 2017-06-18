package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.Projekt;

public class ProjektLoeschen extends VerticalPanel {

	private Vector<Projekt> pVector = new Vector<Projekt>();
	
	private VerticalPanel mainPanel = this;
	
	private Label projektNameLabel = new Label("Projektname: ");
	private ListBox projektListbox = new ListBox();

	

	private Button projektLoeschenButton = new Button("Loeschen", new DeleteClickHandler());

	public ProjektLoeschen() {
		projektNameLabel.addStyleName("Content-label");
		
		
		mainPanel.add(projektNameLabel);
		mainPanel.add(projektListbox);
		mainPanel.add(projektLoeschenButton);

		try {
			ClientSideSettings.getProjektAdministration().findAllProjektByTeilnehmerId(/*ClientSideSettings.getCurrentUser().getId()*/1,
					new GetAllProjekteByIDCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}
	}

	private class DeleteCallback implements AsyncCallback {

		public void onFailure(Throwable caught) {
			Window.alert("Dat läuft noch nit so!");

		}

		public void onSuccess(Object result) {
			RootPanel.get("Content").clear();

		}

	}

	private class GetAllProjekteByIDCallback implements AsyncCallback<Vector<Projekt>> {

		public void onFailure(Throwable caught) {
			Window.alert("Nein das falsch");
		}

		public void onSuccess(Vector<Projekt> result) {
			//Window.alert("Joo Sucess");
			//Window.alert(ClientSideSettings.getCurrentUser().getVorname());
			for (int i = 0; i < result.size(); i++) {
				Projekt p1 = result.elementAt(i);
				pVector.add(p1);
				projektListbox.addItem(p1.getName());
			}
		}
	}

	private class DeleteClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			try {
				int id = pVector.elementAt(projektListbox.getSelectedIndex()).getId();
				Projekt p = new Projekt();
				p.setId(id);
				ClientSideSettings.getProjektAdministration().deleteProjekt(p, new DeleteCallback());

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}

	};
}
