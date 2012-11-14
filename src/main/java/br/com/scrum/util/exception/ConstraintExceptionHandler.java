//package br.com.scrum.util.exception;
//
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.inject.Inject;
//import javax.persistence.PersistenceException;
//
//import org.jboss.logging.Logger;
//import org.jboss.seam.transaction.SeamApplicationException;
//import org.jboss.solder.exception.control.CaughtException;
//import org.jboss.solder.exception.control.Handles;
//import org.jboss.solder.exception.control.HandlesExceptions;
//import org.jboss.solder.exception.control.TraversalMode;
//
//@HandlesExceptions
//@SeamApplicationException(rollback = true)
//public class ConstraintExceptionHandler
//{
//	@Inject FacesContext facesContext;
//	@Inject Logger LOG;
//	
//	public void printExceptionMessage(@Handles(during = TraversalMode.BREADTH_FIRST, precedence = 100)
//									  CaughtException<PersistenceException> event)
//	{
//		LOG.error(event.getException());
//		event.handled();
//		
//		FacesMessage message =  new FacesMessage("project already exist");
//		message.setSeverity(FacesMessage.SEVERITY_ERROR);
//		facesContext.addMessage("", message);
//	}
//
//}
