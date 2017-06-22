package de.hdm.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.Teilnehmer;




public class AnmeldeFormular extends VerticalPanel{
	
		private VerticalPanel mainPanel = this;
		private VerticalPanel labelsPanel = new VerticalPanel();
		
		private Label firstNameLabel = new Label("Vorname: "); 
	 	private TextBox firstNameBox = new TextBox(); 
	 	private Label lastNameLabel = new Label("Nachname: ");
	 	private TextBox lastNameBox = new TextBox(); 
	 	private Label zusatzLabel = new Label("Zusatz: ");
	 	private TextBox zusatzBox = new TextBox(); 
	 	private Label strasseLabel = new Label("Straße: ");
	 	private TextBox strasseBox = new TextBox(); 
	 	private Label plzLabel = new Label("PLZ: ");
	 	private TextBox plzBox = new TextBox(); 
	 	private Label ortLabel = new Label("Ort: ");
	 	private TextBox ortBox = new TextBox(); 
	 
	 	private Button weiter = new Button("Weiter", new CreateTeilnehmerClickHandler());
	 	
	 	
	 	public AnmeldeFormular() {
	 		
	 		//CSS Styling
	 		firstNameLabel.addStyleName("Content-label");
	 		lastNameLabel.addStyleName("Content-label");
	 		zusatzLabel.addStyleName("Content-label");
	 		strasseLabel.addStyleName("Content-label");
	 		plzLabel.addStyleName("Content-label");
	 		ortLabel.addStyleName("Content-label");
	 		firstNameBox.addStyleName("gwt-TextBox");
	 		lastNameBox.addStyleName("gwt-TextBox");
	 
	 	
	 		
	 		mainPanel.add(labelsPanel);
	 		
	 		labelsPanel.add(new HTML(
					"<p>Willkommen bei Pr0ject, es scheint als ob du noch nicht bei uns angemeldet bist, "
					+ "bitte gebe deine Daten hier ein!</p><br />"));
	 		
	 		//Elemente hinzufügen
	 		labelsPanel.add(firstNameLabel);
	 		labelsPanel.add(firstNameBox);
	 		
	 		labelsPanel.add(lastNameLabel);
	 		labelsPanel.add(lastNameBox);
	 		
	 		labelsPanel.add(zusatzLabel);
	 		labelsPanel.add(zusatzBox);
	 		
	 		labelsPanel.add(strasseLabel);
	 		labelsPanel.add(strasseBox);
	 		
	 		labelsPanel.add(plzLabel);
	 		labelsPanel.add(plzBox);
	 		
	 		labelsPanel.add(ortLabel);
	 		labelsPanel.add(ortBox);
	 			 		
	 		labelsPanel.add(weiter);
	 	}
	 	
	 	
	 	
	 	private class CreateTeilnehmerClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) {
				
				try {
					ClientSideSettings.getProjektAdministration().createTeilnehmer(firstNameBox.getText(),
							lastNameBox.getText(), zusatzBox.getText(), strasseBox.getText(), Integer.parseInt(plzBox.getText()),
							ortBox.getText(), ClientSideSettings.getCurrentUser().getEmail(), 1, 2,  new CreateTeilnehmerCallback());
					ClientSideSettings.getCurrentUser().setExisting(true);
					
				} catch (Exception e) {
					Window.alert(e.toString());
					e.printStackTrace();
				}

		
				}
		};
	 	
		private class CreateTeilnehmerCallback implements AsyncCallback<Teilnehmer> {

			public void onFailure(Throwable caught) {
				Window.alert("Dat läuft noch nit so!");

			}

			public void onSuccess(Teilnehmer result) {
				ClientSideSettings.getCurrentUser().setId(result.getId());
				ClientSideSettings.getCurrentUser().setVorname(result.getVorname());
				ClientSideSettings.getCurrentUser().setNachname(result.getNachname());
				ClientSideSettings.getCurrentUser().setZusatz(result.getZusatz());
				ClientSideSettings.getCurrentUser().setStrasse(result.getStrasse());
				ClientSideSettings.getCurrentUser().setPlz(result.getPlz());
				ClientSideSettings.getCurrentUser().setOrt(result.getOrt());
				ClientSideSettings.getProjektAdministration().createProfil(ClientSideSettings.getCurrentUser().getId(), new CreateProfilCallback());
				Window.alert("Deine Daten wurden gespeichert!");
			
			}

		}
		
		private class CreateProfilCallback implements AsyncCallback {

			public void onFailure(Throwable caught) {
				Window.alert("Dat läuft noch nit so!");

			}

			public void onSuccess(Object result) {
			
				Window.alert("Dein Profil wurde gespeichert!");
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(
						new ProfilAnlegen());
			}

		}
	
}
