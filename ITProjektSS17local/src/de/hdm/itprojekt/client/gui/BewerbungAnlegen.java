package de.hdm.itprojekt.client.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.Ausschreibung;

public class BewerbungAnlegen extends VerticalPanel {

	private VerticalPanel mainPanel = this;

	private Label textLabel = new Label("Anschreiben:");
	private TextArea textA = new TextArea();

	Button sendenButton = new Button("Senden", new BewerbungSendenClickHandler());

	Date date = new Date();
	private int ausschreibungID;

	public BewerbungAnlegen(int aID) {

		mainPanel.add(textLabel);
		mainPanel.add(textA);
		mainPanel.add(sendenButton);
		ausschreibungID = aID;

	}

	private class BewerbungSendenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			// TODO Callback

			try {
				ClientSideSettings.getProjektAdministration().createBewerbung(textA.getText(),
						date,
						0.0f, "Laufend", ClientSideSettings.getCurrentUser().getId(), ausschreibungID,
						new BewerbungSendenCallback());
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}

		}
	};

	private class BewerbungSendenCallback implements AsyncCallback {

		public void onFailure(Throwable caught) {
			Window.alert("Nein das falsch");
		}

		public void onSuccess(Object result) {

			RootPanel.get("Content").clear();
		}

	}
}
