package br.com.scrum.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.scrum.entity.Task;
import br.com.scrum.service.TaskService;

@Named
public class TaskConverter implements Converter
{
	
	@Inject private TaskService service;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value)
	{
		return value == null || value.trim().isEmpty() ?
				"" : service.findById(Integer.parseInt(value));					
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value)
	{
		return value == null || value.equals("") || ((Task) value).getId() == null ?
				"" : ((Task) value).getId().toString();
	}

}
