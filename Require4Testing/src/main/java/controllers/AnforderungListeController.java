package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import daos.AnforderungDAO;
import entities.Anforderung;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class AnforderungListeController implements Serializable {
	
	@Inject
	private AnforderungDAO itemDAO;
	private Collection<Anforderung> items = new ArrayList<Anforderung>();
	
	public AnforderungListeController() {
		
	}
	
	public Collection<Anforderung> getItems() {	
		refreshItems();
		return items;
	}
	
	public void remove(Long id) {
		itemDAO.remove(id);
		
		refreshItems();
	}
	
	private void refreshItems() {
		items = itemDAO.getAll();
	}
	
}