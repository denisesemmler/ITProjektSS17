package de.hdm.itprojekt.client.gui;

import java.util.Map;
import java.util.Set;
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
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Projektmarktplatz;
import de.hdm.itprojekt.shared.bo.Teilnehmer;

/**
 * 
 * @author Patricia
 *
 */

public class BewerbungBewerten extends VerticalPanel {

	private VerticalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();

	/*
	 *  Insatnzvariablen zu Speicherung der ErgebnisVektoren und späteren
	 *  Zuweisung für die ListBoxen
	 */
	private Vector<Projektmarktplatz> projektmarktplätze;
	private Vector<Projekt> projekte;
	private Vector<Ausschreibung> ausschreibungen;
	private Vector<Bewerbung> bewerbungen;

	// Dropdown Menü
	private ListBox marktplatzListBox = new ListBox();
	private ListBox projektListBox = new ListBox();
	private ListBox ausschreibungListBox = new ListBox();
	private ListBox bewerbungListBox = new ListBox();

	// Überschrifts Label
	private Label bewerbungBewertenLabel = new Label("Wählen Sie hier die zu bewertende Bewerbung aus");

	// Label für die aufgerufene Bewerbung
	private Label erstellDatum = new Label("Erstell Datum");
	private Label bewerbungsText = new Label("Bewerbungs Text");
	private Label idBewerbung = new Label("ID Bewerbung");
	private Label bewertung = new Label("Bewertung");
	private Label status = new Label("Status");
	private Label stellungnahme = new Label("Stellungnahme");

	// Button zum speichern
	private Button bewertungAbgebenButton = new Button("Bewertung abgeben", new SaveChangesClickHandler());

	/*
	 * gedanklich den Klick aus der Verwaltung von
	 * "RootPanel.get("Content").add(new BewerbungBewerten()); mit dem
	 * Konstruktoraufruf verbinden!
	 */
	public BewerbungBewerten() {
		/*
		 * Mittels dem Projektasync Objekt aus Clientsidsettings, wird die Operation aufgerufen 
		 * und ein AsyncCallBack Objekt für die Verarbeitung der Antwort erzeugt
		 */
		ClientSideSettings.getProjektAdministration().findAllProjektmarktplatz(new AllProjektmarktplatzCallBack());

		mainPanel.add(editorPanel);
		editorPanel.add(bewerbungBewertenLabel);

		editorPanel.add(marktplatzListBox);
		marktplatzListBox.addChangeHandler(new MarktplatzOnChangeHandler());

		editorPanel.add(projektListBox);
		projektListBox.addChangeHandler(new ProjektOnChangeHandler());

		editorPanel.add(ausschreibungListBox);
		projektListBox.addChangeHandler(new AusschreibungOnChangeHandler());

		editorPanel.add(bewerbungListBox);
		projektListBox.addChangeHandler(new BewerbungOnChangeHandler());

		/*
		 * ausgewählte Bewerbung soll angezeigt werden, dazu werden horizontale
		 * Panels auf vertikale Panels gelegt
		 */
		HorizontalPanel idPanel = new HorizontalPanel();
		idPanel.add(idBewerbung);
		editorPanel.add(idPanel);

		HorizontalPanel datumPanel = new HorizontalPanel();
		datumPanel.add(erstellDatum);
		editorPanel.add(datumPanel);

		HorizontalPanel bewerbungPanel = new HorizontalPanel();
		bewerbungPanel.add(bewerbungsText);
		editorPanel.add(bewerbungPanel);

		HorizontalPanel statusPanel = new HorizontalPanel();
		statusPanel.add(status);
		editorPanel.add(statusPanel);

		HorizontalPanel bewertungPanel = new HorizontalPanel();
		bewertungPanel.add(bewertung);
		editorPanel.add(bewertungPanel);

		HorizontalPanel stellungnahmePanel = new HorizontalPanel();
		stellungnahmePanel.add(stellungnahme);
		editorPanel.add(stellungnahmePanel);

		// Button zum mainPanel hinzugefügt
		mainPanel.add(bewertungAbgebenButton);

	}

	// Innerclass Marktplatz Handler
	private class MarktplatzOnChangeHandler implements ChangeHandler {
		/**
		 * Diese Methode wird ausgeführt wenn im Projektkmarktplatz Handler in
		 * der ListBox ein Element ausgewählt wird. Und sich dann die ProjektListbox
		 * zum jeweilig ausgewählten Projekt anpassen/nachladen soll.
		 */
		@Override
		public void onChange(ChangeEvent event) {
			Window.alert(marktplatzListBox.getSelectedValue());
			//ClientSideSettings.getProjektAdministration().findProjekteByProjektmarktplatzId(projektmarktplatzId, callback);
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
			//TODO
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
			//TODO
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
			//TODO
		}

	}

