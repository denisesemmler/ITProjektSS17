package de.hdm.itprojekt.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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

/**
 * Klasse für Anlegen von Projekte
 * 
 * @author Moritz Bittner
 *
 */

public class ProjektBearbeiten extends VerticalPanel {

	private Vector<Projekt> pVector = new Vector<Projekt>();

	private VerticalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();
	private VerticalPanel attributePanel = new VerticalPanel();
	private HorizontalPanel datePanel = new HorizontalPanel();
	private VerticalPanel startPanel = new VerticalPanel();
	private VerticalPanel endPanel = new VerticalPanel();

	/**
	 * Erstellen der Labels
	 */
	private Label marktplatzLabel = new Label("Projekt auswählen: ");
	private Label projektNameLabel = new Label("Projektname: ");
	private Label projektBeschreibungLabel = new Label("Projektbeschreibung: ");
	private Label startDateLabel = new Label("Start Datum: ");
	private Label endDateLabel = new Label("End Datum: ");

	/**
	 * Erstellen der ListBox
	 */
	private ListBox projektListbox = new ListBox();

	/**
	 * Erstellen der Buttons
	 */
	private Button projektSpeichernButton = new Button("Speichern", new SpeichernButtonHandler());

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
	 * Konstruktor fpr Bearbeiten von Projekte
	 */
	public ProjektBearbeiten() {

		mainPanel.add(editorPanel);

		editorPanel.add(attributePanel);
		editorPanel.add(datePanel);

		attributePanel.add(marktplatzLabel);
		attributePanel.add(projektListbox);
		projektListbox.addChangeHandler(new OnChangeHandler());
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

		mainPanel.add(projektSpeichernButton);

		try {
			ClientSideSettings.getProjektAdministration().findAllProjektByTeilnehmerId(
					ClientSideSettings.getCurrentUser().getId(), new GetAllProjekteByIDCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}

	}

	private class GetAllProjekteByIDCallback implements AsyncCallback<Vector<Projekt>> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");
		}

		public void onSuccess(Vector<Projekt> result) {

			for (int i = 0; i < result.size(); i++) {
				Projekt p1 = result.elementAt(i);
				pVector.add(p1);
				projektListbox.addItem(p1.getName());
			}
			projektNameBox.setText(pVector.elementAt(projektListbox.getSelectedIndex()).getName());
			projektBeschreibungArea.setText(pVector.elementAt(projektListbox.getSelectedIndex()).getBeschreibung());
			startPicker.setValue(pVector.elementAt(projektListbox.getSelectedIndex()).getStartDatum());
			startPicker.setCurrentMonth(pVector.elementAt(projektListbox.getSelectedIndex()).getStartDatum());
			endPicker.setValue(pVector.elementAt(projektListbox.getSelectedIndex()).getEndDatum());
			endPicker.setCurrentMonth(pVector.elementAt(projektListbox.getSelectedIndex()).getEndDatum());
		}
	}

	/**
	 * Handler, der auf Änderungen eingeht -> Projekt Attribute aktuallisieren
	 */
	private class OnChangeHandler implements ChangeHandler {

		@Override
		public void onChange(ChangeEvent event) {
			try {
				projektNameBox.setText(pVector.elementAt(projektListbox.getSelectedIndex()).getName());
				projektBeschreibungArea.setText(pVector.elementAt(projektListbox.getSelectedIndex()).getBeschreibung());
				startPicker.setValue(pVector.elementAt(projektListbox.getSelectedIndex()).getStartDatum());
				startPicker.setCurrentMonth(pVector.elementAt(projektListbox.getSelectedIndex()).getStartDatum());
				endPicker.setValue(pVector.elementAt(projektListbox.getSelectedIndex()).getEndDatum());
				endPicker.setCurrentMonth(pVector.elementAt(projektListbox.getSelectedIndex()).getEndDatum());
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}

		}

	}

	/**
	 * ClickHandler zum Speichern von Änderungen
	 */
	private class SpeichernButtonHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			try {
				int id = pVector.elementAt(projektListbox.getSelectedIndex()).getId();
				Projekt p = new Projekt();
				p.setId(id);
				p.setName(projektNameBox.getText());
				p.setBeschreibung(projektBeschreibungArea.getText());
				p.setStartDatum(new java.sql.Date((startPicker.getValue()).getTime()));
				p.setEndDatum(new java.sql.Date((endPicker.getValue()).getTime()));
				ClientSideSettings.getProjektAdministration().updateProjekt(p, new SpeichernCallback());

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}
	};

	/**
	 * Callback für Speichern
	 */
	private class SpeichernCallback implements AsyncCallback<Void> {

		public void onFailure(Throwable caught) {
			Window.alert("Da ist wohl etwas schief gelaufen");

		}

		public void onSuccess(Void result) {
			RootPanel.get("Content").clear();
			RootPanel.get("Content").add(new ProjektVerwalten());

		}

	}
}
