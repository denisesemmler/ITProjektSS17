package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.LoginService;
import de.hdm.itprojekt.shared.bo.Teilnehmer;


/**
 * Das asynchrone Gegenstück des Interface @link {@link LoginService}

 */
public interface LoginServiceAsync {
	/**
	 * @see de.server.login.LoginServiceImpl#login(String);
	 */
	void login(String requestUri, AsyncCallback<Teilnehmer>callback);
	
}
