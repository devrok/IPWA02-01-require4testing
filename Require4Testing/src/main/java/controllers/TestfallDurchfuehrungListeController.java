package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import daos.TestfallDurchfuehrungDAO;
import entities.TestfallDurchfuehrung;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import session.CurrentUser;

@Named
@SessionScoped
public class TestfallDurchfuehrungListeController implements Serializable {

	@Inject
	private CurrentUser currentUser;
	
	@Inject
	private TestfallDurchfuehrungDAO itemDAO;
	private Collection<TestfallDurchfuehrung> items = new ArrayList<TestfallDurchfuehrung>();
	
	public TestfallDurchfuehrungListeController() {
		
	}
	
	public Collection<TestfallDurchfuehrung> getItems() {	
		refreshItems();
		return items;
	}
	
	public void remove(Long id) {
		itemDAO.remove(id);
		
		refreshItems();
	}
	
	private void refreshItems() {
		items = itemDAO.getAllByTester(currentUser.getBenutzerId());
	}
	
}
