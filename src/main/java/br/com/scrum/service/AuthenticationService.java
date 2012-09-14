package br.com.scrum.service;
//package br.com.scrum.service;
//
//import java.io.Serializable;
//
//import javax.enterprise.context.SessionScoped;
//import javax.enterprise.event.Event;
//import javax.inject.Inject;
//import javax.inject.Named;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.PersistenceContextType;
//
//import org.jboss.seam.international.status.Messages;
//import org.jboss.seam.security.Authenticator;
//import org.jboss.seam.security.BaseAuthenticator;
//import org.jboss.seam.security.CredentialsImpl;
//import org.jboss.solder.logging.Logger;
//import org.picketlink.idm.impl.api.PasswordCredential;
//import org.picketlink.idm.impl.api.model.SimpleUser;
//
//import br.com.scrum.domain.entity.User;
//
//@Named
//@SessionScoped
//public class AuthenticationService extends BaseAuthenticator implements Authenticator, Serializable {	
//
//	@PersistenceContext(type = PersistenceContextType.EXTENDED)
//	protected EntityManager entityManager;
//	
//	@Inject private UserService userService;
//	@Inject private CredentialsImpl credentials;
//	@Inject private Messages messages;
//	@Inject private Logger logger;
//	@Inject private Event<User> loginEventSrc;
//
//	@Override
//	public void authenticate() {
//		logger.info("Logging in " + credentials.getUsername());
//		if ((credentials.getUsername() == null) || (credentials.getCredential() == null)) {
//			messages.error("Invalid username or password");
//			setStatus(AuthenticationStatus.FAILURE);
//		}
//		User user = userService.getUserByCredential(credentials.getUsername(), credentials.getPassword());
//		if (user != null && credentials.getCredential() instanceof PasswordCredential && 
//			user.getPassword().equals(((PasswordCredential) credentials.getCredential()).getValue())) {
//			loginEventSrc.fire(user);
//			messages.info("You're signed in as ", user.getName());
//			setStatus(AuthenticationStatus.SUCCESS);
//			setUser(new SimpleUser(user.getLogin())); 
//			return;
//		}
//
//		messages.error("Invalid username or password for ", user.getLogin());
//		setStatus(AuthenticationStatus.FAILURE);
//	}
//
////	public void logout() {
////		String userKey = identity.getUser().getKey();
////		identity.setAuthenticatorClass(IdmAuthenticator.class);
////		identity.logout();
////		logger.info("User logged out [{}]" + userKey);
////	}
//
//	public EntityManager getEntityManager() {
//		return entityManager;
//	}
//
//	private static final long serialVersionUID = 8944449513389432047L;
//
//}
