package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import daos.TestlaufDurchfuehrungDAO;
import entities.TestlaufDurchfuehrung;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class TestlaufDurchfuehrungListeController implements Serializable {
	
	@Inject
	private TestlaufDurchfuehrungDAO itemDAO;
	private Collection<TestlaufDurchfuehrung> items = new ArrayList<TestlaufDurchfuehrung>();
	
	public TestlaufDurchfuehrungListeController() {
		
	}
	
	public Collection<TestlaufDurchfuehrung> getItems() {	
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