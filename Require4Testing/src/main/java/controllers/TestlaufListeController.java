package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import daos.TestlaufDAO;
import entities.Testlauf;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class TestlaufListeController implements Serializable {
	
	@Inject
	private TestlaufDAO itemDAO;
	private Collection<Testlauf> items = new ArrayList<Testlauf>();
	
	public TestlaufListeController() {
		
	}
	
	public Collection<Testlauf> getItems() {	
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