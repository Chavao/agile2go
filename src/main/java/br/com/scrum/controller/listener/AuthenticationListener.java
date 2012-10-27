package br.com.scrum.controller.listener;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.scrum.controller.UserLogged;

@SuppressWarnings("serial")
public class AuthenticationListener implements PhaseListener
{
	@Inject FacesContext facesContext;

	@Override
	public void afterPhase(PhaseEvent event)
	{
		facesContext = event.getFacesContext();
		if ("/login.jsf".equals(facesContext.getViewRoot().getViewId())) {
			return;
		}
		UserLogged userLogged = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{userLogged}", UserLogged.class);
		
		if (userLogged == null || !userLogged.isLoggedIn()) {
			try {
				facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/main/main.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public PhaseId getPhaseId()
	{
		return PhaseId.RESTORE_VIEW;
	}

	@Override
	public void beforePhase(PhaseEvent arg0)
	{}

}
