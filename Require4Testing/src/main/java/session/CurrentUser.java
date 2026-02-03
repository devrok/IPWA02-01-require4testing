package session;


import entities.Benutzer;
//import entities.Rolle;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class CurrentUser implements Serializable {
	
	private Benutzer benutzer;
	
	public CurrentUser() {	
	}
	
	public void setCurrentUser(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	public String getRollenBezeichnung() {
		return benutzer.getRolle().getBezeichnung();
	}

	public String getUsername() {
		return benutzer.getName();
	}
	
	public Long getBenutzerId() {
		return benutzer.getId();
	}
	
	public void reset() {
		this.benutzer = null;
	}
	
	public boolean isValid() {
		return benutzer != null;
	}
	
	public boolean isRequirementEngineer() {
		return checkRolleById(1L);
	}
	
	public boolean isTestManager() {
		return checkRolleById(2L);
	}
	
	public boolean isTestFallErsteller() {
		return checkRolleById(3L);	
	}
	
	public boolean isTester() {
		return checkRolleById(4L);
	}
	
	private boolean checkRolleById(Long rolleId) {
		return benutzer.getRolle().getId() == rolleId;
	}
}