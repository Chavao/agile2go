package br.com.scrum.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.scrum.entity.Sprint;
import br.com.scrum.service.SprintService;

@Named
public class SprintConverter implements Converter
{
	@Inject private SprintService service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value)
	{
		return value == null || value.trim().equals("") ? 
				null : service.findById(Integer.parseInt(value));					
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value)
	{
		return value == null || value.equals("") || ((Sprint) value).getId() == null ?
				"" : ((Sprint) value).getId().toString();
	}

}
