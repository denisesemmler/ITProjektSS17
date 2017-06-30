package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Projektmarktplatz;

/**
 * 
 * @author Patricia
 *
 */

public class BewerbungBewerten extends HorizontalPanel {

	//Panels erstellen
	private HorizontalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();

	/*
	 * Insatnzvariablen zu Speicherung der ErgebnisVektoren und späteren
	 * Zuweisung für die ListBoxen
	 */
	private Vector<Projektmarktplatz> projektmarktplätze = new Vector<Projektmarktplatz>();
	private Vector<Projekt> projekte = new Vector<Projekt>();
	private Vector<Ausschreibung> ausschreibungen = new Vector<Ausschreibung>();
	private Vector<Bewerbung> bewerbungen = new Vector<Bewerbung>();

	// Dropdown Menü Instanzvariablen
	private ListBox marktplatzListBox = new ListBox();
	private ListBox projektListBox = new ListBox();
	private ListBox ausschreibungListBox = new ListBox();
	private ListBox bewerbungListBox = new ListBox();

	// Textboxen Instanzvariablen zum befüllen mit Textinhalten
	private TextBox bewertungInBox = new TextBox();
	private TextBox stellungnahmeInBox = new TextBox();
	private DatePicker startdatumInBox = new DatePicker();
	private DatePicker enddatumInBox = new DatePicker();
	private TextBox manntageInBox = new TextBox();

	// Überschrifts Panel
	private HTMLPanel bewerbungBewertenPanel = new HTMLPanel(
			"<h2>Wählen Sie hier die zu bewertende Bewerbung aus</h2>");

	// Panel für die Dropdownfelder
	private HTMLPanel projektmarktplatzDropdown = new HTMLPanel("<strong>Projektmarktplatz auswählen</strong>");
	private HTMLPanel projektDropdown = new HTMLPanel("<strong>Projekt auswählen</strong>");
	private HTMLPanel ausschreibungDropdown = new HTMLPanel("<strong>Ausschreibung auswählen</strong>");
	private HTMLPanel bewerbungDropdown = new HTMLPanel("<strong>Bewerbung auswählen</strong>");

	// Label für die aufgerufene Bewerbung
	private HTMLPanel erstellDatum = new HTMLPanel("<strong>Erstell Datum:</strong>");
	private HTMLPanel bewerbungsTitel = new HTMLPanel ("<strong>Bewerbungs Titel:</strong>");
	private HTMLPanel bewerbungsText = new HTMLPanel("<strong>Bewerbungs Text:</strong>");
	private HTMLPanel idBewerbung = new HTMLPanel("<strong>ID Bewerbung:</strong>");
	private HTMLPanel status = new HTMLPanel("<strong>Status:</strong>");
	// private HTMLPanel bewertung = new
	// HTMLPanel("<strong>Bewertung:</strong>");
	// private HTMLPanel stellungnahme = new
	// HTMLPanel("<strong>Stellungnahme:</strong>");

	// Leerzeichen zwischen den Labelen
	private HTMLPanel erstellDatumSpacePanel = new HTMLPanel("&nbsp;");
	private HTMLPanel bewerbungsTitelSpacePanel = new HTMLPanel("&nbsp;");
	private HTMLPanel bewerbungsTextSpacePanel = new HTMLPanel("&nbsp;");
	private HTMLPanel idBewerbungSpacePanel = new HTMLPanel("&nbsp;");
	private HTMLPanel statusSpacePanel = new HTMLPanel("&nbsp;");
	// private HTMLPanel bewertungSpacePanel = new HTMLPanel("&nbsp;");
	// private HTMLPanel stellungnahmeSpacePanel = new HTMLPanel("&nbsp;");

	// Label für die Inhalte der ausgewählten Bewerbung
	private Label erstellDatumWert = new Label("wird befüllt");
	private Label bewerbungsTitelWert = new Label("wird befüllt");
	private Label bewerbungsTextWert = new Label("wird befüllt");
	private Label idBewerbungWert = new Label("wird befüllt");
	private Label statusWert = new Label("wird befüllt");
	// private Label bewertungWert = new Label("wird befüllt");// TODO
	// private Label stellungnahmeWert = new Label("wird befüllt");

	// Label für Textboxen
	private Label bewertungsZahlAbgeben = new Label("Hier bitte Bewertung eintragen (0.0-1.0)");
	private Label stellungnahmeTextAbgeben = new Label("Hier bitte zur Bewertung Stellung beziehen");
	private Label startdatum = new Label("Startdatum");
	private Label enddatum = new Label("Enddatum");
	private Label manntage = new Label("Manntage");

