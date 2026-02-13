package utilities;

import java.io.Serializable;

import daos.BenutzerDAO;
import entities.Benutzer;
import entities.Rolle;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import session.CurrentUser;

@Named
@SessionScoped
public class LoginValidator implements Serializable {
	
	@Inject
	private BenutzerDAO benutzerDAO;
	
	public LoginValidator() {
		
	}
	
	public void validateLogin(CurrentUser currentUser, String inputUsername, String inputPassword) {
		currentUser.reset();
		
		// 
		String inputHash = HashCreator.createHash(inputPassword, "");
		
		for(Benutzer benutzer : benutzerDAO.getAll()) {
			if (benutzer.getName().equals(inputUsername)) {
				if (benutzer.getHash().equals(inputHash)) {
					currentUser.setCurrentUser(benutzer);
					if (currentUser.isValid()) {
						System.out.println("##### User found: " + currentUser.getUsername() + " #####");
						System.out.println("##### Rolle: " + currentUser.getRollenBezeichnung() + " #####");
						break;
					}
				}
			}
		}
	}
}
