package controllers;

import java.io.Serializable;

import daos.TestfallDurchfuehrungDAO;
import entities.TestfallDurchfuehrung;
import enums.TestfallErgebnisStatus;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class TestfallDurchfuehrungEditController implements Serializable {

	@Inject
	private TestfallDurchfuehrungDAO itemDAO;

	private Long id;
	private TestfallDurchfuehrung item;

	public TestfallDurchfuehrungEditController() {

	}

	@PostConstruct
	public void load() {
		if (this.id != null) {
			TestfallDurchfuehrung origin = itemDAO.findById(this.id);

			this.item = origin != null ? origin : new TestfallDurchfuehrung();

		} else {
			this.item = new TestfallDurchfuehrung();
		}
	}

	public TestfallDurchfuehrung getItem() {
		return item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    public TestfallErgebnisStatus[] getErgebnisStatusValues() {
        return TestfallErgebnisStatus.values();
    }
	

	public String save() {
		itemDAO.save(item);
		return "testfalldurchfuehrungliste";
	}

	public String cancel() {
		return "testfalldurchfuehrungliste";
	}
}
