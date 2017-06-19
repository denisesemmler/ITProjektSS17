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



public class AusschreibungLoeschen extends VerticalPanel {
	
	private Vector <Ausschreibung> aVector = new Vector<Ausschreibung>();
	
	private VerticalPanel mainPanel = this;
	private Label ausschreibungLabel = new Label("Ausschreibung: ");
	private ListBox ausschreibungListbox = new ListBox();
	
	private Button ausschreibungLoeschenButton = new Button("Loeschen",
			new DeleteButtonHandler());
	
	public AusschreibungLoeschen(){
		mainPanel.add(ausschreibungLabel);
		mainPanel.add(ausschreibungListbox);
				
		mainPanel.add(ausschreibungLoeschenButton);
		
		try {
			ClientSideSettings.getProjektAdministration().findAllAusschreibungByTeilnehmerId(ClientSideSettings.getCurrentUser().getId(),new GetAllAusschreibungenCallback());
		} catch (Exception e) {
			Window.alert(e.toString());
			e.printStackTrace();
		}
	}
	
	private class GetAllAusschreibungenCallback implements AsyncCallback<Vector<Ausschreibung>> {

		public void onFailure(Throwable caught) {
			Window.alert("Läuft garnit");
		}

		public void onSuccess(Vector<Ausschreibung> result) {
		
			for (int i = 0; i < result.size(); i++){
				Ausschreibung aus = result.elementAt(i);
				aVector.add(aus);
				ausschreibungListbox.addItem(aus.getTitel());	
			}
			
		}
	}
	
	private class DeleteButtonHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			try {
				//int id = pVector.elementAt(projektListbox.getSelectedIndex()).getId();
				Ausschreibung a = aVector.elementAt(ausschreibungListbox.getSelectedIndex());
				//p.setId(id);
				ClientSideSettings.getProjektAdministration().deleteAusschreibung(a, new DeleteCallback());

			} catch (Exception e) {
				Window.alert(e.toString());
				e.printStackTrace();
			}
				    	 		    	
	     }
	   };
	

private class DeleteCallback implements AsyncCallback {

	public void onFailure(Throwable caught) {
		Window.alert("Dat läuft noch nit so!");

	}

	public void onSuccess(Object result) {
		RootPanel.get("Content").clear();

	}

}
}
