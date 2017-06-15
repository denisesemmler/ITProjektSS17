package de.hdm.itprojekt.server.login;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.client.rpc.IsSerializable;

import de.hdm.itprojekt.client.gui.ClientSideSettings;
import de.hdm.itprojekt.server.db.TeilnehmerMapper;
import de.hdm.itprojekt.shared.LoginService;
import de.hdm.itprojekt.shared.bo.Teilnehmer;

/**
 * Servlet, das den Login über die GoogleAccountsAPI verwaltet.
 */


public class LoginServiceImpl  extends RemoteServiceServlet implements LoginService {
	
	private static final long serialVersionUID = 1L;
	
	
	@SuppressWarnings("deprecation")
	public Teilnehmer login(String requestUri) {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		Teilnehmer logInf = new Teilnehmer();

		if (user != null) {
			Teilnehmer existingPerson = TeilnehmerMapper.teilnehmerMapper().findByEmail(user.getEmail());
			
			
			if(existingPerson != null){
				ClientSideSettings.getLogger().severe("Userobjekt E-Mail = " + user.getEmail()
						+ "  Bestehender User: E-Mail  =" + existingPerson.getEmail());

				existingPerson.setLoggedIn(true);
				existingPerson.setLogoutUrl(userService.createLogoutURL(requestUri));
				existingPerson.setExisting(true);
				return existingPerson;

			}

			
			logInf.setLoggedIn(true);
			//logInf.setName(user.getNickname());
			logInf.setLogoutUrl(userService.createLogoutURL(requestUri));
			logInf.setEmail(user.getEmail());
			logInf.setExisting(false);
		} else {
			logInf.setLoggedIn(false);
			logInf.setLoginUrl(userService.createLoginURL(requestUri));
			logInf.setLogoutUrl(userService.createLogoutURL(requestUri));
		}
		return logInf;
	}
	/*public Teilnehmer login(String requestUri) throws Exception {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		Teilnehmer t = new Teilnehmer();
		
		if (user != null) {
			//bestehender Teilnehmer?
			Teilnehmer bestehendesProfil = TeilnehmerMapper.teilnehmerMapper().findByEmail(user.getEmail());
			if(bestehendesProfil.getEmail() != null){
				bestehendesProfil.setLoggedIn(true);
				bestehendesProfil.setLogoutUrl(userService.createLogoutURL(requestUri));
				bestehendesProfil.setEmail(user.getEmail());
				return bestehendesProfil;
			}
			//if (bestehendesProfil == null && user != null)
			t.setLoggedIn(true);
			t.setEmail(user.getEmail());
			t.setLogoutUrl(userService.createLogoutURL(requestUri));
			TeilnehmerMapper.teilnehmerMapper().insert(t);
		//if(user == null)
		}else{
			t.setLoggedIn(false);
		}
		t.setLoginUrl(userService.createLoginURL(requestUri));
		return t;
	}*/
	
}

