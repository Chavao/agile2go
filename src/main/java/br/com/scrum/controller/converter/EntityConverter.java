package br.com.scrum.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rafael
 * 
 */
@FacesConverter("entityConverter")
public class EntityConverter implements Converter
{
	private Logger logger = LoggerFactory.getLogger(EntityConverter.class);
	@Inject private EntityManager em;
	
	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object object)
	{
		if (object == null) return null;
		
		try {
			Class<?> classe = object.getClass();
			Long id = (Long) classe.getMethod("getId").invoke(object);
			
			return classe.getName() + "-" + id;
		} catch (Exception e) {
			logger.error("Error converting entity to String", e);
			return null;
		}
		
	}
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String string)
	{
		if (string == null || string.isEmpty()) return null;
		
		try {
			String[] values = string.split("-");
			return em.find(Class.forName(values[0]), Long.valueOf(values[1]));
		} catch (Exception e) {
			logger.error("Error converting String to entity", e);
			return null;
		}
	}


}
