package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Projektmarktplatz;


public class AusschreibungBearbeiten extends VerticalPanel {
	
	

	private Vector <Ausschreibung> aVector = new Vector<Ausschreibung>();
		/**
		 * Erstellen der Panels
		 */
		private VerticalPanel mainPanel = this;
		private VerticalPanel editorPanel = new VerticalPanel();
		private VerticalPanel attributePanel = new VerticalPanel();

		/**
		 * Erstellen der Labels
		 */
		private Label ausschreibungLabel = new Label ("Zu ändernde Ausschreibung");
		private Label ausschreibungTitelLabel = new Label ("Titel der Ausschreibung: ");
		private Label stellenbeschreibungLabel = new Label ("Stellenbeschreibung: ");
		private Label bewerbungsfristLabel = new Label ("Bewerbungsfrist ändern: ");
		
		private ListBox ausschreibungListbox = new ListBox();
		
		private TextBox ausschreibungTitelBox = new TextBox(); 
		private TextBox stellenbeschreibungBox = new TextBox();
		
		private DatePicker bewerbungsfrist = new DatePicker();
	

		/**
		 * Erstellen der Buttons
		 */
		private Button ausschreibungAndernButton = new Button("Aenderungen speichern", new SpeichernClickHandler());
		
		/**
		 * Konstruktor für Anlegen der GUI
		 */
		public AusschreibungBearbeiten() {
		
			// CSS Styling
			ausschreibungTitelLabel.addStyleName("Content-Label");
			stellenbeschreibungLabel.addStyleName("Content-Label");
			bewerbungsfristLabel.addStyleName("Content-label");

			mainPanel.add(editorPanel);

			editorPanel.add(attributePanel);
			
			attributePanel.add(ausschreibungLabel);
			attributePanel.add(ausschreibungListbox);
			ausschreibungListbox.addChangeHandler(new OnChangeHandler());
			
			attributePanel.add(ausschreibungTitelLabel);
			attributePanel.add(ausschreibungTitelBox);
		
			
			attributePanel.add(stellenbeschreibungLabel);
			attributePanel.add(stellenbeschreibungBox);
			
			attributePanel.add(bewerbungsfristLabel);
			attributePanel.add(bewerbungsfrist);
			
			mainPanel.add(ausschreibungAndernButton);
			
			try {
				ClientSideSettings.getProjektAdministration().findAllAusschreibungen(new GetAllAusschreibungenCallback());
			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}
		
		private class OnChangeHandler implements ChangeHandler {

			@Override
			public void onChange(ChangeEvent event) {
				try {
					ausschreibungTitelBox.setText(aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getTitel());
				} catch (Exception e){
					Window.alert(e.toString());
					e.printStackTrace();
				}
				
			}
			
		}
		
		private class GetAllAusschreibungenCallback implements AsyncCallback<Vector<Ausschreibung>> {

			public void onFailure(Throwable caught) {
				Window.alert("Läuft garnit");
			}

			public void onSuccess(Vector<Ausschreibung> result) {
				for (Ausschreibung a : result) {
					ausschreibungListbox.addItem(a.getTitel());	
				}
				for (int i = 0; i < result.size(); i++){
					Ausschreibung aus = result.elementAt(i);
					aVector.add(aus);
				}
				ausschreibungTitelBox.setText(aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getTitel());

			}
		}

		private class SpeichernClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) {
				try {
					int id = aVector.elementAt(ausschreibungListbox.getSelectedIndex()).getId();
					Ausschreibung a = new Ausschreibung();
					a.setId(id);
					a.setTitel(ausschreibungTitelBox.getText());
					ClientSideSettings.getProjektAdministration().updateAusschreibung(a, new SpeichernCallback());

				} catch (Exception e) {
					Window.alert(e.toString());
					e.printStackTrace();
				}
			}
		};

		private class SpeichernCallback implements AsyncCallback {

			public void onFailure(Throwable caught) {
				Window.alert("Dat läuft noch nit so!");

			}

			public void onSuccess(Object result) {
				RootPanel.get("Content").clear();

			}

		}
		


	}