	// Button zum aufrufen der Textboxen
	private Button bewerbungBewerten = new Button("Bewerbung bewerten", new VisibleClickHandler());

	// Button um Bewertung hinzuzufügen der Bewerbung
	private Button bewertungAbgeben = new Button("Bewertung abgeben", new SaveChangesClickHandler());

	/*
	 * gedanklich den Klick aus der Verwaltung von
	 * "RootPanel.get("Content").add(new BewerbungBewerten()); mit dem
	 * Konstruktoraufruf verbinden!
	 */
	public BewerbungBewerten() {

		/*
		 * Mittels dem Projektasync Objekt aus Clientsidsettings, wird die
		 * Operation aufgerufen und ein AsyncCallBack Objekt für die
		 * Verarbeitung der Antwort erzeugt
		 */
		ClientSideSettings.getProjektAdministration().findAllProjektmarktplatz(new AllProjektmarktplatzCallBack());

		// Großes HorizontalPanel Spalte 1 über die ganze Seite
		// TODO

		// Dropdown
		mainPanel.add(editorPanel);
		editorPanel.add(bewerbungBewertenPanel);

		editorPanel.add(projektmarktplatzDropdown);
		editorPanel.add(marktplatzListBox);
		marktplatzListBox.addChangeHandler(new MarktplatzOnChangeHandler());

		editorPanel.add(projektDropdown);
		editorPanel.add(projektListBox);
		projektListBox.addChangeHandler(new ProjektOnChangeHandler());

		editorPanel.add(ausschreibungDropdown);
		editorPanel.add(ausschreibungListBox);
		ausschreibungListBox.addChangeHandler(new AusschreibungOnChangeHandler());

		editorPanel.add(bewerbungDropdown);
		editorPanel.add(bewerbungListBox);
		bewerbungListBox.addChangeHandler(new BewerbungOnChangeHandler());

		// Großes HorizontalPanel Spalte 2 über die ganze Seite
		// TODO

		/*
		 * ausgewählte Bewerbung soll angezeigt werden, dazu werden horizontale
		 * Panels auf vertikale Panels gelegt
		 */
		HorizontalPanel idPanel = new HorizontalPanel();
		idPanel.add(idBewerbung);
		idPanel.add(idBewerbungSpacePanel);
		idPanel.add(idBewerbungWert);
		editorPanel.add(idPanel);

		HorizontalPanel datumPanel = new HorizontalPanel();
		datumPanel.add(erstellDatum);
		datumPanel.add(erstellDatumSpacePanel);
		datumPanel.add(erstellDatumWert);
		editorPanel.add(datumPanel);
		
		HorizontalPanel bewerbungsTitelPanel = new HorizontalPanel();
		bewerbungsTitelPanel.add(bewerbungsTitel);
		bewerbungsTitelPanel.add(bewerbungsTitelSpacePanel);
		bewerbungsTitelPanel.add(bewerbungsTitelWert);
		editorPanel.add(bewerbungsTitelPanel);

		HorizontalPanel bewerbungPanel = new HorizontalPanel();
		bewerbungPanel.add(bewerbungsText);
		bewerbungPanel.add(bewerbungsTextSpacePanel);
		bewerbungPanel.add(bewerbungsTextWert);
		editorPanel.add(bewerbungPanel);

		HorizontalPanel statusPanel = new HorizontalPanel();
		statusPanel.add(status);
		statusPanel.add(statusSpacePanel);
		statusPanel.add(statusWert);
		editorPanel.add(statusPanel);

		// HorizontalPanel bewertungPanel = new HorizontalPanel();
		// bewertungPanel.add(bewertung);
		// bewertungPanel.add(bewertungSpacePanel);
		// bewertungPanel.add(bewertungWert);
		// editorPanel.add(bewertungPanel);
		//
		// HorizontalPanel stellungnahmePanel = new HorizontalPanel();
		// stellungnahmePanel.add(stellungnahme);
		// stellungnahmePanel.add(stellungnahmeSpacePanel);
		// stellungnahmePanel.add(stellungnahmeWert);
		// editorPanel.add(stellungnahmePanel);

		// Button zum mainPanel hinzugefügt, um Textboxen zum befüllen zu
		// erhalten
		editorPanel.add(bewerbungBewerten);

		// Großes HorizontalPanel Spalte 3 über die ganze Seite
		// TODO

		// Zum Verstecken der Textboxen bis es ausgewählt wird
		bewertungsZahlAbgeben.setVisible(false);
		bewertungInBox.setVisible(false);
		bewertungInBox.addKeyUpHandler(new BewertungsKeyUpHandler());
		bewertungInBox.setMaxLength(3);

		startdatum.setVisible(false);
		startdatumInBox.setVisible(false);

		enddatum.setVisible(false);
		enddatumInBox.setVisible(false);

		manntage.setVisible(false);
		manntageInBox.setVisible(false);

		stellungnahmeTextAbgeben.setVisible(false);
		stellungnahmeInBox.setVisible(false);

		// zum Verstecken des Buttons
		bewertungAbgeben.setVisible(false);

		// Textboxen zum befüllen mit Text
		editorPanel.add(bewertungsZahlAbgeben);
		editorPanel.add(bewertungInBox);

		editorPanel.add(startdatum);
		editorPanel.add(startdatumInBox);

		editorPanel.add(enddatum);
		editorPanel.add(enddatumInBox);

		editorPanel.add(manntage);
		editorPanel.add(manntageInBox);

		editorPanel.add(stellungnahmeTextAbgeben);
		editorPanel.add(stellungnahmeInBox);

		// Button zum Bewertung abgeben
		editorPanel.add(bewertungAbgeben);

	}

