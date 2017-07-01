package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;

/**
 * Klasse, die eine Ausschreibung anzeigt und Bewerben Button bereitstellt
 * 
 * @author Moritz Bittner
 *
 */
public class EinzelAusschreibung extends VerticalPanel {

	private VerticalPanel mainPanel = this;
	private HorizontalPanel titelPanel = new HorizontalPanel();
	private HorizontalPanel beschreibungPanel = new HorizontalPanel();
	private HorizontalPanel fristPanel = new HorizontalPanel();
	

	private Label titelLabel = new Label("Titel:");
	private Label beschreibungLabel = new Label("Beschreibung:");
	private Label fristLabel = new Label("Bewerbungsfrist:");
	private Label eigenschaftenLabel = new Label("Voraussetzungen:");
	// Werden dann befüllt
	private Label atitelLabel = new Label();
	private Label abeschreibungLabel = new Label();
	private Label afristLabel = new Label();

	private Button anlegenButton = new Button("Jetzt bewerben!", new JetztBewerbenClickHandler());

	private int ausschreibungID;

	/**
	 * Konstruktor, der übergebene Ausschreibung anzeigt
	 * 
	 * @param a1
	 *            Ausschreibungsobjekt, das angezeigt wird
	 */
	public EinzelAusschreibung(Ausschreibung a1) {
		//CSS-Styling
		titelLabel.addStyleName("label2");
		titelLabel.setWidth("200px");
		atitelLabel.addStyleName("label1");
		atitelLabel.setWidth("200px");
		beschreibungLabel.addStyleName("label2");
		beschreibungLabel.setWidth("200px");
		abeschreibungLabel.addStyleName("label1");
		abeschreibungLabel.setWidth("200px");
		fristLabel.addStyleName("label2");
		fristLabel.setWidth("200px");
		afristLabel.addStyleName("label1");
		afristLabel.setWidth("200px");
		eigenschaftenLabel.addStyleName("label2");
		
		//Panels hinzufügen
		mainPanel.add(titelPanel);
		mainPanel.add(beschreibungPanel);
		mainPanel.add(fristPanel);
		titelPanel.add(titelLabel);
		titelPanel.add(atitelLabel);
		beschreibungPanel.add(beschreibungLabel);
		beschreibungPanel.add(abeschreibungLabel);
		fristPanel.add(fristLabel);
		fristPanel.add(afristLabel);
		mainPanel.add(eigenschaftenLabel);
		
		//Labeltext setzen
		atitelLabel.setText(a1.getTitel());
		abeschreibungLabel.setText(a1.getBeschreibung());
		afristLabel.setText((a1.getBewerbungsfrist().toString()));
		
		//Eigenschaften abrufen der Ausschreibung
		ClientSideSettings.getProjektAdministration().findNameAndWertFromEigenschaften(a1.getProfil_idSuchprofil(), new GetEigenschaftCallback());
		

		

		ausschreibungID = a1.getId();
		
	}

	/**
	 * Clickhandler, der auf Bewerbung anlegen auf Ausschreibung weiterleitet
	 * 
	 */
	private class JetztBewerbenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			//Bei Klick, RootPanel clearen und auf Bewerbung Anlegen wechseln
			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(new BewerbungAnlegen(ausschreibungID));

		}
	};

	

	private class GetEigenschaftCallback implements AsyncCallback<Vector<Eigenschaft>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen!");

		}

		public void onSuccess(Vector<Eigenschaft> result) {
			//Eigenschaften dem Panel hinzhufügen und anzeigen
			for (int i=0; i<result.size(); i++){
				HorizontalPanel eigenschaftenPanel = new HorizontalPanel();
				mainPanel.add(eigenschaftenPanel);
				Label eigenschaftName = new Label(result.elementAt(i).getName());
				eigenschaftenPanel.add(eigenschaftName);
				eigenschaftName.setStylePrimaryName("label2");
				eigenschaftName.setWidth("200px");
				Label eigenschaftWert = new Label(result.elementAt(i).getWertAsString());
				eigenschaftenPanel.add(eigenschaftWert);
				eigenschaftWert.setStylePrimaryName("label1");
				eigenschaftWert.setWidth("200px");
				mainPanel.add(anlegenButton);
			}
			
		}
}
	}


