package de.hdm.itprojekt.client.gui;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Profil;

public class BewerbungAnlegen extends VerticalPanel {

	private VerticalPanel mainPanel = this;

	private Label textLabel = new Label("Anschreiben:");
	private TextArea textA = new TextArea();
	private Label titelLabel = new Label("Bewerbungstitel:");
	private TextBox titelA = new TextBox();

	Button sendenButton = new Button("Senden", new BewerbungSendenClickHandler());

	Date date = new Date();
	private int ausschreibungID;

	Profil p = new Profil();

	public BewerbungAnlegen(int aID) {

		mainPanel.add(titelLabel);
		mainPanel.add(titelA);
		mainPanel.add(textLabel);
		mainPanel.add(textA);

		mainPanel.add(sendenButton);
		ausschreibungID = aID;

		int currentUserId = ClientSideSettings.getCurrentUser().getId();

		try {
			ClientSideSettings.getProjektAdministration().getProfilIdCurrentUser(currentUserId,
					new GetPartnerProfileCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}

	}

	private class BewerbungSendenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			ClientSideSettings.getProjektAdministration().findBewerbungByProfilIdAndAusschreibungId(p.getId(),
					ausschreibungID, new BewerbungByProfilIdAndAusschreibungsIdCallback());

		}
	};

	private class BewerbungSendenCallback implements AsyncCallback<Bewerbung> {

		public void onFailure(Throwable caught) {
			Window.alert("Nein das falsch");
		}

		public void onSuccess(Bewerbung result) {

			RootPanel.get("Content").clear();
		}

	}

	private class GetPartnerProfileCallback implements AsyncCallback<Profil> {

		public void onFailure(Throwable caught) {
			Window.alert("Dat läuft noch nit so Profil finden!");

		}

		public void onSuccess(Profil result) {

			p.setId(result.getId());
			Window.alert("Dein Profil wurde gefunden!");

		}

	}

	private class BewerbungByProfilIdAndAusschreibungsIdCallback implements AsyncCallback<Bewerbung> {

		@Override
		public void onFailure(Throwable caught) {

		}

		@Override
		public void onSuccess(Bewerbung result) {

			if (result == null) {

				try {

					ClientSideSettings.getProjektAdministration().createBewerbung(textA.getText(), date, 0.0f,
							"Laufend", titelA.getText(), p.getId(), ausschreibungID, new BewerbungSendenCallback());
				} catch (Exception e) {
					Window.alert(e.toString());
					e.printStackTrace();
				}
			} else {
				Window.alert("Bewerbung nicht möglich. Es existiert bereits eine Bewerbung.");
			}

		}

	}
}
