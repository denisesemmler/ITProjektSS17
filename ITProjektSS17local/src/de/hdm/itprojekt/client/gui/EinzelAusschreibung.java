package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


import de.hdm.itprojekt.shared.bo.Ausschreibung;

/**
 * Klasse, die eine Ausschreibung anzeigt und Bewerben Button bereitstellt
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
	// Werden dann befüllt
	private Label atitelLabel = new Label();
	private Label abeschreibungLabel = new Label();
	private Label afristLabel = new Label();

	private Button anlegenButton = new Button("Jetzt bewerben!", new JetztBewerbenClickHandler());

	private int ausschreibungID;
	
	/**
	 * Konstrukro, der Übergeben Ausschreibung anzeigt
	 * @param a1 Ausschreibungsobjekt, das angezeigt wird
	 */
	public EinzelAusschreibung(Ausschreibung a1) {
		
		mainPanel.add(titelPanel);
		mainPanel.add(beschreibungPanel);
		mainPanel.add(fristPanel);
		titelPanel.add(titelLabel);
		titelPanel.add(atitelLabel);
		beschreibungPanel.add(beschreibungLabel);
		beschreibungPanel.add(abeschreibungLabel);
		fristPanel.add(fristLabel);
		fristPanel.add(afristLabel);
		
		atitelLabel.setText(a1.getTitel());
		abeschreibungLabel.setText(a1.getBeschreibung());
		afristLabel.setText((a1.getBewerbungsfrist().toString()));
		
		mainPanel.add(anlegenButton);
		
		ausschreibungID = a1.getId();
		
		
	}
	
	/**
	 *Clickhandler, der auf Bewerbung anlegen auf Ausschreibung weiterleitet 
	 * 
	 */
	private class JetztBewerbenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(new BewerbungAnlegen(ausschreibungID));

			
		}
	};

}
