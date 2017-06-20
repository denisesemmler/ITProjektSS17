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

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Projektmarktplatz;

public class BewerbungAnlegen extends VerticalPanel {

	/**
	 * Erstellen der Panels
	 */
	private VerticalPanel mainPanel = this;


	/**
	 * Erstellen der Labels
	 */
	private Label marktplatzLabel = new Label("Projektmarktplatz wählen:");

	/**
	 * Erstellen der ListBox
	 */
	private ListBox marktplatzListbox = new ListBox();
	private ListBox projektListbox = new ListBox();
	private ListBox ausschreibungListbox = new ListBox();

	/**
	 * Erstellen der Buttons
	 */
	private Button marktplatzSuchenButton = new Button("Projekte suchen", new SuchenClickHandler());
	private Button projektSuchenButton = new Button("Ausschreibungen suchen", new SuchenProjektClickHandler());
	private Button ausschreibungAnzeigenButton = new Button("Ausschreibung anzeigen", new AusschreibungAnzeigenClickHandler());
	
	private Vector<Projektmarktplatz> pmVector = new Vector<Projektmarktplatz>();
	private Vector<Projekt> pVector = new Vector<Projekt>();
	private Vector<Ausschreibung> aVector = new Vector<Ausschreibung>();
	private int pmID;
	private int pID;
	private int aID;

	/**
	 * Konstruktor für Anlegen der GUI
	 */
	public BewerbungAnlegen() {

		// CSS Styling
		marktplatzLabel.addStyleName("Content-Label");

		mainPanel.add(marktplatzLabel);
		mainPanel.add(marktplatzListbox);
		mainPanel.add(marktplatzSuchenButton);

		try {
			ClientSideSettings.getProjektAdministration().findAllProjektmarktplatz(new GetAllMarktplatzCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}

	}

	private class GetAllMarktplatzCallback implements AsyncCallback<Vector<Projektmarktplatz>> {

		public void onFailure(Throwable caught) {
			Window.alert("Läuft garnit");
		}

		public void onSuccess(Vector<Projektmarktplatz> result) {

			for (int i = 0; i < result.size(); i++) {
				Projektmarktplatz pm1 = result.elementAt(i);
				pmVector.add(pm1);
				marktplatzListbox.addItem(pm1.getBezeichnung());
			}

		}
	}

	private class SuchenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			// TODO Konstruktor für nächste Klasse (Projekt Suchen)
			pmID = pmVector.elementAt(marktplatzListbox.getSelectedIndex()).getId();
			mainPanel.clear();
			mainPanel.add(marktplatzLabel);
			marktplatzLabel.setText("Projekt wählen");
			mainPanel.add(projektListbox);
			mainPanel.add(projektSuchenButton);
			
			try {
				ClientSideSettings.getProjektAdministration().findProjekteByProjektmarktplatzId(pmID,
						new GetAllProjekteByIDCallback());
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
			
			
		}
	};
	
	private class GetAllProjekteByIDCallback implements AsyncCallback<Vector<Projekt>> {

		public void onFailure(Throwable caught) {
			Window.alert("Nein das falsch");
		}

		public void onSuccess(Vector<Projekt> result) {

			for (int i = 0; i < result.size(); i++) {
				Projekt p1 = result.elementAt(i);
				pVector.add(p1);
				projektListbox.addItem(p1.getName());
			}
			
		}
	}
	
	private class SuchenProjektClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			pID = pVector.elementAt(projektListbox.getSelectedIndex()).getId();
			mainPanel.clear();
			mainPanel.add(marktplatzLabel);
			marktplatzLabel.setText("Ausschreibung suchen:");
			mainPanel.add(ausschreibungListbox);
			mainPanel.add(ausschreibungAnzeigenButton);
			
			try {
				ClientSideSettings.getProjektAdministration().findAusschreibungByProjektId(pID,
						new GetAllAusschreibungByIDCallback());
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
			
		}
	};
	
	private class GetAllAusschreibungByIDCallback implements AsyncCallback<Vector<Ausschreibung>> {

		public void onFailure(Throwable caught) {
			Window.alert("Nein das falsch");
		}

		public void onSuccess(Vector<Ausschreibung> result) {

			for (int i = 0; i < result.size(); i++) {
				Ausschreibung a1 = result.elementAt(i);
				aVector.add(a1);
				ausschreibungListbox.addItem(a1.getTitel());
			}
			
		}
	}
	
	
	
	private class AusschreibungAnzeigenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			// TODO Callback
			
			try {
				ClientSideSettings.getProjektAdministration().findAusschreibungByProjektId(pID,
						new GetAllAusschreibungByIDCallback());
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
			
			
		}
	};
	
	
}
