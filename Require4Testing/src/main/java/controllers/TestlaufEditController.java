package controllers;

import java.io.Serializable;
import java.util.Collection;

import daos.BenutzerDAO;
import daos.TestlaufDAO;
import entities.Anforderung;
import entities.Benutzer;
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
//		initializeTester();
	}

	private void initializeItem() {
		if (this.id != null) {

			Testlauf origin = itemDAO.findById(this.id);

			if (origin == null) {
				this.item = new Testlauf();
			} else {

				this.item = origin.clone();
			}

		} else {
			this.item = new Testlauf();
		}
	}

//	private void initializeTester() {
//		testerinnen = benutzerDAO.findByRollenName("Tester:in");
//	}

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
//		// TODO: noch bei anderen
//		String testerin = item..getTesterin() == null ? "none" : item.getTesterin().getName();
//
//		System.out.println("Save Testfall. Testerin: " + testerin);

		itemDAO.save(item);
		return "testlaufliste";
	}

	public String cancel() {
		return "testlaufliste";
	}

	public Collection<Benutzer> getTesterinnen() {
		return testerinnen;
	}

//	public Long getTesterinId() {
//		if (item.getTesterin() == null) {
//			testerinId = null;
//		} else {
//			testerinId = item.getTesterin().getId();
//		}
//
//		return testerinId;
//	}
//
//	public void setTesterinId(Long testerinId) {
//		this.testerinId = testerinId;
//		if (testerinId == null) {
//			this.item.setTesterin(null);
//		} else {
//			this.item.setTesterin(this.benutzerDAO.findById(testerinId));
//		}
//	}
}