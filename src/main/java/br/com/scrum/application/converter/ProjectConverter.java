package br.com.scrum.application.converter;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.scrum.domain.entity.Project;
import br.com.scrum.domain.service.ProjectService;

@Named
@RequestScoped
public class ProjectConverter implements Converter {
	
	@Inject private ProjectService service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if ( value == null || value.trim().isEmpty() ) {
			return null;
		}		
		return service.withId(Integer.parseInt(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if ( value == null || value.equals("") || ((Project) value).getId() == null ) { 
			return "";		
		}		
		return ((Project) value).getId().toString();			
	}
}
