import java.util.ArrayList;
import java.util.List;
import jakarta.faces.model.SelectItem;

public enum Status {

	UNBEKANNT(0, "Unbekannt"), NEU(1, "Neu"), VERFUEGBAR(2, "Verfügbar");

	private final int id;
	private final String label;

	Status(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static Status fromId(Integer id) {
		if (id == null)
			return UNBEKANNT;
		for (Status s : values()) {
			if (s.id == id)
				return s;
		}
		return UNBEKANNT;
	}

	private static List<SelectItem> selectItems;

	public static List<SelectItem> getSelectItems() {

		if (selectItems.isEmpty()) {
			selectItems = new ArrayList<SelectItem>();

			for (Status status : values()) {
				selectItems.add(new SelectItem(status.getId(), status.getLabel()));
			}			
		}

		return selectItems;
	}
}
