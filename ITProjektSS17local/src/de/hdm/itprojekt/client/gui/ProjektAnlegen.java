package de.hdm.itprojekt.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Projektmarktplatz;

/**
 * GUI Klasse die das VerticalPanel vererbt bekommt für Anlegen von Projekten
 * 
 * @author Moritz Bittner
 */
public class ProjektAnlegen extends VerticalPanel {

	private Vector<Projektmarktplatz> pmVector = new Vector<Projektmarktplatz>();
	/**
	 * Erstellen der Panels
	 */
	private VerticalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();
	private VerticalPanel attributePanel = new VerticalPanel();
	private HorizontalPanel datePanel = new HorizontalPanel();
	private VerticalPanel startPanel = new VerticalPanel();
	private VerticalPanel endPanel = new VerticalPanel();

	/**
	 * Erstellen der Labels
	 */
	private Label marktplatzLabel = new Label("Projektmarktplatz wählen:");
	private Label projektNameLabel = new Label("Projektname: ");
	private Label projektBeschreibungLabel = new Label("Projektbeschreibung: ");
	private Label startDateLabel = new Label("Start Datum: ");
	private Label endDateLabel = new Label("End Datum: ");

	/**
	 * Erstellen der ListBox
	 */
	private ListBox marktplatzListbox = new ListBox();

	/**
	 * Erstellen der Buttons
	 */
	private Button projektAnlegenButton = new Button("Anlegen", new CreateProjectClickHandler());

	/**
	 * Erstellen der TextBoxen
	 */
	private TextBox projektNameBox = new TextBox();
	private TextArea projektBeschreibungArea = new TextArea();
	/**
	 * Erstellen DatePicker
	 */
	private DatePicker startPicker = new DatePicker();
	private DatePicker endPicker = new DatePicker();

	/**
	 * Konstruktor für Anlegen der GUI
	 */
	public ProjektAnlegen() {

		// CSS Styling
		marktplatzLabel.addStyleName("Content-Label");
		projektNameLabel.addStyleName("Content-Label");
		projektBeschreibungLabel.addStyleName("Content-Label");
		startDateLabel.addStyleName("Content-Label");
		endDateLabel.addStyleName("Content-Label");
		projektNameBox.addStyleName("gwt-TextBox");
		projektBeschreibungArea.addStyleName("textarea");

		mainPanel.add(editorPanel);

		editorPanel.add(attributePanel);
		editorPanel.add(datePanel);

		attributePanel.add(marktplatzLabel);
		attributePanel.add(marktplatzListbox);

		attributePanel.add(projektNameLabel);
		attributePanel.add(projektNameBox);
		attributePanel.add(projektBeschreibungLabel);
		attributePanel.add(projektBeschreibungArea);

		datePanel.add(startPanel);
		datePanel.add(endPanel);

		startPanel.add(startDateLabel);
		startPanel.add(startPicker);

		endPanel.add(endDateLabel);
		endPanel.add(endPicker);

		// Set default Value
		startPicker.setValue(new Date(), true);
		endPicker.setValue(new Date(), true);

		mainPanel.add(projektAnlegenButton);

		try {
			ClientSideSettings.getProjektAdministration().findAllProjektmarktplatz(new GetAllMarktplatzCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}

	}

	/**
	 * Alle Marktplätze auslesen Callback
	 * 
	 * @author Moritz Bittner
	 *
	 */
	private class GetAllMarktplatzCallback implements AsyncCallback<Vector<Projektmarktplatz>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");
		}

		public void onSuccess(Vector<Projektmarktplatz> result) {

			for (int i = 0; i < result.size(); i++) {
				Projektmarktplatz pm1 = result.elementAt(i);
				pmVector.add(pm1);
				marktplatzListbox.addItem(pm1.getBezeichnung());
			}
		}
	}

	/**
	 * Callback für Erstellen von Projekt
	 * 
	 * @author Moritz Bittner
	 *
	 */
	private class CreateProjectCallback implements AsyncCallback<Projekt> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");

		}

		public void onSuccess(Projekt result) {
			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(new ProjektVerwalten());

		}

	}

	/**
	 * ClickHandler für Erstellen von Projekt
	 * 
	 * @author Moritz Bittner
	 *
	 */
	private class CreateProjectClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			// Projektmarktplatz ID
			int id = pmVector.elementAt(marktplatzListbox.getSelectedIndex()).getId();
			try {
				//Überprüfen ob Eingaben erfolgt
				String input = projektNameBox.getText();
				if (input.matches("")){
					Window.alert("Bitte einen Projektnamen eingeben");
					return;
				}				
				String input1 = projektBeschreibungArea.getText();
				if (input1.matches("")){
					Window.alert("Bitte eine Projektbeschreibung eingeben");
					return;
				}

				ClientSideSettings.getProjektAdministration().createProjekt(projektNameBox.getText(),
						projektBeschreibungArea.getText(), (startPicker.getValue()), (endPicker.getValue()),
						ClientSideSettings.getCurrentUser().getId(), id, new CreateProjectCallback());
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}
	};
}
