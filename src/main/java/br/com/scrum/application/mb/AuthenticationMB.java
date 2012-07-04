package br.com.scrum.application.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.CredentialsImpl;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.management.IdmAuthenticator;
import org.jboss.solder.logging.Logger;
import org.picketlink.idm.impl.api.PasswordCredential;
import org.picketlink.idm.impl.api.model.SimpleUser;

import br.com.scrum.domain.entity.User;
import br.com.scrum.domain.service.UserService;

@Named
@SessionScoped
public class AuthenticationMB extends BaseAuthenticator implements Authenticator, Serializable {	

	@Inject private UserService userService;
	@Inject private CredentialsImpl credentials;
	@Inject private Identity identity;
	@Inject private Logger logger;
	@Inject private Event<User> loginEventSrc;

	@Override
	public void authenticate() {
		logger.info("Logging in " + credentials.getUsername());
		User user = userService.getUserByCredential(credentials.getUsername(), credentials.getPassword());
		if (user != null && credentials.getCredential() instanceof PasswordCredential && 
				user.getPassword().equals(((PasswordCredential) credentials.getCredential()).getValue())) {
			loginEventSrc.fire(user);
			super.setStatus(AuthenticationStatus.SUCCESS);
			identity.addRole(credentials.getUsername(), "USERS", "GROUP");
			super.setUser(new SimpleUser(user.getLogin()));
			redirectToViewId("/main/main.jsf");
			return;
		}

		setStatus(AuthenticationStatus.FAILURE);
		redirectToLoginIfNotLoggedIn();
	}

	public void logout() {
		String userKey = identity.getUser().getKey();
		identity.setAuthenticatorClass(IdmAuthenticator.class);
		identity.logout();
		logger.info("User logged out " + userKey);
	}

	@Override
	public AuthenticationStatus getStatus() {
		return super.getStatus();
	}

	public boolean isLoggedIn() {
		return getStatus().equals(AuthenticationStatus.SUCCESS);
	}

	public void redirectToLoginIfNotLoggedIn() {
		if (!isLoggedIn()) {
			redirectToViewId("/login.jsf");
		}
	}

	private void redirectToViewId(String viewId) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect(externalContext.getRequestContextPath() + viewId);
		} catch (IOException e) {
			logger.error(e.getCause().getMessage());
		}
	}

	private static final long serialVersionUID = 8944449513389432047L;

}
