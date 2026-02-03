package controllers;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import session.CurrentUser;

@Named
@SessionScoped
public class MainController implements Serializable {

	@Inject 
	private CurrentUser currentUser;
	
	public MainController() {
		
	}

	public CurrentUser getCurrentUser() {
		return currentUser;
	}

	public String logout() {
		currentUser.reset();
		return "login";
	}
	
	public String dummy() {
		return "main";
	}
	
	// --- Requirement Engineer
	
	public String anforderungen() {
		return "anforderung/anforderungliste";
	}
	
	// --- Requirement Engineer
	
	// --- Testmanager:in
	public String testlaeufe() {
		return "testlauf/testlaufliste";
	}
	public String testlaufDurchfuehrungen() {
		return "testlaufdurchfuehrung/testlaufdurchfuehrungliste";
	}
	// --- Testmanager:in
	
	// --- Testfallersteller:in
	public String testfaelle() {
		return "testfall/testfallliste";
	}
	// --- Testfallersteller:in
	
	// --- Tester:in
	public String testfallDurchfuehrungen() {
		return "testfalldurchfuehrung/testfalldurchfuehrungliste";
	}
	// --- Tester:in
	
}
