package controllers;

import java.io.Console;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.faces.model.SelectItem;

import daos.AnforderungDAO;
import daos.TestfallDAO;
import entities.Anforderung;
import entities.Testfall;
import entities.TestfallDurchfuehrung;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class TestfallEditController implements Serializable {
	@Inject
	private TestfallDAO itemDAO;

	@Inject
	AnforderungDAO anforderungDAO;

	private Long id;
	private Testfall item;
	private Long anforderungId;
	private Collection<Anforderung> anforderungen;

	public TestfallEditController() {

	}

	@PostConstruct
	public void load() {
		initializeItem();
		initializeAnforderungen();
	}

	private void initializeItem() {
		if (this.id != null) {
			Testfall origin = itemDAO.findById(this.id);

			this.item = origin != null ? origin : new Testfall();

		} else {
			this.item = new Testfall();
		}
	}

	private void initializeAnforderungen() {
		anforderungen = anforderungDAO.getAll();
	}

	public Testfall getItem() {
		return item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String save() {

		String anforderung = item.getAnforderung() == null ? "none" : item.getAnforderung().getTitel();

		System.out.println("Save Testfall. Anforderung: " + anforderung);

		itemDAO.save(item);
		return "testfallliste";
	}

	public String cancel() {
		return "testfallliste";
	}

	public Collection<Anforderung> getAnforderungen() {
		return anforderungen;
	}

	public Long getAnforderungId() {
		if (item.getAnforderung() == null) {
			anforderungId = null;
		} else {
			anforderungId = item.getAnforderung().getId();
		}

		return anforderungId;
	}

	public void setAnforderungId(Long anforderungId) {
		this.anforderungId = anforderungId;
		if (anforderungId == null) {
			this.item.setAnforderung(null);
		} else {
			this.item.setAnforderung(this.anforderungDAO.findById(anforderungId));
		}
	}
}