	// Innerclass Bewertung abgeben Speicher Button Handler
	private class SaveChangesClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			//TODO
		}

	}

	// Innerclass für AllProjektmarktplatzCallBack RPC Callback
	private class AllProjektmarktplatzCallBack implements AsyncCallback<Vector<Projektmarktplatz>> {

		//Hier wird zuerst das result in der Instanzvariablen gespeichert
		//In der onSucces findet IMMER die Anfragen Behaandlung statt, also was als nächstes geladen werden muss
		@Override
		public void onSuccess(Vector<Projektmarktplatz> result) {
			projektmarktplätze = result;
			
			/*
			 * hier ist eine Schleife die durch die Instanzvariable durchiteriert
			 * ziel ist es die Bezeichnungen der Projektmarktplätze in die Listbox zu schreiben.
			 */
			for (Projektmarktplatz projektmarktplatz : result) {
				marktplatzListBox.addItem(projektmarktplatz.getBezeichnung(),String.valueOf(projektmarktplatz.getId()));
			}
			
			//Hier wird die ID zum ersten Marktplatz aus dem ErgebnissVektor rausgeholt.
			int ersterMarktplatz = result.elementAt(0).getId();
			
			//Hier wird die ID zur Weiterverarbeitung der Callbacks verwendet.
			ClientSideSettings.getProjektAdministration().findProjekteByProjektmarktplatzId(ersterMarktplatz, new FindProjektByProjektmarktplatzCallBack());
			
		}

		@Override
		public void onFailure(Throwable caught) {
			//TODO
		}

	}
	
	
	// Innerclass für FindProjektByProjektmarktplatzCallBack
	private class FindProjektByProjektmarktplatzCallBack implements AsyncCallback<Vector<Projekt>> {
		
		//Hier wird zuerst das result in der Instanzvariablen gespeichert
		//In der onSucces findet IMMER die Anfragen Behaandlung statt, also was als nächstes geladen werden muss
		@Override
		public void onSuccess(Vector<Projekt> result) {
			projekte = result;
			
			/*
			 * hier ist eine Schleife die durch die Instanzvariable durchiteriert
			 * ziel ist es die Bezeichnungen der Projektmarktplätze in die Listbox zu schreiben.
			 */
			for (Projekt projekt : result) {
				projektListBox.addItem(projekt.getBeschreibung(), String.valueOf(projekt.getId()));
			}
			
			//Hier wird die ID zum ersten Projekt aus dem ErgebnissVektor rausgeholt.
			int erstesProjekt = result.elementAt(0).getId();
			
			//Hier wird die ID zur Weiterverarbeitung der Callbacks verwendet.
			ClientSideSettings.getProjektAdministration().findAusschreibungByProjektId(erstesProjekt, new FindAusschreibungByProjektIdCallback());
			
		}
		
		@Override
		public void onFailure(Throwable caught) {
			// 
			
		}
		
	}
	
	//Innerclass für die FindAusschreibungByProjektIdCallback
	private class FindAusschreibungByProjektIdCallback implements AsyncCallback<Vector<Ausschreibung>> {
		
		//Hier wird zuerst das result in der Instanzvariablen gespeichert
		//In der onSucces findet IMMER die Anfragen Behaandlung statt, also was als nächstes geladen werden muss
		@Override
		public void onSuccess(Vector<Ausschreibung> result) {
			ausschreibungen = result;
			
			/*
			 * hier ist eine Schleife die durch die Instanzvariable durchiteriert
			 * ziel ist es die Bezeichnungen der Projektmarktplätze in die Listbox zu schreiben.
			 */
			for (Ausschreibung ausschreibung : result) {
				ausschreibungListBox.addItem(ausschreibung.getBeschreibung(), String.valueOf(ausschreibung.getId()));
			}
			
			//Hier wird die ID zum ersten Projekt aus dem ErgebnissVektor rausgeholt.
			int ersteAusschreibung = result.elementAt(0).getId();
			
			//Hier wird die ID zur Weiterverarbeitung der Callbacks verwendet.
			ClientSideSettings.getProjektAdministration().findBewerbungenTeilnehmerByAusschreibungId(ersteAusschreibung, new FindBewerbungByAusschreibungIdCallback());
			
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO
			
		}
	}
	
	
	//Innerclass für die FindBewerbungByAusschreibungIdCallback
	private class FindBewerbungByAusschreibungIdCallback implements AsyncCallback<Map<Bewerbung,Teilnehmer>> {

		//Hier wird zuerst das result in der Instanzvariablen gespeichert
		//In der onSucces findet IMMER die Anfragen Behaandlung statt, also was als nächstes geladen werden muss
		@Override
		public void onSuccess(Map<Bewerbung,Teilnehmer> result) {
			
			Window.alert("ich bin in der succes Methode von Bewerbung Bewerten");
			
			for (Map.Entry<Bewerbung, Teilnehmer> entry : result.entrySet()){
				Teilnehmer teilnehmerZuBewerbung = entry.getValue();
				String anzeigeFuerListbox = teilnehmerZuBewerbung.getVorname() + " " + teilnehmerZuBewerbung.getNachname();
				Window.alert(anzeigeFuerListbox);
				bewerbungListBox.addItem(anzeigeFuerListbox, String.valueOf(entry.getKey().getId()));
				bewerbungen.add(entry.getKey());
			}
			
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}


		
	}

}
