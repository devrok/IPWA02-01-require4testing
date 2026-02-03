import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "statusConverter", managed = true)
public class StatusConverter implements Converter<Integer> {

	@Override
	public Integer getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        // value kommt aus dem SelectOneMenu -> ID
        return Integer.valueOf(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Integer value) {
		if (value == null) {
            return "";
        }
        // ID -> Label
        return Status.fromId(value).getLabel();
	}
	
}