	// Innerclass Marktplatz Handler
	private class MarktplatzOnChangeHandler implements ChangeHandler {
		/**
		 * Diese Methode wird ausgeführt wenn im Projektkmarktplatz Handler in
		 * der ListBox ein Element ausgewählt wird. Und sich dann die
		 * ProjektListbox zum jeweilig ausgewählten Projekt anpassen/nachladen
		 * soll.
		 */
		@Override
		public void onChange(ChangeEvent event) {
			ClientSideSettings.getProjektAdministration().findProjekteByProjektmarktplatzId(
					Integer.valueOf(marktplatzListBox.getSelectedValue()),
					new FindProjektByProjektmarktplatzCallBack());
		}
	}

	// Innerclass Projekt Handler
	private class ProjektOnChangeHandler implements ChangeHandler {
		/**
		 * Diese Methode wird ausgeführt wenn im Projekt Handler in der ListBox
		 * ein Element ausgewählt wird.
		 */
		@Override
		public void onChange(ChangeEvent event) {
			ClientSideSettings.getProjektAdministration().findAusschreibungByProjektId(
					Integer.valueOf(projektListBox.getSelectedValue()), new FindAusschreibungByProjektIdCallback());
		}

	}

	// Innerclass Ausschreibung Handler
	private class AusschreibungOnChangeHandler implements ChangeHandler {
		/**
		 * Diese Methode wird ausgeführt wenn im Ausschreibung Handler in der
		 * ListBox ein Element ausgewählt wird.
		 */
		@Override
		public void onChange(ChangeEvent event) {

			ClientSideSettings.getProjektAdministration().findBewerbungenByAusschreibungId(
					Integer.valueOf(ausschreibungListBox.getSelectedValue()).intValue(),
					new FindBewerbungByAusschreibungIdCallback());
		}
	}

	// Innerclass Bewerbung Handler
	private class BewerbungOnChangeHandler implements ChangeHandler {
		/**
		 * Diese Methode wird ausgeführt wenn im Bewerbung Handler in der
		 * ListBox ein Element ausgewählt wird.
		 */
		@Override
		public void onChange(ChangeEvent event) {
			int selectedBewerbungsId = Integer.valueOf(bewerbungListBox.getSelectedValue());
			Bewerbung selectedBewerbung = null;

			for (Bewerbung b : bewerbungen) {
				if (b.getId() == selectedBewerbungsId) {
					selectedBewerbung = b;
				}
			}

			if (selectedBewerbung == null) {
				Window.alert("Irgendwas ist mit den bereits geladenen Bewerbungen schief gegangen...");
			} else {

				/*
				 * Hier wird das "wird befüllt" der Bewerbung dann tatsächlich
				 * mit Werten befüllt die statische Methode String.valueof ist
				 * zum ändern der Datentypen Int zu string verantwortlich
				 */
				erstellDatumWert.setText(String.valueOf(selectedBewerbung.getErstellDatum()));
				idBewerbungWert.setText(String.valueOf(selectedBewerbung.getId()));
				bewerbungsTitelWert.setText(selectedBewerbung.getTitel());
				bewerbungsTextWert.setText(selectedBewerbung.getBewerbungsText());
				statusWert.setText(selectedBewerbung.getStatus());
				// bewertungWert.setText(ersteBewerbung.getBewertung());
				// stellungnahmeWert.setText();
			}
		}

	}

	// Innerclass für Bewertungen sichtbar machen button bewerbung bewerten
	private class VisibleClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			// Zum Anzeigen der Bewertung und Stellungnahme durch Klick
			bewertungsZahlAbgeben.setVisible(true);
			bewertungInBox.setVisible(true);

