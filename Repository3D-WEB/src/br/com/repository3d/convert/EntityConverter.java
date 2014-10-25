package br.com.repository3d.convert;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.repository.entidades.AbstractEntity;

@FacesConverter(value = "entityConverter", forClass = AbstractEntity.class)
public class EntityConverter implements Converter {
 
    public Object getAsObject(FacesContext ctx, UIComponent component,
            String value) {
        if (value != null) {
            return this.getAttributesFrom(component).get(value);
        }
        return null;
    }
 
    public String getAsString(FacesContext ctx, UIComponent component,
            Object value) {
 
        if (value != null && ! "".equals(value)) {
            AbstractEntity entity = (AbstractEntity) value;
 
            if (entity.getIdentificado() != null) {
                this.addAttribute(component, entity);
 
                if (entity.getIdentificado() != null) {
                    return String.valueOf(entity.getIdentificado());
                }
                return (String) value;
            }
        }
        return "";
    }
 
    private void addAttribute(UIComponent component, AbstractEntity o) {
        this.getAttributesFrom(component).put(o.getIdentificado().toString(), o);
    }
 
    private Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }
 
} 