package br.com.scrum.util.exception;

import org.jboss.seam.security.AuthorizationException;
import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;
import org.jboss.solder.logging.Logger;

@HandlesExceptions
public class AuthorizationExceptionHandler {
	
	public void handleAuthorizationException(@Handles CaughtException<AuthorizationException> evt, Logger log) {
		log.error("You do not have the necessary permissions to perform that operation: " + evt.getException().getCause().getMessage());
		evt.handled();
	}
}
