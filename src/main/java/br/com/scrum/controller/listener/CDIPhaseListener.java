package br.com.scrum.controller.listener;

import javax.enterprise.event.Observes;
import javax.faces.event.PhaseEvent;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.jboss.seam.faces.event.qualifier.After;
import org.jboss.seam.faces.event.qualifier.Before;

public class CDIPhaseListener
{
	@Inject Logger LOG;
	
	public void before(@Observes @Before PhaseEvent event)
	{
		LOG.debug("START " + event.getPhaseId());
	}
	
	public void after(@Observes @After PhaseEvent event) 
	{
		LOG.debug("END " + event.getPhaseId());
	}
	
}
