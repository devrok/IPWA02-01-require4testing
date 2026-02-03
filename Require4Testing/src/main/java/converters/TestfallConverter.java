package converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import entities.Testfall;

@FacesConverter("testfallConverter")
public class TestfallConverter implements Converter<Testfall> {

    @Override
    public Testfall getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
        	return null;
        }
        
        Long id = Long.valueOf(value);
        Testfall testfall = new Testfall();
        testfall.setId(id);  
        
        return testfall;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Testfall object) {
        if (object == null) {
        	return "";
        }
        
        return object.getId().toString();
    }
}

