package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import daos.TestfallDAO;
import entities.Testfall;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class TestfallListeController implements Serializable {
	
	@Inject
	private TestfallDAO itemDAO;
	private Collection<Testfall> items = new ArrayList<Testfall>();
	
	public TestfallListeController() {
		
	}
	
	public Collection<Testfall> getItems() {	
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