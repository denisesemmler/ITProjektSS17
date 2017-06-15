package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.Teilnehmer;

/**
* The client-side stub for the RPC service.
*/
@RemoteServiceRelativePath("login")
public interface LoginService  extends RemoteService{

	public Teilnehmer login(String requestUri) throws Exception;
	

}