			// zum Verstecken des Buttons
			bewertungAbgeben.setVisible(true);
		}
	}

	// Innerclass für Bewertung abgeben Speicher Button
	private class SaveChangesClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			// Speicherbutton
			ClientSideSettings.getProjektAdministration().bewertungZurBewerbung(
					Integer.valueOf(bewerbungListBox.getSelectedValue()), Float.valueOf(bewertungInBox.getText()),
					stellungnahmeInBox.getText(), Integer.valueOf(projektListBox.getSelectedValue()),
					Integer.valueOf(manntageInBox.getText()), startdatumInBox.getValue(), enddatumInBox.getValue(), new BewerbungBewertenCallback());

			// Zum Verstecken der Bewertung und Stellungnahme nach Eintrag
			bewertungsZahlAbgeben.setVisible(false);
			bewertungInBox.setVisible(false);

			startdatum.setVisible(false);
			startdatumInBox.setVisible(false);

			enddatum.setVisible(false);
			enddatumInBox.setVisible(false);

			manntage.setVisible(false);
			manntageInBox.setVisible(false);

			stellungnahmeTextAbgeben.setVisible(false);
			stellungnahmeInBox.setVisible(false);

			// zum Verstecken des Buttons
			bewertungAbgeben.setVisible(false);
		}

	}

	// Innerclass für AllProjektmarktplatzCallBack RPC Callback
	private class AllProjektmarktplatzCallBack implements AsyncCallback<Vector<Projektmarktplatz>> {

		// Hier wird zuerst das result in der Instanzvariablen gespeichert
		// In der onSucces findet IMMER die Anfragen Behaandlung statt, also was
		// als nächstes geladen werden muss
		@Override
		public void onSuccess(Vector<Projektmarktplatz> result) {
			projektmarktplätze = result;

			/*
			 * hier ist eine Schleife die durch die Instanzvariable
			 * durchiteriert ziel ist es die Bezeichnungen der
			 * Projektmarktplätze in die Listbox zu schreiben.
			 */
			for (Projektmarktplatz projektmarktplatz : result) {
				marktplatzListBox.addItem(projektmarktplatz.getBezeichnung(),
						String.valueOf(projektmarktplatz.getId()));
			}

			// Hier wird die ID zum ersten Marktplatz aus dem ErgebnissVektor
			// rausgeholt.
			int ersterMarktplatz = result.elementAt(0).getId();

			// Hier wird die ID zur Weiterverarbeitung der Callbacks verwendet.
			ClientSideSettings.getProjektAdministration().findProjekteByProjektmarktplatzId(ersterMarktplatz,
					new FindProjektByProjektmarktplatzCallBack());

		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("ERROR - Please try it later again :-)");
		}
	}

	// Innerclass für FindProjektByProjektmarktplatzCallBack
	private class FindProjektByProjektmarktplatzCallBack implements AsyncCallback<Vector<Projekt>> {

		/* Hier wird zuerst das result in der Instanzvariablen gespeichert
		 *In der onSucces findet IMMER die Anfragen Behandlung statt, also was
		 *  als nächstes geladen werden muss(non-Javadoc)
		 */
		@Override
		public void onSuccess(Vector<Projekt> result) {
			projekte = result;

			projektListBox.clear();

			/*
			 * hier ist eine Schleife die durch die Instanzvariable
			 * durchiteriert ziel ist es die Bezeichnungen der
			 * Projektmarktplätze in die Listbox zu schreiben.
			 */
			for (Projekt projekt : result) {
				projektListBox.addItem(projekt.getBeschreibung(), String.valueOf(projekt.getId()));
			}

			// Hier wird die ID zum ersten Projekt aus dem ErgebnissVektor
			// rausgeholt.
			int erstesProjekt = result.elementAt(0).getId();

			// Hier wird die ID zur Weiterverarbeitung der Callbacks verwendet.
			ClientSideSettings.getProjektAdministration().findAusschreibungByProjektId(erstesProjekt,
					new FindAusschreibungByProjektIdCallback());

		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("ERROR - Please try it later again :-)");

		}

	}

	// Innerclass für die FindAusschreibungByProjektIdCallback
	private class FindAusschreibungByProjektIdCallback implements AsyncCallback<Vector<Ausschreibung>> {

		/* Hier wird zuerst das result in der Instanzvariablen gespeichert
		 *In der onSucces findet IMMER die Anfragen Behandlung statt, also was
		 *als nächstes geladen werden muss
		 */
		@Override
		public void onSuccess(Vector<Ausschreibung> result) {
			ausschreibungen = result;

			ausschreibungListBox.clear();

			/*
			 * hier ist eine Schleife die durch die Instanzvariable
			 * durchiteriert ziel ist es die Bezeichnungen der
			 * Projektmarktplätze in die Listbox zu schreiben.
			 */
			for (Ausschreibung ausschreibung : result) {
				ausschreibungListBox.addItem(ausschreibung.getTitel(), String.valueOf(ausschreibung.getId()));
			}

			// Hier wird die ID zum ersten Projekt aus dem ErgebnissVektor
			// rausgeholt.
			int ersteAusschreibung = result.elementAt(0).getId();

			// Hier wird die ID zur Weiterverarbeitung der Callbacks verwendet.
			ClientSideSettings.getProjektAdministration().findBewerbungenByAusschreibungId(ersteAusschreibung,
					new FindBewerbungByAusschreibungIdCallback());

		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("ERROR - Please try it later again :-)");

		}
	}

	// Innerclass für die FindBewerbungByProfilAndAusschreibungIdCallback
	private class FindBewerbungByAusschreibungIdCallback implements AsyncCallback<Vector<Bewerbung>> {

		/* Hier wird zuerst das result in der Instanzvariablen gespeichert
		 * In der onSucces findet IMMER die Anfragen Behaandlung statt, also was
		 * als nächstes geladen werden muss
		 */ 
		@Override
		public void onSuccess(Vector<Bewerbung> result) {

			bewerbungListBox.clear();
			bewerbungen.clear();
			
			bewerbungen.addAll(result);

			// foreach schleife mit einer Map (Key,Value)
			for (Bewerbung b : result) {
				bewerbungListBox.addItem(b.getTitel(), String.valueOf(b.getId()));
			}
			Bewerbung ersteBewerbung = bewerbungen.elementAt(0);

			/*
			 * Hier wird das "wird befüllt" der Bewerbung dann tatsächlich mit
			 * Werten befüllt die statische Methode String.valueof ist zum
			 * ändern der Datentypen Int zu string verantwortlich
			 */
			erstellDatumWert.setText(String.valueOf(ersteBewerbung.getErstellDatum()));
			idBewerbungWert.setText(String.valueOf(ersteBewerbung.getId()));
			bewerbungsTitelWert.setText(ersteBewerbung.getTitel());
			bewerbungsTextWert.setText(ersteBewerbung.getBewerbungsText());
			statusWert.setText(ersteBewerbung.getStatus());
			// bewertungWert.setText(ersteBewerbung.getBewertung());
			// stellungnahmeWert.setText();

		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("ERROR - Please try it later again :-)");
		}

	}

	// Diese Klasse ist für Bewerbung Bewerten
	private class BewerbungBewertenCallback implements AsyncCallback<Void> {

		@Override
		public void onSuccess(Void result) {
			Window.alert("Speicherung war erfolgreich");
			
			//Seite wird hier wieder auf Anfang zurückgesetzt um weitere Bewerbungen zu bewerten
			//TODO 
			RootPanel.get("Content").clear();

		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("ERROR - Please try it later again :-)");
		}

	}

	// Diese Klasse regelt die Zahlenwerte für die Bewertung von 0-1
	private class BewertungsKeyUpHandler implements KeyUpHandler {

		@Override
		public void onKeyUp(KeyUpEvent event) {

			if (bewertungInBox.getText() != "") {
				try {
					// Regelt das die Stellungnahme bei Zahlen größer 1 wegfällt
					Float bewertungsZahl = Float.valueOf(bewertungInBox.getText());
					if (bewertungsZahl > 1.0) {
						stellungnahmeTextAbgeben.setVisible(false);
						stellungnahmeInBox.setVisible(false);
						Window.alert("Nur Zahlen von 0.0 bis 1.0 erlaubt");
						// regelt das die stellungnahmebox bei bewertung 1 dazu
						// kommt
					} else if (bewertungsZahl == 1.0) {
						
						
						startdatum.setVisible(true);
						startdatumInBox.setVisible(true);
						
						enddatum.setVisible(true);
						enddatumInBox.setVisible(true);
						
						manntage.setVisible(true);
						manntageInBox.setVisible(true);
						
						stellungnahmeTextAbgeben.setVisible(true);
						stellungnahmeInBox.setVisible(true);
					}
					// regelt das die stellungnahmebox unter wert 1 wegfällt
					else {
						stellungnahmeTextAbgeben.setVisible(false);
						stellungnahmeInBox.setVisible(false);
					}
				} catch (NumberFormatException e) {
					Window.alert("Nur Zahlen im Format von 0.0 bis 1.0 erlaubt");

				}
			}

		}

	}

}
