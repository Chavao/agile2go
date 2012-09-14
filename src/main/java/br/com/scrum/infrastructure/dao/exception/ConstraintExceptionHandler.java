//package br.com.scrum.infrastructure.dao.exception;
//
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.inject.Inject;
//
//import org.hibernate.exception.ConstraintViolationException;
//import org.jboss.logging.Logger;
//import org.jboss.seam.transaction.SeamApplicationException;
//import org.jboss.solder.exception.control.CaughtException;
//import org.jboss.solder.exception.control.Handles;
//import org.jboss.solder.exception.control.HandlesExceptions;
//
//@HandlesExceptions
//@SeamApplicationException(rollback = true)
//public class ConstraintExceptionHandler
//{
//	@Inject FacesContext facesContext;
//	
//	public void printExceptionMessage(@Handles CaughtException<ConstraintViolationException> event, Logger log) {
//		log.error(event.getException().getStackTrace());
//		event.handled();
//		
//		FacesMessage message =  new FacesMessage("project already exist");
//		message.setSeverity(FacesMessage.SEVERITY_ERROR);
//		facesContext.addMessage("", message);
//	}
//
//}
