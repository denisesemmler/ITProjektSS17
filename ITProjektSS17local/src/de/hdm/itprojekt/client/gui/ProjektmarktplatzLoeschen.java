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

import de.hdm.itprojekt.shared.bo.Projektmarktplatz;

public class ProjektmarktplatzLoeschen extends VerticalPanel {

	private VerticalPanel mainPanel = this;
	private Label marktplatzNameLabel = new Label("Marktplatzname: ");
	private ListBox marktplatzListbox = new ListBox();

	private Button loeschenButton = new Button("Löschen", new DeleteClickHandler());

	private Vector<Projektmarktplatz> pmVector = new Vector<Projektmarktplatz>();

	public ProjektmarktplatzLoeschen() {
		mainPanel.add(marktplatzNameLabel);
		mainPanel.add(marktplatzListbox);
		mainPanel.add(loeschenButton);
		
		try {
			ClientSideSettings.getProjektAdministration().findAllProjektmarktplatz(new GetAllMarktplatzCallback());
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

	private class GetAllMarktplatzCallback implements AsyncCallback<Vector<Projektmarktplatz>> {

		public void onFailure(Throwable caught) {
			Window.alert("Läuft garnit");
		}

		public void onSuccess(Vector<Projektmarktplatz> result) {
			for (Projektmarktplatz p : result) {
				marktplatzListbox.addItem(p.getBezeichnung());
			}
			for (int i = 0; i < result.size(); i++) {
				Projektmarktplatz pm1 = result.elementAt(i);
				pmVector.add(pm1);
			}
		}
	}

	private class DeleteClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			try {
				int id = pmVector.elementAt(marktplatzListbox.getSelectedIndex()).getId();
				Projektmarktplatz pm = new Projektmarktplatz();
				pm.setId(id);
				ClientSideSettings.getProjektAdministration().updateProjektmarktplatz(pm, new DeleteCallback());

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}

	};

}
