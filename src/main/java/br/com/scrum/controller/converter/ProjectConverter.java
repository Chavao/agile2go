package br.com.scrum.controller.converter;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.scrum.entity.Project;
import br.com.scrum.service.ProjectService;

@Named
@RequestScoped
public class ProjectConverter implements Converter
{
	@Inject private ProjectService service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value)
	{
		return value == null || value.trim().isEmpty() ? 
			null : service.withId(Integer.parseInt(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value)
	{
		return value == null || value.equals("") || ((Project) value).getId() == null ? 
				"" : ((Project)value).getId().toString();			
	}
}
