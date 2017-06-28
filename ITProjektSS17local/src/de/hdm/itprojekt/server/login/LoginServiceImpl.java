package de.hdm.itprojekt.server.login;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.client.gui.ClientSideSettings;
import de.hdm.itprojekt.server.db.TeilnehmerMapper;
import de.hdm.itprojekt.shared.LoginService;
import de.hdm.itprojekt.shared.bo.Teilnehmer;

/**
 * Servlet, das den Login über die GoogleAccountsAPI verwaltet.
 * 
 * @author Philipp Mueller
 */

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	private static final long serialVersionUID = 1L;

	/**
	 * @param Domain
	 *            der Startseite
	 * @return neues oder eingeloggtes Profil
	 */
	@SuppressWarnings("deprecation")
	public Teilnehmer login(String requestUri) {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		Teilnehmer logInf = new Teilnehmer();

		if (user != null) {
			Teilnehmer existingPerson = TeilnehmerMapper.teilnehmerMapper().findByEmail(user.getEmail());

			if (existingPerson != null) {
				ClientSideSettings.getLogger().severe("Userobjekt E-Mail = " + user.getEmail()
						+ "  Bestehender User: E-Mail  =" + existingPerson.getEmail());

				existingPerson.setLoggedIn(true);
				existingPerson.setLogoutUrl(userService.createLogoutURL(requestUri));
				existingPerson.setExisting(true);
				return existingPerson;

			}

			logInf.setLoggedIn(true);
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
}
