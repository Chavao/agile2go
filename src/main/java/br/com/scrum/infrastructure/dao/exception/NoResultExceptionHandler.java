package br.com.scrum.infrastructure.dao.exception;

import javax.persistence.NoResultException;

import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;
import org.jboss.solder.logging.Logger;

@HandlesExceptions
public class NoResultExceptionHandler {

	public void noResultExceptionMessage(@Handles CaughtException<NoResultException> event, Logger log) {
		log.error("No entity found: " + event.getException());
		event.handled();
	}
}
