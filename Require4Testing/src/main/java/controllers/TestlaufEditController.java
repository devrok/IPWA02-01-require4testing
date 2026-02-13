package controllers;

import java.io.Serializable;
import java.util.Collection;

import daos.BenutzerDAO;
import daos.TestlaufDAO;
import entities.Anforderung;
import entities.Benutzer;
import entities.TestfallDurchfuehrung;
import entities.Testlauf;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class TestlaufEditController implements Serializable {
	@Inject
	private TestlaufDAO itemDAO;

	@Inject
	private BenutzerDAO benutzerDAO;

	private Long id;
	private Testlauf item;
	private Long testerinId;
	private Collection<Benutzer> testerinnen;

	public TestlaufEditController() {

	}

	@PostConstruct
	public void load() {
		initializeItem();
	}

	private void initializeItem() {
		if (this.id != null) {

			Testlauf origin = itemDAO.findById(this.id);

			this.item = origin != null ? origin : new Testlauf();

		} else {
			this.item = new Testlauf();
		}
	}

	public Testlauf getItem() {
		return item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String save() {
		itemDAO.save(item);
		return "testlaufliste";
	}

	public String cancel() {
		return "testlaufliste";
	}

	public Collection<Benutzer> getTesterinnen() {
		return testerinnen;
	}

}