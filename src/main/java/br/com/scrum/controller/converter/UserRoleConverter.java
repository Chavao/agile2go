package br.com.scrum.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.com.scrum.entity.enums.UserRole;

@Named
public class UserRoleConverter implements Converter
{
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value)
	{
		if ( value == null || value.trim().isEmpty() ) {
			return null;
		}
		for (UserRole u : UserRole.values()) {
			if (u.getCode().equalsIgnoreCase(value)) {
				return u;
			}
		}		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value)
	{
		return value == null || value.equals("") ? "" : ((UserRole)value).getCode();		
	}
	
}
