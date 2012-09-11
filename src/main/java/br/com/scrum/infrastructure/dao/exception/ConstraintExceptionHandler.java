package br.com.scrum.infrastructure.dao.exception;

import javax.persistence.PersistenceException;

import org.jboss.seam.transaction.SeamApplicationException;
import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;
import org.jboss.solder.logging.Logger;

@HandlesExceptions
@SeamApplicationException(rollback = true)
public class ConstraintExceptionHandler
{
	public void printExceptionMessage(@Handles CaughtException<PersistenceException> event, Logger log) {
		log.error(event.getException().getStackTrace());
		event.handled();
	}

}
