package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


import de.hdm.itprojekt.shared.bo.Projektmarktplatz;

public class ProjektmarktplatzBearbeiten extends VerticalPanel {
	
	private Vector <Projektmarktplatz> pmVector = new Vector<Projektmarktplatz>();

	private VerticalPanel mainPanel = this;
	private VerticalPanel editorPanel = new VerticalPanel();

	private Label marktplatzLabel = new Label("Projektmarktplatz auswählen");
	private Label marktplatzNameLabel = new Label("Marktplatzname: ");
	private ListBox marktplatzListbox = new ListBox();
	private TextBox marktplatzNameBox = new TextBox();

	private Button speichernButton = new Button("Speichern", new SaveChangesClickHandler());

	public ProjektmarktplatzBearbeiten() {

		// CSS Styles
		speichernButton.setStylePrimaryName("grotte-button");

		mainPanel.add(editorPanel);
		editorPanel.add(marktplatzLabel);
		editorPanel.add(marktplatzListbox);
		marktplatzListbox.addChangeHandler(new OnChangeHandler());
		editorPanel.add(marktplatzNameLabel);
		editorPanel.add(marktplatzNameBox);

		editorPanel.add(speichernButton);

		try {
			ClientSideSettings.getProjektAdministration().findAllProjektmarktplatz(new GetAllMarktplatzCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}

	}

	private class SaveChangesCallback implements AsyncCallback {

		public void onFailure(Throwable caught) {
			Window.alert("Dat läuft noch nit so!");

		}

		public void onSuccess(Object result) {
			RootPanel.get("Content").clear();

		}

	}

	private class GetAllMarktplatzCallback implements AsyncCallback<Vector<Projektmarktplatz>> {

		public void onFailure(Throwable caught) {
			Window.alert("Läuft garnit");
		}

		public void onSuccess(Vector<Projektmarktplatz> result) {
			for (Projektmarktplatz p : result) {
				marktplatzListbox.addItem(p.getBezeichnung());	
			}
			for (int i = 0; i < result.size(); i++){
				Projektmarktplatz pm1 = result.elementAt(i);
				pmVector.add(pm1);
			}
			marktplatzNameBox.setText(pmVector.elementAt(marktplatzListbox.getSelectedIndex()).getBezeichnung());
			
		}
	}

	private class SaveChangesClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			try {
				int id = pmVector.elementAt(marktplatzListbox.getSelectedIndex()).getId();
				Projektmarktplatz pm = new Projektmarktplatz();
				pm.setId(id);
				pm.setBezeichnung(marktplatzNameBox.getText());
				ClientSideSettings.getProjektAdministration().updateProjektmarktplatz(pm, new SaveChangesCallback());

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
		}
	};
	
	private class OnChangeHandler implements ChangeHandler {

		@Override
		public void onChange(ChangeEvent event) {
			try {
				marktplatzNameBox.setText(pmVector.elementAt(marktplatzListbox.getSelectedIndex()).getBezeichnung());
				
			} catch (Exception e){
				Window.alert(e.toString());
				e.printStackTrace();
			}
			
		}
		
	}

}
