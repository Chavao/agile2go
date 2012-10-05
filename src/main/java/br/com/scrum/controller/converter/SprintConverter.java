package br.com.scrum.controller.converter;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.scrum.entity.Sprint;
import br.com.scrum.service.SprintService;

@Named
@RequestScoped
public class SprintConverter implements Converter {
	
	@Inject private SprintService service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if ( value == null || value.trim().equals("") ) {
			return null;
		}
		return service.findById(Integer.parseInt(value));					
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if ( value == null || value.equals("") || ((Sprint) value).getId() == null ) {
			return "";
		}
		return ((Sprint) value).getId().toString();
	}

}
