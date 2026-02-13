package controllers;

import java.io.Serializable;

import daos.AnforderungDAO;
import entities.Anforderung;
import entities.TestfallDurchfuehrung;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class AnforderungEditController implements Serializable {
	@Inject
	private AnforderungDAO itemDAO;

	private Long id;
	private Anforderung item;
	
	public AnforderungEditController() {
		
	}
	
	@PostConstruct
	public void load() {
	    if (this.id != null) {
	    	Anforderung origin = itemDAO.findById(this.id);
	    	
	    	this.item = origin != null ? origin : new Anforderung();
	    	
	    } else {
	    	this.item = new Anforderung();
	    }
	}

	public Anforderung getItem() {
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
		return "anforderungliste";
	}
	
	public String cancel() {
		return "anforderungliste";
	}
}
